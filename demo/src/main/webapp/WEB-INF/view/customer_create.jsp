<%--
  Created by IntelliJ IDEA.
  User: zxm
  Date: 2018/12/14
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理-- 创建客户</title>
</head>
<body>

    <form method="post" enctype="multipart/form-data" action="/customerUpload">
        <label>文件：</label>
        <input type="file" name="file"  />
        <button type="submit">提交</button>
    </form>
</body>
</html>
