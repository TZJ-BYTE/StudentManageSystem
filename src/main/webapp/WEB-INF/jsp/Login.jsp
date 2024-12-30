<!DOCTYPE html>
<%@ page contentType="text/html; charset=gb2312"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
</head>
<body>
<h2>用户登录</h2>
<form id="registrationForm">
    <label for="username">账号:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">密码:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="button" onclick="login()">登录</button>
    <button type="button" onclick="registerUser()">注册</button>
</form>
<script>
    //跳转到注册页面
    function registerUser(){
        location.href="${pageContext.request.contextPath}/user/registerpage"
    }
    function login() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        // 确保用户输入了用户名和密码
        if (username === '' || password === '') {
            alert('请输入账号和密码');
            return;
        }
        // 创建一个要发送到服务器的JSON对象
        const userData = {
            username: username,
            password: password // 在实际应用中，密码应当在客户端加密
        };
        console.log("请求体",userData)
        // 使用fetch发送POST请求
        fetch('/StudentManageSystem_master_war/user/Login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('请求失败');
                }
                return response.json();
            })
            .then(data => {
                console.log("返回体",data)
                // 处理注册成功的响应
                alert(data.Message);
                // 可以在这里添加跳转逻辑或其他操作
                if(data.success){
                    location.href="${pageContext.request.contextPath}/user/success"
                }
                else alert("登录出错请重试")
            })
            .catch(error => {
                // 处理错误情况
                console.error('出错:', error);
                alert('出错了请重试');
            });
    }
</script>
</body>
</html>
