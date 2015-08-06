<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>客户单位维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript">
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		
		//行政区划input下拉
		initXzqhInputSel();
		
		$("#mainForm_domain_khmc").focus();
		
		var khlx=$("#mainForm_domain_khlxDm").val();
		if(khlx==1){
			$(":radio[name='khlx']")[0].checked=true;
		}
		else if(khlx==2){
			$(":radio[name='khlx']")[1].checked=true;
		}
		else{
			$(":radio[name='khlx']")[1].checked=true;
		}
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			if(!checkKh()){
				showError("请选择客户类型");
				return false;
			}
			saveData();
		});
		
	});
	
	function checkKh(){
		var tag=false;
		var kh=$(":radio[name='khlx']");
		for(var i=0;i<kh.length;i++){
			if(kh[i].checked){
				tag=true;
			}
		}
		return tag;
	}
	
	function checkdata(){
		var controlNameArray = ["domain.khmc","domain.khjc",
		                        "domain.xzqhDm","domain.dz","domain.dh",
		                        "domain.fzr","domain.yb","domain.bz"];
		var labelNameArray = ["客户名称","客户简称",
		                      "行政区划","地址","电话",
		                      "负责人","邮编","备注"];
		var compareValueArray = [100,100,
			                     20,100,40,
			                     40,10,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,
                            false,false,false,
                            false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}


	function saveData(){
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var khmc = trim($("#mainForm_domain_khmc").val()); 
			var khjc = trim($("#mainForm_domain_khjc").val()); 
			
			var xzqhDm = trim($("#mainForm_domain_xzqhDm").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var khlx=$(":radio[name='khlx']")[0].checked ? "1":"2";
			var jsfs=$("#mainForm_domain_ykjsfsDm").val();
			
			var xxgxfsDm = $("#mainForm_domain_xxgxfsDm").val();
			var khbm=$("#mainForm_domain_khbm").val();
			var url = jcontextPath+"/zygl/qykhdjxx!save";  
	    	var jsonObj = {"domain.khDjxh":khDjxh,"domain.ssJgbm":ssJgbm,"domain.khmc":khmc,"domain.khjc":khjc,
                           "domain.xzqhDm":xzqhDm,"domain.dz":dz,"domain.dh":dh,"domain.khbm":khbm,
                           "domain.yb":yb,"domain.fzr":fzr,"domain.bz":bz,"domain.khlxDm":khlx,"domain.ykjsfsDm":jsfs,"domain.xxgxfsDm":xxgxfsDm};   			
			ajaxCommon(url,jsonObj,"saveOk");
	}
	
	function saveOk(data){
		var callOpenWinFun = $("#mainForm_domain_callOpenWinFun").val();
		if (callOpenWinFun != "") {
			var khDjxh = data.domain.khDjxh;
			var khmc = $("#mainForm_domain_khmc").val()
			//window.dialogArguments.initKhxxFromKxdj(khDjxh,khmc);
			try{
			eval("window.dialogArguments."+callOpenWinFun+"(khDjxh,khmc);");
			}catch(e){
	        	//alert("not function:"+e); 
	       	}
		}
		showSuccess("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
	function initXzqhData(width){
		var url = jcontextPath+"/zygl/qykhdjxx!queryXzqhList";	
		var targetObjName="domain.xzqhMc";
		var targetDmObjName="domain.xzqhDm";
		var dropDownSelectedCallback="onDropDownSelected";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":true,"width":width};
		initData(url,jsonObj);
	}
	
	function onDropDownSelected(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_jsonData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xzqhQc){
	        		$("#mainForm_domain_xzqhDm").val(item.xzqhDm);
		        	return;
	        	}
	    	});
		}
	}
	
	function initXzqhInputSel() {
		var url = jcontextPath+"/hygl/hycommondown!queryXzqhInputSel";
		var jsonObj = {};
		ajaxCommon(url, jsonObj,"initXzqhInputSelSuc");
	}

	function initXzqhInputSelSuc(data) {
		var ul = '<ul class="inputseltab">' +
                  '<li id="area1" onClick="setTab(\'area\',1,3)" class="current">省份</li>' +
                  '<li id="area2" onClick="setTab(\'area\',2,3)">城市</li>' +
                  '<li id="area3" onClick="setTab(\'area\',3,3)">县城</li>' +
                '</ul>';
		$("#inputSel_xzqh").html(ul + data.domain.xzqhInputSelHtml + $("#inputSel_xzqh").html());
		//setTab('area',1,7);
	}
</script>
</head>

<body>
<%
try {
%>
<s:form action="qykhdjxx!initMx" namespace="/zygl" method="post"
	id="mainForm" name="mainForm">
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.callOpenWinFun"></s:hidden>
	
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	<s:hidden name="targetObjName"></s:hidden>
	<s:hidden name="targetDmObjName"></s:hidden>
	<s:hidden name="itemIndex"></s:hidden>
	<s:hidden name="dropDownSelectedCallback"></s:hidden>

	<s:hidden name="domain.khlxDm"></s:hidden>
	
	<div id="maincont">
	<div class="pop_contc" >
	<fieldset>
	<legend>基本信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		
		<tr>
			<td width="15%" align="right"><font class="font_red">*</font>客户名称：
			</td>
			<td width="35%"><s:textfield name="domain.khmc"
				cssClass="pop_input noborder bgstyle_required"></s:textfield></td>
			<td align="right"><font class="font_red">*</font>客户简称：
			</td>
			<td><s:textfield name="domain.khjc"
				cssClass="pop_input bgstyle_required"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">行政区划：</td>
			<td>
			<s:hidden name="domain.xzqhDm"></s:hidden>
			<div class="inputsel" style="width:210px;">
  				<s:textfield name="domain.xzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 180px;"></s:textfield>
  				<a href="#" class="icon_arrow" id="xzqh" xzqh="xzqh" onFocus="this.blur()"></a>
		    </div>
			</td>
			<td align="right">客户编码：</td>
			<td><s:textfield name="domain.khbm"
				cssClass="pop_input bgstyle_optional"></s:textfield>
			</td>
		</tr>
		<tr>
			<td align="right">负责人：</td>
			<td><s:textfield name="domain.fzr"
				cssClass="pop_input bgstyle_optional"></s:textfield></td>
			<td align="right">地址：</td>
			<td>
				<s:textfield name="domain.dz"
				cssClass="pop_input bgstyle_optional"></s:textfield>
			</td>
		</tr>
		<tr>
			<td align="right">电话：</td>
			<td><s:textfield name="domain.dh"
				cssClass="pop_input bgstyle_optional"></s:textfield></td>
			<td align="right">邮编：</td>
			<td><s:textfield name="domain.yb"
				cssClass="pop_input bgstyle_optional"></s:textfield></td>
		</tr>
		<tr>
			<td align="right"><font class="font_red">*</font>客户类型：</td>
			<td>
				<s:radio list="#{'1':'协议客户','2':'临时客户'}" name="khlx" theme="simple"></s:radio>
			</td>
			<td colspan="2"></td>
			<td align="right" style="display: none;">余款结算方式：</td>
			<td style="display: none;"><sys:Ykjsfs myName="domain.ykjsfsDm" myId="mainForm_domain_ykjsfsDm"></sys:Ykjsfs></td>
		</tr>
		<tr>
			<td align="right">客户共享：</td>
			<td colspan="3"><sys:Xxgxfs myName="domain.xxgxfsDm" myId="mainForm_domain_xxgxfsDm" myClass="select" /></td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td colspan="3"><s:textarea name="domain.bz" rows="3"
				cssClass="pop_textarea_colspan2"></s:textarea></td>
		</tr>
        <tr>
			<td width="15%" align="right">所属单位：
			</td>
			<td width="35%"><s:textfield name="domain.ssjgmc"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
			<td align="right" width="15%">登记部门：
			</td>
			<td width="35%"><s:textfield name="domain.djjgmc"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">登记人：
			</td>
			<td><s:textfield name="domain.djrmc"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
			<td align="right" width="15%">登记日期：
			</td>
			<td><s:textfield name="domain.djrq"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">创建人：
			</td>
			<td><s:textfield name="domain.cjrMc"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
			<td align="right" width="15%">创建日期：
			</td>
			<td><s:textfield name="domain.cjrq"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">修改人：
			</td>
			<td><s:textfield name="domain.xgrMc"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
			<td align="right" width="15%">修改日期：
			</td>
			<td><s:textfield name="domain.xgrq"
				cssClass="pop_input bgstyle_readonly"></s:textfield></td>
		</tr>
	</table>
	</fieldset>
	
	<div class="pop_btn">
	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
	&nbsp;
	<button type="button" class="pop_btnbg" onclick="window.close();" >关 闭</button>
	</div>
	</div>
	</div>
	<%@include file="/common/message.jsp"%>
</s:form>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
<%
		} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
%>
</body>
</html>
