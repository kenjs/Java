<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询身份证验证申请</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"applyInfoDomain.companyId":trim(document.getElementById("mainForm_applyInfoDomain_companyId").value),
				"applyInfoDomain.applyType":trim(document.getElementById("mainForm_applyInfoDomain_applyType").value),
				"applyInfoDomain.applyTimeQ":trim(document.getElementById("mainForm_applyInfoDomain_applyTimeQ").value),
				"applyInfoDomain.applyTimeZ":trim(document.getElementById("mainForm_applyInfoDomain_applyTimeZ").value),
				"applyInfoDomain.contactName":trim(document.getElementById("mainForm_applyInfoDomain_contactName").value),
				"applyInfoDomain.verifyStart":trim(document.getElementById("mainForm_applyInfoDomain_verifyStart").value),
				"applyInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"applyInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryApplyInfo";
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
		var dataList = text.applyInfoDomain.dataList;
		var totalPages = text.applyInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.applyInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.applyInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["companyId","applyType","applyTime","contactName","contactTelephone","applyComment","verifyTime","verifyStart","verifyComment"];
		var operateObject = {'auditApplyInfo':'审核'};
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditApplyInfo(id){
		var url = jcontextPath+"/queryApplyInfoMx?applyInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportApplyInfo" namespace="/" method="post">



<table  class="conditionTable">
             <tr>            
                <td width="200px">公司<s:textfield name="applyInfoDomain.companyId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">姓名<s:textfield name="applyInfoDomain.contactName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
	                <td width="200px">申请功能
	                 <s:select name="applyInfoDomain.applyType" list="#{'':'全部',0:'货主版功能',1:'身份证查询',2:'VIP功能'}">
	                </s:select>
	                </td>
	                 <td width="200px">审核状态
	                 <s:select name="applyInfoDomain.verifyStart" list="#{0:'等待审核',-1:'审核失败',1:'审核成功'}">
	                </s:select>
	                </td>
                </tr>
                <tr>
	                <td colspan="2">申请时间
	                <s:textfield name="applyInfoDomain.applyTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="applyInfoDomain.applyTimeZ" cssClass="ymdate"></s:textfield>
	                </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="70">操作</td>
			<td width="100">公司</td>
			<td width="100">申请功能</td>
			<td width="90">申请时间</td>
			<td width="100">姓名</td>
			<td width="100">电话</td>
			<td width="100">申请内容</td>
			<td width="90">审核时间</td>
			<td width="100">审核状态</td>
			<td width="100">回复内容</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${applyInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${applyInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='applyInfoDomain.pageInfo.pageSize' value='${applyInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
