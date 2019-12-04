<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contractsToChose" scope="request" type="java.util.List<ru.levelup.junior.entities.Contract>"/>

<html>
<head>
    <title>Contracts</title>
    <script type="text/javascript" src="/script/contractsToChose.js"></script>
    <script type="text/javascript">
        page = 1;
    </script>
    <style>
        <%@include file="style.css" %>
    </style>
</head>
<body  onload="loadFreeContracts(page)">

<h3>Chose contract from the list below:</h3>
<c:if test="${contractsToChose.size() != 0}">
    <table>
        <thead>
        <tr>
            <th>Phone number</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="free-contracts-list">
            <tr>
                <td colspan="2">Loading...</td>
            </tr>
        <%--<c:forEach items="${contractsToChose}" var="contractTo">--%>
            <%--<tr>--%>
                <%--<td>${contractTo.phoneNumber}</td>--%>
                <%--<td>--%>
                    <%--<a href="/dashboard/tariffs?phoneNumber=${contractTo.phoneNumber}">Select</a>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        </tbody>
    </table>
</c:if>

<input type="button" onclick="loadFreeContracts(++page)" value="Next page">

</body>
</html>
