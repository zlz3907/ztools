package com.ztoole.test;

import java.io.Serializable;

public class XMLNewsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nav;// 导航
    private String pageSort;// 原始名schType;// 本网类型 新车 评测 等
    private String pageSubSort;// 原始名schSubType;// 本网二级类型 国内新车 国外新车 等
    private String location;// 原始名area;// 文章地区 全国 or 某个城市
    private Integer inPageSequence;// 页面顺序 原始名 orders;// 文章出现在第几条
    private String content;// 文章内容 原始名 txt;

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
