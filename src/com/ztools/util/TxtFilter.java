package com.ztools.util;

import java.util.regex.Pattern;

import com.ztools.stringpro.Trie;
import com.ztools.stringpro.TrieMap;

public class TxtFilter {

	/**
	 * ��������
	 * 
	 * @param ����
	 */
	private static final String ascii[] = { "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", " ", "!", "&quot;", "#", "$", "%", "&amp;",
			"&apos;", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2",
			"3", "4", "5", "6", "7", "8", "9", ":", ";", "&lt;", "=", "&gt;",
			"?", "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z", "[", "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", "" };

	private static final String[] latins = { "&AACUTE;", "&AACUTE;", "&ACIRC;",
			"&ACIRC;", "&ACUTE;", "&AELIG;", "&AELIG;", "&AGRAVE;", "&AGRAVE;",
			"&ALEFSYM;", "&ALPHA;", "&ALPHA;", "&AMP;", "&AMP;", "&AND;",
			"&ANG;", "&ARING;", "&ARING;", "&ASYMP;", "&ATILDE;", "&ATILDE;",
			"&AUML;", "&AUML;", "&BDQUO;", "&BETA;", "&BETA;", "&BRVBAR;",
			"&BULL;", "&CAP;", "&CCEDIL;", "&CCEDIL;", "&CEDIL;", "&CENT;",
			"&CHI;", "&CHI;", "&CIRC;", "&CLUBS;", "&CONG;", "&COPY;",
			"&COPY;", "&CUP;", "&CURREN;", "&DAGGER;", "&DAGGER;", "&DARR;",
			"&DARR;", "&DEG;", "&DELTA;", "&DIAMS;", "&DIVIDE;", "&DIVIDE;",
			"&EACUTE;", "&EACUTE;", "&ECIRC;", "&ECIRC;", "&EGRAVE;",
			"&EGRAVE;", "&EMPTY;", "&EMSP;", "&EMSP;", "&ENSP;", "&ENSP;",
			"&EPSILON;", "&EQUIV;", "&ETA;", "&ETA;", "&ETH;", "&ETH;",
			"&EUML;", "&EUML;", "&EURO;", "&EXIST;", "&FNOF;", "&FORALL;",
			"&FRAC12;", "&FRAC14;", "&FRAC34;", "&FRASL;", "&GAMMA;",
			"&GAMMA;", "&GE;", "&GT;", "&GT;", "&HARR;", "&HEARTS;",
			"&IACUTE;", "&IACUTE;", "&ICIRC;", "&ICIRC;", "&IEXCL;",
			"&IGRAVE;", "&IGRAVE;", "&IMAGE;", "&INT;", "&IOTA;", "&IQUEST;",
			"&ISIN;", "&IUML;", "&IUML;", "&KAPPA;", "&LAMBDA;", "&LAMBDA;",
			"&LANG;", "&LAQUO;", "&LARR;", "&LCEIL;", "&LDQUO;", "&LE;",
			"&LFLOOR;", "&LOWAST;", "&LOZ;", "&LRM;", "&LSAQUO;", "&LSQUO;",
			"&LT;", "&LT;", "&MACR;", "&MDASH;", "&MICRO;", "&MIDDOT;",
			"&MINUS;", "&MU;", "&MU;", "&NBSP;", "&NBSP;", "&NDASH;", "&NE;",
			"&NI;", "&NOT;", "&NOTIN;", "&NSUB;", "&NTILDE;", "&NTILDE;",
			"&NU;", "&NU;", "&OACUTE;", "&OACUTE;", "&OCIRC;", "&OCIRC;",
			"&OELIG;", "&OELIG;", "&OGRAVE;", "&OGRAVE;", "&OLINE;",
			"&OMICRON;", "&OPLUS;", "&OR;", "&ORDF;", "&ORDM;", "&OSLASH;",
			"&OSLASH;", "&OTILDE;", "&OTILDE;", "&OTIMES;", "&OUML;", "&OUML;",
			"&PARA;", "&PART;", "&PERMIL;", "&PERP;", "&PHI;", "&PHI;", "&PI;",
			"&PI;", "&PIV;", "&PLUSMN;", "&POUND;", "&PRIME;", "&PRIME;",
			"&PROD;", "&PROP;", "&PSI;", "&PSI;", "&QUOT;", "&QUOT;",
			"&RADIC;", "&RANG;", "&RAQUO;", "&RARR;", "&RARR;", "&RCEIL;",
			"&RDQUO;", "&REAL;", "&REG;", "&REG;", "&RFLOOR;", "&RHO;",
			"&RHO;", "&RLM;", "&RSAQUO;", "&RSQUO;", "&SBQUO;", "&SCARON;",
			"&SCARON;", "&SDOT;", "&SECT;", "&SHY;", "&SIGMA;", "&SIGMA;",
			"&SIGMAF;", "&SIM;", "&SPADES;", "&SUB;", "&SUBE;", "&SUP;",
			"&SUP1;", "&SUP2;", "&SUP3;", "&SUPE;", "&SZLIG;", "&THERE4;",
			"&THETA;", "&THETA;", "&THETASYM;", "&THINSP;", "&THORN;",
			"&THORN;", "&TILDE;", "&TIMES;", "&TIMES;", "&TRADE;", "&UACUTE;",
			"&UACUTE;", "&UARR;", "&UARR;", "&UCIRC;", "&UCIRC;", "&UGRAVE;",
			"&UGRAVE;", "&UML;", "&UPSIH;", "&UPSILON;", "&UPSILON;", "&UUML;",
			"&UUML;", "&XI;", "&YACUTE;", "&YACUTE;", "&YEN;", "&YUML;",
			"&YUML;", "&ZETA;", "&ZETA;", "&ZWJ;", "&ZWNJ;" };

	public static void main(String args[]) {
		// for (int i = 0; i < 128; i++) {
		// System.out.println(i+ ""+ ((char)i));
		// }

		TxtFilter txt = new TxtFilter();
		String string="!@#$%^&amp;*()_+";
		
		string=txt.filter(string);
//		HtmlFilter htmlFilter=new HtmlFilter();
//		string=htmlFilter.filter(string);
//		System.out.println("&amp;apos;".replace("&amp;", "&").replace("&apos;",
//				"-------"));
		System.out.println(string);
		// txt.filter("&apos;");
	}

	public static Trie trie = new TrieMap();

	static {
		for (int i = 0; i < latins.length; i++) {
			trie.addWord(latins[i]);
		}
	}

	public String filter(String str) {
//		long startTime=System.currentTimeMillis();
		byte[] bytes = str.getBytes();
		StringBuilder strb = new StringBuilder();
		int blength = bytes.length - 1;
		for (int i = 0; i < bytes.length; i++) {
			String temp = null;
			byte aciNum = bytes[i];
			// System.out.println(bytes[i]);
			boolean flag = false;
			// if (aciNum >= 0 && aciNum < 128) {
			if (aciNum >> 8 != -1) {
				int tmpI = i;
				if (aciNum == 38 && i < blength) {
					int process = bytes.length - i;
					process = process < 8 ? process : 8;
					boolean isNumber = bytes[i + 1] == 35; // &#XXXX model
					StringBuilder word = new StringBuilder("&");
					if (isNumber)
						word.append('#');
					boolean isXmlAlow = false;
					int begin = isNumber ? i + 2 : i + 1;
					int end = process + i;
					for (int j = begin; j < end; j++) {

						if (59 == bytes[j]) {
							word.append(';');
							isXmlAlow = true;
							i = j;
							break;
						}

						if (!isNumber) {
							if (bytes[j] >= 0x61)
								word.append((char) (bytes[j] - 0x20));
							else
								word.append((char) bytes[j]);
							// } else if (isNumber && 48 <= bytes[j] && bytes[j]
							// < 57) {
						} else if (isNumber && bytes[j] >> 4 == 3) {
							word.append((char) bytes[j]);
						} else {
							isXmlAlow = false;
							break;
						}

					}

					if (isXmlAlow) {
						temp = word.toString();
						if (isNumber
								|| (!isNumber && 1 == trie.searchKey(temp)
										.size())) {
							flag = true;
						} else {
							i = tmpI;
							flag = false;
						}
					}
				}else{
					
				}
				if (true == flag) {
//					 temp = strTemp.toString();
					temp="";
				} else {
					 if (!(aciNum >= 48 && aciNum <= 57)&&!(aciNum >= 65 && aciNum <= 90)&&!(aciNum >= 97 && aciNum <= 122)){
						temp="";
					}else{
					temp = ascii[aciNum];
					}
				}

			} else {
				byte by[] = { bytes[i], bytes[++i] };
				temp = new String(by);
			}
			strb.append(temp);
		}
		// System.out.println(strb.toString());
//		long endTime=System.currentTimeMillis();
//		System.out.println("�ı�����ʱ�䣺   "+(endTime-startTime));
		return strb.toString();
	}

	/**
	 * ����XML����ַ�
	 * 
	 * @param text
	 * @return
	 */
	public String checkCharacterData(String text) {

		int errorChar = 0;
		String str = "";

		if (text == null) {

			return str;

		}

		// do check

		char[] data = text.toCharArray();
		char[] datas = new char[data.length];

		for (int i = 0, len = data.length; i < len; i++) {

			char c = data[i];

			int result = c;

			// high surrogate

			if (result >= 0xD800 && result <= 0xDBFF) {

				// Decode surrogate pair

				int high = c;

				try {

					int low = text.charAt(i + 1);

					if (low < 0xDC00 || low > 0xDFFF) {

						char ch = (char) low;

						// System.err.println(ch);

					}

					// Algorithm defined in Unicode spec

					result = (high - 0xD800) * 0x400 + (low - 0xDC00) + 0x10000;

					i++;

				}

				catch (IndexOutOfBoundsException e) {

					e.printStackTrace();

				}

			}

			if (isXMLCharacter(result)) {

				// Likely this character can't be easily displayed

				// because it's a control so we use its hexadecimal

				// representation in the reason.

				// errorChar++;
				char lchar = (char) result;

				str = str + lchar;
				// datas[i]=result;
			}

		}

		// If we got here, everything is OK

		return str;

	}

	private boolean isXMLCharacter(int c) {

		if (c <= 0xD7FF) {

			if (c >= 0x20)
				return true;

			else {

				if (c == '\n')
					return true;

				if (c == '\r')
					return true;

				if (c == '\t')
					return true;

				return false;

			}

		}

		if (c < 0xE000)
			return false;
		if (c <= 0xFFFD)
			return true;
		if (c < 0x10000)
			return false;
		if (c <= 0x10FFFF)
			return true;
		return false;

	}


}
