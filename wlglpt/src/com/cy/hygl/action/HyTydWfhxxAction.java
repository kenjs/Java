package com.cy.hygl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.framework.util.SysDateUtil;
import com.cy.hygl.domain.HyTydWfhxxDomain;
import com.cy.hygl.service.HyTydWfhxxService;

/**
 * THE ACTION FOR ����-���˵�-δ����(���)��Ϣ
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hytydwfhxx", results = {
		@Result(name = "init", location = "/work/hygl/hy_tyd_wfhxx.jsp"),
		@Result(name = "init4Pc", location = "/work/hygl/hy_tyd_wfhxx_pc.jsp"),
		@Result(name = "doUpdate", type = "json"),
		@Result(name = "query", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyTydWfhxxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@Override
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		headList.add("���");
		headList.add("�������");
		headList.add("������");
		headList.add("״̬");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("��������");
		headList.add("��װ");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("���");
		headList.add("������ַ");
		headList.add("Ҫ�󷢻�����");
		headList.add("�ջ���");
		headList.add("�ջ���ַ");
		headList.add("Ҫ�󵽴�����");
		headList.add("�Ǽ���");
		headList.add("�Ǽ�����");
		headList.add("�Ǽǲ���");
		headList.add("��������");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:domain.getDataList()) {
			HyTydWfhxxDomain element = (HyTydWfhxxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getDdbh());
			list.add(element.getFhrMc());
			list.add(element.getHwztMc());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getHwmc());
			list.add(element.getBz());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());
			list.add(element.getLb());
			list.add(element.getFhrDz());
			if (StringUtils.isNotBlank(element.getFhRq())) {
				list.add(SysDateUtil.format(SysDateUtil.parse(element.getFhRq())));
			}else {
				list.add("");
			}
			
			list.add(element.getShrMc());
			list.add(element.getShDz());
			if (StringUtils.isNotBlank(element.getYqDdrq())) {
				list.add(SysDateUtil.format(SysDateUtil.parse(element.getYqDdrq())));
			}else {
				list.add("");
			}
			
			list.add(element.getDjrMc());
			if (StringUtils.isNotBlank(element.getDjRq())) {
				list.add(SysDateUtil.format(SysDateUtil.parse(element.getDjRq())));
			}else {
				list.add("");
			}
			
			list.add(element.getDjJgmc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	@Override
	public String init() throws Exception {
		HyTydWfhxxDomain domain = (HyTydWfhxxDomain)getDomain();
		getService().init(domain, getUserDomain());
		if ("Y".equals(domain.getPcOpenFlag())) {
			return "init4Pc";
		}else {
			return "init";
		}
	}
	
	public String doUpdate() throws Exception {
		((HyTydWfhxxService)getService()).doUpdate(getDomain(),getUserDomain());
		return "doUpdate";
	}
	
	@Override
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyTydWfhxxDomain();
		}
		return domain;
	}

	@Override
	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}

	@Override
	@Resource(name = "hyTydWfhxxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
