/**
 * 
 */
function profileController($rootScope,$scope, $http,$cookies){
	$rootScope.isSuccess=true;
	$rootScope.rdtMsg=false;
	var name=$rootScope.name;
	console.log("",$rootScope.userDetail);
	console.log("$cookies",$cookies);
	if(name==null){
	if($cookies.userCookieModel!=null){
		var userCookie=$cookies.userCookieModel;
		var email=/<emailId>(.*?)<\/emailId>/.exec(userCookie)[1];
		var ses=/<session>(.*?)<\/session>/.exec(userCookie)[1];
		$scope.user1={
				emailId:email,
				session:ses
		};
		if(ses){
			var url="home/login";
			$http({
				url : url,
				method:'POST',
				data : $scope.user1,
				headers:{'Content-Type': 'application/json'}
				
			}).success(function(data, status, headers, config) {
				$rootScope.message=data.message;
				$rootScope.name=data.userAcc.name;
				$rootScope.isAdmin=data.admin;
				if(data.admin){
					$rootScope.isAdmin=true;
				$http({
					url : 'home/getUserDetails',
					method:'POST',
					headers:{'Content-Type': 'application/json'}
					
				}).success(function(data, status, headers, config) {
					$rootScope.userDetail=data;
				});
				}
			});
		}
		
	}else{
		$rootScope.isSuccess=false;
		$rootScope.rdtMsg=true;
		$rootScope.redirectmsg="Error.... Login Again..";
		setTimeout(redirectToHomePage, 5000);
		
		function redirectToHomePage(){
		window.location.href="/resource";
		}
	}
	}
	
	$scope.$on("checkAdmin",function(event,data){
		var userCookie=$cookies.userCookieModel;
		var isAdmin=/<isAdmin>(.*?)<\/isAdmin>/.exec(userCookie)[1];
		
		if(isAdmin=="true"){
			window.location.href="#usersList";
		}else{
			window.location.href="#profile";
		}
	});
}