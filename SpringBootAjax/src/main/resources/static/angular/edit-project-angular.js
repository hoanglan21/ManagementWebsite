app.controller('EditProjectController', function($scope, $http, $routeParams, $location, $rootScope){
	
	//check if projectID is not a number
	if(isNaN($routeParams.projectID)) {
		$location.path('/home').replace();
	}
	
	function load_data(){
		$http.get('/api/project/' + $routeParams.projectID).then(function(response){
			$scope.project = response.data;
			$scope.customer_name = $scope.project.customer.name;
		});
	}
		
	load_data();
	
	$scope.complexityList = [1,2,3,4,5];
	
	$scope.setCustomerId = function() {
		
		var cus_id = $('.add-project-information .add-project-customer datalist option[value="'+ $scope.customer_name +'"]').attr('data-cus-id');
		$scope.project.customer.customerId = cus_id;
	};
	
	$scope.editProjectSubmit = function() {
		
		var technology = [];
		
		var listBlockTechnology = $('.add-project-information #list-block-technology .block');
		
		listBlockTechnology.each(function(){
			technology.push({ "technologyId": $(this).attr("data-block-id") });
		});
		
		
		var scope = [];
		
		var listBlockScope = $('.add-project-information #list-block-scope .block');
		
		listBlockScope.each(function(){
			scope.push({ "scopeId": $(this).attr("data-block-id") });
		});
		
		
		$scope.project.technologies = technology;
		$scope.project.scopes = scope;
		//alert(JSON.stringify($scope.project));
		
		if($scope.project.name && $scope.project.description && $scope.project.complexity && $scope.project.size 
				&& $scope.project.warranty && $scope.project.customer.customerId && scope != "" && technology != "") {
			
			$http.put('/api/project/edit', $scope.project).then(function(response){
				if(response.data.projectId) {
					$location.path('/project/details/' + response.data.projectId).replace();
				} 	
			}, function(error){
				alert(error.data.message);
				load_data();
			});
		} else {
			alert("Please fill out every blank !!!");
			load_data($scope,$http);
		}
		
	};
	
	// add customer modal
	$scope.addCustomerModal = function() {
			$scope.addCustomerModalBox = false;
	}
	
	$scope.closeCustomerModal = function() {
		$scope.addCustomerModalBox = true;
		$('span#alert-email').text("");
	}
		
	$scope.addNewCustomer = function(){
		
		if($scope.new_customer_name && $scope.customer_phone && $scope.customer_email && $scope.customer_address) {
			var data = {
					"name": $scope.new_customer_name,
					"phone": $scope.customer_phone,
					"email": $scope.customer_email,
					"address": $scope.customer_address
				};
			
			var status = "enable";
			
//			angular.forEach($scope.customers, function(){
//				if($(this).val() == $scope.new_customer_name || $(this).attr("label") == $scope.customer_name) {
//					status = "disable";
//				}
//			})
//			
			if(status == "enable") {
				
				if(!validateEmail($scope.customer_email)){
					alert("Email is invalid !!!");
				} else {
					$http.post('/api/customer/add',data).then(function(response){
						
						alert("Success !!!");
						$rootScope.customers.push(response.data);
						
						$scope.project_customer = response.data.customerId;
						
						$('span#alert-email').text("");
						$scope.addCustomerModalBox = true;
					}, function(error){
						alert(error.data.message);
					});
				}
				
			} else {
				alert("Customer's name already existed !!!");
			}
			
		} else {
			alert("Please fill out every blank !!!");
		}
		
		
	};
	
	// add technology modal
	$scope.selectTech = function(event, item) {
		$scope.project.technologies.push(item);
		var index = $scope.technologies.indexOf(item);
		$scope.technologies.splice(index, 1);
	}
	
	$scope.addTechModal = function() {
			$scope.addTechModalBox = false;
			load_tech();
			$scope.search_technology = "";
	}
	
	$scope.closeTechModal = function() {
		$scope.addTechModalBox = true;
	}
	
	$scope.deleteTech = function(item) {
		var index = $scope.project.technologies.indexOf(item);
		$scope.project.technologies.splice(index, 1);
	}
	
	function load_tech(){
	
			$scope.technologies = JSON.parse(JSON.stringify($rootScope.technologies));
			
			angular.forEach($scope.project.technologies, function(item){
				var tech_id = item.technologyId;
				var filtered = $scope.technologies.filter(function(item) { 
	            	return item.technologyId != tech_id;  
	            });
	           
				$scope.technologies = filtered;
				
			});
			
			$scope.$evalAsync();
	
	};
	
	$scope.addNewTechnology = function() {
		if($scope.new_technology) {
			var data = {
					"name": $scope.new_technology
			};
			
			
			$http.post('/api/technology/add',data).then(function(response){
				if(response.data) {
					alert("Success !!!");
					$rootScope.technologies.push(response.data);
					load_tech();
				} 
			}, function(error){
				alert(error.data.message);
			});
			
			$scope.new_technology = "";
			$scope.search_technology = "";
			
		} else {
			alert("Technology's name cannot be empty !!!");
		}
	};
	
	$scope.searchTechnology = function(){
		if($scope.search_technology) {
			var url = '/api/technology/search/'+ $scope.search_technology;
			$http.get(url).then(function(response){
				$scope.technologies = response.data;
				
				angular.forEach($scope.technologies, function(){
    				var tech_id = $(this).attr("data-block-id");
    				var filtered = $scope.technologies.filter(function(item) { 
    	            	return item.technologyId != tech_id;  
    	            });
    	           
    				$scope.technologies = filtered;
    				
    			});
			});
		} else {
			load_tech();
		}
		
		
	};
	
	// add scope modal
	$scope.deleteScope = function(item) {
		var index = $scope.project.scopes.indexOf(item);
		$scope.project.scopes.splice(index, 1);
	}
	
	$scope.selectScope = function(event, item) {
		$scope.project.scopes.push(item);
		var index = $scope.scopes.indexOf(item);
		$scope.scopes.splice(index, 1);
	}
	
	$scope.addScopeModal = function() {
			$scope.addScopeModalBox = false;
			load_scope();
			$scope.search_scope = "";
	}
	
	$scope.closeScopeModal = function() {
		$scope.addScopeModalBox = true;
	}
	
	function load_scope() {
		
		$scope.scopes = JSON.parse(JSON.stringify($rootScope.scopes));
			
			angular.forEach($scope.project.scopes, function(item){
				var scope_id = item.scopeId;
				var filtered = $scope.scopes.filter(function(item) { 
	            	return item.scopeId != scope_id;  
	            });
	           
				$scope.scopes = filtered;
				
			});
			
			$scope.$evalAsync();
	
	}
	
	$scope.addNewScope = function() {
		if($scope.new_scope) {
			var data = {
					"name": $scope.new_scope
			};
			
			
			$http.post('/api/scope/add',data).then(function(response){
				if(response.data) {
					alert("Success !!!");
					$rootScope.scopes.push(response.data);
					load_scope();
				} 
			},function(error){
				alert(error.data.message);
			});
			
			$scope.new_scope = "";
			$scope.search_scope = "";
		} else {
			alert("Scope's name cannot be empty !!!");
		}
	};
	
	$scope.searchScope = function(){
		if($scope.search_scope) {
			var url = '/api/scope/search/'+ $scope.search_scope;
			$http.get(url).then(function(response){
				$scope.scopes = response.data;
				
				angular.forEach($scope.scopes, function(){
    				var scope_id = $(this).attr("data-block-id");
    				var filtered = $scope.scopes.filter(function(item) { 
    	            	return item.scopeId != scope_id;  
    	            });
    	           
    				$scope.scopes = filtered;
    				
    			});
			});
		} else {
			load_scope();
		}
	};
	
});