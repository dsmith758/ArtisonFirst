app.controller('loginController', [ '$rootScope', '$scope', '$location', 'LoginService', 'ProfileService', 'BusinessService', function($rootScope, $scope, $location, LoginService, ProfileService, BusinessService) {

	$scope.message = '';
	$scope.header = 'User Login';
	$scope.subheader = 'LOGIN OR REGISTER A NEW ACCOUNT.';

	$scope.form = {username : '', password : '' };

	$scope.login = function() {
		var promise = LoginService.login( this.form.username, this.form.password);

		promise.then(function(results) {

			if (results.data.account.authenticated == true ) {
				LoginService.setAuth(results.data );
				$location.path('/main');
			} else {
				LoginService.setAuth(null);
				$location.path('/login');
			}
		}, function(error) {
			$location.path('/login');
		})
	}

	$scope.go = function(path) {
		$location.path(path);
	};
	
	$scope.setMessage = function( msg ) {
		$scope.message = msg;
	}

} ]);