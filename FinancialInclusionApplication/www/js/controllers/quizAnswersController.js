fIApp.controller('AnswersCtrl', function ($scope, $http, $stateParams) {
    $scope.data = $stateParams.quizData.data;
    $scope.name = "Quiz Results";

    $http.get('content/quizzes/' + $stateParams.quizData.path)
    .then(function (response) {
        console.log(response.data);
      $scope.quizData = response.data;
      $scope.quizPath = $stateParams.quizFile;
      $scope.name = $scope.quizData.title;
      //Number of correct
      console.log("Correct: " +countCorrectAnswers() +"/"+$scope.data.answerTracker.length);
    });

    var countCorrectAnswers = function(){
        var correctAnswers = 0;
        for(var i =0; i<$scope.data.answerTracker.length; i++){
            if($scope.data.answerTracker[i]){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }


    //Notes on what to do:
    // Firstly I want to print out the score up the top
    // Underneath I want something like a list that says Question 1
    //     These would be colour coded
    //     Click to expand? Says what the question was, your answer and the reason this answer is right/wrong?
    // ?Correct answer was?

    // Down the bottom Retake quiz -blurred if 100%-, back to categories button. 

});
