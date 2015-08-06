var param;

function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}

$(function(){
	getid();
	select_waybill();
	//装车确认 时间默认
	$("#suredate").val(myDates("detetime"));
});

function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	param=idUrl;
}
/****************此方法用来校验物品计费方式的****************/
function measuretype() {
	var i = 1;
	var j = 1;
    var $measuretype=$("select[name='measuretype']");
    $.each($("input[name='goodsid']"),function(n,item){ 
        var measuretype=$($measuretype[n]).val();
        i++;
        if(trim(measuretype) == '按整车计费') {
        	j++;
        }
        //alert(measuretype+i+j)
    })
    if(j>1) {
    	if(i != j) {
        	ymPrompt.alert("\"按整车计费\"不允许和其他计费方式同时存在");
        	return false;
        }
    }
    return true;
}
function btnSave(){
	if(checkData()&&measuretype() ){
		//屏蔽保存按钮，防止多次点击提交
		$("#save").hide();
		//核定货物信息
		var jsonList = eval(createArray()); 
		var jsonText = JSON.stringify(jsonList);
		var consignorprovince = '';//发省
		 var consignorcity = '';//发市
		 var consignorregion = '';//发区县
		 var arr= new Array();
		 arr = $("#consignorprovince").val().split("-");//发货方地址
		 if(arr.length == 2) {
			 consignorprovince = arr[0];
			 consignorcity = arr[1];
		 }else if(arr.length == 3) {
			 consignorprovince = arr[0];
			 consignorcity = arr[1];
			 consignorregion = arr[2];
		 }
		 var consigneeprovince = '';//收省
		 var consigneecity = '';//收市
		 var consigneeregion = '';//收区县
		 var arres= new Array();
		 arres = $("#consigneeprovince").val().split("-");//收货方地址
		 if(arres.length == 2) {
			 consigneeprovince = arres[0];
			 consigneecity = arres[1];
		 }else if(arres.length == 3) {
			 consigneeprovince = arres[0];
			 consigneecity = arres[1];
			 consigneeregion = arres[2];
		 }
		$.ajax({
			url: "../waybillcs/load",   
		 	type:'post',	
		 	dataType:'json', 
		 	data: {jsonText:jsonText,
				 waybillid:$("#waybillid").val(),
				 consigndate:$("#consigndate").val(),
				 consignorlinkman:$("#consignorlinkman").val(),
				 consignortelephonenumber:$("#consignortelephonenumber").val(),
				 consignormobilenumber:$("#consignormobilenumber").val(),
				 consignorprovince:consignorprovince,
				 consignorcity:consignorcity,
				 consignorregion:consignorregion,//县
				 consignortown:$("#consignortown").val(),
				 consignee:$("#consignee").val(),
				 consigneelinkman:$("#consigneelinkman").val(),
				 consigneetelephonenumber:$("#consigneetelephonenumber").val(),
				 consigneemobilenumber:$("#consigneemobilenumber").val(),
				 consigneeprovince:consigneeprovince,
				 consigneecity:consigneecity,
				 consigneeregion:consigneeregion,//县
				 consigneetown:$("#consigneetown").val(),
				 distance:$("#distance").val(),
				 settletype:$("#settletype").val(),
				 receivetype:$("#receivetype").val(),
				 arrivetype:$("#arrivetype").val(),
				 suredate:$("#suredate").val(),  //确认日期
				 backbilltype:$("#backbilltype").val(),
				 backbillnum:$("#backbillnum").val(),
				 urgencydegree:$("#urgencydegree").val(),
				 shiptype:$("#shiptype").val(),
				 clientnumber:$("#clientnumber").val(),
				 memo:$("#memo").val()
				 }, //参数
		   	success:function(data){//回传函数
		   		if(data.msg=="ok"){
		   			ymPrompt.alert({message:"装车确认成功！",handler:closeBack});
		 		}
		 	}
		});
	}
}

function closeBack(){
	var sts = getId().split("=")[2];
	window.location.href="../waybillcs/waybillManager?order=3&sts="+sts;
}





//数据字典
function f_contractc(obj,names,ids,valuess) {
	var types = obj;
	var name = names+ids;
	if(types == '重量单位') {
		var opent = '<option selected="selected" value="1">公斤</option>'+
					'<option value="2">吨</option>';
		$("#"+name).html(opent);
	}else {
		$.ajax({
			url: "../waybillcs/selectListByType",   
		 	type:'post',	
		 	dataType:'json', 
		 	sync:false,
		 	data:{"type":types}, //参数     	               
		   	success:function(data){//回传函数
		 		var dataObj=data;
		 		var opent = ''; 
		 		for(var i=0;i<dataObj.length;i++){
		 			if(dataObj[i].text == valuess) {
		 				opent += '<option selected="selected" value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
		 			}else {
		 				opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
		 			}
		 		}
		 		$("#"+name).html(opent);
		 	}
		});
	}
}

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}

/********根据waybillID查询对象*********/
function select_waybill() {
	var numZ = 0;
	var weightZ = 0;
	var volumeZ = 0;
	$.ajax({
		url: "../waybillcs/selectById", 
	 	type:'post',
	 	sync:false,
	 	dataType:'json',
	 	data:{"waybillid":param}, //参数     	               
	   	success:function(data){//回传函数
	 		 $("#waybillid").val(data.waybillid);
			 $("#frompartyidDIV").html(data.frompartyname);
			 $("#frompartyid").attr("myid",data.frompartyid);
			 $("#clientnumber").val(data.clientnumber);
			 $("#consigndate").val(data.consigndate.substr(0, 10));
			 $("#consignorlinkman").val(data.consignorlinkman);
			 $("#consignortelephonenumber").val(data.consignortelephonenumber);
			 $("#consignormobilenumber").val(data.consignormobilenumber);
			 //发货地
			 var consignorInfo = data.consignorprovince;
			 if(data.consignorcity != ""){
				 consignorInfo = consignorInfo +"-"+ data.consignorcity;
			 }
			 if(data.consignorregion != ""){
				 consignorInfo = consignorInfo +"-"+ data.consignorregion;
			 }
			 $("#consignorprovince").val(consignorInfo);
			 $("#consignortown").val(data.consignortown);
			 $("#consignee").val(data.consignee);
			 $("#consigneelinkman").val(data.consigneelinkman);
			 $("#consigneetelephonenumber").val(data.consigneetelephonenumber);
			 $("#consigneemobilenumber").val(data.consigneemobilenumber);
			 //收获地
			 var consigneeInfo = data.consigneeprovince;
			 if(data.consigneecity != ""){
				 consigneeInfo = consigneeInfo +"-"+ data.consigneecity;
			 }
			 if(data.consigneeregion != ""){
				 consigneeInfo = consigneeInfo +"-"+ data.consigneeregion;
			 }
			 $("#consigneeprovince").val(consigneeInfo);
			 $("#consigneetown").val(data.consigneetown);
			 $("#distance").val(data.distance);
			 $("#settletype").val(data.settletype);
			 $("#receivetype").val(data.receivetype);
			 $("#arrivetype").val(data.arrivetype);
			 $("#backbilltype").val(data.backbilltype);
			 $("#backbillnum").val(data.backbillnum);
			 $("#urgencydegree").val(data.urgencydegree);
			 $("#shiptype").val(data.shiptype);
			 $("#topartyname").html(data.topartyname);
			 
			 $("#memo").val(data.memo);
			 $("#waybillnumberDiv").html(data.waybillnumber);
			 $("#inputmanDiv").html(data.inputman);
			 $("#inputdateDiv").html(formatdate(data.inputdate));
			 //填充货物信息
			if(data.goodslist!=null || data.goodslist.length>0){
	 			var length = data.goodslist.length;
				for(var i =0;i < length; i++){ //展现返回的表格数据
	 				var goods = data.goodslist[i];
	 				var tr=$('<tr style="height: 40px;" id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')"></tr>');
	 				var num = goods.num,factnum = goods.factnum,weight = goods.weight,factweight = goods.factweight;
	 				var volume = goods.volume,factvolume = goods.factvolume;
	 				if(num == ""){num="0"}
	 				if(factnum == ""){factnum="0"}
	 				if(weight == ""){weight="0.0"}
	 				if(factweight == ""){factweight="0.0"}
	 				if(volume == ""){volume="0.0"}
	 				if(factvolume == ""){factvolume="0.0"}
	 				var txtCell = '<td class="tdcenter"><input type="hidden" name="goodsid" id="goodsid"'+i+' value="'+ goods.goodsid +
	 					'"/><input name="goodsname" style="width:100%;height: 25px;" id="goodsname'+i+'" value="'+goods.goodsname+'"/></td>'+
	 					'<td class="tdcenter"><select name="goodstype" style="width:100%;height: 25px;" id="goodstype'+i+'"></select></td>'+
	 					'<td class="tdcenter"><input name="spec" style="width:100%;height: 25px;" id="spec'+i+'" value="'+goods.spec+'"/></td>'+
	 					'<td class="tdcenter"><input name="model" style="width:100%;height: 25px;" id="model'+i+'" value="'+goods.model+'"/></td>'+
	 					'<td class="tdcenter">'+num+'</td>'+
	 					'<td class="tdcenter"><input name="factnum" id="factnum"'+i+'" style="width:100%;height: 25px;" type="text" value="'+factnum+'" onblur="factNum_count()"/></td>'+
	 					'<td class="tdcenter"><select name="packagename" style="width:100%;height: 25px;" id="packagename'+i+'"></select></td>'+
	 					'<td class="tdcenter">'+weight+'</td>'+
	 					'<td class="tdcenter"><input name="factweight" id="factweight"'+i+'" style="width:100%;height: 25px;" type="text" value="'+factweight+'" onblur="factNum_count()"/></td>'+
	 					'<td class="tdcenter">公斤</td>'+
	 					'<td class="tdcenter">'+goods.volume+'</td>'+
	 					'<td class="tdcenter"><input name="factvolume" id="factvolume"'+i+'" style="width:100%;height: 25px;" type="text" value="'+goods.factvolume+'" onblur="factNum_count()"/></td>'+
	 					'<td class="tdcenter"><select name="measuretype" style="width:100%;height: 25px;" id="measuretype'+i+'"></select></td>';
	 				
	 				f_contractc('货物类型','goodstype',i,goods.goodstype);
 					f_contractc('包装单位','packagename',i,goods.packagename);
 					f_contractc('计费方式','measuretype',i,goods.measuretype);
	 				$(txtCell).appendTo(tr);
					$("#hgrid tr:last").after(tr);
					numZ += parseFloat(factnum);
					weightZ += parseFloat(factweight);
					volumeZ += parseFloat(factvolume);
	 			}
				$("#numZ").html((numZ));
				$("#weightZ").html((weightZ)+" 公斤");
				$("#volumeZ").html((volumeZ)+" 立方");
	 			for(var i = data.length;i< length;i++){
	 				 $('#tr'+i).empty();
	 			}
	 		}
	 	}
	});
}

/************自动计算数量、重量、体积、声明价值的总和****************/
function factNum_count(){
	var numZ = 0;
	var weightZ = 0;
	var volumeZ = 0;
	var $num=$("input[name='factnum']");//数量
	var $weight=$("input[name='factweight']");//重量
	var $volume=$("input[name='factvolume']");//体积
	$.each($("input[name='goodsid']"),function(n,item){
        var num=$($num[n]).val(); //数量
        var weight=$($weight[n]).val(); //重量
        var volume=$($volume[n]).val(); //体积
        if(num != '') {
        	 numZ += parseFloat(num);
        }
        if(weight != '') {
        	weightZ += parseFloat(weight);
        }
        if(volume != '') {
        	volumeZ += parseFloat(volume);
        }
	});
	$("#numZ").html((numZ).toFixed(2));
	$("#weightZ").html((weightZ).toFixed(3)+" 公斤");
	$("#volumeZ").html((volumeZ).toFixed(3));
}

/****************此方法用来把多条物品封装成json****************/
function createArray() {
	var jsonarray=[];
    var $goodsid=$("input[name='goodsid']");
    var $goodsname=$("input[name='goodsname']");
    var $goodstype=$("select[name='goodstype']");
    var $spec=$("input[name='spec']");
    var $model=$("input[name='model']");
    var $packagename=$("select[name='packagename']");
    var $weightType=$("select[name='weightType']");
    var $claimvalue=$("input[name='claimvalue']");
    var $measuretype=$("select[name='measuretype']");
    var $factnum = $("input[name='factnum']");
    var $factweight = $("input[name='factweight']");
    var $factvolume = $("input[name='factvolume']");
    var $unitweight = $("input[name='unitWeight']");
    var $unitvolume = $("input[name='unitVolume']");
    
    $.each($("input[name='goodsid']"),function(n,item){
        var goodsid= $($goodsid[n]).val();
        var goodsname=$($goodsname[n]).val();
        var goodstype=$($goodstype[n]).val(); 
        var spec=$($spec[n]).val(); 
        var model=$($model[n]).val(); 
        var packagename=$($packagename[n]).val(); 
        var weightType=$($weightType[n]).val(); 
        var claimvalue=$($claimvalue[n]).val(); 
        var measuretype=$($measuretype[n]).val();
        var factnum = $($factnum[n]).val();
        var factweight = $($factweight[n]).val();
        var factvolume = $($factvolume[n]).val();
        var unitweight = $($unitweight[n]).val();//单位重量
        var unitvolume = $($unitvolume[n]).val();
        var obj=createObj(goodsid,goodsname,goodstype,spec,model,factnum,packagename,factweight,weightType,factvolume,claimvalue,measuretype,unitweight,unitvolume);
        jsonarray.push(obj);
    })
    return jsonarray;
}

function createObj(goodsid,goodsname,goodstype,spec,model,factnum,packagename,factweight,weightType,factvolume,claimvalue,measuretype,unitweight,unitvolume){
	return {
		goodsid:goodsid,
		goodsname:goodsname,
		goodstype:goodstype,
		spec:spec,
		model:model,
		factnum:factnum,
		packagename:packagename,
		factweight:factweight,
		weightType:weightType,
		factvolume:factvolume,
		claimvalue:claimvalue,
		measuretype:measuretype,
		unitweight:unitweight,
		unitvolume:unitvolume
    }
}

//日期字符串转为yyyy-MM-dd HH:mm:ss
function formatdate(dateStr){
	var targetDateStr = dateStr;
	var index = dateStr.indexOf(".");
	if(index != -1 ){
		targetDateStr = dateStr.slice(0,index);
	}
	return targetDateStr;
}

/**********手机校验valeues是值***********/
function bilenumber(obj) {
    var bilenumber = $(obj).val();
	var len = bilenumber.length;   //验证长度
	if(bilenumber != '') {
		if(len != 11) {
			ymPrompt.alert("手机号码长度为11位,请修正后在提交！");
			return false;
		} 
		var reg = /^0{0,1}(13[0-9]|145|147|15[0-3]|15[5-9]|18[0-9])[0-9]{8}$|^$/;
		var result = reg.exec(bilenumber);
		if(result == null){
			ymPrompt.alert("手机号不合法,请修正后在提交！");
			return false;
		}
	}
}

function checkData(){
	var bool = false;
	var $factnum = $("input[name='factnum']");
    var $factweight = $("input[name='factweight']");
    var $factvolume = $("input[name='factvolume']");
	$.each($("input[name='goodsid']"),function(n,item){
		var factnum = $($factnum[n]).val();
		if(factnum=='' || isNaN(factnum)){
			$($factnum[n]).css("border","1px solid red");
			return false;
		}else{
			$($factnum[n]).css("border","1px solid #CCCCCC");
			bool = true;
		}
        var factweight = $($factweight[n]).val();
        if(factweight=='' || isNaN(factweight)){
			$($factweight[n]).css("border","1px solid red");
			return false;
		}else{
			$($factweight[n]).css("border","1px solid #CCCCCC");
			bool = true;
		}
        var factvolume = $($factvolume[n]).val();
        if(factvolume=='' || isNaN(factvolume)){
			$($factvolume[n]).css("border","1px solid red");
			return false;
		}else{
			$($factvolume[n]).css("border","1px solid #CCCCCC");
			bool = true;
		}
	});
	return bool;
}

/******js获取当前日期和时间，传参dete是日期，time是时间,detetime是日期时间*******/
function myDates(vales) {
	var str = vales;
	var detetime = '';
	var myDate = new Date();
	var yyyy = myDate.getFullYear();//获取完整的年份(4位,1970-????)
	var MM = myDate.getMonth();//获取当前月份(0-11,0代表1月)
	var dd = myDate.getDate();//获取当前日(1-31)
	var HH = myDate.getHours(); //获取当前小时数(0-23)
	var mm = myDate.getMinutes();//获取当前分钟数(0-59)
	var ss = myDate.getSeconds();    //获取当前秒数(0-59)
	if(str == 'dete') {//日期
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		detetime = yyyy+'-'+MM+'-'+dd;
	}
	if(str == 'time') {//时间
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = HH+':'+mm+':'+ss;
	}
	if(str == 'detetime') {
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = yyyy+'-'+MM+'-'+dd+" "+HH+':'+mm+':'+ss;
	}
	return detetime;
}