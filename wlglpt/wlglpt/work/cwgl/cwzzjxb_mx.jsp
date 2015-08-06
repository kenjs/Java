<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-��ת���²�</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var rq = trim($("#mainForm_domain_rq").val()); 
			var jsDwDjxh = trim($("#mainForm_domain_jsDwDjxh").val()); 
			var jcHj = trim($("#mainForm_domain_jcHj").val()); 
			var jcXj = trim($("#mainForm_domain_jcXj").val()); 
			var jcYk = trim($("#mainForm_domain_jcYk").val()); 
			var jcCk = trim($("#mainForm_domain_jcCk").val()); 
			var zfHj = trim($("#mainForm_domain_zfHj").val()); 
			var zfYfk = trim($("#mainForm_domain_zfYfk").val()); 
			var zfYk = trim($("#mainForm_domain_zfYk").val()); 
			var zfBxfy = trim($("#mainForm_domain_zfBxfy").val()); 
			var byj = trim($("#mainForm_domain_byj").val()); 
			var zjxq = trim($("#mainForm_domain_zjxq").val()); 
			var xbDwDjxh = trim($("#mainForm_domain_xbDwDjxh").val()); 
			
			var xbje = trim($("#mainForm_domain_xbje").val()); 
			if(xbje/1<=0){
				showAlert("�������������0��");
				return;
			}
			var bz = trim($("#mainForm_domain_bz").val()); 
			var zjdbDjxh = trim($("#mainForm_domain_zjdbDjxh").val()); 

			var url = jcontextPath+"/cwgl/cwzzjxb!save";  
	    	var jsonObj = {"domain.rq":rq,"domain.jsDwDjxh":jsDwDjxh,"domain.jcHj":jcHj,
                           "domain.jcXj":jcXj,"domain.jcYk":jcYk,"domain.jcCk":jcCk,"domain.zfHj":zfHj,"domain.zfYfk":zfYfk,
                           "domain.zfYk":zfYk,"domain.zfBxfy":zfBxfy,"domain.byj":byj,"domain.zjxq":zjxq,"domain.xbDwDjxh":xbDwDjxh,
                           "domain.xbje":xbje,"domain.bz":bz,"domain.zjdbDjxh":zjdbDjxh};    			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
	});
	function saveSuc(){
		showAlert("����ɹ���","closeWin");
	}
	function closeWin(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.xbje","domain.bz"];
		var labelNameArray = ["�²����","��ע˵��"];
		var compareValueArray = [14.2,500];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwgl/cwzzjxb!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.zjdbDjxh"></s:hidden>
	<div class="pop_contc">
	<fieldset>
			<legend>������Ϣ</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="35%" class="td_noborder"></td>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="30%" class="td_noborder"></td>
	  			</tr>
				<tr>
      				<td align="center"><font class="font_red">*</font>�²����</td>
      				<td>
      					<s:textfield name="domain.xbje" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      				<td align="center">�ʽ�����</td>
      				<td>
      					<s:textfield name="domain.zjxq" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				
      			</tr>
				<tr>
      				<td align="center">���յ�λ</td>
      				<td>
      					<s:hidden name="domain.jsDwDjxh"></s:hidden>
      					<s:textfield name="domain.jsDwMc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="center">�²���λ</td>
      				<td>
      					<s:hidden name="domain.xbDwDjxh"></s:hidden>
      					<s:textfield name="domain.xbDwMc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">���ý�</td>
      				<td>
      					<s:textfield name="domain.byj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td  align="center">����</td>
      				<td >
      					<s:textfield name="domain.rq" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">��ע</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>
      		</table>
 	</fieldset>
  	<fieldset>
		<legend>�����Ϣ</legend>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="35%" class="td_noborder"></td>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="30%" class="td_noborder"></td>
	  			</tr>
      			<tr>
      				<td align="center">�ϼ�</td>
      				<td>
      					<s:textfield name="domain.jcHj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="center">�ֽ�</td>
      				<td>
      					<s:textfield name="domain.jcXj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">�Ϳ�</td>
      				<td>
      					<s:textfield name="domain.jcYk" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="center">���</td>
      				<td>
      					<s:textfield name="domain.jcCk" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
    	</table>
 	</fieldset>
  	<fieldset>
  		<legend>֧����Ϣ</legend>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="35%" class="td_noborder"></td>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="30%" class="td_noborder"></td>
	  			</tr>
      			<tr>
      				<td align="center">�ϼ�</td>
      				<td>
      					<s:textfield name="domain.zfHj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="center">Ԥ����</td>
      				<td>
      					<s:textfield name="domain.zfYfk" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">���</td>
      				<td>
      					<s:textfield name="domain.zfYk" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="center">��������</td>
      				<td>
      					<s:textfield name="domain.zfBxfy" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">����</td>
      				<td>
      					<s:textfield name="domain.zfQt" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td colspan="2"></td>
      			</tr>
			</table>
		</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>

