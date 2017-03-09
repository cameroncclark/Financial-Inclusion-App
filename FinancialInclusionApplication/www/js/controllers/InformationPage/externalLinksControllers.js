fIApp.controller('ExternalLinksCtrl', function($scope, $http, dbAccessor) {
    $scope.name = 'External Links';
    $scope.content = 'External Links stored here';

    $http.get('content/externalLinks.json')
        .then(function(response) {
            $scope.externalLinksList = response.data;
            console.log($scope.externalLinksList);
        });

    $scope.getExternalLinks = function() {
        return $scope.externalLinksList;
    };

    $scope.navigateToWebsite = function(urlObject) {
        try {
            var website = urlObject.website;
            window.open(website, '_system', 'location=yes');
            dbAccessor.loadingWebsite();
        } catch (err) {
            alert(err);
        }
    }
});