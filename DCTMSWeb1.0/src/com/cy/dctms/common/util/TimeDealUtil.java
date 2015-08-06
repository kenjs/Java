package com.cy.dctms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class TimeDealUtil {
	/**时间转换为年月日格式
	 * @author WJL
	 * @param time 字符串时间
	 */
	public static String TimeToYyyyMMDd(String time) {
		if (StringUtils.isBlank(time)) return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(time);
			return sdf.format(d);
		} catch (ParseException e) {
			return "时间不合法";
		}
	}

}
