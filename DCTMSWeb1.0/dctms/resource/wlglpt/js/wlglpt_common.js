// 本处主要是处理公用级联下拉处理。
// YWID：业务类别，用于区分是哪个级联业务
//      'gs-fbs'：根据公司动态刷新分包商的下拉
//      'gs-kh'： 根据公司动态刷新客户的下拉
//      'kh-hw'： 根据客户动态刷新货物的下拉
//      'bm-yh':  根据部门动态刷新用户的下拉
//      'fbs-xl': 根据分包商动态刷新线路的下拉
//      'gs-wlryfl': 根据公司动态刷新外联人员分类的下拉
// SJ: 当前上级的值
// defaultValue：级联对象自身的值
// listName：级联对象NAME
// listId：级联对象ID
// containQbBz：是否显示“请选择”
// mcContainDmBz：名称列中是否包含代码
function commonInit(ywid, sj, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (sj == null) {
		sj = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {
	        "domain.ywid":ywid,
			"domain.paramdm":sj,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!commonInit";	
	ajaxCommon(url,jsonObj,"changeList");
}


//财务-费用类别
function cwfylbInit(cwfylbDm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (cwfylbDm == null) {
		cwfylbDm = "";
	}
	 //alert("cwfylbDm:" +cwfylbDm);
	 
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":cwfylbDm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!cwfylbInit";	
	ajaxCommon(url,jsonObj,"changeList",false,false);
}


//部门
function bmInit(sjJgbm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (sjJgbm == null) {
		sjJgbm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":sjJgbm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!bmInit";	
	ajaxCommon(url,jsonObj,"changeList");
}

//岗位
function gwInit(bmDm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (bmDm == null) {
		bmDm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":bmDm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!gwInit";	
	ajaxCommon(url,jsonObj,"changeList");
}

function currentFbsInit(ssJgbm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (ssJgbm == null) {
		ssJgbm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":ssJgbm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!queryCurrentFbs";	
	ajaxCommon(url,jsonObj,"changeList");
}

function changeList(data){
	var list = data.domain.dataList;
	
	//alert("data.domain.currentObjId:"+data.domain.currentObjId);
	
	$("#"+data.domain.currentObjId).empty();
	$.each(list, function(i,domain){
		//alert("domain.mc:"+ domain.mc);
		
	    var option = document.createElement('option');
	    $("#"+data.domain.currentObjId)[0].add(option);
	    $(option).text(domain.mc).val(domain.dm);
	    //默认选中
	    if(data.domain.defaultValue==domain.dm){
	    	$(option).attr("selected", "selected");
	    	$(option).text(domain.mc).val(domain.dm);
	    }
	    //alert($("#"+data.domain.currentObjId).html());
	});
}

//window.open()方式打开新窗口
//url-窗口内容对应的链接
//width-窗口宽度
//height-窗口高度
//left-窗口与屏幕左边距离
//top-窗口与屏幕上边距离
function popWin(url, width, height, left, top){
	url = url + "&random="+Math.random();
	window.open(url,"_blank",'width='+width+',height='+height+',left='+left+',top='+top+',toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no');
}

//在窗口中间打开页面
function openWindowToDialog(url,dialogWidth,dialogHeight,obj){
	var sw = screen.width;
    var sh = screen.height;
    
    var dialogLeft = (sw-dialogWidth)/2;
    var dialogTop = (sh-dialogHeight)/2;
    var parr = 'dialogWidth:'+dialogWidth+'px;dialogHeight:'+dialogHeight+'px;dialogLeft:'+dialogLeft+'px;dialogTop:'+dialogTop+'px;edge: Raised; center: Yes; help: no; resizable: Yes; status: no;';
	window.showModalDialog(url,obj,parr);
	
}