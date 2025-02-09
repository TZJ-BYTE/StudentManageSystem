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

    // 将deleteClass和editClass函数赋值给全局变量,以便在JSP中调用
    window.deleteClass = deleteClass;
    window.editClass = editClass;
}); 