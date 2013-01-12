/**
 * 
 */
package com.ztools.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zhong Lizhi
 */
public class AutoConfigurer implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = -3482941686647354744L;
  private static final long MIN_RELOAD_TIME = 180000L;
  private String charSet = "utf-8";

  private String path = "regexp.properties";
  private Properties prop = new Properties();

  private long lastModifyTime = 0L;
  private long oldCheckTime = System.currentTimeMillis();

  private ReentrantLock lock = new ReentrantLock();

  private boolean isReloadComplete = false;

  public AutoConfigurer(String path) throws FileNotFoundException, IOException {
    this.path = path;
    this.loadFile(this.path);
  }

  public AutoConfigurer(InputStream input) throws IOException {
    this.path = null;
    this.loadFile(input);
  }

  private void loadFile(InputStream input) throws IOException {

    try {
      lock.lock();

      // Properties p = new
      // Properties();
      if (null != input) {
        try {
          prop.clear();
          prop.load(input);
        } finally {
          if (null != input) {
            input.close();
            input = null;
          }
        }

      } else {
        throw new NullPointerException("input is null");
      }
    } finally {
      lock.unlock();
    }
  }

  private void loadFile(String path) throws FileNotFoundException, IOException {

    try {
      lock.lock();

      if (isReloadComplete)
        return;

      // Properties p = new
      // Properties();
      File file = new File(path);
      if (file.exists()) {
        lastModifyTime = file.lastModified();
        InputStream inStream = null;
        try {
          inStream = new FileInputStream(file);
          prop.clear();
          prop.load(inStream);
          isReloadComplete = true;
        } finally {
          if (null != inStream) {
            inStream.close();
            inStream = null;
          }
        }

      } else {
        throw new FileNotFoundException("file path: " + file.getAbsolutePath());
      }
    } finally {
      lock.unlock();
    }
    // return p;
  }

  private Properties getProperties() {
    long currentTime = System.currentTimeMillis();
    if (null != path && MIN_RELOAD_TIME <= currentTime - oldCheckTime) {
      oldCheckTime = currentTime;
      File file = new File(path);
      if (file.exists()) {
        long fileModifyTime = file.lastModified();
        if (fileModifyTime != lastModifyTime) {
          lastModifyTime = fileModifyTime;
          isReloadComplete = false;
          try {
            loadFile(path);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return prop;
  }

  public void setPath(String path) {
    if (null == this.path)
      this.path = path;
  }

  public String getPath() {
    return this.path;
  }

  /**
   * 当key存在返回对应的值 否则返加null
   * 
   * @param key
   * @return
   */
  public String getValue(String key) {
    String tempString = null;
    if (getProperties().containsKey(key)) {
      tempString = getProperties().getProperty(key);
    } else {
      return null;
    }

    if (null != tempString) {
      try {
        byte[] isoBs = tempString.getBytes("ISO-8859-1");
        String gbkString = new String(isoBs, charSet);
        return gbkString.trim();
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    } else {
      Exception e = new NullPointerException("The key is null" + key);
      e.printStackTrace();
    }
    return null;
  }

}
