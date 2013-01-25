<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
    <shiro:user>
	<h1>首页管理</h1>
	<div class="submenu">
		<a id="account-tab"href="${ctx}/item/list/">物品管理</a>
	</div>
	<h1>开发中</h1>
	<div class="submenu">
	</div>
    </shiro:user>
</div>