
//定义各种信息提示框

function hideMessage(){
	enableAllButtons();
	 if (document.getElementById("divProcessing")){
   	 	document.getElementById("divProcessing").style.display = "none";
   	 }
   	 if (document.getElementById("divSearch")){
	 	document.getElementById("divSearch").style.display = "none";
	 }
}

function showMessage(){
	
	// select不可用
  //  $("select").attr("disabled",true);
    disableAllButtons();
	
	var screenwidth,screenheight
	screenwidth = $(window).width();
	screenheight = $(window).height();
	var x = screenwidth / 2 - 160;
	var y = screenheight / 2 - 40 - 60;
	//生成页面divProcessingNode节点
	var divProcessingNode;
	if (document.getElementById("divProcessing")){
        divProcessingNode = document.getElementById("divProcessing");
    }
    else{
        divProcessingNode = document.createElement("DIV");
		//divProcessingNode.styleName="z-index:9998;width:100%;height:100%;position:absolute;display:none;filter:Alpha(opacity=0);";
	    divProcessingNode.style.left = x+"px";
        divProcessingNode.style.top = y+"px";
	    divProcessingNode.style.width="300px";
	    divProcessingNode.style.height="100px";
	    divProcessingNode.style.position = "absolute";
		divProcessingNode.style.filter="Alpha(opacity=0)";
		divProcessingNode.style.zindex=9997;
		divProcessingNode.id = "divProcessing";
		document.body.appendChild(divProcessingNode);	
	}
   
    
    //生成divSearchNode节点
    var divSearchNode;
	if (document.getElementById("divSearch")){
        divSearchNode = document.getElementById("divSearch");
    }
    else{
		
		//divSearchNode.styleClass="z-index:9999;width:350;height:80;position:absolute;display:none;border:1px #3E85EB solid;background-color:#EBF5FF;";
		divSearchNode = document.createElement("DIV");
	    divSearchNode.style.left = x+"px";
        divSearchNode.style.top = y+"px";
	    //divSearchNode.style.width="350px";
	    //divSearchNode.style.height="60px";
	    divSearchNode.style.width="350px";
	    divSearchNode.style.height="40px";
	    divSearchNode.style.position = "absolute";
		//divSearchNode.style.backgroundColor="#EBF5FF";
		//divSearchNode.style.borderWidth ="1px";
		//divSearchNode.style.borderStyle ="solid";
		//divSearchNode.style.color="#3E85EB";
		divSearchNode.style.zindex=9999;
	 	divSearchNode.id = "divSearch";
    	document.body.appendChild(divSearchNode);	
    	
        var dynamicNode = document.createElement("DIV");
		var innerHtml = "<iframe src='javascript:false' style='position:absolute; visibility:inherit; top:-1px; left:-1px; width:352px; height:62px; z-index:-1; filter=\"progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\"';></iframe>"+
					    "<div style='background-color:#D0ECF8;position:absolute;left:15px;top:10px;width:350px;height:25px;border:2 #8ED2EF solid;overflow:hidden'><div style='position:absolute;top:-1;left:0' id='pimg'></div></div>"+
						"<div style='position:absolute;top:18px;left:75px;width:350px;height:25px;font-size:14px;font-weight:blod;font-color:#E7233B;font-family: 宋体' id='abc'>请求正在执行中，请稍候......</div>";
		dynamicNode.innerHTML = innerHtml;
		dynamicNode.style.zindex=9998;
		divSearchNode.appendChild(dynamicNode);	
	}
	
   	flashs();
	str();
	//var DivH=document.body.scrollHeight;
   	document.getElementById("divProcessing").style.display = "block";
	//document.getElementById('divProcessing').style.height=DivH;
	document.getElementById("divSearch").style.display = "block";

}

function ls(){
		pimg.innerHTML="";
		for(i=0;i<9;i++){
			pimg.innerHTML+="<input style=\"width:15px;height:20px;border:0;background:"+"#2DADE2"+";margin:1\">";
		}
}
	
function rs(){
		pimg.innerHTML="";
		for(i=9;i>-1;i--){
		  pimg.innerHTML+="<input style=\"width:15px;height:20px;border:0;background:"+"#2DADE2"+";margin:1\">";
		}
}
	

var g=0;sped=0;
function str(){
	if(pimg.style.pixelLeft<350&&g==0){
		if(sped==0){
			ls();
			sped=1;
		}
		pimg.style.pixelLeft+=2;
		setTimeout("str()",1);
		return;
	}
	g=1;
	if(pimg.style.pixelLeft>-200&&g==1){
		if(sped==1){
			rs();
			sped=0;
		}
		pimg.style.pixelLeft-=2;
		setTimeout("str()",1);
		return;
	}
	g=0;
	str();
}

function flashs(){
	if(abc.style.color=="#707888"){
		abc.style.color="#000000";
		setTimeout("flashs()",500);
	}
	else{
		abc.style.color="#707888";
		setTimeout("flashs()",500);
	}
}


var URL_ALERT = jcontextPath+"/resource/pageframe/html/alert.jsp";
var URL_SUCCES = jcontextPath+"/resource/pageframe/html/success.jsp";
var URL_ERROR = jcontextPath+"/resource/pageframe/html/error.jsp";
var URL_MAXERROR = jcontextPath+"/resource/pageframe/html/maxerror.jsp";
var URL_CONFIRM = jcontextPath+"/resource/pageframe/html/confirm.jsp";

//一般提示信息框，yesCallBack是点击确定之后触发的函数
//调用方式1：showAlert("保存成功！","yesCallBack");	点击确定关闭后调用yesCallBack方法
//调用方式2：showAlert("保存成功！");		点击确定关闭后什么也不做
//参数yesCallBack可选
function showAlert(str,yesCallBack){
	popwindow(URL_ALERT,400,228,str,yesCallBack);
}

/* 
*自定义款高度之后太丑，暂时不用
*@deprecated
*/
//function showAlert(str,width,height,yesCallBack){
//	popwindow(URL_ALERT,width,height,str,yesCallBack);
//}



//成功信息提示框
//用法同showAlert
function showSuccess(str,yesCallBack){
	popwindow(URL_SUCCES,400,228,str,yesCallBack);
}

//错误信息提示框
//用法同showAlert
function showError(str,yesCallBack){
	popwindow(URL_ERROR,400,228,str,yesCallBack);
}

//错误信息提示框
//用法同showAlert
function showMaxError(str,yesCallBack){
	popwindow(URL_MAXERROR,400,290,str,yesCallBack);
}

//yesCallBack是选择yes之后回调的方法，noCallBack是否，如果不指定noCallBack，则默认选择否后关闭后什么也不做
//调用方式1：showConfirm("确定要删除么？","yesCallBack","noCallBack");	选择否关闭后，调用noCallBack方法
//调用方式2：showConfirm("确定要删除么？","yesCallBack");		默认选择否后关闭后什么也不做
//参数yesCallBack必选
//参数noCallBack可选
function showConfirm(str,yesCallBack,noCallBack){
	popwindow(URL_CONFIRM,400,228,str,yesCallBack,noCallBack);
}

