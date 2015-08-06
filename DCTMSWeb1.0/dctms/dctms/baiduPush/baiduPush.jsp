<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>˾�������ϸ��Ϣ</title>
<script type="text/javascript">
	function saveBtn(){
		var title = trim($("#mainForm_baiduPushDomain_title").val());
		var description = trim($("#mainForm_baiduPushDomain_description").val());
		if(description.length>40){   
            alert("��Ϣ����С��40���ַ�");  
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
				alert("�ֻ��Ų���Ϊ��ֵ");
				return;
			}
			data["baiduPushDomain.telephone"]=telephone;
		}else {
			if(!confirm("��ȷ����֪ͨ����������")){
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
			alert("���ͳɹ�");
		}else if(falseFlag=='1'){
			alert("�˺Ų�����");
		}else if(falseFlag=='2'){
			alert("���˺�û�а�");
		}else{
			alert("����ʧ��,δ֪ԭ��");
		}
	}
	
	//ѡ���ǵķ��ش���
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
               <td align="right" >���ͷ�ʽ��</td> 
              <td  align="left">
              <s:radio onclick="changeMethod()" name="baiduPushDomain.uniOrBroadcastFlag" list="#{0:'һ��',1:'������'}" value="0"></s:radio>  
               </td>  
            </tr >
            <tr >  
               <td align="right" ><span style="color:red; padding:0 5px;">*</span>���⣺</td> 
              <td  align="left">   
             <s:textfield name="baiduPushDomain.title"  ></s:textfield>  
               </td>  
            </tr >
	    <tr > 
	     <tr style="margin:40px auto; ">  
               <td align="right" width="40%"><span style="color:red; padding:0 5px;">*</span>��Ϣ��</td> 
              <td width="60%" align="left"> 
              <s:textarea name="baiduPushDomain.description"  rows="3"></s:textarea>  
               </td>  
         </tr >
         <tr id="uni">  
               <td align="right" ><span style="color:red; padding:0 5px;">*</span>˾���˺ţ�</td> 
              <td  align="left">   
             <s:textfield name="baiduPushDomain.telephone"  type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr id="broad" style="display:none">  
               <td align="right" >�㲥���ͣ�</td> 
              <td  align="left">   
             <span style="color:red; padding:0 5px;">��ѡ���˹㲥���ͣ�����֪ͨ��������</span>
               </td>  
	    <tr > 
            
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;">
    			<input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="����" type="button" />
        	</td>
    		</tr>
		</table>
</s:form>
</body>
</html>
