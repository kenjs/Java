<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>结算对账清单核销</title>

<style type="text/css">
html,body {background:none;}
.in {width:100%;height:18px;border:0;background:transparent;text-align:center;}
</style>
<base target="_self" />
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var qdDjxh = $("#qdDjxh").val();
			if(qdDjxh==""||qdDjxh==null){
				showAlert("清单必须选择！");
				return;
			}
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
			var sqKpje = $("#sqKpje").val();
			var wsqKpJe = $("#wsqKpJe").val();
			var bzsm = $("#mainForm_domain_bzsm").val(); 
			var xhStr = $("#mainForm_domain_xhStr").val(); 

            var url = jcontextPath+"/hygl/jskpdzqd!save";  
	        var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.kpsqDjxh":kpsqDjxh,"domain.sqKpje":sqKpje,"domain.bzsm":bzsm};
	        
	        var flag = "";
            var strs=xhStr.split(","); //字符分割   
            var sqje = parseFloat(sqKpje);
			var wsqJe = parseFloat(wsqKpJe);

			for (i=0;i<strs.length ;i++ ){
			    if(qdDjxh == strs[i]){			       
			      flag = "1";
			    } 
			 }
			 if(flag == "1"){
			    var str = "该清单已存在核销记录，不允许新增！<br />可以点击清单名称进行修改。";
			    showAlert(str);
			    return;
			 }
			 if(sqje<=0){
	           showAlert("核销金额不可小于等于 0 ！");
	           return;
	        }else if(sqje>wsqJe){
	           showAlert("核销金额不可大于未申请金额！");
	           return;
	        }
			  ajaxCommon(url,jsonObj,"YesSave");	
		});
	})
	function YesSave(obj){
	    showAlert("保存成功！", "closeWin");
	}
	function closeWin() {
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.sqKpje","domain.bzsm"];
		var labelNameArray = ["申请开票金额","备注说明"];
		var compareValueArray = [16.2,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function showQdxq(){
	    var qdDjxh = $("#mainForm_domain_qdDjxh").val();
	    doMyRefresh(qdDjxh);
	}
	//刷新弹窗
	function doMyRefresh(qdDjxh){
	   var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val(); 
	   var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
	   var khDjxh = $("#mainForm_domain_khDjxh").val(); 
	   var url = jcontextPath+"/hygl/jskpdzqd!initAddHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh+"&domain.qdDjxh="+qdDjxh;
	   reload.href = url;
	   reload.click();
	}
	//修改对账清单
	function onUpdateDzqd(qdDjxh){
	 var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
	 var url = jcontextPath+"/hygl/jskpdzqd!initHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.qdDjxh="+qdDjxh;
     window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
	}
</script>
</head>
<body>
<%try{ %>
<s:form action="jskpdzqd!initAddHxMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.kpsqDjxh"></s:hidden>
	<s:hidden name="domain.xhStr"></s:hidden>
	<input type="hidden" id="mainForm_zt"/>
	<div  style="display:none"><a id="reload" href="">reload</a></div>
	<div class="pop_contc" style="height:360px; overflow:auto;">
	<fieldset>
        	  <legend>对帐清单</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>请选择清单：</td>
     				<td width="80%">
     				    <s:select list="domain.dataList" name="domain.qdDjxh" listKey="qdDjxh" listValue="queryName"  cssClass="pop_input_colspan2  bgstyle_required" onchange="showQdxq()"></s:select>
     				</td>
      			</tr>
      			</table>
    </fieldset>
    <s:if test="domain.dataList.size==0">
    <fieldset>
		<legend>清单信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right">清单名称：</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="qdmc" readonly="readonly" value="<s:property value="#qd.qdmc"/>"/>
     				</td>
     				<td width="25%" align="right">合计金额：</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="heJe" readonly="readonly" value="<s:property value="#qd.heJe"/>"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">已申请开票金额：</td>
     				<td>
     					<input type="text" class="pop_input bgstyle_readonly" name=ysqKpJe readonly="readonly" value="<s:property value="#qd.ysqKpJe"/>"/>
     				</td>
     				<td align="right">未申请开票金额：</td>
     				<td>
     				    <input type="text" class="pop_input bgstyle_readonly" name="wsqKpJe" id="wsqKpJe" readonly="readonly" value="<s:property value="#qd.wsqKpJe"/>"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>核销信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     			</tr>
				<tr>
     				<td  align="right"><font class="font_red">*</font>核销金额：</td>
     				<td  colspan="3">
                       <input type="text" class="pop_input bgstyle_required" name="sqKpje" id="sqKpje" value="<s:property value="#qd.wsqKpJe"/>"/>     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">备注说明：</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
     				</td>
     			</tr>	
		
				<tr>
     				<td width="25%" align="right" style="display: none;">清单序号：</td>
     				<td width="25%" style="display: none;">
     				    <input type="text" class="pop_input bgstyle_readonly" id="qdDjxh" name="qdDjxh" readonly="readonly" value="<s:property value="#qd.qdDjxh"/>"/>
     				</td>
     				<td width="25%" align="right" style="display: none;">开票登记序号：</td>
     				<td width="25%" style="display: none;">
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>	
		</table>
		</fieldset>
	</s:if>
    <s:iterator id="qd" value="domain.dataList" status="sta">
    <s:if test="domain.qdDjxh==#qd.qdDjxh">
    <fieldset>
		<legend>清单信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right" >清单名称：</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="qdmc" readonly="readonly" value="<s:property value="#qd.qdmc"/>"/>
     				</td>
     				<td width="25%" align="right">合计金额：</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="heJe" readonly="readonly" value="<s:property value="#qd.heJe"/>"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">已申请开票金额：</td>
     				<td>
     					<input type="text" class="pop_input bgstyle_readonly" name=ysqKpJe readonly="readonly" value="<s:property value="#qd.ysqKpJe"/>"/>
     				</td>
     				<td align="right">未申请开票金额：</td>
     				<td>
     				    <input type="text" class="pop_input bgstyle_readonly" name="wsqKpJe" id="wsqKpJe" readonly="readonly" value="<s:property value="#qd.wsqKpJe"/>"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>核销信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     			</tr>
				<tr>
     				<td  align="right"><font class="font_red">*</font>核销金额：</td>
     				<td  colspan="3">
                       <input type="text" class="pop_input bgstyle_required" name="sqKpje" id="sqKpje" value="<s:property value="#qd.wsqKpJe"/>"/>     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">备注说明：</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
     				</td>
     			</tr>	
		
				<tr>
     				<td width="25%" align="right" style="display: none;">清单序号：</td>
     				<td width="25%" style="display: none;">
     				    <input type="text" class="pop_input bgstyle_readonly" id="qdDjxh" name="qdDjxh" readonly="readonly" value="<s:property value="#qd.qdDjxh"/>"/>
     				</td>
     				<td width="25%" align="right" style="display: none;">开票登记序号：</td>
     				<td width="25%" style="display: none;">
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>
     	</table>	
		</fieldset>
		</s:if>
	
		</s:iterator>
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