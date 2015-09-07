function homeController($scope, $http,$rootScope,$route) {
	var message;
	var user;
	var name;
	$scope.isSuccess=true;
	$scope.register = function() {
		 user=$scope.user;
		$http({
			url : 'home/register',
			method:'POST',
			data : user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$scope.isSuccess=false;
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
			$route.reload();
			console.log("$scope.message",$rootScope.message);

		});
		
	};
	$scope.login=function(){
		user=$scope.user1;debugger;
		$http({
			url : 'home/login',
			method:'POST',
			data : user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$scope.isSuccess=false;debugger;
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
			$route.reload();
			console.log("$scope.message",$rootScope.message);

		});
	};
};