<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello AngularJS</title>
    <script
            src='http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.js'>
    </script>
    <script
            src='http://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular-resource.js'>
    </script>
    <script type="text/javascript" src="/script/hello.js"></script>
    <style>
        <%@include file="style.css" %>
    </style>
</head>

<body ng-app='conRestApp'>
    <table ng-controller='ContractsCtrl'>
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
        <tbody>
        <tr ng-repeat='usersContract in usersContracts'>
            <td>{{ usersContract.phoneNumber }}</td>
            <td>{{ usersContract.tariff.name }}</td>
            <td>{{ usersContract.tariff.price }}</td>
            <td>{{ usersContract.client.firstName }}</td>
            <td>{{ usersContract.client.lastName }}</td>
            <td>Details</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>