angular.module('app',[]).controller('indexController',function($scope, $http, $location){

const contextPath = 'http://localhost:8090/store';





$scope.loadPage = function () {
        $http.get(contextPath + "/api/v1/products")
        .then(function (response) {
            $scope.products = response.data;
        });
        };

   $scope.addProductToCart = function(id){
        $http.get(contextPath + "/api/v1/products"+ "/" + id)
                .then(function (response) {
                   $scope.loadCart();
                });

   }


   $scope.deleteProductFromCart = function(id){
   $http({
            url: contextPath + '/api/v1/carts',
                            method: 'Delete',
                            params: {
                               id : id
                               }
   }).then (function (response){
   $scope.loadPage();
   });
   };

   $scope.loadCart= function(){
   $http.get(contextPath + "/api/v1/carts")
           .then(function (response) {
               $scope.cart = response.data;
           });
   }
   $scope.clearCart = function(){
    $http.get(contextPath + "/api/v1/carts/clear")
      .then (function (response){
                $scope.loadCart();
                })
   }




    $scope.loadPage();



 });

