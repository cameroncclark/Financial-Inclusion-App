fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
  $scope.name = $stateParams.pageTitle;
  $scope.content = "";
  $scope.sections = [];

  /** 
   * The arrays below hold the cusomised tags the application can parse. 
  */
  var tagMapOpen = ["<video>", "<image>", "<title>", "<subtitle>", "<link>"];
  var tagMapClose = ["</video>", "</image>", "</title>", "</subtitle>", "</link>"];

  $scope.options = {
    loop: false,
    effect: 'horizontal',
    speed: 200,
    pagination: true,
  }

  /**
   * This controls the slider, this is the init function.
   */
  $scope.$on("$ionicSlides.sliderInitialized", function (event, data) {
    $scope.slider = data.slider;
  });

  /**
   * This sets the active page.
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
      $scope.sections[0] = "Invalid File Representation.";
    }

    /**
     * If either the openCounter of closeCounter are still set to null, then there are no section tags within the file
     * which means it has an invalid file representation.
     */
    if (openCounter == null) {
      openCounter = 0;
    }

    if (closeCounter == null) {
      closeCounter = 0;
    }

    /**
     * There is a check to ensure the file is a valid representation.  This is done by making sure all open tags have a corresponding
     * close tags along with both openCounter and closeCounter being greater than 0.  
     * 
     * The for loop then splits the file into individual sections, saving the section title with the content.  It then goes and parses
     * each section to change the custom tags to representation the application can understand.  
     */
    if (openCounter === closeCounter && openCounter > 0 && closeCounter > 0) {
      for (var i = 0; i < openCounter; i++) {
        var sectionStartIndex = content.indexOf('<section>') + 9;
        var sectionEndIndex = content.indexOf('</section>', sectionStartIndex);
        var sectionContent = content.substring(sectionStartIndex, sectionEndIndex);
        $scope.sections.push(parseOutTitle(sectionContent));
        content = content.substring(sectionEndIndex + 10);
      }

      parseEachSection();
    } else {
      $scope.sections[0] = "Invalid File Representation.";
    }

  }

  /**
   * This function takes in the content of a section, creates and object with the title being the section title and returning it back to be parsed.
   */
  var parseOutTitle = function (sectionContent) {
    var contentObject = {};
    var sectionTitleStartIndex = sectionContent.indexOf('<section-title>') + 15;
    var sectionTitleEndIndex = sectionContent.indexOf('</section-title>', sectionTitleStartIndex);
    contentObject.title = sectionContent.substring(sectionTitleStartIndex, sectionTitleEndIndex);
    contentObject.content = sectionContent.substring(sectionTitleEndIndex + 16);
    return contentObject;
  }

  /**
   * This function goes through each section and parses out the cusomtised tags.
   * 
   * A while loop exists to check if any tags still exist to ensure no custom tags are being missed.
   * 
   * The $sce service is used to tell the application that the HTML we return out is safe for users to view and interact with.
   */
  var parseEachSection = function () {
    for (var i = 0; i < $scope.sections.length; i++) {
      var tagsExist = true;
      while (tagsExist) {
        tagsExist = false;
        for (var j = 0; j < tagMapOpen.length; j++) {
          if ($scope.sections[i].content.indexOf(tagMapOpen[j]) != -1) {
            tagsExist = true;
            var startTagIndex = $scope.sections[i].content.indexOf(tagMapOpen[j]);
            var endTagIndex = $scope.sections[i].content.indexOf(tagMapClose[j]) + tagMapOpen[j].length + 1;

            var newTag = switchOnTag(tagMapOpen[j], $scope.sections[i].content.substring(startTagIndex, endTagIndex));
            $scope.sections[i].content = $scope.sections[i].content.replace($scope.sections[i].content.substring(startTagIndex, endTagIndex), newTag);
          }
        }
      }
      if (i < $scope.sections.length - 1) {
        $scope.sections[i].content = $sce.trustAsHtml($scope.sections[i].content);
      } else {
        $scope.sections[i].content = $scope.sections[i].content + parseTagQuiz($scope.topic.quiz);
        $scope.sections[i].content = $sce.trustAsHtml($scope.sections[i].content);
      }
    }
  }

  /**
   * This function takes in the tag and the content, and passes the content to the correct function based on the customised tag.
   */
  var switchOnTag = function (tag, content) {
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
      case "<link>":
        return parseTagLink(content);
        break;
      default:
        break;
    }
  }

  /**
   * Below is the parse functions that turn the customised tags into the representation the application can understand.
   */

  var parseTagVideo = function (content) {
    var parseVideoURLStart = content.indexOf("=") + 1;
    var parseVideoURLEnd = content.indexOf("</video>");
    var videoURL = content.substring(parseVideoURLStart, parseVideoURLEnd);

    //If there is wifi return this one
    if (navigator.onLine) {
      return "<div class=\"video-container content-Box-Shadow\"><iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" + videoURL + "?rel=0&autohide=1&showinfo=0\" frameborder=\"0\" allowfullscreen></iframe></div>";
      //else put in something prettier
    } else {
      return "<div class=\"noInternetWarning\"><div class\"offlineIcon\"><img src=\"img/youtube.png\" alt=\"Content & Quizes\" style=\"width:16vh;height:12vh; margin-top:1vh;\"></div><div class=\"offlineText\">" +
        "Please check you internet connection in order to view the following video</div></div>"
    }

  }

  var parseTagImage = function (content) {
    var parseImageURLStart = content.indexOf("<image>") + 7;
    var parseImageURLEnd = content.indexOf("</image>");
    var imageURL = content.substring(parseImageURLStart, parseImageURLEnd);

    return "<div class=\"content-Image content-Box-Shadow\"><img src=\"content/images/" + imageURL + "\" alt=\"" + imageURL + "\"></div>";
  }

  var parseTagTitle = function (content) {
    var parseTitleStart = content.indexOf("<title>") + 7;
    var parseTitleEnd = content.indexOf("</title>");
    var title = content.substring(parseTitleStart, parseTitleEnd);

    return "<h4 class=\"content-Title contentTitle\">" + title + "</h4><hr>";
  }

  var parseTagSubtitle = function (content) {
    var parseTitleStart = content.indexOf("<subtitle>") + 10;
    var parseTitleEnd = content.indexOf("</subtitle>");
    var subtitle = content.substring(parseTitleStart, parseTitleEnd);

    return "<h5 class=\"content-Subtitle contentSubTitle\">" + subtitle + "</h5>";
  }

  var parseTagLink = function (content) {
    var parseLinkStart = content.indexOf("<link>") + 6;
    var parseLinkEnd = content.indexOf(",");
    var link = content.substring(parseLinkStart, parseLinkEnd);
    var parseNameStart = content.indexOf(",") + 1;
    var parseNameEnd = content.indexOf("</link>");
    var name = content.substring(parseNameStart, parseNameEnd);

    return "<a class=\"content-Link\" onclick=\"window.open('" + link + "', '_system', 'location=yes'); return false;\">" + name + "</a> ";
  }

  var parseTagQuiz = function (quiz) {
    return "<div class=\"centered padding\"><a href=\"#/quiz/" + quiz.url + "\" class=\"button content-Quiz-Button\">" + quiz.title + "</a></div>"
  }

});
