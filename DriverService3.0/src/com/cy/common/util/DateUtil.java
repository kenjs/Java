package com.cy.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cy.common.constants.Constants;

public class DateUtil {

    /**
     * 把日期字符串按指定格式进行格式化
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date formatDate(String dateStr) throws ParseException {
    	if(StringUtils.isBlank(dateStr)) {
    		throw new NullPointerException("date can't be null !");
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMATE_DAY);
    	return sdf.parse(dateStr);
    }
    
    /**
     * 把日期按指定格式进行格式化
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date formatDate(Date date) throws ParseException {
    	if(date == null) {
    		throw new NullPointerException("date can't be null !");
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMATE_DAY);
    	return sdf.parse(sdf.format(date));
    }
    
    /**
     * 把日期按指定格式进行格式化
     * @param dateStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String formatDate2Str(String dateStr) throws ParseException {
    	if(StringUtils.isBlank(dateStr)) {
    		throw new NullPointerException("date can't be null !");
    	}
    	Date date = formatDate(dateStr);
    	return formatDate2Str(date);
    }
    
    /**
     * 把日期按指定格式进行格式化
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String formatDate2Str(Date date) throws ParseException {
    	if(date == null) {
    		throw new NullPointerException("date can't be null !");
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMATE_DAY);
    	return sdf.format(date);
    }
    
    /**
     * 日期比较早晚
     * @param date1
     * @param date2
     * @return
     */
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
    
    /**
     * 日期比较早晚
     * @param dateStr1
     * @param dateStr2
     * @return
     * @throws ParseException
     */
    public static boolean isEarly(String dateStr1,String dateStr2) throws ParseException {
    	Date date1 = formatDate(dateStr1);
    	Date date2 = formatDate(dateStr2);
    	return isEarly(date1, date2);
    }
    
    /**
     * 获得从当前时间起以后的时间
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date getFurturDate(int days) throws ParseException {
    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	date  = calendar.getTime();
    	return formatDate(date);
    }
    
    /**
     * 获得从某时间起以后的时间
     * @param days
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getFurturDate(Date date,int days) throws ParseException {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	date  = calendar.getTime();
    	return formatDate(date);
    }
    
    /**
     * 获得从某时间起以后的时间
     * @param days
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getFurturDate(String dateStr,int days) throws ParseException {
    	Date date = formatDate(dateStr);
    	return getFurturDate(date,days);
    }
    
    /**
     * 获得当前时间
     * @return
     * @throws ParseException
     */
    public static Date getNow() throws ParseException {
    	Calendar calendar = Calendar.getInstance();
    	return formatDate(calendar.getTime());
    }
    
    public static String getNowStr() throws ParseException {
    	Calendar calendar = Calendar.getInstance();
    	return formatDate2Str(calendar.getTime());
    }
    
    public static String getCurrentDateTime() {
    	DateFormat sf = new SimpleDateFormat(Constants.DATE_FORMATE_LONG);
    	Calendar calendar = Calendar.getInstance();
    	return sf.format(calendar.getTime());
    }
    
    public static void main(String[] args) throws ParseException {
		System.out.println(getCurrentDateTime());
	}
}
