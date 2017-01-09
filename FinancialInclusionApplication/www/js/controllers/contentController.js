fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
  $scope.name = $stateParams.pageTitle;
  $scope.content = "";
  $scope.sections = [];
  var ourTags = ["<section>", "</section>", "<title>", "</title>"];

  $http.get('content/topics/' + $stateParams.contentURL)
    .then(function (response) {
      $scope.topic = response.data;
      $scope.content = $scope.parse($scope.topic.content);
    });

  $scope.parse = function (content) {
    $scope.sections = [];
    var openCounter = null;
    var closeCounter = null;

    try {
      openCounter = content.match(new RegExp("<section>", "g") || []).length;
      closeCounter = content.match(new RegExp("</section>", "g") || []).length;
    } catch (error) {
      content = "Invalid file representation."
    }


    if (openCounter == null) {
      openCounter = 0;
    }

    if (closeCounter == null) {
      closeCounter = 0;
    }

    if (openCounter === closeCounter && openCounter > 0 && closeCounter > 0) {
      for (var i = 0; i < openCounter; i++) {
        var sectionStartIndex = content.indexOf('<section>') + 9;
        var sectionEndIndex = content.indexOf('</section>', sectionStartIndex);
        var sectionContent = content.substring(sectionStartIndex, sectionEndIndex);
        $scope.sections.push(sectionContent);
        content = content.substring(sectionEndIndex + 10);
      }

    } else {
      content = "Invalid file representation."
    }
    return content;
  }


  $scope.options = {
    loop: false,
    effect: 'horizontal',
    speed: 200,
    pagination: true,
  }

  /**
   * This controls the slider, this is the init function
   */
  $scope.$on("$ionicSlides.sliderInitialized", function (event, data) {
    $scope.slider = data.slider;
  });

  /**
   * This sets the active page and the 
   */
  $scope.$on("$ionicSlides.slideChangeEnd", function (event, data) {
    $scope.activeIndex = data.slider.activeIndex;
    $scope.previousIndex = data.slider.previousIndex;
  });

});
