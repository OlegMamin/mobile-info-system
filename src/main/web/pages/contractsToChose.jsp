
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contractsToChose" scope="request" type="java.util.List<ru.levelup.junior.entities.Contract>"/>

<html>
<head>
    <title>Contracts</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<h3>Chose contract from the list below:</h3>
<c:if test="${contractsToChose.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Phone number</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${contractsToChose}" var="contractTo">
            <tr>
                <td>${contractTo.phoneNumber}</td>
                <td>Add</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</c:if>
</body>
</html>
