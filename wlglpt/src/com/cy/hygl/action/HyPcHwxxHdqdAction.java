package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.common.action.ExtendAction;
import com.cy.common.domain.UserDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.HyPcHwxxHdqdDomain;
import com.cy.hygl.service.HyPcHwxxHdqdService;

/**
 * THE ACTION FOR 货运-派车-货物信息-回单清单
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hypchwxxhdqd", results = {
		@Result(name = "init", location = "/work/hygl/hy_hdqd.jsp"),
		@Result(name = "initQd", location = "/work/hygl/hy_hdqd_js.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "queryQd", type = "json"),
		@Result(name = "queryHdByQd", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_hdqd_view.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "dbfs", type = "json"),
		@Result(name = "qdjs", type = "json"),
		@Result(name = "qdth", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyPcHwxxHdqdAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	public String dbfs() throws Exception{
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		((HyPcHwxxHdqdService)getService()).dbfs(domain, getUserDomain());		
		return "dbfs";
	}
	public String queryQd() throws Exception{
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		((HyPcHwxxHdqdService)getService()).doQueryQd(domain, getUserDomain());		
		return "queryQd";
	}
	public String queryHdByQd() throws Exception{
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		((HyPcHwxxHdqdService)getService()).queryHdByQd(domain, getUserDomain());		
		return "queryHdByQd";
	}
	public String qdjs() throws Exception{
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		((HyPcHwxxHdqdService)getService()).qdjs(domain, getUserDomain());		
		return "qdjs";
	}
	public String qdth() throws Exception{
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		((HyPcHwxxHdqdService)getService()).qdth(domain, getUserDomain());		
		return "qdth";
	}
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcHwxxHdqdDomain domain = (HyPcHwxxHdqdDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("序号");
		headList.add("回单清单登记序号(SEQ_HD_DJXH)");
		headList.add("清单名称");
		headList.add("接收状态(0 初始，1 发送，2 接收，3 退回)");
		headList.add("发送公司编码");
		headList.add("接收公司编码");
		headList.add("备注");
		headList.add("所属机构");
		headList.add("登记部门");
		headList.add("打包人操作员登记序号");
		headList.add("打包日期");
		headList.add("有效标志(Y/N)");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcHwxxHdqdDomain element = (HyPcHwxxHdqdDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getHdqdDjxh());
			list.add(element.getQdmc());
			list.add(element.getJszt());
			list.add(element.getFsGsbm());
			list.add(element.getJsGsbm());
			list.add(element.getBz());
			list.add(element.getSsJgbm());
			list.add(element.getDjJgbm());
			list.add(element.getDbrCzyDjxh());
			list.add(element.getDbrq());
			list.add(element.getYxbz());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcHwxxHdqdDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcHwxxHdqdDomain) domain;
	}

	@Resource(name = "hyPcHwxxHdqdServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
