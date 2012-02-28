/**
 * StringPro.java
 * 
 * 2007-5-13
 */
package com.ztools.stringpro;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zhong Li Zhi
 * 
 */
public class StringPro {

    /**
     * 将URL串里的中文字符转换为编码形式返回，在转换过程中出现任何异常将会返回原URL串
     * 
     * @param url
     *            需要处理的URL串
     * @return 对中文进行编码后的URL串
     */
    public static String getNormativeUrl(String url) {

        if (null == url)
            return null;
        try {
            char[] cs = url.toCharArray();
            int urlLength = cs.length;
            StringBuilder sbd = new StringBuilder();
            for (int i = 0; i < urlLength; i++) {
                char c = cs[i];
                if (19968 <= c && c <= 40869) {
                    byte[] bs = Character.toString(c).getBytes();
                    sbd.append("%");
                    sbd.append(Integer.toHexString(bs[0] >>> 4 & 0xf));
                    sbd.append(Integer.toHexString(bs[0] & 0xf));
                    sbd.append("%");
                    sbd.append(Integer.toHexString(bs[1] >>> 4 & 0xf));
                    sbd.append(Integer.toHexString(bs[1] & 0xf));
                } else {
                    sbd.append(c);
                }
            }
            return sbd.toString();
        } catch (Exception e) {
            return url;
        }
    }

    /**
     * 判断指定字符串是否由数字组成
     * 
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        char[] numCs = number.toCharArray();
        for (int i = numCs.length - 1; i >= 0; i--) {
            if ('0' > numCs[i] || '9' < numCs[i])
                return false;
        }
        return true;
    }

    /**
     * 从字符串中提取阿拉伯数字
     * 
     * @param str
     * @return
     */
    public static String toExtractNumberFromString(String str) {
        if (null == str)
            return "";
        char[] cs = str.toCharArray();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if (('0' <= cs[i] && '9' >= cs[i]) || '.' == cs[i])
                sbd.append(cs[i]);
        }
        return sbd.toString();
    }

    /**
     * 从字符串中提取中文
     * 
     * @param str
     * @return
     */
    public static String toExtractChineseFromString(String str) {
        if (null == str)
            return "";
        char[] cs = str.toCharArray();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if ((19968 <= cs[i] && 40869 >= cs[i]))
                sbd.append(cs[i]);
        }
        return sbd.toString();
    }

    /**
     * 判断一个字符串是否为16进制的数
     * 
     * @param number
     * @return
     */
    public static boolean isHexDigit(String number) {
        char[] numCs = number.toCharArray();
        for (int i = numCs.length - 1; i >= 0; i--) {
            int k = numCs[i];
            if (k < 48 || k > 102 || (k > 57 && k < 97))
                return false;
        }
        return true;
    }

    /**
     * 将HTML代码中类似"&#NNNNN;"形式的数字转换为中文
     * 
     * @param numberString
     * @return
     */
    public static String formatNumberString(String numberString) {
        // System.out.println("in.. " + numberString);
        char[] numCs = numberString.toCharArray();
        int numCsLengthI = numCs.length;
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < numCsLengthI; i++) {
            if (i < numCsLengthI - 7 && '&' == numCs[i] && '#' == numCs[i + 1]
                    && ';' == numCs[i + 7]) {
                char[] numStr = { numCs[i += 2], numCs[++i], numCs[++i],
                        numCs[++i], numCs[++i] };
                String str = new String(numStr);
                if (isNumber(str)) {
                    sbd
                            .append(Character.toString((char) Integer
                                    .parseInt(str)));
                    i++;
                } else {
                    sbd.append("&#");
                    sbd.append(str);
                }
            } else {
                sbd.append(numCs[i]);
                // System.out.println("> " + i + sbd.toString());
                // System.out.println(" > " + i);
                // if (i == numCsLengthI - 8) {
                // for (int j = numCsLengthI - 7; j < numCsLengthI; j++) {
                // sbd.append(numCs[j]);
                // }
                // }
            }
        }

        return sbd.toString();
    }

    /**
     * 处理HTML标签里的文本
     * 
     * @param str
     * @param isKeepLetter
     *            是否保留英文字母
     * @return
     */
    public static String getKeepInnerText(String str, boolean isKeepLetter) {
        char[] strCs = formatNumberString(str).toCharArray();
        int strLenghtI = strCs.length;
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < strLenghtI; i++) {
            boolean isNeedKeep = 0xff > strCs[i]
                    && (0x30 <= strCs[i] || 0x39 >= strCs[i])
                    || 0x3000 == strCs[i];
            isNeedKeep = isKeepLetter ? isNeedKeep : isNeedKeep
                    && (0x61 > strCs[i] || 0x7a < strCs[i])
                    && (0x41 > strCs[i] || 0x5a < strCs[i]);
            if (!isNeedKeep)
                sbd.append(strCs[i]);
        }
        return sbd.toString();
    }

    /**
     * 剔除字符串里的HTML代码
     * 
     * @param str
     *            要剔除HTML的字符串
     * @return 剔除HTML后的字符串
     */
    public static String keepHtml(String str) {
        // |[^\\u4e00-\\u9fa5]{18,}
        if (null != str)
            return str.replaceAll("<[^>]*>|(&[#a-z0-9]{1,6};)|[\t\n\r 　]", "");
        return "";

    }

    /**
     * 创建消息摘要
     * 
     * @param inputString
     * @return
     */
    public static String createDigest(String inputString) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        byte[] inputBs = inputString.getBytes();
        byte[] digestBs;
        char[] digestCs;
        int digestLengthI;
        MessageDigest mdTemp;

        try {
            mdTemp = MessageDigest.getInstance("SHA");
            digestBs = mdTemp.digest(inputBs);
            digestLengthI = digestBs.length;
            digestCs = new char[digestLengthI * 2];
            for (int i = 0, k = 0; i < digestLengthI; i++) {
                digestCs[k++] = hexDigits[digestBs[i] >>> 4 & 0xf];
                digestCs[k++] = hexDigits[digestBs[i] & 0xf];
            }
            return new String(digestCs);
        } catch (NoSuchAlgorithmException e) {
            // e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<Character, Object> createDictionary(Set<String> patterns) {
        Map<Character, Object> stairIndex = new HashMap<Character, Object>();
        Map<Character, Object> tmpMap = null;
        Map<Character, Object> pMap = null;
        for (String str : patterns) {
            int strLength = str.length();
            pMap = stairIndex;
            for (int i = 0; i < strLength; i++) {
                if (null == pMap.get(str.charAt(i))) {
                    pMap.put(str.charAt(i), new HashMap<Character, Object>());
                }
                if (i == strLength - 1) {
                    Object obj = pMap.get(str.charAt(i));
                    if (obj instanceof HashMap)
                        tmpMap = (HashMap<Character, Object>) obj;
                    else
                        tmpMap = new HashMap<Character, Object>();
                    tmpMap.put('\0', 0);
                    pMap.put(str.charAt(i), tmpMap);
                } else {
                    pMap = (HashMap<Character, Object>) pMap.get(str.charAt(i));
                }
            }
        }
        return stairIndex;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Integer> quickMatchTest(String text,
            Map<Character, Object> patternsMap) {
        Map<String, Integer> rMap = new HashMap<String, Integer>();
        char[] content = text.toCharArray();
        int cLength = content.length;
        Map<Character, Object> m;
        for (int i = 0; i < cLength;) {
            Object obj = patternsMap.get(content[i]);
            // System.out.println("# " + content[i] + "/" + i);
            if (obj != null) {
                int begin = i;
                int end = -1;
                while (obj instanceof Map) {
                    // if (obj instanceof Map) {
                    m = (Map<Character, Object>) obj;
                    if (null != m.get('\0')) {
                        if (1 == m.size()) {
                            end = i;
                            break;
                        }
                        end = i;
                    }
                    if (i == cLength - 1) {
                        break;
                    }
                    obj = m.get(content[++i]);
                    // System.out.println("\t con:" + obj);
                    // } else {
                    // break;
                    // }
                }
                // if (10 == content[i])
                // System.err.println("\t" + begin + "/" + end);
                if (end >= begin) {
                    String str = text.substring(begin, end + 1);
                    Integer cnt = rMap.get(str);
                    cnt = null == cnt ? 1 : ++cnt;
                    rMap.put(str, cnt);
                    i = end + 1;
                    // System.out.println("\ti=end ...");
                } else {
                    // System.out.println("\tend <= begin ... ");
                    i = begin + 1;
                }
            } else {
                i++;
            }
        }
        return rMap;
    }

    /**
     * @param text
     * @param patternsMap
     * @return
     * @deprecated
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Integer> quickMatch(String text,
            Map<Character, Object> patternsMap) {
        Map<String, Integer> rMap = new HashMap<String, Integer>();
        char[] content = text.toCharArray();
        int cLength = content.length;
        boolean isEnd = false;
        Map<Character, Object> m;
        for (int i = 0; i < cLength; i++) {
            Object obj = patternsMap.get(content[i]);
            if (obj != null) {
                m = (Map<Character, Object>) obj;
                if (null == m.get('\0')) {
                    int begin = i;
                    int end = 0;
                    while (true) {
                        if (i == cLength - 1) {
                            i++;
                            break;
                        }
                        Object o = m.get(content[++i]);
                        if (o != null) {
                            m = (Map<Character, Object>) o;
                            // if (null == m)
                            // break;
                            isEnd = null != m.get('\0');
                            if (isEnd) {
                                end = i;
                            }
                            if (1 == m.size() && isEnd) {
                                end = i;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (0 != end && end > begin) {
                        String str = text.substring(begin, end + 1);
                        Integer cnt = rMap.get(str);
                        cnt = null == cnt ? 1 : ++cnt;
                        rMap.put(str, cnt);
                        i = end + 1;
                    }
                    i--;
                } else {
                    Integer cnt = rMap.get(Character.toString(content[i]));
                    cnt = null == cnt ? 1 : ++cnt;
                    rMap.put(Character.toString(content[i]), cnt);
                }
            }
        }
        return rMap;
    }

    /**
     * 判断一个字符串是否能被指定的字符集正确编码
     * 
     * @param str
     *            需要验证的字符串
     * @param charsetName
     *            字符集名称
     * @return true可以被编码，false不能被编码
     */
    public static boolean isSupportedCharset(String str, String charsetName) {
        boolean isLegalArgs = str != null && null != charsetName ? !""
                .equals(charsetName) : false;
        if (isLegalArgs) {
            try {
                Charset charset = Charset.forName(charsetName);
                CharsetEncoder cEncoder = charset.newEncoder();
                return cEncoder.canEncode(str);
            } catch (UnsupportedCharsetException e) {
                return false;
            } catch (UnsupportedOperationException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 为将要在主串中查找的模板串集合建立字符索引
     * 
     * @param patterns
     *            模板串集合
     * @return 树形结构的索引
     * @deprecated
     */
    @SuppressWarnings("unchecked")
    public static Map<Character, Object> createIndexOfPatterns(
            Set<String> patterns) {
        Map<Character, Object> stairIndex = new HashMap<Character, Object>();
        Map<Character, Object> tmpMap = null;
        Map<Character, Object> pMap = null;
        for (String fs : patterns) {
            int fsLength = fs.length();
            if (null == stairIndex.get(fs.charAt(0))) {
                stairIndex.put(fs.charAt(0), 0);
            }
            pMap = stairIndex;
            for (int j = 1; j < fsLength; j++) {
                Object obj = pMap.get(fs.charAt(j - 1));
                if (obj instanceof Map) {
                    tmpMap = (Map<Character, Object>) obj;
                    if (null == tmpMap.get(fs.charAt(j))) {
                        tmpMap.put(fs.charAt(j), 0);
                    }
                } else {
                    tmpMap = new HashMap<Character, Object>();
                    tmpMap.put(fs.charAt(j), 0);
                    pMap.put(fs.charAt(j - 1), tmpMap);
                }
                pMap = tmpMap;
            }
        }
        return stairIndex;
    }

    /**
     * 查找一个模板串集合在主串中分别出现的次数
     * 
     * @param text
     *            主串
     * @param patternsMap
     *            用来辅助查找模板串集合的索引，该索引可以通过createIndexOfPatterns()得到
     * @param keys
     *            模板串集合
     * @return 各模板串在主串中出现次数的映射关系，键为模板串，值为该键在主串中出现的次数
     * @deprecated
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Integer> matchesPatterns(String text,
            Map<Character, Object> patternsMap, Set<String> keys) {
        Map<String, Integer> rMap = new HashMap<String, Integer>();
        char[] content = text.toCharArray();
        int cLength = content.length;
        for (int i = 0; i < cLength; i++) {
            StringBuilder sbd = new StringBuilder();
            char pChar = content[i];
            Object childMap = patternsMap.get(pChar);
            int loopCount = 0;
            sbd.append(pChar);
            while (null != childMap) {
                int next = i + ++loopCount;
                if (childMap instanceof Map) {
                    if (next < cLength) {
                        char c = content[next];
                        childMap = ((Map<Character, Object>) childMap).get(c);
                        if (null == childMap) {
                            if (keys.contains(sbd.toString())) {
                                String key = sbd.toString();
                                Integer count = rMap.get(key);
                                if (null != count) {
                                    rMap.put(key, ++count);
                                } else {
                                    rMap.put(key, 1);
                                }
                                i += loopCount - 1;
                                break;
                            }
                        }
                        sbd.append(c);
                    } else {
                        break;
                    }
                } else {
                    String key = sbd.toString();
                    Integer count = rMap.get(key);
                    if (null != count) {
                        rMap.put(key, ++count);
                    } else {
                        rMap.put(key, 1);
                    }
                    i += loopCount - 1;
                    break;
                }
            }
        }
        return rMap;
    }

    /**
     * 将指定字符串中所有与正则表达式相匹的项以豆号割，并返回。
     * 
     * @param str
     * @param regex
     *            正则
     * @return 返回所有匹配到的字符串，以豆号分割
     */
    public static String getMatcheGroupString(String str, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(str);
        StringBuilder sbd = new StringBuilder();
        while (m.find()) {
            String t = m.group();
            if (sbd.length() != 0 && !t.isEmpty())
                sbd.append(",");
            sbd.append(t);
        }
        return sbd.toString();

    }

    public static Set<String> getMatcheGroupSet(String str, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(str);
        Set<String> sbd = new HashSet<String>();
        while (m.find()) {
            sbd.add(m.group());
        }
        return sbd;

    }

    public static boolean hasMatchFind(String str, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 转换全角字符为半字符
     * 
     * @param str
     * @return
     */
    public static String formatSBCCaseToDBCCase(String str) {
        String ret = str;
        if (null != ret) {
            char[] cs = ret.toCharArray();
            int csLength = cs.length;
            final int DIFFERENCE_VALUE = 65248;
            for (int i = 0; i < csLength; i++) {
                if (65296 <= cs[i] && cs[i] <= 65305) {
                    cs[i] = (char) (cs[i] - DIFFERENCE_VALUE);
                    continue;
                }
                if (65345 <= cs[i] && cs[i] <= 65370) {
                    cs[i] = (char) (cs[i] - DIFFERENCE_VALUE);
                    continue;
                }
                if (65313 <= cs[i] && cs[i] <= 65388) {
                    cs[i] = (char) (cs[i] - DIFFERENCE_VALUE);
                    continue;
                }
            }
            ret = new String(cs);
        }
        return ret;
    }

    public static List<Date> parserDate(String str, Date... dates) {
        List<Date> dList = new ArrayList<Date>();
        if (null != str) {
            String[] ds = str.split(",");
            for (int i = 0; i < ds.length; i++) {
                Date d = StringPro.formatStringToDate(ds[i], dates);
                if (null != d) {
                    dList.add(d);
                }
            }
        }
        return dList;
    }

    public static Date formatStringToDate(String date, Date... dates) {
        if (null != date) {
            String year = "";
            String month = "";
            String day = "";
            date = date.trim();
            if (date.matches("\\d{6,8}")) {
                year = (date.substring(0, 4));
                month = (date.substring(4, 6));
                if (8 == date.length()) {
                    day = date.substring(6);
                } else {
                    day = "01";
                }
            } else if (date
                    .matches("\\d{4}[^\\d]\\d{1,2}([^\\d]\\d{1,2})?[^\\d]?")) {
                year = (date.substring(0, 4));
                String[] ymd = date.split("[^\\d]");
                month = ymd[1];
                if (3 == ymd.length) {
                    day = ymd[2];
                } else {
                    day = "01";
                }
            } else {
                return null;
            }

            if (12 < Integer.parseInt(month) || 31 < Integer.parseInt(day)) {
                return null;
            }
            Calendar c = Calendar.getInstance();
            int compYear = c.get(Calendar.YEAR);
            int arrgYear = Integer.parseInt(year);
            if (arrgYear > compYear || arrgYear < (compYear - 1000)) {
                return null;
            }
            date = year + "-" + month + "-" + day;
            try {
                Date d = DateFormat.getDateInstance().parse(date);
                boolean isFilter = false;
                if (null != dates && 0 < dates.length) {
                    switch (dates.length) {
                    case 1:
                        if (d.getTime() < dates[0].getTime()) {
                            isFilter = true;
                        }
                        break;
                    default:
                        if (d.getTime() < dates[0].getTime()
                                || d.getTime() > dates[1].getTime()) {
                            isFilter = true;
                        }
                        break;
                    }
                }
                if (!isFilter) {
                    return DateFormat.getDateInstance().parse(date);
                }
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        StringBuilder sbd = new StringBuilder();
        sbd.append((char) 10);

        String[] str = { sbd.toString().concat("中国"), "学历", "历史", "北平", "广州",
                "武汉", "天地人", "天地", "上海", "天津", "1 4", "501", "天上" };
        Set<String> set = new java.util.HashSet<String>();
        java.util.Collections.addAll(set, str);
        String text = "天地人";
        Map<Character, Object> m = StringPro.createDictionary(set);
        // Map<Character, Object> m = StringPro.createIndexOfPatterns(set);
        System.out.println(m.toString());
        // long t = new Date().getTime();
        System.out.println(text);
        Map<String, Integer> rm = StringPro.quickMatchTest(text, m);
        // Map<String, Integer> rm = StringPro.matchesPatterns(text, m, set);
        // System.out.println("耗时：" + (new Date().getTime() - t));
        for (String s : rm.keySet()) {
            System.out.println(s + ": " + rm.get(s));
        }

        System.out.println(createDigest("abc"));

    }
}
