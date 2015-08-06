/**
 * wei.huang
 * 2013-10-11
 * @return 指定格式的时间
 */
function myDateToString(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}
/**
 * wei.huang
 * 2013-10-12
 * 获取URL中的参数
 */
function getData(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("?");
	url_Data=url_Data.substr(num+1);
	url_Data= url_Data.split("&");
	var data=new Array();
	$.each(url_Data,function(key,val){
		data[key]=decodeURI(val.split("=")[1]);
	});
	return data;
 }
/**
 * @author wei.huang
 * @since 2013-10-11
 * @function 验证输入框信息的完整性
 * @return 完整:true;不完整:false
 */
function checkInput(){
	var linkman=true;
	if($("#linkman").val()==""){
		linkman=false;
	}
	var helpcode=true;
	if($.trim($("#helpCode").val())==""){
		helpcode=false;
	}
	var call=true;
	if($("#telephone").val()==""&&$("#mobile").val()==""){
		call=false;
	}
	var address=true;
	if($("#address").val()==""){
		address=false;
	}
	var detailaddress=true;
	if($("#detailAddress").val()==""){
		detailaddress=false;
	}
	if(linkman&&call&&address&&detailaddress&&helpcode){
		$("#tips").hide();
		if($("#linkman").val().length>20){
			$("#tips").html("发货联系人最多只能填写20个字！");
			$("#tips").show();
			return false;
		}
		if($.trim($("#mobile").val())!=""){
			if(isNaN($("#mobile").val())||$("#mobile").val().length!=11||!/^\d{11}$/.test($("#mobile").val())){
				$("#tips").html("手机号格式不正确！");
				$("#tips").show();
				return false;
			}
		}
		if($("#detailAddress").val().length>512){
			$("#tips").html("详细地址最多只能填写512个字！");
			$("#tips").show();
			return false;
		}
		return true;
	}else{
		$("#tips").html("信息不完整，请检查！");
		$("#tips").show();
		return false;
	}
}
/**
 * wei.huang
 * 2013-10-11
 * 增加发货地址
 * @return
 */
function consignorAddressAdd(){
	if(!checkInput()){
		return;
	}
	$("#btnSave").attr("disabled",true);
	var check;
	if($("#default").attr("checked")){
		check="1";
	}else{
		check="0";
	}
	var date=myDateToString();
	var address=new Array("","","");
	var splitaddress=$("#address").val().split("-");
	for(var i=0;i<splitaddress.length;i++){
		address[i]=splitaddress[i];
	}
	var url_data=getData();
	var param="&frompartyid="+url_data[0]+"&topartyid="+url_data[3]+"&consigneeorconsignor=0&checked="+check
	+"&consignee="+"&linkman="+$("#linkman").val()+"&mobilenumber="+$("#mobile").val()+"&helpcode="+$.trim($("#helpCode").val())
	+"&telephonenumber="+$("#telephone").val()+"&province="+address[0]+"&city="+address[1]
	+"&region="+address[2]+"&town="+$("#detailAddress").val()+"&inputdate="+date+"&inputman="+url_data[1];
	if(check=="1"&&url_data[2]>0){
		var param1="&consigneeorconsignor=0&checked=0&frompartyid="+url_data[0];
		$.ajax({//修改已有记录的checked值
			url:"../consigneeconsignoraddresscs/updateChecked",
			type:"post",
			dataType:"json",
			data:param1,
			success:function(data1){
			if(data1.msg=="ok"){
				$.ajax({//添加发货地址
					url: "../consigneeconsignoraddresscs/insert",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:param, //参数     	               
				   	success:function(data2){
					if(data2.msg=="ok"){
						window.parent.ymPrompt.doHandler("1,保存,增加",true);
					}
				}
				})
			}
		}
		})
	}else{
		$.ajax({//添加发货地址
			url: "../consigneeconsignoraddresscs/insert",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:param, //参数     	               
		   	success:function(data3){
			if(data3.msg=="ok"){
				window.parent.ymPrompt.doHandler("1,保存,增加",true);
			}
		}
		})
	}
}

function cancel(){
	window.parent.ymPrompt.doHandler("1,取消",true);
}