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

    public XMLBean readXMLBean(XMLBean xmlBean) {
        // String path = this.context+this.conf+"/"+xmlBean.getPath()+this.xml;
        // xmlBean.setPath(path);
        XMLReader.readXMLBean(xmlBean);
        return xmlBean;
    }

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

    public void writeXMLBean(XMLBean xmlBean) throws IOException {
        // String path = this.context+this.conf+"/"+xmlBean.getPath()+this.xml;
        // xmlBean.setPath(path);
        XMLWriter.writeXMLBean(xmlBean);
    }
}
