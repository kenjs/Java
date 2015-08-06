// ������Ҫ�Ǵ����ü�����������
// YWID��ҵ����������������ĸ�����ҵ��
//      'gs-fbs'�����ݹ�˾��̬ˢ�·ְ��̵�����
//      'gs-kh'�� ���ݹ�˾��̬ˢ�¿ͻ�������
//      'kh-hw'�� ���ݿͻ���̬ˢ�»��������
//      'bm-yh':  ���ݲ��Ŷ�̬ˢ���û�������
//      'fbs-xl': ���ݷְ��̶�̬ˢ����·������
//      'gs-wlryfl': ���ݹ�˾��̬ˢ��������Ա���������
// SJ: ��ǰ�ϼ���ֵ
// defaultValue���������������ֵ
// listName����������NAME
// listId����������ID
// containQbBz���Ƿ���ʾ����ѡ��
// mcContainDmBz�����������Ƿ��������
function commonInit(ywid, sj, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (sj == null) {
		sj = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {
	        "domain.ywid":ywid,
			"domain.paramdm":sj,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!commonInit";	
	ajaxCommon(url,jsonObj,"changeList");
}


//����-�������
function cwfylbInit(cwfylbDm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (cwfylbDm == null) {
		cwfylbDm = "";
	}
	 //alert("cwfylbDm:" +cwfylbDm);
	 
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":cwfylbDm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!cwfylbInit";	
	ajaxCommon(url,jsonObj,"changeList",false,false);
}


//����
function bmInit(sjJgbm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (sjJgbm == null) {
		sjJgbm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":sjJgbm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!bmInit";	
	ajaxCommon(url,jsonObj,"changeList");
}

//��λ
function gwInit(bmDm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (bmDm == null) {
		bmDm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":bmDm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!gwInit";	
	ajaxCommon(url,jsonObj,"changeList");
}

function currentFbsInit(ssJgbm, defaultValue, listName, listId, containQbBz, mcContainDmBz){
	if (ssJgbm == null) {
		ssJgbm = "";
	}
	if (defaultValue == null || defaultValue == "") {
		defaultValue = $("#"+listId).val();
	}
	var jsonObj = {"domain.paramdm":ssJgbm,
			"domain.defaultValue":defaultValue,
			"domain.currentObjName":listName,
			"domain.currentObjId":listId,
			"domain.containQbBz":containQbBz,
			"domain.mcContainDmBz":mcContainDmBz};
	
	var url=jcontextPath+"/common/wlglptCommon!queryCurrentFbs";	
	ajaxCommon(url,jsonObj,"changeList");
}

function changeList(data){
	var list = data.domain.dataList;
	
	//alert("data.domain.currentObjId:"+data.domain.currentObjId);
	
	$("#"+data.domain.currentObjId).empty();
	$.each(list, function(i,domain){
		//alert("domain.mc:"+ domain.mc);
		
	    var option = document.createElement('option');
	    $("#"+data.domain.currentObjId)[0].add(option);
	    $(option).text(domain.mc).val(domain.dm);
	    //Ĭ��ѡ��
	    if(data.domain.defaultValue==domain.dm){
	    	$(option).attr("selected", "selected");
	    	$(option).text(domain.mc).val(domain.dm);
	    }
	    //alert($("#"+data.domain.currentObjId).html());
	});
}

//window.open()��ʽ���´���
//url-�������ݶ�Ӧ������
//width-���ڿ��
//height-���ڸ߶�
//left-��������Ļ��߾���
//top-��������Ļ�ϱ߾���
function popWin(url, width, height, left, top){
	url = url + "&random="+Math.random();
	window.open(url,"_blank",'width='+width+',height='+height+',left='+left+',top='+top+',toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no');
}

//�ڴ����м��ҳ��
function openWindowToDialog(url,dialogWidth,dialogHeight,obj){
	var sw = screen.width;
    var sh = screen.height;
    
    var dialogLeft = (sw-dialogWidth)/2;
    var dialogTop = (sh-dialogHeight)/2;
    var parr = 'dialogWidth:'+dialogWidth+'px;dialogHeight:'+dialogHeight+'px;dialogLeft:'+dialogLeft+'px;dialogTop:'+dialogTop+'px;edge: Raised; center: Yes; help: no; resizable: Yes; status: no;';
	window.showModalDialog(url,obj,parr);
	
}