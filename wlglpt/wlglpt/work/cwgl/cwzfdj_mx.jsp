<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-֧���Ǽ���ϸ</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ysyfDjxh = trim($("#mainForm_domain_ysyfDjxh").val()); 
			var yfjsfDm = trim($("#mainForm_domain_yfjsfDm").val()); 
			var yfjsfDjxh = trim($("#mainForm_domain_yfjsfDjxh").val()); 
			var skfmc = trim($("#mainForm_domain_skfmc").val()); 
			var je = trim($("#mainForm_domain_je").val()); 
			if(je/1<=0){
				showAlert("���������0��");
				return;
			}
			var rq = trim($("#mainForm_domain_rq").val()); 
			var zffsDm = trim($("#mainForm_domain_zffsDm").val()); 
			var zcflDm = trim($("#mainForm_domain_zcflDm").val()); 
			
			var yhCshDjxh = $("#mainForm_domain_yhCshDjxh").val(); 
			var yhmc = trim($("#mainForm_domain_yhmc").val()); 
			var hm = trim($("#mainForm_domain_hm").val()); 
			var zh = trim($("#mainForm_domain_zh").val()); 
			var zzrq = trim($("#mainForm_domain_zzrq").val());
			if(zffsDm != "2"){	   
			   yhmc = "";
			   hm = "";
			   zh = "";
			   zzrq = "";
			   var val = $("#mainForm_domain_zcflDm").find("option:selected").text();
			   var strs=val.split(" "); //�ַ��ָ�      
			   yhCshDjxh = strs[2];
			   var showStr;
			   if(zffsDm=="1"){
			   		showStr="�������ʲ���ʼ����ά���ֽ�";
			   }else{
			   		showStr="�������ʲ���ʼ����ά���Ϳ���";
			   }
			   if(yhCshDjxh==null||yhCshDjxh==""){
					showAlert(showStr);
					return;
				}
			}else if(yhCshDjxh==""||yhCshDjxh==null){
				showAlert("�������ʲ���ʼ����ά�������˺ţ�");
				return;
			}
			var lkr = trim($("#mainForm_domain_lkr").val()); 
			var lkrZjlxDm = trim($("#mainForm_domain_lkrZjlxDm").val()); 
			var lkrZjhm = trim($("#mainForm_domain_lkrZjhm").val()); 
			var lkrq = trim($("#mainForm_domain_lkrq").val()); 
			 
			var jbrCzyDjxh = trim($("#mainForm_domain_jbrCzyDjxh").val()); 
			var shrCzyDjxh = trim($("#mainForm_domain_shrCzyDjxh").val()); 
			var shrq = trim($("#mainForm_domain_shrq").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var url = jcontextPath+"/cwzfdj!save";  
	    	var jsonObj = {"domain.ysyfDjxh":ysyfDjxh,"domain.yfjsfDm":yfjsfDm,"domain.yfjsfDjxh":yfjsfDjxh,
                           "domain.skfmc":encodeURI(skfmc),"domain.je":je,"domain.rq":rq,"domain.zffsDm":zffsDm,
                           "domain.zcflDm":zcflDm,"domain.yhCshDjxh":yhCshDjxh,"domain.lkr":encodeURI(lkr),"domain.lkrZjlxDm":lkrZjlxDm,
                           "domain.lkrZjhm":lkrZjhm,"domain.lkrq":lkrq, "domain.yhmc":yhmc,
                           "domain.hm":hm,"domain.zh":zh, "domain.zzrq":zzrq,
                           "domain.jbrCzyDjxh":jbrCzyDjxh,"domain.shrCzyDjxh":shrCzyDjxh, "domain.shrq":shrq,
                           "domain.bz":bz,"domain.djJgbm":djJgbm,"domain.ssJgbm":ssJgbm
                           };
			ajaxCommon(url,jsonObj,"saveOK");
		});
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫɾ���ĸ��ټ�¼��");
				return;
			}
			showConfirm("ȷ��Ҫɾ��ѡ�еĸ��ټ�¼��", "delCwZfdj");
		})
		$("#mainForm_domain_je").val($("#mainForm_domain_wsfJe").val());
		$("#mainForm_domain_skfmc").val($("#mainForm_domain_yfjsfDjmc").val());
		$(".showYh").hide();
		
		$("#mainForm_domain_jbrCzyDjxh").attr("disabled","disabled");
	});
	
	function delCwZfdj() {
		var xhs = $(":checked[name='xhs'][value!='']");
		//showMessage();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.zfDjxhs");
			
			var url = jcontextPath+"/cwgl/cwzfdj!delete";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelCwZfdjSuc", false);
		}
	}
	
	function doDelCwZfdjSuc(){
        showAlert("ɾ���ɹ���", "refresh");
	}
	
	function saveOK(){
		showSuccess("����ɹ���","refresh");
	}
	//ˢ�µ���
	function refresh(){			
	   var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
	   var ysyfDjxh = $("#mainForm_domain_ysyfDjxh").val(); 
	   var url = jcontextPath+"/cwgl/cwzfdj!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.ysyfDjxh="+ysyfDjxh;
	   reload.href = url;
	   reload.click();
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	function checkdata(){

		var controlNameArray = ["domain.je", "domain.rq", "domain.zffsDm", 
								"domain.zcflDm", "domain.jbrCzyDjxh", "domain.djrCzyDjxh"];
		var labelNameArray = ["���", "����", "֧����ʽ", "�ʲ�����", "������", "�Ǽ���"];
		var compareValueArray = [14.2, 20, 1, 2, 16, 16];
		var nodeTypeArray = [NodeType.DECIMAL, NodeType.STRING, NodeType.STRING, 
							NodeType.STRING, NodeType.STRING, NodeType.STRING
							];
		var notNullArray = [true, true, true, true, true, true];

		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function onZffs(){
	   var zffsDm = $("#mainForm_domain_zffsDm").val();
	   var zcflDm = "1"+zffsDm;
	   $("#mainForm_domain_zcflDm").val(zcflDm);
	   if(zffsDm=="2"){
	      $(".showYh").show();
	      updateYh();
	   }else{
	      $(".showYh").hide();
	   }
	}
	function onZcfl(){
	  var zcflDm = $("#mainForm_domain_zcflDm").val();
	  $("#mainForm_domain_zffsDm").val(zcflDm.charAt(1));
	  if(zcflDm.charAt(1)=="2"){
	      $(".showYh").show();
	      updateYh();
	   }else{
	      $(".showYh").hide();
	   }
	}
	
	function updateYh(){
	  var yhCshDjxh = trim($("#mainForm_domain_yhCshDjxh").val()); 
	  if (yhCshDjxh == null || yhCshDjxh == "") {
		  return;
	  }
	  var url = jcontextPath+"/cwzfdj!doGetYh";  
	  var jsonObj = {"domain.yhCshDjxh":yhCshDjxh};
	  ajaxCommon(url,jsonObj,"updateOK");
	}
	function updateOK(data){
	  $("#mainForm_domain_yhmc").val(data.domain.yhmc);
	  $("#mainForm_domain_hm").val(data.domain.hm); 
	  $("#mainForm_domain_zh").val(data.domain.zh);
	}
</script>
<base target="_self" />
</head>

<body >
<%try{ %>
<s:form action="cwzfdj!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
        <div  style="display:none"><a id="reload" href="">reload</a></div>
<!--	Ӧ��Ӧ���ǼǴ���-->
	<s:hidden name="domain.ysyfDjxh"></s:hidden>
	<s:hidden name="domain.djJgbm"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.yfjsfDm"></s:hidden>
	<s:hidden name="domain.yfjsfDjxh"></s:hidden>
		<div id="maincont">
		<div class="pop_contc" >
		<fieldset>
			<legend>Ӧ��</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				     <td align="right">��Դ��</td>
      				<td>
      					<s:textfield name="domain.ysyflyMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">���㷽��</td>
      				<td width="20%">
      					<s:textfield name="domain.yfjsfMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">���ƣ�</td>
      				<td width="20%">
      					<s:textfield name="domain.yfjsfDjmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				
      			</tr>
      			<tr>
      				<td width="13%" align="right">���</td>
      				<td width="20%">
      					<s:textfield name="domain.kmdlMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">��Ŀ��</td>
      				<td>
      					<s:textfield name="domain.kmxlMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">ҵ��Ǽ���ţ�</td>
      				<td>
      					<s:textfield name="domain.ywDjxh" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">Ӧ����</td>
      				<td>
      					<s:textfield name="domain.ysfJe" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�Ѹ���</td>
      				<td>
      					<s:textfield name="domain.yisfJe" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">δ����</td>
      				<td>
      					<s:textfield name="domain.wsfJe" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      			    <td align="right">�������ڣ�</td>
      				<td>
      					<s:textfield name="domain.csrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�Ǽǲ��ţ�</td>
      				<td>
      					<s:textfield name="domain.djJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">������λ��</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				
      			</tr>
      			<tr>
	  			 	<td align="right">˵����</td>
	  			 	<td colspan="5">
	  			 		<s:textarea name="domain.sm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_readonly" ></s:textarea>
	  			 	</td>
	  			 </tr>
			</table>
    	 </fieldset>
      	<fieldset>
      		<legend>֧���Ǽ�</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				  <tr>
				    <td align="right" width="13%"><font class="font_red">*</font>��</td>
	  				<td width="23%">
	  				     <s:textfield name="domain.je" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
	  				</td>
	  				<td align="right" width="12%">�տ��</td>
	                <td width="20%">
	                     <s:textfield name="domain.skfmc" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
	                </td>
	                <td align="right" width="12%"><font class="font_red">*</font>֧����ʽ��</td>
	                <td width="20%">
	                    <sys:Zffs myName="domain.zffsDm" myId="mainForm_domain_zffsDm" myClass="select" onChange="onZffs()"></sys:Zffs>
	                </td>
	  			 </tr> 			
	  			 <tr>
	  			    <td align="right" ><font class="font_red">*</font>�ʲ����ࣺ</td>
	  				<td >
	  				     <sys:ZcflCsh myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" onChange="onZcfl()" ssJgbm="domain.ssJgbm"></sys:ZcflCsh>
	  				</td>
	  				<td align="right" ><font class="font_red">*</font>�����ˣ�</td>
	                <td >
	                    <sys:GsryList myName="domain.jbrCzyDjxh" myId="mainForm_domain_jbrCzyDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:GsryList>
	                </td>
	  				<td align="right" ><font class="font_red">*</font>���ڣ�</td>
	  				<td >
	  				     <sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" />
	  				</td>	
	  			 </tr>
      				
      				<tr class="showYh">
      				    <td  align="right" >�����˺ţ�</td>
		      			<td>
		      				 <sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select" ssJgbm="domain.ssJgbm" onChange="updateYh()"></sys:yhzh>
		      			</td>
		      			<td  align="right" >ת�����ڣ�</td>
		      			<td >
	          				<sys:dateCurrentDayTag myName="domain.zzrq" myId="mainForm_domain_zzrq" myClass="ymdate" />
		      			</td>
		      			
		      			<td style="display: none;">
		      			  <input type="hidden" id="mainForm_domain_yhmc" value="<s:property value="domain.yhmc" />"/>
		      			  <input type="hidden" id="mainForm_domain_hm" value="<s:property value="domain.hm" />"/>
		      			  <input type="hidden" id="mainForm_domain_zh" value="<s:property value="domain.zh" />"/>
		      			</td>
      				</tr>
      				<tr>
      				    <td  align="right" >����ˣ�</td>
		      			<td >
		      				<s:textfield name="domain.lkr" cssClass="pop_input bgstyle_optional" ></s:textfield>
		      			</td>
		      			<td  align="right" >֤�����ͣ�</td>
		      			<td >
		      				<sys:zjlx myId="mainForm_domain_lkrZjlxDm" myName="domain.lkrZjlxDm" myClass="select" contaisQxz="true"></sys:zjlx>
		      			</td>
		      			<td  align="right">֤�����룺</td>
		      			<td >
		      				<s:textfield name="domain.lkrZjhm" cssClass="pop_input bgstyle_optional" ></s:textfield>
		      			</td>
      				</tr>
      				<tr>
      				    <td  align="right" >������ڣ�</td>
		      			<td >
	          				<sys:dateCurrentDayTag myName="domain.lkrq" myId="mainForm_domain_lkrq" myClass="ymdate" />
		      			</td>
		      			<td  align="right" >����ˣ�</td>
		      			<td >
		      			    <sys:GsryList myName="domain.shrCzyDjxh" myId="mainForm_domain_shrCzyDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:GsryList>
		      			</td>
		      			<td  align="right" >������ڣ�</td>
		      			<td >
	          				<sys:dateCurrentDayTag myName="domain.shrq" myId="mainForm_domain_shrq" myClass="ymdate" />
		      			</td>
		      			
      				</tr>
      				<tr>
		      			<td  align="right">��ע��</td>
		      			<td colspan="5">
		      				<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
		      			</td>
      				</tr>
			</table>
			</fieldset>
			<div style="width: 100%;overflow-x:auto;padding: 5px 0 20px 0;overflow-y:hidden;">
			<table id="zTab" width="1080px" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="30px">���</th>
			        <th width="30px"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
			        <th width="120px">�տ</th>
			        <th width="50px">���</th>
			        <th width="70px">֧����ʽ</th>
			        <th width="70px">�ʲ�����</th>
			        <th width="70px">������</th>
			        <th width="70px">����</th>
			        <th width="100px">��������</th>
			        <th width="80px">�˺�</th>
			        <th width="200px">��ע</th>
			        <th width="70px">�Ǽ���</th>
			        <th width="70px">�Ǽ�����</th>
			        
			      </tr>
			      <s:iterator id="zb" value="domain.dataList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.zfDjxh" />" /></td>
				        <td align="center"><s:property value="#zb.skfmc"/></td>
				        <td align="center"><s:property value="#zb.je"/></td>
				        <td align="center"><s:property value="#zb.zffsMc"/></td>
				        <td align="center"><s:property value="#zb.zcflMc"/></td>
				        <td align="center"><s:property value="#zb.jbrCzyMc"/></td>
				        <td align="center"><s:property value="#zb.rq"/></td>
				        <td align="center"><s:property value="#zb.yhmc"/></td>
				        <td align="center"><s:property value="#zb.zh"/></td>
				        <td align="center"><s:property value="#zb.bz"/></td>
				        <td align="center"><s:property value="#zb.djrCzyMc"/></td>
				        <td align="center"><s:property value="#zb.djrq"/></td>
			      	</tr>
			      </s:iterator>
    		</table>
   		</div>
		<div class="pop_btn" style="margin-top: 10px;">
			   <button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="deleteBtn">ɾ ��</button>
				 &nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
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
