/**
 * wei.huang
 * 2013-10-14
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
 * wei.huang
 * 2013-10-14
 * 将待修改的内容填充到文本框中
 */
function fillText(){
	var data=getData();
	$("#consigneeconsignoraddressid").val(data[0]);
	$("#linkman").val(data[1]);
	$("#telephone").val(data[2]);
	$("#mobile").val(data[3]);
	$("#address").val(data[4]);
	$("#detailAddress").val(data[5]);
	$("#default").attr("checked",true);
	if(data[6]=="0"){
		$("#default").attr("checked",false);
	}
	else if(data[6]=="1"){
		$("#default").attr("checked",true);
	}
	$("#consignee").val(data[7]);
	$("#frompartyid").val(data[8]);
	$("#topartyid").val(data[9]);
	$("#helpCode").val(data[10]);
}
/**
 * @author wei.huang
 * @since 2013-10-14
 * @function 验证输入框信息的完整性
 * @return 完整:true;不完整:false
 */
function checkInput(){
	var consignee=true;
	if($("#consignee").val()==""){
		consignee=false;
	}
	var helpcode=true;
	if($("#helpCode").val()==""){
		helpcode=false;
	}
	var linkman=true;
	if($("#linkman").val()==""){
		linkman=false;
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
	if(consignee&&helpcode&&linkman&&call&&address&&detailaddress){
		$("#tips").hide();
		if($("#consignee").val().length>60){
			$("#tips").html("收货方名称最多只能填写60个字！");
			$("#tips").show();
			return false;
		}
		$("#tips").hide();
		if($("#linkman").val().length>20){
			$("#tips").html("收货联系人最多只能填写20个字！");
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
 * 2013-10-14
 * 修改收货地址
 * @return
 */
function consigneeAddressEdit(){
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
	var address=new Array("","","");
	var splitaddress=$("#address").val().split("-");
	for(var i=0;i<splitaddress.length;i++){
		address[i]=splitaddress[i];
	}
	var param="&consigneeconsignoraddressid="+$("#consigneeconsignoraddressid").val()+"&consignee="+$("#consignee").val()+"&helpcode="+$("#helpCode").val()+"&linkman="+$("#linkman").val()+"&mobilenumber="+$("#mobile").val()
	+"&telephonenumber="+$("#telephone").val()+"&province="+address[0]+"&city="+address[1]
	+"&region="+address[2]+"&town="+$("#detailAddress").val()+"&checked="+check;
	if(check=="1"){
		var param2="&consigneeorconsignor=1&checked=0&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val();
		$.ajax({//修改已有记录的checked值
			url:"../consigneeconsignoraddresscs/updateChecked",
			type:"post",
			dataType:"json",
			data:param2,
			success:function(data2){
			if(data2.msg=="ok"){
				$.ajax({//修改收货地址
					url: "../consigneeconsignoraddresscs/update",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:param, //参数     	               
				   	success:function(data3){
					if(data3.msg=="ok"){
						window.parent.ymPrompt.doHandler("2,保存,修改",true);
					}
				}
				})
			}
		}
		})
	}else{
		$.ajax({//修改发货地址
			url: "../consigneeconsignoraddresscs/update",
		 	type:'post',
		 	dataType:'json', 
		 	data:param, //参数     	               
		   	success:function(data3){
			if(data3.msg=="ok"){
				window.parent.ymPrompt.doHandler("2,保存,修改",true);
			}
		}
		})
	}
}

function cancel(){
	window.parent.ymPrompt.doHandler("2,取消",true);
}