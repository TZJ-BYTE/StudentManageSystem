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
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .header {
            background-color: #333;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header .logo {
            font-size: 24px;
        }
        .header .user-info {
            display: flex;
            align-items: center;
        }
        .header .user-info img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .sidebar {
            width: 200px;
            background-color: #f4f4f4;
            height: 100vh;
            position: fixed;
            overflow-y: auto;
        }
        .content {
            margin-left: 200px;
            padding: 20px;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
        }
        .sidebar ul li {
            padding: 10px;
            cursor: pointer;
        }
        .sidebar ul li:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>

<div class="header">
    <div class="logo">系统Logo</div>
    <div class="title">教育管理系统</div>
    <div class="user-info">
        <img src="user-avatar.png" alt="用户头像">
        <div>用户名</div>
        <div style="margin-left: 10px; cursor: pointer;">通知</div>
        <div style="margin-left: 10px; cursor: pointer;">设置</div>
        <div style="margin-left: 10px; cursor: pointer;">退出登录</div>
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
    function navigate(module) {
        // 这里模拟前端路由，根据点击的菜单项来更新内容区
        var content = document.getElementById('main-content');
        switch (module) {
            case 'home':
                content.innerHTML = '<h1>首页</h1><p>这里是系统的首页。</p>';
                break;
            case 'teacher':
                content.innerHTML = '<h1>教师管理</h1><p>这里是教师管理模块。</p>';
                break;
            case 'student':
                content.innerHTML = '<h1>学生管理</h1><p>这里是学生管理模块。</p>';
                break;
            case 'class':
                content.innerHTML = '<h1>班级管理</h1><p>这里是班级管理模块。</p>';
                break;
            case 'course':
                content.innerHTML = '<h1>课程管理</h1><p>这里是课程管理模块。</p>';
                break;
            case 'college':
                content.innerHTML = '<h1>学院管理</h1><p>这里是学院管理模块。</p>';
                break;
            default:
                content.innerHTML = '<h1>页面未找到</h1><p>抱歉，您请求的页面不存在。</p>';
        }
    }
</script>

</body>
</html>
