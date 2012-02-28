package com.ztools.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

/**byte[]数组与对象的工具类
 * @author zouren
 * @time 2010-12-7����05:11:29
 *	
 */
public class ObjectByteUtilly {


	static String str16 = "0123456789ABCDEF";
	static char[] chars = str16.toCharArray();  
	
	/**&#25226;&#23383;&#31526;&#20018;&#30340;&#98;&#121;&#116;&#101;&#25968;&#32452;&#36827;&#34892;&#21387;&#32553;&#20026;&#49;&#54;&#20301;&#23383;&#31526;&#20018;
	 * @param bs
	 * @return btye[]
	 */
	public static String bytes16ToString(byte[] bs){
		StringBuffer re = new StringBuffer();
		 int bit; 
		   for (int i = 0; i < bs.length; i++) {  
	            bit = ((bs[i]+128)) >> 4;  
		   		re.append(chars[bit]);
	            bit = (bs[i]+128) & 0x0f ;  
	            re.append(chars[bit]);  
	        }  
		return re.toString();
	}
	

	/** &#21387;&#32553;&#20026;&#49;&#54;&#20301;&#23383;&#31526;&#20018;&#32;&#36824;&#21407;&#20026;&#19968;&#20010;&#23383;&#31526;&#20018;&#30340;&#98;&#121;&#116;&#101;&#25968;&#32452;
	 * @param str
	 * @return
	 */
	public static byte[] stringToBytes16(String str) {
		byte[] re = new byte[str.length() / 2];
		int n;
		for (int i = 0; i < re.length; i++) {
			n = str16.indexOf(str.charAt(2*i)) * 16-128;
			n += str16.indexOf(str.charAt(2*i + 1));
			re[i] = (byte)n ;
		}
		return re;
	}



	/**
	 *&#23545;&#35937;&#36716;&#20026;&#98;&#121;&#116;&#101;&#91;&#93;&#25968;&#32452;
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] ObjectToByte(Object obj)   {
		byte[] bytes = null;
		// object to bytearray
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		try {
			if(obj!=null){
				bo = new ByteArrayOutputStream();
				 oo= new ObjectOutputStream(bo);
				oo.writeObject(obj);
				bytes = bo.toByteArray();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(oo!=null){
					oo.close();
				}
				if(bo!=null){
					bo.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return bytes;
	}

	/**
	 * &#98;&#121;&#116;&#101;&#91;&#93;&#36716;&#20026;&#23545;&#35937;&#25968;&#32452;
	 * @param bytes
	 * @return �������
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteToObject(byte[] bytes) throws 
			ClassNotFoundException {
		// bytearray to object
		Object obj = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		try {
			if (bytes != null) {
				bi = new ByteArrayInputStream(bytes, 0,
						bytes.length);
				oi = new ObjectInputStream(bi);
				
				obj = oi.readObject();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bi!=null){
					bi.close();
				}
				if(oi!=null){
					oi.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return obj;
	}
	
	 /**
	 * @param n  &#25968;&#23383;
	 * @param scope  &#22810;&#23569;&#20301;&#98;&#121;&#116;&#101;&#25968;&#32452;&#32;&#40664;&#35748;&#20026;&#56;
	 * @return &#25968;&#23383;&#21387;&#32553;&#21518;&#30340;&#98;&#121;&#116;&#101;&#25968;&#32452;
	 */
	public static byte[] compressNumberToByteArray(long n, int scope) {
        byte[] bs = new byte[scope > 0 ? scope : 8];
        for (int j = 0, i = 8 * (scope - 1); i >= 0; i -= 8, j++) {
            bs[j] = (byte) (n >> i & 0xff);
        }
        return bs;
    }

    /** &#21453;&#32534;&#35793;&#25968;&#23383;&#32;&#32;
     * @param&#32;&#98;&#121;&#116;&#101;&#25968;&#32452;&#32;&#32;&#26159;&#20197;&#56;&#20301;&#30340;&#32534;&#35793;&#25104;&#30340;&#25968;&#32452;&#32;
     * @return&#21453;&#32534;&#35793;&#25968;&#23383;&#32;&#32;&#32;&#32;&#32;&#32;
     */
    public static long decompressByteArrayToNumber(byte[] bs) {
        if (null != bs && 0 < bs.length) {
            long ret = 0;
            for (int i = 0; i < bs.length; i++) {
                ret += ((long) (bs[i] & 0xff) << ((bs.length - 1 - i) * 8));
            }
            return ret;
        } else {
            throw new NullPointerException("bs can't null.");
        }
    }
    /** *//**
     * &#25226;&#19968;&#20010;&#25991;&#20214;&#36716;&#20026;&#98;&#121;&#116;&#101;&#25968;&#32452;
     * @Author Sean.guo
     * @EditTime 2007-8-13 ����11:45:28
     */
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        FileInputStream stream = null;
        ByteArrayOutputStream out = null; 
        try {
             stream = new FileInputStream(f);
             out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
          
            return out.toByteArray();
        } catch (IOException e){
        }finally{
        	try {
				if(stream!=null){
					  stream.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return null;
    } 
    
    /**&#25226;&#25991;&#20214;&#36716;&#20026;&#117;&#116;&#102;&#45;&#56;&#32534;&#30721;&#30340;&#23383;&#31526;&#25968;&#32452;
     * @param f 
     * @return
     */
    public static char[]getCharsFromFile(File f){
        if (f == null) {
            return null;
        }
    	BufferedReader fileStream = null;
		CharArrayWriter out = null;
		try{
			 fileStream = new BufferedReader(new InputStreamReader(
						new FileInputStream(f), "utf-8"));
			 out = new CharArrayWriter();
		   char[] b = new char[1024];
            int n;
            while ((n = fileStream.read(b)) != -1){
            	 out.write(b, 0, n);
            }
            return out.toCharArray();
            
        } catch (IOException e){
        }finally{
        	try {
				if(fileStream!=null){
					fileStream.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return null;
    }


}
