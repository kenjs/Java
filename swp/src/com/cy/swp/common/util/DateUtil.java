package com.cy.swp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cy.swp.common.constants.Constants;

public class DateUtil {

	/**
	 * yyyy-MM-dd
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDayDataFromStr(String dataStr) throws ParseException {
		if(StringUtils.isEmpty(dataStr)){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_DAY).parse(dataStr);
	}
	
	
	/**
	 * yyyy-MM-dd
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static String parseDayDataFrom(Date dataStr) throws ParseException {
		if(dataStr == null){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_DAY).format(dataStr);
	}
	
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseLongDataFromStr(String dataStr) throws ParseException {
		if(StringUtils.isEmpty(dataStr)){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_LONG).parse(dataStr);
	}
	
	/**
	 * HH:mm:ss
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static String parseLongHhStrFromDate(Date dataStr) throws ParseException {
		if(dataStr == null){
			return "";
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_HH).format(dataStr);
	}
	
	
	/**
	 * yyyyMMdd
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static String parseDayDataFromStr(Date dataStr) throws ParseException {
		if(dataStr == null){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_TIME).format(dataStr);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param dataStr
	 * @return
	 * @throws ParseException
	 */
	public static String parseLongDataFromStr(Date dataStr) throws ParseException {
		if(dataStr == null){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_LONG).format(dataStr);
	}
	
	/**
	 * 计算两个时间的时间差
	 * startTime开始时间
	 * endTime结束时间
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Integer evaluateTimeDays(String startTime,String endTime){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long between = 0;
        try {
            Date begin = dfs.parse(startTime);
            Date end = dfs.parse(endTime);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long time = (day*24*60)+(hour*60)+min;
        return Integer.parseInt(String.valueOf(time));
	}
	
	
	/**
	 * 时间转化
	 * date当前时间
	 * dayTime相差天数
	 * @param date
	 * @param dayTime
	 * @return
	 */
	public static Date getNextDay(Date date,int dayTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayTime);
		date = calendar.getTime();
		return date;
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getYear(String dateStr) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDayDataFromStr(dateStr));
		return calendar.get(Calendar.YEAR);
	}
	
	public static boolean isEarly(Date before,Date after) {
    	if(before == null || after == null) {
    		throw new NullPointerException("date1 or date2 can't be null !");
    	}
    	Calendar calendarF = Calendar.getInstance();
    	Calendar calendarS = Calendar.getInstance();
    	calendarF.setTime(before);
    	calendarS.setTime(after);
    	
    	return calendarF.before(calendarS);
    }
	
	public static boolean isEarly(String dateStr1,String dateStr2) throws ParseException {
    	Date date1 = parseDayDataFromStr(dateStr1);
    	Date date2 = parseDayDataFromStr(dateStr2);
    	return isEarly(date1, date2);
    }
	
	
	public static void main(String[] args) throws ParseException {
		
	}
}
