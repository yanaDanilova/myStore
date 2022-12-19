angular.module('myStoreApp').controller('cartController', function ($scope, $http, $localStorage) {

   const contextPathCart = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        $http.get(contextPathCart + '/api/v1/carts/' + $localStorage.myStoreGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

  $scope.createOrder = function () {
          $http.post('http://localhost:5555/core/api/v1/orders/create')
              .then(function (response) {
                  alert('The order has been placed');
                  $scope.loadCart();
              });
      }


     $scope.deleteProductFromCart = function(id){
           $http.get(contextPathCart + '/api/v1/carts/' + $localStorage.myStoreGuestCartId+  '/delete/'+ id)
                           .then(function (response) {
                           $scope.loadCart();
                           });
                       };

      $scope.addProductToCart = function (id) {
             $http.get(contextPathCart + '/api/v1/carts/' + $localStorage.myStoreGuestCartId+ '/add/'+ id)
                 .then(function (response) {
                 $scope.loadCart();
                 });
         }


      $scope.clearCart = function(){
         $http.get(contextPathCart + '/api/v1/carts/' + $localStorage.myStoreGuestCartId+ '/clear')
           .then (function (response){
                     $scope.loadCart();
                     })
        }

    $scope.loadCart();


});
