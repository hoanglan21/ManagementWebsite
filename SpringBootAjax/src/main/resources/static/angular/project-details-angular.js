app.controller('ProjectDetailsController', function($scope, $http, $routeParams){
	
	//check if projectID is not a number
	if(isNaN($routeParams.projectID)) {
		$location.path('/home').replace();
	}
	
	function load_data($scope, $http) {
		$http.get("/api/project/"+$routeParams.projectID).then(function(response){
        	$scope.project = response.data;
        });
	}; 
	
	
	load_data($scope, $http);
	
	//project constraint edit
	
	//open add constraint modal box
	$scope.openAddConstraintModal = function(){
		$scope.addConstraintBox = false;
	};
	
	//close add constraint modal box
	$scope.closeAddConstraintModal = function(){
		$scope.addConstraintBox = true;
	};
	
	$scope.editConstraintData = {
		"constraintId": "",
		"description": ""
	};
	
	//open edit constraint modal box
	$scope.openEditConstraintModal = function(constraint){
		$scope.editConstraintData = JSON.parse(JSON.stringify(constraint));
		$scope.editConstraintBox = false;
	};
	
	//close edit constraint modal box
	$scope.closeEditConstraintModal = function(){
		$scope.editConstraintBox = true;
	};
	
	//add constraint method
	$scope.addConstraintBtn = function(){
		var json = {
    			"description": $scope.constraint_description,
    	};
		
    	if ($scope.constraint_description){
    		$http.post("/api/constraint/add/" + $routeParams.projectID, json).then(function(response){
    			if(response.data) {
    				load_data($scope, $http);
    			} 
    		},function(error){
    			alert(error.data.message);
    		});
    		$scope.addConstraintBox = true;
    	} else {
    		alert("Please fill out every blank !!!");
    	}
    	
	};
	
	//edit constraint method
	$scope.editConstraintBtn = function(){
		
		if($scope.editConstraintData.constraintId && $scope.editConstraintData.description){
			
			$http.put("/api/constraint/edit", $scope.editConstraintData).then(function(response){
	    		if(response.data) {
					load_data($scope, $http);
				} 
	    	},function(error){
	    		alert(error.data.message);
	    	});
			$scope.editConstraintBox = true;
		} else {
			alert("Please fill out every blank !!!");
		}
		
	};
	
	
	$scope.deleteConstraint = function(id){
		$http.delete("/api/constraint/delete/"+ id + "/" +$routeParams.projectID).then(function(response){
			alert("success");
			load_data($scope, $http);
    	},function(error){
    		alert(error.data.message);
    	});
		
	};
	
	//project assumption edit
	
	$scope.assumptionForm = {
			"description":"",
			"reason":"",
			"categories":[]
	}
	
	function initAddForm(){
		$scope.assumptionForm.description = "";
		$scope.assumptionForm.reason = "";
		$scope.assumptionForm.categories = [];
	}

	$scope.project_id = $routeParams.projectID;
	
	$scope.assumptions = [];
	$scope.editRecord = {
			"assumptionId":"",
			"description":"",
			"reason":"",
			"categories":""
	},
	$scope.categoriesAssumption = [];

	//open add assumption modal
	$scope.createAssumption = function() {
		$scope.addAssumptionBox = false;
		_refreshCategoryData();
		initAddForm();
	};
	
	//chose category block in add modal
	$scope.addCategoryAdd = function(category){
		$scope.assumptionForm.categories.push(category);
		var index = $scope.categoriesAssumption.indexOf(category);
		$scope.categoriesAssumption.splice(index,1);
	};
	
	//close category block in add modal
	$scope.closeCategoryAdd = function(category){
		$scope.categoriesAssumption.push(category);
		var index = $scope.assumptionForm.categories.indexOf(category);
		$scope.assumptionForm.categories.splice(index,1);
	};
	
	//close add assumption modal
	$scope.closeAddAssumptionBox = function(){
		$scope.addAssumptionBox = true;
	};
	
	//open edit assumption modal
	$scope.openEditAssumptionModal = function(assumption){

			
			$scope.editAssumptionBox = false;
			$scope.editRecord.assumptionId = JSON.parse(JSON.stringify(assumption.assumptionId));
			$scope.editRecord.description = JSON.parse(JSON.stringify(assumption.description));
			$scope.editRecord.categories = JSON.parse(JSON.stringify(assumption.categories));
			$scope.editRecord.reason = JSON.parse(JSON.stringify(assumption.reason));
			
			$http({
				method : 'GET',
				url : '/assumptions/categories'
			}).then(function(res) { // success
				$scope.categoriesAssumptionEdit = res.data;
				$.each(assumption.categories, function(arrayID, category) {
			          
					var filtered = $scope.categoriesAssumptionEdit.filter(function(item) { 
		            	return item.categoryId !== category.categoryId;  
		            });
		           
					$scope.categoriesAssumptionEdit = filtered;
		            
				});
				
			});

	};
	//close category block in edit assumption modal
	$scope.closeCategoryEdit = function(category){
		$scope.categoriesAssumptionEdit.push(category);
		var index = $scope.editRecord.categories.indexOf(category);
		$scope.editRecord.categories.splice(index,1);
	};
	
	//add category block in edit assumption modal
	$scope.addCategoryEdit = function(category){
		$scope.editRecord.categories.push(category);
		var index = $scope.categoriesAssumptionEdit.indexOf(category);
		$scope.categoriesAssumptionEdit.splice(index,1);
	};
	
	//close edit assumption modal
	$scope.closeEditAssumptionModal = function(){
		$scope.editAssumptionBox = true;
	};


	//post add assumption request
	$scope.submitAssumption = function() {
		
		var method = "POST";
		var url = '/assumption/add/'+$scope.project_id;
		// alert(JSON.stringify($scope.assumptionForm));
		$http.post(url,$scope.assumptionForm).then(function(response){
			if(response.data) {
				load_data($scope, $http);
				$scope.addAssumptionBox = true;
			}	
		},function(error){
			alert(error.data.message);
		});
	};
	
	// HTTP DELETE- delete employee by Id
	// Call: http://localhost:8080/employee/{empId}
	$scope.deleteAssumption = function(assumption) {
		$http({
			method : 'DELETE',
			url : '/assumption/delete/' + assumption.assumptionId + "/" + $scope.project_id
		}).then(function(response){
			alert("Success !!!");
			load_data($scope, $http);
		},function(error){
			alert(error.data.message);
		});
	};

	// In case of edit
	$scope.editAssumption = function(assumption) {
	    //alert(JSON.stringify($scope.editRecord));
		var method = "PUT";
		var url = '/assumption/edit';
		$http.put(url,$scope.editRecord).then(function(response){
			if(response.data) {
				load_data($scope, $http);
				$scope.editAssumptionBox = true;
			}	
		},function(error){
			alert(error.data.message);
		});
		
	};

	// Private Method
	// HTTP GET- get all employees collection
	// Call: http://localhost:8080/employees
	
	function _refreshCategoryData() {
		
		$http({
			method : 'GET',
			url : '/assumptions/categories'
		}).then(function(res) { // success
			$scope.categoriesAssumption = res.data;
			
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
		});
	}
	
	
		
});