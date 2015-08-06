package com.cy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ValidateUtil {
	/**
	 * 判断是否为正确的移动电话号码
	 * @return false:不是; true:是
	 * */
	public static boolean validateTelePhone(String telephone){
		if(StringUtils.isEmpty(telephone)){
			return false;
		}
		Pattern pattern=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher macher=pattern.matcher(telephone);
		return macher.matches();
	}
	
	/**
	 * 判断是否为正确的 固定电话号码（0571-88175786或057188175786）
	 * @return false:不是; true:是
	 * */
	public static boolean validatePhone(String telephone){
		if(StringUtils.isEmpty(telephone)){
			return false;
		}
		Pattern pattern=Pattern.compile("^((0\\d{2,3}))(-{0,})(\\d{7,8})(-(\\d{3,}))?$");
		Matcher macher=pattern.matcher(telephone);
		return macher.matches();
	}
	
	/**
	 * 判断是否为正确的身份证号
	 * @return false:不是; true:是
	 * */
	public static boolean validateIdentityLicenseNum(String identityLicenseNum){
		if(StringUtils.isEmpty(identityLicenseNum)){
			return false;
		}
		Pattern pattern=Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$");
		Matcher macher=pattern.matcher(identityLicenseNum);
		return macher.matches();
	}
	
	/**
	 * 
	 * @param mail 邮箱账号
	 * @return 
	 */
	public static boolean isMail(String mail) {
		if(StringUtils.isBlank(mail)) {
			return false;
		}
		Pattern mailRegx = Pattern.compile("^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,4}$");
		Matcher match = mailRegx.matcher(mail);
		return match.matches();
	}
	
	public static void main(String[] args) {
		String mail = "haoyongvv@163.com";
		boolean b = isMail(mail);
		System.out.println(b);
	}
}
