<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>回单登记</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		initRadio();		
		
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#sendBtn").click(function(){
			var wsDm="303004";//费用登记审批表
			var hdDjxh = $("#mainForm_domain_hdDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			scSend(wsDm,"",hdDjxh,oldWsspxh);
		});
		
		$("#mainForm_domain_szHwSl").blur(function(){
			//checkHwslWlss();
		});
		
		$("#mainForm_domain_szHwSl").change(function(){
			var bl = $("#mainForm_domain_szHwSl").val()/1 / $("#mainForm_domain_pcHwDomain_hwSl").val()/1;
			var jssl = $("#mainForm_domain_pcHwDomain_jsSl").val()/1 * bl;
			$("#mainForm_domain_szJsSl").val(jssl.toFixed(2));
			
			var zl = $("#mainForm_domain_pcHwDomain_hwZl").val()/1 * bl;
			$("#mainForm_domain_szHwZl").val(zl.toFixed(2));
			
			var tj = $("#mainForm_domain_pcHwDomain_hwTj").val()/1 * bl;
			$("#mainForm_domain_szHwTj").val(tj.toFixed(2));
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			//校验szhwsl
			var tage='';
			if (!checkHwslWlss()) {
				tage='Y';
			}
		     
			var hdDjxh = $("#mainForm_domain_hdDjxh").val();
			var pcDjxh = trim($("#mainForm_domain_pcDjxh").val()); 
			var wfhDjxh = trim($("#mainForm_domain_wfhDjxh").val()); 
			var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
			var xh = trim($("#mainForm_domain_xh").val()); 
			var szHwSl = trim($("#mainForm_domain_szHwSl").val()); 
			var szHwZl = trim($("#mainForm_domain_szHwZl").val()); 
			var szHwTj = trim($("#mainForm_domain_szHwTj").val()); 
			var yqDdrq = trim($("#mainForm_domain_yqDdrq").val()); 
			var hdjsrq = $("#mainForm_domain_hdjsrq").val();
			var shfsDm = $("[name='shfsDm']:checked").val();
			var szJsSl = trim($("#mainForm_domain_szJsSl").val()); 
			var hdbh = trim($("#mainForm_domain_hdbh").val()); 
			var bz = trim($("#mainForm_domain_bz").val());   
			
			var url = jcontextPath+"/hygl/hypchddj!save";  
	    	var jsonObj = {"domain.hdDjxh":hdDjxh,"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh,"domain.ddDjxh":ddDjxh,"domain.xh":xh,
	                    "domain.szHwSl":szHwSl,"domain.szHwZl":szHwZl,"domain.szHwTj":szHwTj,"domain.yqDdrq":yqDdrq,"domain.hdjsrq":hdjsrq,
	                    "domain.shfsDm":shfsDm,"domain.szJsSl":szJsSl,"domain.hdbh":hdbh,"domain.bz":bz};   			
			ajaxCommon(url,jsonObj,"closeWin");
			//如果有物流损失，往物流损失里插一条数据
			if(tage=='Y'){
				saveWlssDj();
			}
		});
	});
	
	function saveWlssDj(){
		var pcDjxh = trim($("#mainForm_domain_pcDjxh").val()); 
		var wfhDjxh = trim($("#mainForm_domain_wfhDjxh").val()); 
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
		
		
		var wlssHwSl = $("#mainForm_domain_wlssHwSl").val();
		 var wlssHwZl = $("#mainForm_domain_wlssHwZl").val();
	     var wlssHwTj = $("#mainForm_domain_wlssHwTj").val();

	     var zhwSl = $("#mainForm_domain_zhwSl").val();
	     var zhwZl=$("#mainForm_domain_zhwZl").val();
	     var zhwTj=$("#mainForm_domain_zhwTj").val();
	     
	     var szHwSl = trim($("#mainForm_domain_szHwSl").val());
	     var szHwZl = trim($("#mainForm_domain_szHwZl").val());
	     var szHwTj = trim($("#mainForm_domain_szHwTj").val());
	     
	     wlssHwSl = parseFloat(wlssHwSl);
	     wlssHwZl = parseFloat(wlssHwZl);
	     wlssHwTj = parseFloat(wlssHwTj);
	     szHwSl = parseFloat(szHwSl);
	     szHwZl = parseFloat(szHwZl);
	     szHwTj = parseFloat(szHwTj);
	     zhwSl = parseFloat(zhwSl);
	     zhwZl = parseFloat(zhwZl);
	     zhwTj = parseFloat(zhwTj);
	    
		var sl=zhwSl-(wlssHwSl+szHwSl);
		var zl=zhwZl-(wlssHwZl+szHwZl);
		var tj=zhwTj-(wlssHwTj+szHwTj);
		  
	     
		var url=jcontextPath+"/hygl/hypchddj!saveWlssDj";
		var jsonObj={"domain.ddDjxh":ddDjxh,"domain.wfhDjxh":wfhDjxh,"domain.pcDjxh":pcDjxh,
				      "domain.zhwSl":sl,"domain.zhwZl":zl,"domain.zhwTj":tj};
		ajaxCommon(url,jsonObj,"closeWin");
	}
	
	 function checkHwslWlss() {
		var wlssHwSl = $("#mainForm_domain_wlssHwSl").val();
		 var wlssHwZl = $("#mainForm_domain_wlssHwZl").val();
	     var wlssHwTj = $("#mainForm_domain_wlssHwTj").val();

	     var zhwSl = $("#mainForm_domain_zhwSl").val();
	     var zhwZl=$("#mainForm_domain_zhwZl").val();
	     var zhwTj=$("#mainForm_domain_zhwTj").val();
	     
	     var szHwSl = trim($("#mainForm_domain_szHwSl").val());
	     var szHwZl = trim($("#mainForm_domain_szHwZl").val());
	     var szHwTj = trim($("#mainForm_domain_szHwTj").val());
	     
	     
	     
	     wlssHwSl = parseFloat(wlssHwSl);
	     wlssHwZl = parseFloat(wlssHwZl);
	     wlssHwTj = parseFloat(wlssHwTj);
	     szHwSl = parseFloat(szHwSl);
	     szHwZl = parseFloat(szHwZl);
	     szHwTj = parseFloat(szHwTj);
	     zhwSl = parseFloat(zhwSl);
	     zhwZl = parseFloat(zhwZl);
	     zhwTj = parseFloat(zhwTj);
	     
	     if(zhwSl>(wlssHwSl+szHwSl)){
	   		//var sl=zhwSl-(wlssHwSl+szHwSl);
	   		//var zl=zhwZl-(wlssHwZl+szHwZl);
	   		//var tj=zhwTj-(wlssHwTj+szHwTj);
	   		 return false;
	     }
	     return true;
	}
	
	function initRadio() {
		if ($("#mainForm_domain_shfsDm").val() != "") {
			$("[name='shfsDm'][value='"+$("#mainForm_domain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='shfsDm']")[0].checked = true;
		}
		if ($("#mainForm_domain_pcHwDomain_shfsDm").val() != "") {
			$("[name='ddShfsDm'][value='"+$("#mainForm_domain_pcHwDomain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='ddShfsDm']")[0].checked = true;
		}
	}
	
	function saveSuc(data) {
		hideMessage();
		showAlert("保存成功！");
	}
	
	function closeWin() {
		showSuccess("保存成功！","closeW");
	}
	
	function closeW(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.hdbh","domain.szHwSl","domain.szHwZl","domain.szHwTj",
		                        "domain.yqDdrq","domain.szJsSl","domain.hdjsrq","domain.bz"];
		var labelNameArray = ["回单编号","实装数量","实装重量","实装体积",
		                      "要求到达日期","实装结算数量","回单接收日期","备注"];
		var compareValueArray = [100,12.2,12.2,12.2,
		                         11,12.2,11,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
		                     NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,NodeType.STRING];
		var notNullArray = [ false,false,false,false,
                            false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="hypchddj!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.hdDjxh"></s:hidden>
	<s:hidden name="domain.pcDjxh"></s:hidden>
	<s:hidden name="domain.wfhDjxh"></s:hidden>
	<s:hidden name="domain.ddDjxh"></s:hidden>
	<s:hidden name="domain.xh"></s:hidden>
	<s:hidden name="domain.shfsDm" />
	<s:hidden name="domain.pcHwDomain.shfsDm" />
	<s:hidden name="domain.wsspztDm" />
	<s:hidden name="domain.wsSpxh" />
	<s:hidden name="domain.wlssHwSl" />
	<s:hidden name="domain.wlssHwZl" />
	<s:hidden name="domain.wlssHwTj" />
	<s:hidden name="domain.zhwSl" />
	<s:hidden name="domain.zhwZl" />
	<s:hidden name="domain.zhwTj" />
	<s:hidden name="domain.wlssDjxh"></s:hidden>
		<div class="pop_contc">
		<fieldset>
			<legend>实收信息</legend>
			<table width="525" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="105" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">货物名称：</td>
      				<td colspan="3">
      					<s:textfield name="domain.pcHwDomain.hwmc" cssClass="pop_input noborder bgstyle_readonly" />
      				</td>
      				<td colspan="2" align="right">回单编号：</td>
      				<td colspan="3">
      					<s:textfield name="domain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">要求到货日期：</td>
	  				<td colspan="2">
	  					<input type="text" name="domain.yqDdrq" id="mainForm_domain_yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />" class="ymdate"  />
	  				</td>
	  				<td colspan="2" align="right">回单接收日期：</td>
	  				<td colspan="4">
	  					<input type="text" name="domain.hdjsrq" id="mainForm_domain_hdjsrq" value="<s:date name="domain.hdjsrq" format="yyyy-MM-dd" />" class="ymdate"  />
	  				</td>
      			</tr>
      			<tr>
      				<s:hidden name="domain.szJsSl"></s:hidden>
      				<td align="right">收货方式：</td>
	  				<td colspan="5">
	  					<s:radio name="shfsDm" list="#{'1':' 自提','2':' 送货到门' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
      				<td align="right">实收数量：</td>
      				<td>
      					<s:textfield name="domain.szHwSl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">实收重量：</td>
      				<td>
      					<s:textfield name="domain.szHwZl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">实收体积：</td>
      				<td>
      					<s:textfield name="domain.szHwTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      			</tr>
	  			<tr>
	  				<td align="right">备注：</td>
	  				<td colspan="8">
	  					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional"></s:textarea>
	  				</td>
	  			</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>订单信息</legend>
			<table width="525" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="105" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">回单编号：</td>
      				<td colspan="3">
      					<s:textfield name="domain.pcHwDomain.hdbh" cssClass="pop_input noborder bgstyle_readonly" />
      				</td>
      			</tr>
      			<tr>
      			<s:hidden name="domain.pcHwDomain.jsSl"></s:hidden>
      				<td align="right">要求到货日期：</td>
	  				<td colspan="2">
	  					<input type="text" name="domain.pcHwDomain.yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />"  class="pop_input noborder bgstyle_readonly" />
	  				</td>
      				<td align="right">收货方式：</td>
	  				<td colspan="5">
	  					<s:radio name="ddShfsDm" list="#{'1':' 自提','2':' 送货到门' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      			</tr>
	  			
			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			 	<s:if test='domain.spbz == "Y" && (domain.wsspztDm == "0" || domain.wsspztDm == "2")'>
				 	<button type="button" class="pop_btnbg" id="sendBtn">发送审批</button>
				 	&nbsp;
			 	</s:if>
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
