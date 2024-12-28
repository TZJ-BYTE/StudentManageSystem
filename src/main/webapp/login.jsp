<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<div class="login-container">
    <h2>用户登录</h2>
    <form action="${pageContext.request.contextPath}/login" id="loginForm" method="post">
        <input type="text" id="username" name="username" placeholder="用户名" required>
        <input type="password" id="password" name="password" placeholder="密码" required>
        <button type="submit" onclick="loginUser(event)">登录</button>
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/register.jsp'">注册新账号</button>
    </form>

</div>
</body>
</html>
