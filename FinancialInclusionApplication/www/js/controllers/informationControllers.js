fIApp.controller('InformationCtrl', function($scope){
    
       $scope.name = 'Information Page';
       $scope.infoCategories = [
            {name:'External Links', url:'externalLinks', icon:'earth'},
            {name:'Useful Numbers', url:'usefulNumbers', icon:'iphone'},
            {name:'Help', url:'help', icon:'help-buoy'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.infoCategories;
    };
 
});