<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teacher.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>教师管理</h1>
<div class="action-links">
    <a href="${pageContext.request.contextPath}/teacher/add" class="add-button">添加教师</a>
    <div class="search-container">
        <input type="text" id="searchName" placeholder="按姓名搜索" class="search-input">
        <input type="text" id="searchId" placeholder="按工号搜索" class="search-input">
        <button type="button" id="searchButton" class="search-button">搜索</button>
    </div>
</div>
<table id="teacherTable">
    <thead>
    <tr>
        <th>工号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>职称</th>
        <th>联系电话</th>
        <th>研究领域</th>
        <th>部门ID</th>
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
<!-- 引入外部 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/teacher.js"></script>
</body>
</html>