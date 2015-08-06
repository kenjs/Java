//文书审核
function doWsSh(wsspxh,spxh){  
       var url = jcontextPath+"/common/wsspCommon!init.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
   	window.showModalDialog(url,window,"dialogHeight:900px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
   	//window.open(url);
   	onRefresh();
} 

/*对数组封装成json格式*/
function getArrayToJson(obj, paraName) {
	var data = "";
	$.each(obj, function(i){
		data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
	});
	
	return data;
} 

//var myArray=new Array();
function plJudge(){
	var xhs = $(":checked[name='xhs']");
	if (xhs.length <= 0) {
		showAlert("请选择需要批量审核通过的记录！");
		return;
	}
	//myArray=xhs;
	if(xhs.length==1){
		var array=$(xhs[0]).val().split("#");
		var jsonObj = {"domain.wsspxh":array[0],"domain.spxh":array[1],"domain.sprJdxh":array[2]};

		var url=jcontextPath+"/common/wsspCommon!queryWsspms";	
		ajaxCommon(url,jsonObj,"doQueryWsspmsSuc");
	}else{
		//var map=new HashMap(xhs.length);
		//var map=new HashMap();
		var tempJdxh="";
		var wsspxh="";
		var spxh="";
		var flagSuc = true;
		$.each(xhs, function(i, obj){
			var key=$(obj).val();
			var array=key.split("#");
			//if(!map.containsKey(key))
				//map.put(key,key);
				//continue;
			if(i==0){
				wsspxh=array[0];
				spxh=array[1];
				tempJdxh= array[2];
			}else{
				if(tempJdxh!=array[2]){
					showAlert("节点序号不同，不能批量审核通过！");
					flagSuc = false;
					return false;
				}
			}
			//alert(tempJdxh);
		});
		if (!flagSuc) {
			return;
		}
		//myArray=map.values();
		//alert(map.size());
		//alert("myArray"+myArray);
		var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh,"domain.sprJdxh":tempJdxh};

		var url=jcontextPath+"/common/wsspCommon!queryWsspms";	
		ajaxCommon(url,jsonObj,"doQueryWsspmsSuc");
	}
}

function plBack(){
	var xhs = $(":checked[name='xhs']");
	if (xhs.length <= 0) {
		showAlert("请选择需要批量退回审批的记录！");
		return;
	}
	var url = jcontextPath+"/common/wsspCommon!plBack";  
	var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
	ajaxCommon(url,jsonStr,"doPlBackSuc",false);
}

function doQueryWsspmsSuc(data){
	var wsspxh=data.domain.wsspxh;
	var spxh=data.domain.spxh;
	var rtnCode=data.domain.rtnCode;
	var wsspms=data.domain.wsspms;
	var sprJdxh =data.domain.sprJdxh
	//if(wsspms==undefined || wsspms==null || wsspms==""){
		//showAlert("文书审批模式获取失败，不能批量审核通过，请检查！");
		//return;
	//}
	//alert("rtnCode="+rtnCode);
	//alert("wsspms"+wsspms);
	//rtnCode=="2"时无下一级审批，直接调用终审
	if("2"==rtnCode){
		doPlJudge();
	}else{//否则根据文书审批模式选择发送，wsspms=1或2时，需要弹出发送对话框，3时直接发送
		if("1"==wsspms || "2"==wsspms){
			doInitSend(wsspxh,spxh);
		}
		if("3"==wsspms){
			doPlSend();
		}
	}
}
var sprCzyDjxh="";
function doInitSend(wsspxh,spxh){ 
	sprCzyDjxh=""; 
    var url = jcontextPath+"/common/wsspCommon!initSend.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
    window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
   	//window.open(url);
   	if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
   		//showAlert("审批人员出错，请检查！");
   		return;
   	}
   	var url = jcontextPath+"/common/wsspCommon!plSend"; 
   	//var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
   	var xhs = $(":checked[name='xhs']");
   	//var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
   	var jsonObj = {"domain.sprCzyDjxh":sprCzyDjxh};
	jsonStr += jQuery.param(jsonObj);
    ajaxCommon(url,jsonStr,"doPlSendSuc",false);
}
function doPlSend(){  
      var url = jcontextPath+"/common/wsspCommon!plSend"; 
      //var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
      var xhs = $(":checked[name='xhs']");
   	  //var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	  var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
      //var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh};   
      ajaxCommon(url,jsonStr,"doPlSendSuc",false);
}
function doPlSendSuc(data) {
	hideMessage();
	showAlert("批量审批通过成功！");
	onRefresh();
} 
function doPlJudge(){  
     var url = jcontextPath+"/common/wsspCommon!plJudge"; 
     //var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
     var xhs = $(":checked[name='xhs']");
   	 //var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	 var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
     //alert(jsonStr);
     ajaxCommon(url,jsonStr,"doPlJudgeSuc",false);
}
function doPlJudgeSuc(data) {
	hideMessage();
	showAlert("批量审批通过成功！");
	onRefresh();
}
function doPlBackSuc(data) {
	hideMessage();
	showAlert("退回成功！");
	onRefresh();
}

var wsspms="";
// spgsbm 审批公司编码，默认为当前操作员所在公司，但是费用报销申请比较特殊，为记账单位
function scSend(wsDm,wsXmflDjxh,ywDjxh,oldWsspxh,spgsbm){
	if (spgsbm == undefined || spgsbm == null) {
		spgsbm = "";
	}
	sprCzyDjxh="";
	wsspms="";
	var url = jcontextPath+"/common/wsspCommon!initScSend.action?domain.wsDm="+wsDm+"&domain.jgbm="+spgsbm;
    window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
   	//window.open(url);
   	if(undefined==wsspms || null==wsspms || ""==wsspms){
   		//showAlert("数据出错，请检查！");
   		return;
   	}
   	if("1"==wsspms || "2"==wsspms){
   		if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
	   		showAlert("审批人员出错，请检查！");
	   		return;
	   	}
   	}
   	
   	var url = jcontextPath+"/common/wsspCommon!scSend"; 
   	var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.ywDjxh":ywDjxh,"domain.sprCzyDjxh":sprCzyDjxh,"domain.oldWsspxh":oldWsspxh};
    ajaxCommon(url,jsonObj,"doScSendSuc",false);
}

function doScSendSuc(data) {
	hideMessage();
	showAlert("发送成功！");
	window.close();
}
// spgsbm 审批公司编码，默认为当前操作员所在公司，但是费用报销申请比较特殊，为记账单位编码
function plScSend(wsDm,wsXmflDjxh,spgsbm){  
	if (spgsbm == undefined || spgsbm == null) {
		spgsbm = "";
	}
	  var xhs = $(":checked[name='xhs']");
	  if (xhs.length <= 0) {
		showAlert("请选择需要批量发送审批的记录！");
		return;
	  }
	  sprCzyDjxh="";
	  wsspms="";
	  var url = jcontextPath+"/common/wsspCommon!initScSend.action?domain.wsDm="+wsDm+"&domain.jgbm="+spgsbm;
      window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
      if(undefined==wsspms || null==wsspms || ""==wsspms){
	   		//showAlert("数据出错，请检查！");
	   		return;
      }
   	  if("1"==wsspms || "2"==wsspms){
   		 if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
	   		 showAlert("审批人员出错，请检查！");
	   		 return;
	   	 }
   	  }
      var url = jcontextPath+"/common/wsspCommon!plScSend"; 
   	  var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
      var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.sprCzyDjxh":sprCzyDjxh}; 
      jsonStr += jQuery.param(jsonObj);  
      ajaxCommon(url,jsonStr,"doPlScSendSuc",false);
}
function doPlScSendSuc(data) {
	hideMessage();
	showAlert("批量发送成功！");
	onRefresh();
}