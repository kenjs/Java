<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>分包商对账信息</title>
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
			$("#mainForm").attr("action",jcontextPath+"/hygl/xyjszbyjdz!download");
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

	function　printWindow() {  
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　1.5; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
</script>
<base target="_self" />
</head>
<body>
<%try{ %>
<s:form action="xyjszbyjdz!initMx" namespace="/wlgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"/>
	<s:hidden name="domain.zrbmDm"/>
    <s:hidden name="domain.pcrqQ"/>
    <s:hidden name="domain.pcrqZ"/>
    <s:hidden name="domain.dzbz"/>
    <object id="factory" width=0 height=0 classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
      codebase="<sys:context/>/resource/pageframe/js/smsx.cab">
	</object>
    <div class="pop_contc ">
       <div class="divCss">
			  <table id="tab1" width="100%" align="center" border="0" cellspacing="0" cellpadding="0" class="poptab_css tabCss">
			    <tr>
			  		<td class="tab_title" align="center" colspan="12" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			<s:property value="domain.qingDan.gsMc" />&nbsp;&nbsp;分包商月结账单
			  		</td>
			    </tr>
				<tr>
					<td align="center" colspan="13"
						style="border-left: white; border-right: white; border-top: white; border-bottom: white">
						派车日期：<s:property value="domain.pcrqQ" />
						&nbsp;&nbsp;&nbsp;~ &nbsp;&nbsp;&nbsp;
						<s:property value="domain.pcrqZ" />&nbsp;&nbsp;&nbsp;
					</td>  
				</tr>
				<tr>
		        	<th width="2%" class="thCss">序号</th>
		        	<th width="6%" class="thCss">派车单号</th>
		        	<th width="5%" class="thCss">派车日期</th>
		        	<th width="10%" class="thCss">货物名称</th>
		        	<th width="11%"class="thCss">分包商类别</th>
					<th width="10%" class="thCss">分包商</th>
					<th width="4%" class="thCss">结算金额</th>
					<th width="4%" class="thCss">对账金额</th>
					<th width="6%" class="thCss">对账人</th>
					<th width="6%" class="thCss">对账日期</th>
					<th width="8%" class="thCss">对账部门</th>
					<th width="4%" class="thCss">是否差异</th>
					<th width="4%" class="thCss">差异金额</th>
		        </tr>
		        <s:iterator value="domain.dataList" id="db" status="i">
		        <tr>
		        	<Td align="center"><s:property value="#i.index+1"/></Td>
		        	<Td align="center"><s:property value="#db.pcdh"/></Td>
		        	<Td align="center"><s:property value="#db.pcrq"/></Td>
		        	<Td align="center"><s:property value="#db.hwmc"/></Td>
					<Td align="center"><s:property value="#db.jgmc"/></Td>
					<Td align="center"><s:property value="#db.fbsmc"/></Td>
					<Td align="center"><s:property value="#db.jsYj"/></Td>
					<Td align="center"><s:property value="#db.dzje"/></Td>
					<Td align="center"><s:property value="#db.dzrmc"/></Td>
					<Td align="center"><s:property value="#db.dzrq"/></Td>
					<Td align="center"><s:property value="#db.dzjgmc"/></Td>
					<Td align="center">
						<s:if test='#db.dzCybz=="Y"'>有</s:if>
						<s:if test='#db.dzCybz=="N"'>无</s:if>
					</Td>
					<Td align="center"><s:property value="#db.dzcyje"/></Td>
		        </tr>
		        </s:iterator>
		    </table>
		    </div>
		    <div class="pop_btn noprint">
				<button type="button" class="pop_btnbg" id="printBtn">打 印</button>
				  &nbsp;
				 <button type="button" class="pop_btnbg" id="downloadBtn">导出</button>
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
