<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>托运单打印</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;font-size: 16px;}

.tbxx{position:relative; left:0px;top:0px}
.fhrxx{position: relative; left: 0px;}
.hwxx{position: relative; left: 0px;}
.hwxxTab td{height:25px;font-size: 14px;}
.tbxxTab td{height:20px;font-size: 14px;}
.fhrxxTab td {height:28px;font-size: 14px;}

</style>
<script type="text/javascript">
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
			var wsDm = "1001";
			var jsonObj = {"domain.wsDm":wsDm, "domain.leftMargin":leftMargin,"domain.topMargin":topMargin};
			var url = jcontextPath+"/xtgl/qywsdysz!save";  
			ajaxCommon(url,jsonObj,"savePrintSuc");
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		printInit();
	   
	});
	
	function savePrintSuc(data) {
		showAlert("保存成功！");
	}
	
	function　printInit() {
		var wsDm = "1001";
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
		
		document.getElementById("factory").printing.header　=　"";　　   
		document.getElementById("factory").printing.footer　=　"";　　   
		document.getElementById("factory").printing.portrait　=　true;//设为true就是横向 　
		//document.getElementById("factory").printing.printer = "SmartPrinter";　   
		document.getElementById("factory").printing.leftMargin　=　leftMargin;　　   
		document.getElementById("factory").printing.topMargin　=　topMargin;　   
		document.getElementById("factory").printing.rightMargin　=　0;　　　   
		document.getElementById("factory").printing.bottomMargin　=　0;　
		
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
<s:form action="hytydgl!printView" namespace="/hygl" method="post" id="mainForm" name="mainForm">
 	<s:hidden name="domain.hwmxDomain.tempFlag"></s:hidden>
 	
	<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
	</object>		
	
    	<div style="position:relative;height:90mm; margin-top:0mm;margin-left:1mm">
       		<div class="tbxx">
			  <table class="tbxxTab">
		         <tr>
		        	<td> </td>
		        </tr>
		      </table>
		    </div>
		    <div class="fhrxx">
		      <table class="fhrxxTab"width="730">
		        <tr>
		        	<td width="40"></td>
		        	<td width="210"><s:date  name="domain.hwmxDomain.xdrq" format="yyyy-MM-dd"/></td>
		        	<td width="175"><s:property value="domain.hwmxDomain.fhrXzqhMc" /></td>
		        	<td width="210"><s:property value="domain.hwmxDomain.shrXzqhMc" /></td>
		        	<td width="95"></td>
		        </tr>
		        <tr>
		        	<td ></td>
		        	<td ><s:property value="domain.hwmxDomain.fhrLxr"/></td>
		        	<td ></td>
		        	<td ><s:property value="domain.hwmxDomain.shrLxr"/></td>
		        	<td ></td>
		        </tr>
		         <tr>
		        	<td ></td>
		        	<td ><s:property value="domain.hwmxDomain.fhrMc"/></td>
		        	<td ><s:property value="domain.hwmxDomain.fhrLxdh" escape="false"/></td>
		        	<td ><s:property value="domain.hwmxDomain.shrMc"/></td>
		        	<td ><s:property value="domain.hwmxDomain.shrLxdh" escape="false"/></td>
		        </tr>
				<tr>
					<td ></td>
					<td ><s:property value="domain.hwmxDomain.fhrDz"/></td>
					<td ></td>
					<td ><s:property value="domain.hwmxDomain.shrDz"/></td>
					<td ></td>
				</tr>
			  </table>
			</div>
			<div class="hwxx">
			  <table class="hwxxTab" width="730" style="word-break:break-all;word-wrap:break-word">
				<tr>
					<td width="50"></td>
					<td width="40"></td>
					<td width="50"></td>
					<td width="50"></td>
					<td width="70"></td>
					<td width="90"></td>
					
					<td width="80"></td>
					<td width="50"></td>
					<td width="50"></td>
					<td width="50"></td>
					<td width="50"></td>
					<td width="100"></td>
				</tr>
				<tr>
					<td colspan="12">&nbsp;</td>
				</tr>
				<tr>
		        	<td ><s:property value="domain.hwmxDomain.hwmc"/></td>
					<td><s:property value="domain.hwmxDomain.hwbzHldwMc"/></td>
					<td> <s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.hwSl" ></s:param> </s:text>  </td>
					<td align="center"><s:if test='domain.hwmxDomain.hwTj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwTj"/></s:text></s:if> </td>
					<td align="center"><s:if test='domain.hwmxDomain.hwZl > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwZl"/></s:text></s:if> </td>
					<td > </td>
					
					<td align="center"><s:if test='domain.hwmxDomain.fySmjz > 0'><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.fySmjz"></s:param></s:text></s:if></td>
					<td colspan="2"><s:if test='domain.hwmxDomain.shfsDm == "1"'>√自提</s:if><s:if test='domain.hwmxDomain.shfsDm == "2"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√送货</s:if> </td>
					<td colspan="3">
						<s:if test='domain.hwmxDomain.srXf > 0'>√现结</s:if>
						<s:if test='domain.hwmxDomain.srYj > 0'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√月结</s:if> 
						<s:if test='domain.hwmxDomain.srHdf > 0'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;√对付</s:if>
					</td>
				</tr>
				<tr>
		        	<td ></td>
					<td></td>
					<td > </td>
					<td > </td>
					<td > </td>
					<td > </td>
					
					<td > </td>
					<td > </td>
					<td ></td>
					<td colspan="3" rowspan="3" style="margin-top: 0px;">&nbsp;&nbsp;&nbsp;<s:property value="domain.hwmxDomain.bz" escape="false"/></td>
				</tr>
				<tr>
		        	<td ></td>
					<td></td>
					<td ></td>
					<td ></td>
					<td></td>
					<td ><s:if test='domain.hwmxDomain.srHj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHj"/> </s:text></s:if></td>
					
					<td ></td>
					<td colspan="2"><s:if test='domain.hwmxDomain.fyDshk > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.fyDshk"/> </s:text></s:if></td>
					<td colspan="3"> </td>
				</tr>
				<tr>
		        	<td ><s:if test='domain.hwmxDomain.srYsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srYsf"/></s:text></s:if></td>
					<td></td>
					<td ><s:if test='domain.hwmxDomain.srBjf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srBjf"/></s:text></s:if></td>
					<td ></td>
					<td colspan="2" ><s:property value="domain.hwmxDomain.srhjDx"/></td>
					
					<td colspan="3"><s:property value="domain.hwmxDomain.fyDshkDx"/></td>
					<td colspan="3 "></td>
				</tr>
			</table>
		  </div>
		</div>
		<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">打印</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">打印设置</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="saveSetBtn">保存设置</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">打印预览</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div>
		
	<%@include file="/common/message.jsp" %>

 
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
