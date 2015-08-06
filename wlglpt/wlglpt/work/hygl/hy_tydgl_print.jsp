<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>托运单打印</title>
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
	
	function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　2.0; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
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
			  			托运单
			  		</td>
			  	</tr>
		        <tr>
		        	<td colspan="4" align="left" style="border-left: white;border-right: white;border-top: white;border-bottom: white">下单日期：<s:date  name="domain.xdrq" format="yyyy-MM-dd"/></td>
		        	<td colspan="4" align="center"  style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td colspan="4" align="right"  style="border-left: white;border-right: white;border-top: white;border-bottom: white">订单编号：<s:property value="domain.ddbh"/></td>
		        </tr>
		        <tr>
		        	<td align="center">发货人</td>
		        	<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrLxr"/></td>
					<td align="center">发货方</td>
					<td align="left" colspan="5"><s:property value="domain.hwmxDomain.fhrMc"/></td>
					<td align="center">联系电话</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrLxdh"/></td>
				</tr>
				<tr>
					<td align="center">地址</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.fhrDz"/></td>
					<td align="center">地区</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.fhrXzqhMc"/></td>
				</tr>
				<tr>
		        	<td align="center">收货人</td>
		        	<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrLxr"/></td>
					<td align="center">收货方</td>
					<td align="left" colspan="5"><s:property value="domain.hwmxDomain.shrMc"/></td>
					<td align="center">联系电话</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrLxdh"/></td>
				</tr>
				<tr>
					<td align="center">地址</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.shrDz"/></td>
					<td align="center">地区</td>
					<td align="left" colspan="2"><s:property value="domain.hwmxDomain.shrXzqhMc"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">要求发货日期</td>
					<td align="center" ><s:property value="domain.hwmxDomain.yqFhrq"/></td>
					<td align="center" colspan="2">要求到货日期</td>
					<td align="center" ><s:property value="domain.hwmxDomain.yqFhrq"/></td>
					<td align="center" >是否提货</td>
					<td align="center" ><s:if test="domain.hwmxDomain.thflDm==1">是</s:if><s:else>否</s:else></td>
					<td align="center" >送货方式</td>
					<td align="center" colspan="3">送货<input type="checkbox"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自提<input type="checkbox" /></td>
				</tr>
				<tr>
					<th colspan="3">货物名称</th>
					<th colspan="1">包装</th>
					<th colspan="2">货物类型</th>
					<th colspan="2">数量</th>
					<th colspan="2">重量</th>
					<th colspan="2">体积</th>
				</tr>
				<tr>
		        	<td align="center" colspan="3"><s:property value="domain.hwmxDomain.hwmc"/></td>
					<td align="center" colspan="1"><s:property value="domain.hwmxDomain.hwbzHldwMc"/></td>
					<td align="center" colspan="2"><s:if test="domain.hwmxDomain.hwflDm==1">重货</s:if><s:else>泡货</s:else></td>
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
					<td align="center" colspan="2">收入合计</td>
					<td align="center" colspan="2"><s:property value="domain.hwmxDomain.srHj"/></td>
					<td align="center">现付</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srXf"/></td>
					<td align="center">到付</td>
					<td align="center" >
						<s:if test="domain.hwmxDomain.shfsDm==1"><s:property value="domain.hwmxDomain.srThf" /></s:if>
		        		<s:else><s:property value="domain.hwmxDomain.srHdf" /></s:else>
					</td>
					<td align="center">回付</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHf"/></td>
					<td align="center">回扣</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHk"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2">合计（大写）</td>
					<td align="left" colspan="8"><s:property value="domain.hwmxDomain.dxSrHj"/></td>
					<td align="center" >合计</td>
					<td align="center" ><s:property value="domain.hwmxDomain.srHj"/></td>
				</tr>
				<tr>
					<td align="left" colspan="2" >托运人签署：<div style="height: 50px"></div></td>
					<td align="left" colspan="2" >承运人签署：<div style="height: 50px"></div></td>
					<td align="left" colspan="2" >收货人签署：<div style="height: 50px"></div></td>
					<td align="left" colspan="6" >备注：<div style="height: 50px"></div></td>
				</tr>
		    </table>
		<div class="pop_btn noprint">
				 <button type="button" class="pop_btnbg" id="printBtn">打印</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">打印设置</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">打印预览</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div>
		
	</div>
		 
		
	
	<%@include file="/common/message.jsp" %>

 
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
