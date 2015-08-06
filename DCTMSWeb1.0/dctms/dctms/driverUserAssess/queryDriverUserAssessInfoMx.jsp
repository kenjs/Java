<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>司机对企业评价明细信息</title>
<script type="text/javascript">
	function saveBtn(){
		if(!checkData()){
				return;
			}
		var id = trim($("#mainForm_driverUserAssessInfoDomain_id").val());
		var cargoId = trim($("#mainForm_driverUserAssessInfoDomain_cargoId").val());
		var driverId = trim($("#mainForm_driverUserAssessInfoDomain_driverId").val());
		var userId = trim($("#mainForm_driverUserAssessInfoDomain_userId").val());
		var transactionId = trim($("#mainForm_driverUserAssessInfoDomain_transactionId").val());
		var assessEvaluateScore = trim($("#mainForm_driverUserAssessInfoDomain_assessEvaluateScore").val());
		var assess = trim($("#mainForm_driverUserAssessInfoDomain_assess").val());
		var url = jcontextPath+"/saveDriverUserAssessInfo";  
		var data = {"driverUserAssessInfoDomain.id":id
				,"driverUserAssessInfoDomain.cargoId":cargoId
				,"driverUserAssessInfoDomain.driverId":driverId
				,"driverUserAssessInfoDomain.userId":userId
				,"driverUserAssessInfoDomain.transactionId":transactionId
				,"driverUserAssessInfoDomain.assessEvaluateScore":assessEvaluateScore
				,"driverUserAssessInfoDomain.assess":assess
				};
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
		if(text.driverUserAssessInfoDomain.id=="error"){
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
		var cargoId = trim($("#mainForm_driverUserAssessInfoDomain_cargoId").val());
		var driverId = trim($("#mainForm_driverUserAssessInfoDomain_driverId").val());
		var userId = trim($("#mainForm_driverUserAssessInfoDomain_userId").val());
		var transactionId = trim($("#mainForm_driverUserAssessInfoDomain_transactionId").val());
		var assessEvaluateScore = trim($("#mainForm_driverUserAssessInfoDomain_assessEvaluateScore").val());
		var assess = trim($("#mainForm_driverUserAssessInfoDomain_assess").val());
		if(cargoId==null||cargoId.length>50||cargoId.length==0){   
               alert("货源ID字符范围0到50");  
              return false;   
         }
		if(driverId==null||driverId.length>50||driverId.length==0){   
               alert("司机ID字符范围0到50");  
              return false;   
         }
		if(userId==null||userId.length>50||userId.length==0){   
               alert("用户id（企业用户）字符范围0到50");  
              return false;   
         }
		if(transactionId==null||transactionId.length>50||transactionId.length==0){   
               alert("交易订单Id字符范围0到50");  
              return false;   
         }
		if(assessEvaluateScore==null||assessEvaluateScore.length>50||assessEvaluateScore.length==0){   
               alert("评分字符范围0到50");  
              return false;   
         }
		if(assess==null||assess.length>50||assess.length==0){   
               alert("评语字符范围0到50");  
              return false;   
         }
		return true;
	}
</script>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveDriverUserAssessInfo" namespace="/" method="post">
<s:hidden name="driverUserAssessInfoDomain.id"></s:hidden>
	  <table class="mxTable"  > 
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>货源ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.cargoId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>司机ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.driverId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>用户id（企业用户）：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.userId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易订单Id：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.transactionId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>评分：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.assessEvaluateScore" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>评语：</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.assess" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
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
