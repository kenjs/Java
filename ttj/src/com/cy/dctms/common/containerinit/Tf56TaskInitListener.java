package com.cy.dctms.common.containerinit;

import com.cy.dctms.service.Tf56ThreadProvider;
import com.cy.dctms.service.impl.Tf56TaskImpl;
import com.cy.dctms.timeTask.Tf56Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by Hao.yong on 2015/1/15.
 */
public class Tf56TaskInitListener implements ServletContextListener {

    private static Logger LOG = LoggerFactory.getLogger(Tf56TaskInitListener.class);

    List<Thread> behindThreads;
    private static volatile boolean allowExit = false;
    Tf56ThreadProvider tf56Task;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

        if (LOG.isInfoEnabled()) {
            LOG.info("tomcat启动时易配货开始......");
        }

        Tf56TaskImpl tf56Task = (Tf56TaskImpl) applicationContext.getBean("tf56ThreadProvider");
        tf56Task.startBehindThread();
        behindThreads = tf56Task.getBehindThread();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        tf56Task.stopBehindthread();

        for (Thread thread : behindThreads) {
            new WaitBehindThreadEnd(thread).start();
            try {
                while (!allowExit) {
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOG.error("服务器停止时,主线程休眠等待子线程结束时出错：", e);
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
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("当前等待结束的后台线程为：" + t.getName());
                    }
                    t.join();
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(t.getName() + "线程执行完毕！");
                    }
                }
                allowExit = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOG.error("线程链在等待前一线程结束时出错：", e);
            } catch (Exception e) {
                LOG.error("子线程结束状态监听线程运行出错：", e);
            }
        }
    }
}
