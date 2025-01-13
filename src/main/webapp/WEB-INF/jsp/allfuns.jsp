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
    <link rel="stylesheet" href="../css/allfuns.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="header">
    <div class="logo">系统Logo</div>
    <div class="title">学生管理系统</div>
<div class="user-info">
    <img src="../images/1.png" alt="用户头像">
    <div>用户名</div>
    <div class="user-action" onclick="navigate('profile')" style="margin-left: 10px;">我的</div>
    <div class="user-action" onclick="navigate('settings')" style="margin-left: 10px;">设置</div>
    <div class="user-action" onclick="navigate('logout')" style="margin-left: 10px;">退出登录</div>
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
<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="../js/teacher.js"></script>
<script src="../js/allfuns.js"></script>

</body>
</html>