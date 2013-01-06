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
    <title></title>
</head>
<body>
<c:forEach items="${categories}" var="category">
    ${category.name}
</c:forEach>
123
</body>
</html>