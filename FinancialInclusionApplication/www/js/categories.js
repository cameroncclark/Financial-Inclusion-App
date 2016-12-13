fIApp.controller('CategoriesCtrl', function($scope){
    var categories = [{name:'Banking', url:'banking'}, {name:'Tax', url:'tax'},
        {name:'Savings', url:'savings'}];
    var subCategories = [{name:'Current Accounts',url:'current_account'}]
    $scope.getCategoryValues = function(){
        return categories;
    };

    $scope.getSubCategoryValues = function(){
        return subCategories;
    };
});