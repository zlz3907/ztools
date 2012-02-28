package com.ztools.util;

import java.io.UnsupportedEncodingException;

import org.loon.test.encoding.ParseEncoding;

/**
 * ���ַ�Ĳ���
 * 
 * @author zouren
 * @time 2010-12-7����05:14:21
 * 
 */
public class StringUtilly {

	/**
	 * &#25226;&#23383;&#31526;&#20018;&#36716;&#20026;&#117;&#110;&#105;&#99;&#111;&#100;&#101;&#32534;&#30721;
	 * @param str
	 * @return
	 */
	public static String stringToNumString(String str) {
		StringBuffer re = null;
		if (str != null && !"".equals(str)) {
			re = new StringBuffer();
			char[] strArry = str.toCharArray();
			for (int i = 0; i < strArry.length; i++) {
				re.append("&#").append(Integer.toString(strArry[i]))
						.append(";");
			}
			return re.toString();
		}
		return null;
	}

	/**
	 * &#36827;&#34892;&#23383;&#31526;&#20018;&#35268;&#26684;&#21270;&#65288;&
	 * #20840;&#35282;&#36716;&#21322;&#35282;&#65292;&#22823;&#20889;&#36716;&#
	 * 23567;&#20889;&#22788;&#29702;&#65289;
	 * 
	 * @param str
	 * @param charset
	 * @return
	 */
	public static String regularizeString(String str, String charset) {
		StringBuffer re = new StringBuffer();
		try {
			str = new String(str.getBytes(), charset);
			for (int i = 0; i < str.length(); i++) {
				char emp = str.charAt(i);
				re.append(regularize(emp));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return re.toString();
	}

	/**
	 *&#36827;&#34892;&#23383;&#31526;&#35268;&#26684;&#21270;&#65288;&#20840;&#35282;&#36716;&#21322;&#35282;&#65292;&#22823;&#20889;&#36716;&#23567;&#20889;&#22788;&#29702;&#65289;
	 * 
	 * @param input
	 * @return char
	 */
	private static char regularize(char input) {
		if (input == 12288) {
			input = (char) 32;

		} else if (input > 65280 && input < 65375) {
			input = (char) (input - 65248);

		} else if (input >= 'A' && input <= 'Z') {
			input += 32;
		}

		return input;
	}

	/**
	 * @param str
	 * @return&#23383;&#31526;&#19981;&#20026;&#31354;&#20018;&#21644;&#19981;&#20026;&#110;&#117;&#108;&#108;
	 */
	public static boolean isNotBlank(String str) {
		return (str != null && !"".equals(str));
	}

	/**
	 * &#21462;&#24471;&#19968;&#20010;&#27721;&#23383;&#30340;&#85;&#110;&#105;
	 * &#99;&#111;&#100;&#101;&#30721;
	 * &#25226;&#85;&#110;&#105;&#99;&#111;&#100;
	 * &#101;&#30721;&#20998;&#35299;&#20026;&#20004;&#20010;&#49;&#54;&#36827;&#21046;&#25968;&#25454;&#23383;&#31526;&#20018;&#65288;&#20002;&#24323;&#21069;&#20004;&#20010;&#23383;&#33410;&#65289;
	 * &#25226;&#36825;&#20004;&#20010;
	 * &#49;&#54;&#36827;&#21046;&#25968;&#25454;
	 * &#23383;&#31526;&#20018;&#36716;
	 * &#25442;&#25104;&#20108;&#36827;&#21046;&#25968;&#25454;&#23383;&#31526;&#20018;
	 * &#25226;&#20108;&#36827;&#21046;&#25968;&#25454;&#23383;&#31526;&#20018;&#20998;&#35299;&#20026;&#19977;&#20010;&#20018;&#65292;&#31532;&#19968;&#20010;&#20018;&#20026;&#52;&#65288;&#48;&#126;&#52;&#65289;&#20010;&#20301;&#65292;&#22312;&#39640;&#20301;&#21152;
	 * &#19978;&#26631;&#35760;&#20301;&#8220;&#49;&#49;&#49;&#48;&#8221;&#65292;
	 * &#31532;&#20108;&#65288;&#52;&#126;&#49;&#48;&#65289;&#12289;&#19977;&#20010;&#65288;&#49;&#48;&#126;&#49;&#54;&#65289;&#20018;&#22343;&#20026;&#54;&#20010;&#20301;&#65292;&#20998;&#21035;&#22312;&#39640;&#20301;&#21152;&#19978;&#8220;&#49;&#48;&#8221;&#26631;&#35760;&#20301;
	 * &#32;&#25226;&#36825;&#19977;&#20010;&#20108;&#36827;&#21046;&#20018;&#20998;&#21035;&#36716;&#25442;&#20026;&#49;&#48;&#36827;&#21046;&#25968;&#25454;&#24182;&#36171;&#20540;&#32473;&#23383;&#33410;&#22411;&#25968;&#32452;
	 * &#26681;&#25454;&#36825;&#20010;&#23383;&#33410;&#22411;&#25968;&#32452;&#26500;&#36896;&#85;&#84;&#70;&#45;&#56;&#23383;&#31526;
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String gb2312ToUtf8(String str)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (s.charAt(0) > 0x80) {
				byte[] bytes = s.getBytes("Unicode");
				String binaryStr = "";
				for (int j = 2; j < bytes.length; j += 2) {
					// the first byte
					String hexStr = getHexString(bytes[j + 1]);
					String binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
					// the second byte
					hexStr = getHexString(bytes[j]);
					binStr = getBinaryString(Integer.valueOf(hexStr, 16));
					binaryStr += binStr;
				}
				// convert unicode to utf-8
				String s1 = "1110" + binaryStr.substring(0, 4);
				String s2 = "10" + binaryStr.substring(4, 10);
				String s3 = "10" + binaryStr.substring(10, 16);
				byte[] bs = new byte[3];
				bs[0] = Integer.valueOf(s1, 2).byteValue();
				bs[1] = Integer.valueOf(s2, 2).byteValue();
				bs[2] = Integer.valueOf(s3, 2).byteValue();
				String ss = new String(bs, "UTF-8");
				sb.append(ss);
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}

	private static String getHexString(byte b) {
		String hexStr = Integer.toHexString(b);
		int m = hexStr.length();
		if (m < 2) {
			hexStr = "0" + hexStr;
		} else {
			hexStr = hexStr.substring(m - 2);
		}
		return hexStr;
	}

	private static String getBinaryString(int i) {
		String binaryStr = Integer.toBinaryString(i);
		int length = binaryStr.length();
		for (int l = 0; l < 8 - length; l++) {
			binaryStr = "0" + binaryStr;
		}
		return binaryStr;
	}

	/**
	 *&#21028;&#26029;&#23383;&#31526;&#20018;&#30340;&#32534;&#30721;
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) { 
		return getEncoding(str.getBytes());
	}
	private static ParseEncoding  parseEncoding =null;
	/**&#21028;&#26029;&#23383;&#31526;&#20018;&#30340;&#32534;&#30721;
	 * @param stringByte  &#23383;&#31526;&#20018;&#36716;&#20026;&#30340;&#98;&#121;&#116;&#101;&#25968;&#32452;
	 * @return
	 */
	public static String getEncoding(byte[] stringByte){
		if(parseEncoding==null){
			  parseEncoding = new ParseEncoding();
		}
		return parseEncoding.getEncoding(stringByte);
		
	}

	/**
	 *&#23558;&#23383;&#31526;&#20018;&#115;&#116;&#114;&#32534;&#30721;&#26684;&#32;&#24335;&#36716;&#25104;&#99;&#104;&#97;&#114;&#115;&#101;&#116;&#78;&#97;&#109;&#101;&#26684;&#24335;&#23383;&#31526;&#20018;
	 * 
	 * @param str
	 * @param charsetName
	 *            &#40;&#40664;&#35748;&#20540;&#20026;&#117;&#116;&#102;&#45;&#56
	 *            ;&#41;
	 * @return
	 */
	public static String TranEncodeBycharsetName(String str, String charsetName) {
		try {
			if (str != null) {
				charsetName = charsetName == null ? "utf-8" : charsetName;
				String strEncode = getEncoding(str);
				String temp = new String(str.getBytes(strEncode), charsetName);
				return temp;
			}
		} catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		System.out.println(StringUtilly.stringToNumString("数据库key为长度13位 并都是数据的字符串"));

	}
}
