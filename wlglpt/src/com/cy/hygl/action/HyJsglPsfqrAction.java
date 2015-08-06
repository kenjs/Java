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
import com.cy.hygl.domain.HyPcxxglDomain;
import com.cy.hygl.service.HyJsglPsfqrService;

/**
 * 
 */
@Controller		
@Scope("prototype")	
@Action(value = "/hygl/jspsfqr", results = { 
		@Result(name = "init", location = "/work/hygl/hy_psfqr.jsp"),
		@Result(name = "initMx", location = "/work/hygl/hy_psfqr_mx.jsp"),
		@Result(name = "viewMx", location = "/work/hygl/hy_psfqr_mx_readonly.jsp"),
		@Result(name = "save", type = "json"),		
		@Result(name = "delete", type = "json"),	
		@Result(name = "plqr", type = "json"),
		@Result(name = "print", location = "/work/hygl/hy_psfqr_print.jsp"),
		@Result(name = "query", type = "json")})
public class HyJsglPsfqrAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	public String view() throws Exception{
		this.getService().initMx(this.getDomain(), this.getUserDomain());
		return "view";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyPcxxglDomain domain = (HyPcxxglDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("���");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("�������");
		headList.add("�ͻ�����");
		headList.add("��������");
		headList.add("�ջ���");
		headList.add("���ε�λ");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("������Ϣ");
		headList.add("�˷�");
		headList.add("Ԥ��");
		headList.add("����");
		headList.add("����");
		headList.add("����");
		headList.add("���");
		headList.add("�ͻ���ʽ");
		headList.add("���ͷ�");
		headList.add("�Ƿ�ȷ��");

		headList.add("�ɳ���");
		
		headList.add("�ɳ�����");
		headList.add("��������");


		heads.add(headList);

		int i = 1;
		String pcdh = "";
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyPcxxglDomain element = (HyPcxxglDomain) e;
			List list = new ArrayList();
			
			list.add(i++ + "");
			list.add(element.getPcdh());
			list.add(element.getPcrq());
			list.add(element.getDingdan());
			list.add(element.getFhrMc());
			list.add(element.getHwMc());
			list.add(element.getShrMc());
			list.add(element.getZrbmMc());
			list.add(StringUtils.isBlank(element.getSl())?"":Double.parseDouble(element.getSl()));
			list.add(StringUtils.isBlank(element.getZl())?"":Double.parseDouble(element.getZl()));
			list.add(StringUtils.isBlank(element.getTj())?"":Double.parseDouble(element.getTj()));
			list.add(element.getClxx());
			
			if (!pcdh.equals(element.getPcdh())) {
				if (StringUtils.isNotBlank(element.getYfhjyf())) {
					String[] yfhjyf = element.getYfhjyf().split("/");
					if (yfhjyf.length >= 1) {
						list.add(StringUtils.isBlank(yfhjyf[0])?"":Double.parseDouble(yfhjyf[0]));
					}else {
						list.add("");
					}
					if (yfhjyf.length >= 2) {
						list.add(StringUtils.isBlank(yfhjyf[1])?"":Double.parseDouble(yfhjyf[1]));
					}else {
						list.add("");
					}
				}else {
					list.add("");
					list.add("");
				}
				if (StringUtils.isNotBlank(element.getSrhjdf())) {
					String[] srhjdf = element.getSrhjdf().split("/");
					if (srhjdf.length >= 1) {
						list.add(StringUtils.isBlank(srhjdf[0])?"":Double.parseDouble(srhjdf[0]));
					}else {
						list.add("");
					}
					if (srhjdf.length >= 2) {
						list.add(StringUtils.isBlank(srhjdf[1])?"":Double.parseDouble(srhjdf[1]));
					}else {
						list.add("");
					}
				}else {
					list.add("");
					list.add("");
				}
				
				list.add(StringUtils.isBlank(element.getZcZl())?"":Double.parseDouble(element.getZcZl()));
				list.add(StringUtils.isBlank(element.getZcTj())?"":Double.parseDouble(element.getZcTj()));
				
				pcdh = element.getPcdh();
			}else {
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
				list.add("");
			}
			
			list.add(element.getZs());
			list.add(StringUtils.isBlank(element.getSrPsf())?"":Double.parseDouble(element.getSrPsf()));
			
			if("Y".equals(element.getSfQr())){			
				if("����".equals(element.getZs())){
					list.add("");
				}else{
					list.add("��ȷ��");
				}
			}else{
				if("����".equals(element.getZs())){
					list.add("");
				}else{
					list.add("δȷ��");
				}
			}
			
			list.add(element.getPcrMc());
			list.add(element.getPcJgbmMc());
			list.add(element.getSsJgbmMc());


			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String viewMx() throws Exception {
		((HyJsglPsfqrService)getService()).viewMx(getDomain());
		return "viewMx";
	}
	
	public String plqr() throws Exception {
		((HyJsglPsfqrService)getService()).plqr(getDomain(),getUserDomain());
		return "plqr";
	}
	
	//��ӡԤ��
	public String dyyl() throws Exception{
		super.download();
		return "print";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyPcxxglDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyPcxxglDomain) domain;
	}

	@Resource(name = "hyJsglPsfqrServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
