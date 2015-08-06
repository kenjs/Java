package com.cy.common.service;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptDropDownCommonDomain;

public interface WlglptDropDownCommonService extends BaseBusinessService{

	/**
	 * 行政区划autocomplete下拉数据
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryXzqhList(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * @description 行政区划输入框下拉
	 * @param domain
	 * @param userDomain
	 * @throws Exception
	 */
	public void queryXzqhInputSel(WlglptDropDownCommonDomain domain, UserDomain userDomain) throws Exception;
	
	/**
	 * 客户autocomplete下拉数据
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHykhList(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	/**
	 * 货物autocomplete下拉数据
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyhwList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * 装货地址autocomplete下拉数据
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryZhdzList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * 收货人地址autocomplete下拉数据
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyShrDzList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * 收货单位autocomplete下拉数据
	 * @param khDjxh
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryHyShdwList(String khDjxh,UserDomain userDomain) throws Exception;
	
	/**
	 * 车辆信息autocomplete下拉数据
	 * @param domain
	 * @param userDomain
	 * @return
	 * @throws Exception
	 */
	public String[] queryQyClxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public String[] queryQyGcxx(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public String[] queryZrbmThShdz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
	public void queryGsDz(WlglptDropDownCommonDomain domain,UserDomain userDomain) throws Exception;
	
}
