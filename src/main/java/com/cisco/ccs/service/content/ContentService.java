package com.cisco.ccs.service.content;

import java.io.File;
import java.util.List;

import javax.jws.WebService;

import com.cisco.ccs.exception.CCSException;
import com.cisco.ccs.model.FTPSearchCriteria;
import com.cisco.ccs.model.SearchCriteria;
import com.cisco.ccs.model.ftp.FTPMaster;

import net.sf.json.JSONObject;

@WebService
public interface ContentService {

    public static final String BEAN_ID = "contentService";
	
    public int getDefaultCount(String user, File folder, FTPSearchCriteria ftpSearchCriteria);
    
    public List<FTPMaster> getFTPList(String userId, File folder, FTPSearchCriteria FTPSearchCriteria);
    
	public JSONObject getFTPListAsJSON(List<FTPMaster> ftpList);
	
	public JSONObject getFTPDetails(String ftpName);
	

}
