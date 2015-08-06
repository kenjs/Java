package com.cy.dcts.webUser.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.dcts.common.action.BaseAjaxAction;
import com.cy.dcts.common.domain.DataDictInfoDomain;
import com.cy.dcts.common.domain.DriverUserAssessInfoDomain;
import com.cy.dcts.common.domain.PageInfo;
import com.cy.dcts.common.domain.UserDriverAssessInfoDomain;
import com.cy.dcts.webUser.service.IEvaluationManagementService;

/**
 * 查询货主给司机的评价
 * @author haoyong
 *
 */
public class LoadUserDriverEvaluationAction extends BaseAjaxAction{

	private static final long serialVersionUID = -6728785782055746081L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private IEvaluationManagementService evaluationManagementService;

	private DataDictInfoDomain dataDictInfoDomain=new DataDictInfoDomain();
	private UserDriverAssessInfoDomain userDriverAssessInfoDomain;
	private DriverUserAssessInfoDomain driverUserAssessInfoDomain=new DriverUserAssessInfoDomain();
	private String blockDiv="2";//控制显示的层
	private String menuAId;//控制左边菜单的样式
	private String flag="";// 区分点击的是"0"搜索按钮（初始化从第一条开始查）还是"1"分页链接
	@Override
	protected String execMethod() throws Exception {
		//判断是否登陆
		if(this.getSessionUser()==null){
			return LOGIN;
		}
		logger.debug("query user driver  assess info begin. userId=[{}], companyId=[{}]",
		getSessionUser().getId(), getSessionUser().getCompanyId());
				
		//对象初始化
		if(userDriverAssessInfoDomain==null){
			userDriverAssessInfoDomain=new UserDriverAssessInfoDomain();
		}
		if(userDriverAssessInfoDomain.getPageInfo()==null||"0".equals(flag)){//0是点击搜索按钮
			userDriverAssessInfoDomain.setPageInfo(new PageInfo());
		}
		
		try {
			List<UserDriverAssessInfoDomain> list = evaluationManagementService.queryUserDriverAssessList(userDriverAssessInfoDomain, getSessionUser().getId());
			userDriverAssessInfoDomain.setList(list);
			
			//根据货主Id（userId）查询司机对货主评价数(好评数，中评数，差评数)
			DriverUserAssessInfoDomain driverUserAsDom=evaluationManagementService.queryDriverUserAssessCountByAssessScore(getSessionUser().getId());
			if(driverUserAsDom!=null){
				//值为null或“”时赋值为“0”
				if(StringUtils.isEmpty(driverUserAsDom.getSatisfactory())){
					driverUserAssessInfoDomain.setSatisfactory("0");
				}else{
					driverUserAssessInfoDomain.setSatisfactory(driverUserAsDom.getSatisfactory());
				}
				
				if(StringUtils.isEmpty(driverUserAsDom.getArial())){
					driverUserAssessInfoDomain.setArial("0");
				}else{
					driverUserAssessInfoDomain.setArial(driverUserAsDom.getArial());
				}
                if(StringUtils.isEmpty(driverUserAsDom.getNoSatisfactory())){
                	driverUserAssessInfoDomain.setNoSatisfactory("0");
				}else{
					driverUserAssessInfoDomain.setNoSatisfactory(driverUserAsDom.getNoSatisfactory());
				}
			}
			
			//页面根据blockDiv的值去调用分页的js
			driverUserAssessInfoDomain.setPageInfo(new PageInfo());
			logger.info("query User Driver Evaluation Success!  List.size()=[{}]",new Object[]{list.size()});
			return SUCCESS;
		} catch (Exception e) {
			logger.error("query User Driver Evaluation Happens Error", e.getMessage());
			throw new RuntimeException();
		}
	}

	public void setEvaluationManagementService(
			IEvaluationManagementService evaluationManagementService) {
		this.evaluationManagementService = evaluationManagementService;
	}

	public UserDriverAssessInfoDomain getUserDriverAssessInfoDomain() {
		return userDriverAssessInfoDomain;
	}

	public void setUserDriverAssessInfoDomain(
			UserDriverAssessInfoDomain userDriverAssessInfoDomain) {
		this.userDriverAssessInfoDomain = userDriverAssessInfoDomain;
	}

	public DataDictInfoDomain getDataDictInfoDomain() {
		return dataDictInfoDomain;
	}

	public void setDataDictInfoDomain(DataDictInfoDomain dataDictInfoDomain) {
		this.dataDictInfoDomain = dataDictInfoDomain;
	}

	public String getBlockDiv() {
		return blockDiv;
	}

	public void setBlockDiv(String blockDiv) {
		this.blockDiv = blockDiv;
	}

	public DriverUserAssessInfoDomain getDriverUserAssessInfoDomain() {
		return driverUserAssessInfoDomain;
	}

	public void setDriverUserAssessInfoDomain(
			DriverUserAssessInfoDomain driverUserAssessInfoDomain) {
		this.driverUserAssessInfoDomain = driverUserAssessInfoDomain;
	}

	public String getMenuAId() {
		return menuAId;
	}

	public void setMenuAId(String menuAId) {
		this.menuAId = menuAId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


}
