<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Popup</title>
    <style>
        /* 隐藏弹出框 */
        #popup {
            display: none;
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            padding: 20px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.5);
            background-color: #fff;
            z-index: 1001;
        }

        /* 蒙版层 */
        #overlay {
            display: none;
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.5);
            z-index: 1000;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        // 将 contextPath 传递给全局变量
        var contextPath = "${pageContext.request.contextPath}";
    </script>
    <!-- 引用外部JavaScript文件 -->
    <script src="${pageContext.request.contextPath}/js/allfuns.js"></script>
</head>
<body>

<!-- 弹出框内容 -->
<div id="popup">
    <h2>弹出框标题</h2>
    <p>这里是弹出框的内容。</p>
    <button onclick="closePopup()">关闭</button>
</div>

<!-- 蒙版层 -->
<div id="overlay"></div>

<!-- 添加一个按钮用于测试显示弹出框 -->
<button onclick="showPopup()">显示弹出框</button>

</body>
</html>
