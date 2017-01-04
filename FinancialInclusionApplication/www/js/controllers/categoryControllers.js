fIApp.controller('CategoryCtrl', function ($scope, $ionicModal) {
  $scope.name = 'Categories Page';
  $scope.modalSubCategories = [];

  $scope.categories = [{
    "categoryName": "Banking",
    "subCategories": ["Saving money", "APR", "Interest rates"],
    "progress": 50
  }, {
    "categoryName": "Finance",
    "subCategories": ["Managing money", "Credit ratings"],
    "progress": 70
  }];

  $ionicModal.fromTemplateUrl('templates/subCategoriesModal.html', {
    scope: $scope
  }).then(function (modal) {
    $scope.subCategoriesModal = modal;
  });

  $scope.launchCategory = function (category) {
      $scope.modalSubCategories = category.subCategories;
      $scope.subCategoriesModal.show();
  }

  $scope.closeModal = function(){
      $scope.modalSubCategories = [];
      $scope.subCategoriesModal.hide();
  }

  $scope.log = function (name) {
    console.log(name);
  }


});
