fIApp.config(function($stateProvider, $urlRouterProvider){
  $stateProvider.state('home', {
    url:'/',
    templateUrl: 'templates/home.html',
    controller: 'HomeCtrl'
  })
  .state('test', {
    url:'/test',
    templateUrl: 'templates/test.html',
    controller: 'HomeCtrl'
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