// 省市区三级联动
var addressInit = function(_cmbProvince, _cmbCity, defaultProvince, defaultCity)
{
	var cmbProvince = document.getElementById(_cmbProvince);
	var cmbCity = document.getElementById(_cmbCity);
	
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
	function cmbAddOption(cmb, valuestr, str, obj)
	{
		var option = document.createElement("OPTION");
		cmb.options.add(option);
		option.innerHTML = str;
		option.value = valuestr;
		option.obj = obj;
	}
	
	function changeProvince()
	{
		cmbCity.options.length = 0;
		cmbCity.onchange = null;
		if(cmbProvince.selectedIndex == -1)return;
		var item = cmbProvince.options[cmbProvince.selectedIndex].obj;
		for(var i=0; i<item.cityList.length; i++)
		{
			if(item.cityList[i].name == "全部") {
			}else {
				cmbAddOption(cmbCity, item.cityList[i].name, item.cityList[i].name, item.cityList[i]);
			}
			
		}
		cmbSelect(cmbCity, defaultCity);
	}
	
	for(var i=0; i<provinceList.length; i++)
	{
		if(provinceList[i].name == "全部") { 
		}else {
			cmbAddOption(cmbProvince, provinceList[i].name, provinceList[i].name, provinceList[i]);
		}
		
	}
	cmbSelect(cmbProvince, defaultProvince);
	changeProvince();
	cmbProvince.onchange = changeProvince;
}

var provinceList;

//地区
function getIpAreaDict(province, city){
	$.ajax({
		type: "POST",
	   	async:true,
	   	url: jcontextPath + "/getAreaDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	   	success:function(data){
	   		if(data == null){
	   			return;
	   		}
	   		provinceList = eval(data.content);
	   		addressInit('ipProvince', 'ipCity', province, city);	   	
	   	},
	    error:function(data){
		}
	});
}
