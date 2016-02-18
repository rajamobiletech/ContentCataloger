package com.cisco.ccs.controller.list;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cisco.ccs.models.Manifest;
import com.cisco.ccs.models.Spine;

public class LinearListGenerator {
	
	public String[] generateLinearList(String opfFilePath, String containerDirPath, String srcMainPath) throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        InputStream inputStream = new FileInputStream(containerDirPath+"/"+opfFilePath);
        parser.parse(inputStream, handler);
        
        String linearList = "[";
        for (Spine mSpineObj : handler.spineList){
        	for(Manifest mManiObj : handler.manifestList){
        		if(mSpineObj.getIdref().equals(mManiObj.getId())) {
        			if(!linearList.equals("[")) linearList = linearList.concat(",");
        			linearList = linearList.concat("{\"id\":\""+mSpineObj.getIdref()+"\",\"src\":\""+srcMainPath+mManiObj.getHref()+"\",\"linear\":\""+mSpineObj.getLinear()+"\"}");
        			break;
        		}
        	}
        }
        linearList = linearList.concat("]");
        ArrayList<String> linearArray=new ArrayList<String>();
        linearArray.add(0, linearList);
        linearArray.add(1, handler.mTOCPath);
		return new String[] {linearList, handler.mTOCPath, handler.mTitle};
	}
}

//SAX Events
class SAXHandler extends DefaultHandler {
	List<Manifest> manifestList = new ArrayList<Manifest>();
	List<Spine> spineList = new ArrayList<Spine>();
	String mTOCPath = null;
	String mTitle = null;
	Manifest mManifest = null;
	Spine mSpine = null;
	String content = null;
	
	@Override
	//Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	 switch(qName){
	   case "item":
	 	  mManifest = new Manifest();
	 	  mManifest.setHref(attributes.getValue("href"));
	 	  mManifest.setId(attributes.getValue("id"));
	 	  mManifest.setMediaType(attributes.getValue("media-type"));
	 	  mManifest.setProperties(attributes.getValue("properties"));
	 	 if(attributes.getValue("properties") != null && attributes.getValue("properties").equals("nav")) {
	 		mTOCPath = attributes.getValue("href");
	 	 	mManifest.setId("toc");
	 	 }
	     break;
	   case "itemref":
	 	  mSpine = new Spine();
	 	  mSpine.setIdref(attributes.getValue("idref"));
	 	  mSpine.setLinear(attributes.getValue("linear"));
	     break;
	   
	 }
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch(qName){
		  case "item":
		 	 manifestList.add(mManifest); 
		    break;
		  case "itemref":
		 	 spineList.add(mSpine); 
		 	 break;
		  case "dc:title":
			   mTitle = content;
			   break;
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
	       throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}
}

