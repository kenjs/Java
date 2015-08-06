/**********************初始化始发地，目的地autocomplete下拉 开始*******************/
	function initXzqhData(width, targetObjName, targetDmObjName){
		width = width == undefined ? 0 : width;
		var url = jcontextPath+"/hygl/hycommondown!queryXzqhList";	
		targetObjName = targetObjName == undefined ? "fhrXzqhMc" : targetObjName;
		targetDmObjName = targetDmObjName == undefined ? "fhrXzqhDm" : targetDmObjName;
		var dropDownSelectedCallback="onDropDownSelected";
		var width = width;
		var jsonObj = {"targetObjName":"domain."+targetObjName,"targetDmObjName":"domain."+targetDmObjName,"itemIndex":0,
					"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":true,"width":width};
		initData(url,jsonObj);
	}
	
	function onDropDownSelected(li, itemIndex, obj, targetDmObjName) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_jsonData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xzqhQc){
	        		$(obj).val(item.xzqhJc);
	        		$("[name='"+targetDmObjName+"']").val(item.xzqhDm);
		        	return;
	        	}
	    	});
		}
	}
	
	function onDropDownSelected4Shr(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_jsonData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xzqhQc){
	        		$("#mainForm_domain_shrXzqhMc").val(item.xzqhJc);
	        		$("#mainForm_domain_shrXzqhDm").val(item.xzqhDm);
		        	return;
	        	}
	    	});
		}
	   	
	   	$("#mainForm_domain_shrXzqhMc").focus();
	   	$("#mainForm_domain_shrXzqhMc").select();
	}
	/**********************初始化始发地，目的地autocomplete下拉 结束*******************/
	
	//重写初始化成功方法，以实现页面有多个这样的下拉时的区分
	function doInitDataSuc(data) {
		if (data.jsonDataName == undefined || data.jsonDataName == "") {
			$("[name='jsonData']").val(data.jsonData);
		}else {
			$("[name='"+data.jsonDataName+"']").val(data.jsonData);
		}
		setAutoDropDown(data.dropDownData,data.targetObjName,data.targetDmObjName,data.itemIndex,data.dropDownSelectedCallback,data.isCleanText,data.width);
		if (data.jsonDataName == undefined || data.jsonDataName == "") {
			setAutoDropDown(data.dropDownData,"domain.shrXzqhMc","domain.shrXzqhDm",data.itemIndex,"onDropDownSelected4Shr",true,data.width);
		}
		
		if (data.inputSelInitFun != "" && typeof(eval(data.inputSelInitFun)) =="function") {
			eval(data.inputSelInitFun+"(data);");
		}
		
	}
	
	/**********************初始化客户名称autocomplete下拉 开始*******************/
	var tagetJsonDateName="";
	var tagetName="";
	var tagetDmId="";
	function initHykhData(width, ssJgbm, djJgbm,jsonDataName,targetObjName,targetDmObjName){
		width = width == undefined ? 0 : width;
		ssJgbm = ssJgbm == undefined ? "" : ssJgbm;
		djJgbm = djJgbm == undefined ? "" : djJgbm;
		var url = jcontextPath+"/hygl/hycommondown!queryKhmcList";
		tagetJsonDateName = jsonDataName == undefined ? "fhrData" : jsonDataName;
		tagetName = targetObjName==undefined ? "fhrMc" : targetObjName;
		tagetDmId = targetDmObjName ==undefined ? "fhrDjxh" : targetDmObjName;
		var dropDownSelectedCallback="onDropDownSelected4Fhr";
		var inputSelInitFun = "initFhrInputSel";
		var t_name="domain."+tagetName;
		var t_id="domain."+tagetDmId
		var jsonObj = {"targetObjName":t_name,"targetDmObjName":t_id,"itemIndex":0,"jsonDataName":tagetJsonDateName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,"width":width,"inputSelInitFun":inputSelInitFun,
				"domain.khDomain.ssJgbm":ssJgbm,"domain.khDomain.djJgbm":djJgbm};
		initData(url,jsonObj);
	}
	
	function initFhrInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_fhr").empty();
		
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){			
			var li = $("<li onclick=\"onInputSelFhr(this);\" onmouseover=\"liMouseover(this, 'inputSel_fhr')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_fhr"));
	}
	
	function onDropDownSelected4Fhr(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#"+"mainForm_"+tagetJsonDateName).val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	    	try {
	        $(data).each(function(i,item){
	        	if(sValue==item.fhrMc){
	        		var b_flag=false;
	        		if($("#mainForm_domain_khlxDm4js").length>0)
        				b_flag=true;
	        		if(b_flag){
        				var l = $("#hwTbody tr").length;
        				if(l>0){
        					throw ("请先删除货物再修改客户！");
        				}
    	        			$("#mainForm_domain_khlxDm4js").val(item.khlxDm4js);
    		        		$("#mainForm_domain_ykjsfsDm4js").val(item.ykjsfsDm4js);
    		        		$("#mainForm_domain_fhrDjxh2").val(item.fhrDjxh);
    		    		    $("#mainForm_domain_fhrMc2").val(item.fhrMc);
    		    		    doEmptyWhenChange(1);//参数1表示改 0表示初始化
	        		}
	        		$("#mainForm_domain_"+tagetDmId).val(item.fhrDjxh);
	        		if($("[name='domain.fhrDz']").length>0){
	        			//去掉之前绑定的事件
	        			$("[name='domain.fhrDz']").unbind();
	        			initHyZhdzData(item.fhrDjxh, 300);
	        		}
	        		if($("[name='domain.shrMc']").length>0){
	        			//去掉之前绑定的事件
	        			$("[name='domain.shrMc']").unbind();
	        			initHyShDwData(item.fhrDjxh, 300);
	        		}
		        	return false;
	        	}
	    	});
	    	} catch(e) {
	    		showAlert(e);
	    		$("#mainForm_domain_fhrMc").val($("#mainForm_domain_fhrMc2").val());
	    	}
	    	
	    	// 重新聚焦
	    	$("#mainForm_domain_"+tagetName).focus();
	    	$("#mainForm_domain_"+tagetName).select();
		}
	}
	
	function onInputSelFhr(obj) {
		var sValue = $(obj).text();
		$(".inputselcont").hide();//.slideUp("fast");
		$(".inputsc").hide();
		$("#mainForm_domain_"+tagetName).val(sValue);
		
		onDropDownSelected4Fhr(createLi4InputSel(sValue));
	}
	/**********************初始化客户名称autocomplete下拉 结束*******************/
	
	/**********************初始化发货地址autocomplete下拉 结束*******************/
	function initHyZhdzData(khDjxh, width){
		var url = jcontextPath+"/hygl/hycommondown!queryZhdzList";
		var jsonDataName="zhdzData";
		var targetObjName="domain.fhrLxr";
		var targetDmObjName="domain.zhdzDjxh";
		var dropDownSelectedCallback="onDropDownSelected4Zhdz";
		var inputSelInitFun = "initFhrDzInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,"width":width,"inputSelInitFun":inputSelInitFun,
				"domain.khDjxh":khDjxh};
		initData(url,jsonObj);
	}
	
	function initFhrDzInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_fhrLxr").empty();
		
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			if (i == 0) {
				onDropDownSelected4Zhdz(createLi4InputSel(obj.substring(0,obj.indexOf("^"))));
 			 }
			var li = $("<li onclick=\"onInputSelFhrDz(this);\" onmouseover=\"liMouseover(this, 'inputSel_fhrLxr')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_fhrLxr"));
	}
	
	function onDropDownSelected4Zhdz(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_zhdzData").val();
	   	if("[]"!=jsonStr){
	   		var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xsdz){
	        		$("#mainForm_domain_fhrDz").val(item.fhrDz == "null" ? "" : item.fhrDz);
	        		$("#mainForm_domain_fhrXzqhDm").val(item.xzqhDm == "null" ? "" : item.xzqhDm);
	        		$("#mainForm_domain_fhrXzqhMc").val(item.xzqhMc == "null" ? "" : item.xzqhMc);
	        		$("#mainForm_domain_fhrLxr").val(item.fhrLxr == "null" ? "" : item.fhrLxr);
	        		$("#mainForm_domain_fhrLxdh").val(item.fhrLxdh == "null" ? "" : item.fhrLxdh);
		        	return;
	        	}
	    	});
		}
	   	
	   	//$("#mainForm_domain_fhrLxr").focus();
	   	//$("#mainForm_domain_fhrLxr").select();
	}
	
	function onInputSelFhrDz(obj) {
		var sValue = $(obj).text();
		$(".inputselcont").hide()//.slideUp("fast");
		$(".inputsc").hide();
		onDropDownSelected4Zhdz(createLi4InputSel(sValue));
	}
	/**********************初始化发货地址autocomplete下拉 结束*******************/
	
	/**********************初始化收货单位autocomplete下拉 结束*******************/
	function initHyShDwData(khDjxh, width){
		var url = jcontextPath+"/hygl/hycommondown!queryShdwList";
		var jsonDataName="shdwData";
		var targetObjName="domain.shrMc";
		var targetDmObjName="domain.shrDjxh";
		var dropDownSelectedCallback="onDropDownSelected4Shdwmc";
		var inputSelInitFun = "initShDwInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,"width":width,"inputSelInitFun":inputSelInitFun,
				"domain.khDjxh":khDjxh};
		initData(url,jsonObj);
		
	}
	
	function initShDwInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_shrLxr").empty();
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			var li = $("<li onclick=\"onInputSelShDw(this);\" onmouseover=\"liMouseover(this, 'inputSel_shrLxr')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_shrLxr"));
	}
	
	function onDropDownSelected4Shdwmc(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_shdwData").val();
	   	if("[]"!=jsonStr){
	   		var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xsdz){
	        		$("#mainForm_domain_shrDjxh").val(item.shdzDjxh);
	        		$("#mainForm_domain_shrMc").val(item.shrMc == "null" ? "" : item.shrMc);
	        		$("#mainForm_domain_shrDz").val(item.shrDz == "null" ? "" : item.shrDz);
	        		$("#mainForm_domain_shrXzqhDm").val(item.xzqhDm == "null" ? "" : item.xzqhDm);
	        		$("#mainForm_domain_shrXzqhMc").val(item.xzqhMc == "null" ? "" : item.xzqhMc);
	        		$("#mainForm_domain_shrLxr").val(item.shrLxr == "null" ? "" : item.shrLxr);
	        		$("#mainForm_domain_shrLxdh").val(item.shrLxdh == "null" ? "" : item.shrLxdh);
		        	return;
	        	}
	    	});
		}
	   	
	   	$("#inputSel_shrLxr").focus();
	   	$("#inputSel_shrLxr").select();
	}
	
	function onInputSelShDw(obj) {
		var sValue = $(obj).text();
		$(".inputselcont").hide()//.slideUp("fast");
		$(".inputsc").hide();
		onDropDownSelected4Shdwmc(createLi4InputSel(sValue));
	}
	/**********************初始化收货单位autocomplete下拉 结束*******************/
	
	function initXzqhInputSel() {
		var url = jcontextPath+"/hygl/hycommondown!queryXzqhInputSel";
		var jsonObj = {};
		ajaxCommon(url, jsonObj,"initXzqhInputSelSuc");
	}

	function initXzqhInputSelSuc(data) {
		var ul = '<ul class="inputseltab">' +
                  '<li id="area1" onClick="setTab(\'area\',1,3)" class="current">省份</li>' +
                  '<li id="area2" onClick="setTab(\'area\',2,3)">城市</li>' +
                  '<li id="area3" onClick="setTab(\'area\',3,3)">县城</li>' +
                '</ul>';
		$("#inputSel_xzqh").html(ul + data.domain.xzqhInputSelHtml + $("#inputSel_xzqh").html());
		//setTab('area',1,7);
	}
	
	/**********************初始化货物名称autocomplete下拉 开始*******************/
	function initHyhwData(width,khDjxh){
		width = width == undefined ? 0 : width;
		var url = jcontextPath+"/hygl/hycommondown!queryHwmcList";	
		var jsonDataName="hwmcData";
		var targetObjName="domain.hwmxDomain.hwmc";
		var targetDmObjName="domain.hwmxDomain.hwDjxh";
		var dropDownSelectedCallback="onDropDownSelected4Hwmc";
		var inputSelInitFun = "initHwmcInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,"domain.khDjxh":khDjxh,
				"width":width,"inputSelInitFun":inputSelInitFun};
		initData(url,jsonObj);
	}
	
	function initHwmcInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_hwmc").empty();
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			var li = $("<li onclick=\"onInputSelHwmc(this, '"+data.targetObjName+"');\" onmouseover=\"liMouseover(this, 'inputSel_hwmc')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_hwmc"));
	}
	
	function onDropDownSelected4Hwmc(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_hwmcData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.hwmc){
	        		$("#mainForm_domain_hwmxDomain_hwDjxh").val(item.hwDjxh);
	        		if (item.hwxhDjxh != null && item.hwxhDjxh != "null") {
	        			$("#mainForm_domain_hwmxDomain_hwxhDjxh").val(item.hwxhDjxh);
	        		}	        		
	        		$("#mainForm_domain_hwmxDomain_hwBzHldwDm").val(item.bzJldwDm);
	        		$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val(item.slJldwDm);
	        		$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val(item.zlJldwDm);
	        		$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val(item.tjJldwDm);
	        		if (item.jsJldwDm != null && item.jsJldwDm != "null") {
	        			jsJldwFlDm = item.jsJldwDm.substring(0, 2);
	        			$("#mainForm_domain_hwmxDomain_jsJldwFlDm").val(jsJldwFlDm);
	        			initJsJldwDm();
	        			
	        			if ("02" == jsJldwFlDm) {
	        				$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val(item.jsJldwDm);
	        			}else if ("03" == jsJldwFlDm) {
	        				$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val(item.jsJldwDm);
	        			}else {
	        				$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val(item.jsJldwDm);
	        			}
	        		}
		        	return;
	        	}
	    	});
	        
	        $("#mainForm_domain_hwmxDomain_hwmc").focus();
	        $("#mainForm_domain_hwmxDomain_hwmc").select();
	    }
	}
	
	function onInputSelHwmc(obj, hwmcName) {
		var sValue = $(obj).text();
		$(".inputselcont").hide();//.slideUp("fast");
		$(".inputsc").hide();
		$("[name='"+hwmcName+"']").val(sValue);
		
		onDropDownSelected4Hwmc(createLi4InputSel(sValue));
	}
	
	/**********************初始化货物名称autocomplete下拉 结束*******************/

	/**********************初始化车辆信息autocomplete下拉 开始*******************/
	function initClxxData(width, targetObjName, targetDmObjName, clsxDm, pcfsDm){
		width = width == undefined ? 0 : width;
		clsxDm = clsxDm == undefined ? "" : clsxDm;
		pcfsDm = pcfsDm == undefined ? "" : pcfsDm;
		var url = jcontextPath+"/hygl/hycommondown!queryQyClxx";
		var jsonDataName="clxxData";
		var dropDownSelectedCallback="onDropDownSelected4Clxx";
		var inputSelInitFun = "initClxxInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,
				"width":width,"inputSelInitFun":inputSelInitFun, "domain.clxxDomain.clsxDm":clsxDm,"domain.clxxDomain.pcfsDm":pcfsDm};
		initData(url,jsonObj);
	}
	
	function initClxxInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_clxx").empty();
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			var li = $("<li onclick=\"onInputSelClxx(this, '"+data.targetObjName+"');\" onmouseover=\"liMouseover(this, 'inputSel_clxx')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_clxx"));
	}
	
	function onDropDownSelected4Clxx(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_clxxData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xshm){
	 		        $("#mainForm_domain_cyrClhmXh").val(item.clDjxh);
	 		        $("#mainForm_domain_cyrClhm").val(item.clhm);
        		    $("#mainForm_domain_cyrCzxm").val(item.czXm);
        		    $("#mainForm_domain_cyrSjxm").val(item.sjXm);
        		    $("#mainForm_domain_cyrSjsfz").val(item.sjZjhm);
        		    $("#mainForm_domain_cyrSjsjhm").val(item.sjSjhm);
        		    $("#mainForm_domain_cyrQtlxdh").val(item.sjLxdh);
		        	return;
	        	}
	    	});
	    }
	}
	
	function onInputSelClxx(obj, targetObjName) {
		var sValue = $(obj).text();
		$(".inputselcont").hide();//.slideUp("fast");
		$(".inputsc").hide();
		$("[name='"+targetObjName+"']").val(sValue);
		
		onDropDownSelected4Clxx(createLi4InputSel(sValue));
	}
	
	/**********************初始化车辆信息autocomplete下拉 结束*******************/
	
	/**********************初始化挂车号码autocomplete下拉 开始*******************/
	function initGcxxData(width, targetObjName, targetDmObjName, clsxDm, pcfsDm){
		width = width == undefined ? 0 : width;
		clsxDm = clsxDm == undefined ? "" : clsxDm;
		pcfsDm = pcfsDm == undefined ? "" : pcfsDm;
		var url = jcontextPath+"/hygl/hycommondown!queryQyGcxx";
		var jsonDataName="gcxxData";
		var dropDownSelectedCallback="";
		var inputSelInitFun = "initGcxxInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,
				"width":width,"inputSelInitFun":inputSelInitFun, "domain.clxxDomain.clsxDm":clsxDm,"domain.clxxDomain.pcfsDm":pcfsDm};
		initData(url,jsonObj);
	}
	
	function initGcxxInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_gcxx").empty();
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			var li = $("<li onclick=\"onInputSelGcxx(this, '"+data.targetObjName+"');\" onmouseover=\"liMouseover(this, 'inputSel_gcxx')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_gcxx"));
	}
	
	function onInputSelGcxx(obj, targetObjName) {
		var sValue = $(obj).text();
		$(".inputselcont").hide();//.slideUp("fast");
		$(".inputsc").hide();
		$("[name='"+targetObjName+"']").val(sValue);
	}
	
	/**********************初始化挂车号码autocomplete下拉 结束*******************/
	
	/**********************初始化企业提货收货地址autocomplete下拉 结束*******************/
	function initQyThShdzData(zrbmDjxh,tableName, width,targetObjName,targetDmObjName){
		var url = jcontextPath+"/hygl/hycommondown!queryZrbmThShdz";
		var jsonDataName="qyThShdzData";
		targetObjName = "domain." + targetObjName;
		targetDmObjName = "domain." + targetDmObjName;
		var dropDownSelectedCallback="onDropDownSelected4QyThShdz";
		var inputSelInitFun = "initQyThShdzInputSel";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"jsonDataName":jsonDataName,
				"dropDownSelectedCallback":dropDownSelectedCallback,"isCleanText":false,"width":width,"inputSelInitFun":inputSelInitFun,
				"domain.thShdzDomain.zrbmDjxh":zrbmDjxh,"domain.thShdzDomain.tableName":tableName};
		initData(url,jsonObj);
	}
	
	function initQyThShdzInputSel(data) {
		var dataArray = eval("["+(data.dropDownData)+"]");
		$("#inputSel_qyThShdz").empty();
		
		var ul = $("<ul></ul>")
		$.each(dataArray, function(i, obj){
			if (i == 0) {
				onDropDownSelected4QyThShdz(createLi4InputSel(obj.substring(0,obj.indexOf("^"))));
			}
			var li = $("<li onclick=\"onInputSelQyThShdz(this);\" onmouseover=\"liMouseover(this, 'inputSel_qyThShdz')\"></li>");
			$(li).text(obj.substring(0,obj.indexOf("^")));
			$(li).appendTo($(ul));
		});
		$(ul).appendTo($("#inputSel_qyThShdz"));
	}
	
	function onDropDownSelected4QyThShdz(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_qyThShdzData").val();
	   	if("[]"!=jsonStr){
	   		var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xsDz){
	        		initQyThShdzDataFromItem(item);
		        	return;
	        	}
	    	});
		}
	}
	
	function onInputSelQyThShdz(obj) {
		var sValue = $(obj).text();
		$(".inputselcont").hide()//.slideUp("fast");
		$(".inputsc").hide();
		onDropDownSelected4QyThShdz(createLi4InputSel(sValue));
	}
	/**********************初始化企业提货收货地址autocomplete下拉 结束*******************/
	
	function liMouseover(obj, parId) {
		$("#"+parId+" li").removeClass("ac_over");
		$(obj).addClass("ac_over");
	}
	
	function createLi4InputSel(sValue) {
		var li = document.createElement("li");
		li.selectValue = sValue
		return li;
	}