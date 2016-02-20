package com.cisco.ccs.model.ftp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FTPMasterSorter {

	List<FTPMaster> ftpMaster = new ArrayList<>();       
	  public FTPMasterSorter(List<FTPMaster> ftpList) {         
	    this.ftpMaster = ftpList;     
	  }     
	  
	  // File Name Sorting
	  public List<FTPMaster> getASCSortedFTPListByName() {         
	    Collections.sort(ftpMaster, FTPMaster.name_ASC_Comparator);         
	    return ftpMaster;     
	  } 
	  public List<FTPMaster> getDESCSortedFTPListByName() {         
		    Collections.sort(ftpMaster, FTPMaster.name_DESC_Comparator);         
		    return ftpMaster;     
	  }
	  
	  // File Type Sorting
	  public List<FTPMaster> getASCSortedFTPListByType() {         
		    Collections.sort(ftpMaster, FTPMaster.type_ASC_Comparator);         
		    return ftpMaster;     
	  } 
	  public List<FTPMaster> getDESCSortedFTPListByType() {         
		    Collections.sort(ftpMaster, FTPMaster.type_DESC_Comparator);         
		    return ftpMaster;     
	  } 
	  
	  // File Size Sorting
	  public List<FTPMaster> getASCSortedFTPListBySize() {         
		    Collections.sort(ftpMaster, FTPMaster.size_ASC_Comparator);         
		    return ftpMaster;     
	  } 
	  public List<FTPMaster> getDESCSortedFTPListBySize() {         
		    Collections.sort(ftpMaster, FTPMaster.size_DESC_Comparator);         
		    return ftpMaster;     
	  } 
	  
	  // File Last Modified Date Sorting
	  public List<FTPMaster> getASCSortedFTPListByLastModifiedDate() {         
		    Collections.sort(ftpMaster, FTPMaster.date_ASC_Comparator);         
		    return ftpMaster;     
	  } 
	  public List<FTPMaster> getDESCSortedFTPListByLastModifiedDate() {         
		    Collections.sort(ftpMaster, FTPMaster.date_DESC_Comparator);         
		    return ftpMaster;     
	  } 

}
