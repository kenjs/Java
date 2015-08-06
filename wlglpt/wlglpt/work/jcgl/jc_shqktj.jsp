<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>收货情况统计表</title>

<style type="text/css">
	
	#content{
		border-left: white;border-right: white;border-top: white;border-bottom: white
	}

html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">

	$(function(){
		$("#queryBtn").click(function(){
			$("#mainForm").attr("action",jcontextPath+"/jcgl/shqktj!query");
			$("#mainForm").submit();
		})
		
		$("#printBtn").click(function() {
			//document.getElementById("factory").printing.Print(true)
			window.print();
		});
		
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300);
		
		$("#daoChuBtn").click(function(){
			$("#mainForm").attr("action",jcontextPath+"/jcgl/shqktj!download")
			$("#mainForm").submit();
		})
		/* $("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup()
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		
		$("#saveSetBtn").click(function(){
			alert(document.getElementById("factory").printing.leftMargin)
		}); */
		
		//printWindow();
	})
	
	/* function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　1.0; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
	} */

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="jctydgl!initMx" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	
	<div class="right_btnbg noprint">
	    <ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="printBtn" class="licon04">打 印</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		   
	  	</ul>
	</div>
	<div style="display: none;" id="maincont"></div>
<div class="right_cont">  
  <div id="divQuery" class="noprint">
	<fieldset>
		<legend>查询条件</legend>
		   <table width="95%" border="0"  cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="7%" align="right">业务单位：</td>
		          <td width="20%" >  <sys:gsList myName="domain.sjJgbm" myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:gsList></td>
		          <td width="7%" align="right">统计日期：</td>
		          <td width="20%" >  <sys:dateCurrentDayTag myName="domain.tjDate" myId="mainForm_domain_tjDate" myClass="ymdate" /></td>
		          <td width="7%" align="right">客户名称：</td>
		          <td width="20%">
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 230px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		        </tr>
		   </table>
	</fieldset>
  </div>
 
    <table id="tab1" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
    	
    	<tr >
    		<td width="4%" id="content"></td>
    		<td width="13%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    		<td width="6%" id="content"></td>
    	</tr>
   
    	<tr >
    		<td colspan="14" align="center" class="tab_title" id="content"><font size="3">(单位)收货情况统计表</font></td>
    		<td colspan="2" id="content" align="center" class="tab_title"><button type="button" class="pop_btnbg" id="daoChuBtn">导出EXCEL</button></td>
    	</tr>
    	
    
    
    
    	<tr>
    		<td colspan="14" align="center" id="content">统计日期:<s:property value="domain.nowDate" /></td>
    		<td align="center" id="content"></td>
    	</tr>
    	<tr>
    		<td width="4%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="12%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white;"></td>
    		<td width="10%" colspan="3" align="right" style="border-left: white;border-right: white;border-top: white;">金额单位：元</td>
    	</tr>
    	
    	<tr>
    		<td width="4%" rowspan="2" align="center">序号</td>
    		<td width="12%" rowspan="2" align="center">客户名称</td>
    		<td width="6%" colspan="7" align="center">本月</td>
    		<td width="6%" colspan="7" align="center">本年累计</td>
    	</tr>
    	<tr>
    		<td rowspan="1" align="center">重量(吨)</td>
    		<td rowspan="1" align="center">体积(方)</td>
    		<td rowspan="1" align="center">总收入</td>
    		<td rowspan="1" align="center">现付</td>
    		<td rowspan="1" align="center">到付</td>
    		<td rowspan="1" align="center">月结</td>
    		<td rowspan="1" align="center">回扣</td>
    		<td rowspan="1" align="center">重量(吨)</td>
    		<td rowspan="1" align="center">体积(方)</td>
    		<td rowspan="1" align="center">总收入</td>
    		<td rowspan="1" align="center">现付</td>
    		<td rowspan="1" align="center">到付</td>
    		<td rowspan="1" align="center">月结</td>
    		<td rowspan="1" align="center">回扣</td>
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.byZl" /></td>
    		<td align="center"><s:property value="#zb.byTj" /></td>
    		<td align="center"><s:property value="#zb.byXj" /></td>
    		<td align="center"><s:property value="#zb.byXf" /></td>
    		<td align="center"><s:property value="#zb.byDj" /></td>
    		<td align="center"><s:property value="#zb.byHf" /></td>
    		<td align="center"><s:property value="#zb.byHk" /></td>
    		<td align="center"><s:property value="#zb.bnZl" /></td>
    		<td align="center"><s:property value="#zb.bnTj" /></td>
    		<td align="center"><s:property value="#zb.bnXj" /></td>
    		<td align="center"><s:property value="#zb.bnXf" /></td>
    		<td align="center"><s:property value="#zb.bnDj" /></td>
    		<td align="center"><s:property value="#zb.bnHf" /></td>
    		<td align="center"><s:property value="#zb.bnHk" /></td>
    	</tr>
    	</s:iterator>
  </table>
  <!-- 分页表格 id必须为dataList -->
  
  <!-- 分页导航 -->
 
  <%@include file="/common/message.jsp" %>
</div>
	
	
	

</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
