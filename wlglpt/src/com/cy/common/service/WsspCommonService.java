package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.service.BaseService;

public interface WsspCommonService extends BaseService {
	/**
	 * 文书退回
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void saveBack(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 文书发送
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void send(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查询文书审批流转list
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWssplzList(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查看审批意见
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void querySpyj(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 取文书审批模式dm
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryWsspms(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 初始化发送信息
	 * @param userDomain
	 * @param domain
	 * @throws Exception
	 */
	public void initSendXX(UserDomain userDomain, WsspCommonDomain domain) throws Exception;
	/**
	 * 批量发送，发送时先判断是否允许终审，允许终审就终审，否则发送
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void plSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 初始化首次发送信息
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void initScSendXx(WsspCommonDomain domain,UserDomain user) throws Exception;
	/**
	 * 首次发送
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void scSend(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 获取文书 审批流程设置序号
	 * @param domain
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String queryWssplcszxh(WsspCommonDomain domain,UserDomain user) throws Exception;
	
}
