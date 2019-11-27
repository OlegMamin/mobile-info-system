<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="optionList" scope="request" type="java.util.List<ru.levelup.junior.entities.Option>"/>
<jsp:useBean id="contract" scope="request" type="ru.levelup.junior.entities.Contract"/>

<html>
<head>
    <title>Options</title>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body>
<h3>Contract number: ${contract.phoneNumber}.</h3>
<h3>Tariff: ${contract.tariff.name}.</h3>
<h3>Options: </h3>
<c:if test="${optionList.size() == 0}">
    <p>No options in current contract</p>
</c:if>
<c:if test="${optionList.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Cost of connection</th>
            <th>Monthly payment</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${optionList}" var="option">
            <tr>
                <td>${option.name}</td>
                <td>${option.costOfConnection}</td>
                <td>${option.monthlyPayment}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</c:if>
<button onclick="window.location.href='../dashboard'">Show contracts</button>
</body>
</html>