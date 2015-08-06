<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ</title>
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
				"webUserInfoDomain.freezeFlag":trim(document.getElementById("mainForm_webUserInfoDomain_freezeFlag").value),
				"webUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value),
				"webUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value),
				"webUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"webUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
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
		var totalPages = text.webUserInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.webUserInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.webUserInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
		 var columus =["code","name","companyName","mobilephone","businessLicence","organizationCode","submitType","modifyTime"];
		var operateObject = {'updateWebUserInfo':'�޸�','deleteWebUserInfo':'����'};
		var table = document.getElementById("dataList");
		if(text.webUserInfoDomain.freezeFlag==1){
			operateObject = {'unfreezeWebUserInfo':'�ⶳ'};
			//�޸�ʱ��Ϊ����ʱ��
			columus =["code","name","companyName","mobilephone","businessLicence","organizationCode","deleteReason","modifyTime"];
			//�޸ı�ͷ����
			table.rows[0].cells[8].innerHTML = "��������";
			table.rows[0].cells[9].innerHTML = "����ʱ��";
		}else{
			table.rows[0].cells[8].innerHTML = "�Ƿ���֤";
			table.rows[0].cells[9].innerHTML = "����ʱ��";
		}
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateWebUserInfo(id){
		var url = jcontextPath+"/queryWebUserInfoMx?webUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function deleteWebUserInfo(id){
		var url = jcontextPath+"/queryWebUserInfoMx?webUserInfoDomain.id="+id+"&webUserInfoDomain.deleteOrModifyFlag="+0+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
	}	
	function unfreezeWebUserInfo(id){
		if(confirm("ȷ��Ҫ�ⶳô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"webUserInfoDomain.id":id
				 ,"webUserInfoDomain.freezeFlag":1};
		 var url = jcontextPath+"/deleteWebUserInfo";   
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
        alert("����ɹ���");
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
<s:hidden name="webUserInfoDomain.id"></s:hidden>


<table  class="conditionTable">
             <tr>            
                <td width="200px">��¼����<s:textfield name="webUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�û�����<s:textfield name="webUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��˾����<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">�ֻ�����<s:textfield name="webUserInfoDomain.mobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">�Ƿ񶳽�
               <s:select name="webUserInfoDomain.freezeFlag" list="#{0:'δ����',1:'�Ѷ���'}" >
                </s:select>
                </td>
               </tr>
               <tr>
                <td colspan="2">ע��ʱ��
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >���</td>
			<td width="70">����</td>
			<td width="80">��¼����</td>
			<td width="80">����</td>
			<td width="120">��˾����</td>
			<td width="85">�ֻ�����</td>
			<td width="60">Ӫҵִ��</td>
			<td width="80">��֯����</td>
			<td width="60">�Ƿ���֤</td>
			<td width="90">����ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${webUserInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${webUserInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='webUserInfoDomain.pageInfo.pageSize' value='${webUserInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
