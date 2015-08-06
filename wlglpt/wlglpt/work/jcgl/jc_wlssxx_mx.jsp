<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>物流损失信息</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#deleteBtn").click(function(){
			window.close();
		});
	})
</script>
</head>

<body>
<%try{ %>
<s:form action="jctydgl!viewWlssMx" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
		<div class="pop_contc" >
		<div style="margin:20px 0 20px 0;">
			<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
		      <tr>
		        <th width="5%">序号</th>
		        <th width="10%">金额</th>
		        <th width="15%">物流损失原因</th>
		        <th width="15%">损失处理方式</th>
		        <th width="20%">责任人</th>
		      </tr>
		       <tbody id="wlssMxTbody">
     		  <s:iterator id="zb" value="domain.wlssMxList" status="sta">
     		  
		      	<tr>
		        	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			       	<td align="center"><s:property value="#zb.je" /></td>
			       	<td align="center"><s:property value="#zb.ssyy" /></td>			       
			        <td align="center"><s:property value="#zb.wlssclfsMc" /></td>
			        <td align="center"><s:property value="#zb.ssZrr" /></td>			        
		      	</tr>
		     </s:iterator>
		     </tbody>
		    </table>
		</div>
		
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="deleteBtn">关 闭</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
