<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ӭ����-�쵽��-��������ƽ̨-��̨����ϵͳ</title>
<script type="text/javascript" src="<sys:context/>/resource/js/homePage.js"></script>
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    $(function(){
    	queryDriverCar();
		queryOrderCargo();
		queryEndDeployMainOrderCargo();
	});
</script>
</head>

<body>
<jsp:include page="/DCTSTop.jsp" />
<div class="cargoBox1">
	<div class="cargo">
        	<div class="title">
            	 <div class="left">��Դ��Ϣ</div>
                <!--<ul class="newNav">
                	<li>���³�Դ��</li>
                	<li>�㽭�����������޹�˾</li>
                    <li>�ػ�</li>
                    <li>30��</li>
                    <li>���ݡ��Ϻ�</li>
                    <li>2014-3-8</li>
                    <li><img src="<sys:context/>/resource/images/new.gif" /></li>
                </ul> -->
                <div class="right"><a href="javascript:location.href='<sys:context/>/queryDriverCarInfo'" style="color: white;font-size: 18px;">���೵Դ</a></div>
            </div>
            <div class="tableBox" id="DriverCarHtnlId">
           </div>
    </div>
</div>
<div class="cargoBox2">
	<div class="cargo">
        	<div class="title">
            	<div class="left">��Դ��Ϣ</div>
                <!-- <ul class="newNav">
                	<li>���»�Դ��</li>
                	<li>�㽭�����������޹�˾</li>
                    <li>�ػ�</li>
                    <li>30��</li>
                    <li>���ݡ��Ϻ�</li>
                    <li>2014-3-8</li>
                    <li><img src="<sys:context/>/resource/images/new.gif" /></li>
                </ul>-->
                <div class="right"><a href="javascript:location.href='<sys:context/>/queryStartDeployOrderCargoInfo'" style="color: white;font-size: 18px;">�����Դ</a></div>
            </div>
        <div class="tableBox" id="OrderCargoHtnlId">
        </div>
    </div>
</div>
<div class="cargoBox3">
	<div class="cargo">
        	<div class="title">
            	<div class="leftLong">���׼�¼</div>
                
                <div class="rightLong"><a href="javascript:location.href='<sys:context/>/queryEndDeployOrderCargoInfo'" style="color: white;font-size: 18px;">���ཻ�׼�¼</a></div>
            </div>
        <div class="tableBox" id="queryEndDeployMainOrderCargoHtnlId">
        </div>
    </div>
</div>

<div class="phoneBox">
	<div class="phone">
    	   <img class="shouji" src="<sys:context/>/resource/images/iphone.png" />
           <div class="rightBox">
           	<div class="imgBox">
        	<img class="logoImg" src="<sys:context/>/resource/images/logoBig.png" />
            </div>
            <div class="appBox">
            	<span class="leftSpan">������ҵAPP</span>
                <span class="rightSpan">˾��APP</span>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/DCTSBottom.jsp" />
</body>
</html>