<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>快到网-营销平台-客户管理-未联系客户</title>
    <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
    <script type="text/javascript" src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/driverClient/noContactClient.js"></script>
    <script type="text/javascript">
        //加载
        $(function(){
            //初始化查询条件
            initSelect();
        });

        //初始化查询条件
        function initSelect(){
            var noCustomType = '${info.noCustomType}';
            $("#noCustomMonth").val('${info.noCustomMonth}');
            var arr = $("input[name='noCustomType']");
            if(arr && arr.length > 0){
                for(var i = 0;i < arr.length;i++){
                    if($(arr[i]).val() == noCustomType){
                        //选中
                        $(arr[i]).attr("checked", "checked");
                        break;
                    }
                }
            }
            $("#pageSize").val('${page.pageSize}');
            if(noCustomType == "1"){
                //未联系月数查询
                $("#allCustomLiId").show();
            }else{
                //显示预约时间查询
                $("#makCustomLiId").show();
            }
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
        <div style="width:889px; float: right;">
            <div class="fiery tions bd">
                <sf:form id="mainForm" action="${contextPath}/enterNoContCustom.jspx" method="post">
                    <ul>
                        <li><label>手机号码：</label><input id="mobilePhone" name="mobilePhone" type="text" value="${info.mobilePhone}" /></li>
                        <li><label>司机姓名：</label><input id="name" name="name" type="text" value="${info.name}" /></li>
                        <li><label>车牌号：</label><input id="carNumber" name="carNumber" value="${info.carNumber}" type="text" /></li>
                        <li><a id="selectButt" href="javascript:doSubmit();" class="about">查询</a></li>
                    </ul>
                    <input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
                    <input type="hidden" name="curPage" id="curPage" value="${page.curPage}"/>
                    <div class="ntact">
                        <ul>
                            <li><span>客户数量：${page.totalRecords}个</span></li>
                            <li class="radioB">
                                <input name="noCustomType" type="radio" value="${allCustomKey}" onclick="doSubmit(10)" />所有客户<input name="noCustomType" type="radio" value="${makCustomKey}" onclick="doSubmit(10)"/>预约客户
                            </li>
                            <li id="makCustomLiId" style="display: none;margin-right: 20px;">
                                <input type="hidden" id="startNextDateHi" value="${info.startNextDate}">
                                <input type="hidden" id="endNextDateHi" value="${info.endNextDate}">
                                <input style="width: 100px;" id="startNextDate" name="startNextDate" type="text" readonly="readonly" onClick="date_onclick();" onfocus="on_focus();" value="${info.startNextDate}" />
                                <div class="tsxt" id="startNextDateNull" style="display: none;">
                                    <img src="<sys:context/>/resource/image/sonal/met.jpg" />
                                    查询预约开始日期不能为空
                                </div>
                                ~
                                <input style="width: 100px;" id="endNextDate" name="endNextDate" type="text" readonly="readonly" onClick="date_onclick()" onfocus="on_focus();" value="${info.endNextDate}" />
                                <div class="tsxt" id="endNextDateNull" style="display: none;margin-left: 122px;">
                                    <img src="<sys:context/>/resource/image/sonal/met.jpg" />
                                    查询预约截至日期不能为空
                                </div>
                            </li>
                            <li id="allCustomLiId" style="display: none;" onchange="doSubmit()">
                                <select id="noCustomMonth" name="noCustomMonth">
                                    <option value="-1">一个月</option>
                                    <option value="-3">三个月</option>
                                    <option value="-6">半年</option>
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
                </sf:form>
            </div>
            <div class="hrble" style="min-height:265px;">
                <div style="overflow:auto;">
                    <table style="width: 1200px;" border="0" cellpadding="0" cellspacing="0">
                        <thead>
                            <tr align="center">
                                <td style="width: 9%;">司机姓名</td>
                                <td style="width: 12%;">车牌号</td>
                                <td style="width: 12%;">手机号码</td>
                                <td style="width: 16%;">最近联系时间</td>
                                <td style="width: 16%;">最近使用时间</td>
                                <td style="width: 10%;">近15天使用<br/>次数</td>
                                <td style="width: 16%;">预约时间</td>
                                <td style="width: 9%;">客户状态</td>
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
