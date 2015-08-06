package tf56.contract.util;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;


/**
 * 类DateUtils日期操作工具类
 * @athor changmeng.liu
 * @date 2014-3-24
 * @version 1.0
 * @update 
 */
public class DateUtils {

	private static final long MILLIS_IN_A_SECOND = 1000;

	private static final long SECONDS_IN_A_MINUTE = 60;

	private static final long MINUTES_IN_AN_HOUR = 60;

	private static final long HOURS_IN_A_DAY = 24;

	private static final int DAYS_IN_A_WEEK = 7;

	private static final int MONTHS_IN_A_YEAR = 12;

	// private static final int[] daysInMonth = new int[] { 31, 28, 31, 30, 31,
	// 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * 
	 * 最小日期，设定为1000年1月1日
	 */

	public static final Date MIN_DATE = date(1000, 1, 1);

	/**
	 * 
	 * 最大日期，设定为8888年1月1日
	 */

	public static final Date MAX_DATE = date(8888, 1, 1);

	/**
	 * 
	 * 根据年月日构建日期对象。注意月份是从1开始计数的，即month为1代表1月份。
	 * 
	 * @param year
	 *            年
	 * 
	 * @param month
	 *            月。注意1代表1月份，依此类推。
	 * 
	 * @param day
	 *            日
	 * 
	 * @return
	 */

	public static Date date(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * 计算两个日期（不包括时间）之间相差的周年数
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static int getYearDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException(
					"date1 and date2 cannot be null!");
		}
		if (date1.after(date2)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);
		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}

	/**
	 * 
	 * 计算两个日期（不包括时间）之间相差的整月数
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static int getMonthDiff(Date date1, Date date2) {

		if (date1 == null || date2 == null) {

			throw new InvalidParameterException(

			"date1 and date2 cannot be null!");

		}

		if (date1.after(date2)) {

			throw new InvalidParameterException("date1 cannot be after date2!");

		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date1);

		int year1 = calendar.get(Calendar.YEAR);

		int month1 = calendar.get(Calendar.MONTH);

		int day1 = calendar.get(Calendar.DATE);

		calendar.setTime(date2);

		int year2 = calendar.get(Calendar.YEAR);

		int month2 = calendar.get(Calendar.MONTH);

		int day2 = calendar.get(Calendar.DATE);

		int months = 0;

		if (day2 >= day1) {

			months = month2 - month1;

		} else {

			months = month2 - month1 - 1;

		}

		return (year2 - year1) * MONTHS_IN_A_YEAR + months;

	}

	/**
	 * 
	 * 统计两个日期之间包含的天数。包含date1，但不包含date2
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static int getDayDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException(
					"date1 and date2 cannot be null!");

		}
		Date startDate = org.apache.commons.lang.time.DateUtils.truncate(
				date1, Calendar.DATE);
		Date endDate = org.apache.commons.lang.time.DateUtils.truncate(date2,
				Calendar.DATE);
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		long millSecondsInOneDay = HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR
				* SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;
		return (int) ((endDate.getTime() - startDate.getTime()) / millSecondsInOneDay);

	}

	/**
	 * 
	 * 计算time2比time1晚多少分钟，忽略日期部分
	 * 
	 * 
	 * 
	 * @param time1
	 * 
	 * @param time2
	 * 
	 * @return
	 */

	public static int getMinuteDiffByTime(Date time1, Date time2) {

		long startMil = 0;

		long endMil = 0;

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(time1);

		calendar.set(1900, 1, 1);

		startMil = calendar.getTimeInMillis();

		calendar.setTime(time2);

		calendar.set(1900, 1, 1);

		endMil = calendar.getTimeInMillis();

		return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);

	}

	/**
	 * 
	 * 计算指定日期的前一天
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static Date getPrevDay(Date date) {

		return org.apache.commons.lang.time.DateUtils.addDays(date, -1);

	}

	/**
	 * 
	 * 计算指定日期前一周的日期
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static Date getPrevWeekDay(Date date) {

		return org.apache.commons.lang.time.DateUtils.addDays(date, -6);

	}

	/**
	 * 
	 * 计算指定日期前N天的日期
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static Date getPrevNDay(Date date,int n) {

		return org.apache.commons.lang.time.DateUtils.addDays(date, -n);

	}
	/**
	 * 
	 * 计算指定日期前N个月的日期
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static Date getPrevNMonth(Date date, int mount) {

		return org.apache.commons.lang.time.DateUtils.addMonths(date, mount);

	}

	/**
	 * 
	 * 计算指定日期的后一天
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static Date getNextDay(Date date) {

		return org.apache.commons.lang.time.DateUtils.addDays(date, 1);

	}

	/**
	 * 
	 * 判断date1是否在date2之后，忽略时间部分
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static boolean isDateAfter(Date date1, Date date2) {

		Date theDate1 = org.apache.commons.lang.time.DateUtils.truncate(date1,

		Calendar.DATE);

		Date theDate2 = org.apache.commons.lang.time.DateUtils.truncate(date2,

		Calendar.DATE);

		return theDate1.after(theDate2);

	}

	/**
	 * 
	 * 判断date1是否在date2之前，忽略时间部分
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static boolean isDateBefore(Date date1, Date date2) {

		return isDateAfter(date2, date1);

	}

	/**
	 * 
	 * 判断time1是否在time2之后，忽略日期部分
	 * 
	 * 
	 * 
	 * @param time1
	 * 
	 * @param time2
	 * 
	 * @return
	 */

	public static boolean isTimeAfter(Date time1, Date time2) {

		Calendar calendar1 = Calendar.getInstance();

		calendar1.setTime(time1);

		calendar1.set(1900, 1, 1);

		Calendar calendar2 = Calendar.getInstance();

		calendar2.setTime(time2);

		calendar2.set(1900, 1, 1);

		return calendar1.after(calendar2);

	}

	/**
	 * 
	 * 判断time1是否在time2之前，忽略日期部分
	 * 
	 * 
	 * 
	 * @param time1
	 * 
	 * @param time2
	 * 
	 * @return
	 */

	public static boolean isTimeBefore(Date time1, Date time2) {

		return isTimeAfter(time2, time1);

	}

	/**
	 * 
	 * 判断两个日期是否同一天（忽略时间部分）
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static boolean isSameDay(Date date1, Date date2) {

		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);

	}

	/**
	 * 
	 * 判断两个日历天是否同一天（忽略时间部分）
	 * 
	 * 
	 * 
	 * @param date1
	 * 
	 * @param date2
	 * 
	 * @return
	 */

	public static boolean isSameDay(Calendar date1, Calendar date2) {

		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);

	}

	/**
	 * 
	 * 将字符串形式的日期表示解析为日期对象
	 * 
	 * 
	 * 
	 * @param dateString
	 * 
	 * @return
	 */

	public static Date parseDate(String dateString) {
		if (dateString == null)
			return null;

		try {

			return org.apache.commons.lang.time.DateUtils.parseDate(

			dateString, new String[] { "yyyy-MM-dd", "yyyy-M-d",

			"yyyy-MM-d", "yyyy-M-dd", "yyyy年MM月dd日", "yyyy年MM月" });

		} catch (ParseException e) {

			return null;

		}

	}

	/**
	 * 
	 * 将字符串形式的时间表示解析为日期时间对象
	 * 
	 * 
	 * 
	 * @param timeString
	 * 
	 * @return
	 */

	public static Date parseTime(String timeString) {

		try {

			return org.apache.commons.lang.time.DateUtils.parseDate(

			timeString, new String[] { "hh:mm:ss", "h:m:s", "hh:mm",

			"h:m" });

		} catch (ParseException e) {

			return null;

		}

	}

	/**
	 * 
	 * 将字符串形式的日期时间表示解析为时间对象
	 * 
	 * 
	 * 
	 * @param timeString
	 * 
	 * @return
	 */

	public static Date parseDateTime(String timeString) {

		try {

			return org.apache.commons.lang.time.DateUtils.parseDate(

			timeString, new String[] { "yyyy-MM-dd HH:mm:ss",

			"yyyy-M-d H:m:s", "yyyy-MM-dd H:m:s",

			"yyyy-M-d HH:mm:ss" });

		} catch (ParseException e) {

			return null;

		}

	}

	/**
	 * 
	 * 计算两个日期之间包含的星期X的天数。
	 * 
	 * 
	 * 
	 * @param fromDate
	 * 
	 *            起始日期
	 * 
	 * @param toDate
	 * 
	 *            结束日期
	 * 
	 * @param dayOfWeek
	 * 
	 *            星期，例如星期三，星期四
	 * 
	 * @return
	 */

	public static int getWeekDaysBetween(Date fromDate, Date toDate,

	int dayOfWeek) {

		int result = 0;

		Date firstDate = getFirstWeekdayBetween(fromDate, toDate, dayOfWeek);

		if (firstDate == null) {

			return 0;

		}

		Calendar aDay = Calendar.getInstance();

		aDay.setTime(firstDate);

		while (aDay.getTime().before(toDate)) {

			result++;

			aDay.add(Calendar.DATE, DAYS_IN_A_WEEK);

		}

		return result;

	}

	/**
	 * 
	 * 获取在两个日期之间的第一个星期X
	 * 
	 * 
	 * 
	 * @param fromDate
	 * 
	 *            起始日期
	 * 
	 * @param toDate
	 * 
	 *            结束日期
	 * 
	 * @param dayOfWeek
	 * 
	 *            星期，例如星期三，星期四
	 * 
	 * @return
	 */

	public static Date getFirstWeekdayBetween(Date fromDate, Date toDate,

	int dayOfWeek) {

		Calendar aDay = Calendar.getInstance();

		aDay.setTime(fromDate);

		while (aDay.getTime().before(toDate)) {

			if (aDay.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {

				return aDay.getTime();

			}

			aDay.add(Calendar.DATE, 1);

		}

		return null;

	}

	/**
	 * 
	 * 取得参数year指定的年份的总天数
	 * 
	 * 
	 * 
	 * @param year
	 * 
	 * @return
	 */

	public static int getDaysInYear(int year) {

		Calendar aDay = Calendar.getInstance();

		aDay.set(year, 1, 1);

		Date from = aDay.getTime();

		aDay.set(year + 1, 1, 1);

		Date to = aDay.getTime();

		return getDayDiff(from, to);

	}

	/**
	 * 
	 * 取得指定年月的总天数
	 * 
	 * 
	 * 
	 * @param year
	 * 
	 * @param month
	 * 
	 * @return
	 */

	public static int getDaysInMonth(int year, int month) {

		Calendar aDay = Calendar.getInstance();

		aDay.set(year, month, 1);

		Date from = aDay.getTime();

		if (month == Calendar.DECEMBER) {

			aDay.set(year + 1, Calendar.JANUARY, 1);

		} else {

			aDay.set(year, month + 1, 1);

		}

		Date to = aDay.getTime();

		return getDayDiff(from, to);

	}

	/**
	 * 
	 * 获得指定日期的年份
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static int getYear(Date date) {

		return getFieldValue(date, Calendar.YEAR);

	}

	/**
	 * 
	 * 获得指定日期的月份
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static int getMonth(Date date) {

		return getFieldValue(date, Calendar.MONTH) + 1;

	}

	/**
	 * 
	 * 获得指定日期是当年的第几天
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static int getDayOfYear(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_YEAR);

	}

	/**
	 * 
	 * 获得指定日期是当月的第几天
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static int getDayOfMonth(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_MONTH);

	}

	/**
	 * 
	 * 获得指定日期是当周的第几天
	 * 
	 * 
	 * 
	 * @param date
	 * 
	 * @return
	 */

	public static int getDayOfWeek(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_WEEK);

	}

	private static int getFieldValue(Date date, int field) {

		if (date == null) {

			throw new InvalidParameterException("date cannot be null!");

		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(field);

	}

	/**
	 * 
	 * 获得指定日期之后一段时期的日期。例如某日期之后3天的日期等。
	 * 
	 * @param origDate
	 *            基准日期
	 * 
	 * @param amount
	 *            时间数量
	 * 
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * 
	 * @return
	 */

	public static final Date dateAfter(Date origDate, int amount, int timeUnit) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(origDate);

		calendar.add(timeUnit, amount);

		return calendar.getTime();

	}

	/**
	 * 
	 * 获得指定日期之前一段时期的日期。例如某日期之前3天的日期等。
	 * 
	 * @param origDate
	 *            基准日期
	 * 
	 * @param amount
	 *            时间数量
	 * 
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * 
	 * @return
	 */

	public static final Date dateBefore(Date origDate, int amount, int timeUnit) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(origDate);

		calendar.add(timeUnit, -amount);

		return calendar.getTime();

	}

	/**
	 * 
	 * 获得当前时间之后或者之前一段时间。例如某日期之后3天的日期等。
	 * 
	 * @param amount
	 *            时间数量
	 * 
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * 
	 * @return
	 */

	public static final Date nowAfter(int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	public static Date parseDate(String value, String pattern)
			throws ParseException {
		if (StringUtils.isBlank(value) || StringUtils.isBlank(pattern))
			return null;
		return new SimpleDateFormat(pattern).parse(value);
	}

	public static String formatString(Date date, String pattern) {
		if (date == null || StringUtils.isBlank(pattern))
			return null;
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String formatString(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static int getDayOfHour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static Date getArriveTime(Date date, Integer min) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	public static Date getDate(Integer minnute) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minnute);
		return calendar.getTime();
	}

	/**
	 * 
	 * 
	 * @Title: getNextDayTimestamp
	 * 
	 * @Description: 获取N天后的日期
	 * 
	 * @param @param day
	 * @param @return 设定文件
	 * 
	 * @return Timestamp 返回类型
	 * 
	 * @throws
	 */
	public static Timestamp getNextDayTimestamp(Integer day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 * 
	 * 
	 * @Title: isAfter
	 * 
	 * @Description: 判断date2是否晚于date1
	 * 
	 * @param @param date1
	 * @param @param date2
	 * @param @return 设定文件
	 * 
	 * @return boolean 返回类型
	 * 
	 * @throws
	 */
	public static boolean isAfter(Date date1, Date date2) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date1);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar1.after(calendar2);
	}

	/**
	 * 当前系统时间的Timestamp取得
	 * 
	 * @return
	 */
	public static Timestamp getTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/**
	 * 获得当前系统时间的0时0分0秒000
	 * 
	 * @return
	 */
	public static Date getSystemDate() {
		return new Date(System.currentTimeMillis() / 86400000 * 86400000
				- (23 - Calendar.ZONE_OFFSET) * 3600000);
	}

	/**
	 * 
	 * 
	 * @Title: getDayStart
	 * 
	 * @Description: 获取某天的开始时间00:00:00
	 * 
	 * @param @param date
	 * @param @return 设定文件
	 * 
	 * @return Date 返回类型
	 * 
	 * @throws
	 */
	public static Date getDateStart(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getFirstDateOfMonth(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getLastDateOfMonth(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	/**
	 * 
	 * 
	 * @Title: getDateEnd
	 * 
	 * @Description: 获取某个日期时间的结束时间24:00:00
	 * 
	 * @param @param date
	 * @param @return 设定文件
	 * 
	 * @return Date 返回类型
	 * 
	 * @throws
	 */
	public static Date getDateEnd(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 24);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取时段名称，暂时是中文，后面可以改成I18N
	 * 
	 * @return
	 */
	public static String getTimeBucket() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= 7 && hour < 11) {
			return "上午";
		} else if (hour >= 11 && hour < 13) {
			return "中午";
		} else if (hour >= 13 && hour < 18) {
			return "下午";
		} else if (hour >= 18 && hour < 23) {
			return "晚上";
		} else {
			return "午夜";
		}
	}

}
