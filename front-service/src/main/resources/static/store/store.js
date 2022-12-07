angular.module('myStoreApp').controller('storeController', function ($scope, $http, $localStorage) {

    const contextPathCore = 'http://localhost:5555/core';
    const contextPathCart = 'http://localhost:5555/cart';


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



    $scope.generatePagesIndexes = function (startPage, endPage) {
            let arr = [];
            for (let i = startPage; i < endPage + 1; i++) {
                arr.push(i);
            }
            return arr;
        };

    $scope.addProductToCart = function (id) {
        $http.get(contextPathCart + '/api/v1/carts/add/'+ id)
            .then(function (response) {
            });
    }



    $scope.loadPage();
});

