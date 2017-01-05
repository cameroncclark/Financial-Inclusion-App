fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
    $scope.name = $stateParams.pageTitle;
    $scope.content = "";
    
    $http.get('content/topics/'+$stateParams.contentURL)
      .then(function (response) {
        $scope.topic = response.data;
        $scope.content = $scope.parse($scope.topic.content);
      });

    $scope.parse = function(content){
        return $sce.trustAsHtml(content);
    }

});