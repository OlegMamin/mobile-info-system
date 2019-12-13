var app = angular.module('conRestApp', ['ngResource']);

app.factory("usersContracts", function($resource) {
    return $resource("http://localhost:8080/api/contracts/find?clientId=1");
});

app.controller("ContractsCtrl", function($scope, usersContracts) {
    usersContracts.query(function(data) {
        $scope.usersContracts = data;
    }, function(err) {
        console.error("Error occured: ", err);
    });
});