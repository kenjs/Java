package com.cy.swp.common.springex;

/**
 * Created by Richie.Lee on 2014/9/11.
 */
public @interface NoAllowRepeat {
    boolean create() default false;

    boolean check() default false;
}
