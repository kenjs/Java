<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>���˵���ӡ</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<style type="text/css">
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
		printWindow();
	   
	});
	
	function��printWindow() {��   
		document.getElementById("factory").printing.header��=��""; ����   
		document.getElementById("factory").printing.footer��=��""; ����   
		document.getElementById("factory").printing.portrait��=��false;//��Ϊtrue���Ǻ��� ����   
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
 	
	<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
</object>		
	
    <div class="pop_contc" style="text-align: center;">
    	
       
			  <table id="tab1" width="640px" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed;margin: auto;">
			
			  	<tr  id="tr1">
		        	<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="70px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="70px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="50px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        </tr>
			 	<tr>
			  		<td class="tab_title" align="center" colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			<s:property value="domain.ssJgmc" />
			  		</td>
			  	</tr>
			   	<tr>
			  		<td class="tab_title" align="center" colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			���˵�
			  		</td>
			  	</tr>
		        <tr>
		        	<td colspan="4" align="left" style="border-left: white;border-right: white;border-top: white;border-bottom: white">�µ����ڣ�<s:date  name="domain.xdrq" format="yyyy-MM-dd"/></td>
		        	<td colspan="4" align="center"  style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td colspan="4" align="right"  style="border-left: white;border-right: white;border-top: white;border-bottom: white">������ţ�<s:property value="domain.ddbh"/></td>
		        </tr>
		        <tr>
		        	<td align="center">������</td>
		        	<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrLxr"/></td>
					<td align="center">������</td>
					<td align="left" colspan="5"><s:property value="domain.hwmxDomain.fhrMc"/></td>
					<td align="center">��ϵ�绰</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrLxdh"/></td>
				</tr>
				<tr>
					<td align="center">��ַ</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.fhrDz"/></td>
					<td align="center">����</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrXzqhMc"/></td>
				</tr>
				<tr>
		        	<td align="center">�ջ���</td>
		        	<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrLxr"/></td>
					<td align="center">�ջ���</td>
					<td align="left" colspan="5"><s:property value="domain.hwmxDomain.shrMc"/></td>
					<td align="center">��ϵ�绰</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrLxdh"/></td>
				</tr>
				<tr>
					<td align="center">��ַ</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.shrDz"/></td>
					<td align="center">����</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrXzqhMc"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">Ҫ�󷢻�����</td>
					<td align="center" ><s:property value="domain.hwmxDomain.yqFhrq"/></td>
					<td align="center" colspan="2">Ҫ�󵽻�����</td>
					<td align="center" ><s:property value="domain.hwmxDomain.yqFhrq"/></td>
					<td align="center" >�Ƿ����</td>
					<td align="center" ><s:if test="domain.hwmxDomain.thflDm==1">��</s:if><s:else>��</s:else></td>
					<td align="center" >�ͻ���ʽ</td>
					<td align="center" colspan="3">�ͻ�<input type="checkbox"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����<input type="checkbox" /></td>
				</tr>
				<tr>
					<th colspan="3">��������</th>
					<th colspan="1">��װ</th>
					<th colspan="2">��������</th>
					<th colspan="2">����</th>
					<th colspan="2">����</th>
					<th colspan="2">���</th>
				</tr>
				<tr>
		        	<td align="center" colspan="3"><s:property value="domain.hwmxDomain.hwmc"/></td>
					<td align="center" colspan="1"><s:property value="domain.hwmxDomain.hwbzHldwMc"/></td>
					<td align="center" colspan="2"><s:if test="domain.hwmxDomain.hwflDm==1">�ػ�</s:if><s:else>�ݻ�</s:else></td>
					<td align="center" colspan="2"><s:property value="domain.hwmxDomain.hwSl"/> <s:property value="domain.hwmxDomain.hwSlJldwMc"/></td>
					<td align="center" colspan="2"><s:property value="domain.hwmxDomain.hwZl"/> <s:property value="domain.hwmxDomain.hwZlJldwMc"/></td>
					<td align="center" colspan="2"><s:property value="domain.hwmxDomain.hwTj"/> <s:property value="domain.hwmxDomain.hwTjJldwMc"/></td>
				</tr>
				<tr>
		        	<td align="center" colspan="3"></td>
					<td align="center" colspan="1"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
				</tr>
				<tr>
		        	<td align="center" colspan="3"></td>
					<td align="center" colspan="1"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
					<td align="center" colspan="2"></td>
				</tr>
				<tr>					
					<td align="center" colspan="2">����ϼ�</td>
					<td align="center" colspan="2"><s:property value="domain.hwmxDomain.srHj"/></td>
					<td align="center">�ָ�</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srXf"/></td>
					<td align="center">����</td>
					<td align="center" >
						<s:if test="domain.hwmxDomain.shfsDm==1"><s:property value="domain.hwmxDomain.srThf" /></s:if>
		        		<s:else><s:property value="domain.hwmxDomain.srHdf" /></s:else>
					</td>
					<td align="center">�ظ�</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHf"/></td>
					<td align="center">�ؿ�</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHk"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">�ϼƣ���д��</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.dxSrHj"/></td>
					<td align="center" >�ϼ�</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHj"/></td>
				</tr>
				<tr>
					<td align="left" colspan="2" >������ǩ��<div style="height: 50px"></div></td>
					<td align="left" colspan="2" >������ǩ��<div style="height: 50px"></div></td>
					<td align="left" colspan="2" >�ջ���ǩ��<div style="height: 50px"></div></td>
					<td align="left" colspan="6" >��ע��<div style="height: 50px"></div></td>
				</tr>
		    </table>
		<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">��ӡ</button>
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
