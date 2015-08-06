package com.cy.common.action;

import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.cy.common.domain.WsspCommonDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.service.WsspCommonService;

/**
 * 
* @Descriptoin ��������ƽ̨����ҵ��
* @Note
* @author admin
* @since 2013-01-23 ����14:11:39 
* @version
 */
@Controller
@Scope("prototype")
@Action(value = "/common/wsspCommon", results = {
		@Result(name = "init", location = "/work/common/wssp_common.jsp"),
		@Result(name = "save", location = "/work/common/wssp_common.jsp"),
		@Result(name = "judge", type = "json"),
		@Result(name = "back", type = "json"),
		@Result(name = "send", type = "json"),
		@Result(name = "querySpyj", location = "/work/common/wsspyj_ck.jsp"),
		@Result(name = "queryWsspms", type = "json"),
		@Result(name = "initSend", location = "/work/common/initSend.jsp"),
		@Result(name = "plSend", type = "json"),
		@Result(name = "plBack", type = "json"),
		@Result(name = "plJudge", type = "json"),
		@Result(name = "initScSend", location = "/work/common/initSend.jsp"),
		@Result(name = "plScSend", type = "json"),
		@Result(name = "scSend", type = "json")
})
public class WsspCommonAction extends ExtendAction {
	
	/** serialVersionUID*/ 
	private static final long	serialVersionUID	= 1L;
	private WsspCommonDomain domain;
	
	@Override
	public String save() throws Exception {
		this.getService().save(getDomain(),this.getUserDomain());
		this.createMessageUser("����ɹ���");
		return "save";
	}
	//����
	public String judge() throws Exception {
		this.getService().saveMx(getDomain(),this.getUserDomain());
		return "judge";	
	}
	//�˻�
	public String back() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.saveBack(getDomain(),this.getUserDomain());
		
		return "back";	
	}
	//����
	public String send() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.send(getDomain(),this.getUserDomain());
		
		return "send";	
	}
	//�鿴�������
	public String querySpyj() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.querySpyj(getDomain(),this.getUserDomain());
		
		return "querySpyj";	
	}
	public String queryWsspms() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.queryWsspms(getDomain(),this.getUserDomain());
		
		return "queryWsspms";	
	}
	//����ʱ����ʼ������ҳ��
	public String initSend() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.initSendXX(this.getUserDomain(),getDomain());
		
		return "initSend";	
	}
	//��������
	public String plSend() throws Exception {
		//ת��Ϊ�Լ���SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		WsspCommonDomain dom=null;
		List<String> xhs=this.getDomain().getCheckboxs();
		for (String xh : xhs) {
			String array[]=xh.split("#");
			dom=new WsspCommonDomain();
			dom.setWsspxh(array[0]);
			dom.setSpxh(array[1]);
			dom.setSprJdxh(String.valueOf(Integer.parseInt(array[2])+1));
			dom.setSprCzyDjxh(this.getDomain().getSprCzyDjxh());
			dom.setSpjg("1");
			dom.setSpyj("����ͬ��");
			service.plSend(dom,this.getUserDomain());
		}
		return "plSend";	
	}
	//��������
	public String plJudge() throws Exception {
		WsspCommonDomain dom=null;
		List<String> xhs=this.getDomain().getCheckboxs();
		for (String xh : xhs) {
			String array[]=xh.split("#");
			dom=new WsspCommonDomain();
			dom.setWsspxh(array[0]);
			dom.setSpxh(array[1]);
			dom.setSpjg("1");
			dom.setSpyj("��������ͨ��");
			this.getService().saveMx(dom,this.getUserDomain());
		}
		return "plJudge";	
	}
	
	//��������
	public String plBack() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		WsspCommonDomain dom=null;
		List<String> xhs=this.getDomain().getCheckboxs();
		for (String xh : xhs) {
			String array[]=xh.split("#");
			dom=new WsspCommonDomain();
			dom.setWsspxh(array[0]);
			dom.setSpxh(array[1]);
			service.saveBack(dom,this.getUserDomain());
		}
		return "plBack";	
	}
	//��ʼ���״η�����Ϣ
	public String initScSend() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		service.initScSendXx(this.getDomain(), this.getUserDomain());
		return "initScSend";	
	}
	//�״η���
	public String scSend() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		this.getDomain().setWssplzszxh(service.queryWssplcszxh(this.getDomain(), this.getUserDomain()));
		service.scSend(this.getDomain(), this.getUserDomain());
		return "scSend";	
	}
	
	//�����״η���
	public String plScSend() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		this.getDomain().setWssplzszxh(service.queryWssplcszxh(this.getDomain(), this.getUserDomain()));
		WsspCommonDomain dom=null;
		List<String> xhs=this.getDomain().getCheckboxs();
		for (String xh : xhs) {
			String array[]=xh.split("#");
			dom=new WsspCommonDomain();
			dom.setWsDm(this.getDomain().getWsDm());
			dom.setWsXmflDjxh(this.getDomain().getWsXmflDjxh());
			dom.setYwDjxh(array[0]);
			dom.setWssplzszxh(this.getDomain().getWssplzszxh());
			dom.setSprCzyDjxh(this.getDomain().getSprCzyDjxh());
			if(array.length==2){
				dom.setOldWsspxh(array[1]);
			}else{
				dom.setOldWsspxh("");
			}
			service.scSend(dom,this.getUserDomain());
		}
		return "plScSend";	
	}
	
	@Resource(name = "wsspCommonServiceImp")	
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}

	public WsspCommonDomain getDomain() {
		if(domain==null){
			domain = new WsspCommonDomain();
		}
		return domain;
	}

	public void setDomain(WsspCommonDomain domain) {
		this.domain = domain;
	}

}
