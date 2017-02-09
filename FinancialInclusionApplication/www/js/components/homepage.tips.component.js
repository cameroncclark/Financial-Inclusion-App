fIApp.service('randomHint', function(){
            this.generate = function(range){
                return Math.floor(Math.random()*range);
            };
        });

fIApp.component("tipComponent",{
    templateUrl:"templates/tips.html",
    controller: function testController($scope, randomHint, $http){
        $scope.swipeMe = true;
        $scope.hideTip = false;

        //Get the tips from an external JSON file
        $http.get('content/tips.json')
        .then(function (tips) {
            $scope.tips = tips.data;
            $scope.index = randomHint.generate($scope.tips.length);
        });

        $scope.swipeLeft = function(){
            if ($scope.index == $scope.tips.length -1) {
                $scope.index = 0;
                $scope.swipeMe = false;
                $scope.hideTip=true;
            } else {
                $scope.index += 1;
                $scope.swipeMe = false;
                $scope.hideTip=true;
            }
        }

        $scope.swipeRight = function(){
            if ($scope.index == 0) {
                $scope.index = $scope.tips.length -1;
                $scope.swipeMe = false;
                $scope.hideTip=true;
            } else {
                $scope.index -= 1;
                $scope.swipeMe = false;
                $scope.hideTip=true;
            }
        }
    }
});