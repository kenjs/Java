<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>司机审核明细信息</title>
<script type="text/javascript">
	function saveBtn(){
		var title = trim($("#mainForm_baiduPushDomain_title").val());
		var description = trim($("#mainForm_baiduPushDomain_description").val());
		if(description.length>40){   
            alert("信息必须小于40个字符");  
            return false;   
       }
		var uniOrBroadcastFlag = $("[name='baiduPushDomain.uniOrBroadcastFlag']:checked").val();
		var url = jcontextPath+"/baiduPush";  
		var data = {"baiduPushDomain.title":title
				,"baiduPushDomain.description":description
				,"baiduPushDomain.uniOrBroadcastFlag":uniOrBroadcastFlag
				};
		if(uniOrBroadcastFlag=='0'){
			var telephone = trim($("#mainForm_baiduPushDomain_telephone").val());
			if(telephone==null||telephone==''){
				alert("手机号不能为空值");
				return;
			}
			data["baiduPushDomain.telephone"]=telephone;
		}else {
			if(!confirm("您确定发通知给所有人吗")){
				return;
			}
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
	function changeMethod(){
		var uniOrBroadcastFlag = $("[name='baiduPushDomain.uniOrBroadcastFlag']:checked").val();
		if(uniOrBroadcastFlag=='0'){
			document.getElementById("broad").style.display="none";
			document.getElementById("uni").style.display="block";
		}else if(uniOrBroadcastFlag=='1'){
			document.getElementById("broad").style.display="block";
			document.getElementById("uni").style.display="none";
		}else{
			document.getElementById("broad").style.display="none";
			document.getElementById("uni").style.display="none";
		}
	}
	function callBackList(text){
		var falseFlag = text.baiduPushDomain.falseFlag;
		if(falseFlag=='0'){
			alert("推送成功");
		}else if(falseFlag=='1'){
			alert("账号不存在");
		}else if(falseFlag=='2'){
			alert("此账号没有绑定");
		}else{
			alert("推送失败,未知原因");
		}
	}
	
	//选择是的返回处理
	function doYesCallBack(){
		window.close();
	}
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveDriverUserInfo" namespace="/" method="post">
	  <table   class="tabcs" style="width: 620px"   > 
	  
	  		<tr >  
               <td align="right" >推送方式：</td> 
              <td  align="left">
              <s:radio onclick="changeMethod()" name="baiduPushDomain.uniOrBroadcastFlag" list="#{0:'一人',1:'所有人'}" value="0"></s:radio>  
               </td>  
            </tr >
            <tr >  
               <td align="right" ><span style="color:red; padding:0 5px;">*</span>标题：</td> 
              <td  align="left">   
             <s:textfield name="baiduPushDomain.title"  ></s:textfield>  
               </td>  
            </tr >
	    <tr > 
	     <tr style="margin:40px auto; ">  
               <td align="right" width="40%"><span style="color:red; padding:0 5px;">*</span>信息：</td> 
              <td width="60%" align="left"> 
              <s:textarea name="baiduPushDomain.description"  rows="3"></s:textarea>  
               </td>  
         </tr >
         <tr id="uni">  
               <td align="right" ><span style="color:red; padding:0 5px;">*</span>司机账号：</td> 
              <td  align="left">   
             <s:textfield name="baiduPushDomain.telephone"  type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr id="broad" style="display:none">  
               <td align="right" >广播推送：</td> 
              <td  align="left">   
             <span style="color:red; padding:0 5px;">您选择了广播推送，将发通知给所有人</span>
               </td>  
	    <tr > 
            
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;">
    			<input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="推送" type="button" />
        	</td>
    		</tr>
		</table>
</s:form>
</body>
</html>
