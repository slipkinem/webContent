<%--
  Created by IntelliJ IDEA.
  User: slipkinem
  Date: 2017/3/31
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/taglib.jsp"></jsp:include>
<html>
<head>
  <title>登录</title>
  <script src="${basePath}/asset/js/jQuery.js"></script>
</head>
<body>
<!-- 登陆框 -->
<div class='content'>
  <div class='logo'><i style='font-size:90px;' class="iconfont icon-denglu"></i></div>
  <div class='inputBox mt50 ml32'>
    <input type="text" id="username" autofocus="autofocus" autocomplete="off" maxlength="60" placeholder="请输入账号/邮箱/手机号">
    <i style='font-size:20px;margin-left:-32px;opacity:0.8;' class='iconfont icon-yonghuming'></i>
  </div>
  <div class='inputBox mt50 ml32'>
    <input type="password" id="password" autofocus="autofocus" autocomplete="off" maxlength="60" placeholder="请输入密码">
    <i style='font-size:20px;margin-left:-32px;opacity:0.8;' class='iconfont icon-mima'></i>
  </div>

  <div class='mt50 ml32'>
    <button class="login_btn" onclick="login()">登陆</button>
  </div>
</div>
<script>
  function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
      type: "post",//请求方式
      url: "loginUser",//请求地址
      data: {"username": username, "password": password},//传递给controller的json数据
      error: function (e) {
        console.error(e)
        alert("登陆出错！");
      },
      success: function (data) { //返回成功执行回调函数。
        console.log(data)
        if (data.errorCode === 0) {
          window.location.href = "${basePath}"
        } else {
          alert(data.errorMessage)
        }
      }
    });
  }
</script>
</body>
</html>
