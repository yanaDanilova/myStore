angular.module('app',[]).controller('indexController',function($scope, $http, $location){

const contextPath = 'http://localhost:8090/store';





$scope.loadPage = function () {
        $http.get(contextPath + "/api/v1/products")
        .then(function (response) {
            $scope.products = response.data;
        });
        };

  $scope.addProductToCart = function(id){
     $http({
                 url: contextPath + '/api/v1/carts/add',
                 method: 'Get',
                 params: {
                    id : id
                 }
     }).then (function successCallback(response){
         console.log(response.data);
        $scope.loadCart();
     }), (function errorCallback(response){
     console.log(response.data);
     alert('Error'+ response.data)})
     };


   $scope.deleteProductFromCart = function(id){
   $http({
            url: contextPath + '/api/v1/carts/delete',
                            method: 'Get',
                            params: {
                               id : id
                               }
   }).then (function (response){
   console.log(1);
   $scope.loadCart();
   });
   };

 $scope.loadCart = function(){
    $http.get(contextPath + "/api/v1/carts")
    .then(function(response){
    $scope.cart = response.data;
    console.log(response.data);
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

