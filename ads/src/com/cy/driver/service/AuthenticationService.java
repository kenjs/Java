package com.cy.driver.service;

import java.util.Set;

/**
 * Created by haoy on 2015/1/29.
 */
public interface AuthenticationService {

    public Set getRefer();

    public void addRefer(int i);

    public void removeRefer(int i);

    public boolean isOk(int driverId);
}
