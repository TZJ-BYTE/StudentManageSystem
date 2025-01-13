<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Password Update Form</title>
    <style>

        .input-container {
            margin-bottom: 10px;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .input-container label {
            color: #6d6d6d;

        }
        .input-container input {
            width: 100px;
            background-color: #f8f8fb;
            padding: 5px 10px;
            border: 1px solid #d9d9d9;
            border-radius: 4px;
        }
        .input-container input:focus {
            border-color: #fb326c;
        }
        .submit-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            display: flex;
            margin-left: 180px;
        }
        .submit-button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function checkAndUpdatePassword() {
            var originalPassword = document.getElementById('originalPassword').value;
            var newPassword = document.getElementById('newPassword').value;
            var confirmPassword = document.getElementById('confirmPassword').value;

            if (newPassword !== confirmPassword) {
                alert('两次输入的密码不同，请重新输入。');
                return;
            }

            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/updatepasswd?originalPassword=' + encodeURIComponent(originalPassword) + '&newPassword=' + encodeURIComponent(newPassword), true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        alert('密码更新成功！');
                    } else {
                        alert('密码更新失败：' + xhr.statusText);
                    }
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>

<form onsubmit="checkAndUpdatePassword(); return false;">
    <div style="width: 300px;height: 300px;">
        <div class="input-container">
            <label for="originalPassword">原密码：</label>
            <input type="password" id="originalPassword" required>
        </div>

        <div class="input-container">
            <label for="newPassword">新密码：</label>
            <input type="password" id="newPassword" required>
        </div>

        <div class="input-container">
            <label for="confirmPassword">确认新密码：</label>
            <input style="margin-right: 9px" type="password" id="confirmPassword" required>
        </div>

        <input type="submit" value="确认修改" class="submit-button">
    </div>
</form>

</body>
</html>
