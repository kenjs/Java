/*function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	return idUrl;
}*/
$(function(){
	$.ajax({
	 	url: "../contractdictionarycs/selectTextList",
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":"合同类型","random":Math.random()}, //参数     	               
	   	success:function(data){//回传函数
	 		for(var i=0;i<data.length;i++){
	 			var option=$("<option></option>").text(data[i].text);
	 			$("#type").append(option);
	 		}
		}
	});
});
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function btnSubmit(obj){
	$(obj).attr("disabled","disabled");
	var pactnumber=trim($("#pactnumber").val()),type=trim($("#type").val()),frompartyrealname=trim($("#frompartyrealname").val()),
	frompartysignman=trim($("#frompartysignman").val()),topartyrealname=trim($("#topartyrealname").val()),topartysignman=trim($("#topartysignman").val()),
	signdate=trim($("#signdate").val()),enddate=trim($("#enddate").val()),appendixid=$("#appendixid").val(),memo=trim($("#memo").val()),frompartyid=$("#frompartyrealname").attr("myid")==undefined?"":$("#frompartyrealname").attr("myid"),topartyid=$("#topartyrealname").attr("myid")==undefined?"":$("#topartyrealname").attr("myid");
	var params="pactnumber="+pactnumber+"&type="+type+"&frompartyrealname="+frompartyrealname+"&frompartysignman="+frompartysignman+"&topartyrealname="+topartyrealname+
				"&topartysignman="+topartysignman+"&signdate="+signdate+"&enddate="+enddate+"&appendixid="+appendixid+"&memo="+memo+"&frompartyid="+frompartyid+"&topartyid="+topartyid+"&random="+Math.random();
	if(pactnumber==""){
		ymPrompt.alert({"title":"提示","message":"合同编号不能为空！"});
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}
	if(frompartyrealname==""){
		ymPrompt.alert({"title":"提示","message":"合同甲方不能为空！"});
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}
	if(topartyrealname==""){
		ymPrompt.alert({"title":"提示","message":"合同乙方不能为空！"});
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}
	if(enddate==""){
		ymPrompt.alert({"title":"提示","message":"合同到期日期不能为空！"});
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}
	if(!dateCompare(signdate, enddate)){
		ymPrompt.alert({"title":"提示","message":"合同到期日期不能早于签订日期！"});
		$("#btnSubmit").removeAttr("disabled");
		return;
	}
 	$.ajax({
	 	url: "../pactcs/pactsAdd_data",   
	 	type:'POST',	
	 	dataType:'json', 
	 	data:params, //参数     
	    error : function() {    
 		 obj.removeAttribute("disabled");  
	     } ,
	   	success:function(data){//回传函数
	    	 if(data.msg=='ok'){
	    		 $("#pactid").val(data.id);
	    		 $('#file_upload').uploadify('upload','*');
	    		 	ymPrompt.alert({"title":"提示","message":"添加成功,请返回",handler:function(){
					window.location.href="../pactcs/pactsList?order=17";
				}});
	    	 }else{
		    	 obj.removeAttribute("disabled");
	    	 }
          }	
	  }); 
}
$("#reback").click(function(){
	window.location="../pactcs/pactsList?order=17";
});
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
$("#pactnumber").focus(function(e){
	$(".pactnumber").hide();
	
});
$("#frompartyrealname").focus(function(e){
	$(".frompartyrealname").hide();
	
});
$("#topartyrealname").focus(function(e){
	$(".topartyrealname").hide();
	
});

//判断日期大小
function dateCompare(startdate,enddate)
{   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   
	  
	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
	  
	if(starttimes>lktimes)    
	{   
		return false;   
	}   
	else{
		return true;   
	}
}  