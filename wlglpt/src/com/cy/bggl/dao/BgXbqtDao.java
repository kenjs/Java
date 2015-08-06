package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公签到签退 下班签退
 * @author 闫伟
 * @date 2013.1.22
*/ 

public interface BgXbqtDao extends ExtendDao {
	
	/**
	 * 获取应该下班班时间
	 * @return
	 * @throws Exception
	 */
	public List<BgGzsjDomain> getXbSj(String bm)throws Exception;
	
	/**
	 * 查询所有列表
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> selectAll(BaseBusinessDomain baseBusinessDomain,UserDomain user)throws Exception;
	
	/**
	 * 下载
	 * @return
	 * @throws Exception
	 */
	public List<BaseBusinessDomain> downloadList(BaseBusinessDomain baseBusinessDomain,UserDomain user) throws Exception ;
	
	/**
	 * 获取应该下班时间
	 * @return
	 * @throws Exception
	 */
	public List<BgQdqtDomain> getSjXdByCzy(String czy) throws Exception ;
}
