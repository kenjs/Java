<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>角色权限维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
	.td{border: 1px;border-color: gray;border-style: solid;height: 25px;}
</style>
<script type="text/javascript" language="javascript">
		var	treeTag=1;
		$(function()
		{
			var jgbm=$("#mainForm_domain_sjJgbm").val();
			getGwmc(jgbm);
			
			$("#addBtn").click(function()
			{
				var str=$("#chan").val();
				if(trim(str) == '' || trim(str) == 1){
					var selJsDm = $("#selJsDmId").val(); 
					if(selJsDm==null || selJsDm==undefined || trim(selJsDm)==""){
 					showError("请选择一个角色！");
 					return false;
 					}
 					var allQueryNoneNodeGnmkDms=getAllQueryNoneNodeGnmkDms();
					var selDmsStr=getSelMenuDms();
					var url = jcontextPath+"/gnqx/jsqxwh!save";	
					var jsonObj = {"domain.selJsDm":selJsDm,"domain.selDmsStr":selDmsStr,"domain.allQueryNoneNodeGnmkDms":allQueryNoneNodeGnmkDms,"domain.flag":'1'};
					showMessage(); 
					ajaxCommon(url,jsonObj,"doSaveQxJsSucc");		
 				}   
				if(str == 2){
					var selGnmkDmId = $("#selGnmkDmId").val(); 
					var jsDm = $("#jsDm").val(); 
					if(selGnmkDmId==null || selGnmkDmId==undefined || trim(selGnmkDmId)==""){
 					showError("请选择一个功能模块！");
 					return false;
 					}
 					var selJsDmsStr=getSelJsDms();
 					selGnmkDm=getSelGnmkDm();
 					var url = jcontextPath+"/gnqx/jsqxwh!save";	
					var jsonObj = {"domain.selJsDmsStr":selJsDmsStr,"domain.selGnmkDm":selGnmkDm,"domain.flag":'2',"domain.jsList":jsDm};
					showMessage(); 
					ajaxCommon(url,jsonObj,"doSaveQxJsSucc");
				}
			})
		})

	//页面加载时，获取树
		function getStree(value){
			treeTag=value;
			var url = jcontextPath+"/gnqx/jsqxwh!queryTreeStr";	
			var jsonObj = {"domain.tag":treeTag};
			ajaxCommon(url,jsonObj,"doGetTreeSucc");
		}
		
		function doGetTreeSucc(data){
		var treeStr=data.domain.treeStr;
		var gen='权限目录';
		if(1==treeTag){
			$("#xtflMcId1").html("<img src='<sys:context/>/resource/pageframe/images/openedfolder.gif'/>"+gen);
				document.getElementById("qxTreeId1").innerHTML=treeStr;
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
				})
				$.lightTreeview.open('#qxTreeId1 ul')
		    }else if(2==treeTag){
		   		$("#xtflMcId2").html("<img src='<sys:context/>/resource/pageframe/images/openedfolder.gif'/>"+gen);
					document.getElementById("qxTreeId2").innerHTML=treeStr;
			  		$(function(){
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
					$.lightTreeview.open('#qxTreeId2 ul')
		 	   }
	}
	//根据下拉框条件，查出对应的角色
		function getGwmc(value){
			var str=$("#chan").val();
			if(trim(str) != ''){
				treeTag=str;
			}
			if(value==null||trim(value)==''){
				deleteTab(treeTag);
				return false;
			}
			jsonObj={"domain.ssJgbm":value};
			var url=jcontextPath+"/jsqxwh!query";
			ajaxCommon(url,jsonObj,"querySuccess");
		}
		function querySuccess(data){
			deleteTab(treeTag);
			if(treeTag == 1){
				var js=data.domain.listStr;
				var jsTableInnerHTML='<table id="tabb" width="100%">'
					+'<tr bgcolor="#daedf5">'
						+'<th class="td" width="50px">序号</th>'
						+'<th class="td" width="200px">名称</th>'+'<th class="td" width="150px">简称</th>'+'<th class="td" width="260px">备注说明</th>'
					+'</tr>'+'<tbody id="jsTbody">'+js+'</tbody>'+'</table>';
				document.getElementById("jsTab").innerHTML=jsTableInnerHTML;
			}
			else{
				
				changeJsListInnerHTMLBySelGnmkDm();
			}
		}
		
		function deleteTab(value){
			if(value == 1){
				var tab1=document.getElementById("tabb");
			}
			else{
				var tab1=document.getElementById("tabb2");
			}
			var ll=tab1.rows.length;
			for(var i = 1;i < ll; i++){
				tab1.deleteRow(1);
			}
		}
			
		function getGnmkByKey(value,index)
		{
			var str=$("#chan").val();
			if(trim(str) != ''){
				treeTag=str;	
			}	
				$("#jsTbody tr").each(function(){
			if(""!=this.id && this.id.split("-")[0]=="xtjs"){
				if(index==this.id.split("-")[1]){
					this.style.backgroundColor="#FFFF99";
				}else{
					this.style.backgroundColor="#FFFFFF";
				}	
			}
		});
			var url=jcontextPath+"/jsqxwh!queryGnmkDmsByJsDm";
			$("#selJsDmId").val(value);
			jsonObj={"domain.jsDm":value};
			resetMenuDm(1);
			ajaxCommon(url,jsonObj,"doGetGnmkDmsSucc");
		}
	
		function doGetGnmkDmsSucc(data){
		var gnmkDms=data.domain.gnmkDmStr;
		var menuDms=document.getElementsByName("menus1");
		document.getElementById("selMenuDmsId").value=gnmkDms;
				for(var i=0;i<menuDms.length;i++){
					if(gnmkDms.indexOf(menuDms[i].value)!=-1){
						menuDms[i].checked=true;
						var curId=menuDms[i].id;
						var sjMenuId=getStrIds22(curId);
						while(""!=sjMenuId){
							var curSjMenu=document.getElementById(sjMenuId);
							if(undefined!=curSjMenu){
								curSjMenu.checked=true;
							}
							curId=sjMenuId;
							sjMenuId=getStrIds22(curId);
						}
					}
				}
	    }
		
		function getStrIds22(id){
		var strIds="";
		if(id!=null){
			var sjMenuDm=id.split("-")[4];
			var menus1=document.getElementsByName("menus1");
			var curMenuDm="";
			for(var i=0;i<menus1.length;i++){
				curMenuDm=menus1[i].value;
				if(curMenuDm==sjMenuDm){
					strIds+=menus1[i].id;
				}
			}
		}
		return strIds;
	}
		 //tab1、tab2使用，将菜单复选框全部置为未选中 ，tabPageNum是标签页标志，值为1或2
		function resetMenuDm(tabPageNum){
		var menus=document.getElementsByName("menus"+tabPageNum);
		for(var j=0;j<menus.length;j++){
			menus[j].checked=false;
		}
	}
    
    	//tab1获取权限树所有node=N的菜单（即非目录结点）
    	function getAllQueryNoneNodeGnmkDms(){
		var result="";
		var menuDms=document.getElementsByName("menus1");
		for(var i=0;i<menuDms.length;i++){
			if("N"==menuDms[i].id.split("-")[1]){
				result+=menuDms[i].value+",";
			}
		}
		return result;
		}	
		
		
		//tab1中使用，获取选中的复选框(对应的菜单为非目录结点)对应的value
	function getSelMenuDms(){
		var menuDms=document.getElementsByName("menus1");
		var result="";
		for(var i=0;i<menuDms.length;i++){
			if(menuDms[i].checked==true&&"N"==menuDms[i].id.split("-")[1]){
				var ary=menuDms[i].id.split("-")
				result+=menuDms[i].value+","+ary[4]+":";
			}
		}
		return result;
	}
	
	function doSaveQxJsSucc(data){
		hideMessage();
		showAlert("保存成功！");
	}
	
	var j=0;
	function changeDiv(value){
		var div1=$("#div1")[0];
		var div2=$("#div2")[0];
		var tb_1=$("#tb_1")[0];
		var tb_2=$("#tb_2")[0];
		var jgbm=$("#mainForm_domain_sjJgbm").val();
		var chan=$("#chan");
		if(value == 1){
		tb_1.className='hovertab';
		tb_2.className='normaltab';
		div1.style.display="block";
		div2.style.display="none";
		chan.val(1);
		getGwmc(jgbm);
		}
		else{
	    j++;
		tb_1.className='normaltab';
		tb_2.className='hovertab';
		div2.style.display="block";
		div1.style.display="none";
		chan.val(2);
		if(j==1){
			getStree(2);	
		}
		getGwmc(jgbm);
		}
	}
	
	
	
		//tab1、tab2使用,点击某一复选框时使用,tabPageNum= 1:tab1,2:tab2
	function onSelect(tabPageNum,id,checked){
		if(1==tabPageNum){
			if("Y"==id.split("-")[1]){
				var allFollowedMenusIds= getAllFollowedMenuIds(id).split(",");
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
			var sjMenuId=getStrIds22(id);
			var curId=id;
			while(""!=sjMenuId){
				var curSjMenu=document.getElementById(sjMenuId);
				if(undefined!=curSjMenu){
					var ids=getStrIds1(curId,"Y")+getStrIds1(curId,"N");
					//alert(ids);
					if(checked==false&&""==ids){
						curSjMenu.checked=false;
					}
					if(checked==true&&!(curSjMenu.checked)){
						curSjMenu.checked=true;
					}
				}
				curId=sjMenuId;
				sjMenuId=getStrIds22(curId);
			}
		}else if(2==tabPageNum){
		var jgbm=$("#mainForm_domain_sjJgbm").val();
		if(jgbm == '' || jgbm == undefined){
			showError("请选择公司！");
			resetMenuDm(2);
			return false;
		}
			reset2();	
			resetMenuDm(2);
			document.getElementById(id).checked=checked;
			if(checked){
		 		var selMenuDm=id.split("-")[2];
		 		document.getElementById("selGnmkDmId").value=selMenuDm;
		 		changeJsListInnerHTMLBySelGnmkDm(selMenuDm);
			}
		}
	}
	
	function getStrIds1(id,NodeTag){
		var strIds="";
		if(!isUndefinedOrEmpty(id)){
			var sjMenuDm=id.split("-")[4];
			if(!isUndefinedOrEmpty(sjMenuDm)){//排除所有一级菜单
				var menus1=document.getElementsByName("menus1");
				var curMenuId="";
				var curMenu=undefined;
				for(var i=0;i<menus1.length;i++){
					curMenuId=menus1[i].id;
					curMenu=document.getElementById(curMenuId);
					if(!isUndefinedOrEmpty(curMenuId)&&sjMenuDm==curMenuId.split("-")[4]&&NodeTag==curMenuId.split("-")[1]){
						if(undefined!=curMenu&&curMenu.checked==true){
							strIds+=curMenuId;
							if(i!=menus1.length-1){//各id用“,”隔开
								strIds+=",";
							}
						}
					}
				}
			}
		}
		return strIds;
	}
	
	function isUndefinedOrEmpty(str){
		if(undefined==str||""==str){
			return true;
		}else{
			return false;
		}
	}
	
	function getAllFollowedMenuIds(id){
		var strIds="";
		if(!isUndefinedOrEmpty(id)){
			var menuDm=id.split("-")[2];
			var menus1=document.getElementsByName("menus1");
			var curMenuId="";
			for(var i=0;i<menus1.length;i++){
				curMenuId=menus1[i].id;
				if(!isUndefinedOrEmpty(curMenuId)&&menuDm==curMenuId.split("-")[4]){
					strIds+=curMenuId+",";
					if("Y"==curMenuId.split("-")[1]){
						strIds+=getAllFollowedMenuIds(curMenuId);
					}
				}
			}
		}
		return strIds;
	}
	
	function getSelJsDms(){
		var result="";
		var multiBoxs=document.getElementsByName("multiBoxs");
		for(var i=0;i<multiBoxs.length;i++){
			if(multiBoxs[i].checked){
				result+=multiBoxs[i].value+",";
			}
		}
		return result;
	}
	
	//tab2中使用，获取选中的菜单复选框(对应的菜单为非目录，即非结点)对应的value
	function getSelGnmkDm(){
		var result="";
		var menuDms=document.getElementsByName("menus2");
		for(var i=0;i<menuDms.length;i++){
			if(menuDms[i].checked==true&&"N"==menuDms[i].id.split("-")[1]){
				var ary=menuDms[i].id.split("-")
				result=menuDms[i].value+","+ary[4];
			}
		}
		return result;
	}
	
	//tab2中使用，将角色列表复选框全部置为未选中 
	function reset2(){
		var mutiboxs=document.getElementsByName("multiBoxs");
		for(var i=0;i<mutiboxs.length;i++){
			mutiboxs[i].checked=false;
		}
	}
	
	//tab2使用，动态改变角色表格各行排序，即根据当前选中的菜单（功能模块）将数据库里对应的角色显示在表格前面并置前面复选框为选中状态
	function changeJsListInnerHTMLBySelGnmkDm(selGnmkDm){
		var jgbm=$("#mainForm_domain_sjJgbm").val();
		var url = jcontextPath+"/gnqx/jsqxwh!queryJsInnerHtmlByGnmkDm";	
		var jsonObj = {"domain.gnmkDm":selGnmkDm,"domain.ssJgbm":jgbm};
		ajaxCommon(url,jsonObj,"doGetJsSucc");		
	}

	function doGetJsSucc(data){
		var jsStr=data.domain.jsStr;
		var js=data.domain.jsList;
		$("#jsDm").val(js);
		var jsTableInnerHTML='<table id="tabb2" width="100%">'
					+'<tr bgcolor="#daedf5">'
						+'<th class="td" width="20px"><img src="<sys:context/>/resource/pageframe/images/turnover.gif" width="13" height="13" border="0" alt="反选" onClick="reverse()"></th>'
						+'<th class="td" width="50px">序号</th>'
						+'<th class="td" width="200px">名称</th>'+'<th class="td" width="150px">简称</th>'+'<th class="td" width="260px">备注说明</th>'
					+'</tr>'+jsStr+'</table>';
					
				document.getElementById("jsTab1").innerHTML=jsTableInnerHTML;
	}
	
	 function reverse(){
        var obj=document.getElementsByName("multiBoxs");
        reverseCheckbox(obj);
    }
</script>



<style type="text/css">
</style>
</head>
<body onload="getStree(1)">
<s:form action="" namespace="/gnqx" method="post" id="mainForm"	name="mainForm">

	<!-- 操作区 说明：hover_k这个class用作当鼠标移到链接文字上会有阴影框效果,pointer为手型class -->
	<div class="right_btnbg">
		<ul class="lcont">
			<li class="no">操作：</li>
			<li><a href="#" id="addBtn" class="licon06">保存</a></li>
			<li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
		</ul>
	
		<ul class="rcont">
			<li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
			   <li class="ricon03">帮助</li>
		</ul>
	</div>
	<div class="right_cont">
	
	<input type="hidden" id="selJsDmId" />
	<input type="hidden" id="chan" />
	<input type="hidden" id="jsDm" />
	<input type="hidden" id="selGnmkDmId" />
	<input type="hidden" id="selMenuDmsId" />
	<div id="tb_" class="tbb_">
		<ul>
			<li id="tb_1" class="hovertab" onMouseDown="changeDiv(1);">角色-权限</li>
			<li id="tb_2" class="normaltab" onMouseDown="changeDiv(2);">权限-角色</li>
		</ul>
	</div>
	
	<div id="divQuery">
	 <fieldset>
    	 	 <legend>查询条件</legend>
    	 	 	  <table width="95%" align="center" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="3%" align="center">单位：</td>
    	 	 	  		<td  width="15%" align="left">
    	 	 	  			<sys:qxGsList myId="mainForm_domain_sjJgbm" myName="domain.sjJgbm"  myClass="select" onChange="getGwmc(this.value)" contaisQxz="Y"></sys:qxGsList>
    	 	 	  		</td>
						 <td  width="15%" align="left" ></td>
						 <td  width="15%" align="left"></td>
    	 	 	  	</tr>
    	 	 	  </table>
    	 </fieldset>
	</div>
	
	<div class="dis" id="div1" align="left" style="height:650px" >
	<table  border="0" cellspacing="0" cellpadding="0" align="left"
		class="poptabb_css" style="height:560px;" >
		<tr>
			<td width="540x" rowspan="2" valign="top">
			<div  style="height:520px; width:560px; overflow:auto;" id="jsTab">
			<table id="tabb">
				<tr bgcolor="#daedf5">
					<th class="td" width="30px">序号</th>
					<th class="td" width="180px">名称</th>
					<th class="td" width="130px">简称</th>
					<th class="td" width="180px">备注说明</th>
				</tr>
			</table>
			</div>
			</td>
			<td width="19" rowspan="2"></td>
			<td width="450px" colspan="2">
			<table>
				<tr bgcolor="#daedf5">
					<th class="td" width="470px">功能模块</th>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div
				style="overflow:auto; height:520px; margin-top:8px;text-align:left;">
			<div id="xtflMcId1"></div>
			<ul id="qxTreeId1" class="lightTreeview"></ul>
			</div>
			</td>
		</tr>
	</table>
	</div>
	<div class="undis" id="div2" align="left" style="display: none;height: 650px">
	<table border="0" cellspacing="0" cellpadding="0" align="left"
		class="poptabb_css" style="height: 570px">
		<tr>
			<td width="450px" colspan="2">
			<table>
				<tr bgcolor="#daedf5">
					<th class="td" width="480px">功能模块</th>
				</tr>
			</table>
			</td>
			<td width="530px" rowspan="2" valign="top">
			<div style=" height:520px; width:570px; overflow:auto;" id="jsTab1">
			<table id="tabb2">
				<tr bgcolor="#daedf5">
					<th class="td" width="20px"><img
						src="<sys:context/>/resource/pageframe/images/turnover.gif"
						width="13" height="13" border="0" alt="反选" onClick="reverse()"></th>
					<th class="td" width="30px">序号</th>
					<th class="td" width="180px">名称</th>
					<th class="td" width="130px">简称</th>
					<th class="td" width="180px">备注说明</th>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div style="overflow:auto; height:520px; margin-top:8px;text-align:left;">
				<div id="xtflMcId2"></div>
				<ul id="qxTreeId2" class="lightTreeview"></ul>
			</div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	
</s:form>

</body>
</html>
