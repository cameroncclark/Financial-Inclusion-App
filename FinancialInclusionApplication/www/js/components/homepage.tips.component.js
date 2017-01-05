fIApp.service('randomHint', function(){
            var num = Math.floor(Math.random()*10);
            this.generate = function(){
                return num;
            };
        });

fIApp.component("tipComponent",{
    templateUrl:"templates/tips.html",
    controller: function testController($scope, randomHint, $http){

        //Get the tips from an external JSON file
        $http.get('content/tips.json')
        .then(function (tips) {
            $scope.tips = tips.data;
        });

        $scope.index = randomHint.generate();

        $scope.swipeLeft = function(){
            if ($scope.index == $scope.tips.length -1) {
                $scope.index = 0;
            } else {
                $scope.index += 1;
            }
        }

        $scope.swipeRight = function(){
            if ($scope.index == 0) {
                $scope.index = $scope.tips.length -1;
            } else {
                $scope.index -= 1;
            }
        }
    }
});