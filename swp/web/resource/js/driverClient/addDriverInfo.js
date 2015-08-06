//进入导入页面
function enterImportDriver(importType){
    if(!importType){
        promptDialog('error', '系统出错');
        return;
    }
    var url = jcontextPath + "/openImportDriver.jspx?importType=" + importType;
    window.location.href = url;
}

//添加
function saveSubmit(id){
    $("#"+id).attr({"disabled":"disabled"});
    if(!valdMess()){
        $("#"+id).removeAttr("disabled");
        return;
    }
    $.ajax({
        url: jcontextPath + "/addSingleDriver.jspx",
        dataType:'json',
        type: "POST",
        async: false,      //ajax同步
        data:$('#mainForm').serialize(),
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                var rtJson = data.content;
                //成功后的提示
                succPrompt(rtJson);
                //清空页面数据
                cleanData();
            }else if(data.result == '1'){
                location.href = jcontextPath + "/index.jsp";
            }else{
                promptDialog('error', data.errorMessage);
            }
        }
    });
    $("#"+id).removeAttr("disabled");
}

//成功后的提示
function succPrompt(rtJson){
    if(rtJson.result == "2"){//该司机资料已存在但未分配
        //是否确定分配给该营销人员
        var dialog = art.dialog({
            content:'该司机资料已存在（未被分配），是否确定要申请该司机资料？',
            okVal:'确定申请',
            cancelVal:'取消申请',
            lock:true,
            fixed: true,
            id: 'Fm2',
            icon: 'question',
            ok: function () {
                applySubmit(rtJson.driverInfo.id);
            },
            cancel: true
        });
        return;
    }else if(rtJson.result == "3"){//该司机资料已被申请或被分配
        var content = "该司机资料已存在（已被分配/已被申请），申请/所属人：" + getString(rtJson.driverInfo.assisterName);
        art.dialog({
            icon: 'warning',
            content: content,
            button: [{name: '确定', callback: function () {return true;}}]
        });
        return;
    }
    promptDialog('succeed', '添加成功');
    return;
}

//申请司机资料提交
function applySubmit(id){
    if(!id){
        promptDialog('error', '系统出错！');
        return;
    }
    $.ajax({
        url: jcontextPath + "/applySingleDriver.jspx",
        dataType:'json',
        type: "POST",
        async: false,      //ajax同步
        data:'id=' + id,
        error: function(request) {
        },
        success: function(data) {
            if(data.result == '0'){
                promptDialog('succeed', '申请成功');
            }else if(data.result == '1'){
                location.href = jcontextPath + "/index.jsp";
            }else{
                promptDialog('error', data.errorMessage);
            }
        }
    });
}

//提示
function promptDialog(icon, content){
    art.dialog({
        icon: icon,
        content: content
    });
}

//验证
function valdMess(){
    if($("#mobilePhone").val() == ""){
        $("#mobilePhoneNull").show();
        return false;
    }
    //验证手机号码格式
    if(!bilenumber($("#mobilePhone").val())){
        $("#mobilePhoneFormat").show();
        return false;
    }
    return true;
}

//获得焦点去除提示信息
function on_focus(){
    $("div[id^='mobilePhone']").hide();
}

//空值转换
function getString(str){
    return str ? str : "";
}

//清空页面数据
function cleanData(){
    $("#mobilePhone").val("");
    $("#name").val("");
    $("#carNumber").val("");
    $("#carLength").val("");
    $("#carBarType").val("");
    $("#carPlateType").val("");
}