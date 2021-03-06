fIApp.controller('UsefulNumbersCtrl', function ($scope, $http, dbAccessor) {
    $scope.name = 'Useful Numbers';
    $scope.content = 'Useful Numbers will be stored here';

    

    $http.get('content/usefulNumbers.json')
        .then(function(response) {
            $scope.numbersList = response.data;
            console.log($scope.numbersList);
        });

    $scope.getInfoCategoryValues = function () {
        return $scope.numbersList;
    };

    $scope.callingNumber = function () {
        dbAccessor.callingPhone();
    };
});