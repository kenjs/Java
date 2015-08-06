//日期点击事件
function date_onclick(){
    WdatePicker();
    doSubmit(2);
}

//查询
function doSubmit(type){
    //执行清空提示框
    on_focus();
    if(type == "10"){
        //查询条件清空
        cleanSelect();
    }
    if($("input[name='noCustomType']:checked").val() == "2"){
        //验证输入框不为空
        if(!valdInputNull()){
            return;
        }
        if(type == "2"){//日期改变的时候触发
            //验证是否提交
            if(!valdIsSubmit()){
                return;
            }
        }
    }
    $("#mainForm").submit();
}

//查询条件清空
function cleanSelect(){
    $("#mobilePhone").val("");//手机号码
    $("#name").val("");//司机姓名
    $("#carNumber").val("");//车牌号
}

//验证是否提交
function valdIsSubmit(){
    if($("#startNextDate").val() == $("#startNextDateHi").val()
        && $("#endNextDate").val() == $("#endNextDateHi").val()){
        return false;
    }
    return true;
}

//验证输入框不为空
function valdInputNull(){
    if(!$("#startNextDate").val()){//开始日期
        $("div[id^='startNextDate']").show();
        return false;
    }
    if(!$("#endNextDate").val()){//截至日期
        $("div[id^='endNextDate']").show();
        return false;
    }
    return true;
}

//获得焦点的时候执行清空提示框
function on_focus(){
    $("div[id$='Null']").hide();
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