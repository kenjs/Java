var param=getId().split("&")[1].split("=")[1];
var data1;
$(function(){
	$.ajax({
	 	url: "../pactcs/pactDetail",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"pactid":param,"tablename":"pact","tableid":param}, //参数     	               
	   	success:function(data){//回传函数
			$("#pactnumber").val(data.pactnumber);$("#type").val(data.type);$("#frompartyrealname").val(data.frompartyrealname);$("#frompartyrealname").attr("myid",data.frompartyid);$("#topartyrealname").val(data.topartyrealname);$("#topartyrealname").attr("myid",data.topartyid);
			$("#frompartysignman").val(data.frompartysignman);$("#topartysignman").val(data.topartysignman);$("#signdate").val(data.signdate);$("#enddate").val(data.enddate);$("#memo").val(data.memo);
			$("#pactid").val(data.pactid);
			var txt='';
			data1=data;
			if(data.list.length>0){
				for(var i=0;i<data.list.length;i++){
				txt=txt+'<tr style="height:32px;"><td class="tdleft"><span style="color:#1560ea;">'+data.list[i].filename+'</span><a href="javascript:void(0)" style=" margin-left:15px;" onclick="btnDel(this'+','+data.list[i].contractappendixid+')">'+"删除"+'</a></td></tr>';
			}
				$("#myfiles").append(txt);
			}
		}
	});
});
$(function(){
	$.ajax({
	 	url: "../contractdictionarycs/selectTextList",
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":"合同类型","random":Math.random()}, //参数     	               
	   	success:function(data){//回传函数
	 		for(var i=0;i<data.length;i++){
	 			var option=$("<option></option>").text(data[i].text);
	 			$("#type").append(option);
	 		}
		}
	});
});
function getId() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("?")
	idUrl = idUrl.substr(num + 1);
	return idUrl;
}
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
		return false;
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
	   alert(div);
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
function funTestDown(url) {
    var myrar = window.open(data1.list[url].url);
    myrar.document.execCommand("SaveAs");
    myrar.close();
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
$("#reback").click(function(){
	window.location="../pactcs/pactsList?order=17";
});

function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function btnSubmit(obj){
	$("#btnSubmit").attr("disabled","disabled");
	var pactnumber=trim($("#pactnumber").val()),type=trim($("#type").val()),frompartyrealname=trim($("#frompartyrealname").val()),
	frompartysignman=trim($("#frompartysignman").val()),topartyrealname=trim($("#topartyrealname").val()),
	signdate=trim($("#signdate").val()),enddate=trim($("#enddate").val());
	var frompartyid=$("#frompartyrealname").attr("myid")==undefined?"":$("#frompartyrealname").attr("myid"),topartyid=$("#topartyrealname").attr("myid")==undefined?"":$("#topartyrealname").attr("myid");
	var params1="pactnumber="+trim($("#pactnumber").val())+"&type="+trim($("#type").val())+"&frompartyrealname="+trim($("#frompartyrealname").val())+
				"&frompartysignman="+trim($("#frompartysignman").val())+"&topartyrealname="+trim($("#topartyrealname").val())+"&topartysignman="+trim($("#topartysignman").val())+
				"&memo="+trim($("#memo").val())+"&signdate="+signdate+"&enddate="+enddate+"&pactid="+$('#pactid').val()+"&frompartyid="+frompartyid+"&topartyid="+topartyid+"&random="+Math.random();
	if(pactnumber==""){
		$(".pactnumber").html("合同编号不能为空");
		$(".pactnumber").show();
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}else{
		$(".pactnumber").hide();
	}
	if(frompartyrealname==""){
		$(".frompartyrealname").html("合同甲方不能为空");
		$(".frompartyrealname").show();
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}else{
		$(".frompartyrealname").hide();
	}
	if(enddate==""){
		$(".enddate").html("合同到期日期不能为空");
		$(".enddate").show();
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}else{
		$(".enddate").hide();
	}
	if(!dateCompare(signdate, enddate)){
		$(".enddate").html("签订日期不能提前于到期日期");
		$(".enddate").show();
		$("#btnSubmit").removeAttr("disabled");
		return;
	}else{
		$(".enddate").hide();
	}
	if(topartyrealname==""){
		$(".topartyrealname").html("合同乙方不能为空");
		$(".topartyrealname").show();
		$("#btnSubmit").removeAttr("disabled");
		return ;
	}else{
		$(".topartyrealname").hide();
		$("#btnSubmit").removeAttr("disabled");
	}
	$.ajax( {
		url : "../pactcs/pactsEdit_data",
		dataType : "json",
		type :'POST',
		data:params1,
		success : function(data) {
				if(data.msg!='ok'){
					$("#btnSubmit").removeAttr("disabled");
				}
				else{
		    		 $('#file_upload').uploadify('upload','*');
					ymPrompt.alert({"title":"提示","message":"修改成功,请返回",handler:function(){
						window.location.href="../pactcs/pactsList?order=17";
					}});
				}
			}
		});
}
$("#pactnumber").focus(function(e){
	$(".pactnumber").hide();
	
});
$("#frompartyrealname").focus(function(e){
	$(".frompartyrealname").hide();
	
});
$("#topartyrealname").focus(function(e){
	$(".topartyrealname").hide();
	
});


//判断日期大小
function dateCompare(startdate,enddate)
{   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   
	  
	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
	if(starttimes>lktimes)    
	{   
		return false;   
	}
	else{
		return true;   
	}
}  