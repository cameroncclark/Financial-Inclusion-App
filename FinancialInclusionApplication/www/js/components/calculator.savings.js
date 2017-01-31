fIApp.component("savingsCalculator", {
    templateUrl: "templates/calculator.savings.html",
    controller: function savingsCalculatorCtrl($scope, $ionicModal) {

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalHelp = modal;
        });

        $ionicModal.fromTemplateUrl('templates/calculator.savingsCalcModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalSavingAnswer = modal;
        });



        $scope.closeHelp = function () {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function () {
            $scope.modalHelp.show();
        };

        $scope.closeSavingsCalc = function () {
            $scope.modalSavingAnswer.hide();
        };

        $scope.openSavingsCalc = function () {
            $scope.modalSavingAnswer.show();
        };



        $scope.test = "Savings Calculator";
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

        $scope.helpHeader = "Savings Calcualtor";
        
        $scope.helpIntro = "This calcualtor is used to calculate the amount of money you can save over a certain period of time. It does this by taking in your target goal, how much money you have saved per month as well as how much you have saved already.";

        $scope.helpContent = "Within the first text box, enter how much money you want to save. In the second, enter how much money you can afford to save per month. Finally, enter how much money you have already saved in the final text box then press the calcualte button to retrieve your answer!"

        $scope.helpHint = "Handy hint: Take a screenshot of your result so that you can review it later!"

    }

});