package com.cy.dctms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.cy.dctms.common.constants.Constants;

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
	
	public static String parseDayDataFromStr(Date dataStr) throws ParseException {
		if(dataStr == null){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_TIME).format(dataStr);
	}
	
	public static String parseDataFromStr(Date dataStr) throws ParseException {
		if(dataStr == null){
			return null;
		}
		return new SimpleDateFormat(Constants.DATE_FORMATE_DAY).format(dataStr);
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
	
}
