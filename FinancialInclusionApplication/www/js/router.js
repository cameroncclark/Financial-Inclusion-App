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
  });
  $urlRouterProvider.otherwise('/');
});