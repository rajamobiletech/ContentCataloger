package com.cisco.ccs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileManagerUtil {
	
	
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

	    
	public String getOPFContent(File epubPath, String containerFilePath) throws IOException, SAXException, ParserConfigurationException {
        String destinationname = FilenameUtils.removeExtension(epubPath.toString());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(destinationname));
        doc.getDocumentElement().normalize();
        String opfFilePath = doc.getDocumentElement().getElementsByTagName("rootfile").item(0).getAttributes().getNamedItem("full-path").getNodeValue();
		return opfFilePath;

//		
//		
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(destinationname);
//        doc.getDocumentElement().normalize();
//        String opfFilePath = doc.getDocumentElement().getElementsByTagName("rootfile").item(0).getAttributes().getNamedItem("full-path").getNodeValue();
//		return opfFilePath;
	}
	
	
	private boolean epubUnzipper(String epubPath) {
	    
	    String destinationname = FilenameUtils.removeExtension(epubPath);
        unzipArchive(new File(epubPath), new File(destinationname));
        return true;
	}
	
	public String getFTPContentPath(File epubFilePath, String containerFilePath) {
		
		unZipper unZipperObj = new unZipper();
		
		String opfFilePath = "";
		
		if(epubUnzipper(epubFilePath.toString())) {
			FileManagerUtil fileManagerUtil = new FileManagerUtil();	
				try {
					opfFilePath = fileManagerUtil.getOPFContent(epubFilePath, containerFilePath);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
		}
		return opfFilePath;
	}
	
	public void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
