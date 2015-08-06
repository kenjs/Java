 
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
 
	<s:hidden name="domain.gnmkDm" />
	<s:hidden name="domain.xtmlXh" />
	<div class="tips" id="divTips"
		style="display: <s:if test='domain.czts_Csmrz=="N"'>none</s:if><s:if test='domain.czts_Csmrz=="Y"'>block</s:if>">
	<div class="tips_title"><span><img
		src="<sys:context/>/resource/pageframe/images/icon_help.gif"
		align="absmiddle" /> ²Ù×÷ÌáÊ¾£º</span></div>
	<div class="tips_cont">¡¡¡¡<s:property value="domain.cztsxx" escape="false" /></div>
	</div>
	 
 
