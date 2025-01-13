<div id="addTeacherModal" style="display: none;">
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
                <c:forEach items="${departments}" var="department">
                    <option value="${department.departmentId}">${department.departmentName}</option>
                </c:forEach>
            </select><br>

            <button type="submit">提交</button>
            <button type="button" id="cancelAddTeacher">取消</button>
        </form>
    </div>
</div>