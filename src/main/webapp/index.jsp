<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
pageContext.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>海伦社区-首页</title>
<meta name="keywords" content="海伦社区,武东社区,海伦国际业主论坛,海伦国际论坛,武汉海伦国际,青山海伦国际,武东海伦国际,武汉青山海伦国际,武汉青山海伦国际论坛,青山海伦国际论坛"/>
<meta name="description" content="武汉青山海伦国际论坛,方便广大业主相互交流,分享生活。海伦国际欢迎各业主QQ【总】群:645952730"/>
<meta name="author" content="云朵家团队" />
<meta name="baidu-site-verification" content="Kz1C4T16v6" />
</head>
<script>
   //跳转前端
   window.location.href="${path}/view/index";
</script>
<body>
</body>
</html>
