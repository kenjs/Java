package com.cy.interfaceService.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2014/9/15.
 */
public class DateUtils {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化字符串类型的日期 eg. 2014-09-15 09:10:01
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static String formatDateStr(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(simpleDateFormat.parse(dateStr));
    }

    /**
     * 获取今天星期几
     * @return
     */
    public static String getWeekDay() {

        String[] weeks = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday "};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if (index < 0) {
            index = 0;
        }

        return weeks[index];
    }

    public static void main(String args[]) throws ParseException {
        System.out.println(getWeekDay());
    }
}
