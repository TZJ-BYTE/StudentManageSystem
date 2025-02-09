<%--
  Created by IntelliJ IDEA.
  User: tzj
  Date: 2025/2/3
  Time: 下午4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>班级管理</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/class.js"></script>
</head>
<body>
    <h2>班级列表</h2>
    <table>
        <tr>
            <th>班级ID</th>
            <th>班级名称</th>
            <th>班主任</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${classList}" var="clazz">
            <tr>
                <td>${clazz.id}</td>
                <td>${clazz.name}</td>
                <td>${clazz.teacher.name}</td>
                <td>
                    <button onclick="editClass(${clazz.id})">编辑</button>
                    <button onclick="deleteClass(${clazz.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>添加班级</h2>
    <form action="/class/add" method="post">
        <label>班级名称:</label>
        <input type="text" name="name"><br>
        <label>班主任:</label>
        <select name="teacherId">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>
