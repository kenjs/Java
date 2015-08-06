var flag=false;
var flag_copy=false;
var clickcount=0;
var clickArr=new Array();//判断新增按钮是否被点击
var deleteFlag='';
for(var i=0;i<9999;i++){
	clickArr[i]=new Array();
}
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
		url: "../settlecs/selectListOfSettle_jt?random="+Math.random(),   
	 	type:'post',
	 	dataType:'json', 
	 	data:{"settlesetid":$("#settlesetid").html(),"topartyid":$("#topartyid").attr("myid"),"fromaddress":$("#fromaddressInp").val(),
		"toaddress":$("#toaddressInp").val(),"type":$("#type_org").val(),"pageSize":"999333444","skipCount":"0"}, //参数     	               
	   	success:function(data){//回传函数
			if(num==1&&data.Data.length>0){
				ymPrompt.alert({"title":"提示","message":"该"+$("#topartyname").text().trim()+"已设置"});
				clickcount=0;
			}else{
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
		}
	});
}
function createTab(data,count){
//	 var count=$("#hgrid tr").length;
	 var hgrid_tr=$("<tr>").attr({"onmouseover":"f_onmouseover(this)", "onmouseout":"f_onmouseout(this)"}); //创建tr
	 var td1=$("<td valign='top'></td>");//create 1st td
	 var td2=$("<td valign='top'></td>");// create 2nd td
	 var td3=$("<td valign='top'></td>");// create 3rd td
	 var td4=$("<td valign='top' colspan='2'></td>");// create 4th td
	 var td6=$("<td valign='top' style='padding-top:12px;'></td>");// create 6th td
	 var input00=$("<input/>").attr({"type":"hidden","name":"settlesetid","class":"settlesetid","value":data[count].settlesetid,"id":"settlesetid"+(count+1)});
	 $(input00).appendTo(td1);
	 $("#fromaddress").clone(true).appendTo(td1).css('display', 'block').attr('id','fromaddress'+(count+1)).attr('name', 'fromaddress').attr("onblur","input_onblur(this,'1')").attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll fromaddress onfocusHtml').attr('value', data[count].fromaddress);
	 $(td1).appendTo(hgrid_tr);
	 $("#toaddress").clone(true).appendTo(td2).css('display', 'block').attr('id','toaddress'+(count+1)).attr('name', 'toaddress').attr("onblur","input_onblur(this,'1')").attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll toaddress onfocusHtml').attr('value',data[count].toaddress);
	 $(td2).appendTo(hgrid_tr);
	 var option03_1=$("<option>按重量计费</option>").attr({"value":"按重量计费"});
	 var option03_2=$("<option>按体积计费</option>").attr({"value":"按体积计费"});
	 if(data[count].billtype=='按重量计费'){
		 $(option03_1).attr('selected', 'selected');
	 }else{
		 $(option03_2).attr('selected', 'selected');
	 }
	 var input03=$("<select onchange='select_change(this)'/>").attr({"id":"billtype"+(count+1),"name":"billtype", "class":"billtype onfocusHtml","onfocus":"input_onfocus(this,'1')","onblur":"input_onblur(this,'1')","error_msg":"","autocomplete":"off"}).css({"width": "90px", "height": "25px", "margin-left": "10px","border":"1px solid rgb(214, 214, 214)"});
	 $(option03_1).appendTo(input03);
	 $(option03_2).appendTo(input03);
	 $(input03).appendTo(td3);
	 $(td3).appendTo(hgrid_tr);
	 var table4=$("<table class='insideTable'/>").css({});
	 var in_list=data[count].settlestepsetlist;
	 for(var i=0;i<in_list.length;i++){
		 var input01=$("<input/>").attr({"type":"hidden","name":"settlestepsetid","class":"settlestepsetid","value":in_list[i].settlestepsetid,"id":"settlestepsetid"+(count+1)});
		 var input4_1=$("<input/>").attr({"id":"startvalue"+(count+1)+"_1","type":"text","name":"startvalue","class":"startvalue","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":"","value":in_list[i].startvalue,"readonly":"readonly"}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 var span4_1=$("<span/>").html("-").css({"padding-left":"10px"});
		 var input4_2=$("<input/>").attr({"id":"endvalue"+(count+1)+"_1","type":"text","name":"endvalue","class":"endvalue","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":"","oninput":"endvalue_change(this)","value":in_list[i].endvalue}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 var a4_1=$("<a>").attr({"href":"javascript:void(0)","OnClick":"table_add(this)"}).css({"padding-left":"10px"});
		 var input05=$("<input/>").attr({"id":"unitprice"+(count+1)+"_1","type":"text","name":"unitprice", "class":"unitprice","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":"","value":in_list[i].unitprice}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 $(a4_1).html("新增");
		 var a4_2;
		 if(i==0){
			 a4_2=$("<a>").attr({"href":"javascript:void(0)","OnClick":"table_delete(this)","class":"deleteBtn"}).css({"margin-left":"10px","display":"none"}); 
		 }else{
			 a4_2=$("<a>").attr({"href":"javascript:void(0)","OnClick":"table_delete(this)","class":"deleteBtn"}).css({"margin-left":"10px"});
		 }
		 $(a4_2).html("删除");
		 var td4_1=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 var td4_2=$("<td width='16px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 var td4_3=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 var td4_4=$("<td width='34px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 var td4_5=$("<td width='34px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 var td4_6=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"1px 0px 1px 0px"});
		 $(input01).appendTo(td4_1);
		 $(input4_1).appendTo(td4_1);
		 $(span4_1).appendTo(td4_2);
		 $(input4_2).appendTo(td4_3);
		 $(a4_1).appendTo(td4_4);
		 $(a4_2).appendTo(td4_5);
		 $(input05).appendTo(td4_6);
		 var tr4_1=$("<tr/>").css({"padding":"0px 0px 2px 0px"});
		 $(td4_1).appendTo(tr4_1);
		 $(td4_2).appendTo(tr4_1);
		 $(td4_3).appendTo(tr4_1);
		 $(td4_4).appendTo(tr4_1);
		 $(td4_5).appendTo(tr4_1);
		 $(td4_6).appendTo(tr4_1);
		 $(tr4_1).appendTo(table4);
	 }
	 $(table4).appendTo(td4);
	 $(td4).appendTo(hgrid_tr);
	 var a06=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
	 $(a06).html("复制新增");
	 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","10px");
	 $(a07).html("删除");
	 $(a06).appendTo(td6);
	 $(a07).appendTo(td6);
	 $(td6).appendTo(hgrid_tr);
	 $("#hgrid").append(hgrid_tr);
}
function select_change(obj){
	if($(obj).val()=='按重量计费'){
		$(obj).children("option").first().attr('selected','selected');
		$(obj).children("option").last().removeAttr('selected');
	}else{
		$(obj).children("option").first().removeAttr('selected');
		$(obj).children("option").last().attr('selected','selected');
	}
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
			ymPrompt.alert({"title":"提示","message":"请填写"+$("#topartyname").text().trim()+"名称"});
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
		 var td1=$("<td valign='top'></td>");//create 1st td
		 var td2=$("<td valign='top'></td>");// create 2nd td
		 var td3=$("<td valign='top'></td>");// create 3rd td
		 var td4=$("<td valign='top' colspan='2'></td>");// create 4th td
		 var td6=$("<td valign='top' style='padding-top:12px;'></td>");// create 6th td
		 var input00=$("<input/>").attr({"type":"hidden","name":"settlesetid","class":"settlesetid","value":"","id":"settlesetid"+count});
		 $(input00).appendTo(td1);
		 $("#fromaddress").clone(true).appendTo(td1).css('display', 'block').attr('id','fromaddress'+count).attr('name', 'fromaddress').attr("onblur","input_onblur(this,'1')").attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll fromaddress onfocusHtml');
		 $(td1).appendTo(hgrid_tr);
		 $("#toaddress").clone(true).appendTo(td2).css('display', 'block').attr('id','toaddress'+count).attr('name', 'toaddress').attr("onblur","input_onblur(this,'1')").attr('class', 'city_input inputFocus proCityQueryAll proCitySelAll toaddress onfocusHtml');
		 $(td2).appendTo(hgrid_tr);
		 var option03_1=$("<option>按重量计费</option>").attr({"value":"按重量计费"});
		 var option03_2=$("<option>按体积计费</option>").attr({"value":"按体积计费"});
		 var input03=$("<select onchange='select_change(this)'/>").attr({"id":"billtype"+count,"name":"billtype", "class":"billtype onfocusHtml","onfocus":"input_onfocus(this,'1')","onblur":"input_onblur(this,'1')","error_msg":"","autocomplete":"off"}).css({"width": "90px", "height": "25px", "margin-left": "10px","border":"1px solid rgb(214, 214, 214)"});
		 $(option03_1).appendTo(input03);
		 $(option03_2).appendTo(input03);
		 $(input03).appendTo(td3);
		 $(td3).appendTo(hgrid_tr);
		 var input01=$("<input/>").attr({"type":"hidden","name":"settlestepsetid","class":"settlestepsetid","value":"","id":"settlestepsetid"+count});
		 var input4_1=$("<input/>").attr({"id":"startvalue"+count+"_1","type":"text","name":"startvalue","class":"startvalue","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":"","value":"0","readonly":"readonly"}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 var span4_1=$("<span/>").html("-").css({"padding-left":"10px"});
		 var input4_2=$("<input/>").attr({"id":"endvalue"+count+"_1","type":"text","name":"endvalue","class":"endvalue","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":"","oninput":"endvalue_change(this)"}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 var a4_1=$("<a>").attr({"href":"javascript:void(0)","OnClick":"table_add(this)"}).css({"padding-left":"10px"});
		 var input05=$("<input/>").attr({"id":"unitprice"+count+"_1","type":"text","name":"unitprice", "class":"unitprice","onfocus":"input_onfocus(this,'2')","onblur":"input_onblur(this,'2')","error_msg":"","autocomplete":"off","defaultvalue":""}).css({"width": "55px", "height": "25px", "margin-left": "10px","text-align":"center","border":"1px solid rgb(214, 214, 214)"});
		 $(a4_1).html("新增");
		 var a4_2=$("<a>").attr({"href":"javascript:void(0)","OnClick":"table_delete(this)","class":"deleteBtn"}).css({"margin-left":"10px","display":"none"});
		 $(a4_2).html("删除");
		 var td4_1=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 var td4_2=$("<td width='16px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 var td4_3=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 var td4_4=$("<td width='34px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 var td4_5=$("<td width='34px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 var td4_6=$("<td width='65px' style='border-bottom: 0px'/>").css({"padding":"0px 0px 2px 0px"});
		 $(input01).appendTo(td4_1);
		 $(input4_1).appendTo(td4_1);
		 $(span4_1).appendTo(td4_2);
		 $(input4_2).appendTo(td4_3);
		 $(a4_1).appendTo(td4_4);
		 $(a4_2).appendTo(td4_5);
		 $(input05).appendTo(td4_6);
		 var tr4_1=$("<tr/>").css({"padding":"0px 0px 2px 0px"});
		 $(td4_1).appendTo(tr4_1);
		 $(td4_2).appendTo(tr4_1);
		 $(td4_3).appendTo(tr4_1);
		 $(td4_4).appendTo(tr4_1);
		 $(td4_5).appendTo(tr4_1);
		 $(td4_6).appendTo(tr4_1);
		 var table4=$("<table class='insideTable'/>").css({});
		 $(tr4_1).appendTo(table4);
		 $(table4).appendTo(td4);
		 $(td4).appendTo(hgrid_tr);
		 
		 var a06=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_copy_click(this)"});
		 $(a06).html("复制新增");
		 var a07=$("<a>").attr({"href":"javascript:void(0)","OnClick":"f_delete(this)"}).css("margin-left","10px");
		 $(a07).html("删除");
		 $(a06).appendTo(td6);
		 $(a07).appendTo(td6);
		 $(td6).appendTo(hgrid_tr);
		 $("#hgrid").append(hgrid_tr);
	}
}
function endvalue_change(obj){
	var val=$(obj).val();
	var count=$(obj).parent().parent().index();//当前下标
	var len=$(".insideTable tr").length;//行数
	var td=$(obj).parent().parent().next().children("td");
	if(count+1==len){//当前行已经是最后一行
		return;
	}else{
		td.children("input[name=startvalue]").val(val);
	}
}
function input_onfocus(obj, num){
	if(num==2){
		$(obj).css("border","1px solid  #a0cfe0");
	}else if(num==1){
		$(obj).css("border","1px solid  #a0cfe0");
		$(obj).parent().parent().children("td").children("input[type=text]").css("border","1px solid  #a0cfe0");
		$(obj).parent().parent().children("td").children("select").css("border","1px solid  #a0cfe0");
	}
}
function input_onblur(obj, num){
	if(num==2){
		var tr=$(obj).parent().parent().parent().parent().parent().parent();
		var count =$(tr).index();
		var inputset=$(tr).children("td").children('input[type=text]');
		var insideTr=$(tr).children("td").children("table").children("tbody").children("tr").children("td").children('input[type=text]');
		var insideSel=$(tr).children("td").children("select");
		var fromaddress,toaddress,billtype,insidetable=new Array(),settlesetid;
		settlesetid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
		if(undefined==settlesetid){
			settlesetid='';
		}
		//********复制新增，获取当前输入内容，并记录当前输入内容开始*********//*
		$.each(inputset,function(index,item){
			switch(index){
			case 0:
				fromaddress=$(item).val();
				$(item).defaultvalue=fromaddress;//
				break;
			case 1:
				toaddress=$(item).val();
				$(item).defaultvalue=toaddress;//
				break;
			}
		});
		$.each(insideSel,function(index,item){
			billtype=$(item).val();
		});
		$.each(insideTr,function(index,item){
			insidetable[index]=$(item).val();
			if(!testNum(insidetable[index], index)){
				$(item).val('');
			}
		});
		for(var i=0;i<insidetable.length;i=i+3){
			if((fromaddress!=""&&toaddress!=""&&insidetable[i]!=""&&insidetable[i+1]!=""&&insidetable[i+2]!="")||
					((fromaddress!=""&&toaddress!=""&&insidetable[i]!=""&&insidetable[i+2]!=""&&i+1==insidetable.length-2))){
				var params="settlesetid="+settlesetid+"&topartyid="+$("#topartyid").attr("myid")+"&type="+$("#type_org").val()+"&fromaddress="+fromaddress+
				"&toaddress="+toaddress+"&billtype="+billtype+"&startvalue="+insidetable[i]+"&endvalue="+insidetable[i+1]+"&unitprice="+insidetable[i+2]+
				"&random="+Math.random();
				checkIsExist_jt(params);
			}else{
				flag_copy=false;
			}
		}
	}
	$(obj).css("border","1px solid rgb(214, 214, 214)");
}
function testNum(num, index){
	if(index%3!=2){
		return myNumberictow(num, 0);
	}else{
		return myNumberictow(num, 3);
	}
}
/***
 * 检测该条记录是否已存在
 * @param params（该条记录数据）
 * @return true or false;
 * 
 */
function checkIsExist_jt(params){
	$.ajax({
		url:"../settlecs/isExist_jt",
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
}
function table_add(obj){
	var row=$(obj).parent().parent().parent().parent().parent().parent().index();
	var count=$(obj).parent().parent().index();
	var len=$(obj).parent().parent().parent().children("tr").length;
	if(count+1!=len){
		return;
	}
	if(clickArr[row][count]==1){//1表示被点击过
		return;
	}
	var startvalue=$(obj).parent().prev().prev().prev().children("input[name=startvalue]").val();
	var end=$(obj).parent().prev().children("input[name=endvalue]");
	var endvalue=$(end).val();
	var unitprice=$(obj).parent().next().next().children("input[name=unitprice]").val();
	var validate=/^(0|[1-9]\d*)$/;//验证数字
	if(!validate.test(startvalue)){
		ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行新增'});
		return;
	}
	if(!validate.test(endvalue)&&endvalue.trim()!=''){
		$(end).val('');
		return;
	}
	if(endvalue.trim()==''){
		ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行新增'});
		return;
	}
	if(parseInt(startvalue)>=parseInt(endvalue)){
		ymPrompt.alert({"title":"提示","message":'第二个值必须大于第一个值'});
		return;
	}
	if(!myNumberictow(unitprice,3)||unitprice==''||unitprice.trim()==''){
		$(obj).next().next().val('');
		ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行新增'});
		return;
	}
	var tr=$(obj).parent().parent();
	$(tr).after($(tr).clone(true));
	$.each($($(obj).parent().parent().parent().children("tr")),function(index,item){
		if(count+1==index){
			$(item).children('td').children('input[name=settlestepsetid]').attr({"id":"settlestepsetid"+row+"_"+(index+1),"value":""});
			$(item).children('td').children('input[name=startvalue]').attr({"id":"startvalue"+row+"_"+(index+1),"value":endvalue});
			$(item).children('td').children('input[name=endvalue]').attr({"id":"endvalue"+row+"_"+(index+1),"value":""});
			$(item).children('td').children('input[name=unitprice]').attr({"id":"unitprice"+row+"_"+(index+1),"value":""});
			$(item).children('td').children('a.deleteBtn').css({"display":"block"});//显示删除按钮
		}
	});
	clickArr[row][count]=1;
}
function f_copy_click(obj){
	$(".onfocusHtml").css('border', '1px solid rgb(214, 214, 214)');
	var tr=$(obj).parent().parent();
	var count =$(obj).parent().parent().index();
	var inputset=$(obj).parent().parent().children("td").children('input[type=text]');
	var selectset=$(obj).parent().parent().children("td").children('select');
	var insideset=$(obj).parent().prev().children("table").children("tbody").children("tr").children("td").children('input');
	var topartyid;
	var fromaddress,toaddress,billtype,insidetable=new Array(),settlesetid;
	settlesetid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
	// ********复制新增，获取当前输入内容，并记录当前输入内容开始*********//*
	$.each(inputset,function(index,item){
		switch(index){
		case 0:
			fromaddress=$(item).val();
			$(item).defaultvalue=fromaddress;//
			break;
		case 1:
			toaddress=$(item).val();
			break;
		}
	});
	$.each(selectset,function(index,item){
		billtype=$(item).val();
	});
	$.each(insideset,function(index,item){
		insidetable[index]=$(item).val();
	});
	// ********复制新增，获取当前输入内容，并记录当前输入内容结束*********//*
	if(fromaddress==""){
		$(obj).parent().parent().children("td").children('input[name=fromaddress]').css("border","1px solid red");
		ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行复制新增'});
		return false;
	}
	if(toaddress==""){
		$(obj).parent().parent().children("td").children('input[name=toaddress]').css("border","1px solid red");
		ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行复制新增'});
		return false;
	}
	if(insidetable.length==0){
		return false;
	}
	for(var i=0;i<insidetable.length;i++){
		if((i%4==1||i%4==3)&&insidetable[i]==""){
			$(insideset[i]).css("border","1px solid red");
			ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行复制新增'});
			return false;
		}
		if(i%4==2){
			if(insidetable[i]==""&&i+4<$(insideset).length){// 如果不是最后一行，endvalue需不为空
				$(insideset[i]).css("border","1px solid red");
				ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行复制新增'});
				return false;
			}
			if(parseInt(insidetable[i])<=parseInt(insidetable[i-1])){//第二个值小于等于第一个值
				$(insideset[i-1]).css("border","1px solid red");
				$(insideset[i]).css("border","1px solid red");
				ymPrompt.alert({"title":"提示","message":'请先填写完本条信息再进行复制新增'});
				return false;
			}
		}
	}
	
	var settlestepsetid='',startvalue='',endvalue='',unitprice='',step='';
	for(var i=0;i<insidetable.length;i=i+4){
		settlestepsetid=settlestepsetid+insidetable[i]+" ,";
		startvalue=startvalue+insidetable[i+1]+",";
		endvalue=endvalue+insidetable[i+2]+" ,";
		unitprice=unitprice+insidetable[i+3]+",";
		step=step+(i/4+1)+",";
	}
	settlestepsetid=settlestepsetid.substring(0,settlestepsetid.length-1);
	startvalue=startvalue.substring(0,startvalue.length-1);
	endvalue=endvalue.substring(0,endvalue.length-1);
	unitprice=unitprice.substring(0,unitprice.length-1);
	step=step.substring(0,step.length-1);
	var params="settlesetid="+settlesetid+"&settlestepsetid="+settlestepsetid+"&topartyid="+$("#topartyid").attr("myid")
	+"&fromaddress="+fromaddress+"&toaddress="+toaddress
	+"&billtype="+billtype+"&startvalue="+startvalue+"&endvalue="+endvalue+"&unitprice="+unitprice+"&step="+step+
	"&topartyid="+$("#topartyid").attr("myid")+"&type="+$("#type_org").val()+"&frompartyid="+$("#frompartyid").val()+
	"&reporttype="+$("#reporttype").text()+"&random="+Math.random();
	if(settlesetid==''){
		checkIsExist_jt(params);
		if(flag_copy==false){
			$(obj).parent().parent().children("td").children("input[type=text]").css('border', '1px solid red');
			$(obj).parent().parent().children("td").children("select").css('border', '1px solid red');
		}
	}else{
		flag_copy=true;
	}
	if(flag_copy){
		// ********复制新增，将当前记录提交后台服务开始*********//*
		$.ajax({
			url:"../settlecs/insert_jt",
			data:params,
			type:'post',	
		 	dataType:'json', 
		   	success:function(data){
				if(data.msg=="ok"){
					var tr=$(obj).parent().parent();
					$(tr).after($(tr).clone(true));
					var in_settlestep=$(tr).next().children("td").children("table").children("tbody").children("tr").children("td").children("input[name=settlestepsetid]");
					$(in_settlestep).val('');
					var sid=$(obj).parent().parent().children("td").children('input[name=settlesetid]').val();
					if(sid!=''){
						$(obj).parent().parent().next().children("td").children('input[name=settlesetid]').val('');
					}else{
						$(obj).parent().parent().children("td").children('input[name=settlesetid]').val(data.id);
					}
//					$("input.settlesetid").val(data.id);
					$(obj).removeAttr("OnClick");
					// ********复制新增，将当前记录提交后台服务结束 返回结果，新生成一行*********//*
					$.each($(tr).parent().children("tr"),function(index,item){
						if(count<index){
							$(item).children('td').children('input[name=settlesetid]').attr("id","settlesetid"+(count+1));
							$(item).children('td').children('input[name=fromaddress]').attr("id","fromaddress"+(count+1));
							$(item).children('td').children('input[name=toaddress]').attr("id","toaddress"+(count+1));
							$(item).children('td').children('select[name=billtype]').attr("id","billtype"+(count+1));
							var insideTr=$(item).children('td').children("table").children("tbody").children("tr");
							$.each(insideTr, function(insideIndex,insideItem){
								if(data.settlestepsetid!='update'){
									var in_set=$(tr).children("td").children("table").children("tbody").children("tr").children("td").children("input[name=settlestepsetid]");
									$(in_set[insideIndex]).val(data.settlestepsetid.split(",")[insideIndex]);
									$(insideItem).children('td').children('input[name=settlestepsetid]').attr({"id":"settlestepsetid"+(count+1)+"_"+(insideIndex+1)});
								}else{
									$(insideItem).children('td').children('input[name=settlestepsetid]').attr({"id":"settlestepsetid"+(count+1)+"_"+(insideIndex+1)});
								}
								$(insideItem).children('td').children('input[name=startvalue]').attr({"id":"startvalue"+(count+1)+"_"+(insideIndex+1)});
								$(insideItem).children('td').children('input[name=endvalue]').attr({"id":"endvalue"+(count+1)+"_"+(insideIndex+1)});
								$(insideItem).children('td').children('input[name=unitprice]').attr({"id":"unitprice"+(count+1)+"_"+(insideIndex+1)});
							});
						}
					});	 
				}else{
					return;
				}
			}
		});
	}
	
}
function table_delete(obj){
	var tr=$(obj).parent().parent();
	var row=$(tr).parent().parent().parent().parent().index();
	var index_current=$(tr).index();
	var settlestepsetid=$(obj).parent().prev().prev().prev().prev().children("input[name=settlestepsetid]").val();
	var trs=$(tr).parent().children("tr");
	for(var i=index_current;i<trs.length;i++){
		var in_set=$(trs[i]).children("td").children("input[name=settlestepsetid]").val();
		if(in_set==''){
			deleteFlag=deleteFlag+" ,";
		}else{
			deleteFlag=deleteFlag+in_set+",";
		}
	}
	removeObj(obj,index_current,row);
}

function removeObj(obj,index_current,row){
	var tr=$(obj).parent().parent().parent().children("tr");
	$.each(tr,function(index,item){
		if(index+1>tr.length){
			return;
		}
		if(index>=index_current){
			$(item).remove();
			clickArr[row][index-1]=0;
		}
	});
}
function f_delete(obj){
	var index_current =$(obj).parent().parent().index();
	var settlesetid=$(obj).parent().parent().children("td").children("input[name=settlesetid]").val();
	$.each($("#hgrid tr"),function(index,item){
		if(index_current<index){
			$(item).children('td').children('input[name=startvalue]').attr({"id":"startvalue"+(index-1)});
			$(item).children('td').children('input[name=endvalue]').attr({"id":"endvalue"+(index-1)});
			$(item).children('td').children('input[name=unitprice]').attr({"id":"unitprice"+(index-1)});
		}
	});	
	if(settlesetid!=""){
		$.ajax({
			url: "../settlecs/delete_jt?random="+Math.random(),
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
	for(var i=0;i<clickArr[index_current].length;i++){
		clickArr[index_current][i]=0;
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
	 var table_tr=$("#hgrid").children("tbody").children("tr:gt(0)");
	 var table_obj=$(table_tr).children("td").children("input[type=text]");
	 var checkIsEmptyFlag=checkIsEmpty(table_obj, table_tr);
	 var checkRepeatFlag=checkRepeat(table_tr);
	 var checkInsideFlag=checkInside(checkIsEmptyFlag, table_tr);
	 if(checkIsEmptyFlag==2&&checkRepeatFlag&&checkInsideFlag==2){
		 if(flag_copy){
			 $("#save").attr("disabled","disabled");
				$.ajax({
					url: "../settlecs/insertSettleSet_jt", 
				 	type:'post',	
				 	async: false,
				 	dataType:'json',
				 	data:{"jsonText":jsonText,"frompartyid":$("#frompartyid").val()
					,"topartyid":$("#topartyid").attr("myid"),"reporttype":$("#reporttype").text(),"type":$("#type_org").val()}, //参数     	               
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
				$.ajax({
					url: "../settlecs/save_delete", 
				 	type:'post',	
				 	async: false,
				 	dataType:'json',
				 	data:{"deleteFlag":deleteFlag.substring(0, deleteFlag.length-1)}, //参数     	               
				   	success:function(data){//回传函数
				 		deleteFlag='';
					}
				});
		 }
	 }
	 if(checkIsEmptyFlag==0){
		 ymPrompt.alert({"title":"提示","message":"填写必要信息"});
	 }
	 if(!checkRepeatFlag){
		 ymPrompt.alert({"title":"提示","message":"有重复记录出现"});
	 }
	 if(checkInsideFlag==1){
		 ymPrompt.alert({"title":"提示","message":"第二个值必须大于第一个值"});
	 }
	 if(checkInsideFlag==0){
		 ymPrompt.alert({"title":"提示","message":"填写必要信息"});
	 }
}


/***
 * 检测是否有空值
 * @author yaoyan.lin
 * @param object
 * @return 0,1,2   |   0表示发货地/收货地址未填，2表示填写正确
 */
function checkIsEmpty(t_obj, t_tr){
	if(t_tr.length<1){
		return 0;
	}
	for(var i=0;i<t_tr.length;i++){
		for(var j=0;j<2;j++){
			var obj_len=2*i+j;
			if($(t_obj[obj_len]).val()==""){
				$(t_obj[obj_len]).css("border","1px solid red");
				ymPrompt.alert({"title":"提示","message":'请先填写完信息再进行保存'});
				return 0;
			}
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
	$(".onfocusHtml").css('border', '1px solid rgb(214, 214, 214)');
	var re_data=true;
	var fromaddress,toaddress,fromaddress1,toaddress1,billtype,billtype1;
	var obj_length = $(object).length;
	for(var i=0;i<obj_length;i++){
		fromaddress=$($("input[name=fromaddress]")[i]).val();
		toaddress=$($("input[name=toaddress]")[i]).val();
		billtype=$($("select[name=billtype]")[i]).val();
		if(i<obj_length){
		for(var j=i+1;j<obj_length;j++){
			fromaddress1=$($("input[name=fromaddress]")[j]).val();
			toaddress1=$($("input[name=toaddress]")[j]).val();
			billtype1=$($("select[name=billtype]")[j]).val();
			if(fromaddress==fromaddress1&&toaddress==toaddress1&&billtype==billtype1){
				$($("input[name=fromaddress]")[j]).css("border","1px solid red");
				$($("input[name=toaddress]")[j]).css("border","1px solid red");
				$($("select[name=billtype]")[j]).css("border","1px solid red");
				ymPrompt.alert({"title":"提示","message":'请先填写完信息再进行保存'});
				re_data=false;
				continue;
				}
			}
		}
	}
	
	var params="topartyid="+$("#topartyid").attr("myid")
	+"&fromaddress="+fromaddress+"&toaddress="+toaddress
	+"&billtype="+billtype+"&type="+$("#type_org").val()+
	"&random="+Math.random();
	var settlesetid=$(object).children("td").children('input[name=settlesetid]').val();
	if(undefined==settlesetid){
		settlesetid='';
	}
	if(settlesetid==''){
		checkIsExist_jt(params);
		if(flag_copy==false){
			$(object).children("td").children("input[type=text]").css('border', '1px solid red');
			$(object).children("td").children("select").css('border', '1px solid red');
			return false;
		}
	}else{
		flag_copy=true;
	}
	
	return re_data;
}
/**
 * 判断内table中input的值（是否为空，第二个值是否大于第一个值）
 * @param inside_input
 * @return 0表示有空值|1表示第二个值小于等于第一个值|2表示正确
 */
function checkInside(checkIsEmptyFlag, table_tr){
	var flag=0;
	if(checkIsEmptyFlag==0){
		return 0;
	}
	var inside_table=$(table_tr).children("td").children("table");
	for(var i=0;i<inside_table.length;i++){
		var inside_input=$(inside_table[i]).children("tbody").children("tr").children("td").children("input[type=text]");
		for(var j=0;j<inside_input.length;j++){
			if(j%3!=1&&$(inside_input[j]).val()==""){
				$(inside_input[j]).css("border","1px solid red");
				ymPrompt.alert({"title":"提示","message":'请先填写完信息再进行保存'});
				flag=0;
				return;
			}
			if(j%3==1){
				if($(inside_input[j]).val()==""&&j+3<inside_input.length){// 如果不是最后一行，endvalue需不为空
					$(inside_input[j]).css("border","1px solid red");
					ymPrompt.alert({"title":"提示","message":'请先填写完信息再进行保存'});
					flag=0;
					return;
				}
				if(parseInt($(inside_input[j]).val())<=parseInt($(inside_input[j-1]).val())){//第二个值小于等于第一个值
					$(inside_input[j-1]).css("border","1px solid red");
					$(inside_input[j]).css("border","1px solid red");
					ymPrompt.alert({"title":"提示","message":'请先填写完信息再进行保存'});
					flag=1;
					return;
				}
			}
			if(j+1==$(inside_input[j]).length){
				flag=2;
			}
		}
	}
	return flag;
}
function createArray(){
    var jsonarray=[];
    var $settlesetid=$(".settlesetid");
    var $fromaddress=$("input[name=fromaddress]");
    var $toaddress=$("input[name=toaddress]");
    var $billtype=$("select[name=billtype]");
    $.each($(".settlesetid"),function(n,item){
        var settlesetid=$($settlesetid[n]).val();
        var fromaddress=$($fromaddress[n]).val();   
        var toaddress=$($toaddress[n]).val();
        var billtype=$($billtype[n]).val();
        var settlestepsetid='',startvalue='',endvalue='',unitprice='',step='';
        var $insideTr=$(item).parent().next().next().next().children("table").children("tbody").children("tr");
        $.each($insideTr,function(n,insideItem){
        	var settlestep=$(insideItem).children("td").children("input[name=settlestepsetid]").val();
        	if(settlestep!=null&&settlestep!=''){
        		settlestepsetid=settlestepsetid+settlestep+",";
        	}else{
        		settlestepsetid=settlestepsetid+" ,";
        	}
        	startvalue=startvalue+$(insideItem).children("td").children("input[name=startvalue]").val()+",";
        	var end=$(insideItem).children("td").children("input[name=endvalue]").val();
        	if(end!=null&&end!=''){
        		endvalue=endvalue+end+",";
        	}else{
        		endvalue=endvalue+" ,";
        	}
    		unitprice=unitprice+$(insideItem).children("td").children("input[name=unitprice]").val()+",";
    		step=step+(n+1)+",";
        });
        settlestepsetid=settlestepsetid.substring(0,settlestepsetid.length-1);
    	startvalue=startvalue.substring(0,startvalue.length-1);
    	endvalue=endvalue.substring(0,endvalue.length-1);
    	unitprice=unitprice.substring(0,unitprice.length-1);
    	step=step.substring(0,step.length-1);
        var obj=createObj(settlestepsetid,settlesetid,fromaddress,toaddress,billtype,startvalue,endvalue,unitprice,step);
        jsonarray.push(obj);
        settlestepsetid='';
        startvalue='';
        endvalue='';
        unitprice='';
        step='';
    })
    return jsonarray;
}
function createObj(settlestepsetid,settlesetid,fromaddress,toaddress,billtype,startvalue,endvalue,unitprice,step){
	return {
		settlestepsetid:settlestepsetid,
		settlesetid:settlesetid,
		fromaddress:fromaddress,
		billtype:billtype,
    	toaddress:toaddress,
    	startvalue:startvalue,
    	endvalue:endvalue,
    	unitprice:unitprice,
    	step:step
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

