package com.cy.driver.callout.netclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommFunc {

    //生成规定上下限的整型随机数
    public static int getRandom()
    {
        double upLimit = 100000;
        double downLimit = 999999;

        return (int) (Math.random() * (upLimit - downLimit) + downLimit);
    }

    public static String getTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
