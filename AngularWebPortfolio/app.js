var app = angular.module('app', ["ui.bootstrap","ngRoute","ngAnimate","ngTouch"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/aboutMe", {
        templateUrl : "aboutMe.html"
    })
    .when("/projects", {
        templateUrl : "projects.html",
        controller: "ProjectsController"
    })
    .when("/professional", {
        templateUrl : "professional.html"
    })
     .when("/music", {
        templateUrl : "music.html"
    })
     .when("/academic", {
        templateUrl : "academic.html",
        controller:"AcademicController"
    }) 
     .when("/volunteering", {
        templateUrl : "volunteering.html"
    })
        .when("/aboutPage", {
        templateUrl : "aboutPage.html"
    })
    .otherwise({
    redirectTo: "/aboutMe"
  });
});

app.controller('MainController', function ($animate,$scope) {
	 $animate.enabled(true); 

   $scope.slides=[{
      image: "images/shal.jpg",
      text: "Shal Xu",
      button:"About Me",
      link:"/#aboutMe",
      index:0
    },
    {
   	 image: "images/pedals.jpeg",
      text: "4+ Years of Watercolor, Charcoal, Colored Pencil, Pen... ",
      button:"Browse My Art Portfolio HERE",
      link:"http://shalxu.tumblr.com",
      index:1
    },
      {
    	image: "images/code.jpg",
      text: "A Visual Programmer",
      button:"Take a Look at My Other Projects Too!",
      link:"/#projects",
      index:2
    }];

    $scope.nav="aboutMe";
   $scope.checkSlideActive=function(index){
      if(index==0){
        console.log(index);
        return "item active";
      }
      else
        return "item";
    }

    $scope.checkActive=function(item){
      if(item==$scope.nav)
        return "active";
      else
        return;
    }
     $scope.setActive=function(item){
      $scope.nav=item;
    }

});