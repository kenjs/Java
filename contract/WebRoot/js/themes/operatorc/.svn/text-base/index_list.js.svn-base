	function f_hgrid_ini(pagerow) {//初始化表格
			var pagerow=10;  //参数为每页行数
		     f_hgrid_create(pagerow); 	
			//注意：此处的列宽和为820px
			var txt=
				'<tr>' +
				'<th scope="col"  style="width: 120px;" class="tdcenter">账号名</th>' +
				'<th scope="col"  style="width: 90px;" class="tdcenter">姓名</th>' +
				'<th scope="col"  style="width: 110px;" class="tdcenter">手机号码</th>' +
				'<th scope="col"  style="width: 110px;" class="tdcenter">电话号码</th>' +
				'<th scope="col"  style="width: 240px;text-align: center;" class="tdcenter">操作</th>' +
				'</tr>';
			
			for(var i =0;i<pagerow;i++){ //生成不见的空行
				txt=txt+'<tr id="tr'+i+'" class="ctr" >';
			 	txt=txt+'</tr>';
			 }
			$("#hgrid").empty().append(txt);
//			f_onmouseover();
			$(".ctr").hover(function(){
                $(this).css("background-color","#fffddd");
                },
                function(){
                         $(this).css("background-color","#fff");

                });
		}
		
		
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
	//alert("param--1:"+param);
	param=f_hgrid_getparam(param); //得到全部参数
	//alert("param--2:"+param);
	$.ajax({
	 	url: "list_json.json?random="+Math.random(),   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
			$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
			
				txt='<td class="tdcenter1" class="tdcenter">'+data[i].operator+'</td>'+
					'<td class="tdcenter1" class="tdcenter">'+data[i].realname+'</td>'+
					'<td class="tdcenter1" class="tdcenter">'+data[i].mobilenumber+'</td>'+
					'<td class="tdcenter1" class="tdcenter">'+data[i].telephonenumber+'</td>'+
					'<td class="tdcenter1" class="tdcenter"><a href="javascript:void(0)" style="color:#1560ea;padding-left:50px;" onClick="f_detail(&quot;'+data[i].operatorid+'&quot;,&quot;'+data[i].operatorid+'&quot;);">'+ "详情"+'</a><a href="javascript:void(0)" style="color:#1560ea; padding-left:10px;" onClick="f_edit(&quot;'+data[i].operatorid+'&quot;);">'+ "修改"+'</a><a href="javascript:void(0)" style="color:#1560ea;padding-left:10px;" onClick="f_delete(&quot;'+data[i].operatorid+'&quot;,&quot;'+data[i].operator+'&quot;);">'+ "删除"+'</a><a href="javascript:void(0)" style="color:#1560ea;padding-left:10px;" onClick="f_repwd(&quot;'+data[i].operatorid+'&quot;,&quot;'+data[i].operator+'&quot;);">'+ "重置密码"+'</a></td>';		 
	
				$('#tr'+i).empty().append(txt);			      
		     }
			 var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
			//btn_onmouseover();
          }	
	  }); 
}

//删除
function f_delete(operatorid,operator){

 ymPrompt.confirmInfo({message:'确认删除?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
	  function(status){
	  if(status=='ok'){
	  //alert("aa="+dataItem.operator);
	  //alert(dataItem.operator=='admin');
	       if(operator.split("/")[1]=="admin"){
	        alert("不能删除主账号");
	        return false;
	       }
          	 var params="operatorid="+operatorid+"&operator="+operator;
	         $.ajax({
			   type : "get",
			   url : "../operatorcs/delete?random="+Math.random(),
			   data: params,
			   dataType: "json",
			   error : function(){alert("操作失败")},
			   success : function(data) {
			   		if(data.msg=='ok'){
			   			//刷新页面
			   			f_hgrid_json("");
			       	}
			   }
	  		});
        }
      }
        });
        
  }

//重置密码 
function f_repwd(operatorid,operator){
	 ymPrompt.confirmInfo({message:'是否确认重置密码?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		  function(status){
		  if(status=='ok'){
	 if(operator=='admin'){
	        alert("不能重置主账号密码");
	        return false;
	       }
        	 var params="operatorid="+operatorid;
	         $.ajax({
			   type : "post",
			   url : "../operatorcs/updateoldpassword?random="+Math.random(),
			   data: params,
			   dataType: "json",
			   error : function(){alert("操作失败")},
			   success : function(data) {
			   		if(data.msg=='ok'){
			   			//刷新页面
			   			alert("重置成功，新密码为123456s");
			   			f_hgrid_json("");
			       	}else{
			       	alert("操作失败");
			       	}
			   }
	  		});
     }
     }
       });
 }


//跳转到详细页面并传id值
function f_detail(operid,oper){
	window.location="../operatorcs/index_listdetail_contract?order=21&id="+(operid+"/"+oper);
}
//跳转到修改页面并传id值
function f_edit(operid){
	window.location="../operatorcs/index_operatoredit_contract?order=21&id="+operid;
}
function f_add(){
	window.location="../operatorcs/index_add_contract?order=21";
}
document.write("<script type='text/javascript' src='../js/sys/ymPrompt/ymPrompt.js'></script>");
