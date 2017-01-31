fIApp.component("interestCalculator",{
    templateUrl:"templates/calculator.interest.html",
    controller: function interestCalculatorCtrl($scope, $ionicModal){

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalHelp = modal;
        });

        $ionicModal.fromTemplateUrl('templates/calculator.interestCalcModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalInterestAnswer = modal;
        });
      
        $scope.closeHelp = function() {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function() {
            $scope.modalHelp.show();
        };

        $scope.closeInterestCalc = function() {
            $scope.modalInterestAnswer.hide();
        };

        $scope.openInterestCalc = function() {
            $scope.modalInterestAnswer.show();
        };


        $scope.test = "Interest Calculator";
        $scope.slider1 = {
            value: 1,
            options: {
                id: 'slider-id',
                floor: 1,
                ceil: 25,
                step: 1,
                minLimit: 1,
                maxLimit: 25
            }
        }; 

        $scope.helpHeader = "Interest Calcualtor";

        $scope.helpIntro =   "This calcualtor is used to calculate the amount of interest your money would generate over a certain period of time. It does this by taking in your current savings, current interest rate as well as how many years you plan to save for.";

        $scope.helpContent = "Within the first text box enter how much money you currently have that you wish to gain interest on. Within the second text box enter your annual interest rate that is offered by your bank. Finally use the slider to input the number of years that you plan to leave the money in your account. Press the calculate button to retrieve your result.";

        $scope.helpHint = "Handy hint: Take a screenshot of your result so that you can review it later!";
    }
});
