<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>协议登记-货物明细</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200,"hwmxDomain.shrXzqhMc","hwmxDomain.shrXzqhDm");
		//行政区划input下拉
		initXzqhInputSel();
		
		initRadio();
		
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var pcDjxh = $("#mainForm_domain_hwmxDomain_pcDjxh").val();
			var wfhDjxh = $("#mainForm_domain_hwmxDomain_wfhDjxh").val();
			var ddDjxh = trim($("#mainForm_domain_hwmxDomain_ddDjxh").val());
			var xh = trim($("#mainForm_domain_hwmxDomain_xh").val()); 
			
			var szHwBzHldwDm = trim($("#mainForm_domain_hwmxDomain_szHwBzHldwDm").val()); 
			var szJsSl = $("#mainForm_domain_hwmxDomain_szJsSl").val();
			var szHwSl = trim($("#mainForm_domain_hwmxDomain_szHwSl").val()); 
			var szHwZl = trim($("#mainForm_domain_hwmxDomain_szHwZl").val()); 
			var szHwTj = trim($("#mainForm_domain_hwmxDomain_szHwTj").val()); 
			
			var shrDjxh = $("#mainForm_domain_hwmxDomain_shrDjxh").val();
			var shrMc = $("#mainForm_domain_hwmxDomain_shrMc").val(); 
			var shrDz = $("#mainForm_domain_hwmxDomain_shrDz").val(); 
			var shrLxr = $("#mainForm_domain_hwmxDomain_shrLxr").val();
			var shrLxdh = $("#mainForm_domain_hwmxDomain_shrLxdh").val(); 
			
			var yqDdrq = $("#mainForm_domain_hwmxDomain_yqDdrq").val(); 
			var shfsDm = $("[name='shfsDm']:checked").val();
			var bz = $("#mainForm_domain_hwmxDomain_bz").val();
			var hdbh = $("#mainForm_domain_hwmxDomain_hdbh").val();
			
			var url = jcontextPath+"/zyegl/pcxydj!saveHwmxXydj";  
	    	var jsonObj = {"domain.hwmxDomain.pcDjxh":pcDjxh,"domain.hwmxDomain.wfhDjxh":wfhDjxh,"domain.hwmxDomain.ddDjxh":ddDjxh,"domain.hwmxDomain.xh":xh,
	    				"domain.hwmxDomain.szHwBzHldwDm":szHwBzHldwDm,"domain.hwmxDomain.szJsSl":szJsSl,"domain.hwmxDomain.szHwSl":szHwSl,
	    				"domain.hwmxDomain.szHwZl":szHwZl,"domain.hwmxDomain.szHwTj":szHwTj,
                        "domain.hwmxDomain.shrDjxh":shrDjxh,"domain.hwmxDomain.shrMc":shrMc,"domain.hwmxDomain.shrDz":shrDz,
                        "domain.hwmxDomain.shrLxr":shrLxr,"domain.hwmxDomain.shrLxdh":shrLxdh,
                        "domain.hwmxDomain.yqDdrq":yqDdrq,"domain.hwmxDomain.shfsDm":shfsDm,"domain.hwmxDomain.bz":bz,
                        "domain.hwmxDomain.hdbh":hdbh};   			
	    	showMessage();
	    	ajaxCommon(url,jsonObj,"saveHwXydjSuc");
		});
		
		
		$("#mainForm_domain_hwmxDomain_szHwSl").change(function(){
			var bl = $("#mainForm_domain_hwmxDomain_szHwSl").val()/1 / $("#mainForm_domain_pcHwDomain_hwSl").val()/1;
			var jssl = $("#mainForm_domain_pcHwDomain_jsSl").val()/1 * bl;
			$("#mainForm_domain_hwmxDomain_szJsSl").val(jssl.toFixed(2));
			
			var zl = $("#mainForm_domain_pcHwDomain_hwZl").val()/1 * bl;
			$("#mainForm_domain_hwmxDomain_szHwZl").val(zl.toFixed(2));
			
			var tj = $("#mainForm_domain_pcHwDomain_hwTj").val()/1 * bl;
			$("#mainForm_domain_hwmxDomain_szHwTj").val(tj.toFixed(2));
		});
		
	});
	
	function initRadio() {
		if ($("#mainForm_domain_hwmxDomain_shfsDm").val() != "") {
			$("[name='shfsDm'][value='"+$("#mainForm_domain_hwmxDomain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='shfsDm']")[0].checked = true;
		}
		if ($("#mainForm_domain_pcHwDomain_shfsDm").val() != "") {
			$("[name='ddShfsDm'][value='"+$("#mainForm_domain_pcHwDomain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='ddShfsDm']")[0].checked = true;
		}
	}
	
	function saveHwXydjSuc(data) {
		hideMessage();
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.hwmxDomain.szHwSl","domain.hwmxDomain.szHwZl","domain.hwmxDomain.szHwTj",
		                        "domain.hwmxDomain.shrMc","domain.hwmxDomain.shrDz","domain.hwmxDomain.shrLxr","domain.hwmxDomain.shrLxdh",
		                        "domain.hwmxDomain.yqDdrq","domain.hwmxDomain.shrXzqhDm","domain.hwmxDomain.bz"];
		var labelNameArray = ["数量","重量","体积",
		                      "收货单位","收货地址","联系人","联系电话",
		                      "要求到货日期","目的地","备注"];
		var compareValueArray = [12.6,12.6,12.6,
		                         100,100,40,100,
		                         11,20,500];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [false,false,false,
		                    false,false,false,false,
		                    false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="pcxydj!initXydjHwmx" namespace="/zyegl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.hwmxDomain.pcDjxh" />
	<s:hidden name="domain.hwmxDomain.wfhDjxh" />
	<s:hidden name="domain.hwmxDomain.ddDjxh" />
	<s:hidden name="domain.hwmxDomain.xh" />
	<s:hidden name="domain.hwmxDomain.shfsDm" />
	<s:hidden name="domain.pcHwDomain.shfsDm" />
	
	<s:hidden name="jsonData" />
	
	  <div id="maincont">
		<div class="pop_contc">
			<fieldset>
			<legend>实际装货信息</legend>
			<table width="575" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="90" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="70" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="80" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="60" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">货物名称：</td>
      				<td colspan="4">
      					<s:textfield name="domain.pcHwDomain.hwmc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td colspan="2" align="right">回单编号：</td>
      				<td colspan="4">
      					<s:textfield name="domain.hwmxDomain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:hidden name="domain.hwmxDomain.szJsSl"></s:hidden>
      					<s:textfield name="domain.hwmxDomain.szHwSl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.szHwZl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.szHwTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      				<td align="right">包装：</td>
      				<td>
      					<sys:QyBzJldw myName="domain.hwmxDomain.szHwBzHldwDm" myId="mainForm_domain_hwmxDomain_szHwBzHldwDm" contaisQxz="true" myClass="select">   						
      					</sys:QyBzJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">收货单位：</td>
	  				<td colspan="4">
	  					<s:hidden name="domain.hwmxDomain.shrDjxh"></s:hidden>
	  					<s:textfield name="domain.hwmxDomain.shrMc" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
	  				</td>
	  				<td colspan="2" align="right">收货方式：</td>
	  				<td colspan="4">
	  					<s:radio name="shfsDm" list="#{'1':' 自提','2':' 送货到门' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
	  				<td align="right">收货地址：</td>
	  				<td colspan="4">
	  					<s:textfield name="domain.hwmxDomain.shrDz" cssClass="pop_input noborder bgstyle_optional" />
	  				</td>
	  				<td colspan="2" align="right">要求到货日期：</td>
	  				<td colspan="4">
	  					<input type="text" name="domain.hwmxDomain.yqDdrq" id="mainForm_domain_hwmxDomain_yqDdrq" value="<s:date name="domain.hwmxDomain.yqDdrq" format="yyyy-MM-dd" />" class="ymdate"  />
	  				</td>
      			</tr>
	  			<tr>
	  				<td align="right" >目的地：</td>
	  				<td colspan="2">
	  					<s:hidden name="domain.hwmxDomain.shrXzqhDm"></s:hidden>
	  					<div class="inputsel" style="width: 76px;">
	  						<s:textfield name="domain.hwmxDomain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 46px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="hwmxDomain_shrXzqh" onFocus="this.blur()"></a>
			            </div>
	  				</td>
	  				<td align="right">联系人：</td>
	  				<td colspan="2">
	  					<s:textfield name="domain.hwmxDomain.shrLxr" cssClass="pop_input noborder bgstyle_optional" />
	  				</td>
	  				<td colspan="2" align="right">联系电话：</td>
	  				<td colspan="2">
	  					<s:textfield name="domain.hwmxDomain.shrLxdh" cssClass="pop_input noborder bgstyle_optional" />
	  				</td>
	  				<td ></td>
	  			</tr>
	  			<tr>
	  				<td align="right">备注：</td>
	  				<td colspan="10">
	  					<s:textarea name="domain.hwmxDomain.bz" rows="2" cssClass="pop_textarea_colspan2 noborder bgstyle_optional"></s:textarea>
	  				</td>
	  			</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>订单货物信息</legend>
			<table width="575" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="90" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="70" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="80" class="td_noborder"></td>
      				<td width="55" class="td_noborder"></td>
      				<td width="20" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="60" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">回单编号：</td>
      				<td colspan="6">
      					<s:textfield name="domain.pcHwDomain.hdbh" cssClass="pop_input noborder bgstyle_readonly" />
      				</td>
      				<td colspan="4">
      				</td>
      			</tr>
      			<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:hidden name="domain.pcHwDomain.jsSl"></s:hidden>
      					<s:textfield name="domain.pcHwDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      				<td align="right">包装：</td>
      				<td>
      					<sys:QyBzJldw myName="domain.pcHwDomain.hwBzHldwDm" myId="mainForm_domain_hwmxDomain_szHwBzHldwDm" contaisQxz="true" myClass="select">   						
      					</sys:QyBzJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">收货单位：</td>
	  				<td colspan="4">
	  					<s:textfield name="domain.pcHwDomain.shrMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	  				</td>
	  				<td colspan="2" align="right">收货方式：</td>
	  				<td colspan="4">
	  					<s:radio name="ddShfsDm" list="#{'1':' 自提','2':' 送货到门' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
	  				<td align="right">收货地址：</td>
	  				<td colspan="3">
	  					<s:textfield name="domain.pcHwDomain.shrDz" cssClass="pop_input noborder bgstyle_readonly" />
	  				</td>
	  				<td colspan="2" align="right">要求到货日期：</td>
	  				<td colspan="4">
	  					<s:textfield name="domain.pcHwDomain.yqDdrq" cssClass="pop_input noborder bgstyle_readonly" />
	  				</td>
      			</tr>
	  			<tr>
	  				<td align="right" >目的地：</td>
	  				<td colspan="2">
	  					<s:textfield name="domain.pcHwDomain.shrXzqhMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	  				</td>
	  				<td align="right">联系人：</td>
	  				<td colspan="2">
	  					<s:textfield name="domain.pcHwDomain.shrLxr" cssClass="pop_input noborder bgstyle_readonly" />
	  				</td>
	  				<td colspan="2" align="right">联系电话：</td>
	  				<td colspan="2">
	  					<s:textfield name="domain.pcHwDomain.shrLxdh" cssClass="pop_input noborder bgstyle_readonly" />
	  				</td>
	  			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
				<s:if test='domain.hwmxDomain.editFlag == "Y"'>
				<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			 	</s:if>
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
		
		<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
	      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
	    </div>
	 </div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
