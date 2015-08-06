<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>企业对司机评价明细信息</title>
<script type="text/javascript">
	function saveBtn(){
		if(!checkData()){
				return;
			}
		var id = trim($("#mainForm_userDriverAssessInfoDomain_id").val());
		var driverId = trim($("#mainForm_userDriverAssessInfoDomain_driverId").val());
		var cargoId = trim($("#mainForm_userDriverAssessInfoDomain_cargoId").val());
		var userId = trim($("#mainForm_userDriverAssessInfoDomain_userId").val());
		var transactionId = trim($("#mainForm_userDriverAssessInfoDomain_transactionId").val());
		var arriverEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_arriverEvaluateScore").val());
		var serveEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_serveEvaluateScore").val());
		var tradeEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_tradeEvaluateScore").val());
		var assess = trim($("#mainForm_userDriverAssessInfoDomain_assess").val());
		var url = jcontextPath+"/saveUserDriverAssessInfo";  
		var data = {"userDriverAssessInfoDomain.id":id
				,"userDriverAssessInfoDomain.driverId":driverId
				,"userDriverAssessInfoDomain.cargoId":cargoId
				,"userDriverAssessInfoDomain.userId":userId
				,"userDriverAssessInfoDomain.transactionId":transactionId
				,"userDriverAssessInfoDomain.arriverEvaluateScore":arriverEvaluateScore
				,"userDriverAssessInfoDomain.serveEvaluateScore":serveEvaluateScore
				,"userDriverAssessInfoDomain.tradeEvaluateScore":tradeEvaluateScore
				,"userDriverAssessInfoDomain.assess":assess
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
		if(text.userDriverAssessInfoDomain.id=="error"){
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
		var driverId = trim($("#mainForm_userDriverAssessInfoDomain_driverId").val());
		var cargoId = trim($("#mainForm_userDriverAssessInfoDomain_cargoId").val());
		var userId = trim($("#mainForm_userDriverAssessInfoDomain_userId").val());
		var transactionId = trim($("#mainForm_userDriverAssessInfoDomain_transactionId").val());
		var arriverEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_arriverEvaluateScore").val());
		var serveEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_serveEvaluateScore").val());
		var tradeEvaluateScore = trim($("#mainForm_userDriverAssessInfoDomain_tradeEvaluateScore").val());
		var assess = trim($("#mainForm_userDriverAssessInfoDomain_assess").val());
		if(driverId==null||driverId.length>50||driverId.length==0){   
               alert("司机ID字符范围0到50");  
              return false;   
         }
		if(cargoId==null||cargoId.length>50||cargoId.length==0){   
               alert("货物ID字符范围0到50");  
              return false;   
         }
		if(userId==null||userId.length>50||userId.length==0){   
               alert("评价人id字符范围0到50");  
              return false;   
         }
		if(transactionId==null||transactionId.length>50||transactionId.length==0){   
               alert("订单交易Id字符范围0到50");  
              return false;   
         }
		if(arriverEvaluateScore==null||arriverEvaluateScore.length>50||arriverEvaluateScore.length==0){   
               alert("到达速度（评分）字符范围0到50");  
              return false;   
         }
		if(serveEvaluateScore==null||serveEvaluateScore.length>50||serveEvaluateScore.length==0){   
               alert("司机服务态度（评分）字符范围0到50");  
              return false;   
         }
		if(tradeEvaluateScore==null||tradeEvaluateScore.length>50||tradeEvaluateScore.length==0){   
               alert("交易总评价字符范围0到50");  
              return false;   
         }
		if(assess==null||assess.length>50||assess.length==0){   
               alert("用户填写评语字符范围0到50");  
              return false;   
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
<s:form id="mainForm" action="/saveUserDriverAssessInfo" namespace="/" method="post">
<s:hidden name="userDriverAssessInfoDomain.id"></s:hidden>
	  <table class="mxTable"  > 
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>司机ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.driverId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>货物ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.cargoId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>评价人id：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.userId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>订单交易Id：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.transactionId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>到达速度（评分）：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.arriverEvaluateScore" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>司机服务态度（评分）：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.serveEvaluateScore" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易总评价：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.tradeEvaluateScore" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>用户填写评语：</td> 
              <td width="35%" align="left">   
             <s:textfield name="userDriverAssessInfoDomain.assess" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
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
