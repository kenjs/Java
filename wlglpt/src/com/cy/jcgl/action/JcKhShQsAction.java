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
import com.cy.jcgl.domain.JcKhShQsDomain;
import com.cy.jcgl.domain.JcPcxxglDomain;

/**
 * THE ACTION FOR ����-�ɳ���Ϣ����
 * @author LYY
 * time  2013-5-4
 */
@Controller
@Scope("prototype")
@Action(value = "/jcgl/khshqsbh", results = {
		@Result(name = "init", location = "/work/jcgl/jc_khshqs.jsp"),
		@Result(name = "query", location = "/work/jcgl/jc_khshqs.jsp"),
		@Result(name = "initMx", location = "/work/jcgl/jc_pcxxcx_mx.jsp")
		})
@SuppressWarnings("unchecked")
public class JcKhShQsAction extends ExtendAction {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		JcPcxxglDomain domain = (JcPcxxglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ɳ�����");
		headList.add("���");
		headList.add("װ��");
		headList.add("��������");
		headList.add("�ҳ�����");
		headList.add("˾��");
		headList.add("�˷�");
		headList.add("ת�벿��");
		headList.add("�������");
		headList.add("�ͻ�����");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("��������");
		headList.add("��װ");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("������");
		headList.add("Ҫ�󷢻�����");
		headList.add("�ջ���");
		headList.add("Ҫ�󵽴�����");
		headList.add("�ɳ���");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("��������");
		heads.add(headList);
		int i = 1;
		for (BaseBusinessDomain e : (List<BaseBusinessDomain>) domain.getDataList()) {
			JcPcxxglDomain element = (JcPcxxglDomain) e;
			List list = new ArrayList();
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getPcfsMc());
			list.add(element.getZcfxMc());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			list.add(element.getCyrSjxm());
			list.add(element.getYfHj());
			list.add(element.getYfHj());
			list.add(element.getZrbmMc());
			list.add(element.getDingdan());
			list.add(element.getFhrMc());
			list.add(element.getSfdMc());
			list.add(element.getMddMc());
			list.add(element.getHwMc());
			list.add(element.getHwbz());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());
			list.add(element.getFhrMc());
			list.add(element.getYqFhrq());
			list.add(element.getShrMc());
			list.add(element.getYqDdrq());
			list.add(element.getPcrMc());
			list.add(element.getPcrq());
			list.add(element.getPcJgbmMc());
			list.add(element.getSsJgbmMc());
			result.add(list);
		}
		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);
		ExcelExportPOI.createExpXls(ps, "0.00", request);
		return "download";
	}
	
	
	@Resource(name = "jcKhShQsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public BaseBusinessDomain getDomain() {
		if (domain == null) {
			domain = new JcKhShQsDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}
}
	
