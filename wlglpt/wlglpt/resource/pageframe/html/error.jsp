<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>�ޱ����ĵ�</title>
<%@ include file="/common/meta.jsp"%>

<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<style type="text/css">
html, body {background:none;}
</style>

<script language="JavaScript">
	
	<%
		String	yesCallBackName = (String)request.getParameter("yesCallBack");
	%>
	
	$(function(){
		var fucYesName = "<%=yesCallBackName%>";
		
		if(fucYesName != undefined && fucYesName != ""){
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
		}
		$("#messageCloseBtn").focus();
		
	});
	
	

</script>

</head>

<body onLoad="addHandle(document.getElementById('toolbar'), window);">
<div class="pop_title" id="toolbar">
  <div class="pop_titlec"> <span class="pop_titleleft"><h2>&nbsp;>> ϵͳ��Ϣ</h2></span> <span class="pop_titleright"></span> </div><a href="#" id="closePic" title="�ر�" class="pop_close" onFocus="this.blur()"></a>
</div>
<div class="pop_cont">
  <div class="pop_contc" style="height:140px; overflow:auto;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30%" height="60" align="center"><img src="<sys:context/>/resource/pageframe/images/err.gif" /></td>
        <td width="70%"><%=com.cy.framework.util.SysEncodeUtil.ISO2GBK(request.getParameter("myContentStr"))%></td>
      </tr>
    </table>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg" id="messageCloseBtn">ȷ ��</button>
    </div>
  </div>
</div>
</body>

</html>
