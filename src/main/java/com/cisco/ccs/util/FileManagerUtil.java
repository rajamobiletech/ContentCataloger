package com.cisco.ccs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileManagerUtil {
	
	public String getOPFContent(File epubPath, String containerFilePath) throws IOException, SAXException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        String destinationname = FilenameUtils.removeExtension(epubPath.toString());
        Document doc = dBuilder.parse(destinationname);
        doc.getDocumentElement().normalize();
        String opfFilePath = doc.getDocumentElement().getElementsByTagName("rootfile").item(0).getAttributes().getNamedItem("full-path").getNodeValue();
		return opfFilePath;
	}
	
	public String getFTPContentPath(File epubFilePath, String containerFilePath) {
		
		unZipper unZipperObj = new unZipper();
		
		String opfFilePath = "";
		
		if(unZipperObj.epubUnzipper(epubFilePath)) {
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
