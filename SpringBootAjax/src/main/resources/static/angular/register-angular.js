app.controller('RegisterController',function($scope,$http,$location){
	
	$scope.submitAccount = function(){
		if($scope.username && $scope.email && $scope.password && $scope.repassword){
			
			var data = {
				"username":$scope.username,
				"email":$scope.email,
				"password":$scope.password,
				"repassword":$scope.repassword
			};
			
			$http.post('/api/account/add', data).then(function(response){
				alert("Success");
				$location.path('/login').replace();
			},function(error){
				alert(error.data.message);
			});
			
		} else {
			alert("Please fill out every blank!!!");
		}
	};
	
	
	
});