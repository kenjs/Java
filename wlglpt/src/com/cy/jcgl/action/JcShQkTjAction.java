package com.cy.jcgl.action;

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
import com.cy.jcgl.domain.JcPcxxglDomain;
import com.cy.jcgl.domain.JcShQkTjDomain;
import com.cy.jcgl.service.JcPcxxglService;

/**
 * THE ACTION FOR ����-�ɳ���Ϣ����
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/shqktj", results = {
		@Result(name = "init", location = "/work/jcgl/jc_shqktj.jsp"),
		@Result(name = "query", location = "/work/jcgl/jc_shqktj.jsp"),
		@Result(name = "initMx", location = "/work/jcgl/jc_pcxxcx_mx.jsp")
		})
@SuppressWarnings("unchecked")
public class JcShQkTjAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcShQkTjDomain domain = (JcShQkTjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ͻ�����");
		headList.add("�����������֣�");
		headList.add("�������������");
		headList.add("����С��");
		headList.add("�����ָ�");
		headList.add("���µ���");
		headList.add("�����½�");
		headList.add("���»ؿ�");
		headList.add("�����������֣�");
		headList.add("�������������");
		headList.add("����С��");
		headList.add("�����ָ�");
		headList.add("���굽��");
		headList.add("�����½�");
		headList.add("����ؿ�");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcShQkTjDomain element = (JcShQkTjDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getKhMc());
			list.add(element.getByZl());
			list.add(element.getByTj());
			list.add(element.getByXj());
			list.add(element.getByXf());
			list.add(element.getByDj());
			list.add(element.getByHf());
			list.add(element.getByHk());
			list.add(element.getBnZl());
			list.add(element.getBnTj());
			list.add(element.getBnXj());
			list.add(element.getBnXf());
			list.add(element.getBnDj());
			list.add(element.getBnHf());
			list.add(element.getBnHk());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	public String initViewMx() throws Exception {
		((JcPcxxglService)getService()).initMx(getDomain(), getUserDomain());
		return "initViewMx";
	}
	
	@Resource(name = "jcShQkTjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcShQkTjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
