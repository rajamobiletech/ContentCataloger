package com.cisco.ccs.controller.cataloger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ccs.service.content.ContentService;

import net.sf.json.JSONObject;

import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.service.toc.TOCGenerateService;
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
        modelMap.addAttribute("searchOrderResults", contentService.getFTPListAsJSON(ftpList).toString());        
        JSONObject dataObject = contentService.getFTPListAsJSON(ftpList);
        try {
            modelMap.addAttribute("searchOrderResults", dataObject.toString());
            writeSearchResultToResponse(dataObject, response, ftpSearchCriteria);
    
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/checksumForm")
    public String checksum(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	
    	String selectedFTPName = request.getParameter("selectedFTP");
    	System.out.println("Validatechecksum servlet method = "+selectedFTPName);
    	String[] fileNames = selectedFTPName.split("\\|");
    	tocGenerateService.getFTPInfoAsJSON("/Users/rajad/Desktop/", "ftp-docs/", "/META-INF/container.xml", "sharedepubs/catalog/", fileNames[1], "epub");
        return "checksumform";
    }
    
    public FTPSearchCriteria setFTPSearchCriteria(HttpServletRequest request, FTPSearchCriteria searchCriteria) {
        String fileName = request.getParameter("fileName");
        searchCriteria.setFileName(fileName);
        return searchCriteria;
    }

}
