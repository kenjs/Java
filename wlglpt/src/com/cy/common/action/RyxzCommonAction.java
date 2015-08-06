package com.cy.common.action;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.common.domain.RyxzCommonDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.service.RyxzCommonService;

/**
 * 
* @Descriptoin 物流管理平台公共业务
* @Note
* @author admin
* @since 2013-01-23 下午14:11:39 
* @version
 */
@Controller
@Scope("prototype")
@Action(value = "/common/ryxzCommon", results = {
		@Result(name = "ryInit", location = "/work/common/ryxz_common.jsp"),
		@Result(name = "buildTreeStr", type = "json")
})
public class RyxzCommonAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	private RyxzCommonDomain domain;

	// 获取人员
	public String ryxzCommonInit() throws Exception {
		return "ryInit";	
	}
	
	// 获取人员树
	public String buildTreeStr() throws Exception {
		//转化为自己的SERVICE
		RyxzCommonService service = (RyxzCommonService)getService();
		service.queryRyList(getDomain(),this.getUserDomain());
		
		return "buildTreeStr";	
	}
	
	@Resource(name = "ryxzCommonServiceImp")	
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public RyxzCommonDomain getDomain() {
		if(domain==null){
			domain = new RyxzCommonDomain();
		}
		return domain;
	}

	public void setDomain(RyxzCommonDomain domain) {
		this.domain = domain;
	}

}
