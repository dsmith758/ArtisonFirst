app.controller('menuController', [ '$rootScope', '$scope', '$location', 'LoginService', function($rootScope, $scope, $location, LoginService) {
	$scope.isMenuOpen = false;
	$scope.authenticated = $rootScope.authenticated;
	$scope.userName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	$scope.go = function(path) {
		$scope.closeMenu();
		$location.path(path);
	};

	$scope.toggleMenuBtn = function() {
		$scope.isLoggedIn = LoginService.isLoggedIn();
		if (!$scope.isMenuOpen) {
			$scope.authenticated = $rootScope.authenticated;
			$scope.openMenu();
		} else {
			$scope.closeMenu();
		}
	};

	$scope.openMenu = function() {
		$scope.isMenuOpen = true;
		$('nav').css({
			'left' : '0'
		});
	};

	$scope.closeMenu = function() {
		$scope.isMenuOpen = false;
		$('nav').css({
			'left' : '-16em'
		});
	};

	$scope.logout = function() {
		$scope.closeMenu();
		LoginService.logout();
		$location.path('/login');
	};

} ]);