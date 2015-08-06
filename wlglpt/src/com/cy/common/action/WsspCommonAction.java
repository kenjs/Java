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
* @Descriptoin 物流管理平台公共业务
* @Note
* @author admin
* @since 2013-01-23 下午14:11:39 
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
		this.createMessageUser("保存成功！");
		return "save";
	}
	//终审
	public String judge() throws Exception {
		this.getService().saveMx(getDomain(),this.getUserDomain());
		return "judge";	
	}
	//退回
	public String back() throws Exception {
		//转化为自己的SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.saveBack(getDomain(),this.getUserDomain());
		
		return "back";	
	}
	//发送
	public String send() throws Exception {
		//转化为自己的SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.send(getDomain(),this.getUserDomain());
		
		return "send";	
	}
	//查看审批意见
	public String querySpyj() throws Exception {
		//转化为自己的SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.querySpyj(getDomain(),this.getUserDomain());
		
		return "querySpyj";	
	}
	public String queryWsspms() throws Exception {
		//转化为自己的SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.queryWsspms(getDomain(),this.getUserDomain());
		
		return "queryWsspms";	
	}
	//批量时，初始化发送页面
	public String initSend() throws Exception {
		//转化为自己的SERVICE
		WsspCommonService service = (WsspCommonService)getService();
		service.initSendXX(this.getUserDomain(),getDomain());
		
		return "initSend";	
	}
	//批量发送
	public String plSend() throws Exception {
		//转化为自己的SERVICE
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
			dom.setSpyj("批量同意");
			service.plSend(dom,this.getUserDomain());
		}
		return "plSend";	
	}
	//批量终审
	public String plJudge() throws Exception {
		WsspCommonDomain dom=null;
		List<String> xhs=this.getDomain().getCheckboxs();
		for (String xh : xhs) {
			String array[]=xh.split("#");
			dom=new WsspCommonDomain();
			dom.setWsspxh(array[0]);
			dom.setSpxh(array[1]);
			dom.setSpjg("1");
			dom.setSpyj("批量终审通过");
			this.getService().saveMx(dom,this.getUserDomain());
		}
		return "plJudge";	
	}
	
	//批量终审
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
	//初始化首次发送信息
	public String initScSend() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		service.initScSendXx(this.getDomain(), this.getUserDomain());
		return "initScSend";	
	}
	//首次发送
	public String scSend() throws Exception {
		WsspCommonService service = (WsspCommonService)getService();
		this.getDomain().setWssplzszxh(service.queryWssplcszxh(this.getDomain(), this.getUserDomain()));
		service.scSend(this.getDomain(), this.getUserDomain());
		return "scSend";	
	}
	
	//批量首次发送
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
