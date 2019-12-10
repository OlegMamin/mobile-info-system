<%--
  Created by IntelliJ IDEA.
  User: AHYC
  Date: 10.12.2019
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html ng-app="demo">
<head>
    <title>Hello AngularJS</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    <script type="text/javascript" src="/script/hello.js"></script>
</head>

<body>
<div ng-controller="Hello">
    <p>The name is {{greeting.name}}</p>
    <p>The content length is {{greeting.list.length}}</p>
</div>
</body>
</html>