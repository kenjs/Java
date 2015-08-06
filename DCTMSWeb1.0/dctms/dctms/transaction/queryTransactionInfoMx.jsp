<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>交易信息明细信息</title>
<script type="text/javascript">
	function saveBtn(){
		if(!checkData()){
				return;
			}
		var id = trim($("#mainForm_transactionInfoDomain_id").val());
		var orderNumber = trim($("#mainForm_transactionInfoDomain_orderNumber").val());
		var cargoId = trim($("#mainForm_transactionInfoDomain_cargoId").val());
		var driverId = trim($("#mainForm_transactionInfoDomain_driverId").val());
		var deployUserid = trim($("#mainForm_transactionInfoDomain_deployUserid").val());
		var companyId = trim($("#mainForm_transactionInfoDomain_companyId").val());
		var tradeFair = trim($("#mainForm_transactionInfoDomain_tradeFair").val());
		var tradeStart = trim($("#mainForm_transactionInfoDomain_tradeStart").val());
		var tradeStartTime = trim($("#mainForm_transactionInfoDomain_tradeStartTime").val());
		var orderStart = trim($("#mainForm_transactionInfoDomain_orderStart").val());
		var remark = trim($("#mainForm_transactionInfoDomain_remark").val());
		var tradeCancelOrigin = trim($("#mainForm_transactionInfoDomain_tradeCancelOrigin").val());
		var url = jcontextPath+"/saveTransactionInfo";  
		var data = {"transactionInfoDomain.id":id
				,"transactionInfoDomain.orderNumber":orderNumber
				,"transactionInfoDomain.cargoId":cargoId
				,"transactionInfoDomain.driverId":driverId
				,"transactionInfoDomain.deployUserid":deployUserid
				,"transactionInfoDomain.companyId":companyId
				,"transactionInfoDomain.tradeFair":tradeFair
				,"transactionInfoDomain.tradeStart":tradeStart
				,"transactionInfoDomain.tradeStartTime":tradeStartTime
				,"transactionInfoDomain.orderStart":orderStart
				,"transactionInfoDomain.remark":remark
				,"transactionInfoDomain.tradeCancelOrigin":tradeCancelOrigin
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
		if(text.transactionInfoDomain.id=="error"){
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
		var orderNumber = trim($("#mainForm_transactionInfoDomain_orderNumber").val());
		var cargoId = trim($("#mainForm_transactionInfoDomain_cargoId").val());
		var driverId = trim($("#mainForm_transactionInfoDomain_driverId").val());
		var deployUserid = trim($("#mainForm_transactionInfoDomain_deployUserid").val());
		var companyId = trim($("#mainForm_transactionInfoDomain_companyId").val());
		var tradeFair = trim($("#mainForm_transactionInfoDomain_tradeFair").val());
		var tradeStart = trim($("#mainForm_transactionInfoDomain_tradeStart").val());
		var tradeStartTime = trim($("#mainForm_transactionInfoDomain_tradeStartTime").val());
		var orderStart = trim($("#mainForm_transactionInfoDomain_orderStart").val());
		var remark = trim($("#mainForm_transactionInfoDomain_remark").val());
		var tradeCancelOrigin = trim($("#mainForm_transactionInfoDomain_tradeCancelOrigin").val());
		if(orderNumber==null||orderNumber.length>50||orderNumber.length==0){   
               alert("订单编号字符范围0到50");  
              return false;   
         }
		if(cargoId==null||cargoId.length>50||cargoId.length==0){   
               alert("货源ID字符范围0到50");  
              return false;   
         }
		if(driverId==null||driverId.length>50||driverId.length==0){   
               alert("车源ID字符范围0到50");  
              return false;   
         }
		if(deployUserid==null||deployUserid.length>50||deployUserid.length==0){   
               alert("用户ID字符范围0到50");  
              return false;   
         }
		if(companyId==null||companyId.length>50||companyId.length==0){   
               alert("企业ID字符范围0到50");  
              return false;   
         }
		if(tradeFair==null||tradeFair.length>50||tradeFair.length==0){   
               alert("交易金额字符范围0到50");  
              return false;   
         }
		if(tradeStart==null||tradeStart.length>50||tradeStart.length==0){   
               alert("交易状态字符范围0到50");  
              return false;   
         }
		if(tradeStartTime==null||tradeStartTime.length>50||tradeStartTime.length==0){   
               alert("交易状态修改时间字符范围0到50");  
              return false;   
         }
		if(orderStart==null||orderStart.length>50||orderStart.length==0){   
               alert("订单状态（是否有效）字符范围0到50");  
              return false;   
         }
		if(remark==null||remark.length>50||remark.length==0){   
               alert("备注字符范围0到50");  
              return false;   
         }
		if(tradeCancelOrigin==null||tradeCancelOrigin.length>50||tradeCancelOrigin.length==0){   
               alert("交易取消来源字符范围0到50");  
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
<s:form id="mainForm" action="/saveTransactionInfo" namespace="/" method="post">
<s:hidden name="transactionInfoDomain.id"></s:hidden>
	<table class="mxTable"  > 
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>订单编号：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.orderNumber" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>货源ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.cargoId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>车源ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.driverId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>用户ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.deployUserid" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>企业ID：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.companyId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易金额：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeFair" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易状态：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeStart" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易状态修改时间：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeStartTime" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>订单状态（是否有效）：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.orderStart" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>备注：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.remark" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>交易取消来源：</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeCancelOrigin" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
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
