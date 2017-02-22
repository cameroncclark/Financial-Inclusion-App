
fIApp.controller('CalculatorCtrl', function($scope, $ionicModal){
    $scope.name = 'Calculator Page';

    $scope.dropdownActive = false;
    
    $scope.isActiveWelcome = false;
     $scope.isActiveInterest = false;
     $scope.isActiveSavings = false;
     $scope.isActiveMortgage = false;
     $scope.isActiveCreditCard = false;

     $scope.welcome = false;
     $scope.savings = false;
     $scope.interest = false;
     $scope.mortgage = false;
     $scope.creditCard = false;

     $ionicModal.fromTemplateUrl('templates/calculator.selectorModal.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalSelector = modal;
        });

        $scope.openSelector = function () {
            $scope.modalSelector.show();
        };

        $scope.closeSelector = function () {
            $scope.modalSelector.hide();
        };

      

     


    $scope.makeActive = function (active) {
        switch (active) {
            case "welcome":

            $scope.welcome = true;
            $scope.savings = false;
            $scope.interest = false;
            $scope.mortgage = false;
            $scope.creditCard = false;
            $scope.isActiveWelcome = true;
            $scope.isActiveSavings= false;
            $scope.isActiveInterest = false;
            $scope.isActiveMortgage = false;
            $scope.isActiveCreditCard = false;
             $scope.modalSelector.hide();
            break;
            case "savings":
            $scope.welcome = false;
            $scope.savings = true;
            $scope.interest = false;
            $scope.mortgage = false;
            $scope.creditCard = false;
            $scope.isActiveWelcome = false;
            $scope.isActiveSavings= true;
            $scope.isActiveInterest = false;
            $scope.isActiveMortgage = false;
            $scope.isActiveCreditCard = false;
            $scope.modalSelector.hide();
            break;
            case "interest":
            $scope.welcome = false;
            $scope.savings = false;
            $scope.interest = true;
            $scope.mortgage = false;
            $scope.creditCard = false;
            $scope.isActiveWelcome = false;
            $scope.isActiveSavings= false;
            $scope.isActiveInterest = true;
            $scope.isActiveMortgage = false;
            $scope.isActiveCreditCard = false;
             $scope.modalSelector.hide();
            break;
            case "mortgage":
            $scope.welcome = false;
            $scope.savings = false;
            $scope.interest = false;
            $scope.mortgage = true;
            $scope.creditCard = false;
            $scope.isActiveWelcome = false;
            $scope.isActiveSavings= false;
            $scope.isActiveInterest = false;
            $scope.isActiveMortgage = true;
            $scope.isActiveCreditCard = false;
             $scope.modalSelector.hide();
            break;
            case "creditCard":
            $scope.welcome = false;
            $scope.savings = false;
            $scope.interest = false;
            $scope.mortgage = false;
            $scope.creditCard = true;
            $scope.isActiveWelcome = false;
            $scope.isActiveSavings= false;
            $scope.isActiveInterest = false;
            $scope.isActiveMortgage = false;
            $scope.isActiveCreditCard = true;
             $scope.modalSelector.hide();
            break;

        }
    };

    $scope.getHelp = function (active) {
        switch (active) {
            case "savingsHelp":
                $scope.content = "This is the help screen for the savings calculator";
                break;
            case "interestHelp":
                $scope.content = "This is the help screen for the interest calculator";
                break;
            case "mortgageHelp":

            $scope.content = "This is the help screen for the mortgage calcualtor";
            break;
            case "creditCardHelp":
            $scope.content = "This is the help screen for the credit card calcualtor";
            break;

        }
    }

    $scope.createDropDown=function(){
        console.log("Dropdown");
    }

    
});