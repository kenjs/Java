package com.cy.common.service.imp;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

public class BaseBusinessServiceImp implements BaseBusinessService {

	
	public void init(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyInitBefore(baseBusinessDomain, userDomain);

		doCommonInit(baseBusinessDomain, userDomain);
		doMyInit(baseBusinessDomain, userDomain);

		doMyInitAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {
		
	}

	protected void doMyInitBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyInit(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyInitAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	
	public void initMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyInitMxBefore(baseBusinessDomain, userDomain);

		doCommonInitMx(baseBusinessDomain, userDomain);
		doMyInitMx(baseBusinessDomain, userDomain);

		doMyInitMxAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyInitMxBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyInitMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyInitMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	
	public void query(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyQueryBefore(baseBusinessDomain, userDomain);

		doCommonQuery(baseBusinessDomain, userDomain);
		doMyQuery(baseBusinessDomain, userDomain);

		doMyQueryAfter(baseBusinessDomain, userDomain);

	}
	
	public void download(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
		doMyDownloadBefore(baseBusinessDomain, userDomain);
		
		doCommonDownload(baseBusinessDomain, userDomain);
		doMyDownload(baseBusinessDomain, userDomain);
		
		doMyDownloadAfter(baseBusinessDomain, userDomain);
		
	}
	
	private void doCommonDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyDownloadBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDownload(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDownloadAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {}


	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyQueryBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyQuery(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyQueryAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	
	public void queryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyQueryMxBefore(baseBusinessDomain, userDomain);

		doCommonQueryMx(baseBusinessDomain, userDomain);
		doMyQueryMx(baseBusinessDomain, userDomain);

		doMyQueryMxAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyQueryMxBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyQueryMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyQueryMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMySaveBefore(baseBusinessDomain, userDomain);

		doCommonSave(baseBusinessDomain, userDomain);
		doMySave(baseBusinessDomain, userDomain);

		doMySaveAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonSave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	/**
	 * 保存之前调用，此处可以加入校验代码
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	protected void doMySaveBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMySave(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMySaveAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMySaveMxBefore(baseBusinessDomain, userDomain);

		doCommonSaveMx(baseBusinessDomain, userDomain);
		doMySaveMx(baseBusinessDomain, userDomain);

		doMySaveMxAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonSaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMySaveMxBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMySaveMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMySaveMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyDeleteBefore(baseBusinessDomain, userDomain);

		doCommonDelete(baseBusinessDomain, userDomain);
		doMyDelete(baseBusinessDomain, userDomain);

		doMyDeleteAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	/**
	 * 删除之前调用，此处可以加入校验代码
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	protected void doMyDeleteBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDelete(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDeleteAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyDeleteMxBefore(baseBusinessDomain, userDomain);

		doCommonDeleteMx(baseBusinessDomain, userDomain);
		doMyDeleteMx(baseBusinessDomain, userDomain);

		doMyDeleteMxAfter(baseBusinessDomain, userDomain);
	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyDeleteMxBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDeleteMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyDeleteMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	
	public void print(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyPrintBefore(baseBusinessDomain, userDomain);

		doCommonPrint(baseBusinessDomain, userDomain);
		doMyPrint(baseBusinessDomain, userDomain);

		doMyPrintAfter(baseBusinessDomain, userDomain);

	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonPrint(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyPrintBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyPrint(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyPrintAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}

	
	public void printMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

		doMyPrintMxBefore(baseBusinessDomain, userDomain);

		doCommonPrintMx(baseBusinessDomain, userDomain);
		doMyPrintMx(baseBusinessDomain, userDomain);

		doMyPrintMxAfter(baseBusinessDomain, userDomain);

	}
	
	public void saveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		doMySaveEnable(baseBusinessDomain, userDomain);
	}
	
	public void saveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		doMySaveDisable(baseBusinessDomain, userDomain);
	}

	/**
	 * 初始化公共处理方法
	 * 
	 * @param baseBusinessDomain
	 * @param userDomain
	 */
	private void doCommonPrintMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) {

	}

	protected void doMyPrintMxBefore(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyPrintMx(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
	}

	protected void doMyPrintMxAfter(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {

	}
	
	protected void doMySaveEnable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}
	
	protected void doMySaveDisable(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception {
		
	}

}
