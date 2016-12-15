fIApp.service('randomHint', function(){
            var num = Math.floor(Math.random()*10);
            this.generate = function(){
                console.log(num);
                return num;
            };
        });

fIApp.component("tipComponent",{
    templateUrl:"templates/tips.html",
    controller: function testController($scope, randomHint){

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
            },
            {
                "header":"Tip header 4",
                "tip":"This is tip 4."
            },
            {
                "header":"Tip header 5",
                "tip":"This is tip 5."
            },
            {
                "header":"Tip header 6",
                "tip":"This is tip 6."
            },
            {
                "header":"Tip header 7",
                "tip":"This is tip 7."
            },
            {
                "header":"Tip header 8",
                "tip":"This is tip 8."
            },
            {
                "header":"Tip header 9",
                "tip":"This is tip 9."
            },
            {
                "header":"Tip header 10",
                "tip":"This is tip 10."
            },
        ];

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