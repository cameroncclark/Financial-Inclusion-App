fIApp.controller('CalculatorCtrl', function($scope){
    $scope.name = 'Calculator Page';
    $scope.savings = false;
     $scope.isActiveInterest = false;
     $scope.isActiveSavings = false;

     $scope.interest = false;

    $scope.makeActive = function(active){
        switch(active){
            case "savings":
            $scope.savings = true;
            $scope.interest = false;
            $scope.isActiveSavings= true;
            $scope.isActiveInterest = false;
            break;
            case "interest":
            $scope.interest = true;
            $scope.savings = false;
            $scope.isActiveSavings= false;
            $scope.isActiveInterest = true;
            break;
        }
    };
});