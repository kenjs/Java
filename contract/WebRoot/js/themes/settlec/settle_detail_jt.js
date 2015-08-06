function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {// 刷新hGrid数据
	$("#hgrid").hide();
	$("#hgrid").empty();
	$("#loading").css("margin-top", "80px");
	$.ajax( {
			url : "../settlecs/settle_detail_jt_json",
			type : 'post',
			dataType : 'json',
			data : param, // 参数
			success : function(data) {// 回传函数
				$("#loading img").hide();
				if (data.Count == "0") {
					$("#loading").append('<img style="margin-bottom:-10px;" src="../imgs/sys/notice-icon.png"/><span style="margin-left:10px;margin-bottom:20px;color:#3f3f3f;">暂无数据，</span><a href="#" style="margin-left:10px;margin-bottom:20px;color:#1560ea;">立即添加</a>');
					return;
				}
				$("#loading").hide();
				data=data.Data;
				for ( var i = 0; i < data.length; i++) { // 展现返回的表格数据
					var txtRow='<tr height="10px" id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')"></tr>';
				 	$("#hgrid").append(txtRow);
				 	var txtCell = "";
				 	if(i==0){
				 		txtCell = '<td class="tdleft" width="150px">'+ data[i].fromaddress + '</td>'
								+ '<td class="tdleft" width="150px">'+ data[i].toaddress + '</td>'
								+ '<td class="tdleft" width="90px">'+ data[i].billtype + '</td>';
				 	}else{
				 		if(data[i].settlesetid == data[(i-1)].settlesetid){
				 			txtCell = '<td class="tdleft" width="150px">&nbsp;</td>'
									+ '<td class="tdleft" width="150px">&nbsp;</td>'
									+ '<td class="tdleft" width="90px">&nbsp;</td>';
				 		}else{
				 			txtCell = '<td class="tdleft" width="150px">'+ data[i].fromaddress + '</td>'
									+ '<td class="tdleft" width="150px">'+ data[i].toaddress + '</td>'
									+ '<td class="tdleft" width="90px">'+ data[i].billtype + '</td>';
				 		}
				 	}
				 	var value = data[i].startvalue;
				 	if(data[i].endvalue==""){
				 		value = value + " 以上";
				 	}else{
				 		value = value + " ~ " + data[i].endvalue;
				 	}
				 	txtCell = txtCell + '<td class="tdleft" width="160px">'+ value +'</td>'
				 			+ '<td class="tdleft" width="100px">'+ data[i].unitprice + '</td>';
					$('#tr' + i).empty().append(txtCell);
				}
				$("#hgrid").show();
			}
		});
}