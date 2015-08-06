var param;
function getId() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("?")
	idUrl = idUrl.substr(num + 1);
	return idUrl;
}
$(function(){
$.ajax({
 	url: "../subcontractorcs/subcontractor_detail_json",   
 	type:'post',	
 	dataType:'json', 
 	data:{"partyid":getId().split("&")[1].split("=")[1],"tableid":getId().split("&")[1].split("=")[1],"tablename":"party"}, //参数     	               
   	success:function(data){//回传函数
 		var txt="";
		$("#partyname").html(data.partyname);$("#mobilenumber").val(data.mobilenumber);$("#email").val(data.email);$("#contact").val(data.contact);
		$("#telephonenumber").val(data.telephonenumber);$("#fax").val(data.fax);$("#subcontractorname").val(data.organization);
		$("#helpcode").val(data.helpcode);$("#officeaddress").val(CONCAT(data.province+'-'+data.city+'-'+data.region));$("#detailaddress").val(data.officeaddress);
		$("#legalperson").val(data.legalperson);$("#registeredcapital").val(data.registeredcapital);$("#employeescount").val(data.employeescount);
		$("#description").val(data.description);$("#topartyid").val(data.partyid);
		//<td class="tdleft"><a href="../attachment/'+data.list[i].filename+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a>
		if(data.list.length>0){
			for(var i=0;i<data.list.length;i++){
			txt=txt+'<tr style="height:32px;"><td class="tdleft"><span style="color:#1560ea;">'+data.list[i].filename+'</span><a href="javascript:void(0)" style="color:#1560ea; margin-left:15px;" onclick="btnDel(this'+','+data.list[i].contractappendixid+')">'+"删除"+'</a></td></tr>';
		}
			$("#myfiles").append(txt);
		}
		var params="topartyid="+getId().split("&")[1].split("=")[1]+"&shipperorsubcontractor=0";
		$.ajax({
			url: "../contractattributecs/selectContractAttributeInfo",
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
				$.each(data,function(i,obj){
					if(obj.attributeName == '业务员'){
						$('#saler').val(obj.attributeValue);
					}
					if(obj.attributeName == '车辆数量'){
						$('#clsl').val(obj.attributeValue);
					}
					if(obj.attributeName == '投保金额'){
						$('#tbje').val(obj.attributeValue);
					}
					if(obj.attributeName == '年营业额'){
						$('#nyye').val(obj.attributeValue);
					}
					if(obj.attributeName == '网点数量'){
						$('#wdsl').val(obj.attributeValue);
					}
					if(obj.attributeName == '年运输量'){
						$('#nysl').val(obj.attributeValue);
					}
					if(obj.attributeName == '是否使用系统'){
						$('#syxt').val(obj.attributeValue);
					}
					if(obj.attributeName == '优势行业'){
						var strs = (obj.attributeValue).split(",");
						for(i=0;i<strs.length;i++){
							//alert(strs[i]);
							if(strs[i]!=""){
								if(i==0){
									$("#yshyTd1 select").val(strs[i]);
								}else {
									addSelect('yshy',strs[i]);
								}
							}
						}
					}
					if(obj.attributeName == '经营范围'){
						var strs = (obj.attributeValue).split(",");
						for(i=0;i<strs.length;i++){
							if(strs[i]!=""){
								if(i==0){
									$("#jyfwTd1 select").val(strs[i]);
								}else{
									addSelect('jyfw',strs[i]);
								}
							}
						}
					}
				});
		}
		});
		
	}
});
});
function insertNextFile(obj)
{
//获取上传控制个数
    var childnum = document.getElementById("files").getElementsByTagName("input").length;      
    var id = childnum - 1;
    var fullName = obj.value;
    // 插入<div>元素及其子元素
    var fileHtml = '';
    fileHtml += '<div  id = "file_preview' + id + '" style ="border-bottom: 1px solid #CCC;width:160px;">';
    //fileHtml += '<img  width =30 height = 30 src ="images/file.gif" title="' + fullName + '"/>';
    fileHtml += '<a href="javascript:;" mce_href="javascript:;" onclick="removeFile(' + id + ');">取消</a> &nbsp;&nbsp;';
    fileHtml += getFileName(fullName) +'  </div>';
    var fileElement = document.getElementById("files_preview");
    fileElement.innerHTML = fileElement.innerHTML + fileHtml;  
    obj.style.display = 'none';   // 隐藏当前的<input type=”file”/>元素
    addUploadFile(childnum);  // 插入新的<input type=”file”/>元素
}
//  插入新的<input type=”file”/>元素，适合于不同的浏览器（包括IE、FireFox等）
function addUploadFile(index)
{
    try  // 用于IE浏览器
    {  
        var uploadHTML = document.createElement( "<input type='file' id='file_" + index +
                                "' name='file[" + index + "]'  onchange='insertNextFile(this)'/>");
        document.getElementById("files").appendChild(uploadHTML);
    }
    catch(e)  // 用于其他浏览器
    {
        var uploadObj = document.createElement("input");
        uploadObj.setAttribute("name", "file[" + index + "]");
        uploadObj.setAttribute("onchange", "insertNextFile(this)");
        uploadObj.setAttribute("type", "file");
        uploadObj.setAttribute("id", "file_" + index);
        document.getElementById("files").appendChild(uploadObj);
    }
}
function removeFile(index)  // 删除当前文件的<div>和<input type=”file”/>元素
{
    document.getElementById("files_preview").removeChild(document.getElementById("file_preview" + index));
    document.getElementById("files").removeChild(document.getElementById("file_" + index));   
}
function showStatus(obj)  // 显示“正在上传文件”提示信息
{
  //document.getElementById("status").style.visibility="visible";
  //var params=$("#form").val();
}
/*function check_ver(obj){
	alert(obj.value);
	if(obj.value==""){
		alert("请上传资料");
	}
}*/
function check_ver(obj)
{
for(var i=0;i<=document.form1.elements.length-2;i++)
{
	var id='file_'+i;
	if(document.getElementById(id).value==""&&i<document.form1.elements.length-1){
		alert("当前表单不能有空项");
		return;
	}
	else{
		return true;
	}
}
}
function callbackrs(data){
	//var filename=$("#file1").val().match(/[^\/]*$/)[0];
	   if(data.msg=="ok"){
		//$("#file1").val("");
		   alert("ok");
		   document.getElementById("status").style.visibility="hidden";
		   var inputt=document.getElementById("insubmit");
			   //inputt.setAttribute("disabled",true);
		}
	   var ids=data.partyids.split("-");
	   var div=document.getElementById("files_preview").childNodes.length;//获取div下的子元素
	   //alert(div);
	   var files_preview="file_preview";
	   for(var i=0;i<=div-1;i++){
		   var divc=document.getElementById("file_preview"+i);
		   var a=divc.childNodes.item(0);
		   var aa=divc.childNodes.item(2);
		  // alert(aa);
		   if(aa==""||aa==undefined||aa==null){
			   var a1=document.createElement("a");
			   var text=document.createTextNode("删除");
			   a1.appendChild(text);
			   a1.setAttribute("onclick", 'del('+ids[i]+','+i+')');
			   a1.setAttribute("mce_href", "javascript:;");
			   a1.setAttribute("href", "javascript:;");
			   divc.appendChild(a1)
		   }else{
			   return;
		   }
	   }
		if(data.msg=="sorry"){
			alert("上传失败");
			return;
		}
}
function del(id,i){
	alert(id);
	removeFile(i);
}
function getFileName(path){
	var pos1 = path.lastIndexOf('/');
	var pos2 = path.lastIndexOf('\\');
	var pos = Math.max(pos1, pos2)
	if( pos<0 )
	return path;
	else
	return path.substring(pos+1);
	}
$("#btnsave").click(function(){
	$("#btnsave").attr("disabled","disabled");
	var params1="partyname="+trim($("#partyname").val())+"&telephonenumber="+trim($("#telephonenumber").val())+"&fax="+trim($("#fax").val())+"&organization="+trim($("#subcontractorname").val())+
				"&helpcode="+trim($("#helpcode").val())+"&officeaddress="+trim($("#officeaddress").val())+"&registeredcapital="+trim($("#registeredcapital").val())+"&detailaddress="+trim($("#detailaddress").val())+
				"&employeescount="+trim($("#employeescount").val())+"&saler="+trim($("#saler").val())+"&description="+trim($("#description").val())+"&topartyid="+trim($("#topartyid").val())+"&shipperorsubcontractor=0"+"&datasource=总包"+"&random="+Math.random();
	var clsl = $("#clsl").val();
	var tbje = $("#tbje").val();
	var nyye = $("#nyye").val();
	var wdsl = $("#wdsl").val();
	var nysl = $("#nysl").val();
	if((!checkNum(clsl))||clsl==""){
		if(clsl==""){
			ymPrompt.alert("车辆数量不可为空！");
			$("#btnsave").removeAttr("disabled"); 
		}
		return;
	};
	if(!checkNum(tbje)){
		return;
	};
	if(!checkNum(nyye)){
		return;
	};
	if(!checkNum(wdsl)){
		return;
	};
	if(!checkNum(nysl)){
		return;
	};
	var syxt = $("#syxt").val();
	var yshyObj = $(".yshy");
	var yshy = "";
	for(var i=0;i<(yshyObj.length-1);i++){
		var j = i+1;
		for(j;j<(yshyObj.length-1);j++){
			if(yshyObj[i].value==yshyObj[j].value){
				ymPrompt.alert("优势行业不可重复！");
				$("#btnsave").removeAttr("disabled"); 
				return;
			}
		}
		yshy = yshy+yshyObj[i].value+",";
	}
	var jyfwObj = $(".jyfw");
	var jyfw = "";
	for(var i=0;i<(jyfwObj.length-1);i++){
		var j = i+1;
		for(j;j<(jyfwObj.length-1);j++){
			if(jyfwObj[i].value==jyfwObj[j].value){
				ymPrompt.alert("经营范围不可重复！");
				$("#btnsave").removeAttr("disabled"); 
				return;
			}
		}
		jyfw = jyfw+jyfwObj[i].value+",";
	}
	var params1 = params1+"&clsl="+clsl+"&tbje="+tbje+"&nyye="+nyye+"&wdsl="+wdsl+"&nysl="+nysl+"&syxt="+syxt
				  +"&yshy="+yshy+"&jyfw="+jyfw;
	//alert(params1);
	$.ajax( {
		url : "../subcontractorcs/conUpdateSubconBasicInfo",
		dataType : "json",
		type :'POST',
		data:params1,
		success : function(data) {
			if(data.msg!='ok'){
				$("#btnsave").removeAttr("disabled");
			}else{
				$('#file_upload').uploadify('upload','*');
				ymPrompt.alert({"title":"提示","message":"修改成功,请返回",handler:function(){
					window.location.href="../subcontractorcs/subcontractorList?order=14";
				}});
			}
		}
	});
});
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
	}
function CONCAT(str1){
	var str=str1.split('-');
	var ary=new Array();
	var result="";
	for(var i=0;i<str.length;i++){
		if(str[i]!=""){
			ary.push(str[i]);
		}
	}
	if(ary.length==0){
		return result;
	}else for(var j=0;j<ary.length;j++){
		if(j==ary.length-1){
			result+=ary[j];
		}else{
			result+=ary[j]+"-";
		}
	}
	return result;
}
$("#reback").click(function(){
	window.location="../subcontractorcs/subcontractorList?order=14";
});
function downloadFile(i) 
{   
try{ 
var elemIF = document.createElement("iframe");   
elemIF.src = data1.list[i].url;   
elemIF.style.display = "none";   
document.body.appendChild(elemIF);   
}catch(e){ 

} 
} 
function btnDel(obj,contractappendixid){
	var table = document.getElementById("myfile");
	$.ajax({
	 	url: "../pactcs/pact_under_contractappendix_delete",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{contractappendixid:contractappendixid}, //参数     	               
	   	success:function(data){//回传函数
	 		if(data.msg=='ok'){
	 			$(obj).parent("td").parent("tr").empty();
	 		}
		}
	});
}
//数据字典
function f_contract(str,className) {
	var types = str;
	$.ajax({
		url: "../waybillcs/selectListByType",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":types}, //参数     	               
	   	success:function(data){//回传函数
	 		var dataObj=eval(data);
	 		var opent = ''; 
 			for(var i=0;i<dataObj.length;i++){
 				if(i==0){
 					opent += '<option value="'+dataObj[i].text+'" selected="selected">'+dataObj[i].text+'</option>';
 				}else {
 					opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
				}
	 			
	 		}
	 		$("."+className).html(opent);
	 		initSelect(className);
	 	}
	});
}
function initSelect(className){
	var addSelect = $("#"+className+"Div").html();
	//alert(yshySelect)
	$(addSelect).replaceAll("#"+className);
}
//新增并赋值select
function addSelect(className,value){
	//alert(className+":"+value);
	var addSelect = $("#"+className+"Div").html();
	var htmlStr ='';
	var i = 2;
	//有隐藏select 长度比页面多一个
	var yshyLen = $(".yshy").length;
	var jyfwLen = $(".jyfw").length;
	if(yshyLen>jyfwLen){
		if(className=="yshy"){
			str = "yshy";
			i = yshyLen;
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'"></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}else{
			str = "jyfw";
			i = jyfwLen;
			htmlStr = addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a>';
			$("#"+className+"Span"+jyfwLen).html(htmlStr);
		}
	}else if(yshyLen<jyfwLen){
		if(className=="yshy"){
			str = "yshy";
			i = yshyLen;
			htmlStr = addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a>';
			$("#"+className+"Span"+yshyLen).html(htmlStr);
		}else{
			str = "jyfw";
			i = jyfwLen;
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'"></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}
	}else{
		i = jyfwLen;
		if(className=="yshy"){
			str = "yshy";
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'"></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}else{
			str = "jyfw";
			htmlStr = '<tr id="addTr'+i+'"><td class="t_left"></td><td class="yshyTd"><span class="addSpan" id="yshySpan'+i+'"></span></td><td class="t_left"></td><td class="jyfwTd"><span class="addSpan" id="jyfwSpan'+i+'">'+addSelect+'<a onclick=deleteSelect("'+str+'","'+i+'") href="#">删除</a></span></td></tr>';
			$('#addTr'+(i-1)).after(htmlStr);
		}
	}
	if(value!=""){
		$("#"+className+"Span"+i+" select").val(value);
	}
}
//删除 select
function deleteSelect(className,i){
	i = i/1;
	$("."+className+"Td").each(function(k,obj){
		$("#"+className+"Span"+(i+k)+" select").val($("#"+className+"Span"+(i+k+1)+" select").val());
	});
	var len = $("."+className).length;
	$("#"+className+"Span"+(len-1)).empty();
	if($("#yshySpan"+(len-1)).html()==""&&($("#yshySpan"+(len-1)).html()=="")){
		//alert(len)
		$("#addTr"+(len-1)).remove();
	}
}
function checkNum(num){
	if(isNaN(num)){
		ymPrompt.alert("请输入数字！");
		$("#btnsave").removeAttr("disabled"); 
		return false;
	}
	return true;
}