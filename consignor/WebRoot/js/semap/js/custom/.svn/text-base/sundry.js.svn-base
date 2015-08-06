
/**
* 对Date的扩展，将 Date 转化为指定格式的String
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
* eg:
* (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2007-07-02 08:09:04.423
* (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2007-03-10 二 20:09:04
* (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2007-03-10 周二 08:09:04
* (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2007-03-10 星期二 08:09:04
* (new Date()).format("yyyy-M-d h:m:s.S") ==> 2007-7-2 8:9:4.18
*/
Date.prototype.format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        //月份
        "d+": this.getDate(),
        //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
        //小时
        "H+": this.getHours(),
        //小时
        "m+": this.getMinutes(),
        //分
        "s+": this.getSeconds(),
        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f": "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

/**
 * 处理复杂参数的情况用对象形式提交，无等待图标
 * @param url 提交的url
 * @param postdate 提交的数据
 * @param targetBlock 返回展示的divid
 * @param inchingTime 执行延时
 * @param callBack 回调方法
 */
function sundry_ajax_json_noimage(url,postdate,targetBlock,inchingTime,callBack) {
	var randomseed = Math.round(Math.random()*10000000) ; // random

	var htmlUrlString = url;

	if( htmlUrlString.indexOf('?') == -1 )
		htmlUrlString += '?randomseed='+randomseed ;
	else
		htmlUrlString += '&randomseed='+randomseed ;

	var arg = [];
	if( callBack != null ) {
		for(var i=5; i<arguments.length; i++) {
			arg.push(arguments[i]);
		}
	}
	
	var buffer_error = "没有记录...";

	$(targetBlock).empty();//hide the target block
	
	$.ajax({
		url: htmlUrlString,
		type:'post',
		data:postdate,
		dataType: 'html',
		timeout: 50000,
		cache: false,
		success: function(data) {
			$(targetBlock).html(data);
			if( callBack != null )
				callBack.apply(this, arg);
		},
		error: function (request, textStatus, errorThrown) {
			$(targetBlock).html(buffer_error);
			$(targetBlock).show(inchingTime);//hide the target block
		}
	});//getting html pages
   	
	$(targetBlock).show(inchingTime);//show the target block
}

/**
* js获取屏幕分辨率-屏幕大小
* @return Array数组
* 0 屏幕分辨率
* 1 屏幕可用
* 2 网页可见区域
* 3 网页可见区域(包括边线)
* 4 网页正文全文
* 5 网页被卷去
* 6 网页正文部分
* 7 屏幕分辨率
* 8 屏幕可用工作区
**/
function sundry_screenwh(){
	var arr_all = new Array();
	arr_all.push(screen.width+"*"+screen.height);//屏幕分辨率
	arr_all.push(screen.availWidth+"*"+screen.availHeight);//屏幕可用
	arr_all.push(document.body.clientWidth+"*"+document.body.clientHeight);//网页可见区域
	arr_all.push(document.body.offsetWidth+"*"+document.body.offsetHeight);//网页可见区域(包括边线)
	arr_all.push(document.body.scrollWidth+"*"+document.body.scrollHeight);//网页正文全文
	arr_all.push(document.body.scrollTop+"*"+document.body.scrollLeft);//网页被卷去
	arr_all.push(window.screenTop+"*"+window.screenLeft);//网页正文部分
	arr_all.push(window.screen.width+"*"+window.screen.height);//屏幕分辨率
	arr_all.push(window.screen.availWidth+"*"+window.screen.availHeight);//屏幕可用工作区
	return arr_all;
}

/**
* 指定div id 进行全屏显示
* @param divid div的id号
* @param wp 宽度偏移量 number
* @param hp 高度偏移量 number
*/
function sundry_fillscreen(divid,wp,hp){
	wp = (( wp == 'undefined' || wp == null ) ? 0 : wp);
	hp = (( hp == 'undefined' || hp == null ) ? 0 : hp);
	$('#'+divid).css('width',(document.body.clientWidth + wp ) + "px");
	$('#'+divid).css('height',(document.body.clientHeight + hp ) + "px");
}

/**
* ul 下面li背景颜色用两种颜色交替
* @param class1 样式一 string
* @param class2 样式二 string
*/
function sundry_initUL(class1,class2){
	var ul = document.getElementsByTagName('ul');
	for(var i = 0;i < ul.length; i++){
		var temp = 1;
		var li = document.getElementsByTagName('li');
		for(var j = 0;j < li.length; j++){
			if(li[j].parentNode == ul[i]){
				if(temp++ % 2 == 0){
					li[j].className = class1;
				}else{
					li[j].className = class2;
				}
			}
		}
	}
}

/**
* 对数字进行保留几位小数处理
* @param src 数字
* @param pos 保留小数
*/
function sundry_formatFloat(src, pos){
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}

/**
* 每500毫秒刷新画布大小
* @param id 画布id
* @param timeout 延时时间
*/
var sundry_mapCanvas_wh = null;
function sundry_mapCanvas(id,timeout){
	sundry_mapCanvas_wh = setInterval(function(){
		var map_main = document.getElementById(id);
		if(map_main){
			var ywidth = Number(($(map_main).css('width')).substring(0,($(map_main).css('width')).length-2)); // 原来宽度
			var yheight = Number(($(map_main).css('height')).substring(0,($(map_main).css('height')).length-2)); // 原来高度
			var xwidth = Number(document.body.offsetWidth); // 现在宽度
			var xheight = Number(document.body.offsetHeight) - 30; // 现在高度
			
			if(ywidth!=xwidth){ // 宽度更改
				var cyw = ywidth - xwidth; // 宽度差
				$(map_main).css('width',xwidth+'px');// 更改画布操控区宽度
			}
			if(yheight!=xheight){ // 高度更改
				var cyh = ywidth - xheight; // 宽度差
				$(map_main).css('height',xheight+'px');// 更改画布操控区高度
			}
		}
	},timeout);
}

