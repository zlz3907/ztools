package com.ztools.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtilly {

	/**
	 * &#26684;&#24335;&#21270;&#24403;&#21069;&#26102;&#38388;
	 * @param format
	 * @return
	 */
	public static String getCurrentDateString(String format){
		SimpleDateFormat f1=new SimpleDateFormat(format);
		return f1.format(new Date());
	}
	/**
	 * @param format &#26684;&#24335;&#21270;&#23383;&#31526;&#20018;
	 * @param dateString &#26085;&#26399;&#23383;&#31526;&#20018;
	 * @return
	 */
	public static String getFormatDateString(String format,String dateString){
		try {
			SimpleDateFormat f1=new SimpleDateFormat(format);
			 Date date = f1.parse(dateString);
			 return f1.format(date);
		} catch (ParseException e) {
			System.err.println("format:"+format+" dateString:"+dateString);
		}
		return null;
	}
	
	/**
	 * &#20197;&#108;&#111;&#99;&#97;&#108;&#101;&#30340;&#26085;&#26399;&#21644;&#102;&#111;&#114;&#109;&#97;&#116;&#26684;&#24335;&#21270;&#23383;&#31526;&#20018;&#100;&#97;&#116;&#101;&#83;&#116;&#114;&#105;&#110;&#103;
	 * @param format
	 * @param dateString
	 * @param locale
	 * @return
	 */
	public static Date getFormatDate(String format, String dateString,
			Locale locale) {
		SimpleDateFormat f = null;
		Date date = null;
		try {
			if (locale == null) {
				f = new SimpleDateFormat(format);
				date = f.parse(dateString);

			} else {
				f = new SimpleDateFormat(format, locale);
				date = f.parse(dateString);
			}
		
		} catch (ParseException e) {
			System.err.println("locale:"+locale==null?Locale.getDefault():locale+" format:"+format+" dateString:"+dateString);
		}
		return date;
	}
	/**&#26412;&#26426;&#22120;&#30340;&#102;&#111;&#114;&#109;&#97;&#116;&#26684;&#21270;&#100;&#97;&#116;&#101;&#83;&#116;&#114;&#105;&#110;&#103;
	 * @param format
	 * @param dateString
	 * @return
	 */
	public static Date getFormatDate(String format,String dateString ){
		return getFormatDate(format,dateString,null);
	}
	
	/**
	 * @return &#24403;&#21069;&#26085;&#26399;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#39;
	 */
	public static String getCurrenDateString(){
		return getCurrentDateString("yyyy-MM-dd");
	}
	/**
	 * @return &#24403;&#21069;&#26085;&#26399;&#32;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;&#39;
	 */
	public static String getCurrenDateTimeString(){
		return getCurrentDateString("yyyy-MM-dd HH:mm:ss");
	}

	/**把所有的表示日期 都格式化为yyyy-MM-dd HH:mm:ss
	 * @param htmlStr
	 * @return
	 */
	public static String reDate(String htmlStr) {
//		 String regex =
//		 "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\._年月日\\s]((((0?[13578])|(1[02]))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\._年月日\\s]((((0?[13578])|(1[02]))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\._年月日\\s]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		// htmlStr = htmlStr.replace("年", "-").replace("月", "-").replace("日",
		// "").replace("时", ":").replace("分", ":").replace("秒", "").replace("：",
		// ":");
		htmlStr = htmlStr.replaceAll("年|月", "-").replaceAll("日|秒|号", "")
				.replaceAll("点|时|分|：", ":");
		// System.out.println(htmlStr);
//		String regex = "((\\d?[\\-\\/\\._\\s]?((((0?[13578])|(1[02]))[\\-\\/\\._\\s]((([1-2][0-9])|(3[01])|0?[1-9])))|(((0?[469])|(11))[\\-\\/\\._\\s]((([1-2][0-9])|(30)|0?[1-9])))|(0?2[\\-\\/\\._\\s](([1-2][0-9])|(0?[1-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\._\\s]((((0?[13578])|(1[02]))[\\-\\/\\._\\s](([1-2][0-9])|(3[01])|(0?[1-9])))|(((0?[469])|(11))[\\-\\/\\._\\s](([1-2][0-9])|(30)|(0?[1-9])))|(0?2[\\-\\/\\._\\s]((1[0-9])|(2[0-8])|(0?[1-9]))))))";
//		String regex = "(([19|20]?\\d{2})[\\-\\/\\._\\s](0?[1-9]|1[012])[\\-\\/\\._\\s](0?[1-9]|[10-31]))";
		String regex = "(((19|20)?\\d{2})[\\-\\/\\._\\s](1[012]|0?[1-9])[\\-\\/\\._\\s](([1-2][0-9])|(3[01])|0?[1-9]))";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(htmlStr);
		String txtDate = "";
		String txtTime = "";
		String str = "";

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate = df.format(date);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		if (m.find()) {
			str = m.group().trim();
			str = str.replaceAll("\\/", "-").replaceAll("\\.", "-").replaceAll(
					"_", "-").replace(" ", "-");
			if (str.length() <= 5 && str.indexOf("-") != -1) {
				str = nowDate.substring(0, 5) + str;
			} else if (str.length() < 10
					&& str.substring(0, str.indexOf("-")).length() < 4) {
				str = "20" + str;
			}
			txtDate = str.trim();
		}

		if (!"".equals(txtDate)) {
			String regexTime = "(\\s?(1[0-9]|2[0-3]|0?[1-9])(:[0-5]?\\d){1,2})";
			p = Pattern.compile(regexTime, Pattern.CASE_INSENSITIVE);
			m = p.matcher(htmlStr);
			if (m.find()) {
				txtTime = m.group().trim();
			}
		}

		if ("".equals(txtDate.trim())) {
			txtDate = nowDate;
		} else {
			if (!"".equals(txtTime)) {
				
				txtDate = txtDate + " " + txtTime;
				if(txtTime.length()<=5){
					txtDate=txtDate+":00";
				}
			} else {
				txtDate = txtDate + " 00:00:00";
			}
		}

		try {
			Date date2 = df.parse(txtDate);
			if (date2.getTime() > System.currentTimeMillis()) {
				txtDate = nowDate;
			} else {
				txtDate = df.format(date2);
			}

			// System.out.println(strDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			txtDate = nowDate;
		}

		return txtDate;
	}

	/**&#25226;&#25152;&#26377;&#30340;&#34920;&#31034;&#26085;&#26399;&#32;&#37117;&#26684;&#24335;&#21270;&#20026;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;
	 * @param htmlStr
	 * @return
	 */
	public static String reDateLink(String htmlStr) {
		htmlStr = htmlStr.replace("年", "-").replace("月", "-").replace("日", "");
		htmlStr = htmlStr.replaceAll("\\/", "-").replaceAll("\\.", "-")
				.replaceAll("_", "-").replace("(", "").replace(")", "")
				.replace("\r", "").replace("\t", "").replace("\n", "").trim();
		return htmlStr;
	}

	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(reDate(null));
	}
}
