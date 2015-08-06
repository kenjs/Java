package com.cy.dctms.function.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cy.dctms.common.domain.FunctionInfoDomain;
import com.cy.dctms.function.dao.IFunctionInfoDao;
import com.cy.dctms.function.service.IFunctionInfoService;

public class FunctionInfoServiceImp implements IFunctionInfoService {

	private IFunctionInfoDao functionInfoDao;

	@Override
	public FunctionInfoDomain queryFunciontInfo(FunctionInfoDomain functionInfoDomain,String userId) {
		List<FunctionInfoDomain> dataList = functionInfoDao.queryFunctionInfo(userId);
				String grade = "20";
				boolean changeGradeFlag = true;
				StringBuffer sBuffer = new StringBuffer();
				for (FunctionInfoDomain domain : dataList) {
					if (Integer.valueOf(domain.getId())>Integer.valueOf(grade)) {
						changeGradeFlag = true;
						grade = String.valueOf((Integer.valueOf(domain.getId().substring(0, 1))+1)*10);
						sBuffer.append("</div>");
						sBuffer.append("</div>");
					}
					if(changeGradeFlag){
						changeGradeFlag= false;
						sBuffer.append("<div>");
						sBuffer.append("<h4><a href=\"#\" onclick='visibility("+domain.getParentId()+")'>");
						sBuffer.append(domain.getParentName());
						sBuffer.append("</a></h4>");
						sBuffer.append("<div name='functionName' id='"+domain.getParentId()+"'>");
						sBuffer.append("<ul>");
						sBuffer.append("<li>");
						sBuffer.append("<a href=\"#\"  onclick='navigateMenu(\""+domain.getUrl()+"\",\""+domain.getName()+"\")'>");
						sBuffer.append(domain.getName());
						sBuffer.append("</a>");
						sBuffer.append("</li>");
						sBuffer.append("</ul>");
					}else {
						sBuffer.append("<ul>");
						sBuffer.append("<li>");
						sBuffer.append("<a href=\"#\"  onclick='navigateMenu(\""+domain.getUrl()+"\",\""+domain.getName()+"\")'>");
						sBuffer.append(domain.getName());
						sBuffer.append("</a>");
						sBuffer.append("</li>");
						sBuffer.append("</ul>");
					}
				}
				sBuffer.append("</div>");
			functionInfoDomain.setName(sBuffer.toString());
			return functionInfoDomain;
	}
	@Override
	public List<FunctionInfoDomain> queryFunctionInfoList(FunctionInfoDomain functionInfoDomain) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("pageSize", functionInfoDomain.getPageInfo().getPageSize());
			queryMap.put("start", (functionInfoDomain.getPageInfo().getCurPage()-1)*functionInfoDomain.getPageInfo().getPageSize());
			functionInfoDomain.getPageInfo().setTotalRecords(functionInfoDao.queryFunctionInfoCount(queryMap));
			return functionInfoDao.queryFunctionInfoByPage(queryMap);
	}
	@Override
	public FunctionInfoDomain queryFunctionInfoMxById(String id) {
		 return functionInfoDao.queryFunctionInfoById(id);
	}
	@Override
	public void saveFunctionInfo(FunctionInfoDomain functionInfoDomain) {
		functionInfoDao.saveFunctionInfo(functionInfoDomain);
	}
	@Override
	public void deleteFunctionInfo(String id) {
		functionInfoDao.deleteFunctionInfo(id);
	}
	public void setFunctionInfoDao(IFunctionInfoDao functionInfoDao) {
		this.functionInfoDao = functionInfoDao;
	}
	
}
