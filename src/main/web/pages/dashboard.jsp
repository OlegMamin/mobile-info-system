
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contracts" scope="request" type="java.util.List<ru.levelup.junior.entities.Contract>"/>
<html>
<head>
    <title>Dashboard</title>
    <script type="text/javascript">
        clientId = ${sessionScope['clientId']};
    </script>
    <script type="text/javascript" src="/script/dashboard.js"></script>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body onload="loadContracts(clientId)">
    <h1>Welcome, ${sessionScope['clientName']}!</h1>

    <c:if test="${contracts.size() != 0}">
    <table>
        <thead>
            <tr>
                <th>Phone number</th>
                <th>Tariff</th>
                <th>$ per month</th>
                <th>Owner's name</th>
                <th>Owner's surname</th>
                <th>Options</th>
            </tr>
        </thead>
        <tbody id="contracts-list">
            <tr>
                <td colspan="6">Loading...</td>
            </tr>

            <%--<c:forEach items="${contracts}" var="contract">--%>
            <%--<tr>--%>
                <%--<td>${contract.phoneNumber}</td>--%>
                <%--<td>${contract.tariff.name}</td>--%>
                <%--<td>${contract.tariff.price}</td>--%>
                <%--<td>${contract.client.firstName}</td>--%>
                <%--<td>${contract.client.lastName}</td>--%>
                <%--<td><a href="/dashboard/options?contractId=${contract.id}">Details</a></td>--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>

        </tbody>
    </table>
    </c:if>

    <c:if test="${contracts.size() == 0}">
        <p>You have not any contracts.</p>
    </c:if>
    <button onclick="window.location.href='dashboard/contracts'">Add contract</button>

</body>
</html>
