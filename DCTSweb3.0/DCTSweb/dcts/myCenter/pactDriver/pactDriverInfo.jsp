<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-合同司机信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/dataFormat.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<style type="text/css">
  .city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}

  #dataDiv input {
   line-height: 24px;
  }
</style>
<script type="text/javascript">
   $(function(){
   var totalPages = ${pactDriverInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${pactDriverInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${pactDriverInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${pactDriverInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   //搜索信息提交表单
   function getSubmit(flag){
     // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 
      $("#flagId").val(flag);
      
      //运营路线只具体到市,输入框只读,具体到市,具体到区县时默认也只取省市
       //起始地路线
        var startProCityCounty=$("#startProCityCounty").val();
        //目的地路线
        var endProCityCounty=$("#endProCityCounty").val();
      //运营路线拆分及赋值
      //起始地拆分及赋值
      setProvinceCity(startProCityCounty,"startProvince","startCity");
      //目的地拆分及赋值
      setProvinceCity(endProCityCounty,"endProvince","endCity");
       
     document.getElementById('mainForm').submit()
   }
   
   function addPactDriverInfos(){
     if(checkForms()==false){
       return;
     }
     $.ajax({
				url: jcontextPath+"/addPactDriverInfo",   
				type:'post',	
				dataType:'json', 
				data:$("#addPactForm").serialize(),      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						reload();
					}else if(data.result == 1){//未登录
						location.href=jcontextPath+'/dcts/user/login.jsp';
					}else if(data.result == 3){
					artDialogInfo(5,data.errorMessage,'face-smile');//提醒司机安装app
					}else{//出错（例：参数为空）
					artDialogInfo(3,data.errorMessage,'error');
					}
				}
			});
      //execDatabaseInteractionHandle(jcontextPath+"/addPactDriverInfo",$("#addPactForm").serialize(),reload);
   }
   
   
   
   function checkForms(){
    var code=$("#code").val();
      var pactStartTime=$("#pactStartTime").val();
      var pactEndTime=$("#pactEndTime").val();
      if(trim(code)==""){
          $("#codeHtmlId").html("手机号必填！");
		  $("#codeMpt").show();
		  return false;
      }else{
        if(!bilenumber(code)){
        $("#codeHtmlId").html("请填写正确的手机号！");
	    $("#codeMpt").show();
	     return false;
	    }
      }
      if(trim(pactStartTime)==""){
          $("#pactStartTimeHtmlId").html("合同开始时间必填！");
		  $("#pactStartTimeMpt").show();
		  return false;
      }
      //当前时间
		var todatas=new Date(getDateFormat().replace(/\-/g, "\/"));
		var startTimes = new Date(pactStartTime.replace(/\-/g, "\/"));
		if(startTimes<todatas){
			$("#pactStartTimeHtmlId").html("合同开始时间不能小于当前时间！");
		  $("#pactStartTimeMpt").show();
			return false;
		} 
      if(trim(pactEndTime)==""){
       $("#pactEndTimeHtmlId").html("合同结束时间必填！");
		  $("#pactEndTimeMpt").show();
		  return false;
      }
      var endTimes = new Date(pactEndTime.replace(/\-/g, "\/")); 
		if(endTimes<startTimes){
			$("#pactEndTimeHtmlId").html("合同结束时间不能小于开始时间！");
		  $("#pactEndTimeMpt").show();
			return false;
		}
     return true;
   }
   
   //必填项得到焦点时隐藏错误提示层并清空内容
	function getOnfucts(str) {
	    var newStr=str.substring(0, str.length-3)+"HtmlId";
	    $("#"+str).hide();
		$("#"+newStr).html("");
	}
	
	//省市拆分并赋值给hidden
function setProvinceCity(provCityCounty,proviceId,cityId){
	
	var provCityCountys=provCityCounty.split("-");
	if(provCityCountys.length>0){
	$("#"+proviceId).val(provCityCountys[0]);
	if(provCityCountys.length==2){
		$("#"+cityId).val(provCityCountys[1]);
	}
 }
}
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	 <div class="fr sonafr" id="dataDiv">
    	<div class="ntica">
                <div class="mh15"></div>
       <s:form id="addPactForm" action="/addPactDriverInfo" namespace="/" method="post">
        <div class="dataf taine" >
        	<h3 class="mt12"><i>&nbsp;</i>新增合同司机信息</h3>
            <div class="crease">
			<ul>
            	<li><label>手机号<font color="red">*</font></label><input class="int" name="pactDriverInfoDomainObj.code" onfocus="getOnfucts('codeMpt')" value="${pactDriverInfoDomainObj.code }" id="code" type="text" />
            	    <div class="mpt fdc" style="display:none;" id="codeMpt">
	                <div class="wn_a"></div>
	                <div class="wn_s" id="codeHtmlId" ></div>
	                <div class="wn_c"></div>
                </div>
            	</li>
            	<li><label>合同起始时间<font color="red">*</font></label><input class="int" name="pactDriverInfoDomainObj.pactStartTime" onfocus="getOnfucts('pactStartTimeMpt')"  value="${pactDriverInfoDomainObj.pactStartTime }" id="pactStartTime" readonly="readonly" onClick="WdatePicker()" type="text" />
            	       <div class="mpt fdc" style="display:none;" id="pactStartTimeMpt">
	                <div class="wn_a"></div>
	                <div class="wn_s" id="pactStartTimeHtmlId" ></div>
	                <div class="wn_c"></div>
                   </div>
            	</li>
            	<li><label>合同结束时间<font color="red">*</font></label><input class="int" name="pactDriverInfoDomainObj.pactEndTime" onfocus="getOnfucts('pactEndTimeMpt')" value="${pactDriverInfoDomainObj.pactEndTime }"  id="pactEndTime" readonly="readonly" onClick="WdatePicker()" type="text" />
            	      <div class="mpt" style="display:none;" id="pactEndTimeMpt">
	                <div class="wn_a"></div>
	                <div class="wn_s" id="pactEndTimeHtmlId" ></div>
	                <div class="wn_c"></div>
                </div>
            	</li>
                <li class="butse"><a href="javascript:addPactDriverInfos();">添加</a></li>
            </ul>
            </div>
        </div>
        </s:form>
        <div class="cler"></div>
        <s:form id="mainForm" action="/queryPactDriveInfo?menuAId=a_id_10" namespace="/" method="post">
         <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	     <input type="hidden" id="flagId" value="" name="flag"/>
	     
	      <input type="hidden" id="startProvince" name="pactDriverInfoDomain.startProvince" value=""/>
	     <input type="hidden" id="startCity" name="pactDriverInfoDomain.startCity" value=""/>
	     <input type="hidden" id="endProvince" name="pactDriverInfoDomain.endProvince" value=""/>
	     <input type="hidden" id="endCity" name="pactDriverInfoDomain.endCity" value=""/>
        <div class="ctima">
    	<h3><i>&nbsp;</i>合同司机信息列表</h3>
            <div class="crease">
			<ul>
            	<li><label>手机号：</label><input class="int" value="${pactDriverInfoDomain.code }" name="pactDriverInfoDomain.code" type="text" /></li>
                <li><label>车牌号：</label><input class="int" value="${pactDriverInfoDomain.carNumber }" name="pactDriverInfoDomain.carNumber" type="text" /></li>
                 <li><label>司机姓名：</label><input class="int" value="${pactDriverInfoDomain.name }" name="pactDriverInfoDomain.name" type="text" /></li>
            	<li><label>起始时间：</label><input class="int" value="${pactDriverInfoDomain.pactStartTime }" name="pactDriverInfoDomain.pactStartTime" readonly="readonly" onClick="WdatePicker()" type="text" /></li>
            	<li><label>结束时间：</label><input class="int" value="${pactDriverInfoDomain.pactEndTime }"  name="pactDriverInfoDomain.pactEndTime" readonly="readonly" onClick="WdatePicker()" type="text" /></li>
                <li >
	        	<label>运营路线：</label><input name="pactDriverInfoDomain.startProCityCounty" readonly="readonly" style="padding-left: 0px;" value="${pactDriverInfoDomain.startProCityCounty }" id="startProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/><i class="icon1" style="margin: 0 31px;">&nbsp;</i><input name="pactDriverInfoDomain.endProCityCounty" style="padding-left: 0px;" readonly="readonly"   value="${pactDriverInfoDomain.endProCityCounty }" id="endProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
	        	 <li class="butse dify"><a href="javascript:getSubmit('0');">搜索</a></li>
	        	</li>
            </ul>
            </div>
        </div>
        
        <div class="cler"></div>
            <div class="data dataf" >
              <div class="whox">
       <table border="0" cellpadding="0" cellspacing="0">
       <thead>
              <tr>
              	<td width="80">司机姓名</td>
                <td width="80">手机号</td>
                <td width="90">车牌号</td>
                <td width="90">起始时间</td>
                <td width="90">结束时间</td>
               <!-- <td width="120"><select name="" class="slect"><option>交易记录</option></select></td> --> 
               <td width="70">有效天数</td>
                <td width="150">当前位置</td>
                <td>运营路线</td>
                <td width="100">操作</td>
              </tr>
        </thead>
        <s:if test="pactDriverInfoDomain.list.size>0">
          <s:iterator value="pactDriverInfoDomain.list">
              <tr>
              	<td>${name }</td>
                <td><a target="_blank" href="<sys:context/>/openDriverDetailed.action?driverId=${driverId}">${code }</a></td>
                <td>${carNumber }</td>
                <td>${pactStartTime }</td>
                <td>${pactEndTime }</td>
                <td>${pactValidDay }天</td>
                <td title="${lastLocation }"><span class="icon2">&nbsp;</span>${lastLocation }</td>
                <td title="${driverLine }">${driverLine }</td>
                <!-- <td><span class="icon3">&nbsp;</span></td> -->
                <td><a href='javascript:execDatabaseInteractionHandle("<sys:context/>/modifyPactDriverDeleteFlag.action",{"pactDriverInfoDomain.id":${id }},reload)'>删除</a>
                /<a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=">司机轨迹</a>
                </td>
              </tr>
          </s:iterator>
        </s:if>
        </table>
	      </div>
	      <s:if test="pactDriverInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
		 </s:if>
			<input type='hidden' id='curPage' name='pactDriverInfoDomain.pageInfo.curPage' value='${pactDriverInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='pactDriverInfoDomain.pageInfo.curPageNo' value='${pactDriverInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='pactDriverInfoDomain.pageInfo.pageSize' name='pactDriverInfoDomain.pageInfo.pageSize' value='${pactDriverInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
 
        </div>
    </s:form>


        </div>
    </div>
	
    </div>
      <!-- 合作伙伴 -->
       <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />

<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
 <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
 <script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
</body>
</html>
