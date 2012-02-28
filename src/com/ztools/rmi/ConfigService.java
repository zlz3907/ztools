package com.ztools.rmi;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JOptionPane;

import com.ztools.conf.ALCFFactory;
import com.ztools.conf.AutoConfigurer;
import com.ztools.conf.Environment;
import com.ztools.rmi.bean.RmiConfigBean;
import com.ztools.rmi.impl.LocalConfig;
import com.ztools.rmi.impl.RmiConfig;
import com.ztools.xml.XMLBean;
import com.ztools.xml.XMLReader;

public class ConfigService {

	public static LocalConfig localConfig= new LocalConfig();
	/**
	 * 开启RMI服务注册
	 */
	public static void startRmi(){
		try {
			AutoConfigurer rmiServer = ALCFFactory
			.createAutoConfiger(Environment.getContext()+"conf/rmiServer_config.properties");
			//rmi 注册的类
			IConfig rmiConfig = new RmiConfig();
			String serverName = rmiServer.getValue("rmi.ztoolserver");
			int prot = Integer.parseInt(rmiServer.getValue("rmi.ztoolprot"));
			Registry registry = null;
				 try {
					registry = LocateRegistry.createRegistry(prot);
				} catch (Exception e) {
					registry= LocateRegistry.getRegistry(prot);
				}
			registry.rebind(serverName, rmiConfig);
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP
			System.out.println("ztoolRmiServer:"+ip+":"+prot+"/"+serverName+" is ready.....");
			JOptionPane.showMessageDialog(null, "ztoolRmiServer:"+ip+":"+prot+"/"+serverName+" is ready.....","提示信息", JOptionPane.OK_OPTION);
		} catch (Exception e) {
			System.out.println("RmiServer failed: " + e);
			JOptionPane.showMessageDialog(null, "ztoolRmiServer: start failure.....\n"+e,"提示信息", JOptionPane.OK_OPTION);
		}
	}
	/**
	 * @return 得到RMI中对象
	 * @throws RemoteException
	 */
	public static IConfig getRmiService()throws RemoteException{
//		System.setSecurityManager(new RMISecurityManager());
		IConfig config = null;
		XMLBean xmlBean = new XMLBean(Environment.getContext()+"conf/rmiClient_config.xml");
		XMLReader.readXMLBean(xmlBean);
		List<?> list=  xmlBean.getItemList();
		boolean flag = false;
		do{
			if(list.size()==0){
				System.out.println("所有远程服务没有找到");
				throw new RemoteException("所有远程服务没有找到");
				
			}
			int index = (int) (Math.random()*list.size());
			
			 Object obj  = list.get(index);
			 if(obj instanceof RmiConfigBean){
				 RmiConfigBean rmiConfigBean = (RmiConfigBean)obj;
				 try {
//				System.out.println(rmiConfigBean());
					 Registry registry = LocateRegistry.getRegistry(rmiConfigBean.getHost(), rmiConfigBean.getPort());
					 config = (IConfig) registry.lookup(rmiConfigBean.getName());
					 flag=false;
				 } catch (Exception e) {
					 System.out.println("注册表中查找没有相关绑定的名称:"+rmiConfigBean.toString());
					 config=null;
					 list.remove(index);
					 flag=true;
				 }
				 
			 }else{
				list.remove(obj);
				flag = true;
				config = null;
				continue;
			 }
			
			
		}while(flag);
		return config;
	}
public static void main(String[] args) {
	ConfigService.startRmi();
	
}
	
}
