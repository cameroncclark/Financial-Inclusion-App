fIApp.controller('ExternalLinksCtrl', function($scope){
    $scope.name = 'External Links';
    $scope.content = 'External Links stored here';

    $scope.externalLinksList = [
        {name: 'Facebook', blurb:'', website: 'http://www.facebook.com'},
        {name: 'YouTube', blurb:'', website: 'http://www.youtube.com'},
        {name: 'Twitter', blurb:'', website: 'http://www.twitter.com'}];

    $scope.getExternalLinks = function(){
        return $scope.externalLinksList;
    };
});