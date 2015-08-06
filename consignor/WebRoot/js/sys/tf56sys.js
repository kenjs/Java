function f_js_ini_input() {//编辑窗口初始化
	f_js_ini(); //初始化对象
/*
	gcItemChangedFieldList=gcItemChangedFieldList.toString().toLowerCase()+",";
	var cArrField=gcItemChangedFieldList.split(",");
	for (var i=0; i<cArrField.length; i++){
		 cField=cArrField[i];
		if (cField.length>0){
			f_js_ini_input_change(gcFormName, gcItemChangedAction, cField);
		} //替换此函数后不能运行
	}*/
}

function f_js_ini() { //jGrid列表和编辑界面均需执行
	$.ajaxSetup({cache:true}); //不使用缓存
	//初始化按钮
	/*$("input[type='button']").each(function(){
		$(this).f_js_button().init();
	});*/

	//初始化输入框,包含查找框  它必须含有format属性 
	$("input[type='text'],select").each(function(){
		$(this).f_js_input().init();
	});

	//初始化下拉框  它没有format属性
//	$("select[val]").each(function(){
//		$(this).f_js_select().init();
//	});
	//f_js_setEnu();//设置枚举
	/*setTimeout(function() {
		f_js_Set_defaultValue(0);
		}, 1); */ //设置缺省值 需要延迟,解决数据不到位的问题				
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
		/*if (cPV[0]=="dot"){ //数字
			f_js_setDec(id, cPV[1]); 
			return; }
		if (cPV[0]=="df"){ //日期
			f_js_setDate(id, cPV[1]); 
			return; }
		if (cPV[0]=="len"){ //字符最大长度
			f_js_setChar(id, cPV[1]); 
			return; }
		if (cPV[0]=="ddl"){ //下拉列表,格式:action
			f_js_setDdl(id, cPV[1]); //显示列 数据列 action			 
			return; }*/
		
		if (cPV[0]=="ddw" || cPV[0]=="ddw_text" ){ //下拉,格式:action,数据列(可为空)
			var cArrDDW=cPV[1].split(",");
			if (cArrDDW.length<2) cArrDDW[1]=""; //无数据列
			if (cPV[0]=="ddw_text") cArrDDW[1]=null; //空表示可以修改值
			f_js_setDdw(id, cArrDDW[1], cArrDDW[0]); //显示列 数据列 action 
			return; }
				
		
	};	
	return this;
};


function f_js_setDdw(vcShow, vcColID, vcAction){
	//创建下拉列表, vcShow显示列控件名称, vcColID是数据列
	//1.判断iClientID是否存在,如果不存在则创建 用于提交数据列 隐藏对象
	//2.自动创建 cClient_ddw_name,用于存储修改后名称是否变化 隐藏对象
	//vcColID=""表示值必须对应 =null表示可修改值
//	alert(vcShow+' '+vcColID+' '+vcAction);
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
	$.post(vcAction, function(data) {
		$("#"+vcShow).autocomplete(data, {
				minChars:0, 
				max:199,
				autoFill:false,
				scrollHeight: 280,
				matchContains: true,
				multiple:false,
				multipleSeparator:' ', 
				formatItem: function(row, i, max) {
					return ""+row.name +" " + row.help +"" ;
				},
				formatMatch: function(row, i, max) {
					return row.name + " " + row.help;
				},
				formatResult: function(row) {
					return row.name;
				},
				delay:500
			}).result(function(event,item) {//值已改变事件 开始
				if(item){	
				   if (vcColID!="") $("#"+vcColID).val(item.id); //存储ID
				   cShowTmp=item.name; //存储显示名
				   $("#"+vcShow).trigger("evtChange"); //改变事件 
				   		$("#"+vcShow).val(trim($("#"+vcShow).val()));//去除填充后右侧空格
				   }
				}); //值已改变事件 结束
	});  //getJSON结束
}


function trim(str){   
    return str.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   
}  