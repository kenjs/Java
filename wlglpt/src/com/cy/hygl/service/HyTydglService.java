package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货运-托运单管理.
 * 
 * @author HJH
 */

public interface HyTydglService extends BaseBusinessService {

	/**
	 * 刷新货物列表
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void refreshHwList(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 初始化货物维护页面
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 调用托单保存后续处理存储过程（保存未维护的发货、收货地址等信息，处理未发货信息和库存、结算等表）
	 * @param ddDjxh
	 * @param xh
	 * @throws Exception
	 */
	public void callPHyglDdglTydglHxcl(Long ddDjxh, String xh) throws Exception;
	
	/**
	 * 保存货物信息
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 删除货物信息
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void deleteHwMx(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 初始化托单复制页面
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initCopyMx(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * 检索托单复制页面
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryCopy(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * 根据选择的用来复制的托单初始化当前托单
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydFromCopy(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	/**
	 * 根据选择的用来复制的模板初始化当前托单
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void initTydFromTemplate(BaseBusinessDomain domain, UserDomain userDomain)throws Exception;
	
	public void queryYpchwNumByDdDjxh(BaseBusinessDomain domain, UserDomain userDomain) throws Exception;
	/**
	 * 校验订单编号是否重复
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void checkDdbh(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain)throws Exception;
	
	public void printView(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}