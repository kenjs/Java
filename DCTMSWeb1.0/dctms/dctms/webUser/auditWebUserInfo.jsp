<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
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
				"webUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"webUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/auditWebUserInfoList";
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
		var operateObject ={};
        var checkbox = {"name":"webUserInfoDomain.IdList","value":"id"};
		if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="0"){
			document.getElementById("operationTitle").style.display="none";
			document.getElementById("checkAllTitle").style.display="";//为兼容IE和火狐不用block
			//显示全选后，并初始化选择值为空
			document.getElementById("checkAll").checked = false;
			//显示俩按钮
<%--			document.getElementById("selectPushBtn").style.display="";--%>
<%--			document.getElementById("selectPushAllBtn").style.display="";--%>
			document.getElementById("selectPushBtn").style.display="none";
			document.getElementById("selectPushAllBtn").style.display="none";
			showPageTable(dataList,columus,curPageNos,0,{},{},checkbox);
			pageInfo(totalPages,curPageNos,totalRecords);
			return;
		}else{
			document.getElementById("checkAllTitle").style.display="none";
			document.getElementById("operationTitle").style.display="";
			document.getElementById("selectPushBtn").style.display="none";
			document.getElementById("selectPushAllBtn").style.display="none";
		}
		if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="1"){
        	operateObject = {'auditWebUserInfo':'审核'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="2"){
			operateObject = {'auditWebUserInfo':'重新审核'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="3"){
			operateObject = {'auditWebUserInfo':'重新审核'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="0"){
			operateObject = {};
		}
		
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditWebUserInfo(id){
		var url = jcontextPath+"/auditWebUserInfoMx?webUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:700px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
	} 
	//发送消息
	function sendMessage(){
		var idList = document.getElementsByName("webUserInfoDomain.idList");
		var url = jcontextPath+"/sendWebMessagerInit";
		var arr = [];
		var isZeroFlag = true;
		for(var i=0;i<idList.length;i++){
			if(idList[i].checked){
				isZeroFlag =false;
				arr.push("webUserInfoDomain.idList["+i+"]="+idList[i].value);
			}
		}
		if(isZeroFlag){
			alert("请选择要发送的司机");
			return;
		}
		var str = arr.join("&");
		url += "?webUserInfoDomain.isPushAll="+0; 
		url += "&" +str;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
	}
	//发送消息
	function sendAllMessage(){
		var url = jcontextPath+"/sendWebMessagerInit";
		url += "?webUserInfoDomain.isPushAll="+1; 
		url += "&webUserInfoDomain.submitType="+ document.getElementById("mainForm_webUserInfoDomain_submitType").value;
		url += "&webUserInfoDomain.code="+ document.getElementById("mainForm_webUserInfoDomain_code").value;
		url += "&webUserInfoDomain.name="+ encodeURI(encodeURI(document.getElementById("mainForm_webUserInfoDomain_name").value));
		url += "&webUserInfoDomain.companyName="+ encodeURI(encodeURI(document.getElementById("mainForm_webUserInfoDomain_companyName").value));
		url += "&webUserInfoDomain.mobilephone="+ document.getElementById("mainForm_webUserInfoDomain_mobilephone").value;
		url += "&webUserInfoDomain.enterpriseTimeQ="+ document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeQ").value;
		url += "&webUserInfoDomain.enterpriseTimeZ="+ document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeZ").value;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
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
             		  <input type="button" onclick="sendMessage();" value="选择通知" class="selectBtn" id="selectPushBtn" style="display:none"/>
	                 <input type="button" onclick="sendAllMessage();" value="全部通知" class="selectBtn" id="selectPushAllBtn" style="display:none"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 755px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >序号</td>
			<td width="70" id="checkAllTitle"><input type="checkbox" id="checkAll" onclick="oncheckAll('checkAll','webUserInfoDomain.idList')"/></td>
			<td width="70" id="operationTitle">操作</td>
			<td width="80">登录名称</td>
			<td width="80">姓名</td>
			<td width="120">公司名称</td>
			<td width="85">手机号码</td>
			<td width="60">联系人</td>
			<td width="80">电子邮箱</td>
			<td width="60">是否认证</td>
			<td width="90">提审时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' value='${webUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo'  value='${webUserInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='webUserInfoDomain.pageInfo.pageSize' value='${webUserInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
