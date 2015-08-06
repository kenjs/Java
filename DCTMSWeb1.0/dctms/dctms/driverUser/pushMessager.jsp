<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>推送信息初始化信息</title>
<script type="text/javascript">
	window.onload = function(){
	}
	function saveBtn(){
		var idList = document.getElementsByName("idList");
		var data ={};
		for(var i =0;i<idList.length;i++){
			data["driverUserInfoDomain.idList["+i+"]"]=idList[i].value;
		}
		var url = jcontextPath+"/pushDriverMessager";
		AjaxSubmit({
			url:url,
			data:data,
		    method:"post",
		    async:true,
		    success:function(text){
		    	callBackList(text);
		    }
		});
	}
	function callBackList(text){
		if(typeof text =='string'){
			alert(text);
			return;
		}
		var auditRemark = trim($("#mainForm_driverUserInfoDomain_auditRemark").val());
		if(confirm("发送成功!是否关闭页面")){
			doYesCallBack();
		}
	}
	//选择是的返回处理
	function doYesCallBack(){
		window.close();
	}
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/pushDriverMessager" namespace="/" method="post">


	 <table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px;  margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="确定发送" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px;  margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="关闭" type="button" /></td>
    		</tr>
		</table>
		
		<table class="mainTable" style="width: 775px" id="dataList">
				<tr class="mainTopTr">
					<td width="40" >序号</td>
					<td width="80">司机姓名</td>
					<td width="85">手机号码</td>
				</tr>
				<s:iterator id="datas" status="st" value="driverUserInfoDomain.dataList">
				<tr>
					<td><s:property value = "#st.index+1"/></td>
					<td><s:property value = "#datas.name"/></td>
					<td><s:property value = "#datas.code"/></td>
				</tr>
				<input type="hidden" name="idList" value='${datas.id}'></input>
				</s:iterator>
			
		</table>
</s:form>
</body>
</html>
