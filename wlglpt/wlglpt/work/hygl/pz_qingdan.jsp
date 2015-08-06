<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>配载清单</title>
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
		document.getElementById("factory").printing.leftMargin　=　1.5; 　　   
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
    	
       
			  <table id="tab1" align="center" width="649px" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="table-layout: fixed">
			
			  	<tr  id="tr1">
		        	<td  width="35px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
		        	<td  width="105px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="165px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="165px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
					<td  width="75px" style="border-left: white;border-right: white;border-top: white;border-bottom: white"></td>
				
		        </tr>
			  <tr>
			  		<td class="tab_title" align="center" colspan="8" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			<s:property value="domain.qingDan.gsMc" />
			  		</td>
			  </tr>
		       <tr>
			  		<td class="tab_title"  align="center" colspan="8" style="border-left: white;border-right: white;border-top: white;border-bottom: white">
			  			配载清单
			  		</td>
			  </tr>
		       
		        <tr>
		        	 <th >序号</th>
		        	 <th>托单号</th>
					<th>客户单位</th>
					<th>物品名称</th>
					<th>包装</th>
					<th>件数</th>
					<th>体积</th>
					<th>吨位</th>
					
		        </tr>
		        <s:iterator value="domain.pzList" id="zb" status="i">
		        <Tr>
		        	<Td align="center"><s:property value="#i.index+1"/></Td>
		        	<Td align="center"><s:property value="#zb.tdh"/></Td>
					<Td align="center"><s:property value="#zb.khdw"/></Td>
					<td align="center"><s:property value="#zb.hwmc"/></td>
					<td align="center"><s:property value="#zb.bz"/></td>
					<td align="center"><s:property value="#zb.js"/></td>
					<Td align="center"><s:property value="#zb.tj"/></Td>
					<Td align="center"><s:property value="#zb.dw"/></Td>
				
		        </Tr>
		        </s:iterator>
		    </table>
		<div class="pop_btn noprint">
				<button type="button" class="pop_btnbg" id="printBtn">打 印</button>
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
