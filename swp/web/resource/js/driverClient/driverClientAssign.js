//切换table页面
function cutTablePage(allocateStatus, i){
    var arrs = $("li[name='tableLi']");
    if(arrs && arrs.length > 0){
        $("li[name='tableLi']").removeAttr("class");
        $(arrs[i]).attr("class", "on");
    }
    $("#allocateStatus").val(allocateStatus);
    //查询条件清空
    cleanSelect();
    if(allocateStatus != "0"){
        if(i == "2"){
            $("#buttonTdId").show();//同意/不同意操作按钮显示
        }
        //营销人员查询条件显示
        $("#assisterIdQuery").show();
    }else{
        //营销人员查询条件隐藏
        $("#assisterIdQuery").hide();
    }
    if(i == "0"){
        $("#phoneValid").val(1);
    }else{
        $("#phoneValid").val("");
    }
    //执行查询
    selectSubmit("/cutDriverTable.jspx");
}

//下拉框改变时执行查询操作
function doSubmit(){
    selectSubmit("/cutDriverTable.jspx");
}

//执行查询
function selectSubmit(actName){
    $.ajax({
        url: jcontextPath + actName,
        dataType:'json',
        type: "POST",
        async: false,      //ajax同步
        data:$('#mainForm').serialize(),
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                //更新列表
                $("#listTableId").html(data.content.html);
                $("#clientNumSpan").html(data.content.listAllNum);
            }else if(data.result == '1'){
                location.href=jcontextPath+"/index.jsp";
            }else{
                art.dialog({
                    icon: 'error',
                    content: data.errorMessage
                });
            }
        }
    });
}

//清空查询条件
function cleanSelect(){
    $("#realLevel").val("");//客户等级查询条件
    $("#category").val("");//客户分类查询条件
    $("#assisterIdQuery").val("");//营销人员查询条件
    $("#pageSize").val("50");//显示条数查询条件
    $("#assignPersonSelect").val("");//营销人员
    $("#optRegister").val("");//司机注册状态
    $("#buttonTdId").hide();//同意/不同意操作按钮隐藏
    $("#mobilePhone").val("");//手机号码
    $("#name").val("");//司机姓名
    $("#carNumber").val("");//车牌号
}

//全选按钮点击事件
function onclickSelectAll(eobj){
    if($(eobj).attr("checked") == "checked"){
        //全选
        $("input[type='checkbox'][name='infoIds']").attr({"checked":"checked"});
    }else{
        //全不选
        $("input[type='checkbox'][name='infoIds']").removeAttr("checked");
    }
}

//分配客户给营销人员
function assignClient(){
    //防止重复提交
    $("#assignBut").attr({"disabled":"disabled"});
    //清空提交赋值的数据
    cleanSubData();
    //获得选中的司机资料ids
    var driverIds = getDriverIds();
    if(driverIds == ""){
        autoClosePrompt("请勾选司机资料", "assignBut");
        return;
    }
    var actName = "";//cation名称
    if($("#allocateStatus").val() == "1"){
        //分配申请审核页面  直接分配
        actName = "/reviewDriver.jspx";
        //获得客户分配申请表ids（申请审核table页）
        var assisterApplyIds = getAssisterApplyIds();
        if(assisterApplyIds == ""){
            autoClosePrompt("系统出错", "assignBut");
            return;
        }
        $("#assisterApplyIds").val(assisterApplyIds);
    }else{
        //待分配页面、已分配页面的分配
        actName = "/assignDriver.jspx";
    }
    var assignId = $("#assignPersonSelect").val();
    if(assignId == ""){
        autoClosePrompt("请选择营销人员", "assignBut");
        return;
    }
    var content = "确定要分配给 " + $("#assignPersonSelect").find("option:selected").html() + " ？";
    //是否确定分配给该营销人员
    var dialog = art.dialog({
        content:content,
        okVal:'确定分配',
        cancelVal:'取消分配',
        lock:true,
        fixed: true,
        id: 'Fm2',
        icon: 'question',
        ok: function () {
            $("#assisterId").val(assignId);
            $("#driverIds").val(driverIds);
            $("#reviewType").val(30);
            saveSubmit(actName, $("#assignPersonSelect").find("option:selected").html());
        },
        cancel: true
    });
    $("#assignBut").removeAttr("disabled");
}

//清空提交赋值的数据
function cleanSubData(){
    $("#assisterId").val("");
    $("#driverIds").val("");
    $("#assisterApplyIds").val("");
}

//自动关闭的提示
function autoClosePrompt(cont, id){
    $("#"+id).removeAttr("disabled");
    art.dialog({
        time: 3,
        content: cont + '，三秒后自动关闭该提示框！'
    });
}

//获得选中的司机资料ids
function getDriverIds(){
    //获得选中checkbox
    var arrs = $("input[type='checkbox'][name='infoIds']:checked");
    if(!arrs || arrs.length == 0){
        return "";
    }
    var driverIds = "";
    for(var i = 0;i < arrs.length;i++){
        if(driverIds != ""){
            driverIds += ",";
        }
        driverIds += $(arrs[i]).val();
    }
    return driverIds;
}

//获得客户分配申请表ids（申请审核table页）
function getAssisterApplyIds(){
    var arrs = $("input[type='checkbox'][name='infoIds']:checked");
    if(!arrs || arrs.length == 0){
        return "";
    }
    var assisterApplyIds = "";
    for(var i = 0;i < arrs.length;i++){
        if(assisterApplyIds != ""){
            assisterApplyIds += ",";
        }
        assisterApplyIds += $(arrs[i]).next().val();
    }
    return assisterApplyIds;
}

//同意或不同意操作
function viewOnclick(type, id){
    $("#"+id).attr({"disabled":"disabled"});
    //获得选中的司机资料ids
    var driverIds = getDriverIds();
    if(driverIds == ""){
        autoClosePrompt("请勾选司机资料", id);
        return;
    }
    //获得客户分配申请表ids（申请审核table页）
    var assisterApplyIds = getAssisterApplyIds();
    if(assisterApplyIds == ""){
        autoClosePrompt("系统出错", id);
        return;
    }
    var content = "";//提示内容
    if(type == 10){
        content = "确定审核通过？";
        $("#reviewType").val(10);
    }else{
        content = "确定审核不通过？";
        $("#reviewType").val(20);
    }
    //是否确定分配给该营销人员
    var dialog = art.dialog({
        content:content,
        lock:true,
        fixed: true,
        id: 'Fm2',
        icon: 'question',
        ok: function () {
            $("#driverIds").val(driverIds);
            $("#assisterApplyIds").val(assisterApplyIds);
            saveSubmit("/reviewDriver.jspx", "", type);
        },
        cancel: true
    });
    $("#"+id).removeAttr("disabled");
}

//保存提交操作
function saveSubmit(actName, name, type){
    $.ajax({
        url: jcontextPath + actName,
        dataType:'json',
        type: "POST",
        async: false,      //ajax同步
        data:$('#mainForm').serialize(),
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                //更新列表
                $("#listTableId").html(data.content.html);
                $("#clientNumSpan").html(data.content.listAllNum);
                var content = "";
                if(name){
                    content = "分配给 "+name+" 成功，";
                }else{
                    if(type == "10"){
                        content = "申请审核同意成功，";
                    }else{
                        content = "申请审核不同意成功，";
                    }
                }
                content += "成功 "+data.content.sucNum+" 条";
                if(new Number(data.content.errNum) > 0){
                    content += "，失败 "+data.content.errNum+" 条！失败原因可能已被分配！";
                }else{
                    content += "！";
                }
                art.dialog({
                    icon: 'succeed',
                    content: content
                });
            }else if(data.result == '1'){
                location.href = jcontextPath + "/index.jsp";
            }else{
                art.dialog({
                    icon: 'error',
                    content: data.errorMessage
                });
            }
        }
    });
}

//进入导入页面
function enterImportDriver(importType){
    if(!importType){
        art.dialog({
            icon: 'error',
            content: '系统出错'
        });
        return;
    }
    var url = jcontextPath + "/openImportDriver.jspx?importType=" + importType;
    window.location.href = url;
}

//鼠标移上去的触发
function on_mouse_over(eobj, id){
    $.ajax({
        url: jcontextPath + "/queryMaintainRecord.jspx",
        dataType:'json',
        type: "POST",
        async: true,      //ajax同步
        data: 'id=' + id,
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                var rtJsonList = data.content;
                //设置标记无效次数记录的title
                $(eobj).attr("title",setRecodeTitle(rtJsonList));
            }
        }
    });

}

//设置标记无效次数记录的title
function setRecodeTitle(list){
    if(!list || list.length == 0){
        return "无标记无效次数记录数据";
    }
    var titleStr = "";
    for(var i = 0;i < list.length;i++){
        if(titleStr != ""){
            titleStr += "\r\n";
        }
        titleStr += getString(list[i].actionTime);
        titleStr += "   " + getString(list[i].assisterName);
    }
    return titleStr;
}

function getString(str){
    return str ? str : "";
}