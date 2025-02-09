$(document).ready(function() {
    // 删除班级
    function deleteClass(classId) {
        if (confirm("确定要删除这个班级吗?")) {
            $.ajax({
                url: "/class/delete/" + classId,
                type: "POST",
                success: function(result) {
                    alert("班级已删除");
                    window.location.reload();
                },
                error: function(xhr, status, error) {
                    alert("删除班级时发生错误: " + error);
                }
            });
        }
    }

    // 编辑班级
    function editClass(classId) {
        window.location.href = "/class/edit/" + classId;
    }

    // 确认删除班级
    function confirmDelete(classId) {
        deleteClass(classId);
    }

    // 加载班级详细信息
    function loadClassDetail(classId) {
        $.ajax({
            url: contextPath + "/class/getmore/" + classId,
            type: 'GET',
            success: function(clazz) {
                // 填充班级详细信息到模态框中
                var detailContent = "<p><strong>班级ID:</strong> " + (clazz.classId || '无') + "</p>" +
                                  "<p><strong>班级名称:</strong> " + (clazz.className || '无') + "</p>" +
                                  "<p><strong>所属院系ID:</strong> " + (clazz.departmentId || '无') + "</p>" +
                                  "<p><strong>班主任ID:</strong> " + (clazz.teacherId || '无') + "</p>" +
                                  "<p><strong>描述:</strong> " + (clazz.description || '无') + "</p>";
                $("#classDetailContent").html(detailContent);
                // 显示详细信息模态框
                $("#classDetailModal").show();
            },
            error: function(xhr, status, error) {
                console.error('获取班级详细信息失败:', xhr.responseText);
                toastr.error('获取班级详细信息失败: ' + error);
            }
        });
    }

    // 将deleteClass, editClass, confirmDelete, loadClassDetail函数赋值给全局变量,以便在JSP中调用
    window.deleteClass = deleteClass;
    window.editClass = editClass;
    window.confirmDelete = confirmDelete;
    window.loadClassDetail = loadClassDetail;

    // 分页变量
    var currentPage = 1;
    var pageSize = 10;
    var totalPages = 1;
    var searchTerm = "";

    // 加载班级数据
    function loadClasses(page, pageSize, searchTerm) {
        if (page < 1) {
            page = 1;
        }
        if (page > totalPages) {
            page = totalPages;
        }
        console.log("Loading classes for page: " + page + ", pageSize: " + pageSize + ", searchTerm: " + (searchTerm || "null"));
        $.ajax({
            url: contextPath + "/class/search", // 修改 URL 为新的搜索接口
            type: "GET",
            data: {
                page: page,
                pageSize: pageSize,
                searchTerm: searchTerm || "" // 确保 searchTerm 总是被传递
            },
            success: function(response) {
                if (response.success) {
                    var classes = response.data.classes;
                    var tbody = $("#classTable tbody");
                    tbody.empty();
                    if (classes && classes.length > 0) {
                        classes.forEach(function(clazz) {
                            var row = "<tr>" +
                                "<td>" + clazz.classId + "</td>" +
                                "<td>" + clazz.className + "</td>" +
                                "<td>" + clazz.departmentId + "</td>" +
                                "<td>" + clazz.teacherId + "</td>" +
                                "<td class=\"action-links\">" +
                                "<button class=\"btn-edit\" onclick=\"editClass('" + clazz.classId + "')\">编辑</button>" +
                                "<button class=\"btn-delete\" onclick=\"confirmDelete('" + clazz.classId + "')\">删除</button>" +
                                "<button class=\"btn-detail\" onclick=\"loadClassDetail('" + clazz.classId + "')\">详情</button>" +
                                "</td>" +
                                "</tr>";
                            tbody.append(row);
                        });
                    } else {
                        tbody.append("<tr><td colspan='5'>没有找到班级数据</td></tr>");
                    }

                    // 更新分页信息
                    $("#currentPage").text(response.data.currentPage || 1);
                    $("#totalPages").text(response.data.totalPages || 1);
                    totalPages = response.data.totalPages || 1; // 更新 totalPages 变量

                    // 更新分页按钮状态
                    $("#prevPage").prop("disabled", currentPage <= 1);
                    $("#nextPage").prop("disabled", currentPage >= totalPages);
                } else {
                    console.error("加载班级数据失败:", response.message);
                    toastr.error('加载班级数据失败: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
                console.error("加载班级数据失败:", xhr.responseText);
                toastr.error('加载班级数据失败: ' + error);
            }
        });
    }

    // 初始化加载班级数据
    loadClasses(currentPage, pageSize, searchTerm);
});
