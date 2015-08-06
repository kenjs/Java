<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>人员选择</title>
<script type="text/javascript">
	$(function(){	
		onLoadTree();
		
		$("#saveBtn").click(function(){	
			var selMenuids=getSelMenuDms();	
			if(selMenuids != ""){
				var menuStrs = selMenuids.split("||"); //字符分割    
				var rylbs=menuStrs[0].substring(0,menuStrs[0].length-1);
				var values = menuStrs[1].substring(0,menuStrs[1].length-1);
				var names = menuStrs[2].substring(0,menuStrs[2].length-1);		
				window.dialogArguments.czrxhs =values;
				window.dialogArguments.rymcs =names;
				window.dialogArguments.rylbs =rylbs;
				window.close();
			}
		});
	});		
	
	function onLoadTree(){		
		var url = jcontextPath+"/common/ryxzCommon!buildTreeStr";	
		var jsonObj = {};
		ajaxCommon(url,jsonObj,"doBulidTreeSuccess");
	}
	
	function doBulidTreeSuccess(data){
		var qyryTree = data.domain.qyryTree;	
		var fbsryTree = data.domain.fbsryTree;	
		$("#qxTreeId1").html(qyryTree);		
		$("#qxTreeId2").html(fbsryTree);	
		$(function(){
			$('#qxTreeId1').lightTreeview({
				fileico: false,
				folderico: false,
				collapse: false,
				line: true,
				nodeEvent: false,
				unique: false,
				style:"",
				animate: 0
			});		
			$('#qxTreeId2').lightTreeview({
				fileico: false,
				folderico: false,
				collapse: false,
				line: true,
				nodeEvent: false,
				unique: false,
				style:"",
				animate: 0
			});		
		})
		$.lightTreeview.open('#qxTreeId1 ul');	
		$.lightTreeview.open('#qxTreeId2 ul');	
	}
	
	function onSelect(id,checked){	
		//2为公司人员，6为分包商
		if("2" ==id.split("-")[0] || "6" ==id.split("-")[0] ){
			if("Z" ==id.split("-")[1]){
				var allFollowedMenusIds= getAllNodeIdByCurdId(id).split(",");
				var curId="";
				for(var i=0;i<allFollowedMenusIds.length;i++){
					curId=allFollowedMenusIds[i];
					if(undefined!=document.getElementById(curId)){
						if(checked==false){
							document.getElementById(curId).checked=false;
						}else{
							document.getElementById(curId).checked=true;
						}
					}
				}
			}
		}		
	}	
	
		//获取选中的复选框(对应的菜单为非目录结点)对应的value
	function getSelMenuDms(){
		var qyry=document.getElementsByName("qyry");
		var fbsry=document.getElementsByName("fbsry");
		var resultValue="";
		var resultName="";
		var resultRylb="";
		var id;
		var event;
		for(var i=0;i<qyry.length;i++){
			if("J"==qyry[i].id.split("-")[1]){
				if(qyry[i].checked==true){
					resultRylb+="2"+",";
					resultValue+=qyry[i].value+",";
					id = "#"+qyry[i].id;					
					event =$(id).parent();
					resultName += event.text()+",";
					
				}
			}
		}
		for(var i=0;i<fbsry.length;i++){
			if("J"==fbsry[i].id.split("-")[1]){
				if(fbsry[i].checked==true){
					resultRylb+="6"+",";
					resultValue+=fbsry[i].value+",";
					id = "#"+fbsry[i].id;					
					event =$(id).parent();
					resultName += event.text()+",";
				}
			}
		}
		
		return resultRylb+"||"+resultValue +"||"+resultName;
	}
	
	function getAllNodeIdByCurdId(id){
		var returnValues ="";
		if(!isUndefinedOrEmpty(id)){
			var menuDm=id.split("-")[3];
			var rootId=id.split("-")[0];
			var menus;
			if(rootId == "2"){
				menus=document.getElementsByName("qyry");
			}
			if(rootId == "6"){
				menus=document.getElementsByName("fbsry");
			}
			var curMenuId="";
			for(var i=0;i<menus.length;i++){
				curMenuId=menus[i].id;
				if(!isUndefinedOrEmpty(curMenuId)&&menuDm==curMenuId.split("-")[2]){
					returnValues+=curMenuId+",";
					if("Z"==curMenuId.split("-")[1]){
						returnValues+=getAllNodeIdByCurdId(curMenuId);
					}
				}
			
			}
		}
		return returnValues;
	}
	function isUndefinedOrEmpty(str){
		if(undefined==str||""==str){
			return true;
		}else{
			return false;
		}
	}
	
</script>
<base target="_self" />
</head>
<body >
<%try{ %>
<s:form action="ryxzCommon!ryxzCommonInit"  namespace="/common" method="post" id="mainForm" name="mainForm">	
	<div class="right_cont">
		<table border="0" cellspacing="0" cellpadding="0" class="poptab_css">
   	 		<tr>
				<td ><div style="overflow:auto; width:400px; height:440px; margin-top:8px;text-align:left;">			
					<div id="xtflMcId1"><img src='<sys:context/>/resource/pageframe/images/closedfolder1.gif'/><strong>公司内部人员</strong></div>		
					<ul id="qxTreeId1" class="lightTreeview"></ul>	
					<div id="xtflMcId2"><img src='<sys:context/>/resource/pageframe/images/closedfolder1.gif'/><strong>分包商人员</strong></div>		
					<ul id="qxTreeId2" class="lightTreeview"></ul>	
				</div>
			</td>
		</tr>
		</table>
  		<div class="btn">
			<button type="button" id="saveBtn" class="pop_btnbg">确 定</button>&nbsp;
    		<button type="button" class="pop_btnbg" onclick="javascript:window.close();">关 闭</button>
 	 	</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
