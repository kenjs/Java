<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询赋权信息</title>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/areaDataDict.js"></script>
<link href="<sys:context/>/resource/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	$("#saveBtn").click(function(){
		var managerId = trim($("#mainForm_funRealationInfoDomain_managerId").val());
		var boxs = $("[name = boxs]:checked");
		var url = jcontextPath+"/saveFunRealationInfo";  
		var jsonObj ={"funRealationInfoDomain.managerId":managerId};
		for(var i =0;i<boxs.length;i++){
			jsonObj["funRealationInfoDomain.funIdList["+i+"]"]=boxs[i].value;
		}
		AjaxSubmit({
			url:url,
			data:jsonObj,
		    method:"get",
		    async:true,
		    success:function(text){
		    	doBack();
		    }
		});
	});
});

function doBack(){
	if(confirm("保存成功!是否关闭页面")){
		doYesCallBack();
	}
}

//选择是的返回处理
function doYesCallBack(){
	window.close();
}
	
</script>

<style type="text/css">
html,body {overflow-x:hidden;}
</style>
<base target="_self" />
</head>

<body  onselect="false">
<s:form id="mainForm" action="/queryFunRealationInfo" namespace="/" method="post">
<s:hidden name="funRealationInfoDomain.managerId"></s:hidden>
	<table class="mainTable" style="width: 500px;margin:40px auto;">
		<tr style="background:#b4dffc; height:30px; line-height:30px; font-size:16px; color:#004f96;">
			<td width="50">操作</td>
			<td width="200">功能名字</td>
			<td width="200">父类名字</td>
		</tr>
		<s:iterator value="funRealationInfoDomain.dataList" status="st" id="datas">
	        <tr >
	        <td >
	        <s:if test="#datas.sunCount==0">
	      		  <input type="checkbox" name="boxs" value="<s:property value="#datas.id"/>" >
	        </s:if>
	        <s:else>
	       		 <input type="checkbox" name="boxs" value="<s:property value="#datas.id"/>" checked="checked">
	        </s:else>
	        </td>
	        <td><s:property value="#datas.name"/></td>
	        <td><s:property value="#datas.parentName"/></td>

	        </tr>
	        </s:iterator>
    </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input id="saveBtn" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="保存" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="关闭" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>
