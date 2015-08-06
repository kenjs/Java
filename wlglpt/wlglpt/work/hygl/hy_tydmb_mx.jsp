<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>托运单管理 </title>

<style type="text/css">
html,body {background:none;}
.tydSel {width:68px;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/hy_tydmb_mx.js" ></script>
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
	
	$(".hkCal").change(function(){
		var hk = $("#mainForm_domain_srXf").val()/1+$("#mainForm_domain_srHdf").val()/1+$("#mainForm_domain_srThf").val()/1+$("#mainForm_domain_srHf").val()/1+$("#mainForm_domain_srYj").val()/1-$("#mainForm_domain_srHj").val()/1;
		
		$("#mainForm_domain_srHk").val(hk <= 0 ? '' : hk.toFixed(2)+'');
	});
	
	$("#closeBtn1").click(function() {
	    onCloseMx();
	});
	$("#closeBtn2").click(function() {
	    onCloseMx();
	});
	$("#saveBtn").click(function(){
		var hwmc = trim($("#mainForm_domain_hwmxDomain_hwmc").val()); 
	  	if(hwmc==""){
	  		var hwts = $("#hwSavedTbody tr").length + $("#hwTbody tr").length;
	  		if(hwts<=0){
	  			showAlert("至少要有一条货物！");
	  			return;
	  		}
	  		saveData();	
	  	}else{
	  	    doSaveHwStart(1);
	  	   
	  	}
	});
	
	$("#mainForm_domain_fhrMc").change(function() {
		setTimeout(checkKhExists, 200);
	});
	$("#hwmc").click(function() {
        var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		initHyhwData(200,fhrDjxh);
	});
	$("#mainForm_hwmxDomain_hwmc").click(function() {
        var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		initHyhwData(200,fhrDjxh);
	});
	$("#inputSel_fhr li").mouseover(function(){
		liMouseover(this, "inputSel_fhr");
	});
	
	$("#khAddBtn").click(function(){
		var hwts = $("#hwSavedTbody tr").length + $("#hwTbody tr").length;
  		if(hwts>0){
  			showAlert("请先删除货物，再改变客户！");
  			$("#mainForm_domain_fhrDjxh").val($("#mainForm_domain_fhrDjxh2").val());
			$("#mainForm_domain_fhrMc").val($("#mainForm_domain_fhrMc2").val());
  			return;
  		}
		onAddKh();
	});
});

</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="hytydgl!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.mbDjxh" />
	<s:hidden name="domain.thflDm" />
	<s:hidden name="domain.yjjsfsDm" />
	<s:hidden name="domain.ykjsfsDm" />
	<s:hidden name="domain.shfsDm" />
	<s:hidden name="domain.dzztDm" />
	<s:hidden name="domain.xtcs20002" cssClass="notChange"/>
	<s:hidden name="domain.xtcs20014" cssClass="notChange"/>
	<s:hidden name="domain.hwmxDomain.tempFlag" />	
	
	<s:hidden name="domain.khlxDm4js" />
	<s:hidden name="domain.ykjsfsDm4js" />
	<s:hidden name="jsonData" />
	<s:hidden name="fhrData" />
	<s:hidden name="zhdzData" />
	<s:hidden name="shdwData"/>
	<s:hidden name="domain.tcbz" />
	
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.djJgbm"></s:hidden>
	<div class="right_btnbg">
		<ul class="lcont">
			<li class="no">操作：</li>
			<li><a href="#" id="khAddBtn" class="licon14">新增客户</a></li>
		    <li><a href="#" id="closeBtn2" class="licon03">关 闭</a></li>
	  	</ul>
	    <ul class="rcont">
		    <li class="ricon03">帮助</li>
	  	</ul>
	</div> 
	<div id="maincont" style="display: none;"></div>
	<div class="right_cont">
		<fieldset>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
  			<tr>
  				<td align="right" width="10%">模板名称：</td>
  				<td width="15%">
  					<s:textfield name="domain.mbmc" cssClass="pop_input noborder bgstyle_required" />
  				</td>
  			    <td align="right" width="10%">发货单位：</td>
  				<td width="22%">
  					<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div id="khmcShow1">
	      					<div class="inputsel" style="width: 97%;">
		  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_required" cssStyle="width:83%;"></s:textfield>
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
  				
  				
  				<td align="right" width="10%">登记部门：</td>
  				<td width="13%">
  					<s:textfield name="domain.djJgmc" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td colspan="2"></td>
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
      	<jsp:include page="/work/hygl/hy_tydmb_hwmx.jsp"/>
        </div>
		
		<div style="padding-top: 5px;padding-bottom: 20px;overflow-x:auto;width: 100%">
		<table id="zbTab" border="0" width="1910px" cellspacing="0" cellpadding="0" class="poptab_css">
	      <tr>
	      	<th width="30px">序号</th>
	      	<th width="30px">操作</th>
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
	        <th width="60px">回付</th>
	        <th width="60px">回扣</th>
	        
	        <th width="70px">目的地</th>
	        <th width="130px">收货人</th>
	        <th width="150px">收货地址</th>
	        <th width="60px">联系人</th>
	        <th width="80px">联系电话</th>
	        
	        <th width="70px">发货时间</th>
	        <th width="70px">到达时间</th>
	        
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
	      		<td align="center"><a href="javascript:confirmDelHwxx(<s:property value="#hw.xh" />)"><font color="blue">删除</font></a></td>
		        <td align="center"><a href="javascript:onShowHw(<s:property value="#hw.mbDjxh" />,<s:property value="#hw.xh" />)"><font color="blue"><s:property value="#hw.hwmc" /></font></a></td>
		        
		        <td align="center"><s:if test="#hw.thflDm==1">是</s:if><s:else>否</s:else> </td>
		        <td align="center"><s:property value="#hw.shfsMc" /></td>
		        <td align="center"><s:if test="#hw.hwflDm==1">重货</s:if><s:else>泡货</s:else> </td>
		        
		        <td align="right"><s:property value="#hw.hwSl" />&nbsp;<s:if test="#hw.hwSl != null"><s:property value="#hw.hwSlJldwMc" /></s:if>&nbsp;</td>
		        <td align="right"><s:property value="#hw.hwZl" />&nbsp;<s:if test="#hw.hwZl != null"><s:property value="#hw.hwZlJldwMc" /></s:if>&nbsp;</td>
		        <td align="right"><s:property value="#hw.hwTj" />&nbsp;<s:if test="#hw.hwTj != null"><s:property value="#hw.hwTjJldwMc" /></s:if>&nbsp;</td>
		        <td align="center"><s:property value="#hw.hwbzHldwMc" /></td>

		        <td align="center"><s:property value="#hw.srHj" /></td>
		        <td align="center"><s:property value="#hw.srXf" /></td>
		        <s:if test="#hw.shfsDm==1"><td align="center"><s:property value="#hw.srThf" /></td></s:if>
		        <s:if test="#hw.shfsDm==2"><td align="center"><s:property value="#hw.srHdf" /></td></s:if>
		        <td align="center"><s:property value="#hw.srHf" /></td>
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
		 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn1">关 闭</button>
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
