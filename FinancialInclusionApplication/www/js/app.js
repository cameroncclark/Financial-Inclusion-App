// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'

var db = null;

var fIApp = angular.module('financialInclusionApp', ['ionic', 'ngCordova', 'rzModule', 'ngSanitize']);

fIApp.config(function ($ionicConfigProvider) {
  $ionicConfigProvider.views.swipeBackEnabled(false);
});

fIApp.run(function ($ionicPlatform, $http, $rootScope, $cordovaSQLite, dbAccessor, $q) {

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
            }
          }
        });
    }

    /**
     * For when the user first launches the app
     */
    var fillTables = function () {

      /**
       * User Data Table
       */
      var query = "INSERT INTO userData (name, location, avatar) VALUES (?,?,?)";
      $cordovaSQLite.execute(db, query, ["Your Name Here", "Your Location Here", "img/startImage.png"]).then(function (result) {
        console.log("INSERT USER ID -> " + result.insertId);
      }, function (error) {
        console.error(error);
      });

      /**
       * Trophies Table
       */
      var query = "INSERT INTO trophies (title, image, description, hint, acquired) VALUES (?,?,?,?,?)";
      $cordovaSQLite.execute(db, query, ["Updated Your Name", "person", "You have successfully updated your name.", "Try updating your name.", 0]);
      $cordovaSQLite.execute(db, query, ["Updated Your Location", "location", "You have successfully updated your location.", "Try updating your location.", 0]);
      $cordovaSQLite.execute(db, query, ["Updated Your Picture", "person-stalker", "You have successfully updated your picture.", "Try updating your picture.", 0]);
      $cordovaSQLite.execute(db, query, ["Flicked through 20 hints", "information", "You have managed to flick through 20 hints.", "Try flicking through more hints.", 1]);
      $cordovaSQLite.execute(db, query, ["Flicked through 50 hints", "information", "You have managed to flick through 50 hints.", "Try flicking through more hints.", 1]);
      $cordovaSQLite.execute(db, query, ["Flicked through 100 hints", "information", "You have managed to flick through 100 hints.", "Try flicking through more hints.", 1]);
      $cordovaSQLite.execute(db, query, ["Performed a calculation on a calculator", "calculator", "You performed a calculation on a calculator.", "Try using a calculator.", 0]);
      $cordovaSQLite.execute(db, query, ["Performed a calculation on all the calculators", "calculator", "You performed a calculation on all the calculators.", "Try using all calculators.", 0]);
      $cordovaSQLite.execute(db, query, ["Visit an external website", "edit", "You visited an external website.", "Try visiting a website.", 1]);
      $cordovaSQLite.execute(db, query, ["Visit 5 external websites", "edit", "You visited 5 external websites.", "Try visiting more websites.", 1]);
      $cordovaSQLite.execute(db, query, ["Call a phone number", "edit", "You called a phone number.", "Try visiting a website.", 1]);
      $cordovaSQLite.execute(db, query, ["Achieve 25% completion", "trophy", "You have completed 25% of a category.", "Try completing more of a category.", 0]);
      $cordovaSQLite.execute(db, query, ["Achieve 50% completion", "trophy", "You have completed 50% of a category.", "Try completing more of a category.", 0]);
      $cordovaSQLite.execute(db, query, ["Achieve 75% completion", "trophy", "You have completed 75% of a category.", "Try completing more of a category.", 0]);
      $cordovaSQLite.execute(db, query, ["Achieve 100% completion", "trophy", "You have completed 100% of a category.", "Try completing all of a category.", 0]);
      $cordovaSQLite.execute(db, query, ["Attempt a quiz", "checkmark-circled", "You have attempted a quiz.", "Try attempting a quiz.", 0]);
      $cordovaSQLite.execute(db, query, ["Attempted 5 quizzes", "checkmark-circled", "You have attempted 5 quizzes.", "Try attempting more quizzes.", 0]);
      $cordovaSQLite.execute(db, query, ["Attempted all quizzes", "checkmark-circled", "You have attempted all quizzes.", "Try attempting all the quizzes.", 0]);
      $cordovaSQLite.execute(db, query, ["Get 100% in a quiz", "wifi", "You have achieved 100% in a quiz.", "Try getting full marks in a quiz.", 0]);
      $cordovaSQLite.execute(db, query, ["Get 100% in 3 quizzes", "wifi", "You have achieved 100% in 3 quizzes.", "Try getting full marks in multiple quizzes.", 0]);
      $cordovaSQLite.execute(db, query, ["Unlock all the trophies", "trophy", "You have unlocked every trophy, Congratulations!", "Try getting more trophies.", 0]);

      /**
       * Categorys Table
       */
      var catQuery = "INSERT INTO categories (name, percentageComplete) VALUES (?,?)";
      $http.get('content/categories.json')
        .then(function (categories) {
          for (var i = 0; i < categories.data.length; i++) {
            $cordovaSQLite.execute(db, catQuery, [categories.data[i].name, 0.5]);
          }
        });

      /**
       * Subcategories Table
       */
      var subCatQuery = "INSERT INTO subcategories (name, quizURL, percentageComplete, categoryID) VALUES (?,?,?,?)";
      $http.get('content/topics.json')
        .then(function (subcategories) {
          for (var i = 0; i < subcategories.data.length; i++) {
            var SCname;
            var SCcatID;
            var SCquiz = [];
            var len = subcategories.data.length;

            for (var k = 0; k < subcategories.data.length; k++) {
              SCquiz[k] = subcategories.data[k];
            }

            $http.get("content/topics/" + subcategories.data[i])
              .then(function (subcategory) {
                var ref = subcategory.data.reference;
                $http.get('content/categories.json')
                  .then(function (category) {
                    for (var j = 0; j < category.data.length; j++) {
                      if (category.data[j].ID === ref) {
                        var getNameQuery = "SELECT id FROM categories WHERE name LIKE '" + category.data[j].name + "'";

                        $cordovaSQLite.execute(db, getNameQuery, []).then(function (result) {
                          SCcatID = result.rows.item(0).id;
                          SCname = subcategory.data.title;


                          

                          for (var k = 0; k < len; k++) {
                            console.log("Here is the quiz URL: " + SCquiz[j]);
                          }

                          $cordovaSQLite.execute(db, subCatQuery, [SCname, SCquiz, 100, SCcatID]).then(function (result) {
                            console.log("INSERT SUB CAT ID -> " + result.insertId);
                          }, function (error) {
                            console.error(JSON.stringify(error));
                          });
                        }, function (error) {
                          console.error(error);
                        });
                      }
                    }
                  });
              });
          }
        });

      /**
       * Progress Table
       */
      var progressQuery = "INSERT INTO progress (objective, counter, valueChanged) VALUES (?,?,?)";
      $cordovaSQLite.execute(db, progressQuery, ["Tip Counter", 18, null]);
      $cordovaSQLite.execute(db, progressQuery, ["Perform calc 1", null, "false"]);
      $cordovaSQLite.execute(db, progressQuery, ["Perform calc 2", null, "false"]);
      $cordovaSQLite.execute(db, progressQuery, ["Perform calc 3", null, "false"]);
      $cordovaSQLite.execute(db, progressQuery, ["Visit a webpage", null, "false"]);
      $cordovaSQLite.execute(db, progressQuery, ["Visit 5 websites", 0, null]);
      $cordovaSQLite.execute(db, progressQuery, ["Call number", null, "false"]);
      $cordovaSQLite.execute(db, progressQuery, ["Quiz Counter", 0, null]);
      $cordovaSQLite.execute(db, progressQuery, ["100% Quiz Counter", 2, null]);
    }

    /**
     * Setting the user details
     */
    var setGlobalName = function () {
      // Check if data has been added correctly
      var searchQuery = "SELECT * FROM userData";
      var userName = { name: "", location: "", avatar: "img/startImage.png" }
      $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
        if (result.rows.length > 0) {
          userName.name = result.rows.item(0).name;
          userName.location = result.rows.item(0).location;
          userName.avatar = result.rows.item(0).avatar;
          $rootScope.userName = userName;
        } else {
          console.error("NO ROWS EXIST IN userData");
        }
      }, function (error) {
        console.error(error);
      });
    }


    // Initialisation of databases for Android and iOS
    if (isAndroid || isIOS) {
      console.log("entered if");
      db = $cordovaSQLite.openDB({ name: 'my.db', location: 'default' });//TODO: Name Needs Changed

      // Drop Tables
      $cordovaSQLite.execute(db, "DROP TABLE userData");
      $cordovaSQLite.execute(db, "DROP TABLE trophies");
      $cordovaSQLite.execute(db, "DROP TABLE categories");
      $cordovaSQLite.execute(db, "DROP TABLE subcategories");
      $cordovaSQLite.execute(db, "DROP TABLE progress");

      // Initialise all tables
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS userData (id INTEGER PRIMARY KEY, name TEXT, location TEXT, avatar TEXT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS trophies (id INTEGER PRIMARY KEY, title TEXT, image TEXT, description TEXT, hint TEXT, acquired TINYINT)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY, name NVARCHAR(50), percentageComplete INTEGER)");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS subcategories (id INTEGER PRIMARY KEY, name NVARCHAR(50), quizURL NVARCHAR(50), percentageComplete INTEGER, categoryID INTEGER, FOREIGN KEY(categoryID) REFERENCES categories(id))");
      $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS progress (objective NVARCHAR(50) PRIMARY KEY, counter INTEGER, valueChanged TINYINT)");

      //TO DO (CREATE A NEW TABLE FOR SETTINGS)
      //$cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS settings ()");

      var query = "SELECT id FROM userData";
      $cordovaSQLite.execute(db, query, []).then(function (result) {
        console.log("Number of rows in table " + result.rows.length);
        if (result.rows.length == 0) {
          fillTables();
          setGlobalName();
        } else {
          setGlobalName();
        }
      }, function (error) {
        console.error(error)
        console.error("Select function hasnt worked");
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

