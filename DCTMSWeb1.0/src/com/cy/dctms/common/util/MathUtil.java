package com.cy.dctms.common.util;

import org.apache.commons.lang.StringUtils;


public class MathUtil {
	/**С��ת��Ϊ�ٷ���
	 * @author WJL
	 * @param decimal ��������С��
	 * @param num Ҫ������С����λ��
	 */
	public static String decimalChangePercent(String decimal){
		Double d = Double.valueOf(decimal);
		if (d==0) {
			return "";
		}else if (d==1) {
			return "100%";
		} 
		Double percent = d * 100;
		//��Double���͵����ֱ���С�����λ
		String s = subDecimalNum(percent);
		return s+"%";
	} 
	/**����С�����λ��
	 * @author WJL
	 * @param decimal ��������С��
	 * @param num Ҫ������С����λ��
	 */
	public static String subDecimalNum(Double decimal){
		//DecimalFormat df = new DecimalFormat("#.0000");
		//return df.format(decimal);
		return String.format("%."+2+"f", decimal);
	} 
	/**����String���͵�����������������,���ص�Ҳ��String��
	 * @author WJL
	 * @param diviser ����
	 * @param divident ������
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
