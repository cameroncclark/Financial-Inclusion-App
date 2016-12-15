fIApp.component("headerBar",{
    templateUrl:"templates/homepage.header.html",
    controller: function homePageHeaderCtrl($scope, $ionicModal, $ionicPopup){
        $ionicModal.fromTemplateUrl('templates/homePageHeaderTrophy.html', {
            scope: $scope
        }).then(function(modal) {
            $scope.modal = modal;
        });

        $scope.closeTrophy = function() {
            $scope.modal.hide();
        };

        $scope.openTrophy = function() {
            $scope.modal.show();
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
    }
});