package com.ztools.conf;

import java.io.File;
import java.net.URL;

/**
 * @author Zhong Lizhi
 * 
 */
public final class Environment {
    private static String context = null;

    /**
     * @return &#24471;&#21040;&#24403;&#21069;&#39033;&#30446;&#24037;&#31243;&#30340;&#26681;&#36335;&#24452;
     */
    public static final String getContext() {
        if (null != context) {
            return context;
        } else {
            String slash = "/";
            URL url = Environment.class.getResource(slash);

            String resourcPath = "";
            if (null != url) {
                resourcPath = Environment.class.getResource(slash).getPath();
                char endChar = resourcPath.charAt(resourcPath.length() - 1);
                if (!slash.equals(Character.toString(endChar))) {
                    context = resourcPath + slash;
                } else {
                    context = resourcPath;
                }
                context = unescape(context);
                return context;
            }
//            System.out.println("url is null!");
            return "";
        }
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) {
        System.out.println("context: " + getContext());
        System.out.println("conf: " + new File("conf").exists());
    }
}
