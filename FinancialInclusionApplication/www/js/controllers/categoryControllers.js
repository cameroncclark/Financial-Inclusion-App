fIApp.controller('CategoryCtrl', function ($scope, $ionicModal, $http, $rootScope, dbAccessor) {

  /**
   * Initialisation function, this retrieves the external list of categories
   */
  $scope.init = function () {

    $http.get('content/categories.json')
      .then(function (response) {
        $scope.categories = response.data;
          var promise = dbAccessor.getCategoryProgress();
           promise.then(function (progressData) {
            for(var i = 0; i < $scope.categories.length; i++){
              for(var j = 0; j < progressData.length; j++){
                if($scope.categories[i].name === progressData[j].name){
                  $scope.categories[i].progress = progressData[j].progress;
                  break;
                }
              }
            }
            console.log(JSON.stringify($scope.categories));
          });
      });
  }

  $scope.name = 'Categories Page';
  $scope.modalSubCategories = [];

  $ionicModal.fromTemplateUrl('templates/subcategoriesModal.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.subCategoriesModal = modal;
  });

  $scope.launchCategory = function (categoryId) {
    $scope.modalSubCategories = $rootScope.topicMaps[categoryId];
    $scope.subCategoriesModal.show();
  }

  $scope.closeModal = function () {
    $scope.modalSubCategories = [];
    $scope.subCategoriesModal.hide();
  }

  $scope.log = function (name) {
    console.log(name);
  }

  $scope.checkCategoryProgress = function (name) {
    var promise = dbAccessor.getCategoryProgress(name);
    promise.then(function (response) {
      var progress = response;
      console.log("progress=" + progress);
      return progress;
    });
  }
});
