function f_hgrid_ini() {//初始化表格
	var pagerow=8;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col"   width="80px" class="tdcenter">选择</th>' +
		'<th scope="col"   width="100px" class="tdcenter">会员名</th>' +
		'<th scope="col"   width="100px" class="tdcenter">助记码</th>' +
		'<th scope="col"   width="200px" class="tdcenter">发货方名称</th>' +
		'<th scope="col"   width="100px" class="tdcenter">主要联系人</th>' +
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
/**
 * wei.huang
 * 2013-10-21
 * 选择发货方的列表
 */
function f_hgrid_json(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	var params=f_hgrid_getparam(param); //得到全部参数
 	$.ajax({
 	url: "../subcontractorcs/subcontractorList_json",  
 	type:'post',	
 	dataType:'json', 
 	data:params, //参数     	               
   	success:function(data){//回传函数
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i=0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdcenter"><input type="radio" class="radio" name="partyid" value="'+data[i].partyid+'"/></td>'+	
	 		'<td class="tdcenter">'+ data[i].partyname+ 
			'<td class="tdcenter">'+data[i].helpcode+'</td>'+  
			'<td class="tdcenter" style=";padding-left: 15px">'+data[i].organization+'</td>'+  
			'<td class="tdcenter">'+ data[i].legalperson+'</td>'
	 	$('#tr'+i).empty().append(txt);			      
	     }
		var pagerow=$("#pagerow").val(); //每页行数
		for(var i=data.length;i<pagerow;i++){
			$('#tr'+i).empty();
		}
		
		 $("tr[id^='tr']").click(function(){
			 $(this).find("input[name='partyid']").attr("checked",true);
		 });
      }
  }); 
}
function f_Sure(){
	var partyid=$('input[name="partyid"]:checked').val();
	if(partyid==undefined){
		alert("请选择相关发货方");
	}else{
		window.parent.getpartyInfo(partyid);
		window.parent.ymPrompt.doHandler('close',true);
	}
	
}
function f_Cancel(){
	window.parent.ymPrompt.doHandler('close',true);
}
/**
 * wei.huang
 * 2013-10-21
 * 获取URL地址中的参数（仅一个）
 * @return
 */
function getData(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("=");
	url_Data=url_Data.substr(num+1);
	return url_Data;
}
/**
 * wei.huang
 * 2013-10-21
 * 根据条件查询发货方会员
 * @return
 */
function f_SearchClick(){
	var organization=$.trim($("#consignor").val());
	var partyname=$.trim($("#partyname").val());
	var params1="organization="+organization+"&partyname="+partyname+"&partyid="+$("#currentPartyId").val()+"&partytype=发货方";
	$("#pagecode").val("1");
	f_hgrid_json(params1);
}