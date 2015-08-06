<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业</title>
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
		var totalPages = text.webUserInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.webUserInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.webUserInfoDomain.pageInfo.totalRecords;//总记录数
		 var columus =["code","name","companyName","mobilephone","businessLicence","organizationCode","submitType","modifyTime"];
		var operateObject = {'updateWebUserInfo':'修改','deleteWebUserInfo':'冻结'};
		var table = document.getElementById("dataList");
		if(text.webUserInfoDomain.freezeFlag==1){
			operateObject = {'unfreezeWebUserInfo':'解冻'};
			//修改时间为冻结时间
			columus =["code","name","companyName","mobilephone","businessLicence","organizationCode","deleteReason","modifyTime"];
			//修改表头名称
			table.rows[0].cells[8].innerHTML = "冻结理由";
			table.rows[0].cells[9].innerHTML = "冻结时间";
		}else{
			table.rows[0].cells[8].innerHTML = "是否认证";
			table.rows[0].cells[9].innerHTML = "提审时间";
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
		if(confirm("确定要解冻么？")){
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
        alert("冻结成功！");
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
                <td width="200px">登录名称<s:textfield name="webUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">用户姓名<s:textfield name="webUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">公司名称<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">手机号码<s:textfield name="webUserInfoDomain.mobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">是否冻结
               <s:select name="webUserInfoDomain.freezeFlag" list="#{0:'未冻结',1:'已冻结'}" >
                </s:select>
                </td>
               </tr>
               <tr>
                <td colspan="2">注册时间
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >序号</td>
			<td width="70">操作</td>
			<td width="80">登录名称</td>
			<td width="80">姓名</td>
			<td width="120">公司名称</td>
			<td width="85">手机号码</td>
			<td width="60">营业执照</td>
			<td width="80">组织机构</td>
			<td width="60">是否认证</td>
			<td width="90">提审时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${webUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo'  value='${webUserInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='webUserInfoDomain.pageInfo.pageSize' value='${webUserInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
