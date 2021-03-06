fIApp.controller('AnswersCtrl', function ($scope, $http, $stateParams, $ionicPopup, $state, $location, dbAccessor) {
    $scope.data = $stateParams.quizData.data;
    $scope.name = "Quiz Results";

    $http.get('content/quizzes/' + $stateParams.quizData.path)
        .then(function (response) {
            $scope.quizData = response.data;
            $scope.quizPath = $stateParams.quizData.path;
            $scope.name = $scope.quizData.title;
            $scope.correctDisplay = Math.ceil(countCorrectAnswers() / $scope.data.answerTracker.length * 100);
            $scope.correctPercentage = countCorrectAnswers() / $scope.data.answerTracker.length;
            updateProgress();
        });

    var countCorrectAnswers = function () {
        var correctAnswers = 0;
        for (var i = 0; i < $scope.data.answerTracker.length; i++) {
            if ($scope.data.answerTracker[i]) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    function updateProgress() {
        dbAccessor.updateQuiz($scope.correctDisplay);

        /**
         * This Block Of Code Is For Updating Categories
         */

        var currentSubCat = dbAccessor.getSubCatName($stateParams.quizData.path);
        currentSubCat.then(function (subCatOutput) {      
            var updateSubCategoryPromise = dbAccessor.updateSubCategoryProgress(subCatOutput, $scope.correctDisplay);
            updateSubCategoryPromise.then(function (output) {
                var updateCategoryPromise = dbAccessor.updateCategoryProgress();
                updateCategoryPromise.then(function (output) {
                    dbAccessor.checkOverallProgress();
                });
            });
        })
    }

});