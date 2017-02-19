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
            $scope.modalSubCategories[i].progress = progressData[j].progress;
            break;
          }
        }
      }
      console.log("SUBCAT:" + JSON.stringify($scope.modalSubCategories));
    });
    $scope.subCategoriesModal.show();
    //TESTING SPACE

    /**
     * This Block Of Code Is For Updating Categories
     */
    var updateSubCategoryPromise = dbAccessor.updateSubCategoryProgress();
    updateSubCategoryPromise.then(function (output) {
      var updateCategoryPromise = dbAccessor.updateCategoryProgress();
      updateCategoryPromise.then(function (output) {
        var categoryPromise = dbAccessor.getCategoryProgress();
        categoryPromise.then(function (progressData) {
          for (var i = 0; i < $scope.categories.length; i++) {
            for (var j = 0; j < progressData.length; j++) {
              if ($scope.categories[i].name === progressData[j].name) {
                $scope.categories[i].progress = progressData[j].progress;
                break;
              }
            }
          }
          console.log("Categories Being Updated Within Application");
        });
        dbAccessor.checkOverallProgress();
      });
    });

    //END TESTING SPACE
  }

  $scope.closeModal = function () {
    $scope.modalSubCategories = [];
    $scope.subCategoriesModal.hide();
  }

  $scope.log = function (name) {
    console.log(name);
  }
});
