fIApp.component("mortgageCalculator",{
    templateUrl:"templates/calculator.mortgage.html",
    controller: function mortgageCalculatorCtrl($scope){

        $scope.test = "Mortgage calculator";
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