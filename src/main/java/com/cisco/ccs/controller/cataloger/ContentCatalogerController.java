package com.cisco.ccs.controller.cataloger;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ccs.service.content.ContentService;

import net.sf.json.JSONObject;

import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.service.toc.TOCGenerateService;
import com.cisco.ccs.util.CCSConstantUtil;
import com.cisco.ccs.util.CCSControlerConstants;

@Controller(value = "contentCatalogerController")
@RequestMapping(value = { "/", "/ccs" })
@SessionAttributes(types = ContentCatalogerController.class)
public class ContentCatalogerController {
	
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

        String userId = request.getRemoteUser();
        List<FTPMaster> ftpList = null;
        response.setContentType(CCSConstantUtil.CONTENT_TYPE_APPLICATION_JSON);

        	ftpList = contentService.getFTPList(userId);	
            modelMap.addAttribute("searchOrderResults", contentService.getFTPListAsJSON(ftpList).toString());        
            JSONObject dataObject = contentService.getFTPListAsJSON(ftpList);
            try {
                dataObject.put(CCSControlerConstants.SEARCH_DRAW_COUNT, 1);
                dataObject.put(CCSControlerConstants.SEARCH_TOTAL_DISPLAY_RECORDS, 2);
                dataObject.put(CCSControlerConstants.SEARCH_TOTAL_RECORDS, 2);
                response.setCharacterEncoding("UTF-8");
				response.getWriter().write(contentService.getFTPListAsJSON(ftpList).toString());
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

}
