function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th width="120px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">分包商名称</th>' +
		'<th width="50px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">助记码</th>' +
		'<th width="70px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">会员名</th>' +
		'<th width="70px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">联系人</th>' +
		'<th width="90px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">手机</th>' +
		'<th width="90px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">电话</th>' +
		'<th width="190px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">所在地</th>' +
		'<th width="120px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">投保金额（万元）</th>' +
		'<th width="120px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">年营业额（万元）</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">年运输量（吨）</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">车辆数量（辆）</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">网点数量（个）</th>' +	
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left: 10px;">是否使用系统</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr"';
		/*if(i%2 != 0) {
			txt = txt+'style="background-color: #f8feff;"';
		}*/
	 	txt=txt+'></tr>';
	 }
	$("#hgrid").empty().append(txt);	
 	$('.ctr').hover(function(){
 		$(this).css("background-color","#fffddd");
 	},function(){
 		var indexvalue=$(this).index();
 		$(this).css("background-color","#fff");
 	});
 	
	var txt='<tr><th scope="col"   width="100px" >操作</th></tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#operation").empty().append(txt);
}	
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../subcontractorcs/contractAndSubContractorList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数   
	 	cache:false,
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt,txt1;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				//alert(data[i].tbje);
				txt='<td class="tdleft" ><a href="javascript:void(0);" onclick=btnDetail('+data[i].partyid+') style="cursor:pointer">'+data[i].organization+'</a></td>'+		
					'<td class="tdleft">'+data[i].helpcode+'</td>'+  
				 	'<td class="tdleft">'+ data[i].partyname+ '</td>'+
					'<td class="tdleft" style=";padding-left: 15px">'+data[i].contact+'</td>'+  
					'<td class="tdleft">'+ data[i].mobilenumber+ '</td>'+  
				 	'<td class="tdleft">'+ data[i].telephonenumber+ '</td>'+  
				 	'<td class="tdleft">'+data[i].province+data[i].city+data[i].region+'</td>'+
				 	'<td class="tdleft td1">'+ data[i].tbje+ '</td>'+  
				 	'<td class="tdleft td1">'+ data[i].nyye+ '</td>'+  
				 	'<td class="tdleft td1">'+ data[i].nysl+ '</td>'+
				 	'<td class="tdleft td1">'+ data[i].clsl+ '</td>'+  
				 	'<td class="tdleft td1">'+ data[i].wdsl+ '</td>'+  
				 	'<td class="tdleft td1">'+ data[i].syxt+ '</td>';
				txt1 = '<td style="text-align:left;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].partyid+'") ">详情</a><a href="javascript:void(0);" style="margin-left:10px;" onClick=btnEdit("'+data[i].partyid+'")>修改</a></td>'; 
				$('#tr'+i).empty().append(txt);
				$('#czTr'+i).empty().append(txt1);		      
		     }
			/*if(data.length==0){
				txt='<td class="tdleft"><img src="../imgs/sys/notice-icon.png"/><span style="margin-left:10px;color:#1560ea;">暂无数据，</span><a href="#" onClick=btnAdd(this)style="margin-left:10px;color:#1560ea;">立即添加</a></td>'
					$('#tr'+i).empty().append(txt);
			}*/
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
				 $('#czTr'+i).empty();
			 }
          }	
	  }); 
}
/*function f_hgrid_json_query(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	param=f_hgrid_getparam(param); //得到全部参数
 	$.ajax({
 	url: "../subcontractorcs/subcontractorList_json",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
 		$("#loading").hide();
 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
		var txt;
		if(data.length>0){
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft">'+data[i].organization+'</td>'+		
				'<td class="tdleft">'+data[i].helpcode+'</td>'+  
			 	'<td class="tdleft">'+ data[i].partyname+ 
				'<td class="tdleft" style=";padding-left: 15px">'+data[i].legalperson+'</td>'+  
				'<td class="tdleft">'+ data[i].mobilenumber+ 
			 	'<td class="tdleft">'+ data[i].telephonenumber+ 
			 	'<td class="tdleft" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">'+data[i].officeaddress+'</td>'+  
			 	'<td style="text-align:center;color:#444444;"><a id="btndetail" href="#" onclick=btnDetail("'+data[i].partyid+'") style="margin-left:10px;color:#1560ea;">详情</a><span style="width:2px; margin-left: 5px;color:#1560ea;"">|</span><a href="#" style="color:#1560ea;margin-left:10px;" onClick=btnEdit("'+data[i].partyid+'")>修改</a><span style="width:2px; margin-left: 5px;color:#1560ea;">|</span><a id="btnDel" href="#" style="color:#1560ea;margin-left:10px;" onClick=btnDel("'+data[i].partyid+'")>删除</a></td>'; 
		 	$('#tr'+i).empty().append(txt);			      
	     }
		return ;
		}
		if(data.length==0){
			txt='<td class="tdleft"><img src="../imgs/sys/notice-icon.png"/><span style="margin-left:10px;color:#1560ea;">未找到你的信息，</span><a href="#" onClick=btnReback(this)style="margin-left:10px;color:#1560ea;">返回</a></td>'
				$('#tr'+i).empty().append(txt);
		}
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
      }	
  }); 
}*/
function btnDetail(id){
	var height=510;
	$.ajax({
	 	url: "../subcontractorcs/subcontractor_detail_json",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"partyid":id,"tableid":id,"tablename":"party"}, //参数     	               
	   	success:function(data){//回传函数
	 		var url = "../subcontractorcs/subcontractor_detail?partyid="+id;
	 		window.open(url, "_blank");
	 	}
	});
}
function btnEdit(id){
	window.location="../subcontractorcs/subcontractor_edit?order=14&partyid="+id;
}
function btnDel(id){
	//alert(id);
	ymPrompt.confirmInfo({message:'确定删除该分包商吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../subcontractorcs/deleteSubContract",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{topartyid:id,tablename:"party","tableid":id}, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			window.location.reload();
		 		}
		 		else{
		 			ymPrompt.alert({"title":"提示","message":"删除成功"});
		 		}
		 	},
			error:function(){
		 		ymPrompt.alert({"title":"提示","message":"删除失败"});
			}
		});
		}else{
			return false;
		}
		}
		});
}
function btnAdd(){
	window.location="../subcontractorcs/subcontractor_add?order=14";
}
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function focusinput(obj) {
	if(obj.value=="请输入关键字"){
		obj.value = "";
	}
	obj.style.border="1px solid #A0CFE0";
}
function mouseoutinput(obj) {
	if(obj.value==""){
		obj.value = "请输入关键字";
	}
	obj.style.border="1px solid #d3d7d4";
}
function f_hgrid_create(pagerow){//初始化
	//参数为每页行数 
	var txt='<div class="page_nav" style="padding:3px 3px 3px 3px;">'+
		'<span id="pageinfo"></span>'+
		'<span id="pagetop"><a href="#">首页</a></span>'+
		'<span id="pagepre"><a href="#">上一页</a></span>'+
		'<span id="page1"><a href="#"></a></span>'+
		'<span id="page2"><a href="#"></a></span>'+
		'<span id="page3"><a href="#"></a></span>'+		
		'<span id="page4"><a href="#"></a></span>'+		
		'<span id="page5"><a href="#"></a></span>'+		
		'<span id="page6"><a href="#"></a></span>'+		
		'<span id="page7"><a href="#"></a></span>'+		
		'<span id="page8"><a href="#"></a></span>'+		
		'<span id="page9"><a href="#"></a></span>'+			
		'<span id="pagenext"><a href="#">下一页</a></span>'+
		'<span id="pagebottom"><a href="#">末页</a></span>'+
		'<input type="hidden" id="pagecode" value="1"/>'+ 
		'<input type="hidden" id="pageparam" value=""/>'+
		'<input type="hidden" id="pagerow" value="'+pagerow+'"/>'+
		'<input type="hidden" id="recordcount" value="0"/>'+
		'</div><div style="height: 100px;"></div>'
	
		$(".page_nav").remove();
		$("#loading").remove();
		$('#hgrid_div').after(txt); //after
		$('#hgrid').after('<div id="loading" style="text-align:center;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

	$("#pagetop a").click(function(){ //首页
		window.scrollTo(0,0);
		$("#pagecode").val("1");	 
		f_hgrid_json(""); 
		return false; 
	});
	
	$("#pagenext a").click(function(){//下一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val())+1;
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode>parseInt((recordcount-1)/pagerow)+1){pagecode=parseInt((recordcount-1)/pagerow)+1;}
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false;
	});
	
	$("#pagepre a").click(function(){ //上一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		pagecode=pagecode-1;
		if (pagecode<1) pagecode=1;
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false; 
	});
	
	$("#pagebottom a").click(function(){ //末页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode!=parseInt((recordcount-1)/pagerow)+1){
			pagecode=parseInt((recordcount-1)/pagerow)+1;
			$("#pagecode").val(pagecode);
			f_hgrid_json("");
			}
		return false; 
	});

	for (var i=1;i<=9;i++){
		$("#page"+i+" a").click(function(){ 
			window.scrollTo(0,0);
			$("#pagecode").val($(this).text());
			f_hgrid_json("");
			return false; 
		});
	}

}
function f_hgrid_setmsg(data){//data为json数据 
	//var recordcount=data['list'][0].map[0]['entry'][0]['string'][1]; //总记录数
    $(".myjl").remove();
    //	var recordcount=data[0].recordcount;//总记录数
	if(data==null||data=='undefined'){	
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
		}
	else{
	var recordcount=data.Count;//总记录数
	if(recordcount==0||recordcount==''){
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
	}
	else{
	$("#hgridDiv").css("overflow-x","scroll");
	$(".page_nav").show();	
 	$("#recordcount").val(recordcount);//设置总记录数
	var pagecode=$("#pagecode").val(); //当前页
	var pagerow=$("#pagerow").val(); //每页行数
	var pagecount= parseInt((recordcount-1)/pagerow+1); //总页数
	$("#pageinfo").text("共"+recordcount+"条 第"+pagecode+"/"+pagecount.toString()+"页");
	var pagemin=pagecode-4; //第n页最小
	if (pagemin<1) pagemin=1;
	var pagemax=pagemin+8;//最大
	if (pagemax>pagecount) pagemax=pagecount;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	//$("strong").remove();
	for (var i=1;i<=9;i++){ //设置4+1+4个对象
		if (pagemin<=pagemax) {
			$("#page"+i+" a").text(pagemin);
			$("#page"+i).show();
			if (pagemin==pagecode) $("#page"+i+" a").wrapInner("<strong></strong>");			
			}
		else 
			$("#page"+i).hide();
		pagemin=pagemin+1;
	}
	}
	}
	return data.Data; 
}
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
	 		opent = '<option value="" selected="selected">全部</option>';
 			for(var i=0;i<dataObj.length;i++){
 				opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
	 		}
	 		$("#"+className).html(opent);
	 		initSelect(className);
	 	}
	});
}
function checkString(str){
	 if(str=='请输入关键字'){
	 	str="";
	 }
	 return str;
}