package com.cisco.ccs.service.toc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cisco.ccs.controller.list.LinearListGenerator;
import com.cisco.ccs.controller.list.OrderListGenerator;
import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.models.FileMaster;
import com.cisco.ccs.util.FileManagerUtil;
import com.cisco.ccs.util.unZipper;

import net.sf.json.JSONObject;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;



@Service(TOCGenerateService.BEAN_ID)
@WebService(portName = "ContentServicePort", endpointInterface = "com.cisco.ple.service.toc.TOCGenerateService")
@InInterceptors(interceptors = { "com.cisco.ple.filter.ContentServiceFualtInterceptor" })

public class TOCGeneratorServiceImpl implements TOCGenerateService {
	
	 public void unzipArchive(File archive, File outputDir) {
	        try {
	            ZipFile zipfile = new ZipFile(archive);
	            for (Enumeration e = zipfile.getEntries(); e.hasMoreElements(); ) {
	                ZipEntry entry = (ZipEntry) e.nextElement();
	                unzipEntry(zipfile, entry, outputDir);
	            }
	        } catch (Exception e) {
//	            System.out.println("Errore extracting file " + archive, e);
	        }
	    }

	    private void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir) throws IOException {


	        if (entry.isDirectory()) {
	            createDir(new File(outputDir, entry.getName()));
	            return;
	        }
	        
	        File outputFile = new File(outputDir, entry.getName());
	        if (!outputFile.getParentFile().exists()){
	            createDir(outputFile.getParentFile());
	        }

	        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream((ZipArchiveEntry) entry));
	        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

	        try {
	            IOUtils.copy(inputStream, outputStream);
	        } finally {
	            outputStream.close();
	            inputStream.close();
	        }
	    }

	    private void createDir(File dir) {
	        if(!dir.mkdirs()) throw new RuntimeException("Can not create dir "+dir);
	    }

private boolean epubUnzipper(String epubPath) {
		    
		    String destinationname = FilenameUtils.removeExtension(epubPath);
            unzipArchive(new File(epubPath), new File(destinationname));
		return true;
	}
	

private String getOPFContent(String containerDirPath) throws IOException, SAXException, ParserConfigurationException {
	

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(new File(containerDirPath+"/META-INF/container.xml"));
    doc.getDocumentElement().normalize();
    String opfFilePath = doc.getDocumentElement().getElementsByTagName("rootfile").item(0).getAttributes().getNamedItem("full-path").getNodeValue();
	return opfFilePath;

}
	public boolean generateTOC(String baseFileServerDirPath, String contentSourceDirPath, String containerFilePath, 
			String contentDesignationDirPath, String epubFileName, String fileFolderPath, String fileType) {
		
		//String epubFilePath = baseFileServerDirPath+contentSourceDirPath+epubFileName+".epub";
		File epubFilePath = new File(baseFileServerDirPath+contentSourceDirPath+epubFileName+"."+fileType);
		unZipper unZipperObj = new unZipper();
		
		// epub unzip functions
		if(unZipperObj.epubUnzipper(epubFilePath)) {
			System.out.println("unzip - success");
			try {
				FileManagerUtil fileManagerUtil = new FileManagerUtil();
				//String destinationFileFolderPath = FilenameUtils.removeExtension(epubFilePath.toString());
				String opfFilePath = fileManagerUtil.getOPFContent(epubFilePath, containerFilePath);
				LinearListGenerator mLinearListGenerator = new LinearListGenerator();
				
				String destinationFolder = FilenameUtils.removeExtension(epubFileName);
				String destinationPathExt = FilenameUtils.getPath(opfFilePath);
				
				String[] mlinearList = mLinearListGenerator.generateLinearList(opfFilePath, fileFolderPath, destinationFolder +"/"+destinationPathExt);
				OrderListGenerator mOrderListGenerator = new OrderListGenerator();
				String[] mOrderList = mOrderListGenerator.generateOrderList(mlinearList[1], fileFolderPath, opfFilePath, destinationFolder +"/"+destinationPathExt);
				
				String destinationname = FilenameUtils.removeExtension(epubFileName);
				File file = new File(baseFileServerDirPath+contentDesignationDirPath+destinationname+".json");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(mOrderList[0]+"\n");
				bw.write(mlinearList[0]+"\n");
				bw.write("["+mOrderList[1]+"]\n");
				bw.write("[{\"EPubTitle\":\""+mlinearList[2]+"\"}]");
				bw.close();
				System.out.println(destinationname+"-----Done");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

	
	public FileMaster getFTPInformationAsObject(String contentSourceDirPath, String containerFilePath, String epubFileName) {
		File epubFilePath = new File(contentSourceDirPath+epubFileName);
		if(epubUnzipper(epubFilePath.toString())) {
			System.out.println("unzip - success");
				String destinationFolder = FilenameUtils.removeExtension(epubFilePath.toString());

				try {
					String opfFilePath = getOPFContent(destinationFolder);
					FileInformationGenerator mFileInformationGenerator = new FileInformationGenerator();
					FileMaster fileMaster = new FileMaster(); 
					fileMaster = mFileInformationGenerator.getFileInformation(opfFilePath, destinationFolder, fileMaster);
			
					FileUtils.deleteDirectory(new File(destinationFolder));

					return fileMaster;
				} catch (IOException | SAXException | ParserConfigurationException e) {
					e.printStackTrace();
				}
		}else return null;
		return null;
	}

}

