<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>������λά��</title>
<style type="text/css">
html,body {overflow:hidden;}
.hsbl {width:90%;height:80%;text-align:center;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$(":checkbox[name='jbdwbz']").click(function(){
			if($(this).attr("checked")=="checked"){
				$(this).parent().next("td").children().val("1.0");
			}
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			if(!checkNum()){
				showAlert("������λ��־ֻ��ѡ��һ����");
				return ;
			}
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			var nu = $(":checkbox[name='xy']");
			var cb = $(":checkbox[name='jbdwbz']");
			var hsbls = $(":text[name='hsbl']");
			var strObj = "";
			var jbdwbz = "";
			var qybz = "";
			var hsbl = "";
			for(var i=0;i<nu.length;i++){
				var tRows = document.getElementById("zbTab").rows;
				var jldwDm = tRows[i+1].cells[2].innerHTML; 
				hsbl = hsbls[i].value;
				if(cb[i].checked){
					jbdwbz = "Y";
				}else{
					jbdwbz = "N";
				}	
				if(nu[i].checked){
					qybz = "Y";
					if(hsbl/1<0){
						showAlert("ת����������С���㣡");
						return;
					}
				}else{
					qybz = "N";
				}	
				strObj += hsbl+","+qybz+","+jbdwbz+","+jldwDm+"|";
			}	
			
			var url = jcontextPath+"/zygl/qyjcbmjldw!save";
			var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.strObj":strObj};
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function doSuccess(){
		showAlert("����ɹ���","yesCallBack");
	}
	
	function yesCallBack(){
		window.close();
	}
	
	function checkNum(){
		var num = 0;
		var chos = $(":checkbox[name='jbdwbz']");
		for(var i=0;i<chos.length;i++){
			if(chos[i].checked){
				++num;
			}
		}
		//alert(num);
		if(num != "1"){
			return false;
		}else{
			return true;
		}
	}
	
	function checkdata(){
		var controlNameArray = ["domain.hsbl"];
		var labelNameArray = ["�������"];
		var compareValueArray = [20];
		var nodeTypeArray = [NodeType.DECIMAL];
		var notNullArray = [false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%
try {
%>
<s:form action="qyjcbmjldw!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	
	<div class="pop_contc" style="height:320px; overflow:auto;">
	<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
	      <tr>
	        <th width="5%">����</th>
	        <th width="5%">���</th>
	        <th width="20%">������λ����</th>
	        <th width="20%">������λ����</th>
	        <th width="20%">������λ��־</th>
	        <th width="20%">�������λ�������</th>
	      </tr>
	      <s:iterator id="zb" value="domain.jldwList" status="i">
	      	<tr>
		        <td align="center">
		        	<s:if test='#zb.qybz==\"Y\"'>
		        		<s:checkbox checked="true" name="xy"></s:checkbox>
		        	</s:if>
		        	<s:else>
		        		<s:checkbox name="xy"></s:checkbox>
		        	</s:else>
		        </td>
		        <td align="center"><s:property value="#i.index+1"/></td>
		        <td align="center"><s:property value="#zb.jldwDm"/></td>
		        <td align="center"><s:property value="#zb.jldwMc"/></td>
		        <td align="center">
		        	<s:if test='#zb.jbdwbz==\"Y\"'>
		        		<s:checkbox checked="true" name="jbdwbz" id="jbdwbz"></s:checkbox>
		        	</s:if>
		        	<s:else>
		        		<s:checkbox name="jbdwbz" id="jbdwbz" ></s:checkbox>
		        	</s:else>
		        </td>
		        <td align="center">
		        	<input type="text" name="hsbl" value='<s:property value="#zb.hsbl"/>' class="hsbl"/>
				</td>
	      	</tr>
	      </s:iterator>
	    </table>
	<div class="pop_btn">
	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
	&nbsp;
	<button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	</div>
	</div>
	<%@include file="/common/message.jsp"%>
</s:form>
<%} catch (Exception e) {
	e.printStackTrace();
	throw e;
}
%>
</body>
</html>

