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
          for (var i = 0; i < $scope.categories.length; i++) {
            for (var j = 0; j < progressData.length; j++) {
              if ($scope.categories[i].name === progressData[j].name) {
                $scope.categories[i].progress = progressData[j].progress;
                break;
              }
            }
          }
          console.log("PRINT CAT" + JSON.stringify($scope.categories));
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
    var promise = dbAccessor.getSubcatProgress();
    promise.then(function (progressData) {
      for (var i = 0; i < $scope.modalSubCategories.length; i++) {
        for (var j = 0; j < progressData.length; j++) {
          if ($scope.modalSubCategories[i].title === progressData[j].name) {
            console.log("I GET HERE");
            console.log("PROGRESS:" + progressData[j].progress);
            $scope.modalSubCategories[i].progress = progressData[j].progress;
            console.log("SUBCAT:" + JSON.stringify($scope.modalSubCategories));
            break;
          }
        }
      }
    });
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
      console.log("Cat progress=" + progress);
      return progress;
    });
  }

  $scope.checkSubCategoryProgress = function (name) {
    var promise = dbAccessor.getSubCategoryProgress(name);
    promise.then(function (response) {
      var progress = response;
      console.log("Sub Cat progress=" + progress);
      return progress;
    });
  }
});
