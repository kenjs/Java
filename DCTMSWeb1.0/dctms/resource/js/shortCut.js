	document.onkeydown = function(evt){
		var e = evt||window.event;
		if(e.ctrlKey&&String.fromCharCode(e.keyCode)=='S'){
			if(typeof saveBtn=='function' ){
				saveBtn();
			}else if(typeof getSubmit=='function'){
				queryInfoInit();
			}
		}
		if(e.ctrlKey&&String.fromCharCode(e.keyCode)=='A'){
			if(typeof addInfo=='function')
			addInfo();
		}
		if(e.ctrlKey&&String.fromCharCode(e.keyCode)=='W'){
			window.close();
		}
	}
	 
	
