//document.write('<link type="text/css" rel="stylesheet" href="' + $ips.appPath + 'js/validate/jquery.validate.css" />');

$.validator.addMethod("load", function(value, element, params) {
	if ( this.optional(element) )
	return "dependency-mismatch";
	
	var previous = this.previousValue(element);
	if (!this.settings.messages[element.name])
		this.settings.messages[element.name] = {};
	previous.originalMessage = this.settings.messages[element.name].load;
	this.settings.messages[element.name].load = previous.message;
	
	if ( previous.old !== value ) {
		previous.old = value;
		var validator = this;
		this.startRequest(element);
		var data = {};
		data[element.name] = value;
		
		// 重新组装参数，替换#
		var ps = params.split('&');
		var p = '';
		for(var i = 1;i<ps.length;i++){
			var ss2 = ps[i].split('=');
			var v = ss2[1];
			if (v.charAt(0) == '#') v = $(v).val();
			p += ss2[0] + '=' + v + '&';
		}
		p += element.name + '=' + value;
		var mm = ps[0].split(".");
		
		$ips.load(mm[0], mm[1], p, function(response) {
			validator.settings.messages[element.name].load = previous.originalMessage;
			var valid = response === 1;
			if ( valid ) {
				var submitted = validator.formSubmitted;
				validator.prepareElement(element);
				validator.formSubmitted = submitted;
				validator.successList.push(element);
				validator.showErrors();
			} else {
				var errors = {};
				var message = (previous.message = response || validator.defaultMessage( element, "load" ));
				errors[element.name] = $.isFunction(message) ? message(value) : message;
				validator.showErrors(errors);
			}
			previous.valid = valid;
			validator.stopRequest(element, valid);
		});
		return "pending";
	} else if( this.pending[element.name] ) {
		return "pending";
	}
	return previous.valid;
}, "输入数据不合格");

$.validator.addMethod("charNum", function(value, element) {
    	return this.optional(element) || /^[A-Za-z0-9]+$/.test(value);       
}, "只能英文字母、数字");

jQuery.validator.addMethod("unEqualTo", function(value, element, param) {
		return value != $(param).val();
}, "不能输入一样的结果");

$.validator.addMethod("isMobile", function(value, element,param) {
	return this.optional(element) || /(^0?[1][3458][0-9]{9}$)/.test(value);
}, "手机号码不符合要求");

$.validator.addMethod("isPhone", function(value, element,param) {
     return this.optional(element) || /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value);
}, "电话格式:0571-12345678");

(function($) {
	$.extend($.validator.defaults, {
		errorPlacement: function(error, element) {
	//		element.focus(function() {
	//			if (element.hasClass('error')) {
	//				var name = element.attr('name') || element.attr('id');
	//				var label = element.next("label[for='" + name + "']");
	//				if ( label.length == 0 ) error.insertAfter(element)
	//				error.show();
	//			}
	//		});
			showMessage(error, element);
			element.focus(function() {
				showMessage(error, element);
			});
			element.blur(function() {error.hide();});
		}
	});
})(jQuery);

function showMessage(error,element){
	if (element.hasClass('error')) {
		var name = element.attr('name') || element.attr('id');
		var label = element.next("label[for='" + name + "']");
		$(label).append('<img class="arrow" src="../js/themes/trade/css/skin/error_arrow.gif">');
		if ( label.length == 0 ) error.insertAfter(element);
		error.show();
	}
}