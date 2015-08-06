package com.cy.driver.service.impl;

import com.cy.driver.callout.netclient.CalloutClient;
import com.cy.driver.service.CallService;
import org.springframework.stereotype.Service;

/**
 * Created by haoy on 2015/3/9.
 */
@Service("callService")
public class CallServiceImpl implements CallService {

    @Override
    public void call(String caller, String called) throws Exception{
        new CalloutClient("218.75.14.140", 3362).run();
    }
}
