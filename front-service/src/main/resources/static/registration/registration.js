angular.module('myStoreApp').controller('registrationController', function ($scope, $http, $location,$localStorage) {

    const contextPathAuth = 'http://localhost:5555/auth';


    $scope.functionRegistration = function () {
        $http.post( contextPathAuth + '/api/v1/auth/registration',$scope.reguser)
            .then(function (response) {
                if(response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.myStoreCurrentUser = {username: $scope.reguser.username, token: response.data.token};
                    $localStorage.reguser = null;
                    $location.path("/");
                }

            });
    };

});
