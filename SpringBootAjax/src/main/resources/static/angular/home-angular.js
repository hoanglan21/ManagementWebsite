
app.controller('HomeController',function($scope,$http,$document){
	
	function load_data($scope,$http) {
		$http.get('/api/project/page/0').then(function (response) {
			
			var pageAmount = response.data.totalPages;
			
			generateBtn(1,pageAmount);
			
			
			angular.element($document.find('.table-page-number span')).click(function choosePage() { 
				var cur = parseInt($(this).text()) - 1;
				$http.get('/api/project/page/'+cur).then(function (response) {
					$scope.projects = response.data.content;
				});
				
		        generateBtn(parseInt($(this).text()),pageAmount);
		        angular.element($document.find('.table-page-number span')).click(choosePage);
		    });
			
			$scope.projects = response.data.content;
		});
	};
	
	load_data($scope,$http);
	
	$scope.deleteProject = function(id) {
		$http.delete('/api/project/delete/'+id).then(function(response){
			alert("Success !!!");
			load_data($scope,$http);
		});
	}
	
	$scope.clickSearch = function() {
		var tech = 0;
		var scope = 0;
		var name = "";
		if($scope.search_technology) {
			tech = $scope.search_technology;
		}
		
		if($scope.search_scope) {
			scope = $scope.search_scope;
		}
		
		if($scope.search_name) {
			name = $scope.search_name;
		}
		
		$http.get('/api/project/search?page=0&tech_id=' + tech + "&scope_id=" + scope + "&name=" + name).then(function(response){
			
			var pageAmount = response.data.totalPages;
			
			generateBtn(1,pageAmount);
			
			angular.element($document.find('.table-page-number span')).click(function choosePage() { 
				var cur = parseInt($(this).text()) - 1;
				$http.get('/api/project/search?page='+ cur + '&tech_id=' + tech + "&scope_id=" + scope + "&name=" + name).then(function (response) {
					$scope.projects = response.data.content;
				});
				
		        generateBtn(parseInt($(this).text()),pageAmount);
		        
		        angular.element($document.find('.table-page-number span')).click(choosePage);
		    });
			
			$scope.projects = response.data.content;
			
		});
	}
	
});

function generateBtn(current,amount) {  
    var html = '';
    
    if(amount < 6) {
        for(var i = 1; i <= amount; i++){
            if(i == current){
                html = html + '<span id="selected">'+i+'</span>';
            } else {
                html = html + '<span>'+i+'</span>';
            }  
        }
    } else {
        if(current == 1 || current == 2) {
            for(var i = 1; i <= 3; i++){
                if(i == current){
                    html = html + '<span id="selected">'+i+'</span>';
                } else {
                    html = html + '<span>'+i+'</span>';
                }  
            }
            html = html + '...' + '<span>'+ parseInt(amount - 1) +'</span>' + '<span>'+amount+'</span>';
            
        } else if ( current == amount || current == amount - 1){
            
            html = html + '<span>1</span>' + '<span>2</span>' + '...';
            for(var i = amount - 2; i <= amount; i++){
                if(i == current){
                    html = html + '<span id="selected">'+i+'</span>';
                } else {
                    html = html + '<span>'+i+'</span>';
                }  
            }

        } else {
            html = html + '<span>1</span>';

            if(current-2 > 1) {
                html = html + '...';
            } 
            
            for(var i = current - 1; i <= current + 1; i++){
                if(i == current){
                    html = html + '<span id="selected">'+i+'</span>';
                } else {
                    html = html + '<span>'+i+'</span>';
                }   
            }

            if(amount - current - 1 > 1) {
                html = html + '...';
            } 
            html = html + '<span>'+ amount +'</span>';
        }
    }

    document.getElementsByClassName('table-page-number')[0].innerHTML = html;
}

