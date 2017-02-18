fIApp.controller('CalculatorCtrl', function ($scope) {
    $scope.name = 'Calculator Page';

    $scope.isActiveWelcome = false;
    $scope.isActiveInterest = false;
    $scope.isActiveSavings = false;
    $scope.isActiveMortgage = false;

    $scope.welcome = false;
    $scope.savings = false;
    $scope.interest = false;
    $scope.mortgage = false;

    $scope.makeActive = function (active) {
        switch (active) {
            case "welcome":
                $scope.welcome = true;
                $scope.savings = false;
                $scope.interest = false;
                $scope.mortgage = false;
                $scope.isActiveWelcome = true;
                $scope.isActiveSavings = false;
                $scope.isActiveInterest = false;
                $scope.isActiveMortgage = false;
                break;
            case "savings":
                $scope.welcome = false;
                $scope.savings = true;
                $scope.interest = false;
                $scope.mortgage = false;
                $scope.isActiveWelcome = false;
                $scope.isActiveSavings = true;
                $scope.isActiveInterest = false;
                $scope.isActiveMortgage = false;
                break;
            case "interest":
                $scope.welcome = false;
                $scope.savings = false;
                $scope.interest = true;
                $scope.mortgage = false;
                $scope.isActiveWelcome = false;
                $scope.isActiveSavings = false;
                $scope.isActiveInterest = true;
                $scope.isActiveMortgage = false;
                break;
            case "mortgage":
                $scope.welcome = false;
                $scope.savings = false;
                $scope.interest = false;
                $scope.mortgage = true;
                $scope.isActiveWelcome = false;
                $scope.isActiveSavings = false;
                $scope.isActiveInterest = false;
                $scope.isActiveMortgage = true;
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
        }
    }
});