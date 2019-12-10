angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/api/example').
        then(function(response) {
            $scope.greeting = response.data;
        });
    });
