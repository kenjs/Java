<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询司机信息</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"driverUserInfoDomain.id":trim(document.getElementById("mainForm_driverUserInfoDomain_id").value),
				"driverUserInfoDomain.code":trim(document.getElementById("mainForm_driverUserInfoDomain_code").value),
				"driverUserInfoDomain.name":trim(document.getElementById("mainForm_driverUserInfoDomain_name").value),
				"driverUserInfoDomain.carNumber":trim(document.getElementById("mainForm_driverUserInfoDomain_carNumber").value),
				"driverUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeQ").value),
				"driverUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeZ").value),
				"driverUserInfoDomain.freezeFlag":trim(document.getElementById("mainForm_driverUserInfoDomain_freezeFlag").value),
				"driverUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"driverUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryDriverUserInfo";
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
		var dataList = text.driverUserInfoDomain.dataList;
		var totalPages = text.driverUserInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.driverUserInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.driverUserInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["name","code","carNumber","submitType","modifyTime","location","lastTime"];
		var operateObject = {'updateDriverUserInfo':'修改','deleteDriverUserInfo':'冻结'};
		var table = document.getElementById("dataList");
		if(text.driverUserInfoDomain.freezeFlag==1){
			operateObject = {'unfreezeDriverUserInfo':'解冻'};
			//修改时间为冻结时间
			columus =["name","code","carNumber","deleteReason","modifyTime","location","lastTime"];
			//修改表头名称
			table.rows[0].cells[5].innerHTML = "冻结理由";
			table.rows[0].cells[6].innerHTML = "冻结时间";
		}else{
			table.rows[0].cells[5].innerHTML = "是否认证";
			table.rows[0].cells[6].innerHTML = "认证提交时间";
		}
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateDriverUserInfo(id){
		var url = jcontextPath+"/queryDriverUserInfoMx?driverUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
	} 
	function deleteDriverUserInfo(id){
		var url = jcontextPath+"/queryDriverUserInfoMx?driverUserInfoDomain.id="+id+"&driverUserInfoDomain.deleteOrModifyFlag="+0+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
	}	
	function unfreezeDriverUserInfo(id){
		if(confirm("确定要解冻么？")){
			yesCallBack(id);
		}
	}
	function yesCallBack(id){
		 var jsonObj = {"driverUserInfoDomain.id":id
				 ,"driverUserInfoDomain.freezeFlag":1};
		 var url = jcontextPath+"/deleteDriverUserInfo";   
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
        alert("解冻成功！");
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
<s:form id="mainForm" action="/exportDriverUserInfo" namespace="/" method="post">
<s:hidden name="driverUserInfoDomain.id"></s:hidden>


<table  class="conditionTable">
             <tr>            
                <td width="200px">登录帐号<s:textfield name="driverUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">司机姓名<s:textfield name="driverUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">车牌号<s:textfield name="driverUserInfoDomain.carNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">是否冻结
               <s:select name="driverUserInfoDomain.freezeFlag" list="#{0:'未冻结',1:'已冻结'}"></s:select>
               </td>
                </tr>
                <tr>
                <td colspan="2">注册时间
                <s:textfield name="driverUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="driverUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
<%--                <input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>--%>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="70">操作</td>
			<td width="80">司机姓名</td>
			<td width="85">手机号码</td>
			<td width="70">车牌号码</td>
			<td width="60">是否认证</td>
			<td width="90">认证提交时间</td>
			<td width="190">当前位置</td>
			<td width="90">最新定位时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo'  value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize'  value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
