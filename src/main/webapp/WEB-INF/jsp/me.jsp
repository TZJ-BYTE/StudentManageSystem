<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户信息查询</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>查询用户信息</h2>

<form id="userInfoForm">
    <label for="username">用户名:</label>
    <input type="text" id="username" name="username" required>
    <button type="submit">查询</button>
    <button type="button" onclick="login()">返回登录</button>
    <button type="button" onclick="mepassword()">修改密码</button>
</form>

<div id="userInfo"></div>
<div id="responseMessage"></div>

<script>
function login() {
    location.href = "${pageContext.request.contextPath}/user/loginpage";
}
    $(document).ready(function() {
        $('#userInfoForm').submit(function(event) {
            event.preventDefault(); // 阻止表单默认提交行为

            var username = $('#username').val();

            // 准备要发送的数据
            var data = {
                "username": username
            };

            // 发起AJAX POST请求以获取用户信息
            $.ajax({
                url: '${pageContext.request.contextPath}/user/me',
                type: 'POST',
                contentType: 'application/json', // 设置请求头内容类型为JSON
                data: JSON.stringify(data), // 将JavaScript对象转换为JSON字符串
                success: function(response) {
                    // 请求成功时，将用户信息显示在页面上
                    var userInfoHtml = '<p><strong>用户ID:</strong> ' + response.userId + '</p>' +
                        '<p><strong>用户名:</strong> ' + response.username + '</p>';
                    $('#userInfo').html(userInfoHtml);
                },
                error: function(xhr, status, error) {
                    // 请求失败时显示错误信息
                    $('#responseMessage').text('无法获取用户信息: ' + xhr.responseText).css('color', 'red');
                }
            });
        });
    });
    function mepassword(){
        location.href="${pageContext.request.contextPath}"+"/user/me/passwordpage"
    }
</script>

</body>
</html>
