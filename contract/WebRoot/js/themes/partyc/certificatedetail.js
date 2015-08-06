//document.write('<link href="../css/themes/freightoffer.css" rel="stylesheet" />');
var url1,url2,url3,url4,url5;
url1=url2=url3=url4=url5="../imgs/sys/no-pic.jpg";
$(function (){ 
	var params=decodeURI(getparams());
	var paramsarray=params.split("&");	
	var carid=paramsarray[0].split("=")[2];
	var partyid=paramsarray[1].split("=")[1];
	var trailerid=paramsarray[2].split("=")[1];
	var partyname=paramsarray[3].split("=")[1];
	var destination=paramsarray[4].split("=")[1];
//	alert("carid="+carid+" partyid="+partyid+" trailerid="+trailerid);
	//初始化开始
	$("#partyname").html(partyname);
	$("#destination").html(destination);
	
	
	//取有效时间
	$.ajax( {
	type : "POST",
	url : "../partycs/queryInvalidDatebyPartyid?random="+ Math.random(),
	data:{"partyid":partyid},
	dataType : "json",
	error : function() {
		alert("加载错误")
	},
	success : function(data) {				     
	     
		if (data != "" && data.length != 0) {	
			$("#Partyinvaliddate").html(data.invaliddate.substr(0,10));

		}
	}
});
	
	//取诚司机信息
	$.ajax( {
		type : "POST",
		url : "../personcs/queryPersonDetailByPartyid?random="+ Math.random(),
		data:{"partyid":partyid},
		dataType : "json",
		error : function() {
			alert("加载错误")
		},
		success : function(data) {				     	     
			if (data != "" && data.length != 0) {		
				$("#realName").html(data.realname);//司机姓名
				$("#mobileNumber").html(data.mobilenumber);//手机号码
				$("#telephoneNumber").html(data.telephonenumber);//家庭电话
				$("#allowedDriveCarType").html(data.alloweddrivecartype);//准驾车型
				$("#certificateAddress").html(data.certificateaddress);//身份证地址
				$("#certificateNumber").html(data.certificatenumber);//证件号码
				$("#drivingLicenseAddress").html(data.drivinglicenseaddress);//驾驶证地址
				$("#drivingLicenseFileNumber").html(data.drivinglicensefilenumber);//驾驶证号码
				$("#drivingLicenseOffice").html(data.drivinglicenseoffice);//发证机关
				$("#drivingLicenseFirstDate").html(data.drivinglicensefirstdate.substr(0,19));//初次领证日期
//				$("#").html(data.);//发证日期
				$("#drivingLicenseValidDate").html(data.drivinglicensevaliddate.substr(0,19));//开始日期
				$("#drivingLicenseInvalidDate").html(data.drivinglicenseinvaliddate.substr(0,19));//保险终止日期
				$("#drivingLicenseValidYear").html(data.drivinglicensevalidyear);//驾证年限
				$("#drivingLicenseBreaklawCount").html(data.drivinglicensebreaklawcount);//违法次数
				$("#drivingLicenseAccidentCount").html(data.drivinglicenseaccidentcount);//事故次数
				$("#drivinglicensefilenumber").html(data.drivinglicensefilenumber);//登记证书编码（car）==驾证档案编号（person）==司机的驾驶证号码
				

			}
		}
	});
	
	//取车信息
	$.ajax( {
	type : "POST",
	url : "../carcs/querycardetailbycarid?random="+ Math.random(),
	data:{"carid":carid},
	dataType : "json",
	error : function() {
		alert("加载错误")
	},
	success : function(data) {				     
	     
		if (data != "" && data.length != 0) {	
			$("#carplatenumber").html(data.carplatenumber);//车牌号
			$("#carFactoryModel").html(data.carfactorymodel);//厂牌类型
			$("#carowneraddress").html(data.carowneraddress);//住址
			$("#carowner").html(data.carowner);//所有人
			$("#carcertificateoffice").html(data.carcertificateoffice);//发证机关
			$("#caridentcode").html(data.caridentcode);//车辆识别代码
			$("#carenginenumber").html(data.carenginenumber);//发动机号
			$("#caroutsidecolor").html(data.caroutsidecolor);//车身颜色
			$("#carfirstregisterdate").html(data.carfirstregisterdate.substr(0,19));//初次登记日期
			$("#carexamineenddate").html(data.carexamineenddate.substr(0,19));//检验有效期止
			$("#carinsureenddate").html(data.carinsureenddate.substr(0,19));//保险终止日期
			$("#carenginetype").html(data.carenginetype);//发动机型号
			$("#carstatus").html(data.carstatus);//车辆状态
			$("#carfactorydate").html(data.carfactorydate);//出厂日期
			$("#carLong").html(data.carlong);//车外郭长
			$("#carWidth").html(data.carwidth);//车外廓宽
			$("#carHigh").html(data.carhigh);//车外廓高
			$("#carverificationloadquality").html(data.carverificationloadquality);//核定载质量
			$("#carMass").html(data.carmass);//总质量
		

		}
	}
});
	
	//取挂车信息
	$.ajax( {
	type : "POST",
	url : "../carcs/querytrailerdetailbytrailerid?random="+ Math.random(),
	data:{"trailerid":trailerid},
	dataType : "json",
	error : function() {
		alert("加载错误")
	},
	success : function(data) {				     
	     
		if (data != "" && data.length != 0) {		
			$("#traileridentcode").html(data.traileridentcode);//挂车车架号 车辆识别代码 =cKnowcode
			$("#trailerPlateNumber").html(data.trailerplatenumber);//挂车号码
			$("#trailerplatenumber").html(data.trailerplatenumber);//挂车号码（车辆信息里的挂车号码）		
			$("#trailerlong").html(data.trailerlong);//挂车长
			$("#trailerwidth").html(data.trailerwidth);//挂车宽
			$("#trailerhigh").html(data.trailerhigh);//挂车高
	
		}
	}
});
	
	
	
	
	//取图片信息
	
		$.ajax( {
					type : "POST",
					url : "../partycs/queryPartyImagesByPartyid?random="+ Math.random(),
					data:{"partyid":partyid},
					dataType : "json",
					error : function() {
						alert("加载错误")
					},
					success : function(data) {				     
					     
						if (data != "" && data.length != 0) {	
							for ( var i = 0; i < data.length; i++) {
								if(data[i].keyname=="司机头像"){
									url1="http://e.tf56.com/webroot"+data[i].url;
									document.getElementById("image001").setAttribute("src",url1 );
									document.getElementById("image001").setAttribute("onClick","login_show('200px','0px')");
									document.getElementById("img_1").setAttribute("onClick","login_show('200px','0px')");
									};
								if(data[i].keyname=="证件照"){
									url2="http://e.tf56.com/webroot"+data[i].url;
									document.getElementById("image002").setAttribute("src",url2 );
									document.getElementById("image002").setAttribute("onClick","login_show('201px','0px')");
									document.getElementById("img_2").setAttribute("onClick","login_show('201px','0px')");
									};
								if(data[i].keyname=="车辆简项"){
									url3="http://e.tf56.com/webroot"+data[i].url;
									document.getElementById("image003").setAttribute("src",url3 );
									document.getElementById("image003").setAttribute("onClick","login_show('500px','0px')");
									document.getElementById("img_3").setAttribute("onClick","login_show('500px','0px')");
									};
								if(data[i].keyname=="司机简项"){
									url4="http://e.tf56.com/webroot"+data[i].url;
									document.getElementById("image004").setAttribute("src",url4 );
									document.getElementById("image004").setAttribute("onClick","login_show('501px','0px')");
									document.getElementById("img_4").setAttribute("onClick","login_show('501px','0px')");
									};
								if(data[i].keyname=="挂车简项"){
									url5="http://e.tf56.com/webroot"+data[i].url;
									document.getElementById("image005").setAttribute("src",url5 );
									document.getElementById("image005").setAttribute("onClick","login_show('800px','0px')");
									document.getElementById("img_5").setAttribute("onClick","login_show('800px','0px')");
									};

							}

						}
					}
				});


		//初始化结束
		

		 $("#canclebook").click(function() {
				//window.location="../goodssourcecs/index_goodssource_onscreen";
			 window.close();
			});


}); 




function getparams(){

	var idUrl= document.URL;
	var num=idUrl.indexOf("?") 
	idUrl=idUrl.substr(num+1);

	var params=decodeURI(idUrl);
	return idUrl;
  
 }

//调用鼠标悬停变色
//function f_onmouseover(obj){
//	  $(obj).hover(function(){
//		  $(this).css("background", 'url("../imgs/sys/bt-hover-bg.png")');
//     },
//   function(){
//  	   $(this).css("background", 'url("../imgs/sys/bt-bg.png")');
//   });
//   }

//设置图片自动调整  
function SetImgSize(pimg, iw, ih) { //pimg对象，iw缩略图宽度,ih缩略图高度  
	var img = new Image();
	img.src = pimg.src;
	var w = iw;
	var h = ih;

	if (img.width > 0 && img.height > 0) {
		if (img.width > iw || img.height > ih) {
			if ((iw / ih) > (img.width / img.height)) {
				h = ih;
				w = img.width * (ih / img.height);
			} else {
				w = iw;
				h = img.height * (iw / img.width);
			}
		} else {
			w = img.width;
			h = img.height;
		}
	}

	pimg.width = w;
	pimg.height = h;
	pimg.style.display = "";
} 

//function login_show(x,y){
//	document.getElementById("back_img").style.display="block";
//	document.getElementById("login_div1").style.display="block";
//	document.getElementById("login_div1").style.marginTop=x;
//	if(x=='200px'){document.getElementById("bigimage").setAttribute("src", url1);}
//	else if(x=='201px'){document.getElementById("bigimage").setAttribute("src", url2);}
//	else if(x=='1500px'){document.getElementById("bigimage").setAttribute("src", url3);}
//	else if(x=='501px'){document.getElementById("bigimage").setAttribute("src", url4);}
//	else if(x=='800px'){document.getElementById("bigimage").setAttribute("src", url5);}
//	
//}

function login_show(x,y){
	var url="../imgs/sys/no-pic.jpg";

	if(x=='200px'){url= url1}
	else if(x=='201px'){url= url2}
	else if(x=='500px'){url= url3}
	else if(x=='501px'){url= url4}
	else if(x=='800px'){url= url5}
	var params="url="+url;
	window.open('../partycs/pictureshow?random='+Math.random()+params);
}

function login_close(){
	document.getElementById("back_img").style.display="none";
	document.getElementById("login_div1").style.display="none";
}
