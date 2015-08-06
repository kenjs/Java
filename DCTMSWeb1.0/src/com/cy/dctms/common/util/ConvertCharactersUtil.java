package com.cy.dctms.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 字符编码转换工具类
 * 
 * @author hayden 2014-01-02
 */
public class ConvertCharactersUtil {

	// ISO-8859-1编码
	private static String ISO_8859_1 = "ISO-8859-1";
	// GBK编码
	private static String GBK = "GBK";
	// UTF-8编码
	private static String UTF8 = "UTF-8";
	// java Cp850编码(对应Sybase cp850)
	private static String CP850 = "Cp850";

	/**
	 * 将GBK格式数据转成ISO8859-1的数据
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String convertGBK2ISO(String str)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return new String(str.getBytes(GBK), ISO_8859_1);

	}

	/**
	 * GBK编码转换为ISO-8859-1
	 * 
	 * @param baseEntity
	 */
	public static void convertGBK2ISO(Object baseEntity) throws Exception {
		if (baseEntity == null) {
			return;
		}
		convertEncode(GBK, ISO_8859_1, baseEntity);
	}

	/**
	 * GBK编码转换为ISO-8859-1
	 * 
	 * @param baseObjects
	 */
	public static void convertGBK2ISO(List baseList) throws Exception {
		if (baseList == null) {
			return;
		}
		for (Iterator iter = baseList.iterator(); iter.hasNext();) {
			convertEncode(GBK, ISO_8859_1, iter.next());
		}
	}

	/**
	 * GBK编码转换为ISO-8859-1
	 * 
	 * @param baseObjects
	 */
	public static void convertGBK2ISO(Map result) throws Exception {
		if (result == null) {
			return;
		}
		for (Iterator iter = result.keySet().iterator(); iter.hasNext();) {
			convertEncode(GBK, ISO_8859_1, result.get(iter.next()));
		}
	}

	/**
	 * 将ISO8859-1编码的字符串转换为GBK格式的
	 * 
	 * @throws Exception
	 */
	public static String convertISO2GBK(String str)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return new String(str.getBytes(ISO_8859_1), GBK);
	}

	/**
	 * 将ISO8859-1编码的字符串转换为GBK格式 并且Trim掉字符串两头的空格，适合数据库中为CHAR()格式的数据
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String convertISO2GBKWithTrim(String str)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return new String(str.trim().getBytes(ISO_8859_1), GBK);
	}

	/**
	 * ISO-8859-1编码转换为GBK
	 * 
	 * @param baseEntity
	 */
	public static void convertISO2GBK(Object baseEntity) throws Exception {
		if (baseEntity == null) {
			return;
		}
		convertEncode(ISO_8859_1, GBK, baseEntity);
	}

	/**
	 * ISO-8859-1编码转换为GBK
	 * 
	 * @param baseObjects
	 */
	public static void convertISO2GBK(Object[] baseObjects) throws Exception {
		if (baseObjects == null) {
			return;
		}
		for (int i = 0; i < baseObjects.length; i++) {
			convertEncode(ISO_8859_1, GBK, baseObjects[i]);
		}
	}

	/**
	 * ISO-8859-1编码转换为GBK
	 * 
	 * @param baseObjects
	 */
	public static void convertISO2GBK(List list) throws Exception {
		if (list == null) {
			return;
		}
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			convertEncode(ISO_8859_1, GBK, iter.next());
		}
	}

	/**
	 * ISO-8859-1编码转换为GBK
	 * 
	 * @param baseObjects
	 */
	// @SuppressWarnings("rawtypes")
	public static void convertISO2GBK(Map result) throws Exception {
		if (result == null) {
			return;
		}
		for (Iterator iter = result.keySet().iterator(); iter.hasNext();) {
			convertEncode(ISO_8859_1, GBK, result.get(iter.next()));
		}
	}

	/**
	 * 将UTF_8格式数据转成ISO8859-1的数据
	 * 
	 * @throws Exception
	 */
	public static String convertUTF82ISO(String str)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return convertGBK2ISO(URLDecoder.decode(str, UTF8));
	}

	/**
	 * UTF8 - ISO
	 * 
	 * @param baseObjects
	 */
	public static void convertUTF82ISO(Map result)
			throws UnsupportedEncodingException {
		// 如果为空则直接返回 不再抛出异常，直接返回
		if (result == null) {
			return;
		}
		for (Iterator iter = result.keySet().iterator(); iter.hasNext();) {
			Object key = iter.next();
			Object obj = result.get(key);
			result.put(key, convertUTF82ISO((String) obj));
		}
	}

	/**
	 * 将UTF_8格式数据解码成GBK的数据
	 * 
	 * @throws Exception
	 */
	public static String convertUTF82GBK(String str)
			throws UnsupportedEncodingException {
		if (str == null) {
			return null;
		}
		return URLDecoder.decode(str, UTF8);
	}

	/**
	 * UTF-8编码转换为GBK
	 * 
	 * @param baseEntity
	 */
	public static void convertUTF82GBK(Object baseEntity) throws Exception {
		if (baseEntity == null) {
			return;
		}
		decodeURL(baseEntity);
	}

	/**
	 * ISO-8859-1编码转换为GBK
	 * 
	 * @param baseObjects
	 */
	public static void convertUTF82GBK(Map result)
			throws UnsupportedEncodingException {
		if (result == null) {
			return;
		}
		for (Iterator iter = result.keySet().iterator(); iter.hasNext();) {
			Object key = iter.next();
			Object obj = result.get(key);
			result.put(key, convertUTF82GBK((String) obj));
		}
	}

	/**
	 * ISO-8859-1编码转换为Cp850编码
	 * 
	 * @param baseEntity
	 */
	public static void convertISO2Cp850(Object baseEntity) throws Exception {
		if (baseEntity == null) {
			return;
		}
		convertEncode(ISO_8859_1, CP850, baseEntity);
	}

	/**
	 * Cp850编码转换为GBK编码
	 * 
	 * @param baseEntity
	 */
	public static void convertCp8502GBK(Object baseEntity) throws Exception {
		if (baseEntity == null) {
			return;
		}
		convertEncode(CP850, GBK, baseEntity);
	}

	/**
	 * 编码转换
	 * 
	 * @param source
	 *            字符串源字符编码,可以为null或空
	 * @param to
	 *            字符串转换目标编码,可以为null或空
	 * @throws Exception
	 */
	protected static void convertEncode(String source, String to, Object object)
			throws Exception {
		if (null == object) {
			return;
		}
		Class<? extends Object> clazz = object.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		Method setMethod = null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getReturnType().equals(String.class)
					&& methods[i].getName().substring(0, 3).equals("get")) {
				String value = (String) methods[i].invoke(object);
				setMethod = clazz.getDeclaredMethod("set"
						+ methods[i].getName().substring(3),
						new Class[] { String.class });
				if (value != null) {
					setMethod.invoke(object,
							new Object[] { new String(value.getBytes(source),
									to) });
				}
			}
		}
	}

	/**
	 * URL字符 解码成 utf-8编码
	 */
	public static void decodeURL(Object object) throws Exception {
		if (null == object) {
			return;
		}
		Class<? extends Object> clazz = object.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		Method setMethod = null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getReturnType().equals(String.class)
					&& methods[i].getName().substring(0, 3).equals("get")) {
				String value = (String) methods[i].invoke(object);
				setMethod = clazz.getDeclaredMethod("set"
						+ methods[i].getName().substring(3),
						new Class[] { String.class });
				if (value != null) {
					setMethod.invoke(object,
							new Object[] { URLDecoder.decode(value, UTF8) });
				}
			}
		}
	}

}
