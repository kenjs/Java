/**
 * lightTreeview for jQuery 1.2.x
 *
 * author: feiyu
 * e-mail: feiyu@asgard.cn
 * website: http://feiyu.asgard.cn
 *
 * Version: 1.0.0
 */

(function($) {

	$.lightTreeview={
		toggle: function(o,speed) {
			exec(o,speed,'toggle');
		},
		close: function(o,speed) {
			exec(o,speed,'hide');
		},
		open: function(o,speed) {
			exec(o,speed,'show');
		}
	};

	function exec(o,s,act) {
		var f=$(o).parent();
		var ico=f.find('>.flex-ico');
		flex(ico,f,{animate:isNaN(s)?100:s},act);
	}

	$.fn.lightTreeview=function(s1) {
		if(typeof(s1)=='undefined') s1 = {};
		var settings=defaultSettings();
		$.extend(settings,s1);

		this.addClass('lightTreeview');

		//是否启用连节线
		if(!settings.line) this.addClass('treeview-noline');

		//是否设置了风格
		if(settings.style) this.addClass('treeview-'+settings.style);

		//取节点
		var node=$('li:has(ul,ol)');

		//设置结尾的分枝
		$('li:last-child').addClass('branch-last');

		if(settings.collapse) {	//允许伸缩

			//设置带图标的li的连节线
			node.addClass('node-normal').filter(':last-child').attr('class','node-last');

			//默认文件图标支持
			if(settings.fileico) $('li:not(:has(ul,ol))>:first-child',this).addClass('treeview-file');

			//默认文件夹图标支持
			if(settings.folderico) $('>:first-child',node).addClass('treeview-folder');

			//在节点中加入默认加减
			node.css('cursor','pointer').prepend('<span class="flex-ico"></span>').find('>ul,>ol').filter(':hidden').parent().find('>.flex-ico').addClass('flex-close');
			$('>.flex-ico',node.filter(':last-child')).addClass('flex-none');
			$('>ul,>ol',node.filter(':last-child')).filter(':hidden').parent().addClass('node-last-close');
			node.find('>ul,>ol').filter(':hidden').parent().find('>.treeview-folder').addClass('treeview-folder-close');

			//绑定标题行的点击事件
			if(settings.nodeEvent)
				
				
				node.find('>:nth-child(2)').click(function() {
					$(this).parent().find('>.flex-ico').trigger('click');
				});
				
			//checkbox的父节点点击事件全选
			node.find(':checkbox').click(function(event) {
					var parentObj=$(this);
					if($(this).attr("checked")==true){
						$.each(parentObj.parent().next().find(":checkbox"),
							   function(i){
									$(this).attr("checked",true);
								}		
						)
					}else{
						$.each(parentObj.parent().next().find(":checkbox"),
							   function(i){
									$(this).attr("checked",false);
								}		
						)
					}
					
					//checkbox的子节点取消或全选时，父节点也应该被取消或选中。
					
					
					
				/*	var flags=true;
					$.each(parentObj.parent().parent().parent().find(":checkbox"),
							   function(i){
									if(false==$(this).attr("checked")){
										flags=false;
										return;
									};
								}		
						)


						if($.trim(parentObj.parent().parent().parent().parent().find(":first-child").html())!=""){
						}else{
					
							if(flags){
								parentObj.parent().parent().parent().parent().find(":checkbox").eq(0).attr("checked",true);
							}else{
								parentObj.parent().parent().parent().parent().find(":checkbox").eq(0).attr("checked",false);
							}
						
						}*/
					event.stopPropagation(); //取消checkbox点击后收缩树结构事件,
				});
				//子节点绑定事件
				$('input').click(function(){
					if($(this).attr("checked") == true){
					$(this).parents('li').children('div').children('input').attr('checked',true);
					}
					else{//false这里不管，如果是子节点去掉就好了，如果是父节点，交给上面解决	
					}
				});
			/*	$chlidNode = $(".endbox");
				$chlidNode.click(function(){
					if($(this).attr("checked")==true){
						//$($($(this).parents('ul')[1]).find('input')[0]).attr("checked","true");
						var ul=$(this).parents('ul'),lg=ul.length;
						var i=0;
						while(i<lg-1){
						//	$($(ul[++i])).find('input')[0]).attr("checked","true");
							$($(ul[++i]:last-child).attr("checked","true");
							//$($(ul[++i]).find('input')[0]).attr("checked","true");
						}	
					}
				});*/


				
			/*	$chlidNode = $("ul>li",this);
				$chlidNode.find(":checkbox").click(function(){
					//if(this.attr("checked")==true){
					//this.parents("input").attr("checked","true");
					//alert("try");
					if($(this).attr("checked")==true){
						//$(this).parents("input").attr("checked","true");
						$.each(
						$(this)=$(this).parent(),function(i){}
						/*if($(this))
						{
							if($(this).attr("type") == "checkbox") //如果父节点类型为input
								function(i){
									$(this).attr("checed","true");
								}
						}
						);
					}
				
					
				});*/
				
				
				

			//绑定默认事件
			$('>.flex-ico',node).click(function() {
				var f=$(this).parent();	//当前节点
				if(settings.unique && $('>ul,>ol',f).is(':hidden')) {	//同级互斥
					var ff=$('>li:has(ul,ol)',f.parent()).not(f);	//排除当前节点的同级节点集合
					flex($('>:first-child',ff),ff,settings,'hide');
				}
				flex($(this),f,settings);
			});
		}
		return this;
	};

	//缩放操作
	function flex(ico,father,settings,type) {
		var list=$('>ul,>ol',father);
		var ln=ico.filter('.flex-none').parent();
		var ic=ico.not('.flex-none');
		var fl=$('>.treeview-folder',father);
		if(type=='hide') {
			ln.addClass('node-last-close');
			ic.addClass('flex-close');
			fl.addClass('treeview-folder-close');
			list.hide(settings.animate);
		}
		else if(type=='show') {
			ln.removeClass('node-last-close');
			ic.removeClass('flex-close');
			fl.removeClass('treeview-folder-close');
			list.show(settings.animate);
		}
		else {
			ln.toggleClass('node-last-close');
			ic.toggleClass('flex-close');
			fl.toggleClass('treeview-folder-close');
			list.toggle(settings.animate);
		}
	}

	//默认参数设置
	var defaultSettings=function() {
		return {
			collapse: true,		//允许折叠
			line: true,			//有连接线
			animate: 200,		//动画速度
			nodeEvent: true,	//响应分枝整行的事件
			unique: false,		//同级节点下是否互斥
			style: '',			//默认风格
			fileico: false,		//文件图标支持
			folderico: false	//文件夹图标支持
		}
	};
})(jQuery);