package com.cy.driver.common.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * 类ReadPropertiesFile.java的实现描述：读取资源文件的工具类
 *
 */
public class ReadPropertiesFile {

    /**
     * 读取指定路径下的资源文件
     * @param bundleName  properties文件路径
     * @param key
     * @return value
     */
    public static String getString(String bundleName,String key) {
        final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName, Locale.getDefault());
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
