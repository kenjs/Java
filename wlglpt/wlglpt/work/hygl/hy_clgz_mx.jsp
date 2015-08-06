<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>车辆跟踪</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var  pcDjxh= $("#mainForm_domain_pcDjxh").val(); 
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var rq = $("#mainForm_domain_rq").val();
			var sm = $("#mainForm_domain_sm").val(); 
			var xxdz =$("#mainForm_domain_xxdz").val(); 
			var szqyXzqhDm = $("#mainForm_domain_szqyXzqhDm").val();
			var yjDdrq = $("#mainForm_domain_yjDdrq").val();
			var url = jcontextPath+"/hygl/hyclgz!save";  
	        var jsonObj = {"domain.pcDjxh":pcDjxh,"domain.djJgbm":ssJgbm,"domain.rq":rq,"domain.sm":sm,"domain.xxdz":xxdz,"domain.szqyXzqhDm":szqyXzqhDm,"domain.yjDdrq":yjDdrq};
	        ajaxCommon(url,jsonObj,"YesSave");
		});
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("请先选择需要删除的跟踪记录！");
				return;
			}
			showConfirm("确定要删除选中的跟踪记录？", "delHyClgz");
		})
		//区划input下拉
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
	});
	
	function checkdata(){
		var controlNameArray = ["domain.rq","domain.sm","domain.szqyXzqhDm","domain.xxdz",
	    					   "domain.yjDdrq"];
		var labelNameArray = ["日期","说明","所在地址",
		                      "详细地址","预计到达日期"];
		var compareValueArray = [50,200,20,
			                     200,20];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function YesSave(){ 
        showAlert("保存成功！", "refresh");
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}

	function delHyClgz() {
		var xhs = $(":checked[name='xhs'][value!='']");
		//showMessage();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.clgzDjxhs");
			
			var url = jcontextPath+"/hygl/hyclgz!delete";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelHyClgzSuc", false);
		}
	}
	
	function doDelHyClgzSuc(){ 
        //hideMessage();
        showAlert("删除成功！", "refresh");
	}
	//刷新弹窗
	function refresh(){			
	   mainForm.submit();
	}
	function showSm(clgzDjxh){
	   var url = jcontextPath+"/hygl/hyclgz!showSm.action?domain.clgzDjxh="+clgzDjxh;
	   window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:400px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="hyclgz!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
        <div  style="display:none"><a id="reload" href="">reload</a></div>
		<s:hidden name="domain.pcDjxh"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<div id="maincont">
		<div class="pop_contc" style="height:520px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">派车单号：</td>
      				<td width="35%">
      					<s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">司机：</td>
      				<td width="35%">
      					<s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">车辆号码：</td>
      				<td>
      					<s:textfield name="domain.cyrClhm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">挂车号码：</td>
      				<td>
      					<s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">派车人：</td>
      				<td>
      					<s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">派车日期：</td>
      				<td>
      					<s:textfield name="domain.pcrq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">派车部门：</td>
      				<td>
      					<s:textfield name="domain.pcJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">业务单位：</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      		
			</table>
			</fieldset>
			<fieldset>
				<legend>跟踪记录登记</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				  <tr>
	  				<td align="right" width="15%"><font class="font_red">*</font>跟踪日期：</td>
	                <td width="20%"><input class="ymdatetime" id="mainForm_domain_rq" type="text" value="<s:property value="domain.rq"/>"/></td>
	  				<td align="right" width="15%">预计到达日期：</td>
	  				<td width="40%">
	  				    <sys:dateCurrentDayTag myName="domain.yjDdrq" myId="mainForm_domain_yjDdrq" myClass="ymdate" />
	  				</td>	
	  			 	
	  			 </tr>
	  			 <tr>
	  			    <td align="right" >当前所在区域：</td>
  				    <td align="left">
  					<s:hidden name="domain.szqyXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 190px;">
  						<s:textfield name="domain.szqyXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 160px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="szqyXzqh" onFocus="this.blur()"></a>
		            </div>
  				    </td>
	  				<td align="right">详细地址：</td>
	  				<td>
	  					<s:textfield name="domain.xxdz" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
	  				</td>
	  			 </tr>
	  			 <tr>
	  			 	<td align="right">跟踪情况说明：</td>
	  			 	<td colspan="3">
	  			 		<s:textarea name="domain.sm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
	  			 	</td>
	  			 </tr>
    		</table>
    		</fieldset>
    		<div style="width: 100%;overflow:auto;height: 180px;">
			<table id="zTab" width="1000px" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="margin-top: 10px;">
			      <tr>
			        <th width="3%">序号</th>
			        <th width="3%"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
			        <th width="15%">跟踪日期</th>
			        <th width="8%">预到日期</th>
			        <th width="10%">所在区域</th>
			        <th width="15%">详细地址</th>
			        <th width="25%">情况说明</th>
			        <th width="7%">登记部门</th>
			        <th width="7%">登记人</th>
			        <th width="7%">登记日期</th>
			        
			      </tr>
			      <s:iterator id="zb" value="domain.dataList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.clgzDjxh" />" /></td>
				        <td align="center"><s:property value="#zb.rq"/></td>
				        <td align="center"><s:property value="#zb.yjDdrq"/></td>
				        <td align="center"><s:property value="#zb.szqyXzqhMc"/></td>
				        <td align="center"><s:property value="#zb.xxdz"/></td>
				        <td align="center"><a href="javascript:showSm('<s:property value="#zb.clgzDjxh" />')"><font color="blue"><s:property value="#zb.sm"/></font></a></td>
				        <td align="center"><s:property value="#zb.djJgmc"/></td>
				        <td align="center"><s:property value="#zb.cjrCzyMc"/></td>
				        <td align="center"><s:property value="#zb.cjrq"/></td>
			      	</tr>
			      </s:iterator>
    		</table>
    		</div>
    		<div class="pop_btn" style="margin-top: 10px;">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="deleteBtn">删 除</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
         <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
