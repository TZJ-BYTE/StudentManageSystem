// login.js
// 跳转到注册页面
function login() {
    const username = $('#loginUsername').val();
    const password = $('#loginPassword').val();
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
                location.href = contextPath + "/user/allfunspage";
                console.log("登录成功")
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
function register() {

    const username = $('#registerUsername').val();
    const password = $('#registerPassword').val();

    // 获取当前最大 usertid
    $.ajax({
        url: contextPath + "/user/getMaxUsertId",
        type: 'GET',
        success: function(maxUsertId) {
            const usertid = maxUsertId + 1; // 设置新的 usertid

            // 准备要发送的数据
            const data = {
                "usertid": usertid,
                "username": username,
                "password": password
            };

            // 发起AJAX POST请求
            $.ajax({
                url: contextPath + "/user/register",
                type: 'POST',
                contentType: 'application/json', // 设置请求头内容类型为JSON
                data: JSON.stringify(data), // 将JavaScript对象转换为JSON字符串
                success: function(response) {
                    // 请求成功时的处理
                    if (response.success) {
                        let countdown = 3;
                        const intervalId = setInterval(function() {
                            $('#responseMessage').text(`用户注册成功。将在${countdown}秒后跳转到登录页面`).css('color', 'green');
                            countdown--;
                            if (countdown < 0) {
                                clearInterval(intervalId);
                                location.href = contextPath + "/user/loginpage";
                            }
                        }, 1000);
                    } else {
                        $('#responseMessage').text(response.message).css('color', 'red');
                    }
                },
                error: function(xhr, status, error) {
                    // 请求失败时的处理
                    $('#responseMessage').text('注册失败: ' + xhr.responseText).css('color', 'red');
                }
            });
        },
        error: function(xhr, status, error) {
            // 请求失败时的处理
            $('#responseMessage').text('获取最大 usertid 失败: ' + xhr.responseText).css('color', 'red');
        }
    });
}