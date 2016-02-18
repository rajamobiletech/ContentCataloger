package com.cisco.ccs.service.toc;

import javax.jws.WebService;

import net.sf.json.JSONObject;

@WebService
public interface TOCGenerateService {
	
	public static final String BEAN_ID = "TOCService";
	
	public boolean generateTOC(String baseFileServerDirPath, String contentSourceDirPath, String containerFilePath, 
			String contentDesignationDirPath, String epubFileName, String fileFolderPath, String fileType);
	
	public JSONObject getFTPInfoAsJSON(String baseFileServerDirPath, String contentSourceDirPath, String containerFilePath, 
			String contentdestinationDirPath, String epubFileName, String fileType);
}
