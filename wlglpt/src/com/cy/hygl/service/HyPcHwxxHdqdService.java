package com.cy.hygl.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * The SERVICE for 货运-派车-货物信息-回单清单.
 * 
 * @author HJH
 */

public interface HyPcHwxxHdqdService extends BaseBusinessService {
	/**
	 * 打包发送
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void dbfs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查清单
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void doQueryQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 清单接收
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void qdjs(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 清单退回
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void qdth(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
	/**
	 * 查清单中回单
	 * @param baseBusinessDomain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryHdByQd(BaseBusinessDomain baseBusinessDomain, UserDomain userDomain) throws Exception;
}