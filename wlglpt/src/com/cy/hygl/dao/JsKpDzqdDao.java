package com.cy.hygl.dao;





import com.cy.common.dao.ExtendDao;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * The DAO for 开票申请.
 * 
 * @author HCM
 */
public interface JsKpDzqdDao extends ExtendDao {
	/**
	 * 获取核销记录明细
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public void initHxMx(BaseBusinessDomain baseDomain)throws Exception;
	/**
	 * 获取核销记录列表明细
	 * @param baseDomain
	 * @return
	 * @throws Exception
	 */
	public void initAddHxMx(BaseBusinessDomain baseDomain)throws Exception;
	/**
	 * 删除记录
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void deleteJsKpsqMxByKey(String kpsqmxDjxh) throws Exception;
	/**
	 * 核销，修改，删除 后续处理
	 * @param kpsqmxDjxh
	 * @throws Exception
	 */
	public void callPHyglJsglYkphx(String kpsqmxDjxh) throws Exception;
}
