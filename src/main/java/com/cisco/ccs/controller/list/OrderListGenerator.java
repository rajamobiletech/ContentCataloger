
package com.cisco.ccs.controller.list;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

public class OrderListGenerator {
	public String[] generateOrderList(String tocFilePath, String containerDirPath, String mOPFPath, String srcMainPath) throws ParserConfigurationException, SAXException, IOException {
		String destinationPathExt = FilenameUtils.getPath(mOPFPath);
		File file = new File(containerDirPath+"/"+destinationPathExt+tocFilePath);
		Document doc = null;
		String[] TOC = null;
		
			try {
				doc = Jsoup.parse(file, "utf8");
				Element outerOLElement = doc.getElementsByTag("nav").first().getElementsByTag("ol").first();
				Elements outerLIElements = outerOLElement.children();
				
				TOC = treeMaker(outerLIElements,"",1, srcMainPath);
			}catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("File not found in path : " + containerDirPath+"/html/"+tocFilePath);
			}
			return TOC;
	}

public String[] treeMaker(Elements lis,String titleArr, int count, String srcMainPath) {
	String tocJSON = "[";
	for(Element li:lis) {
		Element a =	li.getElementsByTag("a").first();	
		Element ol = li.getElementsByTag("ol").first();
		String subtoc = "[]";
		String order = count+"";
		count++;
		
		if(!titleArr.isEmpty()) titleArr = titleArr.concat(",");
		titleArr = titleArr.concat("\"{\\\"title\\\":\\\""+a.text()+"\\\",\\\"src\\\":\\\""+srcMainPath+a.attr("href")+"\\\"}\"");

		if(ol != null) {
			String[] output_subtoc = treeMaker(ol.children(),titleArr,count, srcMainPath);
			subtoc = output_subtoc[0];
			titleArr = output_subtoc[1];
			count = Integer.parseInt(output_subtoc[2]);
		}
		
		if(!tocJSON.equals("["))
			tocJSON = tocJSON.concat(",");
		tocJSON = tocJSON.concat("{\"title\":\""+a.text()+"\",\"src\":\""+srcMainPath+a.attr("href")+"\",\"order\":\""+order+"\", \"subTOC\":"+subtoc+"}");
	}

	return new String[] { tocJSON.concat("]"), titleArr , count+"" };
	}
}
