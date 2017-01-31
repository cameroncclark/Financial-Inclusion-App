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
        };

        $scope.openTrophy = function () {
            $scope.modalTrophy.show();
            console.log("Trophy opened");
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

        //$scope.trophiesf = [{name: "calculator"},{name: "university"},{name: "calculator"},{name: "trophy"},{name: "calculator"} ]
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

        $scope.showTrophyAlert = function (iconObject) {
            if (iconObject.state == "unlocked") {
                var alertPopup = $ionicPopup.alert({
                    title: iconObject.name,
                    template: iconObject.info
                });
            }
            else if(iconObject.state == "locked"){
                var alertPopup = $ionicPopup.alert({
                    title: "Locked",
                    template: iconObject.info
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
        }
    }
});
