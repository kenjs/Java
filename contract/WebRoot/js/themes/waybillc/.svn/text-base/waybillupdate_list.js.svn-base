$(function(){
	f_hgrid_ini();// 加载列表
	loadQuery();  // 查询数据
	$(".page_nav").css("margin-top","20px");
	$(".page_nav").css("text-align","right");
	//移出翻页层
	$(".page_nav").next().remove();
	$(".page_nav").insertAfter("#hgrid_div");
	$("#hgrid_div .page_nav").remove();
	$(".page_nav").css("text-align","right");
	$(".page_nav").css("margin-top","10px");
});

function loadQuery(){
	 $("#pagecode").val("1");
	 var waybillnumber=trim($("#waybillnumber").val());
	 var clientnumber = trim($("#clientnumber").val());
	 var frompartyname=trim($("#frompartyname").val());
	 var topartyname=trim($("#topartyname").val());
	 var fromdate=trim($("#fromdate").val());
	 var todate=trim($("#todate").val());
	 var status = $("#status").val();
	 var params="waybillnumber="+waybillnumber+"&clientnumber="+clientnumber+"&frompartyname="+frompartyname+
	 	"&topartyname="+topartyname+"&fromdate="+fromdate+"&todate="+todate+"&status="+status+"&fromQuery="+"waybillupdate";
	 f_hgrid_json(params);
}

function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为1000px
	var txt='<tr>' +
			'<th width="120px" class="tdleft" style="height:30px;padding-left:10px;">运单编号</th>' +
			'<th width="40px" class="tdleft" style="height:30px;padding-left:10px;">状态</th>' +
			'<th width="90px" class="tdleft" style="height:30px;padding-left:10px;">客户单号</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:10px;">托运日期</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:10px;">计费状态</th>' +
			'<th width="50px" class="tdcenter" style="height:30px;padding-left:10px;">录单人</th>' +
			'<th width="120px" class="tdleft" style="height:30px;padding-left:10px;">发货方</th>' +
			'<th width="140px" class="tdleft" style="height:30px;padding-left:10px;">分包商</th>' +
			'</tr>';
	for(var i =0;i<pagerow+1;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr"';
	 	txt=txt+'></tr>';
	}
	$("#hgrid").empty().append(txt);
	var txt='<tr>' +
	'<th scope="col" style="height:30px;padding-left:0px;width:160px" class="tdcenter">操作</th>' +
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" >';
	 	txt=txt+'</tr>';
	}
	$("#operation").empty().append(txt);
 	$('.ctr').hover(function(){
 		$(this).css("background-color","#fffddd");
 	},function(){
 		var indexvalue=$(this).index();
 		$(this).css("background-color","#fff");
 	});
}	

function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../waybillcs/waybillUpdateList", 
	 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数  
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt,i;
			for(i=0;i<data.length;i++){ //展现返回的表格数据
				var checkStr='';
				txt='<td class="tdleft" style="color:#1560ea;cursor:pointer">'+checkStr+'<a href="#" onclick=btnDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+	
					'<td class="tdleft">'+data[i].status+'</td>'+ 
				 	'<td class="tdleft">'+ data[i].clientnumber+'</td>'+  
					'<td class="tdleft">'+ format(data[i].consigndate)+'</td>'+  
					'<td class="tdleft">'+data[i].billstatus+'</td>'+ 
				 	'<td class="tdleft">'+ data[i].inputman+'</td>'+  
				 	'<td class="tdleft">'+ data[i].frompartyname+'</td>'+ 
				 	'<td class="tdleft">'+ data[i].topartyname+'</td>';
				var txt1 = '<td style="text-align:center;color:#444444;"><a id="btnAmountDetail" href="javascript:void(0);" onclick=btnAmountDetail("'+data[i].waybillid+'") style="margin-left:10px;color:#1560ea;">费用详情</a>';
				//已作废运单控制不允许操作 add by xiayao 2014-2-25
				if(data[i].status != "已作废"){
					txt1 = txt1 + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnUpdate" href="javascript:void(0);" onclick=btnUpdate("'+data[i].waybillid+'") style="margin-left:3px;color:#1560ea;">修改</a>'+
				 			 '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnCancel" href=\"javascript:void(0);\" onclick=btnCancel("'+data[i].waybillid+'"); style="margin-left:3px;color:#1560ea;">作废</a></td>';
				}
			 	$('#tr'+i).empty().append(txt);
			 	$('#czTr'+i).empty().append(txt1);	      
		     }
			 var len = data.length;
			 if(len==0){
				 $("#recordcount").val("0");
			 }
			 var pagerow=$("#pagerow").val(); //每页行数
			 for(var y = len;y<pagerow+1;y++){
			 	$('#tr'+y).empty();
			 	$('#czTr'+y).empty();
			 }
          },
          error : function(XMLHttpRequest, textStatus, errorThrown) {
			  //session已失效 返回登陆页面
        	  window.location.href="../waybillcs/waybillUpdateList?order=23";
		}
	  }); 
}

/**
 * 调度单详情
 * @param waybillstowageid
 * @return
 */
function detailShow(waybillstowageid){
	var url = '../dispatchtrackcs/waybillstowage_detail?waybillstowageid='+waybillstowageid;
	window.open(url, "_blank");
}

//费用详情
function btnAmountDetail(id){
	var url = "../waybillamountcs/waybillamountDetail?order=5&waybillid="+id;
	window.open(url, "_blank");
}

//运单修改
function btnUpdate(id){
	//window.location.href="../waybillcs/waybillupdate_edit?waybillid="+id;
	var url = "../waybillcs/waybillupdate_edit?waybillid="+id;
	window.open(url,"_blank");
}

//运单作废
function btnCancel(id){
	ymPrompt.confirmInfo({message:'确定作废吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
			if(status=='ok'){
				$.ajax({
					url: "../waybillcs/delete", 
					type:"post",
					dataType:"json",
					data:{"waybillid":id},
					success:function(data){
						if(data.msg=="ok"){
							f_hgrid_json("");
							ymPrompt.alert("作废成功！");
						}else{
							ymPrompt.alert("作废失败！");
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
//详情
function btnDetail(id){
	var url = "../waybillcs/waybill_detail?order=3&waybillid="+id;
	window.open(url,"_blank");
}

function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
}

function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	return date.substr(0,index);
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
