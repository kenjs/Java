<!DOCTYPE html>
<head>
    <%
        String userAgerent = request.getHeader("user-agent");
        if ((userAgerent.contains("MSIE 6.0") || userAgerent.contains("MSIE 7.0")) && !userAgerent.contains("Trident")) {//纯IE6和纯IE7,屏蔽
        } else if (userAgerent.contains("Trident/4.0") && userAgerent.contains("MSIE 7.0")) {//IE8 启用了兼容性视图，强制将文档模式改成IE8标准
            out.print("<meta http-equiv=\"x-ua-compatible\" content=\"IE=8\">");
        } else if (userAgerent.contains("Trident/5.0") && userAgerent.contains("MSIE 7.0") && userAgerent.contains("Mozilla/4.0")) {//IE9 启用了兼容性视图,强制将文档模式改成IE9标准
            out.print("<meta http-equiv=\"x-ua-compatible\" content=\"IE=9\">");
        } else if (userAgerent.contains("Trident/6.0") || userAgerent.contains("rv:11.0")) {//IE10 或 IE11
        }
    %>
</head>
