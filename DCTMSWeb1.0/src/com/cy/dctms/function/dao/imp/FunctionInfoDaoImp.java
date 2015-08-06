package com.cy.dctms.function.dao.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dctms.common.bo.FunctionInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.function.dao.IFunctionInfoDao;


public class FunctionInfoDaoImp extends BaseDao implements IFunctionInfoDao{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionInfoDomain> queryFunctionInfo(String userId) {
		try {
			if ("1".equals(userId)) {
				return (List<FunctionInfoDomain>) queryForList("query_function_info_id_is_1",userId);
			}
			return (List<FunctionInfoDomain>) queryForList("query_function_info",userId);
		} catch (Exception e) {
			logger.error("query_manager_info_by_code error!",e);
			throw new RuntimeException();
		}
	}
	@Override
	public Integer queryFunctionInfoCount(Map<String,Object> map) {
		try {
			return (Integer) queryForObject("query_function_info_count",map);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionInfoDomain> queryFunctionInfoByPage(Map<String,Object> map) {
		try {
			return (List<FunctionInfoDomain>) queryForList("query_function_info_by_page",map);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public FunctionInfoDomain queryFunctionInfoById(String id) {
		try {
			return (FunctionInfoDomain) queryForObject("query_function_info_by_id", id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void saveFunctionInfo(FunctionInfoDomain functionInfoDomain) {
		try {
			FunctionInfo bo = new FunctionInfo();
			bo.setNumber(Integer.valueOf(functionInfoDomain.getNumber()));
			bo.setName(java.net.URLDecoder.decode(functionInfoDomain.getName(),"utf-8"));
			if (StringUtils.isNotBlank(functionInfoDomain.getParentNumber())) {
				bo.setParentNumber(Long.valueOf(functionInfoDomain.getParentNumber()));
			}
			bo.setUrl(java.net.URLDecoder.decode(functionInfoDomain.getUrl(),"utf-8"));
			if (!functionInfoDomain.getId().equals("0")) {
				bo.setId(Long.valueOf(functionInfoDomain.getId()));
				saveObject("modify_function_info", bo);
			}else {
				addObject("add_function_info", bo);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void deleteFunctionInfo(String id) {
		try {
			 deleteObject("delete_function_info_by_id", id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
