app.factory('RegistrationService', function($http, $window) {
	
	return {
		create : function( registration ) {
			return $http.post('/register', registration );
		}
	}
});
