<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin: 10px 0;
        }
        .login-container button:hover {
            background-color: #0056b3;
        }
    </style>
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
