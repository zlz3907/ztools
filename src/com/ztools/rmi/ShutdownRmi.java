package com.ztools.rmi;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import com.ztools.conf.ALCFFactory;
import com.ztools.conf.AutoConfigurer;
import com.ztools.conf.Environment;

public class ShutdownRmi {

	public static void shutDown(){
		try {
			AutoConfigurer rmiServer = ALCFFactory
			.createAutoConfiger(Environment.getContext()+"conf/rmiServer_config.properties");
			
			String serverName = rmiServer.getValue("rmi.ztoolserver");
			int prot = Integer.parseInt(rmiServer.getValue("rmi.ztoolprot"));
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//��ñ���IP
			Registry registry = LocateRegistry.getRegistry(ip, prot);
			registry.unbind(serverName);
//			UnicastRemoteObject.unexportObject(registry.lookup(serverName), true);
			JOptionPane.showMessageDialog(null, "ztoolRmiServer:is shutDown is success.....","", JOptionPane.OK_OPTION);
		} catch (Exception e) {
			System.out.println("RmiServer failed: " + e);
			JOptionPane.showMessageDialog(null, "ztoolRmiServer  shutDown is failure.....\n"+ e,"", JOptionPane.OK_OPTION);
		}
	} 
	public static void main(String[] args) {
		ShutdownRmi.shutDown();
//		System.exit(0);

	}
}
