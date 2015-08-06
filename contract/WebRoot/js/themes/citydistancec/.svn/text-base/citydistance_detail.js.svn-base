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
			url : "../citydistancecs/citydistance_detail_json",
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
					var txtRow='<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')"></tr>';
				 	$("#hgrid").append(txtRow);
					var txtCell = '<td class="tdleft" width="40px">&nbsp;'+(i+1) +'</td>'
								+ '<td class="tdleft" width="150px">'+ data[i].fromaddress + '</td>'
								+ '<td class="tdleft" width="150px">'+ data[i].toaddress + '</td>'
								+ '<td class="tdcenter" width="140px">'+ data[i].distance + '</td>'
								+ '<td class="tdleft" width="150px">'+ data[i].organization+ '</td>';
					$('#tr' + i).empty().append(txtCell);
				}
				$("#hgrid").show();
			}
		});
}