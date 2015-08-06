<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>核销明细</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
  $(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
			var ysqKpje = $("#sqKpje").val();//已申请开票金额
			var sqKpje = $("#mainForm_domain_sqKpje").val();
			var wsqKpJe = $("#mainForm_domain_wsqKpJe").val();
			var bzsm = $("#mainForm_domain_bzsm").val(); 


            var url = jcontextPath+"/hygl/jskpdzqd!save";  
	        var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.kpsqDjxh":kpsqDjxh,"domain.sqKpje":sqKpje,"domain.bzsm":bzsm};
	        var ysqje = parseFloat(ysqKpje);
	        var sqje = parseFloat(sqKpje);
			var wsqJe = parseFloat(wsqKpJe);
	        if(sqje<=ysqje){
	           showAlert("核销金额不可小于等于已申请金额！");
	           return;
	        }else if(sqje>ysqje+wsqJe){
	           showAlert("核销金额不可大于总申请金额！");
	           return;
	        }
	        ajaxCommon(url,jsonObj,"YesSave");
		});	
    })


	function YesSave() {
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
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpdzqd!initHxMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
<input type="hidden" id="sqKpje" value="<s:property value="domain.sqKpje"/>"/>
		<div class="pop_contc" style="height: 300px">
		<fieldset>
        	  <legend>对帐清单</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>选择的清单：</td>
     				<td width="80%">
     				    <s:select list="domain.dataList" name="domain.qdDjxh" listKey="qdDjxh" listValue="queryName"  cssClass="pop_input_colspan2  bgstyle_required" disabled = "true"></s:select>
     				</td>
      			</tr>
      			</table>
        </fieldset>
		<fieldset>
		<legend>清单信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right">清单名称：</td>
     				<td width="25%" >
     				    <s:textfield name="domain.qdmc" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td width="25%" align="right">合计金额：</td>
     				<td width="25%" >
     				    <s:textfield name="domain.heJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">已申请开票金额：</td>
     				<td>
     					<s:textfield name="domain.ysqKpJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td align="right">未申请开票金额：</td>
     				<td>
     				    <s:textfield name="domain.wsqKpJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>核销信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr style="display: none;">
     				<td width="25%" align="right">清单序号：</td>
     				<td width="25%" >
     				    <s:textfield name="domain.qdDjxh" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td width="25%" align="right">开票登记序号：</td>
     				<td width="25%" >
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>
     			<tr>
     				<td width="25%" align="right"><font class="font_red">*</font>核销金额：</td>
     				<td width="25%">
                       <s:textfield name="domain.sqKpje" cssClass="pop_input bgstyle_required" />     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">备注说明：</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
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