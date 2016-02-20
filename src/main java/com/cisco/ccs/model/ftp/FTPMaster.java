package com.cisco.ccs.model.ftp;

import java.util.Comparator;
import java.util.Date;

public class FTPMaster implements Comparable<FTPMaster>{

	private String fileName;
	private String fileType;
	private double fileSize;
	private Date lastModifiedDate;
	
	public String getFileName() {
		return fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public double getFileSize() {
		return fileSize;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public FTPMaster() {
		super();
	}
	
	public FTPMaster(String fileName, String fileType, double fileSize, Date lastModifiedDate) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public int compareTo(FTPMaster ftp) {
        return this.fileName.compareTo(ftp.fileName);
    }

	public static Comparator<FTPMaster> name_ASC_Comparator = new Comparator<FTPMaster>() {         
	    @Override         
	    public int compare(FTPMaster f1, FTPMaster f2) {             
	    	return ((String)f1.getFileName()).compareTo(f2.getFileName());        
	    }     
	  }; 
	  
	public static Comparator<FTPMaster> name_DESC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((String)f2.getFileName()).compareTo(f1.getFileName());        
		    }     
		  }; 
		  
	  public static Comparator<FTPMaster> type_ASC_Comparator = new Comparator<FTPMaster>() {         
	    @Override         
	    public int compare(FTPMaster f1, FTPMaster f2) {             
	    	return ((String)f1.getFileType()).compareTo(f2.getFileType());
	    }     
	  };         

	  public static Comparator<FTPMaster> type_DESC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((String)f2.getFileType()).compareTo(f1.getFileType());
		    }     
	  }; 
	
	  public static Comparator<FTPMaster> size_ASC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((Double)f1.getFileSize()).compareTo(f2.getFileSize());
		    }     
		  };         

	  public static Comparator<FTPMaster> size_DESC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((Double)f2.getFileSize()).compareTo(f1.getFileSize());
		    }     
	  };
	  
	  public static Comparator<FTPMaster> date_ASC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((Date)f1.getLastModifiedDate()).compareTo(f2.getLastModifiedDate());
		    }     
		  };         

	  public static Comparator<FTPMaster> date_DESC_Comparator = new Comparator<FTPMaster>() {         
		    @Override         
		    public int compare(FTPMaster f1, FTPMaster f2) {             
		    	return ((Date)f2.getLastModifiedDate()).compareTo(f1.getLastModifiedDate());
		    }     
	  };
	
	
}
