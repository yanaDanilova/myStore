angular.module('app',['ngStorage']).controller('indexController',function($scope, $http, $location, $localStorage){

const contextPathCore = 'http://localhost:8090/store';
const contextPathCart = 'http://localhost:8190/store-carts';

 $scope.tryToAuth = function () {
         $http.post(contextPathCore + '/api/v1/auth', $scope.user)
             .then(function successCallback(response) {
                 if (response.data.token) {
                     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                     $localStorage.myStoreCurrentUser = {username: $scope.user.username, token: response.data.token};

                     $scope.user.username = null;
                     $scope.user.password = null;
                 }
             }, function errorCallback(response) {
             });
     };

 $scope.tryToLogout = function () {
         $scope.clearUser();
     };

     $scope.clearUser = function () {
         delete $localStorage.myStoreCurrentUser;
         $http.defaults.headers.common.Authorization = '';
     };


$scope.isUserLoggedIn = function () {
         if ($localStorage.myStoreCurrentUser) {
             return true;
         } else {
             return false;
         }
     };



$scope.loadPage = function () {
        $http.get(contextPathCore + "/api/v1/products")
        .then(function (response) {
            $scope.products = response.data;
        });
        };

  $scope.addProductToCart = function(id){
     $http({
                 url: contextPathCart + '/api/v1/carts/add',
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
            url: contextPathCart + '/api/v1/carts/delete',
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
    $http.get(contextPathCart + "/api/v1/carts")
    .then(function(response){
    $scope.cart = response.data;
    console.log(response.data);
    });
    }

   $scope.clearCart = function(){
    $http.get(contextPathCart + "/api/v1/carts/clear")
      .then (function (response){
                $scope.loadCart();
                })
   }
    $scope.createOrder = function(){
  $http.post(contextPathCore + "/api/v1/orders")
        .then (function (response){
                 alert("OK")
                  })
     }



     if ($localStorage.myStoreCurrentUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.myStoreCurrentUser.token;
            }
    $scope.loadPage(1);
    $scope.loadCart();



 });

