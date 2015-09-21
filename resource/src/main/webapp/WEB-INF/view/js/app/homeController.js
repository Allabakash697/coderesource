function homeController($scope, $http,$rootScope,$route) {
	var message;
	var user;
	var user1=$scope.user1;
	var name;
	$scope.isSuccess=true;
	$scope.loading=true;
	console.log("loading1",$scope.loading);
	
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
	var loginCheck=function(){
		user1=$scope.user1;
/*		if(user.emailId!=null&&user.password!=null){*/
		$http({
			url : 'home/login',
			method:'POST',
			data : user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$scope.isSuccess=false;
			window.location.href="#profile";
			setTimeout(function(){/*{
				var image=new Image();
				image.src='img/loading.gif';
			});*/
				
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
			$route.reload();
			console.log("$scope.message",$rootScope.message);
			$scope.loading=false;
			},5000);
			console.log("loading1",$scope.loading);
		});
	
	/*}else {
		alert("Enter Required Fields");
		if(user.emailId==null&&user.password==null){
			$scope.user1=null;
		document.getElementById("emailId").style.borderColor="red";
		document.getElementById("password").style.borderColor="red";
		}else if(user.emailId==null){
			$scope.user1.emailId=null;
			document.getElementById("emailId").style.borderColor="red";
		}else{
			$scope.user1.password=null;
			document.getElementById("password").style.borderColor="red";
		}
	}*/
	};
	console.log("loading2",$scope.loading);
	$scope.login=function(){
		user=$scope.user1;
		loginCheck();
	};
	
};