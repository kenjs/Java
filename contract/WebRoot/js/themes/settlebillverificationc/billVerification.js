function myDateToString(isfull){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	var mydate=year+"-"+month+"-"+day;
	if(isfull){
		mydate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	}
	return mydate;
}
function getData(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("?");
	url_Data=decodeURI(url_Data.substr(num+1));
	url_Data= url_Data.split("&");
	var data=new Array();
	$.each(url_Data,function(key,val){
		data[key]=val.split("=")[1];
	});
	return data;
 }
//获取地址中的参数，第二个是settlebillid,第三个是settlebillnumber,第四个是organization
//第五个是status,第六个是needinoutallamount,第七个是factamount,第八个是needinoutremainamount
//第九个是inorout,第十个是结算期
var urlData=getData();
function fillText(){
	$("#settlebillid").html(urlData[1]);
	$("#settlebillnumber").html(urlData[2]);
	$("#organization").html(urlData[3]);
	$("#status").html(urlData[4]);
	$("#needinoutallamount").html(urlData[5]);
	$("#factamount").html(urlData[6]);
	$("#needinoutremainamount").html(urlData[7]);
}
function f_onmouseover(obj){
	$(obj).css("background-color","#fffddd");
}
function f_onmouseout(obj){
	$(obj).css("background-color","white");
}
var rowNum=0;
var date=myDateToString(false);
function insertRow(){
	var txt='<tr id="tr'+rowNum+'" style="height:36px" onmouseover="f_onmouseover(this)" onmouseout="f_onmouseout(this)">'+
				'<td class="td0"><span style="width:50px">'+(rowNum+1)+'</span></td>'+
				'<td class="td1"><input type="text" maxlength="20" style="width:160px"></td>'+
				'<td class="td2"><input type="text" maxlength="20" style="width:160px" onblur="checkNumber(this)"></td>'+
				'<td class="td3"><input type="text" style="width:145px" onblur="checkAmount(this)"></td>'+
				'<td class="td4"><input type="text" style="width:95px"></td>'+
				'<td class="td5"><input type="text" class="Wdate w80" style="width:95px" onFocus="WdatePicker({firstDayOfWeek:1})"></td>'+
				'<td class="td6"><a href="javascript:void(0)" onclick="deleteRow(this)" style="width:50px">删除</a></td>'+
			'</tr>';
	$("#list_body").append(txt);
	$("#tr"+rowNum+" .td5 input").val(date);
	rowNum++;
}
function deleteRow(obj){
	var index=$(obj).parents("tr").index();
	var len=$("#list_body tr").length;
	$("#tr"+index).remove();
	$("#tr"+index+"_td2").remove();
	$("#tr"+index+"_td3").remove();
	rowNum--;
	sum();
	for(var i=index+1;i<len;i++){
		$("#tr"+i+" .td0 span").html(i);
		$("#tr"+i).attr("id","tr"+(i-1));
		$("#tr"+i+"_td2").animate({top:"-=36px"},0);
		$("#tr"+i+"_td3").animate({top:"-=36px"},0);
		$("#tr"+i+"_td2").attr("id","tr"+(i-1)+"_td2");
		$("#tr"+i+"_td3").attr("id","tr"+(i-1)+"_td3");
	}
}
/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 运单详情
 * @return
 */
function detail(){
	var data=encodeURI(urlData[1]+","+urlData[2]+","+$("#status").html()+","+urlData[9]+","+urlData[3]+","+urlData[8]);
	if(urlData[8]=="1"){
		window.open("../settlebillcs/receivableSettlebillDetail?data="+data);
	}else if(urlData[8]=="0"){
		window.open("../settlebillcs/payableSettlebillDetail?data="+data);
	}
}
/**
 * @author wei.huang
 * @date 2013-11-21
 * @function 弹出提示信息框
 * @param target 
 * @param messageId 消息框id
 * @param message 提示信息
 */
function markText(target,messageId,message){
	if($(messageId).length==0){//当信息提示div不存在时
		var top=$(target).position().top+$(target).outerHeight();
		var left=$(target).position().left;
		var width=$(target).outerWidth();
		messageId=messageId.substr(1);
		var txt='<div id="'+messageId+'" class="tips" style="max-width:'+width+'px;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("#list_body").append(txt);
	}else{//当信息提示div存在时
		$(messageId).empty().append(message);
	}
}
/**
 * @author wei.huang
 * @date 2013-12-24
 * @function 检验重复的发票号
 * @return true表示重复，反之false
 */
var repeatRow=0;//记录重复的行号
function isnRepeat_Number(row){
	var target="#tr"+row+" .td2 input";
	var billNumber=$.trim($(target).val());
	for(var i=0,len=$("#list_body tr").length;i<len;i++){
		var temp=$.trim($("#tr"+i+" .td2 input").val());
		if(temp==""||row==i||repeatRow==row+1){
			continue;
		}
		if(temp==billNumber){
			repeatRow=i+1;
			return true;
		}
	}
	repeatRow=0;
	return false;
}

/**
 * @author wei.huang
 * @date 2013-12-23
 * @function 检验发票号
 * @param row 行号
 * @param col 列号
 * @return
 */
function checkNumber(obj){
	var row=$(obj).parents("tr").index();
	var target="#tr"+row+" .td2 input";
	var messageId="#tr"+row+"_td2";
	if($.trim($(target).val())==""){
		markText(target,messageId,"发票号不能为空!");
		$(target).addClass("error");
	}else if(isnRepeat_Number(row)){
		markText(target,messageId,"该发票号与第"+repeatRow+"行的发票号重复!");
		$(target).addClass("error");
	}else{
		$(messageId).remove();
		$(target).removeClass("error");
	}
}

/**
 * @author wei.huang
 * @date 2013-12-23
 * @function 获取已填发票金额总和
 * @return 
 */
function sum(){
	var total=0;
	for(var i=0,len=$(".td3 input").length;i<len;i++){
		var amount=$(".td3 input").eq(i).val();
		if(!isNaN(amount)&&amount!=""){
			total+=parseFloat(amount);
		}
	}
	$("#totalamount").html(total.toFixed(2));
	return total;
}
/**
 * @author wei.huang
 * @date 2013-12-23
 * @function 检验发票金额
 * @param row 行号
 * @param col 列号
 * @return
 */
function checkAmount(obj){
	var row=$(obj).parents("tr").index();
	var target="#tr"+row+" .td3 input";
	var messageId="#tr"+row+"_td3";
	var billamount=$(target).val();
	if(billamount==""){
		markText(target,messageId,"发票金额不能为空!");
		$(target).addClass("error");
	}else if(!(/^\d{1,10}(\.\d{1,2})?$/.test(billamount))){
		markText(target,messageId,"发票金额最大只能为10位的正数,且最多只能有2位小数!");
		$(target).addClass("error");
	}else if(parseFloat(billamount)==0){
		markText(target,messageId,"您输入的发票金额不合理!");
		$(target).addClass("error");
	}else{
		$(messageId).remove();
		$(target).removeClass("error");
		sum();
		$(target).val(parseFloat(billamount).toFixed(2));
	}
}
function back(){
	var url="../settlebillcs/verificationReceivable?order=9";
	if(urlData[8]=="0"){
		url="../settlebillcs/verificationPayable?order=10";
	}
	window.location=url;
}
var isEnable;//记录是否具有保理资格
function save(obj){
	if($("#list_body tr").length==0){
		return;
	}
	if($(".error").length>0){
		return;
	}else{
//		$(".td1 input").trigger("blur");
		$(".td2 input").trigger("blur");
		$(".td3 input").trigger("blur");
		hideOrShow();
	}
	if($(".error").length>0){
		return;
	}
	var total=sum();
	var settlebillid=urlData[1];
	var remainAmount=parseFloat(urlData[7]);
	if(total>remainAmount){
		var msg="您当前的发票总金额超过了未开票总金额!";
		ymPrompt.alert(msg);
		return;
	}
	if(isEnable==true){
		if($.trim($("#planpaydate").val())==""){
			ymPrompt.alert("请设置计划付款日期！");
			return;
		}
	}
	$(obj).attr("disabled",true);
	var params="";
	//将多条发票信息一次性传到后台
	for(var i=0,len=$("#list_body tr").length;i<len;i++){
		var myParam="bill"+i+"="+settlebillid+","+$.trim($("#tr"+i+" .td1 input").val())+","+$.trim($("#tr"+i+" .td2 input").val())+","+$.trim($("#tr"+i+" .td3 input").val())+","+$("#tr"+i+" .td4 input").val()+","+$.trim($("#tr"+i+" .td5 input").val())+","+$.trim($("#planpaydate").val());
		params+=myParam;
		if(i<len-1){
			params+="&";
		}
	}
	$.ajax({
		url:"../settlebillverificationcs/insert",
		type:"post",
		dataType:"json",
		data:params,
		success:function(data){
			if(data.msg=="ok"){
				var status="部分核销";
				if(remainAmount == total){
					status="全部核销";
				}
				$.ajax({
					url:"../settlebillcs/updateAmountBySettleBillId",
					type:"post",
					dataType:"json",
					data:{"settlebillid":settlebillid,"billamount":total,"status":status},
					success:function(data){
						if(data.msg=="ok"){
							$("#status").html(status);
							$("#factamount").html((parseFloat($("#factamount").html())+total).toFixed(2));
							$("#needinoutremainamount").html((parseFloat($("#needinoutremainamount").html())-total).toFixed(2));
							ymPrompt.alert({message:'核销成功！',slideShowHide:false, title:'提示',handler:back});
						}else{
							ymPrompt.alert("核销失败！");
						}
					}
				})
			}else{
				ymPrompt.alert("操作失败！");
			}
	}
	})
}
var sDiv,divTop,divHeight;
/**
 * @authr wei.huang
 * @date 2013-12-25
 * @function 决定提示框是显示还是隐藏
 * @return
 */
function hideOrShow(){
	var tipsDiv=$(".tips");
	for(var i=0,len=tipsDiv.length;i<len;i++){
		var tip=tipsDiv.eq(i);
		var top=parseFloat(tip.css("top"));
		if(top<=divTop||top>=divTop+divHeight){
			tip.hide();
		}else{
			tip.show();
		}
	}
}
/**
 * @author wei.huang
 * @date 2014-3-5
 * @function 获取分包商的保理资格以及账期
 * @param settlebillid
 * @return
 */
function getConsigneeBusinessDays(settlebillid){
	var businessday;
	if(urlData[8]=="0"){
		$.ajax({
			async:false,
			url:"../bankfactoringcs/selectConsigneeBusinessDays",
			type:"post",
			dataType:"json",
			data:{"settlebillid":settlebillid},
			success:function(data){
				if(data.isenabled=="1"){
					businessday=parseInt(data.businessdays);
				}else{
					businessday=-1;//当没有保理资格时，账期设置为-1
				}
			}
		})
	}else{
		businessday=-1;//当没有保理资格时，账期设置为-1
	}
	return businessday;
}

/**
 * @author wei.huang
 * @date 2014-3-5
 * @function 设置计划付款日期
 * @return
 */
function setPlanPayDate(){
	var businessday=getConsigneeBusinessDays(urlData[1]);
	if(businessday!=-1){
		var time=new Date().getTime()+businessday*24*60*60*1000;
		var date=new Date();
		date.setTime(time);
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var day=date.getDate();
		$("#planpaydate").val(year+"-"+month+"-"+day);
		isEnable=true;
		$(".isShow").show();
	}else{
		$("#planpaydate").val("");
		isEnable=false;
		$(".isShow").hide();
	}
}
$(function(){
	fillText();
	setPlanPayDate();
	sDiv=$("#list_body").parent();
	var iniTop=sDiv.scrollTop();
	divTop=sDiv.position().top;
	divHeight=sDiv.outerHeight();
	sDiv.scroll(function(){
		var scrollTop = sDiv.scrollTop();
		$(".tips").animate({top:"-="+(scrollTop-iniTop)+"px"},0);
		iniTop=sDiv.scrollTop();
		hideOrShow();
	})
})