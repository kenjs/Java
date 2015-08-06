// 省市区三级联动
var addressInit = function(_cmbProvince, _cmbCity, _cmbArea, defaultProvince, defaultCity, defaultArea)
{
	var cmbProvince = document.getElementById(_cmbProvince);
	var cmbCity = document.getElementById(_cmbCity);
	var cmbArea = document.getElementById(_cmbArea);
	
	function cmbSelect(cmb, str)
	{
		for(var i=0; i<cmb.options.length; i++)
		{
			if(cmb.options[i].value == str)
			{
				cmb.selectedIndex = i;
				return;
			}
		}
	}
	function cmbAddOption(cmb, str, obj)
	{
		var option = document.createElement("OPTION");
		cmb.options.add(option);
		option.innerHTML = str;
		option.value = str;
		option.obj = obj;
	}
	
	function changeCity()
	{
		cmbArea.options.length = 0;
		if(cmbCity.selectedIndex == -1)return;
		var item = cmbCity.options[cmbCity.selectedIndex].obj;
		for(var i=0; i<item.areaList.length; i++)
		{
			cmbAddOption(cmbArea, item.areaList[i], null);
		}
		cmbSelect(cmbArea, defaultArea);
	}
	function changeProvince()
	{
		cmbCity.options.length = 0;
		cmbCity.onchange = null;
		if(cmbProvince.selectedIndex == -1)return;
		var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
		for(var i=0; i<item.cityList.length; i++)
		{
			cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i]);
		}
		cmbSelect(cmbCity, defaultCity);
		changeCity();
		cmbCity.onchange = changeCity;
	}
	
	for(var i=0; i<provinceList.length; i++)
	{
		cmbAddOption(cmbProvince, provinceList[i].name, provinceList[i]);
	}
	cmbSelect(cmbProvince, defaultProvince);
	changeProvince();
	cmbProvince.onchange = changeProvince;
}

var provinceList;

//发货地
function getStartAreaDict(province, city, area){
	$.ajax({
		type: "POST",
	   	async:true,
	   	url: jcontextPath + "/getAreaDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			//showAlert("加载失败！");
	   			return;
	   		}
	   		provinceList = eval('('+data.message+')');
	   		addressInit('startProvince', 'startCity', 'startCounty', province, city, area);	   	
	   	},
	    error:function(data){
	    	//showAlert(data.responseText);
		}
	});
}

//卸货地
function getEndAreaDict(province, city, area){
	$.ajax({
		type: "POST",
	   	async:true,
	   	url: jcontextPath + "/getAreaDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			//showAlert("加载失败！");
	   			return;
	   		}
	   		provinceList = eval('('+data.message+')');
	   		addressInit('endProvince', 'endCity', 'endCounty', province, city, area);	   	
	   	},
	    error:function(data){
	    	//showAlert(data.responseText);
		}
	});
}

//地理位置
function getLocationAreaDict(province, city, area){
	$.ajax({
		type: "POST",
	   	async:true,
	   	url: jcontextPath + "/getAreaDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
	   	success:function(data){
	   		if(data == null){
	   			//showAlert("加载失败！");
	   			return;
	   		}
	   		provinceList = eval('('+data.message+')');
	   		addressInit('locationProvince', 'locationCity', 'locationCounty', province, city, area);	   	
	   	},
	    error:function(data){
	    	//showAlert(data.responseText);
		}
	});
}