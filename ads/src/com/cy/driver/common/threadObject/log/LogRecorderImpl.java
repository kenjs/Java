package com.cy.driver.common.threadObject.log;

import com.cy.driver.bo.OperationLogInfoBo;
import com.cy.driver.common.threadObject.BehindThreadProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogRecorderImpl implements LogRecorder, BehindThreadProvider {

    private static final Logger log = LoggerFactory.getLogger(LogRecorderImpl.class);
    private static volatile boolean recordLogThreadRun = true;
    private final byte[] lock = new byte[0];

    @Resource
    private LogService logService;

    private List<Thread> recordLogThreadPool;            //线程池
    private int batchOpNum;                                //可配置 批处理插入日志条数
    private String threadPoolSize;                        //线程池大小

    @Override
    public void addReqLogToQueue(LogBo log) {
        LogRecorder.reqLogs.add(log);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Override
    public List<Thread> getBehindThread() {
        return recordLogThreadPool;
    }

    @Override
    public void startBehindThread() {
        recordLogThreadPool = new ArrayList<Thread>();

        int size = Integer.parseInt(threadPoolSize);
        for (int i = 0; i < size; i++) {
            Thread recodrThread = new RecordLogThread("记录用户行为日志线程");
            recodrThread.start();
            recordLogThreadPool.add(recodrThread);
        }

        if (log.isInfoEnabled()) {
            log.info("记录用户行为日志线程...启动..." + batchOpNum + "," + threadPoolSize);
        }
    }

    @Override
    public void stopBehindthread() {
        recordLogThreadRun = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    //批量保存日志对象
    private void batchSaveReqLog(List<OperationLogInfoBo> list) {
        try {
            logService.insertOperationLog(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    private void saveAllReqLog() {
        try {
            log.info("保存内存中剩余的用户操作行为日志！");
            List<OperationLogInfoBo> list = new ArrayList<OperationLogInfoBo>();
            while (!reqLogs.isEmpty()) {
                list.add(converLogBoToDomain(reqLogs.poll()));
            }
            logService.insertOperationLog(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("所有未记录的用户行为日志保存结束");
    }

    public int getBatchOpNum() {
        return batchOpNum;
    }

    @Value("#{propertiesReader['dctms.batchOpNum']}")
    public void setBatchOpNum(String batchOpNum) {
        this.batchOpNum = Integer.parseInt(batchOpNum);
    }

    public String getThreadPoolSize() {
        return threadPoolSize;
    }

    @Value("#{propertiesReader['dctms.threadPoolSize']}")
    public void setThreadPoolSize(String threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public void setLogService(LogService LogService) {
        this.logService = LogService;
    }

    //内部日志记录线程类
    class RecordLogThread extends Thread {
        @Override
        public void run() {
            while (recordLogThreadRun) {
                try {
                    List<OperationLogInfoBo> list = new ArrayList<OperationLogInfoBo>();
                    long thisBatchStart = System.currentTimeMillis();
                    while (!reqLogs.isEmpty()) {
                        list.add(converLogBoToDomain(reqLogs.poll()));
                        if (batchOpNum < list.size() || (System.currentTimeMillis() - thisBatchStart) / 1000 > 3) {//达到批次值或已取值超过3秒
                            break;
                        }
                    }
                    batchSaveReqLog(list);
                    if (recordLogThreadRun) {
                        if (reqLogs.isEmpty()) {
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
            log.info("系统准备退出！");
            saveAllReqLog();
        }

        RecordLogThread(String name) {
            super(name);
        }
    }

    private OperationLogInfoBo converLogBoToDomain(LogBo poll) {
        OperationLogInfoBo operationLogInfoBo = new OperationLogInfoBo();
        operationLogInfoBo.setType(1);
        operationLogInfoBo.setUserDriverId(poll.getUserDriverId());
        operationLogInfoBo.setOperationType(poll.getOperationType());
        operationLogInfoBo.setOperationName(poll.getOperationName());
        operationLogInfoBo.setRemark(poll.getRemark());
        operationLogInfoBo.setCreateTime(poll.getCreateTime());
        return operationLogInfoBo;
    }
}