
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello</h1>

    <p>${param['p']}</p>
    <p>${header['User-Agent']}</p>

    <form method="post" action="/login">
        <p>Login: <input type="text" name="login" value="${param['login']}"></p>
        <p>Password: <input type="password" name="password"></p>
        <p><input type="submit"></p>
    </form>

</body>
</html>
