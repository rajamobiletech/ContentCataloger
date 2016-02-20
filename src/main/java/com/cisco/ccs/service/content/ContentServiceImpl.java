package com.cisco.ccs.service.content;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.cisco.ccs.model.FTPSearchCriteria;
import com.cisco.ccs.model.SearchCriteria;
import com.cisco.ccs.model.ftp.FTPMasterSorter;
import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.util.CommonsUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(ContentService.BEAN_ID)
@WebService(portName = "ContentServicePort", endpointInterface = "com.cisco.ccs.service.content.ContentService")
public class ContentServiceImpl implements ContentService {

	FTPMasterSorter ftpMasterSorter;   
	@Override
	@WebMethod(exclude = true)
	public int getDefaultCount(String user, File folder, FTPSearchCriteria ftpSearchCriteria) {
		return noOfFilesInFolder(folder, ftpSearchCriteria);
	}
	
	public int noOfFilesInFolder(final File folder, FTPSearchCriteria ftpSearchCriteria) {
		int count = 0;
	    for (final File fileEntry : folder.listFiles()) {
	        	String fileName = fileEntry.getName();
	        	if(fileName.equals(".DS_Store"))
	        		 continue;
	        	String searchFileName = ftpSearchCriteria.getFileName();
	        	if(!searchFileName.equals("")) {
	        		if(Pattern.compile(Pattern.quote(searchFileName), Pattern.CASE_INSENSITIVE).matcher(fileEntry.getName()).find()) count++;
	 	        }else count++;
	    }
	    return count;
	}

	
	private List<FTPMaster> listFilesForFolder(final File folder,  final FTPSearchCriteria ftpSearchCriteria) {
		List<FTPMaster> ftpList = new ArrayList<FTPMaster>();
		
		File[] FileList = folder.listFiles();
		
		if(FileList != null) {
			for (final File fileEntry : FileList) {
	    		String mFileName  = "";
	    		String mFileType  = "";
	    		Date mLastModified;
	        	String fileName = fileEntry.getName();
	        	if(fileName.equals(".DS_Store"))
	        		 continue;
	        	int i = fileName.lastIndexOf('.');
	            if (i > 0) {
	            	mFileName = fileName;
	            	mFileType = fileName.substring(i+1);
	            }
	            double bytes = fileEntry.length();
				double kilobytes = (bytes / 1024);
				double mFileSizeMB = (kilobytes / 1024);
				DecimalFormat df = new DecimalFormat("#.##");      
				mFileSizeMB = Double.valueOf(df.format(mFileSizeMB));	            
	            
	            mLastModified = new Date(fileEntry.lastModified());
	            String searchFileName = ftpSearchCriteria.getFileName();
	            if(!searchFileName.equals("")) {
	            if(Pattern.compile(Pattern.quote(searchFileName), Pattern.CASE_INSENSITIVE).matcher(fileEntry.getName()).find()) 
	            	ftpList.add(new FTPMaster(mFileName, mFileType, mFileSizeMB, mLastModified));
	            }else ftpList.add(new FTPMaster(mFileName, mFileType, mFileSizeMB, mLastModified));
			}
		}else return null;
	    
	    return filterBySearchCriteria(ftpList, ftpSearchCriteria);
	}

	private List<FTPMaster> filterBySearchCriteria(List<FTPMaster> ftpList, FTPSearchCriteria ftpSearchCriteria) {
		ftpMasterSorter = new FTPMasterSorter(ftpList);
		List<FTPMaster> filteredFTPList = new ArrayList<FTPMaster>();
		switch (ftpSearchCriteria.getiSortCol()) {
		case 1:
			if(ftpSearchCriteria.getsSortDir().equals("asc")) ftpList = ftpMasterSorter.getASCSortedFTPListByName();
			else ftpList = ftpMasterSorter.getDESCSortedFTPListByName();
			break;
		case 2:
			if(ftpSearchCriteria.getsSortDir().equals("asc")) ftpList = ftpMasterSorter.getASCSortedFTPListByType();
			else ftpList = ftpMasterSorter.getDESCSortedFTPListByType();
			break;
		case 3:
			if(ftpSearchCriteria.getsSortDir().equals("asc")) ftpList = ftpMasterSorter.getASCSortedFTPListBySize();
			else ftpList = ftpMasterSorter.getDESCSortedFTPListBySize();
			break;
		case 4:
			if(ftpSearchCriteria.getsSortDir().equals("asc")) ftpList = ftpMasterSorter.getASCSortedFTPListByLastModifiedDate();
			else ftpList = ftpMasterSorter.getDESCSortedFTPListByLastModifiedDate();
			break;
		default:
			break;
		}
		int lastRecord = (ftpSearchCriteria.getiDisplayStart()+ftpSearchCriteria.getiDisplayLength()) < ftpSearchCriteria.getTotalRecords() ? (ftpSearchCriteria.getiDisplayStart()+ftpSearchCriteria.getiDisplayLength()) : ftpSearchCriteria.getTotalRecords();
		for(int i = ftpSearchCriteria.getiDisplayStart(); i < lastRecord; i++)
			filteredFTPList.add(ftpList.get(i));
		return filteredFTPList;
		
	}
	
    public List<FTPMaster> getFTPList(String userId, File folder, FTPSearchCriteria FTPSearchCriteria) {
    	List<FTPMaster> ftpDataList = null;
			ftpDataList = listFilesForFolder(folder, FTPSearchCriteria);
		return ftpDataList;
    }
    
	@Override
	@WebMethod(exclude = true)
	public JSONObject getFTPListAsJSON(List<FTPMaster> ftpList) {
		JSONArray jsonDataArray = new JSONArray();
		JSONObject dataObject = new JSONObject();
		for (int i = 0; i < ftpList.size(); i++) {
			JSONObject object = new JSONObject();			
			object.put("fileName", ftpList.get(i).getFileName());
			object.put("fileType", ftpList.get(i).getFileType());
			object.put("fileSize", ftpList.get(i).getFileSize());
			try {
				object.put("fileLastModifiedDate", CommonsUtil.getFormattedDateTime(ftpList.get(i).getLastModifiedDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jsonDataArray.add(object);
		}
		dataObject.put("aaData", jsonDataArray);
		return dataObject;
	}

	@Override
	@WebMethod(exclude = true)
	public JSONObject getFTPDetails(String ftpName) {
		JSONObject ftpDetailsJSON = new JSONObject();
		return ftpDetailsJSON;
	}
	
}
