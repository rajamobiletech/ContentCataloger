/**
 * 
 */
package com.cisco.ccs.model;


public abstract class SearchCriteria {

    private String searchId = "";
    private int iDisplayLength;
    private int iDisplayStart;
    private int totalRecords;
    private int iSortCol;
    private String sSortDir;
    private int sEcho;
    private String ssSearch;

    public String getSearchID() {
        return searchId;
    }

    private boolean defaultSearch = false;

    public boolean isDefaultSearch() {
        return defaultSearch;
    }

    public void setDefaultSearch(boolean defaultSearch) {
        this.defaultSearch = defaultSearch;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getiSortCol() {
        return iSortCol;
    }

    public void setiSortCol(int iSortCol) {
        this.iSortCol = iSortCol;
    }

    public String getsSortDir() {
        return sSortDir;
    }

    public void setsSortDir(String sSortDir) {
        this.sSortDir = sSortDir;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public String getSsSearch() {
        return ssSearch;
    }

    public void setSsSearch(String ssSearch) {
        this.ssSearch = ssSearch;
    }

}
