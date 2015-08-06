package com.cy.dctms.common.util;

import org.apache.commons.lang.StringUtils;


public class MathUtil {
	/**小数转换为百分数
	 * @author WJL
	 * @param decimal 传入来的小数
	 * @param num 要保留的小数点位数
	 */
	public static String decimalChangePercent(String decimal){
		Double d = Double.valueOf(decimal);
		if (d==0) {
			return "";
		}else if (d==1) {
			return "100%";
		} 
		Double percent = d * 100;
		//将Double类型的数字保留小数点后几位
		String s = subDecimalNum(percent);
		return s+"%";
	} 
	/**保留小数点后位数
	 * @author WJL
	 * @param decimal 传入来的小数
	 * @param num 要保留的小数点位数
	 */
	public static String subDecimalNum(Double decimal){
		//DecimalFormat df = new DecimalFormat("#.0000");
		//return df.format(decimal);
		return String.format("%."+2+"f", decimal);
	} 
	/**对俩String类型的整数数做除法运算,返回的也是String。
	 * @author WJL
	 * @param diviser 除数
	 * @param divident 被除数
	 */
	public static String subDecimalNum(String diviser,String divident){
		if (StringUtils.isBlank(diviser)||StringUtils.isBlank(divident)) {
			return "0";
		}
		if (Integer.valueOf(diviser)==0||Integer.valueOf(divident)==0) {
			return "0";
		}
		return String.valueOf(Double.valueOf(diviser)/Double.valueOf(divident));
	} 
}
