	$(function(){
		$("#shfsDm").change(function() {
			shfsChanged();
		});
		
		if ($("#mainForm_domain_hwmxDomain_xh").val() != "") {
			onShowHw($("#mainForm_domain_ddDjxh").val(),$("#mainForm_domain_hwmxDomain_xh").val());
		}
	});
	/***************************************        mx     *******************************************************/
	function saveData() {
		var xtcs20002 = $("#mainForm_domain_xtcs20002").val();
		if(xtcs20002=="0"){
			if(!checkdata1()){
				return;
			}
			var ddbh = trim($("#mainForm_domain_ddbh").val());
			var zgsbm = $("#zgsbm").val();
			//alert(zgsbm);
			if(zgsbm=="1000000021"){
				//alert(ddbh);
				var pattern1 = /^\d{7}$/;
				var pattern2 = /^\d{9}$/;
				if(!(ddbh.match(pattern1)||ddbh.match(pattern2))){
					showAlert("������ű�����7λ��9λ���֣�");
					return;
				}
			}
			/*********У�鶩�����********/
			var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
			var yddbh = trim($("#hidDdbh").val());
			
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			if (tempFlag == "Y"||ddbh!=yddbh) {
		  		var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.ddbh":ddbh};
				var url = jcontextPath+"/hygl/hytydgl!doCheckDdbh";
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
			showAlert("��������Ѵ��ڣ����飡","checkDdbhSuc");
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
			showAlert("���˵���������¼��һ��������ܱ��棬��¼���������ԣ�")
			return false;
		}
		return true;
	}*/
	

	
	function doSave() {
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var xdrq = $("#mainForm_domain_xdrq").val();
		var bz = $("#mainForm_domain_bz").val();
        var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
        var ssJgbm = $("#mainForm_domain_ssJgbm").val();
        var djJgbm = $("#mainForm_domain_djJgbm").val();
        var dzztDm = $("#mainForm_domain_dzztDm").val();
    	
    	
        /************************ԭ���� ����**************************
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
        
		var url = jcontextPath+"/hygl/hytydgl!save";  
    	var jsonObj = {"domain.ddDjxh":ddDjxh,"domain.ddbh":ddbh,"domain.bz":bz,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":fhrMc,"domain.dzztDm":dzztDm,
    			       "domain.xdrq":xdrq,"domain.hwmxDomain.tempFlag":tempFlag,"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm};   	
    	showMessage();
		ajaxCommon(url,jsonObj,"doSaveSuc");
	}
	var ddDjxhPrint;
	var xhPrint;
	function doSaveSuc(data) {
		//$("#mainForm_domain_ddDjxh").val(data.domain.ddDjxh);
		hideMessage();
		ddDjxhPrint = data.domain.ddDjxh;
		xhPrint = data.domain.xh;
		initHykhData(300,$("#mainForm_domain_ssJgbm").val(),$("#mainForm_domain_djJgbm").val());
		showSuccess("����ɹ���", "saveSucYesCallBack");
	}
	
	function saveSucYesCallBack() {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		if (tempFlag == "Y") {
			var url = jcontextPath + '/hygl/hytydgl!printView.action?domain.hwmxDomain.ddDjxh='+ddDjxhPrint+'&domain.hwmxDomain.xh='+xhPrint+'&domain.hwmxDomain.tempFlag=Y';
			doEmpty();
			window.open(url, "_blank");
		}else{
			sysClose();
		}
		
	}
	function doEmpty(){
        var s="";
        $("#mainForm_domain_ddDjxh").val(s);
        var ddbh = trim($("#mainForm_domain_ddbh").val());
        var numQ=ddbh[0]/1;
        //alert(mwNum);
        if(ddbh!=""){
        	if(numQ!=0){
        		$("#mainForm_domain_ddbh").val(ddbh/1+1);
        	}else{
        		var len0=ddbh.length;
        		ddbh = (ddbh/1+1)+"";
        		//alert(ddbh);
        		var len1=ddbh.length;
        		//alert(len0+":"+len1);
        		var str = "";
        		for(i=0;i<len0-len1;i++){
        			str+="0";
        		}
        		$("#mainForm_domain_ddbh").val(str+ddbh);
        	}
        	//alert(ddbh);
        }
//		$("#mainForm_domain_shrXzqhDm").val(s);
//		$("#mainForm_domain_shrXzqhMc").val(s);
		
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
		
		emptyForm();//���������
	}
	//flag 0 ��ʼ�� 1��ʾ��
	function doEmptyWhenChange(flag){
        var s="";
        
//		$("#mainForm_domain_shrXzqhDm").val(s);
//		$("#mainForm_domain_shrXzqhMc").val(s);
		
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

		
		$("#mainForm_domain_srHj").val(s);
		$("#mainForm_domain_srXf").val(s);
        $("#mainForm_domain_srHdf").val(s);
        $("#mainForm_domain_srThf").val(s);
        $("#mainForm_domain_srYj").val(s);
        $("#mainForm_domain_srHf").val(s);
        $("#mainForm_domain_srHk").val(s);
       
		emptyForm();//���������
		
		if(flag==1){
			$("#hwSavedTbody").empty();
			$("#hwTbody").empty();	
        }
	}
	function initRadio() {
		if ($("#mainForm_domain_thflDm").val() == 1) {
			document.getElementsByName("thflDm")[0].checked = true;
		}else {
			document.getElementsByName("thflDm")[1].checked = true;
		}
		/*if ($("#mainForm_domain_shfsDm").val() == 2) {
			document.getElementsByName("shfsDm")[1].checked = true;
		}else {
			document.getElementsByName("shfsDm")[0].checked = true;
		}*/
		$("#shfsDm").val($("#mainForm_domain_shfsDm").val());
		shfsChanged();
	}
	
	function shfsChanged() {
		if($("#shfsDm").val()== null||$("#shfsDm").val()== ""){
			$(".tf").hide();
			$(".df").show();
		}else if ($("#shfsDm").val()== 1||$("#shfsDm").val()== "1") {
			$(".df").hide();
			$("#mainForm_domain_srThf").val($("#mainForm_domain_srHdf").val());
			$("#mainForm_domain_srHdf").val("");
			$(".tf").show();
		}else{
			$(".tf").hide();
			if($("#mainForm_domain_srHdf").val()==""){
				$("#mainForm_domain_srHdf").val($("#mainForm_domain_srThf").val());
				$("#mainForm_domain_srThf").val("");
			}
			$(".df").show();
		}
	}
	
	function checkFhr() {
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		if (fhrDjxh == "" && fhrMc == "") {
			showAlert("����¼��ͻ����ƣ�");
			return false;
		}
		return true;
	}
	function onShowHw(ddDjxh,xh) {
		
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var jsonObj = {"domain.hwmxDomain.ddDjxh":ddDjxh,"domain.hwmxDomain.xh":xh,"domain.hwmxDomain.tempFlag":tempFlag,"domain.fhrDjxh":fhrDjxh};
		var url = jcontextPath+"/hygl/hytydgl!initHwMx";
		ajaxCommon(url,jsonObj,"initHwSuc");
	}
	function onCloseMx() {
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		var l = $("#hwTbody tr").length;
		if(tempFlag=='Y'&&l>0){
		   showConfirm("�е�δ���棬ȷ�Ϲر�ô��", "doCloseMx");
		}else{
		   doCloseMx();
		}
	}
	function doCloseMx() {
			sysClose();
	}
	
	function initHwSuc(obj){
	    $("#lrbz").text("������Ϣ���޸ġ�");
	    $("#mainForm_domain_hwmxDomain_xh").val(obj.domain.hwmxDomain.xh);
	    $("#mainForm_domain_hwmxDomain_hwmc").val(obj.domain.hwmxDomain.hwmc);
	    $("#hwmc2").val(obj.domain.hwmxDomain.hwmc);

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
		
		/*****************����***********************/
		$("#mainForm_domain_srHj").val(obj.domain.hwmxDomain.srHj);
		$("#mainForm_domain_srXf").val(obj.domain.hwmxDomain.srXf);
	    $("#mainForm_domain_srHdf").val(obj.domain.hwmxDomain.srHdf);
	    $("#mainForm_domain_srThf").val(obj.domain.hwmxDomain.srThf);
	    $("#mainForm_domain_srHf").val(obj.domain.hwmxDomain.srHf);
	    $("#mainForm_domain_srYj").val(obj.domain.hwmxDomain.srYj);
	    $("#mainForm_domain_srHk").val(obj.domain.hwmxDomain.srHk);
	    

	    $("#mainForm_domain_hwmxDomain_srYsf").val(obj.domain.hwmxDomain.srYsf); 
	    $("#mainForm_domain_hwmxDomain_srPsf").val(obj.domain.hwmxDomain.srPsf); 
	    $("#mainForm_domain_hwmxDomain_srBjf").val(obj.domain.hwmxDomain.srBjf); 
	    $("#mainForm_domain_hwmxDomain_fySmjz").val(obj.domain.hwmxDomain.fySmjz);
	    $("#mainForm_domain_hwmxDomain_fyDshk").val(obj.domain.hwmxDomain.fyDshk);
	    $("#mainForm_domain_hwmxDomain_hwhh").val(obj.domain.hwmxDomain.hwhh);
	    //alert((obj.domain.hwmxDomain.srBjf/obj.domain.hwmxDomain.fySmjz).toFixed(2));
	    if(obj.domain.hwmxDomain.fySmjz!=""){
		    $("#bjbl").val((obj.domain.hwmxDomain.srBjf/obj.domain.hwmxDomain.fySmjz).toFixed(3));

	    }
	    if(obj.domain.hwmxDomain.sfhdBz=="Y"){
	    	$("#sfhd")[0].checked = true;
	    }else if(obj.domain.hwmxDomain.sfhdBz=="N"){
	    	$("#sfhd")[0].checked = false;
	    }
	    /*****************fhr shr ��ַ��**************************/
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
		var controlNameArray = ["domain.ssJgbm","domain.djJgbm","domain.xdrq",
		                        "domain.ddbh","domain.bz"];
		var labelNameArray = ["ҵ��λ","�Ǽǲ���","�µ�����",
		                      "�������","��ע"];
		var compareValueArray = [16,16,11,
		                         50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,
		                    false,false];	
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	//����������ֹ�¼��ʱ
	function checkdata1(){
		var controlNameArray = ["domain.ssJgbm","domain.djJgbm","domain.xdrq",
		                        "domain.ddbh","domain.bz"];
		var labelNameArray = ["ҵ��λ","�Ǽǲ���","�µ�����",
		                      "�������","��ע"];
		var compareValueArray = [16,16,11,
		                         50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,
		                    true,false];	
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function refreshHwList() {
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		var jsonObj = {"domain.ddDjxh":ddDjxh, "domain.hwmxDomain.tempFlag":tempFlag};
		var url = jcontextPath+"/hygl/hytydgl!refreshHwList"; 
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
			var thfsMc = hw.thflDm=="1"?"��":"��";
			var hwflMc = hw.hwflDm=="1"?"�ػ�":"�ݻ�";
			var df = hw.shfsDm==1?hw.srThf:hw.srHdf;
			$("<td align=\"center\"><a href=\"javascript:confirmDelHwxx("+hw.xh+")\"><font color=\""+color+"\">ɾ��</font></a></td>").appendTo($(trObj));
            $("<td align=\"center\"><a href=\"javascript:onShowHw("+hw.ddDjxh+","+hw.xh+")\"><font color=\""+color+"\">"+hw.hwmc+"</font></a></td>").appendTo($(trObj));
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
			$("<td align=\"center\">"+(hw.srYj==null?"":hw.srYj)+"</td>").appendTo($(trObj));
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
		var hwts = $("#hwSavedTbody tr").length + $("#hwTbody tr").length;
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		//alert(tempFlag);
		if(tempFlag=='N'){
  			if(hwts==1){
  	  			showAlert("����Ҫ��һ�����");
  	  			return;
  	  		}
  		}		
		showConfirm("ȷ��Ҫɾ��ѡ�еĻ�����Ϣ��", "delHwxx");		
	}
	function delHwxx() {
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		showMessage();
		if (hwXhs.length > 0) {
			var jsonStr = getJqueryParam(hwXhs, "domain.hwXhs");
			var jsonObj = {"domain.ddDjxh":ddDjxh, "domain.hwmxDomain.tempFlag":tempFlag};
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/hytydgl!deleteHwMx";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelHwxxSuc", false);
		}else {
			doDelHwxxSuc();
		}
		
	}
	function doDelHwxxSuc(){ 
		delHwxxRows();
        changeBh();
        hideMessage();
        showAlert("ɾ���ɹ���");
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
	
	function initTydFromCopy(ddDjxhCopy) {
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val(); 
		var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
		
		var jsonObj = {"domain.ddDjxhCopy":ddDjxhCopy,"domain.ddDjxh":ddDjxh,"domain.ddbh":ddbh,"domain.hwmxDomain.tempFlag":tempFlag};
		var url = jcontextPath+"/hygl/hytydgl!initTydFromCopy";
		showMessage();
		ajaxCommon(url,jsonObj,"initTydFromCopySuc");
	}
	
	function initTydFromMb(mbDjxh) {
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val(); 
		
		var jsonObj = {"domain.mbDjxh":mbDjxh,"domain.ddDjxh":ddDjxh,"domain.ddbh":ddbh};
		var url = jcontextPath+"/hygl/hytydmbgl!initTydByMb";
		showMessage();
		ajaxCommon(url,jsonObj,"initTydFromMbSuc");
	}
	
	function initTydFromMbSuc(data) {
		initTydDomain(data);
		$("#mainForm_domain_hwmxDomain_tempFlag").val("Y");
		initTydDateFromMb(data.domain);
		initRadio();
		doRefreshHwSuc(data)
		$("#mainForm_domain_fhrDjxh2").val(data.domain.fhrDjxh);
	    $("#mainForm_domain_fhrMc2").val(data.domain.fhrMc);
	    
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
	
	function initTydDateFromCopy(domain) {
		if (domain.yqFhrq != null && domain.yqFhrq != "") {
			$("#mainForm_domain_yqFhrq").val((domain.yqFhrq).substring(0,10))
		}
		
		if (domain.yqDdrq != null && domain.yqDdrq != "") {
			$("#mainForm_domain_yqDdrq").val((domain.yqDdrq).substring(0,10))
		}
		
		if (domain.xdrq != null && domain.xdrq != "") {
			$("#mainForm_domain_xdrq").val((domain.xdrq).substring(0,10))
		}
	}
	
	function initTydDateFromMb(domain) {
		if (domain.yqFhrqDate != null && domain.yqFhrqDate != "") {
			$("#mainForm_domain_yqFhrq").val(domain.yqFhrqDate)
		}
		
		if (domain.yqDdrqDate != null && domain.yqDdrqDate != "") {
			$("#mainForm_domain_yqDdrq").val(domain.yqDdrqDate)
		}
		
		if (domain.xdrq != null && domain.xdrq != "") {
			$("#mainForm_domain_xdrq").val((domain.xdrq).substring(0,10))
		}
	}
	
	function checkKhExists() {
		var khmc = $("#mainForm_domain_fhrMc").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		if (fhrDjxh == "") {
			onDropDownSelected4Fhr(createLi4InputSel(khmc));
		}
		
	}
	function doSaveNewKh() {
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var khDjxh = ""; 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());  
		var xzqhDm = "";
		var dz = "";
		var dh = "";
		
		var fzr = ""; 
		var yb = "";
		var bz = "";
		var khlx="2";
		var jsfs="1";
		
		var xxgxfsDm = "3";
		var khbm="";
		var url = jcontextPath+"/zygl/qykhdjxx!save";
		var jsonObj = {"domain.khDjxh":khDjxh,"domain.ssJgbm":ssJgbm,"domain.khmc":fhrMc,"domain.khjc":fhrMc,
                "domain.xzqhDm":xzqhDm,"domain.dz":dz,"domain.dh":dh,"domain.khbm":khbm,
                "domain.yb":yb,"domain.fzr":fzr,"domain.bz":bz,"domain.khlxDm":khlx,"domain.ykjsfsDm":jsfs,"domain.xxgxfsDm":xxgxfsDm};   			
		ajaxCommon(url,jsonObj,"saveOk",true,false);
	}
	function saveOk(data){
		var khDjxh = data.domain.khDjxh;
		var khmc = $("#mainForm_domain_fhrMc").val();
		$("#mainForm_domain_fhrDjxh").val(khDjxh);
		$("#mainForm_domain_fhrDjxh2").val(khDjxh);
		$("#mainForm_domain_fhrMc2").val(khmc);
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
	
	
	/***************************************        hwmx     *******************************************************/
	
	var saveTydFlag;//1Ϊ�ȱ���hw�����е��� 0ֱ�ӱ���hw
	function doSaveHwStart(flag){
			saveTydFlag = flag;
			var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		    var fhrMc = $("#mainForm_domain_fhrMc").val();
			if (fhrMc != "" && fhrDjxh == "") {
				doSaveNewKh();
			}
			if(!checkHwData()){
				return;
			} 
			if (!checkLxfs()) {
				return;
			}
			if (!checkHdbh()) {
				return;
			}
			if (!checkJsJldwFlDm()) {
				return;
			}
			if(!checkXtcs20014()){
			    return;
			}
			//У���ͻ���ʽ
			var shrDz = trim($("#mainForm_domain_shrDz").val());
			var shfsDm = $("#shfsDm").val();
			if(shfsDm==""){
				showAlert("��ѡ���ͻ���ʽ��");
				return;
			}else if(shfsDm=="2"&&shrDz==""){
				showAlert("�ͻ����ţ��ջ���ַ����Ϊ�գ�");
				return;
			}
			var srHj = trim($("#mainForm_domain_srHj").val());
			var srXf = trim($("#mainForm_domain_srXf").val())/1;
			var srHdf = trim($("#mainForm_domain_srHdf").val())/1;
			var srThf = trim($("#mainForm_domain_srThf").val())/1;
			var srYj = trim($("#mainForm_domain_srYj").val())/1;
			if(srHj!=srXf+srThf+srHdf+srYj){
				showAlert("�ָ����������½�֮�ͱ�������ܷ��ã�");
				return;
			}
			var srYsf = trim($("#mainForm_domain_hwmxDomain_srYsf").val())/1;
			var srPsf = trim($("#mainForm_domain_hwmxDomain_srPsf").val())/1;
			var srBjf = trim($("#mainForm_domain_hwmxDomain_srBjf").val())/1;
			var srBjf = trim($("#mainForm_domain_hwmxDomain_srBjf").val())/1;
			var srHk = trim($("#mainForm_domain_srHk").val())/1;
			//alert(srXf+":"+srYsf+":"+srPsf+":"+srBjf);
			if(srHj!=srYsf+srPsf+srBjf+srHk){
				showAlert("����ѡ����ͷѡ����۷ѡ��ؿ�֮�ͱ�����������룡");
				return;
			}
			var srHj = trim($("#mainForm_domain_srHj").val());
			if (srHj == "") {
				srConfirmYes();
			}else {
				if (!checkSr()) {
					return;
				}
				srConfirmYes();
				/*var hk = $("#mainForm_domain_srHk").val();
				if (hk/1 > 0) {
					showConfirm("�����˵��������в����ؿۣ��Ƿ�ȷ�ϱ��棿","srConfirmYes");
				}else {
					srConfirmYes();
				}*/
			}
	}
	function doSaveHw(){ 
			var xh = trim($("#mainForm_domain_hwmxDomain_xh").val()); 
			var hwmc = trim($("#mainForm_domain_hwmxDomain_hwmc").val()); 
			var hwDjxh = trim($("#mainForm_domain_hwmxDomain_hwDjxh").val());
			var hwxhDjxh = trim($("#mainForm_domain_hwmxDomain_hwxhDjxh").val());
			var hwBzHldwDm = trim($("#mainForm_domain_hwmxDomain_hwBzHldwDm").val()); 
			var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val()); 
			var hwSlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwSlJldwDm").val()); 
			var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
			var hwZlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwZlJldwDm").val()); 
			var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
			var hwTjJldwDm = trim($("#mainForm_domain_hwmxDomain_hwTjJldwDm").val()); 
			var hwflDm = $("[name='hwflDm']:checked").val();
			var ddDjxh = trim($("#mainForm_domain_ddDjxh").val());
			var jsJldwFlDm = $("#jsJldwFlDm").val();
			var hdbh = $("#mainForm_domain_hwmxDomain_hdbh").val();
			var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
			//mainForm_domain_fhrXzqhDmҳ������ʱ��ԭҳ�����δ�ģ� ��ʵ�ʴ���domain.hwmxdomain��  ȡ��ͨ��js��ֵ������ͬ
			var fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val(); 
			var shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val(); 
			
			var fhrDjxh2 = $("#mainForm_domain_fhrDjxh").val();
			var fhrMc2 = $("#mainForm_domain_fhrMc").val();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			var fhrDz = $("#mainForm_domain_fhrDz").val(); 
			var fhrLxr = $("#mainForm_domain_fhrLxr").val();
			var fhrLxdh = $("#mainForm_domain_fhrLxdh").val(); 
			var shrDjxh = $("#mainForm_domain_shrDjxh").val();
			var shrMc = $("#mainForm_domain_shrMc").val(); 
			var shrDz = trim($("#mainForm_domain_shrDz").val());
			var shrLxr = $("#mainForm_domain_shrLxr").val();
			var shrLxdh = $("#mainForm_domain_shrLxdh").val(); 		
			var yqFhrq = $("#mainForm_domain_yqFhrq").val(); 
			var yqDdrq = $("#mainForm_domain_yqDdrq").val(); 
			var shfsDm = $("#shfsDm").val();
	
			//var hdbh = $("#mainForm_domain_hdbh").val();
			 
			var thflDm = $("[name='thflDm']")[0].checked ? "1" : "";
			var yjjsfsDm = $("#mainForm_domain_yjjsfsDm").val();
			var ykjsfsDm;
			
			
			var srHj = trim($("#mainForm_domain_srHj").val());
			if(srHj==""){
				ykjsfsDm = "2";
			}else{
				ykjsfsDm = "1";
			}
			var srXf = $("#mainForm_domain_srXf").val();
	        var srHdf = $("#mainForm_domain_srHdf").val();
	        var srThf = $("#mainForm_domain_srThf").val();
	        var srYj = $("#mainForm_domain_srYj").val();
	        var srHf = "0";
	        var srHk = $("#mainForm_domain_srHk").val();
			
			var srYsf = trim($("#mainForm_domain_hwmxDomain_srYsf").val());
			var srPsf = trim($("#mainForm_domain_hwmxDomain_srPsf").val());
			var srBjf = trim($("#mainForm_domain_hwmxDomain_srBjf").val());
			var fySmjz = trim($("#mainForm_domain_hwmxDomain_fySmjz").val());
			var fyDshk = trim($("#mainForm_domain_hwmxDomain_fyDshk").val());
			var hwhh = trim($("#mainForm_domain_hwmxDomain_hwhh").val());
			var sfhdBz;
			if($("#sfhd")[0].checked){
				sfhdBz = "Y";
			}else{
				sfhdBz = "N";
			}
			
			var url = jcontextPath+"/hygl/hytydgl!saveHwMx";  
	    	var jsonObj = {"domain.hwmxDomain.xh":xh,"domain.hwmxDomain.hwmc":hwmc,"domain.hwmxDomain.hwDjxh":hwDjxh,"domain.hwmxDomain.hwBzHldwDm":hwBzHldwDm,
	    				   "domain.hwmxDomain.hwSl":hwSl,"domain.hwmxDomain.hwSlJldwDm":hwSlJldwDm,"domain.hwmxDomain.hwZl":hwZl,
                           "domain.hwmxDomain.hwZlJldwDm":hwZlJldwDm,"domain.hwmxDomain.hwTj":hwTj,"domain.hwmxDomain.hwTjJldwDm":hwTjJldwDm,
                           "domain.hwmxDomain.hwflDm":hwflDm,"domain.hwmxDomain.ddDjxh":ddDjxh,"domain.hwmxDomain.hwxhDjxh":hwxhDjxh,
                           "domain.hwmxDomain.jsJldwFlDm":jsJldwFlDm,"domain.hwmxDomain.hdbh":hdbh,"domain.hwmxDomain.tempFlag":tempFlag,
                           "domain.hwmxDomain.fhrXzqhDm":fhrXzqhDm,"domain.hwmxDomain.shrXzqhDm":shrXzqhDm,
	    				   "domain.hwmxDomain.fhrDjxh":fhrDjxh2,"domain.hwmxDomain.fhrMc":fhrMc2,"domain.hwmxDomain.fhrDz":fhrDz,"domain.hwmxDomain.fhrLxr":fhrLxr,"domain.hwmxDomain.fhrLxdh":fhrLxdh,
	    				   "domain.hwmxDomain.shrDjxh":shrDjxh,"domain.hwmxDomain.shrMc":shrMc,"domain.hwmxDomain.shrDz":shrDz,"domain.hwmxDomain.shrLxr":shrLxr,"domain.hwmxDomain.shrLxdh":shrLxdh,
	    				   "domain.hwmxDomain.yqFhrq":yqFhrq,"domain.hwmxDomain.yqDdrq":yqDdrq,"domain.hwmxDomain.shfsDm":shfsDm,"domain.hwmxDomain.thflDm":thflDm,
	    				   "domain.hwmxDomain.yjjsfsDm":yjjsfsDm,"domain.hwmxDomain.ykjsfsDm":ykjsfsDm,"domain.hwmxDomain.srHj":srHj,"domain.hwmxDomain.srXf":srXf,
	    		           "domain.hwmxDomain.srHdf":srHdf,"domain.hwmxDomain.srThf":srThf,"domain.hwmxDomain.srYj":srYj,"domain.hwmxDomain.srHf":srHf,"domain.hwmxDomain.srHk":srHk,
	    		           "domain.hwmxDomain.srYsf":srYsf,"domain.hwmxDomain.srPsf":srPsf,"domain.hwmxDomain.srBjf":srBjf,"domain.hwmxDomain.fySmjz":fySmjz,
	    		           "domain.hwmxDomain.fyDshk":fyDshk,"domain.hwmxDomain.hwhh":hwhh,"domain.hwmxDomain.sfhdBz":sfhdBz};   			
	    	showMessage();
	    	ajaxCommon(url,jsonObj,"saveHwSuc");
		}
		function srConfirmYes() {
			var yqFhrq = $("#mainForm_domain_yqFhrq").val(); 
			var yqDdrq = $("#mainForm_domain_yqDdrq").val();
			var dStr = getNow(false); 
			if ((yqFhrq != "" && yqFhrq < dStr) || (yqDdrq != "" && yqDdrq < dStr)) {
				showConfirm("��Ҫ�󷢻����ڡ���Ҫ�󵽻����ڡ�С�ڵ�ǰ���ڣ��Ƿ�ȷ�ϱ��棿", "doSaveHw");
			}else {
				doSaveHw();
			}
		}
		
		function checkSr() {
			/*if (($("#mainForm_domain_srXf").val()/1+$("#mainForm_domain_srHdf").val()/1+$("#mainForm_domain_srThf").val()/1+$("#mainForm_domain_srYj").val()/1) != $("#mainForm_domain_srHj").val()/1) {
				alert("����¼���������������㹫ʽ��\"����=�ָ�\+����\+�½�\"�����飡");
				return false;
			}*/
			return true;
		}
	
	function checkJsJldwFlDm() {
		var jsJldwFlDm = $("#jsJldwFlDm").val();

		var srHj = $("#mainForm_domain_srHj").val()/1;
		if(jsJldwFlDm==""||jsJldwFlDm==null){
		 	$("#jsJldwFlDm").val("01");
		 	jsJldwFlDm="01";
		}
		
		var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val()); 
		var hwSlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwSlJldwDm").val()); 
		if ("01" == jsJldwFlDm && ("" == hwSlJldwDm || hwSl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwSl");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
			return false;
		}
		
		var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
		var hwZlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwZlJldwDm").val()); 
		if ("02" == jsJldwFlDm && ("" == hwZlJldwDm || hwZl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwZl");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
			return false;
		}
		
		var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
		var hwTjJldwDm = trim($("#mainForm_domain_hwmxDomain_hwTjJldwDm").val());
		if ("03" == jsJldwFlDm && ("" == hwTjJldwDm || hwTj/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwTj");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
			return false;
		}
		return true;
	}
	function checkXtcs20014(){
	    var xtcs20014 = $("#mainForm_domain_xtcs20014").val();
	    var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val());
	    var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
		var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
		if(hwSl/1<=0){
	        showAlert("����������㣡");
	        return false;
        }
	    if("Y"==xtcs20014){
        	if(hwZl/1<=0||hwTj/1<=0){
		        showAlert("���������͡�������������0��");
		        return false;
       		}
	    }else {
	    	if (hwZl/1<=0 && hwTj/1<=0) {
	    		showAlert("���������͡����������ͬʱΪ0����������¼������һ�");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	function checkLxfs() {
		var fhrLxr = $("#mainForm_domain_fhrLxr").val();
		var fhrLxdh = $("#mainForm_domain_fhrLxdh").val();
		if (fhrLxr == "" && fhrLxdh == "") {
			showAlert("����������ϵ�ˡ��͡���������ϵ�绰������ͬʱΪ�գ���������¼������һ�");
			return false;
		}
		
		var shrLxr = $("#mainForm_domain_shrLxr").val();
		var shrLxdh = $("#mainForm_domain_shrLxdh").val();
		/*if (shrLxr == "" && shrLxdh == "") {
			showAlert("���ջ�����ϵ�ˡ��͡��ջ�����ϵ�绰������ͬʱΪ�գ���������¼������һ�");
			return false;
		}*/
		if (shrLxdh == "") {
			showAlert("���ջ�����ϵ�绰������Ϊ�գ�");
			return false;
		}
		return true;
	}
	function saveHwSuc(data) {
		hideMessage();
		emptyForm();
		var tempFlag = data.domain.hwmxDomain.tempFlag;
		if ("Y" == tempFlag) {
			$("#mainForm_domain_hwmxDomain_ddDjxh").val(data.domain.hwmxDomain.ddDjxh);
			$("#mainForm_domain_ddDjxh", document).val(data.domain.hwmxDomain.ddDjxh);
			refreshHwList();
		}else {
			refreshHwList();
		}
		if(saveTydFlag==0){
		   if ("Y" != tempFlag) {
				showAlert("�޸ĳɹ�!","doFinish");
			}else{
				doFinish();
			}
		}else{
			doFinish();
		}
		
	}
	function doFinish() {
		if(saveTydFlag==1){
			saveData();
		}
	}
	
	function checkHwData(){
		var controlNameArray = ["domain.fhrXzqhDm","domain.shrXzqhDm",
		                        "domain.fhrMc","domain.fhrDz","domain.fhrLxr","domain.fhrLxdh",
		                        "domain.shrMc","domain.shrDz","domain.shrLxr","domain.shrLxdh",
		                        "domain.yqFhrq","domain.yqDdrq",
		                        "domain.srHj","domain.srXf",
		                        "domain.srHdf","domain.srThf","domain.srHf","domain.srYj","domain.srHk",
		                        "domain.hwmxDomain.hwmc","domain.hwmxDomain.hwBzHldwDm","domain.hwmxDomain.hwSl","domain.hwmxDomain.hwSlJldwDm",
		                        "domain.hwmxDomain.hwZl","domain.hwmxDomain.hwZlJldwDm","domain.hwmxDomain.hwTj","domain.hwmxDomain.hwTjJldwDm",
		                        "domain.hwmxDomain.srYsf","domain.hwmxDomain.srPsf","domain.hwmxDomain.srBjf","domain.hwmxDomain.fySmjz","domain.hwmxDomain.fyDshk","domain.hwmxDomain.hwhh"];
		var labelNameArray = ["ʼ����","Ŀ�ĵ�",
		                      "�ͻ�����","������ַ","��������ϵ��","��������ϵ�绰",
		                      "�ջ���λ","�ջ���ַ","�ջ�����ϵ��","�ջ�����ϵ�绰",
		                      "��������","��������",
		                      "������","�ָ�",
		                      "������","�����","�ص���","�½�","�ؿ�",
		                      "��������","��װ","����","����_������λ","����",
		                      "����_������λ","���","���_������λ",
		                      "�����","���ͷ�","���۷�","������ֵ","���ջ���","����"];
		var compareValueArray = [20,20,
			                     100,100,40,100,
			                     100,100,40,100,
			                     11,11,
			                     14.2,14.2,
			                     14.2,14.2,14.2,14.2,14.2,
		                         100,6,12.2,6,12.2,
			                     6,12.2,6,
			                     14.2,14.2,14.2,14.2,14.2,20];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,
			                 NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
		                     NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,
		                     NodeType.DECIMAL,NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,
		                     NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,true,
		                    true,false,false,false,
                            false,false,false,false,
                            false,false,
                            true,true,
                            true,true,true,true,true,
		                    true,false,false,false,false,
                            false,false,false,
                            false,false,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkHdbh() {
		var hdbh = $("#mainForm_domain_hwmxDomain_hdbh").val();
		var str = hdbh.match(/^[0-9a-zA-Z\,\uff0c]*$/);
		if (str == null) {
			objForFocus = $("#mainForm_domain_hdbh");
			showAlert("�ص����¼�����󣬻ص����ֻ�ܰ������ֺ���ĸ������ص������\",\"�ָ","focusSel");
			return false;
		}
		return true;
	}
	
	//������ҵ�Ļ���������λ��ʼ�� ����ļ�����λ
	function initJldw() {
		var xh = $("#mainForm_domain_hwmxDomain_xh").val();
		if (xh == "") {
			$("#mainForm_domain_hwmxDomain_hwBzHldwDm").val($("#mainForm_domain_hwmxDomain_qyHwBzJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwSlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwZlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwTjJldwDm").val());
		}
	}
	
	//��ʼ�����������λ�������
	function initJsJldwDm() {
		var jsJldwFlDm = $("#mainForm_domain_hwmxDomain_jsJldwFlDm").val();
		 $("#jsJldwFlDm").val(jsJldwFlDm);
	}
	
	function initHwflDm() {
		var hwflDm = $("#mainForm_domain_hwmxDomain_hwflDm").val();
		if (hwflDm == '2') {
			$("[name='hwflDm']")[1].checked = true;
		}else {
			$("[name='hwflDm']")[0].checked = true;
		}
	}
	/***************************************        hwmx     *******************************************************/