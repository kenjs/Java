<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/include.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>����ͷ��</title>
<%@ include file="/common/meta.jsp"%>

<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context/>/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('ul, li, span, h2, a, img');
</script>
<![endif]-->

<script language="javascript">
	/**
	 * �����Ҽ��˵�
	 */
	function disableRigthMenu(){
		return false;
	}

	//window.document.oncontextmenu=disableRigthMenu; 
	
	$(function(){
		initTopMenu();
	});
	
	function selClass(obj) {
		$(obj).addClass("current");
		$(obj).siblings().removeClass("current");
	}
	
	function initTopMenu() {
		var url = jcontextPath + "/xtgl/login!initTopMenu";
		var jsonObj = {};
		ajaxCommon(url,jsonObj,"doTopMenuSuccess");
	}
	
	function doTopMenuSuccess(data) {
		var xtmlXh = data.loginUserDomain.xtmlXh;
		var topMenu = data.loginUserDomain.topMenuList;
		$.each(topMenu, function(i, xtml){
			var liObj = $("<li onclick=\"javascript:selClass(this);\"></li>");
			var html = "<a href=\"javascript:getSubMenu('"+xtml.xtmlXh+"')\"><img src=\""+jcontextPath+"/resource/wlglpt/images/" + xtml.pic +"\" />" + xtml.xtmlMc + "</a>";
			$(liObj).html(html);
			if (xtml.xtmlXh == xtmlXh) {
				$(liObj).addClass("current");
			}
			$("#topMenuId").append(liObj);
		});
	}
	
	function getSubMenu(xtmlXh) {
		parent.parent.document.getElementById("FRM_LEFT").contentWindow.initTrees(xtmlXh);
	}

</script>
</head>

<body>
<div class="top01">
  <!-- ͷ��logo����Ϊ230px*80px -->
  <!-- <p class="top_left01" style="font-size:42px">${session.userInfos.zgsjc }</p> --> 
  <% //���ݲ�ͬ�˿ں��жϰ汾��ʵ��ͬlogo
    		int port = request.getServerPort();
      		String logo = "";
      		if(port==9959){//�����
      			logo = "_internet";
      		}else if(port==9969){//������
      			logo = "_base";
      		}else if(port==9979){//��ǿ��
      			logo = "_enhanced";
      		}else if(port==9989){//�߶˰�
      			logo = "_advanced";
      		}else if(port==9999){//���ư�
      			//��ȡÿ���û���ÿҳ��ʾ����ֵ
				UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
				if(userDomain==null || userDomain.equals("")){
					logo = "";//���û�е�¼��ϢĬ�ϲ�Խ��־
				}else{
      				logo = "_"+userDomain.getQybm();
      			}
      		}
      	%>
 <p class="top_left01"><img src="<sys:context/>/resource/wlglpt/images/wllogo/logo_pic<%=logo %>.png" alt="${session.userInfos.zgsjc }" title="${session.userInfos.zgsjc }" /></p>
  <p class="top_right01"></p>
  <ul class="topmenu" id="topMenuId">
    <li><a href="#"><img src="<sys:context/>/resource/wlglpt/images/c_topicon01.png" />�� ҳ</a></li>
  </ul>
</div>
<div class="top02">
  <p class="top02_left"><span class="icon_user">��ӭ��${session.userInfos.czyMc}��<font id="showGw" class="font_red">${session.userInfos.gsjc} | ${session.userInfos.bmjc} | ${session.userInfos.gwmc}</font>��</span>
  <span class="icon_gw" style="text-indent:16px;"><a href="#" id="gwqhBtn">�л���λ</a></span>

  <span class="logTime">  ��¼ʱ�䣺
  
  <script language=JavaScript>
	function time(){ 
		var now = new Date(); 
		var yy = now.getFullYear(); 
		var mm = now.getMonth(); 
		var dd = now.getDate();
		var hour=now.getHours() < 10 ? "0"+now.getHours() : now.getHours();
		var minute=now.getMinutes() < 10 ? "0"+now.getMinutes() : now.getMinutes();
		var second=now.getSeconds() < 10 ? "0"+now.getSeconds() : now.getSeconds();
		var mmm=new Array();
		mmm[0]="01";
		mmm[1]="02";
		mmm[2]="03";
		mmm[3]="04";
		mmm[4]="05";
		mmm[5]="06";
		mmm[6]="07";
		mmm[7]="08";
		mmm[8]="09";
		mmm[9]="10";
		mmm[10]="11";
		mmm[11]="12";
		mm=mmm[mm];
		var time = yy + "-" + mm +"-"+dd + " " + hour + ":" + minute;
		return time;
	}
	
	document.write(time());
	
  </script>
  
  </span></p>
  <p class="top02_right"><span class="smenu01"><a href="#">��˾��վ</a></span><span class="smenu02"><a href="#" id="xtszBtn">����</a></span><span class="smenu03"><a href="#" id="logoutBtn">���µ�¼</a></span><span class="smenu04"><a href="#" id="outSystemBtn">�˳�</a></span></p>
</div>

<script language="javascript">
	$(function(){
		//����˳�
		$("#logoutBtn").click(function() {
			parent.parent.document.getElementById("FRM_RIGHT").contentWindow.showConfirm("ȷ��Ҫ���µ�¼��","yesCallBackOne");				
		});
		
		$("#outSystemBtn").click(function() {
			parent.parent.document.getElementById("FRM_RIGHT").contentWindow.showConfirm("ȷ��Ҫ�˳�ϵͳ��","yesCallBackTwo");				
		});
		
		$("#xtszBtn").click(function() {
			var url = jcontextPath+"/zygl/xtsz!init"; 
			window.showModalDialog(url,window,"dialogHeight:430px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
			//parent.parent.document.getElementById("FRM_RIGHT").contentWindow.popwindow(url,760,380);
			//navigateMenu(url,'ϵͳ����','true')
		});
		$("#gwqhBtn").click(function(){
		    var url = jcontextPath+"/xtgl/login!initGwqh"; 
		    var result = window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:400px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    if(result!=null){
		       $('#showGw').text(result.name);
		       parent.parent.document.getElementById("FRM_RIGHT").contentWindow.closeAll();
		    }
		    
		})
	});
	
	
	
	/*ϵͳ�˳�*/
	//window.onunload=exit;

	function logout(){
	
		if(!confirm("��ȷ��Ҫ�˳�ϵͳ��")){
			return;
		}
		//ϵͳ�˳�
		exit();
		setTimeout("window.opener=null;top.close()",3)
	 }

	var req = false

	/*Session����*/
	function exit(){
		var url = jcontextPath + "/xtgl/login!logout.action";
		try{
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
				if (req.overrideMimeType) {
					req.overrideMimeType('text/xml');
				}
			}
			else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
//            alert("send...");
		req.open("POST",url,true);
		req.setRequestHeader("Connection","close");
		req.onreadystatechange = listener;
		req.send(null);
		}catch(e){
			alert(e.message);
		}
	}

	function listener(){
		try {
			if(req.readyState == 4){
				req.abort();
			}
		}catch(e){
			alert(e.message);
		}
	}
</script>
<%@include file="/common/message.jsp" %>
</body>
</html>
