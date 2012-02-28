package com.ztools.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Properties;

import com.ztools.conf.AutoConfigurer;
import com.ztools.xml.XMLBean;

public interface IConfig extends Remote,Serializable {
	/**
	 * @param fileName �ļ���
	 * @param groupName  ģ����
	 * @return ����Ӧ�ļ�·���������ļ�
	 */
	public AutoConfigurer readAutoConfiger(String fileName,String groupName) throws RemoteException;
	/**
	 * @param fileName
	 * @param prop ��Ҫд��������ļ�
	 * @param groupName  ģ����
	 * д����Ӧ�ļ�·���������ļ�
	 */
	public void writeAutoConfiger(String fileName,Properties prop,String groupName)throws RemoteException;
	/**
	 * @param xmlBean
	 * @param groupName  ģ����
	 * @return ����Ӧ�ļ�·����XML�ļ�
	 * 
	 * <p>
	 * XMLBean xmlBean = new XMLBean(fileName);
	 * </p>
	 * <p>
		readXMLBean(xmlBean,groupName);
		</p>
	 * <p>
		���������ļ��е����ù���
		</p>
	 * <p>
		List list= xmlBean.getItemList();
		</p>
	 * 
		
	 */
	public XMLBean readXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
	/**
	 * @param xmlBean
	 * @param groupName  ģ����
	 * д����Ӧ�ļ�·����XML�ļ� 
	 * 
	 *д��ĸ�ʽΪ������� 
	 *
	 *<p>������:
	 * RmiConfigBean rmiConfigBean = new RmiConfigBean();
	 * </p>
	 * <p>
		XMLBean XMLBean = new XMLBean("rmiClient",RmiConfigBean.class);
		</p>
	 * <p>
		Ϊ�����ļ����ֵ
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
		....... ���Զ��
		</p>
	 * <p>
		list.add(rmiConfigBean);
		</p>
	 * <p>
		XMLBean.setItemList(list);
		</p>
	 * <p>
		д�������ļ���
		</p>
	 * <p>
		writeXMLBean(XMLBean,groupName);
		</p>
	 */
	public void writeXMLBean(XMLBean xmlBean,String groupName)throws RemoteException;
}
