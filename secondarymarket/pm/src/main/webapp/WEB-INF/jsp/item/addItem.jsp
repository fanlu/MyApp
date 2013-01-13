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
    <link href="/static/js/fineuploader/fineuploader.css" rel="stylesheet">
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/fineuploader/js/util.js"></script>
    <script src="/static/js/fineuploader/js/button.js"></script>
    <script src="/static/js/fineuploader/js/handler.base.js"></script>
    <script src="/static/js/fineuploader/js/handler.form.js"></script>
    <script src="/static/js/fineuploader/js/handler.xhr.js"></script>
    <script src="/static/js/fineuploader/js/uploader.basic.js"></script>
    <script src="/static/js/fineuploader/js/dnd.js"></script>
    <script src="/static/js/fineuploader/js/uploader.js"></script>
    <script src="/static/js/fineuploader/js/jquery-plugin.js"></script>
    <style type="text/css">

    </style>
</head>
<body>
测试
<div id="jquery-wrapped-fine-uploader"></div>
<div id="addyoujianfujian"></div>
</body>
<script type="text/javascript">
    $('#jquery-wrapped-fine-uploader').fineUploader({
        request: {
            endpoint: '/upload',
            forceMultipart:true
        },
        multiple:true,
        validation: {
            allowedExtensions: ['jpeg', 'jpg', 'gif', 'png','xls','doc','xlsx','docx','pdf','txt'],
            sizeLimit: 40960000 // 50 kB = 50 * 1024 bytes16.
        },
        callbacks: {
            onComplete: function(id, fileName, responseJSON) {
                if (responseJSON.success) {
                    $("#addyoujianfujian").append(fileName+";");
                }
            }
        }
    });
</script>
</html>