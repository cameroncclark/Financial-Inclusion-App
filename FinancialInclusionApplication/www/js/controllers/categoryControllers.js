fIApp.controller('CategoryCtrl', function ($scope, $ionicModal, $http, $rootScope) {

 /**
  * Initialisation function, this retrieves the external list of categories
  */
  $scope.init = function () {
    $http.get('content/categories.json')
      .then(function (response) {
        $scope.categories = response.data;
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


});
