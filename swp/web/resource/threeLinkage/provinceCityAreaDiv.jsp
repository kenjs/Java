<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<head>
<title>地址</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    
<style type="text/css">
	.clearfix{overflow:auto;height:1%}
	img{border:0; vertical-align:middle}
	/*a,a:visited{font-size:12px;color:#000;text-decoration:none;}*/
	a:hover{ text-decoration:underline;color:#333}
	.cityinput { background:url(<sys:context/>/resource/threeLinkage/images/ts-city.png); width:180px; height:30px; border:#d6d6d6 1px solid; margin-top:5px; text-indent:5px; line-height:30px;   text-indent:5px;}
	.cityzinput { background:url(<sys:context/>/resource/threeLinkage/images/ts-zcity.png); width:180px; height:30px; border:#d6d6d6 1px solid; margin-top:15px; text-indent:5px; line-height:30px;   text-indent:5px;}
	a,a:hover{text-decoration:none}img{border:0}ul{list-style:none;margin:0;}
	.h3{font-size:13px;font-weight:bold;}#show{background:url(<sys:context/>/resource/threeLinkage/images/photo.jpg)}
	#meun{color:#fff;padding-left:10px;}#meun img{float:left;}#submeun{margin-left:70px;float:left;}
	#submeun span{text-align:center;margin-right:10px;float:left;display:inline;}
	#submeun span a{color:#fff;height:50px;line-height:50px;font-size:14px;font-weight:bold;text-align:center;padding-left:15px;padding-right:15px;display:block;}
	#submeun span.cur{text-align:center;background:#82ce18;margin-right:10px;float:left;display:inline;}
	#top{background-color:#000;margin:0em 0 10px 0em;border-style:solid;border-width:1px;border-color:#E5E5E5;height:50px;line-height:50px;}
    </style>
  </head>
  
  <body>
             <!--弹出省市区县-->
	<div class="provinceCityAll">
	  <div class="tabs clearfix">
	    <ul class="">
	      <li><a href="javascript:" class="current" tb="hotCityAll">热门城市</a></li>
	      <li><a href="javascript:" tb="provinceAll">省份</a></li>
	      <li><a href="javascript:" tb="cityAll" id="cityAll">城市</a></li>
	      <li><a href="javascript:" tb="countyAll" id="countyAll">区县</a></li>
	    </ul>
	  </div>
	  <div class="con">
	    <div class="hotCityAll invis">
	      <div class="pre"><a></a></div>
	      <div class="list">
	        <ul>
	          <!-- 					<li><a href="javascript:"  class="current">南京</a></li> -->
	        </ul>
	      </div>
	      <div class="next"><a class="can"></a></div>
	    </div>
	    <div class="provinceAll invis">
	      <div class="pre"><a></a></div>
	      <div class="list">
	        <ul>
	          <!-- 					<li><a href="javascript:"  class="current">江西省</a></li> -->
	        </ul>
	      </div>
	      <div class="next"><a class="can"></a></div>
	    </div>
	    <div class="cityAll invis">
	      <div class="pre"><a></a></div>
	      <div class="list">
	        <ul>
	          <!-- 					<li><a href="javascript:"  class="current">南京</a></li> -->
	        </ul>
	      </div>
	      <div class="next"><a class="can"></a></div>
	    </div>
	    <div class="countyAll invis">
	      <div class="pre"><a></a></div>
	      <div class="list">
	        <ul>
	        </ul>
	      </div>
	      <div class="next"><a class="can"></a></div>
	    </div>
	  </div>
	</div>
  </body>
</html>
