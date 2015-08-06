<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>托运单补打</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;font-size: 14px;}

.poptab_css td{height:23px;}
.printFont{font-size: 14px;}
.viewFont{float: left;}

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
			var wsDm = "1002";
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
		
		document.getElementById("factory").printing.header　=　"";　　   
		document.getElementById("factory").printing.footer　=　"";　　   
		document.getElementById("factory").printing.portrait　=　true;//设为true就是横向 　　   
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
<s:form action="hytydgl!printAll" namespace="/hygl" method="post" id="mainForm" name="mainForm">
 	<s:hidden name="domain.hwmxDomain.tempFlag"></s:hidden>
 	
	<object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
	</object>		
	
    	<div class="right_cont">
    		<table width="688" border="0" align="center" cellpadding="0" cellspacing="0">
    		    <tr>
			  		<td class="tab_title" align="center">
			  			天送物流集团运单
			  		</td>
			    </tr>
			    <tr>
			  		<td align="right">
			  			单号：<s:property value="domain.hwmxDomain.ddbh" />
			  		</td>
			    </tr>
    		</table>
			  <table width="688" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css">
		        <tr>
		        	<td width="177"><div class="viewFont">承运日期：</div><p class="printFont"><s:date name="domain.hwmxDomain.xdrq" format="yyyy-MM-dd"/></p></td>
		        	<td width="166"><div class="viewFont">始发地：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrXzqhMc" /></p> </td>
		        	<td width="177"><div class="viewFont">目的地：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrXzqhMc" /></p></td>
		        	<td width="168"><div class="viewFont">项目代码：</div></td>
		        </tr>
		      </table>
		      <table width="688" style="margin-top: 5px;" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css" >
		        <tr>
		        	<td width="226"><div class="viewFont">发货人姓名：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxr"/></p></td>
					<td width="118"><div class="viewFont">座机：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxdh" escape="false"/></p></td>
		        	<td width="226"><div class="viewFont">收货人姓名：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxr"/></p></td>
					<td width="118"><div class="viewFont">座机：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxdh" escape="false"/></p></td>
				</tr>
				<tr>
					<td ><div class="viewFont">单位名称：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrMc"/></p></td>
					<td ><div class="viewFont">手机：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrYddh" escape="false"/></p></td>
					<td ><div class="viewFont">单位名称：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrMc"/></p></td>
					<td ><div class="viewFont">手机：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrYddh" escape="false"/></p></td>
				</tr>
				<tr>
					<td ><div class="viewFont">详细地址：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrDz"/></p></td>
					<td ><div class="viewFont">传真：</div></td>
					<td ><div class="viewFont">详细地址：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrDz"/></p></td>
					<td ><div class="viewFont">传真：</div></td>
				</tr>
				<tr>
					<td ></td>
					<td ><div class="viewFont">邮编：</div></td>
					<td ></td>
					<td ><div class="viewFont">邮编：</div></td>
				</tr>
			  </table>
			  <table width="688" style="margin-top: 5px;" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css">
				<tr>
					<td width="49" align="center">品 名</td>
					<td width="49" align="center">包 装</td>
					<td width="49" align="center">件 数</td>
					<td width="51" align="center">体积(㎡)</td>
					<td width="78" align="center">实际重量(kg)</td>
					<td width="78" align="center">计费重量(kg)</td>
					<td width="81" align="center">声明价值</td>
					<td width="88" align="center">交付方式</td>
					<td width="165" align="center">付费方式</td>
				</tr>
				<tr>
		        	<td ><p class="printFont"><s:property value="domain.hwmxDomain.hwmc"/></p></td>
					<td><p class="printFont"><s:property value="domain.hwmxDomain.hwbzHldwMc"/></p></td>
					<td align="center"> <p class="printFont"><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.hwSl" ></s:param> </s:text>  </p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwTj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwTj"/></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwZl > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwZl"/></s:text></s:if></p> </td>
					<td > </td>
					<td > <p class="printFont"><s:if test='domain.hwmxDomain.fySmjz > 0'><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.fySmjz"></s:param></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.shfsDm == "1"'>√自提</s:if> 
										<s:if test='domain.hwmxDomain.shfsDm == "2"'>√送货</s:if> </p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srXf > 0'>√现结</s:if> 
									<s:if test='domain.hwmxDomain.srYj > 0'>√月结</s:if>  
									<s:if test='domain.hwmxDomain.srHdf > 0'>√对付</s:if> </p></td>
				</tr>
				<tr>
		        	<td ></td>
					<td></td>
					<td > </td>
					<td > </td>
					<td > </td>
					<td > </td>
					<td colspan="2"><div class="viewFont">服务类别：</div><p class="printFont"><s:if test='domain.hwmxDomain.hdbh != null && domain.hwmxDomain.hdbh != ""'>√回单</s:if></p></td>
					<td rowspan="3" style="word-break:break-all;word-wrap:break-word"><div class="viewFont">发货记事：</div><p class="printFont"><s:property value="domain.hwmxDomain.bz"/></p></td>
				</tr>
				<tr>
		        	<td align="center">运输费</td>
					<td align="center">燃油费</td>
					<td align="center">保价费</td>
					<td align="center">其他费用</td>
					<td colspan="2"><div class="viewFont">费用总计：</div><p class="printFont"><s:if test='domain.hwmxDomain.srHj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHj"/> </s:text></s:if>元</p> </td>
					<td colspan="2"><div class="viewFont">代收货款：</div><p class="printFont"><s:if test='domain.hwmxDomain.fyDshk > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.fyDshk"/></s:text></s:if>元</p></td>
				</tr>
				<tr>
		        	<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srYsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srYsf"/></s:text></s:if></p></td>
					<td></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srBjf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srBjf"/></s:text></s:if></p></td>
					<td ></td>
					<td colspan="2"><p class="printFont"><s:property value="domain.hwmxDomain.srhjDx"/></p></td>
					<td colspan="2"><p class="printFont"><s:property value="domain.hwmxDomain.fyDshkDx"/></p></td>
				</tr>
			</table>
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
