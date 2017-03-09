fIApp.component("headerBar", {
    templateUrl: "templates/homepage.header.html",
    controller: function homePageHeaderCtrl($scope, $ionicModal, $ionicPopup, $timeout, $rootScope, dbAccessor) {
        $scope.updateProfile = true;
        $scope.userProfileUpdate = {};

        $scope.imageUpdate = {};
        $scope.stuartImage = 'img/stuart.png';
        $scope.liamImage = 'img/liam.png';
        $scope.angusImage = 'img/angus.png';
        $scope.cammyImage = 'img/cammy.png';

        $ionicModal.fromTemplateUrl('templates/homePageHeaderTrophy.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalTrophy = modal;
        });

        $ionicModal.fromTemplateUrl('templates/homePageHeaderProfile.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalProfile = modal;
        });

        $ionicModal.fromTemplateUrl('templates/homepage.header.changeImage.html', {
            scope: $scope
        }).then(function (modal) {
            $scope.modalAvatar = modal;
        });

        $scope.closeTrophy = function () {
            $scope.modalTrophy.hide();
            $scope.newTrophies = null;
        };

        $scope.openTrophy = function () {
            $scope.modalTrophy.show();
            if (!$scope.newTrophies) {
                var promise = dbAccessor.loadTrophyTable(); //PROMISE OBJECT WHICH WILL RETURN DATA WHEN READY
                promise.then(function (response) {
                    //WHEN THE RESPONSE IS READY, SET IT TO SCOPE
                    $scope.newTrophies = response;
                });
            }
        };

        $scope.closeProfile = function () {
            $scope.modalProfile.hide();
            $scope.updateProfile = true;
        };

        $scope.openProfile = function () {
            $scope.modalProfile.show();
        };

        $scope.closeChangePhoto = function () {
            $scope.modalAvatar.hide();
        };

        $scope.openChangePhoto = function () {
            $scope.modalAvatar.show();
        };

        $scope.trophies = [
            // Row 1
            [{ name: "Updated Your Name", icon: "edit", state: "unlocked", colour: { "color": "#000" }, info: "You have successfully updated your name." },
            { name: "Updated Your Location", icon: "compass", state: "unlocked", colour: { "color": "#000" }, info: "You have successfully updated your location." },
            { name: "Updated Your Avatar", icon: "person", state: "unlocked", colour: { "color": "#000" }, info: "You have successfully chosen an avatar." }],

            // Row 2
            [{ name: "Flicked Through 20 Hints", icon: "arrow-swap", state: "unlocked", colour: { "color": "#000" }, info: "You have swiped through 20 hints." },
            { name: "Flicked Through 50 Hints", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try swiping through more helpful hints." },
            { name: "Flicked Through 100 Hints", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try swiping through more helpful hints." }],

            // Row 3
            [{ name: "Performed a Calculation", icon: "calculator", state: "unlocked", colour: { "color": "#000" }, info: "You perfomed a calcualtion on one of the calculators." },
            { name: "Used All Calculators", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try using all of the calculators." },
            { name: "Visited A Website", icon: "wifi", state: "unlocked", colour: { "color": "#000" }, info: "You visited an external website." }],

            // Row 4
            [{ name: "Visited 5 Websites", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try visiting more websites." },
            { name: "Called A Number", icon: "iphone", state: "unlocked", colour: { "color": "#000" }, info: "You called a help line." },
            { name: "25% Completion", icon: "checkmark", state: "unlocked", colour: { "color": "#000" }, info: "You have completed 25% of the app." }],

            // Row 5
            [{ name: "50% Completion", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try making your way through more of the content." },
            { name: "75% Completion", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try making your way through more of the content." },
            { name: "100% Completion", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try making your way through more of the content." }],

            // Row 6
            [{ name: "1 Quiz Complete", icon: "clipboard", state: "unlocked", colour: { "color": "#000" }, info: "You have completed a quiz." },
            { name: "5 Quizzes Complete", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try completing more quizzes." },
            { name: "All Quizzes Complete", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try completing more quizzes." }],

            // Row 7
            [{ name: "100% In A Quiz", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try getting full marks in a quiz." },
            { name: "100% in 3 Quizzes", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "Try getting full marks in several quizzes." },
            { name: "The Final Trophie", icon: "locked", state: "locked", colour: { "color": "#a6a6a6" }, info: "This trophie requires every other trophie to be unlocked" }]
        ];

        $scope.trophiesHandover = [
            // Row 1
            [{ title: "Updated Your Name", image: "edit", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Updated Your Location", image: "compass", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Updated Your Avatar", image: "person", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 2
            [{ title: "Flicked Through 20 Hints", image: "arrow-swap", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Flicked Through 50 Hints", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Flicked Through 100 Hints", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 3
            [{ title: "Performed a Calculation", image: "calculator", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Used All Calculators", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Visited A Website", image: "wifi", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 4
            [{ title: "Visited 5 Websites", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "Called A Number", image: "iphone", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "25% Completion", image: "checkmark", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 5
            [{ title: "50% Completion", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "75% Completion", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "100% Completion", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 6
            [{ title: "1 Quiz Complete", image: "clipboard", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "5 Quizzes Complete", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "All Quizzes Complete", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" }],

            // Row 7
            [{ title: "100% In A Quiz", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "100% in 3 Quizzes", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" },
            { title: "The Final Trophie", image: "locked", description: "A Description", hint: "A Hint", acquired: "0" }]
        ];

        $scope.showTrophyAlert = function (iconObject) {
            if (iconObject.acquired == "1") {
                var alertPopup = $ionicPopup.alert({
                    title: iconObject.title,
                    template: iconObject.description
                });
            }
            else if (iconObject.acquired == "0") {
                var alertPopup = $ionicPopup.alert({
                    title: "Locked",
                    template: iconObject.hint
                });
            }
        };

        $scope.updateImage = function (imageChange) {
            var previousValue = "";
            previousValue = $rootScope.userName.avatar;
            $rootScope.userName.avatar = imageChange;
            dbAccessor.updateAvatar(previousValue, imageChange);
        }

        $scope.updateUserProfile = function () {
            var parameter = "";
            var previousValue = "";
            if ($scope.userProfileUpdate.name) {
                previousValue = $rootScope.userName.name;
                $rootScope.userName.name = $scope.userProfileUpdate.name;
                parameter = $scope.userProfileUpdate.name;
                dbAccessor.updateName(previousValue, parameter);

            }
            if ($scope.userProfileUpdate.location) {
                previousValue = $rootScope.userName.location;
                $rootScope.userName.location = $scope.userProfileUpdate.location;
                parameter = $scope.userProfileUpdate.location;
                dbAccessor.updateLocation(previousValue, parameter);
            }

            $scope.userProfileUpdate = {};
            $scope.closeProfile();

        };

        $scope.switchMode = function () {
            $scope.updateProfile = !$scope.updateProfile;
        };

        $scope.clearHistory = function () {
            dbAccessor.clearAllProgress();
            var alertPopup = $ionicPopup.alert({
                title: "Clear History",
                template: "All History Deleted!"
            });
            $rootScope.userName.avatar = "img/startImage.png";
            $rootScope.userName.name = "Your Name Here";
            $rootScope.userName.location = "Your Location Here";
        };
    }
});
