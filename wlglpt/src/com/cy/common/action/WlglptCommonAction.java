package com.cy.common.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.domain.UserDomain;
import com.cy.common.domain.WlglptCommonDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.service.WlglptCommonService;
import com.cy.zygl.domain.QyKhDjxxDomain;
import com.cy.zygl.service.QyKhDjxxService;

/**
 * 
* @Descriptoin ��������ƽ̨����ҵ��
* @Note
* @author admin
* @since 2011-10-13 ����11:11:39 
* @version
 */
@Controller
@Scope("prototype")
@Action(value = "/common/wlglptCommon", results = {
		@Result(name = "bmInit", type = "json"),
		@Result(name = "gwInit", type = "json"),
		@Result(name = "ryInit", type = "json"),
		@Result(name = "commonInit", type = "json"),
		@Result(name = "queryCurrentFbs", type = "json"),
		@Result(name = "wsInit", type = "json")
})
public class WlglptCommonAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	//�û���¼domain
	private WlglptCommonDomain domain;

	// ������������
	public String commonInit() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WlglptCommonService service = (WlglptCommonService)getService();
		service.getCommonList(getDomain(), getUserDomain());
		
		return "commonInit";	
	}
	
	//ȡ���������б�
	public String bmInit() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WlglptCommonService service = (WlglptCommonService)getService();
		service.getBmList(getDomain());
		
		return "bmInit";	
	}
	
	public String gwInit() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WlglptCommonService service = (WlglptCommonService)getService();
		service.getGwList(getDomain());
		
		return "gwInit";	
	}
	
	public String queryCurrentFbs() throws Exception {
		((WlglptCommonService)getService()).queryCurrentFbs(getDomain(), getUserDomain());
		return "queryCurrentFbs";
	}
	public String wsInit() throws Exception {
		((WlglptCommonService)getService()).queryWs(getDomain());
		return "wsInit";
	}
	
	/*public String queryQykh4AutoComplete() throws Exception{
		QyKhDjxxService service=(QyKhDjxxService)this.getService();
		String [] returnData=service.queryQykh4AutoComplete(getDomain(), getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}*/
	
	@Resource(name = "wlglptCommonServiceImp")	
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public WlglptCommonDomain getDomain() {
		if(domain==null){
			domain
			= new WlglptCommonDomain();
		}
		return domain;
	}

	public void setDomain(WlglptCommonDomain domain) {
		this.domain = domain;
	}

}
