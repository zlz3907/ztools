 package com.ztools.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
  
public class MD5Util  {   
    /**  
     * Ĭ�ϵ������ַ���ϣ���?���ֽ�ת���� 16 ���Ʊ�ʾ���ַ�,apacheУ�����ص��ļ�����ȷ���õľ���Ĭ�ϵ�������  
     */  
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',   
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };   
  
    protected static MessageDigest messagedigest = null;   
    static {   
        try {   
            messagedigest = MessageDigest.getInstance("MD5");   
        } catch (NoSuchAlgorithmException nsaex) {   
            System.err.println(MD5Util.class.getName()   
                    + "��ʼ��ʧ�ܣ�MessageDigest��֧��MD5Util��");   
            nsaex.printStackTrace();   
        }   
    }   
       
    /**  
     * ����ַ��md5У��ֵ  
     *   
     * @param s  
     * @return  
     */  
    public static String getMD5String(String s) {   
        return getMD5String(s.getBytes());   
    }   
       
    /**  
     * �ж��ַ��md5У�����Ƿ���һ����֪��md5����ƥ��  
     *   
     * @param password ҪУ����ַ�? 
     * @param md5PwdStr ��֪��md5У����  
     * @return  
     */  
    public static boolean checkPassword(String password, String md5PwdStr) {   
        String s = getMD5String(password);   
        return s.equals(md5PwdStr);   
    }   
       
    /**  
     * ����ļ���md5У��ֵ  
     *   
     * @param file  
     * @return  
     * @throws IOException  
     */  
    public static String getFileMD5String(File file) throws IOException {          
        InputStream fis;   
        fis = new FileInputStream(file);   
        messagedigest.reset();
        int site = 0;
        byte[] buffer = new byte[1024];
       int numRead = 0;   
        while ((numRead = fis.read(buffer)) > 0) {   
           messagedigest.update(buffer, 0, numRead);  
           site = site + numRead;
      }   
        System.out.println("Site:"+site);
      fis.close();   
//       BigInteger bigInt = new BigInteger(1, messagedigest.digest());   
//          
//         return bigInt.toString(16); 
      
      System.out.println("MD5 byte length: " + messagedigest.digest().length);
       return bufferToHex(messagedigest.digest());   
  }   
 
    /**  
     * JDK1.4�в�֧����MappedByteBuffer����Ϊ����update��������������������Ҫ����MappedByteBuffer��  
     * ԭ���ǵ�ʹ�� FileChannel.map ����ʱ��MappedByteBuffer �Ѿ���ϵͳ��ռ����һ����  
     * ��ʹ�� FileChannel.close �������޷��ͷ������ģ���FileChannel��û���ṩ���� unmap �ķ�����  
     * ��˻�����޷�ɾ���ļ������? 
     *   
    * ���Ƽ�ʹ��  
    *   
    * @param file  
     * @return  
    * @throws IOException  
     */  
    public static String getFileMD5String_old(File file) throws IOException {   
       FileInputStream in = new FileInputStream(file);   
       FileChannel ch = in.getChannel();   
       MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,   
                file.length());          messagedigest.update(byteBuffer);   
        return bufferToHex(messagedigest.digest());   
  }   
 
   public static String getMD5String(byte[] bytes) {   
       messagedigest.update(bytes);   
        try {
			return new String(bufferToHex(messagedigest.digest()).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return null;
    }   
  
   private static String bufferToHex(byte bytes[]) {   
        return bufferToHex(bytes, 0, bytes.length);   
    }   
 
    private static String bufferToHex(byte bytes[], int m, int n) {   
       StringBuffer stringbuffer = new StringBuffer(2 * n);   
       int k = m + n;   
        for (int l = m; l < k; l++) {   
           appendHexPair(bytes[l], stringbuffer);   
       }   
       return stringbuffer.toString();   
    }   
  
   private static void appendHexPair(byte bt, StringBuffer stringbuffer) {   
        char c0 = hexDigits[(bt & 0xf0) >> 4];// ȡ�ֽ��и� 4 λ������ת��, >>> Ϊ�߼����ƣ������λһ������?�˴�δ����}�ַ���кβ��?   
        char c1 = hexDigits[bt & 0xf];// ȡ�ֽ��е� 4 λ������ת��    
       stringbuffer.append(c0);   
       stringbuffer.append(c1);   
   }   
      
    public static void main(String[] args) throws IOException {   
       long begin = System.currentTimeMillis();   
  
        File file = new File("f:/wrar380sc.exe");  
//       File file = new File("D:/data1.xml");   
        String md5 = getFileMD5String(file);   
 
  System.out.println( ObjectByteUtilly.getBytesFromFile(file).length);
//      String md5 = getMD5String("a");   
          
       long end = System.currentTimeMillis();   
       System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000) + "s");   
   }   
//    7d7ff91ae0443d5d714febc6cc4132f2
//    7d7ff91ae0443d5d714febc6cc4132f2 
    // 5bc6fedb105bb036a2dc2edaa7f40b18
    // 5bc6fedb105bb036a2dc2edaa7f40b18
    // 09c1269321f3f3d004edf07ac186b943
    // 71cd48368a194cf931d676872bcd6e17
    // 2df79cd42e4f4854486427ac9d62718c
    // f16cd627c2940fb296b964370fae4744
    
//    74dd9f9229a0dd14f96f542dbff51f7b
//    804d73235a66ddfb9eb2e8b7a955a1fb 
//    c32756a893e4de967034f2abad42e596
//    2eb2668d2bf13379fa3584b9a9867b46
//    5c8f279c60dc6ea735f0532285086215
    

}  
