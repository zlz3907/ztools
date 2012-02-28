package com.ztools.xml;

import java.io.UnsupportedEncodingException;

import com.ztools.util.ObjectByteUtilly;
import com.ztools.util.StringUtilly;

@Deprecated
public class IXMLProcessHexCodeImpl implements IXMLProcess {

    private static String charsetName = "utf-8";

    @Override
    public String read(String str) {
        if (str != null && !"".equals(str)) {

            // if (StringPro.isHexDigit(str.toLowerCase())) {

            byte[] b = ObjectByteUtilly.stringToBytes16(str);

            if (b != null && b.length > 0) {

                // 测试字符编码
                charsetName = StringUtilly.getEncoding(b);
                // for (int i = 0; i < b.length; i++) {
                // byte emp = b[i];
                // if(emp<0&&b.length>1){
                // byte[] strByte = { emp, b[i+1]};
                // char c = new String(strByte).toCharArray()[0];
                // if(0x4E00<=c&&c<=0x9fa5){
                // charsetName = "utf-8";
                // }else{
                // charsetName = "GBK";
                // }
                // break;
                // }
                // }
                try {
                    return new String(b, charsetName);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            // } else {
            // return new IXMLProcessDefaultImpl().read(str);
            // }

        }
        return "";
    }

    @Override
    public String write(String str) {
        if (str != null && !"".equals(str)) {
            return ObjectByteUtilly.bytes16ToString(str.getBytes());
        }
        return "";
    }

}
