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
import com.cy.hygl.domain.XyjsZbYjdzDomain;
import com.cy.hygl.service.XyjsZbYjdzService;
import com.cy.hygl.service.XyjsZbYjdzShService;

/**
 * THE ACTION FOR ���ν���-ת��-�½����
 * @author XIAY
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/xyjszbyjdz", results = {
		@Result(name = "init", location = "/work/hygl/xyjszbyjdz.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/xyjszbyjdz_mx.jsp"),
		@Result(name = "onView", location = "/work/hygl/xyjszbyjdz_ck.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "plDz", type = "json"),
		@Result(name = "print", location = "/work/hygl/xyjszbyjdz_print.jsp"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class XyjsZbYjdzAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		XyjsZbYjdzDomain domain = (XyjsZbYjdzDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");
		headList.add("��������");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("�ְ�������");
		headList.add("�ְ���");
		headList.add("����_δ��");
		headList.add("���˽��");
		headList.add("������");
		headList.add("��������");
		headList.add("���ʲ���");
		headList.add("�Ƿ����");
		headList.add("������");
		headList.add("����״̬");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			XyjsZbYjdzDomain element = (XyjsZbYjdzDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getJgmc());
			list.add(element.getPcdh());
			list.add(element.getPcrq());
			list.add(element.getHwmc());
			list.add(element.getJgmc());
			list.add(element.getFbsmc());
			list.add(element.getJsYj());
			list.add(element.getDzje());
			list.add(element.getDzrCzyDjxh());
			list.add(element.getDzrq());
			list.add(element.getDzJgbm());
			if(element.getDzCybz().equals("Y")){
				list.add("��");
			}else if(element.getDzCybz().equals("N")){
				list.add("��");
			}
			list.add(element.getDzcyje());
			if(element.getDzbz().equals("Y")){//�Ѷ���
				if(element.getWsspztDm().equals("1")){
					list.add("������");
				}else if(element.getWsspztDm().equals("2")){
					list.add("���˻�");
				}else if(element.getWsspztDm().equals("3")){
					list.add("������");
				}
			}else if(element.getDzbz().equals("N")){//δ����
				list.add("δ����");
			}
			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	//��ӡԤ��
	public String dyyl() throws Exception{
		((XyjsZbYjdzService)getService()).dyyl(getDomain(),getUserDomain());
		return "print";
	}
	
	public String onView() throws Exception{
		((XyjsZbYjdzService)getService()).viewMx(getDomain());
		return "onView";
	}
	public String plDz() throws Exception {
		((XyjsZbYjdzService)getService()).plDz(getDomain(),getUserDomain());
		return "plDz";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new XyjsZbYjdzDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (XyjsZbYjdzDomain) domain;
	}

	@Resource(name = "xyjsZbYjdzServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
