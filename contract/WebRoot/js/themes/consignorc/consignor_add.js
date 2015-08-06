//初始化表格
function f_hgrid_ini(k) {
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow,k); 	
	//注意：此处的列宽和为820px
	var txt;
	if(k==1){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;发货联系人</th>' +
		'<th scope="col"   width="65px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="100px" class="tdleft">联系电话</th>' +
		'<th scope="col"   width="100px" class="tdleft">手机</th>' +
		'<th scope="col"   width="150px" class="tdleft">发货地</th>' +
		'<th scope="col"   width="170px" class="tdleft">详细地址</th>' +
		'<th scope="col"   width="65px" class="tdleft">&nbsp;</th>' +
		'<th scope="col"   width="70px" class="tdleft">操作</th>' +
		'</tr>';
	}else if(k==2){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;收货方名称</th>' +
		'<th scope="col"   width="80px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="90px" class="tdleft">收货联系人</th>' +
		'<th scope="col"   width="80px" class="tdleft">手机</th>' +
		'<th scope="col"   width="160px" class="tdleft">收货地</th>' +
		'<th scope="col"   width="170px" class="tdleft">详细地址</th>' +
		'<th scope="col"   width="70px" class="tdleft">&nbsp;</th>' +
		'<th scope="col"   width="70px" class="tdleft">操作</th>' +
		'</tr>';
	}else if(k==3){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;货物名称</th>' +
		'<th scope="col"   width="80px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="80px" class="tdleft">包装</th>' +
		'<th scope="col"   width="95px" class="tdleft">单位重量</th>' +
		'<th scope="col"   width="35px" class="tdleft">单位</th>' +
		'<th scope="col"   width="120px" class="tdleft">单位体积(M<sup>3</sup>)</th>' +
		'<th scope="col"   width="100px" class="tdleft">计费方式</th>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;</th>' +
		'<th scope="col"   width="120px" class="tdleft">操作</th>' +
		'</tr>';
	}else if(k==4){
		txt='<tr>' +
		'<th scope="col"   width="60px" class="tdleft">&nbsp;会员名</th>' +
		'<th scope="col"   width="100px" class="tdleft">分包商名称</th>' +
		'<th scope="col"   width="50px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="60px" class="tdleft">联系人</th>' +
		'<th scope="col"   width="80px" class="tdleft">手机</th>' +
		'<th scope="col"   width="70px" class="tdleft">电话</th>' +
		'<th scope="col"   width="150px" class="tdleft">所在地</th>' +
		'<th scope="col"   width="190px" class="tdleft">详细地址</th>' +
		'<th scope="col"   width="50px" class="tdleft">操作</th>' +
		'</tr>';
	}

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
/**
 * wei.huang
 * 2013-10-11
 * @param msg 结构“index,operate,[operateType]”
 * @return
 */
function callBack(msg){
	//alert(msg);
	var msg_detail=msg.split(",");
	if(msg_detail[1]=="保存"){//点击了保存按钮后父页面才刷新
		var tip="修改成功！";
		if(msg_detail[2]=="增加"){//当操作为新增的时候把页面定位到第一页
			$("#pagecode").val("1");
			tip="添加成功！";
		}
		f_hgrid_json(msg_detail[0]);
		ymPrompt.alert(tip);
	}
}
/**
 * wei.huang
 * 2013-10-12
 * 获取当前登录会员的partyid和真实名称
 */
function getPartyIdAndRealName(){
	$.ajax({
		url: "../logincs/getIdAndName",
		async: false,
	 	type:'post',	
	 	dataType:'json', 
	   	success:function(data){
		$("#frompartyid").val(data.partyid);
		$("#realname").val(data.realname);
	},
		error:function(){
		ymPrompt.alert("加载错误！");
	}
	})
}
/**
 * @author wei.huang
 * @since 2013-10-10
 * @function 根据tab选项执行对应的添加方法
 * @return
 */
function f_tabAdd(){
	if($("#issaved").val()!="yes"){
		ymPrompt.alert('请先保存以上信息！');
		//alert("请先保存以上信息！");
		return;
	}
	var index=$("#selected").index();
	var f_height=380;
	var messageUrl,formTitle;
	if(index==0){
		var data="frompartyid="+encodeURI($("#frompartyid").val())+"&realname="+encodeURI($("#realname").val())+"&listcount="+encodeURI($("#listcount").val())+"&topartyid="+encodeURI($("#addedpartyid").val());
		messageUrl="../consigneeconsignoraddresscs/consignoraddress_add?"+data;
		formTitle="添加发货地址";
	}else if(index==1){
		f_height=410;
		var data="frompartyid="+encodeURI($("#frompartyid").val())+"&realname="+encodeURI($("#realname").val())+"&listcount="+encodeURI($("#listcount").val())+"&topartyid="+encodeURI($("#addedpartyid").val());
		messageUrl="../consigneeconsignoraddresscs/consigneeaddress_add?"+data;
		formTitle="添加收货地址";
	}else if(index==2){
		f_height=360;
		var data="frompartyid="+encodeURI($("#frompartyid").val())+"&realname="+encodeURI($("#realname").val())+"&listcount="+encodeURI($("#listcount").val())+"&topartyid="+encodeURI($("#addedpartyid").val());
		messageUrl="../goodstypecs/goodstype_add?"+data;
		formTitle="添加货物信息";
	}else if(index==3){
		var data="partyid="+encodeURI($("#frompartyid").val())+"&frompartyid="+encodeURI($("#addedpartyid").val()+"&random="+Math.random());
		messageUrl="../consignorcs/consignor_link_subcontractor?"+data;
		formTitle="导入关联分包商";
		f_height=650;
	}
	ymPrompt.win({message:messageUrl,width:600,height:f_height,fixPosition:true,dragOut:false,handler:callBack,title:formTitle,iframe:true});
}
/**
 * @author wei.huang
 * @since 2013-10-9
 * @param consigneeconsignoraddressid
 * @param addressType "1"或"0",表示收货地址或发货地址
 * @return
 */
function f_defaultAddressClick(consigneeconsignoraddressid,addressType){
	var param="frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val()+"&consigneeorconsignor="+addressType+"&checked=0";
	$.ajax({//先修改已有记录的checked值
	 	url: "../consigneeconsignoraddresscs/updateChecked",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数    
	 	success:function(data){
		if(data.msg=="ok"){
			param="consigneeconsignoraddressid="+consigneeconsignoraddressid+"&consigneeorconsignor="+addressType+"&checked=1";
			$.ajax({//再修改当前记录的checked值
				url:"../consigneeconsignoraddresscs/updateChecked",
				type:'post',	
			 	dataType:'json', 
			 	data:param, //参数    
			 	success:function(data){
				if(data.msg=="ok"){
					ymPrompt.alert("设置成功!");
					if(addressType=="0"){
						f_hgrid_json(1);
					}else{
						f_hgrid_json(2);
					}
				}
			},
			error:function(){
				ymPrompt.alert("设置失败!");
			}
			});
		}
	}
	});
}
/**
 * wei.haung
 * 2013-10-15
 * 设置默认货物
 * @return
 */
function f3_defaultGoodsClick(id){
	var param="frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val()+"&checked=0";
	$.ajax({//先修改已有记录的checked值
	 	url: "../goodstypecs/updateChecked",  
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数    
	 	success:function(data){
		if(data.msg=="ok"){
			param="goodstypeid="+id+"&checked=1";
			$.ajax({//再修改当前记录的checked值
				url:"../goodstypecs/updateChecked",
				type:'post',	
			 	dataType:'json', 
			 	data:param, //参数    
			 	success:function(data){
				if(data.msg=="ok"){
					ymPrompt.alert("设置成功!");
					f_hgrid_json(3);
				}
			},
			error:function(){
				ymPrompt.alert("设置失败!");
			}
			});
		}
	}
	});
}
/**
 * @author wei.huang
 * @since 2013-10-10
 * @param id 准备删除的记录的主键(使用于收发货地址)
 * @return
 */
function f12_deleteclick(id,addressType){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../consigneeconsignoraddresscs/delete",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{consigneeconsignoraddressid:id}, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			ymPrompt.alert("删除成功！");
		 			if(addressType=="0"){
						f_hgrid_json(1);
					}else{
						f_hgrid_json(2);
					}
		 		}
		 		else{
		 			ymPrompt.alert("删除失败！");
		 		}
			
		},
			error:function(){
			//alert(data.msg);
			ymPrompt.alert("删除失败！");
		}
		});
		}else{
			return false;
		}
		}
		})
}
/**
 * wei.haung
 * 2013-10-15
 * 删除货物信息
 * @return
 */
function f3_deleteclick(id){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../goodstypecs/delete",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{goodstypeid:id}, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			ymPrompt.alert("删除成功！");
					f_hgrid_json(3);
		 		}
		 		else{
		 			ymPrompt.alert("删除失败！");
		 		}
			
		},
			error:function(){
			//alert(data.msg);
			ymPrompt.alert("删除失败！");
		}
		});
		}else{
			return false;
		}
		}
		})
}
/**
 * wei.huang
 * 2013-10-23
 * 删除分包商
 * @return
 */
function f4_deleteclick(frompartyid,topartyid){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		params="frompartyid="+frompartyid+"&topartyid="+topartyid;
		$.ajax({
		 	url: "../shipperrelasubontractorcs/deleteSubContract",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			ymPrompt.alert("删除成功！");
					f_hgrid_json(4);
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
/**
 * @author wei.huang
 * @since 2013-10-10
 * @function 修改对应信息
 */
function f_editclick(id,linkman,telephone,mobile,address,detailaddress,check,consignee,helpcode){
	var index=$("#selected").index();//0表示修改发货地址，1表示修改收货地址，2表示修改货物信息
	var messageUrl,formTitle,height;
	if(index==0){
		var data_0="id="+encodeURI(id)+"&linkman="+encodeURI(linkman)+"&telephone="+encodeURI(telephone)
		+"&mobile="+encodeURI(mobile)+"&address="+encodeURI(address)+"&detailaddress="+encodeURI(detailaddress)
		+"&check="+encodeURI(check)+"&frompartyid="+encodeURI($("#frompartyid").val())+"&topartyid="+encodeURI($("#addedpartyid").val())+"&helpcode="+encodeURI(helpcode);
		messageUrl="../consigneeconsignoraddresscs/consignoraddress_edit?"+data_0;
		formTitle="修改发货地址";
		height=380;
	}else if(index==1){
		var data_1="id="+encodeURI(id)+"&linkman="+encodeURI(linkman)+"&telephone="+encodeURI(telephone)
		+"&mobile="+encodeURI(mobile)+"&address="+encodeURI(address)+"&detailaddress="+encodeURI(detailaddress)
		+"&check="+encodeURI(check)+"&consignee="+encodeURI(consignee)+"&frompartyid="+encodeURI($("#frompartyid").val())+"&topartyid="+encodeURI($("#addedpartyid").val())+"&helpcode="+encodeURI(helpcode);
		messageUrl="../consigneeconsignoraddresscs/consigneeaddress_edit?"+data_1;
		formTitle="修改收货地址";
		height=410;
	}
	ymPrompt.win({message:messageUrl,width:600,height:height,fixPosition:true,dragOut:false,handler:callBack,title:formTitle,iframe:true});
}
/**
 * wei.haung
 * 2013-10-15
 * 修改货物信息
 * @return
 */
function f3_editclick(goodstypeid,frompartyid,goodsname,helpcode,package,unitweight,unitvolume,chargetype,check,unit,goodstype,spec,model){
	var data_3="goodstypeid="+encodeURI(goodstypeid)+"&frompartyid="+encodeURI(frompartyid)+
	"&goodsname="+encodeURI(goodsname)+"&helpcode="+encodeURI(helpcode)+"&package="+encodeURI(package)+
	"&unitweight="+encodeURI(unitweight)+"&unitvolume="+encodeURI(unitvolume)+
	"&chargetype="+encodeURI(chargetype)+"&checked="+encodeURI(check)+"&frompartyid="+encodeURI($("#frompartyid").val())+
	"&topartyid="+encodeURI($("#addedpartyid").val())+"&unit="+encodeURI(unit)+"&goodstype="+encodeURI(goodstype)+
	"&spec="+encodeURI(spec)+"&model="+encodeURI(model);
	var messageUrl="../goodstypecs/goodstype_edit?"+data_3;
	var formTitle="修改货物信息";
	ymPrompt.win({message:messageUrl,width:600,height:360,fixPosition:true,dragOut:false,handler:callBack,title:formTitle,iframe:true});
}

/**
 * wei.huang
 * 2013-10-8
 * 发货地址列表
 */
function f_hgrid1_json(param) {//刷新hGrid数据，展示发货地址
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
		param=param+"consigneeorconsignor=0&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val();//0表示发货人
	 	$.ajax({
	 	url: "../consigneeconsignoraddresscs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$("#listcount").val(data.Count);
	 		$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				var check='<a href="javascript:void(0)" OnClick="f_defaultAddressClick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;0&quot;)">设为默认</a>';
				if(data[i].checked=="1"){
					check='<span style="color:#EC6110">默认地址</span>';
				}
				var place;
				if(data[i].city==""||data[i].city==null){
					place=data[i].province;
				}else if(data[i].region==""||data[i].region==null){
					place=data[i].province+"-"+data[i].city;
				}else{
					place=data[i].province+"-"+data[i].city+"-"+data[i].region;
				}
				txt='<td class="tdleft">&nbsp;'+data[i].linkMan+'</td>'+
					'<td class="tdleft">'+data[i].helpCode+'</td>'+
					'<td class="tdleft">'+data[i].telephoneNumber+'</td>'+				
					'<td class="tdleft">'+data[i].mobileNumber+'</td>'+
				 	'<td class="tdleft">'+place+'</td>'+
				 	'<td class="tdleft">'+data[i].town+'</td>'+
				 	'<td class="tdleft">'+check+'</td>'+
				 	'<td class="tdleft">'+
				 	'<a href="javascript:void(0)" OnClick="f_editclick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;'+data[i].linkMan+'&quot;,&quot;'+data[i].telephoneNumber+'&quot;,&quot;'+data[i].mobileNumber+'&quot;,&quot;'+place+'&quot;,&quot;'+data[i].town+'&quot;,&quot;'+data[i].checked+'&quot;,&quot;&quot;,&quot;'+data[i].helpCode+'&quot;)">修改</a>'+
				 	'&nbsp'+
				 	'<a href="javascript:void(0)" OnClick="f12_deleteclick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;0&quot;)">删除</a></td>'; 
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
 * 2013-10-8
 * 收货地址列表
 */
function f_hgrid2_json(param) {//刷新hGrid数据，展示收货地址
	//vparam不包括页码和每页行数
	param=f_hgrid_getparam(param); //得到全部参数
	var topartyname = $("#topartyname").val()==null?'':$("#topartyname").val();
	var town = $("#town").val()==null?'':$("#town").val();
	param=param+"&consigneeorconsignor=1&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val()+
				"&topartyname="+topartyname+"&town="+town;//0表示发货人
 	//alert(param);
	$.ajax({
 	url: "../consigneeconsignoraddresscs/selectList",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
 		$("#listcount").val(data.Count);
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			var check='<a href="javascript:void(0)" OnClick="f_defaultAddressClick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;1&quot;)">设为默认</a>';
			if(data[i].checked=="1"){
				check='<span style="color:#EC6110">默认地址</span>';
			}
			var place;
			if(data[i].city==""||data[i].city==null){
				place=data[i].province;
			}else if(data[i].region==""||data[i].region==null){
				place=data[i].province+"-"+data[i].city;
			}else{
				place=data[i].province+"-"+data[i].city+"-"+data[i].region;
			}
			txt='<td class="tdleft">&nbsp;'+data[i].consignee+'</td>'+
				'<td class="tdleft">'+data[i].helpCode+'</td>'+
				'<td class="tdleft">'+data[i].linkMan+'</td>'+					
				'<td class="tdleft">'+data[i].mobileNumber+'</td>'+
			 	'<td class="tdleft">'+place+'</td>'+
			 	'<td class="tdleft">'+data[i].town+'</td>'+
			 	'<td class="tdleft">'+check+'</td>'+
			 	'<td class="tdleft">'+
			 	'<a href="javascript:void(0)" OnClick="f_editclick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;'+data[i].linkMan+'&quot;,&quot;'+data[i].telephoneNumber+'&quot;,&quot;'+data[i].mobileNumber+'&quot;,&quot;'+place+'&quot;,&quot;'+data[i].town+'&quot;,&quot;'+data[i].checked+'&quot;,&quot;'+data[i].consignee+'&quot;,&quot;'+data[i].helpCode+'&quot;)">修改</a>'+
			 	'&nbsp'+
			 	'<a href="javascript:void(0)" OnClick="f12_deleteclick(&quot;'+data[i].consigneeConsignorAddressId+'&quot;,&quot;1&quot;)">删除</a></td>'; 
		 	$('#tr'+i).empty().append(txt);			      
	     }
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
      },
 	error:function(){
    	  ymPrompt.alert("加载错误!");
      }
  }); 
}
/**
 * wei.huang
 * 2013-10-15
 * 货物列表
 */
function f_hgrid3_json(param) {//刷新hGrid数据
	param=f_hgrid_getparam(param); //得到全部参数
	param=param+"&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#addedpartyid").val();
	$.ajax({
		url: "../goodstypecs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){
		$("#listcount").val(data.Count);
		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			var unit=(data[i].unit=="1")?"公斤":"吨";
			var check='<a href="javascript:void(0)" OnClick="f3_defaultGoodsClick(&quot;'+data[i].goodsTypeId+'&quot;)">设为默认</a>';
			if(data[i].checked=="1"){
				check='<span style="color:#EC6110">默认货物</span>';
			}
			txt='<td class="tdleft">&nbsp;'+data[i].goodsName+'</td>'+
				'<td class="tdleft">'+data[i].helpCode+'</td>'+					
				'<td class="tdleft">'+data[i].package+'</td>'+
				'<td class="tdleft">'+data[i].unitWeight+'</td>'+
				'<td class="tdleft">'+unit+'</td>'+
			 	'<td class="tdleft">'+data[i].unitVolume+'</td>'+
			 	'<td class="tdleft">'+data[i].chargeType+'</td>'+
			 	'<td class="tdleft">'+check+'</td>'+
			 	'<td class="tdleft">'+
			 	'<a href="javascript:void(0)" OnClick="f3_editclick(&quot;'+data[i].goodsTypeId+"&quot;,&quot;"+data[i].fromPartyId+"&quot;,&quot;"+data[i].goodsName+"&quot;,&quot;"+data[i].helpCode+"&quot;,&quot;"+data[i].package+"&quot;,&quot;"+data[i].unitWeight+"&quot;,&quot;"+data[i].unitVolume+"&quot;,&quot;"+data[i].chargeType+"&quot;,&quot;"+data[i].checked+"&quot;,&quot;"+data[i].unit+"&quot;,&quot;"+data[i].goodsType+"&quot;,&quot;"+data[i].spec+"&quot;,&quot;"+data[i].model+'&quot;)">修改</a>'+
			 	'&nbsp;'+
			 	'<a href="javascript:void(0)" OnClick="f3_deleteclick(&quot;'+data[i].goodsTypeId+'&quot;)">删除</a></td>'; 
		 	$('#tr'+i).empty().append(txt);			      
	     }
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
	}
	})
}
//分包商列表
function f_hgrid4_json(param) {//刷新hGrid数据
	param=f_hgrid_getparam(param); //得到全部参数
	param=param+"&partyid="+$("#frompartyid").val()+"&frompartyid="+$("#addedpartyid").val()+"&partytype=linked";
	$.ajax({
		url: "../subcontractorcs/contractAndSubContractorList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){
		$("#listcount").val(data.Count);
		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft">&nbsp;'+data[i].partyname+'</td>'+
				'<td class="tdleft">'+data[i].organization+'</td>'+					
				'<td class="tdleft">'+data[i].helpcode+'</td>'+
				'<td class="tdleft">'+data[i].contact+'</td>'+
			 	'<td class="tdleft">'+data[i].mobilenumber+'</td>'+
			 	'<td class="tdleft">'+data[i].telephonenumber+'</td>'+
			 	'<td class="tdleft">'+data[i].province+data[i].city+data[i].region+'</td>'+
			 	'<td class="tdleft">'+data[i].officeaddress+'</td>'+
			 	'<td class="tdleft">'+
			 	'&nbsp;'+
			 	'<a href="javascript:void(0)" OnClick="f4_deleteclick(&quot;'+$("#addedpartyid").val()+"&quot;,&quot;"+data[i].partyid+'&quot;)">删除</a></td>'; 
		 	$('#tr'+i).empty().append(txt);			      
	     }
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
	}
	})
}
//绑定tab事件
var tab = function(tabId,activeId){
	$("#"+tabId).delegate("li:not(#"+activeId+")","click",function(){
		$("#"+activeId).removeAttr("id");
		$(this).attr("id",activeId);
		var index=$(this).index()+1;
		$("#loading").remove();
		$(".page_nav").remove();
		f_hgrid_ini(index);
		$("#pagecode").val("1");
		f_hgrid_json(index);
		if(index==1){
			$("#tab_btn").val("新增发货地址");
		}else if(index==2){
			$("#tab_btn").val("新增收货地址");
		}else if(index==3){
			$("#tab_btn").val("新增货物信息");
		}else if(index==4){
			$("#tab_btn").val("关联分包商");
		}
		$(".page_nav").css("margin-top","20px");
		$(".page_nav").css("text-align","right"); 
	});
};

function f_ImportClick(){
	ymPrompt.win({message:"../consignorcs/consignor_import?id="+$("#frompartyid").val(),width:600,height:500,fixPosition:true,dragOut:false,title:"选择发货方",iframe:true});
}
function getpartyInfo(partyid){
	$.ajax({
	 	url: "../subcontractorcs/subcontractor_detail_json",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"partyid":partyid}, //参数	               
	   	success:function(data){//回传函数
	 		$("#addedpartyid").val(data.partyid);//新增的发货方partyid
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
			$("#description").val(data.description);
			
			var params="topartyid="+partyid+"&shipperorsubcontractor=1";
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
	});
}
/**
 * wei.huang
 * 2013-10-22
 * 返回列表
 * @return
 */
function getBack(){
	window.location="../consignorcs/consignor_list?order=15";
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
			exist= true;
		}
}
});
	return exist;
}
var flag=false;
function f_SaveConsignor(){
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
	if(!flag){
		url="../subcontractorcs/updatePartyInfo";
	}else{
		url= "../subcontractorcs/conUpdateSubconBasicInfo";
	}
	flag=true;
	$.ajax( {
		url : url,
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
/**
 * wei.huang
 * 2013-10-28
 * @param id 附件id
 * @param i 行号
 * 删除附件
 */
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