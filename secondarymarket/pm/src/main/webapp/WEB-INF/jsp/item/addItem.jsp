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
    <title></title>
</head>
<body>
<form action="/item/add" method="POST">
<table>
    <input type="hidden" name="id" value="${item.id}"/>
    <tr>
        <td>主题：</td><td colspan="3"><input type="text" name="title" value="${item.title}"/></td>
    </tr>
    <tr>
        <td>图片：</td><td colspan="3"><input type="text" name="pic" id="pic" value="${item.pic}"/><div id="jquery-wrapped-fine-uploader"></div></td>
    </tr>
    <tr>
        <td>小编：</td><td colspan="3"><textarea rows="4" cols="10" name="desc">${item.desc}</textarea></td>
    </tr>
    <tr>
        <td>推广链接：</td><td colspan="3"><input type="text" name="tbPath" value="${item.tbPath}"/></td>
    </tr>
    <tr>
        <td>类别：</td>
        <td colspan="3">
            <select name="categoryId">
                <c:forEach items="${categories}" var="category">
                    <option <c:if test="${item.categoryId eq category.id}">selected="selected"</c:if> value="${category.id}">${category.title}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>原价：</td><td colspan="3"><input type="text" name="oldPrice" value="${item.oldPrice}"/></td><td>现价：</td><td><input type="text" name="newPrice" value="${item.newPrice}"/></td>
    </tr>
</table>
</form>
</body>
<script type="text/javascript">
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
        callbacks: {
            onComplete: function(id, fileName, responseJSON) {
                alert(fileName);
                if (responseJSON.success) {
                    $("#pic").val(fileName);
                }
            }
        }
    });
</script>
</html>