<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业</title>
<script type="text/javascript">
	window.onload=function(){
		//初始化时间获取当天和这个月的第一天
		var time = new Date();
		var year = time.getFullYear();
		var month = time.getMonth() +1;
		var date = time.getDate();
		if(month<10)
			month = "0" + month;
		if(date<10)
			date = "0" + date;
		var last = year +"-"+ month +"-" +date;
		var first = year + "-" + month + "-" + "01";
		document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value=last;
		document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value=last;
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"webUserInfoDomain.code":trim(document.getElementById("mainForm_webUserInfoDomain_code").value),
				"webUserInfoDomain.name":trim(document.getElementById("mainForm_webUserInfoDomain_name").value),
				"webUserInfoDomain.companyName":trim(document.getElementById("mainForm_webUserInfoDomain_companyName").value),
				"webUserInfoDomain.mobilephone":trim(document.getElementById("mainForm_webUserInfoDomain_mobilephone").value),
				"webUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value),
				"webUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value)
		};
		var url = jcontextPath+"/queryWebUserInfo";
		AjaxSubmit({
			url:url,
			data:data,
		    method:"get",
		    async:true,
		    success:function(text){
		    	callBackList(text);
		    }
		});
	}
	function callBackList(text){
		var dataList = text.webUserInfoDomain.dataList;
        var columus =["code","name","companyName","mobilephone","contactName","email","submitType","modifyTime"];
		showPageTable(dataList,columus,1,0);
	}
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportWebUserInfo" namespace="/" method="post">



<table  class="conditionTable">
             <tr>            
                <td width="200px">登录名称<s:textfield name="webUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">用户姓名<s:textfield name="webUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">公司名称<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">手机号码<s:textfield name="webUserInfoDomain.mobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
                <td colspan="2">注册时间
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                	<input type="button" onclick="queryInfo();" value="查询" class="selectBtn"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 755px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >序号</td>
			<td width="80">登录名称</td>
			<td width="80">姓名</td>
			<td width="140">公司名称</td>
			<td width="85">手机号码</td>
			<td width="60">联系人</td>
			<td width="80">电子邮箱</td>
			<td width="60">是否认证</td>
			<td width="90">提审时间</td>
			
			
		</tr>
    </table>
</s:form>
</body>
</html>
