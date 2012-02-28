package com.ztoole.test;

import java.io.Serializable;

public class XMLRealBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String brand;
    private String series;
    private String content;
    private String digest;
    private String meritAndDemerit;

    public XMLRealBean() {
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getMeritAndDemerit() {
        return meritAndDemerit;
    }

    public void setMeritAndDemerit(String meritAndDemerit) {
        this.meritAndDemerit = meritAndDemerit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
