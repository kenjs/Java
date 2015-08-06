<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>车辆型号维护</title>

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
			var clxh = trim($("#mainForm_domain_clxh").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var cz=$("#mainForm_domain_cz").val();
			var tj=$("#mainForm_domain_tj").val();
			var cd=$("#mainForm_domain_cd").val();
			var kd=$("#mainForm_domain_kd").val();
			var gd=$("#mainForm_domain_gd").val();
			if(cz/1<0){
				showAlert("承重不可小于零！");
				return;
			}
			if(tj/1<0){
				showAlert("体积不可小于零！");
				return;
			}
			if(cd/1<0){
				showAlert("长度不可小于零！");
				return;
			}
			if(kd/1<0){
				showAlert("宽度不可小于零！");
				return;
			}
			if(gd/1<0){
				showAlert("高度不可小于零！");
				return;
			}
			var clxhwhDjxh = trim($("#mainForm_domain_clxhwhDjxh").val()); 

			var url = jcontextPath+"/zygl/qyclxhwh!save";  
	    	var jsonObj = {"domain.clxh":clxh,"domain.bzsm":bzsm,
                           "domain.clxhwhDjxh":clxhwhDjxh,"domain.cz":cz,"domain.tj":tj,"domain.cd":cd,
                           "domain.kd":kd,"domain.gd":gd};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function doSuccess(){
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.clxh","domain.bzsm","domain.cz","domain.tj","domain.cd","domain.kd","domain.gd"];
		var labelNameArray = ["车辆型号","备注说明","承重","体积","长度","宽度","高度"];
		var compareValueArray = [50,200,14.2,14.2,14.2,14.2,14.2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,false,false,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyclxhwh!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_clxhwhDjxh" value='<s:property value="domain.clxhwhDjxh"/>'/>
		<div class="pop_contc" style="height:280px; ">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>车辆型号：</td>
      				<td width="35%">
      					<s:textfield name="domain.clxh" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
      				<td width="15%" align="right">承重：</td>
     				<td width="35%">
     					<s:textfield name="domain.cz" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td> 
      			</tr>
      			<tr>
     				<td align="right">体积：</td>
     				<td>
     					<s:textfield name="domain.tj" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td>     
     				<td align="right">长度：</td>
     				<td>
     					<s:textfield name="domain.cd" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td> 				
     			</tr>
     				<tr>
     				<td align="right">宽度：</td>
     				<td>
     				<s:textfield name="domain.kd" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td>     
     				<td align="right">高度：</td>
     				<td>
     					<s:textfield name="domain.gd" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td> 				
     			</tr>
      			<tr>
      				<td align="right">备注说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="right">创建人：</td>
      				<td width="35%">
      					<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">创建日期：</td>
      				<td width="35%">
      					<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">修改人：</td>
      				<td>
      					<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">修改日期：</td>
      				<td>
      					<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
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
