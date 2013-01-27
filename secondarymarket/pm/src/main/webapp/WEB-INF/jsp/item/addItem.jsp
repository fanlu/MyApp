<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangxin
  Date: 13-1-12
  Time: 下午6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#jquery-wrapped-fine-uploader').fineUploader({
                request: {
                    endpoint: '/upload',
                    forceMultipart:true
                },
                multiple:false,
                validation: {
                    allowedExtensions: ['jpeg', 'jpg', 'gif', 'png','xls','doc','xlsx','docx','pdf','txt'],
                    sizeLimit: 40960000 // 50 kB = 50 * 1024 bytes16.
                },
                text: {
                    uploadButton: '<i class="icon-upload icon-white"></i> 上传'
                }
            }).on('complete', function(event, id, fileName, responseJSON) {
                        if (responseJSON.success) {
                            $("#pic").val(responseJSON.urls[0]);
                        }
                    });
            var min = 260;
            var max = 400;
            if($("input[name=likeCount]").val() == ""){
                $("input[name=likeCount]").val($.fn.getRandom(min, max));
            }
            if($("input[name=collectCount]").val() == ""){
                $("input[name=collectCount]").val($.fn.getRandom(min, max));
            }
            if($("input[name=wantToBuy]").val() == ""){
                $("input[name=wantToBuy]").val($.fn.getRandom(min, max));
            }
        });
        function submitf(){
            var title = $("input[name='title']").val()
            if(title.replace(/[^\x00-\xff]/g, 'xx').length>=50){
                alert("标题超长");
                return false;
            }
            var params = $('#f').serializeObject();
            $.ajax( {
                url : "/item/save",
                method: "post",
                data : params,
//            dataType : "json",
                cache : false,
                error : function(textStatus, errorThrown) {
                    $("#dialog").dialog("close");
                    $("#list4").trigger("reloadGrid");
//                alert("系统ajax交互错误: " + textStatus + errorThrown);
                },
                success : function(data, textStatus) {
                    if(data == "success") {
                        $("#dialog").dialog("close");
                        $("#list4").trigger("reloadGrid");
                        alert("操作成功!");
                    } else {
                        alert("操作失败!");
                    }
                }
            });
        }
        function getTaobaokeDetail(){
            var num_iid = $("input[name=name]").val()
            $.get("/item/getTaobaokeDetail/"+num_iid,null,function(data){
                var item = data.taobaokeItemDetails[0].item;
                $("input[name=pic]").val(item.picUrl);
                var tbPath = data.taobaokeItemDetails[0].clickUrl;
                $("input[name=tbPath]").val(tbPath);
                $("input[name=oldPrice]").val(item.price);
                $("input[name=title]").val(item.title);
            });
        }
    </script>
</head>
<body>
<form action="/item/save" method="POST" id="f">
    <input type="button" value="保存" onclick="submitf();">
    <input type="button" value="获取" onclick="getTaobaokeDetail();">
    <input type="hidden" name="id" value="${item.id}"/>
<table>
    <tr>
        <td>淘宝numid：</td><td colspan="3"><input type="text" name="name" value="${item.name}" style="width:485px"/></td>
    </tr>
    <tr>
        <td>主题：</td><td colspan="3"><input type="text" name="title" value="${item.title}" style="width:485px"/></td>
    </tr>
    <tr>
        <td>图片：</td><td colspan="3"><input type="text" name="pic" id="pic" value="${item.pic}" style="width:485px"/><div id="jquery-wrapped-fine-uploader"></div></td>
    </tr>
    <tr>
        <td>小编：</td><td colspan="3"><textarea rows="4" cols="10" name="desc" style="width:485px">${item.desc}</textarea></td>
    </tr>
    <tr>
        <td>推广链接：</td><td colspan="3"><input type="text" name="tbPath" value="${item.tbPath}" style="width:485px"/></td>
    </tr>
    <tr>
        <td>类别：</td>
        <td>
            <select name="categoryId">
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${item.categoryId eq category.id}">selected="selected"</c:if> value="${category.id}">${category.title}</option>
                </c:forEach>
            </select>
        </td>
        <td>想买：</td><td><input type="text" name="wantToBuy" value="${item.wantToBuy}"/></td>
    </tr>
    <tr>
        <td>喜欢：</td><td><input type="text" name="likeCount" value="${item.likeCount}"/></td><td>收藏：</td><td><input type="text" name="collectCount" value="${item.collectCount}"/></td>
    </tr>
    <tr>
        <td>原价：</td><td><input type="text" name="oldPrice" value="${item.oldPrice}"/></td><td>现价：</td><td><input type="text" name="newPrice" value="${item.newPrice}"/></td>
    </tr>
    <tr><td>评价：</td>
        <td colspan="3">
    <table>
    <tr>
        <td>1<input type="hidden" name="evalList[0].id" value="${item.evalList[0].id}" /></td>
        <td><input type="text" name="evalList[0].eval" value="${item.evalList[0].eval}" /></td>
        <td>2<input type="hidden" name="evalList[1].id" value="${item.evalList[1].id}" /></td>
        <td><input type="text" name="evalList[1].eval" value="${item.evalList[1].eval}" /></td>
    </tr>
    <tr>
        <td>3<input type="hidden" name="evalList[2].id" value="${item.evalList[2].id}" /></td>
        <td><input type="text" name="evalList[2].eval" value="${item.evalList[2].eval}" /></td>
        <td>4<input type="hidden" name="evalList[3].id" value="${item.evalList[3].id}" /></td>
        <td><input type="text" name="evalList[3].eval" value="${item.evalList[3].eval}" /></td>
    </tr>
    </table>
        </td>
    </tr>
</table>

</form>
</body>
</html>