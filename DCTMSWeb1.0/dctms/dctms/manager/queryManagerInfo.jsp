<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ����Ա</title>
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
		var totalPages = text.managerInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.managerInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.managerInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
		var columus =["name","code","macTime"];
		var operateObject = {'updateManagerInfo':'�޸�','deleteManagerInfo':'ɾ��','updateManagerFunctionInfo':'��Ȩ'};
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
		if(confirm("ȷ��Ҫɾ��ô��")){
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
        alert("ɾ���ɹ���");
        queryInfo();
	}
	/**
	 * չʾ��ҳ����-��д
	 * @author WJL
	 * @param dataList ҳ��Ҫչʾ��dataList
	 * @param curPageNos ��ǰҳ
	 * @param columus Ҫչʾ����
	 * @param isOperateFlag  �Ƿ��в������ݣ�����1��ʾ���Բ���������0��ʾ�����Բ���
	 * @param operateObject �������󣬸�ʽΪ�����������������ơ�
	 */
	function showPageTable(dataList,columus,curPageNos,isOperateFlag,operateObject){
		var table = document.getElementById("dataList");
		var tr =null;
		var managerId = <%=request.getSession().getAttribute("id")%>
		//ɾ����������
		while(table.rows.length>1){
			table.deleteRow(1);
		}
		//��ȡ��ʼ����
		var initCount = 20*(curPageNos-1);
		for(var i =0;i<dataList.length;i++){
			tr = table.insertRow(i+1);
			tr.insertCell(0).innerHTML=i+1+initCount;
			colsNum = isOperateFlag;
			if(isOperateFlag==1){
				var operateContent = "";
				//�ж�Ȩ��˭����˭����Ȩ���޸�,��������Աȥ��ɾ�����޸ģ���Ȩ����
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
                <td width="200px">����<s:textfield name="managerInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;"></s:textfield>  </td>
                <td width="200px">�˺�<s:textfield name="managerInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
              </tr>
              <tr>
                <td >
                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
                </td>
                <td width="80px"><input type="button" onclick="addInfo();" value="����" class="selectBtn"/></td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="120">����</td>
			<td width="200">����</td>
			<td width="100">�˺�</td>
			<td width="130">��ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${managerInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${managerInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='managerInfoDomain.pageInfo.pageSize' value='${managerInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>