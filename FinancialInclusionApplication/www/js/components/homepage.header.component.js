fIApp.component("headerBar",{
    templateUrl:"templates/homepage.header.html",
    controller: function homePageHeaderCtrl($scope, $ionicModal, $ionicPopup, $timeout){

        $scope.userProfile = {name:"Stuart Cuthbertson", location: "Glasgow"};
        $scope.userProfileUpdate = {};
        
        $ionicModal.fromTemplateUrl('templates/homePageHeaderTrophy.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalTrophy = modal;
        });

        $ionicModal.fromTemplateUrl('templates/homePageHeaderProfile.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalProfile = modal;
        });

        $scope.closeTrophy = function() {
            $scope.modalTrophy.hide();
        };

        $scope.openTrophy = function() {
            $scope.modalTrophy.show();
        };

        $scope.closeProfile = function() {
            $scope.modalProfile.hide();
        };

        $scope.openProfile = function() {
            $scope.modalProfile.show();
        };

        //$scope.trophiesf = [{name: "calculator"},{name: "university"},{name: "calculator"},{name: "trophy"},{name: "calculator"} ]
        $scope.trophies = [
            [{name:"card",colour:{"color":"#000"}, info:"Where you got this from"},{name:"university",colour:{"color":"#000"}, info:"Where you got this from"},{name:"planet", colour:{"color":"#000"}, info:"Where you got this from"}],
            [{name:"heart",colour:{"color":"#000"}, info:"Where you got this from"},{name:"scissors",colour:{"color":"#000"}, info:"Where you got this from"},{name:"paper-airplane", colour:{"color":"#000"}, info:"Where you got this from"}],
            [{name:"bug",colour:{"color":"#000"}, info:"Where you got this from"},{name:"headphone",colour:{"color":"#000"}, info:"Where you got this from"},{name:"fireball", colour:{"color":"#000"}, info:"Where you got this from"}],
            [{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked", colour:{"color":"#a6a6a6"}, info:"Where you got this from"}],
            [{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked", colour:{"color":"#a6a6a6"}, info:"Where you got this from"}],
            [{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked", colour:{"color":"#a6a6a6"}, info:"Where you got this from"}],
            [{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked",colour:{"color":"#a6a6a6"}, info:"Where you got this from"},{name:"locked", colour:{"color":"#a6a6a6"}, info:"Where you got this from"}]
        ];

        $scope.showTrophyAlert = function(iconObject) {
            if (iconObject.name != "locked") {
                var alertPopup = $ionicPopup.alert({
                    title: 'Trophy',
                    template: iconObject.info
                });
            }
        };

        $scope.updateUserProfile = function() {       
            $scope.userProfile.name = $scope.userProfileUpdate.name;
            $scope.userProfile.location = $scope.userProfileUpdate.location;

            $scope.userProfileUpdate = {};


            $timeout(function() {
                $scope.closeProfile();
            }, 1000);
        };
    }
});