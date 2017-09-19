app.controller('inventoryController', [ '$rootScope', '$scope', '$location', 'LoginService', function($rootScope, $scope, $location, LoginService) {

	$scope.message = '';
	$scope.header = 'Manage Inventory';
	$scope.subheader = 'MANAGE SUPPLIES AND PRODUCTS';
	$scope.loginName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	$scope.go = function(path) {
		LoginService.go( path );
	};
	
	$scope.goBack = function(path) {
		LoginService.back();
	};

} ]);