
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="contracts" scope="request" type="java.util.List<ru.levelup.junior.entities.Contract>"/>
<html>
<head>
    <title>Dashboard</title>
    <style type="text/css">
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-radius: 10px;
            border-spacing: 0;
            text-align: center;
        }
        th {
            background: #BCEBDD;
            color: white;
            text-shadow: 0 1px 1px #2D2020;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        /*th:first-child, td:first-child {*/
            /*text-align: left;*/
        /*}*/
        th:first-child {
            border-top-left-radius: 10px;
        }
        th:last-child {
            border-top-right-radius: 10px;
            border-right: none;
        }
        td {
            padding: 10px 20px;
            background: #F8E391;
        }
        tr:last-child td:first-child {
            border-radius: 0 0 0 10px;
        }
        tr:last-child td:last-child {
            border-radius: 0 0 10px 0;
        }
        tr td:last-child {
            border-right: none;
        }
    </style>
</head>
<body>
    <h1>Welcome, ${sessionScope['clientName']}!</h1>

    <table>
        <thead>
            <tr>
                <th>Phone number</th>
                <th>Tariff</th>
                <th>$ per month</th>
                <th>Owner's name</th>
                <th>Owner's surname</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${contracts}" var="contract">
            <tr>
                <td>${contract.phoneNumber}</td>
                <td>${contract.tariff.name}</td>
                <td>${contract.tariff.price}</td>
                <td>${contract.client.firstName}</td>
                <td>${contract.client.lastName}</td>
            </tr>
            </c:forEach>

        </tbody>
    </table>
</body>
</html>
