<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程管理</title>
    <!-- 引入 jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 引入.toastr CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <!-- 引入 Toastr JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <!-- 其他 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/course.css">
</head>
<body>
<h1>课程管理</h1>
<div class="action-links">
    <button class="add-button" id="addCourseButton">添加课程</button>
    <div class="search-container">
        <input type="text" id="searchTerm" placeholder="输入课程ID或课程名称进行搜索">
        <button id="searchButton">搜索</button>
    </div>
</div>
<table id="courseTable">
    <thead>
    <tr>
        <th>课程ID</th>
        <th>课程名称</th>
        <th>学分</th>
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
<!-- 引入 course.js 文件 -->
<script src="../js/course.js"></script>
<!-- 引入 allfuns.js 文件 -->
<script src="../js/allfuns.js"></script>

<!-- 添加 -->
<div id="addCourseModal" class="modal">
    <div class="modal-content">
        <h2>添加课程</h2>
        <form id="addCourseForm">

            <label for="courseName">课程名称:</label>
            <input type="text" id="courseName" name="courseName" required><br>

            <label for="courseCredits">学分:</label>
            <input type="number" id="courseCredits" name="courseCredits" required><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelAddCourse">取消</button>
        </form>
    </div>
</div>

<!-- 编辑 -->
<div id="editCourseModal" class="modal">
    <div class="modal-content">
        <h2>编辑课程</h2>
        <form id="editCourseForm">

            <label for="editCourseId">课程ID:</label>
            <input type="text" id="editCourseId" name="courseId" readonly><br>

            <label for="editCourseName">课程名称:</label>
            <input type="text" id="editCourseName" name="courseName" required><br>

            <label for="editCourseCredits">学分:</label>
            <input type="number" id="editCourseCredits" name="courseCredits" required><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelEditCourse">取消</button>
        </form>
    </div>
</div>

