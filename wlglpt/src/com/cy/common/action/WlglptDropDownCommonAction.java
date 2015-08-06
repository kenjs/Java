package com.cy.common.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cy.common.domain.WlglptDropDownCommonDomain;
import com.cy.common.service.BaseBusinessService;
import com.cy.common.service.WlglptCommonService;
import com.cy.common.service.WlglptDropDownCommonService;
import com.cy.framework.domain.BaseBusinessDomain;
import com.cy.zygl.domain.QyKhDjxxDomain;

/**
 * 发货人提示
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@Action(value = "/hygl/hycommondown", results = {
		@Result(name = "ajaxQuery", type = "json"),
		@Result(name = "ajaxQuery4Zhdz", type = "json"),
		@Result(name = "ajaxQuery4Shdw", type = "json"),
		@Result(name = "ajaxQuery4Shrdz", type = "json"),
		@Result(name = "ajaxQuery4Hwmc", type = "json")})
public class WlglptDropDownCommonAction extends ExtendAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jsonDataName;
	private String dropDownData;//存放下拉列表数据
	private String jsonData;//存放下拉列表对应的值
	private String targetObjName;//存放目标对象的name（即下拉列表的name）
	private String targetDmObjName;//存放目标对象对应dm的name
	private String itemIndex;
	private String dropDownSelectedCallback;//存放选中下拉列表的回调函数名称
	private Boolean isCleanText;//没有找到对应的记录匹配是否要把输入的内容清空，fasle不清
	private Long width;
	private String inputSelInitFun;
	
	public String queryXzqhList() throws Exception{
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryXzqhList(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}
	
	/**
	 * @description 行政区划输入框下拉
	 * @return
	 * @throws Exception
	 */
	public String queryXzqhInputSel() throws Exception {
		((WlglptDropDownCommonService)this.getService()).queryXzqhInputSel((WlglptDropDownCommonDomain)getDomain(), getUserDomain());
		return "ajaxQuery";
	}
	
	public String queryKhmcList() throws Exception{
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryHykhList(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			//fhrData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}
	
	public String queryHwmcList() throws Exception{
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryHyhwList(domain.getKhDjxh(), getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery4Hwmc";
	}
	
	public String queryZhdzList() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryZhdzList(domain.getKhDjxh(), getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery4Zhdz";
	}
	
	public String queryShrdzList() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryHyShrDzList(domain.getKhDjxh(), getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery4Shrdz";
	}
	
	public String queryShdwList() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		String [] returnData=service.queryHyShdwList(domain.getKhDjxh(), getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery4Shdw";
	}
	
	public String queryQyClxx() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		
		String [] returnData=service.queryQyClxx(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}
	
	public String queryQyGcxx() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		
		String [] returnData=service.queryQyGcxx(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}
	
	public String queryZrbmThShdz() throws Exception {
		WlglptDropDownCommonService service=(WlglptDropDownCommonService)this.getService();
		WlglptDropDownCommonDomain domain = (WlglptDropDownCommonDomain) getDomain();
		
		String [] returnData=service.queryZrbmThShdz(domain, getUserDomain());
		if (returnData!=null && returnData.length >= 2){
			jsonData=returnData[0];
			dropDownData=returnData[1];
		}
		return "ajaxQuery";
	}
	
	public String queryGsDz() throws Exception {
		((WlglptDropDownCommonService)this.getService()).queryGsDz((WlglptDropDownCommonDomain)getDomain(), getUserDomain());
		return "ajaxQuery";
	}
	
	public BaseBusinessDomain getDomain() {
		if (null == domain) {
			domain = new WlglptDropDownCommonDomain();
		}
		return domain;
	}

	public void setDomain(BaseBusinessDomain domain) {
		this.domain = (QyKhDjxxDomain) domain;
	}

	@Resource(name = "wlglptDropDownCommonServiceImp")
	public void setService(BaseBusinessService service) {
		super.setService(service);
	}
	
	public String getJsonDataName() {
		return jsonDataName;
	}

	public void setJsonDataName(String jsonDataName) {
		this.jsonDataName = jsonDataName;
	}

	public String getDropDownData() {
		return dropDownData;
	}
	
	public void setDropDownData(String dropDownData) {
		this.dropDownData = dropDownData;
	}
	
	public String getJsonData() {
		return jsonData;
	}
	
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	public String getTargetObjName() {
		return targetObjName;
	}
	
	public void setTargetObjName(String targetObjName) {
		this.targetObjName = targetObjName;
	}
	
	public String getTargetDmObjName() {
		return targetDmObjName;
	}
	
	public void setTargetDmObjName(String targetDmObjName) {
		this.targetDmObjName = targetDmObjName;
	}
	
	public String getItemIndex() {
		return itemIndex;
	}
	
	public void setItemIndex(String itemIndex) {
		this.itemIndex = itemIndex;
	}
	
	public String getDropDownSelectedCallback() {
		return dropDownSelectedCallback;
	}
	
	public void setDropDownSelectedCallback(String dropDownSelectedCallback) {
		this.dropDownSelectedCallback = dropDownSelectedCallback;
	}
	
	public Boolean getIsCleanText() {
		return isCleanText;
	}
	
	public void setIsCleanText(Boolean isCleanText) {
		this.isCleanText = isCleanText;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public String getInputSelInitFun() {
		return inputSelInitFun;
	}

	public void setInputSelInitFun(String inputSelInitFun) {
		this.inputSelInitFun = inputSelInitFun;
	}

}
