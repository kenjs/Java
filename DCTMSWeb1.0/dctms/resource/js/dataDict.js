
function addSelectOption(selectCmb, name, value)
{
	var option = document.createElement("OPTION");
	selectCmb.options.add(option);
	option.innerHTML = name;
	option.value = name;
}

function loadSelectOption(obj, data){
	for (var i=0;i<data.length;i++){
		addSelectOption(obj, data[i].name, data[i].code);
	}

}

//车辆-板
function getCarPlateTypeDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarPlateTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-栏
function getCarBarTypeDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarBarTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-运营状态
function getCarStateTypeDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarStateTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-车长
function getCarLengthDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarLengthDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-运力-吨位
function getCarWeightDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarWeightDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-运力-体积
function getCarCubageDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarCubageDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//货物类型
function getCargoTypeDict(obj){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCargoTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		loadSelectOption(obj, data.list);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}
