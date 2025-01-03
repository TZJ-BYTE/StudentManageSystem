<%--
  Created by IntelliJ IDEA.
  User: 十一月的肖邦
  Date: 2025/1/3
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>更改密码</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        var contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<h2>更改密码</h2>
<form id="changePasswordForm">
    <label for="userId">用户ID:</label><br>
    <input type="text" id="userId" name="userId" required><br>
    <label for="oldPassword">旧密码:</label><br>
    <input type="password" id="oldPassword" name="oldPassword" required><br>
    <label for="newPassword">新密码:</label><br>
    <input type="password" id="newPassword" name="newPassword" required><br><br>
    <input type="button" value="更新密码" onclick="updatePassword()">
</form>
<script>
    function updatePassword() {
        // 获取表单数据
        var userId = $("#userId").val();
        var oldPassword = $("#oldPassword").val(); // 在真实应用中应加密这个值
        var newPassword = $("#newPassword").val(); // 在真实应用中应加密这个值

        // 创建JSON对象
        var data = {
            "userId": userId,
            "oldPassword": oldPassword,
            "newPassword": newPassword
        };
        console.log("发送的数据是"+data)
        // 发送PUT请求
        $.ajax({
            url: contextPath+'/user/me/password',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                // 处理成功的响应
                if (response.success) {
                    if(response.success){
                        alert("密码更新成功,请重新登录");
                        location.href=contextPath+"/user/loginpage"
                    }
                } else {
                    alert("密码更新失败: " + response.message);
                }
            },
            error: function (xhr, status, error) {
                // 处理错误的响应
                alert('密码更新失败，请重试。错误信息: ' + xhr.responseText);
            }
        });
    }
</script>
</body>
</html>
