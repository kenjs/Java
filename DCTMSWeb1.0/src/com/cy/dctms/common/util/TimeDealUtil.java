package com.cy.dctms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class TimeDealUtil {
	/**ʱ��ת��Ϊ�����ո�ʽ
	 * @author WJL
	 * @param time �ַ���ʱ��
	 */
	public static String TimeToYyyyMMDd(String time) {
		if (StringUtils.isBlank(time)) return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(time);
			return sdf.format(d);
		} catch (ParseException e) {
			return "ʱ�䲻�Ϸ�";
		}
	}

}
