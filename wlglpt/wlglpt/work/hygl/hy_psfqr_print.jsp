<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>���ͷ�ȷ����Ϣ</title>
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
	    
		$("#printBtn").click(function() {
			document.getElementById("factory").printing.Print(true)
		});
		
		$("#downloadBtn").click(function() {
			$("#mainForm").attr("action",jcontextPath+"/hygl/jspsfqr!download");
			$("#mainForm").submit();
		});
		
		$("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup()
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		printWindow();
	});

	function��printWindow() {
		document.getElementById("factory").printing.header��=��""; ����   
		document.getElementById("factory").printing.footer��=��""; ����   
		document.getElementById("factory").printing.portrait��=��false;//��Ϊtrue���Ǻ��� ����   
		document.getElementById("factory").printing.leftMargin��=��1.5; ����   
		document.getElementById("factory").printing.topMargin��=��1.0; ����   
		document.getElementById("factory").printing.rightMargin��=��1.0; ����   
		document.getElementById("factory").printing.bottomMargin��=��1.0; ����   
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
</script>
<base target="_self" />
</head>
<body>
<%try{ %>
<s:form action="jspsfqr!initMx" namespace="/wlgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcdh"/>
    <s:hidden name="domain.pcrqq"/>
    <s:hidden name="domain.pcrqz"/>
    <object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
	</object>
    <div class="pop_contc ">
       <div class="divCss">
			  <table id="tab1" width="100%" align="center" border="0" cellspacing="0" cellpadding="0" class="poptab_css tabCss">
			    <tr>
			  		<td class="tab_title" align="center" colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			<s:property value="domain.qingDan.gsMc" />&nbsp;&nbsp;���ͷ��˵�
			  		</td>
			    </tr>
				<tr>
					<td align="center" colspan="13"
						style="border-left: white; border-right: white; border-top: white; border-bottom: white">
						�ɳ����ڣ�<s:property value="domain.pcrqq" />
						&nbsp;&nbsp;&nbsp; ~ &nbsp;&nbsp;&nbsp;
						<s:property value="domain.pcrqz" />&nbsp;&nbsp;&nbsp;
					</td>  
				</tr>
				<tr>
		        	<th width="3%" class="thCss">���</th>
		        	<th width="6%" class="thCss">�ɳ�����</th>
		        	<th width="4%" class="thCss">�ɳ�����</th>
		        	<th width="6%" class="thCss">�������</th>
		        	<th width="6%"class="thCss">�ͻ�����</th>
					<th width="7%" class="thCss">��������</th>
					<th width="8%" class="thCss">�ջ���</th>
					<th width="8%" class="thCss">���ε�λ</th>
					<th width="3%" class="thCss">����</th>
					<th width="3%" class="thCss">����</th>
					<th width="3%" class="thCss">���</th>
					<th width="14%" class="thCss">������Ϣ</th>
					<th width="4%" class="thCss">�ͻ���ʽ</th>
					<th width="4%" class="thCss">���ͷ�</th>
					<th width="5%" class="thCss">ȷ��״̬</th>
		        </tr>
		        <s:iterator value="domain.dataList" id="db" status="i">
		        <tr>
		        	<Td align="center"><s:property value="#i.index+1"/></Td>
		        	<Td align="center"><s:property value="#db.pcdh"/></Td>
		        	<Td align="center"><s:property value="#db.pcrq"/></Td>
		        	<Td align="center"><s:property value="#db.dingdan"/></Td>
					<Td align="center"><s:property value="#db.fhrMc"/></Td>
					<Td align="center"><s:property value="#db.hwMc"/></Td>
					<Td align="center"><s:property value="#db.shrMc"/></Td>
					<Td align="center"><s:property value="#db.zrbmMc"/></Td>
					<Td align="center"><s:property value="#db.sl"/></Td>
					<Td align="center"><s:property value="#db.zl"/></Td>
					<Td align="center"><s:property value="#db.tj"/></Td>
					<Td align="center"><s:property value="#db.clxx"/></Td>
					<Td align="center"><s:property value="#db.zs"/></Td>
					<Td align="center"><s:property value="#db.srPsf"/></Td>
					<Td align="center">
						<s:if test='#db.sfQr=="Y"'>
							`<s:if test='#db.zs!="����"'>
								��ȷ��
							</s:if>
						</s:if>
						<s:else>
							`<s:if test='#db.zs!="����"'>
								δȷ��
							</s:if>
						</s:else>
					</Td>
		        </tr>
		        </s:iterator>
		    </table>
		    </div>
		    <div class="pop_btn noprint">
				<button type="button" class="pop_btnbg" id="printBtn">�� ӡ</button>
				  &nbsp;
				 <button type="button" class="pop_btnbg" id="downloadBtn">����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">��ӡ����</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">��ӡԤ��</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
			</div>
	  	</div>
	  <%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
