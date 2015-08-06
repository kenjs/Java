<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-发票开票登记明细</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		//alert($("#hidKpr").val());	
		$("#mainForm_domain_kprCzyDjxh").val($("#hidKpr").val());	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
			var kpsqfs = trim($("#mainForm_domain_kpsqfs").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var fpkjje = trim($("#mainForm_domain_fpkjje").val()); 
			var wkjje = trim($("#mainForm_domain_wkjje").val()); 
			if(wkjje/1==0){
				showAlert("未开具金额已为零！");
				return;
			}
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			
			var fpdm = trim($("#mainForm_domain_fpdm").val()); 
			var fphm = trim($("#mainForm_domain_fphm").val()); 
			var kprCzyDjxh = trim($("#mainForm_domain_kprCzyDjxh").val()); 
			var fpdmAndFphm = trim($("#mainForm_domain_fpdmAndFphm").val()); 
			var kpje = trim($("#mainForm_domain_kpje").val());
			if(kpje/1<=0){
				showAlert("开票金额必须大于零！");
				return;
			}
			if(kpje/1>wkjje/1){
				showAlert("开票金额不能大于未开具金额！");
				return;
			}
			var sl = trim($("#mainForm_domain_sl").val());
			if(!isNumber(sl)){
				showAlert("税率必须录入数字（0-1之间）！");
				return;
			}
			if(sl/1<0||sl/1>1){
				showAlert("税率在0-1之间！");
				return;
			}
			var kprq = trim($("#mainForm_domain_kprq").val());

			var djJgbm = trim($("#mainForm_domain_djJgbm").val());
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());

			var rtnCode = trim($("#mainForm_domain_rtnCode").val());
			var errMesge = trim($("#mainForm_domain_errMesge").val());
			
			var url = jcontextPath+"/cwfpkpdj!save";  
	    	var jsonObj = {"domain.kpsqDjxh":kpsqDjxh,"domain.kpsqfs":kpsqfs,"domain.khDjxh":khDjxh,
                           "domain.fpkjje":fpkjje,"domain.wkjje":wkjje,"domain.bzsm":bzsm,"domain.fpdm":fpdm,
                           "domain.fphm":fphm,"domain.kprCzyDjxh":kprCzyDjxh,"domain.kpje":kpje,"domain.sl":sl,
                           "domain.kprq":kprq,"domain.djJgbm":djJgbm, "domain.ssJgbm":ssJgbm
                           };
			ajaxCommon(url,jsonObj,"saveCheck");
		});
	});
	
	
	function saveCheck(data){
		if(data.domain.fpdmAndFphm == "true"){
			showAlert("发票代码和发票号码不能与现有数据完全一致!");
			return ;
		}
<!--		if(data.domain.rtnCode != 0){-->
<!--			showAlert(data.domain.errMesge);-->
<!--			return ;-->
<!--		}-->
		showConfirm("确认信息：" + "<br/>" + 
					"发票代码：" + trim($("#mainForm_domain_fpdm").val()) + "<br/>"
				  + "发票号码：" + trim($("#mainForm_domain_fphm").val()) , "saveOK");
	}
	
	function saveOK(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	function checkdata(){

		var controlNameArray = ["domain.fpdm", "domain.fphm", "domain.kpje"];
		var labelNameArray = ["发票代码", "发票号码", "开票金额"];
		var compareValueArray = [12, 10, 14.2];
		var nodeTypeArray = [NodeType.STRING, NodeType.INTEGER,
							 NodeType.DECIMAL
							 ];
		var notNullArray = [true, true, false];

		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function isNumber(oNum) { 
  		if(!oNum) return false; 
  		var strP=/^\d+(\.\d+)?$/; 
 		if(!strP.test(oNum)) 
 			return false; 
  		try{ 
  			if(parseFloat(oNum)!=oNum) return false; 
  			} 
  		catch(ex) { 
   			return false; 
  		} 
  		return true; 
   }
</script>
</head>

<body >
<%try{ %>
<s:form action="cwfpkpdj!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_kpsqDjxh" value='<s:property value="domain.kpsqDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_khDjxh" value='<s:property value="domain.khDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_djJgbm" value='<s:property value="domain.djJgbm"/>'/>
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	<input type="hidden" id="hidKpr" value='<s:property value="domain.kprCzyDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_fpdmAndFphm" value='<s:property value="domain.fpdmAndFphm"/>'/>
	<input type="hidden" id="mainForm_domain_rtnCode" value='<s:property value="domain.rtnCode"/>'/>
	<input type="hidden" id="mainForm_domain_errMesge" value='<s:property value="domain.errMesge"/>'/>
		<div class="pop_contc" style="height:330px; overflow:auto;">
		<fieldset>
    	 	 <legend>申请信息</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>	      				
		      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>开票申请方式：</td>
		      			<td width="32%">
		      				<s:textfield name="domain.kpsqfs" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
		      			</td>
		      			<td  align="right" width="18%"></td>
	      				<td width="32%">	      					
	      				</td>
      				</tr>
    	 	 	  	<tr>
	      				<td  align="right" width="18%"><font class="font_red" id="font2"></font>申请开票金额：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.sqKpjeHj" rows="3" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td  align="right" width="18%"><font class="font_red" id="font2"></font>已开具金额：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.fpkjje" rows="3" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
      				</tr>
    	 	 	  	<tr>
	      				<td  align="right" width="18%"><font class="font_red" id="font2"></font>未开具金额：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.wkjje" rows="3" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td  align="right" width="18%"><font class="font_red"  id="font2"></font>申请人：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.sqrMc" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
      				</tr>
      				<tr>
	      				<td  align="right" width="18%"><font class="font_red" id="font2"></font>申请部门：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.bmMc" rows="3" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td  align="right" width="18%"><font class="font_red"  id="font2"></font>申请时间：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.djrq" cssClass="pop_input bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
      				</tr>
      				<tr>
		      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>备注说明：</td>
		      			<td width="82%" colspan="3">
		      				<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
		      			</td>
      				</tr>
    	 	 	  </table>
    	 </fieldset>
      	<fieldset>
      		<legend>开票信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font><font class="font_red"  id="font4">*</font>发票代码：</td>
	      			<td width="32%">
	      				<s:textfield name="domain.fpdm" cssClass="pop_input bgstyle_required"></s:textfield>
	      			</td>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font><font class="font_red"  id="font4">*</font>发票号码：</td>
	      			<td width="32%">
	      				<s:textfield name="domain.fphm" cssClass="pop_input bgstyle_required"></s:textfield>
	      			</td>
	      		</tr>
				<tr>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>开票金额：</td>
	      			<td width="32%">
	      				<s:textfield name="domain.kpje" cssClass="pop_input bgstyle_required"></s:textfield>
	      			</td>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>税率：</td>
	      			<td width="32%">
	      				<s:textfield name="domain.sl" cssClass="pop_input bgstyle_required"></s:textfield>
	      			</td>
	      		</tr>
				<tr>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>开票人：</td>
	      			<td width="32%">
	      				<sys:GsryList myName="domain.kprCzyDjxh" myId="mainForm_domain_kprCzyDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:GsryList>
	      			</td>
	      			<td  align="right" width="18%"><font class="font_red"  id="font2"></font>开票日期：</td>
	      			<td width="32%">
	      				<sys:dateCurrentDayTag myName="domain.kprq" myId="mainForm_domain_kprq" myClass="ymdate" />
	      			</td>
	      		</tr>
			</table>
			</fieldset>
		<div class="pop_btn">
			   <button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
				 	&nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div> 
		</div>

	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
