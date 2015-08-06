package com.cy.zyegl.action;

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
import com.cy.zyegl.domain.HyPcXydjDomain;
import com.cy.zyegl.service.HyPcXydjService;

/**
 * THE ACTION FOR ����-�ɳ�-Э��Ǽ�
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zyegl/pcxydj", results = {
		@Result(name = "init", location = "/work/zyegl/hy_pc_xydj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initXydj", location = "/work/zyegl/hy_pc_xydj_mx.jsp"),
		@Result(name = "initHwxxXydj", location = "/work/zyegl/hy_pc_hwmx_xydj.jsp"),
		@Result(name = "initXybd", location = "/work/zyegl/hy_pc_xybd_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "saveHwmxXydj", type = "json"),
		@Result(name = "deleteHwmxXydj", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyPcXydjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcXydjDomain domain = (HyPcXydjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("�ɳ��Ǽ����(SEQ_PC_DJXH)");
		headList.add("Э���");
		headList.add("�˷ѱ��_���˷�");
		headList.add("�˷ѱ��_Ԥ���˷�");
		headList.add("�˷ѱ��_Ѻ��");
		headList.add("�˷ѱ��_��Ϣ��");
		headList.add("�˷ѱ��_˾����");
		headList.add("�˷ѱ��_�����˷�");
		headList.add("�˷ѱ��_�ص���");
		headList.add("��ע");
		headList.add("ҵ��Ա");
		headList.add("��Ч��־(Y/N)");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");
		headList.add("��Ҫ������־(Y/N)");
		headList.add("��������״̬����");
		headList.add("�����������");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcXydjDomain element = (HyPcXydjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getPcDjxh());
			list.add(element.getXyh());
			list.add(element.getYfHj());
			list.add(element.getYfYfyf());
			list.add(element.getYfYj());
			list.add(element.getYfXxf());
			list.add(element.getYfSjs());
			list.add(element.getYfHdyf());
			list.add(element.getYfHdf());
			list.add(element.getBz());
			list.add(element.getYwyCzyDjxh());
			list.add(element.getYxbz());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
			list.add(element.getSpbz());
			list.add(element.getWsspztDm());
			list.add(element.getWsSpxh());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String initXydj() throws Exception {
		((HyPcXydjService)getService()).initXydj(getDomain(), getUserDomain());
		return "initXydj";
	}
	
	public String initXybd() throws Exception {
		((HyPcXydjService)getService()).initXybd(getDomain(), getUserDomain());
		return "initXybd";
	}
	
	public String initHwxxXydj() throws Exception {
		((HyPcXydjService)getService()).initHwxxXydj(getDomain(), getUserDomain());
		return "initHwxxXydj";
	}
	
	public String saveHwmxXydj() throws Exception {
		((HyPcXydjService)getService()).saveHwmxXydj(getDomain(), getUserDomain());
		return "saveHwmxXydj";
	}
	
	public String deleteHwmxXydj() throws Exception {
		((HyPcXydjService)getService()).deleteHwmxXydj(getDomain(), getUserDomain());
		return "deleteHwmxXydj";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcXydjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcXydjDomain) domain;
	}

	@Resource(name = "hyPcXydjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
