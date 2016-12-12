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
  });
  $urlRouterProvider.otherwise('/');
});