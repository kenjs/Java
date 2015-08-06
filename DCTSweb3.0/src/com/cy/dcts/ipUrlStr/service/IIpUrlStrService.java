package com.cy.dcts.ipUrlStr.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface IIpUrlStrService {

	
	/**
	 * 获取访问者IP
	 * 
	 * @author nxj
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request);
	
	
	/**
	 * 根据ip获取地区
	 * 
	 * 返回地址 格式：浙江省-杭州市-西湖区
	 * 
	 * @author nxj
	 * @param content ip "218.75.43.198"
	 * @param encodingString 编码"utf-8"
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAddresses(String content, String encodingString) throws UnsupportedEncodingException;
	
	/**
	 * 获取地区
	 * 
	 * @author nxj
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAddresses(HttpServletRequest request) throws UnsupportedEncodingException; 

	
	/**
	 * 获取省
	 * @param request
	 * @return
	 */
	public String getIpProvince(HttpServletRequest request)throws UnsupportedEncodingException ;
	
	/**
	 * 获取市
	 * @param request
	 * @return
	 */
	public String getIpCity(HttpServletRequest request)throws UnsupportedEncodingException ;
	
	
	/**
	 * 获取省-市
	 * @param request
	 * @return
	 */
	public String getIpProvinceCity(HttpServletRequest request)throws UnsupportedEncodingException ;
}
