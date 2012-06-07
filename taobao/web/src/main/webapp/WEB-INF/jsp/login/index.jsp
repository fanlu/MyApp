<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="qc:admins" content="45633162376646375" />
<title>web</title>
<script src="http://a.tbcdn.cn/apps/top/x/sdk.js?t=120525.js"></script> 
<script> 
TOP.init({ 
      appKey : '12675864', 
      channelUrl : window.location.protocol + '//' + window.location.hostname + '/channel.html'
      //channelUrl : 'http://localhost:8094/channel.html'  
});//每个页面只需配置一次系统参数 
</script> 
</head>
<body>
<div id="taobao-login"> </div>
<script> 
  TOP.ui("login-btn", {
    container: "#taobao-login", 
	type: "4,4", callback:{
      login: function(){},
	  logout: function(){}
    }
  });
</script>
</body>
</html>