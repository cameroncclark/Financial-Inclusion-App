fIApp.controller('ContentCtrl', function ($scope, $http, $stateParams, $sce) {
  $scope.name = $stateParams.pageTitle;
  $scope.content = "";
  var ourTags = ["<section>", "</section>", "<title>", "</title>"];

  $http.get('content/topics/' + $stateParams.contentURL)
    .then(function (response) {
      $scope.topic = response.data;
      $scope.content = $scope.parse($scope.topic.content);
    });

  $scope.parse = function (content) {
    var sections = [];
    var openCounter = 0;
    var closeCounter = 0;

    openCounter = content.match(new RegExp("<section>", "g")).length;
    closeCounter = content.match(new RegExp("</section>", "g")).length;

    if(openCounter == null){
      openCounter = 0;
    }

    if(closeCounter == null){
      closeCounter = 0;
    }

    if (openCounter === closeCounter && openCounter > 0 && closeCounter > 0 ) {
      for(var i = 0 ; i < openCounter; i++){
        var sectionStartIndex = content.indexOf('<section>')+9;
        var sectionEndIndex = content.indexOf('</section>',sectionStartIndex);
        var sectionContent = content.substring(sectionStartIndex,sectionEndIndex);
        sections.push(sectionContent);
        content = content.substring(sectionEndIndex+10);
      }
      console.log(sections);
      
    } else {
      content = "Invalid file representation."
    }
    return content;
  }

});
