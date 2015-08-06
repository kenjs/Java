<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>������������嵥-�������õǼ�</title>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
		initHykhData(300,'','',"jsonData","khMc","khDjxh");
		
		$("#queryBtn").click(function(){
			showMessage();
			$("#mainForm").attr("action", "jssrdzqd!queryFydjList");
			$("#mainForm").submit();
		});
		$("#saveMxBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫ�γ��嵥�ķ��õǼǼ�¼��");
				return;
			}
			
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var xhs = $(":checked[name='xhs'][value!='']");
			if (xhs.length > 0) {
				var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
				var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.existBz":existBz};
				
				jsonStr += jQuery.param(jsonObj);
				var url = jcontextPath+"/hygl/jssrdzqd!saveFydjMx";  
				showMessage();
				ajaxCommon(url,encodeURI(jsonStr),"doSaveMxSuc", false);
			}
		});
		
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	
	function doSaveMxSuc(){ 
		hideMessage();
		window.close();
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		//sysClose();
		//parent.initMx();
		window.close();
	}

</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jssrdzqd!queryMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.qdDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<s:hidden name="jsonData" />
		<div id="maincont" />
		<div class="pop_contc">
		
			<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="100" align="right">�µ����ڣ�</td>
	      				<td width="200">
	      					<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
				          	��
				          	<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
	      				</td>
	      				<td width="100" align="right">�ͻ����ƣ�</td>
	      				<td width="200">
	      					<s:hidden name="domain.khDjxh"></s:hidden>
							<div class="inputsel" style="width: 230px; ">
								<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield> 
								<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a></div>
							<div class="inputsc">
							<div id="inputSel_fhr"
								class="inputselcont inputselFixedSize ac_results"></div>
							</div>
	      				</td>
	      				<td>&nbsp;</td>
	      			</tr>
				</table>
			</fieldset>
			<div class="pop_btn" style="width: 700px;">
				<button type="button" class="pop_btnbg" id="queryBtn">�� ��</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveMxBtn">ȷ ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
			
			<table id="zbTab" width="935" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">���</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="60">���</th>
		        <th width="95">������Ŀ</th>
		        <th width="100">�ͻ�</th>
		        <th width="70">�������</th>
		        <th width="70">�µ�����</th>
		        <th width="100">��������</th>
		        <th width="50">��װ</th>
		        <th width="80">�ص����</th>
		        <th width="50">ʼ����</th>
		        <th width="50">Ŀ�ĵ�</th>
		        <th width="50">����</th>
		        <th width="50">����</th>
		        <th width="50">���</th>
		      </tr>
		      <s:iterator value="domain.fydjList" id="fy" status="sta">
		      	<tr>
	      			<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
	      			<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#fy.fyDjxh" />" /></td>
	      			<td align="right"><s:property value="#fy.je" /></td>
	      			<td align="center"><s:property value="#fy.fydjxmWhMc" /></td>
	      			<td align="center"><s:property value="#fy.khmc" /></td>
	      			<td align="center"><s:property value="#fy.ddbh" /></td>
	      			<td align="center"><s:date name="#fy.xdrq" format="yyyy-MM-dd" /></td>
		      		<td align="center"><s:property value="#fy.hwmc" /></td>
		      		<td align="center"><s:property value="#fy.bz" /></td>
		      		<td align="center"><s:property value="#fy.hdbh" /></td>
		      		<td align="center"><s:property value="#fy.fhrXzqhMc" /></td>
		      		<td align="center"><s:property value="#fy.shrXzqhMc" /></td>
		      		<td align="center"><s:property value="#fy.sl" /></td>
		      		<td align="center"><s:property value="#fy.zl" /></td>
		      		<td align="center"><s:property value="#fy.tj" /></td>
		      	</tr>
		      </s:iterator>   
		    </table>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
