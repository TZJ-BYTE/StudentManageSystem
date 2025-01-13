<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教师管理</title>
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
<h1>教师管理</h1>
<div class="action-links">
    <button class="add-button" id="addTeacherButton">添加教师</button>
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
        <th>研究领域</th>
        <th>联系电话</th>
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
<!-- 引入 teacher.js 文件 -->
<script src="${pageContext.request.contextPath}/js/teacher.js"></script>
<!-- 引入 allfuns.js 文件 -->
<script src="${pageContext.request.contextPath}/js/allfuns.js"></script>

<!-- 添加 -->
<div id="addTeacherModal" class="modal">
    <div class="modal-content">
        <h2>添加教师</h2>
        <form id="addTeacherForm">
            <label for="teacherName">姓名:</label>
            <input type="text" id="teacherName" name="name" required><br>

            <label for="teacherGender">性别:</label>
            <select id="teacherGender" name="gender" required>
                <option value="男">男</option>
                <option value="女">女</option>
            </select><br>

            <label for="teacherTitle">职称:</label>
            <input type="text" id="teacherTitle" name="title"><br>

            <label for="teacherFieldOfStudy">研究领域:</label>
            <input type="text" id="teacherFieldOfStudy" name="fieldOfStudy"><br>

            <label for="teacherContactNumber">联系电话:</label>
            <input type="text" id="teacherContactNumber" name="contactNumber"><br>

            <label for="teacherDepartmentId">部门:</label>
            <select id="teacherDepartmentId" name="departmentId" required>
                <option value="">请选择部门</option>
                <c:if test="${not empty departments}">
                    <c:forEach items="${departments}" var="department">
                        <option value="${department.departmentId}">${department.departmentName}</option>
                    </c:forEach>
                </c:if>
                <c:if test="${empty departments}">
                    <option value="">无部门数据</option>
                </c:if>
            </select><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelAddTeacher">取消</button>
        </form>

    </div>
</div>

<%--<!-- 引入 popup.jsp 的内容 -->--%>
<%--<jsp:include page="popup.jsp"/>--%>
