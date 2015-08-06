<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询货物信息</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"orderCargoInfoDomain.id":trim(document.getElementById("mainForm_orderCargoInfoDomain_id").value),
				"orderCargoInfoDomain.cargoName":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoName").value),

<%--				"orderCargoInfoDomain.cargoType":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoType").value),--%>
<%--				"orderCargoInfoDomain.cargoWeight":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoWeight").value),--%>
<%--				"orderCargoInfoDomain.cargoCubage":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoCubage").value),--%>
<%--				"orderCargoInfoDomain.requestCarLength":trim(document.getElementById("mainForm_orderCargoInfoDomain_requestCarLength").value),--%>
<%--				"orderCargoInfoDomain.requestCarPlateType":trim(document.getElementById("mainForm_orderCargoInfoDomain_requestCarPlateType").value),--%>
<%--				"orderCargoInfoDomain.requestCarBarType":trim(document.getElementById("mainForm_orderCargoInfoDomain_requestCarBarType").value),--%>
<%--				"orderCargoInfoDomain.freight":trim(document.getElementById("mainForm_orderCargoInfoDomain_freight").value),--%>
<%--				"orderCargoInfoDomain.requestStartTime":trim(document.getElementById("mainForm_orderCargoInfoDomain_requestStartTime").value),--%>
<%--				"orderCargoInfoDomain.requestEndTime":trim(document.getElementById("mainForm_orderCargoInfoDomain_requestEndTime").value),--%>
<%--				"orderCargoInfoDomain.startProvince":trim(document.getElementById("mainForm_orderCargoInfoDomain_startProvince").value),--%>
<%--				"orderCargoInfoDomain.startCity":trim(document.getElementById("mainForm_orderCargoInfoDomain_startCity").value),--%>
<%--				"orderCargoInfoDomain.startCounty":trim(document.getElementById("mainForm_orderCargoInfoDomain_startCounty").value),--%>
<%--				"orderCargoInfoDomain.startTown":trim(document.getElementById("mainForm_orderCargoInfoDomain_startTown").value),--%>
<%--				"orderCargoInfoDomain.endProvince":trim(document.getElementById("mainForm_orderCargoInfoDomain_endProvince").value),--%>
<%--				"orderCargoInfoDomain.endCity":trim(document.getElementById("mainForm_orderCargoInfoDomain_endCity").value),--%>
<%--				"orderCargoInfoDomain.endCounty":trim(document.getElementById("mainForm_orderCargoInfoDomain_endCounty").value),--%>
<%--				"orderCargoInfoDomain.endTown":trim(document.getElementById("mainForm_orderCargoInfoDomain_endTown").value),--%>
<%--				"orderCargoInfoDomain.contactName":trim(document.getElementById("mainForm_orderCargoInfoDomain_contactName").value),--%>
<%--				"orderCargoInfoDomain.contactMobilephone":trim(document.getElementById("mainForm_orderCargoInfoDomain_contactMobilephone").value),--%>
<%--				"orderCargoInfoDomain.contactTelephone":trim(document.getElementById("mainForm_orderCargoInfoDomain_contactTelephone").value),--%>
<%--				"orderCargoInfoDomain.remark":trim(document.getElementById("mainForm_orderCargoInfoDomain_remark").value),--%>
<%--				"orderCargoInfoDomain.deletedFlag":trim(document.getElementById("mainForm_orderCargoInfoDomain_deletedFlag").value),--%>
				"orderCargoInfoDomain.deployUserid":trim(document.getElementById("mainForm_orderCargoInfoDomain_deployUserid").value),
<%--				"orderCargoInfoDomain.modifyUserid":trim(document.getElementById("mainForm_orderCargoInfoDomain_modifyUserid").value),--%>
<%--				"orderCargoInfoDomain.companyId":trim(document.getElementById("mainForm_orderCargoInfoDomain_companyId").value),--%>
<%--				"orderCargoInfoDomain.cargoFlag":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoFlag").value),--%>
<%--				"orderCargoInfoDomain.cargoFlagTime":trim(document.getElementById("mainForm_orderCargoInfoDomain_cargoFlagTime").value),--%>
				
				
				"orderCargoInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"orderCargoInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryOrderCargoInfo";
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
		var dataList = text.orderCargoInfoDomain.dataList;
		var totalPages = text.orderCargoInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.orderCargoInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.orderCargoInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["cargoName","cargoFlag","cargoType","cargoWeight","cargoCubage","requestCarLength","requestCarPlateType","requestCarBarType","freight","requestStartTime","requestEndTime","startProvince","startCity","startCounty","startTown","endProvince","endCity","endCounty","endTown","contactName","contactMobilephone","contactTelephone","remark","deployUserid","modifyUserid","companyId","cargoFlagTime"];
		var operateObject = {'updateOrderCargoInfo':'修改','deleteOrderCargoInfo':'删除'};
		var operateObjectMultiParameter = {'jumpHtml(4,3,':['id','订单'],'jumpHtml(4,2,':['deployUserid','企业'],'jumpHtml(4,10,':['id','货源历史状态']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateOrderCargoInfo(id){
		var url = jcontextPath+"/queryOrderCargoInfoMx?orderCargoInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryOrderCargoInfoMx?orderCargoInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteOrderCargoInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"orderCargoInfoDomain.id":id};
		 var url = jcontextPath+"/deleteOrderCargoInfo";   
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
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportOrderCargoInfo" namespace="/" method="post">
<s:hidden name="orderCargoInfoDomain.deployUserid"></s:hidden>
<s:hidden name="orderCargoInfoDomain.id"></s:hidden>

<table  class="conditionTable">
             <tr>            
                <td width="200px">货物名称<s:textfield name="orderCargoInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <!-- 
                <td width="200px">货物类型<s:textfield name="orderCargoInfoDomain.cargoType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">重量（货物）<s:textfield name="orderCargoInfoDomain.cargoWeight"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">体积（货物）<s:textfield name="orderCargoInfoDomain.cargoCubage"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">车型要求（车长）<s:textfield name="orderCargoInfoDomain.requestCarLength"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">板-平板、高低板<s:textfield name="orderCargoInfoDomain.requestCarPlateType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">车型要求（车 栏）<s:textfield name="orderCargoInfoDomain.requestCarBarType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">发布运费价格<s:textfield name="orderCargoInfoDomain.freight"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">要求装货时间<s:textfield name="orderCargoInfoDomain.requestStartTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">要求到货时间<s:textfield name="orderCargoInfoDomain.requestEndTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">装货地-省<s:textfield name="orderCargoInfoDomain.startProvince"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">装货地-市<s:textfield name="orderCargoInfoDomain.startCity"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">装货地-县<s:textfield name="orderCargoInfoDomain.startCounty"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">装货地-自定义地址<s:textfield name="orderCargoInfoDomain.startTown"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">卸货地-省<s:textfield name="orderCargoInfoDomain.endProvince"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">卸货地-市<s:textfield name="orderCargoInfoDomain.endCity"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">卸货地-县<s:textfield name="orderCargoInfoDomain.endCounty"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">卸货地-自定义地址<s:textfield name="orderCargoInfoDomain.endTown"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">联系人<s:textfield name="orderCargoInfoDomain.contactName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">手机号<s:textfield name="orderCargoInfoDomain.contactMobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">固定电话<s:textfield name="orderCargoInfoDomain.contactTelephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">备注<s:textfield name="orderCargoInfoDomain.remark"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">删除状态<s:textfield name="orderCargoInfoDomain.deletedFlag"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">发布用户ID<s:textfield name="orderCargoInfoDomain.deployUserid"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">修改用户ID<s:textfield name="orderCargoInfoDomain.modifyUserid"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">企业ID<s:textfield name="orderCargoInfoDomain.companyId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">货源状态<s:textfield name="orderCargoInfoDomain.cargoFlag"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">状态修改时间<s:textfield name="orderCargoInfoDomain.cargoFlagTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                -->
                </tr>
                <tr>
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
                <td ><input type="button" onclick="addInfo();" value="新增" class="selectBtn" /></td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="300">操作</td>
			<td width="100">货物名称</td>
			<td width="100">货源状态</td>
			<td width="100">货物类型</td>
			<td width="100">重量（货物）</td>
			<td width="100">体积（货物）</td>
			<td width="100">车型要求（车长）</td>
			<td width="100">板-平板、高低板</td>
			<td width="100">车型要求（车 栏）</td>
			<td width="100">发布运费价格</td>
			<td width="130">要求装货时间</td>
			<td width="130">要求到货时间</td>
			<td width="100">装货地-省</td>
			<td width="100">装货地-市</td>
			<td width="100">装货地-县</td>
			<td width="200">装货地-自定义地址</td>
			<td width="100">卸货地-省</td>
			<td width="100">卸货地-市</td>
			<td width="100">卸货地-县</td>
			<td width="200">卸货地-自定义地址</td>
			<td width="100">联系人</td>
			<td width="100">手机号</td>
			<td width="100">固定电话</td>
			<td width="100">备注</td>
			<td width="100">发布用户ID</td>
			<td width="100">修改用户ID</td>
			<td width="100">企业ID</td>
			<td width="130">状态修改时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${orderCargoInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${orderCargoInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='orderCargoInfoDomain.pageInfo.pageSize' value='${orderCargoInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
