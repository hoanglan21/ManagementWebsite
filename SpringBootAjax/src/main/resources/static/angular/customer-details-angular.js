app.controller('CustomerController', function($scope, $http, $routeParams,
		$location) {

	// check if customerID is not a number
	if (isNaN($routeParams.customerID)) {
		$location.path('/home').replace();
	}

	function load_data($scope, $http) {
		$http.get('/api/customer/' + $routeParams.customerID).then(
				function(response) {
					$scope.customer = response.data;
				});
	}
	;

	load_data($scope, $http);

	$scope.alertValidateEmail = function(element) {
		if ($scope.customerForm.email) {
			if (validateEmail($scope.customerForm.email)) {
				$scope.emailAlert = "*valid";
			} else {
				$scope.emailAlert = "*invalid";
			}
		} else {
			$scope.emailAlert = "";
		}

	};

	$scope.customerForm = {
		"customerId" : "",
		"name" : "",
		"phone" : "",
		"email" : "",
		"address" : ""
	};

	// open edit customer modal box
	$scope.openEditCustomerModal = function() {
		$scope.customerForm = JSON.parse(JSON.stringify($scope.customer));
		$scope.editCustomerModalBox = false;
	};

	// close edit customer modal box
	$scope.closeEditCustomerModal = function() {
		$scope.editCustomerModalBox = true;
	}

	// save change customer data
	$scope.saveCustomerEditData = function() {
		if ($scope.customerForm.name && $scope.customerForm.email
				&& $scope.customerForm.address && $scope.customerForm.phone) {

			if (!validateEmail($scope.customerForm.email)) {
				alert("Email is invalid !!!");
				load_data($scope, $http);

			} else {

				$http.put('/api/customer/edit', $scope.customerForm).then(function(response) {
							alert("Success !!!");
							$scope.emailAlert = "";
							$scope.editCustomerModalBox = true;
							$scope.customer = response.data;
						}, function(error) {
							alert(error.data.message);
							$scope.customerForm = JSON.parse(JSON.stringify($scope.customer));
						});

			}

		} else {
			alert("Please fill out every blank !!!");
			$scope.customerForm = JSON.parse(JSON.stringify($scope.customer));
		}
	};

});