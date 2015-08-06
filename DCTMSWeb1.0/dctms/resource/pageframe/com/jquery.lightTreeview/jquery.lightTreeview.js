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

		//�Ƿ�����������
		if(!settings.line) this.addClass('treeview-noline');

		//�Ƿ������˷��
		if(settings.style) this.addClass('treeview-'+settings.style);

		//ȡ�ڵ�
		var node=$('li:has(ul,ol)');

		//���ý�β�ķ�֦
		$('li:last-child').addClass('branch-last');

		if(settings.collapse) {	//��������

			//���ô�ͼ���li��������
			node.addClass('node-normal').filter(':last-child').attr('class','node-last');

			//Ĭ���ļ�ͼ��֧��
			if(settings.fileico) $('li:not(:has(ul,ol))>:first-child',this).addClass('treeview-file');

			//Ĭ���ļ���ͼ��֧��
			if(settings.folderico) $('>:first-child',node).addClass('treeview-folder');

			//�ڽڵ��м���Ĭ�ϼӼ�
			node.css('cursor','pointer').prepend('<span class="flex-ico"></span>').find('>ul,>ol').filter(':hidden').parent().find('>.flex-ico').addClass('flex-close');
			$('>.flex-ico',node.filter(':last-child')).addClass('flex-none');
			$('>ul,>ol',node.filter(':last-child')).filter(':hidden').parent().addClass('node-last-close');
			node.find('>ul,>ol').filter(':hidden').parent().find('>.treeview-folder').addClass('treeview-folder-close');

			//�󶨱����еĵ���¼�
			if(settings.nodeEvent)
				
				
				node.find('>:nth-child(2)').click(function() {
					$(this).parent().find('>.flex-ico').trigger('click');
				});
				
			//checkbox�ĸ��ڵ����¼�ȫѡ
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
					
					//checkbox���ӽڵ�ȡ����ȫѡʱ�����ڵ�ҲӦ�ñ�ȡ����ѡ�С�
					
					
					
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
					event.stopPropagation(); //ȡ��checkbox������������ṹ�¼�,
				});
				//�ӽڵ���¼�
				$('input').click(function(){
					if($(this).attr("checked") == true){
					$(this).parents('li').children('div').children('input').attr('checked',true);
					}
					else{//false���ﲻ�ܣ�������ӽڵ�ȥ���ͺ��ˣ�����Ǹ��ڵ㣬����������	
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
							if($(this).attr("type") == "checkbox") //������ڵ�����Ϊinput
								function(i){
									$(this).attr("checed","true");
								}
						}
						);
					}
				
					
				});*/
				
				
				

			//��Ĭ���¼�
			$('>.flex-ico',node).click(function() {
				var f=$(this).parent();	//��ǰ�ڵ�
				if(settings.unique && $('>ul,>ol',f).is(':hidden')) {	//ͬ������
					var ff=$('>li:has(ul,ol)',f.parent()).not(f);	//�ų���ǰ�ڵ��ͬ���ڵ㼯��
					flex($('>:first-child',ff),ff,settings,'hide');
				}
				flex($(this),f,settings);
			});
		}
		return this;
	};

	//���Ų���
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

	//Ĭ�ϲ�������
	var defaultSettings=function() {
		return {
			collapse: true,		//�����۵�
			line: true,			//��������
			animate: 200,		//�����ٶ�
			nodeEvent: true,	//��Ӧ��֦���е��¼�
			unique: false,		//ͬ���ڵ����Ƿ񻥳�
			style: '',			//Ĭ�Ϸ��
			fileico: false,		//�ļ�ͼ��֧��
			folderico: false	//�ļ���ͼ��֧��
		}
	};
})(jQuery);