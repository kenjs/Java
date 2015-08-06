package com.cy.dctms.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * �ַ�����ת��������
 * 
 * @author hayden 2014-01-02
 */
public class ConvertCharactersUtil {

	// ISO-8859-1����
	private static String ISO_8859_1 = "ISO-8859-1";
	// GBK����
	private static String GBK = "GBK";
	// UTF-8����
	private static String UTF8 = "UTF-8";
	// java Cp850����(��ӦSybase cp850)
	private static String CP850 = "Cp850";

	/**
	 * ��GBK��ʽ����ת��ISO8859-1������
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
	 * GBK����ת��ΪISO-8859-1
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
	 * GBK����ת��ΪISO-8859-1
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
	 * GBK����ת��ΪISO-8859-1
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
	 * ��ISO8859-1������ַ���ת��ΪGBK��ʽ��
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
	 * ��ISO8859-1������ַ���ת��ΪGBK��ʽ ����Trim���ַ�����ͷ�Ŀո��ʺ����ݿ���ΪCHAR()��ʽ������
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
	 * ISO-8859-1����ת��ΪGBK
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
	 * ISO-8859-1����ת��ΪGBK
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
	 * ISO-8859-1����ת��ΪGBK
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
	 * ISO-8859-1����ת��ΪGBK
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
	 * ��UTF_8��ʽ����ת��ISO8859-1������
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
		// ���Ϊ����ֱ�ӷ��� �����׳��쳣��ֱ�ӷ���
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
	 * ��UTF_8��ʽ���ݽ����GBK������
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
	 * UTF-8����ת��ΪGBK
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
	 * ISO-8859-1����ת��ΪGBK
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
	 * ISO-8859-1����ת��ΪCp850����
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
	 * Cp850����ת��ΪGBK����
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
	 * ����ת��
	 * 
	 * @param source
	 *            �ַ���Դ�ַ�����,����Ϊnull���
	 * @param to
	 *            �ַ���ת��Ŀ�����,����Ϊnull���
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
	 * URL�ַ� ����� utf-8����
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
