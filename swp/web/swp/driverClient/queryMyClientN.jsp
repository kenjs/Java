<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%
  String id = request.getParameter("id");
%>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>快到网-营销平台-客户管理-客户详情</title>
  <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
  <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
  <link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

  <script type="text/javascript"  src="<sys:context/>/resource/js/driverClient/queryMyClinent.js"></script>
  <script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
  <script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

  <script type="text/javascript">
    $(function(){
      getDriverInfoN('<%=id %>');
      //获取运营线路
      getDriverLine('<%=id %>','N');
      //获取预约线路
      getDriverBuLineList('<%=id %>','N');
      //获取客户类别
      getCategoryHtml('<%=id %>');
      //获取客户维护记录
      queryMarketingMaintainRecordPage('1');
    });
  </script>
  <style type="text/css">
    .city_input { border:1px solid #d6d6d6; width:180px; height:30px;
      background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat;
      line-height:30px; margin-top:5px;  text-indent:5px;}
  </style>
</head>
<body>
<jsp:include page="/swp/head.jsp" />
<!-- 客户司机id -->
<input type="hidden" id="marketingDriverId" name="marketingDriverId" value="<%=id %>"/>
<input type="hidden" id="menuAIdHi" value="a_id_2" name="menuAId"/>
<div class="mian">
  <div class="mian_bor">
    <%--<jsp:include page="/swp/index/myCenterLeftMenu.jsp" />--%>
    <div class="tabfl" style="width:889px; float: right;">
      <table border="0" cellspacing="0" cellpadding="3" >
        <tr>
          <td id="realLevelHtml" width="207">客户等级：</td>
          <td width="289">客户状态：未安装</td>
          <td width="392" id="mobilePhoneHtml">联系方式：</td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" >
        <tr>
          <td rowspan="2" style="width:40px;">认证信息</td>
          <td width="80">司机姓名</td>
          <td width="70" id="driverName"></td>
          <td width="80">QQ号码</td>
          <td width="70" id="qqNumberHtml"></td>
          <td width="156">身份证号码</td>
          <td width="300"></td>
        </tr>
        <tr>
          <td colspan="3" width="275">行驶证</td>
          <td colspan="3" width="560">驾驶证</td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;" >
        <tr>
          <td rowspan="2" style="width:40px;">活跃情况</td>
          <td width="105">注册时间</td>
          <td width="90"></td>
          <td width="122">使用次数</td>
          <td width="255"></td>
          <td width="105">交易笔数</td>
          <td width="155"></td>
        </tr>
        <tr>
          <td>最近使用时间</td>
          <td></td>
          <td>最近定位地点</td>
          <td></td>
          <td>最近定位时间</td>
          <td></td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;">
        <tr>
          <td rowspan="2" style="width:40px;">车辆信息</td>
          <td width="68">车牌号</td>
          <td width="93" id="carNumberHtml"></td>
          <td width="49">车型</td>
          <td width="281" id="carTypesHtml"></td>
          <td width="49">载重</td>
          <td width="73" id="carWeightHtml"></td>
          <td width="49">体积</td>
          <td width="84" id="carCubageHtml"></td>
          <td width="83"><input name="" type="button" value="编辑" onclick="queryDriverInfo('N');"/></td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;">
        <tr>
          <td rowspan="3" style="width:40px;">营<br />运<br />线<br />路</td>
          <td id="driverLineHtml0" width="349"></td><!-- 运营线路0 -->
          <td width="103">
            <input id="driverLineId0" name="driverLineId0" type="hidden" value="" />
            <input name="" type="button" value="编辑" onclick="queryDriverLineById('0','N');"/>
          </td>
          <td id="oftenCity1Html" width="126"></td>
          <td id="oftenCity2Html" width="126"></td>
          <td id="oftenCity3Html" width="127"></td>
        </tr>
        <tr>
          <td id="driverLineHtml1"></td><!-- 运营线路1 -->
          <td>
            <input id="driverLineId1" name="driverLineId1" type="hidden" value="" />
            <input name="" type="button" value="编辑" onclick="queryDriverLineById('1','N');"/>
          </td>
          <td id="oftenCity4Html"></td>
          <td id="oftenCity5Html"></td>
          <td id="oftenCity6Html"></td>
        </tr>
        <tr>
          <td id="driverLineHtml2"></td><!-- 运营线路2 -->
          <td>
            <input id="driverLineId2" name="driverLineId2" type="hidden" value="" />
            <input name="" type="button" value="编辑" onclick="queryDriverLineById('2','N');"/>
          </td>
          <td colspan="3" width="382">线路不固定，常跑城市线路
            <input name="" type="button" value="编辑" onclick="queryDirverInfoSetOftenCity('N');"/></td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;" id="driverBuLineInfoHtml">
        <!-- 预约线路 -->
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;">
        <tr>
          <td style="width:40px; font-weight:400;">系<br />统<br />货<br />源<br />匹<br />配</td>
          <td style="padding:0px;">
            <div style="width:100%; height:100px;  overflow:auto;">
              <table border="0" cellspacing="1" cellpadding="3" style="margin:-1px;" id="cargoHtml">
                <tr style="font-weight:bold;">
                  <td width="132">装货时间</td>
                  <td width="105">装货地</td>
                  <td width="105">卸货地</td>
                  <td width="133">货物类型</td>
                  <td width="133">发布企业</td>
                  <td width="76">规则</td>
                  <td width="145"><a href="javascript:queryMarketingOrderCargoById('N');">匹配货源>></a></td>
                </tr>
              </table>
            </div>
          </td>
        </tr>
      </table>
      <table class="tabow" border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;">
        <tr>
          <td rowspan="3" style="width:40px; text-align:center;">联<br />系<br />记<br />录</td>
          <td colspan="2" class="number" width="563">
            <dl>
              <dt>手机号码</dt>
              <dd><span id="mobilePhone1Html"></span><span></span></dd>
              <dd><span id="mobilePhone2Html"></span><span><input name="" type="button" value="编辑" onclick="queryMobilePhone('2');"/></span></dd>
              <dd><span id="mobilePhone3Html"></span><span><input name="" type="button" value="编辑" onclick="queryMobilePhone('3');"/></span></dd>
            </dl>
          </td>
          <td width="272"><input name="" type="button" value="无效号码" onclick="queryWuxiaohaoma();"/><span><input name="" type="button" value="发送短信" onclick="setNoteInfo('N');"/></span></td>
        </tr>
        <tr>
          <td width="290">产品、服务：<textarea id="content1" name="content1" cols="" rows=""></textarea></td>
          <td width="272">客户异议：<textarea id="content2" name="content2" cols="" rows=""></textarea></td>
          <td width="272">改进建议：<textarea id="content3" name="content3" cols="" rows=""></textarea></td>
        </tr>
        <tr>
          <td colspan="3" class="absta">
            <ul>
              <li style="width:7%;">客户类别</li>
              <li style="width:17%;" id="categoryIdHtml"></li>
              <li style="width:16%;">
                <select id="hasPurpose" name="hasPurpose">
                  <option value="0">无意向</option>
                  <option value="1">有意向</option>
                </select>
                <!--
                <input type="radio" name="hasPurpose" value="1"  id="hasPurpose_1" />有意向
                <input type="radio" name="hasPurpose" value="0" checked="checked" id="hasPurpose_0" />无意向
                -->
              </li>
              <li style="width:29%;">*下次联系时间&nbsp;<input style="width:43%" id="nextContactDate" name="nextContactDate" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="int m3" /></li>
              <li style="width:25%;">
                <input type="radio" name="isValidCall" value="1" checked="checked" id="RadioGroup1_0" />有效电话
                <input type="radio" name="isValidCall" value="0" id="RadioGroup1_1" />无效电话
              </li>
              <li style="width:4%; border:none;"><input name="" type="button" value="提交" onclick="addMarketingMaintainRecord();"/></li>
            </ul>
          </td>
        </tr>
      </table>
      <table border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;table-layout: fixed;" id="MarketingMaintainRecordIdHtml">

      </table>
      <!-- 分页开始 -->
      <div class="feye" id="pageInfoHtmlId">
        <!-- 分页展示 -->
      </div>
      <!-- 分页结束 -->
    </div>
  </div>
</div>
</body>
</html>
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
