'use strict';

var app = angular.module('ArtisonFirst.artison', [ 'ngRoute', 'ngFileUpload', 'angularFileUpload' ]);

app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider

	.when('/main', {
		templateUrl : 'app/views/landing.html',
		controller : 'landingController',
		data : {
			registration : "required"
		}
	})

	.when('/product', {
		templateUrl : 'app/views/product.html',
		controller : 'productController',
		data : {
			registration : "required"
		}
	})

	.when('/profile', {
		templateUrl : 'app/views/profile.html',
		controller : 'profileController',
		data : {
			registration : "required"
		}
	})

	.when('/business', {
		templateUrl : 'app/views/business.html',
		controller : 'businessController',
		data : {
			registration : "required"
		}
	})

	.when('/login', {
		templateUrl : 'app/views/login.html',
		controller : 'loginController',
		data : {
			registration : "not-required"
		}
	})
	
	.when('/register', {
		templateUrl : 'app/views/registration.html',
		controller : 'registrationController',
		data : {
			registration : "not-required"
		}
	})

	.when('/registered', {
		templateUrl : 'app/views/registered.html',
		controller : 'loginController',
		data : {
			registration : "not-required"
		}
	})
	
	.otherwise({
		redirectTo : '/login'
	});

}]);
