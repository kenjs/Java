<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<title>快到网-个人中心-竞价详情</title>


<script type="text/javascript">
   $(function(){
   var totalPages = ${driverUserInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${driverUserInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${driverUserInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${driverUserInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   
   //确定定车
  function addTrande(driverId,cargoId,tradeFair,cargoFlag){
      var dialog = art.dialog({
    content:'确认该司机为承运人吗？',
    lock:true,
    fixed: true,
    id: 'Fm2',
    icon: 'question',
    ok: function () {
    //参数
     var paramerters={'transactionInfo.driverId':driverId,'transactionInfo.cargoId':cargoId,'transactionInfo:tradeFair':tradeFair,'orderCargoInfo.cargoFlag':cargoFlag};
    	execDatabInteraSuccesDialogOpen(jcontextPath+'/addTransactionInfo',paramerters,'<sys:context/>/dcts/myCenter/transaction/addTransactionInfoSuccess.jsp')
    },
    cancel: true
  });
  }
  
  //后台数据库交互操作(回调函数1.成功后art.dialog.open()弹出成功层，2.没有登录主页跳到登录页面，3.出错后弹错误框提示)
function execHandleOpenSuccessFloor(url,dataParameter,successUrl){
	$.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:dataParameter,
        async: false,
        dataType:'json', 
        error: function(request) {
        },
        success: function(data) {
			  if(data.result=='0'){//成功
			   art.dialog.open(successUrl,{width:437,height:228,lock:true,drag:true});//drag 是否允许用户拖动
			   baiduPushMessagesToDriverTwo(baiduChannelId,baiduUserId,'2');//弹出成功框后给司机发送推送消息
			  }else if(data.result=='1'){//未登录
			     location.href=jcontextPath+"/index.jsp";
			  }else if(data.result=='2'){//出错
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
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <s:form id="mainForm" action="/queryDriverQuoteInfo" namespace="/" method="post">
    <div class="fr sonafr">
     <div class="data dataf" >
    	<h3><i>&nbsp;</i>货源信息</h3>
		  <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td>货物名称</td>
                <td>装货时间</td>
                <td>装货地</td>
                <td>卸货地</td>
                <td width="80">重量</td>
                <td>体积</td>
              </tr>
            </thead>
            <tr>
              <td>${orderCargoInfo.cargoName }</td>
              <td><s:date name="orderCargoInfo.requestStartTime" format="yyyy-MM-dd"/></td>
              <td><div class="ght">${orderCargoInfo.startProvince }-${orderCargoInfo.startCity } <br />${orderCargoInfo.startTown }</div></td>
              <td><div class="ght">${orderCargoInfo.endProvince }-${orderCargoInfo.endCity }<br />${orderCargoInfo.endTown }</div></td>
              <td>${orderCargoInfo.cargoWeight }</td>
              <td>${orderCargoInfo.cargoCubage }</td>
            </tr>
          </table>
        </div>
        <div class="mh15"></div>
        <div class="data dataf" >
        	<h3><i>&nbsp;</i>司机报价</h3>
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
              	<td width="30">&nbsp;</td>
                <td width="80">司机姓名</td>
                <td>车牌号</td>
                <td>车型</td>
                <td>当前位置</td>
                <td>手机号码</td>
                <td>当前报价</td>
                <td>报价类型</td>
                <td width="54">操作</td>
              </tr>
            </thead>
            <s:if test="driverUserInfoDomain.list.size>0">
              <s:iterator value="driverUserInfoDomain.list">
                 <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${name }</td>
              <td>${carNumber }</td>
              <td>${carTypes }</td>
              <td title="${lastLocation }"><span class="icon2">&nbsp;</span>${lastLocation }</td>
              <td>${code }</td>
              <td>${quoteFair }</td>
              <td>${quoteTypeVal }</td>
              <td><a href="javascript:addTrande('${id }','${orderCargoInfo.id }','${quoteFair }',<s:property value='@com.cy.dcts.common.constants.Constants@CARGO_FLAG_TRADING_KEY'/>);">在线订车</a></td>
            </tr>
              </s:iterator>
            </s:if>
          </table>
         <!-- 分页 -->
         <s:if test="driverUserInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='driverUserInfoDomain.pageInfo.curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='driverUserInfoDomain.pageInfo.curPageNo' value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize' name='driverUserInfoDomain.pageInfo.pageSize' value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			   
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

