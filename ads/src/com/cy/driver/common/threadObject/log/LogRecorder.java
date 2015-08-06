package com.cy.driver.common.threadObject.log;

import java.util.concurrent.ConcurrentLinkedQueue;

public interface LogRecorder {
	   //请求日志集合
    final ConcurrentLinkedQueue<LogBo> reqLogs = new ConcurrentLinkedQueue<LogBo>();

    public void addReqLogToQueue(LogBo log);			//把日志对象放到队列中
}
