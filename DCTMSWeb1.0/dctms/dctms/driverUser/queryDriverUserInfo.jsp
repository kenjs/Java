<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ˾����Ϣ</title>
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
		var totalPages = text.driverUserInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.driverUserInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.driverUserInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["name","code","carNumber","submitType","modifyTime","location","lastTime"];
		var operateObject = {'updateDriverUserInfo':'�޸�','deleteDriverUserInfo':'����'};
		var table = document.getElementById("dataList");
		if(text.driverUserInfoDomain.freezeFlag==1){
			operateObject = {'unfreezeDriverUserInfo':'�ⶳ'};
			//�޸�ʱ��Ϊ����ʱ��
			columus =["name","code","carNumber","deleteReason","modifyTime","location","lastTime"];
			//�޸ı�ͷ����
			table.rows[0].cells[5].innerHTML = "��������";
			table.rows[0].cells[6].innerHTML = "����ʱ��";
		}else{
			table.rows[0].cells[5].innerHTML = "�Ƿ���֤";
			table.rows[0].cells[6].innerHTML = "��֤�ύʱ��";
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
		if(confirm("ȷ��Ҫ�ⶳô��")){
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
        alert("�ⶳ�ɹ���");
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
                <td width="200px">��¼�ʺ�<s:textfield name="driverUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">˾������<s:textfield name="driverUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">���ƺ�<s:textfield name="driverUserInfoDomain.carNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">�Ƿ񶳽�
               <s:select name="driverUserInfoDomain.freezeFlag" list="#{0:'δ����',1:'�Ѷ���'}"></s:select>
               </td>
                </tr>
                <tr>
                <td colspan="2">ע��ʱ��
                <s:textfield name="driverUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
<%--                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>--%>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="70">����</td>
			<td width="80">˾������</td>
			<td width="85">�ֻ�����</td>
			<td width="70">���ƺ���</td>
			<td width="60">�Ƿ���֤</td>
			<td width="90">��֤�ύʱ��</td>
			<td width="190">��ǰλ��</td>
			<td width="90">���¶�λʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize'  value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
