<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-企业认证</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<!-- 省市区三级联动 -->
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 .showDiv{width:278px;height:140px;border:none;overflow:hidden;}
.imgDiv{filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
<script type="text/javascript">
   $(function(){
    //var inputObj=$("#authenticationDataId input");
     if($("#webEnterpriseFlagId").val()==$("#enterpriseFlagId").val()){
        $("#h2Id").html("恭喜您！您的企业已通过认证！");
        if($("#flag").val() == '' || $("#flag").val() != 2) {
        	$("#butsId").hide();
        }    
      
     }else{
       $("#h2Id").html("");
       $("#butsId").show();
       
     }
   })
   
   //提交表单
	//保存 
	 function getSubmits() {
	 //得到当前年月日十分秒
	 var myDate = new Date();
	 years=myDate.getFullYear();
	 months=myDate.getMonth();
	 dates=myDate.getDate();
	 hours=myDate.getHours();
	 minutes=myDate.getMinutes();
	 seconds=myDate.getSeconds();
	 var timeStr=years.toString()+months.toString()+dates.toString()+hours.toString()+minutes+seconds.toString();
	//企业认证中都可以为空，如果都为空就不能提交
	var businessLicenceVal=$.trim($("#businessLicenceId").val());
	var businessUploadVal=$.trim($("#businessUpload").val());
	var organizationCodeVal=$.trim($("#organizationCodeId").val());//组织机构代码
	var orgUploadVal=$.trim($("#orgUpload").val());
	//图片重命名(当前年月日十分秒+数字+图片后缀)
	if(businessUploadVal!=null&&businessUploadVal!=""){
	var businessUploadArray=businessUploadVal.split("\\");
	var businessFileName=businessUploadArray[businessUploadArray.length-1];
	var reBusinessFileName=timeStr+"1."+businessFileName.substring(businessFileName.lastIndexOf(".")+1);
	$("#reBusinessImagesId").val(reBusinessFileName);
	} 
	if(orgUploadVal!=null&&orgUploadVal!=""){
	  var orgUploadValArray=orgUploadVal.split("\\");
	var orgFileName=orgUploadValArray[orgUploadValArray.length-1];
	var reOrgFileName=timeStr+"2."+orgFileName.substring(orgFileName.lastIndexOf(".")+1);
	$("#reOrgImagesId").val(reOrgFileName);
	}
	
	//alert(orgUploadVal);
	//得到原有的图片路径
	var orgImagesPath=$.trim($("#orgImagesId").val());
	var businessImagesPath=$.trim($("#businessImagesId").val());
	 
	//营业执照号和营业执照照片并存的（两者要么都为空或都不为空），组织机构号和组织机构照片同上
	if(businessLicenceVal=="" && businessUploadVal != ""){
		$("#errorPromptId").html("请填写营业执照注册号");
		return false;
	}
	if((businessUploadVal=="" &&businessImagesPath=="")&& businessLicenceVal != ""){
		$("#errorPromptId").html("请上传您的营业执照照片");
		return false;
	}
	if(organizationCodeVal=="" && orgUploadVal != ""){
	   $("#errorPromptId").html("请填写组织机构注册号");
	   //$("#errorPromptId").show();
	   return false;
	}
	if((orgUploadVal==""&&orgImagesPath=="") && organizationCodeVal != ""){
	  	$("#errorPromptId").html("请上传您的组织机构照片");
		//$("#errorPromptId").show();
		return false;
	}	
	 //将省市区县拆分对应存值
	    var provCityCounty=$("#provinceCityCountyStrID").val();
	    setProvinceCityCounty(provCityCounty,"proviceId","cityId","countyId");
	 var code = $.trim($("#chkCode").val());
	 if($("#flag").val() != '' && $("#flag").val() == 2) {
		 if(code == "" || code != codeNote) {
			 $("#codeId").html("验证码输入错误");
			 $("#codeId").show();
			 return false;
		 }
     }  	 
	    //提交表单
	    document.getElementById('mainForm').submit();
	}
	
	var curCount;//当前剩余秒数
	var InterValObj; //timer变量，控制时间
	var codeNote = ""; //验证码
	function sendNote() {
		var mobilephone = $.trim($("#user_mobile").val());
		curCount = 120;
		//产生验证码
		for (var i = 0; i < 6; i++) {
			codeNote += parseInt(Math.random() * 9).toString();
        }
        //设置button效果，开始计时
        $("#btnSendCode").attr("disabled", "true");
        $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
		//向后台发送处理数据
        $.ajax({
			url: jcontextPath + "/sendNote",
			async: false,
			type:'post',	
			dataType:'json', 
			data:{noteCode:codeNote,mobilephone:mobilephone,type:7}, //参数     	               
			success:function(data){//回传函数
				
			}
		});
	}
	function SetRemainTime() {
		if (curCount == 0) {                
			window.clearInterval(InterValObj);//停止计时器
	        $("#btnSendCode").removeAttr("disabled");//启用按钮
	        $("#btnSendCode").val("重新发送验证码");
	        codeNote = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
	     }else {
	        curCount--;
	        $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
	     }
	}
	//清空内容
	function cleanPrompt(id){
	 $("#"+id).html("");
	 //$("#"+id).hide();
	}
	function cleanImage(inputId,imageDivId){
	  if(trim($("#"+inputId).val())==""){
	    $("#"+imageDivId).html("");
	  }
	}
</script>
 </head>
 
<body>
<jsp:include page="/head.jsp" />
<!-- menuAId用于给左边菜单的超链接对应加样式 -->
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
<!-- 企业认证常量 -->
<input type="hidden" id="enterpriseFlagId" value='<s:property value="@com.cy.dcts.common.constants.Constants@ENTERPRISE_FLAG_END"/>' />
<!-- 当前企业认证标识 -->
<input type="hidden" id="webEnterpriseFlagId" value='${webUserInfo.enterpriseFlag}' />

<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<!-- 左边菜单 -->
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
     <div class="fr sonafr">
    <div class="flant auton"><tt></tt>
    <h3><i>&nbsp;</i>企业认证 <tt id="h2Id" style="color:#008800;"></tt></h3>
     <s:form id="mainForm" action="/saveCompanyAuthentication" namespace="/" method="post"  enctype="multipart/form-data">
       <input type="hidden"  id="proviceId" value="" name="companyInfo.companyProvince"/>
       <input type="hidden"  id="cityId" value="" name="companyInfo.companyCity"/>
       <input type="hidden"  id="countyId" value="" name="companyInfo.companyCounty"/>
       <input type="hidden" value="${flag }" name="flag" id="flag"/>
    <div class="laing shng" id="authenticationDataId"><!-- ${companyInfo.companyProvince } ${companyInfo.companyCity }${companyInfo.companyCounty } -->
        <ul>
          <li><label>公司名称&nbsp;:</label><input readonly="readonly" name="companyInfo.companyName" value="${companyInfo.companyName }" type="text" class="int m3" /></li>
          <li>
            <label>公司地址&nbsp;:</label><input name="provinceCityCountyStr" readonly="readonly" value="${provinceCityCountyStr }" id="provinceCityCountyStrID"  type="text"  class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
          </li>
          <li>
            <label></label><input name="companyInfo.companyAddress" value="${companyInfo.companyAddress }"  type="text" class="int m3" value="详细地址" />
          </li>
          <li style="overflow:hidden; zoom:1;">
          <label>营业执照注册号:</label><input name="companyInfo.businessLicence" onblur="cleanImage('businessLicenceId','businessImgDiv')" onfocus="cleanPrompt('errorPromptId')" id="businessLicenceId" value="${companyInfo.businessLicence }" type="text" class="int m3" /><br/>
          <!-- 原营业执照路径 -->
             <input type="hidden" id="businessImagesId"  value="${companyInfo.businessImages }" name="companyInfo.businessImages"/>
             <input type="hidden" id="reBusinessImagesId"  value="" name="reBusinessImagesName"/>
            <div class="csout"  style="margin-top:10px;margin-left: 92px;"><div style="margin-top: 0px;" id="businessImgDiv" class="showDiv">
            <s:if test="companyInfo.businessImages!=''">
            <img id="flattensBusinessImages" alt="" style="width:278px; height:130px;"  src="<sys:context/>/outputImage?fileUrl=${companyInfo.businessImages }&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@COMPANY_AUTHENTICATION'/>"/>
            </s:if>
            </div></div>
            <div  class="scth" style="margin-top:11px;" ><input id="businessUpload"  onfocus="cleanPrompt('errorPromptId')" name="upload"  type="file" value="上传" onchange="previewImage(this,'businessImgDiv')"/><p>大小在2M以内</p></div>
          </li>
          <li style="overflow:hidden; zoom:1;">
            <label>组织机构代码&nbsp;:</label><input name="companyInfo.organizationCode" onblur="cleanImage('organizationCodeId','orgImgDiv')" id="organizationCodeId" onfocus="cleanPrompt('errorPromptId')" value="${companyInfo.organizationCode }" type="text" class="int m3" /><br/>
            <input type="hidden" id="orgImagesId" value="${companyInfo.organizationImages }" name="companyInfo.organizationImages"/>
            <input type="hidden" id="reOrgImagesId"  value="" name="reOrgImagesName"/>
             <div class="csout"  style="margin-top:10px;margin-left: 92px;"><div id="orgImgDiv" style="margin-top: 0px;" class="showDiv">
              <s:if test="companyInfo.organizationImages!=''">
             <img id="flattensOrgImage" alt="" style="width:278px; height:130px;" src="<sys:context/>/outputImage?fileUrl=${companyInfo.organizationImages }&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@COMPANY_AUTHENTICATION'/>"/>
             </s:if>
             </div></div>
            <div  class="scth" style="margin-top:11px;"><input id="orgUpload" name="upload"  type="file" onfocus="cleanPrompt('errorPromptIds')" value="上传" onchange="previewImage(this,'orgImgDiv')"/><p>大小在2M以内</p></div>
          </li>
          <li>
            <label>联系人&nbsp;:</label><input name="companyInfo.contactName" value="${companyInfo.contactName }" type="text" class="int m3" />
          </li>
          <li>
            <label>手机号码&nbsp;:</label>
            <input value="<s:property value="#session.user.mobilephone"/>" type="text" value="133****3690" id="user_mobile"/>            
             <s:if test='%{flag == 2}'>
            	<input id="btnSendCode" name="btnSendCode" type="button" value="获取验证码" class="unt" onclick="sendNote()"style="margin-left: 33px"/>
            </s:if>
            <s:else>
            	<a href="<sys:context/>/openUpdateTelephoneView" class="mat4" >修改手机号</a>
            </s:else>            
          </li>
          <s:if test='%{flag == 2}'>
            <li>
	          	<label>输入验证码:</label>
	          	<input id="chkCode" type="text" class="int m3" onfocus="cleanPrompt('codeId')"/>
          	</li>
          </s:if>         
          <li>
            <label>固定电话&nbsp;:</label><input name="companyInfo.contactTelephone" value="${companyInfo.contactTelephone }" type="text" class="int m3" />
          </li>
          <li>
            <tt style="color:red;margin-left:60px;" id="errorPromptId">${errorMessage }</tt>
            <tt style="color:red;margin-left:60px;display: none;" id="codeId"></tt>
          </li>
          <li class="ternow" style=" clear:both;">
            <label></label>
            <input id="butsId" name="" onclick="getSubmits()" type="button" value="提交认证" class="subt" /></li>
          <li></li>
        </ul>
        <div style="clear:both;"></div>
      </div>
      </s:form>
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
<!-- 图片浏览 (必须放到底部)-->
<script type="text/javascript" src="<sys:context/>/resource/js/imgBrowse.js"></script>
   
