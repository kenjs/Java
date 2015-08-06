

function initData(url,jsonObj){
	ajaxCommon(url,jsonObj,"doInitDataSuc");
}

function doInitDataSuc(data) {
	$("[name='jsonData']").val(data.jsonData);
	//$("[name='targetObjName']").val(data.targetObjName);
	//$("[name='targetDmObjName']").val(data.targetDmObjName);
	//$("[name='itemIndex']").val(data.itemIndex);
	//$("[name='dropDownSelectedCallback']").val(data.dropDownSelectedCallback);
	setAutoDropDown(data.dropDownData,data.targetObjName,data.targetDmObjName,data.itemIndex,data.dropDownSelectedCallback,data.isCleanText,data.width);
	if (data.inputSelInitFun != "" && typeof(eval(data.inputSelInitFun)) =="function") {
		eval(data.inputSelInitFun+"(data);");
	}
}
function setAutoDropDown(data, objName,DmObjName,itemIndex, funName,bol_cleanText,width) {
	var dataArray = eval("["+data+"]");
	$("[name='"+objName+"']").autocompleteArray(
		dataArray,
		{
			delay:10,
			minChars:1,
			matchSubset:1,
			autoFill:false,
			onItemSelect:eval(funName),
			maxItemsToShow:10,
			curRowIndex:itemIndex,
			itemDmObjName:DmObjName,
			isCleanText:bol_cleanText,
			width:width
		}
	);
}

function hideHelpWindow(){
	$("[name='cyDropDownHelp']").hide();
}