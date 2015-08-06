package com.cy.zygl.action;

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
import com.cy.zygl.domain.QyKhDjxxDomain;
import com.cy.zygl.service.QyKhDjxxService;

/**
 * THE ACTION FOR ��ҵ-�ͻ�-�Ǽ���Ϣ
 * @author HJH
 */
 @Controller
@Scope("prototype")
@Action(value = "/zygl/qykhdjxx", results = {
		@Result(name = "init", location = "/work/zygl/qykhdjxx.jsp"),
		@Result(name = "query", type = "json"),
		@Result(name = "initMx", location = "/work/zygl/qykhdjxx_mx.jsp"),
		@Result(name = "save", type = "json"),
		//@Result(name = "check", type = "json"),
		@Result(name = "ajaxQuery", type = "json"),
		@Result(name = "delete", type = "json")})
@SuppressWarnings("unchecked")
public class QyKhDjxxAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	
	private String dropDownData;//��������б�����
	private String jsonData;//��������б��Ӧ��ֵ
	private String targetObjName;//���Ŀ������name���������б��name��
	private String targetDmObjName;//���Ŀ������Ӧdm��name
	private String itemIndex;
	private String dropDownSelectedCallback;//���ѡ�������б�Ļص���������
	private Boolean isCleanText;//û���ҵ���Ӧ�ļ�¼ƥ���Ƿ�Ҫ�������������գ�false����
	
	
	/*public String check() throws Exception{
		QyKhDjxxService service=(QyKhDjxxService)this.getService();
		QyKhDjxxDomain domain = (QyKhDjxxDomain) getDomain();
		service.check(domain, getUserDomain());
		return "check";
	}*/
	
	public String queryXzqhList() throws Exception{
		QyKhDjxxService service=(QyKhDjxxService)this.getService();
		QyKhDjxxDomain domain = (QyKhDjxxDomain) getDomain();
		String [] returnData=service.queryXzqhList(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}

	@SuppressWarnings("unchecked")
	public String download() throws Exception {
		super.download();
		QyKhDjxxDomain domain = (QyKhDjxxDomain) getDomain();
		List heads = new ArrayList();
		List result = new ArrayList();
		List headList = new ArrayList();
		
		headList.add("���");

		headList.add("�ͻ�����");
		headList.add("�ͻ����");
		headList.add("��������");
		headList.add("������λ");
		headList.add("�Ǽǲ���");
		headList.add("��ַ");
		headList.add("������");
		headList.add("�绰");
		headList.add("������");
		headList.add("��������");
		headList.add("�޸���");
		headList.add("�޸�����");
		headList.add("�ͻ�����");
		headList.add("�����㷽ʽ");
		headList.add("ƴ��ȫ��");
		headList.add("ƴ�����");

		heads.add(headList);

		int i = 1;
		for (BaseBusinessDomain e:(List<BaseBusinessDomain>)domain.getDataList()) {
			QyKhDjxxDomain element = (QyKhDjxxDomain) e;
			List list = new ArrayList();
			
			list.add(i+++ "");
			list.add(element.getKhmc());
			list.add(element.getKhjc());
			list.add(element.getXzqhMc());
			list.add(element.getSsjgmc());
			list.add(element.getDjjgmc());
			list.add(element.getDz());
			list.add(element.getFzr());
			list.add(element.getDh());
			list.add(element.getCjrMc());
			list.add(element.getCjrq());
			list.add(element.getXgrMc());
			list.add(element.getXgrq());
			list.add(element.getKhlxMc());
			list.add(element.getYkjsfsMc());
			list.add(element.getPyqc());
			list.add(element.getPyjc());

			result.add(list);
		}

		PaginationSupport ps = new PaginationSupport(result, result.size());
		ps.setHeads(heads);

		ExcelExportPOI.createExpXls(ps, "0.00", request);

		return "download";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new QyKhDjxxDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhDjxxDomain) domain;
	}

	@Resource(name = "qyKhDjxxServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public String getDropDownData() {
		return dropDownData;
	}

	public String getDropDownSelectedCallback() {
		return dropDownSelectedCallback;
	}

	public String getItemIndex() {
		return itemIndex;
	}

	public String getJsonData() {
		return jsonData;
	}

	public String getTargetDmObjName() {
		return targetDmObjName;
	}

	public String getTargetObjName() {
		return targetObjName;
	}

	public void setDropDownData(String dropDownData) {
		this.dropDownData = dropDownData;
	}

	public void setDropDownSelectedCallback(String dropDownSelectedCallback) {
		this.dropDownSelectedCallback = dropDownSelectedCallback;
	}

	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setTargetDmObjName(String targetDmObjName) {
		this.targetDmObjName = targetDmObjName;
	}

	public void setTargetObjName(String targetObjName) {
		this.targetObjName = targetObjName;
	}

	public Boolean getIsCleanText() {
		return isCleanText;
	}

	public void setIsCleanText(Boolean isCleanText) {
		this.isCleanText = isCleanText;
	}

}
