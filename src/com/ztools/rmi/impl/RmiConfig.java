package com.ztools.rmi.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import com.ztools.conf.ALCFFactory;
import com.ztools.conf.AutoConfigurer;
import com.ztools.conf.Environment;
import com.ztools.rmi.IConfig;
import com.ztools.xml.XMLBean;
import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;

public class RmiConfig  extends UnicastRemoteObject  implements IConfig {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public  RmiConfig() throws RemoteException {
		super();
	}
	private String xml=".xml";
	private String properties=".properties";
	private String conf = "conf/";
	private String context = Environment.getContext();

	@Override
	public AutoConfigurer readAutoConfiger(String filePath,String groupName )throws RemoteException  {
		filePath = context+conf+groupName+"/"+filePath+properties;
		try {
			return ALCFFactory.createAutoConfiger(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public XMLBean readXMLBean(XMLBean xmlBean,String groupName)throws RemoteException {
		String path = this.context+this.conf+groupName+"/"+xmlBean.getPath()+this.xml;
		xmlBean.setPath(path);
		XMLReader.readXMLBean(xmlBean);
		return xmlBean;
	}

	@Override
	public void writeAutoConfiger(String filePath,Properties prop,String groupName)throws RemoteException {
			FileOutputStream fout = null;
			try {
				filePath = this.context+this.conf+groupName+"/"+filePath+this.properties;
				File file = new File(this.context+this.conf+groupName+"/");
		    	   if(!file.exists()){
		    		   file.mkdirs();
		    	   }
				fout = new FileOutputStream(filePath,true); 
				prop.store(fout, filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(fout!=null){
						fout.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	

	@Override
	public void writeXMLBean(XMLBean xmlBean,String groupName)throws RemoteException {
		String path = this.context+this.conf+groupName+"/"+xmlBean.getPath()+this.xml;
		xmlBean.setPath(path);
		try {
            XMLWriter.writeXMLBean(xmlBean);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
