<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contractOptions" scope="request" type="java.util.List<ru.levelup.junior.entities.Option>"/>
<jsp:useBean id="notConnectedOptions" scope="request" type="java.util.List<ru.levelup.junior.entities.Option>"/>
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
<h3>Connected options: </h3>
<c:if test="${contractOptions.size() == 0}">
    <p>There is no options connected to current contract</p>
</c:if>
<c:if test="${contractOptions.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Cost of connection</th>
            <th>Monthly payment</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contractOptions}" var="option">
            <tr>
                <td>${option.name}</td>
                <td>${option.costOfConnection}</td>
                <td>${option.monthlyPayment}</td>
                <td>
                    <button onclick="window.location.href='/dashboard/options/remove?contractId=${contract.id}&optionId=${option.id}'">
                        Disconnect option
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<h3>Able to connect options: </h3>
<c:if test="${notConnectedOptions.size() == 0}">
    <p>There is no options able to connect</p>
</c:if>
<c:if test="${notConnectedOptions.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Cost of connection</th>
            <th>Monthly payment</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${notConnectedOptions}" var="ncOption">
            <tr>
                <td>${ncOption.name}</td>
                <td>${ncOption.costOfConnection}</td>
                <td>${ncOption.monthlyPayment}</td>
                <td>
                    <button onclick="window.location.href='/dashboard/options/add?contractId=${contract.id}&optionId=${ncOption.id}'">
                        Connect option
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<button onclick="window.location.href='../dashboard'">Show contracts</button>
</body>
</html>