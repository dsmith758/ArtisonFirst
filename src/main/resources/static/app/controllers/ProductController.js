app.controller('productController', [ '$scope', '$location', 'LoginService', function($scope, $location, LoginService) {

	$scope.message = "";
	$scope.userName = LoginService.getUserName();

	$scope.go = function(path) {
		$location.path(path);
	};

} ]);