var partyid;//总包商partyid
$(function(){		
$.ajax( {
				url : "../subcontractorcs/getPartyInfo?random=" + Math.random(),
				dataType : "json",
				contentType : "application/json",
				type : 'POST',
				success : function(data) {
					partyid=data.partyid;
					$("#partyid").val(data.partyid);//总包商partyid
		}
		});
});
function getpartyInfo(partyid1){
$.ajax({
 	url: "../subcontractorcs/subcontractor_detail_json",   
 	type:'post',	
 	dataType:'json', 
 	data:{"partyid":partyid1}, //参数	               
   	success:function(data){//回传函数
 		$("#topartyid").val(data.partyid);//分包商partyid
		$("#partyname").val(data.partyname);$("#mobilenumber").val(data.mobilenumber);$("#email").val(data.email);$("#contact").val(data.contact);
		$("#telephonenumber").val(data.telephonenumber);$("#fax").val(data.fax);$("#subcontractorname").val(data.organization);
		$("#helpcode").val(data.helpcode);$("#officeaddress").val(CONCAT(data.province+"-"+data.city+"-"+data.region));$("#detailaddress").val(data.officeaddress);
		$("#legalperson").val(data.legalperson);$("#registeredcapital").val(data.registeredcapital);$("#employeescount").val(data.employeescount);
		$("#description").val(data.description);
		var params="topartyid="+partyid1+"&shipperorsubcontractor=0";
		$.ajax({
			url: "../contractattributecs/selectContractAttributeInfo",
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
				$.each(data,function(k,v){
					if(v.attributeName == '业务员'){
						$('#saler').val(v.attributeValue);
					}
				});
		}
		});
	}
});
}
function getFileName(path){
	var pos1 = path.lastIndexOf('/');
	var pos2 = path.lastIndexOf('\\');
	var pos = Math.max(pos1, pos2)
	if( pos<0 )
	return path;
	else
	return path.substring(pos+1);
	}
$("#partyname").bind("keypress",function(event){
	if(event.keyCode==13){
		ymPrompt.win({message:"../subcontractorcs/party_import?partyname="+trim($("#partyname").val())+"&partyid="+trim($("#partyid").val()),width:650,height:600,fixPosition:true,dragOut:false,title:'提示信息',iframe:true});
	}
});
$("#btnsave").click(function(){
	if($("#topartyid").val()==""){
		ymPrompt.alert('请先导入会员信息！');
		return;
	}
	if($.trim($('#helpcode').val()) == ''){
		ymPrompt.alert('助记码不能为空！');
		return;
	}
	if($.trim($('#saler').val()) == ''){
		ymPrompt.alert('业务员不能为空！');
		return;
	}
	$("#btnsave").attr("disabled","disabled");
	var params1="partyname="+trim($("#partyname").val())+"&telephonenumber="+trim($("#telephonenumber").val())+"&fax="+trim($("#fax").val())+"&organization="+trim($("#subcontractorname").val())+
				"&helpcode="+trim($("#helpcode").val())+"&officeaddress="+trim($("#officeaddress").val())+"&registeredcapital="+trim($("#registeredcapital").val())+"&detailaddress="+trim($("#detailaddress").val())+
				"&employeescount="+trim($("#employeescount").val())+"&saler="+trim($("#saler").val())+"&description="+trim($("#description").val())+"&topartyid="+trim($("#topartyid").val())+"&shipperorsubcontractor=0"+"&partyid="+partyid+"&datasource=总包"+"&random="+Math.random();
	var clsl = $("#clsl").val();
	var tbje = $("#tbje").val();
	var nyye = $("#nyye").val();
	var wdsl = $("#wdsl").val();
	var nysl = $("#nysl").val();
	if((!checkNum(clsl))||clsl==""){
		if(clsl==""){
			ymPrompt.alert("车辆数量不可为空！");
			$("#btnsave").removeAttr("disabled"); 
		}
		return;
	};
	if(!checkNum(tbje)){
		return;
	};
	if(!checkNum(nyye)){
		return;
	};
	if(!checkNum(wdsl)){
		return;
	};
	if(!checkNum(nysl)){
		return;
	};
	var syxt = $("#syxt").val();
	var yshyObj = $(".yshy");
	var yshy = "";
	for(var i=0;i<(yshyObj.length-1);i++){
		var j = i+1;
		for(j;j<(yshyObj.length-1);j++){
			if(yshyObj[i].value==yshyObj[j].value){
				ymPrompt.alert("优势行业不可重复！");
				$("#btnsave").removeAttr("disabled"); 
				return;
			}
		}
		yshy = yshy+yshyObj[i].value+",";
	}
	var jyfwObj = $(".jyfw");
	var jyfw = "";
	for(var i=0;i<(jyfwObj.length-1);i++){
		var j = i+1;
		for(j;j<(jyfwObj.length-1);j++){
			if(jyfwObj[i].value==jyfwObj[j].value){
				ymPrompt.alert("经营范围不可重复！");
				$("#btnsave").removeAttr("disabled"); 
				return;
			}
		}
		jyfw = jyfw+jyfwObj[i].value+",";
	}
	var params1 = params1+"&clsl="+clsl+"&tbje="+tbje+"&nyye="+nyye+"&wdsl="+wdsl+"&nysl="+nysl+"&syxt="+syxt
				  +"&yshy="+yshy+"&jyfw="+jyfw;
	//alert(params1);
	$.ajax( {
		url : "../subcontractorcs/updatePartyInfo",
		dataType : "json",
		type :'POST',
		data:params1,
		success : function(data) {
			if(data.msg!='ok'){
				$("#btnsave").removeAttr("disabled");
				ymPrompt.alert("操作失败");
			}
			else{
				$(".t_input").each(function( index, element ){
					$(element).val("");
					$('#file_upload').uploadify('upload','*');
					ymPrompt.alert({"title":"提示","message":"添加成功,请返回",handler:function(){
						window.location.href="../subcontractorcs/subcontractorList?order=14";
					}});
					
				});
			}
		},
		error : function(){	ymPrompt.alert("操作失败")}
});
});
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
	}
function CONCAT(str1){
	var str=str1.split('-');
	var ary=new Array();
	var result="";
	for(var i=0;i<str.length;i++){
		if(str[i]!=""){
			ary.push(str[i]);
		}
	}
	if(ary.length==0){
		return result;
	}else for(var j=0;j<ary.length;j++){
		if(j==ary.length-1){
			result+=ary[j];
		}else{
			result+=ary[j]+"-";
		}
	}
	return result;
}
function f_deletefile(id,i){
	$.ajax( {
		url : "../contractappendixcs/delete",
		dataType : "json",
		type :'POST',
		data:{contractappendixid:id},
		success : function(data) {
		if(data.msg=="ok"){
			ymPrompt.alert("删除成功");
			$("#myfile"+i).remove();
		}else{
			ymPrompt.alert("删除失败");
		}
}
});
}
$("#reback").click(function(){
	window.location="../subcontractorcs/subcontractorList?order=14";
});
//数据字典
function f_contract(str,className) {
	var types = str;
	$.ajax({
		url: "../waybillcs/selectListByType",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":types}, //参数     	               
	   	success:function(data){//回传函数
	 		var dataObj=eval(data);
	 		var opent = ''; 
 			for(var i=0;i<dataObj.length;i++){
 				if(i==0){
 					opent += '<option value="'+dataObj[i].text+'" selected="selected">'+dataObj[i].text+'</option>';
 				}else {
 					opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
				}
	 			
	 		}
	 		$("."+className).html(opent);
	 		initSelect(className);
	 	}
	});
}
function initSelect(className){
	var addSelect = $("#"+className+"Div").html();
	//alert(yshySelect)
	$(addSelect).replaceAll("#"+className);
}
//新增并赋值select
function addSelect(className,value){
	//alert(className+":"+value);
	var addSelect = $("#"+className+"Div").html();
	var htmlStr ='';
	var i = 2;
	//有隐藏select 长度比页面多一个
	var yshyLen = $(".yshy").length;
	var jyfwLen = $(".jyfw").length;
	if(yshyLen>jyfwLen){
		if(className=="yshy"){
			str = "yshy";
			i = yshyLen;
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'"></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}else{
			str = "jyfw";
			i = jyfwLen;
			htmlStr = addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a>';
			$("#"+className+"Span"+jyfwLen).html(htmlStr);
		}
	}else if(yshyLen<jyfwLen){
		if(className=="yshy"){
			str = "yshy";
			i = yshyLen;
			htmlStr = addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a>';
			$("#"+className+"Span"+yshyLen).html(htmlStr);
		}else{
			str = "jyfw";
			i = jyfwLen;
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'"></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}
	}else{
		i = jyfwLen;
		if(className=="yshy"){
			str = "yshy";
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'"></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}else{
			str = "jyfw";
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'"></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}
	}
	if(value!=""){
		$("#"+className+"Span"+i+" select").val(value);
	}
}
//删除 select
function deleteSelect(className,i){
	i = i/1;
	$("."+className+"Td").each(function(k,obj){
		$("#"+className+"Span"+(i+k)+" select").val($("#"+className+"Span"+(i+k+1)+" select").val());
	});
	var len = $("."+className).length;
	$("#"+className+"Span"+(len-1)).empty();
	if($("#yshySpan"+(len-1)).html()==""&&($("#yshySpan"+(len-1)).html()=="")){
		//alert(len)
		$("#addTr"+(len-1)).remove();
	}
}
function checkNum(num){
	if(isNaN(num)){
		ymPrompt.alert("请输入数字！");
		$("#btnsave").removeAttr("disabled"); 
		return false;
	}
	return true;
}