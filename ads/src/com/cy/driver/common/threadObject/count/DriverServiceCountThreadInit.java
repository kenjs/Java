package com.cy.driver.common.threadObject.count;

import com.cy.driver.common.threadObject.BehindThreadProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by haoy on 2014/10/8.
 */
public class DriverServiceCountThreadInit implements ServletContextListener{
    private static Logger log = LoggerFactory.getLogger(DriverServiceCountThreadInit.class);

    List<Thread> behindThreads;
    private static volatile boolean allowExit = false;
    BehindThreadProvider threadProvider;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());

        Object obj = applicationContext.getBean("countRecorder");

        //记录用户操作日志线程
        threadProvider = (CountUpdateImpl) obj;
        threadProvider.startBehindThread();
        behindThreads = threadProvider.getBehindThread();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
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
