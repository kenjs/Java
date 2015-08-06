<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-发货单和回单图片放大显示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/jqueryImageSlides/css/preview.css" type="text/css" rel="stylesheet" />


<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
</style>


</head>
  
<body>
<div class="demo">
	<div id="preview">
		<div id="spec-n1">
			<div class="o-img">
				<img id="" alt="" src="<sys:context/>/outputImage?fileUrl=${clickImagePath }"  onerror="this.src='<sys:context/>/resource/jqueryImageSlides/imageDemo/none_347.gif'"  />
			</div>
			<div class="switch">
				<a id="foward" title="上一张">&nbsp;</a>
				<a id="next" title="下一张">&nbsp;</a>
			</div>													
		</div><!--spec-n1 end-->
		
		<div id="spec-n5">
			<div id="spec-top" class="control disabled"></div>
			<div id="spec-bottom" class="control"></div>
			<div id="spec-list">
				<ul>
				<!-- 回单上传图片列表 -->
		        <s:if test="transactionReceiptPathList.size>0">
		        <s:iterator var="tradeReceiptPath" value="transactionReceiptPathList" status="imgIndexId">
		           <li>
		           <img id="<s:property value='#imgIndexId.index' />" alt="" width="128" height="96" name="${tradeReceiptPath.receiptPath }"  src="<sys:context/>/outputImage?fileUrl=${tradeReceiptPath.receiptPath }"/>
		           </li>
		          </s:iterator>
		        </s:if>
		        
					<!-- <li class="curr"><img width="128" height="96" name="demoimg/1.jpg" src="demoimg/1.jpg"/></li> -->
					
																				
				</ul>
			</div>
		</div><!--spec-n5 end-->
				
	</div><!--preview end-->
	
</div><!--demo end-->
											
	<div class="thickdiv" style="display:none;"></div>
	<div class="thickbox" style="width:272px;height:90px;display:none;">
		<div style="width:250" class="thicktitle">
			<span>提示</span>
		</div>
		<div style="width:250px;height:40px;" id="" class="thickcon">已经到最后一张了！</div>
		<a class="thickclose" href="#">×</a>
	</div>
</body>
<script type="text/javascript" src="<sys:context/>/resource/jqueryImageSlides/js/jquery-1.4.2.min.js"></script>						
<script type="text/javascript" src="<sys:context/>/resource/jqueryImageSlides/js/gallery.js"></script>

</html>
