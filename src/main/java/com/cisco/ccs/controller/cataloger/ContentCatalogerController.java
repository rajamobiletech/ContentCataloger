package com.cisco.ccs.controller.cataloger;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.ccs.service.content.ContentService;

import net.sf.json.JSONObject;

import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.models.FileMaster;
import com.cisco.ccs.service.toc.TOCGenerateService;
import com.cisco.ccs.util.CheckSumGenerator;
import com.cisco.ccs.constants.CCSConstantUtil;
import com.cisco.ccs.controller.CCSBaseController;
import com.cisco.ccs.model.FTPSearchCriteria;

@Controller(value = "contentCatalogerController")
@RequestMapping(value = { "/", "/ccs" })
@SessionAttributes(types = ContentCatalogerController.class)
public class ContentCatalogerController extends CCSBaseController {
	
    @Autowired
    @Qualifier("contentService")
    ContentService contentService;
    
    @Autowired
    @Qualifier("TOCService")
    TOCGenerateService tocGenerateService;
    
    FileMaster fileMaster;
    
    @RequestMapping(value = "/contentCataloger")
    public String contentCataloger(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        return "contentcateloger";
    }

	@RequestMapping(value = "/getDefaultFTPSearch")
    public void geDefaultOrderSearch(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

		final File folder = new File("/Users/rajad/Desktop/ftp-docs");
        String userId = request.getRemoteUser();
        List<FTPMaster> ftpList = null;
        response.setContentType(CCSConstantUtil.CONTENT_TYPE_APPLICATION_JSON);

        FTPSearchCriteria ftpSearchCriteria = new FTPSearchCriteria();
        ftpSearchCriteria = setFTPSearchCriteria(request, ftpSearchCriteria);
        ftpSearchCriteria = (FTPSearchCriteria) initSearchCriteriaBean(ftpSearchCriteria, request);
        ftpSearchCriteria.setDefaultSearch(true);
        response.setContentType(CCSConstantUtil.CONTENT_TYPE_APPLICATION_JSON);
        ftpSearchCriteria.setTotalRecords(contentService.getDefaultCount(userId, folder, ftpSearchCriteria));
    	ftpList = contentService.getFTPList(userId, folder, ftpSearchCriteria);	
        JSONObject dataObject = contentService.getFTPListAsJSON(ftpList);
        try {
            modelMap.addAttribute("searchOrderResults", dataObject.toString());
            writeSearchResultToResponse(dataObject, response, ftpSearchCriteria);
    
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/checksumForm")
    public ModelAndView checksum(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	String selectedFTPName = request.getParameter("selectedFTP");
    	System.out.println("checksumForm = "+selectedFTPName);
    	String[] fileNames = selectedFTPName.split("\\|");
    	fileMaster = tocGenerateService.getFTPInformationAsObject("/Users/rajad/Desktop/ftp-docs/", "/META-INF/container.xml", fileNames[1]);
    	fileMaster.setFileName(fileNames[1]);
    	//System.out.println("File Information = "+dataObject.toString());
    	
    	final File file = new File("/Users/rajad/Desktop/ftp-docs/"+fileMaster.getFileName());
   	 
    	String fileName = file.getName();
    	int i = fileName.lastIndexOf('.');
        if (i > 0) {
        	fileMaster.setFileType(fileName.substring(i+1));
        }
        double bytes = file.length();
		double kilobytes = (bytes / 1024);
		double mFileSizeMB = (kilobytes / 1024);
		DecimalFormat df = new DecimalFormat("#.##");      
		mFileSizeMB = Double.valueOf(df.format(mFileSizeMB));	  
		fileMaster.setFileSize(mFileSizeMB);
        fileMaster.setFileName(fileName);
        
    	//Use MD5 algorithm
    	MessageDigest md5Digest;
    	String checksum = null;
    	CheckSumGenerator checkSumGenerator = new CheckSumGenerator();
		try {
			md5Digest = MessageDigest.getInstance("MD5");
			//Get the checksum    	
	    	 checksum = checkSumGenerator.getFileChecksum(md5Digest, file);
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
    	fileMaster.setChecksum(checksum);
    	
    	//see checksum
    	System.out.println(checksum);
    	
        return new ModelAndView("checksumform", "FileInfo", fileMaster);
    }
    
    @RequestMapping(value = "/checksumValidation")
    public ModelAndView checksumValidation(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	String userChecksum = request.getParameter("userChecksum");
    	if(userChecksum.equals(fileMaster.getChecksum())) {
    		return new ModelAndView("catalogoptionform", "FileInfo", fileMaster);
    	}
    	return new ModelAndView("checksumform", "FileInfo", fileMaster);
    }
    
    
    @RequestMapping(value = "/contentCatalog/catalogNewContent")
    public Boolean catalogNewContent(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("catalogNewContent====????????????????");
    	return true;
    }
    
    
    
    public FTPSearchCriteria setFTPSearchCriteria(HttpServletRequest request, FTPSearchCriteria searchCriteria) {
        String fileName = request.getParameter("fileName");
        searchCriteria.setFileName(fileName);
        return searchCriteria;
    }

}
