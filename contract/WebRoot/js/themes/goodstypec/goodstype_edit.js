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
 * 2013-10-17
 * 填写输入框的内容
 */
function fillText(){
	url_data=getData();
	$("#goodstypeid").val(url_data[0]);
	$("#goodsName").val(url_data[2]);
	$("#helpCode").val(url_data[3]);
	$("#unitWeight").val(url_data[5]);
	$("#unit").val(url_data[11]);
	$("#spec").val(url_data[13]);
	$("#model").val(url_data[14]);
	$("#unitVolume").val(url_data[6]);
	if(url_data[8]=="1"){
		$("#default").attr("checked",true);
	}else{
		$("#default").attr("checked",false);
	}
	$("#frompartyid").val(url_data[9]);
	$("#topartyid").val(url_data[10]);
}
/**
 * wei.huang
 * 2013-10-16
 * 动态绑定下拉框
 * @param selectid:下拉框的id,type:字典类型
 * @return
 */
function bindSelect(selectid,type){
	url_data=getData();
	var param="&type="+type;
	$.ajax({
		url:"../contractdictionarycs/selectTextList",
		type:"post",
		dataType:"json", 
	 	data:param,
	 	success:function(data){
		for(var i=0;i<data.length;i++){
			option='<option value="'+i+1+'">'+data[i].text+'</option>';
			$(selectid).append(option);
		}
		if(selectid=="#package"){
			for(var i=0;i<$("#package option").length;i++){
				if($("#package").get(0).options[i].text == url_data[4]){  
			       $("#package").get(0).options[i].selected = true;  
			       break;  
			    }  
			}
		}else if(selectid=="#chargeType"){
			for(var i=0;i<$("#chargeType option").length;i++){
				if($("#chargeType").get(0).options[i].text == url_data[7]){  
			       $("#chargeType").get(0).options[i].selected = true;  
			       break;  
			    }  
			}
		}else if(selectid=="#goodstype"){
			for(var i=0;i<$("#goodstype option").length;i++){
				if($("#goodstype").get(0).options[i].text == url_data[12]){
			       $("#goodstype").get(0).options[i].selected = true;  
			       break;  
			    }  
			}
		}
	},
	error:function(){
		alert("加载错误!");
	}
	})
}
/**
 * @author wei.huang
 * @since 2013-10-16
 * @function 验证输入框信息的完整性
 * @return 完整:true;不完整:false
 */
function checkInput(){
	var goodsName=true;
	if($("#goodsName").val()==""){
		goodsName=false;
	}
	var helpCode=true;
	if($("#helpCode").val()==""){
		helpCode=false;
	}
	
	if(goodsName&&helpCode){
		$("#tips").hide();
		if($("#goodsName").val().length>60){
			$("#tips").html("货物名称最多只能填写60个字！");
			$("#tips").show();
			return false;
		}
		$("#tips").hide();
		if($("#helpCode").val().length>60){
			$("#tips").html("助记码最多只能填写60个字！");
			$("#tips").show();
			return false;
		}
		/*if($("#package option:selected").text()=="--请选择--"){
			$("#tips").html("请选择包装！");
			$("#tips").show();
			return false;
		}*/
		var isNumber=/^\d{1,9}$|^\d{1,9}[.]\d{1,3}$/;
		if($.trim($("#unitWeight").val())!=""){
			if(!isNumber.test($("#unitWeight").val())){
				$("#tips").html("单位重量：整数部分最多9位，小数部分最多3位");
				$("#tips").show();
				return false;
			}
		}
		if($.trim($("#unitVolume").val())!=""){
			if(!isNumber.test($("#unitVolume").val())){
				$("#tips").html("单位体积：整数部分最多9位，小数部分最多3位");
				$("#tips").show();
				return false;
			}
		}
		
		/*if($("#chargeType option:selected").text()=="--请选择--"){
			$("#tips").html("请选择计费方式！");
			$("#tips").show();
			return false;
		}
		if($("#goodstype option:selected").text()=="--请选择--"){
			$("#tips").html("请选择货物类型！");
			$("#tips").show();
			return false;
		}*/
		return true;
	}else{
		$("#tips").html("信息不完整，请检查！");
		$("#tips").show();
		return false;
	}
}
/**
 * wei.huang
 * 2013-10-17
 * 修改货物类型
 * @return
 */
function goodsTypeEdit(){
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
	var param="goodstypeid="+$("#goodstypeid").val()+"&frompartyid="+$("#frompartyid").val()+"&checked="+check+"&goodsname="+$("#goodsName").val()+"&helpcode="+$("#helpCode").val()
	+"&package="+$("#package option:selected").text()+"&unitweight="+$("#unitWeight").val()+"&unit="+$("#unit").val()
	+"&unitvolume="+$("#unitVolume").val()+"&chargetype="+$("#chargeType option:selected").text()+"&goodstype="+$("#goodstype option:selected").text()+"&spec="+$.trim($("#spec").val())+"&model="+$.trim($("#model").val());
	if(check=="1"){
		var param1="&checked=0&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val();
		$.ajax({//修改已有记录的checked值
			url:"../goodstypecs/updateChecked",
			type:"post",
			dataType:"json",
			data:param1,
			success:function(data1){
			if(data1.msg=="ok"){
				$.ajax({//修改货物类型
					url: "../goodstypecs/update",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:param, //参数     	               
				   	success:function(data2){
					if(data2.msg=="ok"){
						window.parent.ymPrompt.doHandler("3,保存,修改",true);
					}
				}
				})
			}
		}
		})
	}else{
		$.ajax({//修改货物类型
			url: "../goodstypecs/update",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:param, //参数     	               
		   	success:function(data3){
			if(data3.msg=="ok"){
				window.parent.ymPrompt.doHandler("3,保存,修改",true);
			}
		}
		})
	}
}

function cancel(){
	window.parent.ymPrompt.doHandler("3,取消",true);
}
