app.factory('ProfileService', function($http, $rootScope, $window) {
	return {
		
		updateProfile : function( userInfo ) {
			return $http.put("/persons/" + $rootScope.personUid, userInfo );
		},

		getProfile : function() {
			var headers = { headers : {
					'session-id' : $rootScope.sessionId,
					'user-token' : $rootScope.token
				}
			}
			var path = "/persons/" + $rootScope.personUid + "?session-id=" + $rootScope.sessionId + "&user-token=" + encodeURIComponent( $rootScope.token );
			return $http.get(path, headers);
		}

	}
});
