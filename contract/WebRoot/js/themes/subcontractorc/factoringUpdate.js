function init_json(){
	var id = getFrompartyid();
	//alert(id)
	$.ajax({
	 	url: "../subcontractorcs/subcontractor_detail_json",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"partyid":id,"tablename":"party","tableid":getid}, //参数     	               
	   	success:function(data){//回传函数
			//$("#topartyname").html(data.partyname);
			$("#topartyidfbsid").text(data.organization);
			var params="topartyid="+id;
			$.ajax({
				url: "../subcontractorcs/factoringDetail_json",
			 	type:'post',	
			 	dataType:'json', 
			 	data:params, //参数                   
			   	success:function(data){
				$.each(data,function(k,obj){
					$('#yhjgzh').val(obj.yhjgzh);
					$('#zzjgdmz').val(obj.zzjgdmz);
				});
				$.ajax({
					url: "../bankfactoringcs/factoringDetailFhf_json2",
				 	type:'post',	
				 	dataType:'json', 
				 	data:params, //参数                   
				   	success:function(data){
					var txt = '',checkboxtxt='';
			 		$("#hgrid tr:gt(0)").empty();
					$.each(data,function(k,obj){
						//alert(obj.frompartyname);
						if(obj.isenabled=='1'){
							checkboxtxt='<input type="checkbox" name="isEnabled" id="is_enabled" style="margin-left:60px;" value="'+obj.frompartyid+'" checked="true" onclick="checkCheckBox(this)"/>';
						}else{
							checkboxtxt='<input type="checkbox" name="isEnabled" id="is_enabled" style="margin-left:60px;" value="'+obj.frompartyid+'" onclick="checkCheckBox(this)"/>';
						}
						txt = txt +
							'<tr>'+'<td>'+obj.frompartyname+'</td>'+
								'<td>'+checkboxtxt+'</td>'+
								'<td>'+'<input type="text" name="businessDays" id="business_days_'+k+'" class="com_input" style="width:70px;" value="'+obj.businessdays+'" onfocus="clearborder(this)"/> '+'</td>'+
							'</tr>';
					});		                    	        					
					$(txt).insertAfter($("#hgrid tr:last"));
				}
				})
			}
			})
	 	}
	})
}

function do_update(){
	var checkboxobj = $("input[name=isEnabled]"),daysinput = $("input[name=businessDays]");
	var frompartyid = '',businessdays = '',isenabled='',jsonarra=[],jsonText='',
		bankcount = trim($("#yhjgzh").val()),organicount = trim($("#zzjgdmz").val())
		,topartyid = getFrompartyid();
	if(bankcount==''||bankcount==null||bankcount==undefined){
		markText("#yhjgzh","不能为空");
		return false;
	}
	if(organicount==''||organicount==null||organicount==undefined){
		markText("#zzjgdmz","不能为空");
		return false;
	}
	var flag = true;
	$(checkboxobj).each(function(i,o){
		isenabled='0';
		if($(this).attr("checked")) {
			isenabled = '1';
		}
		frompartyid = $(this).val();
		businessdays = trim($("#business_days_"+i).val());
		var reg = /^[0-9]*[1-9][0-9]*$/,　　//验证正整数 
			res = reg.test(businessdays);
		if((isenabled=='1') && ! res){
			markText("#business_days_"+i,"请输入账期");
			flag = false;
			return false;
		}
		var jsonstr = createObj(frompartyid,businessdays,isenabled);
		jsonarra.push(jsonstr);
	});
	if(!flag){
		return false;
	}
	jsonText = JSON.stringify(jsonarra);
	$.ajax({
		url:'../subcontractorcs/update_save',
		type:'post',
		dataType:'json',
		data:{topartyid:topartyid,jsonText:jsonText,bankcount:bankcount,organicount:organicount},
		success:function(data){
			if(data.msg=="ok"){
				ymPrompt.alert({message:"修改成功！",handler:f_close});				
			}else{
				ymPrompt.alert("修改失败！");
			}
		},
		error:function(){
			ymPrompt.alert("修改失败！");
		}
	});
}
function createObj(frompartyid,businessdays,isenabled){
	return {
		frompartyid:frompartyid,
		businessdays:businessdays,
		isenabled:isenabled
	}
}
function markText(targetId,message){
	var _targetId=targetId.substr(1);//去掉#
	if($(targetId+"_message").length==0){//当信息提示div不存在时
		var top=$(targetId).position().top+parseInt($(targetId).css("height"));
		var left=$(targetId).position().left;
		var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("body").append(txt);
	}else{//当信息提示div存在时
		$(targetId+"_message").empty().append(message);
	}
	$(targetId).css("border","2px solid red");
	return targetId+"_message";
}
function trim(str){
	 return str.replace(/(^\s*)|(\s*$)/g, "");
}
function getFrompartyid() {
	var idUrl = document.URL;
	idUrl = idUrl.split("?")[1];
	return idUrl.split("&")[1].split("=")[1];
}
function f_close(){
	window.location.href="../subcontractorcs/factoringList?order=24";
}
function clearborder(obj){
	var id = $(obj).attr("id");
	$('#'+id+'_message').empty();
	$(obj).css('border','1px solid #A0CFE0');
}
function checkCheckBox(obj){
	if (!obj.checked) {
		$(obj).parent().next().find('input').val('');
	}
}