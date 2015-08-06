package com.cy.bggl.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.bggl.domain.BgGzsjDomain;
import com.cy.bggl.service.BgGzsjService;
import com.cy.common.action.ExtendAction;
import com.cy.common.service.BaseBusinessService;
import com.cy.framework.common.ExcelExportPOI;
import com.cy.framework.common.PaginationSupport;
import com.cy.framework.domain.BaseBusinessDomain;

/**
 * THE ACTION FOR �칫-����ʱ��
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/bggl/bggzsj", results = {
		@Result(name = "init", location = "/work/bggl/bggzsj.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/bggl/bggzsj_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "checkSave", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class BgGzsjAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		BgGzsjDomain domain = (BgGzsjDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
//		headList.add("��������");
		headList.add("״̬");
		headList.add("��Ч����");
		headList.add("��Ч��ֹ");
		headList.add("�����ϰ�ʱ��");
		headList.add("�����°�ʱ��");
//		headList.add("�����ϰ�ʱ��-ʱ");
//		headList.add("�����ϰ�ʱ��-��");
//		headList.add("�����°�ʱ��-ʱ");
//		headList.add("�����°�ʱ��-��");
//		headList.add("������");
//		headList.add("��������");
//		headList.add("�޸���");
//		headList.add("�޸�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			BgGzsjDomain element = (BgGzsjDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
//			list.add(element.getJgbm());
			if("1".equals(element.getZt())){
				list.add("��Ч");
			}else{
				list.add("��Ч");
			}
			list.add(element.getYxqQ());
			list.add(element.getYxqZ());
			list.add(element.getSbsj());
			list.add(element.getXbsj());
//			list.add(element.getAmSbsjS());
//			list.add(element.getAmSbsjF());
//			list.add(element.getPmSbsjS());
//			list.add(element.getPmSbsjF());
//			list.add(element.getCjrMc());
//			list.add(element.getCjrq());
//			list.add(element.getXgrMc());
//			list.add(element.getXgrq());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String checkSave() throws Exception {
		BgGzsjService qyservice = (BgGzsjService) getService();
		qyservice.checkSave(getDomain());
		return "checkSave";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new BgGzsjDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (BgGzsjDomain) domain;
	}

	@Resource(name = "bgGzsjServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
