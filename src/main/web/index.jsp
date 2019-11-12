
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .error {
            color: crimson;
        }
    </style>
</head>
<body>
    <h1>Hello, ${sessionScope['clientName']}!</h1>

    <p>${param['p']}</p>
    <p>${header['User-Agent']}</p>
    <c:if test="${empty sessionScope['clientId']}">
        <form method="post" action="/login">
            <p>Login: <input type="text" name="login" value="${param['login']}"></p>
            <p>Password: <input type="password" name="password"></p>
            <p><input type="submit"></p>
        </form>
    </c:if>

    <c:if test="${not empty sessionScope['clientId']}">
        <p><a href="/dashboard">Contracts</a></p>
    </c:if>

    <c:if test="${not empty param['login']}">
        <p class="error">
            Login or password is incorrect!
        </p>
    </c:if>
</body>
</html>
