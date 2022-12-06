angular.module('myStoreApp').controller('cartController', function ($scope, $http, $localStorage) {

   const contextPathCart = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        $http.get(contextPathCart + '/api/v1/carts/')
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.loadCart();
            });
    }

     $scope.deleteProductFromCart = function(id){
           $http({
                    url: contextPathCart + '/api/v1/carts/delete',
                                    method: 'Get',
                                    params: {
                                       id : id
                                       }
           }).then (function (response){
           console.log(1);
           $scope.loadCart();
           });
           }

    $scope.addProductToCart = function (id) {
            $http.get(contextPathCart + '/api/v1/carts/add/'+ id)
                .then(function (response) {
                $scope.loadCart();
                });
            };

      $scope.clearCart = function(){
         $http.get(contextPathCart + "/api/v1/carts/clear")
           .then (function (response){
                     $scope.loadCart();
                     })
        }

    $scope.loadCart();


});
