app.controller('LogInController', function($rootScope, $scope, $http, $cookieStore, $location){
	
	$scope.logIn = function() {
		if ($scope.username && $scope.password){
			var account = {
					"username": $scope.username,
					"password": $scope.password
			}
			$http.post('/api/account/login', account).then(function(response){
				if (response.data != null) {
					$cookieStore.put('logIn', response.data);
					$location.path('/home').replace();
					$rootScope.Login = false;
				}
				else {
					alert("Username or password is incorrect");
				}
			},function(error){
				alert(error.data.message);
			})
		}
		else {
			alert("You must fulfill all blanks");
		}
	};
	
});
