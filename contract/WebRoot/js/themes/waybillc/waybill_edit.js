var param;

$(function(){
	getid();
	select_waybill();
});

function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	param=idUrl
}

function tabend_Add(){
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
//	 var td9=$("<td></td>");// create 6th td
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
//	 var input09=$("<input/>").attr({"id":"claimvalue"+count,"class":"com_input","type":"text","name":"claimvalue","defaultvalue":"1","style":"width:85px;","onblur":"num_count()"});
//	 $(input09).appendTo(td9);
//	 $(td9).appendTo(hgrid_tr);
	 var input9=$("<select></select>").attr({"id":"measuretype"+count,"type":"text","name":"measuretype","defaultvalue":"1","style":"width:65px;height:25px;padding-top:4px;"});
	 $(input9).appendTo(td9);
	 $(td9).appendTo(hgrid_tr);
	 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this,'"+count+"')"}).css("margin-left","20px");
	 $(a07).html("删除");
	 $(a07).appendTo(td10);
	 $(td10).appendTo(hgrid_tr);
	 $("#hgrid tr:last").after(hgrid_tr);
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
	 					tabend_Add();
	 					f_contractc('货物类型','goodstype',(i+1),datae[i].goodstype);
	 					f_contractc('包装单位','packagename',(i+1),datae[i].packagename);
	 					f_contractc('计费方式','measuretype',(i+1),datae[i].measuretype);
	 					f_contractc('重量单位','weightType',(i+1),'');
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
