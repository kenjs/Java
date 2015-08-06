var param;

$(function(){
	getid();
	select_waybill();
	$("#consigndate").val(myDates('dete'));
	f_contract('货物类型','goodstype','');
	f_contract('包装单位','packagename','');
	f_contract('计费方式','measuretype','');
	f_contract('结算方式','settletype','');
	f_contract('收货方式','receivetype','');
	f_contract('到货方式','arrivetype','');
	f_contract('回单方式','backbilltype','');
	f_contract('运输方式','shiptype','');
	f_contract('紧急程度','urgencydegree','');
	f_contract('重量单位','weightType','');
	//首次加载下拉框值放在 隐藏域
	f_contract('货物类型','goodstype',0);
	f_contract('包装单位','packagename',0);
	f_contract('重量单位','weightType',0);
	f_contract('计费方式','measuretype',0);
});

function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	param=idUrl
}

function tabendAdd(){
	 var count=$("#hgrid tr").length;
	 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)","style":"height:40px;"}); //创建tr
	 var td0=$("<td><input type='hidden' name='goodsid' id='goodsid"+count+"' value='' /><input type='hidden' name='unitWeight' id='unitWeight"+count+"' value='0' /><input type='hidden' name='unitVolume' id='unitVolume"+count+"' value='0'/><span class='frompartyrealname' style='display:none;color:Red;font-size:10px;'></span><div id='auto'></div></td>");// create 1st td
	 var td1=$("<td></td>");//create 2nd td
	 var td2=$("<td></td>");// create 3rd td
	 var td3=$("<td></td>");// create 4th td
	 var td4=$("<td></td>");// create 5th td
	 var td5=$("<td></td>");// create 6th td
	 var td6=$("<td></td>");// create 6th td
	 var td7=$("<td></td>");// create 6th td
	 var td8=$("<td></td>");// create 6th td
	 var td9=$("<td></td>");// create 6th td
	 var td10=$("<td></td>");// create 6th tdonfocus="onFocues_goods(this)" onblur="consignor_goods(this)"
	 var input00=$("<input/>").attr({"id":"goodsname"+count,"class":"com_input","type":"text","name":"goodsname","defaultvalue":"1","style":"width:85px","onfocus":"onFocues_goods(this,'"+count+"')","onblur":"consignor_goods(this,'"+count+"')"});
	 $(input00).appendTo(td0);
	 $(td0).appendTo(hgrid_tr);
	 var input01=$("<select></select>").attr({"id":"goodstype"+count,"type":"text","name":"goodstype","defaultvalue":"1","style":"width:70px;height:25px;padding-top:4px;"});
	 $(input01).appendTo(td1);
	 $(td1).appendTo(hgrid_tr);
	 var input02=$("<input/>").attr({"id":"spec"+count,"class":"com_input","type":"text","name":"spec","defaultvalue":"1","style":"width:65px;"});
	 $(input02).appendTo(td2);
	 $(td2).appendTo(hgrid_tr);
	 var input03=$("<input/>").attr({"id":"model"+count,"class":"com_input","type":"text","name":"model","defaultvalue":"1","style":"width:65px;"});
	 $(input03).appendTo(td3);
	 $(td3).appendTo(hgrid_tr);
	 var input04=$("<input/>").attr({"id":"num"+count,"onkeyup":"myNumberictow(this)","class":"com_input","type":"text","name":"num","defaultvalue":"1","style":"width:55px;","onfocus":"input_onfocuses('num"+count+"')","onblur":"num_goods(this,'"+count+"')"});
	 $(input04).appendTo(td4);
	 $(td4).appendTo(hgrid_tr);
	 var input05=$("<select></select>").attr({"id":"packagename"+count,"type":"text","name":"packagename","defaultvalue":"1","style":"width:60px;height:25px;padding-top:4px;"});
	 $(input05).appendTo(td5);
	 $(td5).appendTo(hgrid_tr);
	 var input06=$("<input/>").attr({"id":"weight"+count,"onkeyup":"myNumberic(this)","class":"com_input","type":"text","name":"weight","defaultvalue":"1","style":"width:70px;","onblur":"num_count()"});
	 $(input06).appendTo(td6);
	 $(td6).appendTo(hgrid_tr);
	 var input07=$("<select></select>").attr({"id":"weightType"+count,"type":"text","name":"weightType","defaultvalue":"1","style":"width:70px;height:25px;padding-top:4px;"});
	 $(input07).appendTo(td7);
	 $(td7).appendTo(hgrid_tr);
	 var input08=$("<input/>").attr({"id":"volume"+count,"onkeyup":"myNumberic(this)","class":"com_input","type":"text","name":"volume","defaultvalue":"1","style":"width:65px;","onblur":"num_count()"});
	 $(input08).appendTo(td8);
	 $(td8).appendTo(hgrid_tr);
	 var input9=$("<select></select>").attr({"id":"measuretype"+count,"type":"text","name":"measuretype","defaultvalue":"1","style":"width:65px;height:25px;padding-top:4px;"});
	 $(input9).appendTo(td9);
	 $(td9).appendTo(hgrid_tr);
	 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this,'"+count+"')"}).css("margin-left","20px");
	 $(a07).html("删除");
	 $(a07).appendTo(td10);
	 $(td10).appendTo(hgrid_tr);
	 $("#hgrid tr:last").after(hgrid_tr);
	 //多次添加信息，无需从后台再去取下拉框值，避免异步加载赋值不及时问题
	 $("#goodstype"+count).html($("#goodstype0").html());
	 $("#packagename"+count).html($("#packagename0").html());
	 $("#weightType"+count).html($("#weightType0").html());
	 $("#measuretype"+count).html($("#measuretype0").html());
	 //f_contract('货物类型','goodstype',count);
	 //f_contract('包装单位','packagename',count);
	 //f_contract('重量单位','weightType',count);
	 //f_contract('计费方式','measuretype',count);
	 countSize = count;
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

/********根据waybillID查询对象*********/
function select_waybill() {
	$.ajax({
		url: "../waybillamountcs/selectWaybillById",   
	 	type:'post',
	 	sync:false,
	 	dataType:'json', 
	 	data:{"waybillid":param}, //参数     	               
	   	success:function(data){//回传函数
	 		 $("#waybillid").val(data.waybillid);
			 $("#frompartyid").attr("myid",data.frompartyid);
			 $("#frompartyid").val(data.frompartyname);
			 $("#partyid").val(data.partyid);
			 $("#waybillnumber").val(data.waybillnumber);
			 $("#status").val(data.status);
			 //alert(data.outsettlebillid);
			 $("#outsettlebillid").val(data.outsettlebillid);
			 $("#consigndate").val(data.consigndate.substr(0, 10));
			 $("#consignorlinkman").val(data.consignorlinkman);
			 $("#consignortelephonenumber").val(data.consignortelephonenumber);
			 $("#consignormobilenumber").val(data.consignormobilenumber);
			 $("#consignorprovince").val(data.consignorprovince+"-"+data.consignorcity+"-"+data.consignorregion);
			 $("#consignortown").val(data.consignortown);
			 $("#consignee").val(data.consignee);
			 $("#consigneelinkman").val(data.consigneelinkman);
			 $("#consigneetelephonenumber").val(data.consigneetelephonenumber);
			 $("#consigneemobilenumber").val(data.consigneemobilenumber);
			 $("#consigneeprovince").val(data.consigneeprovince+"-"+data.consigneecity+"-"+data.consigneeregion);
			 $("#consigneetown").val(data.consigneetown);
			 $("#distance").val(data.distance);
			 $("#settletype").val(data.settletype);
			 $("#receivetype").val(data.receivetype);
			 $("#arrivetype").val(data.arrivetype);
			 $("#backbilltype").val(data.backbilltype);
			 $("#backbillnum").val(data.backbillnum);
			 $("#urgencydegree").val(data.urgencydegree);
			 $("#shiptype").val(data.shiptype);
			 $("#topartyid").attr("myid",data.topartyid);
			 $("#topartyid").val(data.topartyname);
			 $("#clientnumber").val(data.clientnumber);
			 $("#memo").val(data.memo);
			 $("#inputman").val(data.inputman);
			 $("#inputdate").val(formatdate(data.inputdate));
			 $("#operatorid").val(data.operatorid); 
			 $("#operatorid").val(data.operatorid);
			 $("#waybillnumberDiv").html(data.waybillnumber);
			 $("#inputmanDiv").html(data.inputman);
			 $("#inputdateDiv").html(formatdate(data.inputdate));
			 $("#suremanDiv").html(data.sureman);
			 var datae = eval(data.goodslist);
			 var numZ = 0;
			 var weightZ = 0;
			 var volumeZ = 0;
			 for(var i=0;i<datae.length;i++) {
				 if(i==0) {
 					$("#goodsid").val(datae[i].goodsid);
 					$("#goodsname").val(datae[i].goodsname);
 					$("#goodstype").val(datae[i].goodstype);
 					$("#spec").val(datae[i].spec);
 					$("#model").val(datae[i].model);
 					$("#num").val(datae[i].num);
 					$("#packagename").val(datae[i].packagename);
 					$("#weight").val(datae[i].weight);
 					$("#volume").val(datae[i].volume);
 					$("#measuretype").val(datae[i].measuretype);
 					$("#unitWeight").val(datae[i].unitweight);
 					$("#unitVolume").val(datae[i].unitvolume);
 					if(datae[i].num != ''){
 						numZ += parseFloat(datae[i].num);
 					}
 					if(datae[i].weight != ''){
	 					weightZ += parseFloat(datae[i].weight);
 					}
 					if(datae[i].volume != ''){
	 					volumeZ += parseFloat(datae[i].volume);
 					}
 				}else {
 					tabendAdd();
 					$("#goodsid"+(i+1)).val(datae[i].goodsid);
 					$("#goodsname"+(i+1)).val(datae[i].goodsname);
 					$("#goodstype"+(i+1)).val(datae[i].goodstype);
 					$("#spec"+(i+1)).val(datae[i].spec);
 					$("#model"+(i+1)).val(datae[i].model);
 					$("#num"+(i+1)).val(datae[i].num);
 					$("#packagename"+(i+1)).val(datae[i].packagename);
 					$("#weight"+(i+1)).val(datae[i].weight);
 					$("#volume"+(i+1)).val(datae[i].volume);
 					$("#measuretype"+(i+1)).val(datae[i].measuretype);
 					$("#unitWeight"+(i+1)).val(datae[i].unitweight);
 					$("#unitVolume"+(i+1)).val(datae[i].unitvolume);
 					if(datae[i].num != ''){
 						numZ += parseFloat(datae[i].num);
 					}
 					if(datae[i].weight != ''){
	 					weightZ += parseFloat(datae[i].weight);
 					}
 					if(datae[i].volume != ''){
	 					volumeZ += parseFloat(datae[i].volume);
 					}
 					f_contractc('货物类型','goodstype',(i+1),datae[i].goodstype);
 					f_contractc('包装单位','packagename',(i+1),datae[i].packagename);
 					f_contractc('计费方式','measuretype',(i+1),datae[i].measuretype);
 					f_contractc('重量单位','weightType',(i+1),'');
 				}
			 }
			$("#numZ").html(numZ);
			$("#weightZ").html(weightZ+" 公斤");
			$("#volumeZ").html(volumeZ+"立方");
	 	}
	});
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

document.write('<script src="../js/sys/tf56sys.js" type=text/javascript></script>');
document.write('<script src="../js/sys/ddw/jquery.autocomplete.js" type=text/javascript></script>');
document.write('<script  src="../js/sys/ddw/jquery.bgiframe-2.1.2.js" type=text/javascript></script>');
document.write('<script src="../js/uploadify/jquery.uploadify.min.js" type=text/javascript></script>');
document.write('<script type=text/javascript src="../js/sys/ddw/jquery.bgiframe-2.1.2.js"></script>');
var consignorprovince = '';//开始地
var consigneeprovince = '';//目的地 
var countSize = 0;
var billNumberFlag="";//发货方检验标识
var consignorlinkmanFlag="";//发货联系人检验标识 

var consigneeFlag="";//收货方检验标识

function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
}
function showOverlay() { 
   $("#overlay").height(pageHeight());
   $("#overlay").width(pageWidth());
    // fadeTo第一个参数为速度，第二个为透明度
   // 多重方式控制透明度，保证兼容性，但也带来修改麻烦的问题
    $("#overlay").fadeTo(200, 0.5);
}

 /* 隐藏覆盖层 */
 function hideOverlay() {
     $("#overlay").fadeOut(200);
 }
/* 当前页面高度 */
 function pageHeight() {
     return document.body.scrollHeight;
 }
 /* 当前页面宽度 */
function pageWidth() {
    return document.body.scrollWidth;
 }


function input_onfocus(obj){
	$(obj).css("border","1px solid  #a0cfe0");
}

function input_onfocuses(vale){
	$("#"+vale).css("border","1px solid  #a0cfe0");
	$("#"+vale+"_message").remove();
}

/**检测当前input框输入值是否为空 or 是否满足输入条件
 * *********/
function input_onblur(obj){
	var fromaddress,toaddress,distance,subcontractor;//currentvalue
	var defaultfromaddress,defaulttoaddress,defaultdistance,defaultsubcontractor;//defaultvalue
	var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
	var citydistanceid=$(obj).parent().parent().children("td").children('input[name=citydistanceid]');
	$.each(inputset,function(index,item){
		switch(index){
		case 0:
			fromaddress=$(item).val();
			if(fromaddress==""){
				$(item).css("border","1px solid red");
			}
			break;
		case 3:
			subcontractor=$(item).val();
			if(subcontractor==""){
				$(item).css("border","1px solid red");
			}
			break;			
		}
	});
}

/**删除物品行***/
function f_delete(obj){
	var index_current =$(obj).parent().parent().index();
	var goodsid=$(obj).parent().parent().children("td").children("input[name=goodsid]").val();
	if(goodsid != null && goodsid != '') {
		ymPrompt.confirmInfo({message:'确定删除物品吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
			function(status){
			if(status=='ok'){
				$.ajax({
					url: "../waybillcs/deteleGoods",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:{"goodsid":goodsid}, //参数     	               
				 	success:function(data){//回传函数
				 		if(data.msg == 'ok') {
				 				$.each($("#hgrid tr"),function(index,item){
				 					if(index_current<index){
				 						$(item).children('td').children('input[name=goodsid]').attr("id","goodsid"+(index));
				 						$(item).children('td').children('input[name=goodsname]').attr("id","goodsname"+(index));
				 						$(item).children('td').children('input[name=goodsname]').attr("onblur","consignor_goods(this,'"+index+"')");
				 						$(item).children('td').children('input[name=unitWeight]').attr("id","unitWeight"+(index));
				 						$(item).children('td').children('input[name=unitVolume]').attr("id","unitVolume"+(index));
				 						$(item).children('td').children('input[name=goodstype]').attr("id","goodstype"+(index));
				 						$(item).children('td').children('input[name=spec]').attr("id","spec"+(index));
				 						$(item).children('td').children('input[name=model]').attr("id","model"+(index));
				 						$(item).children('td').children('input[name=num]').attr("id","num"+(index));
				 						$(item).children('td').children('input[name=num]').attr("onblur","num_goods(this,'"+index+"')");
				 						$(item).children('td').children('input[name=packagename]').attr("id","packagename"+(index));
				 						$(item).children('td').children('input[name=weight]').attr("id","weight"+(index));
				 						$(item).children('td').children('input[name=weightType]').attr("id","weightType"+(index));
				 						$(item).children('td').children('input[name=volume]').attr("id","volume"+(index));
				 						$(item).children('td').children('input[name=measuretype]').attr("id","measuretype"+(index));
				 						countSize = index-1;
				 					}
				 				});	
				 				$(obj).parent().parent().remove();
				 				$("#auto").hide("slow");
				 				num_count();
				 			    ymPrompt.alert("物品删除成功！");
				 		}
				 	}
				});
			}else{
				return false;
			}
			}
			})
	}else{
		$.each($("#hgrid tr"),function(index,item){
				if(index_current<index){
					$(item).children('td').children('input[name=goodsid]').attr("id","goodsid"+(index));
					$(item).children('td').children('input[name=goodsname]').attr("id","goodsname"+(index));
					$(item).children('td').children('input[name=goodsname]').attr("onblur","consignor_goods(this,'"+index+"')");
					$(item).children('td').children('input[name=unitWeight]').attr("id","unitWeight"+(index));
					$(item).children('td').children('input[name=unitVolume]').attr("id","unitVolume"+(index));
					$(item).children('td').children('input[name=goodstype]').attr("id","goodstype"+(index));
					$(item).children('td').children('input[name=spec]').attr("id","spec"+(index));
					$(item).children('td').children('input[name=model]').attr("id","model"+(index));
					$(item).children('td').children('input[name=num]').attr("id","num"+(index));
					$(item).children('td').children('input[name=num]').attr("onblur","num_goods(this,'"+index+"')");
					$(item).children('td').children('input[name=packagename]').attr("id","packagename"+(index));
					$(item).children('td').children('input[name=weight]').attr("id","weight"+(index));
					$(item).children('td').children('input[name=weightType]').attr("id","weightType"+(index));
					$(item).children('td').children('input[name=volume]').attr("id","volume"+(index));
					$(item).children('td').children('input[name=measuretype]').attr("id","measuretype"+(index));
					countSize = index-1;
				}
		});
		$(obj).parent().parent().remove();
			$("#auto").hide("slow");
			num_count();
	}
}

//数据字典
function f_contract(obj,names,ids) {
	var types = obj;
	var name = names+ids;
	if(types == '重量单位') {
		var opent ='<option value="1">公斤</option>'+
					'<option value="2">吨</option>';
		$("#"+name).html(opent);
	}else {
		$.ajax({
			url: "../waybillcs/selectListByType",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{"type":types}, //参数     	               
		   	success:function(data){//回传函数
		 		var dataObj=eval(data);
		 		var opent = ''; 
		 		if(types == '收货方式' || types == '到货方式') {
		 			for(var i=(dataObj.length-1);i>=0;i--){
			 			opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
			 		}
		 		}else{
		 			for(var i=0;i<dataObj.length;i++){
			 			opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
			 		}
		 		}
		 		$("#"+name).html(opent);
		 	}
		});
	}
}

/**********根据发货方查询相关详细信息*************/
function consignor_onblur(myid){
		var toPartyId = myid;
		//发货方联系人，联系电话，手机，地址，详细地址
		$.ajax({
			url: "../waybillcs/selectfrompartyParty",
			 type:'post',	
			 dataType:'json',
			 async: false,
			 data:{topartyid:toPartyId,consigneeorconsignor:0,checked:1}, //参数     	               
			 success:function(data){//回传函数
				var data=eval(data);
				if(data.length == 1) {
					$("#consignorlinkman").val(data[0].linkMan);//联系人
				 	$("#consignortelephonenumber").val(data[0].telephoneNumber);//联系电话
				 	$("#consignormobilenumber").val(data[0].mobileNumber);//手机
				 	if(data[0].region == null || data[0].region == "") {
				 		$("#consignorprovince").val(data[0].province+"-"+data[0].city);//发货地
				 		consignorprovince = data[0].province+"-"+data[0].city;
				 	}else {
				 		$("#consignorprovince").val(data[0].province+"-"+data[0].city+"-"+data[0].region);//发货地
				 		consignorprovince = data[0].province+"-"+data[0].city+"-"+data[0].region;
				 	}
				 	$("#consignortown").val(data[0].town);//详细地址
				}
			}
		});
		
		//收货方名称，收货联系人，联系电话，手机，地址，详细地址
		$.ajax({
			url: "../waybillcs/selectfrompartyParty",   
			 type:'post',	
			 dataType:'json',
			 async: false,
			 data:{topartyid:toPartyId,consigneeorconsignor:1,checked:1}, //参数     	               
			 success:function(data){//回传函数
				var data=eval(data);
				if(data.length == 1) {
					$("#consignee").val(data[0].consignee);//收货方
				 	$("#consigneelinkman").val(data[0].linkMan);//收货联系人
				 	$("#consigneetelephonenumber").val(data[0].telephoneNumber);//收货联系电话
				 	$("#consigneemobilenumber").val(data[0].mobileNumber);//手机
				 	if(data[0].region == null || data[0].region == "") {
				 		$("#consigneeprovince").val(data[0].province+"-"+data[0].city);//收货地
				 		consigneeprovince = data[0].province+"-"+data[0].city;
				 	}else{
				 		$("#consigneeprovince").val(data[0].province+"-"+data[0].city+"-"+data[0].region);//收货地
				 		consigneeprovince = data[0].province+"-"+data[0].city+"-"+data[0].region;
				 	} 
				 	$("#consigneetown").val(data[0].town);//详细地址
				}
			}
		});
		
		//发货方默认货物信息
		$.ajax({
			url: "../waybillcs/goodsnameList",   
			 type:'post',	
			 dataType:'json', 
			 data:{topartyid:toPartyId,checked:1}, //参数     	               
			 success:function(data){//回传函数
				var data=eval(data);
				if(data.length == 1) {
					$("#goodsname").val(data[0].goodsName);//货物名称
					$("#unitWeight").val(data[0].unitWeight);//货物单位重量
					$("#unitVolume").val(data[0].unitVolume);//货物单位体积
					$("#goodstype").val(data[0].goodsType);//货物类型
					$("#spec").val(data[0].spec);//货物规格
					$("#model").val(data[0].model);//货物规格
				 	$("#packagename").val(data[0].package);//货物包装
				 	$("#weightType").val(data[0].unit);//货物重量单位 1表示公斤 2表示吨
				 	$("#measuretype").val(data[0].chargeType);//货物计费方式
				}
			}
		});
		consignor_cityDistance(consignorprovince,consigneeprovince);//得到城区间距离
}

/**********根据发货方联系人id查询详细信息*************/
function consignorlinkname(myid){
		var consigneeConsignorAddressId = myid;
		//发货方联系人，联系电话，手机，地址，详细地址
		$.ajax({
			url: "../waybillcs/selectConsigneeConsignorAddressId",   
			type:'post',	
			dataType:'json',
			async: false,
			data:{consigneeconsignoraddressid:consigneeConsignorAddressId}, //参数     	               
			success:function(data){//回传函数
				$("#consignortelephonenumber").val(data.telephonenumber);//联系电话
				$("#consignormobilenumber").val(data.mobilenumber);//手机
				if(data.region == null || data.region == "") {
					$("#consignorprovince").val(data.province+"-"+data.city);//发货地
					consignorprovince = data.province+"-"+data.city;
				}else {
					$("#consignorprovince").val(data.province+"-"+data.city+"-"+data.region);//发货地
					consignorprovince = data.province+"-"+data.city+"-"+data.region;
				}
				$("#consignortown").val(data.town);//详细地址
			}
		});
		consignor_cityDistance(consignorprovince,consigneeprovince);//得到城区间距离
}

/**********根据收货方id查询详细信息*************/
function consigneelinkname(myid){
		var consigneeConsignorAddressId = myid;
		//发货方联系人，联系电话，手机，地址，详细地址
		$.ajax({
			url: "../waybillcs/selectConsigneeConsignorAddressId",   
			 type:'post',	
			 dataType:'json',
			 async: false,
			 data:{consigneeconsignoraddressid:consigneeConsignorAddressId}, //参数     	               
			 success:function(data){//回传函数
				$("#consigneelinkman").val(data.linkman);//收货联系人
				$("#consigneetelephonenumber").val(data.telephonenumber);//收货联系电话
				$("#consigneemobilenumber").val(data.mobilenumber);//手机
				if(data.region == null || data.region == "") {
					 $("#consigneeprovince").val(data.province+"-"+data.city);//收货地
					 consigneeprovince = data.province+"-"+data.city;
				}else{
					 $("#consigneeprovince").val(data.province+"-"+data.city+"-"+data.region);//收货地
					 consigneeprovince = data.province+"-"+data.city+"-"+data.region;
				} 
				$("#consigneetown").val(data.town);//详细地址
			}
		});
		consignor_cityDistance(consignorprovince,consigneeprovince);//得到城区间距离
}


/******城区间距离********/
function consignor_cityDistance(consignorprovince,consigneeprovince){
	var frompartyid = $("#frompartyid").attr("myid");
	var consignorprovinces = '';//开始地
	var consigneeprovinces = '';//目的地
	if(consignorprovince != '') {
		consignorprovinces = consignorprovince;
	}else {
		consignorprovinces = $("#consignorprovince").val();
	}
	if(consigneeprovince != '') {
		consigneeprovinces = consigneeprovince;
	}else {
		consigneeprovinces = $("#consigneeprovince").val();//目的地
	}
	if(frompartyid == '' || consignorprovinces == '' || consigneeprovinces =='') {
		
	}else {
		//发货方联系人，联系电话，手机，地址，详细地址
		$.ajax({
			url: "../waybillcs/selectCityDistanceId",   
			type:'post',	
			dataType:'json', 
			data:{frompartyid:frompartyid,fromaddress:consignorprovinces,toaddress:consigneeprovinces}, //参数     	               
				 success:function(data){//回传函数
				 data = eval(data);
				 if(data == null || data == '') {
					 $("#distance").val('');
				 }else {
					 $("#distance").val(data[0].distance);
				 }
			}
		});
	}
}

/**********根据物品id查询详细信息*************/
function consignor_goods(obj,vlaves){
		var goodsTypeId = $(obj).attr("myid");
		var goodsName = $(obj).val();
		if(goodsName == '') {//goodsTypeId == undefined ||
			$(obj).css("border","1px solid  red");
			markText("#goodsname"+vlaves,"不能为空");
		}else {
			$.ajax({
				url: "../waybillcs/selectGoodId",   
				 type:'post',	
				 dataType:'json', 
				 data:{goodstypeid:goodsTypeId}, //参数     	               
				 success:function(data){//回传函数
					 $("#unitWeight"+vlaves).val(data.unitweight);//货物单位重量
					 $("#unitVolume"+vlaves).val(data.unitvolume);//货物单位体积
					 $("#goodstype"+vlaves).val(data.goodsType);//货物类型
					 $("#spec"+vlaves).val(data.spec);//货物规格
					 $("#model"+vlaves).val(data.model);//型号
					 $("#packagename"+vlaves).val(data.package);//货物包装
					 $("#weightType"+vlaves).val(data.unit);//货物重量单位 1表示公斤 2表示吨
					 $("#measuretype"+vlaves).val(data.chargetype);//货物计费方式
				}
			});
		}
}


/**********根据物品id查询详细信息*************/
function consignorgoods(myid,vlaves){
		var goodsTypeId = myid;
		$.ajax({
			url: "../waybillcs/selectGoodId",   
			type:'post',	
			dataType:'json', 
			data:{goodstypeid:goodsTypeId}, //参数     	               
			success:function(data){//回传函数
				$("#unitWeight"+vlaves).val(data.unitweight);//货物单位重量
				$("#unitVolume"+vlaves).val(data.unitvolume);//货物单位体积
				$("#goodstype"+vlaves).val(data.goodsType);//货物类型
				$("#spec"+vlaves).val(data.spec);//货物规格
				$("#model"+vlaves).val(data.model);//型号
				$("#packagename"+vlaves).val(data.package);//货物包装
				$("#weightType"+vlaves).val(data.unit);//货物重量单位 1表示公斤 2表示吨
				$("#measuretype"+vlaves).val(data.chargetype);//货物计费方式
			}
		});
}

/*******计算（数量*单位重量=总重量，数量*单位体积=总体积）如果手动填写物品，计算总重量和总体积都为0*******/
function num_goods(obj,valaus) {
	var nums = $(obj).val();
	if(trim(nums) == '') {
		markText("#num"+valaus,"不能为空");
		return false;
	}
	var unitWeight = $("#unitWeight"+valaus).val();//
	var unitVolume = $("#unitVolume"+valaus).val();
	var num = $("#num"+valaus).val();
	if(unitWeight != null && unitWeight != '') {
		$("#weight"+valaus).val((parseFloat(unitWeight)*parseFloat(num)).toFixed(3));
	}
	if(unitVolume != null && unitVolume != '') {
		$("#volume"+valaus).val((parseFloat(unitVolume)*parseFloat(num)).toFixed(3));
	}
	num_count();
}

/************自动计算数量、重量、体积、声明价值的总和****************/
function num_count(){
	var numZ = 0;
	var weightZ = 0;
	var volumeZ = 0;
	
	var $num=$("input[name='num']");//数量
	var $weight=$("input[name='weight']");//重量
	var $weightType=$("select[name='weightType']");//重量单位
	var $volume=$("input[name='volume']");//体积
	$.each($("input[name='goodsid']"),function(n,item){
	        var num=$($num[n]).val(); //数量
	        var weight=$($weight[n]).val(); //重量
	        var weightType=$($weightType[n]).val();//重量单位
	        var volume=$($volume[n]).val(); //体积
	        if(weightType == '2') {
	        	weight = parseFloat(weight)*1000;
	        	var temp = weight+"";
	        	var temps = temp.split('.');
	        	if(temps.length>1){
	        		if(temps[1].length>3){
	        			weight = weight.toFixed(3);
	        		}
	        	}
	        }
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
	$("#numZ").html((numZ));
	$("#weightZ").html((weightZ)+" 公斤");
	$("#volumeZ").html((volumeZ)+"立方");
}



/****************此方法用来把多条物品封装成json****************/
function createArray() {
	var jsonarray=[];
    var $goodsid=$("input[name='goodsid']");
    var $goodsname=$("input[name='goodsname']");
    var $goodstype=$("select[name='goodstype']");
    var $spec=$("input[name='spec']");
    var $model=$("input[name='model']");
    var $num=$("input[name='num']");
    var $packagename=$("select[name='packagename']");
    var $weight=$("input[name='weight']");
    var $weightType=$("select[name='weightType']");
    var $volume=$("input[name='volume']");
    var $measuretype=$("select[name='measuretype']");
    var $factnum = $("input[name='num']");
    var $factweight = $("input[name='weight']");
    var $factvolume = $("input[name='volume']");
    var $unitweight = $("input[name='unitWeight']");
    var $unitvolume = $("input[name='unitVolume']");
    
    $.each($("input[name='goodsid']"),function(n,item){
        var goodsid= $($goodsid[n]).val();
        var goodsname=$($goodsname[n]).val();
        var waybillid = $("#waybillid").val();
        var goodstype=$($goodstype[n]).val(); 
        var spec=$($spec[n]).val(); 
        var model=$($model[n]).val(); 
        var num=$($num[n]).val(); 
        var packagename=$($packagename[n]).val(); 
        var weight=$($weight[n]).val(); //重量
        var weightType=$($weightType[n]).val(); 
        var volume=$($volume[n]).val(); 
        var measuretype=$($measuretype[n]).val();
        var factnum = $($num[n]).val();
        var factweight = $($weight[n]).val();
        var factvolume = $($volume[n]).val();
        var unitweight = $($unitweight[n]).val();//单位重量
        var unitvolume = $($unitvolume[n]).val();
        if(weightType == '2') {
        	unitweight = (parseFloat(unitweight)*1000);
        	weight = (parseFloat(weight)*1000);
        	factweight = weight;
        }
        var obj=createObj(goodsid,goodsname,waybillid,goodstype,spec,model,num,factnum,packagename,weight,factweight,weightType,volume,factvolume,measuretype,unitweight,unitvolume);
        jsonarray.push(obj);
    })
    return jsonarray;
}
function createObj(goodsid,goodsname,waybillid,goodstype,spec,model,num,factnum,packagename,weight,factweight,weightType,volume,factvolume,measuretype,unitweight,unitvolume){
	return {
		goodsid:goodsid,
		goodsname:goodsname,
		waybillid:waybillid,
		goodstype:goodstype,
		spec:spec,
		model:model,
		num:num,
		factnum:factnum,
		packagename:packagename,
		weight:weight,
		factweight:factweight,
		weightType:weightType,
		volume:volume,
		factvolume:factvolume,
		measuretype:measuretype,
		unitweight:unitweight,
		unitvolume:unitvolume
    }
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
    })
    if(j>1) {
    	if(i != j) {
        	ymPrompt.alert("\"按整车计费\"不允许和其他计费方式同时存在");
        	return false;
        }
    }
}

/****************修改运单*******************/
function save(valuse) {
	var vals = valuse;
	 if(f_check_save()== false) {//保存校验
		 return;
	 }
	 if(measuretype() == false) {//校验物品计费方式（按整车计费计费都要统一）
		 return;
	 }
	 if($("#status").val()!="待分派"&&$("#topartyid").val()==""){
		 ymPrompt.alert("运单已分派，分包商不可为空！");
		 return;
	 }
	 if($("#status").val()=="待分派"&&$("#topartyid").val()!=""){
		 ymPrompt.alert("请到运单分派模块分派分包商！");
		 return;
	 }
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
	 topartyName = trim($("#topartyid").val());
	 topartyid = trim($("#topartyid").attr("myid"));
	 if(topartyName == null || topartyName == "") {
		 topartyid = "";
	 }
	 $("#buttonSave").attr("style","display:none;");
	 ymPrompt.confirmInfo({message:vals,autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
			function(status){
			if(status=='ok'){
				//遮罩显示
				showOverlay();
				 $.ajax({
						url: "../waybillcs/updateWaybill",
						type:'post',	
						dataType:'json', 
						data:{jsonText:jsonText,
						 waybillid:trim($("#waybillid").val()),
						 frompartyid:trim($("#frompartyid").attr("myid")),
						 partyid:trim($("#partyid").val()),
						 waybillnumber:trim($("#waybillnumber").val()),
						 status:trim($("#status").val()),
						 consigndate:trim($("#consigndate").val()),
						 consignorlinkman:trim($("#consignorlinkman").val()),
						 consignortelephonenumber:trim($("#consignortelephonenumber").val()),
						 consignormobilenumber:trim($("#consignormobilenumber").val()),
						 consignorprovince:trim(consignorprovince),
						 consignorcity:trim(consignorcity),
						 consignorregion:trim(consignorregion),//县
						 consignortown:trim($("#consignortown").val()),
						 consignee:trim($("#consignee").val()),
						 consigneelinkman:trim($("#consigneelinkman").val()),
						 consigneetelephonenumber:trim($("#consigneetelephonenumber").val()),
						 consigneemobilenumber:trim($("#consigneemobilenumber").val()),
						 consigneeprovince:trim(consigneeprovince),
						 consigneecity:trim(consigneecity),
						 consigneeregion:trim(consigneeregion),//显
						 consigneetown:trim($("#consigneetown").val()),
						 distance:trim($("#distance").val()),
						 settletype:trim($("#settletype").val()),
						 receivetype:trim($("#receivetype").val()),
						 arrivetype:trim($("#arrivetype").val()),
						 backbilltype:trim($("#backbilltype").val()),
						 backbillnum:trim($("#backbillnum").val()),
						 urgencydegree:trim($("#urgencydegree").val()),
						 shiptype:trim($("#shiptype").val()),
						 topartyid:topartyid,
						 clientnumber:trim($("#clientnumber").val()),
						 memo:trim($("#memo").val()),
						 inputman:trim($("#inputman").val()),
						 inputdate:trim($("#inputdate").val()),
						 operatorid:trim($("#operatorid").val())
					 	}, //参数     	               
						success:function(data){//回传函数
					 		if(data.msg == 'update') {
					 			//遮罩影藏
					 			hideOverlay();
					 			ymPrompt.alert({message:'运单修改成功！',handler:successFun});
					 		}
						}
					});
			}else{
				$("#buttonSave").attr("style","margin-left: 30px;");
				return false;
			}
		}
	});
}

function successFun(){
	//window.location.href="../waybillcs/waybillupdate_list?order=23";
	opener.loadQuery();
	window.close(); 
}

/***********返回接单管理列表页***************/
function f_fanhui(){
	//window.location.href="../waybillcs/waybillupdate_list?order=23";
	opener.loadQuery();
	window.close();
}

/*****校验发货方是否存在*****/
function f_check_frompartyid() {
	var organizationName = $("#frompartyid").val();
	if(organizationName == '') {
		$("#frompartyid").css("border","1px solid red");
		billNumberFlag = markText("#frompartyid","发货方不能为空！");
		return false;
	}else {
		$.ajax({
			url: "../waybillcs/checkFrompartyName",
		 	type:'post',	
		 	dataType:'json',
		 	async: false,
		 	data:{organizationName:organizationName}, //参数     	               
		   	success:function(data){
		 		if(data.organization == 'sorry' && data.partyid == 'sorry') {//是会员，但不是总包下的发货方
		 			$("#frompartyid").css("border","1px solid red");
		 			$("#frompartyid").attr("myid","");
		 			ymPrompt.alert("发货方不存在！");
		 			return false;
		 		}else if(data.organization == 'NO' && data.partyid == 'NO') {//不是会员
		 			$("#frompartyid").css("border","1px solid red");
		 			$("#frompartyid").attr("myid","");
		 			ymPrompt.alert("发货方不存在！");
		 			return false;
		 		}else {
		 			input_onfocus(obj);
		 			$("#frompartyid").val(data.organization);
		 			$("#frompartyid").attr("myid",data.partyid);
		 			consignor_onblur(data.partyid);
		 		}
		 	}
		});
	}
}


/*****校验发货方是否存在*****/
function f_check_frompartyNAME() {
	var organizationName = $("#frompartyid").val();
	if(organizationName == '') {
		$("#frompartyid").css("border","1px solid red");
		billNumberFlag = markText("#frompartyid","发货方不能为空！");
		return false;
	}else {
		$.ajax({
			url: "../waybillcs/checkFrompartyName",
		 	type:'post',	
		 	dataType:'json',
		 	async: false,
		 	data:{organizationName:organizationName}, //参数     	               
		   	success:function(data){
		 		if(data.organization == 'sorry' && data.partyid == 'sorry') {//是会员，但不是总包下的发货方
		 		}else if(data.organization == 'NO' && data.partyid == 'NO') {//不是会员
		 		}else {
		 			input_onfocus(obj);
		 			$("#frompartyid").val(data.organization);
		 			$("#frompartyid").attr("myid",data.partyid);
		 			consignor_onblur(data.partyid);
		 		}
		 	}
		});
	}
}



/*****校验分包商是否存在*****/
function f_check_topartyid() {
	var frompartyid = $("#frompartyid").attr("myid");//发货方frompartyid
	var topartyid = $("#topartyid").val();//分包商
	if(topartyid == '') {
	}else {
		$.ajax({
			url: "../waybillcs/checkTopartyName",
		 	type:'post',	
		 	dataType:'json', 
		 	data:{organizationName:topartyid,frompartyid:frompartyid}, //参数     	               
		   	success:function(data){
		 		if(data.organization == 'sorry' && data.partyid == 'sorry') {//是会员，但不是总包下的分包商
		 			$("#topartyid").css("border","1px solid red");
		 			$("#topartyid").attr("myid","");
		 			ymPrompt.alert("此分包商不是发货方关联分包商！");
		 			return false;
		 		}else if(data.organization == 'NO' && data.partyid == 'NO') {//分包商不存在
		 			$("#topartyid").css("border","1px solid red");
		 			$("#topartyid").attr("myid","");
		 			ymPrompt.alert("分包商不存在！");
		 			return false;
		 		}else {
		 			$("#topartyid").val(data.organization);
		 			$("#topartyid").attr("myid",data.partyid);
		 		}
		 	}
		});
	}
}

/*****************保存做校验*********************/
function f_check_save() {
	var organizationName = $("#frompartyid").val();//发货方
	if(trim(organizationName) == '') {
		$("#frompartyid").css("border","1px solid red");
		billNumberFlag = markText("#frompartyid","发货方不能为空！");
		return false;
	}else {
		$.ajax({
			url: "../waybillcs/checkFrompartyName",
		 	type:'post',	
		 	dataType:'json', 
		 	data:{organizationName:trim($("#frompartyid").val())}, //参数     	               
		   	success:function(data){
		 		if(data.organization == 'sorry' && data.partyid == 'sorry') {//是会员，但不是总包下的发货方
		 			$("#frompartyid").css("border","1px solid red");
		 			$("#frompartyid").attr("myid","");
		 			$("#frompartyid").focus();
		 			ymPrompt.alert("发货方不存在！");
		 			return false;
		 		}else if(data.organization == 'NO' && data.partyid == 'NO') {//不是会员
		 			$("#frompartyid").css("border","1px solid red");
		 			$("#frompartyid").attr("myid","");
		 			$("#frompartyid").focus();
		 			ymPrompt.alert("发货方不存在！");
		 			return false;
		 		}
		 	}
		});
	}
	var consignorlinkman = $("#consignorlinkman").val();//发联系人
	if(trim(consignorlinkman) == '') {
		$("#consignorlinkman").css("border","1px solid red");
		consignorlinkmanFlag = markText("#consignorlinkman","发联系人不能为空！");
		return false;
	}
	var consignortelephonenumber = $("#consignortelephonenumber").val();//发联系电话
	var consignormobilenumber = $("#consignormobilenumber").val();//发手机
	if(trim(consignortelephonenumber) != '') {
		if(phonenumber(trim(consignortelephonenumber),'发货方') == false) { //校验电话
			return false;
		}
		if(bilenumber(trim(consignormobilenumber),'发货方') == false) {//校验手机
			return false;
		}
	}else {
		if(trim(consignormobilenumber) != '') {
			if(bilenumber(trim(consignormobilenumber),'发货方') == false) {//校验手机
				return false;
			}
		}else {
			$("#consignortelephonenumber").css("border","1px solid red");
			$("#consignormobilenumber").css("border","1px solid red");
			ymPrompt.alert("发货方联系电话和手机必须填写一个！");
			return false;
		}
	}
	
	var consignorprovince = $("#consignorprovince").val();//发地址
	if(consignorprovince == '请选择/输入城市名称' || trim(consignorprovince) == '') {
		$("#consignorprovince").css("border","1px solid red");
		ymPrompt.alert("发货地不能为空！");
		return false;
	}
	var consignee = $("#consignee").val();//收货方
	if(trim(consignee) == '') {
		$("#consignee").css("border","1px solid red");
		consigneeFlag = markText("#consignee","收货方不能为空！");
		return false;
	}
	var consigneelinkman = $("#consigneelinkman").val();//收联系人
	if(trim(consigneelinkman) == '') {
		$("#consigneelinkman").css("border","1px solid red");
		$("#consigneelinkman").focus();
		return false;
	}
	var consigneetelephonenumber = $("#consigneetelephonenumber").val();//收联系电话
	var consigneemobilenumber = $("#consigneemobilenumber").val();//收手机
	if(trim(consigneetelephonenumber) != '') {
		if(phonenumber(trim(consigneetelephonenumber),'收货方') == false) {//校验电话
			return false;
		}
		if(bilenumber(trim(consigneemobilenumber),'收货方') == false) {//校验手机
			return false;
		}
	}else {
		if(trim(consigneemobilenumber) != '') {
			if(bilenumber(trim(consigneemobilenumber),'收货方') == false) {
				return false;
			}
		}else {
			$("#consigneetelephonenumber").css("border","1px solid red");
			$("#consigneemobilenumber").css("border","1px solid red");
			ymPrompt.alert("收货方联系电话和手机必须填写一个！");
			return false;
		}
	}
	var consigneeprovince = $("#consigneeprovince").val();//收地址
	if(consigneeprovince == '请选择/输入城市名称' || trim(consigneeprovince) == '') {
		$("#consigneeprovince").css("border","1px solid red");
		ymPrompt.alert("收货地不能为空！");
		return false;
	}
	var consigneetown = $("#consigneetown").val();//收详细地址
	if(trim(consigneetown) == '') {
		$("#consigneetown").css("border","1px solid red");
		$("#consigneetown").focus();
		return false;
	}	
	
	var t = 1;
	var $goodsname=$("input[name='goodsname']");//物品名称
	var $num=$("input[name='num']");//物品数量
	$.each($("input[name='goodsid']"),function(n,item){
	    var goodsname=$($goodsname[n]).val();
	    var num=$($num[n]).val(); 
	    if(n == 0) {
	    	if(goodsname == '') {
	    		$("#goodsname").css("border","1px solid red");
	    		markText("#goodsname","不能为空");
	    		t++;
	    	}
	    	if(num == '') {
	    		$("#num").css("border","1px solid red");
	    		markText("#num","不能为空");
	    		t++;
	    	}
	    }else {
	    	if(goodsname == '') {
	    		$("#goodsname"+(n+1)).css("border","1px solid red");
	    		markText("#goodsname"+(n+1),"不能为空");
	    		t++;
	    	}
	    	if(num == '') {
	    		$("#num"+(n+1)).css("border","1px solid red");
	    		markText("#num"+(n+1),"不能为空");
	    		t++;
	    	}
	    }
	}); 
	if(t>1) {
		return false;
	}
	if(f_check_topartyid() == false) {//校验分包商
		return false;
	}
	return true;
}

/**********手机校验valeues是值***********/
function bilenumber(valeues,strval) {
    var bilenumber = valeues;
	l = bilenumber.length;   //验证长度
	if(trim(bilenumber) != '') {
		if(l != 11) {
			ymPrompt.alert(strval+"手机号码长度为11位,请修正后在提交！");
			return false;
		} 
		var reg = /^0{0,1}(13[0-9]|145|147|15[0-3]|15[5-9]|18[0-9])[0-9]{8}$|^$/;
		var result = reg.exec(bilenumber);
		if(result == null){
			ymPrompt.alert(strval+"手机号不合法,请修正后在提交！");
			return false;
		}
	}
	return true;
}

/**********电话校验valeues是值***********/
function phonenumber(valeues,strval){
	var bilenumber = valeues;
	l = bilenumber.length;   //验证长度
	if(trim(bilenumber) != '') {
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(trim(bilenumber))){
			ymPrompt.alert(strval+"电话号码必须是数字，请修正后重新填写!");
		    return false;
		}
	}
	return true;
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

/************校验数字必须是正整数，strvalue是值，strtype是什么字段************/
function strInteger(strvalue,strtype){
	var strvalue = strvalue;
	if(trim(strvalue) != '') {
		var reg = new RegExp("^[1-9]*[1-9][0-9]*$");
		if(!reg.test(trim(strvalue))){
			ymPrompt.alert(strtype+"必须是正整数，请修正后重新填写!");
	        return false;
	    }
	}else {
		ymPrompt.alert(strtype+"不能为空，请输入物品数量!");
		return false;
	}
}

/**
 * @author wei.huang
 * @date 2013-11-21
 * @function 提示信息展示
 * @param targetId 目标文本框的Id(带#)
 * @param message 提示信息
 * @return 展示提示信息的div的id(带#)
 */
function markText(targetId,message){
	var _targetId=targetId.substr(1);//去掉#
	if($(targetId+"_message").length==0){//当信息提示div不存在时
		var top=$(targetId).position().top+parseInt($(targetId).css("height"));
		var left=$(targetId).position().left;
		var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("body").append(txt);
	}else{//当信息提示div存在时
		$(targetId+"_message").empty().append(message);
	}
	$(targetId).css("border","2px solid red");
	return targetId+"_message";
}

///************校验数字必须是正整数，strvalue是值，strtype是什么字段************/
function myNumberic(e) {
	var num = ($(e).val());
	if(isNaN(num)){
		$(e).val("");
	}
	var str = num.split(".");
	if(str.length>1){
		var numer = '';
		var strs = str[0];
		if(strs.length>10) {
			numer = strs.substr(0,10);
		}
		if(str[1]!=null&&str[1].length>3){
			var newNum = str[0]+'.'+str[1][0]+str[1][1]+str[1][2];
			$(e).val(newNum);
		}
	}else {
		if(num.length>9) {
			$(e).val(num.substr(0,9));
		}
	}
}

function myNumberictow(e) {
	var num = ($(e).val());
	if(isNaN(num)){
		$(e).val("");
	}
	var str = num.split(".");
	if(str.length>1){
		var numer = '';
		var strs = str[0];
		if(strs.length>10) {
			numer = strs.substr(0,10);
		}
		if(str[1]!=null&&str[1].length>2){
			var newNum = str[0]+'.'+str[1][0]+str[1][1];
			$(e).val(newNum);
		}
	}else{
		if(num.length>10) {
			$(e).val(num.substr(0,10));
		}
	}
}
/**
 * 分包商下拉（判断加入结算单则不得修改）
 * hcm
 * @param e
 * @param partytype
 * @return
 */
function onFocu_toparty(e,partytype){
	//alert("ss");
		if($("#outsettlebillid").val()!=""){
			//alert($("#outsettlebillid").val());
			ymPrompt.alert("已加入应付结算单，不可修改分包商！");
		}
		input_onfocus(e);//waybill_add.js,修改边框颜色的
		var frompartyid = $("#frompartyid").attr("myid");
		var id=$(e).attr("id");
		var id1='#'+id;
        //var wordInput = $("#topartyrealname");  
    	var wordInput=$(id1);
        var wordInputOffset = wordInput.offset();  
          
        //给div设置显示时的背景色  
        $("#auto").css("background-color","white");  
          
        //自动补全框最开始应该隐藏起来  
        $("#auto").hide().css("border","1px #999999 solid").css("position","absolute")  
                .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")  
                .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);  
      
        //给文本框添加键盘按下并弹起的事件  
        wordInput.keyup  
        (  
        function(event)   
        {  
            //处理文本框中的键盘事件  
            var myEvent = event || window.event;  
            var keyCode = myEvent.keyCode;  
            //如果输入的是字母，应该将文本框中最新的信息发送给服务器  
            //如果输入的是退格键或删除键，也应该将文本框中的最新信息发送给服务器  
            //空格键为：32  
            if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46 || keyCode==32)   
            {  
                //1.首先获取文本框中的内容  
                var wordText = wordInput.val();
                var autoNode = $("#auto");  
                if (wordText != "")   
                {  
                    //清空上一次未开始执行的请求  
                    clearTimeout(timeoutId);  
                    //延迟 500毫秒 处理  
                    timeoutId = setTimeout(function()  
                    {  
                        //2.将文本框中的内容发送给服务器段  
                        $.ajax(  
                            {  
                                type: "POST",  
                                url: "../waybillcs/topartyNameList",  
                                data: "organization="+wordText+"&partytype=linked"+"&frompartyid="+frompartyid,  
                                dataType:"json",  
                                success:function(data)  
                                {
    //                              alert(result);  
                                    //进行遍历,并且让div显示  
                                    var s="";  
                                    //需要清空原来的内容  
                                    autoNode.html("");
                                    if(data.Data!=null || data.length>0)  
                                    {  
                                        $("#auto").show("slow");  
                                        $.each(data,function(i,item){  
                                            var newDivNode=$("<div>").attr({"id":i,"myid":data[i].partyid});
                                            var city = data[i].organization;  
    //                                      alert(city.cname);  
                                            newDivNode.html(city).appendTo(autoNode);  
                                            //增加鼠标进入事件，高亮节点  
                                            newDivNode.mouseover  
                                            (  
                                            function(){  
                                                //将原来高亮的节点取消高亮  
                                                if(highlightindex != -1){  
                                                    $("#auto").children("div").eq(highlightindex)  
                                                    .css("background-color","white");  
                                                }  
                                                //纪录新的高亮索引  
                                                highlightindex = $(this).attr("id");  
                                                //鼠标进入的高亮节点  
                                                $(this).css("background-color","#6699CC");  
                                            });  
                                            //鼠标移出节点，取消高亮  
                                            newDivNode.mouseout(  
                                                function(){  
                                                    $(this).css("background-color","white");//取消鼠标移出节点的高亮  
                                                }  
                                            );  
                                            //增加鼠标点击事件，可以进行点击  
                                            newDivNode.click(  
                                            function (){  
                                                var comText = $(this).text();//取出高亮节点的文本内容  
                                                $("#auto").hide("slow");  
                                                document.getElementById('auto').style.display='none';  
                                                highlightindex = -1;wordInput.val(comText);//文本框中的内容变成高亮节点的内容
                        	                    var concomTextTag=$(this).eq(highlightindex).attr("myid");
                        	                    highlightindex = -1;  
                        	                    //文本框中的内容变成高亮节点的内容  
                        	                    wordInput.attr("myid",concomTextTag);
                        	                    wordInput.next().val(concomTextTag)
                                            }  
                                            );  
                                              
                                        });  
                                    }  
                                    //如果服务器段有数据返回，则显示弹出框-------  
                                    if (data!=null || data.length>0)   
                                    {  
                                        autoNode.show("slow");  
                                    } else   
                                    {  
                                        autoNode.hide("slow");  
                                        //弹出框隐藏的同时，高亮节点索引值也制成-1  
                                        highlightindex = -1;  
                                    }  
                                }  
                            }  
                        );  
                    },timeMS);  
                } else {  
                    //autoNode.hide();  
                    //highlightindex = -1;  
                }  
            } else if (keyCode == 38 || keyCode == 40)   
            {  
                //如果输入的是向上38向下40按键  
                if (keyCode == 38)   
                {  
                    //向上  
                    var autoNodes = $("#auto").children("div")  
                    if (highlightindex != -1)   
                    {  
                        //如果原来存在高亮节点，则将背景色改称白色  
                        autoNodes.eq(highlightindex).css("background-color","white");  
                        highlightindex--;  
                    } else   
                    {  
                        highlightindex = autoNodes.length - 1;      
                    }  
                    if (highlightindex == -1)   
                    {  
                        //如果修改索引值以后index变成-1，则将索引值指向最后一个元素  
                        highlightindex = autoNodes.length - 1;  
                    }  
                    //让现在高亮的内容变成红色  
                    autoNodes.eq(highlightindex).css("background-color","#6699CC");  
                }  
                if (keyCode == 40)   
                {  
                    //向下  
                    var autoNodes = $("#auto").children("div")  
                    if (highlightindex != -1)   
                    {  
                        //如果原来存在高亮节点，则将背景色改称白色  
                        autoNodes.eq(highlightindex).css("background-color","white");  
                    }  
                    highlightindex++;  
                    if (highlightindex == autoNodes.length)   
                    {  
                        //如果修改索引值以后index变成-1，则将索引值指向最后一个元素  
                        highlightindex = 0;  
                    }  
                    //让现在高亮的内容变成红色  
                    autoNodes.eq(highlightindex).css("background-color","#6699CC");  
                }  
            } else if (keyCode == 13)   
            {  
                //如果输入的是回车  
      
                //下拉框有高亮内容  
                if (highlightindex != -1)   
                {  
                    //取出高亮节点的文本内容  
                    var comText = $("#auto").hide("slow").children("div").eq(highlightindex).text();  
                    var concomTextTag=$("#auto").hide("slow").children("div").eq(highlightindex).attr("myid");
                    highlightindex = -1;  
                    //文本框中的内容变成高亮节点的内容  
                    $(id1).val(comText);  
                    $(id1).attr("myid",concomTextTag);
                } else   
                {  
                    //下拉框没有高亮内容  
                    //alert("文本框中的[" + $("#word").val() + "]被提交了");  
                    $("#auto").hide("slow");  
                      
                    $("#auto").get(0).blur();  
                }  
            }  
        });  
      
        //给按钮添加事件，表示文本框中的数据被提交  
        $("input[type='button']").click  
        (  
        function()   
        {  
            //alert("文本框中的[" + $("#word").val() + "]被提交了");  
        }  
        ); 
	}
//表示当前高亮的节点  
var highlightindex = -1;  
//延迟请求对应timeout的id  
var timeoutId;  
//延迟请求的间隔时间  
var timeMS = 20;  
