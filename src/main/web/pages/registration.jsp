<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form:form action="/register" method="post" modelAttribute="form">
        <p>Name:
            <form:input type="text" path="firstName"/>
            <form:errors path="firstName" cssStyle="color: firebrick" />
        </p>
        <p>Surname:
            <form:input type="text" path="secondName" />
            <form:errors path="secondName" cssStyle="color: firebrick" />
        </p>
        <p>Passport number:
            <form:input type="text" path="passportNumber" />
            <form:errors path="passportNumber" cssStyle="color: firebrick" />
        </p>
        <p>Login:
            <form:input type="text" path="login" />
            <form:errors path="login" cssStyle="color: firebrick" />
        </p>
        <p>Password:
            <form:password path="password" />
            <form:errors path="password" cssStyle="color: firebrick" />
        </p>
        <p>Confirm password:
            <form:password path="passwordConfirmation" />
            <form:errors path="passwordConfirmation" cssStyle="color: firebrick" />
        </p>

        <input type="submit" value="Register">
    </form:form>
</body>
</html>
