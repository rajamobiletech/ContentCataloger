package com.cisco.ccs.models;

import java.util.Date;

public class FileMaster {

	String FileName;
	String FileType;
	Double FileSize;
	String LastModifiedDate;
	String FileTitle;
	String Locale;
	String Publisher;
	String PartNumber;
	String Checksum;
	
	public String getFileName() {
		return FileName;
	}
	public String getFileType() {
		return FileType;
	}
	public Double getFileSize() {
		return FileSize;
	}
	public String getLastModifiedDate() {
		return LastModifiedDate;
	}
	public String getFileTitle() {
		return FileTitle;
	}
	public String getLocale() {
		return Locale;
	}
	public String getPublisher() {
		return Publisher;
	}
	public String getPartNumber() {
		return PartNumber;
	}
	public String getChecksum() {
		return Checksum;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public void setFileSize(Double fileSize) {
		FileSize = fileSize;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public void setFileTitle(String fileTitle) {
		FileTitle = fileTitle;
	}
	public void setLocale(String locale) {
		Locale = locale;
	}
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}
	public void setChecksum(String checksum) {
		Checksum = checksum;
	}
	public FileMaster(String fileName, String fileType, Double fileSize, String lastModifiedDate, String fileTitle,
			String locale, String publisher, String partNumber, String checksum) {
		super();
		FileName = fileName;
		FileType = fileType;
		FileSize = fileSize;
		LastModifiedDate = lastModifiedDate;
		FileTitle = fileTitle;
		Locale = locale;
		Publisher = publisher;
		PartNumber = partNumber;
		Checksum = checksum;
	}
	public FileMaster() {
		super();
		FileName = "";
		FileType = "";
		FileSize = 0.0;
		LastModifiedDate = "";
		FileTitle = "";
		Locale = "";
		Publisher = "";
		PartNumber = "";
		Checksum = "";
	}
	
}
