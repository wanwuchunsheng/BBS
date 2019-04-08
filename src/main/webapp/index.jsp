<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
pageContext.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
<head>
  <title>首页</title>
  <meta name="baidu-site-verification" content="Kz1C4T16v6" />
</head>
<script>
   //跳转后端
   //window.location.href="${path}/login/index";
   //跳转前端
   window.location.href="${path}/view/index";
</script>
<body>
</body>
</html>
