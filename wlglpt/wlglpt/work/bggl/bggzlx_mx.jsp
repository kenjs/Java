<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>草稿箱</title>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/jquery.MultiFile.js"></script>
<base target='_self'>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	var rylbs="";
	var czrxhs="";
	var rymcs="";
	
	$(function(){
		var saveBz = $("#saveBzId").val();
		if(saveBz=="1"){
				showSuccess("保存成功！","");
		}
		if(saveBz=="2"){
				showSuccess("发送成功！","doOnClose");
		}
		
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
		    var zt = trim($("#mainForm_domain_zt").val()); 
			var bcbzDm = trim($("#mainForm_domain_bcbzDm").val()); 
			var gzlxXh = trim($("#mainForm_domain_gzlxXh").val()); 

			var url = jcontextPath+"/bggl/bggzlx!save";  
	    	var jsonObj = {"domain.zt":zt,"domain.bcbzDm":bcbzDm,"domain.gzlxXh":gzlxXh};   			
			ajaxCommon(url,jsonObj);
		});
		
		$("#selectBtn").click(function(){
			openWindowToDialog(jcontextPath+"/common/ryxzCommon!ryxzCommonInit",430,550,window);  
			$("#mainForm_domain_jsrMcs").val(rymcs);
			$("#mainForm_domain_jsrs").val(czrxhs);	
			$("#mainForm_domain_xtyhflDms").val(rylbs);		
			rylbs ="";		
			czrxhs ="";	
			rymcs="";
		});
		
	});
	
	//bcbzDm=1为暂存，bcbzDm=2为发送
	function onSave(bcbzDm){
			if(!checkdata()){
				return;
			}
			
			if(bcbzDm == 2){
				var jsrs = $("#mainForm_domain_jsrMcs").val();
				if(jsrs == ""){
					showAlert("请选择接收人！");
					return;
				}
			}
			
			$("#bcbzDmId").val(bcbzDm);
			var url = jcontextPath+"/bggl/bggzlx!save"; 

	    	showMessage();
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();

	}
	
	function checkdata(){
		var controlNameArray = ["domain.zt"];
		var labelNameArray = ["主题"];
		var compareValueArray = [200];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	
	//附件下载
	function download(fjxh){
		var gzlxXh = $("#mainForm_domain_gzlxXh").val();
		
		if(trim(gzlxXh)==""){
			return;
		}
		if(fjxh == ""){
			return;
		}
	    var url = jcontextPath+"/bggl/bggzlx!fjxz?domain.gzlxXh="+gzlxXh +"&domain.xh="+fjxh;
	    window.open(url);
	}
	
	//删除附件
	function deleteFj(fjxh,divId){
		var gzlxXh = $("#mainForm_domain_gzlxXh").val();
		
		if(trim(gzlxXh)==""){
			return;
		}
		if(fjxh == ""){
			return;
		}
	    $("#"+divId).remove();
	    var url = jcontextPath+"/bggl/bggzlx!deleteFj";  
	  	var jsonObj = {"domain.gzlxXh":gzlxXh,"domain.xh":fjxh};   			
		ajaxCommon(url,jsonObj,"deleteSuccess",false);
	}
	
	function deleteSuccess(){
		showAlert("删除成功！");
	}
	
	
	function doOnClose(){
		//sysClose();
		//parent.onRefresh();
		window.close();
		
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="bggzlx!initMx" namespace="/bggl" method="post" enctype="multipart/form-data" id="mainForm" name="mainForm">
	<s:hidden name="domain.gzlxXh" id="mainForm_domain_gzlxXh"></s:hidden>
	<s:hidden name="domain.bcbzDm" id="bcbzDmId"></s:hidden>
	<s:hidden name="domain.saveBz" id="saveBzId"></s:hidden>
		<div class="pop_contc" style="height:510px; overflow:auto;">
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="10%" align="center"><font class="font_red">*</font>接受人</td>
      				<td width="90%">
      					<s:hidden name="domain.jsrs"></s:hidden>
      					<s:hidden name="domain.xtyhflDms"></s:hidden>
      					
      					<s:if test="domain.bcbzDm!=2">
      						<s:textfield name="domain.jsrMcs" rows="3" cssClass="pop_input_colspan2 noborder bgstyle_required" readonly="true" cssStyle="width:85%"></s:textfield>&nbsp;<button type="button" class="pop_btnbg" id="selectBtn">接收人选择</button>
      					</s:if>
      					<s:else>
      						<s:textfield name="domain.jsrMcs" rows="3" cssClass="pop_input_colspan2 noborder bgstyle_required" readonly="true"></s:textfield>
      					</s:else>
      				</td>
      			</tr>
      			<tr>
      				<td width="10%" align="center"><font class="font_red">*</font>主题</td>
      				<td width="90%">
      					<s:textfield name="domain.zt" rows="3" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
        			<td width="10%" align="center">邮件内容</td>
      				<td width="90%">
      					<s:textarea name="domain.nr" rows="10" cssClass="ckeditor"></s:textarea>
      				</td>
      			</tr>
      			<tr>
        			<td width="10%" align="center">附件</td>
      				<td width="90%">
      					<s:if test="domain.bcbzDm!=2"><s:file contenteditable="false" cssStyle="width:98%; height:25px;" name="upload" id="fjs"></s:file></s:if>
      					<s:if test="domain.fjList.size() ==0 && domain.bcbzDm==2">无</s:if>
      					<s:else>
 						<s:iterator value="domain.fjList" status="st" id="data">
 						
 						<div id="divFj<s:property value='#data.xh'/>">
      						<a class="font_blue" href="#" onclick='download(<s:property value="#data.xh"/>)'><font color="blue"> <s:property value="#data.fjmc"/></font>
      						<s:if test="domain.bcbzDm!=2"><a class="font_blue" href="#" onclick="deleteFj(<s:property value='#data.xh'/>,'divFj<s:property value='#data.xh'/>')">删 除</a> </s:if>
      						 <br/></a>
      					</div>
      					</s:iterator>
      					</s:else>	
      				</td>
      			</tr>
      			<tr>
      				<td width="10%">
      				</td>
      				<td width="90%">
      				   
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
			<s:if test="domain.bcbzDm!=2">
				<button type="button" class="pop_btnbg"  onclick="onSave(1)">暂 存</button>
				&nbsp;
			 	<button type="button" class="pop_btnbg"  onclick="onSave(2)">发 送</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
			</s:if>
			<s:else>
				<button type="button" class="pop_btnbg" id="closeBtn" onclick="doOnClose();">关 闭</button>
			</s:else>    
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
