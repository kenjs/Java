  $(document).ready(function(){
     var menuAIdHi=$("#menuAIdHi").val();
      var aArray=$(".uls li a");
     for(var i=0;i<aArray.length;i++){
       var aIdEnd=i+1;
       $("#a_id_"+aIdEnd).removeClass();
     }
     if($("#"+menuAIdHi)!=undefined){
    	 $("#"+menuAIdHi).addClass("at");
     }
  });