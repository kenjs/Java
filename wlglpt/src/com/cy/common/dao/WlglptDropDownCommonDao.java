package com.cy.common.dao;

import java.util.List;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;
import com.cy.hygl.domain.QyZrbmThShdzDomain;
import com.cy.zygl.domain.DmXzqhDomain;
import com.cy.zygl.domain.QyYlClxxDomain;

public interface WlglptDropDownCommonDao {
	
	/**
	 * @description 行政区划输入框下拉	
	 * @return
	 * @throws Exception
	 */
	public List<DmXzqhDomain> queryXzqhInputSelList() throws Exception;
	
	/**
	 * @description 取行政大区下拉
	 * @return
	 * @throws Exception
	 */
	public List<DmXzqhDomain> queryXzdqList() throws Exception;

	/**
	 * 检索发货人
	 * @param ssJgbm
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHykhListByBmbm(String bmbm) throws Exception;
	
	public List<WlglptDropDownCommonDomain> queryHykhListBySsjgbm(String ssJgbm) throws Exception;
	
	/**
	 * 检索货物名称
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyhwList(String khDjxh, String CS20001) throws Exception;
	
	/**
	 * 检索发货地址
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShdzList(String khDjxh) throws Exception;
	
	/**
	 * 检索收货地址
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShrDzList(String khDjxh) throws Exception;
	
	/**
	 * 检索收货单位
	 * @param khDjxh
	 * @return
	 * @throws Exception
	 */
	public List<WlglptDropDownCommonDomain> queryHyShdwList(String khDjxh)throws Exception;
	
	/**
	 * 车辆信息
	 * @param bmbm
	 * @return
	 * @throws Exception
	 */
	public List<QyYlClxxDomain> queryQyClxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception;
	
	public List<QyYlClxxDomain> queryQyGcxxBySsbmbm(String bmbm, QyYlClxxDomain clxxDomain) throws Exception;
	
	public List<QyZrbmThShdzDomain> queryZrbmThShdz(QyZrbmThShdzDomain domain, UserDomain userDomain) throws Exception;
}
