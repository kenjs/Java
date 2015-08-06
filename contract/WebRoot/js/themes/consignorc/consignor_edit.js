/**
 * wei.huang
 * 2013-10-23
 * 获取当前发货方会员的partyid
 */
function getConsignorPartyId(){
	var url_Data= document.URL;
	var num=url_Data.lastIndexOf("=");
	url_Data=url_Data.substr(num+1);
	$("#addedpartyid").val(url_Data);
}
/**
 * wei.huang
 * 2013-10-23
 * 填充发货方信息
 * @return
 */
function fillDetail(){
	$.ajax({
		url: "../subcontractorcs/subcontractor_detail_json",
	 	type:'post',	
	 	dataType:'json', 
	 	data:{partyid:$("#addedpartyid").val(),"tableid":$("#addedpartyid").val(),"tablename":"party"}, //参数     	               
	   	success:function(data){
		$("#partyName").val(data.partyname);
		$("#mobilePhone").val(data.mobilenumber);
		$("#email").val(data.email);
		$("#linkMan").val(data.contact);
		$("#telephone").val(data.telephonenumber);
		$("#fax").val(data.fax);
		$("#consignor").val(data.organization);
		$("#helpCode").val(data.helpcode);
		$("#place").val(data.province+data.city+data.region);
		$("#address").val(data.officeaddress);
		$("#legalPerson").val(data.legalperson);
		$("#registeredCapital").val(data.registeredcapital);
		$("#employeesCount").val(data.employeescount);
		$("#issaved").val("yes");
		var txt="";
		//<a href="../attachment/'+data.list[i].filename+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a>
		if(data.list.length>0){
			for(var i=0;i<data.list.length;i++){
				txt=txt+'<tr style="height:32px;"><td class="tdleft"><span style="color:#1560ea">'+data.list[i].filename+'</span><a href="javascript:void(0)" style="color:#1560ea; margin-left:15px;" onclick="btnDel(this'+','+data.list[i].contractappendixid+')">'+"删除"+'</a></td></tr>';
			}
				$("#myfiles").append(txt);
		}
		
		var params="topartyid="+$("#addedpartyid").val()+"&shipperorsubcontractor=1";
		$.ajax({
			url: "../contractattributecs/selectContractAttributeInfo",
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
				$.each(data,function(k,v){
					if(v.attributeName == '业务员'){
						$("#salesman").val(v.attributeValue);
					}
					if(v.attributeName == '客户号'){
						$('#customNum').val(v.attributeValue);
						$("#contractattributeid").val(v.contractAttributeId);
					}
					
				});
		}
		});
	}
	})
}
/**
 * @author wei.huang
 * @date 2013-12-25
 * @function 检验客户号是否已经存在
 * @return
 */
function checkClientNumber(){
	var param="frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val()+"&contractattributeid="+$("#contractattributeid").val()+"&attributevalue="+$.trim($("#customNum").val());
	var exist=false;
	$.ajax( {
		async:false,
		url : "../contractattributecs/selectClientNumberCount",
		dataType : "text",
		type :'POST',
		data:param,
		success : function(data) {
		var count=parseInt(data);
		if(count>0){
			exist=true;
		}
}
});
	return exist;
}
function btnDel(obj,contractappendixid){
	var table = document.getElementById("myfile");
	$.ajax({
	 	url: "../pactcs/pact_under_contractappendix_delete",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{contractappendixid:contractappendixid}, //参数     	               
	   	success:function(data){//回传函数
	 		if(data.msg=='ok'){
	 			$(obj).parent("td").parent("tr").remove();
	 		}
		}
	});
}
function f_SaveConsignor_edit(){
	if($("#addedpartyid").val()==""){
		ymPrompt.alert('请先导入会员信息！');
		return;
	}
	
	if($.trim($('#helpCode').val()) == ''){
		ymPrompt.alert('助记码不能为空！');
		return;
	}
	if($.trim($('#customNum').val()) == ''){
		ymPrompt.alert('客户号不能为空！');
		return;
	}else if(checkClientNumber()){
		ymPrompt.alert('该客户号已经存在!');
		return;
	}
	
	var url;
	/*参数officeaddress应该实际存储的是详细地址,参数detailaddress应该没起到作用    update by wei.huang 2014-3-12*/
	var params1="partyname="+$.trim($("#partyName").val())+"&telephonenumber="+$.trim($("#telephone").val())+"&fax="+$.trim($("#fax").val())+"&organization="+$.trim($("#consignor").val())+
				"&helpcode="+$.trim($("#helpCode").val())+"&officeaddress="+$.trim($("#address").val())+"&detailaddress="+$.trim($("#address").val())+"&registeredcapital="+$.trim($("#registeredCapital").val())+
				"&employeescount="+$.trim($("#employeesCount").val())+"&saler="+$.trim($("#salesman").val())+"&clientcode="+$.trim($('#customNum').val())+"&frompartyid="+$("#addedpartyid").val()+"&shipperorsubcontractor=1"+"&partyid="+$("#frompartyid").val()+"&datasource=总包"+"&random="+Math.random();
	$.ajax( {
		url :"../subcontractorcs/conUpdateSubconBasicInfo",
		dataType : "json",
		type :'POST',
		data:params1,
		success : function(data) {
		if(data.msg=="ok"){
			$("#issaved").val("yes");
			$('#file_upload').uploadify('upload','*');
			ymPrompt.alert('保存成功！');
		}else{
			$("#issaved").val("no");
			ymPrompt.alert('保存失败！');
		}
}
});
}