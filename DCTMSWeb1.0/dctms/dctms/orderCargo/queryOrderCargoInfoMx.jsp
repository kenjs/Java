<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>������Ϣ��ϸ��Ϣ</title>
<script type="text/javascript">
	function saveBtn(){
<%--		if(!checkData()){--%>
<%--				return;--%>
<%--			}--%>
		var id = trim($("#mainForm_orderCargoInfoDomain_id").val());
		var cargoName = trim($("#mainForm_orderCargoInfoDomain_cargoName").val());
		var cargoType = trim($("#mainForm_orderCargoInfoDomain_cargoType").val());
		var cargoWeight = trim($("#mainForm_orderCargoInfoDomain_cargoWeight").val());
		var cargoCubage = trim($("#mainForm_orderCargoInfoDomain_cargoCubage").val());
		var requestCarLength = trim($("#mainForm_orderCargoInfoDomain_requestCarLength").val());
		var requestCarPlateType = trim($("#mainForm_orderCargoInfoDomain_requestCarPlateType").val());
		var requestCarBarType = trim($("#mainForm_orderCargoInfoDomain_requestCarBarType").val());
		var freight = trim($("#mainForm_orderCargoInfoDomain_freight").val());
		var requestStartTime = trim($("#mainForm_orderCargoInfoDomain_requestStartTime").val());
		var requestEndTime = trim($("#mainForm_orderCargoInfoDomain_requestEndTime").val());
		var startProvince = trim($("#mainForm_orderCargoInfoDomain_startProvince").val());
		var startCity = trim($("#mainForm_orderCargoInfoDomain_startCity").val());
		var startCounty = trim($("#mainForm_orderCargoInfoDomain_startCounty").val());
		var startTown = trim($("#mainForm_orderCargoInfoDomain_startTown").val());
		var endProvince = trim($("#mainForm_orderCargoInfoDomain_endProvince").val());
		var endCity = trim($("#mainForm_orderCargoInfoDomain_endCity").val());
		var endCounty = trim($("#mainForm_orderCargoInfoDomain_endCounty").val());
		var endTown = trim($("#mainForm_orderCargoInfoDomain_endTown").val());
		var contactName = trim($("#mainForm_orderCargoInfoDomain_contactName").val());
		var contactMobilephone = trim($("#mainForm_orderCargoInfoDomain_contactMobilephone").val());
		var contactTelephone = trim($("#mainForm_orderCargoInfoDomain_contactTelephone").val());
		var remark = trim($("#mainForm_orderCargoInfoDomain_remark").val());
		var deletedFlag = trim($("#mainForm_orderCargoInfoDomain_deletedFlag").val());
		var deployUserid = trim($("#mainForm_orderCargoInfoDomain_deployUserid").val());
		var modifyUserid = trim($("#mainForm_orderCargoInfoDomain_modifyUserid").val());
		var companyId = trim($("#mainForm_orderCargoInfoDomain_companyId").val());
		var cargoFlag = trim($("#mainForm_orderCargoInfoDomain_cargoFlag").val());
		var cargoFlagTime = trim($("#mainForm_orderCargoInfoDomain_cargoFlagTime").val());
		var url = jcontextPath+"/saveOrderCargoInfo";  
		var data = {"orderCargoInfoDomain.id":id
				,"orderCargoInfoDomain.cargoName":cargoName
				,"orderCargoInfoDomain.cargoType":cargoType
				,"orderCargoInfoDomain.cargoWeight":cargoWeight
				,"orderCargoInfoDomain.cargoCubage":cargoCubage
				,"orderCargoInfoDomain.requestCarLength":requestCarLength
				,"orderCargoInfoDomain.requestCarPlateType":requestCarPlateType
				,"orderCargoInfoDomain.requestCarBarType":requestCarBarType
				,"orderCargoInfoDomain.freight":freight
				,"orderCargoInfoDomain.requestStartTime":requestStartTime
				,"orderCargoInfoDomain.requestEndTime":requestEndTime
				,"orderCargoInfoDomain.startProvince":startProvince
				,"orderCargoInfoDomain.startCity":startCity
				,"orderCargoInfoDomain.startCounty":startCounty
				,"orderCargoInfoDomain.startTown":startTown
				,"orderCargoInfoDomain.endProvince":endProvince
				,"orderCargoInfoDomain.endCity":endCity
				,"orderCargoInfoDomain.endCounty":endCounty
				,"orderCargoInfoDomain.endTown":endTown
				,"orderCargoInfoDomain.contactName":contactName
				,"orderCargoInfoDomain.contactMobilephone":contactMobilephone
				,"orderCargoInfoDomain.contactTelephone":contactTelephone
				,"orderCargoInfoDomain.remark":remark
				,"orderCargoInfoDomain.deletedFlag":deletedFlag
				,"orderCargoInfoDomain.deployUserid":deployUserid
				,"orderCargoInfoDomain.modifyUserid":modifyUserid
				,"orderCargoInfoDomain.companyId":companyId
				,"orderCargoInfoDomain.cargoFlag":cargoFlag
				,"orderCargoInfoDomain.cargoFlagTime":cargoFlagTime
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
		if(text.orderCargoInfoDomain.id=="error"){
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
		var cargoName = trim($("#mainForm_orderCargoInfoDomain_cargoName").val());
		var cargoType = trim($("#mainForm_orderCargoInfoDomain_cargoType").val());
		var cargoWeight = trim($("#mainForm_orderCargoInfoDomain_cargoWeight").val());
		var cargoCubage = trim($("#mainForm_orderCargoInfoDomain_cargoCubage").val());
		var requestCarLength = trim($("#mainForm_orderCargoInfoDomain_requestCarLength").val());
		var requestCarPlateType = trim($("#mainForm_orderCargoInfoDomain_requestCarPlateType").val());
		var requestCarBarType = trim($("#mainForm_orderCargoInfoDomain_requestCarBarType").val());
		var freight = trim($("#mainForm_orderCargoInfoDomain_freight").val());
		var requestStartTime = trim($("#mainForm_orderCargoInfoDomain_requestStartTime").val());
		var requestEndTime = trim($("#mainForm_orderCargoInfoDomain_requestEndTime").val());
		var startProvince = trim($("#mainForm_orderCargoInfoDomain_startProvince").val());
		var startCity = trim($("#mainForm_orderCargoInfoDomain_startCity").val());
		var startCounty = trim($("#mainForm_orderCargoInfoDomain_startCounty").val());
		var startTown = trim($("#mainForm_orderCargoInfoDomain_startTown").val());
		var endProvince = trim($("#mainForm_orderCargoInfoDomain_endProvince").val());
		var endCity = trim($("#mainForm_orderCargoInfoDomain_endCity").val());
		var endCounty = trim($("#mainForm_orderCargoInfoDomain_endCounty").val());
		var endTown = trim($("#mainForm_orderCargoInfoDomain_endTown").val());
		var contactName = trim($("#mainForm_orderCargoInfoDomain_contactName").val());
		var contactMobilephone = trim($("#mainForm_orderCargoInfoDomain_contactMobilephone").val());
		var contactTelephone = trim($("#mainForm_orderCargoInfoDomain_contactTelephone").val());
		var remark = trim($("#mainForm_orderCargoInfoDomain_remark").val());
		var deletedFlag = trim($("#mainForm_orderCargoInfoDomain_deletedFlag").val());
		var deployUserid = trim($("#mainForm_orderCargoInfoDomain_deployUserid").val());
		var modifyUserid = trim($("#mainForm_orderCargoInfoDomain_modifyUserid").val());
		var companyId = trim($("#mainForm_orderCargoInfoDomain_companyId").val());
		var cargoFlag = trim($("#mainForm_orderCargoInfoDomain_cargoFlag").val());
		var cargoFlagTime = trim($("#mainForm_orderCargoInfoDomain_cargoFlagTime").val());
		if(cargoName==null||cargoName.length>50||cargoName.length==0){   
               alert("���������ַ���Χ0��50");  
              return false;   
         }
		if(cargoType==null||cargoType.length>50||cargoType.length==0){   
               alert("���������ַ���Χ0��50");  
              return false;   
         }
		if(cargoWeight==null||cargoWeight.length>50||cargoWeight.length==0){   
               alert("����������ַ���Χ0��50");  
              return false;   
         }
		if(cargoCubage==null||cargoCubage.length>50||cargoCubage.length==0){   
               alert("���������ַ���Χ0��50");  
              return false;   
         }
		if(requestCarLength==null||requestCarLength.length>50||requestCarLength.length==0){   
               alert("����Ҫ�󣨳������ַ���Χ0��50");  
              return false;   
         }
		if(requestCarPlateType==null||requestCarPlateType.length>50||requestCarPlateType.length==0){   
               alert("��-ƽ�塢�ߵͰ��ַ���Χ0��50");  
              return false;   
         }
		if(requestCarBarType==null||requestCarBarType.length>50||requestCarBarType.length==0){   
               alert("����Ҫ�󣨳� �����ַ���Χ0��50");  
              return false;   
         }
		if(freight==null||freight.length>50||freight.length==0){   
               alert("�����˷Ѽ۸��ַ���Χ0��50");  
              return false;   
         }
		if(requestStartTime==null||requestStartTime.length>50||requestStartTime.length==0){   
               alert("Ҫ��װ��ʱ���ַ���Χ0��50");  
              return false;   
         }
		if(requestEndTime==null||requestEndTime.length>50||requestEndTime.length==0){   
               alert("Ҫ�󵽻�ʱ���ַ���Χ0��50");  
              return false;   
         }
		if(startProvince==null||startProvince.length>50||startProvince.length==0){   
               alert("װ����-ʡ�ַ���Χ0��50");  
              return false;   
         }
		if(startCity==null||startCity.length>50||startCity.length==0){   
               alert("װ����-���ַ���Χ0��50");  
              return false;   
         }
		if(startCounty==null||startCounty.length>50||startCounty.length==0){   
               alert("װ����-���ַ���Χ0��50");  
              return false;   
         }
		if(startTown==null||startTown.length>50||startTown.length==0){   
               alert("װ����-�Զ����ַ�ַ���Χ0��50");  
              return false;   
         }
		if(endProvince==null||endProvince.length>50||endProvince.length==0){   
               alert("ж����-ʡ�ַ���Χ0��50");  
              return false;   
         }
		if(endCity==null||endCity.length>50||endCity.length==0){   
               alert("ж����-���ַ���Χ0��50");  
              return false;   
         }
		if(endCounty==null||endCounty.length>50||endCounty.length==0){   
               alert("ж����-���ַ���Χ0��50");  
              return false;   
         }
		if(endTown==null||endTown.length>50||endTown.length==0){   
               alert("ж����-�Զ����ַ�ַ���Χ0��50");  
              return false;   
         }
		if(contactName==null||contactName.length>50||contactName.length==0){   
               alert("��ϵ���ַ���Χ0��50");  
              return false;   
         }
		if(contactMobilephone==null||contactMobilephone.length>50||contactMobilephone.length==0){   
               alert("�ֻ����ַ���Χ0��50");  
              return false;   
         }
		if(contactTelephone==null||contactTelephone.length>50||contactTelephone.length==0){   
               alert("�̶��绰�ַ���Χ0��50");  
              return false;   
         }
		if(remark==null||remark.length>50||remark.length==0){   
               alert("��ע�ַ���Χ0��50");  
              return false;   
         }
		if(deletedFlag==null||deletedFlag.length>50||deletedFlag.length==0){   
               alert("ɾ��״̬�ַ���Χ0��50");  
              return false;   
         }
		if(deployUserid==null||deployUserid.length>50||deployUserid.length==0){   
               alert("�����û�ID�ַ���Χ0��50");  
              return false;   
         }
		if(modifyUserid==null||modifyUserid.length>50||modifyUserid.length==0){   
               alert("�޸��û�ID�ַ���Χ0��50");  
              return false;   
         }
		if(companyId==null||companyId.length>50||companyId.length==0){   
               alert("��ҵID�ַ���Χ0��50");  
              return false;   
         }
		if(cargoFlag==null||cargoFlag.length>50||cargoFlag.length==0){   
               alert("��Դ״̬�ַ���Χ0��50");  
              return false;   
         }
		if(cargoFlagTime==null||cargoFlagTime.length>50||cargoFlagTime.length==0){   
               alert("״̬�޸�ʱ���ַ���Χ0��50");  
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
<s:form id="mainForm" action="/saveOrderCargoInfo" namespace="/" method="post">
<s:hidden name="orderCargoInfoDomain.id"></s:hidden>
	  <table class="mxTable"  >  
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�������ƣ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoName" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�������ͣ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoType" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�����������</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoWeight" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����������</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoCubage" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����Ҫ�󣨳�������</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.requestCarLength" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��-ƽ�塢�ߵͰ壺</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.requestCarPlateType" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>����Ҫ�󣨳� ������</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.requestCarBarType" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�����˷Ѽ۸�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.freight" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>Ҫ��װ��ʱ�䣺</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.requestStartTime" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>Ҫ�󵽻�ʱ�䣺</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.requestEndTime" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>װ����-ʡ��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.startProvince" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>װ����-�У�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.startCity" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>װ����-�أ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.startCounty" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>װ����-�Զ����ַ��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.startTown" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>ж����-ʡ��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.endProvince" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>ж����-�У�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.endCity" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>ж����-�أ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.endCounty" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>ж����-�Զ����ַ��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.endTown" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ϵ�ˣ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.contactName" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�ֻ��ţ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.contactMobilephone" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�̶��绰��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.contactTelephone" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ע��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.remark" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>ɾ��״̬��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.deletedFlag" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�����û�ID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.deployUserid" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�޸��û�ID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.modifyUserid" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ҵID��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.companyId" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��Դ״̬��</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoFlag" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>״̬�޸�ʱ�䣺</td> 
              <td width="35%" align="left">   
             <s:textfield name="orderCargoInfoDomain.cargoFlagTime" style="border:1px solid #999;width:200px; height:20px; " type="text"  ></s:textfield>  
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
