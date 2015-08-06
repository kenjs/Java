<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-修改货源</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<sys:context/>/resource/js/authCode.js"></script>
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/dataFormat.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/pageframe/js/common.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/cargo/saveOraderCargo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>

<script type="text/javascript">

//提交表单
	//保存 
	function getSubmit() {
	  if (!checkAddInfo()) {
		  return;
		}
		document.getElementById('mainForm').submit();
	}
	
</script>

 </head>
  
  
<body>
<jsp:include page="/head.jsp" />

<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<!-- 左边菜单 -->
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	
    <div class="fr sonafr">
    <div class="flant flonba ">
    <s:form id="mainForm" action="/modifyLocalOrderCargoInfo" namespace="/" method="post">
   <input type="hidden" id="startProvinceId" name="orderCargoInfoDomain.startProvince" value=""/>
   <input type="hidden" id="startCityId" name="orderCargoInfoDomain.startCity" value=""/>
   <input type="hidden" id="startCountyId" name="orderCargoInfoDomain.startCounty" value=""/>
   <input type="hidden" id="endProvinceId" name="orderCargoInfoDomain.endProvince" value=""/>
   <input type="hidden" id="endCityId" name="orderCargoInfoDomain.endCity" value=""/>
   <input type="hidden" id="endCountyId" name="orderCargoInfoDomain.endCounty"  value=""/>
   <input type="hidden" id="id" name="orderCargoInfoDomain.id"  value="${orderCargoInfoDomain.id }"/>
    <h3><i>&nbsp;</i>货源修改</h3>
    <div class="laing shng">
        <ul>
          <li>
            <label>装货时间<font color="red">*</font></label>
            <input  name="orderCargoInfoDomain.requestStartTime"  value="${orderCargoInfoDomain.requestStartTime }" id="requestStartTime" onfocus="getOnfucts('requestStartTimeMpt')" readonly="readonly" onClick="WdatePicker()" class="int m3" style="width: 80px"/>
             <div class="mpt" style="display:none" id="requestStartTimeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="requestStartTimeHtmlId" ></div>
                <div class="wn_c"></div>
            </div>
            
         	<span style="padding-left: 70px"></span>
            <label>卸货时间</label>
            <input  name="orderCargoInfoDomain.requestEndTime" value="${orderCargoInfoDomain.requestEndTime }" id="requestEndTime" onfocus="getOnfucts('requestEndTimeMpt')" readonly="readonly" onClick="WdatePicker()" class="int m3" style="width: 80px"/>
            <div class="mpt" style="display:none" id="requestEndTimeMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="requestEndTimeHtmlId"></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>装货地<font color="red">*</font></label>
            <input name="orderCargoInfoDomain.startProCityCounty" value="${orderCargoInfoDomain.startProCityCounty }" id="startProCityCounty"  type="text" onfocus="getOnfucts('startProCityCountyMpt')"  class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            <div class="mpt" style="display:none" id="startProCityCountyMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="startProCityCountyHtmlId"></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
           <label>详细地址</label>
           <input name="orderCargoInfoDomain.startTown" value="${orderCargoInfoDomain.startTown }" type="text" class="int m3" style="width: 330px;"/>
          </li>
          <li>
            <label>卸货地<font color="red">*</font></label>
            <input name="orderCargoInfoDomain.endProCityCounty" value="${orderCargoInfoDomain.endProCityCounty }" id="endProCityCounty"  type="text" onfocus="getOnfucts('endProCityCountyMpt')" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            <div class="mpt" style="display:none" id="endProCityCountyMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="endProCityCountyHtmlId"></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>详细地址</label>
            <input name="orderCargoInfoDomain.endTown" value="${orderCargoInfoDomain.endTown }" type="text" class="int m3" style="width: 330px;"/>
          </li>
          <li class="fkl">
            <label>货物信息&nbsp;</label>
            <s:select list="dataDictInfoDomain.cargoTypeData"  listKey="code" listValue="name" cssClass="sel65" name="orderCargoInfoDomain.cargoType" />
            <label class="wt80">货物名称&nbsp;&nbsp;</label>
            <input name="orderCargoInfoDomain.cargoName"  id="cargoName" onfocus="getOnfucts('cargoNameMpt')"  value="${orderCargoInfoDomain.cargoName }" type="text" class="int int155" />
             <div id="cargoNameMpt" style="margin-left:237px;display:none" class="mpt">
                <div class="wn_a"></div>
                <div id="cargoNameHtmlId" class="wn_s"></div>
                <div class="wn_c"></div>
           </div> 
          </li>
          
           <li>
          <dl class="flkt">
          	<dt>&nbsp;</dt>
            <dd>重量<input name="orderCargoInfoDomain.cargoWeight" value="${orderCargoInfoDomain.cargoWeight }" onfocus="getOnfucts('cargoWeightCubageMpt')" onkeyup="myNumberic(this)" id="cargoWeight" type="text" class="int int40" />吨</dd>
            <dd><label class="wt80">体积&nbsp;&nbsp;</label></dd>
            <dd><input name="orderCargoInfoDomain.cargoCubage" value="${orderCargoInfoDomain.cargoCubage }" onkeyup="myNumberic(this)" id="cargoCubage" type="text" onfocus="getOnfucts('cargoWeightCubageMpt')" class="int int135" style="width:88px;"/>&nbsp;&nbsp;方</dd>
          </dl>
          <div id="cargoWeightCubageMpt" style="margin-left:93px;display:none" class="mpt">
                <div class="wn_a"></div>
                <div id="cargoWeightCubageHtmlId" class="wn_s"></div>
                <div class="wn_c"></div>
           </div>
           <div class="remark_info" >注：货物重量或货物体积必须填写一个。</div>
		  </li>
          
          <li>
            <label>车型要求</label>
            <s:select cssClass="sel65" id="requestCarLength" name="orderCargoInfoDomain.requestCarLength" listKey="name" listValue="name" list="dataDictInfoDomain.carLengthData" />
            &nbsp;&nbsp;
            <s:select cssClass="sel65" id="requestCarBarType" name="orderCargoInfoDomain.requestCarBarType"  list="dataDictInfoDomain.carBarTypeData" listKey="name" listValue="name" />
            &nbsp;&nbsp;
            <s:select cssClass="sel65" id="requestCarPlateType" name="orderCargoInfoDomain.requestCarPlateType" list="dataDictInfoDomain.carPlateTypeData" listKey="name" listValue="name"></s:select>
          </li>
          <li>
            <label>联系人<font color="red">*</font></label>
            <input name="orderCargoInfoDomain.contactName" value="${orderCargoInfoDomain.contactName }" id="contactName" type="text" onfocus="getOnfucts('contactNameMpt')" class="int" />
            <div class="mpt" style="display:none" id="contactNameMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="contactNameHtmlId"></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
           <p class="mut">注：手机号码或固定电话必须填写一个。</p>
            <label>手机号码</label>
            <input name="orderCargoInfoDomain.contactMobilephone" value="${orderCargoInfoDomain.contactMobilephone }" id="contactMobilephone" type="text" onfocus="getOnfucts('contactMobilephoneMpt')" class="int" />
            <div class="mpt" style="display:none" id="contactMobilephoneMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="contactMobilephoneHtmlId"></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>固定电话</label>
            <input name="orderCargoInfoDomain.contactTelephone" value="${orderCargoInfoDomain.contactTelephone }" id="contactTelephone" type="text" onfocus="getOnfucts('contactTelephoneMpt')" class="int" /> 
            <div class="mpt" style="display:none" id="contactTelephoneMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="contactTelephoneHtmlId"></div>
                <div class="wn_c"></div>
            </div>
            
          </li>
          <li>
          	<label class="fl">备注</label>
            <textarea name="orderCargoInfoDomain.remark" style="width: 330px;height: 45px;">${orderCargoInfoDomain.remark }</textarea>
          </li>
          <li>
            <tt style="color:red;margin-left:60px;">${orderCargoInfoDomain.errorMessage }</tt>
           </li>
          <li class="ternow">
            <label></label>
            <input name="" type="button" onclick="getSubmit()" value="提交" class="sub" /></li>
        </ul>
        <div style="clear:both;"></div>
      </div>
      </s:form>
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
