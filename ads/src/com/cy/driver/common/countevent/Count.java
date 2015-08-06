package com.cy.driver.common.countevent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by haoy on 2014/9/25.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Count {
    String[] tableNames();
    String[] columns();
    int[] operaType() default {0};
    String description() default "系统业务统计";
}
