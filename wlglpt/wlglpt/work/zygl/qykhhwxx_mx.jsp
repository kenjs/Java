<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>��������ά��</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			if(!checkJldw()){
				return;
			}
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var khDjxh = $("#mainForm_domain_khDjxh").val(); 
			var hwmc = $("#mainForm_domain_hwmc").val(); 
			var hwjc = $("#mainForm_domain_hwjc").val(); 
			var cdJldwDm = $("#mainForm_domain_cdJldwDm").val(); 
			var cd = $("#mainForm_domain_cd").val(); 
			if(regCk(cd)){
				cd = "";
			}
			var kd = $("#mainForm_domain_kd").val(); 
			if(regCk(kd)){
				kd = "";
			}
			var gd = $("#mainForm_domain_gd").val(); 
			if(regCk(gd)){
				gd = "";
			}
			if(cd/1<0){
				showAlert("���Ȳ���С���㣡");
				return;
			}
			if(kd/1<0){
				showAlert("��Ȳ���С���㣡");
				return;
			}
			if(gd/1<0){
				showAlert("�߶Ȳ���С���㣡");
				return;
			}
			var bzJldwDm = $("#mainForm_domain_bzJldwDm").val(); 
			//var bzJsHsbl = $("#mainForm_domain_bzJsHsbl").val(); 
			//var bzCbHsbl = $("#mainForm_domain_bzCbHsbl").val(); 
			var jsJldwDm = $("input:radio[name='domain.jsJldwDm']:checked").val();
			//var cbJldwDm = $("#mainForm_domain_cbJldwDm").val(); 
			var bz = $("#mainForm_domain_bz").val(); 
			var hwDjxh = $("#mainForm_domain_hwDjxh").val(); 
			
			var sl = $("#mainForm_domain_slJldwDm").val(); 
			var zl = $("#mainForm_domain_zlJldwDm").val(); 
			var tj = $("#mainForm_domain_tjJldwDm").val(); 	
			var url = jcontextPath+"/qykhhwxx!save";  
	    	var jsonObj = {"domain.hwmc":hwmc,"domain.ssJgbm":ssJgbm,"domain.khDjxh":khDjxh,
                           "domain.hwjc":hwjc,"domain.cdJldwDm":cdJldwDm,"domain.cd":cd,
                           "domain.kd":kd,"domain.gd":gd,"domain.bzJldwDm":bzJldwDm,
                           "domain.jsJldwDm":jsJldwDm,"domain.bz":bz,"domain.hwDjxh":hwDjxh,
                           "domain.slJldwDm":sl,"domain.zlJldwDm":zl,"domain.tjJldwDm":tj};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	function saveOk(){
			showSuccess("����ɹ���","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
	function regCk(val){
		var pattern=/^0\.0{0,}$/;  
		var bol = pattern.test(val);
		return bol;
	}
	
	function checkJldw(){
		var jsJldw = $("input:radio[name='domain.jsJldwDm']:checked").val();
		if(jsJldw == "" || jsJldw == null){
			showAlert("���������λ����ѡ��");
			return false;
		}else if(jsJldw == 01){
			var slJldwDm = $("#mainForm_domain_slJldwDm").val();
			if(slJldwDm == "" || slJldwDm == null){
				showAlert("����������λ����ѡ��");
				return false;
			}
		}else if(jsJldw == 02){
			var zlJldwDm = $("#mainForm_domain_zlJldwDm").val();
			if(zlJldwDm == "" || zlJldwDm == null){
				showAlert("����������λ����ѡ��");
				return false;
			}
		}else{
			var tjJldwDm = $("#mainForm_domain_tjJldwDm").val();
			if(tjJldwDm == "" || tjJldwDm == null){
				showAlert("���������λ����ѡ��");
				return false;
			}
		}
		return true;
	}
	
	function checkdata(){
		var controlNameArray = ["domain.hwmc",
		                        "domain.hwjc","domain.cdJldwDm","domain.cd",
		                        "domain.kd","domain.gd","domain.bzJldwDm",
		                        "domain.jsJldwDm","domain.bz"];
		var labelNameArray = ["��������",
		                      "������","���ȼ�����λ","����",
		                      "���","�߶�","��װ������λ",
		                      "���������λ","��ע"];
		var compareValueArray = [100,
			                     100,6,10.6,
			                     10.6,10.6,6,
			                     6,200];
		var nodeTypeArray = [NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
                            true,false,false,
                            false,false,false,
                            false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qykhhwxx!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.khDjxh"></s:hidden>
<s:hidden name="domain.ssJgbm"></s:hidden>
<s:hidden name="domain.hwDjxh"></s:hidden>
	<input type="hidden" id="mainForm_domain_hwDjxh" value='<s:property value="domain.hwDjxh"/>'/>
		<div class="pop_contc" style="height:460px; overflow:auto;">
			<fieldset>
			    <legend>�ͻ���Ϣ</legend>
			    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">�ͻ����ƣ�</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.khjc"  cssClass="pop_input_colspan2  bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			</table>
			    </fieldset>
			<fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>�������ƣ�</td>
      				<td width="35%">
      					<s:textfield name="domain.hwmc"  cssClass="pop_input_colspan2  bgstyle_required" ></s:textfield>
      				</td>
      				<td width="15%" align="right"><font class="font_red">*</font>�����ƣ�</td>
      				<td width="35%">
      					<s:textfield name="domain.hwjc"  cssClass="pop_input  bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">���ȣ�</td>
      				<td>
      					<s:textfield name="domain.cd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      				<td width="15%" align="right">���ȼ�����λ��</td>
      				<td width="35%">
      					<sys:QyCdJldw myName="domain.cdJldwDm" myId="mainForm_domain_cdJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QyCdJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">��ȣ�</td>
      				<td>
      					<s:textfield name="domain.kd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">�߶ȣ�</td>
      				<td>
      					<s:textfield name="domain.gd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">����������λ��</td>
      				<td>
      					<sys:QySlJldw myName="domain.slJldwDm" myId="mainForm_domain_slJldwDm" contaisQxz="true" myClass="select_long" mcContainDmBz="Y"></sys:QySlJldw>
      				</td>
      				<td align="right">����������λ��</td>
      				<td>
      					<sys:QyZlJldw myName="domain.zlJldwDm" myId="mainForm_domain_zlJldwDm" contaisQxz="true" myClass="select_long" mcContainDmBz="Y"></sys:QyZlJldw>
      					
      				</td>
      			</tr>
      			<tr>
      				<td align="right">���������λ��</td>
      				<td>
      					<sys:QyTjJldw myName="domain.tjJldwDm" myId="mainForm_domain_tjJldwDm" myClass="select_long" contaisQxz="true" mcContainDmBz="Y"></sys:QyTjJldw>
      				</td>
      				<td align="right">��װ������λ��</td>
      				<td>
      					<sys:QySlJldw myName="domain.bzJldwDm" myId="mainForm_domain_bzJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QySlJldw>
      				</td>
      			</tr>
      				
      			<tr>
      				<td align="right">���������λ��</td>
      				<td colspan="3" >
      					<s:radio list="#{'01':'����','02':'����','03':'���' }" theme="simple" name="domain.jsJldwDm"></s:radio>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">��ע��</td>
      				<td colspan="3" >
      					<s:textarea name="domain.bz" rows="3"  cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�����ˣ�</td>
      				<td>
      					<s:textfield name="domain.cjrMc" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�������ڣ�</td>
      				<td>
      					<s:textfield name="domain.cjrq" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�޸��ˣ�</td>
      				<td>
      					<s:textfield name="domain.xgrMc" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�޸����ڣ�</td>
      				<td>
      					<s:textfield name="domain.xgrq" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
      				</td>
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
