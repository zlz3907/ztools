package com.ztools.rmi.bean;

import java.io.Serializable;

/**
 * @author zouren
 * @time 2010-8-25����10:42:07 
 * <P>
 * ע��RMI �����ļ���Ӧ�� BEAN
 * </P>
 */
public class RmiConfigBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2682994307751652989L;
	private String host;
	private int port;
	private String name;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return host+":"+port+"/"+name;
	}
}
