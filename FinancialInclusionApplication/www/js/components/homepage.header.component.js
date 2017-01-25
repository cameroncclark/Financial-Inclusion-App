fIApp.component("headerBar",{
    templateUrl:"templates/homepage.header.html",
    controller: function homePageHeaderCtrl($scope, $ionicModal, $ionicPopup, $timeout,dbAccessor){
        $scope.updateProfile = true;

        $scope.userProfile = {name: "Stuart", location: "location"};
        $scope.userProfileUpdate = {};
        $scope.image = 'img/stuart.png';
                
        $scope.imageUpdate = {};
        $scope.stuartImage = 'img/stuart.png';
        $scope.liamImage = 'img/liam.png';
        $scope.angusImage = 'img/angus.png';
        $scope.cammyImage = 'img/cammy.png';

        $scope.initialise = function(){
            console.log("Initialised.")
            $scope.userProfile = dbAccessor.selectUserDetails();
        }
        
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
                
        $ionicModal.fromTemplateUrl('templates/homepage.header.changeImage.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modalAvatar = modal;
        });

        $scope.closeTrophy = function() {
            $scope.modalTrophy.hide();
        };

        $scope.openTrophy = function() {
            $scope.modalTrophy.show();
        };

        $scope.closeProfile = function() {
            $scope.modalProfile.hide();
            $scope.updateProfile = true;
        };

        $scope.openProfile = function() {
            $scope.modalProfile.show();
        };
                
        $scope.closeChangePhoto = function() {
            $scope.modalAvatar.hide();
        };
                
        $scope.openChangePhoto = function() {
            $scope.modalAvatar.show();
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
                
                
                
        $scope.updateImage = function(imageChange){
            $scope.image = imageChange;
        }

        $scope.updateUserProfile = function() {
            if($scope.userProfileUpdate.name){       
                $scope.userProfile.name = $scope.userProfileUpdate.name;
                dbAccessor.updateName($scope.userProfile.name, 'L');
                dbAccessor.selectName('L');
                
            }
            if($scope.userProfileUpdate.location){
            $scope.userProfile.location = $scope.userProfileUpdate.location;
            }

            $scope.userProfileUpdate = {};
            $scope.closeProfile();
           
        };

        $scope.switchMode = function(){
            $scope.updateProfile = !$scope.updateProfile;
        }
    }
});
