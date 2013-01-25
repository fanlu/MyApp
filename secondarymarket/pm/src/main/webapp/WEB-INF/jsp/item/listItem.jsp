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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <style type="text/css">
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
            height:auto;
            vertical-align:text-top;
            padding-top:2px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#list4").jqGrid($.extend(ObjectTemplate.gridSetting,{
                url: "/item/page",
                colNames:['id','名称','图片','主题','编辑描述','推广链接','类别','状态'],
                colModel:[
                    {name:'id',index:'id', width:60, sorttype:"int", hidden:true},
                    {name:'name',index:'name', width:100, hidden:true},
                    {name:'pic',index:'pic', width:100,formatter:imageFormat, unformat:imageUnFormat},
                    {name:'title',index:'title', width:100},
                    {name:'desc',index:'desc', width:90},
                    {name:'tbPath',index:'tbPath', width:80},
                    {name:'categoryId',index:'categoryId', width:18,align:"center",formatter:'select', editoptions:{value:"1:童装童鞋;2:婴儿用品;3:玩具早教;4:孕妈专区"}},
                    {name:'status',index:'status', width:12,sorttype:"float",edittype:'select', formatter:'select', editoptions:{value:"0:未上线;1:上线"}}
                ],
                caption: "单品",
                sortname: "status"
            }));
            $("#list4").jqGrid('navGrid', "#gridPager",ObjectTemplate.pagerSetting);
            $("#newBtn").click(function(){
                $("#dialog").dialog({title:"新建", autoOpen:false, modal:true, resizable:true, width: 700,position:{my:"center",at:"center top"}});
                $("#dialog").load("/item/forAdd").dialog("open");
//        $("#dialog").load("/item/forAdd").modal();
            });
            $("#updateBtn").click(function(){
                var gr = $("#list4").jqGrid('getGridParam','selrow');
                $("#dialog").dialog({title:"修改", autoOpen:false, modal:true, resizable:true, width: 700,position: {my:"center",at:"center top"}});
                $("#dialog").load("/item/forUpdate/"+gr).dialog("open");
            });
        });
        function publish(status){
            var gr = $("#list4").jqGrid('getGridParam','selrow');
            $.get("/item/save", {id:gr, status:status}, function(data){
                if(data == "success"){
                    alert("操作成功");
                    $("#list4").trigger("reloadGrid");
                }
            });
        }
        function imageFormat( cellvalue, options, rowObject ){
            return cellvalue == null ? "" : '<img src="'+cellvalue+'" />';
        }
        function imageUnFormat( cellvalue, options, cell){
            return $('img', cell).attr('src');
        }
    </script>
</head>
<body>
<input type="button" value="新增" id="newBtn" class="btn btn-primary"/>
<input type="button" value="修改" id="updateBtn" class="btn btn-primary"/>
<input type="button" value="上线" id="publishBtn" onclick="publish(1)" class="btn btn-primary"/>
<input type="button" value="取消上线" id="unpublishBtn" onclick="publish(0)" class="btn btn-primary"/>
<table id="list4"></table>
<div id="gridPager"></div>
<div id="dialog"></div>
</body>
</html>