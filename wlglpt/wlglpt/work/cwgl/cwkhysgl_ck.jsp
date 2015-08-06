<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-客户预收收入查看明细</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#yhShow").hide();
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#deleteBtn").click(function(){
			deleteYsMx();
		})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var khMc=$("#mainForm_domain_khMc").val();
			var je = trim($("#mainForm_domain_je").val()); 
			var srDjxh = trim($("#mainForm_domain_srDjxh").val()); 
			var czJe = trim($("#mainForm_domain_czJe").val()); 
		 	if(czJe==""){
				showError("充值金额不能为空！");
				$("#mainForm_domain_czJe").select();
				$("#mainForm_domain_czJe").focus();
				return;
			} 
			je=parseInt(je)+parseInt(czJe);
			var zffs = trim($("#mainForm_domain_zffsDm").val()); 
			var zcfl = trim($("#mainForm_domain_zcflDm").val()); 
			var jbrCzyDjxh = trim($("#mainForm_domain_jbrCzyDjxh").val()); 
			var rq = trim($("#mainForm_domain_rq").val()); 
			var yhCshDjxh=$("#mainForm_domain_yhCshDjxh").val();
			if(zffs!="2"){
				yhCshDjxh="";
			}
			var yhhdh=$("#mainForm_domain_yhhdh").val();
			var bz=$("#mainForm_domain_bz").val();
			var url = jcontextPath+"/khyslr!save";  
	    	var jsonObj = {"domain.khDjxh":khDjxh,"domain.je":je,
                           "domain.srDjxh":srDjxh,"domain.czJe":czJe,"domain.zffs":zffs,"domain.zcfl":zcfl,"domain.jbrCzyDjxh":jbrCzyDjxh,
                           "domain.jbRq":rq,"domain.yhCshDjxh":yhCshDjxh,"domain.yhhdh":yhhdh,"domain.bz":bz,"domain.khMc":khMc};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	
	function deleteYsMx(){
		var xhs=$(":checked[name='xhs'][value!='']");
		if(xhs.length<=0){
			showAlert("请选择要删除的数据！");
			return;
		}
		showConfirm("确定要删除客户收入记录么？","toDelete");
	}
	function toDelete(){
	//	var je=$("#mainForm_domain_je").val();
		var srDjxh=$("#mainForm_domain_srDjxh").val();
		var xhs=$(":checkbox[name='xhs']");
		var url=jcontextPath+"/khyslr!deleteKhMx";
		var str="";
		for(var i=0;i<xhs.length;i++){
			if(xhs[i].checked){
				var je=$(".je")[i].innerText;
				str+=xhs[i].value+","+srDjxh+","+je+"=";
			}
			
		}
		var json={"domain.jsonStr":str};
		ajaxCommon(url,json,"deleteOk");
	}
	var obj;
	function deleteOk(data){
		obj=data;
		showSuccess("删除成功！","refreshTab");
	}
	
	function refreshTab(){
		var check=$(":checkbox[name='xhs']");
		var ary=obj.domain.xhs.split("-");
		var je=obj.domain.je;
		for(var j=0;j<ary.length;j++){
				for(var i=0;i<check.length;i++){
					if(ary[j]==''){
						continue;
					}
					if(check[i].value==ary[j]){
						var td = $(check[i]).parent();
						var tr = $(td).parent();
						$(tr).remove();
					}
				}
		}
			
		initJe(je);
		initIndex();
	}
	function initJe(je){
		$("#mainForm_domain_je").val(je);
	}
	
	function initIndex(){
		var length=$("#tab1 tr").length;
		var index=0;
		for(var i=0;i<length;i++){
			index++;
			$(".index")[i].innerHTML=index;
		}
	}
	
	function checkJs(obj){
		$(":checkbox[name='xhs']").attr("checked",obj.checked);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.czJe","domain.zffsDm",
		                        "domain.zcflDm","domain.jbrCzyDjxh","domain.rq",
		                        "domain.yhCshDjxh","domain.yhhdh"];
		var labelNameArray = ["充值金额","支付方式",
		                      "资产分类","经办人","经办日期","银行账号","银行回单号"];
		var compareValueArray = [14.2,20,
			                     20,20,20,20,20];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING
		                     ,NodeType.STRING];
		var notNullArray = [true,true,
                            true,true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function onCheck1(){
		var zffs=$("#mainForm_domain_zffsDm").val();
		$("#mainForm_domain_zcflDm").val("1"+zffs);
		if(zffs=="2"){
			$("#yhShow").show();
		}
		else{
			$("#yhShow").hide();
		}
	}
	function onCheck2(){
		var zcfl=$("#mainForm_domain_zcflDm").val();
		$("#mainForm_domain_zffsDm").val(zcfl.charAt(1));
		if(zcfl.charAt(1)=="2"){
			$("#yhShow").show();
		}
		else{
			$("#yhShow").hide();
		}
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwkhysgl!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_srDjxh" value='<s:property value="domain.srDjxh"/>'/>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<div id="maincont">
		<div class="pop_contc" style="height:360px;">
			<fieldset>
			<legend>客户基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				     	<td width="15%" align="right">客户名称：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.khMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">余额：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.je" cssClass="pop_input bgstyle_readonly"  ></s:textfield>
				     	 </td>
				    </tr>
				    </table>
			</fieldset>
			<div style="width: 100%;overflow-x:auto;padding: 5px 0 20px 0;overflow-y:hidden;">
			<table id="zTab" width="1200px" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="25px">序号</th>
			        <th width="25px"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
			        <th width="90px">客户名称</th>
			        <th width="50px">金额</th>
			        <th width="55px">支付方式</th>
			        <th width="55px">资产分类</th>		
			        <th width="100px">银行名称</th>
			        <th width="80px">银行账号</th>
			        <th width="70px">银行回单号</th>	        
			        <th width="70px">经办人</th>
			        <th width="70px">日期</th>
			       
			       
			        <th width="200px">备注</th>
			      </tr>
			      <tbody id="tab1">
			      <s:iterator id="zb" value="domain.mxList" status="i">
			      	<tr>
				        <td align="center" class="index"><s:property value="#i.index+1"/></td>
				        <td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.mxXh" />"  <s:if test="#zb.czxfBz==2">disabled="disabled"</s:if> /></td>
				        <td align="center"><s:property value="#zb.khmc"/></td>
				        <td align="center" class="je"><s:property value="#zb.je"/></td>
				        <td align="center"><s:property value="#zb.zffsMc"/></td>
				        <td align="center"><s:property value="#zb.zcflMc"/></td>
				        <td align="center"><s:property value="#zb.yhmc"/></td>
				        <td align="center"><s:property value="#zb.yhzh"/></td>
				        <td align="center"><s:property value="#zb.yhhdh"/></td>
				        <td align="center"><s:property value="#zb.jbrCzyDjmc"/></td>
				       <td align="center"><s:property value="#zb.jbrq"/></td>
				       <td align="center"><s:property value="#zb.bz"/></td>
				     </tr>
			      </s:iterator>
			      </tbody>
    		</table>
    		</div>
			
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="deleteBtn">删除</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
