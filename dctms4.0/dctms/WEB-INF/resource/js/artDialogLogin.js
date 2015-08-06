function test(){
art.dialog({
width:400,
height:100,
id: 'shake-demo',
title: '提示框',
content: '<div class="frkst">对不起！您没有访问车辆详细页面的权限，请先登录在查看车辆详细信息。</div>',
lock: true,
fixed: true,
ok: function () {
//确认调用事件
ext();
inputTipText();  //初始化Input的灰色提示信息  
},
okValue: '提交',
cancelValue:'取消',
cancel: function () {
}
});
}

function ext(){
var loginHtml = '<div class="laing_f">'+
					'<ul>'+
						'<li><label>用户名</label>'+
							'<input id="code" name="code" type="text" class="int_t" tipMsg="用户名/手机号"  onblur="on_blur(\'code\');"  onfocus="on_focus(\'code\');"/>'+
							'<div class="mpt " id="divCodeMpt" style="display:none">'+
				            	'<div class="wn_a"></div>'+
				                '<div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>'+
				                '<div class="wn_s" id="divCodeLength" style="display:none">用户名长度为3到20位！</div>'+
				          		'<div class="wn_s" id="divCodeLetter" style="display:none">用户名只能有数字字母或下划线组成！</div>'+
				                '<div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>'+
				                '<div class="wn_s" id="divMobilephoneNot" style="display:none">手机号码不存在！</div>'+
				                '<div class="wn_s" id="divCoent" style="display:none">请填写完整信息！</div>'+
				                '<div class="wn_c"></div>'+
            				'</div>'+
						'</li>'+
						'<li><label>密码</label>'+
							'<input id="password" name="password" type="password" class="int_t" onblur="on_blur(\'password\');"  onfocus="on_focus(\'password\');"/>'+
							'<div class="mpt " id="divPwdMpt" style="display:none">'+
				            	'<div class="wn_a"></div>'+
				                '<div class="wn_s" id="divPwdNull" style="display:none">密码不能为空！</div>'+
				                '<div class="wn_s" id="divPwdError" style="display:none">密码错误！</div>'+
				                '<div class="wn_c"></div>'+
            				'</div>'+
	          			'</li>'+
	          			'<li class="mort">'+
		          			'<span><a href="javascript:getRetrieveUserPws();">忘记密码？</a></span>'+
		            		'<label></label>'+
		            		'<input name="" type="checkbox" value="" />&nbsp;记住密码'+
		            	'</li>'+
	          			'<li class="lanot_t">'+
	            			'<label></label>'+
	            			'<a href="javascript:login();" class="hoa mr10">登录</a><a href="javascript:getRegister();">注册</a>'+
	            		'</li>'+
	        		'</ul>'+
 			  	'</div>';
art.dialog({
width:400,
height:300,
id: 'shakeId',
title: '登录',
content: loginHtml,
lock: true,
fixed: true
});
}
//关闭所有对话框
function closeUp() {
var list = art.dialog.list;
for (var i in list) {
    list[i].close();
};
}