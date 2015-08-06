<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ������Ϣ</title>
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
		var totalPages = text.orderCargoInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.orderCargoInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.orderCargoInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["cargoName","cargoFlag","cargoType","cargoWeight","cargoCubage","requestCarLength","requestCarPlateType","requestCarBarType","freight","requestStartTime","requestEndTime","startProvince","startCity","startCounty","startTown","endProvince","endCity","endCounty","endTown","contactName","contactMobilephone","contactTelephone","remark","deployUserid","modifyUserid","companyId","cargoFlagTime"];
		var operateObject = {'updateOrderCargoInfo':'�޸�','deleteOrderCargoInfo':'ɾ��'};
		var operateObjectMultiParameter = {'jumpHtml(4,3,':['id','����'],'jumpHtml(4,2,':['deployUserid','��ҵ'],'jumpHtml(4,10,':['id','��Դ��ʷ״̬']};
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
		if(confirm("ȷ��Ҫɾ��ô��")){
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
        alert("ɾ���ɹ���");
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
                <td width="200px">��������<s:textfield name="orderCargoInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <!-- 
                <td width="200px">��������<s:textfield name="orderCargoInfoDomain.cargoType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">���������<s:textfield name="orderCargoInfoDomain.cargoWeight"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��������<s:textfield name="orderCargoInfoDomain.cargoCubage"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����Ҫ�󣨳�����<s:textfield name="orderCargoInfoDomain.requestCarLength"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��-ƽ�塢�ߵͰ�<s:textfield name="orderCargoInfoDomain.requestCarPlateType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����Ҫ�󣨳� ����<s:textfield name="orderCargoInfoDomain.requestCarBarType"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�����˷Ѽ۸�<s:textfield name="orderCargoInfoDomain.freight"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">Ҫ��װ��ʱ��<s:textfield name="orderCargoInfoDomain.requestStartTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">Ҫ�󵽻�ʱ��<s:textfield name="orderCargoInfoDomain.requestEndTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">װ����-ʡ<s:textfield name="orderCargoInfoDomain.startProvince"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">װ����-��<s:textfield name="orderCargoInfoDomain.startCity"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">װ����-��<s:textfield name="orderCargoInfoDomain.startCounty"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">װ����-�Զ����ַ<s:textfield name="orderCargoInfoDomain.startTown"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">ж����-ʡ<s:textfield name="orderCargoInfoDomain.endProvince"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">ж����-��<s:textfield name="orderCargoInfoDomain.endCity"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">ж����-��<s:textfield name="orderCargoInfoDomain.endCounty"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">ж����-�Զ����ַ<s:textfield name="orderCargoInfoDomain.endTown"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��ϵ��<s:textfield name="orderCargoInfoDomain.contactName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�ֻ���<s:textfield name="orderCargoInfoDomain.contactMobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�̶��绰<s:textfield name="orderCargoInfoDomain.contactTelephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��ע<s:textfield name="orderCargoInfoDomain.remark"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">ɾ��״̬<s:textfield name="orderCargoInfoDomain.deletedFlag"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�����û�ID<s:textfield name="orderCargoInfoDomain.deployUserid"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�޸��û�ID<s:textfield name="orderCargoInfoDomain.modifyUserid"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��ҵID<s:textfield name="orderCargoInfoDomain.companyId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��Դ״̬<s:textfield name="orderCargoInfoDomain.cargoFlag"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">״̬�޸�ʱ��<s:textfield name="orderCargoInfoDomain.cargoFlagTime"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                -->
                </tr>
                <tr>
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
                <td ><input type="button" onclick="addInfo();" value="����" class="selectBtn" /></td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="300">����</td>
			<td width="100">��������</td>
			<td width="100">��Դ״̬</td>
			<td width="100">��������</td>
			<td width="100">���������</td>
			<td width="100">��������</td>
			<td width="100">����Ҫ�󣨳�����</td>
			<td width="100">��-ƽ�塢�ߵͰ�</td>
			<td width="100">����Ҫ�󣨳� ����</td>
			<td width="100">�����˷Ѽ۸�</td>
			<td width="130">Ҫ��װ��ʱ��</td>
			<td width="130">Ҫ�󵽻�ʱ��</td>
			<td width="100">װ����-ʡ</td>
			<td width="100">װ����-��</td>
			<td width="100">װ����-��</td>
			<td width="200">װ����-�Զ����ַ</td>
			<td width="100">ж����-ʡ</td>
			<td width="100">ж����-��</td>
			<td width="100">ж����-��</td>
			<td width="200">ж����-�Զ����ַ</td>
			<td width="100">��ϵ��</td>
			<td width="100">�ֻ���</td>
			<td width="100">�̶��绰</td>
			<td width="100">��ע</td>
			<td width="100">�����û�ID</td>
			<td width="100">�޸��û�ID</td>
			<td width="100">��ҵID</td>
			<td width="130">״̬�޸�ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${orderCargoInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${orderCargoInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='orderCargoInfoDomain.pageInfo.pageSize' value='${orderCargoInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
