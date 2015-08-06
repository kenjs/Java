 //打开新的窗口 （肯定有一个新的页面）
function f_open(action,id){
	window.open(action+'?id='+id);
}
 //打开新的窗口 （肯定有一个新的页面）
function f_openAdd(action){
	window.open(action);
}


//生成省份窗口
//从json中加载省市信息
//传入参数时间必须去除空格
function loadInfo(cfromorto, clevel, cprovince, ccity1) {

	var infoArr = new Array(new Array(), new Array());
	var infoArr = new Array(); //先声明一维
	for ( var i = 0; i < 42; i++) { //一维长度为40
		infoArr[i] = new Array(); //在声明二维
		for ( var j = 0; j < 2; j++) { //二维长度为3
			infoArr[i][j] = 0;
		}
	}
	//去除空格
	//cprovince=cprovince.trim();
	//ccity1=cprovince.trim();
	if (clevel == "province") {
		for ( var i = 0; i < json.length; i++) {
			infoArr[i][0] = json[i]['provname'];
			infoArr[i][1] = json[i]['provid'];
			infoArr[41][1] = i + 1;
		}
	} else if (clevel == "city1") {
		if (cprovince == "" || cprovince == null) {
			if (cfromorto == "from") {
				alert('请先选择 起始省份');
				return infoArr;
			}
			if (cfromorto == "to") {
				alert('请先选择 目的省份');
				return infoArr;
			}
		} else {
			for ( var i = 0; i < json.length; i++) {

				if (json[i]['provname'] == cprovince) {
					for ( var j = 0; j < json[i]['city1'].length; j++) {
						infoArr[j][0] = json[i]['city1'][j].city1name;
						infoArr[j][1] = json[i]['city1'][j].city1id;
						infoArr[41][1] = j + 1;
					}
					break;
				}

				if (json.length - 1 == i) {

					return infoArr;
				}

			}
		}
	} else if (clevel == "city2") {
		if (cprovince == "" || cprovince == null) {
			if (cfromorto == "from") {
				alert('请先选择 起始省份');
				return infoArr;
			}
			if (cfromorto == "to") {
				alert('请先选择 目的省份');
				return infoArr;
			}
		}
		if (ccity1 == "" || ccity1 == null) {
			if (cfromorto == "from") {
				alert('请先选择 起始市');
				return infoArr;
			}
			if (cfromorto == "to") {
				alert('请先选择 目的市');
				return infoArr;
			}
		} else {
			for ( var i = 0; i < json.length; i++) {

				if (json[i]['provname'] == cprovince) {
					for ( var j = 0; j < json[i]['city1'].length; j++) {
						if (json[i]['city1'][j].city1name == ccity1) {
							for ( var m = 0; m < json[i]['city1'][j]['city2'].length; m++) {
								infoArr[m][0] = json[i]['city1'][j]['city2'][m].city2name;
								infoArr[m][1] = json[i]['city1'][j]['city2'][m].city2id;
								infoArr[41][1] = m + 1;
							}
							break;
						}
						if (json[i]['city1'].length - 1 == j) {
							alert('市:' + ccity1 + '不存在区县');
							return infoArr;
						}
					}
				}
			}
		}
	}

	return infoArr;
}
//在select中加载当前选中项的下属城市或者区县
//传入参数时必须去除空格
function changAddr(id, clevel, cprovince, ccity1) {
	
	var cfromorto = "from";
	var infoArr = new Array(new Array(), new Array());
	infoArr = loadInfo(cfromorto, clevel, cprovince, ccity1);
	if (clevel == "city1") {
		if (id == "cfromcity") {
			$("#cfromcity").empty();
			$("#cfromcity").prepend("<option value=''>--请选择--</option>"); //为Select插入一个Option(第一个位置)
			$("#cfromregion").empty();
			$("#cfromregion").prepend(
					"<option value=''>--请选择--</option>");
		} else if (id == "ctocity") {

			$("#ctocity").empty();
			$("#ctocity").prepend("<option value=''>--请选择--</option>"); //为Select插入一个Option(第一个位置)
			$("#ctoregion").empty();
			$("#ctoregion").prepend("<option value=''>--请选择--</option>");
		}
	} else if (clevel == "city2") {
		if (id == "cfromregion") {
			$("#cfromregion").empty();
			$("#cfromregion").prepend(
					"<option value=''>--请选择--</option>");
		} else if (id == "ctoregion") {
			$("#ctoregion").empty();
			$("#ctoregion").prepend("<option value=''>--请选择--</option>");
		}
	}
	var newop; //定义一个Option变量用来保存项

	for ( var i = 0, j = infoArr.length; i < j; i++) {
		//循环出相对应的数组的值
		var city = infoArr[i][0]
		if (city == "0") {
			break;
		}
		$("#" + id)
				.append("<option value='" + city + "'>" + city + "</option>");
	}
}