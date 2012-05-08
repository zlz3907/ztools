package com.ztoole.test;

import java.io.Serializable;

public class XMLNewsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nav;
    private String pageSort;
    private String pageSubSort;
    private String location;
    private Integer inPageSequence;
    private String content;

    public XMLNewsBean() {

    }

//    public XMLNewsBean(PageInfo pageInfo) {
//        this.setTitle(pageInfo.getTitle());
//        this.setNav(pageInfo.getNav());
//        this.setUri(pageInfo.getUri());
//        this.setDownloadDate(pageInfo.getDownloadDate());
//        this.setPageSort(pageInfo.getPageSort());
//        this.setPageSubSort(pageInfo.getPageSubSort());
//        this.setExtractData(pageInfo.getExtractData());
//        this.setLocation(pageInfo.getLocation());
//        this.setInPageSequence(pageInfo.getInPageSequence());
//        this.setContent(pageInfo.getContent());
//        this.setPr(pageInfo.getPrdb().getPr());
//        this.setSiteName(pageInfo.getSite().getSiteName());
//        this.setDocId(pageInfo.getDocId());
//    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getPageSort() {
        return pageSort;
    }

    public void setPageSort(String pageSort) {
        this.pageSort = pageSort;
    }

    public String getPageSubSort() {
        return pageSubSort;
    }

    public void setPageSubSort(String pageSubSort) {
        this.pageSubSort = pageSubSort;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getInPageSequence() {
        return inPageSequence;
    }

    public void setInPageSequence(Integer inPageSequence) {
        this.inPageSequence = inPageSequence;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
