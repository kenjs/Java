<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>�ɳ��嵥</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<style type="text/css">
.tabCss td{height:15px;font-size: 14px;}
.thCss{height:15px;font-size: 13px;}
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	    
		hiddenn();
		
		$("#printBtn").click(function() {
			document.getElementById("factory").printing.Print(true)
		});
		
		$("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup()
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		
		$("#daoChuBtn").click(function(){
			$("#mainForm").attr("action",jcontextPath+"/hygl/hypcxxgl!downloadQingDan");
			$("#mainForm").submit();
		})
		printWindow();
	   
	});
	
	function��printWindow() {��   
		document.getElementById("factory").printing.header��=��""; ����   
		document.getElementById("factory").printing.footer��=��""; ����   
		document.getElementById("factory").printing.portrait��=��true;//��Ϊtrue���Ǻ��� ����   
		document.getElementById("factory").printing.leftMargin��=��2.0; ����   
		document.getElementById("factory").printing.topMargin��=��1.0; ����   
		document.getElementById("factory").printing.rightMargin��=��1.0; ����   
		document.getElementById("factory").printing.bottomMargin��=��1.0; ����   
	}
	
	function hiddenn(){
		
		document.getElementById("tr1").style.visibility='hidden';
	}
	

	function yesSaveCallBack(){
		window.close();
	}
	
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="qyzzjg!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
 	<s:hidden name="domain.pcDjxh"></s:hidden>
 	<input type="hidden" name="domain.spbz" value="qingdan"></input>
	<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
</object>		
	
    <div class="pop_contc ">
    	
       <div class="divCss">
			  <table   id="tab1"  width="649px" align="center" border="0" cellspacing="0" cellpadding="0" class="poptab_css tabCss" style="table-layout: fixed">
			
			  	<tr  id="tr1">
		        	<td  width="30px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="60px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="45px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td width="135px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="40px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        </tr>
			  <tr>
			  		<td class="tab_title" align="center" colspan="13" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			<s:property value="domain.qingDan.gsMc" />&nbsp;&nbsp;�泵�����嵥
			  		</td>
			  </tr>
			 
		        <tr>
		        	<td width="5%" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td width="10%" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td colspan="7" align="center" width="65%" style="border-left: white;border-right: white;border-top: white;border-bottom: white">ʼ���أ�&nbsp;<s:property value="domain.qingDan.sfd"/>&nbsp;&nbsp;��&nbsp;Ŀ�ĵأ�&nbsp;<s:property value="domain.qingDan.mdd"/></td>
		        	<td colspan="3" align="center" width="26%" style="border-left: white;border-right: white;border-top: white;border-bottom: white">�ɳ����ţ�<s:property value="domain.qingDan.pcdh"/></td>
		        </tr>
		        <tr>
		        	<td colspan="9" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
		        	˾��������<s:property value="domain.qingDan.sjxm"/>    &nbsp;&nbsp;&nbsp;�������룺<s:property value="domain.qingDan.clhm"/>&nbsp;&nbsp;&nbsp;��ϵ�绰��<s:property value="domain.qingDan.lxdh"/>
		        	</td>
		        	<td colspan="3" align="center" style="border-left: white;border-right: white;border-top: white;border-bottom: white">�ɳ����ڣ�<s:property value="domain.qingDan.pcrq"/></td>
		        </tr>
		        <tr>
		        	 <th class="thCss">���</th>
		        	  <th class="thCss">�������</th>
		        	 <th class="thCss">��������</th>
		        	 <th class="thCss">Ŀ�ĵ�</th>
					<th class="thCss">����</th>
					<th class="thCss">����</th>
					<th class="thCss">���</th>
					<th class="thCss">������</th>
					<th class="thCss">�ջ���</th>
					<th class="thCss">�ջ���ַ</th>
					<th class="thCss">�ͻ���ʽ</th>
					<th class="thCss">����</th>
					<th class="thCss">���ͷ�</th>
					<th class="thCss">���ջ���</th>
		        </tr>
		        <s:iterator value="domain.qdList" id="zb" status="i">
		        <Tr>
		        	<Td align="center"><s:property value="#i.index+1"/></Td>
		        	<Td align="center"><s:property value="#zb.ddbh"/></Td>
		        	<Td align="center"><s:property value="#zb.hwMc"/></Td>
		        	<Td align="center"><s:property value="#zb.mddDz"/></Td>
					<Td align="center"><s:property value="#zb.sl"/></Td>
					<td align="center"><s:property value="#zb.zl"/></td>
					<td align="center"><s:property value="#zb.tj"/></td>
					<Td align="center"><s:property value="#zb.fhr"/></Td>
					<td align="center"><s:property value="#zb.shrMc"/></td>
					<Td align="center"><s:property value="#zb.shrDz"/></Td>
					<Td align="center"><s:property value="#zb.shfsMc"/></Td>
					<Td align="center"><s:property value="#zb.ds"/></Td>
					<Td align="center"><s:property value="#zb.srPsf"/></Td>
					<Td align="center"><s:property value="#zb.dsHk"/></Td>
		        </Tr>
		       
		        </s:iterator>
		         <tr>
	  			 	<td align="center">��ע</td>
	  			 	<td colspan="14">
	  			 		<s:property  value="domain.qingDan.pcBz"/>
	  			 	</td>
	  			 </tr>
		    </table>
		    </div>
		<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">�� ӡ</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">��ӡ����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">��ӡԤ��</button>
			     &nbsp;
			      <button type="button" class="pop_btnbg" id="daoChuBtn">����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		</div>
		
	</div>
		 
		
	
	<%@include file="/common/message.jsp" %>

 
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
