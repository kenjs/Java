function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}
function check(obj){
	if($(obj).attr("checked")=="checked"){
		$("#hgrid").find("input[name=checked]").attr("checked","checked");
	}
	if($(obj).is(":checked")==false){
		$('#hgrid tr input[name=checked]').removeAttr("checked");
	}
}
function wayBillList(){
	var fromaddress=$.trim($("#fromaddress").val());
	var consignorprovince='', consignorcity='', consignorregion='';
	if(fromaddress!=''){
		var fromaddressArr=fromaddress.split("-");
		consignorprovince=fromaddressArr[0];
		if(fromaddress.length>=2){
			consignorcity=fromaddressArr[1];
		}
		if(fromaddress.length>=3){
			consignorregion=fromaddressArr[2];
		}
	}
	
	var toaddress=$.trim($("#toaddress").val());
	var consigneeprovince='', consigneecity='', consigneeregion='';
	if(toaddress!=''){
		var toaddressArr=toaddress.split("-");
		consigneeprovince=toaddressArr[0];
		if(toaddress.length>=2){
			consigneecity=toaddressArr[1];
		}
		if(toaddress.length>=3){
			consigneeregion=toaddressArr[2];
		}
	}
	var params=getId()+"&waybillnumber="+$.trim($("#waybillnumber").val())+"&fromdate="+$("#fromdate").val()
						+"&todate="+$("#todate").val()+"&clientnumber="+$.trim($("#clientnumber").val())
						+"&consignorprovince="+consignorprovince+"&consignorcity="+consignorcity+"&consignorregion="+consignorregion
						+"&consigneeprovince="+consigneeprovince+"&consigneecity="+consigneecity+"&consigneeregion="+consigneeregion
						+"&inorout=0"+"&partytype=0";
	$.ajax({
		url:"../settlebillcs/addList?random="+Math.random(),
		data:params,
	 	type:'post',	
	 	dataType:'json', 
	   	success:function(data){
		elements(data);
		
		}
	});
}
function search(){
	wayBillList();
}
$(function(){
	wayBillList();
});
var elements=function createElements(data){
	 $("#hgrid").empty();
	if(data.length>0){
		for(var i=0;i<data.length;i++){
			var tr=$("<tr></tr>");
			var td1=$("<td></td>").attr("class","checkstatus").css("width","40px");
			var td2=$("<td></td>").text(data[i].waybillnumber).attr("class","waybillnumber").css("width","100px");
			var td3=$("<td></td>").text(data[i].consigndate).attr("class","inputdate").css("width","70px");
			var td4=$("<td></td>").text(data[i].organization).attr("class","consignor").css("width","150px");
			var td5=$("<td></td>").text(data[i].fromaddress).attr("class","fromaddress").css("width","115px");
			var td6=$("<td></td>").text(data[i].toaddress).attr("class","toaddress").css("width","115px");
			var td7=$("<td></td>").text(data[i].clientnumber).attr("class","clientcode").css("width","100px");
			var td8=$("<td></td>").text(data[i].amount).attr("class","inoroutamount").css("width","100px");
			var input=$("<input/>").attr({"type":"checkbox","value":data[i].waybillid,"name":"checked"});
			$(input).appendTo(td1);
			$(td1).appendTo(tr);
			$(td2).appendTo(tr);
			$(td3).appendTo(tr);
			$(td4).appendTo(tr);
			$(td5).appendTo(tr);
			$(td6).appendTo(tr);
			$(td7).appendTo(tr);
			$(td8).appendTo(tr);
			 $("#hgrid").append(tr);
		}
	}else{
		var tr=$("<tr></tr>");
		var div=$("<div></div>").css({"width":"80px","margin-left":"350px","margin-top":"100px"}); 
		var img=$("<img/>").attr("src","../imgs/sys/notice-icon.png");
		$(div).append(img);
		var span=$("<span></span>").css("font-weight","bold").text("暂无数据");
		$(div).append(span);
		$(tr).append(div);
		 $("#hgrid").append(tr);
		 $("#save").removeAttr("type").attr("type","hidden");
	}
	
};
function save(object){
	var type=getId().split("&")[1].split("=")[1];
	var check=$("#hgrid input[name=checked]");
	var ary=new Array();
	for(var i=0;i<check.length;i++){
		if($(check[i]).is(":checked")){
			ary.push({"waybillid":$(check[i]).val()});
		}
	}
	if(ary.length==0){
		alert("请选择相关运单");
		return false;
	}
	$(object).attr("disabled","disabled");
	var jsonList = eval(ary); 
	 var jsonText = JSON.stringify(jsonList);
	 if(type=="1"){
		$.ajax({
			url:"../settlebillcs/insert?"+getId()+"&random="+Math.random(),
			data:{"waybillids":jsonText,"inorout":"0"},
		 	type:'post',	
		 	dataType:'json', 
		   	success:function(data){
				if(data.msg=="ok"){
					window.parent.ymPrompt.doHandler('close',true);
					window.parent.addedList(data.id);
					
				}else{
					alert("操作失败");
					$(object).removeAttr("disabled");
				}
			}
		});
	 }
	 if(type=="0"){
		 	var settlebillid=getId().split("&")[2].split("=")[1];
			$.ajax({
				url:"../settlebillcs/update?"+getId()+"&random="+Math.random(),
				data:{"waybillids":jsonText,"inorout":"0","settlebillid":settlebillid},
			 	type:'post',	
			 	dataType:'json', 
			   	success:function(data){
					if(data.msg=="ok"){
						window.parent.ymPrompt.doHandler('close',true);
						window.parent.addedList(settlebillid);
						
					}else{
						alert("操作失败");
						$(object).removeAttr("disabled");
					}
				}
			});
	 }
}