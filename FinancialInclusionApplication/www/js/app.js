// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'

var db = null;

var fIApp = angular.module('financialInclusionApp', ['ionic', 'ngCordova', 'rzModule', 'ngSanitize']);

fIApp.run(function ($ionicPlatform, $http, $rootScope, $cordovaSQLite, dbAccessor) {

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

    var isAndroid = ionic.Platform.isAndroid();
    var isIOS = ionic.Platform.isIOS();
    if (window.StatusBar) {
      //StatusBar.styleDefault();
      StatusBar.hide();
    }

    //This is going to be a map that links category ID to both the titles under that category and the urls
    //This map looks like: {1: [titles: myTitle, url: myURL]}
    //WHERE: "1" is the Category ID. "titles" is the list of titles associated with that category, "urls" is the url for the file. 
    $rootScope.topicMaps = {};

    //All category codes that are available in the system
    var allCategoryCodes = [];

    //Getting all the categories 
    $http.get('content/categories.json')
      .then(function (categories) {
        //For each of the categories make an empty map
        for (var i = 0; i < categories.data.length; i++) {
          $rootScope.topicMaps[categories.data[i].ID] = [];
          allCategoryCodes.push(categories.data[i].ID);
        }

        //This get request obtains all topic urls
        $http.get('content/topics.json')
          .then(function (topicURLS) {


            //For each of the URLS
            for (var i = 0; i < topicURLS.data.length; i++) {
              //Make a call to get the relevant data and add it to the map
              makeCallToData(topicURLS.data[i]);
            }


          });
      });

    /**
     * This function takes in currentTopic, which is a PATH to the content.
     * Using this path, the content is extracted and added to the map under the correct category.
     */
    var makeCallToData = function (currentTopic) {
      $http.get('content/topics/' + currentTopic)
        .then(function (topics) {
          for (var j = 0; j < allCategoryCodes.length; j++) {
            if (allCategoryCodes[j] === topics.data.reference) {
              var titleURLObject = {};
              titleURLObject.title = topics.data.title;
              titleURLObject.url = currentTopic;
              $rootScope.topicMaps[allCategoryCodes[j]].push(titleURLObject);
              // console.log($rootScope.topicMaps);
            }
          }
        });
    }

    // Initialisation of databases for Android and iOS
    if (isAndroid || isIOS) {
      console.log("entered if");
      db = $cordovaSQLite.openDB({ name: 'my.db', location: 'default' });

      // Initialise all tables
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS people (id INTEGER PRIMARY KEY, firstname TEXT, lastname TEXT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS userData (id INTEGER PRIMARY KEY, name TEXT, location TEXT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS trophies (id INTEGER PRIMARY KEY, title TEXT, image TEXT, description TEXT, hint TEXT, acquired TINYINT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY, name NVARCHAR(50), percentageComplete INTEGER)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS subcategories (url NVARCHAR(50) PRIMARY KEY, name NVARCHAR(50), percentageComplete INTEGER, categoryID INTEGER, FOREIGN KEY(categoryID) REFERENCES categories(id)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS progress (objective NVARCHAR(50) PRIMARY KEY, counter INTEGER, valueChanged TINYINT");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS settings ()");

      var query = "SELECT count(id) AS c FROM userData";
      $cordovaSQLite.execute(db, query, []).then(function (result) {
        console.log("Number of rows in table " + result.rows[0].c);
      }, function (error) {
        console.log(error)
      });

      console.log("You're a phone");
    } else {
      console.log("not a phone");
    }


  });
});

fIApp.controller("appCtrl", function ($scope, $location, $ionicNavBarDelegate) {
  $scope.$watch(function () {
    return $location.path();
  },
    function (currentPath) {
      if (currentPath == '/quiz/answers') {
        $ionicNavBarDelegate.showBackButton(false);
      } else {
        $ionicNavBarDelegate.showBackButton(true);
      }
    })
});

