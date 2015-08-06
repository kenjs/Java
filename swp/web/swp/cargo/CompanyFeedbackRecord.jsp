<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%
String contactMobilephone = request.getParameter("contactMobilephone");
String mark = request.getParameter("mark");
 %>
<head>
<title>快到网-营销平台-企业电话回复</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<script type="text/javascript">
$(function(){
	getCompany();
	getCargoWList();
});

 //获取公司信息
 function getCompany() {
 	$.ajax({
		url: jcontextPath + "/openCompanyFeedbackRecordPagexinx.jspx",
		type:'post',
		dataType:'json',
		data:{'contactMobilephone':<%=contactMobilephone%>,'mark':<%=mark%>},
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var tradeListObj=data.content;
				$("#htmlqyxx1").html(tradeListObj.html1);
				$("#htmlqyxx2").html(tradeListObj.html2);
				$("#htmlqyxx3").html(tradeListObj.html3);
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

 //获取配车登记货源
 function getCargoWList() {
 	$.ajax({
		url: jcontextPath + "/openCompanyFeedbackRecord.jspx",
		type:'post',
		dataType:'json',
		data:{'contactMobilephone':<%=contactMobilephone%>,'mark':<%=mark%>},
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var tradeListObj=data.content;
				$("#html1").html(tradeListObj.html1);
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

 //获取登记结果货源
 function getCargoDList() {
 	$.ajax({
		url: jcontextPath + "/queyrCompanyCargoyList.jspx",
		type:'post',
		dataType:'json',
		data:{'contactMobilephone':<%=contactMobilephone%>,'mark':<%=mark%>},
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var tradeListObj=data.content;
				$("#html2").html(tradeListObj.html1);
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
 //登记联系企业
 function saveCompanyRecord() {
 	var replyResult = $("input[name='replyResult']:checked").val();
 	if(replyResult == "" || replyResult == null) {
 		return;
 	}
	var remark = $("#remark").val();
  	$.ajax({
		url: jcontextPath + "/saveCompanyContacts.jspx",
		type:'post',
		dataType:'json',
		data:{'contactMobilephone':<%=contactMobilephone%>,'replyResult':replyResult,'remark':remark},
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var tradeListObj=data.result;
				if(tradeListObj == '0') {//登记成功
					getCompany();
					getCargoWList();
				}
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

 //登记货源
 function setCargoHxYz(id,cargoResult,cargoId) {
 	var replyResult = $("#replyResultHtmlId").val();
	if(replyResult == "") {
		alert("请先登记企业,不然不能操作货源！");
		return;
	}else if(replyResult == "0") {
 		alert("企业号码无效，不能操作货源!");
 		return;
 	}else if (replyResult == "" || replyResult == null){
		alert("企业没有登记，不能操作货源!");
		return;
	}
  	$.ajax({
		url: jcontextPath + "/saveCompAndCargoContacts.jspx",
		type:'post',
		dataType:'json',
		data:{'id':id,'cargoResult':cargoResult,'cargoId':cargoId},
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var tradeListObj=data.result;
				if(tradeListObj == '0') {//登记成功
					getCargoWList();
				}
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

  function saveSuccess(){
		//弹添加vip成功提示信息框
	   	var contenthtml = '<div class="buced">'+
	   	'<div class="fl ticks" style="width:100px;"><img src="' + jcontextPath + '/resource/image/index/jou.jpg" width="70" height="70" /></div>'+
	   	'<div class="fr frcks" style="width:294px;">恭喜您！保存成功！</div>'+
	   	'<div class="botcks" style="width: 450px;">'+
	   	'<a style="margin-left: 132px;" href="javascript:pageJump(\''+url+'\');">返回首页</a>'+
	   	'</div>';
	   	artDialogPrompts(contenthtml);
	}



  function getCarCompand(cargoId,assistId,mark,str,companyName) {
  		var replyResult = $("input[name='replyResult']:checked").val();
 		if(replyResult == "0") {
 			alert("企业号码无效，不能操作货源!");
 			return;
 		}else if(replyResult == "" || replyResult == null){
			alert("企业没有登记，不能操作货源!");
			return;
		}

  		if(str == "2") {
  			alert("请先登记货源信息在配车!");
  			return;
  		}else {
  			art.dialog.open(jcontextPath + '/swp/driver/queryCargoMateDriver.jsp?cargoId='+cargoId+'&assistId='+assistId+'&mark='+mark+'&companyPhone='+<%=contactMobilephone%>+'&companyName='+companyName,
                {id: 'N36900', title: '系统匹配车源', width: 900, height: 600, lock: true, close: function () {
                    if (this.close) {
                        art.dialog.open.origin.$('#mainForm')[0].reset();
                    }
                }}
    		);
  		}
  }
  //鼠标移上
  function setOnmouseover(str){
	$("#"+str).css('display','block');
  }
  //鼠标移下
  function setOnmouseout(str){
  	$("#"+str).css('display','none');
  }
</script>

</head>

<body>
<input type="hidden" value="${orderCargoInfoDomain.mark }" name="mark" id="markId"/><!-- 区分查询今天和历史的mark -->
<div class="sonafr">
     <div class="data dataf" >
        <h3 class="fllist">发货单位信息</h3>
        <h3 class="fllist">
        	历史登记人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	历史登记时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	历史登记内容
        </h3>
        <!-- 企业信息 -->
        <div id="htmllsjl">
        	<div class="resiz">
	        	<ul id="htmlqyxx1">
	        	<!-- 企业信息 -->
	        	</ul>
	        </div>
	        <div class="ranklist">
	        	<ul id="htmlqyxx2">
	        	<!-- 登记历史信息 -->
	        	</ul>
	        </div>
        </div>
        <div>
        	<!-- 企业登记功能 -->
        	<dl class="detils" id="htmlqyxx3">
        	</dl>
        </div>
        <br/>
    <div class="number">
		<div class="hd">
			<ul>
				<li onmousedown="getCargoWList();">配车登记</li>
				<li onmousedown="getCargoDList();">登记结果</li>
			</ul>
		</div>
		<div class="bd">
			<div style="height:330px; overflow: auto;">
				<table class="detils" border="0" cellpadding="0" cellspacing="0" id="html1">

      			</table>
			</div>
			<div style="height:330px; overflow: auto;">
				<table class="detils" border="0" cellpadding="0" cellspacing="0" id="html2">

      			</table>
			</div>
		</div>
	</div>
    <div class="return fields">
	         <%if("0".equals(mark)){ %>
	         <a href="javascript:pageJump('${contextPath}/queryTodayImportInfo.jspx?mark=<%=mark %>');">返回</a>
	         <%}else { %>
	         	<a href="javascript:pageJump('${contextPath}/queryHistoryImportInfo.jspx?mark=<%=mark %>');">返回</a>
	         <%} %>
    </div>
    <div class="fr twof"> </div>
</div>
 </div>
</body>
</html>
<script src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
jQuery(".number").slide({trigger:"click"});
(function($){

	$.fn.myScroll = function(options){
	//默认配置
	var defaults = {
		speed:40,  //滚动速度,值越大速度越慢
		rowHeight:24 //每行的高度
	};

	var opts = $.extend({}, defaults, options),intId = [];

	function marquee(obj, step){

		obj.find("ul").animate({
			marginTop: '-=1'
		},0,function(){
				var s = Math.abs(parseInt($(this).css("margin-top")));
				if(s >= step){
					$(this).find("li").slice(0, 1).appendTo($(this));
					$(this).css("margin-top", 0);
				}
			});
		}

		this.each(function(i){
			var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
			intId[i] = setInterval(function(){
				if(_this.find("ul").height()<=_this.height()){
					clearInterval(intId[i]);
				}else{
					marquee(_this, sh);
				}
			}, speed);

			_this.hover(function(){
				clearInterval(intId[i]);
			},function(){
				intId[i] = setInterval(function(){
					if(_this.find("ul").height()<=_this.height()){
						clearInterval(intId[i]);
					}else{
						marquee(_this, sh);
					}
				}, speed);
			});

		});

	}

})(jQuery);

$(function(){

	$("div.ranklist").myScroll({
		speed:40,
		rowHeight:22
	});

});
</script>
