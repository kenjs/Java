<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<head>
<title>���˵�����</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;font-size: 14px;}

.poptab_css td{height:23px;}
.printFont{font-size: 14px;}
.viewFont{float: left;}

</style>

<script type="text/javascript">
<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String zgsjc="";
		if (userDomain != null && userDomain.getZgsjc() != null)
			zgsjc = userDomain.getZgsjc();
%>
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	    
		$("#printBtn").click(function() {
			document.getElementById("factory").printing.Print(true)
		});
		
		$("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup();
		});
		
		$("#saveSetBtn").click(function() {
			var leftMargin = document.getElementById("factory").printing.leftMargin;
			var topMargin = document.getElementById("factory").printing.topMargin;
			var wsDm = "1002";
			var jsonObj = {"domain.wsDm":wsDm, "domain.leftMargin":leftMargin,"domain.topMargin":topMargin};
			var url = jcontextPath+"/xtgl/qywsdysz!save";  
			ajaxCommon(url,jsonObj,"savePrintSuc");
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		printInit();
		var gsmc = <%=zgsjc %>;
	   $("#zgsmc").html(gsmc);
	});
	
	function savePrintSuc(data) {
		showAlert("����ɹ���");
	}
	
	function��printInit() {
		var wsDm = "1002";
		var jsonObj = {"domain.wsDm":wsDm};
		var url = jcontextPath+"/xtgl/qywsdysz!selectBj";  
		ajaxCommon(url,jsonObj,"queryPrintSuc");
	}
	
	function queryPrintSuc(data) {
		var leftMargin = data.domain.leftMargin;
		var topMargin = data.domain.topMargin;
		
		if (leftMargin == null || leftMargin == "null") {
			leftMargin = 0.0;
		}
		if (topMargin == null || topMargin == "null") {
			topMargin = 0.0;
		}
		
		document.getElementById("factory").printing.header��=��"";����   
		document.getElementById("factory").printing.footer��=��"";����   
		document.getElementById("factory").printing.portrait��=��true;//��Ϊtrue���Ǻ��� ����   
		document.getElementById("factory").printing.leftMargin��=��leftMargin;����   
		document.getElementById("factory").printing.topMargin��=��topMargin;��   
		document.getElementById("factory").printing.rightMargin��=��0;������   
		document.getElementById("factory").printing.bottomMargin��=��0;��
		
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		if (tempFlag == "Y") {
			document.getElementById("factory").printing.Print(false)
			window.close();
		}
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="hytydgl!printAll" namespace="/hygl" method="post" id="mainForm" name="mainForm">
 	<s:hidden name="domain.hwmxDomain.tempFlag"></s:hidden>
 	
	<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
	</object>		
	
    	<div class="right_cont">
    		<table width="688" border="0" align="center" cellpadding="0" cellspacing="0">
    		    <tr>
			  		<td class="tab_title" align="center" colspan="2" id="zgsmc">���������������޹�˾���˵�</td>
			    </tr>
			    <tr>
			  		<td align="left">
			  			�ල�绰��18957128089
			  		</td>
			  		<td align="right">
			  			���ţ�<s:property value="domain.hwmxDomain.ddbh" />
			  		</td>
			    </tr>
    		</table>
			  <table width="688" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css">
		        <tr>
		        	<td width="177"><div class="viewFont">�������ڣ�</div><p class="printFont"><s:date name="domain.hwmxDomain.xdrq" format="yyyy-MM-dd"/></p></td>
		        	<td width="166"><div class="viewFont">ʼ���أ�</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrXzqhMc" /></p> </td>
		        	<td width="177"><div class="viewFont">Ŀ�ĵأ�</div><p class="printFont"><s:property value="domain.hwmxDomain.shrXzqhMc" /></p></td>
		        </tr>
		      </table>
		      <table width="688" style="margin-top: 5px;" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css" >
		        <tr>
					<td colspan="4"><div class="viewFont">������λ��</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrMc"/></p></td>
					<td colspan="3"><div class="viewFont">�ջ���λ��</div><p class="printFont"><s:property value="domain.hwmxDomain.shrMc"/></p></td>
				</tr>
		        <tr>
		        	<td colspan="2"><div class="viewFont">�����ˣ�</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxr"/></p></td>
					<td colspan="2"><div class="viewFont">��ϵ�绰��</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxdh" escape="false"/></p></td>
		        	<td colspan="2"><div class="viewFont">�ջ��ˣ�</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxr"/></p></td>
					<td><div class="viewFont">��ϵ�绰��</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxdh" escape="false"/></p></td>
				</tr>
				<tr>
					<td colspan="4"><div class="viewFont">������ַ��</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrDz"/></p></td>
					<td colspan="3"><div class="viewFont">�ջ���ַ��</div><p class="printFont"><s:property value="domain.hwmxDomain.shrDz"/></p></td>
			  </tr>
			  <tr>
					<td width="101" align="center">Ʒ ��</td>
					<td width="61" align="center">�� װ</td>
					<td width="81" align="center">�� ��</td>
					<td width="85" align="center">�� ��(�O)</td>
					<td width="60" align="center">�� ��(kg)</td>
					<td width="124" align="center" >������ʽ</td>
					<td width="176" rowspan="5" style="word-break:break-all;word-wrap:break-word">��ע��<s:property value="domain.hwmxDomain.bz"/></td>
				</tr>
				<tr>
		        	<td align="center"><p class="printFont"><s:property value="domain.hwmxDomain.hwmc"/></p></td>
					<td align="center"><p class="printFont"><s:property value="domain.hwmxDomain.hwbzHldwMc"/></p></td>
					<td align="center"> <p class="printFont"><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.hwSl" ></s:param> </s:text>  </p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwTj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwTj"/></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwZl > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwZl"/></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.shfsDm == "1"'>����</s:if> 
										<s:if test='domain.hwmxDomain.shfsDm == "2"'>�ͻ�</s:if> </p></td>
					
				</tr>
				<tr>
		        	<td align="center">�����</td>
					<td align="center">���ͷ�</td>
					<td align="center">���۷�</td>
					<td align="center">�Ƿ�ص�</td>
					<td align="center">���ջ���</td>
					<td align="center">���ʽ</td>		
				</tr>	
				<tr>
		        	<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srYsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srYsf"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srPsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srPsf"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srBjf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srBjf"/></s:text></s:if></p></td>
					<td align="center">
						<s:if test='domain.hwmxDomain.hdbh!=null'>��</s:if>
						<s:else>��</s:else>
					</td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.fyDshk > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.fyDshk"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srXf > 0'>�ָ�</s:if> 
									<s:if test='domain.hwmxDomain.srYj > 0'>�½�</s:if>  
									<s:if test='domain.hwmxDomain.srHdf > 0'>����</s:if> </p></td>
					
				</tr>
				<tr>
					<td colspan="3"><div class="viewFont">�����ܼƣ�</div><p class="printFont"><s:if test='domain.hwmxDomain.srHjMz > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHjMz"/> </s:text></s:if>Ԫ</p> </td>
					<td colspan="3"><div class="viewFont"></div><p class="printFont"><s:property value="domain.hwmxDomain.srHjMzDx"/></p></td>
				</tr>
				<tr>
					<td align="center">ע�����</td>
					<td colspan="8" align="left" style="font-size: 10px;">
							1�����˻��������ʵ����������۸������˱�������������뱣�����䣬�粻���۳��ֻ��𡢻������������Ը���������⳥�������˷ѵ�3����
							<br/>2����׼�ٱ���������׼����Σ����Ʒ�͹��ҽ�����Ʒ����������д�������Ʒ����������˳е������е���������
							<br/>3������ﵽ��Ŀ�ĵ����װ��öԻ����������������𻵾�������
							<br/>4���������ɿ��ܵ���Ȼ�ֺ�������ս�����˱�ԭ������˲������⳥���Ρ�
							<br/>5�����˵��ɷ����˺������ǩ������Ч��������Ч��һ���¡�
					</td>
				</tr>
				<tr>
					<td colspan="5">������ǩ�֣�</td>
					<td colspan="3">�ջ���ǩ�֣�</td>
				</tr>
			</table>
			<table width="688" border="0" align="center" cellpadding="0" cellspacing="0">
			    <tr>
			  		<td align="left" width="330">
			  			��ַ���������³�������·5�ţ�3-2�ţ�
			  		</td>
			  		<td align="left" width="224">
			  			�绰��0571-88236929
			  		</td>
			  		<td align="left" width="134">
			  			���棺0571-88150899
			  		</td>
			    </tr>
			    <tr>
			  		<td align="left">
			  			��ַ�������й������B��1��37��
			  		</td>
			  		<td align="left">
			  			�绰��0556-5363199
			  		</td>
			  		<td align="left">
			  			�ֻ���18955641299
			  		</td>
			    </tr>
    		</table>
		  </div>
		<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">��ӡ</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">��ӡ����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="saveSetBtn">��������</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">��ӡԤ��</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		</div>
		
	<%@include file="/common/message.jsp" %>

 
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
