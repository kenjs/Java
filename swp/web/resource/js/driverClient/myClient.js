//查询
function doSubmit(){
    $("#selectButt").attr({"disabled":"disabled"});
    $("#mainForm").submit();
}

//点击GO跳转
function goSelect(){
    var goCurPage = $("#goCurPage").val();
    var totalPages = $("#totalPages").val();
    var curPage = 1;
    if(isNaN(goCurPage)){
        return;
    }
    if(new Number(goCurPage) >= new Number(totalPages)){
        curPage = totalPages;
    }else if(new Number(goCurPage) <= 1){
        curPage = 1;
    }else{
        curPage = goCurPage;
    }
    $("#curPage").val(curPage);
    doSubmit();
}

//上一页 下一页 的操作
function getRealPageInfo(goCurPage){
    $("#curPage").val(goCurPage);
    doSubmit();
}

//打开客户资料详情页
function openDriverDetails(id, type, driverId){
    var params = "?id=" + id + "&driverId=" + driverId;
    //已注册的
    var url = jcontextPath + "/swp/driverClient/queryMyClient.jsp";
    if(type == 0){
        //未注册
        url = jcontextPath + "/swp/driverClient/queryMyClientN.jsp";
    }
    //window.location.href = url + params;
    var new_window = window.open('_blank','driverDetailPage','top=0,left=0,width='+ (screen.availWidth - 10) +',height='+ (screen.availHeight-50) +',scrollbars=yes,resizable=yes');
    new_window.location.href = url + params;
}

//初始化table分页
function initTablePage(registerKey){
    var arrs = $("li[name='tableLi']");
    if(!arrs || arrs.length == 0){
        return;
    }
    if(registerKey == "1"){
        $(arrs[0]).attr("class", "on");
    }else{
        $(arrs[1]).attr("class", "on");
    }
}

//切换table页面
function cutTablePage(registerKey){
    $("#optRegister").val(registerKey);
    //清空查询条件
    cleanSelect();
    $("#mainForm").submit();
}

//清空查询条件
function cleanSelect(){
    $("#mobilePhone").val("");
    $("#name").val("");
    $("#carNumber").val("");
    $("#carLength").val("");
    $("#carBarType").val("");
    $("#carPlateType").val("");
    $("#realLevel").val("");
    $("#category").val("");
    $("#pageSize").val("50");
}