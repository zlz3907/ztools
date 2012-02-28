package com.ztools.xml;

@Deprecated
public class DefaultXmlCharsetProcess implements IXMLProcess{

	@Override
	public String read(String str) {
		return str;
	}

	@Override
	public String write(String str) {
		return str;
	}

}
