<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tariffsToChose" scope="request" type="java.util.List<ru.levelup.junior.entities.Tariff>"/>

<html>
<head>
    <title>Tariffs</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<h3>Contract number: ${phoneNumber}.</h3>
<h3>Chose tariff from the list below:</h3>
<c:if test="${tariffsToChose.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Tariff</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${tariffsToChose}" var="tariffsTo">
            <tr>
                <td>${tariffsTo.name}</td>
                <td>${tariffsTo.price}</td>
                <td>
                    <a href="/addContract?phoneNumber=${phoneNumber}&tariffId=${tariffsTo.id}">Apply</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</c:if>
</body>
</html>
