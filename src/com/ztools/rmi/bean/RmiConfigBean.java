package com.ztools.rmi.bean;

import java.io.Serializable;

/**
 * @author zouren
 * @time 2010-8-25上午10:42:07 
 * <P>
 * 注册RMI 配置文件对应的 BEAN
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
