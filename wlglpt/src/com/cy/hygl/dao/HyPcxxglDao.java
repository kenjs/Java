package com.cy.hygl.dao;

import java.util.List;

import com.cy.common.dao.ExtendDao;
import com.cy.common.domain.DmbGgDomain;
import com.cy.common.domain.UserDomain;
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.domain.HyQingDanDomain;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.zygl.domain.QyXxzjDjxxDomain;

/**
 * The SERVICEIMP for 货运-派车信息管理
 * time  2013-3-4
 * @author yw
 */

public interface HyPcxxglDao extends ExtendDao {
	
	/**
	 * 
	* @Description: 将派车时选择的货物信息保存到临时表，每个派车单对应一个临时序号
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public void saveWfhxx4Pc(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 从临时表中删除对应的未发货信息 
	* @Note
	* @author 
	* @since 2013-3-12
	* @param domain
	* @throws Exception
	 */
	public void deleteWfhxxTmp4Pc(HyPcxxglDomain domain) throws Exception;
	
	/**
	 *  删除派车货物信息里的一条记录，置yxbz='N' 并更新版本号
	 * @param pcDjxh
	 * @param wfhDjxh
	 * @param bbh
	 * @throws Exception
	 */
	public void deleteHyPcHwxxByKey(String pcDjxh, String wfhDjxh, String bbh) throws Exception;
	
	/**
	 * 
	* @Description: 将派车单对应的货物 信息备份到临时表中
	* @Note
	* @author 
	* @since 2013-3-12
	* @param domain 
	* @throws Exception
	 */
	public void bakPcHwxx2Tmp(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 检索该派车单所关联的派车——货物信息
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @return
	* @throws Exception
	 */
	public List<HyTydWfhxxDomain> queryPcHwxxByPcXh(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 保存派车信息时调用存储过程 P_HYGL_DDGL_PCXX_HXCL
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void callPHyglDdglPcxxHxcl(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 
	* @Description: 删除派车信息时调用存储过程 P_HYGL_DDGL_PCXX_DELETE
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void callPHyglDdglPcxxDelete(HyPcxxglDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 
	* @Description: 根据派车货物临时序号删除临时表数据
	* @Note
	* @author 
	* @since 2013-3-12
	* @param lsXh
	* @throws Exception
	 */
	public void deletePcHwxxTmpByLsXh(String lsXh) throws Exception;
	
	/**
	 * 
	* @Description: 删除该派车单所关联的派车——货物信息
	* @Note
	* @author 
	* @since 2013-3-8
	* @param domain
	* @throws Exception
	 */
	public void deletePcHwxxByPcXh(HyPcxxglDomain domain) throws Exception;
	
	/**
	 * 取该部门下维护的车辆数量（包括共享车辆）
	 * @param bmbm
	 * @param clsxDm
	 * @return
	 * @throws Exception
	 */
	public Integer queryQyClxxCountBySsbmbm(String bmbm, String clsxDm) throws Exception;
	
	/**
	 * 根据主键检索货运派车的运费信息
	 * @param pcDjxh
	 * @return
	 * @throws Exception
	 */
	public HyPcxxglDomain queryHyPcYfxxByKey(String pcDjxh) throws Exception;
	
	public void queryPcxxSjsInitVal(HyPcxxglDomain domain) throws Exception;
	
	public List<DmbGgDomain> queryPchwClfsdmList(String pcfsDm) throws Exception;
	
	public List<HyTydWfhxxDomain> queryWfhxx(HyPcxxglDomain domain) throws Exception;
	
	public List<HyQingDanDomain> qingdan(HyPcxxglDomain domain) throws Exception;
	//public Integer queryExistWfhxxCount(List<String> wfhDjxhs) throws Exception;
	
	public void updatePchwxxBbh(HyPcxxglDomain domain) throws Exception;
	public String queryZgs(UserDomain userDomain) throws Exception;
	/**
	 * 根据wfhDjxh 取确认的配送费
	 * @param wfhDjxh
	 * @return
	 * @throws Exception
	 */
	public Double getQrPsf(String wfhDjxh) throws Exception;
}
