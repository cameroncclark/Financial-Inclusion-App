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
            workOutResult();
            $scope.modalSavingAnswer.show();
        };



        $scope.header = "Savings Calculator";
        
        $scope.helpHeader = "Savings Calcualtor";

        $scope.helpIntro = "This calcualtor is used to calculate the amount of money you can save over a certain period of time. It does this by taking in your target goal, how much money you have saved per month as well as how much you have saved already.";

        $scope.helpContent = "Within the first text box, enter how much money you want to save. In the second, enter how much money you can afford to save per month. Finally, enter how much money you have already saved in the final text box then press the calcualte button to retrieve your answer!"

        $scope.helpHint = "Handy hint: Take a screenshot of your result so that you can review it later!"


        $scope.savingsGoalValue;
        $scope.alreadySavedValue;
        $scope.perMonthValue;

        $scope.result;
        $scope.numMonths;
        $scope.numDays;

        var workOutResult = function () {
            $scope.result = (($scope.savingsGoalValue * 1) - ($scope.alreadySavedValue * 1)) / $scope.perMonthValue;

            var splitNumber =
                [
                    Math.floor($scope.result),
                    $scope.result % 1
                ];
            $scope.numMonths = splitNumber[0];
            $scope.numDays = Math.ceil(splitNumber[1] * 30);
        }
    }
});