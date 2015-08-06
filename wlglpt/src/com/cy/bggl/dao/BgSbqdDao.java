package com.cy.bggl.dao;

import java.util.List;

import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.domain.BgQdqtDomain;
import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.UserDomain;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR 办公签到签退 上班签到
 * @author 闫伟
 * @date 2013.1.22
*/ 

public interface  BgSbqdDao extends ExtendDao {
	
	/**
	 * 获取应该上班时间
	 * @return
	 * @throws Exception
	 */
	public  List<BgGzsjDomain> getSbSj(String bm)throws Exception;
	
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
	 * 获取应该上班时间
	 * @return
	 * @throws Exception
	 */
	public List<BgQdqtDomain> getSjQdByCzy(String czy) throws Exception ;
}
