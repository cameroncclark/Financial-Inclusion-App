fIApp.component("interestCalculator",{
    templateUrl:"templates/calculator.interest.html",
    controller: function interestCalculatorCtrl($scope, $ionicModal){

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalHelp = modal;
        });
      
        $scope.closeHelp = function() {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function() {
            $scope.modalHelp.show();
        };

        $scope.test = "Interest calculator";
        $scope.slider1 = {
            value: 10,
            options: {
                floor: 1,
                ceil: 25,
                step: 1,
                minLimit: 1,
                maxLimit: 25
            }
        };    
    }

});
