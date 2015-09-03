function homeController($scope, $http,$rootScope,$route) {
	var message;
	$scope.isSuccess=true;
	$scope.register = function() {
		var user=$scope.user;
		var name=$scope.user.name;
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
};