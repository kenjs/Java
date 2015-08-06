<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>

	<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
	<title>快到网-个人中心-账号管理</title>
	<style type="text/css">
		.lines {
			padding-left: 90px;
		}
	
		.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
		 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
		 line-height:30px; margin-top:5px;  text-indent:5px;}
   </style>
	<script>
		$(document).ready(function(){
			$("#keep_add").click(function(){
				var htmlObj = $(".lines li:first").clone();
				$(".lines").append('<li>'+htmlObj.html()+'&nbsp;&nbsp;<a href="javascript:void(0);" onclick=deleLine(this)>删除</a></li>');
				
			});
			
			//根据登录用户id查询评价
			webUserAppraiseCount();
		});
		function clearMsg(obj) {
			$(obj).next().hide();
		}
		function deleLine(o) {
			$(o).parent().remove();
		}
		function getSubmit() {
			if(!checkData()) {
				return;
			}		
			var name = $.trim($("input[name='domain.name']").val()),
				contactTelephone = $.trim($("input[name='domain.contactTelephone']").val()),
				email = $.trim($("input[name='domain.email']").val()),
				userQq = $.trim($("input[name='domain.userQq']").val());				
			$.ajax({
				url:jcontextPath+'/accountManagementUpdateAction',
				type:'POST',
				async:false,
				dataType:'json',
                data:{"domain.name":name,
                	"domain.email":email,"domain.contactTelephone":contactTelephone,
                	"domain.userQq":userQq},
                success:function(data){
                	if(data.result== '1'){
                		 art.dialog({
                			 	icon: 'succeed',
                			 	time: '2',
                			    ok:function(){
                    				window.location.reload();
                    			},
                			    content: '修改成功！',                			   
                			    cancelVal: '关闭',
                			    cancel: true //为true等价于function(){}
                			});
                	} 
                	else if(data.result== '0'){
                		 art.dialog({
                			icon: 'error',
                			time: '3',
                			ok:function(){
                				window.location.reload();
                			},
             			    content: '修改失败！',
             			    cancelVal: '关闭',
             			    cancel: true //为true等价于function(){}
             			});
                	}
                }
			});
			//document.getElementById('mainForm').submit();
			return true;
		}
		
		function checkData() {
			var telephone = $.trim($("input[name='domain.contactTelephone']").val()),
				email = $.trim($("input[name='domain.email']").val()),
				qq = $.trim($("input[name='domain.userQq']").val());
			var telReg = /^((\d{3,4})|\d{3,4}-)?\d{7,8}$/,
				emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/,
				qqReg = /^[1-9][0-9]{4,}$/;
			if(telephone != '') {
				if(!telReg.test(telephone)){
					$("#telephoneMessageId").html("电话号码格式错误！");
					$("#telephoneMessage").show();
					return false;
				}
			}
			if(email != '') {
				if(!emailReg.test(email)) {
					$("#emailMessageId").html("电子邮箱格式错误！");
					$("#emailMessage").show();
					return false;
				}
			}
			if(qq != '') {
				if(!qqReg.test(qq)) {
					$("#qqMessageId").html("QQ号码格式错误！");
					$("#qqMessage").show();
					return false;
				}
			}
			return true;
		}
		
		
	</script>
</head>
<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<input type="hidden" id="menuAIdHi" value="${domain.menuAId }" name="domain.menuAId"/>
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
    	<div class="head_top">
		<dl>
        	<dt><img src="<sys:context/>/resource/image/index/tx.png" width="100" height="100" /></dt>
            <dd class="adt">
            <h3>${userInfoDomain.code }<a href="<sys:context/>/openUpdatePwdView">密码修改</a></h3>
            <p>手机号码：<span class="m15">${userInfoDomain.mobilephone }</span><a href="<sys:context/>/openUpdateTelephoneView">修改手机号码</a></p>
           <!-- 司机对物流(主账户)公司的评价 -->
             <s:if test="#session.user.parentId==0">
                <div class="numder scnt" id="webUserAppraiseCountId">
            	</div>
             </s:if>
            
            </dd>
        </dl>
        </div>
 <div class="flant thton">
    <h3><i>&nbsp;</i>账户资料</h3>
    <s:form action="/accountManagementUpdateAction" namespace="/" method="post" id="mainForm" name="mainForm">
       <input type="hidden"  id="proviceId" value="" name="domain.companyProvince"/>
       <input type="hidden"  id="cityId" value="" name="domain.companyCity"/>
       <input type="hidden"  id="countyId" value="" name="domain.companyCounty"/>
    <div class="laing shng">
        <ul>
          <li class="state"><label>登录名&nbsp;:</label>
          	<span style="font-size: 15px;">${userInfoDomain.code }</span>
          </li>
          <li class="state"><label>编码&nbsp;:</label>
          	<span style="font-size: 15px;">${userInfoDomain.encoded }</span>
          </li>
          <li class="state"><label>公司类型&nbsp;:</label>
          	<span style="font-size: 15px;">
          	<c:if test="${userInfoDomain.userType == '0'}">
				物流公司
			</c:if>
			<c:if test="${userInfoDomain.userType == '1'}">
				发货方
			</c:if>
			<c:if test="${userInfoDomain.userType == '2'}">
				收货方
			</c:if>
          	</span>
          </li>
          <li>
            <label>您的公司名&nbsp;:</label>
             <!-- <span style="font-size: 15px;padding-right: 35%">${domain.companyName }</span>  -->               
            <input name="domain.companyName" readonly="readonly" type="text" class="int m3" value="${domain.companyName }" style="margin-right: 10px;padding-left: 0px;border: none;"/>                               
            <s:if test="%{userInfoDomain.enterpriseFlag == 0}">
            	<a href="<sys:context/>/openCompanyAuthentication?menuAId=a_id_2">马上认证</a>
            </s:if>
            <s:elseif test="%{userInfoDomain.enterpriseFlag == 1}">
            	<a href="<sys:context/>/openCompanyAuthentication?menuAId=a_id_2&flag=2">重新认证</a>
            </s:elseif>
          </li>
          <li>
            <label>真实姓名&nbsp;:</label>
            <input name="domain.name" type="text" class="int m3" value="${domain.name }" />
           <!--  <a href="###">实名认证</a> -->
          </li>
          <li>
            <label>公司地址&nbsp;:</label>
            <span style="font-size: 15px;">
            	<s:if test="domain.provinceCityCountyStr == ''||domain.provinceCityCountyStr == null">
            		暂无信息
            	</s:if>
            	<s:else>
            		${domain.provinceCityCountyStr } ${domain.companyAddress }
            	</s:else>
            </span>            	
          </li>
          <li>
            <label>营业执照&nbsp;:</label>
            <span style="font-size: 15px;">
            <s:if test="domain.businessLicence == '' ||domain.companyAddress == null">
            		暂无信息
            	</s:if>
            	<s:else>
            		${domain.businessLicence }
            </s:else>
            </span>
          </li>
          <li>
            <label>组织机构代码&nbsp;:</label>
            <span style="font-size: 15px;">
             <s:if test="domain.organizationCode == ''||domain.companyAddress == null">
            		暂无信息
            	</s:if>
            	<s:else>
            		${domain.organizationCode }
            </s:else>
            </span>
          </li>
          <li>
            <label>固定电话&nbsp;:</label>
            <input name="domain.contactTelephone" type="text" class="int m3" value="${domain.contactTelephone }" onfocus="clearMsg(this)"/>
            <div class="mpt" style="display:none;margin-left:93px" id="telephoneMessage">
                <div class="wn_a"></div>
                <div class="wn_s" id="telephoneMessageId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>电子邮箱&nbsp;:</label>
            <input name="domain.email" type="text" class="int m3" value="${domain.email }" onfocus="clearMsg(this)"/>
            <div class="mpt" style="display:none;margin-left:93px" id="emailMessage">
                <div class="wn_a"></div>
                <div class="wn_s" id="emailMessageId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>QQ号码&nbsp;:</label>
            <input name="domain.userQq" type="text" class="int m3" value="${domain.userQq }" onfocus="clearMsg(this)"/>
            <div class="mpt" style="display:none;margin-left:93px" id="qqMessage">
                <div class="wn_a"></div>
                <div class="wn_s" id="qqMessageId" ></div>
                <div class="wn_c"></div>
            </div>
          </li>          
         <!--   <li>
            <label>常运线路&nbsp;:</label>
     		<ul class="lines">
     			<li>
     				从&nbsp;&nbsp;<select name=""><option>浙江省-杭州市</option></select>&nbsp;&nbsp;&nbsp;到&nbsp;&nbsp;<select name=""><option>浙江省-杭州市</option></select>
     			</li>
     		</ul>      -->  
            
            <!--<div><label></label>从&nbsp;&nbsp;<select name=""><option>浙江省-杭州市</option></select>&nbsp;&nbsp;&nbsp;到&nbsp;&nbsp;<select name=""><option>浙江省-杭州市</option></select></div>-->
           <!--  <p class="add"><a href="javascript:void(0);" id="keep_add">继续添加</a></p>
          </li>-->
          <li class="ternow">
          	<label></label>
            <input name="" type="button" value="保存" class="sub" onclick="getSubmit()" />
          </li>  
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
<!-- 个人中心结束 -->
<jsp:include page="/bottom.jsp" />

 <!-- 弹出省市区的层 -->
    <jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
   <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
   <script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
</body>
</html>
