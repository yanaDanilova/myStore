angular.module('app',['ngStorage']).controller('indexController',function($scope, $http, $location, $localStorage){

const contextPathCore = 'http://localhost:5555/core';
const contextPathCart = 'http://localhost:5555/cart';

 $scope.tryToAuth = function () {
         $http.post( 'http://localhost:5555/auth/api/v1/auth', $scope.user)
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

 $scope.generatePagesIndexes = function (startPage, endPage) {
         let arr = [];
         for (let i = startPage; i < endPage + 1; i++) {
             arr.push(i);
         }
         return arr;
     };


$scope.loadPage = function (page) {
        $http({
            url: contextPathCore + '/api/v1/products',
            method: 'GET',
            params: {
                p: page,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

            console.log("PAGE FROM BACKEND")
            console.log($scope.productsPage);
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

