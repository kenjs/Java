<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���õǼǹ���</title>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkSzje()){
				return;
			}
			var url = jcontextPath+"/hygl/fydjgl!save"
			
			/*var fyclfsDm = $("#mainForm_domain_fyclfsDm").val();
			var khDjxh = $("#mainForm_domain_khDjxh").val();
			
			if(fyclfsDm == "11" || fyclfsDm == "21"){
				if(khDjxh == ""){
					showAlert("���Ʋ���Ϊ��!");
					return ;
				}
			}*/
			//dm 1 Ϊ ��������
			var srje = trim($("#mainForm_domain_srje").val());
			var zfje = trim($("#mainForm_domain_zfje").val());
			var skfCzyDjxh = $("#mainForm_domain_skfCzyDjxh").val();
			//alert(skfCzyDjxh);
			if(isNaN(zfje)&&zfje!=""){
				showAlert("���������֣�");
				return;
			}
			if(isNaN(srje)&&srje!=""){
				showAlert("���������֣�");
				return;
			}
			if(zfje==0){
				if(skfCzyDjxh!=""&&skfCzyDjxh!=null){
					showAlert("������֧����");
					return;
				}
			}
			if(skfCzyDjxh==""||skfCzyDjxh==null){
				if(zfje!=0){
					showAlert("��ѡ���տ��");
					return;
				}
			}
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();
		});
		
		var sj = $("#mainForm_domain_pcDjxh").val();
		commonInit("KHMC", sj, '', "domain.khDjxh", "mainForm_domain_khDjxh", "Y", "N");
		
		//var kh = $("#mainForm_domain_khDjxh").val();
		//initSel(sj,kh);
		//$("#mainForm_domain_khDjxh").change(function(){
		//	initSel($("#mainForm_domain_pcDjxh").val(),this.value);
		//});
		
	});
	
	function doScSendSuc(data) {
		hideMessage();
		showAlert("���ͳɹ���","refreshList");
	}
	
	function refreshList() {
		var url = jcontextPath+"/hygl/fydjgl!initMx.action"
		
		$("#mainForm").attr("action",url);
		$("#mainForm").submit();
	}
	
	function onUpdate(obj) {
		$("#mainForm_domain_srje").val('');
		$("#mainForm_domain_zfje").val('');
		$(":[name='domain.skfCzyDjxh']").val('');		
		$("#mainForm_domain_selOrUpd").val(2);
		
		var rowInedx = $(obj).parents("tr").index();		
		var tabLen = document.getElementById("zTab").rows;
		  var srje = tabLen[rowInedx].cells[3].innerHTML;
		  var zfje = tabLen[rowInedx].cells[4].innerHTML;
		  var skf = $(tabLen[rowInedx].cells[6].innerHTML).val();
		  var fyxm = tabLen[rowInedx].cells[11].innerHTML;
		  var fyDjxh = tabLen[rowInedx].cells[12].innerHTML;
		  
		  $("#mainForm_domain_fyDjxh").val(fyDjxh);
		  $("#mainForm_domain_fydjxmWhXh").val(fyxm);
		  $("#mainForm_domain_srje").val(srje);
		  $("#mainForm_domain_zfje").val(zfje);
		  $(":[name='domain.skfCzyDjxh']").val(skf);
	}
	
	var fyDjxhGlo;
	function onDel(fyDjxh) {
		fyDjxhGlo = fyDjxh;
		showConfirm("ȷ��Ҫɾ���ü�¼��", "doDel");
	}
	
	function doDel() {
		var khDjxh = $("#mainForm_domain_khDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var jsonObj = {"domain.fyDjxh":fyDjxhGlo,"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh,"domain.khDjxh":khDjxh,"domain.ddDjxh":ddDjxh};
		var url = jcontextPath+"/hygl/fydjgl!delete";
		ajaxCommon(url,jsonObj,"deleSuccess");
	}
	
	function checkHw(obj) {
		$(":checkbox[name='delSel']").attr("checked", obj.checked);
	}
	
	function deleSuccess(){
		showAlert("ɾ���ɹ���","refreshWin");
	}
	function refreshWin() {
		var url = jcontextPath+"/hygl/fydjgl!initMx";
		$("#mainForm").attr("action",url);
		$("#mainForm").submit();
	}
	function closeWin(){
		window.close();
	}
	
	function initSel(val1,val2){
		var url = jcontextPath+"/hygl/fydjgl!queryKhHw";
		var jsonObj = {"domain.pcDjxh":val1,"domain.khDjxh":val2};
		ajaxCommon(url,jsonObj,"doInitSel");
	}
	
	/**function  doInitSel(data){
		$("#mainForm_domain_wfhDjxh").empty();
		var list = data.domain.khHwList;
		var s = document.getElementById('mainForm_domain_wfhDjxh');
		var option = document.createElement('option');
        s.options.add(option)
      	option.text = "---��ѡ��---";
      	option.value = "";
		
		for(var i=0;i<list.length;i++){
			var option = document.createElement('option');
			s.options.add(option)
      		option.text = list[i].mc;
      		option.value = list[i].dm;
		}
	}**/
	
	function checkSzje(){
		var srje = trim($("#mainForm_domain_srje").val());
		var zfje = trim($("#mainForm_domain_zfje").val());
		if(isNaN(srje)){
			showAlert("[������]����¼������");
			return false;
		}
		if(isNaN(zfje)){
			showAlert("[֧�����]����¼������");
			return false;
		}
		if(srje == '' && zfje == ''){
			showAlert("[������]��[֧�����]������¼��һ�");
			return false;
		}
		return true;
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="fydjgl!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.pcDjxh"></s:hidden>
		<s:hidden name="domain.wfhDjxh"></s:hidden>
		<s:hidden name="domain.clsxDm"></s:hidden>
		<s:hidden name="domain.fyDjxh"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.ddDjxh"></s:hidden>
		<s:hidden name="domain.xh"></s:hidden>
		<s:hidden name="domain.khDjxh"></s:hidden>
		<s:hidden name="domain.zgsbm"></s:hidden>
		<s:hidden name="domain.selOrUpd"></s:hidden>
		<s:hidden name="domain.fkfCzyDjxh"/>
		
		<div class="pop_contc" >
		<fieldset>
			<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">�ɳ����ţ�</td>
      				<td width="35%">
      					<s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">˾����</td>
      				<td width="35%">
      					<s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�������룺</td>
      				<td>
      					<s:textfield name="domain.cyrClhm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">�ҳ����룺</td>
      				<td>
      					<s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�ɳ��ˣ�</td>
      				<td>
      					<s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�ɳ����ڣ�</td>
      				<td>
      					<s:textfield name="domain.pcrq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�ɳ����ţ�</td>
      				<td>
      					<s:textfield name="domain.pcJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">ҵ��λ��</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      		
			</table>
			</fieldset>
			&nbsp;
			<div style="overflow:auto;">�ѵǼǷ�����Ϣ��</div>
			<table id="zTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="4%">���</th>
			        <th width="12%">����</th>
			        <th width="5%">״̬</th>
			        <th width="6%">������</th>
			        <th width="6%">֧�����</th>
			         <th width="8%">���</th>
			        <th width="14%">�տ</th>
			        <th width="11%">������Ŀ</th>			        
			         <th width="10%">�������</th>
			        <th width="13%">�ͻ�����</th>
			        <th width="11%">��������</th>			        	    
			      </tr>
			      <s:iterator id="zb" value="domain.fyDjMxList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center">
				        	<s:if test='#zb.spbz=="Y" && (#zb.wsspztDm=="0" || #zb.wsspztDm=="2")'>
				        		<a href="javascript:scSend('303001','','<s:property value="#zb.fyDjxh" />','<s:property value="#zb.wsSpxh" />');"><font color="blue">����</font></a>&nbsp;
				        	</s:if>
				        	<a href="#" onclick="javascript:onUpdate(this);"><font color="blue">�޸�</font></a>&nbsp;
				        	<a href="javascript:onDel('<s:property value="#zb.fyDjxh" />');"><font color="blue">ɾ��</font></a>
				        </td>
				        <td align="center">
				        	<s:property value="#zb.wsspztMc" />
				        </td>
				        <td align="center"><s:property value="#zb.srje"/></td>
				        <td align="center"><s:property value="#zb.zfje"/></td>
				        <td align="center"><s:property value="#zb.khmc"/></td>			       
				        <td align="center">
				        	<s:select list="domain.skfList" listKey="dm" listValue="mc" cssClass="select" name="#zb.skfCzyDjxh" disabled="true">				        		
				        	</s:select>
				        </td>
				        <td align="center"><s:property value="#zb.fydjXmmc"/></td>
				        <td align="center"><s:property value="#zb.ddbh"/></td>
				        <td align="center"><s:property value="#zb.khmc"/></td>
				       <td align="center"><s:property value="#zb.hwmc"/></td>
				       <td style="display: none;"><s:property value="#zb.fydjxmWhXh"/></td>				        
				        <td style="display: none;"><s:property value="#zb.fyDjxh" /></td>				        
			      	</tr>
			      </s:iterator>
    		</table>
    		
		    <div style="overflow:auto;">���õǼǣ�</div>		   
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
				<tr>
					<th width="10%">������Ŀ</th>
					<th width="10%">�ͻ�����</th>
					<th width="10%">��������</th>
					<th width="10%">������</th>
					<th width="10%">���</th>
					<th width="10%">֧�����</th>
					<th width="10%">�տ</th>
				</tr>
				<tr>
					<td align="center">
						<sys:FyxmList myId="mainForm_domain_fydjxmWhXh" myName="domain.fydjxmWhXh" myClass="select" ssJgbm="domain.zgsbm" clsxDm="domain.clsxDm"></sys:FyxmList>
					</td>
					<td align="center">
						<s:textfield name="domain.khmc" cssClass="pop_input noborder bgstyle_readonly"/>
					</td>
					<td align="center">
						<s:textfield name="domain.hwmc" cssClass="pop_input noborder bgstyle_readonly"/>
					</td>
					<td align="center">
						<s:textfield name="domain.srje" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
					</td>
					<td align="center">
						<s:textfield name="domain.khmc" cssClass="pop_input noborder bgstyle_readonly"/>
					</td>
					<td align="center">
						<s:textfield name="domain.zfje" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
					</td>
					<td align="center">
						<s:select list="domain.skfList" listKey="dm" listValue="mc" cssClass="select" name="domain.skfCzyDjxh"></s:select>
					</td>
				</tr>		
    		</table>  
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