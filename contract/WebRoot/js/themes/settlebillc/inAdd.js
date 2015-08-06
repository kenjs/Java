	function add(){
		var consignor=$("#consignor").attr("myid");
		if(consignor!=""&&$("#consignor").val()!=""){
			ymPrompt.win({message:'../settlebillcs/inAddWayBill?frompartyid='+consignor+"&type=1",width:820,height:500,fixPosition:true,dragOut:false,title:'添加托运单',iframe:true});
		}else{
			ymPrompt.alert({title:"提示",message:"请填写发货方"});
			return false;
		}
		}
	var settleBillId;
	function addedList(settleBillId1){
		settleBillId=settleBillId1;
		$.ajax({
			url: "../settlebillcs/selectAddedList",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{"settlebillid":settleBillId,"inorout":"1"}, //参数     	               
		   	success:function(data){//回传函数
		 		elements(data,settleBillId);
		 		$("#settledate").html(startdate+"~"+enddate);
		 		$("#needinallamount").html(parseFloat(sumamount).toFixed(2));
		}
		});
	}
	var startdate,enddate,sumamount=0;
	var elements=function createElements(data,settleBillId){
		 $("#hgrid1").empty();
		 sumamount=0;
		for(var i=0;i<data.length;i++){
			if(i==0){
				startdate=data[i].consigndate;
			}
			if(i==data.length-1){
				enddate=data[i].consigndate;
			}
			sumamount+=parseFloat(data[i].amount);
			var tr=$("<tr></tr>");
			var td1=$("<td></td>").attr("class","waybillnumber").css({"width":"100px","padding-left":"10px"});
			var td2=$("<td></td>").text(data[i].consigndate).attr("class","consigndate").css("width","100px");
			var td3=$("<td></td>").text(data[i].clientnumber).attr("class","clientnumber").css("width","80px");
			var td4=$("<td></td>").text(data[i].goodsname).attr("class","goodsname").css("width","80px");
			var td5=$("<td></td>").text(data[i].num).attr("class","num").css("width","80px");
			var td6=$("<td></td>").text(data[i].weight).attr("class","weight").css("width","80px");
			var td7=$("<td></td>").text(data[i].volume).attr("class","volume").css("width","80px");
			var td8=$("<td></td>").text(data[i].amount).attr("class","amount").css("width","100px");
			var td9=$("<td></td>").attr("class","action").css("width","80px");
			var a=$("<a></a>").attr({"onclick":'deleteWayBillOfSettleBill(this,'+data[i].waybillid+')'}).text("删除");
			var input=$("<input/>").attr({"type":"hidden","value":data[i].waybillid,"class":"waybillid"});
			var a1=$("<a></a>").attr({"href":'../waybillcs/waybill_detail?order=2&waybillid='+data[i].waybillid,"target":"_blank"}).text(data[i].waybillnumber);
			$(a1).appendTo(td1);
			$(td1).appendTo(tr);
			$(input).appendTo(td1);
			$(td2).appendTo(tr);
			$(td3).appendTo(tr);
			$(td4).appendTo(tr);
			$(td5).appendTo(tr);
			$(td6).appendTo(tr);
			$(td7).appendTo(tr);
			$(td8).appendTo(tr);
			$(a).appendTo(td9);
			$(td9).appendTo(tr);
			 $("#hgrid1").append(tr);
		}
	};
	function deleteWayBillOfSettleBill(obj,waybillId){
		var sumamount1=0;
		ymPrompt.confirmInfo({message:'确定删除?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
			function(status){
			if(status=='ok'){
				$(obj).parent().parent().remove();
				var length1=$("#hgrid1").find("tr").length;
				var operator="";
				if(length1=="0"){
					 operator="delSettleBill";
				}
				$.ajax({
					url: "../settlebillcs/deleteWayBillOfSettleBill",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:{"settlebillid":settleBillId,"waybillid":waybillId,"action":operator,"inorout":"1"}, //参数     	               
				   	success:function(data){//回传函数
				 		var consigndate1=$("#hgrid1 .consigndate");
						var length=$("#hgrid1 .consigndate").length;
			 			var amount1=$("#hgrid1 .amount");
						var startdate2=$(consigndate1[0]).html();
						var enddate2=$(consigndate1[length-1]).html();
				 		if(length1==0){
				 			window.location=("../settlebillcs/inList?order=7");
				 		}else{
							$("#settledate").html(startdate2+"~"+enddate2);
							for(var i=0;i<length;i++){
								sumamount1+=$(amount1[i]).html();
							}
					 		$("#needinallamount").html(parseFloat(sumamount1).toFixed(2));
				 		}
				}
				});
			}
		}
		});
	}
