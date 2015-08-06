/**获取当前时间yyyy-MM-dd格式
 * @author WJL
 * @param time //传入时间类型日期
 * @returns {String}返回yyyy-MM-dd格式字符串
 */
function getyyyyMMddTime(time){ 
    var year = time.getFullYear();       //年
    var month = time.getMonth() + 1;     //月
    var day = time.getDate();            //日
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day;
    return(clock); 
} 
/**跳转
 * @author WJL
 * @param id
 * @parambe fore
 * @param after 跳转到1司机,2web企业，3订单，4货物，5公司，6司机评价，7企业企业评价 
 * 8司机操作日志，9企业操作日志,10货源历史状态,11订单详细信息
 */
function jumpHtml(before,after,id){
	var funName = "";
	var url = jcontextPath ;
	switch (after) {
	case 1:
		funName = '司机查询';
		url += "/dctms/driverUser/queryDriverUserInfo.jsp";
		url += "?mainForm_driverUserInfoDomain_id";
		break;
	case 2:
		funName = '企业查询';
		url += "/dctms/webUser/queryWebUserInfo.jsp";
		url += "?mainForm_webUserInfoDomain_id";
		break;
	case 3:
		funName = '订单查询';
		url += "/dctms/transaction/queryTransactionInfo.jsp";
		switch(before){
		case 1:
			url += "?mainForm_transactionInfoDomain_driverId";
			break;
		case 2:
			url += "?mainForm_transactionInfoDomain_deployUserid";
			break;
		case 4:
			url += "?mainForm_transactionInfoDomain_cargoId";
			break;
		}
		break;
	case 4:
		funName = '货物查询';
		url += "/dctms/orderCargo/queryOrderCargoInfo.jsp";
		switch(before){
		case 2:
			url += "?mainForm_orderCargoInfoDomain_deployUserid";
			break;
		case 3:
			url += "?mainForm_orderCargoInfoDomain_id";
			break;
		}
		break;
	case 6:
		funName = '司机评价查询';
		url += "/dctms/userDriverAssess/queryUserDriverAssessInfo.jsp";
		switch(before){
		case 1:
			url += "?mainForm_userDriverAssessInfoDomain_driverId";
			break;
		case 2:
			url += "?mainForm_userDriverAssessInfoDomain_userId";
			break;
		case 3:
			url += "?mainForm_userDriverAssessInfoDomain_transactionId";
			break;
		case 4:
			url += "?mainForm_userDriverAssessInfoDomain_cargoId";
			break;
		}
		break;
	case 7:
		funName = '企业评价查询';
		url += "/dctms/driverUserAssess/queryDriverUserAssessInfo.jsp";
		switch(before){
		case 1:
			url += "?mainForm_driverUserAssessInfoDomain_driverId";
			break;
		case 2:
			url += "?mainForm_driverUserAssessInfoDomain_userId";
			break;
		case 3:
			url += "?mainForm_driverUserAssessInfoDomain_transactionId";
			break;
		case 4:
			url += "?mainForm_driverUserAssessInfoDomain_cargoId";
			break;
		}
		break;
	case 8:
		funName = '司机操作日志';
		url += "/dctms/operationLog/queryDriverOperationLog.jsp";
		url += "?mainForm_operationLogDomain_userDriverId";
		break;
	case 9:
		funName = '企业操作日志';
		url += "/dctms/operationLog/queryWebOperationLog.jsp";
		url += "?mainForm_operationLogDomain_userDriverId";
		break;
	case 10:
		funName = '货源历史状态';
		url += "/dctms/orderCargo/queryOrderCargoLastInfo.jsp";
		switch(before){
		case 4:
			url += "?mainForm_orderCargoLastInfoDomain_cargoId";
			break;
		}
		break;
	case 11:
		funName = '订单历史状态';
		url += "/dctms/transaction/queryTransactionLastInfo.jsp";
		switch(before){
		case 3:
			url += "?mainForm_transactionLastInfoDomain_transactionId";
			break;
		}
		break;
		
	}
	url+="="+id;
	parent.parent.document.getElementById("FRM_BLANK").contentWindow.navigate(url,funName,true);
}
/**
 * 根据URL得到传递过来的值，传递过来的值仅仅一个初始化这个值
 * @author WJL
 */
function queryConditionInit(){
		thisURL = document.URL;
		var start = thisURL.indexOf("?");
		if(start>0){
			var end = thisURL.indexOf("=");
			if(end>start){
				var id = thisURL.substring(start+1,end);
				var value = thisURL.substring(end+1);
				document.getElementById(id).value=value;
			}
		}
}
/**
 * 展示分页界面
 * @author WJL
 * @param dataList 页面要展示的dataList
 * @param curPageNos 当前页
 * @param columus 要展示的列
 * @param isOperateFlag  是否有操作内容，数字1表示可以操作，数字0表示不可以操作
 * @param operateObject 操作对象，格式为“方法名：操作名称”
 * @param operateObjectMultiParameter 操作对象，格式为“方法名(1,2,：操作名称”,多参数传递
 * @param checkbox复选框	 格式为{"name":name,"value":value}name为复选框里的名字，id为里面的值实际上是列名
 */
function showPageTable(dataList,columus,curPageNos,isOperateFlag,operateObject,operateObjectMultiParameter,checkbox){
	var table = document.getElementById("dataList");
	var tr =null;
	var td = null;
	//删除已有数据
	while(table.rows.length>1){
		table.deleteRow(1);
	}
	
	//获取开始数据
	var initCount = 20*(curPageNos-1);
	for(var i =0;i<dataList.length;i++){
		tr = table.insertRow(i+1);
		tr.insertCell(0).innerHTML=i+1+initCount;
		var colsNum = 0;
		if(checkbox!=null){
			colsNum++;
			tr.insertCell(colsNum).innerHTML='<input type="checkbox" name="'+checkbox["name"]+'" value="'+dataList[i][checkbox["value"]]+'"/>';
		}
		colsNum += isOperateFlag;
		if(isOperateFlag==1){
			var operateContent = "";
			for(var funName in operateObject){
				operateContent += '<a  href="#" onclick="'+funName+'('+dataList[i]['id']+');">'+operateObject[funName]+' </a>'
			
			}
			for(var funName in operateObjectMultiParameter){
				operateContent += '<a  href="#" onclick="'+funName+dataList[i][operateObjectMultiParameter[funName][0]]+');">'+operateObjectMultiParameter[funName][1]+' </a>'
			
			}
			tr.insertCell(colsNum).innerHTML= operateContent;
			
		}
		for(var j in columus){
			colsNum++;
			td = tr.insertCell(colsNum)
			td.innerHTML=dataList[i][columus[j]]==null?"":dataList[i][columus[j]];
			td.title = dataList[i][columus[j]]==null?"":dataList[i][columus[j]];
		}
	}
}
/**复选框全选与全不选
 * @author WJL
 * @param id 被点击的id
 * @param name 要全选的复选框名字
 */
function oncheckAll(id,name){
	var checkAll = document.getElementById(id).checked;
	var list = document.getElementsByName(name);
	if(checkAll){
		for(var i = 0; i<list.length;i++){
			list[i].checked=true;
		}
	}else{
		for(var i = 0; i<list.length;i++){
			list[i].checked=false;
		}
	}
}
/**数组转换为对象
 * @author WJL
 * @param array要转换的数组，这个数组是得到的
 * @param key  要转换为对象的key
 * @param data 传入的对象，在这个对象的基础上加值，如果没有值那就不传
 */
function arrayToObject(array,key,data){
	if(!data){
		data = {};
	}
	for(var i=0;i<array.length;i++){
		data[key+"["+i+"]"]=array[i];
	}
	return data;
}
//由于jquery获取复选框获取到的是所有被选中的值，所以用js重写
/**获取多选框，单选框被选中的值
 * @author WJL
 * @param array要转换的数组，这个数组是得到的
 * @param key  要转换为对象的key
 * @param data 传入的对象，在这个对象的基础上加值，如果没有值那就不传
 */
function getCheckValue(name){
	var arrayAll = document.getElementsByName(name);
	var arrayCheck =[];
	for(var i = 0 ;i<arrayAll.length;i++){
		if(arrayAll[i].checked){
			arrayCheck.push(arrayAll[i].value);
		}
	}
	return arrayCheck;
}
/**对表格的某一列进行合并
 * @author WJL
 * @param rows表格的所有行
 * @param col  要合并的列
 */
function contactRow(rows,col,start,end){
	var foreData  = "";
	var rowspan = 1;
	//从最后一行开始，到倒数第二行结束，倒数第二行为总计空值。
	for(var i =start;i>=end-1;i--){
		//处理第一条数据也就是表格的最后一条数据
		if(i==start){
			foreData = rows[i].cells[col].innerHTML;
			continue;
		}
		if(foreData!=rows[i].cells[col].innerHTML){
			foreData = rows[i].cells[col].innerHTML;
			rows[i+1].cells[col].rowSpan =rowspan;
			rowspan = 1;
		}else{
			rows[i+1].deleteCell(col);
			rowspan++;
		}
	}
}
