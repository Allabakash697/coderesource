'use strict';
var app = angular.module('homeApp', []);

app.controller('homeController', [ '$scope', '$http','$location','$rootScope', function($scope, $http,$rootScope) {
	var user;
var val="hello2";
/*$scope.isSuccess=false;
$scope.showRegister=true;*/
	$scope.register = function() {
		/*
		 * $scope.name=name; console.log("name",name); $scope.emailId=emailId;
		 * $scope.password=password;
		 */
		$http({
			url : "/resource/home/register",
			data : user
		}).success(function(data, status, headers, config) {
/*			$scope.dummy=data;
*/
			$rootScope.$broadcast("dummy",data);
			document.location.href='partials/profile';
			console.log("$scope.dummy",$rootScope.dummy);
			/*$scope.isSuccess=true;
			$scope.showRegister=false;*/

		});
		console.log("$scope.dummy",$scope.dummy);
	};
	
} ]);

app.controller('profileController', [ '$scope', '$http','$location','$rootScope', function($scope, $http,$rootScope) {
	
} ]);