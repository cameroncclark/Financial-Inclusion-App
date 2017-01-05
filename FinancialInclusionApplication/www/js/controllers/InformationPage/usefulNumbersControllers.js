fIApp.controller('UsefulNumbersCtrl', function($scope){
    $scope.name = 'Useful Numbers';
    $scope.content = 'Useful Numbers will be stored here';
    
    $scope.numbersList = [
            {name:'A Useful Company', number:'01411234567'},
            {name:'Another Useful Company', number:'01411234568'},
            {name:'Stuart Cuthbertson', number:'07766624002'},
            {name:'Angus Arnold', number:'07545616173'},
            {name:'Cameron Clark', number:'07788464196'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.numbersList;
    };
});