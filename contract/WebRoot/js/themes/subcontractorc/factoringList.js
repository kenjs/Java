/*****列表页表头******/
function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:3px;"  width="220px" class="tdleft">&nbsp;分包商名称</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;"  width="140px" class="tdleft">银行保理状态</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="140px" class="tdleft">银行监管账号</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="140px" class="tdleft">组织机构代码证</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="180px" class="tdleft">操作</th>' +
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
function f_hgrid_json(param){//刷新表格数据
	param = f_hgrid_getparam(param);//得到全部参数
	//alert(param)
	$.ajax({
		url:"../subcontractorcs/factoringList_json",
		type:"post",
		dataType:"json",
		data:param,
		success:function(data){
		$('#loading').hide(); 		
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
 		var txt;
 		var opera;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft" ><a href="javascript:void(0);" onclick=btnDetail('+data[i].partyid+') style="cursor:pointer">'+data[i].organization+'</a></td>'+		
			'<td class="tdleft">'+data[i].status+'</td>'+  
		 	'<td class="tdleft">'+ data[i].yhjgzh+ '</td>'+
			'<td class="tdleft">'+data[i].zzjgdmz+'</td>';
			if(data[i].status == '启用'){
				opera = '<td class="tdleft"><a href="javascript:void(0);" onclick=btnDetail("'+data[i].partyid+'") style="margin-left:5px;">详情</a>'+
				' <a href="javascript:void(0);" onclick=btnUpdateStatus("'+data[i].partyid+'",1)>停用</a>'+
				'</td>';
			} else {
				opera = '<td class="tdleft"><a href="javascript:void(0);" onclick=btnDetail("'+data[i].partyid+'") style="margin-left:5px;">详情</a>'+	
				' <a href="javascript:void(0);" onclick=btnUpdate("'+data[i].partyid+'","'+data[i].status+'")>修改</a>'+
				' <a href="javascript:void(0);" onclick=btnUpdateStatus("'+data[i].partyid+'",0)>启用</a>'+
				'</td>';
			}
			txt = txt+opera;
			$('#tr'+i).empty().append(txt);
		}
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
	}
	});
	
}
function f_searchclick(){
	$("#pagecode").val("1");
	var status = $('.status div span').text();
		if(status=="全部"){
			status="";
		}
	var topartyname =$("#topartyname").val();
	var params="organization="+topartyname+"&status="+status+"&partytype=分包";
	f_hgrid_json(params);
}
function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	return date.substr(0,index);
}
function btnDetail(partyid){
	var url = "../subcontractorcs/factoringDetail?order=24&partyid="+partyid;
	window.open(url, "_blank");
}
function btnUpdate(partyid,ztatus){
	if(ztatus == '启用'){
		ymPrompt.alert("只有在“停用”状态允许修改信息！");
	}else{
		var url = "../subcontractorcs/factoring_update?order=24&partyid="+partyid;
		window.location.href=url;
	}
}
function btnUpdateStatus(partyid,bz){
	//alert(partyid+" "+bz)
	var str = "";
	if(bz==0){
		status = "启用";
	}else {
		status = "停用";
	}
	str = "是否"+status+"银行保理业务？"
	ymPrompt.confirmInfo({message:str,autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status1){
		if(status1=='ok'){
			$.ajax({
				url: "../subcontractorcs/updateStatus", 
				type:"post",
				dataType:"json",
				data:{"topartyid":partyid,"status":status},
				success:function(data){
					if(data.msg=="ok"){
						f_searchclick("");
						ymPrompt.alert(status+"成功！");
					}else{
						ymPrompt.alert(status+"失败！");
					}
				},
				error:function(){
					ymPrompt.alert("作废失败！");
				}
		});
		}else{
			return false;
		}
		}
		})
}
/******************************详情 新增 修改**************************************/
function getWaybillid(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("=");
	url_Data=url_Data.substr(num+1);
	$("#waybillid").val(url_Data.split("=")[1]);
}
function f_hgrid_ini_fhf() {//初始化表格
	var pagerow=99;  //参数为每页行数
	//f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col"   width="40%">所属发货方名称</th>' +
		'<th scope="col"   width="30%" >是否允许银行保理</th>' +
		'<th scope="col"   width="30%" >账期（天）</th>' +
		'</tr>';
	for(var i =0;i<pagerow+1;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}
/**
 * hcm
 * 填充详情
 */
function fillDetail(){
	var id = getFrompartyid();
	//alert(id)
	$.ajax({
	 	url: "../subcontractorcs/subcontractor_detail_json",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"partyid":id,"tablename":"party","tableid":getid}, //参数     	               
	   	success:function(data){//回传函数
			//$("#topartyname").html(data.partyname);
			$("#topartyname").html(data.organization);
			var params="topartyid="+id;
			$.ajax({
				url: "../subcontractorcs/factoringDetail_json",
			 	type:'post',	
			 	dataType:'json', 
			 	data:params, //参数                   
			   	success:function(data){
				$.each(data,function(k,obj){
					$('#yhjgzh').html(obj.yhjgzh);
					$('#zzjgdmz').html(obj.zzjgdmz);
				});
				$.ajax({
					url: "../bankfactoringcs/factoringDetailFhf_json2",
				 	type:'post',	
				 	dataType:'json', 
				 	data:params, //参数                   
				   	success:function(data){
					var txt = "";
					$.each(data,function(k,obj){
						var str = "";
						if(obj.isenabled==0){
							str = "否";
						}else{
							str = "是";
						}
						txt = txt +
						'<tr>'+'<td>'+obj.frompartyname+'</td>'+
							'<td>'+str+'</td>'+
							'<td>'+obj.businessdays+'</td>'+
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
function getFrompartyid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	return idUrl.split("=")[1];
}
function f_add(){
	var url = "../subcontractorcs/factoring_add?order=24";
	window.location.href=url;
}
function backBtn(){
	window.close();
}