// login.js
// 跳转到注册页面
function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    // 确保用户名和密码不为空
    if (username === '' || password === '') {
        alert('请输入用户名和密码');
        return;
    }
    // 构造要发送的JSON对象
    const userData = {
        username: username,
        password: password
    };
    console.log("发送数据", userData);
    // 使用fetch发送POST请求
    fetch(contextPath + '/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('请求失败');
            }
            return response.json();
        })
        .then(data => {
            console.log("接收数据", data);
            // 处理登录成功的响应
            // 如果登录成功，直接跳转到成功页面
            if (data.success) {
                location.href = contextPath + "/home/allfunspage";
            } else {
                alert("登录失败，请检查用户名和密码");
            }
        })
        .catch(error => {
            // 处理请求错误
            console.error('错误:', error);
            alert('请求发生错误，请重试');
        });
}
