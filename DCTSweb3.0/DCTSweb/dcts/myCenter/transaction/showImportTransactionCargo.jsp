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

<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>

<script type="text/javascript">
//屏蔽F5刷新键   
  $(document).bind("keydown",function(e){   
		e=window.event||e;
		if(e.keyCode==116){
		e.keyCode = 0;
		return false; //屏蔽F5刷新键   
		}
		});

	function getSubmit(){
	   //提交表单
	    document.getElementById('mainForm').submit();
	   
   
   }
   
   
//备用
function submitFormImage(){
   $.ajax({
				url: jcontextPath + "/saveImportTrandeCargo",   
				type:'post',	
				dataType:'json', 
				data:$("#mainForm").serialize(),      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						
						
					}else if(data.result == 1){//未登录
						location.href=jcontextPath+'/dcts/user/login.jsp';
					}else {//出错（例：参数为空）
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
      <s:form id="mainForm" action="/saveImportTrandeCargo" namespace="/" method="post">
        <div class="data dataf" > 
           <font><b style="color:#ffaad5">红色标记</b>表示：信息有误请核查
           <s:if test="#session.user!=null&&#session.user.parentId==0">
              <s:if test="#session.user.userType==0">(发货方编码、发货单号、收货方、收货单号必填)</s:if>
              <s:elseif test="#session.user.userType==1">(发货单号、收货方、收货单号必填;若承运商司机号码不为空,则必填承运商编码)</s:elseif>
            </s:if></font>
          <h3 class="serve"><i>&nbsp;</i>列表信息 &nbsp;&nbsp; <tt id="h2Id" style="color:#008800;">${sucessMessages }&nbsp;&nbsp;${retMessages }</tt>
          <tt id="h2Id" style="color:red;">${errorMessage }</tt>
          <a style="margin-right: 30px;" href="<sys:context/>/dcts/myCenter/transaction/importTransactionOrderCargo.jsp">返回导入</a>
          <a style="margin-right: 30px;"  href="javascript:getSubmit();">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;</h3>
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td>货物名称</td>
                <td>装货省</td>
                <td>装货市</td>
                <td>装货区县</td>
                <td>卸货省</td>
                <td>卸货市</td>
                <td>卸货区县</td>
                <td>发货方</td>
                <td>发货单号</td>
                <td>收货方</td>
                <td>收货单号</td>
                <td>承运商</td>
                <td width="80px">承运司机号码</td>
              </tr>
            </thead>
           <s:if test="transactionInfoDomain.list.size>0">
         <s:iterator value="transactionInfoDomain.list">
              <tr>
              <td><input name="driverIds" type="hidden" value="${driverId }"/><input   name="cargoNames" type="text" value="${cargoName }"/></td>
              <!-- 装货地省市区 -->
              <s:if test="flas.indexOf('1')>-1">
              <td ><input  style="background: #ffaad5;" name="startProvinces" type="text" value="${startProvince }"/></td>
              </s:if><s:else>
              <td><input  name="startProvinces" type="text" value="${startProvince }"/></td>
              </s:else>
              <s:if test="flas.indexOf('2')>-1">
              <td ><input  style="background: #ffaad5;" name="startCitys" type="text" value="${startCity }"/></td>
              </s:if><s:else>
              <td><input  name="startCitys" type="text" value="${startCity }"/></td>
              </s:else>
              <s:if test="flas.indexOf('3')>-1">
              <td ><input  style="background: #ffaad5;" name="startCountys" type="text" value="${startCounty }"/></td>
              </s:if><s:else>
              <td><input  name="startCountys" type="text" value="${startCounty }"/></td>
              </s:else>
              
              <!-- 卸货地省市区 -->
               <s:if test="flas.indexOf('4')>-1">
                <td ><input  style="background: #ffaad5;" name="endProvinces" type="text" value="${endProvince }"/></td>
               </s:if><s:else>
                <td><input  name="endProvinces" type="text" value="${endProvince }"/></td>
               </s:else>
               <s:if test="flas.indexOf('5')>-1">
                <td ><input  style="background: #ffaad5;" name="endCitys" type="text" value="${endCity }"/></td>
               </s:if><s:else>
                <td><input  name="endCitys" type="text" value="${endCity }"/></td>
               </s:else>
               <s:if test="flas.indexOf('6')>-1">
                <td ><input  style="background: #ffaad5;" name="endCountys" type="text" value="${endCounty }"/></td>
               </s:if><s:else>
                <td><input  name="endCountys" type="text" value="${endCounty }"/></td>
               </s:else>
               
               <!-- 发货方 收货方 承运商编码及司机手机号 -->
               <s:if test="flas.indexOf('7')>-1">
               <td ><input  style="background: #ffaad5;" name="shipperCodes" type="text" value="${shipperCode }"/></td>
               </s:if><s:else>
               <td><input  name="shipperCodes" type="text" value="${shipperCode }"/></td>
               </s:else>
               <s:if test="flas.indexOf('8')>-1">
               <td ><input  style="background: #ffaad5;" name="shipperOrderNos" type="text" value="${shipperOrderNo }"/></td>
               </s:if><s:else>
               <td><input  name="shipperOrderNos" type="text" value="${shipperOrderNo }"/></td>
               </s:else>
               <s:if test="flas.indexOf('9')>-1">
               <td ><input  style="background: #ffaad5;" name="receiverCodes" type="text" value="${receiverCode }"/></td>
               </s:if><s:else><td><input  name="receiverCodes" type="text" value="${receiverCode }"/></td></s:else>
               <s:if test="flas.indexOf('a')>-1">
               <td ><input  style="background: #ffaad5;" name="receiverOrderNos" type="text" value="${receiverOrderNo }"/></td>
               </s:if><s:else>
               <td><input  name="receiverOrderNos" type="text" value="${receiverOrderNo }"/></td>
               </s:else>
                <s:if test="flas.indexOf('b')>-1">
              <td><input  style="background: #ffaad5" name="shipperCompanyCodes" type="text" value="${shipperCompanyCode }"/></td>
              </s:if><s:else>
                <td><input  name="shipperCompanyCodes" type="text" value="${shipperCompanyCode }"/></td>
              </s:else>
               <s:if test="flas.indexOf('c')>-1">
               <td ><input  style="background: #ffaad5;" name="codes" type="text" value="${code }"/></td>
               </s:if><s:else><td><input  name="codes" type="text" value="${code }"/></td></s:else>
            </tr>
         </s:iterator></s:if>
          
          </table>
        </div>
        </s:form>
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
