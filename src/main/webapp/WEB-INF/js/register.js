// register.js
$(document).ready(function() {
    $('#registrationForm').submit(function(event) {
        event.preventDefault(); // 阻止表单默认提交行为
        const username = $('#username').val();
        const password = $('#password').val();
        // 准备要发送的数据
        const data = {
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
    });
});
