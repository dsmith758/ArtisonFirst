app.controller('landingController', [ '$rootScope', '$scope', '$location', 'LoginService', function($rootScope, $scope, $location, LoginService) {

	$scope.message = '';
	$scope.header = 'What next?';
	$scope.subheader = 'SELECT WHAT YOU WOULD LIKE TO DO.';
	$scope.loginName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	$scope.go = function(path) {
		LoginService.go( path );
	};
	
	$scope.goBack = function(path) {
		LoginService.back();
	};

} ]);