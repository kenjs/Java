package com.cy.dctms.logInterface;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.cy.dctms.bo.OperationLogInfoBo;

public interface LogRecorder {

	//请求日志集合
    ConcurrentLinkedQueue<OperationLogInfoBo> reqLogs = new ConcurrentLinkedQueue<OperationLogInfoBo>();

    public void addReqLogToQueue(OperationLogInfoBo bo);			//把日志对象放到队列中
}
