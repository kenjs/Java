//�������
function doWsSh(wsspxh,spxh){  
       var url = jcontextPath+"/common/wsspCommon!init.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
   	window.showModalDialog(url,window,"dialogHeight:900px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
   	//window.open(url);
   	onRefresh();
} 

/*�������װ��json��ʽ*/
function getArrayToJson(obj, paraName) {
	var data = "";
	$.each(obj, function(i){
		data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
	});
	
	return data;
} 

//var myArray=new Array();
function plJudge(){
	var xhs = $(":checked[name='xhs']");
	if (xhs.length <= 0) {
		showAlert("��ѡ����Ҫ�������ͨ���ļ�¼��");
		return;
	}
	//myArray=xhs;
	if(xhs.length==1){
		var array=$(xhs[0]).val().split("#");
		var jsonObj = {"domain.wsspxh":array[0],"domain.spxh":array[1],"domain.sprJdxh":array[2]};

		var url=jcontextPath+"/common/wsspCommon!queryWsspms";	
		ajaxCommon(url,jsonObj,"doQueryWsspmsSuc");
	}else{
		//var map=new HashMap(xhs.length);
		//var map=new HashMap();
		var tempJdxh="";
		var wsspxh="";
		var spxh="";
		var flagSuc = true;
		$.each(xhs, function(i, obj){
			var key=$(obj).val();
			var array=key.split("#");
			//if(!map.containsKey(key))
				//map.put(key,key);
				//continue;
			if(i==0){
				wsspxh=array[0];
				spxh=array[1];
				tempJdxh= array[2];
			}else{
				if(tempJdxh!=array[2]){
					showAlert("�ڵ���Ų�ͬ�������������ͨ����");
					flagSuc = false;
					return false;
				}
			}
			//alert(tempJdxh);
		});
		if (!flagSuc) {
			return;
		}
		//myArray=map.values();
		//alert(map.size());
		//alert("myArray"+myArray);
		var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh,"domain.sprJdxh":tempJdxh};

		var url=jcontextPath+"/common/wsspCommon!queryWsspms";	
		ajaxCommon(url,jsonObj,"doQueryWsspmsSuc");
	}
}

function plBack(){
	var xhs = $(":checked[name='xhs']");
	if (xhs.length <= 0) {
		showAlert("��ѡ����Ҫ�����˻������ļ�¼��");
		return;
	}
	var url = jcontextPath+"/common/wsspCommon!plBack";  
	var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
	ajaxCommon(url,jsonStr,"doPlBackSuc",false);
}

function doQueryWsspmsSuc(data){
	var wsspxh=data.domain.wsspxh;
	var spxh=data.domain.spxh;
	var rtnCode=data.domain.rtnCode;
	var wsspms=data.domain.wsspms;
	var sprJdxh =data.domain.sprJdxh
	//if(wsspms==undefined || wsspms==null || wsspms==""){
		//showAlert("��������ģʽ��ȡʧ�ܣ������������ͨ�������飡");
		//return;
	//}
	//alert("rtnCode="+rtnCode);
	//alert("wsspms"+wsspms);
	//rtnCode=="2"ʱ����һ��������ֱ�ӵ�������
	if("2"==rtnCode){
		doPlJudge();
	}else{//���������������ģʽѡ���ͣ�wsspms=1��2ʱ����Ҫ�������ͶԻ���3ʱֱ�ӷ���
		if("1"==wsspms || "2"==wsspms){
			doInitSend(wsspxh,spxh);
		}
		if("3"==wsspms){
			doPlSend();
		}
	}
}
var sprCzyDjxh="";
function doInitSend(wsspxh,spxh){ 
	sprCzyDjxh=""; 
    var url = jcontextPath+"/common/wsspCommon!initSend.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
    window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
   	//window.open(url);
   	if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
   		//showAlert("������Ա�������飡");
   		return;
   	}
   	var url = jcontextPath+"/common/wsspCommon!plSend"; 
   	//var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
   	var xhs = $(":checked[name='xhs']");
   	//var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
   	var jsonObj = {"domain.sprCzyDjxh":sprCzyDjxh};
	jsonStr += jQuery.param(jsonObj);
    ajaxCommon(url,jsonStr,"doPlSendSuc",false);
}
function doPlSend(){  
      var url = jcontextPath+"/common/wsspCommon!plSend"; 
      //var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
      var xhs = $(":checked[name='xhs']");
   	  //var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	  var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
      //var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh};   
      ajaxCommon(url,jsonStr,"doPlSendSuc",false);
}
function doPlSendSuc(data) {
	hideMessage();
	showAlert("��������ͨ���ɹ���");
	onRefresh();
} 
function doPlJudge(){  
     var url = jcontextPath+"/common/wsspCommon!plJudge"; 
     //var jsonStr = getArrayToJson(myArray, "domain.checkboxs");
     var xhs = $(":checked[name='xhs']");
   	 //var jsonStr = getJqueryParam(myArray, "domain.checkboxs");
   	 var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
     //alert(jsonStr);
     ajaxCommon(url,jsonStr,"doPlJudgeSuc",false);
}
function doPlJudgeSuc(data) {
	hideMessage();
	showAlert("��������ͨ���ɹ���");
	onRefresh();
}
function doPlBackSuc(data) {
	hideMessage();
	showAlert("�˻سɹ���");
	onRefresh();
}

var wsspms="";
// spgsbm ������˾���룬Ĭ��Ϊ��ǰ����Ա���ڹ�˾�����Ƿ��ñ�������Ƚ����⣬Ϊ���˵�λ
function scSend(wsDm,wsXmflDjxh,ywDjxh,oldWsspxh,spgsbm){
	if (spgsbm == undefined || spgsbm == null) {
		spgsbm = "";
	}
	sprCzyDjxh="";
	wsspms="";
	var url = jcontextPath+"/common/wsspCommon!initScSend.action?domain.wsDm="+wsDm+"&domain.jgbm="+spgsbm;
    window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
   	//window.open(url);
   	if(undefined==wsspms || null==wsspms || ""==wsspms){
   		//showAlert("���ݳ������飡");
   		return;
   	}
   	if("1"==wsspms || "2"==wsspms){
   		if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
	   		showAlert("������Ա�������飡");
	   		return;
	   	}
   	}
   	
   	var url = jcontextPath+"/common/wsspCommon!scSend"; 
   	var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.ywDjxh":ywDjxh,"domain.sprCzyDjxh":sprCzyDjxh,"domain.oldWsspxh":oldWsspxh};
    ajaxCommon(url,jsonObj,"doScSendSuc",false);
}

function doScSendSuc(data) {
	hideMessage();
	showAlert("���ͳɹ���");
	window.close();
}
// spgsbm ������˾���룬Ĭ��Ϊ��ǰ����Ա���ڹ�˾�����Ƿ��ñ�������Ƚ����⣬Ϊ���˵�λ����
function plScSend(wsDm,wsXmflDjxh,spgsbm){  
	if (spgsbm == undefined || spgsbm == null) {
		spgsbm = "";
	}
	  var xhs = $(":checked[name='xhs']");
	  if (xhs.length <= 0) {
		showAlert("��ѡ����Ҫ�������������ļ�¼��");
		return;
	  }
	  sprCzyDjxh="";
	  wsspms="";
	  var url = jcontextPath+"/common/wsspCommon!initScSend.action?domain.wsDm="+wsDm+"&domain.jgbm="+spgsbm;
      window.showModalDialog(url,window,"dialogHeight:160px;dialogWidth:625px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
      if(undefined==wsspms || null==wsspms || ""==wsspms){
	   		//showAlert("���ݳ������飡");
	   		return;
      }
   	  if("1"==wsspms || "2"==wsspms){
   		 if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
	   		 showAlert("������Ա�������飡");
	   		 return;
	   	 }
   	  }
      var url = jcontextPath+"/common/wsspCommon!plScSend"; 
   	  var jsonStr = getJqueryParam(xhs, "domain.checkboxs");
      var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.sprCzyDjxh":sprCzyDjxh}; 
      jsonStr += jQuery.param(jsonObj);  
      ajaxCommon(url,jsonStr,"doPlScSendSuc",false);
}
function doPlScSendSuc(data) {
	hideMessage();
	showAlert("�������ͳɹ���");
	onRefresh();
}