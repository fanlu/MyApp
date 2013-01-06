<%--
  Created by IntelliJ IDEA.
  User: wangxin
  Date: 13-1-6
  Time: 下午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>妈妈跳蚤街</title>
    <link rel="stylesheet" type="text/css" href="/static/styles/style.css">
</head>
<body>
<div id="mainbox">
    <!--<div class="hd" style="position:fixed;width:760px;left:0;top:0;right:0;z-index:2;margin:auto;">-->
    <div class="hd" style="position:;width:760px;left:0;top:0;right:0;z-index:2;margin:auto;">
        <div>
            <div class="logo"></div>
            <div class="clear"></div>
        </div>
        <div class="nav">
            <div class="mask"></div>
            <div class="fr none" style="position:relative;cursor:pointer;margin-right:10px;height:32px;width:32px;"><a href="javascript:void('collect');" onclick="javascript:collectread();return false;" class="mycollect" title="我的收藏"></a></div>
            <ul>
                <li class="current"><a href="javascript:void('nav0');" onclick="javascript:showItems(0);return false;">全部</a></li>
                <c:forEach items="${categories}" var="category">
                    <li><a href="javascript:void('${category.name}');" onclick="javascript:showItems('${category.name}');return false;">${category.title}</a></li>
                </c:forEach>
            </ul>
            <div class="clear"></div>
            <div class="subnav"></div>
        </div>
    </div>
</div>

123
</body>
</html>