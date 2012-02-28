package com.ztools.xml;

import java.io.Serializable;
import java.util.List;

import com.ztools.conf.Environment;

public class XMLBean implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 6583353418773596223L;
    private final String ROOT_NAME = "XMLBean";
    private String path;
    private String rootName;
    private Class<?> beanClass;
    private List<?> itemList;
    private Integer number;
    private boolean isAbsolute = true;
    private Object bean;
    private AbsHandler handler;

    private String charset = "UTF-8";

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public XMLBean(String path) {
        this.path = path;
        this.rootName = ROOT_NAME;
    }

    public XMLBean(String path, Class<?> c) {
        this(path);
        if (null == c) {
            throw new NullPointerException("t is Null.");
        }
        this.beanClass = c;

        if (null == path) {
            throw new NullPointerException("path is Null.");
        }
    }

    public String getPath() {
        if (isAbsolute)
            return path;
        return Environment.getContext() + path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public List<?> getItemList() {
        return itemList;
    }

    public void setItemList(List<?> itemList) {
        this.itemList = itemList;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public void setAbsolute(boolean isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    public AbsHandler getHandler() {
        return handler;
    }

    public void setHandler(AbsHandler handler) {
        this.handler = handler;
    }

}
