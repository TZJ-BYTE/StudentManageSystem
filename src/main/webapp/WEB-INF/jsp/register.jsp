<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
    <script>
        // 定义上下文路径变量
        var contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>

<div class="form-container">
    <div class="form-header">
        <h2>用户注册</h2>
        <span>已经在系统中？<a href="${pageContext.request.contextPath}/user/loginpage">登录</a></span>
    </div>

    <form id="registrationForm">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <button type="submit">注册</button>
    </form>

    <div id="responseMessage"></div>
</div>

</body>
</html>
