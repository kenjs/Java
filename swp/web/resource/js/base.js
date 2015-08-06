//用于验证，添加错误提示的效果
function addErrorMessager(text,value){
    var html = "<div class='tsxt'>";
    html += "<img src='" + jcontextPath + "/resource/image/sonal/met.jpg'/>";
    html += value
    html += "</div>";
    $(text).parent().append(html);
}
//用于验证，取消错误的效果
function cancelErrorMessager(text){
    $(text).parent().find("div").remove();
}