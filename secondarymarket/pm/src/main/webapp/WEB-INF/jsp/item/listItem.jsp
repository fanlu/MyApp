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
</head>
<body>
<table id="list4"></table>
</body>
<script type="text/javascript">
    jQuery("#list4").jqGrid({
        datatype: "local",
        height: 250,
        colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
        colModel:[
            {name:'id',index:'id', width:60, sorttype:"int"},
            {name:'invdate',index:'invdate', width:90, sorttype:"date"},
            {name:'name',index:'name', width:100},
            {name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
            {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},
            {name:'total',index:'total', width:80,align:"right",sorttype:"float"},
            {name:'note',index:'note', width:150, sortable:false}
        ],
        multiselect: true,
        caption: "Manipulating Array Data"
    });
    var mydata = [
        {id:"1",invdate:"2007-10-01",name:"中文",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
        {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
        {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
        {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
        {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
        {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
        {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
        {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
        {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
    ];
    for(var i=0;i<=mydata.length;i++)
        jQuery("#list4").jqGrid('addRowData',i+1,mydata[i]);
</script>
</html>