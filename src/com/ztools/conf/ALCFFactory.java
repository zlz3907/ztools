/**
 * 
 */
package com.ztools.conf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Zhong Lizhi
 */
public class ALCFFactory {

  private static HashMap<String, AutoConfigurer> producesMap = new HashMap<String, AutoConfigurer>();

  public static AutoConfigurer createAutoConfiger(String filePath)
      throws FileNotFoundException, IOException {
    AutoConfigurer autoConfiger = producesMap.get(filePath);
    if (null == autoConfiger) {
      autoConfiger = new AutoConfigurer(filePath);
      producesMap.put(filePath, autoConfiger);
    }
    return autoConfiger;
  }

  public static AutoConfigurer createAutoConfiger(InputStream input)
      throws IOException {
    AutoConfigurer autoConfiger = producesMap.get("input_" + input.hashCode());
    if (null == autoConfiger) {
      autoConfiger = new AutoConfigurer(input);
      producesMap.put("input_" + input.hashCode(), autoConfiger);
    }
    return autoConfiger;
  }

  public static void deleteAutoConfiger(String filePath) {
    producesMap.remove(filePath);
  }

  public static void deleteAutoConfiger(AutoConfigurer autoConfiger) {
    deleteAutoConfiger(autoConfiger.getPath());
  }

  /**
   * @param filePath
   * @param parameterName
   * @param parameterValue
   */
  public static void writeProperties(String filePath, String parameterName,
      String parameterValue) {
    deleteAutoConfiger(filePath);
    Properties prop = new Properties();
    InputStream fis = null;
    OutputStream fos = null;
    try {
      fis = new FileInputStream(filePath);
      prop.load(fis);
      fos = new FileOutputStream(filePath);
      prop.setProperty(parameterName, parameterValue);
      prop.store(fos, "Update '" + parameterName + "' value");

    } catch (IOException e) {
      System.err.println("Visit " + filePath + " for updating " + parameterName
          + " value error");
    } finally {
      try {
        if (fos != null) {
          fos.close();
        }
        if (fis != null) {
          fis.close();
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    AutoConfigurer autoConfiger = null;
    try {
      autoConfiger = ALCFFactory.createAutoConfiger("path/filename");
    } catch (FileNotFoundException e2) {
      // û�ҵ��ļ�
      e2.printStackTrace();
      System.exit(1);
    } catch (IOException e2) {
      // ���쳣
      System.exit(2);
      e2.printStackTrace();
    }
    autoConfiger.getValue("key");

    for (int i = 0; i < 10; i++) {
      new Thread() {
        public void run() {
          while (true) {
            try {
              System.out.println(Thread.currentThread().getName()
                  + " "
                  + ALCFFactory.createAutoConfiger("regexp.properties")
                      .getValue("huxiTitle"));
            } catch (FileNotFoundException e1) {
              e1.printStackTrace();
            } catch (IOException e1) {
              e1.printStackTrace();
            }
            try {
              Thread.sleep(300);
            } catch (InterruptedException e) {
              e.printStackTrace();
              // Out.print(
              // e);
            }
          }
        }
      }.start();
    }
  }

}
