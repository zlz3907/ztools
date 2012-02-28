package com.ztoole.test;

import java.io.UnsupportedEncodingException;

import com.ztools.mail.Base64;
import com.ztools.stringpro.StringPro;

public class StringTest {
    public static void main(String[] args) {
        byte[] bs = "���&b,;'\"*&^%$#@!~ѽ".getBytes();
        byte[] bs2 = Base64.encode(bs);
        System.out.println(new String(bs2));
        System.out.println(Base64.decode(new String(bs2)));
        
        String s64 = Base64.encode("���");
        
        System.out.println(s64);
        System.out.println(Base64.decode(s64));
       
        
        System.out.println(StringPro.getNormativeUrl("���"));
        System.out.println("");
    }
    
    
}
