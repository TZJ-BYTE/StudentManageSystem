<%--
  Created by IntelliJ IDEA.
  User: 十一月的肖邦
  Date: 2024/12/30
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>用户注册</h2>

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

<script>
    $(document).ready(function() {
        $('#registrationForm').submit(function(event) {
            event.preventDefault(); // 阻止表单默认提交行为

            var username = $('#username').val();
            var password = $('#password').val();

            // 准备要发送的数据
            var data = {
                "username": username,
                "password": password
            };

            // 发起AJAX POST请求
            $.ajax({
                url: '${pageContext.request.contextPath}/user/register',
                type: 'POST',
                contentType: 'application/json', // 设置请求头内容类型为JSON
                data: JSON.stringify(data), // 将JavaScript对象转换为JSON字符串
                success: function(response) {
                    // 请求成功时的处理
                    $('#responseMessage').text(response.message).css('color', 'green');
                    if(response.success){
                        location.href="${pageContext.request.contextPath}/user/success"
                    }
                },
                error: function(xhr, status, error) {
                    // 请求失败时的处理
                    $('#responseMessage').text('注册失败: ' + xhr.responseText).css('color', 'red');
                }
            });
        });
    });
</script>

</body>
</html>
