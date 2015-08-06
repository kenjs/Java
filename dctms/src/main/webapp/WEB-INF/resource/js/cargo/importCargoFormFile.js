 	//下载模板 
	function xiazmb(url){
		parent.location.href = url;
	}
	
	//导入货源
	function importCargo(){		
		$("#successHtmlId").html("");
		//防止二次提交
		$("#saveBtn").attr('disabled','disabled');
		var filePath=$("#uploadFile").val();
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
		$("#mainForm").attr("action",jcontextPath+"/importOrderCargoFromFile");
		$('#mainForm').submit();
			
		return true; 
	}
	
	//清空提示
	function cleanContext(){  
	  $("#errorHtmlId").html("");
	 $("#successHtmlId").html("");
	}