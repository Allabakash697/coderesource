'use strict';
var app = angular.module('homeApp', [ 'ngRoute' ]);

app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/profile', {
		templateUrl : 'partials/profile',
		controller :'homeController'
	})
	.when('/login', {
		templateUrl : 'partials/login',
	})
	.when('/home', {
		templateUrl : 'partials/login',
	});
} ]);