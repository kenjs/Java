package com.cy.dctms.util;

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
 * 常用工具类。
 * 
 * @since 2009-6-8
 * @author 
 * @version 1.00 2009-6-8
 */
@SuppressWarnings("unchecked")
public class SysToolsUtil {

	/**
	 * 将捕获的异常封装成字符串, 而后抛出, 如: try { ... } catch (SomeException se) { String s = convertException(se); throw new MyException(s); }
	 * 
	 * @param e
	 * @return
	 */
	public static String convertException(Exception e) {
		return "caught exception " + e.getClass().getName() + ", " + e.getMessage();
	}

	/**
	 * 将字符串数组list中的字符串以分隔符separator连接成一个字符串,且每个字符串冠以单引号 如将数组str[]={str1,str2,str3}以/为分隔符转换为"'str1'/'str2'/'str3'" 如将数组str[]={str1,str2,str3}以,为分隔符转换为"'str1','str2','str3'"
	 * 
	 * @param list
	 *            需合并的字符串数组
	 * @param separator
	 *            用于合并的分隔符
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
	 * 字符串转码
	 * 
	 * @param a_str
	 *            字符串值
	 * @param a_orig:
	 *            如 "ISO-8859-1"
	 * @param a_dest:
	 *            如 "GB2312"
	 * @return 转码后的值
	 * @throws UnsupportedEncodingException
	 */
	public static String convertEncode(String a_str, String a_orig, String a_dest) throws UnsupportedEncodingException {
		byte[] temp = a_str.getBytes(a_orig);
		return new String(temp, a_dest);
	}

	/**
	 * 计算字符串是"Y"还是"N", 只有字符串是"Y", "1"(可以有空格), 才返回"Y", 否则为"N"
	 * 
	 * @param a_value
	 *            需要判别的字符串
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
	 * 获取数据库当前时间，若失败则返回空。
	 * <p>
	 * 测试通过的数据库有：Oracle、Microsoft SQL Server、Sybase。
	 * 
	 * @param conn
	 *            数据库连接。
	 * @return 数据库当前时间。
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

				// 下面的数据库没经过测试。
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
				// 其他
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
	 * 格式化小数（四舍五入、指定精度）。
	 * <p>
	 * 浮点数有个很恶心的问题，就是当涉及类型转换时会造成失真。
	 * <p>
	 * 例如：<code>double d = 3.005;</code>
	 * <p>
	 * 当你<code>new BigDecimal(d)</code>得到的结果却是：
	 * <p>
	 * 3.00499999999999989341858963598497211933135986328125
	 * <p>
	 * 若此时进行四舍五入，可怜的0.005就没有了。 别以为<code>DecimalFormat</code>能用，它的处理机制是和<code>BigDecimal</code>一样的。
	 * <p>
	 * 较好的办法就是将double转成字符串，字符串表述最准确，不存在失真的问题。
	 * <p>
	 * 
	 * @param value
	 *            输入数值。
	 * @param dec
	 *            要求精度。
	 * @return 四舍五入后的数值。
	 * @author hst
	 */
	public static double formatDecimal(double value, int dec) {
		java.math.BigDecimal bd = new java.math.BigDecimal(String.valueOf(value));
		bd = bd.setScale(dec, java.math.BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * 把double转换BigDecimal。
	 * 
	 * @param d
	 *            输入数值。
	 * @param scale
	 *            保留的小数位数。
	 * @return BigDecimal。
	 * @author hst
	 */
	public static BigDecimal decValue(double d, int scale) {
		BigDecimal rs = new BigDecimal(String.valueOf(d));
		return rs.setScale(scale, java.math.BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 将double类型转换成BigDecimal，保留两位小数。
	 * 
	 * @param d
	 *            输入数值。
	 * @return BigDecimal。
	 */
	public static BigDecimal double2BigDecimal(double d) {
		return decValue(d, 2);
	}

	/**
	 * html内容转换。
	 * 
	 * @param str
	 *            纯文本。
	 * @return 带html格式文本。
	 * @author hst
	 */
	public static String htmlEncoder(String str) {
		if (str == null)
			return ("");
		if (str.equals(""))
			return ("");

		// 建立一个StringBuffer来处理输入数据

		StringBuffer buf = new StringBuffer();
		char ch1 = '\n';
		char ch2 = '\n';

		for (int i = 0; i < str.length(); i++) {
			ch1 = str.charAt(i);

			if ((ch1 == ' ') && ((i + 1) < str.length())) { // 将两个空格转换为一个全角中文空格
				ch2 = str.charAt(i + 1);
				if (ch2 == ' ') {
					buf.append("　");
					i++;
				} else {
					buf.append(ch1);
				}
			} else if (ch1 == '\n') {
				buf.append("<br>");
			} else if (ch1 == '\t') {
				buf.append("　　");
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
	 * 关闭数据库连接。
	 * 
	 * @param conn
	 *            数据库连接。
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
	 * 关闭Statement。
	 * 
	 * @param stmt
	 *            Statement。
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
	 * 返回异常的堆栈信息。
	 * 
	 * @param exception
	 *            异常。
	 * @return 堆栈信息。
	 * @author hst
	 */
	public static String getStackTrace(Throwable exception) {

		StackTraceElement[] ste = exception.getStackTrace();
		StringBuffer sbResult = new StringBuffer();

		for (int i = 0; i < ste.length; i++) {
			if (i > 0)
				sbResult.append("\n    at ");// 加入换行和缩进。
			sbResult.append(ste[i].toString());
		}

		return sbResult.toString();
	}

	// ------------ 来自Req的方法 --------------
	/**
	 * 获得request中某个参数的值，若参数不存在则返回默认值。
	 * 
	 * @param request
	 * @param name
	 *            参数名
	 * @param defaultVal
	 *            默认值
	 * @return 参数的值
	 * @author 吴仁光
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
	 * 获得request中某个参数的值，若参数不存在则返回空串。
	 * 
	 * @param request
	 * @param name
	 *            参数名
	 * @return String 参数的值
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
	 * 获得request中某个参数的数组。
	 * 
	 * @param request
	 * @param name
	 *            参数名
	 * @return String[] 参数的数组
	 * @author hst
	 * @version 1.02 2006-01-18
	 */
	public static String[] getParameterValues(HttpServletRequest request, String name) {
		String[] value = request.getParameterValues(name);
		return value;
	}

	/**
	 * 判断一个字符串是否空值或空串。
	 * <p>
	 * 
	 * @param str
	 *            要判断的字符串。
	 * @return 若字符串为空或只有空格的空串则返回true，否则返回false。
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
	 * 如果字符串str为空则转换为str1
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
	 * 获得单引号包含的以逗号分割的字符串，主要用来生成SQL语句的‘IN’参数
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
	 * 将字符串str转换为GBK编码格式
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
	 * 转换成ISO编码格式
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
	 * 将ISO8859-1编码的字符串转换为GBK格式的
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
				return "编码转换错误!";
			}
		}
	}

	/**
	 * 将ISO8859-1编码的字符串转换为GBK格式,并且Trim掉字符串两头的空格，适合数据库中为CHAR()格式的数据
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
				return "编码转换错误!";
			}
		}
	}

	/**
	 * 将GBK格式数据转成ISO8859-1的数据
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
				return "编码转换错误!";
			}
		}
	}

	/**
	 * 取得扩展名
	 * 
	 * @param String
	 *            s
	 * @return String
	 * @author 吴仁光
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
	 * 取得文件名称(不带路径).
	 * 
	 * @param String
	 *            fileName
	 * @return String
	 * @author 吴仁光
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
	 * 取得Date的format格式的字符串
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
	 * 将format格式的字符串转换成Date
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
			System.out.println("字符串转日期错误");
			return null;
		}
		return d;
	}

	/**
	 * 日期转字符串yyyy-MM-dd
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
	 * 字符串yyyy-MM-dd转日期
	 * 
	 * @author ljb
	 */
	public static Date string2date(String s) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (Exception e) {
			System.out.println("字符串转日期错误");
			return null;
		}
		return d;
	}

	/**
	 * 日期转字符串yyyy-MM-dd HH:mm:ss
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
	 * 字符串yyyy-MM-dd HH:mm:ss转日期
	 * 
	 * @author ljb
	 */
	public static Date string2dateDetail(String s) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try {
			d = df.parse(s);
		} catch (Exception e) {
			System.out.println("字符串转日期错误");
			return null;
		}
		return d;
	}

	/**
	 * 把指定的日期加上amount数量的field
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
	 * 返回非空对象。
	 * 
	 * @param str
	 *            字符串。
	 * @return 若参数为空，则返回空串。
	 * @author hst
	 */
	public static String notNull(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param bool
	 *            布尔类型。
	 * @return 若参数为空，则返回false。
	 * @author hst
	 */
	public static Boolean notNull(Boolean bool) {
		if (bool == null)
			return Boolean.FALSE;
		else
			return bool;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param dbl。
	 * @return 若参数为空，则返回0。
	 * @author hst
	 */
	public static Double notNull(Double dbl) {
		if (dbl == null)
			return new Double(0);
		else
			return dbl;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param flt。
	 * @return 若参数为空，则返回0。
	 * @author hst
	 */
	public static Float notNull(Float flt) {
		if (flt == null)
			return new Float(0);
		else
			return flt;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param integer。
	 * @return 若参数为空，则返回0。
	 * @author hst
	 */
	public static Integer notNull(Integer integer) {
		if (integer == null)
			return new Integer(0);
		else
			return integer;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param lng。
	 * @return 若参数为空，则返回0。
	 * @author hst
	 */
	public static Long notNull(Long lng) {
		if (lng == null)
			return new Long(0);
		else
			return lng;
	}

	/**
	 * 返回非空对象。
	 * 
	 * @param sht。
	 * @return 若参数为空，则返回0。
	 * @author hst
	 */
	public static Short notNull(Short sht) {
		if (sht == null)
			return new Short("0");
		else
			return sht;
	}







	/**
	 * 过滤对象数组，获得两个数组的交集。
	 * <p>
	 * 
	 * @param objects1
	 *            对象数组1。
	 * @param objects2
	 *            对象数组2。
	 * @return 两个数组的交集。
	 * @since 2006-08-22
	 * @author hst
	 * @version 1.01 2007-01-18
	 */
	public static Object[] filterObjects(Object[] objects1, Object[] objects2) {
		if (objects1 == null || objects2 == null) {
			throw new NullPointerException();
		}
		// 为免修改了原来的对象，新建一个数组处理。
		Object[] newObjects = new Object[objects1.length];
		System.arraycopy(objects1, 0, newObjects, 0, newObjects.length);
		List lst1 = Arrays.asList(newObjects);
		List lst2 = Arrays.asList(objects2);
		lst1.retainAll(lst2);

		return lst1.toArray();
	}

	/**
	 * 将日期转为中文，用于打印输出
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
		String sYear = "年";
		String sMonth = "月";
		String sDay = "日";
		String[] number = { "Ο", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

		// 年
		while (year > 0) {
			int k = year % 10;
			year = year / 10;
			sYear = number[k] + sYear;
		}
		// 月
		if (month <= 9)
			sMonth = number[month] + sMonth;
		else if (month == 10)
			sMonth = "十" + sMonth;
		else
			sMonth = "十" + number[month - 10] + sMonth;

		// 日
		if (day <= 9)
			sDay = number[day] + sDay;
		else
			sDay = (day >= 20 ? number[day / 10] : "") + "十" + (day % 10 == 0 ? "" : number[day % 10]) + sDay;

		return sYear + sMonth + sDay;
	}


	/**
	 * 去掉重复的字符串
	 * 
	 * @param targetStr
	 *            要去掉重复值的字符串数组
	 * @return 去掉重复值后的字符串数组
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
//	 * 创建导出的excel文件，并设置下载的相关属性，返回该文件的物理路径。
//	 * 
//	 * @param ps
//	 *            需导出的结果集。
//	 * @param request
//	 *            request。
//	 * @return 文件的物理路径。
//	 * @since 2006-11-28
//	 * @author hst
//	 * @version 1.00 2006-11-28
//	 */
//	public static String createExpXls(PaginationSupport ps, HttpServletRequest request) throws Exception {
//		return createExpXls(ps, null, request);
//	}
//
//	/**
//	 * 创建导出的excel文件，并设置下载的相关属性，返回该文件的物理路径。
//	 * 
//	 * @param ps
//	 *            需导出的结果集。
//	 * @param numberFormat
//	 *            数字格式
//	 * @param request
//	 * @return 文件的物理路径。
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
//			// 设置数字格式
//			sxc.setNumberFormat(numberFormat);
//		}
//		String sFilePath = null;// 文件生成的路径
//		UserInfo userInfo = SessionProcessor.getInstance().getUserInfo(request);
//
//		if (userInfo != null) {
//			// 按税务机关－操作员编码为目录存放临时文件。
//			sFilePath = WebConstants.TEMP_PATH + "/" + "commonxls/" + userInfo.swjgDm.substring(0, 7) + "0000" + "/" + userInfo.loginName + "/" + sNow + ".xls";
//		} else {
//			sFilePath = WebConstants.TEMP_PATH + "/" + "commonxls/" + "unknow/" + sNow + ".xls";
//		}
//
//		if (ps.getHeads() != null) {
//			// 加入表头。
//			sxc.appendHead(ps.getHeads());
//		}
//
//		if (ps.getFieldNames() != null) {
//			// 当查询结果为bo或domain的情况。
//			sxc.appendBean(ps.getItems(), ps.getFieldNames());
//		} else {
//			// 当查询结果为List-Object[]或List-List的情况。
//			sxc.append(ps.getItems());
//		}
//
//		// 创建xls文件。
//		sxc.createXls(sFilePath);
//
//		// 设置下载的参数。
//		request.setAttribute(WebConstants.DOWNLOAD_SRC_PATH, sFilePath);
//		if (!isNullOrEmpty(ps.getExpFileName())) {
//			request.setAttribute(WebConstants.DOWNLOAD_FILE_NAME, ps.getExpFileName() + ".xls");
//		}
//
//		return sFilePath;
//	}
//
//	/**
//	 * 对resultList结果返回分页对象
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
//		// 构造分页显示
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
	 * 对一个List的元素进行重新排列
	 * 
	 * @param list
	 * @param keyFieldName
	 *            作为排列字段的名称
	 *            <P>
	 *            必须为String或者Number或者Date类型
	 * @param isDesc
	 *            是否降序排列
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
				throw new Exception("排列字段的类型只能是String或者Date或者Number。");
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
	 * 比较两个Comparable对象的大小
	 * 
	 * @param one
	 * @param other
	 * @return 大于返回正数，等于返回0，小于返回负数
	 * @throws Exception
	 * @since 2007-10-17
	 * @author hst
	 * @version 1.00 2007-10-17
	 */
	public static int compare(Object one, Object other) throws Exception {

		if (one instanceof Comparable && other instanceof Comparable) {
			return ((Comparable) one).compareTo(other);
		}

		throw new Exception("两个对象不能比较。");
	}

	/**
	 * 将首字母转为大写
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
	 * 将首字母转为小写
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
	 * 获取Sequence ID
	 * @param conn
	 * @param sequenceName
	 * @return
	 * @throws Exception
	 * @since 2009-6-23
	 * @author hst
	 * @version 1.00 2009-6-23
	 */
	public static String getSequence(Connection conn, String sequenceName) throws Exception {
		// 构造SQL
		StringBuffer SQL = new StringBuffer("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");

		// 初始化局部变量
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
