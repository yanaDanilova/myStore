angular.module('myStoreApp').controller('ordersController', function ($scope, $http) {
    $scope.loadOrders = function () {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.orders = response.data;
            });
    };

    $scope.loadOrders();
});
