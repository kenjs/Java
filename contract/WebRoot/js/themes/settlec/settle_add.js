var flag=false;
var flag_copy=false;
var clickcount=0;
function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl.split("&")[2];
}
$(function(){
	if(getId()!=""){
		$(".conditionDiv").show();
		$("#type1").css("display","inline").html(idUrl.split("&")[1].split("=")[1]);
		$("#frompartyrealname").css("display","inline").html(idUrl.split("&")[4].split("=")[1]);
		$("#settlesetid").html(idUrl.split("&")[5].split("=")[1]);//隐藏settlesetid的值
		$("#reporttype").html(idUrl.split("&")[6].split("=")[1]);
		$("#type_org").css("display","none").val(idUrl.split("&")[1].split("=")[1]);
		//document.getElementById("topartyid").type="hidden";
		$("#topartyid").hide();
		$("input[name=type_org]").val(idUrl.split("&")[1].split("=")[1]);
		$("#topartyid").attr("myid",idUrl.split("&")[2].split("=")[1]);
		$("#topartyname").html(idUrl.split("&")[3].split("=")[1]+' ');
		selectList(obj,2);//后面的参数来判断新增和修改，1为新增，2为修改
		flag=true;
		flag_copy=true;
		clickcount=1;
	}else{
		$("#reporttype").html(idUrl.split("&")[1].split("=")[1]);
	}
	$(".onfocusHtml").click(function(){
		$(".onfocusHtml").css('border','1px solid rgb(214, 214, 214)');
	});
	$(".onfocusInput").click(function(){
		$(".onfocusInput").css('border','1px solid rgb(214, 214, 214)');
	});
});
function typeChange(){//下拉改变的同时改变（发货商/分包商）标签
	var type_org=$("#type_org").val();
	if(type_org=='应付结算'){
		$("#topartyname").html('分包商');
		$("#partyType").val('分包');
		$("#topartyid").attr('onfocus',"onFocus(this,'分包')");
	}else if(type_org=='应收结算'){
		$("#topartyname").html('发货方 ');
		$("#partyType").val('发货方');
		$("#topartyid").attr('onfocus',"onFocus(this,'发货方')");
	}
	$("#topartyid").val('');
	$("#topartyid").attr('myid', '');
	$("#hgrid tr:gt(0)").remove();//移除列表的内容（行数大于1的行）
	clickcount=0;
}
function selectList(obj,num){//需要调用接口查询列表
	var topartyid=$("#topartyid").attr("myid");
	if(topartyid==""){
		ymPrompt.alert({"title":"提示","message":"请填写"+$("#topartyname").text().trim()+"名称"});
		return;
	}
	$("#hgrid tr:gt(0)").remove();
	//createTab
	$.ajax({
		url: "../settlecs/selectListOfSettle?random="+Math.random(),   
	 	type:'post',
	 	dataType:'json', 
	 	data:{"settlesetid":$("#settlesetid").html(),"topartyid":$("#topartyid").attr("myid"),"fromaddress":$("#fromaddressInp").val(),
		"toaddress":$("#toaddressInp").val(),"type":$("#type_org").val(),"pageSize":"999333444","skipCount":"0"}, //参数     	               
	   	success:function(data){//回传函数
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
	 		if(num==1&&data.length>0){
	 			ymPrompt.alert({"title":"提示","message":"该"+$("#topartyname").text().trim()+"已设置"});
	 			clickcount=0;
	 			return;//如果是新增，就不执行创建表格操作
	 		}
	 		else if(num==1&&data.length==0){
	 			clickcount=1;
	 			tabendAdd();
	 			flag_copy=true;
	 		}
			for(var i=0;i<data.length;i++){
				createTab(data,i);
			}
		}
	});
}
function createTab(data,count){
//	 var count=$("#hgrid tr").length;
	 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
	 var td1=$("<td></td>");//create 1st td
	 var td2=$("<td></td>");// create 2nd td
	 var td3=$("<td></td>");// create 3rd td
	 var td4=$("<td></td>");// create 4th td
	 var td5=$("<td></td>");// create 5th td
	 var td6=$("<td></td>");// create 6th td
	 var td7=$("<td></td>");// create 7th td
	 var td8=$("<td></td>");// create 8th td
	 var input00=$("<input/>").attr({"type":"hidden","name":"settlesetid","class":"settlesetid","value":data[count].settlesetid,"id":"settlesetid"+count});
	 $(input00).appendTo(td1);
	 $("#fromaddress").clone(true).appendTo(td1).css('display', 'block').attr('id','fromaddress'+count).attr('name', 'fromaddress').attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll fromaddress onfocusHtml').val(data[count].fromaddress);
	 $(td1).appendTo(hgrid_tr);
	 $("#toaddress").clone(true).appendTo(td2).css('display', 'block').attr('id','toaddress'+count).attr('name', 'toaddress').attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll toaddress onfocusHtml').val(data[count].toaddress);
	 $(td2).appendTo(hgrid_tr);
	 var input03=$("<input/>").attr({"id":"eachweightprice"+count,"type":"text","name":"eachweightprice", "class":"eachweightprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].eachweightprice});
	 $(input03).appendTo(td3);
	 $(td3).appendTo(hgrid_tr);
	 var input04=$("<input/>").attr({"id":"eachvolumeprice"+count,"type":"text","name":"eachvolumeprice", "class":"eachvolumeprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].eachvolumeprice});
	 $(input04).appendTo(td4);
	 $(td4).appendTo(hgrid_tr);
	 var input05=$("<input/>").attr({"id":"eachtonkilometerprice"+count,"type":"text","name":"eachtonkilometerprice", "class":"eachtonkilometerprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].eachtonkilometerprice});
	 $(input05).appendTo(td5);
	 $(td5).appendTo(hgrid_tr);
	 var input06=$("<input/>").attr({"id":"eachcubekilometerprice"+count,"type":"text","name":"eachcubekilometerprice", "class":"eachcubekilometerprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].eachcubekilometerprice});
	 $(input06).appendTo(td6);
	 $(td6).appendTo(hgrid_tr);
	 var input07=$("<input/>").attr({"id":"eachcarprice"+count,"type":"text","name":"eachcarprice", "class":"eachcarprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":"","value":data[count].eachcarprice});
	 $(input07).appendTo(td7);
	 $(td7).appendTo(hgrid_tr);
	 var a08=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
	 $(a08).html("复制新增");
	 var a09=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","10px");
	 $(a09).html("删除");
	 $(a08).appendTo(td8);
	 $(a09).appendTo(td8);
	 $(td8).appendTo(hgrid_tr);
	 $("#hgrid tr:last").after(hgrid_tr);	
}
function reback(obj){
	window.location="../settlecs/settle_set?order=4";
}
function f_onmouseover(obj){
	$(obj).css("background-color","#fffddd");
}
function f_onmouseout(obj){
	$(obj).css("background-color","white");
}
function tabendAdd(){
	if($("#type_org").val()==''){
		ymPrompt.alert({"title":"提示","message":"请选择结算类型"});
		return;
	}
	clickcount++;
	$("#auto").css("display","none");
	if(clickcount<=1){
		var topartyid=$.trim($("input[name=topartyid]").val());
		if(topartyid==""&&$("input[name=topartyid]").attr("myid")==""){
			ymPrompt.alert({"title":"提示","message":"请填写"+$("#topartyname").text()+"名称"});
			clickcount=0;
			return false;
		}
		else{
			$.ajax({
				url: "../settlecs/selectToPartyId",
			 	type:'post',	
			 	dataType:'json', 
			 	async: false,
			 	data:{topartyid:topartyid,partyType:$("#partyType").val()}, //参数
			   	success:function(data){//回传函数
					if(data.msg=="1"){
						$("#frompartyid").val(data.id);
						flag=true;
						flag_copy=true;
						selectList(obj,1);
					}
					if(data.msg=="0"){
						ymPrompt.alert({"title":"提示","message":"无此"+$("#topartyname").text()});
					}
				}
			});
		}
	}else{
		if(flag==false){
			ymPrompt.alert({"title":"提示","message":"请选择相关"+$("#topartyname").text()});
			return;
		}
		 var count=$("#hgrid tr").length;
		 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
		 var td1=$("<td></td>");//create 1st td
		 var td2=$("<td></td>");// create 2nd td
		 var td3=$("<td></td>");// create 3rd td
		 var td4=$("<td></td>");// create 4th td
		 var td5=$("<td></td>");// create 5th td
		 var td6=$("<td></td>");// create 6th td
		 var td7=$("<td></td>");// create 7th td
		 var td8=$("<td></td>");// create 8th td
		 var input00=$("<input/>").attr({"type":"hidden","name":"settlesetid","class":"settlesetid","value":"","id":"settlesetid"+count});
		 $(input00).appendTo(td1);
		 $("#fromaddress").clone(true).appendTo(td1).css('display', 'block').attr('id','fromaddress'+count).attr('name', 'fromaddress').attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll fromaddress onfocusHtml');
		 $(td1).appendTo(hgrid_tr);
		 $("#toaddress").clone(true).appendTo(td2).css('display', 'block').attr('id','toaddress'+count).attr('name', 'toaddress').attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll toaddress onfocusHtml');
		 $(td2).appendTo(hgrid_tr);
		 var input03=$("<input/>").attr({"id":"eachweightprice"+count,"type":"text","name":"eachweightprice", "class":"eachweightprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input03).appendTo(td3);
		 $(td3).appendTo(hgrid_tr);
		 var input04=$("<input/>").attr({"id":"eachvolumeprice"+count,"type":"text","name":"eachvolumeprice", "class":"eachvolumeprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input04).appendTo(td4);
		 $(td4).appendTo(hgrid_tr);
		 var input05=$("<input/>").attr({"id":"eachtonkilometerprice"+count,"type":"text","name":"eachtonkilometerprice onfocusInput", "class":"eachtonkilometerprice","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input05).appendTo(td5);
		 $(td5).appendTo(hgrid_tr);
		 var input06=$("<input/>").attr({"id":"eachcubekilometerprice"+count,"type":"text","name":"eachcubekilometerprice onfocusInput", "class":"eachcubekilometerprice","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input06).appendTo(td6);
		 $(td6).appendTo(hgrid_tr);
		 var input07=$("<input/>").attr({"id":"eachcarprice"+count,"type":"text","name":"eachcarprice", "class":"eachcarprice onfocusInput","onfocus":"input_onfocus(this)","onblur":"input_onblur(this)","error_msg":"","autocomplete":"off","defaultvalue":""});
		 $(input07).appendTo(td7);
		 $(td7).appendTo(hgrid_tr);
		 var a08=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
		 $(a08).html("复制新增");
		 var a09=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","10px");
		 $(a09).html("删除");
		 $(a08).appendTo(td8);
		 $(a09).appendTo(td8);
		 $(td8).appendTo(hgrid_tr);
		 $("#hgrid tr:last").after(hgrid_tr);
	}
}
function input_onfocus(obj){
	$(".onfocusInput").css("border","1px solid  #a0cfe0");
}
function input_onblur(obj){
	var fromaddress,toaddress,eachweightprice,eachvolumeprice,eachtonkilometerprice;
	var defaultfromaddress,defaulttoaddress,defaulteachweightprice,defaulteachvolumeprice,defaulteachtonkilometerprice;
	var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
	var settlesetid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
	$.each(inputset,function(index,item){
		switch(index){
		case 0:
			fromAddress=$(item).val();
			$(item).defaultvalue=fromAddress;//
			break;
		case 1:
			toAddress=$(item).val();
			break;
		case 2:
			eachweightprice=$(item).val();
			    if(myNumberictow(eachweightprice, 3)){
			    	eachweightprice=$(item).val();
			    }else{
			    	eachweightprice="";
			    	$(item).val("");
			    }
			break;	
		case 3:
			eachvolumeprice=$(item).val();
			if(myNumberictow(eachvolumeprice, 2)){
				eachvolumeprice=$(item).val();
			}else{
				eachvolumeprice="";
				$(item).val("");
			}
			break;	
		case 4:
			eachtonkilometerprice=$(item).val();
			if(myNumberictow(eachtonkilometerprice, 2)){
				eachtonkilometerprice=$(item).val();
			}else{
				eachtonkilometerprice="";
				$(item).val("");
			}
			break;
		case 5:
			eachcarprice=$(item).val();
			var reg = /^[0-9]+(.[0-9]{1,2})?$/;
			if(myNumberictow(eachcarprice, 2)){
				eachcarprice=$(item).val();
			}else{
				eachcarprice="";
				$(item).val("");
			}
			break;	
		}
	});
	var topartyid=$("#topartyid").attr('myid');
	var type=$("#type_org").val();
	if(fromaddress!=""&&toaddress!=""&&(eachweightprice!=""||eachvolumeprice!=""||eachtonkilometerprice!="")){
		var params="settlesetid="+settlesetid+"&topartyid="+topartyid+"&type="+type+"&fromaddress="+fromaddress+
		"&toaddress="+toaddress+"&eachweightprice="+eachweightprice+"&eachvolumeprice="+eachvolumeprice+
		"&eachtonkilometerprice="+$("#eachtonkilometerprice").val()+"&random="+Math.random();
		checkIsExist(params);
	}else{
		flag_copy=false;
	}
	return flag_copy;
}
/***
 * 检测该条记录是否已存在
 * @param params（该条记录数据）
 * @return true or false;
 * 
 */
function checkIsExist(params){
	$.ajax({
		url:"../settlecs/isExist",
		data:params,
		async: false,
		type:'post',	
	 	dataType:'json', 
	   	success:function(data){
		if(data.msg=="0"&&data.msg!=""){
			flag_copy=true;
		}else{
			flag_copy= false;
		}
	}
	});
	return flag_copy;
}
function f_copy_click(obj){
	var tr=$(obj).parent().parent();
	var count =$(obj).parent().parent().index();
	var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
	var topartyid;
	var fromaddress,toaddress,eachweightprice,eachvolumeprice,eachtonkilometerprice,eachcubekilometerprice,eachcarprice,settlesetid;
	var defaultfromaddress,defaulttoaddress,defaulteachweightprice,defaulteachvolumeprice,defaulteachtonkilometerprice;
	settlesetid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
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
			eachweightprice=$(item).val();
			    if(myNumberictow(eachweightprice, 3)){
			    	eachweightprice=$(item).val();
			    }else{
			    	eachweightprice="";
			    	$(item).val("");
			    }
			break;	
		case 3:
			eachvolumeprice=$(item).val();
			if(myNumberictow(eachvolumeprice, 2)){
				eachvolumeprice=$(item).val();
			}else{
				eachvolumeprice="";
				$(item).val("");
			}
			break;	
		case 4:
			eachtonkilometerprice=$(item).val();
			if(myNumberictow(eachtonkilometerprice, 2)){
				eachtonkilometerprice=$(item).val();
			}else{
				eachtonkilometerprice="";
				$(item).val("");
			}
			break;	
		case 5:
			eachcubekilometerprice=$(item).val();
			if(myNumberictow(eachcubekilometerprice, 2)){
				eachcubekilometerprice=$(item).val();
			}else{
				eachcubekilometerprice="";
				$(item).val("");
			}
			break;	
		case 6:
			eachcarprice=$(item).val();
			if(myNumberictow(eachcarprice, 2)){
				eachcarprice=$(item).val();
			}else{
				eachcarprice="";
				$(item).val("");
			}
			break;	
		}
	});	
	//********复制新增，获取当前输入内容，并记录当前输入内容结束*********//*
	if(fromaddress==""){
		$(obj).parent().parent().children("td").children('input[name=fromaddress]').css("border","1px solid red");
		return false;
	}
	if(toaddress==""){
		$(obj).parent().parent().children("td").children('input[name=toaddress]').css("border","1px solid red");
		return false;
	}
	if(eachweightprice==""&&eachvolumeprice==""&&eachtonkilometerprice==""&&eachcubekilometerprice==""&&eachcarprice==""){
		$(obj).parent().parent().children("td").children('input[name=eachweightprice]').css("border","1px solid red");
		$(obj).parent().parent().children("td").children('input[name=eachvolumeprice]').css("border","1px solid red");
		$(obj).parent().parent().children("td").children('input[name=eachtonkilometerprice]').css("border","1px solid red");
		$(obj).parent().parent().children("td").children('input[name=eachcubekilometerprice]').css("border","1px solid red");
		$(obj).parent().parent().children("td").children('input[name=eachcarprice]').css("border","1px solid red");
		return false;
	}
	//*********检查此条记录是否存在**********//*
	var params="settlesetid="+settlesetid+"&fromaddress="+fromaddress+"&toaddress="+toaddress+
	"&eachweightprice="+eachweightprice+"&eachvolumeprice="+eachvolumeprice+"&eachtonkilometerprice="+
	eachtonkilometerprice+"&eachcubekilometerprice="+eachcubekilometerprice+"&eachcarprice="+eachcarprice+
	"&topartyid="+$("#topartyid").attr("myid")+"&type="+$("#type_org").val()+"&frompartyid="+$("#frompartyid").val()+
	"&reporttype="+$("#reporttype").text()+"&random="+Math.random();
	if(settlesetid==''){
		checkIsExist(params);
		if(flag_copy==false){
			$(obj).parent().parent().children("td").children("input:lt(3)").css('border', '1px solid red');
		}
	}
	if(flag_copy){
	//********复制新增，将当前记录提交后台服务开始*********//*
		$.ajax({
			url:"../settlecs/insert",
			data:params,
			type:'post',	
		 	dataType:'json', 
		   	success:function(data){
			if(data.msg=="ok"){
				$(obj).parent().parent().after($(tr).clone(true));
				var sid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
				if(sid!=''){
					$(obj).parent().parent().next().children("td").children('input[name=settlesetid]').val('');
				}else{
					$(obj).parent().parent().children("td").children('input[name=settlesetid]').val(data.id);
				}
				$(obj).removeAttr("OnClick");
				//********复制新增，将当前记录提交后台服务结束 返回结果，新生成一行*********//*
				$.each($("#hgrid tr"),function(index,item){
					if(count<index){
						$(item).children('td').children('input[name=settlesetid]').attr("id","settlesetid"+index);
						$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+index);
						$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+index);
						$(item).children('td').children('input[name=eachweightprice]').attr("id","eachweightprice"+index);
						$(item).children('td').children('input[name=eachvolumeprice]').attr("id","eachvolumeprice"+index);
						$(item).children('td').children('input[name=eachtonkilometerprice]').attr("id","eachtonkilometerprice"+index);
						$(item).children('td').children('input[name=eachcubekilometerprice]').attr("id","eachcubekilometerprice"+index);
						$(item).children('td').children('input[name=eachcarprice]').attr("id","eachcarprice"+index);
					}
				});	 
			}else{
				return;
			}
		}
		});
	}
}
function f_delete(obj){
	var index_current =$(obj).parent().parent().index();
	var settlesetid=$(obj).parent().parent().children("td").children("input[name=settlesetid]").val();
	$.each($("#hgrid tr"),function(index,item){
		if(index_current<index){
			$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+(index-1));
			$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+(index-1));
			$(item).children('td').children('input[name=eachweightprice]').attr("id","eachweightprice"+(index-1));//citydistanceid
			$(item).children('td').children('input[name=eachvolumeprice]').attr("id","eachvolumeprice"+(index-1));
			$(item).children('td').children('input[name=eachtonkilometerprice]').attr("id","eachtonkilometerprice"+(index-1));
			$(item).children('td').children('input[name=eachcubekilometerprice]').attr("id","eachcubekilometerprice"+(index-1));
			$(item).children('td').children('input[name=eachcarprice]').attr("id","eachcarprice"+(index-1));
		}
	});	
	if(settlesetid!=""){
		$.ajax({
			url: "../settlecs/delete?random="+Math.random(),
		 	type:'post',	
		 	dataType:'json', 
		 	data:{"settlesetid":settlesetid}, //参数     	               
		   	success:function(data){//回传函数
		 		if(data.msg=='ok'){
		 			$(obj).parent().parent().remove();
		 		}
		 		else{
		 			ymPrompt.alert({"title":"提示","message":'删除失败'});
		 		}
		}
		});
	}else{
		$(obj).parent().parent().remove();
	}

}
/***
 * 批量保存
 * @author yaoyan.lin
 * @return
 */
function save(){
	 var jsonList = eval(createArray()); 
	 var jsonText = JSON.stringify(jsonList);
	 var table_tr=$("#hgrid tr");
	 var table_td=$("#hgrid tr").children("td");
	 var table_obj=$("#hgrid tr").children("td").children("input[type=text]");
	 var object=$("#hgrid tr:gt(0)");
	 var checkIsEmptyFlag=checkIsEmpty(table_obj, table_tr, table_td);
	 var checkRepeatFlag=checkRepeat(object);
	 if(checkIsEmptyFlag==2&&checkRepeatFlag){
		$("#save").attr("disabled","disabled");
		$.ajax({
			url: "../settlecs/insertSettleSet", 
		 	type:'post',	
		 	dataType:'json',
		 	data:{"jsonText":jsonText,"frompartyid":$("#frompartyid").val()
			,"topartyid":$("#topartyid").attr("myid"),"type":$("#type_org").val(),"reporttype":$("#reporttype").text()}, //参数     	               
		   	success:function(data){//回传函数
		 		for(var i=0;i<data.length;i++){
		 			$("#settlesetid"+(i+1)).val(data[i].id);
		 		}
		 		$("#save").removeAttr("disabled");
		 		ymPrompt.alert({"title":"提示","message":'保存成功',handler:
		 			function callback(){
			 			location.href='../settlecs/settle_set?order=4';
			 		}
		 		});
			},
			fail:function(){
				$("#save").removeAttr("disabled");
			}
		});
	 }
	 if(checkIsEmptyFlag==0&&checkRepeatFlag){
		 ymPrompt.alert({"title":"提示","message":"填写必要信息"});
	 }
	 if(checkIsEmptyFlag==1&&checkRepeatFlag){
		 ymPrompt.alert({"title":"提示","message":"请至少填写一种单价类型"});
	 }
	 if(!checkRepeatFlag&&checkIsEmptyFlag){
		 ymPrompt.alert({"title":"提示","message":"有重复记录出现"});
	 }
}


/***
 * 检测是否有空值
 * @author yaoyan.lin
 * @param object
 * @return 0,1,2   |   0表示发货地/收货地址未填，1表示重货单价/泡货单价/吨公里单价/方公里单价/整车单价均未填，2表示填写正确
 */
function checkIsEmpty(t_obj, t_tr, t_td){
	if(t_tr.length<2){
		return 0;
	}
	for(var i=1;i<t_tr.length;i++){
		for(var j=0;j<2;j++){
			var obj_len=7*i+j-7;
			if($(t_obj[obj_len]).val()==""){
				$(t_obj[obj_len]).css("border","1px solid red");
				return 0;
			}
		}
		if($(t_obj[7*i-5]).val()==""&&$(t_obj[7*i-4]).val()==""&&$(t_obj[7*i-3]).val()==""&&$(t_obj[7*i-2]).val()==""&&$(t_obj[7*i-1]).val()==""){
			$(t_obj[7*i-5]).css("border","1px solid red");
			$(t_obj[7*i-4]).css("border","1px solid red");
			$(t_obj[7*i-3]).css("border","1px solid red");
			$(t_obj[7*i-2]).css("border","1px solid red");
			$(t_obj[7*i-1]).css("border","1px solid red");
			return 1;
		}
		if(i+1==t_tr.length){
			return 2;
		}
	}
}
/**
 * 检测是否有重复记录
 * @author yaoyan.lin
 * @return true or false
 */
function checkRepeat(object){
	var re_data=true;
	var fromaddress,toaddress,fromaddress1,toaddress1;
	var obj_length = object.length;
	for(var i=0;i<obj_length;i++){
		fromaddress=$($("input[name=fromaddress]")[i]).val();
		toaddress=$($("input[name=toaddress]")[i]).val();
		if(i<obj_length){
		for(var j=i+1;j<obj_length;j++){
			fromaddress1=$($("input[name=fromaddress]")[j]).val();
			toaddress1=$($("input[name=toaddress]")[j]).val();
			if(fromaddress==fromaddress1&&toaddress==toaddress1){
				$($("input[name=fromaddress]")[j]).css("border","1px solid red");
				$($("input[name=toaddress]")[j]).css("border","1px solid red");
				re_data=false;
				continue;
			}
		}
	}
}
	return re_data;
}
function createArray(){
    var jsonarray=[];
    var $settlesetid=$(".settlesetid");
    var $fromaddress=$("input[name=fromaddress]");
    var $toaddress=$("input[name=toaddress]");
    var $eachweightprice=$(".eachweightprice");
    var $eachvolumeprice=$(".eachvolumeprice");
    var $eachtonkilometerprice=$(".eachtonkilometerprice");
    var $eachcubekilometerprice=$(".eachcubekilometerprice");
    var $eachcarprice=$(".eachcarprice");
    $.each($(".settlesetid"),function(n,item){
        var settlesetid=$($settlesetid[n]).val();
        var fromaddress=$($fromaddress[n]).val();   
        var toaddress=$($toaddress[n]).val();
        var eachweightprice=$($eachweightprice[n]).val();
        var eachvolumeprice=$($eachvolumeprice[n]).val();
        var eachtonkilometerprice=$($eachtonkilometerprice[n]).val();
        var eachcubekilometerprice=$($eachcubekilometerprice[n]).val();
        var eachcarprice=$($eachcarprice[n]).val();
        var obj=createObj(settlesetid,fromaddress,toaddress,eachweightprice,eachvolumeprice,eachtonkilometerprice,eachcubekilometerprice,eachcarprice);
        jsonarray.push(obj);
    })
    return jsonarray;
}
function createObj(settlesetid,fromaddress,toaddress,eachweightprice,eachvolumeprice,eachtonkilometerprice,eachcubekilometerprice,eachcarprice){
	return {
		settlesetid:settlesetid,
		fromaddress:fromaddress,
    	toaddress:toaddress,
    	eachweightprice:eachweightprice,
    	eachvolumeprice:eachvolumeprice,
    	eachtonkilometerprice:eachtonkilometerprice,
    	eachcubekilometerprice:eachcubekilometerprice,
    	eachcarprice:eachcarprice
    }
}

//检验小数
function myNumberictow(num, len) {
	if(isNaN(num)){
		return false;
	}
	var str = num.split(".");
	if(str.length>1){
		var strs = str[0];
		if(strs.length>10) {
			return false;
		}
		if(str[1]!=null&&str[1].length>len){
			return false;
		}
	}else{
		if(num.length>10) {
			return false;
		}
	}
	return true;
}

