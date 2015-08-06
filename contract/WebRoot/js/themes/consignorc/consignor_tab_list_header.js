//初始化表格
function f_hgrid_ini(k) {
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow,k); 	
	//注意：此处的列宽和为820px
	var txt;
	if(k==1){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdcenter">发货联系人</th>' +
		'<th scope="col"   width="100px" class="tdcenter">联系电话</th>' +
		'<th scope="col"   width="100px" class="tdcenter">手机</th>' +
		'<th scope="col"   width="150px" class="tdcenter">发货地</th>' +
		'<th scope="col"   width="170px" class="tdcenter">详细地址</th>' +
		'<th scope="col"   width="100px" class="tdcenter">&nbsp;</th>' +
		'<th scope="col"   width="100px" class="tdcenter">操作</th>' +
		'</tr>';
	}else if(k==2){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdcenter">收货方名称</th>' +
		'<th scope="col"   width="90px" class="tdcenter">收货联系人</th>' +
		'<th scope="col"   width="80px" class="tdcenter">联系电话</th>' +
		'<th scope="col"   width="80px" class="tdcenter">手机</th>' +
		'<th scope="col"   width="160px" class="tdcenter">发货地</th>' +
		'<th scope="col"   width="170px" class="tdcenter">详细地址</th>' +
		'<th scope="col"   width="70px" class="tdcenter">&nbsp;</th>' +
		'<th scope="col"   width="70px" class="tdcenter">操作</th>' +
		'</tr>';
	}else if(k==3){
		txt='<tr>' +
		'<th scope="col"   width="100px" class="tdcenter">货物名称</th>' +
		'<th scope="col"   width="80px" class="tdcenter">助记码</th>' +
		'<th scope="col"   width="80px" class="tdcenter">包装</th>' +
		'<th scope="col"   width="120px" class="tdcenter">单位重量(k<span style="text-transform:lowercase">g</span>)</th>' +
		'<th scope="col"   width="120px" class="tdcenter">单位体积(M<sup>3</sup>)</th>' +
		'<th scope="col"   width="100px" class="tdcenter">计费方式</th>' +
		'<th scope="col"   width="100px" class="tdcenter">&nbsp;</th>' +
		'<th scope="col"   width="120px" class="tdcenter">操作</th>' +
		'</tr>';
	}else if(k==4){
		txt='<tr>' +
		'<th scope="col"   width="50px" class="tdcenter">会员名</th>' +
		'<th scope="col"   width="100px" class="tdcenter">分包商名称</th>' +
		'<th scope="col"   width="50px" class="tdcenter">助记码</th>' +
		'<th scope="col"   width="60px" class="tdcenter">联系人</th>' +
		'<th scope="col"   width="80px" class="tdcenter">手机</th>' +
		'<th scope="col"   width="80px" class="tdcenter">电话</th>' +
		'<th scope="col"   width="150px" class="tdcenter">所在地</th>' +
		'<th scope="col"   width="190px" class="tdcenter">详细地址</th>' +
		'<th scope="col"   width="50px" class="tdcenter">操作</th>' +
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