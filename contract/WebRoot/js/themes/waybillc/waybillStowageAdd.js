function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}

$(function(){
	//加载运单
 	f_hgrid_json(getId());
 	//下拉框填充值
	f_contract('车辆类型','cartype','');
	$("#dispatchdate").val(myDates("detetime"));
	$("#topartyname").val(idUrl.split("&")[1].split("=")[1]);
});

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}

function f_hgrid_json(param) {// 填充hGrid数据
	$("#loading").css("margin-top", "80px");
	$.ajax({
		url : "../waybillcs/stowage",
		type : 'post',
		dataType : 'json',
		data : param, // 参数
		success : function(data) {// 回传函数
			$("#loading img").hide();
			$("#loading").hide();
			var count=data.Count;
			data=eval(data.Data);
			$("#topartyid").val(data[0].topartyid);
			for(var i= 0;i<count;i++){// 展现返回的表格数据
				var tr=$('<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')"></tr>');
				var txtCell = '<td class="tdleft" width="110px">'+data[i].waybillnumber+'</td>'
					+ '<td class="tdleft">'+ data[i].frompartyname + '</td>'
					+ '<td class="tdleft clientnumber">'+ data[i].clientnumber + '</td>'
					+ '<td class="tdleft sourcesystem" style="display: none;">'+ data[i].sourcesystem+ '</td>'
					+ '<td class="tdleft">'+ data[i].consignorprovince + data[i].consignorcity + data[i].consignorregion + '</td>'
					+ '<td class="tdleft">'+ data[i].consignortown + '</td>'
					+ '<td class="tdleft">'+ data[i].consigneetown + '</td>'
					+ '<td class="tdleft">'+ data[i].consigneeprovince + data[i].consigneecity + data[i].consigneeregion + '</td>'
					+ '<td class="tdleft">'+ data[i].factnum + '</td>'
					+ '<td class="tdcenter">'+ data[i].factweight + '</td>'
					+ '<td class="tdleft">'+ data[i].factvolume+ '</td>'
					+ '<td class="tdleft topartyname" style="display: none;">'+ data[i].topartyname+ '</td>'
					;
				$(txtCell).appendTo(tr);
				$("#hgrid tr:last").after(tr);
			}
			$("#hgrid").show();
		}
	});
}

function btnSave(){
	if(checkData()){
		//屏蔽保存按钮，防止多次点击提交
		$("#save").hide();
		var drivername=trim($("#drivername").val());
		var carplatenumber=trim($("#carplatenumber").val());
		var paperdispatchnumber=trim($("#paperdispatchnumber").val());
		var cartype=$("#cartype").val();
		var drivermobile=trim($("#drivermobile").val());
		var dispatchdate=$("#dispatchdate").val();
		var topartyid=$("#topartyid").val();
		var topartyname=$(".topartyname").text();

		var clientnumber=$(".clientnumber");
		var clientnumberStr='';
		$.each(clientnumber, function(index, item){
			if($(item).next().text()==="RYP"){
				clientnumberStr=clientnumberStr+$(item).text()+",";
			}
		});
		clientnumberStr=clientnumberStr.substring(0, clientnumberStr.length-1);
		
		var param = "drivername="+drivername+"&carplatenumber="+carplatenumber+"&carrier="+$("#topartyname").val()+"&paperdispatchnumber="+paperdispatchnumber+
			"&cartype="+cartype+"&drivermobile="+drivermobile+"&dispatchdate="+dispatchdate+"&"+getId()+"&topartyid="+topartyid+"&topartyname="+topartyname
			+"&clientnumberStr="+clientnumberStr;
		$.ajax({
			url: "../waybillstowagecs/insert",   
		 	type:'post',	
		 	dataType:'json', 
		 	data: param, //参数     	               
		   	success:function(data){//回传函数
				if(data.msg=="ok"){
		   			ymPrompt.alert({message:"配载成功！",handler:closeBack});
		   			if(clientnumberStr===""){
		   				return false;
		   			}else{
		   				$.ajax({
			   				url: "../waybillstowagecs/waybillStowageCommit",
			   				type: 'post',
			   				dataType: 'json',
			   				data: param,
			   				success: function(dat){
		   						ymPrompt.alert({message:"发送XML成功！",handler:closeBack});
			   				},
			   				error: function(){
			   					ymPrompt.alert({message:"发送XML失败！",handler:closeBack});
			   				}
			   			});
		   			}
		 		}
		 	}
		});
	}
}

function closeBack(){
	window.parent.ymPrompt.doHandler('ok',true);
}

function markText(targetId,message){
	var _targetId=targetId.substr(1);//去掉#
	if($(targetId+"_message").length==0){//当信息提示div不存在时
		var top=$(targetId).position().top+parseInt($(targetId).css("height"));
		var left=$(targetId).position().left;
		var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("body").append(txt);
	}else{//当信息提示div存在时
		$(targetId+"_message").show();
	}
	$(targetId).css("border","2px solid red");
	return targetId+"_message";
}

function checkData(){
	//司机姓名
	var carplatenumber = $("#carplatenumber");
	if(trim($(carplatenumber).val())==""){
		//提示样式
		markText("#carplatenumber","不能为空！");
		return false;
	}else{
		$("#carplatenumber_message").hide();
		$(carplatenumber).css("border","1px solid #CCCCCC");
	}
	//司机手机
	var drivermobile = $("#drivermobile");
	if(trim($(drivermobile).val())==""){
		markText("#drivermobile","不能为空！");
		return false;
	}else{
		if(bilenumber(drivermobile)){
			$("#drivermobile_message").hide();
			$(drivermobile).css("border","1px solid #CCCCCC");
		}else{
			return false;
		}
	}
	return true;
}
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
}
/**数据字典-填充下拉框
 * obj: 数据类型 not null
 * names:字段名称 not null
 * ids：下拉框index标识 null
*/
function f_contract(obj,names,ids) {
	var types = obj;
	var name = names+ids;
	$.ajax({
		url: "../waybillcs/selectListByType",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":types}, //参数     	               
	   	success:function(data){//回传函数
	 		var dataObj=eval(data);
	 		var opent = '<option selected="selected" value=""></option>'; 
	 		for(var i=0;i<dataObj.length;i++){
	 			opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
	 		}
	 		$("#"+name).html(opent);
	 	}
	});
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

/**********手机校验valeues是值***********/
function bilenumber(obj) {
    var bilenumber = trim($(obj).val());
    $(obj).val(bilenumber)
    //alert("'"+bilenumber)
	var len = bilenumber.length;   //验证长度
	if(bilenumber != '') {
		if(len != 11) {
			ymPrompt.alert("司机手机号码长度为11位,请修正后在提交！");
			return false;
		} 
		var reg = /^0{0,1}(13[0-9]|145|147|15[0-3]|15[5-9]|18[0-9])[0-9]{8}$|^$/;
		var result = reg.exec(bilenumber);
		if(result == null){
			ymPrompt.alert("司机手机号不合法,请修正后在提交！");
			return false;
		}
	}
	return true;
}

