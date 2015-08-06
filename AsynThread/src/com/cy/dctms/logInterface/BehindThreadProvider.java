package com.cy.dctms.logInterface;

import java.util.List;

public interface BehindThreadProvider {

	List<Thread> getBehindThread();  //提供线程池
    void startBehindThread();
    void stopBehindthread();

}
