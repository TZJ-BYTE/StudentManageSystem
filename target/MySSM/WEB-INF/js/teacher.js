// 从HTML元素中读取数据
var paginationElement = $(".pagination");
var currentPage = parseInt(paginationElement.data("current-page"), 10) || 1;
var totalPages = parseInt(paginationElement.data("total-pages"), 10) || 1;
var pageSize = parseInt(paginationElement.data("page-size"), 10) || 10;


$(document).ready(function() {
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

    // 显示添加教师弹出框
    $("#addTeacherButton").click(function() {
        $("#addTeacherModal").addClass("show");
        $.ajax({
            url: contextPath + "/department/getDepartments", // 修改: url: contextPath + "/department/teacher"
            type: "GET",
            success: function(response) {
                // 填充部门数据到模态框中的下拉菜单
                var departmentSelect = $("#teacherDepartmentId");
                departmentSelect.empty();
                departmentSelect.append("<option value=''>请选择部门</option>");
                if (response && response.length > 0) {
                    response.forEach(function(department) {
                        departmentSelect.append("<option value='" + department.departmentId + "'>" + department.departmentName + "</option>");
                    });
                } else {
                    departmentSelect.append("<option value=''>无部门数据</option>");
                }
            },
            error: function(xhr, status, error) {
                console.error("加载教师数据失败:", xhr.responseText);
                alert("加载教师数据失败: " + error);
            }
        });
    });

    // 隐藏添加教师弹出框
    $("#cancelAddTeacher").click(function() {
        $("#addTeacherModal").removeClass("show");
    });

    // 提交添加教师表单
    $("#addTeacherForm").submit(function(event) {
        event.preventDefault();
        // 获取表单数据
        var formData = $(this).serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        // 获取当前最大teacherid
        $.ajax({
            url: contextPath + '/teacher/getMaxTeacherId',
            type: 'GET',
            success: function(maxTeacherId) {
                formData.teacherId = maxTeacherId + 1; // 设置新的teacherId

                $.ajax({
                    url: contextPath + '/teacher/add',
                    type: 'POST',
                    contentType: 'application/json', // 确保设置正确的Content-Type
                    data: JSON.stringify(formData), // 确保数据格式正确
                    success: function(response) {
                        // 处理成功响应
                        toastr.success('教师添加成功');
                        $("#addTeacherModal").removeClass("show");
                        loadTeachers(currentPage, pageSize, "", ""); // 刷新教师列表
                    },
                    error: function(xhr, status, error) {
                        console.error('添加教师失败:', xhr.responseText);
                        toastr.error('添加教师失败: ' + error);
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('获取最大teacherid失败:', xhr.responseText);
                toastr.error('获取最大teacherid失败: ' + error);
            }
        });
    });
});

function confirmDelete(teacherId) {
    if (confirm("您确定要删除工号为 " + teacherId + " 的教师吗？")) {
        $.ajax({
            url: contextPath + "/teacher/delete/" + teacherId,
            type: 'DELETE',
            success: function(response) {
                toastr.success('删除教师成功');
                loadTeachers(currentPage, pageSize, "", ""); // 刷新教师列表
            },
            error: function(xhr, status, error) {
                console.error('删除教师失败:', xhr.responseText);
                toastr.error('删除教师失败: ' + error);
            }
        });
    }
}
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
                        "<td>" + teacher.fieldOfStudy + "</td>" +
                        "<td>" + teacher.contactNumber + "</td>" +
                        "<td>" + teacher.departmentId + "</td>" +
                        "<td class=\"action-links\">" +
                        "<button class=\"btn-edit\" onclick=\"window.location.href='" + contextPath + "/teacher/edit/" + teacher.teacherId + "'\">编辑</button>" +
                        "<button class=\"btn-delete\" onclick=\"confirmDelete('" + teacher.teacherId + "')\">删除</button>" +
                        "<button class=\"btn-detail\" onclick=\"window.location.href='" + contextPath + "/teacher/detail/" + teacher.teacherId + "'\">详情</button>" +
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
            toastr.error('加载教师数据失败: ' + error);
        }
    });
}