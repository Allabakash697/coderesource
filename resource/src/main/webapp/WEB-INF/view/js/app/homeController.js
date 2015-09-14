function homeController($scope, $http,$rootScope,$route) {
	var message;
	var user;
	var name;
	$scope.isSuccess=true;
	$scope.loading=true;
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
		user=$scope.user1;debugger;
/*		if(user.emailId!=null&&user.password!=null){*/
		$http({
			url : 'home/login',
			method:'POST',
			data : user,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data, status, headers, config) {
			$scope.isSuccess=false;debugger;
			window.location.href="#profile";
			setTimeout(function(){/*{
				var image=new Image();
				image.src='img/loading.gif';
			});*/
				
			$rootScope.message=data.message;
			$rootScope.name=data.userAcc.name;
			$route.reload();
			console.log("$scope.message",$rootScope.message);
			},5000);
			$scope.loading=false;
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
	
	$scope.login=function(){
		user=$scope.user1;debugger;
		if(user.emailId!=null&&user.emailId!=""&&user.password!=null&&user.password!=""){
		loginCheck();
		}else{
			alert("Enter Required Fields");
			if(user.emailId==null&&user.password==null){
				colorMailId();
				colorPassword();
			}else if(user.emailId==""&&user.password==""){
				colorMailId();
				colorPassword();
			}else if(user.emailId==null||user.emailId==""){
				colorMailId();
			}else if(user.password==null||user.password==""){
				colorPassword();
			}
		}
	};
	var colorMailId=function(){
		document.getElementById("emailId").style.borderColor="red";
	}
	var colorPassword=function(){
		document.getElementById("password").style.borderColor="red";
	}
};