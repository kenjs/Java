<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>������Ϣ��ʼ����Ϣ </title>
<script type="text/javascript">
	window.onload = function(){
	}
	function saveBtn(){
		var table = document.getElementById("dataList");
		var data ={"webUserInfoDomain.sendType":document.getElementById("mainForm_webUserInfoDomain_sendType").value};
		for(var i =0;i<table.rows.length-1;i++){
			data["webUserInfoDomain.dataList["+i+"].mobilephone"]=table.rows[i+1].cells[2].innerHTML;
			data["webUserInfoDomain.dataList["+i+"].email"]=table.rows[i+1].cells[3].innerHTML;
		}
		var url = jcontextPath+"/sendWebMessager";
		AjaxSubmit({
			url:url,
			data:data,
		    method:"post",
		    async:true,
		    success:function(text){
		    	callBackList(text);
		    }
		});
	}
	function callBackList(text){
		if(typeof text =='string'){
			alert(text);
			return;
		}
		if(confirm("���ͳɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		window.close();
	}
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
.mainTable{ margin-left:30px;}
.deout{ width:800px; margin-top:60px;}
.deout select{ width:180px; height:26px; line-height:26px; vertical-align:middle;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveDriverUserInfo" namespace="/" method="post">


	 <table class="deout">
	 		<tr >
		      <td align="right" >ͬʱ֪ͨ����ͨ����</td>
		      <td align="left">
            		 <s:select name="webUserInfoDomain.sendType" list="#{'0':'���ʼ������','1':'ֻ������','2':'ֻ���ʼ�'}" ></s:select>
		      </td>
		    </tr >
	</table>	
	<table class="mxBtnTable">    
    		<tr >
    			<td><input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px;  margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="ȷ������" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px;  margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="�ر�" type="button" /></td>
    		</tr>
		</table>
		
		<table class="mainTable" style="width: 775px" id="dataList">
				<tr class="mainTopTr">
					<td width="40" >���</td>
					<td width="80">��˾����</td>
					<td width="85">�ֻ�����</td>
					<td width="85">�ʼ�</td>
				</tr>
				<s:iterator id="datas" status="st" value="webUserInfoDomain.dataList">
				<tr>
					<td><s:property value = "#st.index+1"/></td>
					<td><s:property value = "#datas.companyName"/></td>
					<td><s:property value = "#datas.mobilephone"/></td>
					<td><s:property value = "#datas.email"/></td>
				</tr>
				</s:iterator>
			
		</table>
</s:form>
</body>
</html>
