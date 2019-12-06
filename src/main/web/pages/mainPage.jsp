<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<h1>Hello</h1>
<body>
<c:if test="${empty sessionScope['clientId']}">
    <form:form action="/login" method="post" modelAttribute="loginForm">
        <p>Login:
            <form:input type="text" path="login" />
            <form:errors path="login" cssStyle="color: firebrick" />
        </p>
        <p>Password:
            <form:password path="password" />
            <form:errors path="password" cssStyle="color: firebrick" />
        </p>

        <security:csrfInput/>

        <input type="submit" value="Sign in">
    </form:form>
    <input type="button" value="Registration" onClick='location.href="/register"'>
    <%--<a href="/register">Registration</a>--%>
</c:if>
<c:if test="${not empty sessionScope['clientId'] && empty sessionScope['isAdmin']}">
    <p><a href="/dashboard">Dashboard</a></p>
</c:if>
<c:if test="${not empty sessionScope['isAdmin']}">
    <p><a href="/admin">Admin page</a></p>
</c:if>

</body>
</html>
