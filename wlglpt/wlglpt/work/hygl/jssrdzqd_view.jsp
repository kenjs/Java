<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>收入对帐清单查看</title>
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
	
	function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　true;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　2.0; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
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
			  			&nbsp;&nbsp;收入对账清单
			  		</td>
			  </tr>
			  <tr>
		        	<td colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
		        	清单名称：<s:property value="domain.qdmc"/>
		        	</td>		        	
		       </tr>
		      <tr>
		      	<th class="thCss">序号</th>		      	
		      	<th class="thCss">来源</th>
		      	<th class="thCss">对账金额</th>
		      	<th class="thCss">未结</th>
		        <th class="thCss">差异金额</th>
		        <th class="thCss">结果</th>
		        <th class="thCss">订单编号</th>
		        <th class="thCss">下单日期</th>
		        <th class="thCss">货物名称</th>
		        <th class="thCss">包装</th>
		        <th class="thCss">回单编号</th>
		        <th class="thCss">始发地</th>
		        <th class="thCss">目的地</th>
		        <th class="thCss">数量</th>
		        <th class="thCss">重量</th>
		        <th class="thCss">体积</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="domain" status="sta">
		      	<tr>
	      			<td align="center" class="bh"><s:property value="#sta.index+1" /></td>		      		
		      		<td align="center">
		      			<s:if test='1 == #domain.ywLydm'>收入对账</s:if>
		      			<s:elseif test="2 == #domain.ywLydm">费用登记</s:elseif>
		      			<s:elseif test="3 == #domain.ywLydm">物流损失</s:elseif>
		      		</td>
		      		<td align="center"><s:property value="#domain.dzje" /></td>
		      		<td align="center"><s:property value="#domain.jsWj" /></td>
		      		<td align="center"><s:property value="#domain.dzcyje" /></td>
		      		<td align="center">
		      		<s:if test='"Y"==#domain.dzcybz'>
		      			<font color="red">有差异</font>
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
				 <button type="button" class="pop_btnbg" id="printBtn">打 印</button>
				  &nbsp;
			    <button type="button" class="pop_btnbg" id="printSetBtn">打印设置</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="viewBtn">打印预览</button>
			     &nbsp;
			      <button type="button" class="pop_btnbg" id="execBtn">导出</button>
			     &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div>	
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
