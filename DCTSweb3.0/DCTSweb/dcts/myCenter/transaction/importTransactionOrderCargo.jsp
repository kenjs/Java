<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-批量导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/cargo/importCargoFormFile.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<script type="text/javascript">
		//导入订单货源信息（货主版）20140813
	function importTransactionCargo(){
		$("#saveBtn").attr({"disabled":"disabled"});//防止二次提交
		var filePath=$("#uploadFile").val();
		if(filePath==''){
			$("#errorHtmlId").html("请先选择要导入的文件!");
			$("#saveBtn").removeAttr("disabled");
			return false;
		}
		if(filePath.substring(filePath.lastIndexOf(".") + 1) != "xls"){
			$("#errorHtmlId").html("系统只支持xls格式的文件！");
			$("#saveBtn").removeAttr("disabled");
			return false;
		}
		
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:jcontextPath+"/importTransactionInfos",
	        data:$('#mainForm').serialize(),
	        async: false,
	        dataType:'json', 
	        error: function(request) {
	        },
	        success: function(data) {
				  if(data.result=='0'){//成功
				   alert("成功");
				  }else if(data.result=='1'){//未登录
				     location.href=jcontextPath+"/index.jsp";
				  }else {//出错
				  art.dialog({
					  time:3,
				      icon: 'error',
	                  content: data.errorMessage
	      		  });
				  }
				}
	        
	    });
	}
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	
	<div class="fr sonafr">
      <div class="stuop">
        <h3><i>&nbsp;</i>导入订单货源</h3>
        <s:form  method="post" id="mainForm" action="/importTransactionInfos" namespace="/" enctype="multipart/form-data">
        <div class="round">
          <ul>
            <li>
              <label>选择文件：</label>
              <s:file contenteditable="false" id="uploadFile" onfocus="cleanContext();" name="uploadFile" ></s:file>
            </li>
            <li><b style="margin-left:75px;color:red;" id="errorHtmlId">${errorMessage}</b></li>
            <li>
              <label></label>
              <a href="javascript:xiazmb('<sys:context/>/downloadTransactionCargoFile');">下载模板</a>
              <a id="saveBtn" href="javascript:importCargo();">导入</a>
               <!--<input type="button" onc value="导入" id="saveBtn"/> -->
              <li><b style="color:#00DB00; font-size: 23px" id="successHtmlId"></b></li>
          </ul>
        </div>
        </s:form>
        <!-- 
        <div class="data dataf" >
          <h3 class="serve"><i>&nbsp;</i>列表信息<a href="###">保存</a></h3>
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td>货物名称</td>
                <td>装货地</td>
                <td>卸货地</td>
                <td>发货方</td>
                <td>发货单号</td>
                <td>收货方</td>
                <td>订单号</td>
                <td>承运商</td>
                <td>承运司机号码</td>
              </tr>
            </thead>
            <tr>
              <td>李娟</td>
              <td>李娟</td>
              <td>李娟</td>
              <td>省市区</td>
              <td>某地</td>
              <td>183680180497</td>
              <td>0617547</td>
              <td>2014-04-14</td>
              <td>18369856</td>
            </tr>
          </table>
        </div> -->
      </div>
      <div class="fr twof"> </div>
    </div>
	
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
