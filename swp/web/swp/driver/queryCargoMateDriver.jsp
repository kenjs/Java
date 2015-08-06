<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%
String cargoId = request.getParameter("cargoId");
String assistId = request.getParameter("assistId");
String mark = request.getParameter("mark");
String companyPhone = request.getParameter("companyPhone");
String companyName = request.getParameter("companyName");
companyName = new String(companyName.getBytes("iso-8859-1"),"UTF-8");
 %>
<head>
<title>快到网-营销平台-货找车</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>


<script type="text/javascript">
   var fcftj = "0";
   $(function(){
   		getCar();
   });
   
   function getCar(){
   		//获取司机信息
   		$.ajax({
			url: jcontextPath + "/queryCargoMateDriver.jspx",  
			type:'post',	
			dataType:'json', 
			data:{cargoId:<%=cargoId%>,assistId:<%=assistId%>,mark:<%=mark%>,
			companyPhone:<%=companyPhone%>,companyName:'<%=companyName%>'},      	               
			success:function(data){//回传函数
				if(data.result == 0) {//成功
					var tradeListObj=data.content;
					$("#htmlID2").html(tradeListObj.html2);
					$("#htmlId").html(tradeListObj.html1);
				}else if(data.result == 1){//未登录
					location.href=jcontextPath+'/swp/login.jsp';
				}else {//出错（例：参数为空）
					 art.dialog({
						time:3,
						icon: 'error',
						content: data.errorMessage 
					});
				}
			}
		});
   
   }
   
   function setDriverContacts(strSize,strI) {
	var a = 0;
	for(var i=0;i<strSize;i++){
		var radio1Value= $(":radio[name='marketingCargoDriverContactsArry["+i+"].replyResult']:checked").val();
		if(radio1Value == '0') {
			a++;
		}
		if(a >3) {
			$(":radio[name='marketingCargoDriverContactsArry["+strI+"].replyResult'][value='4']").attr("checked","checked");
			break;
		}
	}
  }
  
$(function(){
	$('#subId').click(function(){
		$("#subId").attr({"disabled":"disabled"});//防止二次提交
  		$.ajax({
			url: jcontextPath+'/addCargoDriverContacts.jspx',   
			type:'post',	
			dataType:'json', 
			data:$("#mainForm").serialize(),      	               
			success:function(data){//回传函数
				if(data.result == 0) {//成功
					closeDialogAndReloadOrigin();
				}else if(data.result == 1){//未登录
					location.href=jcontextPath+'/swp/login.jsp';
				}else {//出错（例：参数为空）
					$("#"+promptId).html(data.errorMessage);
				}
			}
		});
	});
});
</script>
</head>
  
<body>
<sf:form id="mainForm" action="${contextPath}/addCargoDriverContacts.jspx" method="post">

	<div class="fr sonafr">
		<div class="data dataf">
			<table border="0" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<td>货物名称</td>
					<td>联系人</td>
					<td>装货时间</td>
					<td>装货地</td>
					<td>卸货地</td>
					<td width="80">重量(吨)</td>
					<td>体积(方)</td>
					<td>要求车型</td>
				</tr>
				</thead>
				<tbody id="htmlID2">
				<tr>
					<td>测试货物</td>
					<td>乔先生</td>
					<td>2014-12-05</td>
					<td><div class="ght">浙江省-杭州市 <br></div></td>
					<td><div class="ght">湖北省-武汉市<br></div></td>
					<td>15.0</td>
					<td>35.0</td>
					<td>
						6.2 米
						高栏
						高板

					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<br/>
    	<div class="funtop">
	        <div class="data dataf" style="height:400px; overflow: auto;">
	          	<table border="0" cellpadding="0" cellspacing="0" id="htmlId">
	          	</table>
	        </div>
        	<!-- 返回首页按钮 -->
           	<div class="ncrea" style="margin-left: 350px;margin-top: 20px;">
           		 <input type="button" name="button" id="subId" class="boxto" value="完成"/>
        	</div>
    	</div>
   </div>
</sf:form>
<br />
<br />
<br />
</body>
</html>
