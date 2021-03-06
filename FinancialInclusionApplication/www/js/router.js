fIApp.config(function($stateProvider, $urlRouterProvider){
  $stateProvider.state('home', {
    url:'/',
    templateUrl: 'templates/home.html',
    controller: 'HomeCtrl'
  })
  .state('categories', {
    url:'/categories',
    templateUrl: 'templates/categories.html',
    controller: 'CategoryCtrl'
  })
  .state('content', {
    url:'/content/:contentURL/:pageTitle',
    templateUrl: 'templates/content.html',
    controller: 'ContentCtrl'
  })
  .state('quiz', {
    url:'/quiz/:quizFile',
    templateUrl: 'templates/quizDisplay.html',
    controller: 'QuizCtrl'
  })
  .state('completeQuiz', {
    url:'/quiz/answers',
    templateUrl: 'templates/quizAnswersDisplay.html',
    controller: 'AnswersCtrl',
    params: {
      quizData: null
    }
  })
  .state('banking', {
    url:'/banking',
    templateUrl: 'templates/banking.html',
    controller: 'BankingCtrl'
  })
  .state('tax', {
    url:'/tax',
    templateUrl: 'templates/tax.html',
    controller: 'TaxCtrl'
  })
  .state('calculator', {
    url:'/calculator',
    templateUrl: 'templates/calculator.html',
    controller: 'CalculatorCtrl'
  })
  .state('savings', {
    url:'/savings',
    templateUrl: 'templates/savings.html',
    controller: 'SavingsCtrl'
  })
  .state('current_account', {
    url:'/current_account',
    templateUrl: 'templates/current_account.html',
    controller: 'CurrentAccountCtrl'
  })
  .state('information',{
    url:'/information',
    templateUrl: 'templates/information.html',
    controller: 'InformationCtrl'
  })
  .state('externalLinks',{
    url:'/externalLinks',
    templateUrl: 'templates/information.externalLinks.html',
    controller: 'ExternalLinksCtrl'
  })
  .state('usefulNumbers',{
    url:'/usefulNumbers',
    templateUrl: 'templates/information.usefulNumbers.html',
    controller: 'UsefulNumbersCtrl'
  })
  .state('help',{
    url:'/help',
    templateUrl: 'templates/information.help.html',
    controller: 'HelpCtrl'
  });

  $urlRouterProvider.otherwise('/');
});