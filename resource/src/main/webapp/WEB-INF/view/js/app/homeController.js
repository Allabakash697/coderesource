function homeController($scope, $http,$rootScope,$route) {
	$scope.isSuccess=true;
	$scope.register = function() {
		var user=$scope.user;
		$http({
			url : 'home/register',
			method:'POST',
			data : user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$scope.isSuccess=false;
			$rootScope.dummy=data;
			$route.reload();
			console.log("$scope.dummy",$rootScope.dummy);

		});
		
	};
	$scope.dummy=$rootScope.dummy;

};