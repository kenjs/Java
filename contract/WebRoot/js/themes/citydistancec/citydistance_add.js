var flag=false;
var flag_copy=false;
var clickcount=0;
function f_onmouseover(obj){
	$(obj).css("background-color","#fffddd");
}
function f_onmouseout(obj){
	$(obj).css("background-color","white");
}
function tabendAdd(){
	var count2=$("#hgrid tr").length;
	if(count2<8){
		$("div.hgrid").css({"height":"auto","overflow-y":"hidden"});
	}else{
		$("div.hgrid").css({"height":"300px","overflow-y":"auto"})
	}
	clickcount++;
	$("#auto").css("display","none");
	if(clickcount<=1){
		var organization=$.trim($("input[name=organization]").val());
		var myid="";
			if($("input[name=organization]").attr("myid")==undefined){
				myid="";
			}else{
				myid=$("input[name=organization]").attr("myid");
			}
		if(organization==""&&myid==""){
			ymPrompt.alert({"title":"提示","message":"请填写发货方名称"});
			clickcount=0;
			return false;
		}
		else{
		$.ajax({
			url: "../citydistancecs/selectConsignor",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{organization:organization}, //参数     	               
		   	success:function(data){//回传函数
			if(data.msg=="1"){
				$("#frompartyid").val(data.id);
				 $("input[name=organization]").attr("myid",data.id);
				 $.ajax({
						url: "../citydistancecs/checkConsignorExist",   
					 	type:'post',	
					 	dataType:'json', 
					 	data:{"frompartyid": $("input[name=organization]").attr("myid")}, //参数     	               
					   	success:function(data){//回传函数
					 		if(data.msg!="0"){
					 			ymPrompt.alert({"title":"提示","message":"该发货方已经设置城区间距离"});clickcount=0;
					 			return false;
					 		}else{
					 			var count=$("#hgrid tr").length;
								 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
								 var td0=$("<td></td>").css("padding-left","10px");// create 1st td
								 var td1=$("<td></td>");//create 2nd td
								 var td2=$("<td></td>");// create 3rd td
								 var td3=$("<td></td>");// create 4th td
								 var td4=$("<td></td>");// create 5th td
								 var td5=$("<td></td>");// create 6th td
								 var input00=$("<input/>").attr({"type":"hidden","name":"citydistanceid","class":"citydistanceid","value":"","id":"citydistanceid"+count});
								 $(td0).text(count);
								 $(input00).appendTo(td1);
								 $(td0).appendTo(hgrid_tr);
								 var input01=$("<input/>").attr({"id":"fromaddress"+count,"type":"text","name":"fromaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
								 $(input01).appendTo(td1);
								 $(td1).appendTo(hgrid_tr);
								 var input02=$("<input/>").attr({"id":"toaddress"+count,"type":"text","name":"toaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
								 $(input02).appendTo(td2);
								 $(td2).appendTo(hgrid_tr);
								 var input03=$("<input/>").attr({"id":"distance"+count,"type":"text","name":"distance", "class":"distance","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
								 $(input03).appendTo(td3);
								 $(td3).appendTo(hgrid_tr);
								 var input04=$("<input/>").attr({"id":"subcontractor"+count,"type":"text","name":"subcontractor", "class":"subcontractor","onfocus":"onFocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
								 var input05=$("<input/>").attr({"id":"subcontractorid"+count,"type":"hidden","name":"subcontractorid", "class":"subcontractorid"});
								 $(input04).appendTo(td4);
								 $(input05).appendTo(td4);
								 //$(td4).appendTo(input05);
								 $(td4).appendTo(hgrid_tr);
								 var a06=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
								 $(a06).html("复制新增");
								 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","20px");
								 $(a07).html("删除");
								 $(a06).appendTo(td5);
								 $(a07).appendTo(td5);
								 $(td5).appendTo(hgrid_tr);
								 $("#hgrid tr:last").after(hgrid_tr);	
					 		}
					}
					});
				 
			}
			if(data.msg=="0"){
				ymPrompt.alert({"title":"提示","message":"无此发货方"});
				clickcount=0;
				return false;
			}
		}
		});
		}
	}else{
		var count=$("#hgrid tr").length;
		 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
		 var td0=$("<td></td>").css("padding-left","10px");// create 1st td
		 var td1=$("<td></td>");//create 2nd td
		 var td2=$("<td></td>");// create 3rd td
		 var td3=$("<td></td>");// create 4th td
		 var td4=$("<td></td>");// create 5th td
		 var td5=$("<td></td>");// create 6th td
		 var input00=$("<input/>").attr({"type":"hidden","name":"citydistanceid","class":"citydistanceid","value":"","id":"citydistanceid"+count});
		 $(td0).text(count);
		 $(input00).appendTo(td1);
		 $(td0).appendTo(hgrid_tr);
		 var input01=$("<input/>").attr({"id":"fromaddress"+count,"type":"text","name":"fromaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input01).appendTo(td1);
		 $(td1).appendTo(hgrid_tr);
		 var input02=$("<input/>").attr({"id":"toaddress"+count,"type":"text","name":"toaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input02).appendTo(td2);
		 $(td2).appendTo(hgrid_tr);
		 var input03=$("<input/>").attr({"id":"distance"+count,"type":"text","name":"distance", "class":"distance","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input03).appendTo(td3);
		 $(td3).appendTo(hgrid_tr);
		 var input04=$("<input/>").attr({"id":"subcontractor"+count,"type":"text","name":"subcontractor", "class":"subcontractor","onfocus":"onFocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 var input05=$("<input/>").attr({"id":"subcontractorid"+count,"type":"hidden","name":"subcontractorid", "class":"subcontractorid"});
		 $(input04).appendTo(td4);
		 $(input05).appendTo(td4);
		 //$(td4).appendTo(input05);
		 $(td4).appendTo(hgrid_tr);
		 var a06=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
		 $(a06).html("复制新增");
		 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","20px");
		 $(a07).html("删除");
		 $(a06).appendTo(td5);
		 $(a07).appendTo(td5);
		 $(td5).appendTo(hgrid_tr);
		 $("#hgrid tr:last").after(hgrid_tr);	
	}
}
function f_copy_click(obj){
$("#auto").css("display","none");
var sub=$(obj).parent().parent().children("td").children('input[name=subcontractor]');
var count2=$("#hgrid tr").length;
if(count2<8){
	$("div.hgrid").css({"height":"auto","overflow-y":"hidden"});
}else{
	$("div.hgrid").css({"height":"300px","overflow-y":"auto"})
}
var fuck=sub_blur(sub);
if(fuck==false){
	return false;
};
var tr=$(obj).parent().parent();
var count =$(obj).parent().parent().index();
var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
var topartyid;
var fromaddress,toaddress,distance,subcontractor,citydistanceid;
var defaultfromaddress,defaulttoaddress,defaultdistance,defaultsubcontractor;
citydistanceid=$(obj).parent().parent().children("td").children('input[name=citydistanceid]').val();
//********复制新增，获取当前输入内容，并记录当前输入内容开始*********//*
$.each(inputset,function(index,item){
	switch(index){
	case 0:
		fromaddress=$(item).val();
		$(item).defaultvalue=fromaddress;//
		break;
	case 1:
		toaddress=$(item).val();
		break;
	case 2:
		distance=$(item).val();
		  var reg = /^[0-9]$|^([1-9])([0-9]){0,5}$|^100000$/;
		    if(reg.test(distance)){
		    	distance=$(item).val();
		    }else{
		    	distance="";
		    	$(item).val("");
		    }
		break;	
	case 3:
		subcontractor=$(item).val();
		topartyid=$(item).attr("myid")==undefined?"":$(item).attr("myid");
		break;			
	}
});	
//********复制新增，获取当前输入内容，并记录当前输入内容结束*********//*
if(fromaddress==""){
	//$(obj).parent().parent().children("td").children('input[name=fromaddress]').css("border","1px solid red");
	var warn="#"+$(obj).parent().parent().children("td").children('input[name=fromaddress]').attr("id");
	markText(warn,"发货地不能为空")
	return false;
}
if(toaddress==""){
	//$(obj).parent().parent().children("td").children('input[name=toaddress]').css("border","1px solid red");
	var warn="#"+$(obj).parent().parent().children("td").children('input[name=toaddress]').attr("id");
	markText(warn,"发货地不能为空")
	return false;
}
if(distance==""){
	//$(obj).parent().parent().children("td").children('input[name=distance]').css("border","1px solid red");
	var warn="#"+$(obj).parent().parent().children("td").children('input[name=distance]').attr("id");
	markText(warn,"距离不能为空")
	return false;
}
//*********检查此条记录是否存在**********//*
var params="citydistanceid="+citydistanceid+"&fromaddress="+fromaddress+"&toaddress="+toaddress+"&distance="+distance+"&subcontractor="+subcontractor+"&topartyid="+topartyid+"&frompartyid="+$("#frompartyid").val()+"&random="+Math.random();
var params1="citydistanceid="+citydistanceid+"&fromaddress="+fromaddress+"&toaddress="+toaddress+"&distance="+distance+"&subcontractor="+subcontractor+"&frompartyid="+$("#frompartyid").val()+"&random="+Math.random();
flag_copy=checkIsExist(params1);
if(flag_copy){
//********复制新增，将当前记录提交后台服务开始*********//*
$.ajax({
	url:"../citydistancecs/insert",
	data:params,
	type:'post',	
 	dataType:'json', 
   	success:function(data){
	if(data.msg=="ok"){
		$(obj).parent().parent().children("td").children('input[name=citydistanceid]').val(data.id);
		$(obj).parent().parent().after($(tr).clone());
		$(obj).parent().parent().next().children("td").children("input[name=citydistanceid]").val("");
		$(obj).removeAttr("OnClick");
		//********复制新增，将当前记录提交后台服务结束 返回结果，新生成一行*********//*
		$.each($("#hgrid tr"),function(index,item){
			if(count<index){
				$(item).children('td').eq(0).html(index);
				$(item).children('td').children('input[name=citydistanceid]').attr("id","citydistanceid"+index);
				$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+index);
				$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+index);
				$(item).children('td').children('input[name=distance]').attr("id","distance"+index);
				$(item).children('td').children('input[name=subcontractor]').attr("id","subcontractor"+index);
				$(item).children('td').children('input[name=subcontractorid]').attr("id","subcontractorid"+index);
			}
		});	 
	}else{
		return;
	}
}
});
}else{
$(obj).parent().parent().after($(tr).clone());
$(obj).parent().parent().next().children("td").children("input[name=citydistanceid]").val("");
$(obj).removeAttr("OnClick");
//********复制新增，将当前记录提交后台服务结束 返回结果，新生成一行*********//*
$.each($("#hgrid tr"),function(index,item){
	if(count<index){
		$(item).children('td').eq(0).html(index);
		$(item).children('td').children('input[name=citydistanceid]').attr("id","citydistanceid"+index);
		$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+index);
		$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+index);
		$(item).children('td').children('input[name=distance]').attr("id","distance"+index);
		$(item).children('td').children('input[name=subcontractor]').attr("id","subcontractor"+index);
		$(item).children('td').children('input[name=subcontractorid]').attr("id","subcontractorid"+index);
	}
});	

}
}
function createArray(){
var jsonarray=[];
var $citydistanceid=$(".citydistanceid");
var $fromaddress=$("input[name=fromaddress]");
var $toaddress=$("input[name=toaddress]");
var $distance=$(".distance");
var $subcontractor=$(".subcontractor");
var $subcontractorid=$(".subcontractorid");
$.each($(".citydistanceid"),function(n,item){
    var citydistanceid=$($citydistanceid[n]).val();
    var fromaddress=$($fromaddress[n]).val();    
    var toaddress=$($toaddress[n]).val();
    var distance=$($distance[n]).val();
    var subcontractor=$($subcontractor[n]).val();
    var subcontractorid=$($subcontractorid[n]).val();
    var obj=createObj(citydistanceid,fromaddress,toaddress,distance,subcontractor,subcontractorid);
    jsonarray.push(obj);
})
return jsonarray;
}

function createObj(citydistanceid,fromaddress,toaddress,distance,subcontractor,topartyid){
return {
	citydistanceid:citydistanceid,
	fromaddress:fromaddress,
	toaddress:toaddress,
	distance:distance,
	subcontractor:subcontractor,
	topartyid:topartyid
}
}
function f_delete(obj){
var index_current =$(obj).parent().parent().index();
var citydistanceid=$(obj).parent().parent().children("td").children("input[name=citydistanceid]").val();
var from=$(obj).parent().parent().children("td").children("input[name=fromaddress]").attr("id");
$("#"+from+"_message").remove();
var to=$(obj).parent().parent().children("td").children("input[name=toaddress]").attr("id");
$("#"+to+"_message").remove();
var dis=$(obj).parent().parent().children("td").children("input[name=distance]").attr("id");
$("#"+dis+"_message").remove();
$.each($("#hgrid tr"),function(index,item){
	if(index_current<index){
		$(item).children('td').eq(0).html(index-1);
		$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+(index-1));
		$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+(index-1));
		$(item).children('td').children('input[name=distance]').attr("id","distance"+(index-1));//citydistanceid
		$(item).children('td').children('input[name=subcontractor]').attr("id","subcontractor"+(index-1));
		$(item).children('td').children('input[name=citydistanceid]').attr("id","distance"+(index-1));
		$(item).children('td').children('input[name=subcontractorid]').attr("id","distance"+(index-1));
	}
});	
$(obj).parent().parent().remove();
var count2=$("#hgrid tr").length;
if(count2<=8){
	$("div.hgrid").css({"height":"auto","overflow-y":"hidden"});
}else{
	$("div.hgrid").css({"height":"300px","overflow-y":"auto"})
}
if(citydistanceid!=""){
	$.ajax({
		url: "../citydistancecs/delete?random="+Math.random(),   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"citydistanceid":citydistanceid}, //参数     	               
	   	success:function(data){//回传函数
	 		$(obj).parent().parent().remove();
	}
	});
}else{
	$(obj).parent().parent().remove();
}

}
function input_onfocus(obj){
var warn="#"+$(obj).attr("id")+"_message";
$(warn).remove();
$(obj).css("border","1px solid  #a0cfe0");
}
/**检测当前input框输入值是否为空 or 是否满足输入条件
* *********/
function input_onblur(obj){
//$(obj).css("border","1px solid #D3D7D4");
var fromaddress,toaddress,distance,subcontractor;//currentvalue
var defaultfromaddress,defaulttoaddress,defaultdistance,defaultsubcontractor;//defaultvalue
var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
var citydistanceid=$(obj).parent().parent().children("td").children('input[name=citydistanceid]');
$.each(inputset,function(index,item){
	switch(index){
	case 0:
		fromaddress=$(item).val();
		break;
	case 1:
		toaddress=$(item).val();
		break;
	case 2:
		distance=$(item).val();
		  var reg =/^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
		    if(reg.test(distance)){
		    	distance=$(item).val();
		    }else{
		    	distance="";
		    	$(item).val("");
		    }
		if(distance==""){
			$(item).css("border","1px solid red");
		}
		break;	
	case 3:
		subcontractor=$(item).val();
		break;			
	}
});
if(fromaddress!=""&&toaddress!=""&&distance!=""){
	var params="citydistanceid="+citydistanceid+"&fromaddress="+fromaddress+"&toaddress="+toaddress+"&distance="+distance+"&subcontractor="+subcontractor+"&frompartyid="+$("#frompartyid").val()+"&random="+Math.random();
	flag_copy=checkIsExist(params);
}else{
	flag_copy=false;
}
return flag_copy;
}
function fromaddress_onfocus(obj){
$(obj).css("border","1px solid  #a0cfe0");

}
function toaddress_onfocus(obj){
$(obj).css("border","1px solid  #a0cfe0");

}
/***
* 检测该条记录是否已存在
* @param params（该条记录数据）
* @return true or false;
* 
*/
function checkIsExist(params){
$.ajax({
	url:"../citydistancecs/isExist",
	data:params,
	type:'post',	
	async:false,
 	dataType:'json', 
   	success:function(data){
	if(data.msg=="0"&&data.msg!=""){
		flag_copy=true;
	}else{
		$(obj).parent().parent().children("td").children("a").eq(0).removeAttr("OnClick");
		flag_copy= false;
	}
}
});
return flag_copy;
}
/***
* 批量保存
* @author lianggui.zhou
* @return
*/
function save(){
$("#auto").css("display","none");
if($("#hgrid tr").length==1){
	return false;
}
 var sub=$("#hgrid tr:last").children("td").children("input[name=subcontractor]"); 
 var fuck=sub_blur(sub);
 if(fuck==false){
 	return false;
 };
 var jsonList = eval(createArray()); 
 var jsonText = JSON.stringify(jsonList);
 var table_input=$("#hgrid tr").children("td").children("input[type=text]");
 var object=$("#hgrid tr:gt(0)");
 var checkIsEmptyFlag=checkIsEmpty(table_input);
 var checkRepeatFlag=checkRepeat(object);
 $("#save").attr("disabled","disabled");
 if(checkIsEmptyFlag&&checkRepeatFlag){
	$.ajax({
		url: "../citydistancecs/insertCityDistance",   
	 	type:'post',	
	 	dataType:'json', 
	 	async:false,
	 	data:{"jsonText":jsonText,"frompartyid":$("#frompartyid").val()}, //参数     	               
	   	success:function(data){//回传函数
	 	if(data.length){
	 		for(var i=0;i<data.length;i++){
	 			$("#citydistanceid"+(i+1)).val(data[i].id);
	 		}
	 		window.location="../citydistancecs/list?order=18"
	 		}else{
	 			 $("#save").removeAttr("disabled");
	 			 ymPrompt.alert({"title":"提示","message":"操作失败"});
	 		}
	 	}
	});
 }
 if(!checkIsEmptyFlag&&checkRepeatFlag){
	 ymPrompt.alert({"title":"提示","message":"填写必要信息"});
	 $("#save").removeAttr("disabled");
 }
 if(!checkRepeatFlag&&checkIsEmptyFlag){
	 ymPrompt.alert({"title":"提示","message":"有重复记录出现"});
	 $("#save").removeAttr("disabled");
 }
}
/***
* 检测是否有空值
* @author lianggui.zhou
* @param object
* @return false or true
*/
function checkIsEmpty(object){
var result=true;
for(var i=0;i<object.length;i++){
	if($(object[i]).val()==""&&$(object[i]).attr("name")!="subcontractor"){
		//$(object[i]).css("border","1px solid red");
		var warn="#"+$(object[i]).attr("id");
		markText(warn,"不能为空");
		result= false;
	}
}
return result;
}
/**
* 检测是否有重复记录
* @author lianggui.zhou
* @return true or false
*/
function checkRepeat(object){
var re_data=true;
var fromAddress,toAddress,fromAddress1,toAddress1;
var obj_length = object.length;
for(var i=0;i<obj_length;i++){
	fromAddress=$($("input[name=fromaddress]")[i]).val();
	toAddress=$($("input[name=toaddress]")[i]).val();
	if(i<obj_length){
	for(var j=i+1;j<obj_length;j++){
			fromAddress1=$($("input[name=fromaddress]")[j]).val();
			toAddress1=$($("input[name=toaddress]")[j]).val();
		if(fromAddress==fromAddress1&&toAddress==toAddress1){
			//$($("input[name=fromaddress]")[j]).css("border","1px solid red");
			//$($("input[name=toaddress]")[j]).css("border","1px solid red");
			markText("#"+$($("input[name=fromaddress]")[j]).attr("id"),"重复记录");
			markText("#"+$($("input[name=toaddress]")[j]).attr("id"),"重复记录")
			re_data=false;
			continue;
		}
	}
}
}
return re_data;
}
function createTab(data,count){
 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
 var td0=$("<td></td>").css("padding-left","10px");// create 1st td
 var td1=$("<td></td>");//create 2nd td
 var td2=$("<td></td>");// create 3rd td
 var td3=$("<td></td>");// create 4th td
 var td4=$("<td></td>");// create 5th td
 var td5=$("<td></td>");// create 6th td
 var input00=$("<input/>").attr({"type":"hidden","name":"citydistanceid","class":"citydistanceid","value":data[count].citydistanceid,"id":"citydistanceid"+count});
 $(td0).text(count+1);
 $(input00).appendTo(td1);
 $(td0).appendTo(hgrid_tr);
 var input01=$("<input/>").attr({"id":"fromaddress"+count+1,"type":"text","name":"fromaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].fromaddress});
 $(input01).appendTo(td1);
 $(td1).appendTo(hgrid_tr);
 var input02=$("<input/>").attr({"id":"toaddress"+count+1,"type":"text","name":"toaddress", "class":"city_input  inputFocus proCityQueryAll proCitySelAll","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].toaddress});
 $(input02).appendTo(td2);
 $(td2).appendTo(hgrid_tr);
 var input03=$("<input/>").attr({"id":"distance"+count+1,"type":"text","name":"distance", "class":"distance","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].distance});
 $(input03).appendTo(td3);
 $(td3).appendTo(hgrid_tr);
 var input04=$("<input/>").attr({"id":"subcontractor"+count+1,"type":"text","name":"subcontractor", "class":"subcontractor","onfocus":"onFocus(this)","onblur":"sub_blur(this)","onmousemout":"sub_onmousemove(this)","autocomplete":"off","defaultvalue":"","myid":data[count].topartyid,"value":data[count].organization});
 var input05=$("<input/>").attr({"id":"subcontractorid"+count+1,"type":"hidden","name":"subcontractorid", "class":"subcontractorid","value":data[count].topartyid});
 $(input04).appendTo(td4);
 $(input05).appendTo(td4);
 //$(td4).appendTo(input05);
 $(td4).appendTo(hgrid_tr);
 var a06=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
 $(a06).html("复制新增");
 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","20px");
 $(a07).html("删除");
 $(a06).appendTo(td5);
 $(a07).appendTo(td5);
 $(td5).appendTo(hgrid_tr);
 $("#hgrid tr:last").after(hgrid_tr);	
}
function reback(obj){
window.location="../citydistancecs/list?order=18";
}
function sub_blur(object){
var sub_blur_result=false;
var display=$("#auto").css("display");
var organization=$(object).val();
if(display=="none"&&organization!=""){
$.ajax({
	url:"../citydistancecs/checkSubcontract",
	data:{"organization":organization,"frompartyid":$("#frompartyid").val()},
	type:'post',	
 	dataType:'json', 
 	async:false,
   	success:function(data){
	if(data.msg=="0"&&data.msg!=""){
		$(object).attr("myid","");
		$(object).next().val("");
		 markText("#"+$(object).attr("id"),"该分包商不存在");
		 sub_blur_result=false;
	}if(data.msg=="1"){
		$(object).attr("myid",data.id);
		$(object).next().val(data.id);
		sub_blur_result= true;
	}
}
});
}else{
	$(object).attr("myid","");
	$(object).next().val("");
	sub_blur_result= true;
}
return sub_blur_result;
}
function markText(targetId,message){
var _targetId=targetId.substr(1);//去掉#
if($(targetId+"_message").length==0){//当信息提示div不存在时
	var top=$(targetId).offset().top+parseInt($(targetId).css("height"));
	var left=$(targetId).offset().left;
	var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:2px;position:absolute;top:'+top+'px;left:'+left+'px">'+
	message+'</div>';
	$("body").append(txt);
}else{//当信息提示div存在时
	$(targetId+"_message").empty().append(message);
}
$(targetId).css("border","2px solid red");
return targetId+"_message";
}
