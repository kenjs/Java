<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function querySpyj(wsspxh,spxh){
		var url = jcontextPath+"/common/wsspCommon!querySpyj.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
		//window.open (url,'newwindow','height=300,width=600,top=140,left=160,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
		window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:600px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
	}
</script>
<%try{ %>
	<table width="790" border="0" align="center" cellpadding="2" cellspacing="0" class="poptab_css">
		<tr>
			<th width="30">���</th>
			<th width="30">��־</th>
			<th width="60">������</th>
			<th width="70">����ʱ��</th>
			<th width="120">��������</th>
			<th width="70">������λ</th>
			<th width="30">�ڵ�</th>
			<th width="30">ϵ��</th>
			<th width="60">������</th>
			<th width="70">��������</th>
			<th width="70">�������</th>
			<th width="120">�������</th>
			<th width="30">����</th>
		</tr>
		<s:iterator value="domain.wssplzList" id="ws" status="sta"> 
			<tr>
				<td align="center"><s:property value="#ws.spxh"/></td>
				<td align="center"><s:property value="#ws.fsthbz"/></td>
				<td align="center"><s:property value="#ws.fsrmc"/></td>
				<td align="center"><s:property value="#ws.fsrq"/></td>
				<td align="center"><s:property value="#ws.spjgmc"/></td>
				<td align="center"><s:property value="#ws.spgwmc"/></td>
				<td align="center"><s:property value="#ws.jdxh"/></td>
				<td align="center"><s:property value="#ws.qzxs"/></td>
				<td align="center"><s:property value="#ws.sprmc"/></td>
				<td align="center"><s:property value="#ws.sprq"/></td>
				<td align="center"><s:property value="#ws.spjg"/></td>
				<td align="center">
					<a href="#" onclick="javascipt:querySpyj('<s:property value="#ws.wsspxh"/>','<s:property value="#ws.spxh"/>');" title="<s:property value="#ws.spyj"/>">
						<font color="blue"><s:property value="#ws.spyj"/></font>
					</a>
				</td>
				<td align="center"><s:property value="#ws.cqbz"/></td>
			</tr>
		</s:iterator>
	</table>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
