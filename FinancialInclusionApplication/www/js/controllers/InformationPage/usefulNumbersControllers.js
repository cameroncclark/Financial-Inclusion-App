fIApp.controller('UsefulNumbersCtrl', function($scope){
    $scope.name = 'Useful Numbers';
    $scope.content = 'Useful Numbers will be stored here';
    
    $scope.numbersList = [
            {name:'A Useful Company', number:'+441411234567'},
            {name:'Another Useful Company', number:'+441411234568'},
            {name:'Stuart Cuthbertson', number:'+447766624002'},
            {name:'Angus Arnold', number:'+447545616173'},
            {name:'Cameron Clark', number:'+447788464196'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.numbersList;
    };
});