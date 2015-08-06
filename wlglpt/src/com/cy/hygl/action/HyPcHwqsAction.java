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
import com.cy.common.service.BaseBusinessService;
import com.cy.hygl.domain.HyPcHwqsDomain;
import com.cy.hygl.service.HyPcHwqsService;

/**
 * THE ACTION FOR ����-�ɳ�-����ǩ��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/hypchwqs", results = {
		@Result(name = "init", location = "/work/hygl/hy_pchwqs.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_pchwqs_mx.jsp"),
		@Result(name = "initPsfMx", location = "/work/hygl/hy_pc_xypsflr_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "checkWlssSfDj",type= "json" ),
		@Result(name = "savePsf", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class HyPcHwqsAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@Override
	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcHwqsDomain domain = (HyPcHwqsDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		//headList.add("����ǩ�յǼ����");
		headList.add("״̬");
		headList.add("�ɳ��Ǽ����");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("˾��");
		headList.add("��ϵ�绰");
		headList.add("�ͻ���ʽ");
		headList.add("���ͷ�");
		headList.add("��������");
		headList.add("��װ");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("����");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("�ջ���");
		headList.add("�ջ���ַ");
		headList.add("�ջ�����ϵ�绰");
		headList.add("ǩ����");
		headList.add("ǩ������");
		

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:domain.getDataList()) {
			HyPcHwqsDomain element = (HyPcHwqsDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			//list.add(element.getHwqsDjxh());
			list.add(element.getHwqsDjxh()==null?"δ����":"�ѽ���");
			list.add(element.getPcDjxh());
			list.add(element.getPcdh());
			list.add(element.getPcrq());
			list.add(element.getCyrClhm());
			list.add(element.getCyrSjxm());
			list.add(element.getCyrSjsjhm());
			list.add(element.getShfsMc());
			list.add(element.getPsfy());
			list.add(element.getHwMc());
			list.add(element.getHwbz());
			list.add(element.getSl());
			list.add(element.getZl());
			list.add(element.getTj());
			list.add(element.getSrDf());
			list.add(element.getFhrXzqhMc());
			list.add(element.getShrXzqhMc());
			list.add(element.getShrMc());
			list.add(element.getQsrMc());
			list.add(element.getQsrq());
			

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String initPsfMx() throws Exception {
		((HyPcHwqsService)getService()).initPsfMx(getDomain());
		return "initPsfMx";
	}
	
	public String savePsf() throws Exception {
		((HyPcHwqsService)getService()).savePsf(getDomain(),getUserDomain());
		return "savePsf";
	}
	
	public String checkWlssSfDj() throws Exception {
		((HyPcHwqsService)getService()).checkWlssSfDj(getDomain());
		return "checkWlssSfDj";
	}
	
	@Override
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcHwqsDomain();
		}
		return domain;
	}

	@Override
	public void setDomain(BaseBusinessDomain domain) {
		this.domain = domain;
	}

	@Override
	@Resource(name = "hyPcHwqsServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
