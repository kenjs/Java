<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>分公司维护</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
		var bz=$("#mainForm_domain_qybz").val();
		if(bz=='Y'){
			$(":radio[name='bz']")[0].checked=true;
		}
		else if(bz=='N'){
			$(":radio[name='bz']")[1].checked=true;
		}
		else{
			$(":radio[name='bz']")[0].checked=true;
		}
		
	    $("#closeBtn").click(function(){
			window.close();
		})
	
		 document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var mc = trim($("#mainForm_domain_mc").val()); 
			var bz =$(":radio[name='bz']")[0].checked?"Y":"N";
			var jc = trim($("#mainForm_domain_jc").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var sj = trim($("#mainForm_domain_sjJgbm").val()); 
			var id = trim($("#mainForm_domain_jgbm").val()); 
			var byj = trim($("#mainForm_domain_byj").val()); 
			if(byj!=null){
				if(byj/1<0){
					showAlert("备用金不能小于0！");
					return;
				}
			}			
			var fhrXzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			if(fhrXzqhDm==''||fhrXzqhDm==undefined){
				showError("行政区划不能为空！");
				return;
			}
			var url = jcontextPath+"/qyzzjg!save";  
	    	var jsonObj = {"domain.mc":mc,"domain.jc":jc,"domain.dz":dz,"domain.fzr":fzr,"domain.yb":yb,
	    			   "domain.dh":dh,"domain.sjJgbm":sj,"domain.jgbm":id,"domain.qybz":bz,"domain.fhrXzqhDm":fhrXzqhDm,"domain.byj":byj};   			
			ajaxCommon(url,jsonObj,"saveOk"); 
		});
	    
	   
	});
	
	
	function saveOk(list){
		showSuccess("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.jc","domain.dz",
		                        "domain.dh","domain.yb","domain.fzr","domain.byj"];
		var labelNameArray = ["名称","简称","地址",
		                      "电话","邮编","负责人","备用金"];
		var compareValueArray = [100,100,100,
			                     40,10,40,16.2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.INTEGER,NodeType.STRING,NodeType.STRING,NodeType.DECIMAL];
		var notNullArray = [true,true,true,
                            true,false,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="qyzzjg!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
 		<s:hidden name="domain.jgbm"></s:hidden>
		<s:hidden name="domain.sjJgbm"></s:hidden>
		<s:hidden name="domain.qybz"></s:hidden>
			<s:hidden name="jsonData" />
		<div id="maincont">
    <div class="pop_contc" style="height:320px; overflow:auto;">
    	 <fieldset>
    	 	 <legend>上级单位</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="15%" align="right">上级单位名称：</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    	 </fieldset>
        <fieldset>
		    <legend>分公司基本信息</legend>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				    <tr>
				   		<td width="15%" align="right"><font class="font_red">*</font>名称 ：</td>
				      	<td width="35%">
				      	<s:textfield name="domain.mc" rows="3" cssClass="pop_input bgstyle_required" ></s:textfield>
				        </td>
				        <td width="15%" align="right"><font class="font_red">*</font>简称：</td>
				      	<td width="35%">
				      	<s:textfield name="domain.jc" rows="3" cssClass="pop_input bgstyle_required" ></s:textfield>
				        </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right"><font class="font_red">*</font>地址：</td>
				      	<td width="35%">
				      		<s:textfield name="domain.dz" rows="3" cssClass="pop_input bgstyle_required" ></s:textfield>
				        </td>
				        <td width="15%" align="right"><font class="font_red">*</font>电话：</td>
				      	<td width="35%">
				      		 <s:textfield name="domain.dh" cssClass="pop_input bgstyle_required" ></s:textfield>
				        </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right"><font class="font_red">*</font>行政区划：</td>
				     	 <td align="left" width="30%">
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 186px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 161px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		            
  				  </td>
				      	<td width="15%" align="right"><font class="font_red">*</font>负责人：</td>
				      	<td>
				      		<s:textfield name="domain.fzr" cssClass="pop_input bgstyle_required" ></s:textfield>
				      	</td>
				    </tr>
				    <tr>
				    	<td width="15%" align="right">备用金：</td>
				      	<td>
				      		<s:textfield name="domain.byj" cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				      	<td width="15%" align="right">启用标志：</td>
				      	<td>
				      		<s:radio list="#{'1':'启用','2':'停用'}" name="bz" theme="simple"></s:radio>
				      	</td>
				      	
				    </tr>
				  <tr>
				    	<td width="15%" align="right">邮编：</td>
				      	<td>
				      		<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				     	
				      	<td colspan="2"></td>
				      	
				      	
				    </tr>
				    
				    <tr>
				     	<td width="15%" align="right">创建人：</td>
				     	 <td>
				      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">创建日期：</td>
				     	 <td>
				      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right">修改人：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">修改日期：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    
				  
			</table>
		 </fieldset>
		 <div class="pop_btn">
			   <button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
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
