<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="header" class="row">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="#">麻麻跳蚤街</a>
                <div class="nav-collapse">
                    <ul class="nav">
                        <li class="active"><a href="#">Home</a></li>
                        <shiro:user><li><a href="/item/list">物品管理</a></li></shiro:user>
                    </ul>
                    <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><shiro:principal property="name"/><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <shiro:guest><li><a href="${ctx}/login">登录</a></li></shiro:guest>
                            <shiro:user><li><a href="${ctx}/logout">退出登录</a></li></shiro:user>
                            <li class="divider"></li>
                            <li class="nav-header">Nav header</li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>