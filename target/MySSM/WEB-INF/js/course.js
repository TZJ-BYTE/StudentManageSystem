// 从HTML元素中读取数据
var paginationElement = $(".pagination");
var currentPage = parseInt(paginationElement.data("current-page"), 10) || 1;
var totalPages = parseInt(paginationElement.data("total-pages"), 10) || 1;
var pageSize = parseInt(paginationElement.data("page-size"), 10) || 10;

$(document).ready(function() {
    // 初始化加载
    var searchTerm = $("#searchTerm").val() || ""; // 获取初始搜索词
    loadCourses(currentPage, pageSize, searchTerm);

    // 上一页按钮点击事件
    $(".pagination a:contains('上一页')").click(function(event) {
        event.preventDefault();
        if (currentPage > 1) {
            currentPage--;
            var searchTerm = $("#searchTerm").val() || ""; // 获取当前搜索词
            loadCourses(currentPage, pageSize, searchTerm);
        }
    });

    // 下一页按钮点击事件
    $(".pagination a:contains('下一页')").click(function(event) {
        event.preventDefault();
        if (currentPage < totalPages) {
            currentPage++;
            var searchTerm = $("#searchTerm").val() || ""; // 获取当前搜索词
            (currentPage, pageSize, searchTerm);
        }
    });

    // 搜索按钮点击事件
    $("#searchButton").click(function() {
        currentPage = 1; // 重置当前页为第一页
        var searchTerm = $("#searchTerm").val(); // 获取搜索词
        loadCourses(currentPage, pageSize, searchTerm);
    });

    // 显示添加课程弹出框
    $("#addCourseButton").click(function() {
        $("#addCourseModal").addClass("show");
        $.ajax({
            url: contextPath + "/department/getDepartments",
            type: "GET",
            success: function(response) {
                // 填充部门数据到模态框中的下拉菜单
                var departmentSelect = $("#courseDepartmentId");
                departmentSelect.empty();
                departmentSelect.append("<option value=''>请选择部门</option>");
                if (response && response.length > 0) {
                    response.forEach(function(department) {
                        departmentSelect.append("<option value='" + department.departmentId + "'>" + department.departmentId + " (" + department.departmentName + ")</option>");
                    });
                } else {
                    departmentSelect.append("<option value=''>无部门数据</option>");
                }
            },
            error: function(xhr, status, error) {
                console.error("加载部门数据失败:", xhr.responseText);
                toastr.error('加载部门数据失败: ' + error);
            }
        });
    });

    // 隐藏添加课程弹出框
    $("#cancelAddCourse").click(function() {
        $("#addCourseModal").removeClass("show");
    });



// 提交编辑课程表单
$("#editCourseForm").submit(function(event) {
    event.preventDefault();

    // 获取表单数据
    var formData = $(this).serializeArray().reduce(function(obj, item) {
        obj[item.name] = item.value;
        return obj;
    }, {});

    // 获取 courseId
    var courseId = $(this).data("courseId");

    $.ajax({
        url: contextPath + '/course/edit/' + courseId,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(response) {
            toastr.success(response.message);
            $("#editCourseModal").hide();
            loadCourses(currentPage, pageSize, "", ""); // 刷新课程列表
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText, error);
            toastr.error(xhr.responseText || error);
        }
    });
});
});

function confirmDelete(courseId) {
    if (confirm("您确定要删除课程ID为 " + courseId + " 的课程吗？")) {
        $.ajax({
            url: contextPath + "/course/delete/" + courseId,
            type: 'DELETE',
            success: function(response) {
                toastr.success('删除课程成功');
                loadCourses(currentPage, pageSize, "", ""); // 刷新课程列表
            },
            error: function(xhr, status, error) {
                console.error('删除课程失败:', xhr.responseText);
                toastr.error('删除课程失败: ' + error);
            }
        });
    }
}

function loadCourses(page, pageSize, searchTerm) {
    console.log("Loading courses for page: " + page);
    $.ajax({
        url: contextPath + "/courses/search",
        type: "GET",
        data: {
            page: page,
            pageSize: pageSize,
            searchTerm: searchTerm || ""
        },
        success: function(response) {
            if (response && response.success) {
                var courses = response.data.courses;
                var tbody = $("#courseTable tbody");
                tbody.empty(); // 清空表格内容

                if (courses && courses.length > 0) {
                    courses.forEach(function(course) {
                        var row = "<tr>" +
                            "<td>" + course.courseId + "</td>" + // 课程ID
                            "<td>" + course.courseName + "</td>" + // 课程名称
                            "<td>" + course.credits + "</td>" + // 学分
                            "<td class=\"action-links\">" +
                            "<button class=\"btn-edit\" onclick=\"editCourse('" + course.courseId + "')\">编辑</button>" +
                            "<button class=\"btn-delete\" onclick=\"confirmDelete('" + course.courseId + "')\">删除</button>" +
                            "<button class=\"btn-detail\" onclick=\"window.location.href='" + contextPath + "/course/detail/" + course.courseId + "'\">详情</button>" +
                            "</td>" +
                            "</tr>";
                        tbody.append(row);
                    });
                } else {
                    tbody.append("<tr><td colspan='4'>没有找到课程数据</td></tr>");
                }

                // 更新分页信息（如果有的话）
                $("#currentPage").text(response.data.currentPage || 1);
                $("#totalPages").text(response.data.totalPages || 1);
            } else {
                console.error("加载课程数据失败:", response.message);
                toastr.error('加载课程数据失败: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("加载课程数据失败:", xhr.responseText);
            toastr.error('加载课程数据失败: ' + error);
        }
    });
}

function editCourse(courseId) {
    // ... 根据您的实际需求修改此函数，以适应课程编辑的逻辑
}

$("#cancelEditCourse").click(function() {
    $("#editCourseModal").removeClass("show");
});
