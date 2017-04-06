<%--
  Created by IntelliJ IDEA.
  User: slipkinem
  Date: 2017/4/5
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/taglib.jsp"></jsp:include>
<html>
<head>
  <title>注册</title>
  <script src="${basePath}/asset/js/jQuery.js"></script>
</head>
<body>
<fieldset>
  <label>用户名</label>
  <input id="username" type="text">
</fieldset>
<fieldset>
  <label>密码</label>
  <input id="userPassword" type="password">
</fieldset>
<button onclick="register()">注册</button>
<script>
  function register() {
    $.ajax({
      type: 'POST',
      url: 'registerUser',
      data: {
        username: $('#username').val(),
        password: $('#userPassword').val()
      },
      error: function (e) {
        alert('服务器出错')
        throw e
      },
      success: function (data) {
        alert(data.errorMessage)
      }
    })
  }
</script>
</body>
</html>
