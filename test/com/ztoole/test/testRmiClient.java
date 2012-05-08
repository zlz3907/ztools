package com.ztoole.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ztools.conf.AutoConfigurer;
import com.ztools.conf.Environment;
import com.ztools.rmi.ConfigService;
import com.ztools.rmi.IConfig;
import com.ztools.rmi.bean.RmiConfigBean;
import com.ztools.xml.XMLBean;
import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;

public class testRmiClient {
    public static void main(String[] argv) {

        XMLBean xmlBean = new XMLBean("e:/person.xml", Person.class);
        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i < 1; i++) {
            Person p = new Person("Name" + i, i + "", "f");
            List<String> c = new ArrayList<String>();
            c.add("xx");
            c.add("xxx");
            p.setChildren(c);

            List<Integer> t = new ArrayList<Integer>();
            t.add(1);
            t.add(333);
            p.setThr(t);

            List<Object> objList = new ArrayList<Object>();
            objList.add(1); // int
            objList.add(1123132123123L); // long
            objList.add(1.00001D); // double
            objList.add(9.99f); // float
            objList.add((short) 5); // short
            objList.add((byte) 120); // byte

            objList.add('c'); // char
            objList.add("hello xml");
            objList.add(new Person("aaa", "12", "f"));

            p.setObjlist(objList);

            List<House> houses = new ArrayList<House>();
            House h = new House("hourse1", "beijing", 100);
            List<Room> rooms = new ArrayList<Room>();
            rooms.add(new Room(1));
            rooms.add(new Room(2));
            rooms.add(new Room(3));
            h.setRooms(rooms);
            houses.add(h);
            houses.add(new House("hourse2", "wuhan", 200));
            houses.add(new House("hourse3", "wuhan", 120));

            p.setHouses(houses);
            list.add(p);
        }
        xmlBean.setItemList(list);
        try {
            XMLWriter.writeXMLBean(xmlBean);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        XMLBean xmlBean2 = new XMLBean("e:/person.xml", Person.class);
        list = new ArrayList<Person>();
        xmlBean2.setItemList(list);
        XMLReader.readXMLBean(xmlBean2);
        System.out.println(xmlBean2.getItemList());
        xmlBean2.setPath("e:/person2.xml");
        try {
            XMLWriter.writeXMLBean(xmlBean2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void localReadXML() {
        XMLBean xmlBean = new XMLBean("e:/person.xml", Person.class);
        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person("Name" + i, i + "", "f");
            List<House> houses = new ArrayList<House>();
            houses.add(new House("hourse1", "beijing", 100));
            houses.add(new House("hourse2", "wuhan", 200));
            houses.add(new House("hourse3", "wuhan", 120));
            list.add(p);
        }
        try {
            XMLWriter.writeXMLBean(xmlBean);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println(xmlBean);

    }

    public void localWriteXML() {
        // д���·��
        XMLBean xmlBean = new XMLBean(Environment.getContext()
                + "conf/rmiClient_config1.xml", RmiConfigBean.class);
        RmiConfigBean rmiConfigBean1 = new RmiConfigBean();
        rmiConfigBean1.setHost("localhost");
        rmiConfigBean1.setName("testXML");
        rmiConfigBean1.setPort(2300);

        RmiConfigBean rmiConfigBean2 = new RmiConfigBean();
        rmiConfigBean2.setHost("localhost2");
        rmiConfigBean2.setName("testXML2");
        rmiConfigBean2.setPort(2302);

        List<RmiConfigBean> list = new ArrayList<RmiConfigBean>();
        list.add(rmiConfigBean1);
        list.add(rmiConfigBean2);

        xmlBean.setItemList(list);
        try {
            ConfigService.localConfig.writeXMLBean(xmlBean);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void localWritePrpo() {
        FileInputStream fin = null;
        try {
            Properties prop = new Properties();
            fin = new FileInputStream(Environment.getContext()
                    + "conf/rmiServer_config.properties");
            prop.load(fin); // �����ļ�
            ConfigService.localConfig.writeAutoConfiger(Environment
                    .getContext()
                    + "conf/rmiServer_config11.properties", prop);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void localReadPrpo() {
        AutoConfigurer autoConfigurer = ConfigService.localConfig
                .readAutoConfiger(Environment.getContext()
                        + "conf/rmiServer_config11.properties");
        System.out.println(autoConfigurer);

    }

    public void rmiReadXML() {
        try {
            IConfig IConfig = ConfigService.getRmiService();
            String groupName = "group1";
            XMLBean xmlBean1 = new XMLBean("pp", RmiConfigBean.class);
            xmlBean1 = IConfig.readXMLBean(xmlBean1, groupName);
            System.out.println(xmlBean1);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void rmiWriteXML() {
        try {
            IConfig IConfig = ConfigService.getRmiService();
            String groupName = "group1";
            XMLBean xmlBean = new XMLBean("pp", RmiConfigBean.class);
            RmiConfigBean rmiConfigBean1 = new RmiConfigBean();
            rmiConfigBean1.setHost("localhost");
            rmiConfigBean1.setName("testXML");
            rmiConfigBean1.setPort(2300);

            RmiConfigBean rmiConfigBean2 = new RmiConfigBean();
            rmiConfigBean2.setHost("localhost2");
            rmiConfigBean2.setName("testXML2");
            rmiConfigBean2.setPort(2302);

            List<RmiConfigBean> list = new ArrayList<RmiConfigBean>();
            list.add(rmiConfigBean1);
            list.add(rmiConfigBean2);
            xmlBean.setItemList(list);
            IConfig.writeXMLBean(xmlBean, groupName);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void rmiWritePrpo() {
        FileInputStream fin = null;
        try {
            IConfig IConfig = ConfigService.getRmiService();
            Properties prop = new Properties();
            String groupName = "group1";
            String fileName = "p";
            fin = new FileInputStream(Environment.getContext()
                    + "conf/rmiServer_config.properties");

            prop.load(fin); // �����ļ�
            IConfig.writeAutoConfiger(fileName, prop, groupName);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void rmiReadPrpo() {
        String fileName = "p";
        String groupName = "group1";
        try {
            IConfig IConfig = ConfigService.getRmiService();
            AutoConfigurer pr = IConfig.readAutoConfiger(fileName, groupName);
            System.out.println(pr.getValue("rmi.dbprot"));
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
