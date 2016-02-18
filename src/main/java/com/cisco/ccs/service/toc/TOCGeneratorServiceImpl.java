package com.cisco.ccs.service.toc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.cisco.ccs.controller.list.LinearListGenerator;
import com.cisco.ccs.controller.list.OrderListGenerator;
import com.cisco.ccs.util.FileManagerUtil;
import com.cisco.ccs.util.unZipper;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;



@Service(TOCGenerateService.BEAN_ID)
@WebService(portName = "ContentServicePort", endpointInterface = "com.cisco.ple.service.toc.TOCGenerateService")
@InInterceptors(interceptors = { "com.cisco.ple.filter.ContentServiceFualtInterceptor" })

public class TOCGeneratorServiceImpl implements TOCGenerateService {
	
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
	
	public JSONObject getFTPInfoAsJSON(String baseFileServerDirPath, String contentSourceDirPath, String containerFilePath, 
			String contentdestinationDirPath, String epubFileName, String fileType) {
		JSONObject ftpInfoObject = new JSONObject();
		FileManagerUtil fileManager = new FileManagerUtil();
		File sourcePath = new File(baseFileServerDirPath+contentSourceDirPath+epubFileName+"."+fileType);
		String FTPContentPath = fileManager.getFTPContentPath(sourcePath, containerFilePath);
		return ftpInfoObject;
	}
}

