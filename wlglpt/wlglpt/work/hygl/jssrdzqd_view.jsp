<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��������嵥�鿴</title>
<style type="text/css">
.tabCss td{height:15px;font-size: 14px;}
.thCss{height:15px;font-size: 13px;}
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		hiddenn();
		
		$("#execBtn").click(function(){
			$("#mainForm").attr("action",jcontextPath+"/hygl/jssrdzqd!download");
			$("#mainForm").submit();
		});
		
		$("#printBtn").click(function() {
			document.getElementById("factory").printing.Print(true)
		});
		
		$("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup()
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
				
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
</script>
<base target="_self" />
</head>
<body>
<%try{ %>
<s:form action="jssrdzqd!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.qdDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.dzqdHzfsDm"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      		codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
		</object>	
		<div class="pop_contc ">
		<div class="divCss">
			<table   id="tab1"  width="740px" align="center" border="0" cellspacing="0" cellpadding="0" class="poptab_css tabCss" style="table-layout: fixed">
			
			  	<tr  id="tr1">
		        	<td  width="30px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="60px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="45px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="90px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td width="135px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="40px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        </tr>
			  <tr>
			  		<td class="tab_title" align="center" colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			&nbsp;&nbsp;��������嵥
			  		</td>
			  </tr>
			  <tr>
		        	<td colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
		        	�嵥���ƣ�<s:property value="domain.qdmc"/>
		        	</td>		        	
		       </tr>
		      <tr>
		      	<th class="thCss">���</th>		      	
		      	<th class="thCss">��Դ</th>
		      	<th class="thCss">���˽��</th>
		      	<th class="thCss">δ��</th>
		        <th class="thCss">������</th>
		        <th class="thCss">���</th>
		        <th class="thCss">�������</th>
		        <th class="thCss">�µ�����</th>
		        <th class="thCss">��������</th>
		        <th class="thCss">��װ</th>
		        <th class="thCss">�ص����</th>
		        <th class="thCss">ʼ����</th>
		        <th class="thCss">Ŀ�ĵ�</th>
		        <th class="thCss">����</th>
		        <th class="thCss">����</th>
		        <th class="thCss">���</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="domain" status="sta">
		      	<tr>
	      			<td align="center" class="bh"><s:property value="#sta.index+1" /></td>		      		
		      		<td align="center">
		      			<s:if test='1 == #domain.ywLydm'>�������</s:if>
		      			<s:elseif test="2 == #domain.ywLydm">���õǼ�</s:elseif>
		      			<s:elseif test="3 == #domain.ywLydm">������ʧ</s:elseif>
		      		</td>
		      		<td align="center"><s:property value="#domain.dzje" /></td>
		      		<td align="center"><s:property value="#domain.jsWj" /></td>
		      		<td align="center"><s:property value="#domain.dzcyje" /></td>
		      		<td align="center">
		      		<s:if test='"Y"==#domain.dzcybz'>
		      			<font color="red">�в���</font>
		      		</s:if>
		      		<s:else>&nbsp;</s:else>
		      		</td>
		      		<td align="center"><s:property value="#domain.ddbh" /></td>
		      		<td align="center"><s:property value="#domain.xdrq" /></td>
		      		<td align="center"><s:property value="#domain.hwmc" /></td>
		      		<td align="center"><s:property value="#domain.bz" /></td>
		      		<td align="center"><s:property value="#domain.hdbh" /></td>
		      		<td align="center"><s:property value="#domain.sfd" /></td>
		      		<td align="center"><s:property value="#domain.mdd" /></td>
		      		<td align="center"><s:property value="#domain.sl" /></td>
		      		<td align="center"><s:property value="#domain.zl" /></td>
		      		<td align="center"><s:property value="#domain.tj" /></td>
		      	</tr>
		      </s:iterator>
		    </table>
		    </div>
			<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">�� ӡ</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">��ӡ����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">��ӡԤ��</button>
			     &nbsp;
			      <button type="button" class="pop_btnbg" id="execBtn">����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		</div>	
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
