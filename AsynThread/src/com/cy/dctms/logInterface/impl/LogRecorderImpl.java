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
	
	private List<Thread> recordLogThreadPool;			//线程池
	private String batchOpNum = "50";					//可配置 批处理插入日志条数
	private String threadPoolSize = "2";				//线程池大小

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
			Thread recodrThread = new RecordLogThread("记录用户行为日志线程");
			recodrThread.start();
			recordLogThreadPool.add(recodrThread);
		}

		if(log.isInfoEnabled()) {
			log.info("记录用户行为日志线程...启动..." + batchOpNum + "," + threadPoolSize);
		}
	}

	@Override
	public void stopBehindthread() {
		recordLogThreadRun = false;
		synchronized (reqLogs) {
			reqLogs.notifyAll();
		}
	}

	//批量保存日志对象
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
		log.debug("批量保存用户操作行为日志结束！");

		if (log.isDebugEnabled()) {
			log.info(new StringBuilder("系统中还有\t").append(reqLogs.size())
					.append("\t条用户行为日志未记录...").toString());
		}
	}
	//
	private void saveAllReqLog() {
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:spring-application-*.xml");
		Object obj = factory.getBean("operationLogService");
		operationLogService = (OperationLogServiceImpl) obj;
		
		int count = reqLogs.size()/2;
		if (log.isDebugEnabled()) {
			log.debug(new StringBuilder("服务停止时，系统中还有\t").append(count)
					.append("\t条用户行为日志未记录...").toString());
			log.debug("开始保存所有未记录的用户行为日志......");
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

	//内部日志记录线程类
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
			log.debug("系统准备退出，保存内存中剩余的用户操作行为日志！");
			saveAllReqLog();
		}

		RecordLogThread(String name) {
			super(name);
		}
	}	
}
