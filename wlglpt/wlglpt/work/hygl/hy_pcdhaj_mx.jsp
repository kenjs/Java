<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>电话安检</title>

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
			var url = jcontextPath+"/hypcdhaj!save"
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.ajDhhm","domain.pcJgmc","domain.pcrCzyMc","domain.ajjgDm",
	    					   "domain.pcrq","domain.ajQkms","domain.bz"];
		var labelNameArray = ["安检电话号码","安检部门","安检人",
		                      "安检结果代码","安检日期","安检情况描述","备注"];
		var compareValueArray = [20,50,200,
			                     1,50,500,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.INTEGER,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,
                            true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="hypcdhaj!initMx" namespace="" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.pcDjxh"></s:hidden>
		
		<div class="pop_contc" style="height:700px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>安检电话号码</td>
      				<td width="35%">
      					<s:textfield name="domain.ajDhhm" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      				<td width="15%" align="right"><font class="font_red">*</font>安检部门</td>
      				<td width="35%">
      					<s:textfield name="domain.pcJgmc" cssClass="pop_input noborder bgstyle_optional" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>安检人</td>
      				<td>
      					<s:textfield name="domain.pcrCzyMc" cssClass="pop_input noborder bgstyle_optional" readonly="true"></s:textfield>
      				</td>
      				<td align="right"><font class="font_red">*</font>安检结果</td>
      				<td>
      					<sys:Ajjg myName="domain.ajjgDm" myId="mainForm_domain_ajjgDm" contaisQxz="true" myClass="select"></sys:Ajjg>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>安检日期</td>
      				<td>
      					<s:textfield name="domain.pcrq" cssClass="ymdate" ></s:textfield>
      				</td>
      				<td align="right"><font class="font_red">*</font>所属机构</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input noborder bgstyle_optional" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">安检情况描述</td>
      				<td colspan="3">
      					<s:textarea name="domain.ajQkms" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
      		
			</table>
			</fieldset>
			
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		    
			<fieldset>
				<legend>派车信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
				  <tr>
	  				<td align="center" width="13%">派车单号</td>
	  				<td width="20%">
	  					<s:property value="domain.pcdh"/>
	  				</td>
	  				<td align="center">司机姓名</td>
	  				<td>
	  					<s:property value="domain.sjXm"/>
	  				</td>
	  			 </tr>
	  			 <tr>
	  				<td align="center">车辆号码</td>
	  				<td>
	  					<s:property value="domain.clhm"/>
	  				</td>
	  				<td align="center">挂车号码</td>
	  				<td>
	  					<s:property value="domain.gchm"/>
	  				</td>
	  				
	  			</tr>
	  		
	  			<tr>
	  			    <td align="center">派车人</td>
	  				<td>
	  					<s:property value="domain.pcrCzyMc"/>
	  				</td>
	  				<td align="center">派车日期</td>
	  				<td>
	  					<s:property value="domain.pcrq"/>
	  				</td>
	  			
	  			</tr>
	  			<tr>
	  					<td align="center">派车部门</td>
	  				<td>
	  					<s:property value="domain.pcJgmc"/>
	  				</td>
	  				<td align="center">所属机构</td>
	  				<td>
	  					<s:property value="domain.ssJgmc"/>
	  				</td>
	  			</tr>
    		</table>
    		</fieldset>
			安检电话信息：
			<table id="zTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="5%">序号</th>
			        <th width="15%">安检电话</th>
			        <th width="15%">安检结果</th>
			        <th width="15%">安检人</th>
			        <th width="15%">安检日期</th>
			        <th width="15%">安检情况描述</th>
			        <th width="20%">备注</th>
			      </tr>
			      <s:iterator id="zb" value="domain.ajdhList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center"><s:property value="#zb.ajDhhm"/></td>
				        <td align="center"><s:property value="#zb.ajjg"/></td>
				        <td align="center"><s:property value="#zb.pcrCzyMc"/></td>
				        <td align="center"><s:property value="#zb.pcrq"/></td>
				        <td align="center"><s:property value="#zb.ajQkms"/></td>
				        <td align="center"><s:property value="#zb.bz"/></td>
			      	</tr>
			      </s:iterator>
    		</table>
    		
    	
				安检照片信息：
			<table id="bTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      
			  
			      	<tr>
			      		<div style="width: 760px">
			      	  		 <s:iterator id="zb" value="domain.ajzpList" status="i">
			      	   			<img src="work/hygl/zpaj_photo.jsp?photo_no=<s:property value="#zb.ajDjxh"/>" alt="<s:property value="#zb.pcrq"/>" width="100px" height="120px" />
			      	   		</s:iterator>
				        </div>
				    </tr>
			     
    		</table>
    	
			
		</div>
	
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
