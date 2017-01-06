fIApp.component("savingsCalculator",{
    templateUrl:"templates/calculator.savings.html",
    controller: function savingsCalculatorCtrl($scope, $ionicModal){

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalHelp = modal;
        });

        $ionicModal.fromTemplateUrl('templates/calculator.savingsCalcModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalSavingAnswer = modal;
        });



        $scope.closeHelp = function() {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function() {
            $scope.modalHelp.show();
        };

        $scope.closeSavingsCalc = function() {
            $scope.modalSavingAnswer.hide();
        };

        $scope.openSavingsCalc = function() {
            $scope.modalSavingAnswer.show();
        };

        

        $scope.test = "Savings calculator";
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

        $scope.help = "This is the help screen for the Savings Calculator";
    }

});