$(function(){
	var url = document.URL;
	var par = url.split("?")[1].split("&")[1].split("=")[1];
	$("#stowage_num").text(par);
	$("#carbegindate").val(myDates("detetime"));
});
function btnSave(){
	var waybillstowageid=document.URL.split("?")[1].split("&")[0].split("=")[1],
		carbegindate=$("#carbegindate").val(),systemdispatchnumber=document.URL.split("?")[1].split("&")[1].split("=")[1];
	$.ajax({
		url:'../dispatchtrackcs/carloadconfirm_save',
		type:'post',
		dataType:'json',
		data:{"waybillstowageid":waybillstowageid,"carloaddate":carbegindate,"systemdispatchnumber":systemdispatchnumber},
		success:function(data){
			if(data.msg=='ok'){
				window.parent.ymPrompt.doHandler('load_ok',true);
			}else{
				window.parent.ymPrompt.doHandler('load_fail',true);
			}
		},
		error:function(){
			window.parent.ymPrompt.doHandler('load_fail',true);
		}
	});
}

function myDates(vales) {
	var str = vales;
	var detetime = '';
	var myDate = new Date();
	var yyyy = myDate.getFullYear();//获取完整的年份(4位,1970-????)
	var MM = myDate.getMonth();//获取当前月份(0-11,0代表1月)
	var dd = myDate.getDate();//获取当前日(1-31)
	var HH = myDate.getHours(); //获取当前小时数(0-23)
	var mm = myDate.getMinutes();//获取当前分钟数(0-59)
	var ss = myDate.getSeconds();    //获取当前秒数(0-59)
	if(str == 'dete') {//日期
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		detetime = yyyy+'-'+MM+'-'+dd;
	}
	if(str == 'time') {//时间
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = HH+':'+mm+':'+ss;
	}
	if(str == 'detetime') {
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = yyyy+'-'+MM+'-'+dd+" "+HH+':'+mm+':'+ss;
	}
	return detetime;
}