<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
	<head>
	<link href="<sys:context/>/resource/css/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<sys:context />/resource/pageframe/com/jquery-1.6.1.js"></script>
<title>菜单</title>
<style>
</style>

<script type="text/javascript">
window.onload=init;
function init(){
	ajaxSend(jcontextPath+ "/queryFunction");
}
function	ajaxSend(url){
//	取消对象赋值
//	var gnmkDm = $("#domain_gnmkDm").val();
//	data["domain.gnmkDm"] = gnmkDm;
	url = url + ".action";
	$.ajax({
			   type: "POST",
			   url: url,
			   dataType:"json",
			   contentType: "application/x-www-form-urlencoded; charset=GBK", 
			   success: function(data){
				   doSuccessFucName(data);
			   },
			   //后台有Exception时会执行，通过SysIntegratedExceptionInterceptor类写数据流
			   error:function(data){
			   }
	 });  
}
function doSuccessFucName(data){
	$("#functionInfoDomain_name").html(data.functionInfoDomain.name);
}

function visibility(id){
	var div = document.getElementById(id);  
	if (div.style.display == "none"){     
			div.style.display = "block"; 
	}else{   
			div.style.display = "none"; 
	}
}
function unfold(){
		var t = document.all ;	
		for(var i=0;i<t.length;i++){
			if(t[i].name=="functionName"){
			t[i].style.display = "block";
			} 
		}
		
}
function collapseAll(){
	var t = document.all ;	
	for(var i=0;i<t.length;i++) {
		if(t[i].name=="functionName"){
			t[i].style.display = "none";
		}
	}
}
function navigateMenu(url,funName){
	url = jcontextPath + "/" +url;
	parent.parent.document.getElementById("FRM_BLANK").contentWindow.navigate(url,funName,true);
}
</script>
</head>
		<body >
	<s:form action="/managerLogout"   id="mainForm" method="post" namespace="/">
	<s:hidden name="functionInfoDomain.name"></s:hidden>
<%--		<div id="collapseAll" onclick="collapseAll()">全关闭</div>--%>
<%--		<div id="unfold" onclick="unfold()">全展开</div>--%>
<%--		<div>关联到当前视图</div>--%>
<div style="margin: 8px"></div>
<div class="mainLeft" id="functionInfoDomain_name">
	<img src="<sys:context/>/resource/images/leftClose.png" />
</div>
		</s:form>
	</body>
</html>
