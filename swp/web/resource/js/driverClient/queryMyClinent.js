/**
 * Created by nixianjing on 14/12/12.
 */

/************************获取司机全部信息开始*******************************/
/**
 * 获取司机客户信息（未安装）
 * @param id
 */
function getDriverInfoN(id) {
    if(id == null || id == '') {
        setAlert("页面加载过慢！请重新刷新页面！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/queryMarketingDriverInfoN.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':'N'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                $("#marketingDriverId").val(driverInfo.id);
                //客户等级
                $("#realLevelHtml").html('客户等级：'+driverInfo.realLevel);
                //联系方式
                $("#mobilePhoneHtml").html('联系方式：'+driverInfo.mobilePhone);
                //司机姓名
                $("#driverName").html(driverInfo.name);
                //QQ号码
                $("#qqNumberHtml").html(driverInfo.qqNumber);
                //车牌号
                $("#carNumberHtml").html(driverInfo.carNumber);
                //车型
                $("#carTypesHtml").html(driverInfo.carTypes);
                //载重
                $("#carWeightHtml").html(driverInfo.carWeight);
                //体积
                $("#carCubageHtml").html(driverInfo.carCubage);
                //线路不固定，常跑城市线路
                $("#oftenCity1Html").html(driverInfo.oftenCity1);
                $("#oftenCity2Html").html(driverInfo.oftenCity2);
                $("#oftenCity3Html").html(driverInfo.oftenCity3);
                $("#oftenCity4Html").html(driverInfo.oftenCity4);
                $("#oftenCity5Html").html(driverInfo.oftenCity5);
                $("#oftenCity6Html").html(driverInfo.oftenCity6);
                //手机号码
                $("#mobilePhone1Html").html(driverInfo.mobilePhone);
                $("#mobilePhone2Html").html(driverInfo.mobilePhone2);
                $("#mobilePhone3Html").html(driverInfo.mobilePhone3);
                $("#nextContactDate").val(driverInfo.nextContactDate);
                //if(driverInfo.hasPurpose == '0'){
                    //$("#hasPurpose_0").attr("checked","checked");
                    //$("#hasPurpose_1").attr("checked","");
                //}else if(driverInfo.hasPurpose == '1') {
                  //  $("#hasPurpose_0").attr("checked","");
                   // $("#hasPurpose_1").attr("checked","checked");
                //}
                if(driverInfo.hasPurpose == '' || driverInfo.hasPurpose == 'null' || driverInfo.hasPurpose == null){
                }else {
                    $("#hasPurpose").val(driverInfo.hasPurpose);
                }
            }else if(data.result == 1){//未登录

            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 获取司机活跃信息
 * @param driverId
 */
function getActiveDriverInfo(driverId) {
    if(driverId == null || driverId == '') {
        setAlert("页面加载过慢！请重新刷新页面！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/queryActiveDriverInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'driverId':driverId},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                $("#reghtTimeHtml").html(driverInfo.reghtTime);

                $("#user15DayNumHtml").html(driverInfo.user15DayNum);

                $("#lastUserTimeHtml").html(driverInfo.lastUserTime);

                if(driverInfo.lastLocation != null && driverInfo.lastLocation != "") {
                    $("#lastLocationHtml").html(driverInfo.lastLocation+"<input name=\"\" onclick=\"getLocation('1');\" type=\"button\" value=\"轨迹\" />");
                }
                $("#lastLocationTimeHtml").html(driverInfo.lastLocationTime);

            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}


/**
 * 获取司机客户信息（已安装（已认证、未认证））
 * @param id
 */
function getDriverInfo(id) {
    if(id == null || id == '') {
        setAlert("页面加载过慢！请重新刷新页面！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/queryMarketingDriverInfoN.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':'Y'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                $("#marketingDriverId").val(driverInfo.id);
                $("#driverId").val(driverInfo.driverId);
                //客户等级
                $("#realLevelHtml").html('客户等级：'+driverInfo.realLevel);
                if(driverInfo.customerType == '3') {
                    $("#customerTypeHtml").html('客户状态：已认证');
                }else {
                    $("#customerTypeHtml").html('客户状态：未认证');
                }
                //联系方式
                $("#mobilePhoneHtml").html('联系方式：'+driverInfo.mobilePhone);
                //司机姓名
                $("#driverName").html(driverInfo.name);
                //QQ号码
                $("#qqNumberHtml").html(driverInfo.qqNumber);
                //身份证
                $("#identityLicenseNumHtml").html(driverInfo.identityLicenseNum);
                //车牌号
                $("#carNumberHtml").html(driverInfo.carNumber);
                //车型
                $("#carTypesHtml").html(driverInfo.carTypes);
                //载重
                $("#carWeightHtml").html(driverInfo.carWeight);
                //体积
                $("#carCubageHtml").html(driverInfo.carCubage);
                //线路不固定，常跑城市线路
                $("#oftenCity1Html").html(driverInfo.oftenCity1);
                $("#oftenCity2Html").html(driverInfo.oftenCity2);
                $("#oftenCity3Html").html(driverInfo.oftenCity3);
                $("#oftenCity4Html").html(driverInfo.oftenCity4);
                $("#oftenCity5Html").html(driverInfo.oftenCity5);
                $("#oftenCity6Html").html(driverInfo.oftenCity6);
                //手机号码
                $("#mobilePhone1Html").html(driverInfo.mobilePhone);
                $("#mobilePhone2Html").html(driverInfo.mobilePhone2);
                $("#mobilePhone3Html").html(driverInfo.mobilePhone3);
                $("#nextContactDate").val(driverInfo.nextContactDate);
                //if(driverInfo.hasPurpose == '0'){
                  //  $("#hasPurpose_0").attr("checked","checked");
                    //$("#hasPurpose_1").attr("checked","");
                //}else if(driverInfo.hasPurpose == '1') {
                  //  $("#hasPurpose_0").attr("checked","");
                    //$("#hasPurpose_1").attr("checked","checked");
                //}
                if(driverInfo.hasPurpose == '' || driverInfo.hasPurpose == 'null' || driverInfo.hasPurpose == null){
                }else {
                    $("#hasPurpose").val(driverInfo.hasPurpose);
                }
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}
/************************获取司机全部信息结束*******************************/

/************************获取司机运营线路开始*******************************/
/**
 * 获取司机的多条运营线路
 * @param id  or driverId
 * @param str 注册和未注册
 */
function getDriverLine(id,str) {
    if(id == null || id == '') {
        setAlert("页面加载过慢！请重新刷新页面！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/queryDriverLineByIdOrDriverId.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverLine = data.content;
                for(var i = 0;i < driverLine.length;i++) {
                    var linehtml = "";
                    if(driverLine[i].startProvince != null) {
                        linehtml += driverLine[i].startProvince;
                    }
                    if(driverLine[i].startCity != null) {
                        linehtml += driverLine[i].startCity;
                    }
                    if(driverLine[i].startCounty != null) {
                        linehtml += driverLine[i].startCounty;
                    }
                    linehtml += " —— ";
                    if(driverLine[i].endProvince != null) {
                        linehtml += driverLine[i].endProvince;
                    }
                    if(driverLine[i].endCity != null) {
                        linehtml += driverLine[i].endCity;
                    }
                    if(driverLine[i].endCounty != null) {
                        linehtml += driverLine[i].endCounty;
                    }
                    $("#driverLineHtml"+i).html(linehtml);
                    $("#driverLineId"+i).val(driverLine[i].id);
                }
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}
/**
 * 根据运营线路id获取运营线路对象
 * @param i
 * @param str
 */
function queryDriverLineById(i,str) {
    var id = $("#driverLineId"+i).val();
    $.ajax({
        url: jcontextPath + "/queryDriverLineInfoById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverLineInfo = data.content;
                out(driverLineInfo,"编辑司机运营线路");
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

function updateDriverLineById(str) {
    var driverLineId = $("#driverLineId").val();
    var startPcc = $("#startPcc").val();
    var endPcc = $("#endPcc").val();
    if(startPcc == null || startPcc == '') {
        $("#updateNewsId").html("<font color='red' size='12px'>运营线路出发地不能为空！</font>");
        return;
    }
    if(endPcc == null || endPcc == '') {
        $("#updateNewsId").html("<font color='red' size='12px'>运营线路目的地不能为空！</font>");
        return;
    }
    var customerDriverId = "";
    var marId = "";
    if(str == "N") {
        customerDriverId = $("#marketingDriverId").val();
    }else {
        customerDriverId = $("#driverId").val();
        marId = $("#marketingDriverId").val();
    }
    if(customerDriverId == null || customerDriverId == '') {
        setAlert("页面加载过慢！请重新刷新页面！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/updateDriverLineInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'id':driverLineId,'customerDriverId':customerDriverId,'startPcc':startPcc,'endPcc':endPcc,'status':str,'marId':marId},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var lineId = data.content;
                $("#driverLineId").val(lineId);
                $("#updateNewsId").html("<font color='red' size='12px'>编辑运营线路信息成功！</font>");
                getDriverLine(customerDriverId,str);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/************************获取司机运营线路结束*******************************/

/************************获取司机预约线路开始*******************************/
function getDriverBuLineList(id,str) {
    $.ajax({
        url: jcontextPath + "/queryDriverBuLineInfoList.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverBuLineInfoHtml = data.content;
                $("#driverBuLineInfoHtml").html(driverBuLineInfoHtml);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

function queryDriverBuLineById(i,str) {
    var id = $("#driverBuLineId"+i).val();
    $.ajax({
        url: jcontextPath + "/queryDriverBuLineInfoById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverBuLineInfoHtml = data.content;
                out(driverBuLineInfoHtml,"编辑预约线路");
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

function updateDriverBuLine(str) {
    var driverBusLineId = $("#driverBusLineId").val();
    var startTime = $("#startTime").val();
    if(startTime == null || startTime == "") {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路开始时间不能为空！</font>");
        return;
    }
    var endTime = $("#endTime").val();
    if(endTime == null || endTime == "") {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路结束时间不能为空！</font>");
        return;
    }
    if(getDate(startTime,endTime)<0) {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路开始时间小于或等于结束时间！</font>");
        return;
    }
    if(getDateNew(endTime)<0) {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路结束时间不能小于当前时间！</font>");
        return;
    }
    var startPcc = $("#startPcc").val();
    if(startPcc == null || startPcc == "") {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路出发地不能为空！</font>");
        return;
    }
    var endPcc = $("#endPcc").val();
    if(endPcc == null || endPcc == "") {
        $("#updateNewsId").html("<font color='red' size='12px'>预约线路目的地不能为空！</font>");
        return;
    }
    var quoteType = $("#quoteType").val();
    var quoteFair = $("#quoteFair").val();
    if(quoteFair == null || quoteFair == "") {
        quoteFair = 0;
    }
    var customerDriverId = "";
    var marId = "";
    if(str == "N") {
        customerDriverId = $("#marketingDriverId").val();
    }else {
        customerDriverId = $("#driverId").val();
        marId = $("#marketingDriverId").val();
    }
    $.ajax({
        url: jcontextPath + "/updateDriverBuLineInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'id':driverBusLineId,'customerDriverId':customerDriverId,'startTime':startTime,'endTime':endTime,
            'startPcc':startPcc,'endPcc':endPcc,'quoteType':quoteType,'quoteFair':quoteFair,'status':str,'marId':marId},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var lineId = data.content;
                $("#driverBusLineId").val(lineId);
                $("#updateNewsId").html("<font color='red' size='12px'>编辑预约线路信息成功！</font>");
                getDriverBuLineList(customerDriverId,str);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/************************获取司机预约线路结束*******************************/


/************************修改车辆信息开始*******************************/
/**
 * 司机姓名
 * QQ号码
 * 车牌号
 * 车辆长
 * 车辆板
 * 车辆栏
 * 车辆重量（运力）
 * 车辆体积（运力）
 *
 */
/**
 * 查询司机信息（车辆信息）
 */
function queryDriverInfo(str) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryUpdateDriverInfoByDriverInfoDomain.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str,'type':'1'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                out(driverInfo,'编辑车辆信息');
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 修改司机信息
 * @param str
 */
function updateDriverInfo(str) {
    var id = $("#marketingDriverId").val();
    var carName = $("#carName").val();
    var carNumber = $("#carNumber").val();
    var carLength = $("#carLength").val();
    var carPlateType = $("#carPlateType").val();
    var carBarType = $("#carBarType").val();
    var carWeight = $("#carWeight").val();
    var carCubage = $("#carCubage").val();
    var driverId = $("#driverId").val();
    var qqNumber = $("#qqNumber").val();
    $.ajax({
        url: jcontextPath + "/queryUpdateDriverInfoByDriverInfoDomain.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'carNumber':carNumber,'carLength':carLength,'name':carName,
            'carPlateType':carPlateType,'carWeight':carWeight,'carBarType':carBarType,
            'carCubage':carCubage,'driverId':driverId,'qqNumber':qqNumber,
            'status':str,'type':'2'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                $("#updateNewsId").html("<font color='red' size='12px'>修改司机车辆信息成功！</font>");
                if(str == 'N') {
                    getDriverInfoN(id);
                }else {
                    getDriverInfo(id);
                }
                queryMarketingMaintainRecordPage('1');
            }else if(data.result == 1){//未登录

            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}
/************************修改车辆信息结束*******************************/

/************************修改司机常跑城市信息开始*******************************/
/**
 * 查询司机常跑城市信息
 */
function queryDirverInfoSetOftenCity(str) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryUpdateDriverInfoSetOftenCity.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'status':str,'type':'1'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                out(driverInfo,'编辑司机常跑城市信息');
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 修改司机常跑城市
 * @param str
 */
function updateDirverInfoSetOftenCity(str){
    var id = $("#marketingDriverId").val();
    var oftenCity1 = $("#oftenCity1").val();
    var oftenCity2 = $("#oftenCity2").val();
    var oftenCity3 = $("#oftenCity3").val();
    var oftenCity4 = $("#oftenCity4").val();
    var oftenCity5 = $("#oftenCity5").val();
    var oftenCity6 = $("#oftenCity6").val();
    $.ajax({
        url: jcontextPath + "/queryUpdateDriverInfoSetOftenCity.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'oftenCity1':oftenCity1,'oftenCity2':oftenCity2,
            'oftenCity3':oftenCity3,'oftenCity4':oftenCity4,
            'oftenCity5':oftenCity5,'oftenCity6':oftenCity6,
            'status':str,'type':'2'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                $("#updateNewsId").html("<font color='red' size='12px'>修改司机常跑城市信息成功！</font>");
                if(str == 'N') {
                    getDriverInfoN(id);
                }else {
                    getDriverInfo(id);
                }
            }else if(data.result == 1){//未登录

            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}



/************************修改司机常跑城市信息结束*******************************/


/************************修改司机备用手机号开始*****************************************/

function queryMobilePhone(orderId) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryMobilePhone.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'orderId':orderId},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var driverInfo = data.content;
                out(driverInfo,'编辑司机备用手机号信息');
            }else if(data.result == 1){//未登录

            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

function updateMobilePhone(orderId) {
    var id = $("#marketingDriverId").val();
    var mobilePhone2 = "";
    var mobilePhone3 = "";
    if(orderId == "2") {
        mobilePhone2 = $("#mobilePhone2").val();
    }
    if(orderId == "3") {
        mobilePhone3 = $("#mobilePhone3").val();
    }
    $.ajax({
        url: jcontextPath + "/updateMobilePhone.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'mobilePhone2':mobilePhone2,'mobilePhone3':mobilePhone3,'orderId':orderId},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var mobilePhone = data.content;
                $("#updateNewsId").html("<font color='red' size='12px'>编辑司机备用号码成功！</font>");
                $("#mobilePhone"+orderId+"Html").html(mobilePhone);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}


/************************修改司机备用手机号开始*****************************************/

/**********无效号码操作*************/
function queryWuxiaohaoma() {
    var countHtml = "<div class='tabfl'>";
    countHtml += "<div style='height: 30px; text-align: center;' id='updateNewsId'>此操作是号码无效，无效号码就不在属于您的客户，请慎重操作！</br>确认无效号码请点击确认按钮，否则就点击取消。</div>"
    countHtml += "<div class='buttons'><a href='javascript:updateWuxiaohaoma();'>确认</a><a href='javascript:closes();'>取消</a></div></div>";
    out(countHtml,"确认号码无效");
}
function updateWuxiaohaoma() {
    var id = $("#marketingDriverId").val();
    var category = $("#category").val();
    $.ajax({
        url: jcontextPath + "/updateWuxiaohaoma.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'category':category},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                closes();
                location.href=jcontextPath+'/enterMyCustom.jspx';
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}
/**********无效号码操作*************/

/**
 * 查询客户类别
 */
function getCategoryHtml(id) {
    $.ajax({
        url: jcontextPath + "/queryCategoryById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var counten = data.content;
                $("#categoryIdHtml").html(counten);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 修改客户类别
 */
function updateCategoryHtml() {
    var id = $("#marketingDriverId").val();
    var categoryselsct = $("#categoryselsct").val();
    $.ajax({
        url: jcontextPath + "/updateCategoryById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'categoryselsct':categoryselsct},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                getCategoryHtml(id)
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 提价
 */
function addMarketingMaintainRecord(){
    var id = $("#marketingDriverId").val();
    var content1 = $("#content1").val();
    var content2 = $("#content2").val();
    var content3 = $("#content3").val();
    var nextContactDate = $("#nextContactDate").val();
    var isValidCall = $('input[name="isValidCall"]:checked').val();
    if(nextContactDate == null || nextContactDate == "") {
        setAlert("下次联系时间不能为空！");
        return;
    }
    //var hasPurpose = $('input[name="hasPurpose"]:checked').val();
    var hasPurpose = $("#hasPurpose").val();
    $.ajax({
        url: jcontextPath + "/addMarketingMaintainRecordById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'content1':content1,'content2':content2,'content3':content3,'isValidCall':isValidCall,'nextContactDate':nextContactDate,'hasPurpose':hasPurpose},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                setAlert(data.errorMessage);
                queryMarketingMaintainRecordPage('1');
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}


/**
 * 当前页数
 * @param curPage
 */
function queryMarketingMaintainRecordPage(curPage) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryMarketingMaintainRecordPage.jspx",
        type:'post',
        dataType:'json',
        data:{'customerId':id,'curPage':curPage,'pageSize':'10'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var marketingMaintainRecord = data.content;
                $("#MarketingMaintainRecordIdHtml").html(marketingMaintainRecord.html1);
                realPageInfo(marketingMaintainRecord.pageInfo.totalPages,marketingMaintainRecord.pageInfo.curPageNo,marketingMaintainRecord.pageInfo.totalRecords);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 分页
 * @param totalPages 总页数
 * @param curPageNos 当前所在页码（在第几页）
 * @param totalRecords 总记录数
 */
function realPageInfo(totalPages,curPageNos,totalRecords) {
    var liststep = 5;//最多显示分页页数
    var totalPage = totalPages;//总页数
    var curpage = curPageNos;//当前页数
    var totalRecord = totalRecords;	//总记录数

    if (totalPage < curpage) {
        curpage = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
    }
    if (curpage < 1) {
        curpage = 1;//如果分页变量小于１,则将分页变量设为１
    }
    //计算要展示哪几页
    var listbegin;
    var listend;

    listbegin = curpage - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2));
    listend = curpage - 1 + ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2) + 1);// 分页信息显示到第几页

    if((totalPage - curpage) < parseInt(liststep /2)){
        listbegin = totalPage - liststep + 1;
        listend = totalPage;
    }
    if(curpage <= parseInt(liststep /2)){
        listbegin = 1;
        listend = liststep;
    }
    if (listbegin < 1) {
        listbegin = 1;
    }
    if (listend > totalPage) {
        listend = totalPage;
    }

    var pageHTML = "<dl>";
    pageHTML += "<dt><a href='javascript:queryMarketingMaintainRecordPage(1);'>首页</a></dt>";
    if (curpage == 1) {//上一页
        pageHTML += "<dt><a href='javascript:queryMarketingMaintainRecordPage("+curpage+");'>上一页</a></dt>";
    }
    if(curpage>1) {
        pageHTML += "<dt><a href='javascript:queryMarketingMaintainRecordPage("+(curpage-1)+");'>上一页</a></dt>";
    }
    pageHTML += "</dl><ul>";
    for (var i = listbegin; i <= listend; i++) {
        if (i != curpage) {//如果i不等于当前页
            pageHTML += "<li><a href='javascript:queryMarketingMaintainRecordPage("+i+");'>"+i+"</a></li>";
        } else {
            pageHTML += "<li><a class='ult'>"+i+"</a></li>";
        }
    }
    pageHTML += "</ul><dl>";
    //下一页
    if(curpage == totalPage) {
        pageHTML += "<dt><a href='javascript:queryMarketingMaintainRecordPage("+curpage+");'>下一页</a></dt>";
    }else {
        pageHTML += "<dt><a href='javascript:queryMarketingMaintainRecordPage("+(curpage + 1)+");'>下一页</a></dt>";
    }
    pageHTML += "<dd><a href='javascript:queryMarketingMaintainRecordPage("+totalPages+");'>尾页</a></dd>";
    pageHTML += "<dd><a>共"+totalPages+"页\/"+totalRecord+"条信息</a></dd>";
    pageHTML += "</dl>";
    if(totalPage == 0){
        $("#pageInfoHtmlId").html("");
    }else {
        $("#pageInfoHtmlId").html(pageHTML);
    }
}

/**
 * 匹配货源
 */
function queryMarketingOrderCargoById(str){
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryMarketingOrderCargoById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'start':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                $("#cargoHtml").html(data.content);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 发送货源给司机
 * @param cargoId 货源id
 */
function setCargoNoteInfo(cargoId) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/setCargoNotePushInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'cargoId':cargoId,'start':'N'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                setAlert("发送货源成功！")
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 推送货源给司机
 * @param cargoId 货源id
 */
function setCargoPushInfo(cargoId) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/setCargoNotePushInfo.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'cargoId':cargoId,'start':'Y'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                setAlert("推送货源成功！")
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 发送短信(不针对货源)可以选择模板
 * @param str
 */
function setNoteInfo(str) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryNoteInfoByDriverId.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'start':str},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var note = data.content;
                out(note,"发送短信");
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 预览短信
 * @param str
 */
function querySystemNoteById(id) {
    $.ajax({
        url: jcontextPath + "/querySystemNoteById.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var note = data.content;
               $("#contentNoteId").val(note);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

/**
 * 发送短信(不针对货源)可以选择模板
 * @param str
 */
function addNoteInfo() {
    var id = $("#marketingDriverId").val();
    var noteId = $('input[name="noteInfoId"]:checked').val();
    if(noteId == null || noteId == '') {
        $("#updateNewsId").html("请选择要发送的短信模板！");
        return;
    }
    var mobilePhone = $('input[name="mobilePhoneNoteId"]:checked').val();
    if(mobilePhone == null || mobilePhone == '') {
        $("#updateNewsId").html("请选择要发送的短信手机号码！");
        return;
    }
    $.ajax({
        url: jcontextPath + "/addNoteInfoByDriverId.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'noteId':noteId,'mobilePhone':mobilePhone},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                $("#updateNewsId").html("发送短信成功！");
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}
/**
 * 车辆轨迹
 */
function getLocation(curPage) {
    var driverId = $("#driverId").val();
    $.ajax({
        url: jcontextPath + "/queryLocation.jspx",
        type:'post',
        dataType:'json',
        data:{'driverId':driverId,'curPage':curPage,'pageSize':'15'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var locationInfo = data.content;
                var htmls = '<div class="tabfl"><div style="height: 30px; text-align: center;" id="updateNewsId"></div>';
                htmls += '<table id="getLocationPageIdHtml" border="0" cellspacing="1" cellpadding="3" style="margin-top:-1px;">';
                htmls += locationInfo.html;
                htmls += '</table>';
                htmls += '<div class="feye" id="pageInfoLocationHtmlId"></div></div>';
                out(htmls,"司机定位轨迹")
                locationPageInfo(locationInfo.pageInfo.totalPages,locationInfo.pageInfo.curPageNo,locationInfo.pageInfo.totalRecords);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}


function getLocationPage(curPage) {
    var driverId = $("#driverId").val();
    $.ajax({
        url: jcontextPath + "/queryLocation.jspx",
        type:'post',
        dataType:'json',
        data:{'driverId':driverId,'curPage':curPage,'pageSize':'15'},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                var locationInfo = data.content;
                $("#getLocationPageIdHtml").html(locationInfo.html);
                locationPageInfo(locationInfo.pageInfo.totalPages,locationInfo.pageInfo.curPageNo,locationInfo.pageInfo.totalRecords);
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}


/**
 *  轨迹分页
 * @param totalPages 总页数
 * @param curPageNos 当前所在页码（在第几页）
 * @param totalRecords 总记录数
 */
function locationPageInfo(totalPages,curPageNos,totalRecords) {
    var liststep = 5;//最多显示分页页数
    var totalPage = totalPages;//总页数
    var curpage = curPageNos;//当前页数
    var totalRecord = totalRecords;	//总记录数

    if (totalPage < curpage) {
        curpage = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
    }
    if (curpage < 1) {
        curpage = 1;//如果分页变量小于１,则将分页变量设为１
    }
    //计算要展示哪几页
    var listbegin;
    var listend;

    listbegin = curpage - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2));
    listend = curpage - 1 + ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2) + 1);// 分页信息显示到第几页

    if((totalPage - curpage) < parseInt(liststep /2)){
        listbegin = totalPage - liststep + 1;
        listend = totalPage;
    }
    if(curpage <= parseInt(liststep /2)){
        listbegin = 1;
        listend = liststep;
    }
    if (listbegin < 1) {
        listbegin = 1;
    }
    if (listend > totalPage) {
        listend = totalPage;
    }

    var pageHTML = "<dl>";
    pageHTML += "<dt><a href='javascript:getLocationPage(1);'>首页</a></dt>";
    if (curpage == 1) {//上一页
        pageHTML += "<dt><a href='javascript:getLocationPage("+curpage+");'>上一页</a></dt>";
    }
    if(curpage>1) {
        pageHTML += "<dt><a href='javascript:getLocationPage("+(curpage-1)+");'>上一页</a></dt>";
    }
    pageHTML += "</dl><ul>";
    for (var i = listbegin; i <= listend; i++) {
        if (i != curpage) {//如果i不等于当前页
            pageHTML += "<li><a href='javascript:getLocationPage("+i+");'>"+i+"</a></li>";
        } else {
            pageHTML += "<li><a class='ult'>"+i+"</a></li>";
        }
    }
    pageHTML += "</ul><dl>";
    //下一页
    if(curpage == totalPage) {
        pageHTML += "<dt><a href='javascript:getLocationPage("+curpage+");'>下一页</a></dt>";
    }else {
        pageHTML += "<dt><a href='javascript:getLocationPage("+(curpage + 1)+");'>下一页</a></dt>";
    }
    pageHTML += "<dd><a href='javascript:getLocationPage("+totalPages+");'>尾页</a></dd>";
    pageHTML += "<dd><a>共"+totalPages+"页\/"+totalRecord+"条信息</a></dd>";
    pageHTML += "</dl>";
    if(totalPage == 0){
        $("#pageInfoLocationHtmlId").html("");
    }else {
        $("#pageInfoLocationHtmlId").html(pageHTML);
    }
}


/**
 * 查看司机行驶证、驾驶证
 * @param type
 */
function getDriverImg(type) {
    var id = $("#marketingDriverId").val();
    $.ajax({
        url: jcontextPath + "/queryDriverImg.jspx",
        type:'post',
        dataType:'json',
        data:{'id':id,'type':type},
        success:function(data){//回传函数
            if(data.result == 0) {//成功
                if(type == "0") {
                    getDriverCar("驾驶证",data.content)
                }else {
                    getDriverCar("行驶证",data.content)
                }
            }else if(data.result == 1){//未登录
            }else {//出错（例：参数为空）
                setAlert(data.errorMessage);
            }
        }
    });
}

function getDriverCar(title,url) {
    art.dialog({
        padding: 0,
        title: title,
        content: '<div style="width: 300px; height: 300px;"><img  style="width: 300px; height: 300px;" src="'+jcontextPath+'/outputImage.jspx?fileUrl='+url+'" /></div>',
        lock: true
    });
}



/**
 * 弹窗
 * @param strContent内容
 * @param strTitle标题
 */
function out(strContent,strTitle){
    art.dialog({
        lock:false,
        title:strTitle,
        content: strContent
    });
}

//提示弹窗
function setAlert(contentVal) {
    art.dialog({
        time:3,
        icon: 'error',
        content:contentVal
    });
}

/**
 *关闭弹窗
 */
function closes() {
    var list = art.dialog.list;
    for (var i in list) {
        list[i].close();
    };
}
function getDate(str1,str2) {
    var arr1 = str1.split("-");
    var arr2 = str2.split("-");
    var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
    var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
    var date = Number(date2)-Number(date1);
    return date;
}

function getDateNew(str2) {
    var myDate = new Date();
    var arr2 = str2.split("-");
    var date1=new Date(parseInt(myDate.getFullYear()),parseInt(myDate.getMonth())-1,parseInt(myDate.getDate()),0,0,0);
    var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
    var date = Number(date2)-Number(date1);
    return date;
}