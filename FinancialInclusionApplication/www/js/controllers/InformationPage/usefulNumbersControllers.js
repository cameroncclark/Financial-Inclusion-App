fIApp.controller('UsefulNumbersCtrl', function($scope){
    $scope.name = 'Useful Numbers';
    $scope.content = 'Useful Numbers will be stored here';
    
    // Information on useful numbers. website may be redundant for this purpose
    $scope.numbersList = [
            {name:'A Useful Company', number:'01411234567'},
            {name:'Another Useful Company', number:'01411234568'},
            {name:'Stuart Cuthbertson', number:'07766624002'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.numbersList;
    };
});