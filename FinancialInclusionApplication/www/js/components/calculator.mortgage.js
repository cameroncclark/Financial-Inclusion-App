fIApp.component("mortgageCalculator",{
    templateUrl:"templates/calculator.mortgage.html",
    controller: function mortgageCalculatorCtrl($scope, $ionicModal){

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalHelp = modal;
        });

        $ionicModal.fromTemplateUrl('templates/calculator.mortgageCalcModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalMortgageAnswer = modal;
        });

      
        $scope.closeHelp = function() {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function() {
            $scope.modalHelp.show();
        };

         $scope.closeMortgageCalc = function() {
            $scope.modalMortgageAnswer.hide();
        };

        $scope.openMortgageCalc = function() {
            $scope.modalMortgageAnswer.show();
        };

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
            value: 1,
            options: {
                floor: 1,
                ceil: 20,
                step: 1,
                minLimit: 1,
                maxLimit: 20
            }
        };

        $scope.helpHeader = "Mortgage Calculator";
        
        $scope.helpIntro = "This calculator is usde to calculate mortgage repayments. It does this by taking in the amount of money you want to recieve in a mortgage, the interest rate of the mortgage and how many years you want to take in order to pay it off.";

        $scope.helpContent = "Within the first text box enter the amount of money that you wish to take out on a mortgage and within the second text box enter the interest rate for the mortgage that you have taken out. Use the slider to input the number of years that you wish to take to repay the morgage and then press the calcuate button to retrieve your result."

        $scope.helpHint = "Handy hint: Take a screenshot of your result so that you can review it later!"

    }

});