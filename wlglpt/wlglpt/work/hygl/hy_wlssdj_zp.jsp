<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>货运-物流损失照片上传</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		initWlssMxXl();
		$("#mainForm_domain_wlssyyWhXh").focus();
		$("#closeBtn").click(function(){
			window.close();
		})
		$(".wlssMxJe").change(function() {
			calJeHj();
		});
		$("#mainForm_domain_ssSl").change(function() {
			var sl = $("#mainForm_domain_ssSl").val()/1;
			var ysl = $("#mainForm_domain_sl").val()/1;
			var tj = $("#mainForm_domain_tj").val()/1;
			var zl = $("#mainForm_domain_zl").val()/1;
			var jsSl = $("#mainForm_domain_jsSl").val()/1;
			//alert("原："+ysl+":"+tj+":"+zl+":"+jsSl);

			var bl = sl/ysl;
			$("#mainForm_domain_ssTj").val((tj*bl).toFixed(2));
			$("#mainForm_domain_ssZl").val((zl*bl).toFixed(2));
			$("#jsSl").val((jsSl*bl).toFixed(2));
			
			if(sl<=0||sl>ysl){
				showAlert("数量不可小于零且不可大于发货数量！","initSl");
			}
		});
		
		$("#sendBtn").click(function(){
			var wsDm="303002";//物流损失登记审批表
			var wlssDjxh = $("#mainForm_domain_wlssDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			//alert(oldWsspxh+":"+wlssDjxh);
			scSend(wsDm,"",wlssDjxh,oldWsspxh);
		});
		
		
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if(xhs.length<=0){
				showAlert("请选择要删除的损失记录！")
				return;
			}else{
				showConfirm("删除后不可恢复！确认删除么？","delWlssMx")
			}
		});	
		
		$("[name='wlssclfsDm']").change(function(){
			var allRows = $("#wlssMxTbody").find("tr");
			var rowNum = $(this).parent().parent();
			var index= allRows.index(rowNum);
			var gsRy = $("#qyryDiv").html();
			var sj = $("#sjDiv").html();
			var ryCel = $("#wlssMxTbody tr:eq("+index+") td:nth-child(9)");
			if($(this).val() == 3){
				ryCel.html(gsRy);
			}else if($(this).val() == 1){
	    		ryCel.html(sj);
	    }	else{
				ryCel.html("");
			}
		});
		
		$("#saveBtn").click(function(){
			
			
			var photo=$("#fjs").val();
			if(photo==''){
				showError("安检照片不能为空!");
				return false;
			}
	
			var hou=photo.lastIndexOf(".");
			var mc=photo.substring(hou+1);
			if(mc!="jpg"&&mc!="jpeg"&&mc!="gif"&&mc!="png"&&mc!="bmp"&&mc!="tiff"&&mc!="swf"&&mc!="svg"){
				showError("系统只支持jpg、jpeg、gif、png、bmp、tiff、swf、svg格式的图片！");
				return;
			}
			$("#mainForm").attr("action",jcontextPath+"/wlssdjzp!uploadFile");
			$("#mainForm").submit();

		});
		
	    /*//根据pcDjxh初始化客户名称
		var djxh=$("#mainForm_domain_pcDjxh").val();
		commonInit("KHMC", djxh, '', "domain.kh", "mainForm_domain_kh", "Y", "Y");
		
		//根据客户初始化货物
		var djxh=$("#mainForm_domain_pcDjxh").val();
		var id=$("#mainForm_domain_kh").val();
		var url=jcontextPath+"/hygl/wlssdj!getHw";
		var json={"domain.pcDjxh":djxh,"domain.fhrDjxh":id,"domain.conBz":'Y',"domain.conDm":'Y',"domain.currentObjId":"mainForm_domain_hw","domain.currentObjName":"domain.hw","domain.defaultValue":$("#mainForm_domain_hw").val()};
		ajaxCommon(url,json,"getHwOk");*/
		$("#showBt").click(function(){
			var showtext=$("#showBt").val();
			if(showtext=="显示"){
				$("#showBt").val("隐藏");
				
				$.ajax({
					   type: "POST",
					   async:false,
					   url: jcontextPath+"/wlssdjzp!showPhoto",
					   dataType:"json",
					   data: {wlssdjxh:$("#mainForm_domain_wlssDjxh").val()},
					   contentType: "application/x-www-form-urlencoded; charset=GBK", 
					   success:function(data){
						   //var htmls="<img src='' alt='"jcontextPath+"/resource/wlglpt/images/qq.jpg' width='100px' height='120px' />";
						  // $("#showPhoto").html(html);
						 //eval("var data="+data);
						  var htmls="";
						  for(var i=0;i<data.length;i++){
							  htmls +="<img id='"+data[i].zpscxh+"' src='/wlglpt/work/hygl/wlss_photo.jsp?photo_no="+data[i].zpscxh+"' onclick='deletePhoto("+data[i].zpscxh+")' alt='"+data[i].zpmc+"' />";
						  }
						 var winWidth= document.body.clientWidth;
						   $("#showPhoto").html(htmls);
						   $("img").load(function(){
								if(this.width>=winWidth){
									this.width=winWidth-100;
								}
							});
					   },
					   error:function(data){
						   showError("出现异常情况，请联系管理员！");
					   }
				});
			}else{
				$("#showBt").val("显示");
				$("#showPhoto").html("");
			}
			
		});
		
		
		
	});
	var ph_no='';
	function deletePhoto(photo_no){
		ph_no=photo_no;
		showConfirm("确定要删除该图片么？","toDelete");
	}
	function toDelete(){
		var url=jcontextPath+"/hygl/wlssdjzp!deletePhoto";
    	json={"zpscxh":ph_no,"domain.wlssDjxh":$("#mainForm_domain_wlssDjxh").val()};
    	ajaxCommon(url,json,"okDelete");
    	
	}
	function okDelete(){
		$("#"+ph_no).attr("src","").hide();
		showAlert("删除成功！");
    	onRefresh();
	}
	function calJeHj() {
		/*var wlssMxJes = $(".wlssMxJe");
			var je = 0;
			$.each(wlssMxJes, function(i, obj){
				je = je+(wlssMxJes[i].value)/1;
			});
			$("#mainForm_domain_hjSr").val(je.toFixed(2));*/
	}
	//自定义jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	function initSl(){
		$("#mainForm_domain_ssSl").val("");
		$("#mainForm_domain_ssTj").val("");
		$("#mainForm_domain_ssZl").val("");
		$("#mainForm_domain_ssSl").select();
	}
	function initWlssMxXl(){
		var mxts = $("#wlssMxTbody tr").length;
		for(var i=0;i<mxts;i++){
			
			var wlssyyWhXh = $(".wlssyyWhXhTd")[i].innerText;
			var wlssclfsDm = $(".wlssclfsDmTd")[i].innerText;
			var zrrDjxhs = $(".zeR")[i].innerText;
			//alert(mxts);
			//alert(wlssyyWhXh+wlssclfsDm);
			$("#wlssyyWhXhDiv select").val(wlssyyWhXh);
			$("#wlssclfsDmDiv select").val(wlssclfsDm);
			$("#qyryDiv select").val(zrrDjxhs);
			$("#sjDiv select").val(zrrDjxhs);
			var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
			var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
			//alert($("#wlssclfsDmDiv option:eq("$(".wlssclfsDmTd")[i].html()-1")").html());
			var gsRy = $("#qyryDiv").html();
			var sj = $("#sjDiv").html();
			$(".wlssyyWhXhTd")[i].innerHTML=wlssyyWhXhDiv;
			$(".wlssclfsDmTd")[i].innerHTML=wlssclfsDmDiv;
			var ssDmInit = $("#wlssMxTbody tr:eq("+i+") td:nth-child(5)").html();
			if($(ssDmInit).val() == 3){
				$(".zeR")[i].innerHTML=gsRy;
			}else if($(ssDmInit).val() == 1){
				$(".zeR")[i].innerHTML=sj;
			}
		}
		
		
		//物流损失来源下拉
		var wlssLybz=$("#mainForm_domain_wlssLybz").val();
		$("#wlssLybz").val(wlssLybz);
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	function saveOk(){
		showSuccess("保存成功！","closeWin");
	}
	function closeWin(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.hjSr","domain.ssSl","domain.ssZl","domain.ssTj"];
		var labelNameArray = ["物流损失金额","货物损失数量","货物损失重量","货物损失体积"];
		var compareValueArray = [14.2,12.2,12.2,12.2];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function getHw(id){
		
		var djxh=$("#mainForm_domain_pcDjxh").val();
		var id=$("#mainForm_domain_kh").val();
		var url=jcontextPath+"/hygl/wlssdj!getHw";
		var json={"domain.pcDjxh":djxh,"domain.fhrDjxh":id,"domain.conBz":'Y',"domain.conDm":'Y',"domain.currentObjId":"mainForm_domain_hw","domain.defaultValue":$("#mainForm_domain_hw").val()};
		ajaxCommon(url,json,"getHwOk");
	}
	
	function getHwOk(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = $("<option>").text(domain.mc).val(domain.dm);
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc).val(domain.dm);
		    }
		    
		    $("#"+data.domain.currentObjId).append(option);
		});
	}
	
	function addWlssMxInit() {
		var xh = $("#wlssMxTbody tr").length;
		var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
		var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
		$("#wlssyyWhXhTd"+xh).html(wlssyyWhXhDiv);
		$("#wlssclfsDmTd"+xh).html(wlssclfsDmDiv);
		var gsRy = $("#qyryDiv").html();
		var sj = $("#sjDiv").html();
		var ssDmSel = $("#zbTab tr:eq("+xh+") td:nth-child(5)").html();
	    if($(ssDmSel).val() == 3){
	    	$("#qyRY"+xh).html(gsRy);
	    }else if($(ssDmSel).val() == 1){
	    	$("#qyRY"+xh).html(sj);
	    }
	}
	function ssFun(obj,xh){
		var ssDmSel = $("#zbTab tr:eq("+xh+") td:nth-child(9)");
		var gsRy = $("#qyryDiv").html();
		var sj = $("#sjDiv").html();
		if($($(obj).html()).val() == 3){
			ssDmSel.html(gsRy);
		}else if($($(obj).html()).val() == 1){
	    	ssDmSel.html(sj);
	    }else{
			ssDmSel.html("");
		}
	}
	function changeBh() {
		var bhs = $(".bh");
		$.each(bhs, function(i, obj){
			$(obj).text(i+1);
		});
	}
	function delWlssMx() {
		var xhs = $(":checked[name='xhs'][value!='']");
		var wlssDjxh = $("#mainForm_domain_wlssDjxh").val();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.xhs");
			var jsonObj = {"domain.wlssDjxh":wlssDjxh,"domain.spbcbz":"N"};
			jsonStr += jQuery.param(jsonObj);
			//alert(jsonStr);
			var url = jcontextPath+"/hygl/wlssdj!deleteWlssdjMx";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelWlssMxSuc", false);
		}else {
			doDelWlssMxSuc(-1);
		}
	}
	function doDelWlssMxSuc(obj){
		var xhs = $(":checked[name='xhs']");
		$.each(xhs, function(i, obj){
			var td = $(obj).parent();
			var tr = $(td).parent();
			$(tr).remove();
		});
		if(obj!=-1){
			//alert(obj.domain.je)
			$("#mainForm_domain_hjSr").val(obj.domain.je);
		}else{
			calJeHj();
		}
		changeBh();
	}
</script>
</head>

<base target="_self" />
<body>
<%try{ %>
<s:form action="wlssdjzp!uploadFile" namespace="" method="post" id="mainForm"  enctype="multipart/form-data">
	<s:hidden name="domain.wsSpxh"></s:hidden>
	<s:hidden name="domain.pcDjxh"></s:hidden>
	<s:hidden name="domain.wlssDjxh"></s:hidden>
	<s:hidden name="domain.ddDjxh"></s:hidden>
	<s:hidden name="domain.hwmxxh"></s:hidden>
	<s:hidden name="domain.wfhDjxh"></s:hidden>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.khmc"></s:hidden>
	<s:hidden name="domain.hwmc"></s:hidden>
	<s:hidden name="domain.sl"></s:hidden>
	<s:hidden name="domain.zl"></s:hidden>
	<s:hidden name="domain.tj"></s:hidden>
	<s:hidden name="domain.jsSl"></s:hidden>
		<div class="pop_contc" >
			<fieldset>
					<legend>基本信息</legend>
			
			<table width="96%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="14%" align="right">派车单号：</td>
      				<td width="18%" align="left"><s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td width="14%" align="right">车辆号码：</td>
      				<td width="18%" align="left"><s:textfield name="domain.clhm4Query" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td width="14%" align="right">挂车号码：</td>
      				<td width="18%" align="left"><s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
					<td  align="right">司机：</td>
      				<td  align="left"><s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td  align="right">派车人：</td>
      				<td ><s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td align="right">派车日期：</td>
      				<td><s:textfield name="domain.pcrq" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
					<td  align="right">派车部门：</td>
      				<td ><s:textfield name="domain.pcJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td align="right">业务单位：</td>
      				<td ><s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td align="right">损失来源：</td>
      				<td >
      					<s:hidden name="domain.wlssLybz"></s:hidden>
      					<s:select list="#{'':' ---请选择---','0':' 直接登记','1':' 回单登记时','2':' 订单接收时' }" id="wlssLybz" cssClass="select" disabled="true"></s:select>
      				</td>
				</tr>
			</table>
			</fieldset>
			<fieldset>
					<legend>费用信息</legend>
			<table width="96%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				<tr>
      				<td width="14%" align="right"><font class="font_red">*</font>物流损失金额：</td>
      				<td width="18%">
      					<s:textfield name="domain.hjSr" id="mainForm_domain_hjSr" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="14%" align="right">客户：</td>
      				<td width="18%">
      					<s:textfield name="domain.Khmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right" width="14%">货物：</td>
      				<td width="18%">
      					<s:textfield name="domain.hwmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      			    </td>
      			</tr>			
      			<tr>
      				
      				<td align="right">货物损失数量：</td>
      				<td >
      					<s:textfield name="domain.ssSl" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">货物损失重量：</td>
      				<td >
      					<s:textfield name="domain.ssZl" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">货物损失体积：</td>
      				<td >
      					<s:textfield name="domain.ssTj" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right" style="display: none;">结算数量：</td>
      				<td style="display: none;">
      					<s:textfield id="jsSl" name="jsSl" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			
      			</tr>
      			
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_readonly" ></s:textarea>
      				</td>
      			</tr>

			</table>
		</fieldset>
		
		<!--	物流损失照片上传开始	-->
		
		<s:hidden name="domain.jsSl"></s:hidden>
		<fieldset>
			<legend>照片安检信息</legend>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				   <tr>
				    	<td width="10%" align="right"><font class="font_red">*</font> 物流损失照片</td>
				      	<td width="30%">
				      	   <s:file contenteditable="false" id="fjs" cssStyle="width:98%; height:25px;" name="zpfile" ></s:file>
				     
				 		</td>
				        <td width="10%" align="right">自定义照片名 </td>
				      	<td width="15%">
	      					<s:textfield name="zpfileName" cssClass="pop_input"></s:textfield>
						</td>
						<td width="10%">
				      		<button type="button" class="pop_btnbg" id="saveBtn">保存</button>
						</td>
				   </tr>
				   
			</table>
		</fieldset>
		
		
		<!-- 物流损失照片上传结束 -->
		
		
		<div style="margin:20px 0 20px 0;">
			<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
		      <tr>
		        <th width="5%">序号</th>
		        <th width="5%" style="display: none;"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="10%">金额</th>
		        <th width="15%">物流损失原因</th>
		        <th width="15%">损失处理方式</th>
		        <th width="15%">客户</th>
		        <th width="20%">货物</th>
		        <th width="20%">责任人</th>
		      </tr>
		       <tbody id="wlssMxTbody">
     		  <s:iterator id="zb" value="domain.wlssMxList" status="sta">
     		  
		      	<tr>
		        	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			        <td align="center" style="display: none;"><input type="checkbox" name="xhs" value="<s:property value="#zb.xh" />" /></td>
			       	<td align="center"><s:property value="#zb.je" /></td>
			       	<td class="wlssyyWhXhTd"><s:property value="#zb.wlssyyWhXh" /></td>
			        <td class="wlssclfsDmTd"><s:property value="#zb.wlssclfsDm" /></td>
			        <td align="center"><s:property value="domain.khmc" /></td>
			        <td align="center"><s:property value="domain.hwmc" /></td>
			        <td style="display: none;"><input type="hidden" class="xh" value="<s:property value="#zb.xh" />" /></td>
			        <td class="zeR"><s:property value="#zb.pcygCzyDjxh" /></td>
		      	</tr>
		     </s:iterator>
		     </tbody>
		    </table>
		</div>
		
		  <div align="center" style="width: 80%;">  物流损失照片：&nbsp;
		    <button type="button" class="pop_btnbg" id="showBt" >显示</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn" >关闭</button>
		   </div>
			<table id="bTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      	<tr>
			      		<div style="width: 760px" id="showPhoto" >
			      			<!--  <img src="" alt="" width="100px" height="120px" />-->
				        </div>
				    </tr>
			     
    		</table>
    		
		
		
		</div>
	<div id="wlssyyWhXhDiv" style="display: none;"><sys:Wlssyy myName="wlssyyWhXh" myClass="select wlssyyWhXh"></sys:Wlssyy></div>
	<div id="wlssclfsDmDiv" style="display: none;"><sys:Wlssclfs myName="wlssclfsDm" myClass="select wlssclfsDm"></sys:Wlssclfs></div>
	<div id="qyryDiv" style="display: none;">
		<sys:GsryList myId="qyRy" myName="qyRy" contaisQxz="false" myClass="select" ssJgbm="domain.zgsbm"></sys:GsryList>
	</div>
	<div id="sjDiv" style="display: none;">
		<s:select list="domain.sjList" name="domain.sjList" listKey="dm" listValue="mc" cssClass="select"></s:select>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>

