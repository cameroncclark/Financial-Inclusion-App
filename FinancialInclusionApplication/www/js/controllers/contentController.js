fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
  $scope.name = $stateParams.pageTitle;
  $scope.content = "";
  $scope.sections = [];

  var tagMapOpen = ["<video>","<image>","<title>","<subtitle>"];
  var tagMapClose = ["</video>","</image>","</title>","</subtitle>"];

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
      var tagsExist = true;
      while (tagsExist) {
        tagsExist = false;
        for(var j=0; j<tagMapOpen.length;j++){
          if($scope.sections[i].content.indexOf(tagMapOpen[j]) != -1){
            tagsExist = true;
            var startTagIndex = $scope.sections[i].content.indexOf(tagMapOpen[j]);
            var endTagIndex = $scope.sections[i].content.indexOf(tagMapClose[j])+tagMapOpen[j].length+1;
            
            var newTag = switchOnTag(tagMapOpen[j],$scope.sections[i].content.substring(startTagIndex,endTagIndex));
            $scope.sections[i].content = $scope.sections[i].content.replace($scope.sections[i].content.substring(startTagIndex,endTagIndex),newTag);
          }
        }
      }
      $scope.sections[i].content = $sce.trustAsHtml($scope.sections[i].content);
    }
  }

  var switchOnTag = function(tag, content){
    switch (tag) {
      case "<video>":
        return parseTagVideo(content);
        break;
      case "<image>":
        return parseTagImage(content);
        break;
      case "<title>":
        return parseTagTitle(content);
        break;
      case "<subtitle>":
        return parseTagSubtitle(content);
        break;
    
      default:
        break;
    }
  }

  var parseTagVideo = function(content){
    var parseVideoURLStart = content.indexOf("=")+1;
    var parseVideoURLEnd = content.indexOf("</video>");
    var videoURL = content.substring(parseVideoURLStart,parseVideoURLEnd);
    
    //return "<iframe width=\"560\" height=\"315\" ng-src=\"https://www.youtube.com/embed/" + videoURL + "?rel=0&autohide=1&showinfo=0\" frameborder=\"0\" allowfullscreen></iframe>";
    return "<div class=\"video-container\"><iframe width=\"560\" height=\"315\" ng-src=\"https://www.youtube.com/embed/" + videoURL + "?rel=0&autohide=1&showinfo=0\" frameborder=\"0\" allowfullscreen></iframe></div>";
  }

  var parseTagImage = function(content){
    var parseImageURLStart = content.indexOf("<image>")+7;
    var parseImageURLEnd = content.indexOf("</image>");
    var imageURL = content.substring(parseImageURLStart,parseImageURLEnd);
    
    return "<div class=\"content-Image\"><img src=\"content/images/" + imageURL + "\" alt=\""+ imageURL + "\"></div>";
  }

  var parseTagTitle = function(content){
    var parseTitleStart = content.indexOf("<title>")+7;
    var parseTitleEnd = content.indexOf("</title>");
    var title = content.substring(parseTitleStart,parseTitleEnd);
    
    return "<h2 class=\"content-Title\">" + title + "</h2>";
  }

  var parseTagSubtitle = function(content){
    var parseTitleStart = content.indexOf("<subtitle>")+10;
    var parseTitleEnd = content.indexOf("</subtitle>");
    var title = content.substring(parseTitleStart,parseTitleEnd);
    
    return "<h3 class=\"content-Subtitle\">" + title + "</h3>";
  }
});
