<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>˾�������ϸ��Ϣ</title>
<script type="text/javascript">
	var isPass; 
	function saveBtn(submitType){
		var id = trim($("#mainForm_driverUserInfoDomain_id").val());
		if(submitType==null){
			alert("��ѡ����˽��");
			return;
		}
		var url = jcontextPath+"/auditDriverUserInfo";  
		var data = {"driverUserInfoDomain.id":id
				,"driverUserInfoDomain.submitType":submitType
				};
		if(submitType==2){
			isPass = "��ͨ��";
			var reason = getCheckValue("driverUserInfoDomain.reason");
			if (reason.length==0) {
				alert("�����벻ͨ������");
				return;
			}
			//����ת��Ϊ����
			data = arrayToObject(reason,"driverUserInfoDomain.reason",data);
		}else{
			isPass = "ͨ��";
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
		if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}
	//ѡ���ǵķ��ش���
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
		
		//1.����ת��
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
		
		//2.���¼�
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
			var bStop=true;		//��һ���˶��ͽ����ˡ������е�ֵ��������
			for(var attr in json)
			{
				//1.ȡ��ǰ��ֵ
				var iCur=0;
				
				if(attr=='opacity')
				{
					iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
				}
				else
				{
					iCur=parseInt(getStyle(obj, attr));
				}
				
				//2.���ٶ�
				var iSpeed=(json[attr]-iCur)/8;
				iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
				
				//3.���ֹͣ
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
#mainForm_driverUserInfoDomain_submitType3, #mainForm_driverUserInfoDomain_submitType2 {
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
	padding:0 12px 0 36px;
}
.tabm .fwm10 {
	float:left;
	display:inline;
	padding-right:12px;
}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveDriverUserInfo" namespace="/" method="post">
<s:hidden name="driverUserInfoDomain.id"></s:hidden>
	  <table class="mxTable"  style="width:100%; *table-layout:fixed;"> 
	    <tr >  
	    	<td align="right" class="flwh">��¼�ʺţ�</td> 
              <td width="48%" align="left">   
             ${driverUserInfoDomain.code}
               </td>  
	    <td align="right" class="flwh">˾��������</td> 
              <td width="52%" align="left">   
             ${driverUserInfoDomain.name}
               </td>  
            </tr >
            <tr > 
               <td align="right" class="flwh">�����պţ�</td> 
              <td width="48%" align="left">   
            ${driverUserInfoDomain.carNumber}
               </td>  
               <td align="right" class="flwh">���֤�ţ�</td> 
              <td width="52%" align="left">   
             ${driverUserInfoDomain.identityLicenseNum}
               </td>  
            </tr >
            
	   </table>
  <table class="tabm" >
    <tr >
      <td class="fwm">˾�����֤������Ƭ</td>
       <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
      <s:if test='driverUserInfoDomain.identityLicenseNumFront!=null&&driverUserInfoDomain.identityLicenseNumFront!=""'> 
					<img  src="<sys:context/>/outputImage?fileUrl=${driverUserInfoDomain.identityLicenseNumFront}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
				</s:if>	<s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
      </td>
      <td class="fwm10">˾�����֤������Ƭ</td>
       <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
     <s:if test='driverUserInfoDomain.identityLicenseNumContrary!=null&&driverUserInfoDomain.identityLicenseNumContrary!=""'>
            		 <img  src="<sys:context/>/outputImage?fileUrl=${driverUserInfoDomain.identityLicenseNumContrary}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
            	</s:if><s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
      </td>
    </tr >
    <tr >
      <td class="fwm">��ʻ֤��Ƭ</td>
       <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
     <s:if test='driverUserInfoDomain.driversLicense!=null&&driverUserInfoDomain.driversLicense!=""'>
             	<img  src="<sys:context/>/outputImage?fileUrl=${driverUserInfoDomain.driversLicense}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
             </s:if>	<s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
      </td>
      <td class="fwm10">��ʻ֤��Ƭ</td>
       <td  align="left" width="48%"  align="left" style="position: relative;height:200px">
       <s:if test='driverUserInfoDomain.drivingLicense!=null&&driverUserInfoDomain.drivingLicense!=""'>
            	 <img  src="<sys:context/>/outputImage?fileUrl=${driverUserInfoDomain.drivingLicense}" width="300px" height="200px" style=" top: 0px; left: 0px;"/>
             </s:if>	<s:else><img  src="<sys:context/>/resource/images/defaultNotUpload.png" width="300px" height="200px" style=" top: 0px; left: 0px;"/></s:else>
      </td>
    </tr >
  </table>
		<table class="tblf">
			    <tr>
			      <td align="right" style="float:left; width:206px;">���״̬��</td>
			      <td align="left" id="submitTypeTd">
			   		${driverUserInfoDomain.submitType}
			    </tr>
		
			    <tr id="reason">
			      <td align="right" valign="top">��ͨ�����ɣ�</td>
			      <td align="left">
			      <s:checkbox name="driverUserInfoDomain.reason" fieldValue="���֤"></s:checkbox>���֤
			      <s:checkbox name="driverUserInfoDomain.reason" fieldValue="��ʻ֤"></s:checkbox>��ʻ֤
			      <s:checkbox name="driverUserInfoDomain.reason" fieldValue="��ʻ֤"></s:checkbox>��ʻ֤
			      </td>
			    </tr >
    		    <tr class="tab100">
				      <td></td>
				      <td>
				      <input onclick="saveBtn(3)" value="ͨ��" type="button" />
				      <input onclick="saveBtn(2)" value="��ͨ��" type="button" />
				        <input  id="closeWindow" value="�ر�" type="button" /></td>
				    </tr>
		</table>
</s:form>
</body>
</html>
