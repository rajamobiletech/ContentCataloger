package com.cisco.ccs.model;

import org.springframework.stereotype.Component;

import com.cisco.ccs.model.SearchCriteria;

@Component(FTPSearchCriteria.BEAN_ID)
public class FTPSearchCriteria extends SearchCriteria {
    public static final String BEAN_ID = "ftpSearch";
    private String fileName;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
 }
