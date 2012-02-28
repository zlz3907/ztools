package com.ztools.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Properties;

import com.ztools.conf.AutoConfigurer;
import com.ztools.xml.XMLBean;

public interface IConfig extends Remote,Serializable {
	/**
	 * @param fileName 文件名
	 * @param groupName  模块名
	 * @return 得相应文件路径的属性文件
	 */
	public AutoConfigurer readAutoConfiger(String fileName,String groupName) throws RemoteException;
	/**
	 * @param fileName
	 * @param prop 需要写入的属性文件
	 * @param groupName  模块名
	 * 写入相应文件路径的属性文件
	 */
	public void writeAutoConfiger(String fileName,Properties prop,String groupName)throws RemoteException;
	/**
	 * @param xmlBean
	 * @param groupName  模块名
	 * @return 得相应文件路径的XML文件
	 * 
	 * <p>
	 * XMLBean xmlBean = new XMLBean(fileName);
	 * </p>
	 * <p>
		readXMLBean(xmlBean,groupName);
		</p>
	 * <p>
		返回配置文件中的配置果集
		</p>
	 * <p>
		List list= xmlBean.getItemList();
		</p>
	 * 
		
	 */
	public XMLBean readXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
	/**
	 * @param xmlBean
	 * @param groupName  模块名
	 * 写入相应文件路径的XML文件 
	 * 
	 *写入的格式为类的属性 
	 *
	 *<p>例如类:
	 * RmiConfigBean rmiConfigBean = new RmiConfigBean();
	 * </p>
	 * <p>
		XMLBean XMLBean = new XMLBean("rmiClient",RmiConfigBean.class);
		</p>
	 * <p>
		为配置文件设会值
		</p>
	 * <p>
		RmiConfigBean.setHost("");
		</p>
	 * <p>
		RmiConfigBean.setName("Hello");
		</p>
	 * <p>
		RmiConfigBean.setPort(1199);
		</p>
	 * <p>
		List list = new ArrayList();
		</p>
	 * <p>
		....... 可以多个
		</p>
	 * <p>
		list.add(rmiConfigBean);
		</p>
	 * <p>
		XMLBean.setItemList(list);
		</p>
	 * <p>
		写入配置文件中
		</p>
	 * <p>
		writeXMLBean(XMLBean,groupName);
		</p>
	 */
	public void writeXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
}
