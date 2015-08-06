<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>自营车辆维护</title>

<style type="text/css">
html,body {background:none;}
.in {width:95%;height:18px;text-align:center;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#addBtn").click(function(){
		   	var xh = $("#sjxxTbody tr").length+1;
		    var trObj = $("<tr></tr>");
			$("<td align=\"center\" class=\"bh\">"+xh+"</td>").appendTo($(trObj));
		    $("<td align=\"center\"><input type=\"checkbox\" name=\"xhs\" value=\"\"/></td>").appendTo($(trObj));
		    $("<td><input type='text' name='sjXm' class='pop_input bgstyle_optional sjXm'/></td>").appendTo($(trObj));
		    $("<td><input type='text' name='sjZjhm' class='pop_input bgstyle_optional sjZjhm'/></td>").appendTo($(trObj));
		    $("<td><input type='text' name='jszhm' class='pop_input bgstyle_optional jszhm'/></td>").appendTo($(trObj));
		    $("<td><input type='text' name='sjSjhm' class='pop_input bgstyle_optional sjSjhm'/></td>").appendTo($(trObj));
		    $("<td><input type='text' name='sjLxdh' class='pop_input bgstyle_optional sjLxdh'/></td>").appendTo($(trObj));
		    $("<td style='display:none;'><input type='hidden' name='xh' class='xh' value=\"\"/></td>").appendTo($(trObj));
			$(trObj).appendTo($("#sjxxTbody"));    
		});
		//共享方式默认2
		var clDjxh = $("#mainForm_domain_clDjxh").val();
		if(clDjxh == null || clDjxh == ""){
		    $("#mainForm_domain_xxgxfsDm").val(2);
		}
		$("#delBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if(xhs.length<=0){
				showAlert("请选择要删除的司机信息！")
				return;
			}else{
				showConfirm("删除后不可恢复！确认删除么？","deleteSj")
			}
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var czXm = trim($("#mainForm_domain_czXm").val()); 
			var clhm = trim($("#mainForm_domain_clhm").val()); 
			var czZjlxDm = $("#mainForm_domain_czZjlxDm").val(); 
			var czZjhm = $("#mainForm_domain_czZjhm").val(); 
			var czLxdh = $("#mainForm_domain_czLxdh").val(); 
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var xxgxfsDm = $("#mainForm_domain_xxgxfsDm").val();
			var clxhwhDjxh = $("#mainForm_domain_clxhwhDjxh").val();
		
			var gcbz = $("[name='gcbz']:checked").val();
			var thclbz = ""; 
			var ysclbz = ""; 
			var psclbz = "";
			var e1 = document.getElementById("thcl");
			if(e1.checked){
				thclbz="Y";
			} else {
				thclbz="N";
			}
			var e2 = document.getElementById("yscl");
			if(e2.checked){
				ysclbz="Y";
			} else {
				ysclbz="N";
			}
			var e3 = document.getElementById("pscl");
			if(e3.checked){
				psclbz="Y";
			} else {
				psclbz="N";
			}
			var bz = $("#mainForm_domain_bz").val(); 

			var clDjxh = trim($("#mainForm_domain_clDjxh").val()); 
			
			var xhObjs = $(".xh");
			var sjXmObjs = $(".sjXm");
			var sjZjhmObjs = $(".sjZjhm");
			var jszhmObjs = $(".jszhm");
			var sjSjhmObjs = $(".sjSjhm");
			var sjLxdhObjs = $(".sjLxdh");
			
			var xhs = new Array();
			var sjXms = new Array();
			var sjZjhms = new Array();
			var jszhms = new Array();
			var sjSjhms = new Array();
			var sjLxdhs = new Array();
			
			
			$.each(xhObjs, function(i, obj){
				xhs[i]=xhObjs[i].value;
				sjXms[i]=sjXmObjs[i].value;
				sjZjhms[i]=sjZjhmObjs[i].value;
				jszhms[i]=jszhmObjs[i].value;
				sjSjhms[i]=sjSjhmObjs[i].value;
				sjLxdhs[i]=sjLxdhObjs[i].value;
			});
			var jsonStr = getJqueryParamZdy(xhs, "domain.xhs");
			jsonStr+= getJqueryParamZdy(sjXms, "domain.sjXms");
			jsonStr+= getJqueryParamZdy(sjZjhms, "domain.sjZjhms");
			jsonStr+= getJqueryParamZdy(jszhms, "domain.jszhms");
			jsonStr+= getJqueryParamZdy(sjSjhms, "domain.sjSjhms");
			jsonStr+= getJqueryParamZdy(sjLxdhs, "domain.sjLxdhs");			
			
			if(thclbz=="N"&&ysclbz=="N"&&psclbz=="N"){
			   showAlert("车辆标识不可全为空！");
			}else{
			var url = jcontextPath+"/zygl/qyylclxx!save";  
	    	var jsonObj = {"domain.czXm":czXm,"domain.clhm":clhm,"domain.czZjlxDm":czZjlxDm,"domain.czZjhm":czZjhm,
                           "domain.czLxdh":czLxdh,"domain.ssJgbm":ssJgbm,"domain.thclbz":thclbz,"domain.ysclbz":ysclbz,
                           "domain.psclbz":psclbz,"domain.bz":bz,"domain.clDjxh":clDjxh,"domain.xxgxfsDm":xxgxfsDm,
                           "domain.clxhwhDjxh":clxhwhDjxh,"domain.gcbz":gcbz};
			jsonStr += jQuery.param(jsonObj);
			//alert(jsonStr);
			ajaxCommon(url,encodeURI(jsonStr),"YesSave", false);
			}
		});
	});
	
	function YesSave(data){
		var callOpenWinFun = $("#mainForm_domain_callOpenWinFun").val();
		if (callOpenWinFun != "") {
			var clDjxh = data.domain.clDjxh;
			var clhm = $("#mainForm_domain_clhm").val()
		    try{
				eval("window.dialogArguments."+callOpenWinFun+"(clDjxh,clhm);");
				}catch(e){
		        	//alert("not function:"+e); 
		       	}
		}
		$("#mainForm_domain_clDjxh").val(data.domain.clDjxh);		
		showSuccess("保存成功！","afterSave");
	}
	
	function afterSave(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.czXm","domain.clhm","domain.czZjlxDm","domain.czZjhm",
		                        "domain.czLxdh","domain.ssJgbm","domain.thclbz","domain.ysclbz",
		                        "domain.psclbz","domain.bz",
		                        "sjXm","sjZjhm","jszhm","sjSjhm","sjLxdh"];
		var labelNameArray = ["车主_姓名","车辆号码","车主_证件类型代码","车主_证件号码",
		                      "车主_联系电话","所属机构","提货车辆标志","运输车辆标志",
		                      "配送车辆标识","备注",
		                      "司机姓名","司机证件号码","驾驶证号码","司机手机号码","司机联系电话"];
		var compareValueArray = [100,50,50,50,
								 20,20,10,10,
								 10,200,
								 100,30,30,50,50];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
							 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,false,
							false,false,true,true,
                            true,false,
                            true,true,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
   	//自定义jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	function changeBh() {
		var bhs = $(".bh");
		$.each(bhs, function(i, obj){
			$(obj).text(i+1);
		});
	}
	function deleteSj() {
		var xhs = $(":checked[name='xhs'][value!='']");
		var clDjxh = $("#mainForm_domain_clDjxh").val();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.xhs");
			var jsonObj = {"domain.clDjxh":clDjxh};
			jsonStr += jQuery.param(jsonObj);
			//alert(jsonStr);
			var url = jcontextPath+"/zygl/qyylclxx!deleteSjxx";  
			ajaxCommon(url,encodeURI(jsonStr),"deleteSjSuc", false);
		}else {
			deleteSjSuc();
		}
	}
	function deleteSjSuc(){
		var xhs = $(":checked[name='xhs']");
		$.each(xhs, function(i, obj){
			var td = $(obj).parent();
			var tr = $(td).parent();
			$(tr).remove();
		});
		changeBh();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="qyylclxx!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.clDjxh"></s:hidden>
	<s:hidden name="domain.callOpenWinFun"></s:hidden>
	<input type="hidden" id="mainForm_zt"/>
	<div class="pop_contc" style="height:420px; overflow:auto;">
	<fieldset>
        	  <legend>所属单位</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>所属公司：</td>
     				<td width="85%">
     					<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" contaisQxz="false" myClass="select"/>
     				</td>
      			</tr>
      			</table>
		    </fieldset>
	<fieldset>
		<legend>基本信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		         <tr>     				
     				<td align="right">
     					<s:if test='domain.thclbz==\"Y\"'>
           					<s:checkbox name="domain.thclbz" checked="true" id="thcl" style="width:20px;"></s:checkbox>
  						</s:if>
  						<s:else>
   							<s:checkbox name="domain.thclbz" id="thcl" style="width:20px;"></s:checkbox>
  						</s:else>
     				          提货车辆
     				</td>
     				
     				<td align="right">
     					<s:if test='domain.psclbz==\"Y\"'>
           					<s:checkbox name="domain.psclbz" checked="true" id="pscl" style="width:20px;"></s:checkbox>
  						</s:if>
  						<s:else>
   							<s:checkbox name="domain.psclbz" id="pscl" style="width:20px;"></s:checkbox>
  						</s:else>
     				          配送车辆
     				</td>
     				
     				<td align="right" >
     					<s:if test='domain.ysclbz==\"Y\"'>
           					<s:checkbox name="domain.ysclbz" checked="true" id="yscl" style="width:20px;"></s:checkbox>
  						</s:if>
  						<s:else>
   							<s:checkbox name="domain.ysclbz" id="yscl" style="width:20px;"></s:checkbox>
  						</s:else>
     				          运输车辆
     				</td>
     			</tr>
				<tr>
     				<td width="15%" align="right"><font class="font_red">*</font>车主姓名：</td>
     				<td width="30%" colspan="2">
     					<s:textfield name="domain.czXm" cssClass="pop_input bgstyle_required" ></s:textfield>
     				</td>
     				<td width="15%" align="right"><font class="font_red">*</font>车辆号码：</td>
     				<td width="20%" >
     					<s:textfield name="domain.clhm" cssClass="pop_input bgstyle_required" ></s:textfield>
     				</td>     				
  				    <td width="20%">挂车：
  				         <s:if test="domain.gcbz==null"><s:radio name="gcbz" list="#{'Y':' 是','N':' 否' }" value="'Y'" theme="simple"></s:radio></s:if>
  				         <s:if test="domain.gcbz!=null"><s:radio name="gcbz" list="#{'Y':' 是','N':' 否' }" value="domain.gcbz" theme="simple"></s:radio></s:if>
  				    </td>
     			</tr>
     			<tr>
     			    <td align="right"><font class="font_red">*</font>车辆型号：</td>
     				<td colspan="2">
     					<sys:QyClxhwh myName="domain.clxhwhDjxh" myId="mainForm_domain_clxhwhDjxh" myClass="select" ></sys:QyClxhwh>
     				</td>
     				<td align="right">联系电话：</td>
     				<td colspan="2">
     					<s:textfield name="domain.czLxdh" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td>   
     				
     			</tr>
     			<tr>
     				<td align="right">证件类型：</td>
     				<td colspan="2">
     					<sys:zjlx myName="domain.czZjlxDm" myId="mainForm_domain_czZjlxDm" myClass="select" contaisQxz="true"/>
     				</td>
     				<td align="right">证件号码：</td>
     				<td colspan="2">
     					<s:textfield name="domain.czZjhm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
     				</td>
     			</tr>     			
     			<tr>
     				<td align="right">共享方式：</td>
     				<td colspan="8">
     					<sys:Xxgxfs myId="mainForm_domain_xxgxfsDm" myName="domain.xxgxfsDm" myClass="select" contaisQxz="false"/>
     				</td>     
     								
     			</tr>
     				
     			<tr>
     				<td align="right">备注：</td>
     				<td colspan="8">
     					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
     				</td>
     			</tr>
     			
		</table>
		</fieldset>
		<div style="margin-top: 20px;">
		<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
		      <tr>
		        <th width="5%">序号</th>
		        <th width="5%"><input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,'xhs');" /></th>
		        <th width="17.5%">司机姓名</th>
		        <th width="17.5%">身份证号</th>
		        <th width="15%">驾驶证号</th>
		        <th width="15%">手机号码</th>
		        <th width="20%">其它联系电话</th>
		      </tr>
		      <tbody id="sjxxTbody">
		      <s:iterator id="zb" value="domain.sjList" status="sta">
		      	<tr>
			        <td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			      	<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.xh" />" /></td>
			        <td><input type='text' name='sjXm' class="pop_input bgstyle_optional sjXm" value='<s:property value="#zb.sjXm" />'/></td>
			        <td><input type='text' name='sjZjhm' class='pop_input bgstyle_optional sjZjhm' value='<s:property value="#zb.sjZjhm" />'/></td>
			        <td><input type='text' name='jszhm' class='pop_input bgstyle_optional jszhm' value='<s:property value="#zb.jszhm" />'/></td>
			        <td><input type='text' name='sjSjhm' class='pop_input bgstyle_optional sjSjhm' value='<s:property value="#zb.sjSjhm" />'/></td>
			        <td><input type='text' name='sjLxdh' class='pop_input bgstyle_optional sjLxdh' value='<s:property value="#zb.sjLxdh" />'/></td>
			        <td style="display: none;"><input type="hidden" name='xh' class="xh" value='<s:property value="#zb.xh" />'/></td>
		      	</tr>
		      </s:iterator>
		      </tbody>
		    </table>
		</div>
		</div>
	
	<div class="pop_btn">
		<button type="button" class="pop_btnbg" id="addBtn">新增司机</button>
	 	&nbsp;
		<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
	 	&nbsp;	
	 	<button type="button" class="pop_btnbg" id="delBtn">删 除</button>
	 	&nbsp;
	    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
