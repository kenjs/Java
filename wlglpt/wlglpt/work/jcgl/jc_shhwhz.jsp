<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>客户收货趋势变化</title>

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
		
	document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
		initHykhData(300);
		$("#queryBtn").click(function(){
			var jgbm=$("#mainForm_domain_sjJgbm").val();
			var rqq=$("#mainForm_domain_tjRq").val();
			var kh=$("#mainForm_domain_fhrDjxh").val();
			if(jgbm==''){
				showError("请选择业务单位！");
				return;
			}1
			if(rqq==''){
				showError("请选择统计日期！");
				return;
			}
			if(kh==''){
				showError("请选择客户名称！");
				return;
			}
			
			$("#mainForm").attr("action",jcontextPath+"/jcgl/shhwhz!query");
			$("#mainForm").submit();
		})
		
		
		$("#printBtn").click(function() {
			//document.getElementById("factory").printing.Print(true)
			window.print();
		});
		
		/* $("#printSetBtn").click(function(){
			document.getElementById("factory").printing.PageSetup()
		});
		
		$("#viewBtn").click(function(){
			document.getElementById("factory").printing.Preview()
		});
		
		printWindow(); */
	})
/* 	function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　1.0; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
	}
	 */
	
//显示隐藏查询条件
function slideToggle(sydiv){
	if (sydiv=="jbxx")
		{$("#jbxxcont").slideToggle(100);} //显示隐藏基本信息效果的切换,点一下收,点一下开
	if (sydiv=="pchwxx")
		{$("#pchwxxcont").slideToggle(100);} //显示隐藏派车货物信息效果的切换,点一下收,点一下开
	if (sydiv=="clgzxx")
		{$("#clgzxxcont").slideToggle(100);} //显示隐藏车辆跟踪信息效果的切换,点一下收,点一下开
	if (sydiv=="yfzfxx")
		{$("#yfzfxxcont").slideToggle(100);} //显示隐藏运费支付信息效果的切换,点一下收,点一下开

}

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="jctydgl!initMx" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
	
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
		   <table width="100%" border="0"  cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="10%" align="right">业务单位：</td>
		          <td width="25%" >  <sys:gsList myName="domain.sjJgbm" myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:gsList></td>
		          <td width="10%" align="right">统计日期：</td>
		          <td width="20%" >  <s:textfield name="domain.tjRq" readonly="true" cssClass="ymdate" ></s:textfield>
							
				  </td>
				  
				  <td width="6%">客户名称：
		          <td width="25%">
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
    	<tr>
    		<td width="4%" id="content"></td>
    		<td width="12%" id="content"></td>
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
    		<td colspan="16" align="center" id="content" class="tab_title"><font size="3">(单位)客户收货货物汇总统计表</font></td>
    	</tr>
    	
    
    	<tr>
    		<td colspan="16" align="center" id="content">统计日期:<s:property value="domain.tjRq" /> </td>
    	</tr>
    	<tr>
    		<td width="50%" colspan="6" align="left" style="border-left: white;border-right: white;border-top: white">客户单位：<s:property  value="domain.jgMc"/></td>
    		
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		
    		<td width="10%" colspan="4" align="right" style="border-left: white;border-right: white;border-top: white">金额单位：元</td>
    	</tr>
    	
    	<tr>
    		<td width="4%" rowspan="3" align="center">序号</td>
    		<td width="12%" rowspan="3" align="center">客户名称</td>
    		<td width="6%" colspan="7"  align="center">本月</td>
    		<td width="6%" colspan="7" align="center">本年累计</td>
    		
    	</tr>
    	<tr>
    		<td align="center" rowspan="2">数量</td>
    		<td align="center" rowspan="2">重量(吨)</td>
    		<td align="center" rowspan="2">体积(方)</td>
    		<td align="center" colspan="4" >收入</td>
    		<td align="center" rowspan="2">数量</td>
    		<td align="center" rowspan="2">重量(吨)</td>
    		<td align="center" rowspan="2">体积(方)</td>
    		<td align="center" colspan="4" >收入</td>
    	</tr>
    		<tr>
    		<td align="center" >小计</td>
    		<td align="center" >现付</td>
    		<td align="center" >到付</td>
    		<td align="center" >回付</td>
    		<td align="center" >小计</td>
    		<td align="center" >现付</td>
    		<td align="center" >到付</td>
    		<td align="center" >回付</td>
    		
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.bySl" /></td>
    		<td align="center"><s:property value="#zb.byZl" /></td>
    		<td align="center"><s:property value="#zb.byTj" /></td>
    		<td align="center"><s:property value="#zb.byXj" /></td>
    		<td align="center"><s:property value="#zb.byXf" /></td>
    		<td align="center"><s:property value="#zb.byDj" /></td>
    		<td align="center"><s:property value="#zb.byHf" /></td>
    		<td align="center"><s:property value="#zb.bnSl" /></td>
    		<td align="center"><s:property value="#zb.bnZl" /></td>
    		<td align="center"><s:property value="#zb.bnTj" /></td>
    		<td align="center"><s:property value="#zb.bnXj" /></td>
    		<td align="center"><s:property value="#zb.bnXf" /></td>
    		<td align="center"><s:property value="#zb.bnDj" /></td>
    		<td align="center"><s:property value="#zb.bnHf" /></td>
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
