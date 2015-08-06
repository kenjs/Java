<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>快到网-营销平台-用户管理列表</title>
  <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
  <link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
  <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />

  <script type="text/javascript">
    $(function(){
      var totalPages = ${marketingUserInfoDomain.pageInfo.totalPages};//总页数
      var curPageNos = ${marketingUserInfoDomain.pageInfo.curPageNo};//当前页数
      var totalRecords = ${marketingUserInfoDomain.pageInfo.totalRecords};//总记录数
      cargoPageInfo(totalPages,curPageNos,totalRecords);//分页方法
    });

    function realMoreSbmint(str){
      $("#curPage").val(str);
      document.getElementById('mainForm').submit();
      return true;
    }
    function cargoPageInfo(totalPages,curPageNos,totalRecords) {
      var liststep = 5;//最多显示分页页数
      var totalPage = totalPages;//总页数
      var curpage = curPageNos;//当前页数
      var totalRecord = totalRecords;	//总记录数

      if (totalPage < curpage) {
        curpage = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
      }
      if (curpage < 1) {
        curpage = 1;//如果分页变量小于１,则将分页变量设为１
      }
      //计算要展示哪几页
      var listbegin;
      var listend;

      listbegin = curpage - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2));
      listend = curpage - 1 + ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2) + 1);// 分页信息显示到第几页

      if((totalPage - curpage) < parseInt(liststep /2)){
        listbegin = totalPage - liststep + 1;
        listend = totalPage;
      }
      if(curpage <= parseInt(liststep /2)){
        listbegin = 1;
        listend = liststep;
      }
      if (listbegin < 1) {
        listbegin = 1;
      }
      if (listend > totalPage) {
        listend = totalPage;
      }

      var pageHTML = "<dl>";
      pageHTML += "<dt><a href='javascript:realMoreSbmint(1);'>首页</a></dt>";
      if (curpage == 1) {//上一页
        pageHTML += "<dt><a href='javascript:realMoreSbmint("+curpage+");'>上一页</a></dt>";
      }
      if(curpage>1) {
        pageHTML += "<dt><a href='javascript:realMoreSbmint("+(curpage-1)+");'>上一页</a></dt>";
      }
      pageHTML += "</dl><ul>";
      for (var i = listbegin; i <= listend; i++) {
        if (i != curpage) {//如果i不等于当前页
          pageHTML += "<li><a href='javascript:realMoreSbmint("+i+");'>"+i+"</a></li>";
        } else {
          pageHTML += "<li><a class='ult'>"+i+"</a></li>";
        }
      }
      pageHTML += "</ul><dl>";
      //下一页
      if(curpage == totalPage) {
        pageHTML += "<dt><a href='javascript:realMoreSbmint("+curpage+");'>下一页</a></dt>";
      }else {
        pageHTML += "<dt><a href='javascript:realMoreSbmint("+(curpage + 1)+");'>下一页</a></dt>";
      }
      pageHTML += "<dd><a href='javascript:realMoreSbmint("+totalPages+");'>尾页</a></dd>";
      pageHTML += "<dd><a>共"+totalPages+"页\/"+totalRecord+"条信息</a></dd>";
      pageHTML += "</dl>";
      if(totalPage == 0){
        $("#pageInfoHtmlId").html("");
      }else {
        $("#pageInfoHtmlId").html(pageHTML);
      }
    }




    function updatePw(userId) {
      $.ajax({
        url: jcontextPath + "/updatePw.jspx",
        type:'post',
        dataType:'json',
        data:{'userId':userId},
        success:function(data){//回传函数
          if(data.result == 0) {//成功
            realMoreSbmint('1');
          }
        }
      });
    }

    function updateDeFl(userId,deleteFalg) {
      $.ajax({
        url: jcontextPath + "/updateDeFl.jspx",
        type:'post',
        dataType:'json',
        data:{'userId':userId,'deleteFalg':deleteFalg},
        success:function(data){//回传函数
          if(data.result == 0) {//成功
            realMoreSbmint('1');
          }
        }
      });
    }

    //打开新增或编辑页面
    function addUser(userId) {
      $.ajax({
        url: jcontextPath + "/addUserInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'userId':userId},
        success:function(data){//回传函数
          if(data.result == 0) {//成功
            var userInfo = data.content;
            outStr(userInfo,"编辑用户信息");
          }
        }
      });
    }

    //保存用户信息
    function saveUser() {
      var id = $("#id").val();
      var code = $("#code").val();
      if(code == null || code == "") {
        $("#updateNewsId").html("登录名不能为空！");
        return;
      }
      var name = $("#name").val();
      if(name == null || name == "") {
        $("#updateNewsId").html("用户名不能为空！");
        return;
      }
      var sex = $("#sex").val();
      var phoneNumber = $("#phoneNumber").val();
      var contactNumber = $("#contactNumber").val();
      var age = $("#age").val();
      var position = $("#position").val();
      if(position == null || position == "") {
        $("#updateNewsId").html("职务不能为空！");
        return;
      }
      var joinGroup = $("#joinGroup").val();
      var deleteFlag = $("#deleteFlag").val();
      $.ajax({
        url: jcontextPath + "/saveUserInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'code':code,'name':name,'sex':sex,'phoneNumber':phoneNumber,'contactNumber':contactNumber,'age':age,
        'position':position,'joinGroup':joinGroup,'deleteFlag':deleteFlag},
        success:function(data) {//回传函数
          if (data.result == 0) {//成功
            userInfocloses();
            getsAlertes("编辑用户成功！");
            realMoreSbmint("1");
          }
        }
      });
    }

    /**
     * 弹窗
     * @param strContent内容
     * @param strTitle标题
     */
    function outStr(strContent,strTitle){
      art.dialog({
        lock:false,
        title:strTitle,
        content: strContent
      });
    }

    //提示弹窗
    function getsAlertes(contentVal) {
      art.dialog({
        time:3,
        icon: 'error',
        content:contentVal
      });
    }

    /**
     *关闭弹窗
     */
    function userInfocloses() {
      var list = art.dialog.list;
      for (var i in list) {
        list[i].close();
      };
    }
  </script>

</head>
<body>
<jsp:include page="/swp/head.jsp" />
<div class="mian">
  <div class="mian_bor">
    <jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
    <input type="hidden" id="menuAIdHi" value="a_id_7" name="menuAId"/>
    <sf:form id="mainForm" action="${contextPath}/queryMarketingUserInfoDomainPageList.jspx" namespace="/" method="post">
      <input type='hidden' id='curPage' name='pageInfo.curPage' value='${marketingUserInfoDomain.pageInfo.curPage}' /><!-- 需要显示页面 -->
<div class="fiery tions">
  <ul>
    <li><label>登录名：</label><input name="code" type="text" /></li>
    <li style="position:relative;"><label>姓名：</label><input name="name" type="text" /></li>
    <li>
      <label>职务：</label>
      <select name="position">
        <option value="" <c:if test="${marketingUserInfoDomain.position == null}">selected="true"</c:if> ></option>
        <option value="0" <c:if test="${marketingUserInfoDomain.position == 0}">selected="true"</c:if>>高管</option>
        <option value="1" <c:if test="${marketingUserInfoDomain.position == 1}">selected="true"</c:if>>经理</option>
        <option value="2" <c:if test="${marketingUserInfoDomain.position == 2}">selected="true"</c:if>>主管</option>
        <option value="3" <c:if test="${marketingUserInfoDomain.position == 3}">selected="true"</c:if>>组长</option>
        <option value="4" <c:if test="${marketingUserInfoDomain.position == 4}">selected="true"</c:if>>专员</option>
      </select>
    </li>
    <li>
      <label>状态：</label>
      <select name="deleteFlag">
        <option value="" <c:if test="${marketingUserInfoDomain.deleteFlag == null}">selected="true"</c:if>></option>
        <option value="0" <c:if test="${marketingUserInfoDomain.deleteFlag == 0}">selected="true"</c:if>>启用</option>
        <option value="1" <c:if test="${marketingUserInfoDomain.deleteFlag == 1}">selected="true"</c:if>>禁用</option>
      </select>
    </li>
    <li>
      <label>所属组：</label>
      <select name="joinGroup">
        <option value="" <c:if test="${marketingUserInfoDomain.joinGroup == null}">selected="true"</c:if>></option>
        <option value="0" <c:if test="${marketingUserInfoDomain.joinGroup == 0}">selected="true"</c:if>>未分组</option>
        <option value="1" <c:if test="${marketingUserInfoDomain.joinGroup == 1}">selected="true"</c:if>>营销一组</option>
        <option value="2" <c:if test="${marketingUserInfoDomain.joinGroup == 2}">selected="true"</c:if>>营销二组</option>
        <option value="3" <c:if test="${marketingUserInfoDomain.joinGroup == 3}">selected="true"</c:if>>营销三组</option>
      </select>
    </li>
    <li><a href="javascript:realMoreSbmint('1');" class="about">查询</a></li>
    <li><a href="javascript:addUser('');" class="about">新增</a></li>
  </ul>
</div>
<div class="hrble fr" style="width: 859px;">
  <table border="0" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
      <td width="80px">登录名</td>
      <td width="80px">姓名</td>
      <td width="80px">手机号码</td>
      <td width="130px">创建时间</td>
      <td width="60px">状态</td>
      <td width="60px">职务</td>
      <td width="70px">所属组</td>
      <td width="110">操作</td>
    </tr>
    </thead>
    <c:if test="${fn:length(marketingUserInfoDomain.list) > 0}">
    <c:forEach items="${marketingUserInfoDomain.list}" var="pic">
      <tr>
        <td>${pic.code}</td>
        <td>${pic.name}</td>
        <td>${pic.phoneNumber}</td>
        <td>${pic.createTime}</td>
        <td>
          <c:if test="${pic.deleteFlag == 0}">
            启用
          </c:if>
          <c:if test="${pic.deleteFlag == 1}">
            禁用
          </c:if>
        </td>
        <td>
          <c:if test="${pic.position == 0}">
            高管
          </c:if>
          <c:if test="${pic.position == 1}">
            经理
          </c:if>
          <c:if test="${pic.position == 2}">
            主管
          </c:if>
          <c:if test="${pic.position == 3}">
            组长
          </c:if>
          <c:if test="${pic.position == 4}">
            专员
          </c:if>
        </td>
        <td>
          <c:if test="${pic.joinGroup == 0}">
            未分组
          </c:if>
          <c:if test="${pic.joinGroup == 1}">
            营销一组
          </c:if>
          <c:if test="${pic.joinGroup == 2}">
            营销二组
          </c:if>
          <c:if test="${pic.joinGroup == 3}">
            营销三组
          </c:if>
        </td>
        <td>
          <c:if test="${pic.deleteFlag == 0}">
            <a href="javascript:updateDeFl('${pic.id}','1');">禁用</a>
          </c:if>
          <c:if test="${pic.deleteFlag == 1}">
            <a href="javascript:updateDeFl('${pic.id}','0');">启用</a>
          </c:if>
          <a href="javascript:addUser('${pic.id}');">编辑</a>
          <a href="javascript:updatePw('${pic.id}');">重置</a>
        </td>
      </tr>
    </c:forEach>
    </c:if>
  </table>
  <!-- 分页开始 -->
  <div class="feye" id="pageInfoHtmlId">
    <!-- 分页展示 -->
  </div>
  <!-- 分页结束 -->
</div>
    </sf:form>
  </div>
</div>
</body>
</html>