<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>托运单打印</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;font-size: 14px;}

.tbxx{position:relative; left:0px;top:0px}
.fhrxx{position: relative; left: 0px;}
.hwxx{position: relative; left: 0px;}
.hwxxTab td{height:28px;font-size: 14px;}
.tbxxTab td{height:25px;font-size: 14px;}
.fhrxxTab td {height:25px;font-size: 14px;}
span{padding: 0 16px 0 16px}
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
	    showDxsr();
	});
	function  showDxsr(){
		var srhj = $("#srhj").text();
		if(srhj==null ||srhj == ""){
			return;
		}
		var str = srhj.split(".");
		var j = 1;
		for(var i=str[0].length-1;i>=0;i--){
			var dx = zhDx(str[0][i]);
			if(dx==null||dx==""){
			    dx=" ";
			}
			$("#span"+j).text(dx);
			j++;
		}
		if(str.length>1){
			var dx = zhDx(str[0][0]);
			if(dx==null||dx==""){
			    dx="零";
			}
			$("#span6").text(dx);
		}else{
			$("#span6").text("零");
		}
	}
	function savePrintSuc(data) {
		showAlert("保存成功！");
	}
	function zhDx(num){
		 var dx;
		 switch(num)
		 {
		  case "0":
			   dx="零";
			   break;
		  case "1":
			   dx="壹";
			   break;
		  case "2":
			   dx="贰";
			   break;
		  case "3":
			   dx="叁";
			   break;
		  case "4":
			   dx="肆";
			   break;
		  case "5":
			   dx="伍";
			   break;
		  case "6":
			   dx="陆";
			   break;
		  case "7":
			   dx="柒";
			   break;
		  case "8":
			   dx="捌";
			   break;
		  case "9":
			   dx="玖";
			   break;
		}
		return dx;
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
		        	<td width="30"></td>
		        	<td width="205"><s:property value="domain.hwmxDomain.shrXzqhMc" /></td>
		        	<td width="225"></td>
		        	<td width="160">
			        	&nbsp;<s:date  name="domain.hwmxDomain.xdrq" format="yyyy"/>
			        	&nbsp;&nbsp;&nbsp;<s:date  name="domain.hwmxDomain.xdrq" format="MM"/>
			        	&nbsp;&nbsp;&nbsp;<s:date  name="domain.hwmxDomain.xdrq" format="dd"/>
		        	</td>
		        </tr>
		      </table>
		    </div>
		    <div class="fhrxx">
		      <table class="fhrxxTab">
		        <tr>
		        	<td width="40"></td>
		        	<td width="160"><s:property value="domain.hwmxDomain.fhrMc"/></td>
		        	<td width="290"><s:property value="domain.hwmxDomain.fhrDz"/></td>
		        	<td width="130"><s:property value="domain.hwmxDomain.fhrLxdh" escape="false"/></td>
				</tr>
				<tr>
		        	<td> </td>
		        	<td ><s:property value="domain.hwmxDomain.shrLxr"/></td>
		        	<td ><s:property value="domain.hwmxDomain.shrDz"/></td>
		        	<td ><s:property value="domain.hwmxDomain.shrLxdh" escape="false"/></td>
				</tr>
			  </table>
			</div>
			<div class="hwxx">
			  <table class="hwxxTab">
				<tr>
					<td width="90"></td>
					<td width="65"></td>
					<td width="45"></td>
					
					<td width="98"></td>
					<td width="97"></td>
					<td width="97"></td>
					
					<td width="55"></td>
					<td width="75"></td>
				</tr>	
				<tr>
					<td><s:property value="domain.hwmxDomain.hwmc"/></td>
					<td><s:property value="domain.hwmxDomain.hwbzHldwMc"/></td>
					<td><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.hwSl" ></s:param></s:text></td>
					
					<td>
						<s:if test='domain.hwmxDomain.hwZl > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwZl"/></s:text></s:if>
						/<s:if test='domain.hwmxDomain.hwTj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwTj"/></s:text></s:if>
					</td>
					<td><s:if test='domain.hwmxDomain.srHj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHj"/> </s:text></s:if></td>
					<td><s:if test='domain.hwmxDomain.srBjf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srBjf"/></s:text></s:if></td>
					
					<td></td>
					<td><s:if test='domain.hwmxDomain.srXf > 0'>现结</s:if><s:if test='domain.hwmxDomain.srYj > 0'>月结</s:if><s:if test='domain.hwmxDomain.srHdf > 0'>到付</s:if></td>
				</tr>				
				<tr>
		        	<td colspan="8"></td>
				</tr>
				<tr>
		        	<td colspan="8"></td>
				</tr>
				<tr>
		        	<td colspan="7">
		        		<span id="span5" style="margin-left: 90px;">&nbsp;</span>
		        		<span id="span4">&nbsp;</span>
		        		<span id="span3">&nbsp;</span>
		        		<span id="span2">&nbsp;</span>
		        		<span id="span1">&nbsp;</span>
		        		<span id="span6">&nbsp;</span>
		        	</td>
		        	<td id="srhj"><s:if test='domain.hwmxDomain.srHj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHj"/> </s:text></s:if></td>
				</tr>
			</table>
			<table class="fhrxxTab">
		        <tr>
		        	<td width="30"></td>
		        	<td width="600"><s:property value="domain.hwmxDomain.fhrMc"/><s:property value="domain.hwmxDomain.bz" escape="false"/></td>
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
