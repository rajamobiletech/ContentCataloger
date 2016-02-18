package com.cisco.ccs.service.content;

import java.util.List;

import javax.jws.WebService;

import com.cisco.ccs.exception.CCSException;
import com.cisco.ccs.model.ftp.FTPMaster;

import net.sf.json.JSONObject;

@WebService
public interface ContentService {

    public static final String BEAN_ID = "contentService";
    
	public List<String> getAllFTPSources() throws CCSException;
	
    public List<FTPMaster> getFTPList(String userId);
    
	public JSONObject getFTPListAsJSON(List<FTPMaster> ftpList);
	
	public JSONObject getFTPDetails(String ftpName);


}
