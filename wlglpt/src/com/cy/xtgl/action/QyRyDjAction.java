package com.cy.xtgl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.xtgl.domain.QyRydjDomain;
/**
 * The Action for 企业人员登记用户维护
 * 
 * @Author Yu huan
 * @Date 2013-01-9
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Action(value = "xtgl/yhwh", 
		results = {
		@Result(name = "init", location = "/work/xtgl/qyrydj.jsp"),
		@Result(name = "initMx", location = "/work/xtgl/qyrydj_mx.jsp"),		
		@Result(name = "query", type = "json"),
		@Result(name = "save", type = "json"),
		@Result(name = "saveDisable", type = "json"),
		@Result(name = "saveEnable", type = "json"),
		@Result(name = "delete", type = "json")
		})

public class QyRyDjAction extends ExtendAction {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyRydjDomain domain = (QyRydjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("序号");
		headList.add("名称");
		headList.add("账号");
		headList.add("主部门");
		headList.add("手机号码");
		headList.add("手机短号");
		headList.add("办公电话");
		headList.add("办公短号");
		headList.add("家庭电话");
		headList.add("QQ号码");
		headList.add("MSN号码");
		headList.add("EMAIL地址");
		headList.add("创建人");
		headList.add("创建日期");
		headList.add("修改人");
		headList.add("修改日期");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			QyRydjDomain element = (QyRydjDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getMc());
			list.add(element.getZh());
			list.add(element.getJc());
			list.add(element.getSjhm());
			list.add(element.getSjdh());
			list.add(element.getBgdh());
			list.add(element.getBgdhao());
			list.add(element.getJtdh());
			list.add(element.getQq());
			list.add(element.getMsn());
			list.add(element.getEmail());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}

	@Resource(name = "qyRydjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new QyRydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}

