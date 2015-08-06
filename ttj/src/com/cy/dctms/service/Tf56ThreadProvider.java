package com.cy.dctms.service;

import java.util.List;

/**
 * Created by haoy on 2015/1/19.
 */
public interface Tf56ThreadProvider {
    List<Thread> getBehindThread();
    void startBehindThread();
    void stopBehindthread();
}
