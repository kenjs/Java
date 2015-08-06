<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>ϵͳ��λά��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			var nu = $(":checkbox[name='xy']");
			var str = "";
			var xybz = "";
			for(var i=0;i<nu.length;i++){
				if(nu[i].checked){
					xybz="Y";
				}else{
					xybz="N";
				}
				var tRows = document.getElementById("zbTab").rows;
				var gwMc = tRows[i+1].cells[3].innerHTML; 
				var gwJc = tRows[i+1].cells[4].innerHTML; 
				var bzsm = tRows[i+1].cells[5].innerHTML; 
				var gwDm = tRows[i+1].cells[2].innerHTML;
				str += bzsm+","+gwMc+","+gwJc+","+gwDm+","+xybz+"|"; 
			}	
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var url = jcontextPath+"/xtgl/gwwh!saveXtgw";  
	    	var jsonObj = {"domain.str":str,"domain.ssJgbm":ssJgbm};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
		
		$('#checkedAll').click(function(){  
        
		//	if(this.checked){/*��Ĭ���Ƿ�Ϊѡ�н����ж�*/  
		//		$('[name=checkboxt]:checkbox').attr('checked',true);/*checkedΪtrueʱΪĬ����ʾ��״̬,Ϊtrueʵ��ȫѡ����*/  
		//	}else{  
		//		$('[name=checkboxt]:checkbox').attr('checked',false);/*falseΪ��ѡ��Ϊȫ��ѡ����*/  
		//	}  
		//	ʵ��ȫѡȫ��ѡ����һ�ַ���  
    		$('[name=xy]:checkbox').attr('checked',this.checked);/*checkedΪtrueʱΪĬ����ʾ��״̬*/  
          
      	});  
	});
	function doSuccess(){
		showAlert("����ɹ���","yesCallBack");
	}
	
	function yesCallBack(){
		window.close();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="gwwh!initXtgwMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	<div class="pop_contc" style="height:320px; overflow:auto;">
		<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
	      <tr>
	        <th width="8%"><input type="checkbox" title="ȫѡ/ȫ��ѡ" id="checkedAll"/></th>
	        <th width="5%">���</th>
	        <th width="12%">ϵͳ��λ����</th>
	        <th width="12%">��λ����</th>
	        <th width="15%">��λ���</th>
	        <th width="15%">��ע˵��</th>
	      </tr>
	      <s:iterator id="zb" value="domain.xtgwList" status="i">
	      	<tr>
		        <td align="center">
		        	<s:if test='#zb.xybz==\"Y\"'>
		        		<s:checkbox checked="true" name="xy"></s:checkbox>
		        	</s:if>
		        	<s:else>
		        		<s:checkbox name="xy"></s:checkbox>
		        	</s:else>
		        </td>
		        <td align="center"><s:property value="#i.index+1"/></td>
		        <td align="center"><s:property value="#zb.gwDm"/></td>
		        <td align="center"><s:property value="#zb.gwMc"/></td>
		        <td align="center"><s:property value="#zb.gwJc"/></td>
		        <td align="center"><s:property value="#zb.bzsm"/></td>
	      	</tr>
	      </s:iterator>
	    </table>
		<div class="pop_btn">
		 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	    </div>
	</div>
	
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
