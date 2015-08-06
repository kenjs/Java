<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-����Ǽ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      var ysyfDjxhs;
      $(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});	
			$("#pldjBtn").click(function(){
				var xhs = $(":checked[name='pldj']");
				var hjJe=0;
				var num =0;
				ysyfDjxhs = new Array();
				$.each(xhs,function(i,obj){
					var strs = obj.value.split("#");
					ysyfDjxh = strs[0];
					ysyfDjxhs[i]=ysyfDjxh;
					wsfJe = strs[1]/1;
					hjJe = hjJe+wsfJe;
					num++;
				})
				var zcflDm = $("#mainForm_domain_zcflDm").val();
				var yh = $("#yhTd select").find("option:selected").text();
				var rq = $("#mainForm_domain_rq").val(); 
				var str;
				if(zcflDm=="11"){
					str = "�ֽ��˻�";
				}else if(zcflDm=="12"){
					str = "�����˻� "+yh;
				}else{
					str = "�Ϳ��˻�"
				}
				if(xhs.length<=0){
					showAlert("��ѡ��Ҫ�Ǽǵ�Ӧ�ո���¼��")
					return;
				}else{
					showConfirm("һ��"+num+"��Ӧ�ո���¼���ϼƽ��"+hjJe+"Ԫ�������Ǽǵ�"+str+"���Ǽ�����Ϊ"+rq+"��ȷ�������Ǽ�ô��","doPlDj")
				}
			});		
			//��ʼ�����
			initDataGrid();
			initJsfMc();
			initYh();
	});	
	//�Զ���jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		return data;
	}
	function initYh(){
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		if(zcflDm=="12"){
			$("#yhTd").html($("#yhDiv").html());
		}else{
			$("#yhTd").html($("#noneDiv").html());
		}
		
	}
	function doPlDj(){
		var rq = $("#mainForm_domain_rq").val(); 
		var yhCshDjxh = $("#yhTd select").val();
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		if(zcflDm != "12"){
		   yhhdh = "";
		   var val = $("#mainForm_domain_zcflDm").find("option:selected").text();
		   var strs=val.split(" "); //�ַ��ָ�      
		   yhCshDjxh = strs[2];
		   var showStr;
		   if(zcflDm=="11"){
		   		showStr="�������ʲ���ʼ����ά���ֽ�";
		   }else{
		   		showStr="�������ʲ���ʼ����ά���Ϳ���";
		   }
		   if(yhCshDjxh==null||yhCshDjxh==""){
				showAlert(showStr);
				return;
			}
		}else{
			if(yhCshDjxh==null||yhCshDjxh==""){
				showAlert("�������ʲ���ʼ����ά�������˺ţ�");
				return;
			}
		}
		//alert(yhCshDjxh+rq);
		var jsonStr = getJqueryParamZdy(ysyfDjxhs, "domain.ysyfDjxhs");
		var jsonObj = {"domain.yhCshDjxh":yhCshDjxh,"domain.rq":rq,"domain.zcflDm":zcflDm};
		jsonStr += jQuery.param(jsonObj);
		var url = jcontextPath+"/cwgl/cwysyfsrdj!plDj";  
        //alert(jsonStr);
        ajaxCommon(url,encodeURI(jsonStr),"saveOk", false);
	}
	function saveOk(){
		showSuccess("����ɹ���","onRefresh");
	}
	function onUpdate(id){
		var url = jcontextPath+"/cwysyfsrdj!initMx.action?domain.ysyfDjxh="+id;
		window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:850px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
   }
   function initJsfMc(){
   		 var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
   		 //alert(yfjsfDm);
   		 if(yfjsfDm=="11"||yfjsfDm=="12"||yfjsfDm=="21"||yfjsfDm=="22"||yfjsfDm=="23"||yfjsfDm=="41"){
   		 	$(".mcTd").show();
   		 	$(".noneTd").hide();
   		 }else{
   		 	$(".mcTd").hide();
   		 	$(".noneTd").show();
   		 	$("#mainForm_domain_yfjsfDjmc").val("");
   		 }
   }
   /*function initMc(){
      var sj = $("#mainForm_domain_ssJgbm").val();
      var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
      var url = jcontextPath+"/cwgl/cwysyfsrdj!queryXl";  
	  var jsonObj = {"domain.ssJgbm":sj,"domain.yfjsfDm":yfjsfDm};
	  ajaxCommon(url,jsonObj,"YesInit");
   }
   function YesInit(obj){
      document.getElementById("mainForm_domain_yfjsfDjxh").options.length=0; 
      var list = obj.domain.yfjsfMcList;
     
      for(var i=0;i<list.length;i++){
        document.getElementById("mainForm_domain_yfjsfDjxh").options.add(new Option(list[i].mcStr,list[i].yfjsfDjxh));
      }
      $("#mainForm_domain_yfjsfDjxh").val();
   }*/
   
    function onRegister(ysyfDjxh){
    	var url = jcontextPath+"/cwgl/cwysyfsrdj!initMx.action?domain.ysyfDjxh="+ysyfDjxh + "&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:680px;dialogWidth:850px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		//window.open(url)
		onRefresh();
    }
    var jsfDjxh="";
    function onDelete(ysyfDjxh,yfjsfDjxh){
    	jsfDjxh=yfjsfDjxh;
    	keyValue = ysyfDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
    }
    
    function yesCallBack(){
    	var url = jcontextPath+"/cwgl/cwysyfsrdj!cancle";
    	var jsonObj = {"domain.ysyfDjxh":keyValue,"domain.yfjsfDjxh":jsfDjxh};
    	ajaxCommon(url,jsonObj,"doSuccess");
    }
	
	function doSuccess(){     
        showAlert("�����ɹ���");
        onRefresh();
	}
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var sj = $("#mainForm_domain_ssJgbm").val();
		var djJgbm = $("#mainForm_domain_djJgbm").val();
		var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
        var yfjsfDjxh = "";
        var yfjsfDjmc = $("#mainForm_domain_yfjsfDjmc").val();
        var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
		var zt = $("input[name='domain.zt']:checked").val();
		var ddbh = $("#mainForm_domain_ddbh").val(); 
		var ysyflyDm = $("#mainForm_domain_ysyflyDm").val();
		var kmxlDm = $("#mainForm_domain_kmxlDm").val();
  		//����������
		var url = jcontextPath+"/cwysyfsrdj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":sj,"domain.djJgbm":djJgbm,"domain.yfjsfDm":yfjsfDm,"domain.yfjsfDjxh":yfjsfDjxh,"domain.yfjsfDjmc":encodeURI(yfjsfDjmc),
		 			  "domain.rqQ":rqQ,"domain.rqZ":rqZ, "domain.zt":zt,"domain.ddbh":encodeURI(ddbh),
		 			  "domain.ysyflyDm":ysyflyDm,"domain.kmxlDm":kmxlDm}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "20";
		String gsbm = userDomain.getGsbm();
		if (userDomain != null && userDomain.getRowNum() != null && !"".equals(userDomain.getRowNum().trim()))
			rowNum = userDomain.getRowNum();
	%>
	
	//jqGrid  ��ʼ�����	
	function initDataGrid(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-15,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'pldj\');" />','����','Ӧ��Ӧ��״̬����','״̬','Ӧ��Ӧ���Ǽ����','ҵ��Ǽ����','�������','Ԥ�����㷽DJXH','���㷽','����','���','����',
		              'δ��','���','��Ŀ','��Դ����','��Դ','��������',
		              '˵��','�Ǽǲ���','������λ','���ζ���','���ι�˾'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
              {name:'pageXh', index:'pageXh', sortable:false,width:'35', align:'center'},
			  {name:'pldjCheck',index:'pldjCheck',width:'30',align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false,width:'65', align:'center'},
			  {name:'ysyfztDm', index:'ysyfztDm',hidden:true},
			  {name:'ysyfztMc', index:'ysyfztMc', width:'35', align:'center'},
			  {name:'ysyfDjxh', index:'ysyfDjxh',hidden:true, align:'center'}, 
			  {name:'ywDjxh', index:'ywDjxh', width:'100',hidden:true, align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70',align:'center'},
			  {name:'yfjsfDjxh', index:'yfjsfDjxh',hidden:true, align:'center'},
		      {name:'yfjsfMc', index:'yfjsfMc', width:'50', align:'center'},
		      {name:'yfjsfDjmc', index:'yfjsfDjmc', width:'150', align:'center'}, 
		      {name:'ysfJe', index:'ysfJe', width:'50', align:'center'},
			  {name:'yisfJe', index:'yisfJe', width:'50', align:'center'},
			  {name:'wsfJe', index:'wsfJe', width:'50', align:'center'},			  		     
		      {name:'kmdlMc', index:'kmdlMc', width:'70', align:'center'}, 	      
		      {name:'kmxlMc', index:'kmxlMc', width:'70', align:'center'},
		      {name:'ysyflyDm', index:'ysyflyDm', width:'70', align:'center',hidden:true},			  
			  {name:'ysyflyMc', index:'ysyflyMc', width:'70', align:'center'},			  
			  {name:'csrq', index:'csrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},	
			  {name:'sm', index:'sm', width:'400', align:'left'},            
			  {name:'djJgmc', index:'djJgmc', width:'60', align:'center'},
			  {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'},
			  {name:'syDdDjxh', index:'syDdDjxh', width:'80', align:'center',hidden:true},
			  {name:'syGsbm', index:'syGsbm', width:'80', align:'center',hidden:true}
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'cwbdDjXh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'asc',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	//userdata: "userdata",						    // ��ĳЩ����£�������Ҫ�ӷ������˷���һЩ������������ֱ�Ӱ�������ʾ������У��������ڱ�ĵط���ʾ
	        	repeatitems : false     						// ���ó�false���ں�̨����ֵ��ʱ�򣬿��������Ҳ���ÿ��ֵ������
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '������Ϣ'							//�������,
		  }); 
		  
		  //����pageѡ��Ϊ1
		  jQuery("#dataList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/cwysyfsrdj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"ysyfDjxh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������  
		                var ysyfztDm = jQuery("#dataList").jqGrid('getCell', cl,"ysyfztDm"); 
		                var yfjsfDjxh = jQuery("#dataList").jqGrid('getCell', cl,"yfjsfDjxh"); 
		                var ysyflyMc = jQuery("#dataList").jqGrid('getCell', cl,"ysyflyMc"); 
		                var wsfJe = jQuery("#dataList").jqGrid('getCell', cl,"wsfJe");
		                var syGsbm = jQuery("#dataList").jqGrid('getCell', cl,"syGsbm");
		                var syDdDjxh = jQuery("#dataList").jqGrid('getCell', cl,"syDdDjxh");
		                var ysyflyDm = jQuery("#dataList").jqGrid('getCell', cl,"ysyflyDm");
		                if(ysyfztDm == '21'){
			                var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
			                var linkPlDj = '<input type="checkbox" name="pldj" value="'+val+"#"+wsfJe+'" />';
		                }
		                else if(ysyfztDm == '22'){
			                var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">�޸�</font></a> " + 
			                " <a href=\"javascript:onDelete('"+val+"','"+yfjsfDjxh+"')\"><font color=\"blue\">����</font></a>";
			                var linkPlDj = '<input type="checkbox" name="pldj"  disabled="disabled" value="'+val+"#"+wsfJe+'" />';
		                }
		                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
		                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ywDjxh");
		                if(ysyflyDm=="1"){
		                	 str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
		                }else if(ysyflyDm=="15"){
		                	 if(<%=gsbm%>==syGsbm){
		                	 	str="<a href=\"javascript:onViewMx("+syDdDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
		                	 }else{
		                	 	str=ddbh;
		                	 }
		                }else{
		                	 str=ddbh;
		                }
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
		                $("#dataList").jqGrid('setRowData', cl, { 'pldjCheck': linkPlDj});
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="cwysyfsrdj!query" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
	    <ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="pldjBtn" class="licon01">�����Ǽ�</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 

	<div class="right_cont">
		<div id="divQuery" style="overflow:auto">
			<fieldset>
				<legend>��ѯ����</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8%" align="right">ҵ���ţ�</td> 
						<td width="21%">  
						   <sys:fgsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select" contaisQxz="true"></sys:fgsList>						   
		  			    </td>
						<td width="8%" align="right">���㷽��</td>
						<td width="21%" >
						   <s:select list="domain.yfjsfDmList" name="domain.yfjsfDm" listKey="yfjsfDm" listValue="lbStr"
						             cssClass="select" onchange="initJsfMc()"></s:select>
						</td>
						<td width="8%" align="right" class="mcTd">���ƣ�</td>
						<td width="21%" class="mcTd">
							<s:textfield name="domain.yfjsfDjmc" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
						</td>
						<td width="29%"colspan="2" class="noneTd"></td>
						
						<td style="display: none;">
						   <select name="domain.yfjsfDjxh" id="mainForm_domain_yfjsfDjxh" class="select" >
						      <option value="">-- ��ѡ�� --</option>
						   </select>
						</td>		
					</tr>
					 <tr> 
		  			    <td align="right">���ڣ�</td>
		                <td>
		                  <sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
	          			      ��
	          		      <sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
		                </td>
				        <td align="right">������ţ�</td>
						<td >
							<s:textfield name="domain.ddbh" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
						</td>
				        <td align="right">״̬��</td>
					    <td>
					        <s:radio name="domain.zt" list='#{"":"����","1":"����","2":"δ�գ��꣩"}' listKey="key" listValue="value"></s:radio>
					    </td>
		        		
					</tr>
					<tr> 
		  			    <td align="right">��Դ��</td>
		                <td><sys:DmYsyfly myId="mainForm_domain_ysyflyDm" myName="domain.ysyflyDm" myClass="select" contaisQxz="true"></sys:DmYsyfly></td>
				        <td align="right">��Ŀ��</td>
						<td ><sys:DmKmxl myId="mainForm_domain_kmxlDm" myName="domain.kmxlDm" myClass="select" srBz="Y" contaisQxz="true"></sys:DmKmxl></td>
				        <td colspan="2"></td>
					</tr>	             
				</table>
			</fieldset>
			<fieldset>
				<legend>�����Ǽ�</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8%" align="right">�ʲ����ࣺ</td>
						<td width="21%" >
						  <sys:ZcflCsh myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" onChange="initYh()" ssJgbm="domain.ssJgbm"></sys:ZcflCsh>
						</td>
						<td width="8%" align="right">���У�</td>
						<td width="21%" id="yhTd"></td>
						<td width="8%" align="right">�Ǽ����ڣ�</td> 
						<td width="21%"> 
							<sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" />
		  			    </td>
					</tr>	   
				</table>
			</fieldset>
		</div>
		<div style="display: none;" id="yhDiv"><sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:yhzh></div>
		<div style="display: none;" id="noneDiv"><select id="noneSelect" class="select"><option value="" selected="selected"></option></select></div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
</html>