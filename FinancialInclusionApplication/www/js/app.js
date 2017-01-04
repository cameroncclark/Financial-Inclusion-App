// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
var fIApp = angular.module('financialInclusionApp', ['ionic', 'rzModule']);

fIApp.run(function ($ionicPlatform, $http, $rootScope) {
  $ionicPlatform.ready(function () {
    if (window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }

    if (window.StatusBar) {
      StatusBar.styleDefault();
    }

    //This is going to be a map that links category ID to both the titles under that category and the urls
    $rootScope.topicMaps = [];

    //Getting all the categories 
    $http.get('content/categories.json')
      .then(function (categories) {
        //For each of the categories make an empty map
        for (var i = 0; i < categories.data.length; i++) {
          var newCategoryMap = {};
          newCategoryMap[categories.data[i].ID] = {"titles": [], "urls":[]};
          $rootScope.topicMaps.push(newCategoryMap);

        }
        
        //This get request obtains all topic urls
        $http.get('content/topics.json')
          .then(function (topicURLS) {

            //For each of the URLS
            for (var i = 0; i < topicURLS.data.length; i++) {
              $http.get('content/topics/' + topicURLS.data[i])
                .then(function (topics) {
                  // for(var j = 0; j < $rootScope.topicMaps.length; j++){
                    
                  // }
                });
            }
          });
      });




  });
});
