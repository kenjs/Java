<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�������ͳ�Ʊ�</title>

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
			var jgbm=$("#mainForm_domain_sjJgbm").val();
			var rqq=$("#mainForm_domain_rqq").val();
			var rqz=$("#mainForm_domain_rqz").val();
			var kh=$("#mainForm_domain_fhrDjxh").val();
			if(jgbm==''){
				showError("��ѡ��ҵ��λ��");
				return;
			}
			if(rqq==''){
				showError("��ѡ��ͳ��������");
				return;
			}
			if(rqz==''){
				showError("��ѡ��ͳ������ֹ��");
				return;
			}
			if(kh==''){
				showError("��ѡ��ͻ����ƣ�");
				return;
			}
			
			$("#mainForm").attr("action",jcontextPath+"/jcgl/fcqktj!query");
			$("#mainForm").submit();
		})
	})

/* function��printWindow() {��   
		document.getElementById("factory").printing.header��=��""; ����   
		document.getElementById("factory").printing.footer��=��""; ����   
		document.getElementById("factory").printing.portrait��=��false;//��Ϊtrue���Ǻ��� ����   
		document.getElementById("factory").printing.leftMargin��=��1.5; ����   
		document.getElementById("factory").printing.topMargin��=��1.0; ����   
		document.getElementById("factory").printing.rightMargin��=��1.0; ����   
		document.getElementById("factory").printing.bottomMargin��=��1.0; ����   
	}	 */
	
//��ʾ���ز�ѯ����
function slideToggle(sydiv){
	if (sydiv=="jbxx")
		{$("#jbxxcont").slideToggle(100);} //��ʾ���ػ�����ϢЧ�����л�,��һ����,��һ�¿�
	if (sydiv=="pchwxx")
		{$("#pchwxxcont").slideToggle(100);} //��ʾ�����ɳ�������ϢЧ�����л�,��һ����,��һ�¿�
	if (sydiv=="clgzxx")
		{$("#clgzxxcont").slideToggle(100);} //��ʾ���س���������ϢЧ�����л�,��һ����,��һ�¿�
	if (sydiv=="yfzfxx")
		{$("#yfzfxxcont").slideToggle(100);} //��ʾ�����˷�֧����ϢЧ�����л�,��һ����,��һ�¿�

}

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="jctydgl!initMx" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
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
		          <td width="20%" >  <sys:gsList myName="domain.sjJgbm" myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:gsList></td>
		          <td width="10%" align="right">ͳ�����ڣ�</td>
		          <td width="25%" >  <s:textfield name="domain.rqz" readonly="true" cssClass="ymdate" ></s:textfield> 
				  </td>
				  
				 
		        </tr>
		   </table>
	</fieldset>
  </div>
    <table id="tab1" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
    	<tr>
    		<td width="4%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    	
    	</tr>
    	<tr >
    		<td colspan="15" align="center" id="content" class="tab_title"><font size="3">(��λ)�������ͳ�Ʊ�</font></td>
    	</tr>
    	
    
    	<tr>
    		<td colspan="15" align="center" id="content">ͳ������:<s:property value="domain.rqq" /> �� <s:property value="domain.rqz" /></td>
    	</tr>
    	<tr>
    		<td width="45%" colspan="4" align="left" style="border-left: white;border-right: white;border-top: white">��·��</td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="20%" colspan="3" align="right" style="border-left: white;border-right: white;border-top: white">��λ��Ԫ</td>
    	</tr>
    	
    	<tr>
    		<td width="4%" rowspan="2" align="center">���</td>
    		<td width="8%" rowspan="2" align="center">�·�</td>
    		<td width="6%" rowspan="2" align="center">��������</td>
    		<td width="6%" colspan="4" align="center">����</td>
    		<td width="6%" colspan="6" align="center">�ɱ�</td>
    		<td width="6%" rowspan="2" align="center">����ƽ��ë��</td>
    		<td width="6%" rowspan="2" align="center">����ƽ��ë����</td>
    	</tr>
    	<tr>
    		<td align="center">С��</td>
    		<td align="center">�ָ�</td>
    		<td align="center">����</td>
    		<td align="center">�ظ�</td>
    		<td align="center">С��</td>
    		<td align="center">���</td>
    		<td align="center">����</td>
    		<td align="center">����</td>
    		<td align="center">���ռ��</td>
    		<td align="center">����ռ��</td>
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.ddbh" /></td>
    		<td align="center"><s:property value="#zb.xdrq" /></td>
    		<td align="center"><s:property value="#zb.sl" /></td>
    		<td align="center"><s:property value="#zb.zl" /></td>
    		<td align="center"><s:property value="#zb.tj" /></td>
    		<td align="center"><s:property value="#zb.xj" /></td>
    		<td align="center"><s:property value="#zb.xf" /></td>
    		<td align="center"><s:property value="#zb.df" /></td>
    		<td align="center"><s:property value="#zb.hf" /></td>
    		<td align="center"><s:property value="#zb.sfd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
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
