<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>快到网-营销平台-客户管理-客户分配</title>
    <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/driverClient/driverClientAssign.js"></script>
    <script type="text/javascript">
        //加载
        $(function(){
            //初始化查询条件
            initSelect();
        });

        //初始化查询条件
        function initSelect(){
            $("#realLevel").val('${info.realLevel}');
            $("#category").val('${info.category}');
            $("#pageSize").val('${page.pageSize}');
        }
    </script>
</head>
<body>
<jsp:include page="/swp/head.jsp" />
<div class="mian">
    <div class="mian_bor">
        <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
        <div class="slideox" style="width:889px; float: right;">
            <div class="hd">
                <ul>
                    <li name="tableLi" class="on" onclick="cutTablePage('${stateMap.waitKey}',0);">待分配的客户</li>
                    <li name="tableLi" onclick="cutTablePage('${stateMap.endKey}',1);">已分配的客户</li>
                    <li name="tableLi" onclick="cutTablePage('${stateMap.ingKey}',2);">分配申请审核</li>
                </ul>
            </div>
            <div class="bd">
<sf:form id="mainForm" method="post">
            <div class="fiery tions bd">
                <ul>
                    <li><label>手机号码：</label><input id="mobilePhone" name="mobilePhone" type="text" value="${info.mobilePhone}" /></li>
                    <li><label>司机姓名：</label><input id="name" name="name" type="text" value="${info.name}" /></li>
                    <li><label>车牌号：</label><input id="carNumber" name="carNumber" value="${info.carNumber}" type="text" /></li>
                    <li><a id="selectButt" href="javascript:doSubmit();" class="about">查询</a></li>
                </ul>
                <div class="ntact">
                    <input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
                    <input type="hidden" id="reviewType" name="reviewType"/>
                    <input type="hidden" id="allocateStatus" name="allocateStatus" value="${info.allocateStatus}"/>
                    <input type="hidden" id="phoneValid" name="phoneValid" value="${info.phoneValid}"/>
                    <input type="hidden" id="assisterId" name="assisterId"/>
                    <input type="hidden" id="driverIds" name="driverIds"/>
                    <input type="hidden" id="assisterApplyIds" name="assisterApplyIds"/>
                    <ul>
                        <li><a href="javascript:enterImportDriver('${importType}');" class="lbout">导入客户</a></li>
                        <li><span>客户数量：<font id="clientNumSpan">${page.totalRecords}</font>个</span></li>
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
                        <li>
                            <select id="optRegister" name="optRegister" onchange="doSubmit()">
                                <option value="">全部客户</option>
                                <option value="1">已注册</option>
                                <option value="0">未注册</option>
                            </select>
                        </li>
                        <li>
                            <select style="display: none;" id="assisterIdQuery" name="assisterIdQuery" onchange="doSubmit()">
                                <option value="">营销人员</option>
                                <c:if test="${fn:length(info.marketingUserlist) > 0 }">
                                    <c:forEach items="${info.marketingUserlist}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </c:if>
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
            <div class="hrble" style="min-height:222px;">
                <div style="overflow: auto;">
                    <table id="listTableId" style="width:1200px;" border="0" cellpadding="0" cellspacing="0">
                        ${info.listHtml}
                    </table>
                </div>
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style='width:10px;' align="center">
                            <input name="" type="checkbox" onclick="onclickSelectAll(this);" />
                        </td>
                        <td style="width:50px;" align="left">
                            &nbsp;全选
                        </td>
                        <td class="seout" id="buttonTdId" style="width:150px;display: none;" align="left">
                            <input id="agreeBut" type="button" value="同意" onclick="viewOnclick('10','agreeBut');" />
                            <input id="disagreeBut" type="button" value="不同意" onclick="viewOnclick('20','disagreeBut');" />
                        </td>
                        <td class="seout" align="right">
                            <select id="assignPersonSelect">
                                <option value="">营销人员</option>
                                <c:if test="${fn:length(info.marketingUserlist) > 0 }">
                                    <c:forEach items="${info.marketingUserlist}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <input id="assignBut" type="button" value="分配" onclick="assignClient();" />
                        </td>
                    </tr>
                </table>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
