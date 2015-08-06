<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>快到网-营销平台-客户管理-导入司机客户</title>
    <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/driverClient/importDriverFile.js"></script>
    <script type="text/javascript">
        $(function(){
            var result = '${result}';//导入结果
            var sucNum = '${rtmapdata.sucNum}';//成功条数
            var errorNum = '${rtmapdata.errorNum}';//失败条数
            //导入成功后打开提示
            openPrompt(result, sucNum, errorNum);
        });
    </script>
</head>

<body>
<jsp:include page="/swp/head.jsp" />
<div class="mian">
    <div class="mian_bor">
        <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
        <div style="width:889px; float: right;">
            <sf:form  method="post" id="mainForm" action="${contextPath}/importExcelDriverMess.jspx" namespace="/"  enctype="multipart/form-data">
                <input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
                <input type="hidden" id="importType" name="importType" value="${importType}"/>
                <input type="hidden" id="driverIds" name="driverIds"/>
                <input type="hidden" id="refreshIds" name="refreshIds" value="${rtmapdata.refreshIds}"/>
                <div class="fileu">
                    <h3 class="eview"><i>&nbsp;</i>导入客户</h3>
                    <ul>
                        <li>
                            <label>选择文件：</label>
                            <input type="file" id="uploadFile" name="uploadFile" onfocus="cleanContext();">
                        </li>
                        <li><b style="margin-left:75px;color:red;" id="errorHtmlId">${errorMessage}</b></li>
                        <li>
                            <label></label>
                            <input onclick="xiazmb('${contextPath}/downloadDriverFileTemplate.jspx?fileName=driver.xls');" type="button" value="下载模版" />
                            <input id="saveBtn" onclick="importDriver();" type="button" value="导入" />
                        </li>
                        <li id="sucessLiMess" style="display: none;">
                            <%--导入成功提示--%>
                            <p class="word">恭喜您导入成功！成功${rtmapdata.sucNum}条，${rtmapdata.errorNum}条数据库已存在（未分配：${rtmapdata.noAssignNum}，已分配：${rtmapdata.allocatedNum}）</p>
                        </li>
                    </ul>
                </div>
                <div class="slideox" id="addClientDiv" style="display: none;">
                    <div class="hd">
                        <ul>
                            <li name="tableLi" class="on" onclick="cutTablePage(0);">待分配</li>
                            <li name="tableLi" onclick="cutTablePage(1);">导入成功</li>
                        </ul>
                    </div>
                    <div class="bd">
                        <div id="noAssignDiv" style="display: none;">
                            <div class="hrble"  style="overflow:auto;">
                                <table style="width:1200px;" border="0" cellpadding="0" cellspacing="0">
                                    <thead>
                                        <tr align="center">
                                            <td style="width:4%;">&nbsp;</td>
                                            <td style="width:9%;">司机姓名</td>
                                            <td style="width:11%;">车牌号</td>
                                            <td style="width:11%;">手机号码</td>
                                            <td style="width:11%;">最近使用时间</td>
                                            <td style="width:9%;">近15天使用<br/>次数</td>
                                            <td style="width:16%;">最近定位地点</td>
                                            <td style="width:11%;">最近定位时间</td>
                                            <td style="width:9%;">客户等级</td>
                                            <td style="width:9%;">客户状态</td>
                                        </tr>
                                    </thead>
                                    <tbody id="noAssignTable">
                                        ${rtmapdata.noAssignHtml}
                                    </tbody>
                                </table>
                            </div>
                            <div class="hrble">
                                <table id border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style='width:22px;' align="center">
                                            <input name="" type="checkbox" onclick="onclickSelectAll(this);" />
                                        </td>
                                        <td style="width:80px;" align="left">
                                            &nbsp;全选
                                        </td>
                                        <td class="seout">
                                            <input id="applyBut" type="button" value="分配申请" onclick="applyClient();" />
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                            <%--导入成功的客户--%>
                        <div class="hrble" id="saveSucessDiv" style="display: none;overflow:auto;">
                            <table style="width:1200px;" border="0" cellpadding="0" cellspacing="0">
                                <thead>
                                    <tr align="center">
                                        <td style="width:9%;">司机姓名</td>
                                        <td style="width:11%;">车牌号</td>
                                        <td style="width:11%;">手机号码</td>
                                        <td style="width:11%;">最近使用时间</td>
                                        <td style="width:9%;">近15天使用<br/>次数</td>
                                        <td style="width:16%;">最近定位地点</td>
                                        <td style="width:11%;">最近定位时间</td>
                                        <td style="width:8%;">客户状态</td>
                                        <td style="width:8%;">客户等级</td>
                                        <td style="width:8%;">所属人</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    ${rtmapdata.sucHtml}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </sf:form>
        </div>
    </div>
</div>
</body>
</html>
