package com.cy.dctms.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description 日期处理工具类
 * @author 		haoy
 *
 */
public class DateUtils {

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String formatDateYyyyMmDd(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(date) || StringUtils.isWhitespace(date)) {
            return "";
        }
        try {
            return sdf.format(sdf.parse(date)).replaceAll("-","");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getYyyyMmDdH() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

	/**
	 * 获取当前年月 eg. 201409
	 * @return string
	 */
	public static String getYearAndMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(new Date());
	}

    /**
     * 获取年 eg. 2014
     * @return
     */
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

	/**
	 * 获取当前月份 eg. 9
	 * @return int
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前年月日 eg. 2014-09-02
	 * @return string
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

    /**
     * 获取当前时间 eg. 11月05日16:17:13
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间 16:17:13
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期的前一天 eg. 2014-09-01
     * @return string
     */
    public static String getBeforeDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        return sdf.format(calendar.getTime());
    }


    /**
     * 获取当前日期的前几天 eg. 2014-09-01
     * @return string
     */
    public static String getBeforeDay(int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -days);

        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前月第一天
     * @return string
     */
    public static String getFirstDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前月前一个月第一天
     * @return string
     */
    public static String getFirstDayOfPreMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return sdf.format(calendar.getTime());
    }

    /**
     * 判断今天是否为本月的第一天
     * @return boolean
     */
    public static boolean isFirstDay() {
        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH);

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        int nextMonth = calendar.get(Calendar.MONTH);

        if (month == nextMonth) {
            return false;
        }

        return true;
    }

    /**
     * 获取今天星期几
     * @return
     */
    public static String getWeekDay() {

        String[] weeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (index < 0) {
            index = 0;
        }

        return weeks[index];
    }

	public static void main(String[] args) throws ParseException {
		System.out.println(getCurrentDateTime());
	}
}
