fIApp.controller('CalculatorCtrl', function($scope){
    $scope.name = 'Calculator Page';
    $scope.savings = false;
     $scope.isActive = false;

    $scope.makeActive = function(active){
        switch(active){
            case "savings":
            $scope.savings = true;
            $scope.isActive= true;
            break;
        }
    };
});