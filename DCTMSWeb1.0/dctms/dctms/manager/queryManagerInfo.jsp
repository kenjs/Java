<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询管理员</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"managerInfoDomain.name":trim(document.getElementById("mainForm_managerInfoDomain_name").value),
				"managerInfoDomain.code":trim(document.getElementById("mainForm_managerInfoDomain_code").value),
				"managerInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"managerInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryManagerInfo";
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
		var dataList = text.managerInfoDomain.dataList;
		var totalPages = text.managerInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.managerInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.managerInfoDomain.pageInfo.totalRecords;//总记录数
		var columus =["name","code","macTime"];
		var operateObject = {'updateManagerInfo':'修改','deleteManagerInfo':'删除','updateManagerFunctionInfo':'赋权'};
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateManagerInfo(id){
		var url = jcontextPath+"/queryManagerInfoMx?managerInfoDomain.id="+id;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryManagerInfoMx?managerInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function updateManagerFunctionInfo(id){
		var url = jcontextPath+"/queryFunRealationInfo?funRealationInfoDomain.managerId="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();	
	}
	function deleteManagerInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"managerInfoDomain.id":id};
		 var url = jcontextPath+"/deleteManagerInfo";   
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
	/**
	 * 展示分页界面-重写
	 * @author WJL
	 * @param dataList 页面要展示的dataList
	 * @param curPageNos 当前页
	 * @param columus 要展示的列
	 * @param isOperateFlag  是否有操作内容，数字1表示可以操作，数字0表示不可以操作
	 * @param operateObject 操作对象，格式为“方法名：操作名称”
	 */
	function showPageTable(dataList,columus,curPageNos,isOperateFlag,operateObject){
		var table = document.getElementById("dataList");
		var tr =null;
		var managerId = <%=request.getSession().getAttribute("id")%>
		//删除已有数据
		while(table.rows.length>1){
			table.deleteRow(1);
		}
		//获取开始数据
		var initCount = 20*(curPageNos-1);
		for(var i =0;i<dataList.length;i++){
			tr = table.insertRow(i+1);
			tr.insertCell(0).innerHTML=i+1+initCount;
			colsNum = isOperateFlag;
			if(isOperateFlag==1){
				var operateContent = "";
				//判断权限谁建立谁才有权利修改,超级管理员去掉删除，修改，赋权操作
				if(managerId==dataList[i].parentId&&dataList[i].id!=1){
					for(var funName in operateObject){
						operateContent += '<a  href="#" onclick="'+funName+'('+dataList[i].id+');">'+operateObject[funName]+' </a>'
					
					}
				}
				tr.insertCell(1).innerHTML= operateContent;
				
			}
			for(var j in columus){
				colsNum++;
				tr.insertCell(colsNum).innerHTML=dataList[i][columus[j]]==null?"":dataList[i][columus[j]];
			}
		}
	}
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportManagerInfo" namespace="/" method="post">

	<table  class="conditionTable">
            <tr>
                <td width="200px">姓名<s:textfield name="managerInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;"></s:textfield>  </td>
                <td width="200px">账号<s:textfield name="managerInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
              </tr>
              <tr>
                <td >
                <input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
                </td>
                <td width="80px"><input type="button" onclick="addInfo();" value="新增" class="selectBtn"/></td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="120">操作</td>
			<td width="200">姓名</td>
			<td width="100">账号</td>
			<td width="130">绑定时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${managerInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo'  value='${managerInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='managerInfoDomain.pageInfo.pageSize' value='${managerInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>