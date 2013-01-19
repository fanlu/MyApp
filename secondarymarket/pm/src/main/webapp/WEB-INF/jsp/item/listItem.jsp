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
</head>
<body>
<jsp:include page="../index.jsp" />
<input type="button" value="新增" id="newBtn" class="btn btn-primary"/>
<input type="button" value="修改" id="updateBtn" class="btn btn-primary"/>
<table id="list4"></table>
<div id="gridPager"></div>
<div id="dialog"></div>
</body>
<script type="text/javascript">
    $("#list4").jqGrid($.extend(ObjectTemplate.gridSetting,{
        url: "/item/page",
        colNames:['id','名称', '主题', '图片','编辑描述','推广链接','类别'],
        colModel:[
            {name:'id',index:'id', width:60, sorttype:"int", hidden:true},
            {name:'name',index:'name', width:100, hidden:true},
            {name:'pic',index:'pic', width:100,formatter:imageFormat, unformat:imageUnFormat},
            {name:'title',index:'title', width:100},
            {name:'desc',index:'desc', width:90},
            {name:'tbPath',index:'tbPath', width:80},
            {name:'categoryId',index:'categoryId', width:10,sorttype:"float"},
        ],
        caption: "单品"
    }));
    $("#list4").jqGrid('navGrid', "#gridPager",ObjectTemplate.pagerSetting);
    $("#newBtn").click(function(){
        $("#dialog").dialog({title:"新建", autoOpen:false, modal:true, resizable:true, width: 600,position:{my:"center",at:"center top"}});
//        $("#dialog").load("/item/forAdd").dialog("open");
        $("#dialog").load("/item/forAdd").modal();
    });
    $("#updateBtn").click(function(){
        var gr = $("#list4").jqGrid('getGridParam','selarrrow');
        $("#dialog").dialog({title:"修改", autoOpen:false, modal:true, resizable:true, width: 600,position: {my:"center",at:"center top"}});
        $("#dialog").load("/item/forUpdate/"+gr).dialog("open");
    });
    function imageFormat( cellvalue, options, rowObject ){
        return '<img src="'+cellvalue+'" />';
    }
    function imageUnFormat( cellvalue, options, cell){
        return $('img', cell).attr('src');
    }
</script>
</html>