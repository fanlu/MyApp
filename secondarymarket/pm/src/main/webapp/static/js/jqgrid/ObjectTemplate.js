var ObjectTemplate = {};
ObjectTemplate.jsonReader = {
    root : "result",
    page : "pageNo",
    total : "totalPages",
    records : "totalCount", // 总记录数
    repeatitems : false
    // 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
};
//ObjectTemplate.formatePostData = function(postData) {// 在发送数据前进行一次预处理
//    var formatedPostData = [];
//    // 此部分扫描表定义中的列，自动添加参数
//    var propertys = [];
//    for (index = 0; index < this.p.colModel.length; index++) {
//        if (this.p.colModel[index].index != null) {
//            propertys[propertys.length] = this.p.colModel[index].index;
//        }
//    }
//    formatedPostData[0] = propertys;
//    for ( var index = 0; index < postData.length; index++) {
//        formatedPostData[formatedPostData.length] = postData[index];
//    }
//    formatedPostData[formatedPostData.length] = postData.sortColumns;
//    formatedPostData[formatedPostData.length] = postData.order;
//    formatedPostData[formatedPostData.length] = postData.page;
//    formatedPostData[formatedPostData.length] = postData.limit;
//    var searchCodition = "";
//    if (postData._search) {
//        switch (postData.searchOper) {
//            case 'eq':
//            case 'bw':
//            case 'bn':
//            case 'ew':
//            case 'en':
//            case 'cn':
//            case 'nc':
//            case 'nu':
//            case 'nn':
//            case 'in':
//            case 'ni':
//            default:
//                searchCodition = " like '%" + postData.searchString + "%'";
//        }
//        formatedPostData[1] += " and " + postData.searchField + searchCodition;
//    }
//    var unSouportArg = {};
//    unSouportArg.filters = postData.filters;
//    unSouportArg.nd = postData.nd;
//    return formatedPostData;
//};
ObjectTemplate.gridSetting = {
    altRows : true,//设置为交替行表格
    autoencode : true,//当设置为true时，对来自服务器的数据和提交数据进行encodes编码。如< 将被转换为&lt;
    rownumbers:true,//显示行号
//    serializeGridData:ObjectTemplate.formatePostData,
    height : "450",
    width : "300",//初始时，表格的宽度，如果设置了随窗口改变自动适应宽度，该值实效
    autowidth : true,
    sortname : 'id',
    sortorder : 'asc',
    datatype : "json",
    rowNum : 100,
    rowList : [ 10, 50, 100 ],
    viewrecords : true,
    multiselect : true,
    multiboxonly : false,
    pager : "#gridPager",
    cloneToTop:true,
    jsonReader : ObjectTemplate.jsonReader,
    prmNames:{
        //search: 'search',
        page:"pageNo",
        rows:"pageSize",
        sort:"orderBy",
        order:"order"
    },
    caption : "JqGrid模板标题"
};
ObjectTemplate.pagerSetting = {
    edit : false,
    add : false,
    del : false,
    search : false
};
//ObjectTemplate.getDwrArgs = function (postData)
//{
//    var dwrArgs = [];
//    if($.isFunction(this.p.serializeGridData)){
//        dwrArgs = this.p.serializeGridData.apply(this,[postData]);
//    }
//    var ts = this;
//    var dwrSettings = {
//        preHook:function(){
//            ts.grid.hDiv.loading = true;
//            if(ts.p.hiddengrid) { return;}
//            switch(ts.p.loadui)
//            {
//                case "disable":
//                    break;
//                case "enable":
//                    $("#load_"+$.jgrid.jqID(ts.p.id)).show();
//                    break;
//                case "block":
//                    $("#lui_"+$.jgrid.jqID(ts.p.id)).show();
//                    $("#load_"+$.jgrid.jqID(ts.p.id)).show();
//                    break;
//            }
//        },
//        postHook:function(){
//            ts.grid.hDiv.loading = false;
//            switch(ts.p.loadui) {
//                case "disable":
//                    break;
//                case "enable":
//                    $("#load_"+$.jgrid.jqID(ts.p.id)).hide();
//                    break;
//                case "block":
//                    $("#lui_"+$.jgrid.jqID(ts.p.id)).hide();
//                    $("#load_"+$.jgrid.jqID(ts.p.id)).hide();
//                    break;
//            }
//        },
//        async : true,
//        httpMethod : "POST",
//        timeout : "3000",
//        callback : function(data) {
//            ts.addJSONData(data);
//        }
//    };
//    dwrArgs[dwrArgs.length] = dwrSettings;
//    return dwrArgs;
//}