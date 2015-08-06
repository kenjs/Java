package com.cy.driver.common.threadObject.count;

import com.cy.driver.common.threadObject.BehindThreadProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoy on 2014/10/8.
 */
@Service
public class CountUpdateImpl implements CountRecord,BehindThreadProvider {

    private static final Logger log = LoggerFactory.getLogger(CountUpdateImpl.class);

    private static volatile boolean recordLogThreadRun = true;

    private final byte[] lock = new byte[0];

    @Resource
    private CountService countService;

    private List<Thread> countThreadPool;			//线程池
    private int batchCountNum;					    //可配置 批处理插入日志条数
    private int threadPoolSize;						//线程池大小

    @Override
    public List<Thread> getBehindThread() {
        return countThreadPool;
    }

    @Override
    public void startBehindThread() {
        countThreadPool = new ArrayList<Thread>(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            Thread thread = new CountThread("统计系统业务事件点");
            thread.start();
            countThreadPool.add(thread);
        }

        if (log.isDebugEnabled()) {
            log.debug("统计系统业务事件点线程启动..." + "线程池大小为：" + threadPoolSize);
        }
    }

    @Override
    public void stopBehindthread() {
        recordLogThreadRun = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public void addCountToQueue(Count count) {
        CountRecord.counts.add(count);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private void batchSaveCountObj(List<Count> list) {

        try {
            countService.updateCount(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("业务事件点批量保存结束！");


    }

    private void saveAllCountObj() {

        try {
            log.info("保存内存中剩余的事件点！");
            List<Count> list = new ArrayList<Count>();
            while (!counts.isEmpty()) {
                list.add(counts.poll());
            }
            countService.updateCount(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("所有未记录的业务事件点保存结束.");
    }

    public int getBatchCountNum() {
        return batchCountNum;
    }

    @Value("#{propertiesReader['dctms.batchCountNum']}")
    public void setBatchCountNum(int batchCountNum) {
        this.batchCountNum = batchCountNum;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    @Value("#{propertiesReader['dctms.threadPoolSize']}")
    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    //统计系统业务事件点线程类
    class CountThread extends Thread {
        @Override
        public void run() {
            while (recordLogThreadRun) {
                try {
                    List<Count> list = new ArrayList<Count>();
                    long thisBatchStart = System.currentTimeMillis();
                    while (!counts.isEmpty()) {
                        list.add(counts.poll());
                        if (batchCountNum < list.size() || (System.currentTimeMillis() - thisBatchStart) / 1000 > 3) {//达到批次值或已取值超过3秒
                            break;
                        }
                    }
                    batchSaveCountObj(list);
                    if (recordLogThreadRun) {
                        if (counts.isEmpty()) {
                            synchronized (lock) {
                                log.info("当前日志缓存池已无数据，日志记录线程进入休眠！");
                                lock.wait();
                            }
                        } else {
                            Thread.sleep(6000);
                            log.debug("休眠6秒！");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.info("系统准备退出!");
            saveAllCountObj();
        }

        CountThread(String name) {
            super(name);
        }
    }
}
