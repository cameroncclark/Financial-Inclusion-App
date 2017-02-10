fIApp.component("welcomeCalculator",{
    templateUrl:"templates/calculator.welcome.html",
    controller: function welcomeCalculatorCtrl($scope, $ionicModal){


        $ionicModal.fromTemplateUrl('templates/calculator.welcomeHelpModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalHelp = modal;
        });

$scope.closeHelp = function () {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function () {
            $scope.modalHelp.show();
        };
 $scope.test = "Calculators";
 $scope.helpHeader = "Calculators help";
 $scope.helpIntro = "Use the bar at the top to select which calculator you wish to use";
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