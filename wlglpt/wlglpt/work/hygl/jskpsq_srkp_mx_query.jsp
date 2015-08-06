<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>收入开票申请查询</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
		initHykhData(300,'','',"jsonData","khMc","khDjxh");
		
		$("#queryBtn").click(function(){
			showMessage();
			$("#mainForm").attr("action", "jskpsq!querySrKpMx");
			$("#mainForm").submit();
		});
		
		$("#addBtn").click(function(){
			//var length=$("#thisTab tr").length;
			var xhs=$(":checkbox[name='xhs']");
			var kpsqDjxh=$("#mainForm_domain_kpsqDjxh").val();
			var ywDjxh=new Array();
			var je=new Array();
			var jsonStr="";
			var j=0;
			$.each(xhs,function(i,obj){
				if(obj.checked){
					var jeVal=$(".je")[i].innerText;
					var ywDjxhVal=obj.value;
					ywDjxh[j]=ywDjxhVal;
					je[j]=jeVal;
					j++;
				}
			})
			if(j==0){
				showError("请选择一个收入开票信息！");
				return;
			}
			jsonStr=getJqueryParamZdy(ywDjxh,"domain.ywDjxhStr");
			jsonStr+=getJqueryParamZdy(je,"domain.jeStr");
			var url=jcontextPath+"/hygl/jskpsq!savaSrKpMxTemp";
			var jsonObj={"domain.kpsqDjxh":kpsqDjxh};
			jsonStr+=jQuery.param(jsonObj);
			ajaxCommon(url,jsonStr,"doSaveYkpSuc",false);
		});
		
		$("#closeBtn").click(function(){
			window.close();
		})
		
		
	});
	
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&"; 
		});
		
		return data;
	}
	
	function doSaveYkpSuc(data) {
		hideMessage();
		var str=data.domain.ywDjxhs;
		window.returnValue=str;
		window.close();		
	}
	
	function onUpdateDzqd(kpsqmxDjxh){
		var existBz = trim($("#mainForm_domain_existBz").val()); 
		var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
		var url = jcontextPath+"/hygl/jskpsq!queryMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.existBz="+existBz+"&domain.kpsqmxDjxh="+kpsqmxDjxh+"&domain.dzQdXgbz=Y";
		
		window.showModalDialog(url,window,"dialogHeight:360px;dialogWidth:560px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
	}
	function checkdata(){
		var controlNameArray = ["domain.sqKpjeHj","domain.sqKprq","domain.bzsm"];
		var labelNameArray = ["申请开票金额","申请开票日期","备注说明"];
		var compareValueArray = [16.2,10,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	function initMx() {
		$("#mainForm").attr("action", "jskpsq!initMx");
		mainForm.submit();
	}
	
	function delKpsqDzQd() {
		var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
		var xhs = $(":checked[name='xhs'][value!='']");
		var existBz = trim($("#mainForm_domain_existBz").val()); 
		showMessage();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.kpsqmxDjxhs");
			var jsonObj = {"domain.kpsqDjxh":kpsqDjxh,"domain.existBz":existBz};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/jskpsq!deleteMx";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelKpsqDzQdxxSuc", false);
		}
	}
	
	function doDelKpsqDzQdxxSuc(){ 
        hideMessage();
        showAlert("删除成功！");
        initMx();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.kpsqDjxh"></s:hidden>
<s:hidden name="jsonData" />
		<div id="maincont" />
		<div class="pop_contc">
			<fieldset>
				<legend>检索信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="100" align="right">下单日期：</td>
	      				<td width="200">	      					
				          	<sys:dateFirstDLastMonthTag myClass="ymdate" myId="mainForm_domain_rqQ" myName="domain.rqQ"></sys:dateFirstDLastMonthTag>
				          	～
				          	<sys:dateCurrentDayTag myClass="ymdate" myId="mainForm_domain_rqZ" myName="domain.rqZ"></sys:dateCurrentDayTag>				          	
	      				</td>
	      				<td width="100" align="right">客户名称：</td>
	      				<td width="200">
	      					<s:hidden name="domain.khDjxh"></s:hidden>
							<div class="inputsel" style="width: 230px; ">
								<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield> 
								<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a></div>
							<div class="inputsc">
							<div id="inputSel_fhr"
								class="inputselcont inputselFixedSize ac_results"></div>
							</div>
	      				</td>
	      				<td>&nbsp;</td>
	      			</tr>
				</table>
			</fieldset>
			<div class="pop_btn">
				<button type="button" class="pop_btnbg" id="queryBtn">检 索</button>
			 	&nbsp;
				<button type="button" class="pop_btnbg" id="addBtn">确定</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>			
			<table id="zbTab" width="850" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">序号</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="80">收入开票金额</th>
		        <th width="160">运费结算方名称</th>
		        <th width="80">科目小类名称</th>
		        <th width="85">来源</th>
		        <th width="85">产生日期</th>
		        <th width="320">说明</th>
		       
		      </tr>
		      <tbody id="thisTab">
		      <s:iterator value="domain.srKpList" id="dzqd" status="sta">
		        <tr>
			      	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#dzqd.ysyfDjxh" />" /></td>
			        <td align="center" class="je">
				        <s:property  value="#dzqd.ysfJe"/>
			        </td>
			        <td align="center"><s:property value="#dzqd.khMc" /></td>
			        <td align="center"><s:property value="#dzqd.kmxlMc" /></td>
			        <td align="center"><s:property value="#dzqd.ysyflyMc" /></td>
			        <td align="center"><s:property value="#dzqd.csrq" /></td>
			        <td align="center"><s:property value="#dzqd.sm" /></td>
			      </tr>
		      </s:iterator>
		      </tbody>
		    </table>			
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
