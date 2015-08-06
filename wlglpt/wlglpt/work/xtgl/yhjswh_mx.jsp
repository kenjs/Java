<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>用户角色维护</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			var czyDjxh = $("#mainForm_domain_czyDjxh").val();
			var selJsDms = "";
			$("[name='domain.jsDjxh']:checked").each(function(){
				selJsDms+=trim($(this).val())+",";
			});
			var url = jcontextPath+"/xtgl/yhjswh!saveMx";  
	    	var jsonObj = {"domain.selJsDms":selJsDms,"domain.czyDjxh":czyDjxh};
	    	
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="yhjswh!saveMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_czyDjxh" value='<s:property value="domain.czyDjxh"/>'/>
	
	<div class="pop_contc" style="overflow:auto;height: 300px;">
	  <table width="98%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
    	<thead>
	      <tr>
	        <th width="15%">操作</th>
	        <th width="25%">角色名称</th>
	       <th width="25%">角色简称</th>
	       	 <th width="35%">所属单位</th>
	       	 
	      </tr>
	    </thead>
    	<tbody>
	    	<s:iterator value="domain.dataList" status="st" id="d">
	            <tr>
	                <td align="center" width="5%">
						<s:if test='#d.selBz==\"Y\"'>
		               		<input type="checkbox" name="domain.jsDjxh" checked="checked" value="${jsDjxh}"/>
				    	</s:if>
				    	<s:else>
				     		<input type="checkbox" name="domain.jsDjxh" value="${jsDjxh}"/>
				    	</s:else>
					</td>
	                <td align="center" width="25%">${jsMc}</td>
	                 <td align="center" width="25%">${jsJc}</td>
	                 <td align="center" width="35%">${sjMc}</td>
	            </tr>
	        </s:iterator>
        </tbody>
	  </table>
	  <div class="pop_btn">
		<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
		&nbsp;
		<button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	 </div>
	 <br/>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
