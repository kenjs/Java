var html;
$(function() {
		$.ajax({
				url : "../logincs/getInit?random=" + Math.random(),
				dataType : "json",
				contentType : "application/json",
				type : 'get',
				success : function(data) {
						html='<table class="c-logo-table">'
							+'<tr>'
							+'<td class="c-logo-left">' 
							+'<a href="../logincs/index_main">'
							+'<img src="../imgs/login/newlogin/logo.png" alt="传化物流" border="0"/>'
							+'</a>'
							+'</td>'
							+'<td class="c-logo-center"></td>'		
							+'<td class="c-logo-right">'
							+'<a href="#">'
							+'<img src="../imgs/login/newlogin/login_tel.png" alt="传化物流" border="0"/>'
							+'</a>'
							+'</td>' 
							+'</tr>'
							+'</table>'
							$("#logo").html(html);
		          },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
		          
		        }
			});
		
});
document.write('<link href="../css/webui/logo.css" rel="stylesheet" type="text/css" />')