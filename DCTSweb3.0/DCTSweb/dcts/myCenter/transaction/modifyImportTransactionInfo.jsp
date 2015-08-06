<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-修改导入的订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>

<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>

<script type="text/javascript">
  //提交表单
	//保存 
	function getSubmit() {  
	   if(!checkModifyInfo()){
	     return;
	  }
		document.getElementById('mainForm').submit();
	}
	
	function checkModifyInfo(){    
	 //发货方编码判断不为空
	 var shipperCode=$("#shipperCode").val();
	 if(shipperCode==""){
	 $("#shipperCodeHtmlId").html("发货方编码必填！");
			$("#shipperCodeMpt").show();
			return false;
	 }
	 //发货单号判断不为空
	 var shipperOrderNo=$("#shipperOrderNo").val();
	  if(shipperOrderNo==""){
	 $("#shipperOrderNoHtmlId").html("发货单号必填！");
			$("#shipperOrderNoMpt").show();
			return false;
	 }
	 //收货方编码判断不为空
	 var receiverCode=$("#receiverCode").val();
	  if(receiverCode==""){
	 $("#receiverCodeHtmlId").html("收货方编码必填！");
			$("#receiverCodeMpt").show();
			return false;
	 }
	 //订单号不为空
	 var receiverOrderNo=$("#receiverOrderNo").val();
	  if(receiverOrderNo==""){
	 $("#receiverOrderNoHtmlId").html("订单号必填！");
			$("#receiverOrderNoMpt").show();
			return false;
	 }
	 
	 //若承运人司机号码不为空则承运商也不为空
	 var code=$("#code").val();
	 var shipperCompanyCode=$("#shipperCompanyCode").val();
	 if(shipperCompanyCode==""&&code!=""){
	 $("#shipperCompanyCodeHtmlId").html("请填写承运商编码！");
	   $("#shipperCompanyCodeMpt").show();
			return false;
	 }
	 
	   if(trim(code) !=""&&!bilenumber(code)){
			  $("#codeHtmlId").html("请填写正确的手机号！");
			  $("#codeMpt").show();
		     return false;
		  }
	 
	 
	 //装货地
	 var serarchStartProCityCounty=$("#serarchStartProCityCounty").val();
	 //卸货地
	 var serarchEndProCityCounty=$("#serarchEndProCityCounty").val();
	 //装货地或卸货地拆分赋值
	 if(serarchStartProCityCounty!=""){
	   setProvinceCityCounty(serarchStartProCityCounty,"startProvinceId","startCityId","startCountyId");
	 }
	 if(serarchEndProCityCounty!=""){
	   setProvinceCityCounty(serarchEndProCityCounty,"endProvinceId","endCityId","endCountyId");
	 }
	 return true;
	}
	
	//必填项得到焦点时隐藏错误提示层并清空内容
	function getOnfucts(str) {
	    var newStr=str.substring(0, str.length-3)+"HtmlId";
	    $("#"+str).hide();
		$("#"+newStr).html("");
		$("#errorMessageId").html("");
		
	}
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	   <div class="fr sonafr">
    <div class="flant auton">
    <h3><i>&nbsp;</i>修改订单</h3>
    <div class="laing shng">
    <s:form id="mainForm" action="/modifyImportTransactionInfo" namespace="/" method="post">
   <input type="hidden" id="startProvinceId" name="orderCargoInfo.startProvince" value=""/>
   <input type="hidden" id="startCityId" name="orderCargoInfo.startCity" value=""/>
   <input type="hidden" id="startCountyId" name="orderCargoInfo.startCounty" value=""/>
   <input type="hidden" id="endProvinceId" name="orderCargoInfo.endProvince" value=""/>
   <input type="hidden" id="endCityId" name="orderCargoInfo.endCity" value=""/>
   <input type="hidden" id="endCountyId" name="orderCargoInfo.endCounty"  value=""/>
   <input type="hidden" id="cargoId" name="transactionInfoDomain.cargoId"  value="${transactionInfoDomain.cargoId }"/>
   <input type="hidden" id="id" name="transactionInfoDomain.id"  value="${transactionInfoDomain.id }"/>
   
   <input type="hidden" id="shipperCompanyId" name="transactionInfoDomain.shipperCompanyId"  value="${transactionInfoDomain.shipperCompanyId }"/>
   <input type="hidden" id="shipperCodeId" name="transactionInfoDomain.shipperCodeId"  value="${transactionInfoDomain.shipperCodeId }"/>
   <input type="hidden" id="receiverCodeId" name="transactionInfoDomain.receiverCodeId"  value="${transactionInfoDomain.receiverCodeId }"/>
        <ul class="order">
          <li> 
            <label>货物名称&nbsp;:</label><input name="transactionInfoDomain.cargoName" value="${transactionInfoDomain.cargoName }" type="text" class="int m3" /></li>
          <li>
            <label>装货地&nbsp;:</label><input name="transactionInfoDomain.serarchStartProCityCounty" readonly="readonly" value="${transactionInfoDomain.serarchStartProCityCounty }" id="serarchStartProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
          </li>
          <li>
            <label>卸货地&nbsp;:</label><input name="transactionInfoDomain.serarchEndProCityCounty" readonly="readonly" value="${transactionInfoDomain.serarchEndProCityCounty }" id="serarchEndProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
          </li>
          <li>
          <s:if test="#session.user!=null&&#session.user.userType==1"><!-- 登陆是发货方（货主）不允许修改自己的信息 只读-->
           <label>发货方&nbsp;:<span>*</span></label><input onfocus="getOnfucts('shipperCodeMpt')" name="transactionInfoDomain.shipperCode" id="shipperCode" readonly="readonly" value="${transactionInfoDomain.shipperCode }"  type="text" class="int m3" />
          </s:if><s:else>
            <label>发货方&nbsp;:<span>*</span></label><input onfocus="getOnfucts('shipperCodeMpt')" name="transactionInfoDomain.shipperCode" id="shipperCode" value="${transactionInfoDomain.shipperCode }"  type="text" class="int m3" />
            </s:else>
            <div class="mpt" style="display:none" id="shipperCodeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="shipperCodeHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>发货单号&nbsp;:<span>*</span></label><input onfocus="getOnfucts('shipperOrderNoMpt')" name="transactionInfoDomain.shipperOrderNo" id="shipperOrderNo" value="${transactionInfoDomain.shipperOrderNo }" type="text" class="int m3" />
            <div class="mpt" style="display:none" id="shipperOrderNoMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="shipperOrderNoHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>收货方&nbsp;:<span>*</span></label><input onfocus="getOnfucts('receiverCodeMpt')" name="transactionInfoDomain.receiverCode" id="receiverCode" value="${transactionInfoDomain.receiverCode }" type="text" class="int m3" />
           <div class="mpt" style="display:none" id="receiverCodeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="receiverCodeHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>订单号&nbsp;:<span>*</span></label><input onfocus="getOnfucts('receiverOrderNoMpt')" name="transactionInfoDomain.receiverOrderNo" id="receiverOrderNo" value="${transactionInfoDomain.receiverOrderNo }" type="text" class="int m3" />
            <div class="mpt" style="display:none" id="receiverOrderNoMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="receiverOrderNoHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
          <s:if test="#session.user!=null&&#session.user.userType==0"><!-- 登陆是物流企业（承运商）不允许修改自己的信息只读 -->
           <label>承运商&nbsp;:</label><input id="shipperCompanyCode" onfocus="getOnfucts('shipperCompanyCodeMpt')" name="transactionInfoDomain.shipperCompanyCode" readonly="readonly" value="${transactionInfoDomain.shipperCompanyCode }" type="text" class="int m3" />
          </s:if><s:else>
            <label>承运商&nbsp;:</label><input id="shipperCompanyCode" onfocus="getOnfucts('shipperCompanyCodeMpt')" name="transactionInfoDomain.shipperCompanyCode" value="${transactionInfoDomain.shipperCompanyCode }" type="text" class="int m3" />
            </s:else>
            <div class="mpt" style="display:none" id="shipperCompanyCodeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="shipperCompanyCodeHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>          
          <li>
            <label>承运司机号码&nbsp;:</label><input id="code" onfocus="getOnfucts('codeMpt')" name="transactionInfoDomain.code" value="${transactionInfoDomain.code }" type="text" class="int m3" />
            <div class="mpt" style="display:none" id="codeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="codeHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>  
          <li><ee id="errorMessageId" style="color:red;margin-left: 40px;">${transactionInfoDomain.errorMessage }</ee></li>        
          <li class="ternow" style=" clear:both;">
            <label></label>
            <input name="" type="button" value="提交订单" onclick="getSubmit();" class="subt" /></li>
          <li></li>
        </ul>
        <div style="clear:both;"></div>
        </s:form>
      </div>
    </div>
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
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>

</body>
</html>
