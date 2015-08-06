<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>企业明细信息</title>
<script type="text/javascript">
	var isPass; 
	function saveBtn(submitType){
		var id = trim($("#mainForm_webUserInfoDomain_id").val());
		var sendNoteOrMail = trim($("#mainForm_webUserInfoDomain_sendNoteOrMail").val());
		var url = jcontextPath+"/auditWebUserInfo";  
		var data = {"webUserInfoDomain.id":id
				,"webUserInfoDomain.submitType":submitType
				,"webUserInfoDomain.sendNoteOrMail":sendNoteOrMail
				};
		if(submitType==2){
			isPass = "不通过";
			var reason = getCheckValue("webUserInfoDomain.reason");
			if (reason.length==0) {
				alert("请输入不通过理由");
				return;
			}
			//数组转换为对象
			data = arrayToObject(reason,"webUserInfoDomain.reason",data);
		}else{
			isPass = "通过";
		}
		AjaxSubmit({
			url:url,
			data:data,
		    method:"get",
		    async:true,
		    success:function(text){
		    	callBackList(text);
		    }
		});
	}
	function callBackList(text){
		if(typeof text =='string'){
			alert(text);
			return;
		}
		document.getElementById("submitTypeTd").innerHTML = isPass;
		if(confirm("保存成功!是否关闭页面")){
			doYesCallBack();
		}
	}
	
	//选择是的返回处理
	function doYesCallBack(){
		window.close();
	}
	
	
	window.onload=function ()
	{
		//var oUl=document.getElementById('ul1');
		//var aLi=oUl.getElementsByTagName('img');
		var aLi=document.getElementsByTagName('img');
		var iMinZindex=2;
		var i=0;
		
		//1.布局转换
<%--		for(i=0;i<aLi.length;i++)--%>
<%--		{--%>
<%--			//aLi[i].innerHTML='left:'+aLi[i].offsetLeft+', top:'+aLi[i].offsetTop;--%>
<%--			aLi[i].style.left=aLi[i].offsetLeft+'px';--%>
<%--			aLi[i].style.top=aLi[i].offsetTop+'px';--%>
<%--		}--%>
		
		for(i=0;i<aLi.length;i++)
		{
			aLi[i].style.position='absolute';
<%--			aLi[i].style.margin='0';--%>
		}
		
		//2.加事件
		for(i=0;i<aLi.length;i++)
		{
			if(i==0||i==2){
				aLi[i].onmouseover=function ()
				{
					this.style.zIndex=iMinZindex++;
					startMove(this, {width: 750, height: 500, marginLeft: -40, marginTop: -150});
					
				};
			}else{
				aLi[i].onmouseover=function ()
				{
					this.style.zIndex=iMinZindex++;
					startMove(this, {width: 750, height: 500, marginLeft: -420, marginTop: -150});
					
				};
			}
			aLi[i].onmouseout=function ()
			{
				
				startMove(this, {width: 300, height: 200, marginLeft: 0, marginTop: 0});
			};
		}
	};

	/**
	 * @author miaov
	 */
	function getStyle(obj, attr)
	{
		if(obj.currentStyle)
		{
			return obj.currentStyle[attr];
		}
		else
		{
			return getComputedStyle(obj, false)[attr];
		}
	}

	function startMove(obj, json, fn)
	{
		clearInterval(obj.timer);
		obj.timer=setInterval(function (){
			var bStop=true;		//这一次运动就结束了——所有的值都到达了
			for(var attr in json)
			{
				//1.取当前的值
				var iCur=0;
				
				if(attr=='opacity')
				{
					iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
				}
				else
				{
					iCur=parseInt(getStyle(obj, attr));
				}
				
				//2.算速度
				var iSpeed=(json[attr]-iCur)/8;
				iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
				
				//3.检测停止
				if(iCur!=json[attr])
				{
					bStop=false;
				}
				
				if(attr=='opacity')
				{
					obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
					obj.style.opacity=(iCur+iSpeed)/100;
				}
				else
				{
					obj.style[attr]=iCur+iSpeed+'px';
				}
			}
			
			if(bStop)
			{
				clearInterval(obj.timer);
				
				if(fn)
				{
					fn();
				}
			}
		}, 30)
	}
	
	
	
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
* {
	margin:0px;
	padding:0px;
}
td {
	color:#333;
}
.tabm {
	margin:15px auto;
	width:100%;
}
.tabm tr td {
	padding:5px 0px;
}
.tab100 {
	width:100%;
}
.tab100 td {
	padding:20px 0px 30px 0px;
}
.tab100 input {
	width:80px;
	border-radius:3px;
	height:30px;
	margin-right:20px;
	background:#2b94f1;
	border:1px solid #3596f0;
	color:white;
	font-size:16px;
	cursor:pointer;
}
#mainForm_webUserInfoDomain_submitType3, #mainForm_webUserInfoDomain_submitType2 {
	margin-right:6px;
}
.mxTable {
	line-height:26px;
}
.tblf {
	table-layout:fixed;
	line-height:36px;
}
.flwh {
	float:left;
	width:116px;
}
.tabm .fwm {
	float:left;
	width:14px;
	padding:0 12px 0 36px;
}
.tabm .fwm10 {
	float:left;
	width:14px;
	display:inline;
	padding-right:12px;
}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveWebUserInfo" namespace="/" method="post">
<s:hidden name="webUserInfoDomain.id"></s:hidden>
	  <table class="mxTable"  style="width:100%; *table-layout:fixed;"> 
	     <tr >  
	    	<td align="right" class="flwh">登录帐号：</td> 
              <td width="48%" align="left">   
            	 ${webUserInfoDomain.code}
               </td>  
	   		 <td align="right" class="flwh">姓名：</td> 
              <td width="52%" align="left">   
            	 ${webUserInfoDomain.name}
               </td>  
            </tr >
	    <tr >  
               <td align="right" class="flwh">手机号：</td> 
              <td align="left">   
             ${webUserInfoDomain.mobilephone}
               </td>  
               <td align="right" class="flwh">公司名称：</td> 
              <td align="left">   
             ${webUserInfoDomain.companyName}
               </td>  
            </tr >
          <tr >  
               <td align="right" class="flwh">联系人：</td> 
              <td align="left">   
             ${webUserInfoDomain.contactName}
               </td>  
               <td align="right" class="flwh">电子邮件：</td> 
              <td align="left">   
             ${webUserInfoDomain.email}
               </td>  
            </tr >
	    <tr>  
               <td align="right" class="flwh">营业执照注册号：</td> 
              <td  align="left">   
             		${webUserInfoDomain.businessLicence}
               </td>  
               <td align="right" class="flwh">组织机构代码：</td> 
              <td align="left">   
            		 ${webUserInfoDomain.organizationCode}
               </td>  
            </tr >
	   </table>
	      <table class="tabm">
			    <tr >
			      <td class="fwm">组织机构照片</td>
			      <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
				       <s:if test='webUserInfoDomain.organizationImages!=null&&webUserInfoDomain.organizationImages!=""'> 
							<img  src="<sys:context/>/outputImage?fileUrl=${webUserInfoDomain.organizationImages}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
						</s:if>	<s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
			      </td>
			      <td class="fwm10">营业执照照片</td>
			       <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
			      	<s:if test='webUserInfoDomain.businessImages!=null&&webUserInfoDomain.businessImages!=""'> 
						<img  src="<sys:context/>/outputImage?fileUrl=${webUserInfoDomain.businessImages}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
					</s:if>	<s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
			      </td>
			    </tr >
  		</table>
		<table class="tblf">
		    <tr>
		      <td align="right" style="float:left; width:206px;">审核状态：</td>
		      <td align="left" id="submitTypeTd">
		      		${webUserInfoDomain.submitType}
		      </td>
		    </tr>
		    <tr >
		      <td align="right" >同时通知货主通过：</td>
		      <td align="left">
              <s:if test='webUserInfoDomain.email!=null&&webUserInfoDomain.email!=""'>
            		 <s:select name="webUserInfoDomain.sendNoteOrMail" list="#{'1':'发邮件','0':'发短信'}" ></s:select>
              </s:if>
              <s:else>
             		 <s:select name="webUserInfoDomain.sendNoteOrMail" list="#{'0':'发短信'}"></s:select>
              </s:else>
		      </td>
		    </tr >
		    <tr id="reason">
		      <td align="right" valign="top">理由：</td>
		      <td align="left">
		       <s:checkbox name="webUserInfoDomain.reason" fieldValue="营业执照注册号"></s:checkbox>营业执照注册号
			      <s:checkbox name="webUserInfoDomain.reason" fieldValue="组织机构代码"></s:checkbox>组织机构代码
			      <s:checkbox name="webUserInfoDomain.reason" fieldValue="组织机构照片"></s:checkbox>组织机构照片
			      <s:checkbox name="webUserInfoDomain.reason" fieldValue="营业执照照片"></s:checkbox>营业执照照片
		    </tr >
		    <tr class="tab100">
		      <td></td>
		      <td>
		      <input onclick="saveBtn(3)" value="通过" type="button" />
		      <input onclick="saveBtn(2)" value="不通过" type="button" />
		      <input  id="closeWindow" value="关闭" type="button" /></td>
		    </tr>
		  </table>
</s:form>
</body>
</html>
