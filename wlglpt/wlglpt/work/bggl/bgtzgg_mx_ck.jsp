<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>办公-通知公告</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/jquery.MultiFile.js"></script>
<base target='_self'>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#pop_hfBtn").click(function(){
			window.close();
		});
	});
	
	//附件下载
	function download(fjxh){
		var tzggXh = $("#mainForm_domain_tzggXh").val();
		
		if(trim(tzggXh)=="" || fjxh == ""){
			return;
		}
	    var url = jcontextPath+"/bggl/bgtzgg!fjxz.action?domain.tzggXh="+tzggXh +"&domain.xh="+fjxh;
	    window.open(url);
	}	
	function doOnClose(){
		sysClose();
		
		//var tzggXh = $("#mainForm_domain_tzggXh").val();
		//if(trim(tzggXh)==""){
			//return;
		//}
		//parent.onRefresh();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgtzgg!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm" enctype="multipart/form-data">
	<s:hidden name="domain.tzggXh"></s:hidden>
	
		<div class="pop_contc" style="height:320px; overflow:auto;">
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="center">发布范围</td>
      				<td width="35%">
      					<sys:gsBmList myId="mainForm_domain_jgbm" myName="domain.jgbm" mcContainDmBz="Y" contaisQxz="true" myClass="select"/>
      				</td>
      				<td width="15%" align="center">下级共享：</td>
      				<td width="35%" align="left">
      					<s:if test='domain.xjgxbz==\"Y\"'>
      						是
      					</s:if>
      					<s:else>
      						否
      					</s:else>
      				<td>
      			</tr>
      			<tr>
      				<td width="15%" align="center">主题</td>
      				<td width="85%" colspan="3">
      					<s:textfield readonly="true" name="domain.zt" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">内容</td>
      				<td colspan="3">
      					<s:textarea name="domain.nr" readonly="true" rows="5" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">附件</td>
      				<td colspan="3">
      					<s:if test="domain.fjList.size() ==0 && domain.bcztDm==2">无</s:if>
      					<s:else>
 						<s:iterator value="domain.fjList" status="st" id="data">
 						
 						<div id="divFj<s:property value='#data.xh'/>">
      						<a class="font_blue" href="#" onclick='download(<s:property value="#data.xh"/>)'><font color="blue"> <s:property value="#data.fjmc"/></font>
      						 <br/></a>
      					</div>
      					</s:iterator>
      					</s:else>	
      				</td>
      			</tr>

			</table>
			</fieldset>
			<script type="text/javascript">
		    //CKEDITOR.replace('mainForm_domain_nr',{skin : 'kama',language : 'zh-cn'});
		    $(function(){ // wait for document to load 
				$('#fjs').MultiFile({ 
					STRING: {
				   		remove: '<img src="/wlglpt/resource/pageframe/images/bin.gif" height="16" width="16" border="0" alt="x"/>'
				  	}
				}); 
			});
	   		</script>
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="pop_hfBtn">返 回</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
