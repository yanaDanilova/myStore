(function () {
    angular
        .module('myStoreApp', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.myStoreCurrentUser) {
            try {
                let jwt = $localStorage.myStoreCurrentUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.myStoreCurrentUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
        }
    }
})();

angular.module('myStoreApp').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

    $scope.tryToAuth = function () {
             $http.post('http://localhost:5555/auth/api/v1/auth', $scope.user)
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


});