<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>

<head>
<title>个人中心菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        $(function(){
            if($("#menuAIdHi").val()){
                $("#a_id_1").removeAttr("class");
                $("#" + $("#menuAIdHi").val()).attr("class", "on");
            }
            //禁止readOnly、disabled的输入框的Backspace键 作用于Firefox、Opera
            document.onkeypress = banBackSpace;
            //禁止readOnly、disabled的输入框的Backspace键 作用于IE、Chrome
            document.onkeydown = banBackSpace;
        });

        /** 禁止readOnly、disabled的输入框的Backspace键 */
        function banBackSpace(e){
            var ev = e || window.event;//获取event对象
            var obj = ev.target || ev.srcElement;//获取事件源
            var t = obj.type || obj.getAttribute('type');//获取事件源类型
            //获取作为判断条件的事件类型
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            //并且readOnly属性为true或disabled属性为true的，则退格键失效
            var flag1= ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")&& (vReadOnly==true || vDisabled==true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2= ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ;
            //判断
            if(flag2 || flag1)return false;
        }
    </script>
</head>

<body>
	<div class="fl sonafl ">
        	<ul class="uls">
	            <!-- <li><h4><span>短信管理</span></h4></li>
	            <li><a id="" href="${contextPath}/openAddNotePage.jspx"><i class="nox2">&nbsp;</i>发送短信</a></li>
	            <li><a id="" href="${contextPath}/queryLocalOrderCargoInfo.jspx?"><i class="nox3">&nbsp;</i>短信查询</a></li> -->
				<c:if test="${sessionScope.user.position==0}">
					<li><h4><span>用户管理</span></h4></li>
					<li><a id="a_id_7" href="${contextPath}/queryMarketingUserInfoDomainPageList.jspx"><i>&nbsp;</i>用户列表</a></li>
					<li><h4><span>导入管理</span></h4></li>
					<li><a id="a_id_1" class="on" href="${contextPath}/queryTodayImportInfo.jspx?mark=0"><i>&nbsp;</i>导入货源</a></li>
					<li><h4><span>客户管理</span></h4></li>
					<li><a id="a_id_2" href="${contextPath}/enterMyCustom.jspx"><i>&nbsp;</i>我的客户</a></li>
					<li><a id="a_id_3" href="${contextPath}/enterNoContCustom.jspx"><i>&nbsp;</i>未联系客户</a></li>
					<li><a id="a_id_4" href="${contextPath}/enterAddDriverInfo.jspx"><i>&nbsp;</i>添加客户</a></li>
					<li><a id="a_id_5" href="${contextPath}/enterCustom.jspx"><i>&nbsp;</i>客户分配</a></li>
					<li><a id="a_id_6" href="${contextPath}/enterStatisticalAnalyse.jspx"><i>&nbsp;</i>统计分析</a></li>
				</c:if>
				<c:if test="${sessionScope.user.position==1}">
                    <li><h4><span>用户管理</span></h4></li>
                    <li><a id="a_id_7" href="${contextPath}/queryMarketingUserInfoDomainPageList.jspx"><i>&nbsp;</i>用户列表</a></li>
					<li><h4><span>导入管理</span></h4></li>
					<li><a id="a_id_1" class="on" href="${contextPath}/queryTodayImportInfo.jspx?mark=0"><i>&nbsp;</i>导入货源</a></li>
					<li><h4><span>客户管理</span></h4></li>
					<li><a id="a_id_2" href="${contextPath}/enterMyCustom.jspx"><i>&nbsp;</i>我的客户</a></li>
					<li><a id="a_id_3" href="${contextPath}/enterNoContCustom.jspx"><i>&nbsp;</i>未联系客户</a></li>
					<li><a id="a_id_4" href="${contextPath}/enterAddDriverInfo.jspx"><i>&nbsp;</i>添加客户</a></li>
					<li><a id="a_id_5" href="${contextPath}/enterCustom.jspx"><i>&nbsp;</i>客户分配</a></li>
					<li><a id="a_id_6" href="${contextPath}/enterStatisticalAnalyse.jspx"><i>&nbsp;</i>统计分析</a></li>
	            </c:if>
				<c:if test="${sessionScope.user.position==2}">
					<li><h4><span>用户管理</span></h4></li>
					<li><a id="a_id_7" href="${contextPath}/queryMarketingUserInfoDomainPageList.jspx"><i>&nbsp;</i>用户列表</a></li>
					<li><h4><span>导入管理</span></h4></li>
					<li><a id="a_id_1" class="on" href="${contextPath}/queryTodayImportInfo.jspx?mark=0"><i>&nbsp;</i>导入货源</a></li>
					<li><h4><span>客户管理</span></h4></li>
					<li><a id="a_id_2" href="${contextPath}/enterMyCustom.jspx"><i>&nbsp;</i>我的客户</a></li>
					<li><a id="a_id_3" href="${contextPath}/enterNoContCustom.jspx"><i>&nbsp;</i>未联系客户</a></li>
					<li><a id="a_id_4" href="${contextPath}/enterAddDriverInfo.jspx"><i>&nbsp;</i>添加客户</a></li>
					<li><a id="a_id_5" href="${contextPath}/enterCustom.jspx"><i>&nbsp;</i>客户分配</a></li>
					<li><a id="a_id_6" href="${contextPath}/enterStatisticalAnalyse.jspx"><i>&nbsp;</i>统计分析</a></li>
				</c:if>
				<c:if test="${sessionScope.user.position==3}">
					<li><h4><span>导入管理</span></h4></li>
					<li><a id="a_id_1" class="on" href="${contextPath}/queryTodayImportInfo.jspx?mark=0"><i>&nbsp;</i>导入货源</a></li>
					<li><h4><span>客户管理</span></h4></li>
					<li><a id="a_id_2" href="${contextPath}/enterMyCustom.jspx"><i>&nbsp;</i>我的客户</a></li>
					<li><a id="a_id_3" href="${contextPath}/enterNoContCustom.jspx"><i>&nbsp;</i>未联系客户</a></li>
					<li><a id="a_id_4" href="${contextPath}/enterAddDriverInfo.jspx"><i>&nbsp;</i>添加客户</a></li>
				</c:if>
				<c:if test="${sessionScope.user.position==4}">
					<li><h4><span>导入管理</span></h4></li>
					<li><a id="a_id_1" class="on" href="${contextPath}/queryTodayImportInfo.jspx?mark=0"><i>&nbsp;</i>导入货源</a></li>
					<li><h4><span>客户管理</span></h4></li>
					<li><a id="a_id_2" href="${contextPath}/enterMyCustom.jspx"><i>&nbsp;</i>我的客户</a></li>
					<li><a id="a_id_3" href="${contextPath}/enterNoContCustom.jspx"><i>&nbsp;</i>未联系客户</a></li>
					<li><a id="a_id_4" href="${contextPath}/enterAddDriverInfo.jspx"><i>&nbsp;</i>添加客户</a></li>
				</c:if>
        </ul>
    </div>
</body>
</html>
