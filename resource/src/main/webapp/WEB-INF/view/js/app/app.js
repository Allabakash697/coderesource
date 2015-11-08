'use strict';
var app = angular.module('homeApp', [ 'ngRoute','ngCookies' ]);

app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/profile', {
		templateUrl : 'partials/profile',
	})
	.when('/login', {
		templateUrl : 'partials/login',
	})
	.when('/usersList', {
		templateUrl : 'partials/usersList',
	})
	.when('/', {
		templateUrl : 'partials/login',
	});
} ]);


//Testing purpose **************************************
/*app.controller('homeCtrl',function($http){
	console.log("here");
	var url="home/getHtml";
	var url="home/getHtml";
	$http({
		url : url,
		method:'GET',
		dataType:'html'
		
	}).success(function(data, status, headers, config) {
		console.log("here",data);debugger;
		window.location.href=data;
		jQuery.getScript(data);
		console.log("here");
	});
});*/