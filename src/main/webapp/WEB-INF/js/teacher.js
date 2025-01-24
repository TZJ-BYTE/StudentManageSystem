// 从HTML元素中读取数据
var paginationElement = $(".pagination");
var currentPage = parseInt(paginationElement.data("current-page"), 10) || 1;
var totalPages = parseInt(paginationElement.data("total-pages"), 10) || 1;
var pageSize = parseInt(paginationElement.data("page-size"), 10) || 10;

$(document).ready(function() {
    // 初始化加载
    var searchTerm = $("#searchTerm").val() || ""; // 获取初始搜索词
    loadTeachers(currentPage, pageSize, searchTerm);

    // 上一页按钮点击事件
    $(".pagination a:contains('上一页')").click(function(event) {
        event.preventDefault();
        if (currentPage > 1) {
            currentPage--;
            var searchTerm = $("#searchTerm").val() || ""; // 获取当前搜索词
            loadTeachers(currentPage, pageSize, searchTerm); // 调用 loadTeachers 函数加载数据
        }
    });

    // 下一页按钮点击事件
    $(".pagination a:contains('下一页')").click(function(event) {
        event.preventDefault();
        if (currentPage < totalPages) {
            currentPage++;
            var searchTerm = $("#searchTerm").val() || ""; // 获取当前搜索词
            loadTeachers(currentPage, pageSize, searchTerm); // 调用 loadTeachers 函数加载数据
        }
    });

    // 搜索按钮点击事件
    $("#searchButton").click(function() {
        currentPage = 1; // 重置当前页为第一页
        var searchTerm = $("#searchTerm").val(); // 获取搜索词
        loadTeachers(currentPage, pageSize, searchTerm);
    });

    // 显示添加教师弹出框
    $("#addTeacherButton").click(function() {
        $("#addTeacherModal").addClass("show");
        $.ajax({
            url: contextPath + "/department/getDepartments",
            type: "GET",
            success: function(response) {
                // 填充部门数据到模态框中的下拉菜单
                var departmentSelect = $("#teacherDepartmentId");
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
                console.error("加载教师数据失败:", xhr.responseText);
                toastr.error('加载教师数据失败: ' + error);
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
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    success: function(response) {
                        toastr.success(response.message);
                        $("#addTeacherModal").removeClass("show");
                        loadTeachers(currentPage, pageSize, "", ""); // 刷新教师列表
                    },
                    error: function(response, xhr, status, error) {
                        console.error(response.message, xhr.responseText);
                        toastr.error(response.message, + error);
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('获取最大teacherid失败:', xhr.responseText);
                toastr.error('获取最大teacherid失败: ' + error);
            }
        });
    });

    // 提交编辑教师表单
    $("#editTeacherForm").submit(function(event) {
        event.preventDefault();

        // 获取表单数据
        var formData = $(this).serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        // 获取 teacherId
        var teacherId = $(this).data("teacherId");

        $.ajax({
            url: contextPath + '/teacher/edit/' + teacherId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                toastr.success(response.message);
                $("#editTeacherModal").hide();
                loadTeachers(currentPage, pageSize, "", ""); // 刷新教师列表
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText, error);
                toastr.error(xhr.responseText || error);
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
function loadTeacherDetail(teacherId) {
    $.ajax({
        url: contextPath + "/teacher/getmore/" + teacherId,
        type: 'GET',
        success: function(teacher) {
            // 填充教师详细信息到模态框中
            var detailContent = "<p><strong>工号:</strong> " + (teacher.teacherId || '无') + "</p>" +
                              "<p><strong>姓名:</strong> " + (teacher.name || '无') + "</p>" +
                              "<p><strong>性别:</strong> " + (teacher.gender || '无') + "</p>" +
                              "<p><strong>职称:</strong> " + (teacher.title || '无') + "</p>" +
                              "<p><strong>研究领域:</strong> " + (teacher.fieldOfStudy || '无') + "</p>" +
                              "<p><strong>联系电话:</strong> " + (teacher.contactNumber || '无') + "</p>" +
                              "<p><strong>部门ID:</strong> " + (teacher.departmentId || '无') + "</p>" +
                              "<p><strong>部门名称:</strong> " + (teacher.departmentName || '无') + "</p>" +
                              "<p><strong>地址:</strong> " + (teacher.address || '无') + "</p>" +
                              "<p><strong>班级ID:</strong> " + (teacher.classId || '无') + "</p>" +
                              "<p><strong>班级名称:</strong> " + (teacher.className || '无') + "</p>";
            $("#teacherDetailContent").html(detailContent);
            // 显示详细信息模态框
            $("#teacherDetailModal").show();
        },
        error: function(xhr, status, error) {
            console.error('获取教师详细信息失败:', xhr.responseText);
            toastr.error('获取教师详细信息失败: ' + error);
        }
    });
}


// 关闭详细信息模态框
$("#closeDetailModal").click(function() {
    $("#teacherDetailModal").hide();
});

function loadTeachers(page, pageSize, searchTerm) {
    console.log("Loading teachers for page: " + page + ", pageSize: " + pageSize + ", searchTerm: " + (searchTerm || "null"));
    $.ajax({
        url: contextPath + "/teacher/search", // 修改 URL 为新的搜索接口
        type: "GET",
        data: {
            page: page,
            pageSize: pageSize,
            searchTerm: searchTerm || "" // 确保 searchTerm 总是被传递
        },
        success: function(response) {
            if (response.success) {
                var teachers = response.data.teachers;
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
                            "<button class=\"btn-edit\" onclick=\"editTeacher('" + teacher.teacherId + "')\">编辑</button>" +
                            "<button class=\"btn-delete\" onclick=\"confirmDelete('" + teacher.teacherId + "')\">删除</button>" +
                            "<button class=\"btn-detail\" onclick=\"loadTeacherDetail('" + teacher.teacherId + "')\">详情</button>" +
                            "</td>" +
                            "</tr>";
                        tbody.append(row);
                    });
                } else {
                    tbody.append("<tr><td colspan='8'>没有找到教师数据</td></tr>");
                }

                // 更新分页信息
                $("#currentPage").text(response.data.currentPage || 1);
                $("#totalPages").text(response.data.totalPages || 1);
                totalPages = response.data.totalPages || 1; // 更新 totalPages 变量
            } else {
                console.error("加载教师数据失败:", response.message);
                toastr.error('加载教师数据失败: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("加载教师数据失败:", xhr.responseText);
            toastr.error('加载教师数据失败: ' + error);
        }
    });
}


function editTeacher(teacherId) {
    // 获取教师数据
    $.ajax({
        url: contextPath + "/teacher/get/" + teacherId,
        type: 'GET',
        success: function(teacher) {
            // 填充教师数据到模态框中的表单
            $("#editTeacherId").val(teacher.teacherId);
            $("#editTeacherName").val(teacher.name);
            $("#editTeacherGender").val(teacher.gender);
            $("#editTeacherTitle").val(teacher.title);
            $("#editTeacherFieldOfStudy").val(teacher.fieldOfStudy);
            $("#editTeacherContactNumber").val(teacher.contactNumber);
            $("#editTeacherDepartmentId").val(teacher.departmentId);

            // 将 teacherId 存储在全局变量或表单 data 属性中
            $("#editTeacherForm").data("teacherId", teacherId);

            // 获取部门数据
            $.ajax({
                url: contextPath + "/department/getDepartments",
                type: "GET",
                success: function(response) {
                    // 填充部门数据到模态框中的下拉菜单
                    var departmentSelect = $("#editTeacherDepartmentId");
                    departmentSelect.empty();
                    departmentSelect.append("<option value=''>请选择部门</option>");
                    if (response && response.length > 0) {
                        response.forEach(function(department) {
                            departmentSelect.append("<option value='" + department.departmentId + "'>" + department.departmentId + " (" + department.departmentName + ")</option>");
                        });
                    } else {
                        departmentSelect.append("<option value=''>无部门数据</option>");
                    }

                    // 设置当前教师的部门
                    departmentSelect.val(teacher.departmentId);
                },
                error: function(xhr, status, error) {
                    console.error("加载部门数据失败:", xhr.responseText);
                    toastr.error('加载部门数据失败: ' + error);
                }
            });

            // 显示编辑教师弹出框
            $("#editTeacherModal").addClass("show");
        },
        error: function(xhr, status, error) {
            console.error('获取教师数据失败:', xhr.responseText);
            toastr.error('获取教师数据失败: ' + error);
        }
    });
}

$("#cancelEditTeacher").click(function() {
    $("#editTeacherModal").removeClass("show");
});
