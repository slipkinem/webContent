<%--
  Created by IntelliJ IDEA.
  User: slipkinem
  Date: 2017/3/31
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
  String path = request.getContextPath();
  int port = request.getServerPort();
  String basePath = null;
  if (port == 80) {
    basePath = request.getScheme() + "://" + request.getServerName() + path;
  } else {
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
  }
  request.setAttribute("basePath", basePath);
%>
