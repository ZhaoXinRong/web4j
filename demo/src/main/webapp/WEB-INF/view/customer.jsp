<%--
  Created by IntelliJ IDEA.
  User: zxm
  Date: 2018/12/14
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户管理-- 客户列表</title>
</head>
<body>
    <%--<table border="1">
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>操作</th>
        </tr>
        <c:forEach var="customer" items="${customerList}" >
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contact}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/customer_edit?id=${customer.id}">编辑</a>
                    <<a href="${pageContext.request.contextPath}/customer_delete?id=${customer.id}">删除</a>

                </td>
            </tr>

        </c:forEach>
    </table>--%>

    ${data.id}


</body>
</html>
