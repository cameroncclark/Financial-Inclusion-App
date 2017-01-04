fIApp.controller('ExternalLinksCtrl', function($scope){
    $scope.name = 'External Links';
    $scope.content = 'External Links stored here';

    $scope.externalLinksList = [
        {name: 'Facebook', website: 'http://www.facebook.com'},
        {name: 'YouTube', website: 'http://www.youtube.com'},
        {name: 'Twitter', website: 'http://www.twitter.com'}];

    $scope.getExternalLinks = function(){
        return $scope.externalLinksList;
    };
});