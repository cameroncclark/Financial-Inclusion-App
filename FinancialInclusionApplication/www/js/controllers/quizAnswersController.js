fIApp.controller('AnswersCtrl', function ($scope, $http, $stateParams, $ionicPopup, $state, $location) {
    $scope.data = $stateParams.quizData.data;
    $scope.name = "Quiz Results";

    $http.get('content/quizzes/' + $stateParams.quizData.path)
    .then(function (response) {
      $scope.quizData = response.data;
      $scope.quizPath = $stateParams.quizData.path;
      $scope.name = $scope.quizData.title;
      $scope.correctDisplay = countCorrectAnswers() +"/"+$scope.data.answerTracker.length;
      $scope.correctPercentage = countCorrectAnswers()/$scope.data.answerTracker.length;
      console.log($location.path());
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

    $scope.showQuestionAlert = function(question, index) {
        var alertPopup = $ionicPopup.alert({
            title: question.questionText,
            template: question.reason[index]
        });
    };
});
