<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>˾������ҵ������ϸ��Ϣ</title>
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
		var cargoId = trim($("#mainForm_driverUserAssessInfoDomain_cargoId").val());
		var driverId = trim($("#mainForm_driverUserAssessInfoDomain_driverId").val());
		var userId = trim($("#mainForm_driverUserAssessInfoDomain_userId").val());
		var transactionId = trim($("#mainForm_driverUserAssessInfoDomain_transactionId").val());
		var assessEvaluateScore = trim($("#mainForm_driverUserAssessInfoDomain_assessEvaluateScore").val());
		var assess = trim($("#mainForm_driverUserAssessInfoDomain_assess").val());
		if(cargoId==null||cargoId.length>50||cargoId.length==0){   
               alert("��ԴID�ַ���Χ0��50");  
              return false;   
         }
		if(driverId==null||driverId.length>50||driverId.length==0){   
               alert("˾��ID�ַ���Χ0��50");  
              return false;   
         }
		if(userId==null||userId.length>50||userId.length==0){   
               alert("�û�id����ҵ�û����ַ���Χ0��50");  
              return false;   
         }
		if(transactionId==null||transactionId.length>50||transactionId.length==0){   
               alert("���׶���Id�ַ���Χ0��50");  
              return false;   
         }
		if(assessEvaluateScore==null||assessEvaluateScore.length>50||assessEvaluateScore.length==0){   
               alert("�����ַ���Χ0��50");  
              return false;   
         }
		if(assess==null||assess.length>50||assess.length==0){   
               alert("�����ַ���Χ0��50");  
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
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ԴID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.cargoId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>˾��ID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.driverId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�û�id����ҵ�û�����</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.userId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>���׶���Id��</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.transactionId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>���֣�</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.assessEvaluateScore" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>���</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserAssessInfoDomain.assess" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
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
