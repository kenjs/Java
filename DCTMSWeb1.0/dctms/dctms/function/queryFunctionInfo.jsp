<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询功能</title>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/areaDataDict.js"></script>
<link href="<sys:context/>/resource/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"functionInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"functionInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryFunctionInfo";
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
		var dataList = text.functionInfoDomain.dataList;
		var totalPages = text.functionInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.functionInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.functionInfoDomain.pageInfo.totalRecords;//总记录数
	    var columus =["number","name","parentNumber","url"];
		var operateObject = {'updateFunctionInfo':'修改','deleteFunctionInfo':'删除'};
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateFunctionInfo(id){
		var url = jcontextPath+"/queryFunctionInfoMx?functionInfoDomain.id="+id;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function addFunctionInfo(){
		var url = jcontextPath+"/queryFunctionInfoMx?functionInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteFunctionInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id)
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"functionInfoDomain.id":id};
		 var url = jcontextPath+"/deleteFunctionInfo";   
		 AjaxSubmit({
				url:url,
				data:jsonObj,
			    method:"get",
			    async:true,
			    success:function(text){
			    	doSuccess();
			    }
			}); 
	}
	
	function doSuccess(){
        alert("删除成功！");
        queryInfo();
	}	
	
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/queryFunctionInfo" namespace="/" method="post">



<table style="margin-top:10px;">
            <tr style=" height:30px; ">
                <td style="width:80px; text-align:center;"><input type="button" onclick="queryInfoInit();" value="查询" style="width:60px; height:24px; background:#2b94f1; border:1px solid #3596f0; color:white; border-radius:3px;"/></td>
                <td style="width:80px; text-align:center;"><input type="button" onclick="addFunctionInfo();" value="新增" style="width:60px; height:24px; background:#2b94f1; border:1px solid #3596f0; color:white; border-radius:3px;"/></td>
            </tr>
        </table>
	<table class="mainTable"  style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="100">操作</td>
			<td width="50">编号</td>
			<td width="120">功能名字</td>
			<td width="60">父类编号</td>
			<td width="500">URL</td>

		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' name='functionInfoDomain.pageInfo.curPage' value='${functionInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' name='functionInfoDomain.pageInfo.curPageNo' value='${functionInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='functionInfoDomain.pageInfo.pageSize' name='functionInfoDomain.pageInfo.pageSize' value='${functionInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
