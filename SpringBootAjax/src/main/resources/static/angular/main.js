function validateEmail(email) {
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  return re.test(email);
}



var app = angular.module('mainApp', ['ngRoute','ngAnimate', 'ngCookies']);

app.config(function($routeProvider,$locationProvider){
	
	$locationProvider.html5Mode({
        enabled: true
    });
	
	$routeProvider.when('/home',{
		// template:'<h1>Hello from home</h1>',
		templateUrl:'home.html',
		controller:'HomeController' 
			
	}).when('/project/edit/:projectID',{
		templateUrl:'editProject.html',
		controller:'EditProjectController'
			
	}).when('/project/details/:projectID',{
		templateUrl:'projectDetails.html',
		controller:'ProjectDetailsController'
			
	}).when('/project/add',{
		templateUrl: 'addProject.html',
		controller:"AddProjectController"
			
	}).when('/customer/:customerID',{
		templateUrl:'customerDetails.html',
		controller:"CustomerController"
			
	}).when('/login',{
		templateUrl:'login.html',
		controller:"LogInController"
			
	}).when('/register',{
		templateUrl:'register.html',
		controller:"RegisterController"
			
	}).otherwise({
		redirectTo:'/home'
	});
	
}).run(function($location, $rootScope, $cookieStore , $http){
	
	$rootScope.$on('$routeChangeStart', function (event) {

		if ($location.path() == '/login'){
		
			if ($cookieStore.get('logIn')) {
				
				$rootScope.Login = false;
				$location.path('/home').replace();
			
			}
		}

	});
	
	if(!$rootScope.scopes){
		$http.get('/api/scope').then(function(response){
			$rootScope.scopes = response.data;
		});
	}
	
	if(!$rootScope.technologies){
		$http.get('/api/technology').then(function(response){
			$rootScope.technologies = response.data;
		}); 
	}
	
	if(!$rootScope.customers){
		$http.get('/api/customer').then(function(response){
			$rootScope.customers = response.data;
		}); 
	}
	$rootScope.Logout = function() {
		if ($cookieStore.get('logIn')) {
			$cookieStore.remove('logIn');
			$rootScope.Login = true;
			$location.path('/login').replace();

		}
	}
});
