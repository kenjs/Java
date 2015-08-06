package com.cy.dctms.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.logInterface.BehindThreadProvider;

public class DctmsSysLogRecordInitListener implements ServletContextListener {
	
	private static Logger log = LoggerFactory.getLogger(DctmsSysLogRecordInitListener.class);
	
	List<Thread> behindThreads;
    private static volatile boolean allowExit = false;
    BehindThreadProvider threadProvider;

    @Override
	public void contextInitialized(ServletContextEvent arg0) {
		//��¼�û�������־�߳�
        //threadProvider = new LogRecorderImpl();
    	
        threadProvider.startBehindThread();
        behindThreads = threadProvider.getBehindThread();
	}
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		for (Thread thread : behindThreads) {
			new WaitBehindThreadEnd(thread).start();
		
		    threadProvider.stopBehindthread();
		    try {
		        while (!allowExit) {
		            Thread.sleep(5000);
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		        log.error("������ֹͣʱ,���߳����ߵȴ����߳̽���ʱ����", e);
		    }
		}
	}
	
	public void setThreadProvider(BehindThreadProvider threadProvider) {
		this.threadProvider = threadProvider;
	}

	static class WaitBehindThreadEnd extends Thread {
        private final Thread t;

        WaitBehindThreadEnd(Thread behindThreads) {
            this.t = behindThreads;
        }

        @Override
        public void run() {
            try {
                if (t != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("��ǰ�ȴ������ĺ�̨�߳�Ϊ��" + t.getName());
                    }
                    t.join();
                    if (log.isDebugEnabled()) {
                        log.debug(t.getName() + "�߳�ִ����ϣ�");
                    }
                }
                allowExit = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("�߳����ڵȴ�ǰһ�߳̽���ʱ����", e);
            } catch (Exception e) {
                log.error("���߳̽���״̬�����߳����г���", e);
            }
        }
    }
}
