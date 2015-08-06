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
		//记录用户操作日志线程
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
		        log.error("服务器停止时,主线程休眠等待子线程结束时出错：", e);
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
                        log.debug("当前等待结束的后台线程为：" + t.getName());
                    }
                    t.join();
                    if (log.isDebugEnabled()) {
                        log.debug(t.getName() + "线程执行完毕！");
                    }
                }
                allowExit = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("线程链在等待前一线程结束时出错：", e);
            } catch (Exception e) {
                log.error("子线程结束状态监听线程运行出错：", e);
            }
        }
    }
}
