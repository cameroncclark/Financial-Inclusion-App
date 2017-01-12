fIApp.controller('QuizCtrl', function ($scope, $http, $stateParams, $sce) {
    $scope.questionIndex = 0;
    $scope.userAnswers = {
        previousAnswers:[],
        answerTracker:[],
        currentAnswer:'-1'
    };

  $http.get('content/quizzes/' + $stateParams.quizFile)
    .then(function (response) {
      $scope.quizData = response.data;
      $scope.name = $scope.quizData.title;
      $scope.setQuestion();
    });

    $scope.setQuestion = function(){
        $scope.activeQuestion = $scope.quizData.questions[$scope.questionIndex];
        if ($scope.questionIndex < $scope.userAnswers.previousAnswers.length) {
            $scope.userAnswers.currentAnswer = $scope.userAnswers.previousAnswers[$scope.questionIndex];
        }
    }

    $scope.nextQuestion = function(){
        $scope.determineAnswer();
        $scope.questionIndex++;
        $scope.setQuestion();
    }

    $scope.previousQuestion = function(){
        $scope.questionIndex--;
        // $scope.userAnswers.currentAnswer = $scope.userAnswers.previousAnswers[$scope.questionIndex];
        $scope.setQuestion();
    }

    $scope.determineAnswer = function(){
        if ($scope.questionIndex < $scope.userAnswers.previousAnswers.length) {
            if($scope.userAnswers.currentAnswer == $scope.activeQuestion.answer){
                $scope.userAnswers.answerTracker[$scope.questionIndex] = true;
            }else{
                $scope.userAnswers.answerTracker[$scope.questionIndex] = false;
            }
            $scope.userAnswers.previousAnswers[$scope.questionIndex] = $scope.userAnswers.currentAnswer;
        } else {
            if($scope.userAnswers.currentAnswer == $scope.activeQuestion.answer){
                $scope.userAnswers.answerTracker.push(true);
            }else{
                $scope.userAnswers.answerTracker.push(false);
            }
            $scope.userAnswers.previousAnswers.push($scope.userAnswers.currentAnswer);
        }
        $scope.userAnswers.currentAnswer = -1;
        console.log($scope.userAnswers);
    }

});
