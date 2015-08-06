//导入成功后打开提示
function openPrompt(result, sucNum, errorNum){
    if(result){
        var importType = $("#importType").val();
        if(importType == "20"){
            //客户分配导入的提示
            promptClientAssign(sucNum, errorNum);
        }else{
            //添加客户的导入提示
            promptAddClient();
        }
    }
}

//添加客户的导入提示
function promptAddClient(){
    $("#sucessLiMess").show();
    $("#addClientDiv").show();
    $("#noAssignDiv").show();
}

//客户分配的导入提示
function promptClientAssign(sucNum, errorNum){
    var openHtml = '<div class="nerh">';
    openHtml += '<dl>';
    openHtml += '<dt><img src="'+jcontextPath+'/resource/image/index/jou.jpg" width="50" height="50" /></dt>';
    openHtml += '<dd>恭喜！您已成功导入司机客户！成功导入'+sucNum+'条';
    if(new Number(errorNum) > 0){
        openHtml += '，失败'+errorNum+'条！失败的原因可能存在该司机资料！';
    }else{
        openHtml += '！';
    }
    openHtml += '</dd>';
    openHtml += '</dl>';
    openHtml += '</div>';
    openHtml += '<div class="buttons">';
    openHtml += '<a href="javascript:enterImportDriver();">继续导入</a>';
    openHtml += '<a href="javascript:goBackClientAssign();">返回客户分配</a>';
    openHtml += '</div>';
    art.dialog({
        width:400,
        height:100,
        id: 'shake-demo—add',
        title: '消息',
        content:openHtml,
        lock: true,
        fixed: true,
        cancel:false
    });
}

//继续导入
function enterImportDriver(){
    var importType = $("#importType").val();
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

//返回客户分配页面
function goBackClientAssign(){
    var url = jcontextPath + "/enterCustom.jspx";
    window.location.href = url;
}

$(document).bind("keydown",function(e){
    e=window.event||e;
    if(e.keyCode==116){
        e.keyCode = 0;
        return false; //屏蔽F5刷新键
    }
});

//清空提示
function cleanContext(){
    $("#errorHtmlId").html("");
}

//导入
function importDriver(){
    cleanContext();
    $("#saveBtn").attr({"disabled":"disabled"});//防止二次提交
    var filePath=$("#uploadFile").val();
    if(filePath==''){
        $("#errorHtmlId").html("请先选择要导入的文件!");
        $("#saveBtn").removeAttr("disabled");
        return;
    }
    var fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
    //不区分大小写的比较
    if(!(fileType.toLowerCase() == "xls".toLowerCase())){
        $("#errorHtmlId").html("系统只支持xls格式的文件！");
        $("#saveBtn").removeAttr("disabled");
        return;
    }
    $('#mainForm').submit();
    return true;
}

//下载模板
function xiazmb(url){
    parent.location.href = url;
}

//切换table页面
function cutTablePage(i){
    var arrs = $("li[name='tableLi']");
    if(arrs && arrs.length > 0){
        $("li[name='tableLi']").removeAttr("class");
        $(arrs[i]).attr("class", "on");
    }
    if(i == 0){
        //待分配的table页面
        $("#noAssignDiv").show();
        $("#saveSucessDiv").hide();
        //刷新待分配列表
        refreshDriverList();
    }else{
        $("#noAssignDiv").hide();
        $("#saveSucessDiv").show();
    }
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

//分配申请
function applyClient(){
    $("#applyBut").attr({"disabled":"disabled"});
    //获得选中的司机资料ids
    var driverIds = getDriverIds();
    if(driverIds == ""){
        autoClosePrompt("请勾选司机资料");
        return;
    }
    //是否确定分配申请
    var dialog = art.dialog({
        content:'是否确定申请？',
        okVal:'确定申请',
        cancelVal:'取消申请',
        lock:true,
        fixed: true,
        id: 'Fm2',
        icon: 'question',
        ok: function () {
            $("#driverIds").val(driverIds);
            saveSubmit();
        },
        cancel: true
    });
    $("#applyBut").removeAttr("disabled");
}

//分配审核保存
function saveSubmit(){
    $.ajax({
        url: jcontextPath + "/applyDriver.jspx",
        dataType:'json',
        type: "POST",
        data:$('#mainForm').serialize(),
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                //移除已申请成功的司机客户资料(更新列表)
                removeNoAssignTr();
                autoClosePrompt("申请成功");
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

//自动关闭的提示
function autoClosePrompt(cont){
    $("#applyBut").removeAttr("disabled");
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

//移除已申请成功的司机客户资料
function removeNoAssignTr(){
    var arrs = $("input[type='checkbox'][name='infoIds']:checked");
    if(!arrs || arrs.length == 0){
        return;
    }
    for(var i = 0;i < arrs.length;i++){
        //移除选中的行
        $(arrs[i]).parent().parent().remove();
    }
}

//刷新待分配的列表
function refreshDriverList(){
    var refreshIds = $("#refreshIds").val();
    if(!refreshIds){
        return;
    }
    $.ajax({
        url: jcontextPath + "/refreshImportDriver.jspx",
        dataType:'json',
        type: "POST",
        data:$('#mainForm').serialize(),
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                //更新列表
                $("#noAssignTable").html(data.content);
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