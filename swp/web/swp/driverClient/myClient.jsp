<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我的客户</title>
    <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/js/driverClient/myClient.js"></script>
    <script type="text/javascript">
        //加载
        $(function(){
            var registerKey = '${info.optRegister}';
            //初始化table分页
            initTablePage(registerKey);
            //初始化查询条件
            initSelect();
        });

        //初始化查询条件
        function initSelect(){
            $("#carLength").val('${info.carLength}');
            $("#carBarType").val('${info.carBarType}');
            $("#carPlateType").val('${info.carPlateType}');
            $("#realLevel").val('${info.realLevel}');
            $("#category").val('${info.category}');
            $("#pageSize").val('${page.pageSize}');
        }
    </script>
</head>
<body>
<jsp:include page="/swp/head.jsp" />
<%--总页数--%>
<input type="hidden" id="totalPages" value="${page.totalPages}"/>
<div class="mian">
    <div class="mian_bor">
        <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
        <div class="slideox" style="width:889px; float: right;">
            <div class="hd">
                <ul>
                    <li name="tableLi" onclick="cutTablePage('${registerYesKey}');">已注册司机</li>
                    <li name="tableLi" onclick="cutTablePage('${registerNoKey}');">未注册司机</li>
                </ul>
            </div>
            <sf:form id="mainForm" action="${contextPath}/enterMyCustom.jspx" method="post">
                <input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
                <input type="hidden" name="optRegister" id="optRegister" value="${info.optRegister}"/>
                <input type="hidden" name="curPage" id="curPage" value="${page.curPage}"/>
                <div class="fiery tions bd">
                    <ul>
                        <li><label>手机号码：</label><input id="mobilePhone" name="mobilePhone" type="text" value="${info.mobilePhone}" /></li>
                        <li><label>司机姓名：</label><input id="name" name="name" type="text" value="${info.name}" /></li>
                        <li><label>车牌号：</label><input id="carNumber" name="carNumber" value="${info.carNumber}" type="text" /></li>
                        <li><a id="selectButt" href="javascript:doSubmit();" class="about">查询</a></li>
                        <%--
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
                        </li>--%>
                    </ul>
                    <div class="ntact">
                        <ul>
                            <li><span>客户总量：${allDriverPage.totalRecords}个</span></li>
                            <li>
                                <select id="realLevel" name="realLevel" onchange="doSubmit()">
                                    <option value="">客户等级</option>
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                </select>
                            </li>
                            <li>
                                <select id="category" name="category" onchange="doSubmit()">
                                    <option value="">客户分类</option>
                                    <option value="1">1类</option>
                                    <option value="2">2类</option>
                                    <option value="3">3类</option>
                                    <option value="4">4类</option>
                                    <option value="5">5类</option>
                                    <option value="6">6类</option>
                                </select>
                            </li>
                            <li class="fr">
                                <select id="pageSize" name="pageSize" onchange="doSubmit()">
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <option value="200">200</option>
                                    <option value="300">300</option>
                                    <option value="500">500</option>
                                    <option value="1000">1000</option>
                                </select><span style="color: #000000;">条/页</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </sf:form>
            <div class="hrble">
                <div style="overflow:auto;">
                    <table style="width:1200px;" border="0" cellpadding="0" cellspacing="0">
                        <thead>
                        <tr align="center">
                            <td style="width:8%;">司机姓名</td>
                            <td style="width:11%;">车牌号</td>
                            <td style="width:11%;">手机号码</td>
                            <td style="width:12%;">最近联系时间</td>
                            <td style="width:12%;">最近使用时间</td>
                            <td style="width:9%;">近15天使用<br/>次数</td>
                            <td style="width:17%;">最近定位地点</td>
                            <td style="width:12%;">最近定位时间</td>
                            <td style="width:8%;">客户状态</td>
                        </tr>
                        </thead>
                        <tbody>
                            ${info.listHtml}
                        </tbody>
                    </table>
                </div>
                <!-- 分页开始 -->
                <div class="feye" id="pageInfoHtmlId">
                    ${info.pageHtml}
                </div>
                <!-- 分页结束 -->
            </div>
        </div>
    </div>
</div>

</body>
</html>
