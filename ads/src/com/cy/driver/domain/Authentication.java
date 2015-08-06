package com.cy.driver.domain;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by haoy on 2015/1/29.
 */
public class Authentication {

    private static SoftReference<Set> softReference;

    private Authentication() {
        Set list = new HashSet();
        list.add(0);
        softReference = new SoftReference<Set>(list);
    }

    public static Authentication newInstance() {
        return new Authentication();
    }

    public SoftReference<Set> getSoftReference() {
        if (softReference == null || softReference.get() == null || softReference.get().size() == 0) {
            new Authentication();
        }
        return softReference;
    }
}
