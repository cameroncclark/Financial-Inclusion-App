fIApp.component("tipComponent",{
    templateUrl:"templates/tips.html",
    controller: function testController($scope){
        $scope.tips = [
            {
                "header":"Tip header 1",
                "tip":"This is tip 1."
            },
            {
                "header":"Tip header 2",
                "tip":"This is tip 2."
            },
            {
                "header":"Tip header 3",
                "tip":"This is tip 3."
            }
        ];

        $scope.index = 0;

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