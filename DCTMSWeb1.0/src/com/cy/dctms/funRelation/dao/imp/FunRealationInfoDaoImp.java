package com.cy.dctms.funRelation.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cy.dctms.common.bo.FunRealationInfo;
import com.cy.dctms.common.bo.ManagerWorkLogInfo;
import com.cy.dctms.common.dao.BaseDao;
import com.cy.dctms.common.domain.FunRealationInfoDomain;
import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.funRelation.dao.IFunRealationInfoDao;

public class FunRealationInfoDaoImp extends BaseDao implements IFunRealationInfoDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<FunRealationInfoDomain> queryFunRealationInfoByPage(FunRealationInfoDomain funRealationInfoDomain,String userId) {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if ("1".equals(userId)) {
				return (List<FunRealationInfoDomain>) queryForList("query_funRelation_info_by_page_id_is_1",funRealationInfoDomain.getManagerId());
			}
			queryMap.put("managerId", funRealationInfoDomain.getManagerId());
			queryMap.put("userId", userId);
			return (List<FunRealationInfoDomain>) queryForList("query_funRelation_info_by_page",queryMap);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	@Override
	public void saveFunRealationInfo(FunRealationInfoDomain funRealationInfoDomain,String userId) {
		try {
			deleteObject("delete_funRelation_info_by_id", funRealationInfoDomain.getManagerId());
			FunRealationInfo bo = new FunRealationInfo();
			bo.setManagerId(Long.valueOf(funRealationInfoDomain.getManagerId()));
			String content = "";
			String functionName = "";
			for (String str : funRealationInfoDomain.getFunIdList()) {
				bo.setFunctionId(Long.valueOf(str));
				Long id =addObject("add_funRelation_info", bo);
				FunctionInfoDomain functionInfoDomain =(FunctionInfoDomain) queryForObject("query_function_info_by_id", str);
				functionName = functionInfoDomain.getName();
				content += functionName+";";
			}
			ManagerWorkLogInfo MWLIbo = new ManagerWorkLogInfo();
		 	MWLIbo.setName("赋权管理员信息");
		 	MWLIbo.setContent(content);
		 	MWLIbo.setManagerId(Long.valueOf(userId));
		 	MWLIbo.setTableName("t_manager_function_relation_info");
			addObject("add_workLog_info", MWLIbo);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
