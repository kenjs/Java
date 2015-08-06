<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����Ǽ�</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			checkZfFs();
			
			
			
		});
		
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫɾ���ļ�¼��");
				return;
			}
			showConfirm("ȷ��Ҫɾ��ѡ�еļ�¼��", "delSrdj");
		})
		$("#mainForm_domain_je").val($("#mainForm_domain_wsfJe").val());
		$("#mainForm_domain_fkfmc").val($("#mainForm_domain_yfjsfDjmc").val());
		$("#yhShow").hide();
		
		$("#mainForm_domain_jbrCzyDjxh").attr("disabled","disabled");
	});

	function checkZfFs(){
		var zffsDm = $("#mainForm_domain_zffsDm").val();
		
		var djxh=$("#mainForm_domain_yfjsfDjxh").val();
		var jgbm=$("#mainForm_domain_ssJgbm").val();
		var url=jcontextPath+"/cwgl/cwysyfsrdj!checkZfFs";
		if(zffsDm=='4'){
			var json={"domain.yfjsfDjxh":djxh,"domain.ssJgbm":jgbm};
			ajaxCommon(url,json,"checkAfter");
		}
		else{
			onSave();
		}
	}
	
	function checkAfter(data){
		var je = trim($("#mainForm_domain_je").val());
		var ysJe=data.domain.je;
		var wsJe=data.domain.wsJe;
		//alert(parseFloat(ysJe))
		if(parseFloat(wsJe)>parseFloat(ysJe)){
			showError("�������˵ǼǸÿͻ��ĳ�ֵ��");
		}else if(parseFloat(wsJe)>0&&parseFloat(je)>parseFloat(ysJe)-parseFloat(wsJe)){
			showError("�������˵ǼǸÿͻ��ĳ�ֵ��");
		}else if(parseFloat(wsJe)<=0&&parseFloat(je)>parseFloat(ysJe)){
			showError("����Ǽǽ��ܴ��ڸ�Ԥ�ս�");
		}else{
			onSave();
		}
	}
	
	function onSave(){
		var ysyfDjxh = $("#mainForm_domain_ysyfDjxh").val();
		var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
		var yfjsfDjxh = $("#mainForm_domain_yfjsfDjxh").val();			 
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var djJgbm = $("#mainForm_domain_djJgbm").val(); 		
		var fkfmc = trim($("#mainForm_domain_fkfmc").val());
		var je = trim($("#mainForm_domain_je").val());
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		var zffsDm = $("#mainForm_domain_zffsDm").val();
		var jbrCzyDjxh =$("#mainForm_domain_jbrCzyDjxh").val(); 
		var rq = $("#mainForm_domain_rq").val();
		var yhCshDjxh = $("#mainForm_domain_yhCshDjxh").val();
		var yhhdh = trim($("#mainForm_domain_yhhdh").val());
		if(zffsDm != "2"){
		   yhhdh = "";
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
		}else{
			if(yhCshDjxh==null||yhCshDjxh==""){
				showAlert("�������ʲ���ʼ����ά�������˺ţ�");
				return;
			}
		}
		
		var bz = trim($("#mainForm_domain_bz").val()); 
		//���У��
		var jyJe = parseFloat(je);
		var wsfJe = parseFloat($("#mainForm_domain_wsfJe").val());
		if(jyJe<=0){
           showAlert("��������С�ڵ��� 0 ��");
           return;
        }else if(jyJe>wsfJe){
           showAlert("�������ɴ���δ�����");
           return;
        }
		
		var url = jcontextPath+"/cwgl/cwysyfsrdj!save";  
        var jsonObj = {"domain.ysyfDjxh":ysyfDjxh,"domain.yfjsfDm":yfjsfDm,"domain.yfjsfDjxh":yfjsfDjxh,"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm,
                       "domain.fkfmc":fkfmc,"domain.je":je,"domain.zcflDm":zcflDm,"domain.zffsDm":zffsDm,
                       "domain.jbrCzyDjxh":jbrCzyDjxh,"domain.rq":rq,"domain.yhCshDjxh":yhCshDjxh,"domain.yhhdh":yhhdh,"domain.bz":bz};
        ajaxCommon(url,jsonObj,"YesSave");
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fkfMc","domain.je","domain.zcflDm","domain.zffsDm","domain.jbrCzyDjxh",
		                        "domain.rq","domain.yhCshDjxh","domain.yhhdh","domain.bz"];
		var labelNameArray = ["���","���","֧����ʽ","�ʲ�����","������",
		                      "����","���г�ʼ�����","���лص���","��ע"];
		var compareValueArray = [100,14.2,2,2,16,
			                     20,16,50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,true,true,
		                    true,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function YesSave(){ 
        showAlert("����ɹ���", "refresh");
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}

	function delSrdj() {
		var xhs = $(":checked[name='xhs'][value!='']");
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.srDjxhs");
			
			var url = jcontextPath+"/cwgl/cwysyfsrdj!delete";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelSrdjSuc", false);
		}
	}
	
	function doDelSrdjSuc(){ 
        showAlert("ɾ���ɹ���", "refresh");
	}
	//ˢ�µ���
	function refresh(){			
	   var ysyfDjxh = $("#mainForm_domain_ysyfDjxh").val();
	   var url = jcontextPath+"/cwysyfsrdj!initMx?domain.ysyfDjxh="+ysyfDjxh;
	   reload.href = url;
	   reload.click();
	}
	function onZffs(){
	   var zffsDm = $("#mainForm_domain_zffsDm").val();
	   var zcflDm = "1"+zffsDm;
	   $("#mainForm_domain_zcflDm").val(zcflDm);
	   if(zffsDm=="2"){
	      $("#yhShow").show();
	   }else{
	      $("#yhShow").hide();
	   }
	}
	function onZcfl(){
	  var zcflDm = $("#mainForm_domain_zcflDm").val();
	  $("#mainForm_domain_zffsDm").val(zcflDm.charAt(1));
	  if(zcflDm.charAt(1)=="2"){
	      $("#yhShow").show();
	   }else{
	      $("#yhShow").hide();
	   }
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="cwfsfysrdj!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
        <div  style="display:none"><a id="reload" href="">reload</a></div>
		<s:hidden name="domain.ysyfDjxh"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.yfjsfDm"></s:hidden>
		<s:hidden name="domain.yfjsfDjxh"></s:hidden>
		<div id="maincont">
		<div class="pop_contc">
		<fieldset>
			<legend>������Ϣ</legend>
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
      				<td align="right">Ӧ�ս�</td>
      				<td>
      					<s:textfield name="domain.ysfJe" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">���ս�</td>
      				<td>
      					<s:textfield name="domain.yisfJe" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">δ�ս�</td>
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
			<legend>����Ǽ�</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				  <tr>
				    <td align="right" width="13%"><font class="font_red">*</font>��</td>
	  				<td width="23%">
	  				     <s:textfield name="domain.je" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
	  				</td>
	  				<td align="right" width="12%"><font class="font_red">*</font>�����</td>
	                <td width="20%">
	                     <s:textfield name="domain.fkfmc" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
	                </td>
	                <td align="right" width="12%"><font class="font_red">*</font>֧����ʽ��</td>
	                <td width="20%">
	                    <sys:Zffs myName="domain.zffsDm" myId="mainForm_domain_zffsDm" myClass="select" onChange="onZffs()" khysBz="Y"></sys:Zffs>
	                </td>	  					
	  			 </tr> 			
	  			 <tr>
	  			    
	  			    <td align="right" width="13%"><font class="font_red">*</font>�ʲ����ࣺ</td>
	  				<td width="20%">
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
	  			  <tr id="yhShow">
	  				<td align="right">�����˺ţ�</td>
	                <td>
	                     <sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:yhzh>
	                </td>
	  				<td align="right" >���лص��ţ�</td>
	  				<td >
	  				     <s:textfield name="domain.yhhdh" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
	  				</td>	
	  			 </tr>
	  			 <tr>
	  			 	<td align="right">��ע��</td>
	  			 	<td colspan="5">
	  			 		<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
	  			 	</td>
	  			 </tr>
    		</table>
    		</fieldset>
    		<div style="width: 100%;overflow-x:auto;padding: 5px 0 20px 0;overflow-y:hidden;">
			<table id="zTab" width="1200px" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="30px">���</th>
			        <th width="30px"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
			        <th width="120px">���</th>
			        <th width="50px">���</th>
			        <th width="70px">֧����ʽ</th>
			        <th width="70px">�ʲ�����</th>			        
			        <th width="70px">������</th>
			        <th width="70px">����</th>
			        <th width="100px">��������</th>
			        <th width="80px">�����˺�</th>
			        <th width="70px">���лص���</th>
			        <th width="200px">��ע</th>
			        <th width="70px">�Ǽ���</th>
			        <th width="70px">�Ǽ�����</th>
			        <th width="100px">�Ǽǲ���</th>
			      </tr>
			      <s:iterator id="zb" value="domain.dataList" status="i">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.srDjxh" />" /></td>
				        <td align="center"><s:property value="#zb.fkfmc"/></td>
				        <td align="center"><s:property value="#zb.je"/></td>
				        <td align="center"><s:property value="#zb.zffsMc"/></td>
				        <td align="center"><s:property value="#zb.zcflMc"/></td>
				        <td align="center"><s:property value="#zb.jbrCzyDjmc"/></td>
				        <td align="center"><s:property value="#zb.rq"/></td>
				        <td align="center"><s:property value="#zb.yhmc"/></td>
				        <td align="center"><s:property value="#zb.yhzh"/></td>
				        <td align="center"><s:property value="#zb.yhhdh"/></td>
				        <td align="center"><s:property value="#zb.bz"/></td>
				        <td align="center"><s:property value="#zb.djrCzyDjmc"/></td>
				        <td align="center"><s:property value="#zb.djrq"/></td>
				        <td align="center"><s:property value="#zb.djJgmc"/></td>
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
