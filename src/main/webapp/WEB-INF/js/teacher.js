$(document).ready(function() {
    // 从HTML元素中读取数据
    var paginationElement = $(".pagination");
    var currentPage = parseInt(paginationElement.data("current-page"), 10) || 1;
    var totalPages = parseInt(paginationElement.data("total-pages"), 10) || 1;
    var pageSize = parseInt(paginationElement.data("page-size"), 10) || 10;

    function loadTeachers(page, pageSize, searchName, searchId) {
        console.log("Loading teachers for page: " + page);
        $.ajax({
            url: contextPath + "/teacher/findall",
            type: "GET",
            data: {
                page: page,
                pageSize: pageSize,
                name: searchName,
                id: searchId
            },
            success: function(response) {
                var teachers = response.teachers;
                var tbody = $("#teacherTable tbody");
                tbody.empty();
                if (teachers && teachers.length > 0) {
                    teachers.forEach(function(teacher) {
                        var row = "<tr>" +
                            "<td>" + teacher.teacherId + "</td>" +
                            "<td>" + teacher.name + "</td>" +
                            "<td>" + teacher.gender + "</td>" +
                            "<td>" + teacher.title + "</td>" +
                            "<td>" + teacher.contactNumber + "</td>" +
                            "<td>" + teacher.fieldofStudy + "</td>" +
                            "<td>" + teacher.departmentid + "</td>" +
                            "<td class=\"action-links\">" +
                                "<a href=\"" + contextPath + "/teacher/edit/" + teacher.teacherId + "\" class=\"btn-edit\">编辑</a>" +

                                "<a href=\"#\" onclick=\"confirmDelete('" + teacher.teacherId + "')\" class=\"btn-delete\">删除</a>" +

                                "<a href=\"" + contextPath + "/teacher/detail/" + teacher.teacherId + "\" class=\"btn-detail\">详情</a>" +
                            "</td>" +
                            "</tr>";
                        tbody.append(row);
                    });
                } else {
                    tbody.append("<tr><td colspan='8'>没有找到教师数据</td></tr>");
                }

                // 更新分页信息
                $("#currentPage").text(response.currentPage || 1);
                $("#totalPages").text(response.totalPages || 1);
            },
            error: function(xhr, status, error) {
                console.error("加载教师数据失败:", xhr.responseText); // 更详细的错误信息
                alert("加载教师数据失败: " + error);
            }
        });
    }

    // 初始化加载
    loadTeachers(currentPage, pageSize, "", "");

    // 上一页按钮点击事件
    $(".pagination a:contains('上一页')").click(function(event) {
        event.preventDefault();
        if (currentPage > 1) {
            currentPage--;
            var searchName = $("#searchName").val();
            var searchId = $("#searchId").val();
            loadTeachers(currentPage, pageSize, searchName, searchId);
        }
    });

    // 下一页按钮点击事件
    $(".pagination a:contains('下一页')").click(function(event) {
        event.preventDefault();
        if (currentPage < totalPages) {
            currentPage++;
            var searchName = $("#searchName").val();
            var searchId = $("#searchId").val();
            loadTeachers(currentPage, pageSize, searchName, searchId);
        }
    });

    // 搜索按钮点击事件
    $("#searchButton").click(function() {
        currentPage = 1; // 重置当前页为第一页
        var searchName = $("#searchName").val();
        var searchId = $("#searchId").val();
        loadTeachers(currentPage, pageSize, searchName, searchId);
    });
});

function confirmDelete(teacherId) {
    if (confirm("您确定要删除工号为 " + teacherId + " 的教师吗？")) {
        window.location.href = contextPath + "/teacher/delete/" + teacherId;
    }
}
