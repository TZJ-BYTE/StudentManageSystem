<%--
  Created by IntelliJ IDEA.
  User: 十一月的肖邦
  Date: 2025/2/12
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门管理</title>
    <!-- 引入 jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 引入.toastr CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <!-- 引入 Toastr JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <!-- 其他 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/depart.css">
</head>
<body>
<h1>部门管理</h1>
<div class="action-links">
    <button class="add-button" id="addDepartmentButton">添加部门</button>
    <div class="search-container">
        <input type="text" id="searchTerm" placeholder="输入部门ID或部门名称进行搜索">
        <button id="searchButton">搜索</button>
    </div>
</div>
<table id="departmentTable">
    <thead>
    <tr>
        <th>部门ID</th>
        <th>部门名称</th>
        <th>地址</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <!-- 动态生成的行将插入到这里 -->
    </tbody>
</table>
<div class="pagination"
     data-current-page="${empty param.page ? 1 : param.page}"
     data-total-pages="${empty totalPages ? 1 : totalPages}"
     data-page-size="${empty param.pageSize ? 10 : param.pageSize}">
    <c:if test="${param.page > 1}">
        <a href="?page=${param.page - 1}&pageSize=${param.pageSize}">上一页</a>
    </c:if>
    <span>当前页: <span id="currentPage">${empty param.page ? 1 : param.page}</span> / 总页数: <span id="totalPages">${empty totalPages ? 1 : totalPages}</span></span>
    <c:if test="${param.page < totalPages}">
        <a href="?page=${param.page + 1}&pageSize=${param.pageSize}">下一页</a>
    </c:if>
</div>
<script>
    // 将 contextPath 传递给全局变量
    var contextPath = "${pageContext.request.contextPath}";
</script>
<!-- 引入 department.js 文件 -->
<script src="../js/depart.js"></script>
<!-- 引入 allfuns.js 文件 -->
<script src="../js/allfuns.js"></script>

<!-- 添加 -->
<div id="addDepartmentModal" class="modal">
    <div class="modal-content">
        <h2>添加部门</h2>
        <form id="addDepartmentForm">

            <label for="departmentName">部门名称:</label>
            <input type="text" id="departmentName" name="departmentName" required><br>

            <label for="departmentAddress">地址:</label>
            <input type="text" id="departmentAddress" name="departmentAddress" required><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelAddDepartment">取消</button>
        </form>
    </div>
</div>

<!-- 编辑 -->
<div id="editDepartmentModal" class="modal">
    <div class="modal-content">
        <h2>编辑部门</h2>
        <form id="editDepartmentForm">

            <label for="editDepartmentId">部门ID:</label>
            <input type="text" id="editDepartmentId" name="departmentId" readonly><br>

            <label for="editDepartmentName">部门名称:</label>
            <input type="text" id="editDepartmentName" name="departmentName" required><br>

            <label for="editDepartmentAddress">地址:</label>
            <input type="text" id="editDepartmentAddress" name="departmentAddress" required><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelEditDepartment">取消</button>
        </form>
    </div>
</div>
</body>
</html>

