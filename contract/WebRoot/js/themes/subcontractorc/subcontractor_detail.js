var param=getid();
var data1="";
$(function(){
$.ajax({
 	url: "../subcontractorcs/subcontractor_detail_json",   
 	type:'post',	
 	dataType:'json', 
 	data:{"partyid":param,"tablename":"party","tableid":param}, //参数     	               
   	success:function(data){//回传函数
 		data1=data;
		$("#partyname").html(data.partyname);$("#mobilenumber").html(data.mobilenumber);$("#email").html(data.email);$("#contact").html(data.contact);
		$("#telephonenumber").html(data.telephonenumber);$("#fax").html(data.fax);$("#subcontractorname").html(data.organization);
		$("#helpcode").html(data.helpcode);$("#officeaddress").html(data.province+data.city+data.region);$("#detailaddress").html(data.officeaddress);
		$("#legalperson").html(data.legalperson);$("#registeredcapital").html(data.registeredcapital==""?"--":data.registeredcapital+"万元");$("#employeescount").html(data.employeescount);
		$("#description").html(data.description);
		var txt="";
		if(data.list.length>0){
			for(var i=0;i<data.list.length;i++){
			/*txt=txt+'<tr style="height:25px;"><td class="tdleft"><a href="../attachment/'+data.list[i].filename+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a></td></tr>';*/
				txt=txt+'<tr style="height:25px;"><td class="tdleft"><a href="'+data.list[i].url+'" title="下载" style="color:#1560ea;">'+data.list[i].filename+'</a></td></tr>';
		}
			$("#myfiles").append(txt);
		}
		var params="topartyid="+param+"&shipperorsubcontractor=0";
		$.ajax({
			url: "../contractattributecs/selectContractAttributeInfo",
		 	type:'post',	
		 	dataType:'json', 
		 	data:params, //参数     	               
		   	success:function(data){
				$.each(data,function(k,obj){
					if(obj.attributeName == '业务员'){
						$('#saler').html(obj.attributeValue);
					}
					if(obj.attributeName == '车辆数量'){
						$('#clsl').html(obj.attributeValue);
					}
					if(obj.attributeName == '投保金额'){
						$('#tbje').html(obj.attributeValue);
					}
					if(obj.attributeName == '年营业额'){
						$('#nyye').html(obj.attributeValue);
					}
					if(obj.attributeName == '网点数量'){
						$('#wdsl').html(obj.attributeValue);
					}
					if(obj.attributeName == '年运输量'){
						$('#nysl').html(obj.attributeValue);
					}
					if(obj.attributeName == '是否使用系统'){
						$('#syxt').html(obj.attributeValue);
					}
					if(obj.attributeName == '优势行业'){
						var newstr=(obj.attributeValue).replace(new RegExp(/(,)/g),' ');
						$('#yshy').html(newstr);
					}
					if(obj.attributeName == '经营范围'){
						var newstr=(obj.attributeValue).replace(new RegExp(/(,)/g),' ');
						$('#jyfw').html(newstr);
					}
				});
		}
		});
		
	}
});
});
function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("=")
	idUrl = idUrl.substr(num + 1);
	return idUrl;
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