<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>���˵�-������ϸ</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	var hwflObserverFlag = true;
	var flag1=0;
	<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String qybm = userDomain.qybm;
	%>
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initJsJldwDm();
		initHwflDm();
		var khDjxh = trim($("#mainForm_domain_fhrDjxh").val()); 
		initHyhwData(200,khDjxh);
		initJldw();
		$("#mainForm_domain_hwmxDomain_hwBzHldwDm").focus();
		$("#hwmcShow2").hide();
		$("#showSaveHw2").hide();
		initXzqhData(200);
	   //��������input����
	   initXzqhInputSel();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
	    if (fhrDjxh != "") {
		  initHyZhdzData(fhrDjxh,300);
		  initHyShDwData(fhrDjxh,300);
	    }
		$("#mainForm_domain_fhrDjxh2").val($("#mainForm_domain_fhrDjxh").val());
		$("#mainForm_domain_fhrMc2").val($("#mainForm_domain_fhrMc").val());
		
		//��ʼ�� ���
		doEmptyWhenChange(0);//1��ʾ�� 0��ʾ��ʼ��
		
		$(".saveHwBtn").click(function(){
			doSaveHwStart(0);
		});
		$("#emptyHwBtn").click(function(){
		     emptyForm();
		})
		$("#mainForm_domain_hwmxDomain_hwflDm").click(function() {
			hwflObserverFlag = false;
		});
		$(".srHjJs").change(function(){
			doSrHjJs();
		});
		
		$("#mainForm_domain_srXf").click(function(){
			if(flag1==0){
				var srHj = trim($("#mainForm_domain_srHj").val());
				$("#mainForm_domain_srXf").val(srHj);
				$("#mainForm_domain_srYj").val("");
				$("#mainForm_domain_srThf").val("");
				$("#mainForm_domain_srHdf").val("");
				if("TS"!="<%=qybm%>"){
					flag1=1;
				}
			}
		});
		$("#mainForm_domain_srYj").click(function(){
			if(flag1==0){
				var srHj = trim($("#mainForm_domain_srHj").val());
				$("#mainForm_domain_srXf").val("");
				$("#mainForm_domain_srYj").val(srHj);
				$("#mainForm_domain_srThf").val("");
				$("#mainForm_domain_srHdf").val("");
				if("TS"!="<%=qybm%>"){
					flag1=1;
				}
			}
		});
		$("#mainForm_domain_srThf").click(function(){
			if(flag1==0){
				var srHj = trim($("#mainForm_domain_srHj").val());
				$("#mainForm_domain_srXf").val("");
				$("#mainForm_domain_srYj").val("");
				$("#mainForm_domain_srThf").val(srHj);
				$("#mainForm_domain_srHdf").val("");
				if("TS"!="<%=qybm%>"){
					flag1=1;
				}
			}
		});
		$("#mainForm_domain_srHdf").click(function(){
			if(flag1==0){
				var srHj = trim($("#mainForm_domain_srHj").val());
				$("#mainForm_domain_srXf").val("");
				$("#mainForm_domain_srYj").val("");
				$("#mainForm_domain_srThf").val("");
				$("#mainForm_domain_srHdf").val(srHj);
				if("TS"!="<%=qybm%>"){
					flag1=1;
				}
			}
		});
		$(".hwflObserver").change(function(){
			var xh = $("#mainForm_domain_hwmxDomain_xh").val();
			if (hwflObserverFlag) {
				if ($("#mainForm_domain_hwmxDomain_hwTj").val() != "" && $("#mainForm_domain_hwmxDomain_hwZl").val() == "") {
					$("[name='hwflDm']")[1].checked = true;
				}else if ($("#mainForm_domain_hwmxDomain_hwZl").val() != "" && $("#mainForm_domain_hwmxDomain_hwTj").val() == "") {
					$("[name='hwflDm']")[0].checked = true;
				}else if ($("#mainForm_domain_hwmxDomain_hwZl").val() != "" && $("#mainForm_domain_hwmxDomain_hwTj").val() != "") {
					var ZlTjProportion = $("#mainForm_domain_hwmxDomain_ZlTjProportion").val();
					if (ZlTjProportion != "") {
						var zl = $("#mainForm_domain_hwmxDomain_hwZl").val();
						var tj = $("#mainForm_domain_hwmxDomain_hwTj").val();
						if (zl/1 > tj/1*ZlTjProportion) {
							$("[name='hwflDm']")[0].checked = true;
						}else {
							$("[name='hwflDm']")[1].checked = true;
						}
					}
				}
			}
		});
		$(".bjjs").change(function(){
			var bjbl = $("#bjbl").val()/1;
			var fySmjz = trim($("#mainForm_domain_hwmxDomain_fySmjz").val());
			if(isNaN(fySmjz)){
				showAlert("������ֵ���������֣�");
				$("#mainForm_domain_hwmxDomain_fySmjz").val("");
				return;
			}
			fySmjz=fySmjz/1;
			$("#mainForm_domain_hwmxDomain_srBjf").val((fySmjz*bjbl).toFixed(2));
			
			doSrHjJs();
		});
	});
	
	
	function emptyForm() {
	    $("#lrbz").text("������Ϣ��������");
	    hwflObserverFlag = true;
	    flag1=0;
	    $("#hwmcShow1").show();
	    $("#hwmcShow2").hide();
	    $("#showSaveHw1").show();
	    $("#showSaveHw2").hide();
	    
	    $("#mainForm_domain_hwmxDomain_hwDjxh").val("");
	    $("#mainForm_domain_hwmxDomain_hwxhDjxh").val("");
	    $("#mainForm_domain_hwmxDomain_hwmc").val("");
		$("#mainForm_domain_hwmxDomain_hdbh").val("");
		$("#mainForm_domain_hwmxDomain_hwSl").val("");
		$("#mainForm_domain_hwmxDomain_hwZl").val("");
		$("#mainForm_domain_hwmxDomain_hwTj").val("");
		
		$("#mainForm_domain_hwmxDomain_xh").val("");
		$("select").val("");
		var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
		if(xtcs20000=="1"){
			$("#shfsDm").val("2");
		}
		if("SJ"=="<%=qybm%>"){
			$("#shfsDm").val("1");
		}
		initJldw();
		$("#mainForm_domain_hwmxDomain_hwmc").focus();
		$("#mainForm_domain_hwmxDomain_hwmc").select();
		
		var s="";
		$("#mainForm_domain_srHj").val(s);
		$("#mainForm_domain_srXf").val(s);
        $("#mainForm_domain_srHdf").val(s);
        $("#mainForm_domain_srThf").val(s);
        $("#mainForm_domain_srYj").val(s);
        $("#mainForm_domain_srHf").val(s);
        $("#mainForm_domain_srHk").val(s);
        
        $("#mainForm_domain_hwmxDomain_srYsf").val(s);
		$("#mainForm_domain_hwmxDomain_srPsf").val(s);
		$("#mainForm_domain_hwmxDomain_srBjf").val(s);
		$("#mainForm_domain_hwmxDomain_fySmjz").val(s);
		$("#mainForm_domain_hwmxDomain_fyDshk").val(s);
	}
	function doSrHjJs() {
			var srYsf = trim($("#mainForm_domain_hwmxDomain_srYsf").val());
			var srPsf = trim($("#mainForm_domain_hwmxDomain_srPsf").val());
			var srBjf = trim($("#mainForm_domain_hwmxDomain_srBjf").val());
			var srHk = trim($("#mainForm_domain_srHk").val());
			if(isNaN(srYsf)){
				showAlert("����ѱ��������֣�");
				$("#mainForm_domain_hwmxDomain_srYsf").val("");
				return;
			}
			if(isNaN(srPsf)){
				showAlert("���ͷѱ��������֣�");
				$("#mainForm_domain_hwmxDomain_srPsf").val("");
				return;
			}
			if(isNaN(srHk)){
				showAlert("�ؿ۱��������֣�");
				$("#mainForm_domain_hwmxDomain_srHk").val("");
				return;
			}
			$("#mainForm_domain_srHj").val(srPsf/1+srBjf/1+srYsf/1+srHk/1);
			flag1=0;
	}
</script>
</head>

<body>
<%try{ %>

	
	<s:hidden name="domain.hwmxDomain.xh" />
	<s:hidden name="domain.hwmxDomain.hwflDm" />
	
	<s:hidden name="domain.hwmxDomain.jsJldwFlDm" />
	<s:hidden name="domain.hwmxDomain.qyHwBzJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwSlJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwZlJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwTjJldwDm" />
	<s:hidden name="domain.hwmxDomain.ZlTjProportion" cssClass="notChange"/>
	

	
	<s:hidden name="hwmcData"></s:hidden>
	
		
		<fieldset>
		<legend id="lrbz">������Ϣ��������</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
  			<tr>
  				<td width="10%" class="td_noborder"></td>
  				<td width="10%" class="td_noborder"></td>
  				<td width="10%" class="td_noborder"></td>
  				<td width="13%" class="td_noborder"></td>
  				
  				<td width="10%" class="td_noborder"></td>
  				<td width="13%" class="td_noborder"></td>
  				<td width="12%" class="td_noborder"></td>
  				<td width="12%" class="td_noborder"></td>
  				<td width="10%" class="td_noborder"></td>
  			</tr>
  			<tr>
  				<td align="right">�����ˣ�</td>
  				<td>
  					<div class="inputsel" style="width: 100%; ">
  						<s:textfield name="domain.fhrLxr" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:60%"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhrLxr" onFocus="this.blur()"></a>
					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhrLxr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right" >������λ��</td>
  				<td colspan="2">
  					    <s:hidden name="domain.fhrDjxh2"></s:hidden>
  						<s:textfield name="domain.fhrMc2" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
  				</td>
  				
  				<td align="right">��ϵ�绰��</td>
  				<td>
  					<s:textfield name="domain.fhrLxdh" cssClass="pop_input noborder bgstyle_optional" />
  				</td>
  				
  				<td align="right"><button type="button" class="pop_btnbg noTabSelect" id="emptyHwBtn">���</button></td>
  				<td id="showSaveHw1"><button type="button" class="pop_btnbg saveHwBtn noTabSelect" id="saveHwBtn">��ӻ���</button></td>
  				<td id="showSaveHw2"><button type="button" class="pop_btnbg saveHwBtn noTabSelect" id="saveHwBtn">�������</button></td>
  			</tr>
  			<tr>
  				<td align="right">ʼ���أ�</td>
  				<td>
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 100%;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 60%;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
  				</td>
  				<td align="right">������ַ��</td>
  				<td colspan="2">
  					<s:textfield name="domain.fhrDz" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
  				</td>
  				
  				<td align="right">�Ƿ������</td>
  				<td>
  					<s:radio name="thflDm" list="#{'1':' ��','':' ��' }" theme="simple"></s:radio>
  				</td>
  				<td align="right">�������ڣ�</td>
  				<td>
  					<input type="text" name="domain.yqFhrq" id="mainForm_domain_yqFhrq" value="<s:date name="domain.yqFhrq" format="yyyy-MM-dd" />" class="ymdate"  />
  				</td>
  			</tr>
  			<tr>
  				<td align="right">�ջ��ˣ�</td>
  				<td>
  					<div class="inputsel" style="width: 100%; ">
  						<s:textfield name="domain.shrLxr" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:60%"></s:textfield>
  						<a href="#" class="icon_arrow" id="shrLxr" onFocus="this.blur()"></a>
					</div>
			  		<div class="inputsc">
		              <div id="inputSel_shrLxr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right">�ջ���λ��</td>
  				<td colspan="2">
  					<s:hidden name="domain.shrDjxh"></s:hidden> 					
  					<s:textfield name="domain.shrMc" cssClass="pop_input noborder inputext bgstyle_optional"></s:textfield>
  				</td>
  				
  				<td align="right">��ϵ�绰��</td>
  				<td>
  					<s:textfield name="domain.shrLxdh" cssClass="pop_input noborder bgstyle_optional" />
  				</td>
  				<td colspan="2"></td>
  			</tr>
  			<tr>
  				<td align="right" >Ŀ�ĵأ�</td>
  				<td>
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 100%">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 60%;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
  				</td>
  				<td align="right">�ջ���ַ��</td>
  				<td colspan="2">
  					<s:textfield name="domain.shrDz" cssClass="pop_input noborder bgstyle_optional" />
  				</td>
  				
  				<td align="right">�ͻ���ʽ��</td>
  				<td >
  					<s:select list="#{'':' ---��ѡ��---','1':' ����','2':' �ͻ�' }" id="shfsDm" cssClass="select"></s:select>
  				</td>
  				
  				<td align="right">�������ڣ�</td>
  				<td>
  					<input type="text" name="domain.yqDdrq" id="mainForm_domain_yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />" class="ymdate"  />
  				</td>
  			</tr>
  			<tr>
      				<td align="right">�������ƣ�</td>
      				<td colspan="2">
      					<s:hidden name="domain.hwmxDomain.hwDjxh"></s:hidden>
      					<s:hidden name="domain.hwmxDomain.hwxhDjxh"></s:hidden>
      					<s:hidden name="domain.hwmxDomain.sfhdBz"></s:hidden>
      					<div id="hwmcShow1">
	      					<div class="inputsel" style="width:97%">
		  						<s:textfield name="domain.hwmxDomain.hwmc" cssClass="pop_input noborder inputext bgstyle_required" cssStyle="width:80%;"></s:textfield>
		  						<a href="#" class="icon_arrow" id="hwmc" onFocus="this.blur()"></a>
		  					</div>
	  					</div>
	  					<div id="hwmcShow2">
	  					    <input type="text" id="hwmc2" class="pop_input noborder inputext bgstyle_readonly"/>
		                </div>
				  		<div class="inputsc">
			              <div id="inputSel_hwmc" class="inputselcont inputselFixedSize ac_results">
			              </div>
			              <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:200px; height:200px;"></iframe>
			            </div>
      				</td>
      				<td align="right">ǩ�ջص�<s:checkbox name="sfhd" checked="true" id="sfhd" style="width:20px;"></s:checkbox>�ص��ţ�</td>
      				<td >
      					<s:textfield name="domain.hwmxDomain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      				<td colspan="3" style="display: none;">
      					<div class="font_red" >(ע������ص�����ö��ŷָ�)</div>
      				</td>
      				<td align="right">���㣺</td>
      				<td >
      				    <s:select list="#{'':' ---��ѡ��---','01':' ������','02':' ������','03':' �����' }" id="jsJldwFlDm" cssClass="select"></s:select>
      				</td>
      				<td align="right">��װ��</td>
      				<td>
      					<sys:QyBzJldw myName="domain.hwmxDomain.hwBzHldwDm" myId="mainForm_domain_hwmxDomain_hwBzHldwDm" contaisQxz="true" myClass="select">   						
      					</sys:QyBzJldw>
      				</td>
      			</tr>
		</table>
		
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="6%" class="td_noborder"></td>
      				<td width="12%" class="td_noborder"></td>
      				
      				<td width="8%" class="td_noborder"></td>
      				<td width="14%" class="td_noborder"></td>

      			</tr>
      			
      			<tr>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<sys:QySlJldw myName="domain.hwmxDomain.hwSlJldwDm" myId="mainForm_domain_hwmxDomain_hwSlJldwDm" contaisQxz="false" myClass="select noTabSelect">
      					</sys:QySlJldw>
      				</td>      			
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_optional hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyZlJldw myName="domain.hwmxDomain.hwZlJldwDm" myId="mainForm_domain_hwmxDomain_hwZlJldwDm" contaisQxz="false" myClass="select noTabSelect">      						
      					</sys:QyZlJldw>
      				</td>
      				<td align="right">�����</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_optional hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyTjJldw myName="domain.hwmxDomain.hwTjJldwDm" myId="mainForm_domain_hwmxDomain_hwTjJldwDm" contaisQxz="false" myClass="select noTabSelect">      					
      					</sys:QyTjJldw>
      				</td>
      				
      				<td align="right">���ͣ�</td>
      				<td>
      					<s:radio id="hwflDm" name="hwflDm" list="#{'1':' �ػ�','2':' �ݻ�' }" theme="simple"></s:radio>
      				</td>
      				<td align="right">���ջ��</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.fyDshk" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      		   </tr>
      		   <tr>
  				<td align="right">�����:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srYsf" cssClass="pop_input inputright noborder bgstyle_optional srHjJs" ></s:textfield>
  				</td>
  				<td align="right">���ͷ�:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srPsf" cssClass="pop_input inputright noborder bgstyle_optional srHjJs" ></s:textfield>
  				</td>
  				<td  class="jsfsChanged" align="right">�ؿۣ�</td>
  				<td class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srHk" cssClass="pop_input inputright noborder bgstyle_optional srHjJs " ></s:textfield>
  				</td>
  				<td align="right">���۷�:</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.srBjf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right"  class="jsfsChanged">�����룺</td>
  				<td class="jsfsChanged" >
  					<s:textfield name="domain.srHj" cssClass="pop_input inputright noborder bgstyle_optional  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				
  				<td align="right" >�ָ���</td>
  				<td colspan="2">
  					<s:textfield name="domain.srXf" cssClass="pop_input inputright noborder bgstyle_optional " ></s:textfield>
  				</td>
  				<td align="right" class="df">������</td>
  				<td class="df" colspan="2">
  					<s:textfield name="domain.srHdf" cssClass="pop_input inputright noborder bgstyle_optional " ></s:textfield>
  				</td>
  				<td align="right" class="tf">������</td>
  				<td class="tf" colspan="2" >
  					<s:textfield name="domain.srThf" cssClass="pop_input inputright noborder bgstyle_optional " ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" >�½᣺</td>
  				<td  class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srYj" cssClass="pop_input inputright noborder bgstyle_optional  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" style="display: none;">�ظ���</td>
  				<td  class="jsfsChanged" style="display: none;">
  					<s:textfield name="domain.srHf" cssClass="pop_input inputright noborder bgstyle_optional  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right">������ֵ��</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.fySmjz" cssClass="pop_input inputright noborder bgstyle_optional bjjs" ></s:textfield>
  				</td>
  				<td align="right">���۱��ʣ�</td>
  				<td >
  					<s:select list="#{'0.005':' ǧ��֮��','0.010':' ǧ��֮ʮ' }" id="bjbl" cssClass="select bjjs"></s:select>
  				</td>
  			</tr>
  			<tr >
  				<td align="right">��ע��</td>
  				<td colspan="17">
  					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
  				</td>
  			</tr>
			</table>
		 
		</fieldset>
			
	
	<%@include file="/common/message.jsp" %>

<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
