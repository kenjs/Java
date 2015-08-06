<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>快到网-营销平台-客户管理-统计分析</title>
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<sys:context/>/resource/js/base.js"></script>
    <script type="text/javascript">
        $(function(){
            var joinGroupKey = "${info.joinGroup}";
            //初始化查询条件
            initSelect(joinGroupKey);
        });

        //初始化查询条件
        function initSelect(joinGroupKey){
            $("#joinGroup").val(joinGroupKey);
        }

        //查询
        function selectSubmit(eobj){
            $(eobj).attr({"disabled":"disabled"});
            //验证查询条件
            if(!valdSelectInput()){
                $(eobj).removeAttr("disabled");
                return;
            }
            $("#mainForm").submit();
        }

        //验证查询条件
        function valdSelectInput(){
            if($("#startNextDate").val() == ""){
                addErrorMessager($("#startNextDate")[0], "查询开始日期不能为空！");
                return false;
            }
            if($("#endNextDate").val() == ""){
                addErrorMessager($("#endNextDate")[0], "查询截至日期不能为空！");
                return false;
            }
            return true;
        }

        //获得焦点执行清除提示消息
        function on_focus(eobj){
            var arr = $("input[type='text']");
            if(!arr || arr.length == 0){
                return;
            }
            for(var i = 0;i < arr.length;i++){
                cancelErrorMessager(arr[i]);
            }
        }
    </script>
</head>
<body>
<jsp:include page="/swp/head.jsp" />

<div class="mian">
    <div class="mian_bor">
        <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
        <input type="hidden" id="menuAIdHi" value="${menuAId}" name="menuAId"/>

        <div class="slideox" style="width:889px; float: right;">
            <%--查询条件--%>
            <div class="fiery tions">
<sf:form id="mainForm" action="${contextPath}/enterStatisticalAnalyse.jspx" method="post">
                <div class="ntact">
                    <ul>
                        <li>
                            <select id="joinGroup" name="joinGroup">
                                <option value="-1">所有成员</option>
                                <option value="1">营销一组</option>
                                <option value="2">营销二组</option>
                                <option value="3">营销三组</option>
                                <option value="0">未分组</option>
                            </select>
                        </li>
                        <li>
                            <input style="width: 100px;" type="text" readonly="readonly" onClick="WdatePicker();" onfocus="on_focus(this);" id="startNextDate" name="startNextDate" value="${info.startNextDate}"/>
                        </li>
                        <li>
                            &nbsp;~&nbsp;
                        </li>
                        <li>
                            <input style="width: 100px;" type="text" readonly="readonly" onClick="WdatePicker();" onfocus="on_focus(this);" id="endNextDate" name="endNextDate" value="${info.endNextDate}"/>
                        </li>
                        <li>
                            <a href="###" onclick="selectSubmit(this);" class="about">查询</a>
                        </li>
                    </ul>
</sf:form>
                </div>
            </div>
            <%--数据展示--%>
            <div class="hrble">
                <div style="overflow:auto;">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <thead>
                            <tr align="center">
                                <td width="7%">序号</td>
                                <td width="15%">成员名称</td>
                                <td width="15%">客户数量</td>
                                <td width="15%">呼出总量</td>
                                <td width="16%">有效电话量</td>
                                <td width="16%">司机注册量</td>
                                <td width="16%">司机认证量</td>
                            </tr>
                        </thead>
                        ${info.listHtml}
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
