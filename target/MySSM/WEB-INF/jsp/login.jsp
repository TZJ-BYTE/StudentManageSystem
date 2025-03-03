<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        var contextPath = "${pageContext.request.contextPath}";

        // 定义全局函数
        function showRegister() {
            console.log('showRegister called'); // 添加调试信息
            document.body.classList.add('register-mode');
        }

        function showLogin() {
            console.log('showLogin called'); // 添加调试信息
            document.body.classList.remove('register-mode');
        }

        // 确保 DOM 完全加载后再执行其他初始化代码
        document.addEventListener('DOMContentLoaded', function() {
            console.log('DOMContentLoaded event triggered'); // 添加调试信息
            // 其他初始化代码可以放在这里
        });
    </script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<div class="form-container">
    <div id="loginDiv" class="form">
        <h1>用户登录</h1>
        <button type="button" onclick="showRegister()">还没有账号？注册</button>
        <form id="loginForm" action="${pageContext.request.contextPath}/user/login" method="post">
            <label for="loginUsername">用户名:</label>
            <input type="text" id="loginUsername" name="username" required>
            <br><br>
            <label for="loginPassword">密码:</label>
            <input type="password" id="loginPassword" name="password" required>
            <br><br>
            <button type="button" onclick="login()">登录</button>
        </form>
    </div>
    <div id="registerDiv" class="form hidden">
        <h1>用户注册</h1>
        <button type="button" onclick="showLogin()">返回登录</button>
        <form id="registerForm" action="${pageContext.request.contextPath}/user/register" method="post">
            <label for="registerUsername">用户名:</label>
            <input type="text" id="registerUsername" name="username" required>
            <br><br>
            <label for="registerPassword">密码:</label>
            <input type="password" id="registerPassword" name="password" required>
            <br><br>
            <button type="button" onclick="register()">注册</button>
        </form>
        <div id="responseMessage"></div>
    </div>
</div>

</body>
</html>
