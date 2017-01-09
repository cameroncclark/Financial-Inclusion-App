fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
  $scope.name = $stateParams.pageTitle;
  $scope.content = "";
  $scope.sections = [];

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

  $http.get('content/topics/' + $stateParams.contentURL)
    .then(function (response) {
      $scope.topic = response.data;
      $scope.parseIntoSections($scope.topic.content);
    });

/**
 * This code parses all content <section> tags into an Array.
 */
  $scope.parseIntoSections = function (content) {
    $scope.sections = [];
    var openCounter = null;
    var closeCounter = null;

    try {
      openCounter = content.match(new RegExp("<section>", "g") || []).length;
      closeCounter = content.match(new RegExp("</section>", "g") || []).length;
    } catch (error) {
      $scope.sections[0] =  "Invalid file representation.";
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
        $scope.sections.push(parseOutTitle(sectionContent));
        content = content.substring(sectionEndIndex + 10);
      }

      //Parse the content within each of the sections 
      parseEachSection();
    } else {
      $scope.sections[0] =  "Invalid file representation.";
    }

  }

  var parseOutTitle = function(sectionContent){
    var contentObject = {};
    var sectionTitleStartIndex = sectionContent.indexOf('<section-title>') + 15;
    var sectionTitleEndIndex = sectionContent.indexOf('</section-title>', sectionTitleStartIndex);
    contentObject.title = sectionContent.substring(sectionTitleStartIndex, sectionTitleEndIndex);
    contentObject.content = sectionContent.substring(sectionTitleEndIndex + 16);
    return contentObject;
  }

  var parseEachSection = function(){
    //For each section
    for(var i = 0; i<$scope.sections.length; i++ ){
      console.log($scope.sections[i]);
    }
  }

});
