	$(function(){
		$("[name='shfsDm']").click(function() {
			shfsChanged();
		});
		
		if ($("#mainForm_domain_hwmxDomain_xh").val() != "") {
			onShowHw($("#mainForm_domain_mbDjxh").val(),$("#mainForm_domain_hwmxDomain_xh").val());
		}
	});
	function saveData() {
		//模板中xtcs20002没用到为空
		var xtcs20002 = $("#mainForm_domain_xtcs20002").val();
		if(xtcs20002=="0"){
			if(!checkdata1()){
				return;
			}
			/*********校验订单编号********/
			var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
			var yddbh = trim($("#hidDdbh").val());
			var ddbh = trim($("#mainForm_domain_ddbh").val());
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			if (tempFlag == "Y"||ddbh!=yddbh) {
		  		var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.ddbh":ddbh};
				var url = jcontextPath+"/hygl/hytydmbgl!doCheckDdbh";
				ajaxCommon(url,jsonObj,"checkDdbh");
			}else{
				doSave();
			}
		}else{
			if(!checkdata()){
				return;
			}
			doSave();
		}
			
	}
	function checkDdbh(obj) {
		if(obj.domain.ddbh=="-1"){
			showAlert("订单编号已存在，请检查！","checkDdbhSuc");
		}else{
			doSave();
		}
	}
	function checkDdbhSuc(){
		$("#mainForm_domain_ddbh").select();
	}
	/*function checkHwts() {
		var hwts = $("#hwSavedTbody tr").length + $("#hwTbody tr").length;
		if (hwts <= 0) {
			showAlert("托运单必须至少录入一条货物才能保存，请录入货物后重试！")
			return false;
		}
		return true;
	}*/
	

	
	function doSave() {
		var mbDjxh = trim($("#mainForm_domain_mbDjxh").val()); 
		var mbmc = trim($("#mainForm_domain_mbmc").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		
		var bz = $("#mainForm_domain_bz").val();
        var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
        var ssJgbm = $("#mainForm_domain_ssJgbm").val();
        var djJgbm = $("#mainForm_domain_djJgbm").val();
        var dzztDm = $("#mainForm_domain_dzztDm").val();
    	
    	
        /************************原数据 不用**************************
        var fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val(); 
		var shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val(); 
		var fhrDjxh2 = $("#mainForm_domain_fhrDjxh2").val();
		var fhrMc2 = $("#mainForm_domain_fhrMc2").val();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		var fhrDz = $("#mainForm_domain_fhrDz").val(); 
		var fhrLxr = $("#mainForm_domain_fhrLxr").val();
		var fhrLxdh = $("#mainForm_domain_fhrLxdh").val(); 
		var shrDjxh = $("#mainForm_domain_shrDjxh").val();
		var shrMc = $("#mainForm_domain_shrMc").val(); 
		var shrDz = $("#mainForm_domain_shrDz").val(); 
		var shrLxr = $("#mainForm_domain_shrLxr").val();
		var shrLxdh = $("#mainForm_domain_shrLxdh").val(); 		
		var yqFhrq = $("#mainForm_domain_yqFhrq").val(); 
		var yqDdrq = $("#mainForm_domain_yqDdrq").val(); 
		var shfsDm = document.getElementsByName("shfsDm")[0].checked ? "1" : "2"; 

		//var hdbh = $("#mainForm_domain_hdbh").val();
		 
		var thflDm = $("[name='thflDm']")[0].checked ? "1" : "";
		
		var yjjsfsDm = $("#mainForm_domain_yjjsfsDm").val();
		var ykjsfsDm;
		
		var srHj = trim($("#mainForm_domain_hwmxDomain_srHj").val());
		if(srHj==""){
			ykjsfsDm = "2";
		}else{
			ykjsfsDm = "1";
		}
		var srXf = $("#mainForm_domain_hwmxDomain_srXf").val();
        var srHdf = $("#mainForm_domain_hwmxDomain_srHdf").val();
        var srThf = $("#mainForm_domain_hwmxDomain_srThf").val();
        var srYj = "0";
        var srHf = $("#mainForm_domain_hwmxDomain_srHf").val();
        var srHk = $("#mainForm_domain_hwmxDomain_srHk").val();
        */
        
		var url = jcontextPath+"/hygl/hytydmbgl!save";  
    	var jsonObj = {"domain.mbDjxh":mbDjxh,"domain.mbmc":mbmc,"domain.bz":bz,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":fhrMc,"domain.dzztDm":dzztDm,
    			       "domain.hwmxDomain.tempFlag":tempFlag,"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm};   	
    	showMessage();
		ajaxCommon(url,jsonObj,"doSaveSuc");
	}
	
	function doSaveSuc(data) {
		//$("#mainForm_domain_ddDjxh").val(data.domain.ddDjxh);
		hideMessage();
		showSuccess("保存成功！", "saveSucYesCallBack");
	}
	
	function saveSucYesCallBack() {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		if (tempFlag == "Y") {
			doEmpty();
		}else{
			sysClose();
		}
		
	}
	function doEmpty(){
        var s="";
        $("#mainForm_domain_mbDjxh").val(s);
        $("#mainForm_domain_mbmc").val(s);
        
        
		$("#mainForm_domain_fhrXzqhDm").val(s); 
		$("#mainForm_domain_shrXzqhDm").val(s);
		$("#mainForm_domain_fhrXzqhMc").val(s); 
		$("#mainForm_domain_shrXzqhMc").val(s);
		
		$("#mainForm_domain_fhrDjxh").val(s);
		$("#mainForm_domain_fhrMc").val(s); 
		$("#mainForm_domain_fhrDjxh2").val(s);
		$("#mainForm_domain_fhrMc2").val(s); 
		$("#mainForm_domain_fhrDz").val(s); 
		$("#mainForm_domain_fhrLxr").val(s);
		$("#mainForm_domain_fhrLxdh").val(s); 
		
		$("#mainForm_domain_shrDjxh").val(s);
		$("#mainForm_domain_shrMc").val(s); 
		$("#mainForm_domain_shrDz").val(s); 
		$("#mainForm_domain_shrLxr").val(s);
		$("#mainForm_domain_shrLxdh").val(s); 
		
		
		$("#mainForm_domain_yqFhrq").val(s); 
		$("#mainForm_domain_yqDdrq").val(s);
		$("#mainForm_domain_bz").val(s);
		
		$("#mainForm_domain_srHj").val(s);
		$("#mainForm_domain_srXf").val(s);
        $("#mainForm_domain_srHdf").val(s);
        $("#mainForm_domain_srThf").val(s);
        $("#mainForm_domain_srYj").val(s);
        $("#mainForm_domain_srHf").val(s);
        $("#mainForm_domain_srHk").val(s);
        
		$("#hwSavedTbody").empty();
		$("#hwTbody").empty();
		
		emptyForm();//清货物数据
	}
	function doEmptyWhenChange(flag){
        var s="";
        $("#mainForm_domain_fhrXzqhDm").val(s); 
		$("#mainForm_domain_shrXzqhDm").val(s);
		$("#mainForm_domain_fhrXzqhMc").val(s); 
		$("#mainForm_domain_shrXzqhMc").val(s);
		
		$("#mainForm_domain_fhrDz").val(s); 
		$("#mainForm_domain_fhrLxr").val(s);
		$("#mainForm_domain_fhrLxdh").val(s); 
		
		$("#mainForm_domain_shrDjxh").val(s);
		$("#mainForm_domain_shrMc").val(s); 
		$("#mainForm_domain_shrDz").val(s); 
		$("#mainForm_domain_shrLxr").val(s);
		$("#mainForm_domain_shrLxdh").val(s); 
		
		
		$("#mainForm_domain_yqFhrq").val(s); 
		$("#mainForm_domain_yqDdrq").val(s);
		$("#mainForm_domain_bz").val(s);
		
		$("#mainForm_domain_srHj").val(s);
		$("#mainForm_domain_srXf").val(s);
        $("#mainForm_domain_srHdf").val(s);
        $("#mainForm_domain_srThf").val(s);
        $("#mainForm_domain_srYj").val(s);
        $("#mainForm_domain_srHf").val(s);
        $("#mainForm_domain_srHk").val(s);
        if(flag==1){
			$("#hwSavedTbody").empty();
			$("#hwTbody").empty();
        }
		emptyForm();//清货物数据
		}
	function initRadio() {
		if ($("#mainForm_domain_thflDm").val() == 1) {
			document.getElementsByName("thflDm")[0].checked = true;
		}else {
			document.getElementsByName("thflDm")[1].checked = true;
		}
		if ($("#mainForm_domain_shfsDm").val() == 2) {
			document.getElementsByName("shfsDm")[1].checked = true;
		}else {
			document.getElementsByName("shfsDm")[0].checked = true;
		}	
		shfsChanged();
	}
	
	function shfsChanged() {
		if (document.getElementsByName("shfsDm")[0].checked) {
			$(".df").hide();
			$("#mainForm_domain_srThf").val($("#mainForm_domain_srHdf").val());
			$("#mainForm_domain_srHdf").val("");
			$(".tf").show();
		}else {
			$(".tf").hide();
			$("#mainForm_domain_srHdf").val($("#mainForm_domain_srThf").val());
			$("#mainForm_domain_srThf").val("");
			$(".df").show();
		}
	}
	
	function checkFhr() {
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		if (fhrDjxh == "" && fhrMc == "") {
			showAlert("请先录入客户名称！");
			return false;
		}
		return true;
	}
	function onShowHw(mbDjxh,xh) {
		
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		//alert(tempFlag);
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var jsonObj = {"domain.hwmxDomain.mbDjxh":mbDjxh,"domain.hwmxDomain.xh":xh,"domain.hwmxDomain.tempFlag":tempFlag,"domain.fhrDjxh":fhrDjxh};
		var url = jcontextPath+"/hygl/hytydmbgl!initHwMx";
		ajaxCommon(url,jsonObj,"initHwSuc");
	}
	function onCloseMx() {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		var l = $("#hwTbody tr").length;
		if(tempFlag=='Y'&&l>0){
		   showConfirm("托单未保存，确认关闭么？", "doCloseMx");
		}else{
		   doCloseMx();
		}
	}
	function doCloseMx() {
			sysClose();
	}
	
	function initHwSuc(obj){
	    $("#lrbz").text("货物信息【修改】");
	    $("#mainForm_domain_hwmxDomain_xh").val(obj.domain.hwmxDomain.xh);
	    $("#mainForm_domain_hwmxDomain_hwmc").val(obj.domain.hwmxDomain.hwmc);
	    $("#hwmc2").val(obj.domain.hwmxDomain.hwmc);
	    $("#hwmcShow1").hide();
	    $("#hwmcShow2").show();
		$("#showSaveHw1").hide();
	    $("#showSaveHw2").show();
		    
		$("#mainForm_domain_hwmxDomain_hdbh").val(obj.domain.hwmxDomain.hdbh); 
		$("#mainForm_domain_hwmxDomain_hwDjxh").val(obj.domain.hwmxDomain.hwDjxh);
		$("#mainForm_domain_hwmxDomain_hwxhDjxh").val(obj.domain.hwmxDomain.hwxhDjxh);
		$("#mainForm_domain_hwmxDomain_hwBzHldwDm").val(obj.domain.hwmxDomain.hwBzHldwDm); 
		$("#mainForm_domain_hwmxDomain_hwSl").val(obj.domain.hwmxDomain.hwSl); 
		$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val(obj.domain.hwmxDomain.hwSlJldwDm); 
		$("#mainForm_domain_hwmxDomain_hwZl").val(obj.domain.hwmxDomain.hwZl); 
		$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val(obj.domain.hwmxDomain.hwZlJldwDm); 
		$("#mainForm_domain_hwmxDomain_hwTj").val(obj.domain.hwmxDomain.hwTj); 
		$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val(obj.domain.hwmxDomain.hwTjJldwDm); 
		$("#jsJldwFlDm").val(obj.domain.hwmxDomain.jsJldwFlDm); 
		$("#mainForm_domain_hwmxDomain_hwflDm").val(obj.domain.hwmxDomain.hwflDm);
		initHwflDm();
		
		$("#mainForm_domain_shfsDm").val(obj.domain.hwmxDomain.shfsDm);
		$("#mainForm_domain_thflDm").val(obj.domain.hwmxDomain.thflDm);
		initRadio();
		
		/*****************收入***********************/
		$("#mainForm_domain_srHj").val(obj.domain.hwmxDomain.srHj);
		$("#mainForm_domain_srXf").val(obj.domain.hwmxDomain.srXf);
	    $("#mainForm_domain_srHdf").val(obj.domain.hwmxDomain.srHdf);
	    $("#mainForm_domain_srThf").val(obj.domain.hwmxDomain.srThf);
	    $("#mainForm_domain_srHf").val(obj.domain.hwmxDomain.srHf);
	    $("#mainForm_domain_srHk").val(obj.domain.hwmxDomain.srHk);
	    
	    /*****************fhr shr 地址等**************************/
		$("#mainForm_domain_fhrXzqhDm").val(obj.domain.hwmxDomain.fhrXzqhDm); 
		$("#mainForm_domain_shrXzqhDm").val(obj.domain.hwmxDomain.shrXzqhDm);
		$("#mainForm_domain_fhrXzqhMc").val(obj.domain.hwmxDomain.fhrXzqhMc); 
		$("#mainForm_domain_shrXzqhMc").val(obj.domain.hwmxDomain.shrXzqhMc);
		
		$("#mainForm_domain_fhrDjxh2").val(obj.domain.hwmxDomain.fhrDjxh);
		$("#mainForm_domain_fhrMc2").val(obj.domain.hwmxDomain.fhrMc); 
		$("#mainForm_domain_fhrDz").val(obj.domain.hwmxDomain.fhrDz); 
		$("#mainForm_domain_fhrLxr").val(obj.domain.hwmxDomain.fhrLxr);
		$("#mainForm_domain_fhrLxdh").val(obj.domain.hwmxDomain.fhrLxdh); 
		
		$("#mainForm_domain_shrDjxh").val(obj.domain.hwmxDomain.shrDjxh);
		$("#mainForm_domain_shrMc").val(obj.domain.hwmxDomain.shrMc); 
		$("#mainForm_domain_shrDz").val(obj.domain.hwmxDomain.shrDz); 
		$("#mainForm_domain_shrLxr").val(obj.domain.hwmxDomain.shrLxr);
		$("#mainForm_domain_shrLxdh").val(obj.domain.hwmxDomain.shrLxdh); 
		
		$("#mainForm_domain_yqFhrq").val(obj.domain.hwmxDomain.yqFhrq); 
		$("#mainForm_domain_yqDdrq").val(obj.domain.hwmxDomain.yqDdrq);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.ssJgbm","domain.djJgbm","domain.mbmc","domain.fhrDjxh",
		                        "domain.ddbh","domain.bz"];
		var labelNameArray = ["业务单位","登记部门","模板名称","客户",
		                      "订单编号","备注"];
		var compareValueArray = [16,16,100,16,
		                         50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,true,
		                    false,false];	
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	//当订单编号手工录入时
	function checkdata1(){
		var controlNameArray = ["domain.ssJgbm","domain.djJgbm","domain.mbmc","domain.fhrDjxh",
		                        "domain.ddbh","domain.bz"];
		var labelNameArray = ["业务单位","登记部门","模板名称","客户",
		                      "订单编号","备注"];
		var compareValueArray = [16,16,100,16,
		                         50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,true,
		                    true,false];	
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function refreshHwList() {
		var mbDjxh = trim($("#mainForm_domain_mbDjxh").val()); 
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		var jsonObj = {"domain.mbDjxh":mbDjxh, "domain.hwmxDomain.tempFlag":tempFlag};
		var url = jcontextPath+"/hygl/hytydmbgl!refreshHwList"; 
		showMessage();
		ajaxCommon(url,jsonObj,"doRefreshHwSuc");
	}
	
	function doRefreshHwSuc(data) {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		hideMessage();
		var hwList = data.domain.hwList;
		$("#hwSavedTbody").empty();
		$("#hwTbody").empty();
		
		var xh = $("#hwSavedTbody tr").length + $("#hwTbody tr").length;
		
		$.each(hwList, function(i, hw){
			var trObj = $("<tr id=\"tr"+hw.xh+"\"></tr>");
			var td1 = $("<td align=\"center\" class=\"bh\"></td>");
			$(td1).text(++xh).appendTo($(trObj));
			var color = "blue";
			var thfsMc = hw.thflDm=="1"?"是":"否";
			var hwflMc = hw.hwflDm=="1"?"重货":"泡货";
			var df = hw.shfsDm==1?hw.srThf:hw.srHdf;
			$("<td align=\"center\"><a href=\"javascript:confirmDelHwxx("+hw.xh+")\"><font color=\""+color+"\">删除</font></a></td>").appendTo($(trObj));
            $("<td align=\"center\"><a href=\"javascript:onShowHw("+hw.mbDjxh+","+hw.xh+")\"><font color=\""+color+"\">"+hw.hwmc+"</font></a></td>").appendTo($(trObj));
			$("<td align=\"center\">"+thfsMc+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.shfsMc==null?"":hw.shfsMc)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+hwflMc+"</td>").appendTo($(trObj));
			
      		$("<td align=\"right\">"+(hw.hwSl == null ? "" : hw.hwSl) +"&nbsp;"+(hw.hwSlJldwMc == null || hw.hwSl == null ? "" : hw.hwSlJldwMc)+"&nbsp;</td>").appendTo($(trObj)); 
	        $("<td align=\"right\">"+(hw.hwZl == null ? "" : hw.hwZl)+"&nbsp;"+(hw.hwZlJldwMc == null || hw.hwZl == null ? "" : hw.hwZlJldwMc)+"&nbsp;</td>").appendTo($(trObj));
	        $("<td align=\"right\">"+(hw.hwTj == null ? "" : hw.hwTj)+"&nbsp;"+(hw.hwTjJldwMc == null || hw.hwTj == null ? "" : hw.hwTjJldwMc)+"&nbsp;</td>").appendTo($(trObj));
	        $("<td align=\"center\">"+(hw.hwbzHldwMc == null ? "" : hw.hwbzHldwMc)+"</td>").appendTo($(trObj));
	        
	        $("<td align=\"center\">"+(hw.srHj==null?"":hw.srHj)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.srXf==null?"":hw.srXf)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(df==null?"":df)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.srHf==null?"":hw.srHf)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.srHk==null?"":hw.srHk)+"</td>").appendTo($(trObj));
			
			$("<td align=\"center\">"+(hw.shrXzqhMc==null?"":hw.shrXzqhMc)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.shrMc==null?"":hw.shrMc)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.shrDz==null?"":hw.shrDz)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.shrLxr==null?"":hw.shrLxr)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.shrLxdh==null?"":hw.shrLxdh)+"</td>").appendTo($(trObj));
	        
			$("<td align=\"center\">"+(hw.yqFhrq==null?"":hw.yqFhrq)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.yqDdrq==null?"":hw.yqDdrq)+"</td>").appendTo($(trObj));
						
			$("<td align=\"center\">"+(hw.fhrXzqhMc==null?"":hw.fhrXzqhMc)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.fhrMc==null?"":hw.fhrMc)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.fhrDz==null?"":hw.fhrDz)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.fhrLxr==null?"":hw.fhrLxr)+"</td>").appendTo($(trObj));
			$("<td align=\"center\">"+(hw.fhrLxdh==null?"":hw.fhrLxdh)+"</td>").appendTo($(trObj));
	        
        	$(trObj).appendTo($("#hwTbody"));
	        
		});
	}
	var hwXhs = new Array();
	function confirmDelHwxx(xh) {
		hwXhs[0] = xh;
		showConfirm("确定要删除选中的货物信息吗？", "delHwxx");		
	}
	function delHwxx() {
		var mbDjxh = $("#mainForm_domain_mbDjxh").val();
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		showMessage();
		if (hwXhs.length > 0) {
			var jsonStr = getJqueryParam(hwXhs, "domain.hwXhs");
			var jsonObj = {"domain.mbDjxh":mbDjxh, "domain.hwmxDomain.tempFlag":tempFlag};
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/hytydmbgl!deleteHwMx";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelHwxxSuc", false);
		}else {
			doDelHwxxSuc();
		}
		
	}
	function doDelHwxxSuc(){ 
		delHwxxRows();
        changeBh();
        hideMessage();
        showAlert("删除成功！");
	}
	
	function delHwxxRows() {
		$("#tr"+hwXhs[0]).remove();
	}
	function getJqueryParam(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	function changeBh() {
		var bhs = $(".bh");
		$.each(bhs, function(i, obj){
			$(obj).text(i+1);
		});
	}
	
	/*function onUpdateHw(ddDjxh, xh) {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var url = jcontextPath+"/hygl/hytydgl!initHwMx.action?domain.hwmxDomain.ddDjxh="+ddDjxh+"&domain.hwmxDomain.xh="+xh
					+"&domain.hwmxDomain.tempFlag="+tempFlag+"&domain.fhrDjxh="+fhrDjxh+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:440px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
	}
	
	function checkHw(obj) {
		$(":checkbox[name='hwXhs']").attr("checked", obj.checked);
	}*/
	

	function initTydFromMbSuc(data) {
		initTydDomain(data);
		$("#mainForm_domain_hwmxDomain_tempFlag").val("Y");
		initTydDateFromMb(data.domain);
		initRadio();
		doRefreshHwSuc(data)
		
		hideMessage();
	}
	
	function initTydFromCopySuc(data) {
		initTydDomain(data)
		initTydDateFromCopy(data.domain);
		initRadio();
		doRefreshHwSuc(data)
		$("#mainForm_domain_fhrDjxh2").val(data.domain.fhrDjxh);
	    $("#mainForm_domain_fhrMc2").val(data.domain.fhrMc);
		hideMessage();
	}
	
	function initTydDomain(data) {
		var domain = data.domain;
		var inputs = $(":input[name^='domain.'][name!='domain.ssJgbm'][name!='domain.djJgbm'][class!='notChange']");
		
		$.each(inputs, function(i, obj){
			if (obj.name != null && obj.name != "") {
				if (eval(obj.name) == null || eval(obj.name) == "null") {
					$(obj).val("");
				}else {
					$(obj).val(eval(obj.name));
				}
			}
		});
	}
	
	
	function checkKhExists() {
		var khmc = $("#mainForm_domain_fhrMc").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		if (fhrDjxh == "") {
			onDropDownSelected4Fhr(createLi4InputSel(khmc));
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		if(khmc!=""){
			if (fhrDjxh == "") {
				showAlert("该客户未维护，请先点击【新增客户】维护客户信息！");
				$("#mainForm_domain_fhrDjxh").val($("#mainForm_domain_fhrDjxh2").val());
				$("#mainForm_domain_fhrMc").val($("#mainForm_domain_fhrMc2").val());
				//onAddKh();
			}
		}
	}
	
	function onAddKh() {
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var url = jcontextPath+"/zygl/qykhdjxx!initMx?domain.khmc="+fhrMc+"&domain.callOpenWinFun=initKhxxFromKxdj";
		window.showModalDialog(url,window,"dialogHeight:430px;dialogWidth:680px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		//window.open(url);
	}
	
	function initKhxxFromKxdj(khDjxh, khmc) {
		$("#mainForm_domain_fhrDjxh").val(khDjxh);
		$("#mainForm_domain_fhrMc").val(khmc);
		
		$("#mainForm_domain_fhrDjxh2").val(khDjxh);
		$("#mainForm_domain_fhrMc2").val(khmc);
		
		doEmptyWhenChange(1);
	}
