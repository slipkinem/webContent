<%--
  Created by IntelliJ IDEA.
  User: slipkinem
  Date: 2017/3/30
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/common/taglib.jsp"></jsp:include>
<html>
<head>
  <title>标题</title>
  <script src="asset/js/jQuery.js"></script>
  <style>
    * {
      padding: 0;
      margin: 0;
    }

    ul li {
      list-style: none;
    }

    a {
      color: #fff;
      text-decoration: none;
    }

    .header {
      height: 72px;
      background: #458fce;
    }

    .header .logo {
      float: left;
      color: #fff;
      line-height: 72px;
      margin-left: 20px;
    }

    .header ul li.first {
      margin-left: 30px;
      background: #74b0e2;
    }

    .header ul li {
      color: #fff;
      float: left;
      width: 112px;
      height: 72px;
      text-align: center;
      line-height: 72px;
      cursor: pointer;
    }

    .header ul li:hover {
      background: #74b0e2;
    }

    .banner {
      height: 380px;
      margin-top: 20px;
      position: relative;
      overflow: hidden;
    }

    .banner .content {
      width: 1060px;
      height: 450px;
      margin: 20px auto;
      position: relative;
    }

    .banner ul li img {
      width: 1024px;
      height: 380px;
    }

    .banner .content ul {
      width: 10000px;
    }

    .banner .content ul {
      width: 10000px;
    }

    .fl {
      float: left!important;
    }

    .fr {
      float: right!important;
    }
    .login {
      margin-right: 20px;
      line-height:72px;
    }

    .btn_left, .btn_right {
      display: inline-block;
      width: 21px;
      height: 150px;
      background: url(./asset/img/a0.jpg) no-repeat;
      position: absolute;
      left: -38px;
      top: 90px;
    }

    .btn_right {
      background-position: -29px 0;
      position: absolute;
      left: 1041px;
      top: 90px;
    }

    .banner .content:hover .btn_left {
      opacity: 1;
    }

    .banner .content:hover .btn_right {
      opacity: 1;
    }
  </style>
</head>
<body>
<!-- 头部页面 -->
<jsp:include page="WEB-INF/common/header.jsp"></jsp:include>

<div class="banner">
  ${basePath}
  <div class="content">
    <ul>
      <li>
        <a href="javascript:void(0)">
          <img src="asset/img/a0.jpg">
        </a>
      </li>
      <li>
        <a href="javascript:void(0)">
          <img
              src="asset/img/a0.jpg"/>
        </a>
      </li>
      <li>
        <a href="javascript:void(0)">
          <img
              src="asset/img/a0.jpg"/>
        </a>
      </li>
      <li>
        <a href="javascript:void(0)">
          <img
              src="asset/img/a0.jpg"/>
        </a>
      </li>
      <li>
        <a href="javascript:void(0)">
          <img
              src="asset/img/a0.jpg"/>
        </a>
      </li>
    </ul>
    <i class='btn_left'></i>
    <i class='btn_right'></i>
  </div>
</div>
<script>
  var leftBtn = $('.btn_left').eq(0); //左按钮
  var rightBtn = $('.btn_right').eq(0);//右按钮
  var ul = $('.banner .content ul').eq(0);

  rightBtn.on('click', function () {
    ul.animate({marginLeft: -1024}, 1000);
  });
</script>
</body>
</html>
