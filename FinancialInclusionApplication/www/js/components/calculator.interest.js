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

        $scope.test = "Interest calculator";
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
    }

});
