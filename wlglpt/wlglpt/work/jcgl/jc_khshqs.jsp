<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ͻ��ջ���ϸͳ�Ʊ�</title>

<style type="text/css">
	
	#content{
		border-left: white;border-right: white;border-top: white;border-bottom: white
	}

html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">

	$(function(){
		
	document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
	$("#printBtn").click(function() {
		//document.getElementById("factory").printing.Print(true)
		window.print();
	});
	
	/* $("#printSetBtn").click(function(){
		document.getElementById("factory").printing.PageSetup()
	});
	
	$("#viewBtn").click(function(){
		document.getElementById("factory").printing.Preview()
	});
	
	printWindow(); */
	
		initHykhData(300);
		$("#queryBtn").click(function(){
			var sjJgbm=$("#mainForm_domain_sjJgbm").val();
			var rqq=$("#mainForm_domain_rqq").val();
			var rqz=$("#mainForm_domain_rqz").val();
			var fhrDjxh=$("#mainForm_domain_fhrDjxh").val();
			var khMc=$("#mainForm_domain_fhrMc").val();
			if(sjJgbm==''){
				showError("��ѡ��ҵ��λ��");
				return;
			}
			
			$("#mainForm").attr("action",jcontextPath+"/jcgl/khshqsbh!query");
			$("#mainForm").submit();
		})
	})

	/* function��printWindow() {��   
		document.getElementById("factory").printing.header��=��""; ����   
		document.getElementById("factory").printing.footer��=��""; ����   
		document.getElementById("factory").printing.portrait��=��false;//��Ϊtrue���Ǻ��� ����   
		document.getElementById("factory").printing.leftMargin��=��1.5; ����   
		document.getElementById("factory").printing.topMargin��=��1.0; ����   
		document.getElementById("factory").printing.rightMargin��=��1.5; ����   
		document.getElementById("factory").printing.bottomMargin��=��1.0; ����   
	} */
	
//��ʾ���ز�ѯ����
function slideToggle(sydiv){
	if (sydiv=="syquery")
		{$("#divQuery").slideToggle(100);} //��ʾ�����˷�֧����ϢЧ�����л�,��һ����,��һ�¿�

}

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="khshqsbh!init" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
	

	<div class="right_btnbg noprint">
	    <ul class="lcont">
		   <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="printBtn" class="licon04">�� ӡ</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		   
	  	</ul>
	</div>
	<div style="display: none;" id="maincont"></div>
<div class="right_cont">  
  <div id="divQuery" class="noprint">
	<fieldset>
		<legend>��ѯ����</legend>
		   <table width="100%" border="0"  cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="10%" align="right">ҵ��λ��</td>
		          <td width="20%" >  <sys:csGsList myClass="select" myId="mainForm_domain_sjJgbm" myName="domain.sjJgbm" mcContainDmBz="Y"/></td>			  
				  <td width="8%" align="right">�ͻ����ƣ�</td>
		          <td width="25%">
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 230px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		           <td width="8%" align="right">�µ����ڣ�</td>
		          <td width="25%" >  <s:textfield name="domain.rqq" readonly="true" cssClass="ymdate" ></s:textfield>
							 �� 
							<s:textfield name="domain.rqz" readonly="true" cssClass="ymdate"></s:textfield>
				  </td>
		        </tr>
		   </table>
	</fieldset>
  </div>
 
    <table id="tab1" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
    	<tr>
    		<td width="4%" id="content"></td>
    	
    		<td width="6%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="7%" id="content"></td>
    		
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>    	
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>  
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>      		
    	
    	</tr>
    	<tr >
    		<td class="tab_title" colspan="16" align="center" id="content"><font size="3">�ͻ���Ӫ�����</font></td>
    	</tr>
    	
    
    	<tr>
    		<td colspan="16" align="center" id="content"></td>
    	</tr>
     <tr><!--
    		<td width="50%" colspan="5" align="left" style="border-left: white;border-right: white;border-top: white">�ͻ���λ��<s:property value="domain.jgMc" /></td>
    		
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="10%"  align="right" style="border-left: white;border-right: white;border-top: white">��λ��Ԫ</td>-->	
    	</tr>
     
    	<tr>
    		<td width="4%" rowspan="2" align="center">���</td>
    		<td width="6%" rowspan="2" align="center">������λ</td>
    		<td width="6%" rowspan="2" align="center">�ͻ�����</td>
    		<td width="6%" rowspan="2" align="center">��Ϣ��</td>
    		<td width="6%" colspan="4" align="center">����</td>
    		<td width="6%" colspan="5" align="center">֧��</td>
    		<td width="6%" colspan="2" align="center">����</td>
    	</tr>
    	<tr>
    		<td align="center">�½�</td>
    		<td align="center">���½�</td>
    		<td align="center">����</td>
    		<td align="center">������ʧ</td>
    		<td align="center">���</td>
    		<td align="center">����</td>
    		<td align="center">����</td>
    		<td align="center">����</td>
    		<td align="center">������ʧ</td>
    		<td align="center">ë��</td>
    		<td align="center">ë����</td>
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.ssjgMc" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.xxf" /></td>
    		
    		<td align="center"><s:property value="#zb.yj" /></td>
    		<td align="center"><s:property value="#zb.fyj" /></td>
    		<td align="center"><s:property value="#zb.srdd" /></td>
    		<td align="center"><s:property value="#zb.wlsssr" /></td>
    		
    		<td align="center"><s:property value="#zb.th" /></td>
    		<td align="center"><s:property value="#zb.ys" /></td>
    		<td align="center"><s:property value="#zb.ps" /></td>
    		<td align="center"><s:property value="#zb.zcdd" /></td>
    		<td align="center"><s:property value="#zb.wlsszc" /></td>  
    		  		
    		<td align="center"><s:property value="#zb.lr" /></td>
    		<td align="center"><s:property value="#zb.lrR" /></td>
    	</tr>
    	</s:iterator>
  </table>
  <!-- ��ҳ��� id����ΪdataList -->
  
  <!-- ��ҳ���� -->
 
  <%@include file="/common/message.jsp" %>
</div>
	
	
	

</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
