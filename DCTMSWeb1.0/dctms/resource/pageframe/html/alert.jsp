<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>�ޱ����ĵ�</title>

<style type="text/css">
html, body {background:none;}
</style>

<script language="JavaScript">
	
	<%
		String	yesCallBackName = (String)request.getParameter("yesCallBack");
	%>
	
	$(function(){
		var fucYesName = "<%=yesCallBackName%>";
		
		if(fucYesName != undefined && fucYesName != "" && fucYesName != ""){
			//�رհ�ť�¼�
			$("#messageCloseBtn").unbind("click");
			$("#messageCloseBtn").click(function(){
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
			
			$("#closePic").unbind("click");
			$("#closePic").click(function(){
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
		}
		
		$("#messageCloseBtn").focus();
		
	});
	
	

</script>

</head>


<!-- <body onLoad="addHandle(document.getElementById('toolbar'), window);"> --><!-- JS��û��Ȩ�޴��� -->
<body>
<div class="pop_title" id="toolbar">
  <div class="pop_titlec"> <span class="pop_titleleft"><h2>&nbsp;>> ϵͳ��Ϣ</h2></span> <span class="pop_titleright"></span> </div><a href="#" id="closePic" title="�ر�" class="pop_close" onFocus="this.blur()"></a>
</div>
<div class="pop_cont">
  <div class="pop_contc" style="height:140px; overflow:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="60" align="center"><img src="<sys:context/>/resource/pageframe/images/info.gif" /></td>
        <td width="70%"><%=com.cy.dcts.common.util.ConvertCharactersUtil.convertISO2GBK(request.getParameter("myContentStr"))%></td>
      </tr>
    </table>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg" id="messageCloseBtn">ȷ ��</button>
    </div>
  </div>
</div>
</body>

</html>
