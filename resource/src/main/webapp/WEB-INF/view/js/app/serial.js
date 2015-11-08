function serialController($scope,$http){
	
	
	/*$scope.getNextNumb=function(){
		var data=$scope.numbr;debugger;
		 var url="numb/getNext?numb="+data;
		$http({
			url:url,
			method:'GET',
			data:$scope.numbr,
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data,status,headers,config){
			debugger;
			$scope.nextNumber=data;
			
		});
	};*/
	
	$scope.getNextNumb=function(){
		var data=$scope.numbr;debugger;
		 var url="numb/getNext?numb="+data;
		$http({
			url:url,
			method:'GET',
			headers:{'Content-Type': 'application/json'}
			
		}).success(function(data,status,headers,config){
			/*debugger;
			$scope.nextNumber=data;*/
			
		});
	};
	
	$scope.getNextNumbers=function(){
		var getData=$scope.serialNumber;debugger;
		var url="numb/getNumbersList";
			$http({
				url:url,
				method:"POST",
				data:getData,
				headers: {'Content-Type': 'application/json'}
				
			}).success(function(data,status,headers,config){
				debugger;
				$scope.numbers=data;
			});
	}
}