<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>托运单管理 </title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/hy_tydgl_mx.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
$(function(){
	document.getElementById("maincont").onmousewheel=hideHelpWindow;
	initHykhData(300,$("#mainForm_domain_ssJgbm").val(),$("#mainForm_domain_djJgbm").val());
	initRadio();	
	
	var sjJgbm = $("#mainForm_domain_ssJgbm").val();
	var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
	if (tempFlag == "Y") {
		$("#khmcShow2").hide();
	}else{
		$("#khmcShow1").hide();
	}
	
	$("#mainForm_domain_djJgbm").change(function(){
		$("[name='domain.fhrMc']").unbind();
		initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
	});
	
	
	$(":radio").attr("disabled", true);
	$(":input").attr("readonly", true);
	
	$("#closeBtn").click(function() {
		window.close();
	});
	
});

function onPrintView(xh){
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var url = jcontextPath + '/hygl/hytydgl!printView.action?domain.hwmxDomain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
    	//navigateMenu(url,'托运单打印','true');
    	window.open(url, "_blank");
    	//window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}

</script>
<base target="_self" />
</head>

<body>
<%try{ %>
	<s:hidden name="domain.thflDm" />
	<s:hidden name="domain.shfsDm" />
	
	<s:hidden name="fhrData" />
	<div id="maincont" style="display: none;"></div>
	<div class="pop_contc">
		<fieldset>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
  			<tr>
  			    <td align="right" width="10%">客户名称：</td>
  				<td width="22%">
  					<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div id="khmcShow1">
	      					<div class="inputsel" style="width: 97%;">
		  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_required" cssStyle="width:82%;"></s:textfield>
		  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					        </div>
  					</div>
  					<div id="khmcShow2">
  					    <input type="text" id="khmc2" class="pop_input noborder inputext bgstyle_readonly" value="<s:property value="domain.fhrMc" />"/>
	                </div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right" width="10%">订单编号：</td>
  				<td width="13%">
  					<s:if test="domain.xtcs20002==0">
  						<input id="hidDdbh" type="hidden" value="<s:property value="domain.ddbh" />"/>
  						<s:textfield name="domain.ddbh" cssClass="pop_input noborder inputext bgstyle_required"></s:textfield>
  					</s:if>
  					<s:else>
	  					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
  					</s:else>
  				</td>
  				
  				<td align="right" width="10%">下单日期：</td>
  				<td width="10%">
  					<input type="text" name="domain.xdrq" id="mainForm_domain_xdrq" value="<s:date name="domain.xdrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_optional"  />
  				</td>
  				<td align="right" width="10%">登记部门：</td>
  				<td width="15%">
  					<s:textfield name="domain.djJgmc" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  			</tr>
  			<tr style="display: none;">
  				<td align="right">备注：</td>
  				<td colspan="7">
  					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
  				</td>
  			</tr>
		</table>
		
		</fieldset>
		
		<div class="pop_tbcont" id="jbxxcont">
      	<jsp:include page="/work/jcgl/jc_tydglcx_hwmx.jsp"/>
        </div>
		
		<div style="padding-top: 5px;padding-bottom: 20px;overflow-x:auto;width: 100%;overflow-y:hidden;">
		<table id="zbTab" border="0" width="1950px" cellspacing="0" cellpadding="0" class="poptab_css">
	      <tr>
	      	<th width="30px">序号</th>
	      	<th width="70px">操作</th>
	        <th width="100px">货物名称</th>
	        <th width="30px">提货</th>
	        <th width="30px">送货</th>
	        <th width="30px">类型</th>
	        
	        <th width="60px">数量</th>
	        <th width="60px">重量</th>
	        <th width="60px">体积</th>
	        <th width="60px">包装</th>
	        
	        <th width="60px">收入</th>
	        <th width="60px">现付</th>
	        <th width="60px">到付</th>
	        <th width="60px">月结</th>
	        <th width="60px">回扣</th>
	        
	        <th width="70px">目的地</th>
	        <th width="130px">收货人</th>
	        <th width="150px">收货地址</th>
	        <th width="60px">联系人</th>
	        <th width="80px">联系电话</th>
	        
	        <th width="70px">发货日期</th>
	        <th width="70px">到达日期</th>
	        
	        <th width="70px">始发地</th>
	        <th width="130px">发货人</th>
	        <th width="150px">发货地址</th>
	        <th width="60px">联系人</th>
	        <th width="80px">联系电话</th>
	      </tr>
	      <tbody id="hwSavedTbody">
	      <s:iterator value="domain.hwList" id="hw" status="sta">
	      	<tr id="tr<s:property value="#hw.xh" />">
	      		<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
	      		<td align="center">
	      			<a href="javascript:onPrintView(<s:property value="#hw.xh" />)"><font color="blue"> 打印</font></a>
	      		</td>
		        <td align="center"><a href="javascript:onShowHw(<s:property value="#hw.ddDjxh" />,<s:property value="#hw.xh" />)"><font color="blue"><s:property value="#hw.hwmc" /></font></a></td>
		        <td align="center"><s:if test="#hw.thflDm==1">是</s:if><s:else>否</s:else></td>
		        <td align="center"><s:property value="#hw.shfsMc" /></td>
		        <td align="center"><s:if test="#hw.hwflDm==1">重货</s:if><s:else>泡货</s:else> </td>
		        
		        <td align="right"><s:property value="#hw.hwSl" />&nbsp;<s:if test="#hw.hwSl != null"><s:property value="#hw.hwSlJldwMc" /></s:if>&nbsp;</td>
		        <td align="right"><s:property value="#hw.hwZl" />&nbsp;<s:if test="#hw.hwZl != null"><s:property value="#hw.hwZlJldwMc" /></s:if>&nbsp;</td>
		        <td align="right"><s:property value="#hw.hwTj" />&nbsp;<s:if test="#hw.hwTj != null"><s:property value="#hw.hwTjJldwMc" /></s:if>&nbsp;</td>
		        <td align="center"><s:property value="#hw.hwbzHldwMc" /></td>

		        <td align="center"><s:property value="#hw.srHj" /></td>
		        <td align="center"><s:property value="#hw.srXf" /></td>
		        <td align="center">
		        	<s:if test="#hw.shfsDm==1"><s:property value="#hw.srThf" /></s:if>
		        	<s:else><s:property value="#hw.srHdf" /></s:else>
		        </td>
		        <td align="center"><s:property value="#hw.srYj" /></td>
		        <td align="center"><s:property value="#hw.srHk" /></td>
		        
		        <td align="center"><s:property value="#hw.shrXzqhMc" /></td>
		        <td align="center"><s:property value="#hw.shrMc" /></td>
		        <td align="center"><s:property value="#hw.shrDz" /></td>
		        <td align="center"><s:property value="#hw.shrLxr" /></td>
		        <td align="center"><s:property value="#hw.shrLxdh" /></td>
		        
		        <td align="center"><s:property value="#hw.yqFhrq" /></td>
		        <td align="center"><s:property value="#hw.yqDdrq" /></td>
		        
		        <td align="center"><s:property value="#hw.fhrXzqhMc" /></td> 
		        <td align="center"><s:property value="#hw.fhrMc" /></td>
		        <td align="center"><s:property value="#hw.fhrDz" /></td>
		        <td align="center"><s:property value="#hw.fhrLxr" /></td>
		        <td align="center"><s:property value="#hw.fhrLxdh" /></td>
	      	</tr>
	      </s:iterator>
	      </tbody>
	      <tbody id="hwTbody">
	      </tbody>
	    </table>
	    </div>
	    <div class="pop_btn">
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
		</div>
	
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
