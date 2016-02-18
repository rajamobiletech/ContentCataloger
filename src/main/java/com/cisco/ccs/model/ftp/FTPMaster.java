package com.cisco.ccs.model.ftp;

import java.util.Date;

public class FTPMaster {

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
}
