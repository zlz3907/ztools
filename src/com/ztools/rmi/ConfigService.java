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

    public static LocalConfig localConfig = new LocalConfig();

    public static void startRmi() {
        try {
            AutoConfigurer rmiServer = ALCFFactory
                    .createAutoConfiger(Environment.getContext()
                            + "conf/rmiServer_config.properties");
            IConfig rmiConfig = new RmiConfig();
            String serverName = rmiServer.getValue("rmi.ztoolserver");
            int prot = Integer.parseInt(rmiServer.getValue("rmi.ztoolprot"));
            Registry registry = null;
            try {
                registry = LocateRegistry.createRegistry(prot);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(prot);
            }
            registry.rebind(serverName, rmiConfig);
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();// ��ñ���IP
            System.out.println("ztoolRmiServer:" + ip + ":" + prot + "/"
                    + serverName + " is ready.....");
            JOptionPane.showMessageDialog(null, "ztoolRmiServer:" + ip + ":"
                    + prot + "/" + serverName + " is ready.....", "",
                    JOptionPane.OK_OPTION);
        } catch (Exception e) {
            System.out.println("RmiServer failed: " + e);
            JOptionPane.showMessageDialog(null,
                    "ztoolRmiServer: start failure.....\n" + e, "",
                    JOptionPane.OK_OPTION);
        }
    }

    public static IConfig getRmiService() throws RemoteException {
        // System.setSecurityManager(new RMISecurityManager());
        IConfig config = null;
        XMLBean xmlBean = new XMLBean(Environment.getContext()
                + "conf/rmiClient_config.xml");
        XMLReader.readXMLBean(xmlBean);
        List<?> list = xmlBean.getItemList();
        boolean flag = false;
        do {
            if (list.size() == 0) {
                System.out.println("");
                throw new RemoteException("");

            }
            int index = (int) (Math.random() * list.size());

            Object obj = list.get(index);
            if (obj instanceof RmiConfigBean) {
                RmiConfigBean rmiConfigBean = (RmiConfigBean) obj;
                try {
                    // System.out.println(rmiConfigBean());
                    Registry registry = LocateRegistry.getRegistry(
                            rmiConfigBean.getHost(), rmiConfigBean.getPort());
                    config = (IConfig) registry.lookup(rmiConfigBean.getName());
                    flag = false;
                } catch (Exception e) {
                    System.out.println("e:" + rmiConfigBean.toString());
                    config = null;
                    list.remove(index);
                    flag = true;
                }

            } else {
                list.remove(obj);
                flag = true;
                config = null;
                continue;
            }

        } while (flag);
        return config;
    }

    public static void main(String[] args) {
        ConfigService.startRmi();

    }

}
