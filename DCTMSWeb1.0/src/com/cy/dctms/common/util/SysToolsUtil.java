package com.cy.dctms.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;


/**
 * ���ù����ࡣ
 * 
 * @since 2009-6-8
 * @author 
 * @version 1.00 2009-6-8
 */
@SuppressWarnings("unchecked")
public class SysToolsUtil {

	/**
	 * ��������쳣��װ���ַ���, �����׳�, ��: try { ... } catch (SomeException se) { String s = convertException(se); throw new MyException(s); }
	 * 
	 * @param e
	 * @return
	 */
	public static String convertException(Exception e) {
		return "caught exception " + e.getClass().getName() + ", " + e.getMessage();
	}

	/**
	 * ���ַ�������list�е��ַ����Էָ���separator���ӳ�һ���ַ���,��ÿ���ַ������Ե����� �罫����str[]={str1,str2,str3}��/Ϊ�ָ���ת��Ϊ"'str1'/'str2'/'str3'" �罫����str[]={str1,str2,str3}��,Ϊ�ָ���ת��Ϊ"'str1','str2','str3'"
	 * 
	 * @param list
	 *            ��ϲ����ַ�������
	 * @param separator
	 *            ���ںϲ��ķָ���
	 * @return
	 */
	public static String combine(String[] list, String separator) {
		if (list == null)
			return null;
		StringBuffer result = new StringBuffer();
		int i = 0;
		int listSize = list.length;
		for (i = 0; i < listSize - 1; i++) {
			result.append("'" + (String) list[i] + "'");
			result.append(separator);
		}
		result.append("'" + (String) list[i] + "'");
		return result.toString();
	}

	/**
	 * �ַ���ת��
	 * 
	 * @param a_str
	 *            �ַ���ֵ
	 * @param a_orig:
	 *            �� "ISO-8859-1"
	 * @param a_dest:
	 *            �� "GB2312"
	 * @return ת����ֵ
	 * @throws UnsupportedEncodingException
	 */
	public static String convertEncode(String a_str, String a_orig, String a_dest) throws UnsupportedEncodingException {
		byte[] temp = a_str.getBytes(a_orig);
		return new String(temp, a_dest);
	}

	/**
	 * �����ַ�����"Y"����"N", ֻ���ַ�����"Y", "1"(�����пո�), �ŷ���"Y", ����Ϊ"N"
	 * 
	 * @param a_value
	 *            ��Ҫ�б���ַ���
	 * @return
	 */
	public static String getYesNo(String a_value) {
		if (a_value != null && (a_value.trim().equals("Y") || a_value.trim().equals("1"))) {
			return "Y";
		} else {
			return "N";
		}
	}


	/**
	 * ��ȡ���ݿ⵱ǰʱ�䣬��ʧ���򷵻ؿա�
	 * <p>
	 * ����ͨ�������ݿ��У�Oracle��Microsoft SQL Server��Sybase��
	 * 
	 * @param conn
	 *            ���ݿ����ӡ�
	 * @return ���ݿ⵱ǰʱ�䡣
	 * @author hst
	 */
	public static java.util.Date getDate(Connection conn) {

		String sql = null;
		String sProductName = null;

		Statement stmt = null;
		ResultSet rs = null;

		try {
			sProductName = conn.getMetaData().getDatabaseProductName().toLowerCase();

			if (sProductName.indexOf("oracle") != -1) {
				// Oracle (Oracle)
				sql = "select systimestamp from dual";
			} else if (sProductName.indexOf("sql server") != -1) {
				// SQL Server (Microsoft SQL Server)
				sql = "select getdate()";
			} else if (sProductName.indexOf("adaptive server") != -1) {
				// Sybase (Adaptive Server Enterprise)
				sql = "select getdate()";

				// ��������ݿ�û�������ԡ�
			} else if (sProductName.indexOf("db2") != -1) {
				// DB2
				sql = "SELECT CURRENT TIMESTAMP FROM SYSIBM.SYSDUMMY1";
			} else if (sProductName.indexOf("informix") != -1) {
				// INFORMIX
				sql = "select today";
			} else if (sProductName.indexOf("mysql") != -1) {
				// MYSQL
				sql = "select now()";
			} else {
				// ����
				sql = "select getdate()";
			}

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getTimestamp(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return null;
	}

	/**
	 * ��ʽ��С�����������롢ָ�����ȣ���
	 * <p>
	 * �������и��ܶ��ĵ����⣬���ǵ��漰����ת��ʱ�����ʧ�档
	 * <p>
	 * ���磺<code>double d = 3.005;</code>
	 * <p>
	 * ����<code>new BigDecimal(d)</code>�õ��Ľ��ȴ�ǣ�
	 * <p>
	 * 3.00499999999999989341858963598497211933135986328125
	 * <p>
	 * ����ʱ�����������룬������0.005��û���ˡ� ����Ϊ<code>DecimalFormat</code>���ã����Ĵ�������Ǻ�<code>BigDecimal</code>һ���ġ�
	 * <p>
	 * �Ϻõİ취���ǽ�doubleת���ַ������ַ���������׼ȷ��������ʧ������⡣
	 * <p>
	 * 
	 * @param value
	 *            ������ֵ��
	 * @param dec
	 *            Ҫ�󾫶ȡ�
	 * @return ������������ֵ��
	 * @author hst
	 */
	public static double formatDecimal(double value, int dec) {
		java.math.BigDecimal bd = new java.math.BigDecimal(String.valueOf(value));
		bd = bd.setScale(dec, java.math.BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * ��doubleת��BigDecimal��
	 * 
	 * @param d
	 *            ������ֵ��
	 * @param scale
	 *            ������С��λ����
	 * @return BigDecimal��
	 * @author hst
	 */
	public static BigDecimal decValue(double d, int scale) {
		BigDecimal rs = new BigDecimal(String.valueOf(d));
		return rs.setScale(scale, java.math.BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * ��double����ת����BigDecimal��������λС����
	 * 
	 * @param d
	 *            ������ֵ��
	 * @return BigDecimal��
	 */
	public static BigDecimal double2BigDecimal(double d) {
		return decValue(d, 2);
	}

	/**
	 * html����ת����
	 * 
	 * @param str
	 *            ���ı���
	 * @return ��html��ʽ�ı���
	 * @author hst
	 */
	public static String htmlEncoder(String str) {
		if (str == null)
			return ("");
		if (str.equals(""))
			return ("");

		// ����һ��StringBuffer��������������

		StringBuffer buf = new StringBuffer();
		char ch1 = '\n';
		char ch2 = '\n';

		for (int i = 0; i < str.length(); i++) {
			ch1 = str.charAt(i);

			if ((ch1 == ' ') && ((i + 1) < str.length())) { // �������ո�ת��Ϊһ��ȫ�����Ŀո�
				ch2 = str.charAt(i + 1);
				if (ch2 == ' ') {
					buf.append("��");
					i++;
				} else {
					buf.append(ch1);
				}
			} else if (ch1 == '\n') {
				buf.append("<br>");
			} else if (ch1 == '\t') {
				buf.append("����");
			}
			/*-------------------modify by hst 2004-12-10 begin---------------*/
			/*
			 * else if (ch1 == '<') { buf.append("&lt;"); } else if (ch1 == '>') { buf.append("&gt;"); } else if (ch1 == '&') { buf.append("&amp;"); }
			 */
			/*-------------------modify by hst 2004-12-10 end----------------*/

			else {
				buf.append(ch1);
			}
		}

		return buf.toString();

	}

	/**
	 * �ر����ݿ����ӡ�
	 * 
	 * @param conn
	 *            ���ݿ����ӡ�
	 * @author hst
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر�Statement��
	 * 
	 * @param stmt
	 *            Statement��
	 * @author hst
	 */
	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����쳣�Ķ�ջ��Ϣ��
	 * 
	 * @param exception
	 *            �쳣��
	 * @return ��ջ��Ϣ��
	 * @author hst
	 */
	public static String getStackTrace(Throwable exception) {

		StackTraceElement[] ste = exception.getStackTrace();
		StringBuffer sbResult = new StringBuffer();

		for (int i = 0; i < ste.length; i++) {
			if (i > 0)
				sbResult.append("\n    at ");// ���뻻�к�������
			sbResult.append(ste[i].toString());
		}

		return sbResult.toString();
	}

	// ------------ ����Req�ķ��� --------------
	/**
	 * ���request��ĳ��������ֵ���������������򷵻�Ĭ��ֵ��
	 * 
	 * @param request
	 * @param name
	 *            ������
	 * @param defaultVal
	 *            Ĭ��ֵ
	 * @return ������ֵ
	 * @author ���ʹ�
	 * @author hst
	 * @version 1.01 2006-01-18
	 */
	public static String getParameter(HttpServletRequest request, String name, String defaultVal) {
		String value = request.getParameter(name);

		if (value == null || value.trim().length() < 1) {
			value = defaultVal;
		}

		return value;
	}

	/**
	 * ���request��ĳ��������ֵ���������������򷵻ؿմ���
	 * 
	 * @param request
	 * @param name
	 *            ������
	 * @return String ������ֵ
	 * @author hst
	 * @version 1.02 2006-01-18
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);

		if (value == null || value.trim().length() < 1) {
			return "";
		}
		return value;
	}

	/**
	 * ���request��ĳ�����������顣
	 * 
	 * @param request
	 * @param name
	 *            ������
	 * @return String[] ����������
	 * @author hst
	 * @version 1.02 2006-01-18
	 */
	public static String[] getParameterValues(HttpServletRequest request, String name) {
		String[] value = request.getParameterValues(name);
		return value;
	}

	/**
	 * �ж�һ���ַ����Ƿ��ֵ��մ���
	 * <p>
	 * 
	 * @param str
	 *            Ҫ�жϵ��ַ�����
	 * @return ���ַ���Ϊ�ջ�ֻ�пո�Ŀմ��򷵻�true�����򷵻�false��
	 * @author hst
	 * @version 1.0 2006.08.28
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().length() < 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ����ַ���strΪ����ת��Ϊstr1
	 * 
	 * @param str
	 * @param str1
	 * @author hr
	 * @return
	 */
	public static String getNullStr(String str, String str1) {
		if (isNullOrEmpty(str)) {
			return str1;
		} else {
			return str;
		}
	}

	/**
	 * ��õ����Ű������Զ��ŷָ���ַ�������Ҫ��������SQL���ġ�IN������
	 * 
	 * @param collection
	 *            String
	 * @return String
	 * @author hr
	 */
	public static String getCollectionStr(String collection) {
		String coll[] = collection.split(",");
		collection = "";
		for (int i = 0; i < coll.length; i++) {
			if (i == 0) {
				collection = collection + "'" + coll[i] + "'";
			} else {
				collection = collection + ",'" + coll[i] + "'";
			}
		}
		return collection;
	}

	/**
	 * ���ַ���strת��ΪGBK�����ʽ
	 * 
	 * @param str
	 * @return
	 * @author hr
	 */
	public static String getGBKStr(String str) {
		try {
			String temp_p;
			temp_p = str;
			temp_p = getNullStr(temp_p, "");
			byte[] temp_t = temp_p.getBytes("ISO8859_1");
			String temp = new String(temp_t, "GBK");
			return temp;
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * ת����ISO�����ʽ
	 * 
	 * @param str
	 * @return
	 * @author hr
	 */
	public static String getISOStr(String str) {
		try {
			String temp_p;
			temp_p = str;
			temp_p = getNullStr(temp_p, "");
			byte[] temp_t = temp_p.getBytes("ISO8859_1");
			String temp = new String(temp_t);
			return temp;
		} catch (Exception e) {
		}
		return "null";
	}

	/**
	 * ��ISO8859-1������ַ���ת��ΪGBK��ʽ��
	 * 
	 * @param str
	 * @return
	 * @author hr
	 */
	public static String ISO2GBK(String str) {
		if (str == null)
			return null;
		else {
			try {
				return new String(str.getBytes("iso8859-1"), "GBK");
			} catch (Exception ex) {
				return "����ת������!";
			}
		}
	}

	/**
	 * ��ISO8859-1������ַ���ת��ΪGBK��ʽ,����Trim���ַ�����ͷ�Ŀո��ʺ����ݿ���ΪCHAR()��ʽ������
	 * 
	 * @param str
	 * @return
	 * @author hr
	 */
	public static String ISO2GBKWithTrim(String str) {
		if (str == null)
			return null;
		else {
			try {
				return new String(str.trim().getBytes("iso8859-1"), "GBK");
			} catch (Exception ex) {
				return "����ת������!";
			}
		}
	}

	/**
	 * ��GBK��ʽ����ת��ISO8859-1������
	 * 
	 * @param str
	 * @return
	 * @author hr
	 */
	public static String GBK2ISO(String str) {
		if (str == null) {
			return null;
		} else {
			try {
				return new String(str.getBytes("GBK"), "iso8859-1");
			} catch (Exception ex) {
				return "����ת������!";
			}
		}
	}

	/**
	 * ȡ����չ��
	 * 
	 * @param String
	 *            s
	 * @return String
	 * @author ���ʹ�
	 */
	public static String getSuffixName(String s) {
		if (s.length() == 0)
			return null;
		int i = s.lastIndexOf(".");
		if (i < 0 || i >= s.length() - 1)
			return null;
		else
			return s.substring(i + 1);
	}

	/**
	 * ȡ���ļ�����(����·��).
	 * 
	 * @param String
	 *            fileName
	 * @return String
	 * @author ���ʹ�
	 */
	public static String getFileName(String fileName) {
		if (fileName.length() == 0)
			return null;
		int i = fileName.lastIndexOf("\\");
		if (i < 0 || i >= fileName.length() - 1) {
			i = fileName.lastIndexOf("/");
			if (i < 0 || i >= fileName.length() - 1)
				return fileName;
		}
		return fileName.substring(i + 1);
	}

	/**
	 * ȡ��Date��format��ʽ���ַ���
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @since 2007-12-13
	 * @author linjb
	 * @version 1.00 2007-12-13
	 */
	public static String date2string(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String s = null;
		if (date != null)
			s = df.format(date);
		else
			s = "";
		return s;
	}

	/**
	 * ��format��ʽ���ַ���ת����Date
	 * 
	 * @param s
	 * @param format
	 * @return
	 * @since 2007-12-13
	 * @author linjb
	 * @version 1.00 2007-12-13
	 */
	public static Date string2date(String s, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (Exception e) {
			System.out.println("�ַ���ת���ڴ���");
			return null;
		}
		return d;
	}

	/**
	 * ����ת�ַ���yyyy-MM-dd
	 * 
	 * @author ljb
	 */
	public static String date2string(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = null;
		if (date != null)
			s = df.format(date);
		else
			s = "";
		return s;
	}

	/**
	 * �ַ���yyyy-MM-ddת����
	 * 
	 * @author ljb
	 */
	public static Date string2date(String s) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (Exception e) {
			System.out.println("�ַ���ת���ڴ���");
			return null;
		}
		return d;
	}

	/**
	 * ����ת�ַ���yyyy-MM-dd HH:mm:ss
	 * 
	 * @author ljb
	 */
	public static String date2stringDetail(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = null;
		if (date != null)
			s = df.format(date);
		else
			s = "";
		return s;
	}

	/**
	 * �ַ���yyyy-MM-dd HH:mm:ssת����
	 * 
	 * @author ljb
	 */
	public static Date string2dateDetail(String s) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (Exception e) {
			System.out.println("�ַ���ת���ڴ���");
			return null;
		}
		return d;
	}

	/**
	 * ��ָ�������ڼ���amount������field
	 * 
	 * @param preDate
	 * @param field
	 * @param amount
	 * @return
	 * @since 2007-1-5
	 * @author ljb
	 * @version 1.00 2007-1-5
	 */
	public static String addDate(String preDate, int field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(string2date(preDate));
		if (field == Calendar.MONTH) {
			c.add(Calendar.DATE, 1);
			c.add(Calendar.MONTH, amount);
			c.add(Calendar.DATE, -1);
		} else {
			c.add(field, amount);
		}
		return date2string(c.getTime());
	}

	/**
	 * ���طǿն���
	 * 
	 * @param str
	 *            �ַ�����
	 * @return ������Ϊ�գ��򷵻ؿմ���
	 * @author hst
	 */
	public static String notNull(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param bool
	 *            �������͡�
	 * @return ������Ϊ�գ��򷵻�false��
	 * @author hst
	 */
	public static Boolean notNull(Boolean bool) {
		if (bool == null)
			return Boolean.FALSE;
		else
			return bool;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param dbl��
	 * @return ������Ϊ�գ��򷵻�0��
	 * @author hst
	 */
	public static Double notNull(Double dbl) {
		if (dbl == null)
			return new Double(0);
		else
			return dbl;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param flt��
	 * @return ������Ϊ�գ��򷵻�0��
	 * @author hst
	 */
	public static Float notNull(Float flt) {
		if (flt == null)
			return new Float(0);
		else
			return flt;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param integer��
	 * @return ������Ϊ�գ��򷵻�0��
	 * @author hst
	 */
	public static Integer notNull(Integer integer) {
		if (integer == null)
			return new Integer(0);
		else
			return integer;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param lng��
	 * @return ������Ϊ�գ��򷵻�0��
	 * @author hst
	 */
	public static Long notNull(Long lng) {
		if (lng == null)
			return new Long(0);
		else
			return lng;
	}

	/**
	 * ���طǿն���
	 * 
	 * @param sht��
	 * @return ������Ϊ�գ��򷵻�0��
	 * @author hst
	 */
	public static Short notNull(Short sht) {
		if (sht == null)
			return new Short("0");
		else
			return sht;
	}







	/**
	 * ���˶������飬�����������Ľ�����
	 * <p>
	 * 
	 * @param objects1
	 *            ��������1��
	 * @param objects2
	 *            ��������2��
	 * @return ��������Ľ�����
	 * @since 2006-08-22
	 * @author hst
	 * @version 1.01 2007-01-18
	 */
	public static Object[] filterObjects(Object[] objects1, Object[] objects2) {
		if (objects1 == null || objects2 == null) {
			throw new NullPointerException();
		}
		// Ϊ���޸���ԭ���Ķ����½�һ�����鴦��
		Object[] newObjects = new Object[objects1.length];
		System.arraycopy(objects1, 0, newObjects, 0, newObjects.length);
		List lst1 = Arrays.asList(newObjects);
		List lst2 = Arrays.asList(objects2);
		lst1.retainAll(lst2);

		return lst1.toArray();
	}

	/**
	 * ������תΪ���ģ����ڴ�ӡ���
	 * 
	 * @param date
	 * @return
	 * @since 2006-9-1
	 * @author hst
	 * @version 1.01 2006-9-11
	 */
	public static String date2Chinese(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		String sYear = "��";
		String sMonth = "��";
		String sDay = "��";
		String[] number = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��" };

		// ��
		while (year > 0) {
			int k = year % 10;
			year = year / 10;
			sYear = number[k] + sYear;
		}
		// ��
		if (month <= 9)
			sMonth = number[month] + sMonth;
		else if (month == 10)
			sMonth = "ʮ" + sMonth;
		else
			sMonth = "ʮ" + number[month - 10] + sMonth;

		// ��
		if (day <= 9)
			sDay = number[day] + sDay;
		else
			sDay = (day >= 20 ? number[day / 10] : "") + "ʮ" + (day % 10 == 0 ? "" : number[day % 10]) + sDay;

		return sYear + sMonth + sDay;
	}


	/**
	 * ȥ���ظ����ַ���
	 * 
	 * @param targetStr
	 *            Ҫȥ���ظ�ֵ���ַ�������
	 * @return ȥ���ظ�ֵ����ַ�������
	 * @since 2006-9-15
	 * @author hst
	 * @version 1.00 2006-9-15
	 */
	public static String[] distinctStr(String[] targetStr) {

		if (targetStr == null)
			return null;

		Set set = new TreeSet();

		for (int i = 0; i < targetStr.length; i++) {
			set.add(targetStr[i]);
		}

		Iterator it = set.iterator();
		String[] result = new String[set.size()];
		int j = 0;

		while (it.hasNext()) {
			result[j] = (String) it.next();
			j++;
		}
		return result;
	}

//	/**
//	 * ����������excel�ļ������������ص�������ԣ����ظ��ļ�������·����
//	 * 
//	 * @param ps
//	 *            �赼���Ľ������
//	 * @param request
//	 *            request��
//	 * @return �ļ�������·����
//	 * @since 2006-11-28
//	 * @author hst
//	 * @version 1.00 2006-11-28
//	 */
//	public static String createExpXls(PaginationSupport ps, HttpServletRequest request) throws Exception {
//		return createExpXls(ps, null, request);
//	}
//
//	/**
//	 * ����������excel�ļ������������ص�������ԣ����ظ��ļ�������·����
//	 * 
//	 * @param ps
//	 *            �赼���Ľ������
//	 * @param numberFormat
//	 *            ���ָ�ʽ
//	 * @param request
//	 * @return �ļ�������·����
//	 * @throws Exception
//	 * @since 2007-10-18
//	 * @author hst
//	 * @version 1.00 2007-10-18
//	 */
//	public static String createExpXls(PaginationSupport ps, String numberFormat, HttpServletRequest request) throws Exception {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		String sNow = sdf.format(new Date());
//		SimpleXlsCreator sxc = new SimpleXlsCreator();
//		if(!StringUtils.isBlank(numberFormat)){
//			// �������ָ�ʽ
//			sxc.setNumberFormat(numberFormat);
//		}
//		String sFilePath = null;// �ļ����ɵ�·��
//		UserInfo userInfo = SessionProcessor.getInstance().getUserInfo(request);
//
//		if (userInfo != null) {
//			// ��˰����أ�����Ա����ΪĿ¼�����ʱ�ļ���
//			sFilePath = WebConstants.TEMP_PATH + "/" + "commonxls/" + userInfo.swjgDm.substring(0, 7) + "0000" + "/" + userInfo.loginName + "/" + sNow + ".xls";
//		} else {
//			sFilePath = WebConstants.TEMP_PATH + "/" + "commonxls/" + "unknow/" + sNow + ".xls";
//		}
//
//		if (ps.getHeads() != null) {
//			// �����ͷ��
//			sxc.appendHead(ps.getHeads());
//		}
//
//		if (ps.getFieldNames() != null) {
//			// ����ѯ���Ϊbo��domain�������
//			sxc.appendBean(ps.getItems(), ps.getFieldNames());
//		} else {
//			// ����ѯ���ΪList-Object[]��List-List�������
//			sxc.append(ps.getItems());
//		}
//
//		// ����xls�ļ���
//		sxc.createXls(sFilePath);
//
//		// �������صĲ�����
//		request.setAttribute(WebConstants.DOWNLOAD_SRC_PATH, sFilePath);
//		if (!isNullOrEmpty(ps.getExpFileName())) {
//			request.setAttribute(WebConstants.DOWNLOAD_FILE_NAME, ps.getExpFileName() + ".xls");
//		}
//
//		return sFilePath;
//	}
//
//	/**
//	 * ��resultList������ط�ҳ����
//	 * 
//	 * @param currentPage
//	 * @param resultList
//	 * @return
//	 * @since 2009-6-10
//	 * @author hst
//	 * @version 1.00 2009-6-10
//	 */
//	public static PaginationSupport paginate(int currentPage, List resultList) {
//		List list = new ArrayList();
//		// �����ҳ��ʾ
//		int pageSize = WebConstants.DEFAULT_PAGE_NUM;
//		int startIndex = (currentPage - 1) * pageSize;
//		if (startIndex < 0) {
//			startIndex = 0;
//		}
//		int endIndex = resultList.size() > startIndex + pageSize ? startIndex + pageSize : resultList.size();
//		for (int i = startIndex; i < endIndex; i++) {
//			if (resultList.size() > i) {
//				list.add(resultList.get(i));
//			}
//		}
//		return new PaginationSupport(list, resultList.size(), pageSize, startIndex);
//	}

	/**
	 * ��һ��List��Ԫ�ؽ�����������
	 * 
	 * @param list
	 * @param keyFieldName
	 *            ��Ϊ�����ֶε�����
	 *            <P>
	 *            ����ΪString����Number����Date����
	 * @param isDesc
	 *            �Ƿ�������
	 * @throws Exception
	 * @since 2007-10-17
	 * @author hst
	 * @version 1.00 2007-10-17
	 */
	public static void sort(List list, String keyFieldName, boolean isDesc) throws Exception {

		List temp = new ArrayList();
		String getterName = "get" + String.valueOf(keyFieldName.charAt(0)).toUpperCase() + keyFieldName.substring(1);

		for (int index = 0; index < list.size(); index++) {
			Object element = list.get(index);
			Object key = element.getClass().getMethod(getterName).invoke(element);
			if (!(key instanceof String || key instanceof Number || key instanceof Date)) {
				throw new Exception("�����ֶε�����ֻ����String����Date����Number��");
			}
			Object[] one = { key, element };
			temp.add(one);
		}

		for (int i = 0; i < temp.size(); i++) {
			Object[] one = (Object[]) temp.get(i);
			Object keyOne = one[0];
			int rate = 0;
			for (int j = 0; j < temp.size(); j++) {
				if (j == i)
					continue;
				Object[] other = (Object[]) temp.get(j);
				Object keyOther = other[0];
				if (compare(keyOne, keyOther) > 0)
					rate++;
				if (compare(keyOne, keyOther) == 0 && j < i)
					rate++;
			}

			if (isDesc) {
				rate = temp.size() - rate - 1;
			}
			list.remove(rate);
			list.add(rate, one[1]);

		}

	}

	/**
	 * �Ƚ�����Comparable����Ĵ�С
	 * 
	 * @param one
	 * @param other
	 * @return ���ڷ������������ڷ���0��С�ڷ��ظ���
	 * @throws Exception
	 * @since 2007-10-17
	 * @author hst
	 * @version 1.00 2007-10-17
	 */
	public static int compare(Object one, Object other) throws Exception {

		if (one instanceof Comparable && other instanceof Comparable) {
			return ((Comparable) one).compareTo(other);
		}

		throw new Exception("���������ܱȽϡ�");
	}

	/**
	 * ������ĸתΪ��д
	 * 
	 * @param source
	 * @return
	 * @since 2007-10-18
	 * @author hst
	 * @version 1.00 2007-10-18
	 */
	public static String replaceFirstCharToBig(String source) {
		if (source == null || source.equals("")) {
			return null;
		}
		String first = source.substring(0, 1).toUpperCase();
		return source.length() == 1 ? first : first.concat(source.substring(1));
	}

	/**
	 * ������ĸתΪСд
	 * 
	 * @param source
	 * @return
	 * @since 2007-10-18
	 * @author hst
	 * @version 1.00 2007-10-18
	 */
	public static String replaceFirstCharToSmall(String source) {
		if (source == null || source.equals("")) {
			return null;
		}
		String first = source.substring(0, 1).toLowerCase();
		return source.length() == 1 ? first : first.concat(source.substring(1));
	}

	
	/**
	 * ��ȡSequence ID
	 * @param conn
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 * @since 2009-6-23
	 * @author hst
	 * @version 1.00 2009-6-23
	 */
	public static String getSequence(Connection conn, String sequenceName) throws Exception {
		// ����SQL
		StringBuffer SQL = new StringBuffer("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");

		// ��ʼ���ֲ�����
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sequence = null;
		ps = conn.prepareStatement(SQL.toString());
		rs = ps.executeQuery();
		if (rs.next()) {
			sequence = rs.getString(1);
		}
		close(ps);
		return sequence;
	}
}
