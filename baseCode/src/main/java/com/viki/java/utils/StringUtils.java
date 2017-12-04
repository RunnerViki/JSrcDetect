package com.viki.java.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public class StringUtils {

	private StringUtils() {}

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     *
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	 public static String filterEmoji(String str,String raplaceStr) {  
		 if(StringUtils.isEmpty(str))
		 {
			 return str;
		 }
		 Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u2775]|[\u2794-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
         Matcher emojiMatcher = emoji.matcher(str);
         if (emojiMatcher.find()) 
         {
             str = emojiMatcher.replaceAll(raplaceStr);
             return str ; 
         }
         return str;
     }

	 public static String filterEmoji(String str)
	 {
		 return filterEmoji(str,"");
	 }
	 public static String filterSymbols(String str,String raplaceStr)
	 {
		 if(StringUtils.isEmpty(str))
		 {
			 return str;
		 }
		 String result=str.replace(" ", raplaceStr);
		 result=result.replaceAll("[\\pP\\p{Punct}]","").replaceAll("[//pP]","").replaceAll("[//p{P}]","");
		 return result;
	 }
	 public static String filterSymbols(String str)
	 {
		 return filterSymbols(str,"");
	 }

}
