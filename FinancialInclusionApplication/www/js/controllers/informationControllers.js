fIApp.controller('InformationCtrl', function($scope){
    
       $scope.name = 'Information Page';
       $scope.infoCategories = [
            {name:'External Links', url:'externalLinks', icon:'earth', colour:'#E06255'},
            {name:'Useful Numbers', url:'usefulNumbers', icon:'iphone', colour:'#FFA500'},
            {name:'Help', url:'help', icon:'help-buoy', colour:'#28D230'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.infoCategories;
    };
 
});