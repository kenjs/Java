<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<% String successMessage =request.getParameter("successMessage"); %>
<head>
<title>快到网-个人中心-批量导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/cargo/importCargoFormFile.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript">
   $(document).bind("keydown",function(e){   
		e=window.event||e;
		if(e.keyCode==116){
		e.keyCode = 0;
		return false; //屏蔽F5刷新键   
		}
		});
			 
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value='<%=request.getParameter("menuAId")  %>' name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form  method="post" id="mainForm" action="/importOrderCargoFromFile" namespace="/" enctype="multipart/form-data">
	
	    <div class="fr sonafr">
	    <div class="flant flonba ">
	    <h3><i>&nbsp;</i>导入货源</h3>
	        <div class="round">
	                    <ul>
	                        <li><label>选择文件：</label>
	                        <s:file contenteditable="false" id="uploadFile" onfocus="cleanContext();" name="uploadFile" ></s:file>
	                        
	                        </li>
	                        <li><b style="margin-left:75px;color:red;" id="errorHtmlId">${errorMessage}</b></li>
	                        <li><label></label><a href="javascript:xiazmb('<sys:context/>/downloadOrderCargoFileTemplate');">下载模板</a>
	                        <a id="saveBtn" href="javascript:importCargo();">保存</a><a href="javascript:xiazmb('<sys:context/>/openAddLocalOrderCargoInfo');">返回发布页面</a></li>
	                         <li>
	                           <b style="color:#00DB00; font-size: 23px" id="successHtmlId">
	                            <%if(!"".equals(successMessage) && successMessage != null ) {%>
	                           
	                                                                        恭喜您导入成功！导入记录为 <%=request.getParameter("successMessage")  %>条货源信息.
	                         <%} %>
	                      </b>
	                  </li>
	             </ul>
	        </div>
	    </div>
	        <div class="fr twof">
	    <div class="styfl">
	     <h3><i>&nbsp;</i>订车流程</h3>
	    	<div class="fure">
	      	<tt class="at">搜索车源</tt>
	      	<tt class="at1">选择车辆</tt>
	      	<tt class="at2">联系车主</tt>
	      	<tt class="at3">确定车辆</tt>  
	        </div>
	    </div>
	    </div>
	    </div>
        </s:form>
    </div>
      <!-- 合作伙伴 -->
       <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />

<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />

</body>
</html>
