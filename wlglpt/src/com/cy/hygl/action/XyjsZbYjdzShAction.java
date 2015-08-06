package com.cy.hygl.action;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.XyjsZbYjdzShDomain;
import com.cy.hygl.service.XyjsZbYjdzShService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;


/**
 * THE ACTION FOR 财务-月结对账
 * @author hcm
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/xyjszbyjdzsh", results = {
		@Result(name = "init", location = "/work/hygl/xyjszbyjdzsh.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "deleteMx", type = "json"),
		@Result(name = "checkXmfl", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/xyjszbyjdz_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class XyjsZbYjdzShAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsZbYjdzShDomain domain = (XyjsZbYjdzShDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("财务登记序号(SEQ_CW_DJXH)");
		headList.add("所属机构(总公司)");
		headList.add("费用类别_财务登记序号");
		headList.add("费用项目名称");
		headList.add("审批流程_项目分类登记序号");
		headList.add("有效标志(Y/N)");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsZbYjdzShDomain element = (XyjsZbYjdzShDomain) e;
			List list = new ArrayList();
			
/*			list.add(i+++ "");
			list.add(element.getCwDjxh());
			list.add(element.getSsJgbm());
			list.add(element.getFylbCwDjxh());
			list.add(element.getFyxmMc());
			list.add(element.getSplcXmflDjxh());
			list.add(element.getYxbz());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
*/
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}

	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsZbYjdzShDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsZbYjdzShDomain) domain;
	}

	@Resource(name = "xyjsZbYjdzShServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
