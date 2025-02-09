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
    <!-- 引入 jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 引入 toastr CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <!-- 引入 toastr JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <!-- 其他 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teacher.css">

</head>
<body>
    <h2>班级列表</h2>
    <table id="classTable">
        <thead>
            <tr>
                <th>班级ID</th>
                <th>班级名称</th>
                <th>所属院系ID</th>
                <th>班主任ID</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <!-- 数据将通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <div>
        <button onclick="loadClasses(currentPage - 1, pageSize, searchTerm)" disabled id="prevPage">上一页</button>
        <span>第 <span id="currentPage">1</span> 页 / 共 <span id="totalPages">1</span> 页</span>
        <button onclick="loadClasses(currentPage + 1, pageSize, searchTerm)" id="nextPage">下一页</button>
    </div>

    <h2>添加班级</h2>
    <form action="/class/add" method="post">
        <label>班级名称:</label>
        <input type="text" name="className"><br>
        <label>所属院系ID:</label>
        <input type="text" name="departmentId"><br>
        <label>班主任ID:</label>
        <select name="teacherId">
            <c:forEach items="${teacherList}" var="teacher">
                <option value="${teacher.id}">${teacher.name}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>
