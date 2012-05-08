package com.ztools.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Properties;

import com.ztools.conf.AutoConfigurer;
import com.ztools.xml.XMLBean;

public interface IConfig extends Remote,Serializable {
	public AutoConfigurer readAutoConfiger(String fileName,String groupName) throws RemoteException;
	public void writeAutoConfiger(String fileName,Properties prop,String groupName)throws RemoteException;
	public XMLBean readXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
	public void writeXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
}
