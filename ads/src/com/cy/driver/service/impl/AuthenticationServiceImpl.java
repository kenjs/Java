package com.cy.driver.service.impl;

import com.cy.driver.domain.Authentication;
import com.cy.driver.service.AuthenticationService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.Set;

/**
 * Created by haoy on 2015/1/29.
 */
@Scope("singleton")
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Authentication authen = Authentication.newInstance();

    @Override
    public Set getRefer() {
        SoftReference<Set> softReference = authen.getSoftReference();
        return softReference.get();
    }

    @Override
    public void addRefer(int i) {
        Set list = getRefer();
        list.add(i);
    }

    @Override
    public void removeRefer(int i) {
        Set list = getRefer();
        if (list.contains(i)) {
            list.remove(i);
        }
    }

    @Override
    public boolean isOk(int driverId) {
        Set list = getRefer();
        if (list.contains(driverId)) {
            return true;
        }
        return false;
    }
}
