
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
