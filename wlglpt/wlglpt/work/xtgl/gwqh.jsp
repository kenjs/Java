<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��λ�л�</title>
<style type="text/css">
html,body {overflow:hidden;}
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#messageCloseBtn").click(function(){
			window.close();
		})
		$("#sureBtn").click(function(){
		    var gwDjxh = $('input:radio[name="gwxh"]:checked').val();
		    if(gwDjxh == null||gwDjxh == ""){
		        showAlert("��ѡ���λ��");
		        return;
		    }
	        if(gwDjxh == ${session.userInfos.gwDjxh}){
	           window.close();
	        }else{
	            showConfirm("ȷ��Ҫ�л���λ��<br/>���л������ر����д��ڣ�", "updateGw");
	        }
		})
		$("input[name='gwxh']").val([${session.userInfos.gwDjxh}]);
	})
	function onRadio(gwDjxh){
	   $("input[name='gwxh']").val([gwDjxh]); 
	}
	var gwDjxh = "";
    function onFinish(gwDjxh){
    gwDjxh = gwDjxh;
	  if(gwDjxh == ${session.userInfos.gwDjxh}){
	     window.close();
	  }else{
	      showConfirm("ȷ��Ҫ�л���λ��<br/>���л������ر����д��ڣ�", "updateGw");
	  }
    }
    function updateGw(){
        if(gwDjxh==""){
           gwDjxh = $('input:radio[name="gwxh"]:checked').val();
        }
        var url = jcontextPath+"/xtgl/login!gwqh";  
	    var jsonObj = {"loginUserDomain.gwDjxh":gwDjxh};
	    ajaxCommon(url,jsonObj,"onClose");
    }
	function onClose(data) {
	  var url = jcontextPath+"/xtgl/login!gwqh";  
	  var jsonObj = {"loginUserDomain.gwDjxh":gwDjxh};
	  var gwDjxh = data.loginUserDomain.gwDjxh;
	  var obj = new Object(); 
      var str = $('#'+gwDjxh).text();
      var strs = str.split("(");
      obj.name = strs[0];
	  window.returnValue =  obj;
	  window.close();
	}
</script>
</head>

<!-- body���onload���϶�iframe�õĲ����޸� -->
<body>
<s:form action="xtsz!init" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
<input type="hidden" name="savelx" id="savelxId" value="1"/>
<s:hidden name="domain.csxhs"></s:hidden>
<!-- �������ڱ�����ر�ͼ�� id="toolbar"Ϊ�϶�iframe�����õ��������޸� -->

<!-- ���������������� style������Ϊ�������ݸ߶ȣ������涨�ĸ߶��Զ������������߶�ֵ����ʵ�ʸı� -->

  <div class="pop_contc" style="height:350px; overflow:auto;">
  <fieldset>
   <legend>���л���λ�б�</legend>  
     <ul >
        <s:iterator id="zb" value="qyRyGwDomain.dataList" status="i">
            <li style="text-align: left;">
               <input type="radio" name="gwxh" value="<s:property value="#zb.gwDjxh"/>"/>
               <a href="javascript:onRadio(<s:property value="#zb.gwDjxh" />)" id="<s:property value="#zb.gwDjxh" />" ondblclick="onFinish(<s:property value="#zb.gwDjxh" />)">
                   <s:property value="#zb.gsmc"/> | <s:property value="#zb.bmmc"/> | <s:property value="#zb.gwmc"/>(<s:property value="#zb.zjbzStr"/>)
               </a>
            </li>
        </s:iterator>
      </ul>
 </fieldset>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg" id="sureBtn">ȷ��</button>
      &nbsp;
      <button type="button" class="pop_btnbg" id="messageCloseBtn">�� ��</button>
    </div>
    </div>

<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
