<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ����Ա������־��Ϣ</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"managerWorkLogInfoDomain.name":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_name").value),
				"managerWorkLogInfoDomain.managerCode":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_managerCode").value),
				"managerWorkLogInfoDomain.managerName":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_managerName").value),
				"managerWorkLogInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"managerWorkLogInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryManagerWorkLogInfo";
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
		var dataList = text.managerWorkLogInfoDomain.dataList;
		var totalPages = text.managerWorkLogInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.managerWorkLogInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.managerWorkLogInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["managerCode","createTime","managerName","name","content"];
        var operateObject = {'auditDriverUserInfo':'���'};
		showPageTable(dataList,columus,curPageNos,0,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditDriverUserInfo(id){
		var url = jcontextPath+"/auditDriverUserInfoMx?driverUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
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
<s:form id="mainForm" action="/exportManagerWorkLogInfo" namespace="/" method="post">



<table class="conditionTable">
            <tr>
            	<td width="250px">������־��<s:textfield name="managerWorkLogInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
				<td width="250px">  ����Ա�˺�<s:textfield name="managerWorkLogInfoDomain.managerCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>        
				<td width="250px">  ����Ա����<s:textfield name="managerWorkLogInfoDomain.managerName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>     
            </tr>
            <tr>
                <td >
                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40">���</td>
			<td width="80">����Ա�˺�</td>
			<td width="150">����ʱ��</td>
			<td width="200">����Ա����</td>
			<td width="150">������־��</td>
			<td width="1000" style="overflow:hidden" >��������</td>
		</tr>
    </table>
     <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${managerWorkLogInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${managerWorkLogInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='managerWorkLogInfoDomain.pageInfo.pageSize' value='${managerWorkLogInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
