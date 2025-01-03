<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师管理</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .action-links a {
            margin: 0 5px;
        }
        .pagination {
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
        }
    </style>
</head>
<body>
<h1>教师管理</h1>
<div class="action-links">
    <a href="/api/teacher/add">添加教师</a>
</div>
<table>
    <thead>
    <tr>
        <th>工号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>职称</th>
        <th>联系电话</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr>
            <td>${teacher.teacherId}</td>
            <td>${teacher.name}</td>
            <td>${teacher.gender}</td>
            <td>${teacher.title}</td>
            <td>${teacher.contactNumber}</td>
            <td>
                <a href="/api/teacher/edit/${teacher.teacherId}">编辑</a>
                <a href="#" onclick="confirmDelete('${teacher.teacherId}')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}&pageSize=${pageSize}">上一页</a>
    </c:if>
    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}&pageSize=${pageSize}">下一页</a>
    </c:if>
</div>

<script>
    function confirmDelete(teacherId) {
        if (confirm("您确定要删除工号为 " + teacherId + " 的教师吗？")) {
            window.location.href = "/api/teacher/delete/" + teacherId;
        }
    }
</script>
</body>
</html>
