package com.cy.swp.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式验证工具�?
 *
 * @author hayden
 */
public class ValidateUtil {
    /**
     * 判断是否为正确的移动电话号码
     *
     * @return false:不是; true:�?
     */
    public static boolean validateTelePhone(String telephone) {
        if (StringUtils.isEmpty(telephone)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher macher = pattern.matcher(telephone);
        return macher.matches();
    }

    /**
     * 判断是否为正确的 固定电话号码�?571-88175786�?
     *
     * @return false:不是; true:�?
     */
    public static boolean validatePhone(String telephone) {
        if (StringUtils.isEmpty(telephone)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$");
        Matcher macher = pattern.matcher(telephone);
        return macher.matches();
    }

    /**
     * 判断是否为正确的 固定电话号码�?57188175786�?
     *
     * @return false:不是; true:�?
     */
    public static boolean validatePhonees(String telephone) {
        if (StringUtils.isEmpty(telephone)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^((0\\d{2,3}))(\\d{7,8})(-(\\d{3,}))?$");
        Matcher macher = pattern.matcher(telephone);
        return macher.matches();
    }

    /**
     * 判断是否为正确的身份证号
     *
     * @return false:不是; true:�?
     */
    public static boolean validateIdentityLicenseNum(String identityLicenseNum) {
        if (StringUtils.isEmpty(identityLicenseNum)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$");
        Matcher macher = pattern.matcher(identityLicenseNum);
        return macher.matches();
    }
 
    /**
     * 验证车牌�?
     * @param carNumber
     * @return
     */
   
    public static boolean validateCarNumber(String carNumber){
    	if (StringUtils.isEmpty(carNumber)) {
            return false;
        }
    	 Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$|^[a-zA-Z]{2}\\d{7}$");
         Matcher macher = pattern.matcher(carNumber);
         return macher.matches();
    }
}
