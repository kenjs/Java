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
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			//批量发送
			$("#plScSendBtn").click(function(){
				var wsDm="305001";//收入对帐差异审核表
				plScSend(wsDm,"");
			});
			//批量对账
			$("#plDzBtn").click(function(){
				//alert(0);
				var checkedVal = new Array();
				var xhs = $(":checked[name='dzxhs']");
				if (xhs.length <= 0) {
					showAlert("请选择需要批量对账的记录！");
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
			
			//初始化表格(按订单)
			initDataGrid();
			initList();	
			var dzfsDm = trim($("#mainForm_domain_dzfsDm").val());
			if(undefined==dzfsDm || null==dzfsDm || ""==dzfsDm){
				dzfsDm="1";//默认显示按订单
			}
			//changeTable(dzfsDm);	
	});
	function plDzSuccess(){
		showAlert("批量对账成功！");
        onRefresh();
	}

	function changeRq(dzzt){
		if("1"==dzzt){
			$("#rqTd").text("下单日期："); 
			$("#mainForm_domain_rqQ").val(""); 
		}else{
			$("#rqTd").text("对帐日期："); 
			var defultRqQ=$("#mainForm_domain_defultRqQ").val(); 
			$("#mainForm_domain_rqQ").val(defultRqQ); 
		}
	}
	
	function changeTable(dzfsDm){
	
		//显示按订单的table
		if("1"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGrid();
		}
		//显示按订单货物的table
		if("2"==dzfsDm){
			$("#dataList").GridUnload();
		    initDataGridByDdHw();
		}
		//显示按派车的table
		if("3"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByPc();
		}
		//显示按派车货物的table
		if("4"==dzfsDm){
		    $("#dataList").GridUnload();
			initDataGridByPcHw();
		}
		//显示按回单的table
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
		    //默认选中
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
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.ddDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jsglsrdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    	
	/**************************************分页开始****************************************/
	//刷新当前页面
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
			showAlert("请您选择业务单位！");
			return;
		}
		/*if(undefined==bmDm || null==bmDm || ""==bmDm){
			showAlert("请您选择登记部门！");
			return;
		}*/
		if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
			//showAlert("请您选择客户名称！");
			//return;
			khDjxh="";
		}
		if(undefined==dzfsDm || null==dzfsDm || ""==dzfsDm){
			showAlert("请您选择对账方式！");
			return;
		}
		
		//显示按订单的table
		if("1"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGrid();
		}
		//显示按订单货物的table
		if("2"==dzfsDm){
			$("#dataList").GridUnload();
		    initDataGridByDdHw();
		}
		//显示按派车的table
		if("3"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByPc();
		}
		//显示按派车货物的table
		if("4"==dzfsDm){
		    $("#dataList").GridUnload();
			initDataGridByPcHw();
		}
		//显示按回单的table
		if("5"==dzfsDm){
			$("#dataList").GridUnload();
			initDataGridByHd();
		}
		
		if("1"==dzztDm){
			if(undefined==rqZ || null==rqZ || ""==rqZ){
				showAlert("请您选择下单日期止！");
				return;
			}
			
			$("#dataList").jqGrid('hideCol',["dzcybz","dzcyje","dzje"]);
		}
		if("2"==dzztDm){
			if(undefined==rqQ || null==rqQ || ""==rqQ){
				showAlert("请您选择对帐日期起！");
				return;
			}
			if(undefined==rqZ || null==rqZ || ""==rqZ){
				showAlert("请您选择对帐日期止！");
				return;
			}
			//
			$("#dataList").jqGrid('showCol',["dzcybz","dzcyje","dzje"]);
		}
		
		//请求表格数据
		var url = jcontextPath+"/hygl/jsglsrdz!query";
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.bmDm":encodeURI(bmDm),"domain.dzfsDm":encodeURI(dzfsDm),
		 	"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
		 	"domain.ddbh":encodeURI(ddbh),"domain.dzztDm":encodeURI(dzztDm),"domain.khMc":encodeURI(khMc),
		 	"domain.yjZtDm":encodeURI(yjZtDm)}		
		 	//请求的参数，json格
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
		 
	}
	//jqGrid  初始化表格（按订单）
	function initDataGrid(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','批量审批<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '批量对账<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','操作',
		    		  '文书审批序号','文书审批状态dm','审核',
		    		  '结算登记序号','对账登记序号','状态dm','状态','客户名称','对账金额','结算金额','结果','差异金额','ddDjxh','订单编号','下单日期', 
		    		  '货物名称', '结算数量', '回单编号','始发地', '目的地', '数量', '重量', '体积','包装',
		    		  '对帐人','对帐日期','对账部门','业务单位'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DDBH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		   // multiselect: true, //是否显示多选框  
		    //multiboxonly: false,  //是否只有点击多选框时,才执行选择多选框checkbox.默认为false,点击一行亦选定此行的多选框
		    //multiselectWidth: 50, //多选框所在列的宽度   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
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
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dzfsDm = trim($("#mainForm_domain_dzfsDm").val());
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jsDjxh"); 	  //获取当前单元格里面的登记序号 
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
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+dzDjxh+"','"+dzfsDm+"')\"><font color=\"blue\">对帐</font></a>";
                }
                if("2"==dzztDm){
                	if("0"==wsSpztDm || "2"==wsSpztDm){//未发送和退回
                		input="<input type=\"checkbox\" name=\"xhs\" value=\""+val+"#"+wsspxh+"\" />";
                	}else{
                		input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"#"+wsspxh+"\" />";
                	}
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+dzDjxh+"','"+dzfsDm+"')\"><font color=\"blue\">修改</font></a>";
                	dzInput = "<input type=\"checkbox\" name=\"dzxhs\" disabled=\"disabled\" value=\""+val+"#"+dzDjxh+"#"+dzfsDm+"\" />";
                }
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol2': dzInput });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                
                var hdshStr = '';
                if (hdshFlag == '0') {
                	hdshStr = '<font color="red">未收回</font>';
                }else if (hdshFlag == '1') {
                	hdshStr = '已收回';
                }
                $("#dataList").jqGrid('setRowData', cl, {'hdshFlag': hdshStr });
           }
       var gridName = "dataList";
       //按订单
       if("1"==dzfsDm){
       		var a = ['pageXh','checkboxoperationcol','checkboxoperationcol2','hstoperationcol','wsSpztDm','wsspxh','wsSpztMc','jsDjxh','dzDjxh','dzztDm','dzztMc','khMc',
	   			'dzsr','jssr','dzcybz','dzcyje','ddbh','xdrq','dzrMc','dzrq','bmmc','dwmc'
	            ];
 		
       		Merger(gridName, 'pageXh', a);
       }
       
	   
   }
   
   //jqGrid  初始化表格(按订单货物)
	function initDataGridByDdHw(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','批量审批<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '批量对账<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','操作',
		    		  '文书审批序号','文书审批状态dm','审核',
		    		  '结算登记序号','对账登记序号','状态dm','状态','客户名称','月结','对账金额','结果','差异金额','ddDjxh','订单编号','下单日期', 
		    		  '货物名称', '回单编号','始发地', '目的地', '数量', '重量', '体积','包装',
		    		  '发货人','电话','收货人','电话','收货地址','回单收回',
		    		  '对帐人','对帐日期','对账部门','业务单位'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 50,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,200],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DDBH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		   // multiselect: true, //是否显示多选框  
		    //multiboxonly: false,  //是否只有点击多选框时,才执行选择多选框checkbox.默认为false,点击一行亦选定此行的多选框
		    //multiselectWidth: 50, //多选框所在列的宽度   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
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
	
	//jqGrid  初始化表格(按派车)
	function initDataGridByPc(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','批量审批<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '批量对账<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','操作',
		    		  '文书审批序号','文书审批状态dm','审核',
		    		  '结算登记序号','对账登记序号','状态dm','状态','客户名称','对账金额','结算金额','结果','差异金额','派车单号','派车日期','车辆号码','司机',
		    		  'ddDjxh','订单编号','下单日期', '货物名称', '结算数量', '回单编号','始发地', '目的地', '数量', '重量', '体积','包装',
		    		  '对帐人','对帐日期','对账部门','业务单位'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DDBH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		   // multiselect: true, //是否显示多选框  
		    //multiboxonly: false,  //是否只有点击多选框时,才执行选择多选框checkbox.默认为false,点击一行亦选定此行的多选框
		    //multiselectWidth: 50, //多选框所在列的宽度   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
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
	
	//jqGrid  初始化表格(按派车货物)
	function initDataGridByPcHw(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','批量审批<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '批量对账<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','操作',
		    		  '文书审批序号','文书审批状态dm','审核',
		    		  '结算登记序号','对账登记序号','状态dm','状态','客户名称','对账金额','结算金额','结果','差异金额','派车单号','派车日期','车辆号码','司机',
		    		  'ddDjxh','订单编号','下单日期', 
		    		  '货物名称', '结算数量', '回单编号','始发地', '目的地', '数量', '重量', '体积','包装',
		    		  '对帐人','对帐日期','对账部门','业务单位'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DDBH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		   // multiselect: true, //是否显示多选框  
		    //multiboxonly: false,  //是否只有点击多选框时,才执行选择多选框checkbox.默认为false,点击一行亦选定此行的多选框
		    //multiselectWidth: 50, //多选框所在列的宽度   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
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
	
	//jqGrid  初始化表格(按回单)
	function initDataGridByHd(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','批量审批<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '批量对账<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'dzxhs\');" />','操作',
		    		  '文书审批序号','文书审批状态dm','审核',
		    		  '结算登记序号','对账登记序号','状态dm','状态','客户名称','对账金额','结算金额','结果','差异金额','回单编号',
		    		  '货物名称', '结算数量','始发地', '目的地', '数量', '重量', '体积','包装','派车单号','派车日期','车辆号码','司机',
		    		  'ddDjxh','订单编号','下单日期', '对帐人','对帐日期','对账部门','业务单位'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DDBH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		   // multiselect: true, //是否显示多选框  
		    //multiboxonly: false,  //是否只有点击多选框时,才执行选择多选框checkbox.默认为false,点击一行亦选定此行的多选框
		    //multiselectWidth: 50, //多选框所在列的宽度   
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
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
	
     /**************************************分页结束****************************************/
	