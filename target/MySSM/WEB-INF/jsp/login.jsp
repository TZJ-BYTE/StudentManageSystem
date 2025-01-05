<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<div class="form-container">
    <div class="form-header">
        <h2>用户登录</h2>
        <span>还没有账号？<a href="${pageContext.request.contextPath}/user/registerpage">注册</a></span>
    </div>

    <form id="loginForm">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>
        <br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <button type="button" onclick="login()">登录</button>
    </form>

    <div id="responseMessage"></div>
</div>

</body>
</html>