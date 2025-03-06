// 从HTML元素中读取数据
var paginationElement = $(".pagination");
var currentPage = parseInt(paginationElement.data("current-page"), 10) || 1;
var totalPages = parseInt(paginationElement.data("total-pages"), 10) || 1;
var pageSize = parseInt(paginationElement.data("page-size"), 10) || 10;
$(document).ready(function() {
    // 初始化加载
    var searchTerm = $("#searchTerm").val() || ""; // 获取初始搜索词
    loadDepartments(currentPage, pageSize, searchTerm);

});
function loadDepartments(page, pageSize, searchTerm) {
    console.log("Loading departments for page: " + page);
    $.ajax({
        url: contextPath + "/depart/search",
        type: "GET",
        data: {
            page: page,
            pageSize: pageSize,
            searchTerm: searchTerm || ""
        },
        success: function(response) {
            if (response && response.success) {
                var departments = response.data.departments;
                var tbody = $("#departmentTable tbody");
                tbody.empty(); // 清空表格内容

                if (departments && departments.length > 0) {
                    departments.forEach(function(department) {
                        var row = "<tr>" +
                            "<td>" + department.departmentId + "</td>" + // 部门ID
                            "<td>" + department.departmentName + "</td>" + // 部门名称
                            "<td>" + department.address + "</td>" + // 地址
                            "<td class=\"action-links\">" +
                            "<button class=\"btn-edit\" onclick=\"editDepartment('" + department.departmentId + "')\">编辑</button>" +
                            "<button class=\"btn-delete\" onclick=\"confirmDelete('" + department.departmentId + "')\">删除</button>" +
                            "<button class=\"btn-detail\" onclick=\"window.location.href='" + contextPath + "/department/detail/" + department.departmentId + "'\">详情</button>" +
                            "</td>" +
                            "</tr>";
                        tbody.append(row);
                    });
                } else {
                    tbody.append("<tr><td colspan='4'>没有找到部门数据</td></tr>");
                }

                // 更新分页信息（如果有的话）
                $("#currentPage").text(response.data.currentPage || 1);
                $("#totalPages").text(response.data.totalPages || 1);
            } else {
                console.error("加载部门数据失败:", response.message);
                toastr.error('加载部门数据失败: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("加载部门数据失败:", xhr.responseText);
            toastr.error('加载部门数据失败: ' + error);
        }
    });
}
