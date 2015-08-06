	function onFocuesg(e){
		//alert("ss");
			var toPartyId = $("#frompartyid").attr("myid");
			input_onfocus(e);//waybill_add.js,修改边框颜色的
			$(consigneeFlag).remove();
			consigneeFlag="";
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
	                                url: "../waybillcs/selectLinkname",  
	                                data:"&topartyid="+toPartyId+"&consigneeorconsignor=1&consignee="+wordText,  
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
	                                        data=eval(data);
	                                        $.each(data,function(i,item){  
	                                            var newDivNode=$("<div>").attr({"id":i,"myid":data[i].consigneeConsignorAddressId});
	                                            var city = data[i].consignee;  
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
	                        	                    consigneelinkname(concomTextTag);
	                        	                    wordInput.next().val(concomTextTag)
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
	                    consigneelinkname(concomTextTag);
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
