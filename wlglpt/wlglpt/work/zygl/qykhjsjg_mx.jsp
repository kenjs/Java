<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>结算价格维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		//行政区划input下拉
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		
		//初始化单选按钮
		var fw=$("#mainForm_domain_syfw").val();
		var khDjxh=$("#mainForm_domain_khDjxh").val();
		var radio=$(":radio[name='fanwei']");
		if(fw=='Y'){
			radio[0].checked=true;
			commonInit('kh-hw', 0, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
		}
		else if(fw=='N'){
			radio[1].checked=true;
			commonInit('kh-hw', khDjxh, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
		}
		else{
			radio[0].checked=true;
			commonInit('kh-hw', 0, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
		}
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			if(!checkHw()){
				return;
			}
			saveDataBefore();
		});
		
		var dm=$("#mainForm_domain_jldwFlDm").val();
		commonInit('fl-dw',dm, '', 'domain.jldwDm', 'mainForm_domain_jldwDm', 'Y', 'Y');
		$("#mainForm_domain_jldwFlDm").change(function(){
			commonInit('fl-dw', $(this).val(), '', 'domain.jldwDm', 'mainForm_domain_jldwDm', 'Y', 'Y');
		})
		
		$("#getCs").click(function(){
			   
			   var qtypejs = document.getElementById("mainForm_domain_cs");  
			   var index = qtypejs.selectedIndex;
			   var txtjs = qtypejs.options[index].text;  
			   var text=txtjs.split(" ");
			   var jsgs=$("#mainForm_domain_jgjsgs").val();
			   var str=jsgs+"#"+text[0];
			   $("#mainForm_domain_jgjsgs").val(str);
		
		})
			
			var dm=$("#mainForm_domain_hwDjxh").val();
			commonInit('fl-dw',dm, '', 'domain.hwxhDjxh', 'mainForm_domain_hwxhDjxh', 'Y', 'Y');
			$("#mainForm_domain_hwDjxh").change(function(){
				commonInit('qyhw-xh', $(this).val(), '', 'domain.hwxhDjxh', 'mainForm_domain_hwxhDjxh', 'Y', 'Y');
			})
		
	});
	
	
	
	
	function checkdata(){
		var controlNameArray = ["domain.fhrXzqhDm","domain.shrXzqhDm",
		                        "domain.lcs","domain.ddts",
		                        "domain.jgjsgs","domain.xtgs","domain.jldwDm","domain.yxqQ"];
		var labelNameArray = ["始发地","目的地",
		                      "里程数","到达天数",
		                      "价格计算公式","系统公式","计量单位","有效时间起"];
		var compareValueArray = [100,100,
		                         16.2,6.2,
			                     200,200,20,50];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
		                     NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,
                            true,true,
                            true,true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}


	function saveDataBefore(){
		var url = jcontextPath+"/zygl/qykhjsjg!saveCheck";  
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var hwDjxh = trim($("#mainForm_domain_hwDjxh").val()); 
		var hwxhDjxh = trim($("#mainForm_domain_hwxhDjxh").val()); 
		var fhr = trim($("#mainForm_domain_fhrXzqhDm").val()); 
		var shr = trim($("#mainForm_domain_shrXzqhDm").val()); 
		var yxqq = trim($("#mainForm_domain_yxqq").val()); 
		var yxqz = trim($("#mainForm_domain_yxqz").val()); 
		var jsjgDjxh = trim($("#mainForm_domain_jsjgDjxh").val()); 
		var jsonObj = {"domain.khDjxh":khDjxh,"domain.ssJgbm":ssJgbm,"domain.hwDjxh":hwDjxh,
                "domain.fhrXzqhDm":fhr,"domain.shrXzqhDm":shr,
                "domain.yxqQ":yxqq,"domain.yxqZ":yxqz,"domain.hwxhDjxh":hwxhDjxh,"domain.jsjgDjxh":jsjgDjxh};   			
	ajaxCommon(url,jsonObj,"toSave");
	}
	
	function toSave(data){
		var tager=data.domain.tager;
		if(tager=="1"){
			showError("该客户条件下的结算价格已经维护！");
			return;
		}
		saveData();
	}
	
	function saveData(){
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var hwDjxh = trim($("#mainForm_domain_hwDjxh").val()); 
			var hwxhDjxh = trim($("#mainForm_domain_hwxhDjxh").val()); 
			var fw=$(":radio[name='fanwei']")[0].checked ? 'Y':'N';
			var fhr = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var jgsm=trim($("#mainForm_domain_jgsm").val());
			var shr = trim($("#mainForm_domain_shrXzqhDm").val()); 
			var lcs = trim($("#mainForm_domain_lcs").val()); 
			var ddtc = trim($("#mainForm_domain_ddts").val()); 
			var fl  = trim($("#mainForm_domain_jldwFlDm").val()); 
			var dw = trim($("#mainForm_domain_jldwDm").val()); 
			var jggs = trim($("#mainForm_domain_jgjsgs").val()); 
			var xtgs = trim($("#mainForm_domain_xtgs").val()); 
			var yxqq = trim($("#mainForm_domain_yxqq").val()); 
			var yxqz = trim($("#mainForm_domain_yxqz").val()); 
			var jsjgDjxh = trim($("#mainForm_domain_jsjgDjxh").val()); 
			var url = jcontextPath+"/zygl/qykhjsjg!save";  
	    	var jsonObj = {"domain.khDjxh":khDjxh,"domain.ssJgbm":ssJgbm,"domain.hwDjxh":hwDjxh,"domain.syfw":fw,
                           "domain.fhrXzqhDm":fhr,"domain.shrXzqhDm":shr,"domain.lcs":lcs,
                           "domain.ddts":ddtc,"domain.jgjsgs":jggs,"domain.xtgs":xtgs,"domain.jldwFlDm":fl,"domain.jldwDm":dw,
                           "domain.yxqQ":yxqq,"domain.yxqZ":yxqz,"domain.hwxhDjxh":hwxhDjxh,"domain.jsjgDjxh":jsjgDjxh,"domain.jgsm":jgsm};   			
			ajaxCommon(url,jsonObj,"saveOk");
	}
	
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	
	function saveAfter(){
		window.close();
	}
	
	function checkHw(){
		var hwDjxh = trim($("#mainForm_domain_hwDjxh").val()); 
		if($(":radio[name='fanwei']")[1].checked){
			if(hwDjxh==''||hwDjxh==null){
				showAlert("请指定一条货物！");
				return false;
			}
		}
		return true;
	}
	
	
	

	
	
	function getHw(){
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var radio=$(":radio['name=fanwei']");
		if(radio[1].checked){
			commonInit('kh-hw', khDjxh, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
		}
		else{
			commonInit('kh-hw', 0, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
		}
	}
	
	
</script>
</head>

<body>
<%
try {
%>
<s:form action="qykhdjxx!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="jsonData"></s:hidden>
		<s:hidden name="domain.khDjxh"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.syfw"></s:hidden>
		<s:hidden name="domain.jsjgDjxh"></s:hidden>
	<div id="maincont">
	<div class="pop_contc" style="height:520px; overflow:auto;">
	<fieldset>
    	 	 <legend>所属单位</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="11%" align="right">所属单位：</td>
    	 	 	  		<td  width="55%"  colspan="3"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input_colspan2 noborder bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    		 </fieldset>
	<fieldset>
		    <legend>基本信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		<tr>
			<td width="15%" align="right">客户名称：
			</td>
			<td width="55%" colspan="3"><s:textfield name="domain.khMc"
				cssClass="pop_input_colspan2 noborder bgstyle_required" readonly="true"></s:textfield></td>
			
		</tr>
		<tr>
			<td align="right">适用范围：
			</td>
			<td><s:radio list="#{'1':'全部货物','2':'指定货物'}" name="fanwei" onclick="getHw()"></s:radio></td>
			<td align="right" width="15%">货物：
			</td>
			<td align="left" width="33%"><select name="domain.hwDjxh" id="mainForm_domain_hwDjxh" class="select">
						<option value="${domain.hwDjxh}"></option>
						</select>
			</td>
		</tr>
		
		<tr>
				 <td align="right" width="13%">货物型号：</td>
      				<td width="30%">
      				<select name="domain.hwxhDjxh" id="mainForm_domain_hwxhDjxh" class="select">
      				<option value="${domain.hwxhDjxh}"></option>
      				</select>
      				</td>
			  <td align="right" width="13%"><font class="font_red">*</font>有效期间：</td>
      				<td width="30%">
      					<input type="text" name="domain.yxqQ" id="mainForm_domain_yxqq" value="<s:property value="domain.yxqQ" />"  class="ymdate"  />
      				～
      					<input type="text" name="domain.yxqZ" id="mainForm_domain_yxqz" value="<s:property value="domain.yxqZ" />"  class="ymdate"  />
      				</td>
			
		</tr>
		<tr>
			  <td align="right" width="13%"><font class="font_red">*</font>计量单位分类：</td>
      				<td width="30%">
      					<sys:JldwFl myId="mainForm_domain_jldwFlDm" myName="domain.jldwFlDm" contaisQxz="false" myClass="select" mcContainDmBz="Y" />
      				</td>
			<td align="right" width="13%"><font class="font_red">*</font>计量单位：</td>
      				<td align="left" width="30%">
      					<select name="domain.jldwDm" id="mainForm_domain_jldwDm" class="select">
      						<option value="${domain.jldwDm}"></option>
      					</select>
      				</td>
		</tr>
		<tr>
			<td align="right" width="13%"><font class="font_red">*</font>始发地：</td>
  				  <td align="left" width="30%">
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		            
  				  </td>
			<td align="right" width="13%" ><font class="font_red">*</font>目的地：</td>
  				<td align="left" width="30%">
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
  				</td>	
  		</tr>
		
		<tr>
			  <td align="right" width="13%"><font class="font_red">*</font>里程数：</td>
      				<td width="30%">
      					<s:textfield name="domain.lcs" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
			<td align="right" width="13%"><font class="font_red">*</font>达到天数：</td>
      				<td align="left" width="30%">
      					<s:textfield name="domain.ddts" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
		</tr>
		
		<tr>
			<td width="18%" align="right"><font class="font_red">*</font>价格计算公式：
			</td>
			<td width="30%" colspan="3"><s:textarea name="domain.jgjsgs" id="mainForm_domain_jgjsgs"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
			</td>
		</tr>
		<tr>
			<td width="18%" align="right">参数：</td>
			<td width="30%"><sys:Csbm myId="mainForm_domain_cs" myClass="select"></sys:Csbm>
			</td>
			<td width="18%" align="right"><button type="button" class="pop_btnbg" id="getCs">插入</button></td>
		</tr>
		
		<tr>
			<td  width="18%" align="right">价格说明：</td>
			<td colspan="3"><s:textarea name="domain.jgsm"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
			</td>
			
		</tr>
		<tr>
			<td  width="18%" align="right"><font class="font_red">*</font>系统公式：</td>
			<td colspan="3"><s:textarea name="domain.xtgs"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
			</td>
			
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
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
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
