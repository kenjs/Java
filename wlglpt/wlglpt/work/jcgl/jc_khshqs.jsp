<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>客户收货明细统计表</title>

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
			var sjJgbm=$("#mainForm_domain_sjJgbm").val();
			var rqq=$("#mainForm_domain_rqq").val();
			var rqz=$("#mainForm_domain_rqz").val();
			var fhrDjxh=$("#mainForm_domain_fhrDjxh").val();
			var khMc=$("#mainForm_domain_fhrMc").val();
			if(sjJgbm==''){
				showError("请选择业务单位！");
				return;
			}
			
			$("#mainForm").attr("action",jcontextPath+"/jcgl/khshqsbh!query");
			$("#mainForm").submit();
		})
	})

	/* function　printWindow() {　   
		document.getElementById("factory").printing.header　=　""; 　　   
		document.getElementById("factory").printing.footer　=　""; 　　   
		document.getElementById("factory").printing.portrait　=　false;//设为true就是横向 　　   
		document.getElementById("factory").printing.leftMargin　=　1.5; 　　   
		document.getElementById("factory").printing.topMargin　=　1.0; 　　   
		document.getElementById("factory").printing.rightMargin　=　1.5; 　　   
		document.getElementById("factory").printing.bottomMargin　=　1.0; 　　   
	} */
	
//显示隐藏查询条件
function slideToggle(sydiv){
	if (sydiv=="syquery")
		{$("#divQuery").slideToggle(100);} //显示隐藏运费支付信息效果的切换,点一下收,点一下开

}

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="khshqsbh!init" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
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
		          <td width="20%" >  <sys:csGsList myClass="select" myId="mainForm_domain_sjJgbm" myName="domain.sjJgbm" mcContainDmBz="Y"/></td>			  
				  <td width="8%" align="right">客户名称：</td>
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
		           <td width="8%" align="right">下单日期：</td>
		          <td width="25%" >  <s:textfield name="domain.rqq" readonly="true" cssClass="ymdate" ></s:textfield>
							 ～ 
							<s:textfield name="domain.rqz" readonly="true" cssClass="ymdate"></s:textfield>
				  </td>
		        </tr>
		   </table>
	</fieldset>
  </div>
 
    <table id="tab1" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
    	<tr>
    		<td width="4%" id="content"></td>
    	
    		<td width="6%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>
    		<td width="7%" id="content"></td>
    		
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>    	
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>  
    		<td width="5%" id="content"></td>
    		<td width="7%" id="content"></td>      		
    	
    	</tr>
    	<tr >
    		<td class="tab_title" colspan="16" align="center" id="content"><font size="3">客户经营情况表</font></td>
    	</tr>
    	
    
    	<tr>
    		<td colspan="16" align="center" id="content"></td>
    	</tr>
     <tr><!--
    		<td width="50%" colspan="5" align="left" style="border-left: white;border-right: white;border-top: white">客户单位：<s:property value="domain.jgMc" /></td>
    		
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="6%" style="border-left: white;border-right: white;border-top: white"></td>
    		<td width="10%"  align="right" style="border-left: white;border-right: white;border-top: white">金额单位：元</td>-->	
    	</tr>
     
    	<tr>
    		<td width="4%" rowspan="2" align="center">序号</td>
    		<td width="6%" rowspan="2" align="center">所属单位</td>
    		<td width="6%" rowspan="2" align="center">客户名称</td>
    		<td width="6%" rowspan="2" align="center">信息费</td>
    		<td width="6%" colspan="4" align="center">收入</td>
    		<td width="6%" colspan="5" align="center">支出</td>
    		<td width="6%" colspan="2" align="center">利润</td>
    	</tr>
    	<tr>
    		<td align="center">月结</td>
    		<td align="center">非月结</td>
    		<td align="center">代垫</td>
    		<td align="center">物流损失</td>
    		<td align="center">提货</td>
    		<td align="center">运输</td>
    		<td align="center">配送</td>
    		<td align="center">代垫</td>
    		<td align="center">物流损失</td>
    		<td align="center">毛利</td>
    		<td align="center">毛利率</td>
    	</tr>
    	<s:iterator value="domain.dataList" id="zb" status="i">
    	<tr>
    		<td align="center"><s:property value="#i.index+1" /></td>
    		<td align="center"><s:property value="#zb.ssjgMc" /></td>
    		<td align="center"><s:property value="#zb.khMc" /></td>
    		<td align="center"><s:property value="#zb.xxf" /></td>
    		
    		<td align="center"><s:property value="#zb.yj" /></td>
    		<td align="center"><s:property value="#zb.fyj" /></td>
    		<td align="center"><s:property value="#zb.srdd" /></td>
    		<td align="center"><s:property value="#zb.wlsssr" /></td>
    		
    		<td align="center"><s:property value="#zb.th" /></td>
    		<td align="center"><s:property value="#zb.ys" /></td>
    		<td align="center"><s:property value="#zb.ps" /></td>
    		<td align="center"><s:property value="#zb.zcdd" /></td>
    		<td align="center"><s:property value="#zb.wlsszc" /></td>  
    		  		
    		<td align="center"><s:property value="#zb.lr" /></td>
    		<td align="center"><s:property value="#zb.lrR" /></td>
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
