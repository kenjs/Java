<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>


<head>
<title>照片安检</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
	
		var photo=$("#fjs").val();
		if(trim(photo)==''){
			document.getElementById("div1").src=jcontextPath+"/resource/wlglpt/images/qq.jpg";
			document.getElementById("div1").style.width="110px";
			document.getElementById("div1").style.height="90px";
		}
		var bz=$("#mainForm_domain_bz").val();
		if(bz!=''){
			$("#mainForm_domain_bz").val("");
		}
		var tag=$("#mainForm_domain_tage").val();
		if(tag=='1'){
			showSuccess("保存成功！","");
		}
		
		$("#closeBtn").click(function(){
			window.close();
		})
		
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
			$("#mainForm").attr("action",jcontextPath+"/hyzpaj!save");
			 $("#mainForm").submit();
		})
	});
	
	function photoes(){
		var photo=$("#fjs").val();
		if(photo!=null && trim(photo)!=''){
			document.getElementById("div1").src=photo;
			document.getElementById("div1").style.width="110px";
			document.getElementById("div1").style.height="90px";
		}
	}
	function checkdata(){
		var controlNameArray = ["domain.ajzp","domain.bz","domain.pcrCzyDjxh","domain.djJgbm4Query","domain.ssJgbm","domain.pcrq"];
		var labelNameArray = ["派车单号","备注","派车人","派车部门","所属机构","派车日期"];
		var compareValueArray = [16,40,40,40,40,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [false,false,true,true,false,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
		//<img src=jcontextPath+"/hyzpaj!photo?photo_no=1000000172" />
	}
</script>


<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="hyzpaj!initMx" namespace="" method="post" id="mainForm"  enctype="multipart/form-data">
	
	<s:hidden name="domain.pcDjxh"></s:hidden>
	<s:hidden name="domain.tage"></s:hidden>
	<s:hidden name="domain.pcrCzyDjxh"></s:hidden>
	<s:hidden name="domain.pcJgbm"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.pcrq"></s:hidden>
	<div class="pop_contc" style="height:700px; overflow:auto;">
		<fieldset>
			<legend>照片安检信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				 
				
					
				
				   
				   
				   <tr>
				    	<td width="10%" align="right"><font class="font_red">*</font> 安检照片</td>
				      	<td width="30%">
				      	   <s:file contenteditable="false" onchange="photoes()" id="fjs" cssStyle="width:98%; height:25px;" name="fjFile" ></s:file>
				     
				 		</td>
				        <td width="10%" align="right">安检照片 </td>
				      	<td width="30%">
				      	
				      			<div style="height: 90px;margin-top:0px;">
										<img id="div1" src="" />
								</div>		
						</td>
				   </tr>
				    <tr>
				    	<td width="10%" align="right">备注 </td>
				      	<td width="30%" colspan="3">
				      		 <s:textfield name="domain.bz" cssClass="pop_input_colspan2 "  ></s:textfield>
				     	</td>
				        
				   </tr>
			</table>
		</fieldset>
		
		<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div>
		
		<fieldset>
				<legend>派车信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
				  <tr>
	  				<td align="center" width="13%">派车单号</td>
	  				<td width="20%">
	  					<s:property value="domain.pcDh"/>
	  				</td>
	  				<td align="center">司机姓名</td>
	  				<td>
	  					<s:property value="domain.sjXm"/>
	  				</td>
	  			 </tr>
	  			 <tr>
	  				<td align="center">车辆号码</td>
	  				<td>
	  					<s:property value="domain.clHm"/>
	  				</td>
	  				<td align="center">挂车号码</td>
	  				<td>
	  					<s:property value="domain.gcHm"/>
	  				</td>
	  				
	  			</tr>
	  			
	  			<tr>
	  				<td align="center">派车人</td>
	  				<td>
	  					<s:property value="domain.pcrCzyMc"/>
	  				</td>
	  				<td align="center">派车日期</td>
	  				<td>
	  					<s:property value="domain.pcrq"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td align="center">派车部门</td>
	  				<td>
	  					<s:property value="domain.pcXm"/>
	  				</td>
	  				<td align="center">所属机构</td>
	  				<td>
	  					<s:property value="domain.ssXm"/>
	  				</td>
	  			</tr>
    		</table>
    		</fieldset>
		
		
				安检电话信息：
			<table id="zTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="5%">序号</th>
			        <th width="15%">安检电话</th>
			        <th width="15%">安检结果</th>
			        <th width="15%">安检人</th>
			        <th width="15%">安检日期</th>
			        <th width="15%">安检情况描述</th>
			        <th width="20%">备注</th>
			      </tr>
			      <s:iterator id="zb" value="domain.ajdhList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center"><s:property value="#zb.ajDhhm"/></td>
				        <td align="center"><s:property value="#zb.ajjg"/></td>
				        <td align="center"><s:property value="#zb.pcrCzyMc"/></td>
				        <td align="center"><s:property value="#zb.pcrq"/></td>
				        <td align="center"><s:property value="#zb.ajQkms"/></td>
				        <td align="center"><s:property value="#zb.bz"/></td>
			      	</tr>
			      </s:iterator>
    		</table>
    	
    		安检照片：
			<table id="bTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      
			  
			      	<tr>
			      		<div style="width: 760px">
			      	  		 <s:iterator id="zb" value="domain.ajzpList" status="i">
			      	   			<img src="work/hygl/zpaj_photo.jsp?photo_no=<s:property value="#zb.ajDjxh"/>" alt="<s:property value="#zb.pcrq"/>" width="100px" height="120px" />
			      	   		</s:iterator>
				        </div>
				    </tr>
			     
    		</table>
  
	 
			
		
   
    		
    	
		
    </div> 
  
 
 
　
    
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
