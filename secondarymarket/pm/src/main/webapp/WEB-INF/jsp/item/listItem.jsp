<%--
  Created by IntelliJ IDEA.
  User: wangxin
  Date: 13-1-12
  Time: 下午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <title></title>
    <link href="/static/js/jqueryui/jquery-ui-1.9.2.custom.min.css" rel="stylesheet">
    <link href="/static/js/jqgrid/ui.jqgrid.css" rel="stylesheet">
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jqgrid/grid.locale-cn.js"></script>
    <script src="/static/js/jqgrid/jquery.jqGrid.min.js"></script>
    <script src="/static/js/jqgrid/ObjectTemplate.js"></script>
</head>
<body>
<table id="list4"></table>
<div id="gridPager"></div>
</body>
<script type="text/javascript">
    $("#list4").jqGrid($.extend(ObjectTemplate.gridSetting,{
        url: "/item/page",
        colNames:['id','名称', '主题', '图片','编辑描述','推广链接','类别'],
        colModel:[
            {name:'id',index:'id', width:60, sorttype:"int", hidden:true},
            {name:'name',index:'name', width:100},
            {name:'title',index:'title', width:100},
            {name:'pic',index:'pic', width:100},
            {name:'desc',index:'desc', width:90},
            {name:'tbPath',index:'tbPath', width:80, align:"right",sorttype:"float"},
            {name:'categoryId',index:'categoryId', width:80, align:"right",sorttype:"float"},
        ],
        caption: "单品"
    }));
</script>
</html>