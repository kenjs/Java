<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>管理员明细信息</title>
<script type="text/javascript">
	function saveBtn(){
		if(!checkData()){
			return;
		}
		var id = trim($("#mainForm_managerInfoDomain_id").val());
		var name = trim($("#mainForm_managerInfoDomain_name").val());
		var code = trim($("#mainForm_managerInfoDomain_code").val());
		var validateMacFlag = $("[name=managerInfoDomain.validateMacFlag]:checked").val();
		var url = jcontextPath+"/saveManagerInfo";  
		var data = {"managerInfoDomain.id":id
				,"managerInfoDomain.name":name
				,"managerInfoDomain.code":code
				,"managerInfoDomain.validateMacFlag":validateMacFlag
				};
		if(id=="0"){
			var password = trim($("#mainForm_managerInfoDomain_password").val());
			if(password==null||password.length>50||password.length==0){
				alert("密码字符范围0到50");
				return ;
			}
			data["managerInfoDomain.password"]=password;
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
		if(text.managerInfoDomain.id=="error"){
			alert("账号已经存在,保存失败");
			return;
		}
		if(confirm("保存成功!是否关闭页面")){
			doYesCallBack();
		}
	}
	
	//选择是的返回处理
	function doYesCallBack(){
		window.close();
	}
	
	function checkData(){
		var name = trim($("#mainForm_managerInfoDomain_name").val());
		var code = trim($("#mainForm_managerInfoDomain_code").val());
		var validateMacFlag = $("[name=managerInfoDomain.validateMacFlag]:checked").val();
		if(code==null||code.length>50||code.length==0){
			alert("账号字符范围0到50");
			return false;
		}
		var myReg = new RegExp("^[0-9A-Za-z]{2,}$");
		if(! myReg.test(code)){
			alert("账号必须是两位以上字母和数字");
			return false;
		}
		if(name==null||name.length>50||name.length==0){
			alert("姓名字符范围0到50");
			return false;
		}
		if(validateMacFlag==null){
			alert("请选择是否绑定电脑");
			return;
		}
		return true;
	}
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveManagerInfo" namespace="/" method="post">
<s:hidden name="managerInfoDomain.id"></s:hidden>
	 <table class="mxTable"  > 
	  <caption>管理员信息明细表</caption>
	     <s:if test='managerInfoDomain.id ==0'>
	     <tr style="margin:40px auto; ">
	      <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>账号：</td>
	       <td width="35%" align="left">  
	      <s:textfield name="managerInfoDomain.code" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
	      </td>
	    </tr >
	    <tr style="margin:40px auto; ">
	      <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>密码：</td>
	       <td width="35%" align="left">      
	      <s:textfield name="managerInfoDomain.password" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield>     
	      </td>
	    </tr>
	    </s:if>
	    <s:else>
	    <tr style="margin:40px auto; ">
	      <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>账号：</td>
	       <td width="35%" align="left">  
	      <s:textfield name="managerInfoDomain.code" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  readonly="true"></s:textfield> 
	      </td>
	    </tr >
	    </s:else>
	    <tr style="margin:40px auto; ">
	      <td align="right" width="30%"><span style="color:red; padding:0 5px;">*</span>姓名：</td>
	       <td width="70%" align="left">      
	      <s:textfield name="managerInfoDomain.name" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text" ></s:textfield>   
	      </td>
	     </tr>
	     <tr style="margin:40px auto; ">
	      <td align="right" width="30%"><span style="color:red; padding:0 5px;">*</span>绑定：</td>
	       <td width="70%" align="left">      
	      <s:radio name="managerInfoDomain.validateMacFlag" list="#{1:'需要',0:'不需要'}"></s:radio>
	      </td>
	     </tr>
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="保存" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="关闭" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>