<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�����ͺ�ά��</title>


<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context/>/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

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
			var hwxhDjxh = $("#mainForm_domain_hwxhDjxh").val(); 
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var khDjxh = $("#mainForm_domain_khDjxh").val(); 
			var xhmc = $("#mainForm_domain_xhmc").val(); 
			var xhjc = $("#mainForm_domain_xhjc").val(); 
			var xhqc = $("#mainForm_domain_xhqc").val(); 
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
			var cbJldwDm = $("#mainForm_domain_cbJldwDm").val(); 
			var bz = $("#mainForm_domain_bz").val(); 
			var hwDjxh = $("#mainForm_domain_hwDjxh").val(); 
			
			var sl = $("#mainForm_domain_slJldwDm").val(); 
			var zl = $("#mainForm_domain_zlJldwDm").val(); 
			var tj = $("#mainForm_domain_tjJldwDm").val(); 	
			var url = jcontextPath+"/qykhhwxh!save";  
	    	var jsonObj = {"domain.xhmc":xhmc,"domain.ssJgbm":ssJgbm,"domain.khDjxh":khDjxh,"domain.hwxhDjxh":hwxhDjxh,
                           "domain.xhjc":xhjc,"domain.xhqc":xhqc,"domain.cdJldwDm":cdJldwDm,"domain.cd":cd,
                           "domain.kd":kd,"domain.gd":gd,"domain.bzJldwDm":bzJldwDm,
                           "domain.jsJldwDm":jsJldwDm,"domain.cbJldwDm":cbJldwDm,"domain.bz":bz,"domain.hwDjxh":hwDjxh,
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
		var controlNameArray = ["domain.xhmc",
		                        "domain.xhjc","domain.xhqc","domain.cdJldwDm","domain.cd",
		                        "domain.kd","domain.gd","domain.bzJldwDm",
		                        "domain.jsJldwDm","domain.cbJldwDm","domain.bz"];
		var labelNameArray = ["�ͺ�����",
		                      "�ͺż��","�ͺ�ȫ��","���ȼ�����λ","����",
		                      "���","�߶�","��װ������λ",
		                      "���������λ","�ɱ�������λ","��ע"];
		var compareValueArray = [100,
			                     100,200,6,10.6,
			                     10.6,10.6,6,
			                     6,6,200];
		var nodeTypeArray = [NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
                            true,false,false,false,
                            false,false,false,
                            false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qykhhwxh!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.khDjxh"></s:hidden>
<s:hidden name="domain.ssJgbm"></s:hidden>
<s:hidden name="domain.hwDjxh"></s:hidden>
<s:hidden name="domain.hwxhDjxh"></s:hidden>
	<input type="hidden" id="mainForm_domain_hwDjxh" value='<s:property value="domain.hwDjxh"/>'/>
		<div class="pop_contc" style="height:400px; overflow:auto;">
		   <fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>�ͺ����ƣ�</td>
      				<td width="35%">
      					<s:textfield name="domain.xhmc"  cssClass="pop_input  bgstyle_required" ></s:textfield>
      				</td>
      				<td width="15%" align="right"><font class="font_red">*</font>�ͺż�ƣ�</td>
      				<td width="35%">
      					<s:textfield name="domain.xhjc"  cssClass="pop_input  bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right">�ͺ�ȫ�ƣ�</td>
      				<td width="35%">
      					<s:textfield name="domain.xhqc"  cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      				<td width="15%" align="right">���ȼ�����λ��</td>
      				<td width="35%">
      					<sys:QyCdJldw myName="domain.cdJldwDm" myId="mainForm_domain_cdJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QyCdJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">���ȣ�</td>
      				<td>
      					<s:textfield name="domain.cd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">��ȣ�</td>
      				<td>
      					<s:textfield name="domain.kd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�߶ȣ�</td>
      				<td>
      					<s:textfield name="domain.gd" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">���������λ��</td>
      				<td>
      					<s:radio list="#{'01':'����','02':'����','03':'���' }" theme="simple" name="domain.jsJldwDm"></s:radio>
      				</td>
      			</tr>
      				
      			<tr>
      				<td align="right">��װ������λ��</td>
      				<td>
      					<sys:QySlJldw myName="domain.bzJldwDm" myId="mainForm_domain_bzJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QySlJldw>
      				</td>
      				<td align="right">�ɱ�������λ��</td>
      				<td>
      					<sys:QyAllJldw myName="domain.cbJldwDm" myId="mainForm_domain_cbJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QyAllJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">������</td>
      				<td>
      					<sys:QySlJldw myName="domain.slJldwDm" myId="mainForm_domain_slJldwDm" contaisQxz="true" myClass="select_long" mcContainDmBz="Y"></sys:QySlJldw>
      				</td>
      				<td align="right">������</td>
      				<td>
      					<sys:QyZlJldw myName="domain.zlJldwDm" myId="mainForm_domain_zlJldwDm" contaisQxz="true" myClass="select_long" mcContainDmBz="Y"></sys:QyZlJldw>
      					
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�����</td>
      				<td>
      					<sys:QyTjJldw myName="domain.tjJldwDm" myId="mainForm_domain_tjJldwDm" myClass="select_long" contaisQxz="true" mcContainDmBz="Y"></sys:QyTjJldw>
      				</td>
      				<td colspan="2" ></td>
      			</tr>
      			<tr>
      				<td align="right">��ע��</td>
      				<td colspan="3" >
      					<s:textarea name="domain.bz"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
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
