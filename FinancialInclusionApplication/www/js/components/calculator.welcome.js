fIApp.component("welcomeCalculator",{
    templateUrl:"templates/calculator.welcome.html",
    controller: function welcomeCalculatorCtrl($scope){

        $scope.test = "Welcome";
        $scope.slider1 = {
            value: 10,
            options: {
                floor: 0,
                ceil: 250,
                step: 1,
                minLimit: 10,
                maxLimit: 250
            }
        };

        $scope.slider2 = {
            value: 10,
            options: {
                floor: 0,
                ceil: 20,
                step: 1,
                minLimit: 1,
                maxLimit: 20
            }
        };
    }

});