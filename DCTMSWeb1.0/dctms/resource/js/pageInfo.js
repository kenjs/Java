// �б�ҳ��ҳ��
// 2014-02-19 nxj

function pageInfo(totalPages,curPageNos,totalRecords) {
	var liststep = 10;//�����ʾ��ҳҳ��
	var totalPage = totalPages;//��ҳ��
	var page = curPageNos;//��ǰҳ��
	var totalRecord = totalRecords;	//�ܼ�¼��
	
   if (totalPage < page) {
        page = totalPage;//�����ҳ��������ҳ�����򽫷�ҳ�������Ϊ��ҳ��
   }
   if (page < 1) {
		page = 1;//�����ҳ����С�ڣ�,�򽫷�ҳ������Ϊ��
   }
//   var listbegin = page - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep/2) + 1);
//   if (listbegin < 1) {
//        listbegin = 1;
//   }
//   var listend = page + liststep;//��ҳ��Ϣ��ʾ���ڼ�ҳ
//   if (listend > totalPage) {
//		listend = totalPage + 1;
//   } 
   var listbegin =0;
   var listend = 0;
   //�ж���ҳ���Ƿ�С��10
   if(totalPage<=liststep){
	   listbegin = 1;
	   listend = totalPage;
   }else {
	   if(page<=5){
		   listbegin = 1;
		   listend = liststep;
	   }
	   else if(totalPage - page>=5){
		   listbegin = page - 4;
		   listend = page +5;
	   }else{
		   listbegin = totalPage - liststep +1;
		   listend = totalPage;
	   }
   }
   var pageHTML = "<a href='javascript:getPageInfos(1);'>��ҳ</a>";
   if (page == 1) {//��һҳ
	   pageHTML += "<a href='javascript:getPageInfos("+page+");'>��һҳ</a>";
   }
   if(page>1) {
	   pageHTML += "<a href='javascript:getPageInfos("+(page-1)+");'>��һҳ</a>";
   }
   pageHTML += "<span class='pageNum'>";
   for (var i = listbegin; i < listend+1; i++) {
	   if (i != page) {//���i�����ڵ�ǰҳ
			pageHTML += "<a href='javascript:getPageInfos("+i+");' >"+i+"</a>";
       } else {
    	   pageHTML += "<a class='currentA'>"+i+"</a>";
	   }
	}
   pageHTML += "</span>";
	//��һҳ
	if(page == totalPage) {
		pageHTML += "<a href='javascript:getPageInfos("+page+");'>��һҳ</a>";
	}else {
		pageHTML += "<a href='javascript:getPageInfos("+(page + 1)+");'>��һҳ</a>";
	} 
	pageHTML += "<a href='javascript:getPageInfos("+totalPages+");'>βҳ</a>";
	pageHTML += ""+
				"<p>��<input class='jump' type='text' id='pageGo' name='pageGo' value='' />ҳ"+
				"<input class='go-botton' onclick='getGo();' type='button' value='GO' /></p>"+
				""+
				"<span class='text'>��"+totalPages+"ҳ\/"+totalRecord+"����Ϣ</span>";
	if(totalPage == 0){
		var pageInfoHtmlId = document.getElementById("pageInfoHtmlId");
		pageInfoHtmlId.innerHTML="";
		$("#pageInfoHtmlId").append(pageHTML);
		//$("#pageInfoHtmlId").hide();
	}else {
		var pageInfoHtmlId = document.getElementById("pageInfoHtmlId");
		pageInfoHtmlId.innerHTML="";

		$("#pageInfoHtmlId").append(pageHTML);
	}
}


//˾����ʷ�켣��ҳ
//function locationPageInfo(totalPages,curPageNos,totalRecords) {
//	var liststep = 5;//�����ʾ��ҳҳ��
//	var totalPage = totalPages;//��ҳ��
//	var page = curPageNos;//��ǰҳ��
//	var totalRecord = totalRecords;	//�ܼ�¼��
//	
//   if (totalPage < page) {
//        page = totalPage;//�����ҳ��������ҳ�����򽫷�ҳ�������Ϊ��ҳ��
//   }
//   if (page < 1) {
//		page = 1;//�����ҳ����С�ڣ�,�򽫷�ҳ������Ϊ��
//   }
//   var listbegin = page - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep/2) + 1);
//   if (listbegin < 1) {
//        listbegin = 1;
//   }
//   var listend = page + liststep;//��ҳ��Ϣ��ʾ���ڼ�ҳ
//   if (listend > totalPage) {
//		listend = totalPage + 1;
//   } 
//   var pageHTML = "<ul class='number'><li><a href='javascript:getPageInfos(1);'>��ҳ</a></li>";
//   if (page == 1) {//��һҳ
//	   pageHTML += "<li><a href='javascript:getPageInfos("+page+");'>��һҳ</a></li>";
//   }
//   if(page>1) {
//	   pageHTML += "<li><a href='javascript:getPageInfos("+(page-1)+");'>��һҳ</a></li>";
//   }
//   for (var i = listbegin; i < listend; i++) {
//	   if (i != page) {//���i�����ڵ�ǰҳ
//			pageHTML += "<li><a href='javascript:getPageInfos("+i+");'>"+i+"</a></li>";
//       } else {
//    	   pageHTML += "<li><a class='currentA'>"+i+"</a></li>";
//	   }
//	}
//	//��һҳ
//	if(page == totalPage) {
//		pageHTML += "<li><a href='javascript:getPageInfos("+page+");'>��һҳ</a></li>";
//	}else {
//		pageHTML += "<li><a href='javascript:getPageInfos("+(page + 1)+");'>��һҳ</a></li>";
//	} 
//	pageHTML += "<li><a href='javascript:getPageInfos("+totalPages+");'>βҳ</a></li>";
//	pageHTML += "<li class='text'>��"+totalPages+"ҳ\/"+totalRecord+"����Ϣ</li></ul>";
//	if(totalPage == 0){
//		$("#pageInfoHtmlId").append("");
//	}else {
//		$("#pageInfoHtmlId").append(pageHTML);
//	}
//	
//}

// ��ѯ����
function getSubmit() {
	$("#curPage").val(1);
	$("#curPageNo").val(1);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//��ҳ
function getPageInfos(curPages) {
	var curPage = curPages;//Ҫ��ʾ��ҳ��
	$("#curPage").val(curPage);
	$("#curPageNo").val(curPage);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//ֱ�ӵ���ҳ��
function getGo() {
	var pageGo = $("#pageGo").val();//Ҫ��ʾ��ҳ��
	if(trim(pageGo) == '') {
		return false;
	}else {
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(trim(pageGo))){
		    return false;
		}
	}
	$("#curPage").val(pageGo);  
	$("#curPageNo").val(pageGo);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//������
function getOnmouseover(myId) {
	$("#tr"+myId).addClass("selected");
}

//�������
function getOnmouseout(myId) {
	$("#tr"+myId).removeClass("selected");
}
/**
* ajaxCommon ajax�ύjs����
* @param method post����get�ύ
* @param url �����url��ַ 
* @param data �����data����
* @param async �Ƿ��첽trueΪ�첽
* @param obj �ص�����
*/
function AjaxSubmit(obj){
	var url = obj.url + "?random="+Math.random();
	if(obj.method==="get"){
		url+=(url.indexOf("?")==-1?"?":"&")+params(obj.data);
	}
	var xhr = createXhr();
	if(obj.async){
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				JsAjaxCallBack(xhr,obj);
			}
		};
	}
	xhr.open(obj.method,url,obj.async);
	if(obj.method==="post"){
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send(params(obj.data));
	}else{
		xhr.send(null);
	}
	if(!obj.async){
		JsAjaxCallBack(xhr,obj);
	}
}


//jsajax����ֵ��ת��Ϊ�ַ���
function params(data){
	var arr =[];
	for(var i in data ){
		arr.push(encodeURI(i)+"="+encodeURI(encodeURI(data[i])));
	}
	return arr.join("&");
}
//js ajax�л�ֵ
function JsAjaxCallBack(xhr,obj){
	if(xhr.status==200){
		var result = xhr.responseText;
		result=result.replace(/[\r\n]/g,"");//ȥ���س�����
		if(result=="login"){
			window.parent.parent.location.href=jcontextPath+ "/managerLogout";//���ص���¼ҳ��
			return;
		}
		if(result=="loginMx"){
			window.close();
			return;
		}
		if(result=="�쳣���˴β�����Ч"){
			alert("�쳣���˴β�����Ч");
			return;
		}
		if(result.indexOf("{")==-1){
			obj.success(result);
			return;
		}
		obj.success(JSON.parse(result));
	}else{
		alert("���ʳ�������"+xhr.status);
	}
}
//����Xhr���������
function createXhr(){
	if(typeof XMLHttpRequest!="undefined"){
		return new XMLHttpRequest();
	}else if(typeof ActiveXObject!="undefined"){
		var version = [
		               "MSXML2.XMLHttp.6.0",
		               "MSXML2.XMLHttp.3.0",
		               "MSXML2.XMLHttp"
		               ];
		for(var i in version){
			try{
				return new ActiveXObject(version[i]);
			}catch(e){
				//����
			}
		}
	}
	throw new Error("�����������֧��ajax");
	return xhr;
}

//��ʼ������
function queryInfoInit(){
	document.getElementById("curPage").value=1;
	document.getElementById("curPageNo").value=1;
	queryInfo();
}
