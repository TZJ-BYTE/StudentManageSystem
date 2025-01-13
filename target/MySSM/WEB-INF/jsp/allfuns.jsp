<%--
  Created by IntelliJ IDEA.
  User: 十一月的肖邦
  Date: 2025/1/2
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>教育管理系统</title>
    <link rel="stylesheet" href="../css/styles.css"> <!-- 添加CSS文件引入 -->
    <link rel="stylesheet" href="../css/allfuns.css"> <!-- 添加新的CSS文件引入 -->
</head>
<body>

<div class="header">
    <div class="logo">系统Logo</div>
    <div class="title">学生管理系统</div>
    <div class="dropdown">
        <div class="dropbtn">
            <img src="../images/1.png" class="user-info-img" alt="用户头像">
            <div>${usermsg}</div>
        </div>
        <div class="dropdown-content">
            <div class="user-action" onclick="showPopupLayer()">我的</div>

            <div class="user-action" onclick="navigate('logout')">退出登录</div>
        </div>
    </div>
</div>

<div class="sidebar">
    <ul>
        <li onclick="navigate('home')">首页</li>
        <li onclick="navigate('teacher')">教师管理</li>
        <li onclick="navigate('student')">学生管理</li>
        <li onclick="navigate('class')">班级管理</li>
        <li onclick="navigate('course')">课程管理</li>
        <li onclick="navigate('college')">学院管理</li>
    </ul>
</div>

<div class="content" id="main-content">
    <h1>欢迎使用教育管理系统</h1>
    <p>请从左侧菜单选择一个模块。</p>
</div>
<%--弹出层的标签--%>
<!-- 遮罩层 -->
<div id="overlay"></div>
<!-- 按钮用于触发弹出层 -->
<button id="popup-button">点击弹出</button>
<!-- 弹出层，初始时隐藏 -->
<div id="popup-layer" style="display:none;">
    <div class="pop-header">
        <div class="pop-title">个人信息</div>
        <div id="close-button" class="pop-close" style="cursor:pointer;">×</div>
    </div>
    <div class="pop-body-list-item">
        <div class="pop-body-text">
            <text style="color: #6d6d6d;padding-right: 20px">
                用户头像:
            </text>
        </div>
        <div class="pop-body-image">
            <img class="pop-body-image" src="../images/1.png" style="vertical-align: middle;">
        </div>
    </div>
    <div class="pop-body-list-item" style="display: flex; align-items: center;">
        <text style="color: #6d6d6d;padding-right: 20px;">用户昵称:</text>
        <div id="nicknameDisplay">${usermsg}</div>
        <img id="editNickname" style="width: 17px;height: 17px;padding-left: 15px; cursor:pointer;"
             src="../images/pen.png" alt="Edit">
        <div id="nicknameInputContainer" style="display:none; flex-grow: 1; position: relative;">
            <input type="text" id="nicknameInput" value="${usermsg}"
                   style="width: 100%; height: 34px; background-color: #f8f8fb; border-color: #fb326c;padding: 0; box-sizing: border-box;">
            <button id="confirmBtn"
                    style="display:none; position: absolute; right: 0; top: 0; height: 34px; border: none; background: none;">
                确认
            </button>
        </div>
    </div>
    <div class="pop-body-list-item">
        <text style="color: #6d6d6d;padding-right: 20px">
            手机号码
        </text>
        <div>
            -
        </div>
    </div>
    <div class="pop-body-list-item">
        <text style="color: #6d6d6d;padding-right: 20px">
            邮箱号码
        </text>
        <div>
            -
        </div>
    </div>
    <div class="pop-header" style="padding-top: 80px">
        <div class="pop-title">更改密码</div>
    </div>
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
</div>
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="../js/allfuns.js"></script>
<%--弹出层的script--%>

</body>
</html>