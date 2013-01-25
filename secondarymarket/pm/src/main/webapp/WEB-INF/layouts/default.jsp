<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>project<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/css/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<!--<link href="/static/js/jqueryui/jquery-ui-1.9.2.custom.min.css" rel="stylesheet">-->
<link href="/static/js/jqueryui/jquery-ui-1.10.0.custom.min.css" rel="stylesheet">
<link href="/static/js/jqgrid/ui.jqgrid.css" rel="stylesheet">
<link href="/static/css/styles.css" rel="stylesheet">
<!--<script src="/static/js/jquery-1.8.3.min.js"></script>-->
<script src="/static/js/jquery.extend.js"></script>
<script type="text/javascript" src="/static/css/bootstrap/js/bootstrap.min.js"></script>
<!--<script src="/static/js/jqueryui/jquery-ui-1.9.2.custom.min.js"></script>-->
<script src="/static/js/jqueryui/jquery-ui-1.10.0.custom.min.js"></script>
<script src="/static/js/jqgrid/grid.locale-cn.js"></script>
<script src="/static/js/jqgrid/jquery.jqGrid.min.js"></script>
<script src="/static/js/jqgrid/ObjectTemplate.js"></script>
<link href="/static/js/fineuploader/fineuploader.css" rel="stylesheet">
<script src="/static/js/fineuploader/js/util.js"></script>
<script src="/static/js/fineuploader/js/button.js"></script>
<script src="/static/js/fineuploader/js/handler.base.js"></script>
<script src="/static/js/fineuploader/js/handler.form.js"></script>
<script src="/static/js/fineuploader/js/handler.xhr.js"></script>
<script src="/static/js/fineuploader/js/uploader.basic.js"></script>
<script src="/static/js/fineuploader/js/dnd.js"></script>
<script src="/static/js/fineuploader/js/uploader.js"></script>
<script src="/static/js/fineuploader/js/jquery-plugin.js"></script>

<sitemesh:head />

</head>

<body>
	<div class="container" style="width:1200px">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="row">
			<%@ include file="/WEB-INF/layouts/left.jsp"%>
			<div id="main" class="span12">
				<sitemesh:body />
			</div>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>

</body>
</html>