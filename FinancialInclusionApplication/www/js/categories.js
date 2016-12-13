fIApp.controller('CategoriesCtrl', function($scope){
    var categories = [{name:'Banking', url:'banking', progress:80}, {name:'Tax', url:'tax', progress:50},
        {name:'Savings', url:'savings', progress:10}];
    var subCategories = [{name:'Current Accounts',url:'current_account'}]
    $scope.getCategoryValues = function(){
        return categories;
    };

    $scope.getSubCategoryValues = function(){
        return subCategories;
    };
});