<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>������Ϣ��ϸ��Ϣ</title>
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
			alert("�˺��Ѿ�����,����ʧ��");
			return;
		}
		if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}
	
	//ѡ���ǵķ��ش���
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
               alert("��������ַ���Χ0��50");  
              return false;   
         }
		if(cargoId==null||cargoId.length>50||cargoId.length==0){   
               alert("��ԴID�ַ���Χ0��50");  
              return false;   
         }
		if(driverId==null||driverId.length>50||driverId.length==0){   
               alert("��ԴID�ַ���Χ0��50");  
              return false;   
         }
		if(deployUserid==null||deployUserid.length>50||deployUserid.length==0){   
               alert("�û�ID�ַ���Χ0��50");  
              return false;   
         }
		if(companyId==null||companyId.length>50||companyId.length==0){   
               alert("��ҵID�ַ���Χ0��50");  
              return false;   
         }
		if(tradeFair==null||tradeFair.length>50||tradeFair.length==0){   
               alert("���׽���ַ���Χ0��50");  
              return false;   
         }
		if(tradeStart==null||tradeStart.length>50||tradeStart.length==0){   
               alert("����״̬�ַ���Χ0��50");  
              return false;   
         }
		if(tradeStartTime==null||tradeStartTime.length>50||tradeStartTime.length==0){   
               alert("����״̬�޸�ʱ���ַ���Χ0��50");  
              return false;   
         }
		if(orderStart==null||orderStart.length>50||orderStart.length==0){   
               alert("����״̬���Ƿ���Ч���ַ���Χ0��50");  
              return false;   
         }
		if(remark==null||remark.length>50||remark.length==0){   
               alert("��ע�ַ���Χ0��50");  
              return false;   
         }
		if(tradeCancelOrigin==null||tradeCancelOrigin.length>50||tradeCancelOrigin.length==0){   
               alert("����ȡ����Դ�ַ���Χ0��50");  
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
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>������ţ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.orderNumber" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ԴID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.cargoId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ԴID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.driverId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�û�ID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.deployUserid" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ҵID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.companyId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>���׽�</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeFair" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����״̬��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeStart" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����״̬�޸�ʱ�䣺</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeStartTime" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����״̬���Ƿ���Ч����</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.orderStart" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ע��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.remark" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����ȡ����Դ��</td> 
              <td width="35%" align="left">   
             <s:textfield name="transactionInfoDomain.tradeCancelOrigin" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="����" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>
