 //添加一个cookie

    function addCookie(name,value,expireHours){
        var cookieStr=name+"="+escape(value);
        if(expireHours>0){   //为0时不设定过期时间，浏览器关闭时cookie自动消失
            var date=new Date();
            date.setTime(date.getTime()+expireHours*3600*1000);
            cookieStr=cookieStr+";expire="+date.toGMTString();
        }
        document.cookie=cookieStr;
    }



    //获取指定名称的cookie值
    function getCookie(name){
        var strCookie=document.cookie;
        var arrCookie=strCookie.split("; ");
        for(var i=0;i<arrCookie.length;i++){
            var arr=arrCookie[i].split("=");
            if(arr[0]==name){ 
            	return unescape(arr[1]);
            }
        }
        return "";
    }
	