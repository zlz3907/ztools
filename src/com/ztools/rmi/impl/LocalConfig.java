package com.ztools.rmi.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.ztools.conf.ALCFFactory;
import com.ztools.conf.AutoConfigurer;
import com.ztools.conf.Environment;
import com.ztools.xml.XMLBean;
import com.ztools.xml.XMLReader;
import com.ztools.xml.XMLWriter;

public class LocalConfig {
    private String conf = "conf/";
    public String context = Environment.getContext();

    /**
     * @param filePath
     *            文件路径
     * @return
     */
    public AutoConfigurer readAutoConfiger(String filePath) {
        // filePath = context+conf+"/"+filePath+properties;
        try {
            return ALCFFactory.createAutoConfiger(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param xmlBean
     * @return 读XML
     *         <p>
     *         例如:
     *         </p>
     * 
     *         <p>
     *         第一个参数为完整路径
     *         </p>
     *         <p>
     *         XMLBean xmlBean = new
     *         XMLBean(Environment.getContext()+"conf/Client.xml"
     *         ,ConfigBean.class);
     *         </p>
     *         xmlBean = readXMLBean(xmlBean)
     *         <p>
     *         List list = xmlBean.getItemList()
     *         </p>
     */
    public XMLBean readXMLBean(XMLBean xmlBean) {
        // String path = this.context+this.conf+"/"+xmlBean.getPath()+this.xml;
        // xmlBean.setPath(path);
        XMLReader.readXMLBean(xmlBean);
        return xmlBean;
    }

    /**
     * @param filePath
     *            写入文件路径
     * @param prop
     *            需要写入属性文件
     */
    public void writeAutoConfiger(String filePath, Properties prop) {
        FileOutputStream fout = null;
        try {
            // filePath = this.context+this.conf+"/"+filePath+this.properties;
            File file = new File(this.context + this.conf + "/");
            if (!file.exists()) {
                file.mkdirs();
            }
            fout = new FileOutputStream(filePath, true);
            prop.store(fout, filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param xmlBean
     * 
     *            *
     *            <p>
     *            例如:
     *            </p>
     *            <p>
     *            第一个参数为完整路径 XMLBean XMLBean = new
     *            XMLBean(Environment.getContext
     *            ()+"conf/Client.xml",ConfigBean.class);
     *            </p>
     *            <p>
     *            为配置文件设会值
     *            </p>
     *            <p>
     *            configBean.setHost("");
     *            </p>
     *            <p>
     *            configBean.setName("Hello");
     *            </p>
     *            <p>
     *            configBean.setPort(1199);
     *            </p>
     *            <p>
     *            List list = new ArrayList();
     *            </p>
     *            <p>
     *            ....... 可以多个
     *            </p>
     *            <p>
     *            list.add(configBean);
     *            </p>
     *            <p>
     *            XMLBean.setItemList(list);
     *            </p>
     *            <p>
     *            写入配置文件中
     *            </p>
     *            <p>
     *            writeXMLBean(XMLBean);
     *            </p>
     * @throws IOException
     */
    public void writeXMLBean(XMLBean xmlBean) throws IOException {
        // String path = this.context+this.conf+"/"+xmlBean.getPath()+this.xml;
        // xmlBean.setPath(path);
        XMLWriter.writeXMLBean(xmlBean);
    }
}
