package com.ztools.xml;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ztools.stringpro.StringPro;
import com.ztools.stringpro.Trie;
import com.ztools.stringpro.TrieMap;

@Deprecated
public class LatinChar {

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

	private static final String[] latins = { "&aacute;", "&aacute;", "&acirc;",
			"&acirc;", "&acute;", "&aelig;", "&aelig;", "&agrave;", "&agrave;",
			"&alefsym;", "&alpha;", "&alpha;", "&amp;", "&amp;", "&and;",
			"&ang;", "&aring;", "&aring;", "&asymp;", "&atilde;", "&atilde;",
			"&auml;", "&auml;", "&bdquo;", "&beta;", "&beta;", "&brvbar;",
			"&bull;", "&cap;", "&ccedil;", "&ccedil;", "&cedil;", "&cent;",
			"&chi;", "&chi;", "&circ;", "&clubs;", "&cong;", "&copy;",
			"&copy;", "&cup;", "&curren;", "&dagger;", "&dagger;", "&darr;",
			"&darr;", "&deg;", "&delta;", "&diams;", "&divide;", "&divide;",
			"&eacute;", "&eacute;", "&ecirc;", "&ecirc;", "&egrave;",
			"&egrave;", "&empty;", "&emsp;", "&emsp;", "&ensp;", "&ensp;",
			"&epsilon;", "&equiv;", "&eta;", "&eta;", "&eth;", "&eth;",
			"&euml;", "&euml;", "&euro;", "&exist;", "&fnof;", "&forall;",
			"&frac12;", "&frac14;", "&frac34;", "&frasl;", "&gamma;",
			"&gamma;", "&ge;", "&gt;", "&gt;", "&harr;", "&hearts;",
			"&iacute;", "&iacute;", "&icirc;", "&icirc;", "&iexcl;",
			"&igrave;", "&igrave;", "&image;", "&int;", "&iota;", "&iquest;",
			"&isin;", "&iuml;", "&iuml;", "&kappa;", "&lambda;", "&lambda;",
			"&lang;", "&laquo;", "&larr;", "&lceil;", "&ldquo;", "&le;",
			"&lfloor;", "&lowast;", "&loz;", "&lrm;", "&lsaquo;", "&lsquo;",
			"&lt;", "&lt;", "&macr;", "&mdash;", "&micro;", "&middot;",
			"&minus;", "&mu;", "&mu;", "&nbsp;", "&nbsp;", "&ndash;", "&ne;",
			"&ni;", "&not;", "&notin;", "&nsub;", "&ntilde;", "&ntilde;",
			"&nu;", "&nu;", "&oacute;", "&oacute;", "&ocirc;", "&ocirc;",
			"&oelig;", "&oelig;", "&ograve;", "&ograve;", "&oline;",
			"&omicron;", "&oplus;", "&or;", "&ordf;", "&ordm;", "&oslash;",
			"&oslash;", "&otilde;", "&otilde;", "&otimes;", "&ouml;", "&ouml;",
			"&para;", "&part;", "&permil;", "&perp;", "&phi;", "&phi;", "&pi;",
			"&pi;", "&piv;", "&plusmn;", "&pound;", "&prime;", "&prime;",
			"&prod;", "&prop;", "&psi;", "&psi;", "&quot;", "&quot;",
			"&radic;", "&rang;", "&raquo;", "&rarr;", "&rarr;", "&rceil;",
			"&rdquo;", "&real;", "&reg;", "&reg;", "&rfloor;", "&rho;",
			"&rho;", "&rlm;", "&rsaquo;", "&rsquo;", "&sbquo;", "&scaron;",
			"&scaron;", "&sdot;", "&sect;", "&shy;", "&sigma;", "&sigma;",
			"&sigmaf;", "&sim;", "&spades;", "&sub;", "&sube;", "&sup;",
			"&sup1;", "&sup2;", "&sup3;", "&supe;", "&szlig;", "&there4;",
			"&theta;", "&theta;", "&thetasym;", "&thinsp;", "&thorn;",
			"&thorn;", "&tilde;", "&times;", "&times;", "&trade;", "&uacute;",
			"&uacute;", "&uarr;", "&uarr;", "&ucirc;", "&ucirc;", "&ugrave;",
			"&ugrave;", "&uml;", "&upsih;", "&upsilon;", "&upsilon;", "&uuml;",
			"&uuml;", "&xi;", "&yacute;", "&yacute;", "&yen;", "&yuml;",
			"&yuml;", "&zeta;", "&zeta;", "&zwj;", "&zwnj;" };

	public static Trie trie = new TrieMap();

	public static Map<Character, Object> dict;

	static {
		for (int i = 0; i < latins.length; i++) {
			trie.addWord(latins[i]);
		}

		Set<String> set = new HashSet<String>();
		for (String s : latins) {
			set.add(s);
		}
		dict = StringPro.createDictionary(set);
	}

	public static String toXMLString(String str) {
		byte[] bytes = str.getBytes();
		StringBuilder strb = new StringBuilder();
		int blength = bytes.length - 1;
		boolean isUtf8 = false;
		boolean isFinded = false;
		for (int i = 0; i < bytes.length; i++) {
			String temp = null;
			byte aciNum = bytes[i];
			// System.out.println(bytes[i]);
			boolean flag = false;
			// if (aciNum >= 0 && aciNum < 128) {
			if (aciNum >> 8 != -1) {
				int tmpI = i;
				if (aciNum == 38 && i < blength) {

					// if there are characters accord "&XXXXXXXX;" model and
					// these characters behind the "&" then maximum of eight
					// characters.
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
							if (bytes[j] <= 0x5A && bytes[j] >= 0x41)
								word.append((char) (bytes[j] + 0x20));
							else
								word.append((char) bytes[j]);
							// } else if (isNumber && 48 <= bytes[j] && bytes[j]
							// < 57) {
						} else if (isNumber && bytes[j] >> 4 == 3
								&& (bytes[j] & 0xf) < 10) {
							word.append((char) bytes[j]);
						} else {
							isXmlAlow = false;
							break;
						}

					}

					if (isXmlAlow) {
						temp = word.toString();
						if (isNumber
								|| (!isNumber && 1 == StringPro.quickMatchTest(
										temp, dict).size())) {
							flag = true;
						} else {
							i = tmpI;
							flag = false;
						}
					}
				}
				if (true == flag) {
					// temp = strTemp.toString();
					// temp = "";
				} else {
					temp = ascii[aciNum];
				}

			} else {
				
				if (!isFinded) {
						byte by[] = { bytes[i], bytes[i+1]};
						String c = new String(by);
//						System.err.println("ccc: " + i);
					if (str.charAt(i) == c.charAt(0)) {
						isUtf8 = true;
					}
					isFinded = true;
				}

//				if (i < blength) {
//					System.out.println(bytes.length + "    i=" + i
//							+ "    blength=" + blength);
//					byte by[] = { bytes[i], bytes[++i], bytes[++i] };
//					temp = new String(by);
					
//				} else {
//					System.out.println(bytes[blength]
//							+ "   "
//							+ new String(new byte[] { bytes[blength - 2],
//									bytes[blength - 1], bytes[blength] }));
//				}
				
				
				if (isUtf8) {
					byte by[] = { bytes[i], bytes[++i]};
					temp = new String(by);
				} else {
					byte by[] = { bytes[i], bytes[++i], bytes[++i] };
					temp = new String(by);
				}
				
				
			}
			strb.append(temp);
		}
		// System.out.println(strb.toString());
		return strb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // System.out.println((char)(0x7B-0x20));
        System.out.println(LatinChar
                .toXMLString("&ld \nquo;�ر𡱲���Ӧ�����н�ͷ����˾���һ�����衣"));
        // for (int i = 0; i < 128; i++) {
        // System.out.println((char) i);
        // }
        // if (true)return;
        // TODO Auto-generated method stub
        System.out.println(Integer.toBinaryString(0x30));
        System.out.println(Integer.toBinaryString(0x39));
        System.out.println((0x39) >> 4);
        System.out.println(0x31 & 0x5f);
        System.out.println(0x61 & 0x5f);
        System.out
                .println("�����ı����ȣ�"
                        + "Ѷ&quot;�������ŵ&QUOT;��&quot;Ѥ����Ӱ����&quot;��&quot;��ܰ��ͥͿѻ&quot;��&quot;�������Ѱ��&quot;��"
                                .length());
        System.out.println(LatinChar.toXMLString("�û����\"��ͥͿѻ\"Ϊ������\"�˼�����\""));
        long time = System.currentTimeMillis();
        // for (int i = 0; i < 1000; i++) {
        // LatinChar
        // .toXMLString("&& #1234;����ɾ����һ�䣬&nbsp;������Ҫ֪��,����һ��ʼ�����Ĳ�����,������,�������������ǿ��Ҳ����Ȼ������������˼����֣���������߸�Ҫ��������һ����ȷ�Ĺ���,��������Ŀ�������Ҫ�Ĳ�������,������,Ҳ���ǿ�������������Գ�������ɽ�ͻ��ñȽ�û��,������Ϊ��ɽ�Ŀ���Ƚ�ϡ��,������Ŀ���������,������ȴ����,���½���������,��������¶ȹ��ʱ,����Ҳ�ή�Ϳ����ܶ�,ֻҪ�����ܶ�һ��,�Ǻ������ͻ����,���ڽ���İ�����һ���ö�û�С��ǽ���ϵͳ����ΰѿ��������������أ����ȿ���ᾭ�������о,Ȼ�󾭹����ܺ��ɽ����ſ��ƽ���������,֮�����������ڵĽ���������ȼ��������������ȼ�ա��ó��Ƽ�Ů����һ������--����|��ԭ���֧�ӳ�������°��ϴ�����ɫ��|ͨ���ٻ�130�����������߳�չ��Ļ|���������Կ���Ϻ�Ұ�����������������ߵ�һ���ǽ�ԭ���Ŀ�����о���ɸ������ĸ�װƷ,��ʵ���Ǻ���ȷ�Ĺ��ԭ�����ǵ��ͺġ��ɱ����;öȼ�������������,���ѿ�����о������ص���ڹ��˶��Ǹ�����,ͬʱҲ��������ʽ��ơ�ԭ���Ŀ�����о����ԭ�����еļ�����,�����ܻ���Ư���ĵ������ŷ�Ӧ,�����˸�ת��ʱ���Ե���Щ�������ġ���װ������������о�Ļ�,��ʵ�������ĵĵ���Ť��������ı�̫��,��Ϊԭ����������Ȼ�ɱ���һ������������,����ת�ٵı��ֿɾ����ϴ�,������˻���ø���ʱ���ű�ıȽ����,��������Ҳ�ȽϺ�,ͬʱ���˵���������Ҳ����̫��,������ϴ����ظ�ʹ�õ����,�������Ŀ�����о��ʵ��C/Pֵ�൱�ߵĸ�װƷ����������������ɵķ۳�����Ч�����,��ԭ���ıȵĻ�һ���ǲ���һ��,��ֻҪ�Ǵ������Ĳ�Ʒ,��Զ��Ǿ�������Ͽ����Բ��������Ϸ���,����߿��԰���ʹ��,����������ĳ�������,Ҳ�������������š���������·������û�о�����ԵĲ�Ʒ,����߿��ܾ͵�����ร���㹽ͷ���˲�,ϸ�ı���һ��ø�����������о�ĸ��һ���Ʒ���Ǳ��ǳ�Ϊ���㹽ͷ����Բ���Ϳ�����о����ͬ��ԭ����ʽ�ĸ�����������о���õ�һ������,�㹽ͷ��˵��360�ȵ�ȫ��λ����,���������֮�µ�Ȼ����������,��������ľ��������ְ��ֺ޵��������㹽ͷ���ڸ�ת����ı��־��������İ���,����Ť��������ʧҲ���������,���һ�㲢���������ų���װ,ͬʱ���㹽ͷ��������൱�㷺,����û�м����䱣��,�����������ĸ���Ҳ���൱����Ҫ,�����㹽ͷ����������������,������һ�����û�С�������˵�㹽ͷ�Ĺ���Ч��ȸ���������о��,�����Ǹ�������������о�Է۳������Եĺû����ֲ���ڲ��ʵ����,���������״���������֪����ô���������������о�������㹽ͷ��,������Ϊ���ñ���,���½�������������������ƴ�������ÿ���������ռ������,��Щ�����ã��㹽ͷ�����Բ�����ҥ��������������İ��ס�ֻҪ������ô�����Ұ�ʱ����,������������о���㹽ͷ,����ӵ�и�ԭ��������о����޼��Ĺ���Ч�����˿�����о�������ڵ����۹���֮��,���������Ž���ܵ����뵽������ǰ������ܲ���ֻ��һ֧�𽺹���ô��,ͬ��縺����ѹ����Ĺ�Ч�������ֳ���໻�����Ͻ����,��Ҫԭ������������ѹʱǿ������������𽺹���Ϊ����,�����д�����ֲ������ѹֵ,���������֧�𽺽��������,��˲�ʹ�����Ͻ���ʵĽ���ܡ������Ͻ�����Ҳ���������ȵ�ȱ��,��Щ���ǽ��и�װʱ��Ҫע��ĵط�����������ǿ�����Ƴɵĸ�ѹ��,ͬʱ���𽺲������ȼ��߿�ѹ�Ե�˫���ŵ�,���ۼ۽ϸ߼����ó��ͽ������������в���ĵط�������������ע��,���͵��Ҫ�������ϵͳ�ĸ�װ���˿�˵�������,���������ǽ�����ܡ������š���������ŵ�����ǿ��,�ⲿ�־�ǣ�浽��Ϊרҵ��ϸ����У�趨,ͬʱ���ͼ����Ҳ����һ������,���С���ڴ˾Ͳ����������������߱���Ҫ֪��,��װ�������������Ļ����㹽ͷ,��Ŀ������ߵ�λʱ���ڵĿ�������,����ý�ԭ����λʱ���ڸ��Ľ�����,������߿������������������������ٵĻ����ڽ����ż����������,�������ط����ǽ���ϵͳǿ������Ҫ�ؼ�����ϵͳ�빩�͵Ĵ����൱��Ҫ,��������Ķ൫���������޷�Ư��ȼ�ա���೵�Ѷ���������������о,�������㹽ͷ,�����ڹ���ϵͳ������ȴ���������͵�ѹ����û�С�����ֻ�����ȼ��Ч�ʱ��,�������ܲ�����������,�����ͺ�Ҳ����ֵı�ԭ����Ҫ������͡��������,�������װϢϢ���,����������һ����ǿ����������Ŀȴ��ά��ԭ��,����Ҳɾ����һ�㡣abc&nbsp;&");
        // }
        time = System.currentTimeMillis() - time;
        System.out.println("��ʱ��" + time);
        // System.out.println(LatinChar.toXMLString("&& #1234;����ɾ����һ�䣬&nbsp;������Ҫ֪��,����һ��ʼ�����Ĳ�����,������,�������������ǿ��Ҳ����Ȼ������������˼����֣���������߸�Ҫ��������һ����ȷ�Ĺ���,��������Ŀ�������Ҫ�Ĳ�������,������,Ҳ���ǿ�������������Գ�������ɽ�ͻ��ñȽ�û��,������Ϊ��ɽ�Ŀ���Ƚ�ϡ��,������Ŀ���������,������ȴ����,���½���������,��������¶ȹ��ʱ,����Ҳ�ή�Ϳ����ܶ�,ֻҪ�����ܶ�һ��,�Ǻ������ͻ����,���ڽ���İ�����һ���ö�û�С��ǽ���ϵͳ����ΰѿ��������������أ����ȿ���ᾭ�������о,Ȼ�󾭹����ܺ��ɽ����ſ��ƽ���������,֮�����������ڵĽ���������ȼ��������������ȼ�ա��ó��Ƽ�Ů����һ������--����|��ԭ���֧�ӳ�������°��ϴ�����ɫ��|ͨ���ٻ�130�����������߳�չ��Ļ|���������Կ���Ϻ�Ұ�����������������ߵ�һ���ǽ�ԭ���Ŀ�����о���ɸ������ĸ�װƷ,��ʵ���Ǻ���ȷ�Ĺ��ԭ�����ǵ��ͺġ��ɱ����;öȼ�������������,���ѿ�����о������ص���ڹ��˶��Ǹ�����,ͬʱҲ��������ʽ��ơ�ԭ���Ŀ�����о����ԭ�����еļ�����,�����ܻ���Ư���ĵ������ŷ�Ӧ,�����˸�ת��ʱ���Ե���Щ�������ġ���װ������������о�Ļ�,��ʵ�������ĵĵ���Ť��������ı�̫��,��Ϊԭ����������Ȼ�ɱ���һ������������,����ת�ٵı��ֿɾ����ϴ�,������˻���ø���ʱ���ű�ıȽ����,��������Ҳ�ȽϺ�,ͬʱ���˵���������Ҳ����̫��,������ϴ����ظ�ʹ�õ����,�������Ŀ�����о��ʵ��C/Pֵ�൱�ߵĸ�װƷ����������������ɵķ۳�����Ч�����,��ԭ���ıȵĻ�һ���ǲ���һ��,��ֻҪ�Ǵ������Ĳ�Ʒ,��Զ��Ǿ�������Ͽ����Բ��������Ϸ���,����߿��԰���ʹ��,����������ĳ�������,Ҳ�������������š���������·������û�о�����ԵĲ�Ʒ,����߿��ܾ͵�����ร���㹽ͷ���˲�,ϸ�ı���һ��ø�����������о�ĸ��һ���Ʒ���Ǳ��ǳ�Ϊ���㹽ͷ����Բ���Ϳ�����о����ͬ��ԭ����ʽ�ĸ�����������о���õ�һ������,�㹽ͷ��˵��360�ȵ�ȫ��λ����,���������֮�µ�Ȼ����������,��������ľ��������ְ��ֺ޵��������㹽ͷ���ڸ�ת����ı��־��������İ���,����Ť��������ʧҲ���������,���һ�㲢���������ų���װ,ͬʱ���㹽ͷ��������൱�㷺,����û�м����䱣��,�����������ĸ���Ҳ���൱����Ҫ,�����㹽ͷ����������������,������һ�����û�С�������˵�㹽ͷ�Ĺ���Ч��ȸ���������о��,�����Ǹ�������������о�Է۳������Եĺû����ֲ���ڲ��ʵ����,���������״���������֪����ô���������������о�������㹽ͷ��,������Ϊ���ñ���,���½�������������������ƴ�������ÿ���������ռ������,��Щ�����ã��㹽ͷ�����Բ�����ҥ��������������İ��ס�ֻҪ������ô�����Ұ�ʱ����,������������о���㹽ͷ,����ӵ�и�ԭ��������о����޼��Ĺ���Ч�����˿�����о�������ڵ����۹���֮��,���������Ž���ܵ����뵽������ǰ������ܲ���ֻ��һ֧�𽺹���ô��,ͬ��縺����ѹ����Ĺ�Ч�������ֳ���໻�����Ͻ����,��Ҫԭ������������ѹʱǿ������������𽺹���Ϊ����,�����д�����ֲ������ѹֵ,���������֧�𽺽��������,��˲�ʹ�����Ͻ���ʵĽ���ܡ������Ͻ�����Ҳ���������ȵ�ȱ��,��Щ���ǽ��и�װʱ��Ҫע��ĵط�����������ǿ�����Ƴɵĸ�ѹ��,ͬʱ���𽺲������ȼ��߿�ѹ�Ե�˫���ŵ�,���ۼ۽ϸ߼����ó��ͽ������������в���ĵط�������������ע��,���͵��Ҫ�������ϵͳ�ĸ�װ���˿�˵�������,���������ǽ�����ܡ������š���������ŵ�����ǿ��,�ⲿ�־�ǣ�浽��Ϊרҵ��ϸ����У�趨,ͬʱ���ͼ����Ҳ����һ������,���С���ڴ˾Ͳ����������������߱���Ҫ֪��,��װ�������������Ļ����㹽ͷ,��Ŀ������ߵ�λʱ���ڵĿ�������,����ý�ԭ����λʱ���ڸ��Ľ�����,������߿������������������������ٵĻ����ڽ����ż����������,�������ط����ǽ���ϵͳǿ������Ҫ�ؼ�����ϵͳ�빩�͵Ĵ����൱��Ҫ,��������Ķ൫���������޷�Ư��ȼ�ա���೵�Ѷ���������������о,�������㹽ͷ,�����ڹ���ϵͳ������ȴ���������͵�ѹ����û�С�����ֻ�����ȼ��Ч�ʱ��,�������ܲ�����������,�����ͺ�Ҳ����ֵı�ԭ����Ҫ������͡��������,�������װϢϢ���,����������һ����ǿ����������Ŀȴ��ά��ԭ��,����Ҳɾ����һ�㡣abc&nbsp;&"));
    }

}
