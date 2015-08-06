﻿var f_js_encode=function(vcStr){
	//用于编码成适合URL传递的值
	vcStr=f_js_replaceAll(vcStr, "'", "-"); //防止用于SQL
	vcStr=f_js_replaceAll(vcStr, "&", "-"); //防止URL参数
	vcStr=f_js_replaceAll(vcStr, "=", "-"); //防止URL参数
	vcStr=encodeURI(vcStr, "utf-8");		
	vcStr=encodeURI(vcStr, "utf-8");		
	return vcStr;
}


// 检查输入的一串字符是否为小数
function checkDecimal(vcStr){
    if (vcStr.match(/^-?\d+(\.\d+)?$/g) == null) {
        return false;
    }
    else {
        return true;
    }
}

//检查输入的手机号码格式是否正确
function checkHandset(vcStr){
    if (vcStr.match(/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/) == null) {
        return false;
    }
    else {
        return true;
    }
}

//检查日期格式是否正确
function checkDate(vcStr){
    var value = vcStr.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (value == null) {
    	      alert(vcStr +" 不是日期类型");
        return false;
    }
    else {
        var date = new Date(value[1], value[3] - 1, value[4]);
        if (date.getFullYear() == value[1] && (date.getMonth() + 1) == value[3] && date.getDate() == value[4])
        	{alert(vcStr +" true");
        	return true;}
       	else
       		{alert(vcStr +" 不是日期类型");
       		return false;}
    }
}

//检查全日期时间格式是否正确
function checkDatetime(vcStr){
	 //vcStr格式: 2007-06-05 10:57:10
    var value = vcStr.match(/^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/);
    if (value == null) {
        return false;
    }
    else {
        return true;
    }   
}

function f_js_setChar(vcID, vcLen){
	//设置字符最大长度
	if (isNaN(vcLen)) vcLen="0";//不是数字
	vcLen=parseInt(vcLen)
	if (vcLen<=0) vcLen=4000;
	$("#"+vcID).attr("maxlength", vcLen);
}

function f_js_setDdw(vcShow, vcColID, vcAction){
	//创建下拉列表, vcShow显示列控件名称, vcColID是数据列
	//1.判断iClientID是否存在,如果不存在则创建 用于提交数据列 隐藏对象
	//2.自动创建 cClient_ddw_name,用于存储修改后名称是否变化 隐藏对象
	//vcColID=""表示值必须对应 =null表示可修改值
	//alert(vcShow+' '+vcColID+' '+vcAction);
	var idNull=0;
	if (vcColID==null) {idNull=1; vcColID="";} //可以改变值
	if (vcColID!="") {
		if ($("#"+vcColID).length<=0) vcColID=""; //不存在数据列对象
	}
	var cShowTmp; //用于存储显示名是否一致
	cShowTmp=$("#"+vcShow).val(); //初始值
	
	//创建改变事件
	if (idNull==0) {
		$("#"+vcShow).change(function(){
			if ($("#"+vcShow).val() !=cShowTmp ) { //显示的名称与保存的保存不同 开始
				 $("#"+vcShow).val("");  //置空三者
				 if (vcColID!="") $("#"+vcColID).val("");
				 cShowTmp="";
		     } //显示的名称与保存的保存不同 结束
		});	
	}

	//取数
	$.getJSON(vcAction, function(data) {
		$("#"+vcShow).autocomplete(data, {
				scrollHeight: 280,
				matchContains: true,
				formatItem: function(row, i, max) {
					return ""+row.name +" " + row.help +"" ;
				},
				formatMatch: function(row, i, max) {
					return row.name + " " + row.help;
				},
				formatResult: function(row) {
					return row.name;
				}
			}).result(function(event,item) {//值已改变事件 开始
				if(item){	
				   if (vcColID!="") $("#"+vcColID).val(item.id); //存储ID
				   cShowTmp=item.name; //存储显示名
				   $("#"+vcShow).trigger("evtChange"); //改变事件 
				   }
				}); //值已改变事件 结束
	});  //getJSON结束
}
	


function f_js_setDdl(vcShow, vcAction){
	//创建下拉列表, vcShow显示列控件名称, vcColID是数据列
	//2.自动创建 cClient_ddw_name,用于存储修改后名称是否变化 隐藏对象
	//取数
	$.getJSON(vcAction, function(data) {
		var iniValue, selectRow=0, iRow=0; //初始值 选中行 初始行;
		iniValue=$("#"+vcShow).attr("val"); //select 对象没有value
		if ((typeof iniValue)!="string" || iniValue==null) {iniValue="";}	//无此类型		
		$("#"+vcShow).append("<option value=''></option>"); //加空行
		for (var j=0;j<data.length;j++){ 
			$('#'+vcShow).append('<option value="'+data[j].id+'">'+data[j].name+'</option>');
			iRow++;
			if (selectRow==0) {
				if (iniValue==data[j].id) selectRow=iRow; //默认选中行
				}
	 		
		} //逐个对比 结束
		setTimeout(function() { //解决IE6出错:无法设置selected属性。未指明的错误 但IE8会有错误
			{if ((typeof $('#'+vcShow).get(0).options)!="undefined") $('#'+vcShow).get(0).options[selectRow].selected=true;}
			}, 1); 		
		
	});  //getJSON结束
}

function  f_js_replaceAll(vcStr, vcOld, vcNew){
	//替换所有字符
	while(vcStr.indexOf(vcOld)!=-1 ) {
		vcStr=vcStr.replace(vcOld, vcNew); 
	}
	return vcStr; 
}


function f_js_charLength(s){
	//判断字符串长度,汉字=2，字符=1
	var w = 0;
	for (var i=0; i<s.length; i++) {
	   var c = s.charCodeAt(i);
	   //单字节加1
	   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
	    w++;
	   }
	   else {
	    w+=2;
	   }
	}
	return w;
}

function f_js_setDec(vcID, viDot) {
	//设置对象的小数位数,viDot只能从0到10间, 其它表示不处理小数
	//viDot 小数位数 注意 当值=0或=空串
	//当值改变时判断
	$("#"+vcID).change(function(){ //方法change开始
		var cNewNum;
		var cNum=$("#"+vcID).val();
		if (cNum=="") return; 
		if (isNaN(cNum)){//不是数字
			$("#"+vcID).val("");
			return;	}
		if (typeof viDot!="number") viDot=parseInt(viDot); //转为数字	
		if (isNaN(viDot)){ //不控制小数
			cNewNum=f_js_replaceAll(cNum, " ", ""); //处理空格
			if (cNewNum!=cNum) $("#"+vcID).val(cNewNum); //含有空格时
			return;} //没有小数位数
		//是数字,检查小数	
		if (viDot<0 || viDot>10) {return;} //只能是０至１０位
		var cArr=cNum.split(".");
		if (cArr.length<=1) cArr[1]=""; //补上小数
		cArr[0]=f_js_replaceAll(cArr[0], " ", ""); //处理空格
		cArr[1]=f_js_replaceAll(cArr[1], " ", "");
		cArr[1]=cArr[1]+"0000000000";
		cArr[1]=cArr[1].substr(0, viDot); //截取
		if (viDot>0) cNewNum=cArr[0]+"."+cArr[1]; else cNewNum=cArr[0];
		if (cNewNum!=cNum) $("#"+vcID).val(cNewNum);
	}); //方法change结束
}



$.fn.f_js_button = function(){
	//设置指定按钮的为图片风格
		this.init = function(){
			var obj = $(this); 			//var id = $(this).attr("id")||$(this).attr('value');
			var id = $(this).attr("id");  //$(this).attr("id", "_"+id);
			$(this).removeAttr("id"); //移除id  用于创建新图标ID
			var icon =id; //图标
			var bntStr=[
				'<table id="'+id+'-p" class="z-btn" cellSpacing=0 cellPadding=0 border=0><tbody><tr>',
					'<td class=z-btn-left><i>&nbsp;</i></td>',
					'<td class=z-btn-center><em unselectable="on">',
						'<button id="',id,
						'" class="z-btn-text ',icon,'" >',$(this).attr('value'),'</button>',
					'</em></td>',
					'<td class=z-btn-right><i>&nbsp;</i></td>',
				'</tr></tbody></table>'
			];
			var bnt = $(bntStr.join('')).f_js_button();
			bnt.disable();
			if(obj.attr("disabled"))
				bnt.disable();
			else bnt.enable();

			$(this).replaceWith(bnt);
			bnt.data("_self", bnt);  
			return bnt;
		};
		
		this.enable = function(){
			this.removeClass("z-btn-dsb");
			this.hover(
				  function () {
				    $(this).addClass("z-btn-over");
				  },
				  function () {
				    $(this).removeClass("z-btn-over");
				  }
				)
		};
		
		this.disable = function(){
			 this.addClass("z-btn-dsb");
			 this.unbind("hover");
			// this.unbind("click");
		};  		
		return this;
};


	


$.fn.f_js_input = function(){
	//设置指定输入框的网络风格
	this.init = function(){
		var format= $(this).attr("format");
		if (format==null) {return this;} 
		var id= $(this).attr("id"); //id
		//只处理len df dot 和ddw ,不处理enu 
		var cPV=format.split(":"); //格式: type:值
		if (cPV.length<2) return; //参数不足
		if (cPV[0]=="dot"){ //数字
			f_js_setDec(id, cPV[1]); 
			return; }
		if (cPV[0]=="df"){ //日期
			f_js_setDate(id, cPV[1]); 
			return; }
		if (cPV[0]=="len"){ //字符最大长度
			f_js_setChar(id, cPV[1]); 
			return; }

		if (cPV[0]=="ddw" || cPV[0]=="ddw_text" ){ //下拉,格式:action,数据列(可为空)
			var cArrDDW=cPV[1].split(",");
			if (cArrDDW.length<2) cArrDDW[1]=""; //无数据列
			if (cPV[0]=="ddw_text") cArrDDW[1]=null; //空表示可以修改值
			f_js_setDdw(id, cArrDDW[1], cArrDDW[0]); //显示列 数据列 action 
			return; }
				

		if (cPV[0]=="ddl"){ //下拉列表,格式:action
			f_js_setDdl(id, cPV[1]); //显示列 数据列 action			 
			return; }
	};	
	return this;
};




function f_js_ini() { //jGrid列表和编辑界面均需执行
	$.ajaxSetup({cache:true}); //不使用缓存

	//初始化按钮
	$("input[type='button']").each(function(){
		$(this).f_js_button().init();
	});

	//初始化输入框,包含查找框  它必须含有format属性 
	$("input[type='text'],select").each(function(){
		$(this).f_js_input().init();
	});

	//初始化下拉框  它没有format属性
//	$("select[val]").each(function(){
//		$(this).f_js_select().init();
//	});

	

	f_js_setEnu();//设置枚举
	setTimeout(function() {
		f_js_Set_defaultValue(0);
		}, 1);  //设置缺省值 需要延迟,解决数据不到位的问题		
			
};
	
		
function f_js_jqGrid_Set(data) {//设置jqGridData  包含页码
	var iCurrentPage=parseInt($("#pagecurrent").val()); //当前页		
	var iAllRecord=data[0].recordcount; //总记录数
	$("#allrecord").val(iAllRecord);//设置总记录数

	var pagerow=$("#pagerow").val(); //每页条数 用户输入值
	
	var iAllPage= parseInt((iAllRecord-1)/pagerow+1);			
	$("#pageinfo").text("共"+$("#allrecord").val()+"条 "+iCurrentPage+"/"+iAllPage+"页");
	if ($("#pageadd").val()==0)	iCurrentPage=iCurrentPage-40; //页码数递增或递减

	iCurrentPage=iCurrentPage+10;
	if (iCurrentPage<iAllPage && iCurrentPage>0) {
		$("#page1 a").text('  '+iCurrentPage+'  ');
		$("#page1").show();}
	else
		$("#page1").hide();

	iCurrentPage=iCurrentPage+10;
	if (iCurrentPage<iAllPage && iCurrentPage>0) {
		$("#page2 a").text('  '+iCurrentPage+'  ');
		$("#page2").show();}
	else
		$("#page2").hide();

	iCurrentPage=iCurrentPage+10;
	if (iCurrentPage<iAllPage && iCurrentPage>0) {
		$("#page3 a").text('  '+iCurrentPage+'  ');
		$("#page3").show();}
	else
		$("#page3").hide();


	//var height=data.length*20+50;
	//$("#main").attr("height","1000");
	$("#tfgrid").clearGridData(); //清除数据	
	data=data[0].data; //
	 for(var i =0;i<data.length;i++){
	     $("#tfgrid").addRowData(i+1,data[i]); }
	 //根据返回的数据条数,设置高度
	 var row=data.length;
	 if (row<15) row=15;
	 var height=parseInt(row*23.25+12);
	 if (height<245) height=245; //最少10行的高度
	 $("#tfgrid").setGridHeight(height); 

}

				 

function f_js_iniQuery() { //初始化查询,适用于jqGrid
    f_js_ini(); //初始化按钮,文本框等
    
    if ($("#btn-refresh").length>0) {//刷新按钮
		$("#btn-refresh").bind("click",function(){
			if ($("#findform").length>0) {//设置查找框中所有数据为空				
 			 	$("#findform input,select").val(""); //置空查找框中数据
			}
			$("#pagecurrent").val(1); //第1页
			$("#pagerow").val(15); //用户每页n条
			$(this).f_get_data();
		});  
 	}

    
	if ($("#btn-search").length>0) {//查找按钮
		$("#btn-search").bind("click",function(){ 
			$("#pagecurrent").val(1); //第1页
			$(this).f_get_data();
		});  
 	}

	/**
	var cwhIE="",cwhFF="",vnavi="";
	var iWidthn,iWidths,iTopn,iTops; //弹出窗口的宽度;
	var iHeightn,iHeights,iLeftn,iLefts; //弹出窗口的高度;
	iWidthn=660; 
	iWidths=790;
	iHeightn=400; 
	iHeights=470;
	iTopn = (window.screen.availHeight-30-iHeightn)/2; //获得窗口的垂直位置;
	iLeftn = (window.screen.availWidth-10-iWidthn)/2; //获得窗口的水平位置;
	iTops = (window.screen.availHeight-30-iHeights)/2; //获得窗口的垂直位置;
	iLefts = (window.screen.availWidth-10-iWidths)/2; //获得窗口的水平位置;
	vnavi=navi();
	
	if(window.gcEditAction){//判断此变量是否定义
		if(gcEditAction=="goodsinfo_edit_jsp.action"){
			if(vnavi.substr(0,2)=="IE"){
				cwhIE='dialogWidth:'+iWidths+'px;dialogHeight:'+iHeights+'px;scroll:0';
			}else{
				cwhFF='width='+iWidths+'px,height='+iHeights+'px,top='+iTops+',left='+iLefts+',resizable=no,scrollbars=no';
			}
		}else{
			if(vnavi.substr(0,2)=="IE"){
				cwhIE='dialogWidth:'+iWidthn+'px;dialogHeight:'+iHeightn+'px;scroll:0';
			}else{
				cwhFF='height='+iHeightn+'px,width='+iWidthn+'px,top='+iTopn+',left='+iLeftn+',resizable=no,scrollbars=no';
			}
		}
	}
	if(window.gcEditDirectAction){//gcEditDirectAction=="goodsinfo_val_edit_jsp.action"
		
		if(gcEditDirectAction=="goodsinfo_val_edit_jsp.action"){
			if(vnavi.substr(0,2)=="IE"){
				cwhIE='dialogWidth:'+iWidths+'px;dialogHeight:'+iHeights+'px;scroll:0';
			}else{
				cwhFF='width='+iWidths+'px,height='+iHeights+'px,top='+iTops+',left='+iLefts+',resizable=no,scrollbars=no';
			}
		}
	}
	if ($("#btn-add").length>0 && typeof(gcAddAction)=="string") {//增加按钮
		$("#btn-add").bind("click",function(){
			//alert('dd');
	   		var url=gcAddAction+"?random="+Math.random()+"&id=";
	   		//如果不是默认高度和宽高,则需在被打开后设置如 window.dialogHeight = "1500px";
	   		var win;// =window.showModalDialog(url,"",cwh); 
	   		if(vnavi.substr(0,2)=="IE"){
	   			win =window.showModalDialog(url,"",cwhIE);
	   		}else{
	   			win=window.open(url,'新开',cwhFF);
	   		}

	   		$(this).f_get_data(); //刷新数据
		});
	}
	
	if ($("#btn-edit").length>0 && typeof(gcEditAction)=="string") {//修改按钮
		$("#btn-edit").bind("click",function(){
			var idList= $("#tfgrid").getGridParam("selarrrow"); //获取多行
	  		if (idList.length!=1) {
	  			if (idList.length<1) alert("请选中一行"); else alert("只能选中一行");
	  			return;}  		
	   		rowData=$("#tfgrid").getRowData(idList[0]);//获取某一行的列数据
	   		var url=gcEditAction+"?random="+Math.random()+"&id="+rowData[gcIdentField.toString().toLowerCase()];
	   		
	   		var win;
	   		if(vnavi.substr(0,2)=="IE"){
	   			win =window.showModalDialog(url,"",cwhIE);
	   		}else{
	   			win=window.open(url,'新开',cwhFF);
	   		}
			$(this).f_get_data(); //刷新数据   		
	  });
	}
	
	
	
	if ($("#btn-edit-direct").length>0 && typeof(gcEditDirectAction)=="string") {//直接修改按钮
		$("#btn-edit-direct").bind("click",function(){
			var idList= $("#tfgrid").getGridParam("selarrrow"); //获取多行
	  		if (idList.length!=1) {
	  			if (idList.length<1) alert("请选中一行"); else alert("只能选中一行");
	  			return;}  		
	   		rowData=$("#tfgrid").getRowData(idList[0]);//获取某一行的列数据
	   		var url=gcEditDirectAction+"?random="+Math.random()+"&id="+rowData[gcIdentField.toString().toLowerCase()];
	   		
	   		var win;
	   		if(vnavi.substr(0,2)=="IE"){
	   			win =window.showModalDialog(url,"",cwhIE);
	   		}else{
	   			win=window.open(url,'新开',cwhFF);
	   		}
			$(this).f_get_data(); //刷新数据   		
	  });
	}
	
	if ($("#btn-delete").length>0 && typeof(gcDelAction)=="string") {//修改按钮
		$("#btn-delete").bind("click",function(){ //删除
			var pagecode=$("#pagecurrent").val(); //当前页
			var idList= $("#tfgrid").getGridParam("selarrrow"); //获取多行
	  		if (idList.length<1) return; //无数据
			if (confirm("确认删除选中的记录?  ("+idList.length+"条)" )==false) return;
			var id=$("#tfgrid").getRowData(idList[0])[gcIdentField.toString().toLowerCase()];

			for (var i=1; i<idList.length; i++) {
				id=id+","+$("#tfgrid").getRowData(idList[i])[gcIdentField.toString().toLowerCase()];}
			$.getJSON(gcDelAction, {id:id, random:Math.random()}, 
				function(data){
					if (data[0].msg.length<=0) 
				 		{$(this).f_get_data();} //刷新数据
				 	else
				 		{alert(data[0].msg);} //提示错误信息
				});	//设置jqGrid的数据以及页码		
			});
	}
	*/
	
	
	
	$.fn.f_get_data= function() {//刷新jGrid数据
		alert('dd');
		var params="";
		if ($("#findform").length>0) {params=$("#findform").serialize(); }//这里直接就序列化了表单里面的值
		var pagecode=$("#pagecurrent").val(); //当前页
		var pagerow=$("#pagerow").val();//得到用户每页n条
	 	params="pagecode="+pagecode+"&pagerow="+pagerow+"&random="+Math.random()+"&"+params;
	 	$.ajax({url: gcGridDataAction, type:'post',	dataType:'json', data:params, //参数     	               
	   		success:function(data){//回传函数
 				f_js_jqGrid_Set(data);
	            }    
	         });
	}

    
	$('input.ui-pg-input').hide(); 
	var page='<input type="hidden" id="pagecurrent" value="1"/>'+ 
		'<input type="hidden" id="allrecord" value="0"/>'+
		'<input type="hidden" id="pageadd" value="1"/>'+ //是否向下翻 1=是0=向上
		'<span id="pageinfo" style="padding-left:20%;">aa</span>'+
		'<span id="pagetop"><a href="#"> 首页  </a></span>'+
		'<span id="pagepre"><a href="#"> 上一页 </a></span>'+
		'<span id="page1"><a href="#"></a></span>'+
		'<span id="page2"><a href="#"></a></span>'+
		'<span id="page3"><a href="#"></a></span>'+		
		'<span id="pagenext"><a href="#">  下一页  </a></span>'+
		'<span id="pagebottom"><a href="#">  末页   </a></span>'+
		'<span>每页<input type="text" id="pagerow" value="15" maxlength="3" style="width: 32px;border: 0px;text-align: center;"/>条</span>'+ //每页显示n条
		'<br/><br/><br/>'
	$('#tfgridpage').prepend(page);
	$.getScript("js/jqgrid/jqgrid.js"); //载入页码等jqgrid相关 
}

//浏览器
function navi(){
	var vnavi="";
 	if (navigator.userAgent.indexOf("MSIE 6.0")>0){vnavi="IE6";}
 	else if (navigator.userAgent.indexOf("MSIE 7.0")>0) {vnavi="IE7";}
 	else if (navigator.userAgent.indexOf("MSIE 8.0")>0) {vnavi="IE8";}
 	else if (navigator.userAgent.indexOf("MSIE 9.0")>0) {vnavi="IE9";}
 	else if (navigator.userAgent.indexOf("MSIE") > 0) {vnavi="MSIE";}
 	else if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {vnavi="Firefox";}
 	else if (isSafari = navigator.userAgent.indexOf("Safari") > 0) {vnavi="Safari";}
 	else if (isCamino = navigator.userAgent.indexOf("Camino") > 0) {vnavi="Camino";}
 	else if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0) {vnavi="Gecko";}
	//alert('dd'+vnavi);
 	return vnavi;
}

function f_js_setDate(vcID, vcFormat){
	//设置此控件名为日期型
//alert("abc");
	if (vcFormat!="16" && vcFormat!="19" && vcFormat!="10" ) vcFormat="10";
	//10:宽度64px 16:98px 19:116px 
	//10=yyyy-mm-dd 16=yyyy-mm-dd hh:mm 19=yyyy-mm-dd hh:mm:ss
	var cWidth //图片在距左边的宽度
	if (vcFormat=="10") {cWidth="70px";}
	else if (vcFormat=="16") {cWidth="105px";}
	else {cWidth="120px";}
	
	$("#"+vcID).width(cWidth); //宽度
	//创建一个图片
	$("<img src='sys/calendar/calendar.gif' id='img_"+vcID+"' style='cursor: pointer;width:20px;'/>").insertAfter("#"+vcID);
 
	//$("#"+vcID).focus(function(){ //日期控件得到焦点时显示日期  
	//	calendar.show(document.getElementById(vcID)); 
	//});	

	$("#img_"+vcID).click(function(){ //日期图片点击时  
		calendar.show(document.getElementById(vcID)); 
	});	

	//检查日期是否正确:通过改变事件来判断
	$("#"+vcID).change(function(){
		var cDate;
		cDate=$("#"+vcID).val();
		cDate=f_js_toDate(cDate, vcFormat);

		$("#"+vcID).val(cDate); //尝试转换成有效的日期
		calendar.hide();
	});
	
	//按下键时, 不显示日期
	$("#"+vcID).keydown(function(){
		calendar.hide();
	});
	
	var cValue=$("#"+vcID).val();
	if (cValue!="") {
		$("#"+vcID).val(f_js_toDate(cValue, vcFormat));}

}		



function f_js_toDate(vcStr, vcFormat){
	//把vcStr转换成日期 如 5=本月5日 5.6表示本年5月6日
	//vcFormat 日期格式, 只能是10 16 19 yyyy-mm-dd yyyy-mm-dd hh:mm yyyy-mm-dd hh:mm:ss
//alert("ttt");
	vcStr=f_js_replaceAll(vcStr, ".", "-");
	vcStr=f_js_replaceAll(vcStr, "/", "-");
	vcStr=f_js_replaceAll(vcStr, "\\", "-");
	var cDH=vcStr.split(" "); //先拆分日期和时间

	var cArr=cDH[0].split("-");
	var cDate;
	if (cArr.length<3){//只能是1至2位
		cDate=new Date();
		if (cArr.length==1){//只有日期
			cArr[2]=cArr[0]; //日期
			cArr[0]=cDate.getFullYear(); //年
			cArr[1]=cDate.getMonth()+1; //月
			}
		else if (cArr.length==2) {//只有月日		
			cArr[2]=cArr[1]; //日
			cArr[1]=cArr[0]; //月
			cArr[0]=cDate.getFullYear(); //年
			}
		else {return "";}
	}
	if (isNaN(cArr[0])||isNaN(cArr[1])||isNaN(cArr[2])||cArr[0].length<1 ||cArr[1].length<1 ||cArr[2].length<1) return "";//不是数字

	var iYear =parseInt(cArr[0],10);
	var iMonth=parseInt(cArr[1],10);
	var iDay =parseInt(cArr[2],10);
	if (iYear<1900||iYear>2199) return "";
	if (iMonth<1||iMonth>12) return "";
	if (iDay<1||iDay>31) return "";
	
	cArr[0]=iYear.toString();
	cArr[1]=iMonth.toString();
	cArr[2]=iDay.toString();
	if (iMonth<10) cArr[1]="0"+cArr[1];    
	if (iDay<10) cArr[2]="0"+cArr[2];    
	vcStr=cArr[0]+"-"+cArr[1]+"-"+cArr[2];	

	if (f_js_isDate(vcStr)==false) return ""; //不是日期

	if (vcFormat=="10") return vcStr; //yyyy-mm-dd
	

	//时间判断
	if (cDH.length<2) cDH[1]="00:00:00";//时间判断
	var cHMS=cDH[1].split(":");
	if (cHMS.length<1) cHMS[0]="00";
	if (cHMS.length<2) cHMS[1]="00";
	if (cHMS.length<3) cHMS[2]="00";
	
	if (isNaN(cHMS[0])||cHMS[0].length<1) cHMS[0]="00";
	if (isNaN(cHMS[1])||cHMS[1].length<1) cHMS[1]="00";
	if (isNaN(cHMS[2])||cHMS[2].length<1) cHMS[2]="00";

	var iHour = parseInt(cHMS[0],10); 
	var iMinute = parseInt(cHMS[1],10); 
	var iSec = parseInt(cHMS[2],10); 
	if (iHour<0 || iHour>23) iHour=0; //小时
	if (iMinute<0 || iMinute>59) iMinute=0; //分钟
	if (iSec<0 || iSec>59) iSec=0; //秒

	cHMS[0]=iHour.toString();
	cHMS[1]=iMinute.toString();
	cHMS[2]=iSec.toString();
	if (iHour<10) cHMS[0]="0"+cHMS[0];    
	if (iMinute<10) cHMS[1]="0"+cHMS[1];    
	if (iSec<10) cHMS[2]="0"+cHMS[2];    


	var cTime;
	if (vcFormat=="16") 
		cTime=cHMS[0]+":"+cHMS[1]; 
	else 
		cTime=cHMS[0]+":"+cHMS[1]+":"+cHMS[2]; //19

	vcStr=vcStr+" "+cTime;
	return vcStr; 
}
 

function f_js_isDate(vcStr) {
	//判断vcStr是否日期型 包含判断了datetime型
	//三种日期型格式: yyyy-mm-dd yyyy-mm-dd hh:mm yyyy-mm-dd hh:mm:ss , 如2011-1-22  2011-01-22 12:12 2011-01-22 12:12:12
	var arrDate=vcStr.split(" ");
	if (arrDate.length>2) return false; //空格太多
	var arrYMD=arrDate[0].split("-");
	if (arrYMD.length<3) return false; //日期不足三位
	var iYear = parseInt(arrYMD[0],10); 
	var iMonth= parseInt(arrYMD[1],10); 
	var iDay  = parseInt(arrYMD[2],10);
//alert(iYear +"   "+iMonth +"   "+iDay);
	if (iYear<1900 || iYear >2199) return false; //年份错误
	if (iMonth > 12 || iMonth<1) return false; //月份错误
	if (iDay > 31 || iDay<1) return false; //日期错误
	if ((iMonth==2 || iMonth==4||iMonth==6||iMonth==9||iMonth==11) && iDay>=31) return false; //日期错误
	if (iMonth==2){
		if (iDay>=30) return false
		var booYear = (iYear % 4 == 0 && (iYear % 100 != 0 || iYear % 400 == 0)); //闰年
		if (booYear==false && iDay>=29) return false; //非闰年有29	
		}
	if (arrDate.length<2) return true; //无时间
	
	return f_js_isTime(arrDate[1]); //判断时间	
}

function f_js_isTime(vcStr) { 
	//只能是 12:12 或 12:13:59  不能是 12
	var arrHMS=vcStr.split(":");
	if (arrHMS.length<2) return false; //不能有: 2011-12-12 12
	if (arrHMS.length<3) arrHMS[2]="00";	
	var iHour = parseInt(arrHMS[0],10); 
	var iMinute = parseInt(arrHMS[1],10); 
	var iSec = parseInt(arrHMS[2],10); 
	if (iHour<0 || iHour>23) return false; //小时
	if (iMinute<0 || iMinute>59) return false; //分钟
	if (iSec<0 || iSec>59) return false; //秒
	return true;
}



function f_js_save(vcFormID, vcSaveAction, vcIDField){
	//vcFormID form的ID, vcSaveAction 保存执行的action, vbClose 保存成功后是否关闭窗口
	if (vcIDField==null || vcIDField==""){
		alert("必须填ident字段:f_js_save()");
		return; } 
	vcIDField=vcIDField.toString().toLowerCase(); //转小写
	//检查哪些字段不能为空
	var cNullID="", cNullMsg="", id, nullmsg, value; //非空的第一个ID, 非空的所有提示
	$("#"+vcFormID+" input[type='text']").each(function(){
		nullmsg= $(this).attr("nullmsg");
		if (typeof(nullmsg)=="string") {
			value=$(this).val(); //值
			if ((value==null || value=="" ||value.length<1) &&nullmsg.length>0) {//值为空
				cNullMsg=cNullMsg+nullmsg+"\n";
				if (cNullID.length<1) {cNullID=$(this).attr("id");} //id;
			}
		} 
	});
	if (cNullID.length>0) {//非空提示
		alert("\n"+cNullMsg); //非空提示
		document.getElementById(cNullID).focus(); //光标定位
		return; //返回函数
	}
 	var params=$("#"+vcFormID).serialize(); //这里直接就序列化了表单里面的值；很方便
 	//params=encodeURIComponent(params);
 	$.ajax({
 		url:vcSaveAction, 
 		type:'post', 
 		dataType:'json', 
 		data:params, //后台处理程序
 		//async: false, 
   		success:function(data){//回传函数
           	if (data[0].id.length>0&&vcIDField.length>0) $("#"+vcIDField).val(data[0].id); //设置ID值
           	if (data[0].msg.length>0) {
           		$('#msg').text(data[0].msg); //实现提取数据并更换div中内容，不刷新页面。
           		//alert(data[0].msg);
           		}else{
                	$('#msg').text("保存成功!"); 
                } 
            },error:function(XMLHttpRequest, textStatus, errorThrown){
            	alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);

              	alert("错了");
        	  }	
       });
}

function f_js_getMenu(){ //得到权限菜单
 	$.ajax({
 		url:"usergetmenu_getjson.action",
 		type:'post', 
 		dataType:'json',     	               
   		success:function(data){//回传函数
 			var txt="", cmenu1="";
 			var iCount=1;
			for (var i=0; i<data.length; i++) {
				if (data[i].cmenu1!=cmenu1){ //补上标题
					cmenu1=data[i].cmenu1;
					if (i>0){txt=txt+'</ul></dd></dl>';}
					//txt=txt+'<dl id="dl_items_1_'+iCount+'"><dt>'+data[i].cmenu1+'</dt><dd><ul>'
					txt=txt+'<dl id="dl_items_1_'+iCount+'"><dt><a href="#" style="text-decoration:none;" onclick="addTabs(\''+data[i].caction+'?itf56menuid='+data[i].itf56menuid+'\','+data[i].itf56menuid+',\''+data[i].ctf56menu+'\')"><center>'+data[i].cmenu1+'</center></a></dt><dd><ul>'
					iCount++;
				}
				//txt=txt+'<li><a href="'+data[i].caction+'" target="main">'+data[i].ctf56menu+'</a></li>';
				if(data[i].ctf56menu!=data[i].cmenu1){//非一级菜单添加链接
					txt=txt+'<li><a href="#" onclick="addTabs(\''+data[i].caction+'\','+data[i].itf56menuid+',\''+data[i].ctf56menu+'\')">'+data[i].ctf56menu+'</a></li>';
				}
					//txt=txt+'<li><a href="#">aaa</a></li>';
			
			}
			txt=txt+'</ul></dd></dl>'; //结束
			if (iCount>0) {
				$('#items_common').prepend(txt);
				
				$("#menu").find("dt").click(function(){
					dt = $(this);
					dd = $(this).next("dd");
					if(dd.css("display")=="none"){
						dd.slideDown("fast");
						dt.css("background-position","left bottom");
					}else{
						dd.slideUp("fast");
						dt.css("background-position","left top");
					}
				});
			
			}
			
		}
    });
}

function f_js_empty(vcFormID){//清空录入界面内容
	$("#"+vcFormID+" input[type='text']").each(function(){//清空text
		$(this).val(""); //清空值
	});
	$("#"+vcFormID+" input[type='radio']").each(function(){//清空radio
		$(":checked").css("background-color","#FFFFFF");//先去背景
		$(":checked").attr("checked",false);//取消选中
	});
	$("#"+vcFormID+" textarea").each(function(){//清空textarea
		$(this).val(""); //清空值
	});
}



function f_js_ini_input() {//编辑窗口初始化
	f_js_ini(); //初始化对象

	if($("#btn-back").length>0){//按钮存在
		$("#btn-back").click(function(){ //返回按钮
			self.close(); 
		});
 	}
	
	if($("#btn-save").length>0){//按钮存在
		$("#btn-save").click(function(){ //保存按钮
			f_js_save(gcFormName, gcSaveAction, gcIdentField); 
		});
	}
	
	if($("#btn-empty").length>0){//按钮存在
		$("#btn-empty").click(function(){ //清空按钮
			f_js_empty(gcFormName); 
		});
	}
	
	if($("#btn-save-pub").length>0){//按钮存在
		$("#btn-save-pub").click(function(){ //保存发布按钮
			//alert('btn-save-pub');
			f_js_save(gcFormName, gcSavePubAction, gcIdentField); 
		});
	}
	
	if($("#btn-save-pub-direct").length>0){//按钮存在
		$("#btn-save-pub-direct").click(function(){ //保存发布按钮
			//alert('btn-save-pub');
			f_js_save(gcFormName, gcSavePubAction, gcIdentField); 
		});
	}
	
	if($("#btn-search").length>0){//按钮存在
		$("#btn-search").click(function(){ //保存按钮
			//alert('d'); 
		});
	}
	$(".mtitle").css("width","90%"); //防止窗口出现水平滚动条

	gcItemChangedFieldList=gcItemChangedFieldList.toString().toLowerCase()+",";
	var cArrField=gcItemChangedFieldList.split(",");
	for (var i=0; i<cArrField.length; i++){
		 cField=cArrField[i];
		if (cField.length>0){
			f_js_ini_input_change(gcFormName, gcItemChangedAction, cField);
		} //替换此函数后不能运行
	}
}


function f_js_ini_input_change(gcFormName, gcItemChangedAction, vcField) {//输入改变事件
    var $f=$("#"+vcField);     
    $f.bind("evtChange",function(){    //onClick的处理逻辑});   	//$f.change(function(){ //改变事件
	 	var params=$("#"+gcFormName).serialize(); //这里直接就序列化了表单里面的值；很方便
	 	params="item="+vcField+"&"+params;
		$.ajax({
	 		url:gcItemChangedAction, 
	 		type:'post', 
	 		dataType:'json', 
	 		data:params, //后台处理程序     	               
	   		success:function(data){//回传函数
	 			var txt="", cmenu1="";
	 			for (var i=0; i<data.length; i++) {	
					$("#"+data[i].item).val(data[i].value); //设置ID值
	 			}
	       	}    
	    });		
	});
   //如果不是下拉数据窗口(ddw),则创建change
	var format=$f.attr("format");
	if (typeof(format)=="undefined" ||format.length<=0){format="";}
	if(format.substr(0, 3)!="ddw") { //不是下拉,则需调用
  		$f.change(function(){
  			$f.trigger("evtChange"); //改变事件    
  		}); 
	}		

}

 



function f_js_Set_defaultValue(viLevel) {
	//设置缺省值,  如选项,下拉,需程序设置, 但有format设置的除外
	//选项框
	//viLevel 层 如果没有设置则默认0 用于多级联动 
	var defaultvalue, name, id, obj, i, bExists, iLevel, format;
	bExists=false;
//alert("sss");	
	$("input[defaultvalue][type='radio']").each(function(){//含有defaultvalue属性
		iLevel=$(this).attr("level"); //层
		if (typeof iLevel=="undefined" || iLevel.length<=0){iLevel=0;} //必须有defaultvalue 和name
		if (viLevel!=iLevel) {return true;} //层不同
		id=$(this).attr("id");
		
		if (typeof(id)=="undefined" || id.length<=0){return true;} //必须有defaultvalue 和id
		defaultvalue=$(this).attr("defaultvalue");
		if (typeof(defaultvalue)=="undefined" ||defaultvalue.length<=0) {return true;} //下一个
		name=$(this).attr("name");
		if (typeof name=="undefined" || name.length<=0){return true;}
		$("input[name="+name+"][value="+defaultvalue+"]").attr("checked",true);//选中
		bExists=true;
	});

	
	//文本框,文本下拉框
	$("input[defaultvalue][type='text']").each(function(){//含有defaultvalue属性
		iLevel=$(this).attr("level"); //层	
		if (typeof iLevel=="undefined" || iLevel.length<=0){iLevel=0;} //必须有defaultvalue 和name
		if (viLevel!=iLevel) {return true;} //层不同

		defaultvalue=$(this).attr("defaultvalue");
		if (typeof(defaultvalue)=="undefined" ||defaultvalue.length<=0) {return true;}

		$(this).attr("value", defaultvalue);//值
		bExists=true;
	
		//如果format是ddw ddw_text的,则不允许change事件
		format=$(this).attr("format");
		if (typeof(format)=="undefined" ||format.length<=0){format="";}
		if(format.substr(0, 3)=="ddw") {return true;}		
		id=$(this).attr("id");
		if (typeof(id)!="undefined" &&id.length>0 ){$("#"+id).change();}
	});

	//下拉框
	$("select[defaultvalue]").each(function(){//含有defaultvalue属性  开始
		iLevel=$(this).attr("level"); //层
		if (typeof iLevel=="undefined" || iLevel.length<=0){iLevel=0;} //必须有defaultvalue 和name
		if (viLevel!=iLevel) {return true;} //层不同

		defaultvalue=$(this).attr("defaultvalue");	
		if (defaultvalue=="") {return true;}
		id=$(this).attr("id");
		if (typeof id=="undefined" ||id.length<=0){return true;} //必须有defaultvalue 和name

		bExists=true;

		obj=$("#"+id);
        var options = obj.find("option");
	    options.each(function (i) {
			if ($(this).text() === defaultvalue) {
			$(this).attr("selected", true); //-- 也可用 obj.get(0).selectedIndex = i; 替代
			$("#"+id).change();
			//$("#"+id).trigger("change");
			//	alert(id);			//alert(typeof($("#"+id).data("events").));				
			//if (typeof($("#"+id).data("events")["change"])!="undefined") {
			return false; //跳出each循环 
          	}
        });
	}); //含有defaultvalue属性  结束


	//隐藏文本框和多选框
	if (viLevel==0){ //只运行一次 开始
		$("input[defaultvalue][type='hidden']").each(function(){//含有defaultvalue属性
			defaultvalue=$(this).attr("defaultvalue");
			if (typeof(defaultvalue)=="undefined" ||defaultvalue.length<=0) {return true;}
			$(this).attr("value", defaultvalue);//值
			bExists=true;
		});
		$("input[defaultvalue][type='checkbox']").each(function(){//含有defaultvalue属性
			defaultvalue=$(this).attr("defaultvalue");
			if (typeof(defaultvalue)=="undefined" ||defaultvalue.length<=0) {return true;}
			name=$(this).attr("name");
			if (typeof name=="undefined" || name.length<=0){return true;}  
			var cArr=defaultvalue.split(", ");
			for (var i=0;i<cArr.length;i++){
				defaultvalue=cArr[i].toString();
				if (defaultvalue.length>0) {
					$('input[name="'+name+'"][value="'+defaultvalue+'"]').attr('checked','true');//选中
				}
			}			
		});
	} //只运行一次 结束
	
	
	if (bExists==true) { //存在数据时,自循环 再下一级
		setTimeout(function() {
			f_js_Set_defaultValue(viLevel+1);
			}, 1);  //设置缺省值 需要延迟,解决数据不到位的问题		
	}
	return bExists;
}



function f_js_setEnu() {
	//下拉列表字段  枚举选项 自定义选项
	//vcStrDdlb, vcStrRadio格式:控件ID名1,tag1|控件ID名2,tag2|
	//如f_js_setEnu("txtEnu,1|txtAbc,202", "")
	//枚举Enu
//			alert($(this).attr("id")); //("select[format^enuradio:]").
	var cArrID=new Array(), cArrEnu=new Array(), iArrType=new Array(), iCount, cFirstStr; //存储ID enuID和分类
	 
	iCount=0;
	$("select[format^='enu:']").each(function(){//下拉列表
		cArrID[iCount]=$(this).attr("id");
		cArrEnu[iCount]=$(this).attr("format").substr(4,10);
		iArrType[iCount]=1;
		iCount=iCount+1;
	});
	$("input[type='radio'][format^='enu:']").each(function(){//radio
		cArrID[iCount]=$(this).attr("id");
		cArrEnu[iCount]=$(this).attr("format").substr(4,10);
		iArrType[iCount]=2;
		iCount=iCount+1;
	});
	if (iCount<=0) {return;} //没有enu:
	
	$.getJSON("enu.action?random="+Math.random(), function(data){ //getJSON开始
		var iCurrent, id, cList="", cName, iLen, iShowLen, iRow=0, checked; //初始值 选中行 初始行;	
		for (iCurrent=0; iCurrent<iCount; iCurrent++) {//处理各字段  开始
			id=cArrID[iCurrent];		
			cName=$("#"+id).attr("name"); //名称
			iRow=0;
			if (iArrType[iCurrent]==1) { //下拉列表开始
				$("#"+id).append("<option value=''></option>"); //加空行
				for (var j=0;j<data.length;j++){ //逐个对比 开始 已做ordery by iSysEnuID, isnull(iNo, iAutoID)
			 		if (data[j].id==cArrEnu[iCurrent]){//相同的枚举ID
						$('#'+id).append('<option value="'+data[j].d+'">'+data[j].s+'</option>');
						iRow++;	}
		 			else 
		 				if (iRow>0) {break;} //如果已增加,则跳出
				} //逐个对比 结束
			}
			
			if (iArrType[iCurrent]==2) { //radio处理  开始
				//第1行有id和defaultvalue,其它行没有id
				cFirstStr='id="'+id+'"';
				defaultvalue=$("#"+id).attr("defaultvalue");
				if (typeof(defaultvalue)=="undefined"){defaultvalue="";} //有定义defaultvalue 
				cList="";
				iLen=0;
				cFirstStr='';
				for (var j=0;j<data.length;j++){ //逐个对比 开始 已做ordery by iSysEnuID, isnull(iNo, iAutoID)
			 		if (data[j].id==cArrEnu[iCurrent]){//相同的枚举ID
			 			iShowLen=f_js_charLength(data[j].s);
			 			if ((iLen+iShowLen)>=50){//换行
			 				iLen=0;
							cList=cList+'<br>';}
			 			else {
			 				if (iLen>0){//不是首行
				 				iLen=iLen+4;//加空格
								cList=cList+'&nbsp;&nbsp;&nbsp;&nbsp;';}}
				 		iLen=iLen+iShowLen+2;
				 		if (defaultvalue.length>0 && data[j].d==defaultvalue){checked=' checked="checked"';} else {checked='';}
						cList=cList+'<input type="radio" '+cFirstStr+' name="'+cName+'" value = "'+data[j].d+'" '+checked+' style="width: 10px">'+data[j].s+'</input>';
						cFirstStr='';
						iRow++;} //id="'+id+'" 
		 			else 
		 				if (iRow>0) {break;} //如果已增加,则跳出
				} //逐个对比 结束
				
				if (cList.length>0) $("#"+id).replaceWith(cList);
			//alert(cList);				 		

				//if (typeof(defaultvalue)!="undefined"){$("#"+id).attr("defaultvalue", "");} //填上默认值
			} //Radio处理  结束
		} //处理各字段  结束
	}); //getJSON结束	
}

//选项卡效果 add by xiayao 2013-11-14
function tabChange(layerClass){ 
	//界面调用，动态传入外层标签class样式值
	var _layerClass = $("."+layerClass);
	//找到所有<li>元素，单击事件
	_layerClass.find("li").click(function(){
		var _this = $(this);
		var _rel = _this.attr("rel");
		_this.addClass("current").siblings().removeClass("current");
		//显示与<li>标签 rel 属性值相同的层显示，同时设置与之同级的 层全部隐藏
		$("#"+_rel).removeClass("fn-hide").siblings().addClass("fn-hide");
		loadQuery();//查询
	});
}//tab切换
//>>>>>>>>>>>选项卡效果 end>>>>>>>>>>>>>>>>>>>>>>>

//物流进度效果 add by xiayao 2013-11-15
function progress(layerClass){	
	//界面调用，动态传入外层标签class样式值
	var _layerClass = $("."+layerClass);
	var _time = _layerClass.find("li").find(".time");
	for ( i = 0 ; i < _time.length; i++ ){
		var _timeText = $.trim(_time.eq(i).text());
		//各个物流状态下值不为空时，显示已经到达完成状态
		if(_timeText == ""){
			return false;
		}else{//设置完成样式
			_time.eq(i).parent().find(".text").addClass("fn-cGreen");
			_time.eq(i).parent().find(".status").addClass("complete");
			_time.eq(i).parent().prev().find(".line").addClass("fn-bg-blue");
		}
	}
}
//>>>>>>>>>>>物流进度效果 end>>>>>>>>>>>>>>>>>>>>>

