$(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			
			initHykhData(300,$("#mainForm_domain_dwDm").val(), $("#mainForm_domain_bmDm").val(),"jsonData","khMc","khDjxh");
			
			$("input[name='domain.dzztDm']").change(function(){
				changeRq(this.value);
			});
			$("#mainForm_domain_bmDm").change(function(){
				initHykhData(300, $("#mainForm_domain_dwDm").val(), $(this).val(),"jsonData","khMc","khDjxh");
			});
			
			$("#mainForm_domain_dzfsDm").change(function(){
				changeTable($(this).val());
			});
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			//��������
			$("#plScSendBtn").click(function(){
				var wsDm="305001";//������ʲ�����˱�
				plScSend(wsDm,"");
			});
			//��������
			$("#plDzBtn").click(function(){
				//alert(0);
				var checkedVal = new Array();
				var xhs = $(":checked[name='dzxhs']");
				if (xhs.length <= 0) {
					showAlert("��ѡ����Ҫ�������˵ļ�¼��");
					return;
				}
				var dzCheckBoxs = $(":checkbox[name='dzxhs']:checked:visible:enabled");
				$.each(dzCheckBoxs,function(i){
					checkedVal[i] = $(dzCheckBoxs[i]).val();
				});
				var url = jcontextPath+"/hygl/jsglsrdz!plDz";	
				var jsonObj = {"domain.pldzStr":checkedVal};
				ajaxCommon(url,jsonObj,"plDzSuccess");
			});
			
			//��ʼ�����(������)
			initDataGrid();
			initList();	
			var dzfsDm = trim($("#mainForm_domain_dzfsDm").val());
			if(undefined==dzfsDm || null==dzfsDm || ""==dzfsDm){
				dzfsDm="1";//Ĭ����ʾ������
			}
			//changeTable(dzfsDm);	
	});
	function plDzSuccess(){
		showAlert("�������˳ɹ���");
        onRefresh();
	}

	function changeRq(dzzt){
		if("1"==dzzt){
			$("#rqTd").text("�µ����ڣ�"); 
			$("#mainForm_domain_rqQ").val(""); 
		}else{
			$("#rqTd").text("�������ڣ�"); 
			var defultRqQ=$("#mainForm_domain_defultRqQ").val(); 
			$("#mainForm_domain_rqQ").val(defultRqQ); 
		}
	}
	
	function changeTable(dzfsDm){
	
		//��ʾ��������table
		if("1"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGrid();
		}
		//��ʾ�����������table
		if("2"==dzfsDm){
			$("#dataList").GridUnload();
		    initDataGridByDdHw();
		}
		//��ʾ���ɳ���table
		if("3"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByPc();
		}
		//��ʾ���ɳ������table
		if("4"==dzfsDm){
		    $("#dataList").GridUnload();
			initDataGridByPcHw();
		}
		//��ʾ���ص���table
		if("5"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByHd();
		}
	}

	function initList() {
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var bmDm =$("#mainForm_domain_bmDm").val();
		var jsonObj = {"domain.paramdm":dwDm,
			"domain.defaultValue":bmDm,
			"domain.currentObjName":"domain.bmDm",
			"domain.currentObjId":"mainForm_domain_bmDm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = $("<option>").text(domain.mc).val(domain.dm);
		    //Ĭ��ѡ��
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc).val(domain.dm);
		    }
		    
		    $("#"+data.domain.currentObjId).append(option);
		});
	}

    function onUpdate(jsDjxh,dzDjxh,dzfsDm){
    	var url = jcontextPath+"/hygl/jsglsrdz!initMx.action?domain.jsSrdzDomain.jsDjxh="+jsDjxh+"&domain.jsSrdzDomain.dzDjxh="+dzDjxh+"&domain.jsSrdzDomain.dzfsDm="+dzfsDm+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:520px;dialogWidth:810px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    	//popwindow(jcontextPath+"/hygl/jsglsrdz!initMx?domain.jsSrdzDomain.jsDjxh="+jsDjxh);
    }
    
    var keyValue = "";
	function onDelete( dzDjxh){
		keyValue = dzDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.ddDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jsglsrdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var dwDm = trim($("#mainForm_domain_dwDm").val()); 
		var bmDm = trim($("#mainForm_domain_bmDm").val());
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var khMc = $("#mainForm_domain_khMc").val();
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var ddbh = trim($("#mainForm_domain_ddbh").val()); 
		var dzfsDm = trim($("#mainForm_domain_dzfsDm").val()); 
		var dzztDm =$("input[name='domain.dzztDm']:checked").val();
		var yjZtDm = $("input[name='domain.yjZtDm']:checked").val();
		
		if(undefined==dwDm || null==dwDm || ""==dwDm){
			showAlert("����ѡ��ҵ��λ��");
			return;
		}
		/*if(undefined==bmDm || null==bmDm || ""==bmDm){
			showAlert("����ѡ��Ǽǲ��ţ�");
			return;
		}*/
		if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
			//showAlert("����ѡ��ͻ����ƣ�");
			//return;
			khDjxh="";
		}
		if(undefined==dzfsDm || null==dzfsDm || ""==dzfsDm){
			showAlert("����ѡ����˷�ʽ��");
			return;
		}
		
		//��ʾ��������table
		if("1"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGrid();
		}
		//��ʾ�����������table
		if("2"==dzfsDm){
			$("#dataList").GridUnload();
		    initDataGridByDdHw();
		}
		//��ʾ���ɳ���table
		if("3"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByPc();
		}
		//��ʾ���ɳ������table
		if("4"==dzfsDm){
		    $("#dataList").GridUnload();
			initDataGridByPcHw();
		}
		//��ʾ���ص���table
		if("5"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByHd();
		}
		
		if("1"==dzztDm){
			if(undefined==rqZ || null==rqZ || ""==rqZ){
				showAlert("����ѡ���µ�����ֹ��");
				return;
			}
			
			$("#dataList").jqGrid('hideCol',["dzcybz","dzcyje","dzje"]);
		}
		if("2"==dzztDm){
			if(undefined==rqQ || null==rqQ || ""==rqQ){
				showAlert("����ѡ�����������");
				return;
			}
			if(undefined==rqZ || null==rqZ || ""==rqZ){
				showAlert("����ѡ���������ֹ��");
				return;
			}
			//
			$("#dataList").jqGrid('showCol',["dzcybz","dzcyje","dzje"]);
		}
		
		//����������
		var url = jcontextPath+"/hygl/jsglsrdz!query";
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.bmDm":encodeURI(bmDm),"domain.dzfsDm":encodeURI(dzfsDm),
		 	"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
		 	"domain.ddbh":encodeURI(ddbh),"domain.dzztDm":encodeURI(dzztDm),"domain.khMc":encodeURI(khMc),
		 	"domain.yjZtDm":encodeURI(yjZtDm)}		
		 	//����Ĳ�����json��
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
		 
	}
	//jqGrid  ��ʼ����񣨰�������
	function initDataGrid(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','����',
		    		  '�����������','��������״̬dm','���',
		    		  '����Ǽ����','���˵Ǽ����','״̬dm','״̬','�ͻ�����','���˽��','������','���','������','ddDjxh','�������','�µ�����', 
		    		  '��������', '��������', '�ص����','ʼ����', 'Ŀ�ĵ�', '����', '����', '���','��װ',
		    		  '������','��������','���˲���','ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', sortable:false, width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'checkboxoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'checkboxoperationcol2', index:'checkboxoperationcol2', sortable:false, width:'80', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'checkboxoperationcol2' + rowId + '\'';
				    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'wsSpztDm', index:'wsSpztDm', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpztDm' + rowId + '\'';
			    }
			  },
			  {name:'wsspxh', index:'wsspxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspxh' + rowId + '\'';
			    }
			  },
			  {name:'wsSpztMc', index:'wsSpztMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpztMc' + rowId + '\'';
			    }
			  },
			  {name:'jsDjxh', index:'jsDjxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsDjxh' + rowId + '\'';
			    }
			  },
			  {name:'dzDjxh', index:'dzDjxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzDjxh' + rowId + '\'';
			    }
			  },
			  {name:'dzztDm', index:'dzztDm', hidden:true,width:'10', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzztDm' + rowId + '\'';
			    }
			  },
			  {name:'dzztMc', index:'dzztMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzztMc' + rowId + '\'';
			    }
			  },
			  {name:'khMc', index:'khMc', width:'150', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'khMc' + rowId + '\'';
			    }
			  },
			  {name:'dzsr', index:'dzsr', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzsr' + rowId + '\'';
			    }
			  },
			  {name:'jssr', index:'jssr', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jssr' + rowId + '\'';
			    }
			  },
			  {name:'dzcybz', index:'dzcybz', width:'35', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzcybz' + rowId + '\'';
			    }
			  },
			  {name:'dzcyje', index:'dzcyje', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzcyje' + rowId + '\'';
			    }
			  },
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'70', align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ddbh' + rowId + '\'';
			    }
			  },
			  {name:'xdrq', index:'xdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'xdrq' + rowId + '\'';
			    }
			  },
			 
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'jssl', index:'jssl', width:'60', align:'right'}, 
		      {name:'hdbh', index:'hdbh', width:'80', align:'right'}, 
		      {name:'sfd', index:'sfd', width:'50', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', align:'center'}, 
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'bz', index:'bz', width:'50', align:'center'}, 
		      
		      {name:'dzrMc', index:'dzrMc', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzrMc' + rowId + '\'';
			    }
			  },
			  
			  {name:'dzrq', index:'dzrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzrq' + rowId + '\'';
			    }
			  },
			  {name:'bmmc', index:'bmmc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'bmmc' + rowId + '\'';
			    }
			  },
			  {name:'dwmc', index:'dwmc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dwmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DDBH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		   // multiselect: true, //�Ƿ���ʾ��ѡ��  
		    //multiboxonly: false,  //�Ƿ�ֻ�е����ѡ��ʱ,��ִ��ѡ���ѡ��checkbox.Ĭ��Ϊfalse,���һ����ѡ�����еĶ�ѡ��
		    //multiselectWidth: 50, //��ѡ�������еĿ��   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		       	  $("#mainForm").attr("action",jcontextPath+"/hygl/jsglsrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dzfsDm = trim($("#mainForm_domain_dzfsDm").val());
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jsDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var dzztDm =jQuery("#dataList").jqGrid('getCell', cl,"dzztDm");
                var dzDjxh =jQuery("#dataList").jqGrid('getCell', cl,"dzDjxh"); 
                var wsspxh = jQuery("#dataList").jqGrid('getCell', cl,"wsspxh");
                var wsSpztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsSpztDm");
                var hdshFlag = jQuery("#dataList").jqGrid('getCell', cl,"hdshFlag");
                
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var input ="";
                var link="";
                var dzInput = "";
                if("1"==dzztDm){
                	input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"#"+wsspxh+"\" />";
                	dzInput = "<input type=\"checkbox\" name=\"dzxhs\"  value=\""+val+"#"+dzDjxh+"#"+dzfsDm+"\" />";
                	//alert(dzInput);
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+dzDjxh+"','"+dzfsDm+"')\"><font color=\"blue\">����</font></a>";
                }
                if("2"==dzztDm){
                	if("0"==wsSpztDm || "2"==wsSpztDm){//δ���ͺ��˻�
                		input="<input type=\"checkbox\" name=\"xhs\" value=\""+val+"#"+wsspxh+"\" />";
                	}else{
                		input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"#"+wsspxh+"\" />";
                	}
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+dzDjxh+"','"+dzfsDm+"')\"><font color=\"blue\">�޸�</font></a>";
                	dzInput = "<input type=\"checkbox\" name=\"dzxhs\" disabled=\"disabled\" value=\""+val+"#"+dzDjxh+"#"+dzfsDm+"\" />";
                }
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol2': dzInput });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                
                var hdshStr = '';
                if (hdshFlag == '0') {
                	hdshStr = '<font color="red">δ�ջ�</font>';
                }else if (hdshFlag == '1') {
                	hdshStr = '���ջ�';
                }
                $("#dataList").jqGrid('setRowData', cl, {'hdshFlag': hdshStr });
           }
       var gridName = "dataList";
       //������
       if("1"==dzfsDm){
       		var a = ['pageXh','checkboxoperationcol','checkboxoperationcol2','hstoperationcol','wsSpztDm','wsspxh','wsSpztMc','jsDjxh','dzDjxh','dzztDm','dzztMc','khMc',
	   			'dzsr','jssr','dzcybz','dzcyje','ddbh','xdrq','dzrMc','dzrq','bmmc','dwmc'
	            ];
 		
       		Merger(gridName, 'pageXh', a);
       }
       
	   
   }
   
   //jqGrid  ��ʼ�����(����������)
	function initDataGridByDdHw(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','����',
		    		  '�����������','��������״̬dm','���',
		    		  '����Ǽ����','���˵Ǽ����','״̬dm','״̬','�ͻ�����','�½�','���˽��','���','������','ddDjxh','�������','�µ�����', 
		    		  '��������', '�ص����','ʼ����', 'Ŀ�ĵ�', '����', '����', '���','��װ',
		    		  '������','�绰','�ջ���','�绰','�ջ���ַ','�ص��ջ�',
		    		  '������','��������','���˲���','ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', sortable:false, width:'80', align:'center'},
			  {name:'checkboxoperationcol2', index:'checkboxoperationcol2', sortable:false, width:'80', align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false, width:'35', align:'center'},
			  {name:'wsSpztDm', index:'wsSpztDm', hidden:true,width:'20', align:'center'},
			  {name:'wsspxh', index:'wsspxh', hidden:true,width:'20', align:'center'},
			  {name:'wsSpztMc', index:'wsSpztMc', width:'50', align:'center'},
			  {name:'jsDjxh', index:'jsDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzDjxh', index:'dzDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzztDm', index:'dzztDm', hidden:true,width:'10', align:'center'},
			  {name:'dzztMc', index:'dzztMc', width:'50', align:'center'},
			  {name:'khMc', index:'khMc', width:'150', align:'center'},
			  {name:'dzwj', index:'dzwj', width:'70', align:'center'},
			  {name:'dzje', index:'dzje', width:'65', align:'center', hidden:true},
			  {name:'dzcybz', index:'dzcybz', width:'35', align:'center', hidden:true},
			  {name:'dzcyje', index:'dzcyje', width:'60', align:'center', hidden:true},
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'70', align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			  {name:'xdrq', index:'xdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'hdbh', index:'hdbh', width:'70', align:'right'}, 
		      {name:'sfd', index:'sfd', width:'50', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', align:'center'}, 
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'bz', index:'bz', width:'50', align:'center'}, 
		      {name:'fhrLxr', index:'fhrLxr', width:'50', align:'center'}, 
		      {name:'fhrLxdh', index:'fhrLxdh', width:'70', align:'center'}, 
		      {name:'shrLxr', index:'shrLxr', width:'50', align:'center'}, 
		      {name:'shrLxdh', index:'shrLxdh', width:'70', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'80', align:'left'}, 
		      {name:'hdshFlag', index:'hdshFlag', width:'50', align:'center'}, 
		      {name:'dzrMc', index:'dzrMc', width:'50', align:'center'},
			  {name:'dzrq', index:'dzrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'bmmc', index:'bmmc', width:'100', align:'center'},
			  {name:'dwmc', index:'dwmc', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 50,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,200],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DDBH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		   // multiselect: true, //�Ƿ���ʾ��ѡ��  
		    //multiboxonly: false,  //�Ƿ�ֻ�е����ѡ��ʱ,��ִ��ѡ���ѡ��checkbox.Ĭ��Ϊfalse,���һ����ѡ�����еĶ�ѡ��
		    //multiselectWidth: 50, //��ѡ�������еĿ��   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		       	  $("#mainForm").attr("action",jcontextPath+"/hygl/jsglsrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	//jqGrid  ��ʼ�����(���ɳ�)
	function initDataGridByPc(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','����',
		    		  '�����������','��������״̬dm','���',
		    		  '����Ǽ����','���˵Ǽ����','״̬dm','״̬','�ͻ�����','���˽��','������','���','������','�ɳ�����','�ɳ�����','��������','˾��',
		    		  'ddDjxh','�������','�µ�����', '��������', '��������', '�ص����','ʼ����', 'Ŀ�ĵ�', '����', '����', '���','��װ',
		    		  '������','��������','���˲���','ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', sortable:false, width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'checkboxoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'checkboxoperationcol2', index:'checkboxoperationcol2', sortable:false, width:'80', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'checkboxoperationcol2' + rowId + '\'';
				    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'wsSpztDm', index:'wsSpztDm', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpztDm' + rowId + '\'';
			    }
			  },
			  {name:'wsspxh', index:'wsspxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspxh' + rowId + '\'';
			    }
			  },
			  {name:'wsSpztMc', index:'wsSpztMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpztMc' + rowId + '\'';
			    }
			  },
			  {name:'jsDjxh', index:'jsDjxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsDjxh' + rowId + '\'';
			    }
			  },
			  {name:'dzDjxh', index:'dzDjxh', hidden:true,width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzDjxh' + rowId + '\'';
			    }
			  },
			  {name:'dzztDm', index:'dzztDm', hidden:true,width:'10', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzztDm' + rowId + '\'';
			    }
			  },
			  {name:'dzztMc', index:'dzztMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzztMc' + rowId + '\'';
			    }
			  },
			  {name:'khMc', index:'khMc', width:'150', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'khMc' + rowId + '\'';
			    }
			  },
			  {name:'dzsr', index:'dzsr', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzsr' + rowId + '\'';
			    }
			  },
			  {name:'jssr', index:'jssr', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jssr' + rowId + '\'';
			    }
			  },
			  {name:'dzcybz', index:'dzcybz', width:'35', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzcybz' + rowId + '\'';
			    }
			  },
			  {name:'dzcyje', index:'dzcyje', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzcyje' + rowId + '\'';
			    }
			  },
			  {name:'pcdh', index:'pcdh', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
			  },
			  {name:'clhm', index:'clhm', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'clhm' + rowId + '\'';
			    }
			  },
			  {name:'sjxm', index:'sjxm', width:'60', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'sjxm' + rowId + '\'';
			    }
			  },
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'70', align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			  {name:'xdrq', index:'xdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'jssl', index:'jssl', width:'60', align:'right'}, 
		      {name:'hdbh', index:'hdbh', width:'80', align:'right'}, 
		      {name:'sfd', index:'sfd', width:'50', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', align:'center'}, 
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'bz', index:'bz', width:'50', align:'center'}, 
		      {name:'dzrMc', index:'dzrMc', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzrMc' + rowId + '\'';
			    }
			  },
			  
			  {name:'dzrq', index:'dzrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dzrq' + rowId + '\'';
			    }
			  },
			  {name:'bmmc', index:'bmmc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'bmmc' + rowId + '\'';
			    }
			  },
			  {name:'dwmc', index:'dwmc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'dwmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DDBH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		   // multiselect: true, //�Ƿ���ʾ��ѡ��  
		    //multiboxonly: false,  //�Ƿ�ֻ�е����ѡ��ʱ,��ִ��ѡ���ѡ��checkbox.Ĭ��Ϊfalse,���һ����ѡ�����еĶ�ѡ��
		    //multiselectWidth: 50, //��ѡ�������еĿ��   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		       	  $("#mainForm").attr("action",jcontextPath+"/hygl/jsglsrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	//jqGrid  ��ʼ�����(���ɳ�����)
	function initDataGridByPcHw(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','����',
		    		  '�����������','��������״̬dm','���',
		    		  '����Ǽ����','���˵Ǽ����','״̬dm','״̬','�ͻ�����','���˽��','������','���','������','�ɳ�����','�ɳ�����','��������','˾��',
		    		  'ddDjxh','�������','�µ�����', 
		    		  '��������', '��������', '�ص����','ʼ����', 'Ŀ�ĵ�', '����', '����', '���','��װ',
		    		  '������','��������','���˲���','ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', sortable:false, width:'80', align:'center'},
			  {name:'checkboxoperationcol2', index:'checkboxoperationcol2', sortable:false, width:'80', align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false, width:'35', align:'center'},
			  {name:'wsSpztDm', index:'wsSpztDm', hidden:true,width:'20', align:'center'},
			  {name:'wsspxh', index:'wsspxh', hidden:true,width:'20', align:'center'},
			  {name:'wsSpztMc', index:'wsSpztMc', width:'50', align:'center'},
			  {name:'jsDjxh', index:'jsDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzDjxh', index:'dzDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzztDm', index:'dzztDm', hidden:true,width:'10', align:'center'},
			  {name:'dzztMc', index:'dzztMc', width:'50', align:'center'},
			  {name:'khMc', index:'khMc', width:'150', align:'center'},
			  {name:'dzsr', index:'dzsr', width:'65', align:'center'},
			  {name:'jssr', index:'jssr', width:'70', align:'center'},
			  {name:'dzcybz', index:'dzcybz', width:'35', align:'center', hidden:true},
			  {name:'dzcyje', index:'dzcyje', width:'60', align:'center', hidden:true},
			  {name:'pcdh', index:'pcdh', width:'70', align:'center'},
		      {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'clhm', index:'clhm', width:'70', align:'center'},
		      {name:'sjxm', index:'sjxm', width:'70', align:'center'},
		      {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'70', align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			  {name:'xdrq', index:'xdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'jssl', index:'jssl', width:'60', align:'right'}, 
		      {name:'hdbh', index:'hdbh', width:'80', align:'right'}, 
		      {name:'sfd', index:'sfd', width:'50', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', align:'center'}, 
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'bz', index:'bz', width:'50', align:'center'}, 
		      {name:'dzrMc', index:'dzrMc', width:'70', align:'center'},
			  {name:'dzrq', index:'dzrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'bmmc', index:'bmmc', width:'100', align:'center'},
			  {name:'dwmc', index:'dwmc', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DDBH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		   // multiselect: true, //�Ƿ���ʾ��ѡ��  
		    //multiboxonly: false,  //�Ƿ�ֻ�е����ѡ��ʱ,��ִ��ѡ���ѡ��checkbox.Ĭ��Ϊfalse,���һ����ѡ�����еĶ�ѡ��
		    //multiselectWidth: 50, //��ѡ�������еĿ��   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		       	  $("#mainForm").attr("action",jcontextPath+"/hygl/jsglsrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	//jqGrid  ��ʼ�����(���ص�)
	function initDataGridByHd(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '��������<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','����',
		    		  '�����������','��������״̬dm','���',
		    		  '����Ǽ����','���˵Ǽ����','״̬dm','״̬','�ͻ�����','���˽��','������','���','������','�ص����',
		    		  '��������', '��������','ʼ����', 'Ŀ�ĵ�', '����', '����', '���','��װ','�ɳ�����','�ɳ�����','��������','˾��',
		    		  'ddDjxh','�������','�µ�����', '������','��������','���˲���','ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', sortable:false, width:'80', align:'center'},
			  {name:'checkboxoperationcol2', index:'checkboxoperationcol2', sortable:false, width:'80', align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false, width:'35', align:'center'},
			  {name:'wsSpztDm', index:'wsSpztDm', hidden:true,width:'20', align:'center'},
			  {name:'wsspxh', index:'wsspxh', hidden:true,width:'20', align:'center'},
			  {name:'wsSpztMc', index:'wsSpztMc', width:'50', align:'center'},
			  {name:'jsDjxh', index:'jsDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzDjxh', index:'dzDjxh', hidden:true,width:'20', align:'center'},
			  {name:'dzztDm', index:'dzztDm', hidden:true,width:'10', align:'center'},
			  {name:'dzztMc', index:'dzztMc', width:'50', align:'center'},
			  {name:'khMc', index:'khMc', width:'150', align:'center'},
			  {name:'dzsr', index:'dzsr', width:'65', align:'center'},
			  {name:'jssr', index:'jssr', width:'70', align:'center'},
			  {name:'dzcybz', index:'dzcybz', width:'35', align:'center', hidden:true},
			  {name:'dzcyje', index:'dzcyje', width:'60', align:'center', hidden:true},
			  {name:'hdbh', index:'hdbh', width:'80', align:'right'}, 
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'jssl', index:'jssl', width:'60', align:'right'}, 
		      {name:'sfd', index:'sfd', width:'50', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', align:'center'}, 
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'bz', index:'bz', width:'50', align:'center'},
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'},
		      {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'clhm', index:'clhm', width:'70', align:'center'},
		      {name:'sjxm', index:'sjxm', width:'70', align:'center'},
		      {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'70', align:'center'},
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			  {name:'xdrq', index:'xdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'dzrMc', index:'dzrMc', width:'70', align:'center'},
			  {name:'dzrq', index:'dzrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'bmmc', index:'bmmc', width:'100', align:'center'},
			  {name:'dwmc', index:'dwmc', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DDBH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		   // multiselect: true, //�Ƿ���ʾ��ѡ��  
		    //multiboxonly: false,  //�Ƿ�ֻ�е����ѡ��ʱ,��ִ��ѡ���ѡ��checkbox.Ĭ��Ϊfalse,���һ����ѡ�����еĶ�ѡ��
		    //multiselectWidth: 50, //��ѡ�������еĿ��   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		       	  $("#mainForm").attr("action",jcontextPath+"/hygl/jsglsrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
     /**************************************��ҳ����****************************************/
	