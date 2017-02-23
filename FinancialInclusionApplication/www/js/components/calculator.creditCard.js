fIApp.component("creditcardCalculator", {
    templateUrl: "templates/calculator.creditCard.html",
    controller: function creditCardCalculatorCtrl($scope, $ionicModal) {

        var vm = this;

        $ionicModal.fromTemplateUrl('templates/calculator.helpModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalHelp = modal;
        });

        $ionicModal.fromTemplateUrl('templates/calculator.creditCardCalcModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalCreditCardAnswer = modal;
        });

        $scope.closeHelp = function() {
            $scope.modalHelp.hide();
        };

        $scope.openHelp = function() {
            $scope.modalHelp.show();
        };

         $scope.closeCreditCardCalc= function() {
            $scope.modalCreditCardAnswer.hide();
        };

        $scope.openCreditCardCalc = function() {
            workOutResult();
            $scope.modalCreditCardAnswer.show();
        };



        $scope.test = "Credit Card Calculator";
        $scope.creditSlider = {
            value: 1,
            options: {
                floor: 0,
                ceil: 500,
                step: 1,
                minLimit: 1,
                maxLimit: 500
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

        $scope.helpHeader = "Credit Card Calcualtor";

        $scope.visible = false;

        $scope.helpIntro = "This calcualtor is used to calculate the amount of money you can save over a certain period of time. It does this by taking in your target goal, how much money you have saved per month as well as how much you have saved already.";

        $scope.helpContent = "Within the first text box, enter how much money you want to save. In the second, enter how much money you can afford to save per month. Finally, enter how much money you have already saved in the final text box then press the calcualte button to retrieve your answer!"

        $scope.helpHint = "Handy hint: Take a screenshot of your result so that you can review it later!"


        $scope.oustandingBalance;
        $scope.APR;
        var rValue = 0;

        $scope.result;
        $scope.numYears;
        $scope.numMonths;
        $scope.numDays;
        //$scope.result =  $scope.outstandingBalance * ((rValue * (1 + rValue)^$scope.creditSlider)/(((1 + rValue)^$scope.creditSlider)-1));


        var workOutResult = function () {

var mRate=($scope.APR/100)/12;
            var remainingBalance=$scope.oustandingBalance;
	var startBalance=$scope.oustandingBalance;
	var minPayment=mRate*$scope.oustandingBalance;
	var months=0;
	var lastPayment;
             rValue = ($scope.APR/12);
             var interest=0;
             while (remainingBalance>0)
	{
		months++;
		interest += remainingBalance*mRate;
		remainingBalance=remainingBalance*(1 + mRate)-$scope.creditSlider.value;
	}

            //$scope.result = $scope.oustandingBalance * ((rValue * (Math.pow((1 + rValue), $scope.creditSlider.value))) / (Math.pow((1 + rValue), $scope.creditSlider.value) - 1));
            //$scope.result = Math.pow(2,2);
            //$scope.result =  $scope.outstandingBalance * (1 + rValue)^$scope.creditSlider.value;
            //$scope.result =  rValue*(1 + rValue);
            $scope.result = months;
            $scope.totalAmount = interest;

            var splitNumber =
                [
                    Math.floor($scope.result),
                    $scope.result % 1
                ];
            $scope.numYears = Math.floor(splitNumber[0]/12);
            $scope.numMonths = Math.floor(splitNumber[0]%12);
            $scope.numDays = Math.ceil(splitNumber[1] * 30);
        }
        
    
        
    }
});