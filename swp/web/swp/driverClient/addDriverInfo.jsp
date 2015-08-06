<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>快到网-营销平台-客户管理-添加客户</title>
    <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/driverClient/addDriverInfo.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<jsp:include page="/swp/head.jsp" />
<div class="mian">
    <div class="mian_bor">
        <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
        <div style="width:889px; float: right;">
            <sf:form id="mainForm" method="post">
                <input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
                <div class="fiery">
                    <h3 class="eview"><i>&nbsp;</i>添加客户</h3>
                    <ul>
                        <li>
                            <label>手机号码：<span style="color: #FF0000;">*</span></label>
                            <input id="mobilePhone" name="mobilePhone" type="text" onfocus="on_focus();" />
                            <div class="tsxt" id="mobilePhoneNull" style="display: none;margin-left: 68px;">
                                <img src="<sys:context/>/resource/image/sonal/met.jpg" />
                                手机号码不能为空
                            </div>
                            <div class="tsxt" id="mobilePhoneFormat" style="display: none;margin-left: 68px;">
                                <img src="<sys:context/>/resource/image/sonal/met.jpg" />
                                手机号码格式不正确
                            </div>
                        </li>
                        <li><label>司机姓名：</label><input id="name" name="name" type="text" /></li>
                        <li><label>车牌号：</label>&nbsp;<input id="carNumber" name="carNumber" type="text" /></li>
                        <li>
                            <label>车型：</label>
                            <select id="carLength" name="carLength">
                                <option value="">请选择</option>
                                <c:forEach items="${dataDictInfoDomain.carLengthData }" var="cargoTypeDataObj" varStatus="status">
                                    <c:if test="${status.index != 0}" >
                                        <option value="${cargoTypeDataObj.name }">${cargoTypeDataObj.name }</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <select id="carBarType" name="carBarType">
                                <option value="">请选择</option>
                                <c:forEach items="${dataDictInfoDomain.carBarTypeData }" var="cargoTypeDataObj" varStatus="status">
                                    <c:if test="${status.index != 0}" >
                                        <option value="${cargoTypeDataObj.name }">${cargoTypeDataObj.name }</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <select id="carPlateType" name="carPlateType">
                                <option value="">请选择</option>
                                <c:forEach items="${dataDictInfoDomain.carPlateTypeData }" var="cargoTypeDataObj" varStatus="status">
                                    <c:if test="${status.index != 0}" >
                                        <option value="${cargoTypeDataObj.name }">${cargoTypeDataObj.name }</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                    <div class="ufry"><a id="addButId" href="javascript:saveSubmit('addButId')">添加</a><a href="javascript:enterImportDriver('${importType}');">导入客户</a></div>
                </div>
            </sf:form>
        </div>
    </div>
</div>
</body>
</html>
