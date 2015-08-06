package com.cy.driver.service;

/**
 * Created by haoy on 2015/3/9.
 */
public interface CallService {

    /**
     * 呼叫
     * @param caller 呼叫人号码
     * @param called 被叫人号码
     * @throws
     */
    public void call(String caller, String called) throws Exception;
}
