// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'

var db = null;

var fIApp = angular.module('financialInclusionApp', ['ionic', 'ngCordova', 'rzModule']);

fIApp.run(function ($ionicPlatform, $http, $rootScope, $cordovaSQLite) {
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
    db = $cordovaSQLite.openDB("my.db");
            $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS people (id integer primary key, firstname text, lastname text)");
  });
});

fIApp.controller("ExampleController", function($scope, $cordovaSQLite, $cordovaCamera, $cordovaFile) {

  $scope.images = [];
 
    $scope.addImage = function() {
        var options = {
		destinationType : Camera.DestinationType.FILE_URI,
		sourceType : Camera.PictureSourceType.CAMERA, // Camera.PictureSourceType.PHOTOLIBRARY
		allowEdit : false,
		encodingType: Camera.EncodingType.JPEG,
		popoverOptions: CameraPopoverOptions,
	};
	
	// 3
	$cordovaCamera.getPicture(options).then(function(imageData) {
 
		// 4
		onImageSuccess(imageData);
 
		function onImageSuccess(fileURI) {
			createFileEntry(fileURI);
		}
 
		function createFileEntry(fileURI) {
			window.resolveLocalFileSystemURL(fileURI, copyFile, fail);
		}
 
		// 5
		function copyFile(fileEntry) {
			var name = fileEntry.fullPath.substr(fileEntry.fullPath.lastIndexOf('/') + 1);
			var newName = makeid() + name;
 
			window.resolveLocalFileSystemURL(cordova.file.dataDirectory, function(fileSystem2) {
				fileEntry.copyTo(
					fileSystem2,
					newName,
					onCopySuccess,
					fail
				);
			},
			fail);
		}
		
		// 6
		function onCopySuccess(entry) {
			$scope.$apply(function () {
				$scope.images.push(entry.nativeURL);
			});
		}
 
		function fail(error) {
			console.log("fail: " + error.code);
		}
 
		function makeid() {
			var text = "";
			var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
			for (var i=0; i < 5; i++) {
				text += possible.charAt(Math.floor(Math.random() * possible.length));
			}
			return text;
		}
 
	}, function(err) {
		console.log(err);
	});
    }
 
    $scope.urlForImage = function(imageName) {
        console.log("get correct path for image");
    }
 
    $scope.insert = function(firstname, lastname) {
        var query = "INSERT INTO people (firstname, lastname) VALUES (?,?)";
        $cordovaSQLite.execute(db, query, [firstname, lastname]).then(function(res) {
            console.log("INSERT ID -> " + res.insertId);
        }, function (err) {
            console.error(err);
        });
    }
 
    $scope.select = function(lastname) {
        var query = "SELECT firstname, lastname FROM people WHERE lastname = ?";
        $cordovaSQLite.execute(db, query, [lastname]).then(function(res) {
            if(res.rows.length > 0) {
                console.log("SELECTED -> " + res.rows.item(0).firstname + " " + res.rows.item(0).lastname);
            } else {
                console.log("No results found");
            }
        }, function (err) {
            console.error(err);
        });
    }
 
}); 