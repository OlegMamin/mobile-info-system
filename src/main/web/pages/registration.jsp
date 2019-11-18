
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="/register" method="post">
        <p>Name:
            <input type="text" name="name"/>
        </p>
        <p>Surname:
            <input type="text" name="surname"/>
        </p>
        <p>Passport number:
            <input type="text" name="passport-number"/>
        </p>
        <p>Login:
            <input type="text" name="login"/>
        </p>
        <p>Password:
            <input type="password" name="password"/>
        </p>
        <p>Confirm password:
            <input type="password" name="password-confirmation"/>
        </p>

        <input type="submit" value="Register">
    </form>
</body>
</html>
