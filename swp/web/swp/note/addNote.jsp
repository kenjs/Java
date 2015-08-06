<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/transaction/transaction.js"></script>
   
	<script src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/jquery.artDialog.js"></script>
	<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
	
	<title>快到网-营销平台-管理中心</title>
	<style>
	*{ margin:0px; padding:0px; font-size:12px; font-family:"微软雅黑"; }
	.mobnr{ width:100%; color:#333;}
	.mobnr tr td{ height:30px; border-bottom:dashed 1px #dbdbdb; line-height:30px; padding:0 10px;}
	.mobnr thead td{ border-bottom:2px solid #ddd; font-size:14px;}
	</style>
	
	<script type="text/javascript">
   		//成功提示
function queryNote() {
	var sendType = $('input:radio:checked').val();
	if(sendType == "") {
		return;
	}
	var contenthtml = '<div class="frkst">对不起！暂无短信模板！</div>';
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/querySystemNoteTemplateHtml.jspx",
	   	dataType:"json",
	    data:{businessType:'1',sendType:sendType}, //参数     	
		success:function(data){//回传函数
			var result = data.result;
			if(result == '-1') {
				window.location.href = jcontextPath + "/";
			}else {
				var dataObj = data.content;
				if(dataObj != "" && dataObj != null) {
					contenthtml = dataObj;
				}
			}
	   	}
	});
	art.dialog({
		width:800,
		height:200,
		id: 'shake-demo—Id',
		title: '短信模板列表',
		content: contenthtml,
		lock: true,
		fixed: true,
		cancel:false,
		button:[{name: '关闭'}]
	});
}
function getNoteContent(str) {
	$("#content").val(str);
	closeUp();
} 
//关闭所有对话框
function closeUp() {
	var list = art.dialog.list;
	for (var i in list) {
    	list[i].close();
	};
}
function getNoteSubmit(){
	var mobilephone = $("#mobilephone").val();
	if(mobilephone == "") {
		return;
	}else {
		var errorNote = "";
		var arr1 = new Array(); 
		arr1 = mobilephone.split(',');
		for(var i = 0;i<arr1.length;i++) {
			if(bilenumber(arr1[i]) == false) {
				errorNote = arr1[i]+',';
			}
		}
		if(errorNote != '') {
			alert(errorNote);
			return;
		}
	}
	var content = $("#content").val();
	if(content == '') {
		return;
	}
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/addMarketingNoteRecord.jspx",
	   	dataType:"json",
	    data:{mobilephone:mobilephone,content:content}, //参数     	
		success:function(data){//回传函数
			var dataObj = data.result;
			if(dataObj == '-1') {
				window.location.href = jcontextPath + "/";
			}else if(dataObj == '1'){
				alert("手机号码不能为空！");
			}else if(dataObj == '2'){
				alert("短信内容不能为空！");
			}else {
				setNote();
			}
	   	}
	});
}

function setNote() {
	art.dialog({
		width:200,
		height:100,
		id: 'shake—Id',
		title: '提示',
		content: '<div class="frkst">短信发送成功！</div>',
		lock: true,
		fixed: true,
		cancel:false,
		button:[{name: '确定', callback: function(){
			$("#mobilephone").val("");
			$("#content").val("");
		}}]
	});
}
</script>
</head>
<body>
<div class="mian">
	<div class="mian_bor">
		<jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
	    <div class="fr sonafr">
	    	<div class="flant flonba " style="width:600px; border:none;" >
	    		<h3><i>&nbsp;</i>短信发送</h3>
		    	<div class="laing shng">
			        <ul>
			       		<li><p style="color:#f00;">手机号码请以英文的逗号","隔开</p></li>
			          	<li>
				           	<input name="" type="button" onclick="queryNote()" value="选择模板" class="sub" style=" float:right; border:solid 1px #999; border-radius:3px;" />
				          	<label class="fl">手机号码</label>
				            <textarea id="mobilephone" name="mobilephone" style="width: 330px;height: 45px;"></textarea>
			          	</li>
			          	<li>
				          	<label class="fl">发送类型</label>
				            <input type="radio" id="sendType" name="sendType" value="2" checked="checked"  />企业&nbsp&nbsp&nbsp
				            <input type="radio" id="sendType" name="sendType" value="1"  />司机
			          	</li>
			          	<li>
				          	<label class="fl">发送内容</label>
				            <textarea id="content" name="content" readonly="readonly" style="width: 330px;height: 45px;" ></textarea>
			          	</li>
			          	<li class="ternow">
				            <label></label>
				            <input name="" type="button" onclick="getNoteSubmit()" value="发送短信" class="sub" />&nbsp;&nbsp;
			            </li>
			        </ul>
		        	<div style="clear:both;"></div>
		      	</div>
	    	</div>
		</div>   
    </div>
</div>
</body>
</html>
