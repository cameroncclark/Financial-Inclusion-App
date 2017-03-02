fIApp.controller('ExternalLinksCtrl', function ($scope,$http) {
    $scope.name = 'External Links';
    $scope.content = 'External Links stored here';

    $http.get('content/externalLinks.json')
        .then(function (response) {
            $scope.externalLinksList = response.data;
            console.log($scope.externalLinksList);
        });

    $scope.getExternalLinks = function () {
        return $scope.externalLinksList;
    };
});