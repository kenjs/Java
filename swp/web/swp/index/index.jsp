<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/ie_compatibility.jsp"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript" src="<sys:context/>/resource/js/pageInfoTwo.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/index/index.js"></script>


	
	<title>快到网-营销平台-管理中心</title>
	<script type="text/javascript">
	$(function(){
		//移除导航条1的样式
		$("#firstUl li:eq(0)").removeClass("on");
		
		 if(${mark }==0){//今天的导入记录
			 var totalPage = ${orderCargoInfoDomain.pageInfo.totalPages};//总页数
				var curPageNo = ${orderCargoInfoDomain.pageInfo.curPageNo};//当前页数
				var pageSize = ${orderCargoInfoDomain.pageInfo.pageSize};//每页显示数据
				var totalRecord = ${orderCargoInfoDomain.pageInfo.totalRecords};//总记录数
				pageInfo(totalPage,curPageNo,totalRecord);
				
				//给导航栏2添加样式
				$("#twoUl li:eq(0)").addClass("on");
        }
		 else{//历史导入记录
			var totalPages = ${orderCargoInfoDomains.pageInfo.totalPages};//总页数
			var curPageNos = ${orderCargoInfoDomains.pageInfo.curPageNo};//当前页数
			var pageSize = ${orderCargoInfoDomains.pageInfo.pageSize};//每页显示数据
			var totalRecords = ${orderCargoInfoDomains.pageInfo.totalRecords};//总记录数
			pageInfoTwo(totalPages,curPageNos,totalRecords);
			
			//层的展示与隐藏
			$("#firstDivs").hide();
			$("#twoDivs").show();
			//给导航栏2添加样式
			$("#twoUl li:eq(1)").addClass("on");
		   }
		
			//统计某个营销专员今天导入的货源总条数
		    countTodayImportCargos();
		   });
	

	
	//查询历史匹配不成功的有效货源对应的公司信息
	function mainSubmit(flag){
	    // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接
		   document.getElementById("towForm").submit();
	  }
   </script>
</head>
<body>
<jsp:include page="/swp/head.jsp" />
<div class="mian">
	<div class="mian_bor">
		<jsp:include page="/swp/index/myCenterLeftMenu.jsp" />
		
		<div class="fr sonafr" style="width:889px;">
    	<div class="number">
        	<div class="hd">
            	<ul id="firstUl">
                	<li class="on">首页</li>
                   <!--  <li>导入历史记录</li> 
                   <li>今日注册客户</li>
                    <li>今日潜在客户</li>-->
                </ul>
            </div>
            <div class="bd">
            	<div>
                	<div class="break">
                    	<div class="hd" >
                        	<ul id="twoUl">
                            	<li onmousedown="getSubmit('0');">今日导入货源&nbsp;</li>
                            	<li onmousedown="mainSubmit('0')">历史货源今日发货&nbsp;</li>
                            </ul>
                        </div>
                       
                        <div class="bd">
                        	<div id="firstDivs">
                        	 <!-- 导入货源按钮 -->
                        <div class="ncrea" style="margin-left: 30px;"><a href="${contextPath}/openImportCargo.jspx">导入货源</a>
                         <!-- 显示今日总导入货源 -->
            			<b id="countImportId" style="margin-left: 26px;"></b>
           			 </div>
                        	
           <sf:form id="mainForm" action="${contextPath}/queryTodayImportInfo.jspx?mark=0" namespace="/" method="post">
           <div class="data" >
            <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="50">序号</td>
                <td width="120">发货单位</td>
                <td width="70">联系人</td>
                <td width="100">联系电话</td>
                <td width="70">货源数量</td>
                <td width="70">企业现状</td>
                <td width="70">联系状态</td>
                <td width="90">首次联系时间</td>
                <td width="70">操作</td>
              </tr>
            </thead>
            </table>
        <div class="before">
        <c:if test="${fn:length(orderCargoInfoDomain.list)>0 }">
        <c:forEach items="${orderCargoInfoDomain.list}" var="orderCargoInfoDomainObj" varStatus="status">  
         <h3>
            <table border="0" cellpadding="0" cellspacing="0">
             <tr onmousedown="onmousedownTr('${orderCargoInfoDomainObj.contactMobilephone }',0,'cargonInfoId${status.index }');">
                <td width="50">${status.index+1 }</td>
                <td width="120">${orderCargoInfoDomainObj.companyName }</td>
                <td width="70">${orderCargoInfoDomainObj.contactName }</td>
                <td width="100">${orderCargoInfoDomainObj.contactMobilephone }</td>
                <td width="70">${orderCargoInfoDomainObj.importCount }</td>
                <td width="70">
                <c:choose>
                  <c:when test="${orderCargoInfoDomainObj.regCompanyId==''||orderCargoInfoDomainObj.regCompanyId==null }">
                                                        未注册
                  </c:when>
                  <c:otherwise>
                   	已注册
                  </c:otherwise>
                </c:choose>
                </td>
                <td width="70">
                <c:choose>
                    <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '0'}"><%=Constants.COMP_REPLYRESULT_INVALID_VALUE %></c:when>
                    <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '2'}"><%=Constants.COMP_REPLYRESULT_INTENTION_VALUE %></c:when>
                    <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '3'}"><%=Constants.COMP_REPLYRESULT_NOT_INTENTION_VALUE %></c:when>
                    <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '4'}"><%=Constants.COMP_REPLYRESULT_NOT_SPECIFY_VALUE %></c:when>
                    <c:otherwise>
                        未联系
                    </c:otherwise>
                </c:choose>
                </td>
                <td width="90">
                	${orderCargoInfoDomainObj.firstDate }
                </td>
               <td width="70"><a href="javascript:lianxidjcargo('${orderCargoInfoDomainObj.contactMobilephone }','0');">联系登记</a></td>
             </tr>            
          </table></h3>
           <ul>
          <li id="cargonInfoId${status.index }">
         </li>                                    
         </ul>
        </c:forEach>
        </c:if> 
        </div>
          <c:if test="${fn:length(orderCargoInfoDomain.list) > 0}">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</c:if>
			<input type='hidden' id='curPage' name='pageInfo.curPage' value='${orderCargoInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='pageInfo.curPageNo' value='${orderCargoInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='orderCargoInfoDomain.pageInfo.pageSize' name='pageInfo.pageSize' value='${orderCargoInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
         </div>
         </sf:form>
         </div>
         
         <!-- 历史导入的未匹配成功的有效货源 -->
           <div style="display:none;" id="twoDivs">
             <sf:form id="towForm" action="${contextPath}/queryHistoryImportInfo.jspx?mark=1" namespace="/" method="post">
                            <div class="data" >
            <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="50">序号</td>
                <td width="120">发货单位</td>
                <td width="70">联系人</td>
                <td width="100">联系电话</td>
                <td width="70">货源数量</td>
                <td width="70">企业现状</td>
                <td width="70">联系状态</td>
                <td width="90">首次联系时间</td>
                <td width="70">操作</td>
              </tr>
            </thead>
            </table>
            <div class="before" id="historyInfoId">
       		
       		<c:if test="${fn:length(orderCargoInfoDomains.list)>0 }">
        <c:forEach items="${orderCargoInfoDomains.list}" var="orderCargoInfoDomainObjs" varStatus="status">  
         <h3>
            <table border="0" cellpadding="0" cellspacing="0">
             <tr onmousedown="onmousedownTr('${orderCargoInfoDomainObjs.contactMobilephone }',1,'hcargonInfoId${status.index }');">
                <td id="xh" width="50">${status.index+1 }</td>
                <td width="120">${orderCargoInfoDomainObjs.companyName }</td>
                <td width="70">${orderCargoInfoDomainObjs.contactName }</td>
                <td width="100">${orderCargoInfoDomainObjs.contactMobilephone }</td>
                <td width="70">${orderCargoInfoDomainObjs.importCount }</td>
                <td width="70">
                <c:choose>
                  <c:when test="${orderCargoInfoDomainObjs.regCompanyId==''||orderCargoInfoDomainObjs.regCompanyId==null }">
                                                        未注册
                  </c:when>
                  <c:otherwise>
                   	已注册
                  </c:otherwise>
                </c:choose>
                </td>
                
                <td width="70">
                    <c:choose>
                        <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '0'}"><%=Constants.COMP_REPLYRESULT_INVALID_VALUE %></c:when>
                        <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '2'}"><%=Constants.COMP_REPLYRESULT_INTENTION_VALUE %></c:when>
                        <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '3'}"><%=Constants.COMP_REPLYRESULT_NOT_INTENTION_VALUE %></c:when>
                        <c:when test="${orderCargoInfoDomainObj.cargoResultVal == '4'}"><%=Constants.COMP_REPLYRESULT_NOT_SPECIFY_VALUE %></c:when>
                        <c:otherwise>
                            未联系
                        </c:otherwise>
                    </c:choose>
                </td>
                <td width="90">
                	${orderCargoInfoDomainObj.firstDate }
                </td>
                <td width="70"><a href="javascript:lianxidjcargo('${orderCargoInfoDomainObjs.contactMobilephone }','1');">联系登记</a></td>
             </tr>            
          </table></h3>
           <ul id="cargoInfosId">
          <li id="hcargonInfoId${status.index }">
         </li>                                    
         </ul>
        </c:forEach>
        </c:if> 
          </div>
          <!-- 分页 -->
          <!-- <div id="towPageId"> -->
          <c:if test="${fn:length(orderCargoInfoDomains.list)>0 }">
            <div class="numberBox" id="pageInfoHtmlIds"></div>
          </c:if>
            <input type="hidden" id="curPages" name="pageInfo.curPage" value="${orderCargoInfoDomains.pageInfo.curPage }"/>
			<input type="hidden" id="curPageNos" name="pageInfo.curPageNo" value="${orderCargoInfoDomains.pageInfo.curPageNo }"/>
			<input type="hidden" id="orderCargoInfoDomains.pageInfo.pageSize" name="pageInfo.pageSize" value="${orderCargoInfoDomains.pageInfo.pageSize }"/>
           <!--</div>-->
          
         </div>
         </sf:form>
           </div>
         </div>
                    </div>

               </div>
               
       <div>table二</div><!--table二-->
       <div>table三</div><!--table三-->
       <div>table四</div><!--tabl四-->
       
       
       
            </div>
            
        </div>
    
    
      <div class="fr twof"> </div>
    </div>
    </div>
</div>
</body>
</html>

<script src="<sys:context/>/resource/js/jquery.js"></script>
<script src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
jQuery(".number").slide({trigger:"click"});
jQuery(".before").slide({titCell:"h3", targetCell:"ul",defaultIndex:1,effect:"slideDown",delayTime:300,trigger:"click",defaultPlay:true,returnDefault:false});
</script>
