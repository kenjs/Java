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
import com.cy.hygl.domain.HyMbTydDomain;
import com.cy.hygl.domain.HyWlSsDjGlDomain;
import com.cy.hygl.domain.HyZyglFydjDomain;
import com.cy.hygl.service.HyWlSsDjService;


/**
 * THE ACTION FOR ����-ģ��-������ʧ(��ѯ��mxҳ��Ϊ���������)
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/hygl/wlssdjgl", results = {
		@Result(name = "init", location = "/work/hygl/hy_wlssdjgl.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/hygl/hy_wlssdjgl_mx.jsp"),
		@Result(name = "save", type = "json"),
		@Result(name = "getHw", type = "json"),
		@Result(name = "delete", type = "json"),
		@Result(name = "checkTemplateName", type = "json")
		})
@SuppressWarnings("unchecked")
public class HyWlSsDjGlAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		HyWlSsDjGlDomain domain = (HyWlSsDjGlDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();

		headList.add("���");
		headList.add("״̬");
		headList.add("�������");
		headList.add("�ͻ�����");
		headList.add("��������");
		headList.add("ʼ����");
		headList.add("Ŀ�ĵ�");
		headList.add("ת�벿��");
		headList.add("��������");
		headList.add("������ַ");
		headList.add("�ջ���ַ");
		headList.add("���");
		
		headList.add("��ʧԭ��");
		headList.add("�ɳ�����");
		headList.add("��������");
		headList.add("�ҳ�����");
		headList.add("˾��");
		headList.add("�˷�");
		headList.add("Ԥ��");
		headList.add("�ɳ���");
		headList.add("�ɳ�����");
		headList.add("�ɳ�����");
		headList.add("ҵ��λ");

	

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			HyWlSsDjGlDomain element = (HyWlSsDjGlDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			if(StringUtils.isNotBlank(element.getWlssDjxh())){
				list.add("�ѵǼ�");
			}else{
				list.add("δ�Ǽ�");
			}
			list.add(element.getDdbh());
			list.add(element.getKhmc());
			list.add(element.getHwMc());
			list.add(element.getFhrXzqhMc());
			
			list.add(element.getShrXzqhMc());
			list.add(element.getZrbmMc());
			list.add(element.getJsSl());
			list.add(element.getFhrDz());
			list.add(element.getShrDz());
			
			list.add(element.getJe()==null?"":Double.parseDouble(element.getJe()));
			list.add(element.getWlssyyMc());
			list.add(element.getPcdh());
			list.add(element.getCyrClhm());
			list.add(element.getCyrGchm());
			
			list.add(element.getCyrSjxm());
			list.add(element.getYfHj());
			list.add(element.getYfYfYf());
			list.add(element.getPcrMc());
			list.add(element.getPcrQ());
			list.add(element.getPcJgmc());
			list.add(element.getSsJgmc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public String getHw() throws Exception{
		HyWlSsDjGlDomain domain=(HyWlSsDjGlDomain)getDomain();
		//((HyWlSsDjService)getService()).getHw(domain,getUserDomain());
		return "getHw";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new HyWlSsDjGlDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (HyMbTydDomain) domain;
	}

	@Resource(name = "hyWlSsDjGlServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
}
