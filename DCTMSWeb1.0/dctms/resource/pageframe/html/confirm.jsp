<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>无标题文档</title>
<style type="text/css">
html, body {background:none;}
</style>

<script language="JavaScript">
	
	<%
		String	yesCallBackName = (String)request.getParameter("yesCallBack");
		String	noCallBackName = (String)request.getParameter("noCallBack");
	%>
	
	$(function(){
		var fucYesName = "<%=yesCallBackName%>";
		var fucNoName = "<%=noCallBackName%>";
		
		//查询按钮事件
		$("#sureBtn").click(function(){
			sysMessageClose();
			try{
		        if(typeof(eval("parent."+fucYesName)) == "function"){
		        	eval("parent."+fucYesName+"();");
		        	return false;
		        }
	        }catch(e){
	        	//alert("not function:"+e); 
	       	}
		});
		
		$("#sureBtn").focus();
		
		if(fucNoName != undefined && fucNoName != ""){
			if("noTabCloseNoCallBack" == fucNoName){
				$("#closePic").unbind("click");	 	
				$("#closePic").click(function(){
					sysMessageClose();
					try{
				        if(typeof(eval("parent."+fucNoName)) == "function"){
				        	eval("parent."+fucNoName+"();");
				        	return false;
				        }
			        }catch(e){
			        	//alert("not function:"+e); 
			       	}
				});
			}
			
			//关闭按钮事件
			$("#messageCloseBtn").unbind("click");
			$("#messageCloseBtn").click(function(){
				sysMessageClose();
				try{
			        if(typeof(eval("parent."+fucNoName)) == "function"){
			        	eval("parent."+fucNoName+"();");
			        	return false;
			        }
		        }catch(e){
		        	//alert("not function:"+e); 
		       	}
			});
		}
		
	});
	
	

</script>

</head>

<body onLoad="addHandle(document.getElementById('toolbar'), window);">
<div class="pop_title" id="toolbar">
  <div class="pop_titlec"> <span class="pop_titleleft"><h2>&nbsp;>> 系统信息</h2></span> <span class="pop_titleright"></span> </div><a href="#" id="messageClosePic" title="关闭" class="pop_close" onFocus="this.blur()"></a>
</div>
<div class="pop_cont">
  <div class="pop_contc" style="height:140px; overflow:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="60" align="center"><img src="<sys:context/>/resource/pageframe/images/ask.gif" /></td>
        <td width="70%"><%=com.cy.dcts.common.util.ConvertCharactersUtil.convertISO2GBK(request.getParameter("myContentStr"))%></td>
      </tr>
    </table>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg" id="sureBtn">是</button>
      &nbsp;
      <button type="button" class="pop_btnbg" id="messageCloseBtn">否</button>
    </div>
  </div>
</div>
</body>

</html>
