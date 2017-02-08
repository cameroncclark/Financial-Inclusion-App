// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'

var db = null;

var fIApp = angular.module('financialInclusionApp', ['ionic', 'ngCordova', 'rzModule', 'ngSanitize']);

fIApp.config(function($ionicConfigProvider){
  $ionicConfigProvider.views.swipeBackEnabled(false);
});

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

    // For when the user first launches the app
    var fillTables = function () {

        // User Data
        var query = "INSERT INTO userData (name, location, avatar) VALUES (?,?,?)";
        $cordovaSQLite.execute(db, query, ["Your Name Here", "Your Location Here", "img/startImage.png"]).then(function (result) {
            console.log("INSERT USER ID -> " + result.insertId);
        }, function (error) {
            console.error(error);
        });

        // Trophies
        var query = "INSERT INTO trophies (title, image, description, hint, acquired) VALUES (?,?,?,?,?)";
        $cordovaSQLite.execute(db, query, ["Updated Your Name","edit","You have successfully updated your name.","Try updating your name.",0]);
        $cordovaSQLite.execute(db, query, ["Updated Your Location","edit","You have successfully updated your location.","Try updating your location.",0]);
        $cordovaSQLite.execute(db, query, ["Updated Your Picture","edit","You have successfully updated your picture.","Try updating your picture.",0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 20 hints","edit","You have managed to flick through 20 hints.","Try flicking through more hints.",0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 50 hints","edit","You have managed to flick through 50 hints.","Try flicking through more hints.",0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 100 hints","edit","You have managed to flick through 100 hints.","Try flicking through more hints.",0]);
        $cordovaSQLite.execute(db, query, ["Performed a calculation on a calculator","edit","You performed a calculation on a calculator.","Try using a calculator.",0]);
        $cordovaSQLite.execute(db, query, ["Performed a calculation on all the calculators","edit","You performed a calculation on all the calculators.","Try using all calculators.",0]);
        $cordovaSQLite.execute(db, query, ["Visit an external website","edit","You visited an external website.","Try visiting a website.",0]);
        $cordovaSQLite.execute(db, query, ["Visit 5 external websites","edit","You visited 5 external websites.","Try visiting more websites.",0]);
        $cordovaSQLite.execute(db, query, ["Call a phone number","edit","You called a phone number.","Try visiting a website.",0]);
        $cordovaSQLite.execute(db, query, ["Achieve 25% completion","edit","You have completed 25% of a category.","Try completing more of a category.",0]);
        $cordovaSQLite.execute(db, query, ["Achieve 50% completion","edit","You have completed 50% of a category.","Try completing more of a category.",0]);
        $cordovaSQLite.execute(db, query, ["Achieve 75% completion","edit","You have completed 75% of a category.","Try completing more of a category.",0]);
        $cordovaSQLite.execute(db, query, ["Achieve 100% completion","edit","You have completed 100% of a category.","Try completing all of a category.",0]);
        $cordovaSQLite.execute(db, query, ["Attempt a quiz","edit","You have attempted a quiz.","Try attempting a quiz.",0]);
        $cordovaSQLite.execute(db, query, ["Attempted 5 quizzes","edit","You have attempted 5 quizzes.","Try attempting more quizzes.",0]);
        $cordovaSQLite.execute(db, query, ["Attempted all quizzes","edit","You have attempted all quizzes.","Try attempting all the quizzes.",0]);
        $cordovaSQLite.execute(db, query, ["Get 100% in a quiz","edit","You have achieved 100% in a quiz.","Try getting full marks in a quiz.",0]);
        $cordovaSQLite.execute(db, query, ["Get 100% in 3 quizzes","edit","You have achieved 100% in 3 quizzes.","Try getting full marks in multiple quizzes.",0]);
        $cordovaSQLite.execute(db, query, ["Unlock all the trophies","edit","You have unlocked every trophy, Congratulations!","Try getting more trophies.",0]);    
    }
    
    var setGlobalName = function () {
    // Check if data has been added correctly
         var searchQuery = "SELECT * FROM userData";
         var userName = {name:"",location:"",avatar:"img/startImage.png"}
         $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
             if (result.rows.length > 0) {
                userName.name = result.rows.item(0).name;
                userName.location = result.rows.item(0).location;
                userName.avatar = result.rows.item(0).avatar;
                console.log("USER DATA TABLE -> " + result.rows.item(0).name + " " + result.rows.item(0).location + " " + result.rows.item(0).avatar);
                 $rootScope.userName = userName;
                 console.log("Initial username: " + $rootScope.userName.avatar);
             } else {
                 console.log("NO ROWS EXIST");
             }
         }, function (error) {
             console.error(error);
         });

         var searchQuery = "SELECT * FROM trophies";
         var trophieCheck = {title:"", image:"", description:"", hint:"", acquired:""};
         $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
             if (result.rows.length > 0) {
                //console.log("TROPHIES TABLE -> " + result.rows.item(0).title + " " + result.rows.item(0).image + " " + result.rows.item(0).description + " " + result.rows.item(0).hint + " " + result.rows.item(0).acquired);
             } else {
                 console.log("NO ROWS EXIST");
             }
         }, function (error) {
             console.error(error);
         });
    }


    // Initialisation of databases for Android and iOS
    if (isAndroid || isIOS) {
      console.log("entered if");
      db = $cordovaSQLite.openDB({ name: 'my.db', location: 'default' });

      // Drop Tables
      $cordovaSQLite.execute(db, "DROP TABLE userData");
      $cordovaSQLite.execute(db, "DROP TABLE trophies");

      // Initialise all tables
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS userData (id INTEGER PRIMARY KEY, name TEXT, location TEXT, avatar TEXT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS trophies (id INTEGER PRIMARY KEY, title TEXT, image TEXT, description TEXT, hint TEXT, acquired TINYINT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY, name NVARCHAR(50), percentageComplete INTEGER)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS subcategories (url NVARCHAR(50) PRIMARY KEY, name NVARCHAR(50), percentageComplete INTEGER, categoryID INTEGER, FOREIGN KEY(categoryID) REFERENCES categories(id)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS progress (objective NVARCHAR(50) PRIMARY KEY, counter INTEGER, valueChanged TINYINT");
      
      //TO DO (CREATE A NEW TABLE FOR SETTINGS)
      //$cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS settings ()");

      var query = "SELECT id FROM userData";
      $cordovaSQLite.execute(db, query, []).then(function (result) {
        console.log("Number of rows in table " + result.rows.length);
        if (result.rows.length == 0) {
          fillTables();
          setGlobalName();
        }else{
          setGlobalName();
        }
      }, function (error) {
        console.log(error)
        console.log("Select function hasnt worked");
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

