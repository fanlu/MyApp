<%--
  Created by IntelliJ IDEA.
  User: wangxin
  Date: 13-1-6
  Time: 下午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="com.mmtzj.util.Constant" %>
<%@ page import="com.mmtzj.util.QQConstant" %>
<c:set var="staticUrl" value="<%=Constant.staticUrl%>"/>
<c:set var="appid" value="<%=QQConstant.APP_ID_SM%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Language" content="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <title>妈妈跳蚤街</title>
    <link rel="stylesheet" type="text/css" href="${staticUrl}/styles/style.css">
    <script type="text/javascript" src="${staticUrl}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="http://fusion.qq.com/fusion_loader?appid=${appid}&platform=qzone"></script>
    <script type="text/javascript">
        function openShareDialog(iid,type){
            fusion2.dialog.share
            ({
                url:'http://rc.qzone.qq.com/${appid}',
                showcount:'0',/*是否显示分享总数,显示：'1'，不显示：'0' */
                desc:$("#desc_"+iid).text(),/*默认分享理由(可选)*/
                title:$("#title_"+iid).text(),/*分享标题(可选)*/
                site:'妈妈跳蚤街',/*分享来源 如：腾讯网(可选)*/
                pics :$("#pic_"+iid).attr("src"),
                summary:'',
                context:"share",
                onShown:  function (opt){
                },
                onSuccess : function (opt){
                    $.get("/qqapp/ishare/"+type, function(data) {
                    });
                },
                onCancel : function (opt){
                },
                onClose : function (opt){
                }
            });
        }

        function share(iid){
            var snum = $('#share_'+iid).html();
            snum = parseInt(snum) + 1;
            $('#share_'+iid).html(snum);
            $.ajax({
                url: "/qqapp/ilike",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {
                    'iid': iid
                },
                type: 'POST',
                success: function(b) {
                },
                error: function(transport) {
                    //alert("服务器繁忙，请稍候重试!");
                }
            });
            openShareDialog(iid,"ilike");
        }
        function collect(iid){
            var snum = $('#collect_'+iid).html();
            snum = parseInt(snum) + 1;
            $('#collect_'+iid).html(snum);
            $.ajax({
                url: "/qqapp/icollect",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {
                    'iid': iid
                },
                type: 'POST',
                success: function(b) {
                },
                error: function(transport) {
                    //alert("服务器繁忙，请稍候重试!");
                }
            });
            openShareDialog(iid,"icollect");
        }
        function showItems(itype){
            $('.current').removeClass('current');
            $('.nav ul li').eq(itype).addClass('current');
            $('.itemtype_0').css('display','none');
            $('.itemtype_0.fix').prependTo(".wrap_list");
            $('.middletip').remove();
            if(itype==2){
                $('.itemtype_2').css('display','block');
                $('<p class="middletip">每天推荐5款包包和5款美鞋，下面是精选出来的5款美鞋哦~</p>').appendTo('.wrap_list');
                $('.itemtype_3').appendTo(".wrap_list");
                $('.itemtype_3').css('display','block');
            }else if(itype==3){
                $('.itemtype_3').css('display','block');
                $('<p class="middletip">每天推荐5款美鞋和5款包包，下面是精选出来的5款包包哦~</p>').appendTo('.wrap_list');
                $('.itemtype_2').appendTo(".wrap_list");
                $('.itemtype_2').css('display','block');
            }else if(itype==4){
                $('.itemtype_4').css('display','block');
                $('<p class="middletip">每天推荐5样配饰下和5样美妆，面是精选出来的5款美妆哦~</p>').appendTo('.wrap_list');
                $('.itemtype_5').appendTo(".wrap_list");
                $('.itemtype_5').css('display','block');
            }else if(itype==5){
                $('.itemtype_5').css('display','block');
                $('<p class="middletip">每天推荐5样美妆和5样配饰，下面是精选出来的5款配饰哦~</p>').appendTo('.wrap_list');
                $('.itemtype_4').appendTo(".wrap_list");
                $('.itemtype_4').css('display','block');
            }else{
                $('.itemtype_'+itype).css('display','block');
            }
        }
        function togo(tmpurl,iid){
            $.ajax({
                url: "/qqapp/iclick",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: {
                    'iid': iid
                },
                type: 'POST',
                success: function(b) {
                },
                error: function(transport) {
                    //alert("服务器繁忙，请稍候重试!");
                }
            });
        }
    </script>

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
                    <li><a href="javascript:void('nav${category.name}');" onclick="javascript:showItems('${category.id}');return false;">${category.title}</a></li>
                </c:forEach>
            </ul>
            <div class="clear"></div>
            <div class="subnav"></div>
        </div>
    </div>

    <div class="bd" style="padding-top:0px;">
        <div class="tbanner"><img src="${staticUrl}/images/topbanner.png"></div>
        <div class="wrap_list">
    <c:forEach items="${items}" var="item">
    <div class="wrap_box itemtype_0 itemtype_${item.categoryId} ">
        <p class="wrap_hd"><a href="${item.tbPath}" onclick="javascript:togo('${item.tbPath}',${item.id});" target="_blank">
            <i class="item_tip_5 wrap_hd_tips"></i><span id="title_${item.id}">${item.title}</span>
            <em class="bold" style="text-decoration:line-through;color: orange;font-weight: normal;">原价 ${item.oldPrice}元</em>&nbsp;&nbsp;&nbsp;<em class="bold">现价 ${item.newPrice}元</em></a></p>
        <ul class="wrap_bd">
            <li class="fl w310">
                <a href="${item.tbPath}" onclick="javascript:togo('${item.tbPath}',${item.id});" target="_blank">
                    <!--<img id="pic_${item.id}" src="${item.pic}"></a>-->
                    <img id="pic_${item.id}" src="http://i1.mmtzj.com/images/20130114/20130114223537.png"></a>
            </li>
            <li class="fr">
                <p class="item_desc"><span class="orange">小编推荐：</span><span id="desc_${item.id}">${item.desc}</span></p>
                <div class="item_comm">
                    <c:forEach items="${item.evalList}" var="eval">
                    <div><img src="${eval.id}"><span>${eval.eval}</span></div>
                    </c:forEach>
                </div>
                <p class="wrap_bd_bottom_2">
                    已有<span class="pink">336</span>人想买
                </p>
                <p class="wrap_bd_bottom">
                    <span class="fl">
                        <a class="share_btn pngfix fl" href="javascript:void('${item.id}_00');" onclick="javascript:share(${item.id})"></a>
                        <a class="fl num_lico" href="javascript:void('${item.id}_01');" onclick="javascript:share(${item.id});" id="share_${item.id}">${item.likeCount}</a>
                        <i class="num_rico"></i>
                    </span>
                    <span class="fl">
                        <a class="collect_btn pngfix fl" href="javascript:void('${item.id}_10');" onclick="javascript:collect(${item.id});"></a>
                        <!--window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http%3A%2F%2Frc%2eqzone%2eqq%2ecom%2Fmyhome%2F%3Fpid%3D13892%26via%3DQZ%2eASSISTANT%2eFEED%2eSHARE&amp;title='+encodeURIComponent('美发工具海绵卷发棒')+'&amp;pics='+encodeURIComponent('http://img02.taobaocdn.com/bao/uploaded/i2/12732018826909138/T1WywuXgXaXXXXXXXX_!!0-item_pic.jpg_310x310.jpg')+'&amp;summary='+encodeURIComponent('')+'&amp;desc='+encodeURIComponent('使用方便简洁，百变发型随心所欲，真的很实用哦~'),'_blank','scrollbars=no,width=600,height=560,left=175,top=70,status=no,resizable=yes');return false;-->
                        <a class="fl num_lico" href="javascript:void('${item.id}_11');" onclick="javascript:collect(${item.id});" id="collect_${item.id}">${item.collectCount}</a>
                        <i class="num_rico"></i></span>
                    <a class="tobuy_btn fr" href="${item.tbPath}" onclick="javascript:togo('${item.tbPath}',${item.id});" target="_blank">
                        <span style="text-align:center;float:left;width:66px;height:35px;font-size:16px;">￥${item.newPrice}</span><span style="text-align:center;float:left;width:64px;height:35px;font-size:14px;">去购买</span>
                    </a>
                </p>
            </li>
            <div class="clear"></div>
        </ul>
        <div class="separate"></div>
    </div>
    </c:forEach>
    <script type="text/javascript">
        var itemtype1 = parseInt('${itemTypes.itemtype1}');
        var itemtype2 = parseInt('${itemTypes.itemtype2}');
        var itemtype3 = parseInt('${itemTypes.itemtype3}');
        var itemtype4 = parseInt('${itemTypes.itemtype4}');
        var itemtype5 = parseInt('${itemTypes.itemtype5}');
        var itemplus = parseInt('0');
        var item1toshow = 10;
        var item2toshow = 5;
        var item3toshow = 5;
        var item4toshow = 5;
        var item5toshow = 5;
        var tmparray= new Array();
        function getRandom(maxs,mins){  //随机生成maxs到mins之间的数
            return Math.round(Math.random()*(maxs-mins))+mins;
        }
        function getArray(count,maxs,mins){
            while(tmparray.length < count){
                var temp = getRandom(maxs,mins);
                if($.inArray(temp, tmparray)==-1){
                    tmparray.push(temp);
                }
            }
            return tmparray;
        }
        function random_select(itemtype,itemtypes,amount){
            var arr = new Array();
            var select_bet_times = $("#select_bet_times").val();
            var arr= getArray(amount,itemtypes,0);
            for(var i=0;i<arr.length;i++){
                //$('.itemtype_'+itemtype).eq(arr[i]).css('display','none');
                $('.itemtype_'+itemtype).eq(arr[i]).addClass('none');
            }
            tmparray.length=0;
        }
        function itemshow(){
            var itemcounts = 0;
            if(itemtype1>item1toshow){
                random_select(1,(itemtype1-1),(itemtype1-item1toshow));
                itemcounts += item1toshow;
            }else{
                itemcounts += itemtype1;
            }
            if(itemtype2>item2toshow){
                random_select(2,(itemtype2-1),(itemtype2-item2toshow));
                itemcounts += item2toshow;
            }else{
                itemcounts += itemtype2;
            }
            if(itemtype3>item3toshow){
                random_select(3,(itemtype3-1),(itemtype3-item3toshow));
                itemcounts += item3toshow;
            }else{
                itemcounts += itemtype3;
            }
            if(itemtype4>item4toshow){
                random_select(4,(itemtype4-1),(itemtype4-item4toshow));
                itemcounts += item4toshow;
            }else{
                itemcounts += itemtype4;
            }
            if(itemtype5>item5toshow){
                random_select(5,(itemtype5-1),(itemtype5-item5toshow));
                itemcounts += item5toshow;
            }else{
                itemcounts += itemtype5;
            }
            $('.fix').removeClass('none');
            $('.itemtype_1.none').remove();
            $('.itemtype_2.none').remove();
            $('.itemtype_3.none').remove();
            $('.itemtype_4.none').remove();
            $('.itemtype_5.none').remove();
            //$('.paipai').appendTo(".wrap_list");
            itemcounts += itemplus;
        }
        itemshow();
    </script>
    </div>
    <div class="c mt15"><img src="${staticUrl}/images/bmuyoule.png"></div>
    <div class="tbanner" style="position:relative;width:678px;height:217px;background:url('${staticUrl}/images/bottombanner2.png') center center no-repeat;">
        <a style="position:absolute;left:28px;top:60px;width:100px;height:120px;cursor:pointer;display:block;" href="http://www.taobao.com/go/chn/tbk_channel/lady.php?pid=mm_27508566_3117978_10377431&amp;mode=86" target="_blank"></a>
        <a style="position:absolute;left:155px;top:60px;width:100px;height:120px;cursor:pointer;display:block;" href="http://s.click.taobao.com/t_8?e=7HZ5x%2BOzffaf8CdCl42apBmoFQ%3D%3D&amp;p=mm_27508566_0_0" target="_blank"></a>
        <a style="position:absolute;left:290px;top:60px;width:100px;height:120px;cursor:pointer;display:block;" href="http://www.taobao.com/go/chn/tbk_channel/jewelry.php?pid=mm_27508566_3117978_10377431&amp;mode=86" target="_blank"></a>
        <a style="position:absolute;left:420px;top:60px;width:100px;height:120px;cursor:pointer;display:block;" href="http://www.taobao.com/go/chn/tbk_channel/beauty.php?pid=mm_27508566_3117978_10377431&amp;mode=86" target="_blank"></a>
        <a style="position:absolute;left:545px;top:60px;width:100px;height:120px;cursor:pointer;display:block;" href="http://s.click.taobao.com/t_8?e=7HZ5x%2BOzdZdHgllS0RLJLXvytA%3D%3D&amp;p=mm_27508566_0_0" target="_blank"></a>
    </div>
    </div>
</div>
</body>
</html>