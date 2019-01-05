<%--
  Created by IntelliJ IDEA.
  User: zxm
  Date: 2018/12/24
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

    <shiro:guest>
        <p>身份：游客</p>
        <a href="<c:url value="/login" />">登陆</a>
        <<a href="<c:url value="/register" />">注册</a>
    </shiro:guest>


    <shiro:user>
        <p>身份：<shiro:principal></shiro:principal></p>
        <a href="<c:url value="/space" /> ">空间</a>
        <a href="<c:url value="/logout" />">退出</a>
    </shiro:user>



</body>
</html>
