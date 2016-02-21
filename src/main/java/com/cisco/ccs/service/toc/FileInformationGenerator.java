package com.cisco.ccs.service.toc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.models.FileMaster;

import net.sf.json.JSONObject;

public class FileInformationGenerator {
	
	public FileMaster getFileInformation(String opfFilePath, String containerDirPath, FileMaster fileMaster) throws ParserConfigurationException, SAXException, IOException {
		
		JSONObject fileInfoJSON = new JSONObject();
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        InputStream inputStream = new FileInputStream(containerDirPath+"/"+opfFilePath);
        parser.parse(inputStream, handler);
        
        fileMaster.setFileTitle(handler.Title);
        fileMaster.setPublisher(handler.Publisher);
        fileMaster.setLocale(handler.Language);
        fileMaster.setPartNumber(handler.Identifier);
        fileMaster.setLastModifiedDate(handler.Date);
		return fileMaster;
	}
}

//SAX Events
class SAXHandler extends DefaultHandler {
	String Title = null;
	String Publisher = null;
	String Language = null;
	String Date = null;
	String Identifier = null;
	String content = null;
	
	@Override
	//Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(qName){
		  case "dc:title":
			  Title = content;
			   break;
		  case "dc:language":
			   Language = content;
			   break;
		  case "dc:publisher":
			   Publisher = content;
			   break;
		  case "dc:identifier":
			   Identifier = content;
			   break;
		  case "dc:date":
			   Date = content;
			   break;
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
	       throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}
}

