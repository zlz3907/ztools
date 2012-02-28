package com.ztools.xml;

import java.io.Serializable;

import org.xml.sax.helpers.DefaultHandler;

abstract public class AbsHandler extends DefaultHandler implements Serializable {

    private static final long serialVersionUID = 1L;

    abstract public Object getXmlObject();
}
