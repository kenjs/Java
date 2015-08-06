package com.cy.dcts.common.util;

import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 获取流水号
 * @author nxj
 *
 */
public class PrimaryGenerater {

	private static final String SERIAL_NUMBER = "XXXXXX"; // 流水号格式
	
	 /**
     * 生成下一个编号
   */
   public synchronized static String generaterNextNumber(String sno) {
        String id = null;
        if (StringUtils.isEmpty(sno)) {
            id = "000001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("000000");
            id = df.format(1 + Integer.parseInt(sno));
        }
        return id;
    }
}
