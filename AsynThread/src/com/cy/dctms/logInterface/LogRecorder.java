package com.cy.dctms.logInterface;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.cy.dctms.bo.OperationLogInfoBo;

public interface LogRecorder {

	//������־����
    ConcurrentLinkedQueue<OperationLogInfoBo> reqLogs = new ConcurrentLinkedQueue<OperationLogInfoBo>();

    public void addReqLogToQueue(OperationLogInfoBo bo);			//����־����ŵ�������
}
