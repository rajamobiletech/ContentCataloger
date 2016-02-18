package com.cisco.ccs.service.content;

import java.io.File;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.cxf.interceptor.InInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cisco.ccs.dao.OrderDao;
import com.cisco.ccs.exception.CCSException;
import com.cisco.ccs.model.ftp.FTPMaster;
import com.cisco.ccs.util.CommonsUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(ContentService.BEAN_ID)
@WebService(portName = "ContentServicePort", endpointInterface = "com.cisco.ple.service.content.ContentService")
@InInterceptors(interceptors = { "com.cisco.ple.filter.ContentServiceFualtInterceptor" })
public class ContentServiceImpl implements ContentService {

//	@Autowired
//	OrderDao orderDao;
	
	@Override
	@WebMethod(exclude = true)
	public List<String> getAllFTPSources() throws CCSException {
		List<String> ftpDataList = null;
		try {
			final File folder = new File("/Users/rajad/Desktop/ftp-docs");
			listFilesForFolder(folder);
			//ftpDataList = orderDao.getAllOrderSources();
		} catch (Exception exception) {
			throw new CCSException(exception);
		}
		return ftpDataList;
	}
	
	public List<FTPMaster> listFilesForFolder(final File folder) {
		List<FTPMaster> ftpList = new ArrayList<FTPMaster>();
	    for (final File fileEntry : folder.listFiles()) {
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
	            ftpList.add(new FTPMaster(mFileName, mFileType, mFileSizeMB, mLastModified));
	    }
	    return ftpList;
	}

    public List<FTPMaster> getFTPList(String userId) {
    	List<FTPMaster> ftpDataList = null;
			final File folder = new File("/Users/rajad/Desktop/ftp-docs");
			ftpDataList = listFilesForFolder(folder);
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
