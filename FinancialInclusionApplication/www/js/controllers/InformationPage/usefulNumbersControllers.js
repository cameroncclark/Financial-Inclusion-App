fIApp.controller('UsefulNumbersCtrl', function($scope){
    $scope.name = 'Useful Numbers';
    $scope.content = 'Useful Numbers will be stored here';
    
    $scope.numbersList = [
            {name:'A Useful Company', blurb:'This company is really useful. Here are it\'s credentials!' , number:'+441411234567'},
            {name:'Another Useful Company', blurb:'This company is also really useful. Here are it\'s credentials!', number:'+441411234568'},
            {name:'Stuart Cuthbertson', blurb:'He\'s so sound, look, press the phone button to call.', number:'+447766624002'},
            {name:'Angus Arnold', blurb:'Angus has a sore ear, don\'t call him, he wont be able to hear you', number:'+447545616173'},
            {name:'Cameron Clark', blurb:'Cammy gets called 20 times a day, his phone will probably be busy.', number:'+447788464196'}];

    $scope.getInfoCategoryValues = function(){
        return $scope.numbersList;
    };
});