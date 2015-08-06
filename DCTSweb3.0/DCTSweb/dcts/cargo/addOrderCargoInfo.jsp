<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-发布货源</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="<sys:context/>/resource/js/authCode.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
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
		//document.getElementById('mainForm').submit();
		$.ajax({
                cache: true,
                type: "POST",
                url:jcontextPath+"/addLocalOrderCargoInfo",
                data:$('#mainForm').serialize(),
                async: false,
                dataType:'json', 
                error: function(request) {
                },
                success: function(data) {
                $("#subId").removeAttr("disabled");
					  if(data.result=='0'){
					  //$("#subId").attr({"disabled":"disabled"});//防止二次提交
					  getOpenes(data.content);
					   //art.dialog.open('<sys:context/>/dcts/cargo/addOrderCargoInfoSuccess.jsp',{width:437,height:228,lock:true,drag:true});//drag 是否允许用户拖动
					  }else if(data.result=='1'){
					     location.href=jcontextPath+"/index.jsp";
					  }else{
					  artDialogInfo(3,data.errorMessage,'error');
					  }
					}
                
            });
	}
	
</script>
 </head>
  
  <body>
   <jsp:include page="/heades.jsp" />
   
   <div class="mian">
   <s:form id="mainForm" action="/addLocalOrderCargoInfo" namespace="/" method="post">
   <input type="hidden" id="startProvinceId" name="orderCargoInfoDomain.startProvince" value=""/>
   <input type="hidden" id="startCityId" name="orderCargoInfoDomain.startCity" value=""/>
   <input type="hidden" id="startCountyId" name="orderCargoInfoDomain.startCounty" value=""/>
   <input type="hidden" id="endProvinceId" name="orderCargoInfoDomain.endProvince" value=""/>
   <input type="hidden" id="endCityId" name="orderCargoInfoDomain.endCity" value=""/>
   <input type="hidden" id="endCountyId" name="orderCargoInfoDomain.endCounty"  value=""/>
   <input type="hidden" id="id" name="orderCargoInfoDomain.id"  value="${orderCargoInfoDomain.id }"/>
	<div class="fl flant w850">
    <h3>发布货源</h3>
    <div class="laing shng">
        <ul>
          <li class="ly_roto">
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
            <input name="orderCargoInfoDomain.startProCityCounty" readonly="readonly" value="${orderCargoInfoDomain.startProCityCounty }" id="startProCityCounty"  type="text" onfocus="getOnfucts('startProCityCountyMpt')"  class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
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
            <input name="orderCargoInfoDomain.endProCityCounty" readonly="readonly" value="${orderCargoInfoDomain.endProCityCounty }" id="endProCityCounty"  type="text" onfocus="getOnfucts('endProCityCountyMpt')" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
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
            <dd>重量<input name="orderCargoInfoDomain.cargoWeight" onfocus="getOnfucts('cargoWeightCubageMpt')" onkeyup="myNumberic(this)" id="cargoWeight" type="text" class="int int40" />吨</dd>
            <dd><label class="wt80">体积&nbsp;&nbsp;</label></dd>
            <dd><input name="orderCargoInfoDomain.cargoCubage" onkeyup="myNumberic(this)" id="cargoCubage" type="text" onfocus="getOnfucts('cargoWeightCubageMpt')" class="int int135" style="width:88px;"/>&nbsp;&nbsp;方</dd>
          </dl>
          <div id="cargoWeightCubageMpt" style="margin-left:93px;display:none" class="mpt">
                <div class="wn_a"></div>
                <div id="cargoWeightCubageHtmlId" class="wn_s"></div>
                <div class="wn_c"></div>
           </div>
           <div class="mtf" >注：货物重量或货物体积必须填写一个。</div>
		  </li>
          <li>
            <label>车型要求</label>
            <s:select cssClass="sel65" id="requestCarLength" name="orderCargoInfoDomain.requestCarLength" listKey="name" listValue="name" list="dataDictInfoDomain.carLengthData" />
            &nbsp;
            <s:select cssClass="sel65" id="requestCarBarType" name="orderCargoInfoDomain.requestCarBarType"  list="dataDictInfoDomain.carBarTypeData" listKey="name" listValue="name" />
            &nbsp;
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
           <p class="mut"></p>
            <label>手机号码</label>
            <input name="orderCargoInfoDomain.contactMobilephone" value="${orderCargoInfoDomain.contactMobilephone }" id="contactMobilephone" type="text" onfocus="getMobilephone()" class="int" />
            <div class="mpt" style="display:none" id="contactMobilephoneMpt">
                <div class="wn_a"></div>
                <div class="wn_s" id="contactMobilephoneHtmlId"></div>
                <div class="wn_c"></div>
            </div>
            <div class="mtf" >注：手机号码或固定电话必须填写一个。</div>
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
          <li class="ternow">
            <label></label>
            <input name="" id="subId" type="button" onclick="getSubmit()" value="提交" class="sub" />&nbsp;&nbsp;
           <input class="sub"  type="button" onclick="locationHref('<sys:context/>/dcts/cargo/importCargoFromFile.jsp');" value="导入货源" />
            </li>
        </ul>
        <div style="clear:both;"></div>
      </div>
    </div>
    </s:form>
    
    <div class="fr w450">
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
   
	<!-- 合作伙伴 -->
        <div class="both"></div>
  <jsp:include page="/cooperativePartner.jsp" />
<br />
<br />
</div>
<jsp:include page="/bottom.jsp" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">	
	function getOpenes(orderCargoId){
		var openHtml = '<div class="buced">'+
                		'<div class="fl ticks"><img src="<sys:context/>/resource/image/index/jou.jpg" width="70" height="70" /></div>'+
                		'<div class="fr frcks">恭喜！您已成功发布货源！</div>'+
                		'<div class="botcks" style="width: 450px;">'+
                		'<a href="javascript:setDriverCar(\''+orderCargoId+'\')">立即匹配车源</a>'+
                		'<a href="javascript:continuePublish();">继续发布</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/queryLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=a_id_4\');">我的货源</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/index.jsp\');">返回首页<i>&nbsp;</i></a></div>'+
        				'</div>';
		art.dialog({
		width:400,
		height:100,
		id: 'shake-demo—add',
		title: '消息',
		content:openHtml,
		lock: true,
		fixed: true,
		cancel:false
	});
}

function setDriverCar(orderCargoId) {
 	closeUp();
	art.dialog.open(
		'<sys:context/>/dcts/cargo/openDriver.jsp?orderCargoId='+orderCargoId+'&types=1',
		{id:'N3690',title:'订车',width:1220,height:600,lock:true,close:function(){
			if(this.close){
				art.dialog.open.origin.$('#mainForm')[0].reset();
			}
		}}
	);//drag 是否允许用户拖动
}
  	
function continuePublish(){
   art.dialog.open.origin.$('#mainForm')[0].reset();
   closeUp();
}

//关闭所有对话框
function closeUp() {
	var list = art.dialog.list;
	for (var i in list) {
    	list[i].close();
	};
}
</script>
   <!-- 弹出省市区的层 -->
    <jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
   <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
   <script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
  </body>
</html>
