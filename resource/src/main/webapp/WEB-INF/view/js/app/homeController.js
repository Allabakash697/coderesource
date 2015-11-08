function homeController($scope, $http,$rootScope,$route,$cookies,$cookieStore) {
	$rootScope.isSuccess=false;
	$rootScope.loginError=false;
	/*.........................................*/
	(function() {
		 $('.emptyCheck > input').keyup(function() {
			 var empty=false;
		$('.emptyCheck > input').each(function(){
			if($(this).val() == '') {
				empty = true;		
		}
		});
		 if (empty) {
			 $('#botton').attr('disabled', 'disabled');
		 }else{
			 $('#botton').removeAttr('disabled');
		 }
		 });
	})()
	
	/*.........................................*/
	$scope.register = function() {
		 /*user=$scope.user;*/
		$http({
			url : 'home/register',
			method:'POST',
			data : $scope.user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$rootScope.isSuccess=true;
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
				window.location.href="#profile";
		});
		
	};
	
	$scope.loginCheck=function(){
		$http({
			url : 'home/login',
			method:'POST',
			data : $scope.user1,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			
			/*window.location.href="#profile";*/
			if(data.userAcc.name!=null){
				$rootScope.isSuccess=true;
				if(data.admin){
				window.location.href="#usersList";
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
			$http({
				url : 'home/getUserDetails',
				method:'POST',
				headers:{'Content-Type': 'application/json'}
				
			}).success(function(data, status, headers, config) {
				$rootScope.userDetail=data;
			});
			}else{
				window.location.href="#profile";
				$rootScope.message=data.message;
				$rootScope.name=data.userAcc.name;
			}
			}else{
				$rootScope.loginError=true;
				$rootScope.messageError=data.message;
			}
		});
	
	};
	
	$scope.login=function(){
		user=$scope.user1;
		$scope.loginCheck();
	};
	
	/*$scope.loginCheck=function(){
		
		$http({
			url : 'home/reload',
			method:'POST',
			data : $scope.user1,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			console.log("data",status);debugger;
			console.log("$cookies",$cookies);
			alert("Suceess");
		});
	}*/
	$scope.routeTo=function(){
		if($cookies.userCookieModel!=null){
			$rootScope.$broadcast("checkAdmin");
		}else{
			window.location.href="/resource";
		}
	}
	
	$scope.logOut=function(){
		/*angular.forEach($cookies, function (v, k) {
			$cookieStore.remove(k);
		});*/
		$rootScope.isSuccess=false;
		$cookieStore.remove("userCookieModel");
		window.location.href="/resource";
	}
};