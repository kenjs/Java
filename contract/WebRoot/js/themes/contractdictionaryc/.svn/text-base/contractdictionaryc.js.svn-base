function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col"   width="120px" class="tdleft">&nbsp;字典类型</th>' +
		'<th scope="col"   width="170px" class="tdleft">文本</th>' +
		'<th scope="col"   width="300px" class="tdleft">描述</th>' +
		'<th scope="col"   width="150px" class="tdleft">更新时间</th>' +
		'<th scope="col"   width="80px" class="tdleft">操作</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
		var type=$("#type").val();
		var text=$("#text").val();
		if(type!=""&&type!="全部"){
			param=param+"&type="+type;
		}
		if(text!=""&&text!=null){
			param=param+"&text="+text;
		}
	 	$.ajax({
	 	url: "../contractdictionarycs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft">&nbsp;'+data[i].type+'</td>'+
					'<td class="tdleft">'+data[i].text+'</td>'+					
					'<td class="tdleft">'+data[i].description+'</td>'+
				 	'<td class="tdleft">'+data[i].updateDate+'</td>'+
				 	'<td class="tdleft"><a href="javascript:void(0)" OnClick="f_editclick(&quot;'+data[i].contractDictionaryId+'&quot;&#44;&quot;'+data[i].type+'&quot;&#44;&quot;'+data[i].text+'&quot;&#44;&quot;'+data[i].description+'&quot;)">修改</a>'+
				 	'&nbsp'+
				 	'<a href="javascript:void(0)" OnClick="f_deleteclick(&quot;'+data[i].contractDictionaryId+'&quot;)">删除</a></td>'; 
			 	$('#tr'+i).empty().append(txt);			      
		     }
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
          }	
	  }); 
}
/**
 * wei.huang
 * 2013-9-23
 * @param msg "按钮类型,操作类型"
 * @return
 */
function callBack(msg){
	var msg_data=msg.split(",");
	if(msg_data[0]=="保存"){
		var msg="修改成功！";
		if(msg_data[1]=="增加"){
			$("#pagecode").val("1");
			msg="添加成功！";
		}
		f_hgrid_json("");
		ymPrompt.alert(msg);
	}
}
function f_editclick(id,ctype,ctext,cdescription){
	var data="id="+encodeURI(id)+"&type="+encodeURI(ctype)+"&text="+encodeURI(ctext)+"&description="+encodeURI(cdescription);
	ymPrompt.win({message:"../contractdictionarycs/contractdictionary_edit?"+data,width:600,height:320,fixPosition:true,dragOut:false,handler:callBack,title:'修改字典',iframe:true});
}
function f_deleteclick(id){	
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../contractdictionarycs/delete", 
		 	type:'post',	
		 	dataType:'json', 
		 	data:{contractdictionaryid:id}, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			ymPrompt.alert("删除成功！");
		 			f_hgrid_json("");
		 		}
		 		else{
		 			ymPrompt.alert("删除失败！");
		 		}
			
		},
			error:function(){
			ymPrompt.alert("删除失败！");
		}
		});
		}else{
			return false;
		}
		}
		})
	}

function f_searchclick(){
	$("#pagecode").val("1");
	f_hgrid_json("");
}

function f_insertclick(){
	ymPrompt.win({message:"../contractdictionarycs/contractdictionary_add",width:600,height:320,fixPosition:true,dragOut:false,handler:callBack,title:'添加字典',iframe:true});
}
