
function navigate(module) {
    var content = document.getElementById('main-content');
    switch (module) {
        case 'home':
            content.innerHTML = '<h1>首页</h1><p>这里是系统的首页。</p>';
            break;
        case 'teacher':
            fetch(contextPath + "/home/teachermanage")
                .then(response => response.text())
                .then(data => {
                    content.innerHTML = data;

                    // 重新加载所有 <script> 标签
                    const scriptTags = content.getElementsByTagName('script');
                    Array.from(scriptTags).forEach(script => {
                        if (script.src) {
                            // 如果有 src 属性，创建新的 <script> 标签并插入到 body 中
                            const newScript = document.createElement('script');
                            newScript.src = script.src;
                            newScript.async = false; // 确保按顺序加载
                            document.body.appendChild(newScript);
                        } else {
                            // 如果是内联脚本，直接执行
                            eval(script.innerHTML);
                        }
                    });

                    // 确保 jQuery 已加载后再执行 teacher.js
                    if (typeof $ === 'undefined') {
                        console.error("jQuery is not loaded correctly.");
                    } else {
                        $(document).ready(function() {
                            console.log("Document ready, initializing...");
                            // 初始化 teacher.js 中的功能
                            loadTeachers(1, 10); // 或者根据实际情况初始化
                        });
                    }
                })
                .catch(error => console.error('Error loading teacher.jsp:', error));
            break;
        case 'student':
            content.innerHTML = '<h1>学生管理</h1><p>这里是学生管理模块。</p>';
            break;
        case 'class':
            content.innerHTML = '<h1>班级管理</h1><p>这里是班级管理模块。</p>';
            break;
        case 'course':
            content.innerHTML = '<h1>课程管理</h1><p>这里是课程管理模块。</p>';
            break;
        case 'college':
            content.innerHTML = '<h1>学院管理</h1><p>这里是学院管理模块。</p>';
            break;
        //我的
        case 'profile':
            console.log(contextPath)
            location.href = contextPath + "/user/mepage";
            break;
        //设置
        case 'settings':
            location.href = "";
            break;
        //退出
        case 'logout':
            // 在这里执行退出登录逻辑，例如重定向到登录页面
            location.href = contextPath + "/user/loginpage";
            // 实际应用中可能是：
            // window.location.href = 'login.html';
            break;
        default:
            content.innerHTML = '<h1>页面未找到</h1><p>抱歉，您请求的页面不存在。</p>';
    }
}
var popupButton = document.getElementById('popup-button');
var popupLayer = document.getElementById('popup-layer');
var overlay = document.getElementById('overlay');
var closeButton = document.getElementById('close-button');
// 添加点击事件，用于显示弹出层和遮罩层
function showPopupLayer() {
    var popupLayer = document.getElementById('popup-layer');
    var overlay = document.getElementById('overlay');
    popupLayer.style.display = 'block';
    overlay.style.display = 'block';
    document.body.classList.add('modal-open'); // 添加类以改变背景颜色
}
// 添加点击事件，用于隐藏弹出层和遮罩层
closeButton.addEventListener('click', function () {
    popupLayer.style.display = 'none';
    overlay.style.display = 'none';
    document.body.classList.remove('modal-open'); // 移除类以恢复背景颜色
});
// 也可以添加点击遮罩层关闭弹出层的逻辑
overlay.addEventListener('click', function () {
    popupLayer.style.display = 'none';
    overlay.style.display = 'none';
    document.body.classList.remove('modal-open');
});
//    输入框
document.getElementById('editNickname').addEventListener('click', function () {
    var displayDiv = document.getElementById('nicknameDisplay');
    var inputDiv = document.getElementById('nicknameInputContainer');
    var input = document.getElementById('nicknameInput');
    var editImg = document.getElementById('editNickname');
    var confirmBtn = document.getElementById('confirmBtn');

    displayDiv.style.display = 'none';
    inputDiv.style.display = 'block';
    editImg.style.display = 'none';
    confirmBtn.style.display = 'block';

    input.value = displayDiv.innerText;
    input.focus();
    //input.style.borderColor = '#fb326c';

    confirmBtn.addEventListener('click', function () {
        displayDiv.innerText = input.value; // 更新显示的昵称
        // 发送GET请求到/user/updateusername
        var xhr = new XMLHttpRequest();
        xhr.open('GET', contextPath+'/user/updateusername?newValue=' + encodeURIComponent(input.value), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) { // 检查请求是否完成
                try {
                    var response = JSON.parse(xhr.responseText); // 尝试解析响应文本为JSON
                    if (response === true) {
                        // 响应是布尔值true，表示更新成功
                        console.log('Nickname updated successfully');
                        alert("修改昵称成功");
                    } else {
                        // 响应是布尔值false，表示更新失败
                        alert("修改昵称失败请重试");
                    }
                } catch (e) {
                    // 响应不是有效的JSON，可能是服务器返回了错误信息
                    alert("服务器错误：" + xhr.responseText);
                }
            }
        };
        xhr.onerror = function () {
            // 请求过程中发生错误
            alert("请求出错，请检查网络连接或联系管理员");
        };
        xhr.send();
    });
    // 移除原有blur事件监听器，因为我们希望在点击确认按钮后才隐藏输入框
    input.removeEventListener('blur', arguments.callee);
});
//    更改密码
function checkAndUpdatePassword() {
    var originalPassword = document.getElementById('originalPassword').value;
    var newPassword = document.getElementById('newPassword').value;
    var confirmPassword = document.getElementById('confirmPassword').value;

    if (newPassword !== confirmPassword) {
        alert('两次输入的密码不同，请重新输入。');
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('GET', contextPath+'/user/updatepasswd?originalPassword=' + encodeURIComponent(originalPassword) + '&newPassword=' + encodeURIComponent(newPassword), true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            var response = JSON.parse(xhr.responseText); // 解析响应文本为JSON对象
            if (xhr.status === 200 && response) {
                alert('密码更新成功！');
            } else {
                alert('密码更新失败：' + (xhr.statusText || 'Unknown error'));
            }
        }
    };
    xhr.send();
}