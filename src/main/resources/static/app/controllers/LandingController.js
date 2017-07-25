app.controller('landingController', [ '$rootScope', '$scope', '$location', function($rootScope, $scope, $location) {

	$scope.message = '';
	$scope.header = 'What next?';
	$scope.subheader = 'SELECT WHAT YOU WOULD LIKE TO DO.';
	$scope.loginName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	$scope.goBack = function( ) {
		$location.path('/main');
	};

	$scope.go = function(path) {
		$location.path(path);
	};

} ]);