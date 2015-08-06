<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"webUserInfoDomain.code":trim(document.getElementById("mainForm_webUserInfoDomain_code").value),
				"webUserInfoDomain.name":trim(document.getElementById("mainForm_webUserInfoDomain_name").value),
				"webUserInfoDomain.companyName":trim(document.getElementById("mainForm_webUserInfoDomain_companyName").value),
				"webUserInfoDomain.mobilephone":trim(document.getElementById("mainForm_webUserInfoDomain_mobilephone").value),
				"webUserInfoDomain.submitType":trim(document.getElementById("mainForm_webUserInfoDomain_submitType").value),
				"webUserInfoDomain.enterpriseTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeQ").value),
				"webUserInfoDomain.enterpriseTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeZ").value),
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
		var totalPages = text.webUserInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.webUserInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.webUserInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["code","name","companyName","mobilephone","contactName","email","submitType","modifyTime"];
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditWebUserInfo(id){
		var url = jcontextPath+"/auditWebUserInfoMx?webUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:700px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
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
               <td >审核状态
               <s:select name="webUserInfoDomain.submitType" list="#{1:'已提交',0:'未提交',2:'未通过',3:'已通过'}" >
                </s:select>
                <td colspan="2">提审日期
                <s:textfield name="webUserInfoDomain.enterpriseTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="webUserInfoDomain.enterpriseTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                	<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="60">操作</td>
			<td width="100">登录名称</td>
			<td width="100">用户姓名</td>
			<td width="300">公司名称</td>
			<td width="100">手机号码</td>
			<td width="100">联系人</td>
			<td width="200">电子邮箱</td>
			<td width="200">认证是否通过</td>
			<td width="130">提审时间</td>
		</tr>
    </table>
</s:form>
</body>
</html>
