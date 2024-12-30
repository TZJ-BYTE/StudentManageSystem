<!DOCTYPE html>
<%@ page contentType="text/html; charset=gb2312"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
</head>
<body>
<h2>�û���¼</h2>
<form id="registrationForm">
    <label for="username">�˺�:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">����:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="button" onclick="login()">��¼</button>
    <button type="button" onclick="registerUser()">ע��</button>
</form>
<script>
    //��ת��ע��ҳ��
    function registerUser(){
        location.href="${pageContext.request.contextPath}/user/registerpage"
    }
    function login() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        // ȷ���û��������û���������
        if (username === '' || password === '') {
            alert('�������˺ź�����');
            return;
        }
        // ����һ��Ҫ���͵���������JSON����
        const userData = {
            username: username,
            password: password // ��ʵ��Ӧ���У�����Ӧ���ڿͻ��˼���
        };
        console.log("������",userData)
        // ʹ��fetch����POST����
        fetch('/StudentManageSystem_master_war/user/Login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('����ʧ��');
                }
                return response.json();
            })
            .then(data => {
                console.log("������",data)
                // ����ע��ɹ�����Ӧ
                alert(data.Message);
                // ���������������ת�߼�����������
                if(data.success){
                    location.href="${pageContext.request.contextPath}/user/success"
                }
                else alert("��¼����������")
            })
            .catch(error => {
                // ����������
                console.error('����:', error);
                alert('������������');
            });
    }
</script>
</body>
</html>
