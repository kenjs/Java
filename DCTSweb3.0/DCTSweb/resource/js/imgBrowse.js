//说明：图片上传预览插件
//上传的时候可以生成固定宽高范围内的等比例缩放图

//参数设置：
//width                     存放图片固定大小容器的宽
//height                    存放图片固定大小容器的高
//imgDiv                    页面DIV的JQuery的id
//maxSize					图片大小最大限制(K)
//imgType                   数组后缀名
//**********************图片上传预览插件*************************
(function($) {
    jQuery.fn.extend({
        uploadPreview: function(opts) {
            opts = jQuery.extend({
                width: 0,
                height: 0,
                imgDiv: "#imgDiv",
                maxSize:300,
                imgType: ["gif", "jpeg", "jpg", "bmp", "png"],
                callback: function() { return false; }
            }, opts || {});
            //var _self = this;
            var _this = $(this);
            var imgDiv = $(opts.imgDiv);
            imgDiv.width(opts.width);
            imgDiv.height(opts.height);
             
            var version = parseInt($.browser.version,10);
            
            autoScaling = function() {
            	
                if (version == 7 || version == 8  || version == 9) imgDiv.get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = "image";
                var img_width = imgDiv.width();
                var img_height = imgDiv.height();
                if (img_width > 0 && img_height > 0) {
                    var rate = (opts.width / img_width < opts.height / img_height) ? opts.width / img_width : opts.height / img_height;
                    if (rate <= 1) {
                        if (version == 7 || version == 8  || version == 9) imgDiv.get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = "scale";
                        imgDiv.width(img_width * rate);
                        imgDiv.height(img_height * rate);
                    } else {
                        imgDiv.width(img_width);
                        imgDiv.height(img_height);
                    }
                    var left = (opts.width - imgDiv.width()) * 0.5;
                    var top = (opts.height - imgDiv.height()) * 0.5;
                    imgDiv.css({ "margin-left": left, "margin-top": top });
                    imgDiv.show();
                }
            },
            
            createImg = function(){
        		imgDiv.html('');
        		
                var img = $("<img />");
                imgDiv.replaceWith(img);
                imgDiv = img;
            },
   
            _this.change(function() {
            	
                if (this.value) {
                    if (!RegExp("\.(" + opts.imgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                        alert("图片类型必须是" + opts.imgType.join("，") + "中的一种");
                        this.value = "";
                        return false;
                    }
                    imgDiv.hide();
                    if ($.browser.msie && version < 10) {
                    	
                        if (version == 6) {
                        	
                            var image = new Image();
                            image.onload = function(){
                                if( (image.fileSize/1024) > opts.maxSize) {
                                	alert('图片大小不能超过'+opts.maxSize+'K');
                                	return false;
                                }
                            }
                            image.src = 'file:///' + this.value;

                            createImg();
                            imgDiv.attr('src', image.src);
                            autoScaling();
                        }  else {
                        	
                        	this.select();
                        	var img = document.selection.createRange().text;
                            var image = new Image();
                            image.onload = function(){
                                if( (image.fileSize/1024) > opts.maxSize) {
                                	alert('图片大小不能超过'+opts.maxSize+'K');
                                	return false;
                                }
                            }
                            image.src = img;                        	
                            
                        	imgDiv.html('');                        	
                            imgDiv.css({ filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image)" });
                            imgDiv.get(0).filters.item("DXImageTransform.Microsoft.AlphaImageLoader").sizingMethod = "image";                           
                            
                            try {
                                imgDiv.get(0).filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = img;
                            } catch (e) {
                                alert("无效的图片文件！");
                                return;
                            }                            
                            
                            setTimeout("autoScaling()", 100);                            
                        }
                    }
                    else {
                    	try{   
                    		var file = null;
                    		var size = 0;
                    		if(this.files && this.files[0] ){
                    			file = this.files[0]; 
                    			size = file.size;
                    		}else if(this.files && this.files.item(0)) {                    			
                    			file = this.files.item(0);   
                    			size = file.fileSize;
                    	    } 
 
                    		if((size/1024) > opts.maxSize){
                    			alert('图片大小不能超过'+opts.maxSize+'K');
                            	return false;
                			}
                			
                			createImg();
                    		
                    		//Firefox 因安全性问题已无法直接通过input[file].value 获取完整的文件路径
                    		try{
                    			//Firefox7.0 以下                    			
                    			imgDiv.attr('src', file.getAsDataURL());
                    		}catch(e){
                    			//Firefox8.0以上                    			
                    			imgDiv.attr('src', window.URL.createObjectURL(file));
                    		}
                    		
	                        imgDiv.css({ "vertical-align": "middle" });
	                        setTimeout("autoScaling()", 100);
                    	}catch(e){                    		
                    		//支持html5的浏览器,比如高版本的firefox、chrome、ie10
                    		if (this.files && this.files[0]) {                    		
                    			if((this.files[0].size/1024) > opts.maxSize){
                                	alert('图片大小不能超过'+opts.maxSize+'K');
                                	return false;
                    			}
                    			
                    	        var reader = new FileReader(); 
                    	        reader.onload = function (e) {                      	        	
                    	        	imgDiv.attr('src', e.target.result);  
                    	        };
                    	        reader.readAsDataURL(this.files[0]); 
                    	        setTimeout("autoScaling()", 100);
                    	    }  
                    	};
                    }
                }
            });
        }
    
   
    });
})(jQuery);


function previewImage(file,imgDivId){
	$("#imgDivId").val("");
  var MAXWIDTH  = 300;
  var MAXHEIGHT = 148;
  //var showId = $(file).prev().children().attr('id');
  var imgId = imgDivId + 'ImgShow';//$(file).prev().children().attr('id');
  var div = document.getElementById(imgDivId);
  if (file.files && file.files[0]){
    div.innerHTML = '<img id='+ imgId +' class="imgDiv">';
    var img = document.getElementById(imgId);
    img.onload = function(){
      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
      img.width = rect.width;
      img.height = rect.height;
      img.style.marginLeft = rect.left+'px';
      img.style.marginTop = rect.top+'px';
    }
    var reader = new FileReader();
    reader.onload = function(evt){img.src = evt.target.result;}
    reader.readAsDataURL(file.files[0]);
  }else{
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id='+ imgId +' class="imgDiv">';
    var img = document.getElementById(imgId);
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
  }
} 
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}      
