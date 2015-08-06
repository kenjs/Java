package com.cy.interfaceService.common;

import cn.jpush.api.utils.StringUtils;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * 获取手机运营商
 * Created by nixianjing on 15/1/23.
 */
public class MobileUtil {


    /**
     * 判断传入的参数号码为哪家运营商
     * @param mobileSectionNo  移动号段
     * @param unicomSectionNo  联通号段
     * @param telecomSectionNo 电信号段
     * @param mobile 手机号码
     * @return 运营商名称 （-1号码错误、1移动、2联通、3电信、0其他）
     */
    public static String validateMobile(String mobileSectionNo,String unicomSectionNo,String telecomSectionNo,String mobile) {
        String returnString="";
        if(mobile==null || mobile.trim().length()!=11){
            return "-1";        //mobile参数为空或者手机号码长度不为11，错误！
        }
        String[] mobileSectionNoSplit = mobileSectionNo.split(",");
        String[] unicomSectionNoSplit = unicomSectionNo.split(",");
        String[] telecomSectionNoSplit = telecomSectionNo.split(",");
        if(StringUtils.isEmpty(returnString)) {
            for(int i = 0;i<mobileSectionNoSplit.length;i++) {
                if(mobile.trim().substring(0,3).equals(mobileSectionNoSplit[i].toString())) {
                    returnString = "1";
                    break;
                }
            }
        }
        if(StringUtils.isEmpty(returnString)) {
            for(int i = 0;i<unicomSectionNoSplit.length;i++) {
                if(mobile.trim().substring(0,3).equals(unicomSectionNoSplit[i].toString())) {
                    returnString = "2";
                    break;
                }
            }
        }
        if(StringUtils.isEmpty(returnString)) {
            for(int i = 0;i<telecomSectionNoSplit.length;i++) {
                if(mobile.trim().substring(0,3).equals(telecomSectionNoSplit[i].toString())) {
                    returnString = "3";
                    break;
                }
            }
        }
        if(StringUtils.isEmpty(returnString)) {
            returnString = "0";
        }
        return returnString;
    }
}
