//电话校验（0571-88175786）
function testit(valeues){  
	var filter=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	return filter.test(valeues);  
}
//电话校验（057188175786）
function checkphone(valeues){
    var filter=/^((0\d{2,3}))(\d{7,8})(-(\d{3,}))?$/;
	return filter.test(valeues);
}

//手机校验，必须是
function bilenumber(valeues) {
    var bilenumber = valeues;
	l = bilenumber.length;
	if($.trim(bilenumber) != '') {
		if(l != 11) {
			return false;
		} 
		var reg = /^0{0,1}(13[0-9]|145|147|15[0-3]|15[5-9]|18[0-9]|17[6-8])[0-9]{8}$|^$/;
		var result = reg.exec(bilenumber);
		if(result == null){
			return false;
		}
	}
	return true;
}
//校验小数点三位
function myNumberic(e) {
	var num = $.trim($(e).val());
	if(isNaN(num)){
		$(e).val("");
	}else {
		$(e).val(num);
		var str = num.split(".");
		if(str.length>1){
			var numer = '';
			var strs = str[0];
			if(strs.length>10) {
				numer = strs.substr(0,10);
			}
			if(str[1]!=null && str[1].length>4){
				var newNum = str[0]+'.'+str[1][0]+str[1][1]+str[1][2]+str[1][3];
				$(e).val($.trim(newNum));
			}
		}else {
			if(num.length>9) {
				$(e).val($.trim(num.substr(0,9)));
			}
		}
	}
}


//校验电子邮箱
function emialIf(valeues) {
	reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(!reg.test($.trim(valeues)))
    {
        return false;
    }
    return true;
}


function inputTipText(){   
    $("input[tipMsg]").each(function(){  
        if($(this).val() == ""){  
            var oldVal=$(this).attr("tipMsg");  
        if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}  
        $(this)  
           .css({"color":"#888"})     //灰色  
           .focus(function(){  
            if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}  
           })  
           .blur(function(){  
            if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}  
           })  
           .keydown(function(){$(this).css({"color":"#000"})});  
        }  
    });  
} 

/**
 * @author wei.huang
 * @date 2013-11-21
 * @function 提示信息展示
 * @param targetId 目标文本框的Id(带#)
 * @param message 提示信息
 * @return 展示提示信息的div的id(带#)
 */
function markText(targetId,message){
	var _targetId=targetId.substr(1);//去掉#
	if($(targetId+"_message").length==0){//当信息提示div不存在时
		var top=$(targetId).position().top+parseInt($(targetId).css("height"));
		var left=$(targetId).position().left;
		var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("body").append(txt);
	}else{//当信息提示div存在时
		$(targetId+"_message").empty().append(message);
	}
	$(targetId).css("border","2px solid red");
	//return targetId+"_message";
}

function input_onfocus(obj){
	$(obj).css("border","1px solid  #a0cfe0");
	$("#"+obj.id+"_message").remove();
}

function onlyNum(){
	if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
 	if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}

//数字钱转换大写中文
function Chinese(num){     
    if(!/^\d*(\.\d*)?$/.test(num))throw(new Error(-1, "Number is wrong!"));     
    var AA = new Array("零","壹","贰","叁","肆","伍","陆","柒","捌","玖");     
    var BB = new Array("","拾","佰","仟","萬","億","圆","");     
    var CC = new Array("角", "分", "厘");     
    var a = (""+ num).replace(/(^0*)/g, "").split("."), k = 0, re = "";     
    for(var i=a[0].length-1; i>=0; i--){     
        switch(k){     
            case 0 : re = BB[7] + re; break;     
            case 4 : if(!new RegExp("0{4}\\d{"+ (a[0].length-i-1) +"}$").test(a[0]))     
                         re = BB[4] + re; break;     
            case 8 : re = BB[5] + re; BB[7] = BB[5]; k = 0; break;     
        }     
        if(k%4 == 2 && a[0].charAt(i)=="0" && a[0].charAt(i+2) != "0") re = AA[0] + re;     
        if(a[0].charAt(i) != 0) re = AA[a[0].charAt(i)] + BB[k%4] + re; k++;     
    }     
    if(a.length>1){     
        re += BB[6];     
        for(var i=0; i<a[1].length; i++){     
            re += AA[a[1].charAt(i)] + CC[i];     
            if(i==2) break;     
        }   
        if(a[1].charAt(0)=="0" && a[1].charAt(1)=="0"){  
            re+="元整";  
        }    
    }else{  
        re+="元整";  
    }     
    return re;     
} 

//校验货币
function isPriceNumber(_keyword){  
    if(_keyword == "0" || _keyword == "0." || _keyword == "0.0" || _keyword == "0.00"){  
        _keyword = "0"; return true;  
    }else{  
        var index = _keyword.indexOf("0");  
        var length = _keyword.length;  
        if(index == 0 && length>1){/*0开头的数字串*/  
            var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;  
            if(!reg.test(_keyword)){  
                return false;  
            }else{  
                return true;  
            }  
        }else{/*非0开头的数字*/  
            var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/;  
            if(!reg.test(_keyword)){  
                return false;  
            }else{  
                return true;  
            }  
        }             
        return false;  
    }  
}  
