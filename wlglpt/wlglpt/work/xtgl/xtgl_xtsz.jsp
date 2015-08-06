<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ϵͳ����</title>
<style type="text/css">
html,body {overflow:hidden;}
html,body {background:none;}
</style>

<script type="text/javascript">
	function setTab(name,cursel,n){
		$("#savelxId").val(cursel);
		for(i=1;i<=n;i++){
	  		var menu=document.getElementById(name+i);
	 		var con=document.getElementById("popset_"+name+"_"+i);
	  		menu.className=i==cursel?"current":"";
	  		con.style.display=i==cursel?"block":"none";
	 	}
	}	
	
		$(function(){
		$("#messageCloseBtn").click(function(){
			window.close();
		})	
			
			
			
		$("#saveBtn").click(function(){
			var flag = $("#savelxId").val();
			if(flag == "1"){
				if(!checkdata()){
					return;
				}
				var pwd = $("#mainForm_domain_pwd").val(); 
				var pwdNew = $("#mainForm_domain_pwdNew").val(); 
				var pwdNew2 = $("#pwdNew2Id").val(); 
				if(pwdNew != pwdNew2){
					showAlert("����������ȷ��������ͬ������!");
					return;
				}
				var url = jcontextPath+"/xtgl/xtsz!save";  
		    	var jsonObj = {"domain.pwd":pwd,"domain.pwdNew":pwdNew,"domain.flag":flag}; 
		    }else if(flag == "2"){
				if(!checkdata2()){
					return;
				}
				var sjhm = $("#mainForm_domain_sjhm").val(); 
				var sjdh = $("#mainForm_domain_sjdh").val(); 
				var bgdh = $("#mainForm_domain_bgdh").val(); 
				var bgdhao = $("#mainForm_domain_bgdhao").val(); 
				var jtdh = $("#mainForm_domain_jtdh").val(); 
				var qq = $("#mainForm_domain_qq").val(); 
				var msn = $("#mainForm_domain_msn").val(); 
				var email = $("#mainForm_domain_email").val(); 
				var url = jcontextPath+"/xtgl/xtsz!save";  
		    	var jsonObj = {"domain.sjhm":sjhm,"domain.sjdh":sjdh,"domain.bgdh":bgdh,"domain.bgdhao":bgdhao,"domain.jtdh":jtdh,"domain.qq":qq,"domain.msn":msn,"domain.email":email,"domain.flag":flag}		    
		    }else if(flag == "3"){
		    	if(!checkdata3()){
					return;
				}
				var csxhs = $("#mainForm_domain_csxhs").val(); 
				var csmrz = document.getElementsByName("domain.csmrzs");
				var csmrzs = "";
				
				for(var i = 0 ;i<csmrz.length ;i++){
					if(csmrzs == ""){
						csmrzs = csmrz[i].value;
					}else{
						csmrzs = csmrzs + "," + csmrz[i].value;
					}					
				}
				var url = jcontextPath+"/xtgl/xtsz!save"; 
				var jsonObj = {"domain.csxhs":csxhs,"domain.csmrzs":csmrzs,"domain.flag":flag}; 
		    }    			
			ajaxCommon(url,jsonObj,"saveSucc");
		});
	});
		
		function saveSucc(){
			showSuccess("����ɹ���","saveAfter");
		}
		
		function saveAfter(){
			window.close();
		}
		
		
	
	<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "20";
		if (userDomain != null && userDomain.getRowNum() != null && !"".equals(userDomain.getRowNum().trim()))
			rowNum = userDomain.getRowNum();
	%>
	
	function checkdata(){
		var controlNameArray = ["domain.pwd",
		                        "domain.pwdNew","domain.pwdNew2"];
		var labelNameArray = ["ԭʼ����",
		                      "������","������ȷ��"];
		var compareValueArray = [100,
			                     100,100];
		var nodeTypeArray = [NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
                            true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkdata2(){
		var controlNameArray = ["domain.sjhm","domain.sjdh","domain.bgdh","domain.bgdhao",
		                        "domain.jtdh","domain.qq","domain.msn","domain.email"];
		var labelNameArray = ["�ֻ�����","�ֻ��̺�","�칫�绰","�칫�̺�","��ͥ�绰",
		                      "QQ����","MSN����","EMAIL��ַ"];
		var compareValueArray = [40,40,40,40,
			                     40,20,20,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [false,false,false,false,
                            false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkdata3(){
		var controlNameArray = ["domain.csmrzs"];
		var labelNameArray = ["����Ĭ��ֵ"];
		var compareValueArray = [100];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}

	function checkNum(str,id){
		var rs = /^[1-9]+[0-9]*]*$/;
		if(!rs.test(str)){
			showAlert("��¼�����֣�");
			$("#"+id).val("");
		}
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
		    <legend>������Ϣ</legend>
		    
    <div class="pop_setcont">
      <ul class="pop_setl">
        <li id="menu1" onclick="setTab('menu',1,3)" class="current">�����޸�</li>
        <li id="menu2" onclick="setTab('menu',2,3)">������Ϣ</li>
        <li id="menu3" onclick="setTab('menu',3,3)">��������</li>
      </ul>
      <div class="pop_setr">
        <div id="popset_menu_1">
   
          <table width="87%" style="height: 70px" border="0" align="center" cellpadding="0" cellspacing="0" class="poptab_css" >
            <tr>
              <td width="36%" height="35" align="center">ԭʼ���룺</td>
              <td width="64%">
              	<s:password name="domain.pwd" cssClass="pop_input  bgstyle_required" ></s:password>
              </td>
              
            </tr>
            <tr>
              <td height="35" align="center">�����룺</td>
              <td><s:password name="domain.pwdNew" cssClass="pop_input  bgstyle_required" ></s:password></td>
            </tr>
            <tr>
              <td height="35" align="center">������ȷ�ϣ�</td>
              <td><s:password name="domain.pwdNew2" id="pwdNew2Id" cssClass="pop_input  bgstyle_required" ></s:password></td>
            </tr>
          </table>

        </div>
        <div id="popset_menu_2" style="display:none;">
          <table width="97%" border="0" align="center" cellpadding="0" cellspacing="0" class="poptab_css">
            <tr>
              <td width="18%" height="35" align="left">�û����ƣ�</td>
              <td width="35%">
              	<s:textfield name="domain.mc" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
              </td>
              <td width="18%" height="35" align="right">�����ţ�</td>
              <td width="35%">
              	<s:textfield name="domain.zbm" cssClass="pop_input  bgstyle_readonly" ></s:textfield>
              </td>              
            </tr>
            <tr>
              <td width="15%" height="35" align="left">�ֻ����룺</td>
              <td width="35%">
              	<s:textfield name="domain.sjhm" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>
              <td width="15%" height="35" align="right">�ֻ��̺ţ�</td>
              <td width="35%">
              	<s:textfield name="domain.sjdh" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>              
            </tr>
            <tr>
              <td width="15%" height="35" align="left">�칫���룺</td>
              <td width="35%">
              	<s:textfield name="domain.bgdh" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>
              <td width="15%" height="35" align="right">�칫�̺ţ�</td>
              <td width="35%">
              	<s:textfield name="domain.bgdhao" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>              
            </tr>
            <tr>
              <td width="15%" height="35" align="left">��ͥ�绰��</td>
              <td width="35%">
              	<s:textfield name="domain.jtdh" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>
              <td width="15%" height="35" align="right">QQ��</td>
              <td width="35%">
              	<s:textfield name="domain.qq" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>              
            </tr>
            <tr>
              <td width="15%" height="35" align="left">MSN��</td>
              <td width="35%">
              	<s:textfield name="domain.msn" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>
              <td width="15%" height="35" align="right">EMAIL��</td>
              <td width="35%">
              	<s:textfield name="domain.email" cssClass="pop_input  bgstyle_optional" ></s:textfield>
              </td>              
            </tr>
          </table>
		</div>
        <div id="popset_menu_3" style="display:none;">
        	<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0" class="poptab_css">
        		<thead>
        			<tr>
	        			<th align="center" width="10%">���</th>
	        			<th align="center" width="55%">��������</th>
	        			<th align="center" width="35%">����ֵ</th>
	        		</tr>
        		</thead>
        		<tbody>
			        <s:iterator value="domain.dataList" id="data" status="st">
			        	<tr>
							<td align="center"><s:property value="#st.index+1" /></td>
							<td align="left"><s:property value="#data.csmc" /></td>
							<s:if test="#data.sjxlbDm == '21'">
								<td align="left">
									
									<s:select list="#data.xzxmList" name="domain.csmrzs" value="#data.csmrz" cssStyle="width:150px;" listKey="xzxmValueDm" listValue="xzxmValueMc"></s:select>
									
								</td>
							</s:if>
							<s:elseif test="#data.sjxlbDm == '23'">
								<td>
									
								<s:select list="#data.xzxmList" name="domain.csmrzs" value="#data.csmrz" cssStyle="width:150px;" listKey="xzxmValueDm" listValue="xzxmValueMc"></s:select></td>
							</s:elseif>
							<s:elseif  test="#data.sjxlbDm == '12'">
								<td>
										<input type="text" name="domain.csmrzs" value="${data.csmrz}" id="csmrId<s:property value="#st.index+1" />" onchange="checkNum(this.value,this.id);" class="pop_input_colspan2  bgstyle_required"/>
								</td>
							</s:elseif>
						</tr>
			        </s:iterator>
	        	</tbody>
        	</table>
        </div>
      </div>
    </div>
 </fieldset>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
      &nbsp;
      <button type="button" class="pop_btnbg" id="messageCloseBtn">�� ��</button>
    </div>
    </div>

<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
