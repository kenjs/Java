package com.cy.dctms.logInterface;

import java.util.List;

public interface BehindThreadProvider {

	List<Thread> getBehindThread();  //�ṩ�̳߳�
    void startBehindThread();
    void stopBehindthread();

}
