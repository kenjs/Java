<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
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
<% 
	    //获取每个用户的每页显示参数值
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
			  		<td class="tab_title" align="center" colspan="2" id="zgsmc">杭州梦在物流有限公司托运单</td>
			    </tr>
			    <tr>
			  		<td align="left">
			  			监督电话：18957128089
			  		</td>
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
		        </tr>
		      </table>
		      <table width="688" style="margin-top: 5px;" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css" >
		        <tr>
					<td colspan="4"><div class="viewFont">发货单位：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrMc"/></p></td>
					<td colspan="3"><div class="viewFont">收货单位：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrMc"/></p></td>
				</tr>
		        <tr>
		        	<td colspan="2"><div class="viewFont">发货人：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxr"/></p></td>
					<td colspan="2"><div class="viewFont">联系电话：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrLxdh" escape="false"/></p></td>
		        	<td colspan="2"><div class="viewFont">收货人：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxr"/></p></td>
					<td><div class="viewFont">联系电话：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrLxdh" escape="false"/></p></td>
				</tr>
				<tr>
					<td colspan="4"><div class="viewFont">发货地址：</div><p class="printFont"><s:property value="domain.hwmxDomain.fhrDz"/></p></td>
					<td colspan="3"><div class="viewFont">收货地址：</div><p class="printFont"><s:property value="domain.hwmxDomain.shrDz"/></p></td>
			  </tr>
			  <tr>
					<td width="101" align="center">品 名</td>
					<td width="61" align="center">包 装</td>
					<td width="81" align="center">件 数</td>
					<td width="85" align="center">体 积(㎡)</td>
					<td width="60" align="center">重 量(kg)</td>
					<td width="124" align="center" >交付方式</td>
					<td width="176" rowspan="5" style="word-break:break-all;word-wrap:break-word">备注：<s:property value="domain.hwmxDomain.bz"/></td>
				</tr>
				<tr>
		        	<td align="center"><p class="printFont"><s:property value="domain.hwmxDomain.hwmc"/></p></td>
					<td align="center"><p class="printFont"><s:property value="domain.hwmxDomain.hwbzHldwMc"/></p></td>
					<td align="center"> <p class="printFont"><s:text name="format.number.noComma"> <s:param value="domain.hwmxDomain.hwSl" ></s:param> </s:text>  </p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwTj > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwTj"/></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.hwZl > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.hwZl"/></s:text></s:if></p> </td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.shfsDm == "1"'>自提</s:if> 
										<s:if test='domain.hwmxDomain.shfsDm == "2"'>送货</s:if> </p></td>
					
				</tr>
				<tr>
		        	<td align="center">运输费</td>
					<td align="center">配送费</td>
					<td align="center">保价费</td>
					<td align="center">是否回单</td>
					<td align="center">代收货款</td>
					<td align="center">付款方式</td>		
				</tr>	
				<tr>
		        	<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srYsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srYsf"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srPsf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srPsf"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srBjf > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srBjf"/></s:text></s:if></p></td>
					<td align="center">
						<s:if test='domain.hwmxDomain.hdbh!=null'>是</s:if>
						<s:else>否</s:else>
					</td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.fyDshk > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.fyDshk"/></s:text></s:if></p></td>
					<td align="center"><p class="printFont"><s:if test='domain.hwmxDomain.srXf > 0'>现付</s:if> 
									<s:if test='domain.hwmxDomain.srYj > 0'>月结</s:if>  
									<s:if test='domain.hwmxDomain.srHdf > 0'>到付</s:if> </p></td>
					
				</tr>
				<tr>
					<td colspan="3"><div class="viewFont">费用总计：</div><p class="printFont"><s:if test='domain.hwmxDomain.srHjMz > 0'><s:text name="format.number.noComma"><s:param value="domain.hwmxDomain.srHjMz"/> </s:text></s:if>元</p> </td>
					<td colspan="3"><div class="viewFont"></div><p class="printFont"><s:property value="domain.hwmxDomain.srHjMzDx"/></p></td>
				</tr>
				<tr>
					<td align="center">注意事项：</td>
					<td colspan="8" align="left" style="font-size: 10px;">
							1、托运货物必须如实地声明货物价格。托运人必须向承运人申请保价运输，如不保价出现货损、货差由托运人自负。（最高赔偿金额不超过运费的3倍）
							<br/>2、不准假报货名、不准托运危险物品和国家禁用物品。货物中如夹带以上物品后果由托运人承担，并承担连带责任
							<br/>3、如货物到达目的地外包装完好对货物内数量、重量损坏均不负责。
							<br/>4、如遇不可抗拒的自然灾害、军事战争、核爆原因承运人不负责赔偿责任。
							<br/>5、本运单由发货人和理件人签名后生效。本单有效期一个月。
					</td>
				</tr>
				<tr>
					<td colspan="5">托运人签字：</td>
					<td colspan="3">收货人签字：</td>
				</tr>
			</table>
			<table width="688" border="0" align="center" cellpadding="0" cellspacing="0">
			    <tr>
			  		<td align="left" width="330">
			  			地址：杭州市下城区康宁路5号（3-2门）
			  		</td>
			  		<td align="left" width="224">
			  			电话：0571-88236929
			  		</td>
			  		<td align="left" width="134">
			  			传真：0571-88150899
			  		</td>
			    </tr>
			    <tr>
			  		<td align="left">
			  			地址：安庆市光彩四期B区1栋37号
			  		</td>
			  		<td align="left">
			  			电话：0556-5363199
			  		</td>
			  		<td align="left">
			  			手机：18955641299
			  		</td>
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
