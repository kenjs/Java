/**
 * wei.haung
 * 2013-10-23
 * 更换tab页面的内容
 * @return
 */
function tab(tabId,activeId){
	$("#"+tabId).delegate("li:not(#"+activeId+")","click",function(){
		$("#"+activeId).removeAttr("id");
		$(this).attr("id",activeId);
		var index=$(this).index()+1;
		$("#loading").remove();
		$(".page_nav").remove();
		f_hgrid_ini(index);
		$("#pagecode").val("1");
		f_hgrid_json(index);
		$(".page_nav").css("margin-top","20px");
		$(".page_nav").css("text-align","right"); 
	});
};
/**
 * wei.huang
 * 2013-10-12
 * 获取当前登录的总包会员的partyid和真实名称
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
 * wei.huang
 * 2013-10-23
 * 获取当前发货方会员的partyid
 */
function getConsignorPartyId(){
	var url_Data= document.URL;
	var num=url_Data.lastIndexOf("=");
	url_Data=url_Data.substr(num+1);
	$("#topartyid").val(url_Data);
}
/**
 * wei.huang
 * 2013-10-23
 * 初始化表格
 */
function f_hgrid_ini(k) {
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow,k); 	
	//注意：此处的列宽和为820px
	var txt;
	if(k==1){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;发货联系人</th>' +
		'<th scope="col"   width="100px" class="tdleft">联系电话</th>' +
		'<th scope="col"   width="100px" class="tdleft">手机</th>' +
		'<th scope="col"   width="200px" class="tdleft">发货地</th>' +
		'<th scope="col"   width="220px" class="tdleft">详细地址</th>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;</th>' +
		'</tr>';
	}else if(k==2){
		txt='<tr>' +
		'<th scope="col"   width="130px" class="tdleft">&nbsp;收货方名称</th>' +
		'<th scope="col"   width="90px" class="tdleft">收货联系人</th>' +
		'<th scope="col"   width="80px" class="tdleft">联系电话</th>' +
		'<th scope="col"   width="80px" class="tdleft">手机</th>' +
		'<th scope="col"   width="160px" class="tdleft">发货地</th>' +
		'<th scope="col"   width="190px" class="tdleft">详细地址</th>' +
		'<th scope="col"   width="70px" class="tdleft">&nbsp;</th>' +
		'</tr>';
	}else if(k==3){
		txt='<tr>' +
		'<th scope="col"   width="150px" class="tdleft">&nbsp;货物名称</th>' +
		'<th scope="col"   width="80px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="100px" class="tdleft">包装</th>' +
		'<th scope="col"   width="95px" class="tdleft">单位重量</th>' +
		'<th scope="col"   width="35px" class="tdleft">单位</th>' +
		'<th scope="col"   width="120px" class="tdleft">单位体积(M<sup>3</sup>)</th>' +
		'<th scope="col"   width="150px" class="tdleft">计费方式</th>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;</th>' +
		'</tr>';
	}else if(k==4){
		txt='<tr>' +
		'<th scope="col"   width="70px" class="tdleft">&nbsp;会员名</th>' +
		'<th scope="col"   width="130px" class="tdleft">分包商名称</th>' +
		'<th scope="col"   width="50px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="80px" class="tdleft">联系人</th>' +
		'<th scope="col"   width="80px" class="tdleft">手机</th>' +
		'<th scope="col"   width="80px" class="tdleft">电话</th>' +
		'<th scope="col"   width="150px" class="tdleft">所在地</th>' +
		'<th scope="col"   width="180px" class="tdleft">详细地址</th>' +
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
 * 2013-10-8
 * 发货地址列表
 */
function f_hgrid1_json(param) {//刷新hGrid数据，展示发货地址
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
		param=param+"consigneeorconsignor=0&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val();//0表示发货人
	 	$.ajax({
	 	url: "../consigneeconsignoraddresscs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
//	 		$("#listcount").val(data.Count);
	 		$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				var check="&nbsp;"
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
					'<td class="tdleft">'+data[i].telephoneNumber+'</td>'+				
					'<td class="tdleft">'+data[i].mobileNumber+'</td>'+
				 	'<td class="tdleft">'+place+'</td>'+
				 	'<td class="tdleft">'+data[i].town+'</td>'+
				 	'<td class="tdleft">'+check+'</td>'; 
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
	param=param+"&consigneeorconsignor=1&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val();//0表示发货人
 	$.ajax({
 	url: "../consigneeconsignoraddresscs/selectList",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
// 		$("#listcount").val(data.Count);
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			var check='&nbsp';
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
				'<td class="tdleft">'+data[i].linkMan+'</td>'+					
				'<td class="tdleft">'+data[i].telephoneNumber+'</td>'+
				'<td class="tdleft">'+data[i].mobileNumber+'</td>'+
			 	'<td class="tdleft">'+place+'</td>'+
			 	'<td class="tdleft">'+data[i].town+'</td>'+
			 	'<td class="tdleft">'+check+'</td>'; 
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
	param=param+"&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val();
	$.ajax({
		url: "../goodstypecs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){
//		$("#listcount").val(data.Count);
		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			var unit=(data[i].unit=="1")?"公斤":"吨";
			var check='&nbsp';
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
			 	'<td class="tdleft">'+check+'</td>'; 
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
	param=param+"&partyid="+$("#frompartyid").val()+"&frompartyid="+$("#topartyid").val()+"&partytype=linked";
	$.ajax({
		url: "../subcontractorcs/contractAndSubContractorList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){
//		$("#listcount").val(data.Count);
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
			 	'<td class="tdleft">'+data[i].officeaddress+'</td>'; 
		 	$('#tr'+i).empty().append(txt);			      
	     }
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
	}
	})
}
/**
 * wei.huang
 * 2013-10-23
 * 返回发货方列表
 * @return
 */
function f_Close(){
	self.opener=null;  
	self.open('', '_self');  
	self.close();
}
/**m
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
	 	data:{partyid:$("#topartyid").val(),"tableid":$("#topartyid").val(),"tablename":"party"}, //参数     	               
	   	success:function(data){
		$("#partyname").html(data.partyname);
		$("#mobilenumber").html(data.mobilenumber);
		$("#email").html(data.email);
		$("#contact").html(data.contact);
		$("#telephonenumber").html(data.telephonenumber);
		$("#fax").html(data.fax);
		$("#consignor").html(data.organization);
		$("#helpcode").html(data.helpcode);
		$("#officeaddress").html(data.province+data.city+data.region);
		$("#detailaddress").html(data.officeaddress);
		$("#legalperson").html(data.legalperson);
		$("#registeredcapital").html(data.registeredcapital==""?"--":data.registeredcapital+"万元");
		$("#employeescount").html(data.employeescount);
		$("#description").val(data.description);	
		var txt="";
		if(data.list.length>0){
			for(var i=0;i<data.list.length;i++){
				/*txt=txt+'<tr style="height:25px;"><td class="tdleft"><a href="../attachment/'+data.list[i].filename+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a></td></tr>';*/
				txt=txt+'<tr style="height:25px;"><td class="tdleft"><a href="'+data.list[i].url+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a></td></tr>';
			}
				$("#myfiles").append(txt);
		}
		var params="topartyid="+$("#topartyid").val()+"&shipperorsubcontractor=1";
		$.ajax({
			url: "../contractattributecs/selectContractAttributeInfo",
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
				$.each(data,function(k,v){
					if(v.attributeName == '业务员'){
						$('#saler').html(v.attributeValue);
					}
					if(v.attributeName == '客户号'){
						$('#customnumber').html(v.attributeValue);
					}
				});
		}
		});
	}
	});
}
