function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为840px
	var txt='<tr>' +
		'<th scope="col"   width="180px" class="tdleft">&nbsp;发货方名称</th>' +
		'<th scope="col"   width="100px" class="tdleft">助记码</th>' +
		'<th scope="col"   width="100px" class="tdleft">客户号</th>' +
		'<th scope="col"   width="100px" class="tdleft">会员名</th>' +
		'<th scope="col"   width="100px" class="tdleft">线路条数</th>' +
		'<th scope="col"   width="140px" class="tdleft">最后编辑时间</th>' +
		'<th scope="col"   width="120px" class="tdleft">操作</th>' +
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
	 	$.ajax({
	 	url: "../citydistancecs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft">&nbsp;'+data[i].organization+'</td>'+
					'<td class="tdleft">'+data[i].helpcode+'</td>'+					
					'<td class="tdleft">'+data[i].attributeValue+'</td>'+
				 	'<td class="tdleft">'+data[i].partyname+'</td>'+
				 	'<td class="tdleft">'+data[i].routeCount+'</td>'+					
					'<td class="tdleft">'+data[i].updateDate+'</td>'+
				 	'<td class="tdleft"><a href="javascript:void(0)" onclick="detail(&quot;'+data[i].organization+'&quot;,&quot;'+data[i].routeCount+'&quot;,&quot;'+data[i].fromPartyId+'&quot;)">详情</a>&nbsp'+
				 	'<a href="javascript:void(0)" onclick="edit(&quot;'+data[i].fromPartyId+'&quot;,&quot;'+data[i].organization+'&quot;)">修改</a>&nbsp'+
				 	'<a href="javascript:void(0)" onclick="deleteCityDistance(&quot;'+data[i].fromPartyId+'&quot;,&quot;'+data[i].partyId+'&quot;)">删除</a></td>'; 
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
 * @author wei.huang
 * @date 2013-11-07
 * @function 根据发货方名称和会员名查询城区距离列表
 * @return
 */
function selectListByOrganizationAndPartyname(){
	$("#pagecode").val(1);
	var param="organization="+$.trim($("#organization").val())+"&partyname="+$.trim($("#partyname").val());
	f_hgrid_json(param);
}
function insert(){
	window.location="../citydistancecs/citydistance_add?order=18&";
}
function detail(organization,routeCount,partyId){
	var data="frompartyid="+partyId+"&sender="+organization+"&routenum="+routeCount+"&random="+Math.random();
	ymPrompt.win({message:"../citydistancecs/citydistance_detail?"+encodeURI(data),width:670,height:450,fixPosition:true,dragOut:false,title:'城区距离详情',iframe:true});
}
function edit(fromPartyId,organization){
	var params=encodeURI("frompartyid="+fromPartyId+"&organization="+organization);
	window.location="../citydistancecs/citydistance_edit?order=18&"+params;
}
/**
 * @author wei.huang
 * @date 2013-11-07
 * @function 根据发货方名称和会员名查询城区距离列表
 * @return json数据
 */
function deleteCityDistance(frompartyid,partyid){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../citydistancecs/deleteByFromPartyIdAndPartyId", 
				type:"post",
				dataType:"json",
				data:{"frompartyid":frompartyid,"partyid":partyid},
				success:function(data){
					if(data.msg=="ok"){
						f_hgrid_json("");
						ymPrompt.alert("删除成功！");
					}else{
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
