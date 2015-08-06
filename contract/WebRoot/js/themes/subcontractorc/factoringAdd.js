var topartyid = '';
function onFocus(e,partytype){
	//alert("ss");
	//input_onfocus(e);//waybill_add.js,修改边框颜色的
	clearborder(e);
	var id=$(e).attr("id");
	var id1='#'+id;
    //var wordInput = $("#topartyrealname");  
	var wordInput=$(id1);
    var wordInputOffset = wordInput.offset();  
      
    //给div设置显示时的背景色  
    $("#auto").css("background-color","white");  
      
    //自动补全框最开始应该隐藏起来  
    $("#auto").hide().css("border","1px #999999 solid").css("position","absolute")  
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")  
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);  
  
    //给文本框添加键盘按下并弹起的事件  
    wordInput.keyup  
    (  
    function(event)   
    {  
        //处理文本框中的键盘事件  
        var myEvent = event || window.event;  
        var keyCode = myEvent.keyCode;  
        //如果输入的是字母，应该将文本框中最新的信息发送给服务器  
        //如果输入的是退格键或删除键，也应该将文本框中的最新信息发送给服务器  
        //空格键为：32  
        if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46 || keyCode==32)   
        {  
            //1.首先获取文本框中的内容  
            var wordText = wordInput.val();
            var autoNode = $("#auto");
            $('#yhjgzh').val("");
			$('#zzjgdmz').val("");
			$("#hgrid tr:gt(0)").empty();
            if (wordText != "")   
            {  
                //清空上一次未开始执行的请求  
                clearTimeout(timeoutId);  
                //延迟 500毫秒 处理  
                timeoutId = setTimeout(function()  
                {  
                    //2.将文本框中的内容发送给服务器段  
                    $.ajax(  
                        {  
                            type: "POST",  
                            url: "../waybillcs/frompartyNameList",  
                            data: "organization="+wordText+"&partytype="+partytype,  
                            dataType:"json",  
                            success:function(data)  
                            {
//                              alert(result);  
                                //进行遍历,并且让div显示  
                                var s="";  
                                //需要清空原来的内容  
                                autoNode.html("");
                                if(data.Data!=null || data.length>0)  
                                {  
                                    $("#auto").show("slow");  
                                    $.each(data,function(i,item){  
                                        var newDivNode=$("<div>").attr({"id":i,"myid":data[i].partyid});
                                        var city = data[i].organization;  
//                                      alert(city.cname);  
                                        newDivNode.html(city).appendTo(autoNode);  
                                        //增加鼠标进入事件，高亮节点  
                                        newDivNode.mouseover  
                                        (  
                                        function(){  
                                            //将原来高亮的节点取消高亮  
                                            if(highlightindex != -1){  
                                                $("#auto").children("div").eq(highlightindex)  
                                                .css("background-color","white");  
                                            }  
                                            //纪录新的高亮索引  
                                            highlightindex = $(this).attr("id");  
                                            //鼠标进入的高亮节点  
                                            $(this).css("background-color","#6699CC");  
                                        });  
                                        //鼠标移出节点，取消高亮  
                                        newDivNode.mouseout(  
                                            function(){  
                                                $(this).css("background-color","white");//取消鼠标移出节点的高亮  
                                            }  
                                        );  
                                        //增加鼠标点击事件，可以进行点击  
                                        newDivNode.click(  
                                        function (){  
                                            var comText = $(this).text();//取出高亮节点的文本内容  
                                            $("#auto").hide("slow");  
                                            document.getElementById('auto').style.display='none';  
                                            highlightindex = -1;wordInput.val(comText);//文本框中的内容变成高亮节点的内容
                    	                    var concomTextTag=$(this).eq(highlightindex).attr("myid");
                    	                    highlightindex = -1;  
                    	                    //文本框中的内容变成高亮节点的内容  
                    	                    wordInput.attr("myid",concomTextTag);
                    	                    wordInput.next().val(concomTextTag);   
                    	                    topartyid = concomTextTag;
                    	                    
                    	                    $.ajax({
                    	                    	url:"../subcontractorcs/factoring_validate",
                    	                    	type:'post',
                    	                    	dataType:'json',
                    	                    	async:false,
                    	                    	data:{"topartyid":concomTextTag},
                    	                    	success:function(data){                    	                    		
                    	                    		if(data.msg=='ok'){
                    	                    			ymPrompt.alert({message:"该分包商已经加入银行保理！",handler:clearinput});                    	                    			
                    	                    			return;
                    	                    		}else{
                    	                    			$.ajax({
                                	        				url: "../subcontractorcs/factoringDetail_json",
                                	        			 	type:'post',	
                                	        			 	dataType:'json',                     	        			 
                                	        			 	data:{"topartyid":concomTextTag}, //参数                   
                                	        			   	success:function(data){      
                                	        			 		if(data!=null){
            		                    	        				$.each(data,function(k,obj){	                    	        					
            		                    	        					$('#yhjgzh').val(obj.yhjgzh);
            		                    	        					$('#zzjgdmz').val(obj.zzjgdmz);
            		                    	        				});}
            	                    	        				$.ajax({
            	                    	        					url: "../subcontractorcs/factoringDetailFhf_json",
            	                    	        				 	type:'post',	
            	                    	        				 	dataType:'json', 
            	                    	        				 	data:{"topartyid":concomTextTag}, //参数                   
            	                    	        				   	success:function(data){
            	                    	        				 		var txt = '';
            	                    	        				 		$("#hgrid tr:gt(0)").empty();
            		                    	        					$.each(data,function(k,obj){
            		                    	        						//alert(obj.frompartyname);
            		                    	        						txt = txt +
            		                    	        							'<tr>'+'<td>'+obj.frompartyname+'</td>'+
            		                    	        								'<td>'+'<input type="checkbox" name="isEnabled" style="margin-left:60px;" value="'+obj.frompartyid+'" />'+'</td>'+
            		                    	        								'<td>'+'<input type="text" name="businessDays" id="business_days_'+k+'" class="com_input" style="width:70px;" onfocus="clearborder(this)"/> '+'</td>'+
            		                    	        							'</tr>';
            		                    	        					});		                    	        					
            		                    	        					$(txt).insertAfter($("#hgrid tr:last"));
            	                    	        					}
            	                    	        				})
            	                    	        			}
            	                    	        		})
                    	                    		}
                    	                    	}
                    	                    });
                    	                    
                                        }  
                                        );  
                                          
                                    });  
                                }  
                                //如果服务器段有数据返回，则显示弹出框-------  
                                if (data!=null || data.length>0)   
                                {  
                                    autoNode.show("slow");  
                                } else   
                                {  
                                    autoNode.hide("slow");  
                                    //弹出框隐藏的同时，高亮节点索引值也制成-1  
                                    highlightindex = -1;  
                                }  
                            }  
                        }  
                    );  
                },timeMS);  
            } else {  
                //autoNode.hide();  
                //highlightindex = -1;  
            }  
        } else if (keyCode == 38 || keyCode == 40)   
        {  
            //如果输入的是向上38向下40按键  
            if (keyCode == 38)   
            {  
                //向上  
                var autoNodes = $("#auto").children("div")  
                if (highlightindex != -1)   
                {  
                    //如果原来存在高亮节点，则将背景色改称白色  
                    autoNodes.eq(highlightindex).css("background-color","white");  
                    highlightindex--;  
                } else   
                {  
                    highlightindex = autoNodes.length - 1;      
                }  
                if (highlightindex == -1)   
                {  
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素  
                    highlightindex = autoNodes.length - 1;  
                }  
                //让现在高亮的内容变成红色  
                autoNodes.eq(highlightindex).css("background-color","#6699CC");  
            }  
            if (keyCode == 40)   
            {  
                //向下  
                var autoNodes = $("#auto").children("div")  
                if (highlightindex != -1)   
                {  
                    //如果原来存在高亮节点，则将背景色改称白色  
                    autoNodes.eq(highlightindex).css("background-color","white");  
                }  
                highlightindex++;  
                if (highlightindex == autoNodes.length)   
                {  
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素  
                    highlightindex = 0;  
                }  
                //让现在高亮的内容变成红色  
                autoNodes.eq(highlightindex).css("background-color","#6699CC");  
            }  
        } else if (keyCode == 13)   
        {  
            //如果输入的是回车  
  
            //下拉框有高亮内容  
            if (highlightindex != -1)   
            {  
                //取出高亮节点的文本内容  
                var comText = $("#auto").hide("slow").children("div").eq(highlightindex).text();  
                var concomTextTag=$("#auto").hide("slow").children("div").eq(highlightindex).attr("myid");
                highlightindex = -1;  
                //文本框中的内容变成高亮节点的内容  
                $(id1).val(comText);  
                $(id1).attr("myid",concomTextTag);     
                topartyid = concomTextTag;
                $.ajax({
                	url:"../subcontractorcs/factoring_validate",
                	type:'post',
                	dataType:'json',
                	async:false,
                	data:{"topartyid":concomTextTag},
                	success:function(data){                    	                    		
                		if(data.msg=='ok'){
                			ymPrompt.alert({message:"该分包商已经加入银行保理！",handler:clearinput});                    	                    			
                			return;
                		}else{
                			$.ajax({
                				url: "../subcontractorcs/factoringDetail_json",
                			 	type:'post',	
                			 	dataType:'json', 
                			 	data:{"topartyid":concomTextTag}, //参数                   
                			   	success:function(data){
                			 		if(data!=null){
                			 			$.each(data,function(k,obj){
            	    						$('#yhjgzh').val(obj.yhjgzh);
            	    						$('#zzjgdmz').val(obj.zzjgdmz);
            	    					});
                			 		}
            	    				$.ajax({
            	    					url: "../subcontractorcs/factoringDetailFhf_json",
            	    				 	type:'post',	
            	    				 	dataType:'json', 
            	    				 	data:{"topartyid":concomTextTag}, //参数                   
            	    				   	success:function(data){
            	    				 		var txt = '';
                    				 		$("#hgrid tr:gt(0)").empty();
            	        					$.each(data,function(k,obj){
            	        						//alert(obj.frompartyname);	        				
            	        						txt = txt +
            	        							'<tr>'+'<td>'+obj.frompartyname+'</td>'+
            	        								'<td>'+'<input type="checkbox" name="isEnabled" style="margin-left:60px;" value="'+obj.frompartyid+'" />'+'</td>'+
            	        								'<td>'+'<input type="text" name="businessDays" id="business_days_'+k+'" class="com_input" style="width:70px;" value="" onfocus="clearborder(this)"/> '+'</td>'+
            	        							'</tr>';
            	        					});		         	      
            	        					$(txt).insertAfter($("#hgrid tr:last"));
            	    					}
            	    				})
            	    			}
                			})
                		}
                	}
                });
                
            } else   
            {  
                //下拉框没有高亮内容  
                //alert("文本框中的[" + $("#word").val() + "]被提交了");  
                $("#auto").hide("slow");  
                  
                $("#auto").get(0).blur();  
            }  
        }  
    });  
  
    //给按钮添加事件，表示文本框中的数据被提交  
    $("input[type='button']").click  
    (  
    function()   
    {  
        //alert("文本框中的[" + $("#word").val() + "]被提交了");  
    }  
    ); 
}
//表示当前高亮的节点  
var highlightindex = -1;  
//延迟请求对应timeout的id  
var timeoutId;  
//延迟请求的间隔时间  
var timeMS = 20;  

function do_save(){
	var checkboxobj = $("input[name=isEnabled]"),daysinput = $("input[name=businessDays]");
	var frompartyid = '',businessdays = '',isenabled='',jsonarra=[],jsonText='',
		bankcount = trim($("#yhjgzh").val()),organicount = trim($("#zzjgdmz").val()),
		bankfactoringid='',topartyidfbsid=trim($("#topartyidfbsid").val());
	
	if(topartyidfbsid==''||topartyidfbsid==null||topartyidfbsid==undefined){
		markText("#topartyidfbsid","不能为空");
		return false;
	}
	if(bankcount==''||bankcount==null||bankcount==undefined){
		markText("#yhjgzh","不能为空");
		return false;
	}
	if(organicount==''||organicount==null||organicount==undefined){
		markText("#zzjgdmz","不能为空");
		return false;
	}
	var flag = true;
	$(checkboxobj).each(function(i,o){
		isenabled='0';
		if($(this).attr("checked")) {
			isenabled = '1';
		}
		frompartyid = $(this).val();
		businessdays = trim($("#business_days_"+i).val());
		var reg = /^[0-9]*[1-9][0-9]*$/,　　//验证正整数 
			res = reg.test(businessdays);
		if((isenabled=='1') && ! res){
			markText("#business_days_"+i,"请输入账期");
			flag = false;
			return false;
		}
		var jsonstr = createObj(frompartyid,businessdays,isenabled);
		jsonarra.push(jsonstr);
	});
	if(!flag){
		return false;
	}
	jsonText = JSON.stringify(jsonarra);
	$.ajax({
		url:'../subcontractorcs/do_save',
		type:'post',
		dataType:'json',
		data:{topartyid:topartyid,jsonText:jsonText,bankcount:bankcount,organicount:organicount,bankfactoringid:bankfactoringid},
		success:function(data){
			if(data.msg=="ok"){
				ymPrompt.alert({message:"保存成功！",handler:f_close});				
			}else{
				ymPrompt.alert("保存失败！");
			}
		},
		error:function(){
			ymPrompt.alert("保存失败！");
		}
	});
}
function createObj(frompartyid,businessdays,isenabled){
	return {
		frompartyid:frompartyid,
		businessdays:businessdays,
		isenabled:isenabled
	}
}
function markText(targetId,message){
	var _targetId=targetId.substr(1);//去掉#
	if($(targetId+"_message").length==0){//当信息提示div不存在时
		var top=$(targetId).position().top+parseInt($(targetId).css("height"));
		var left=$(targetId).position().left;
		var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
		message+'</div>';
		$("body").append(txt);
	}else{//当信息提示div存在时
		$(targetId+"_message").empty().append(message);
	}
	$(targetId).css("border","2px solid red");
	return targetId+"_message";
}
function trim(str){
	 return str.replace(/(^\s*)|(\s*$)/g, "");
}
function f_close(){
	window.location.href="../subcontractorcs/factoringList?order=24";
}
function clearborder(obj){
	var id = $(obj).attr("id");
	$('#'+id+'_message').empty();
	$(obj).css('border','1px solid #A0CFE0');
}
function clearinput(){
	$('#topartyidfbsid').val('');
}