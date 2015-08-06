<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>发车情况统计表</title>

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
	
		initHykhData(300);
		$("#queryBtn").click(function(){
			var jgbm=$("#mainForm_domain_sjJgbm").val();
			var rqq=$("#mainForm_domain_rqq").val();
			var rqz=$("#mainForm_domain_rqz").val();
			var kh=$("#mainForm_domain_fhrDjxh").val();
			if(jgbm==''){
				showError("请选择业务单位！");
				return;
			}
			if(rqq==''){
				showError("请选择统计日期起！");
				return;
			}
			if(rqz==''){
				showError("请选择统计日期止！");
				return;
			}
			if(kh==''){
				showError("请选择客户名称！");
				return;
			}
			
			$("#mainForm").attr("action",jcontextPath+"/jcgl/fcqktj!query");
			$("#mainForm").submit();
		})
	})

/* function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　1.5; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.0; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
	}	 */
	
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
		          <td width="20%" >  <sys:gsList myName="domain.sjJgbm" myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:gsList></td>
		          <td width="10%" align="right">统计日期：</td>
		          <td width="25%" >  <s:textfield name="domain.rqz" readonly="true" cssClass="ymdate" ></s:textfield> 
				  </td>
				  
				 
		        </tr>
		   </table>
	</fieldset>
  </div>
    <table id="tab1" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
    	<tr>
    		<td width="4%" id="content"></td>
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
    		<td colspan="15" align="center" id="content" class="tab_title"><font size="3">(单位)发车情况统计表</font></td>
    	</tr>
    	
    
    	<tr>
    		<td colspan="15" align="center" id="content">统计日期:<s:property value="domain.rqq" /> ～ <s:property value="domain.rqz" /></td>
    	</tr>
    	<tr>
    		<td width="45%" colspan="4" align="left" style="border-left: white;border-right: white;border-top: white">线路：</td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="20%" colspan="3" align="right" style="border-left: white;border-right: white;border-top: white">金额单位：元</td>
    	</tr>
    	
    	<tr>
    		<td width="4%" rowspan="2" align="center">序号</td>
    		<td width="8%" rowspan="2" align="center">月份</td>
    		<td width="6%" rowspan="2" align="center">发车数量</td>
    		<td width="6%" colspan="4" align="center">收入</td>
    		<td width="6%" colspan="6" align="center">成本</td>
    		<td width="6%" rowspan="2" align="center">单车平均毛利</td>
    		<td width="6%" rowspan="2" align="center">单车平均毛利率</td>
    	</tr>
    	<tr>
    		<td align="center">小计</td>
    		<td align="center">现付</td>
    		<td align="center">到付</td>
    		<td align="center">回付</td>
    		<td align="center">小计</td>
    		<td align="center">提货</td>
    		<td align="center">运输</td>
    		<td align="center">配送</td>
    		<td align="center">提货占比</td>
    		<td align="center">配送占比</td>
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.ddbh" /></td>
    		<td align="center"><s:property value="#zb.xdrq" /></td>
    		<td align="center"><s:property value="#zb.sl" /></td>
    		<td align="center"><s:property value="#zb.zl" /></td>
    		<td align="center"><s:property value="#zb.tj" /></td>
    		<td align="center"><s:property value="#zb.xj" /></td>
    		<td align="center"><s:property value="#zb.xf" /></td>
    		<td align="center"><s:property value="#zb.df" /></td>
    		<td align="center"><s:property value="#zb.hf" /></td>
    		<td align="center"><s:property value="#zb.sfd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
    		<td align="center"><s:property value="#zb.mdd" /></td>
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
