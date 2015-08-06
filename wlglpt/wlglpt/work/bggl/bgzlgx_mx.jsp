<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>�칫-���Ϲ���</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/jquery.MultiFile.js"></script>
<base target='_self'>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		var saveBz = $("#mainForm_domain_saveBz").val();
		if("1"==saveBz){
				showSuccess("����ɹ���","");
		}
		if("2"==saveBz){
				showSuccess("���ͳɹ���","doOnClose");
		}
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var bcztDm="1";
			$("#mainForm_domain_bcztDm").val(bcztDm);
			var url = jcontextPath+"/bggl/bgzlgx!save";  

	    	showMessage();
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();
		});
		
		$("#publishBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var bcztDm="2";
			$("#mainForm_domain_bcztDm").val(bcztDm);
			var url = jcontextPath+"/bggl/bgzlgx!save";  

	    	showMessage();
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();
		});
		$("#pop_closeBtn").click(function(){
			window.close();
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.jgbm","domain.ly",
		                        "domain.zlmc","domain.xjgxbz"];
		var labelNameArray = ["������Χ","��Դ",
		                      "��������","�¼�����"];
		var compareValueArray = [16,200,
			                     200,1];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,
                            true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
		//��������
	function download(fjxh){
		var zlgxDjxh = $("#mainForm_domain_zlgxDjxh").val();
		
		if(trim(zlgxDjxh)=="" || fjxh == ""){
			return;
		}
	    var url = jcontextPath+"/bggl/bgzlgx!fjxz.action?domain.zlgxDjxh="+zlgxDjxh +"&domain.xh="+fjxh;
	    window.open(url);
	}	
	//ɾ������
	function deleteFj(fjxh,divId){
		var zlgxDjxh = $("#mainForm_domain_zlgxDjxh").val();
		
		if(trim(zlgxDjxh)=="" || fjxh == ""){
			return;
		}
	    $("#"+divId).remove();
	    var url = jcontextPath+"/bggl/bgzlgx!deleteFj";  
	  	var jsonObj = {"domain.zlgxDjxh":zlgxDjxh,"domain.xh":fjxh};   			
		ajaxCommon(url,jsonObj,"deleteSuccess",false);
	}
	
	function deleteSuccess(){
		showAlert("ɾ���ɹ���");
	}
	function doOnClose(){
		sysClose();
		
		var zlgxDjxh = $("#mainForm_domain_zlgxDjxh").val();
		if(trim(zlgxDjxh)==""){
			return;
		}
		parent.onRefresh();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgzlgx!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm" enctype="multipart/form-data">
	<s:hidden name="domain.zlgxDjxh"></s:hidden>
	<s:hidden name="domain.bcztDm"></s:hidden>
	<s:hidden name="domain.saveBz"></s:hidden>
		<div class="pop_contc" style="height:320px; overflow:auto;">
			<fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="center"><font class="font_red">*</font>������Χ</td>
      				<td width="35%">
      					<sys:gsBmList myId="mainForm_domain_jgbm" myName="domain.jgbm" mcContainDmBz="Y" contaisQxz="true" myClass="select"/>
      				</td>
      				<td align="center" colspan="2">
      					<s:if test='domain.xjgxbz==\"Y\"'>
      						<s:checkbox name="domain.xjgxbz" label="�¼�����" fieldValue="Y" value="true"></s:checkbox>
      					</s:if>
      					<s:else>
      						<s:checkbox name="domain.xjgxbz" label="�¼�����" fieldValue="Y"></s:checkbox>
      					</s:else>
      					�¼�����
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="center"><font class="font_red">*</font>��������</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.zlmc" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">��Դ</td>
      				<td colspan="3">
      					<s:textfield name="domain.ly"  cssClass="pop_input_colspan2 noborder bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td align="center">����˵��</td>
      				<td colspan="3">
      					<s:textarea name="domain.sm" rows="4" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">����</td>
      				<td colspan="3">
      					<s:file contenteditable="false" cssStyle="width:98%; height:25px;" name="upload" id="fjs"></s:file>
 						<s:iterator value="domain.fjList" status="st" id="data">
 						
 						<div id="divFj<s:property value='#data.xh'/>">
      						<a class="font_blue" href="#" onclick='download(<s:property value="#data.xh"/>)'><font color="blue"> <s:property value="#data.fjmc"/></font></a>
      						<s:if test="domain.bcztDm!=2"><a class="font_blue" href="#" onclick="deleteFj(<s:property value='#data.xh'/>,'divFj<s:property value='#data.xh'/>')">ɾ ��</a> </s:if>
      						 <br/>
      					</div>
      					</s:iterator>
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
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="publishBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="pop_closeBtn">�� ��</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
