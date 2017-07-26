app.factory('BusinessService', function($http, $rootScope, $window) {
	return {

		updateBusiness : function( companyUid, organization ) {
			return $http.put("/companies/" + companyUid, organization );

		},

		getBusiness : function() {
				var headers = { headers : {
					'session-id' : $rootScope.sessionId,
					'user-token' : $rootScope.token
				}
			}
			var path = "/persons/" + $rootScope.personUid + "/companies" + "?session-id=" + $rootScope.sessionId + "&user-token=" + encodeURIComponent( $rootScope.token );
			return $http.get(path, headers);
		}
	};
});
