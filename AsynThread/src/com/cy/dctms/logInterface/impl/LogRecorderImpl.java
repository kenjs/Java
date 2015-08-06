package com.cy.dctms.logInterface.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cy.dctms.bo.OperationLogInfoBo;
import com.cy.dctms.logInterface.BehindThreadProvider;
import com.cy.dctms.logInterface.LogRecorder;
import com.cy.dctms.logInterface.OperationLogService;

public class LogRecorderImpl implements BehindThreadProvider, LogRecorder {

private static final Logger log = LoggerFactory.getLogger(LogRecorderImpl.class);
	
	private static volatile boolean recordLogThreadRun = true;
	
	private OperationLogService operationLogService;
	
	private List<Thread> recordLogThreadPool;			//�̳߳�
	private String batchOpNum = "50";					//������ �����������־����
	private String threadPoolSize = "2";				//�̳߳ش�С

	@Override
	public void addReqLogToQueue(OperationLogInfoBo logBo) {
		LogRecorder.reqLogs.add(logBo);
		synchronized(reqLogs) {
			reqLogs.notifyAll();
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
		for(int i = 0; i < size; i ++) {
			Thread recodrThread = new RecordLogThread("��¼�û���Ϊ��־�߳�");
			recodrThread.start();
			recordLogThreadPool.add(recodrThread);
		}

		if(log.isInfoEnabled()) {
			log.info("��¼�û���Ϊ��־�߳�...����..." + batchOpNum + "," + threadPoolSize);
		}
	}

	@Override
	public void stopBehindthread() {
		recordLogThreadRun = false;
		synchronized (reqLogs) {
			reqLogs.notifyAll();
		}
	}

	//����������־����
	private void batchSaveReqLog() {
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:spring-application-*.xml");
		Object obj = factory.getBean("operationLogService");
		operationLogService = (OperationLogService) obj;
		
		for (int i = 0; i < Integer.parseInt(batchOpNum); i++) {
			OperationLogInfoBo model = reqLogs.poll();
			try {
				operationLogService.insertOperationLog(model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.debug("���������û�������Ϊ��־������");

		if (log.isDebugEnabled()) {
			log.info(new StringBuilder("ϵͳ�л���\t").append(reqLogs.size())
					.append("\t���û���Ϊ��־δ��¼...").toString());
		}
	}
	//
	private void saveAllReqLog() {
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:spring-application-*.xml");
		Object obj = factory.getBean("operationLogService");
		operationLogService = (OperationLogServiceImpl) obj;
		
		int count = reqLogs.size()/2;
		if (log.isDebugEnabled()) {
			log.debug(new StringBuilder("����ֹͣʱ��ϵͳ�л���\t").append(count)
					.append("\t���û���Ϊ��־δ��¼...").toString());
			log.debug("��ʼ��������δ��¼���û���Ϊ��־......");
		}
		for (int i = 0; i < count; i++) {
			OperationLogInfoBo model = reqLogs.poll();
			try {
				operationLogService.insertOperationLog(model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getBatchOpNum() {
		return batchOpNum;
	}

	public void setBatchOpNum(String batchOpNum) {
		this.batchOpNum = batchOpNum;
	}

	public String getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(String threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	//�ڲ���־��¼�߳���
	class RecordLogThread extends Thread {
		@Override
		public void run() {
			while (recordLogThreadRun) {
				try {
					if (Integer.parseInt(batchOpNum) > reqLogs.size()) {
						synchronized (reqLogs) {
							reqLogs.wait();
						}
					} else {
						batchSaveReqLog();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			log.debug("ϵͳ׼���˳��������ڴ���ʣ����û�������Ϊ��־��");
			saveAllReqLog();
		}

		RecordLogThread(String name) {
			super(name);
		}
	}	
}
