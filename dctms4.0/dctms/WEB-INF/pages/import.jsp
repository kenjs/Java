<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>快到网-批量导入货源</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.js"></script>
<script type="text/javascript" src="resource/js/ajaxfileupload.js"></script>
<style type="text/css">
	.buttonT{
		height: 32px;
		width: 96px;
		background: url("resource/image/index/radius.jpg") no-repeat scroll -198px -220px transparent;
		line-height: 32px;
		cursor: pointer;
		margin: 0 5px;
	}
</style>
<script type="text/javascript">
function ajaxFileUpload(){
	//防止二次提交
	$("#saveBtn").attr('disabled','disabled');
	var filePath = $("#uploadFile").val();
	if(filePath==''){
		$("#errorHtmlId").html("请先选择要导入的文件!");
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(filePath.substring(filePath.lastIndexOf(".") + 1) != "xls"){
		$("#errorHtmlId").html("系统只支持xls格式的文件！");
		$("#saveBtn").removeAttr('disabled')
		return;
	}
    $.ajaxFileUpload({
     	url:'importExcel',            //需要链接到服务器地址
     	secureuri:false,
     	fileElementId:'uploadFile',                        //文件选择框的id属性
     	dataType: 'json',                                     //服务器返回的格式，可以是json, xml
     	success: function (data,status){      
         	//alert(data.msg);
         	$("#errorHtmlId").html(data.msg);
         	$("#uploadFile").val("");
         	$("#saveBtn").removeAttr("disabled");
     	},
     	error: function (data,status,e){
     		$("#errorHtmlId").html(e);
     		$("#uploadFile").val("");
     		$("#saveBtn").removeAttr("disabled");
     	}	
    });
      
  }
function cleanContext(){  
	$("#errorHtmlId").html("");	
}
</script>
</head>
<body>
<!--主要内容-->
<div class="mian">
	<div class="fl flant w850">
    <h3><i>&nbsp;</i>导入货源</h3>
    <form  method="post" id="mainForm" action="importExcel"  enctype="multipart/form-data">  	
    	<div class="round">
        	<ul>
            	 <li><label>选择文件：</label>
	                <input type="file" id="uploadFile" name="uploadFile" onfocus="cleanContext()"/>
	               </li>
	              <li><b style="margin-left:75px;color:red;" id="errorHtmlId"></b></li>
	              <li><label></label>	              	              
	              <a href="downloadExcel">下载模板</a>
	              <input type="button" onclick="ajaxFileUpload()" style="height: 32px;width: 96px;" class="buttonT" id="saveBtn" value="保存" />	              
	              <!--  <input type="button" onclick="xiazmb('<sys:context/>/openAddLocalOrderCargoInfo')" style="height: 32px;width: 96px;" class="buttonT" value="返回发布页面" />-->
	              </li>	            
            </ul>
        </div>
        </form>
    </div>
    <div class="fr w450">
    <div class="styfl">
    <h3 style="padding: 0 30px;"><i>&nbsp;</i>发布货源立享</h3>
    	<ul>
        	<li><a href="">智能匹配车源 </a></li>
        	<li><a href="">精准推送至海量车源</a></li>
        	<li><a href="">实时跟踪货物</a></li>
        	<li><a href="">司机身份证查询</a></li>
        </ul>
    </div>
    </div>
     <!-- 合作伙伴 -->
    <div class="both mh36"></div>
</div>
</body>
</html>
