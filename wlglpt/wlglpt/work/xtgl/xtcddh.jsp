<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>系统菜单导航</title>
<%@ include file="/common/meta.jsp"%>
<!-- 实现IE6下png图片透明 -->
<!--[if IE 6]>
<script src="<sys:context/>/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->
<style type="text/css">
	.Tabb{ border-collapse:collapse; }
	.Tabb td{ border-left:solid 0.1px #ffffff}
	.Tabb td{ border-top:solid 0.1px #ffffff}
	
</style>
<script type="text/javascript" src="<sys:context />/resource/pageframe/js/ddaccordion.js"></script>
<script type="text/javascript">
	$(function(){
		
		
		$("#closeBtn").click(function(){
			window.close();
		})
		
	});
	

	
	function saveOk(){
		showSuccess("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.dh","domain.fzr"];
		var labelNameArray = ["名称","电话","负责人"];
		var compareValueArray = [100,100,100];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function getGnMk(id,sj,url,mc){
		var a = url + "?domain.gnmkDm="+id+"&domain.xtmlXh="+sj;
		var b=mc;
		var c=true;
		window.dialogArguments.parent.parent.document.getElementById("FRM_RIGHT").contentWindow.navigate(a,b,c);
		window.close();
	}
</script>
</head>

<body>
<%
try {
%>
<s:form action="bmwh!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	
	
	<div class="pop_contc" style="height:580px; overflow:auto;">
		
	
	   
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" id="tab">    			
			   <s:iterator value="loginUserDomain.topMenuList" id="zb">
			  	 <tr>
			  			<Td width="150px"  align="center" class="tdd"><div style="width: 80px"><font size="3px"><s:property value="#zb.xtmlMc" /></font></div></Td>
			  			<Td width="64%">
			  				<table width="50%" class="Tabb">
			  				<s:iterator value="#zb.gnList" id="bb">
			  					<Tr>
			  					<Td align="center"><div style="width: 90px"><font size="2.5px"><s:property value="#bb.gnmkMc"/></font></div></Td>
			  					
			  				<Td class="tdd"><div align="left" style="width: 580px">
			  					<s:iterator value="#bb.domainList" id="bb"><a href="javascript:void(0)" onclick="getGnMk('<s:property value="#bb.gnmkDm"/>','<s:property value="#bb.sjMenuDm"/>','<s:property value="#bb.url"/>','<s:property value="#bb.gnmkMc"/>')"><s:property value="#bb.gnmkMc"/></a>&nbsp;&nbsp;
			  					</s:iterator>
			  					</div>	
			  				</Td>	
			  			
			  					</Tr>
			  				</s:iterator>	
			  				</table>
			  			</Td>
			  	 </tr>
			   </s:iterator>
			  		
			</table>
			
			 <div class="pop_btn">
		       <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>

	<%@include file="/common/message.jsp"%>
</s:form>
<%
} catch (Exception e) {
	e.printStackTrace();
	throw e;
}
%>
</body>
</html>
