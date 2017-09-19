app.factory('LoginService', function($http, $rootScope, $window, $location) {

	var history = [];
	var current = '';
	
	return {
		
		back : function() {
	        if ( history.length > 0 ) {
	        	current = history[ history.length - 1 ];
	        	history.splice( history.length -1 );
	        }
	        if ( current == '' ) {
	        	current = '/main';
	        }
	        $location.path( current );
		},
	
		go : function( location ) {
			if ( current != '' ) {
				history.push( current );				
			}
			current = location;
			$location.path( location );
		},
		
		clearHistory : function() {
			history = [];
			current = '';
		},

		login : function(loginName, password) {
			return $http.post('/login', {
				account : {
					loginName : loginName,
					password : password
				}
			});
		},

		logout : function() {
			$http.post('/logout', {
				account : {
					sessionId : $rootScope.sessionId,
					token : $rootScope.token
				}
			});
			this.clearAuth();
		},

		isLoggedIn : function() {
			return ($rootScope.authenticated);
		},

		isAuthenticated : function() {
			if ($rootScope.userInfo == null) {
				return $http.post('/isAuthenticated', {
					account : {
						sessionId : $rootScope.sessionId,
						token : $rootScope.token
					}
				});
			}
			return null;
		},

		setAuth : function(results) {
			if (results != null) {
				$rootScope.authenticated = results.account.authenticated;
				$rootScope.sessionId = results.account.sessionId;
				$rootScope.token = results.account.token;
				$rootScope.displayName = results.account.displayName;
				$rootScope.loginName = results.account.loginName;
				$rootScope.personUid = results.account.personUid;
				$rootScope.companyUid = results.account.companyUid;
				$rootScope.authParam = "?session-id=" + $rootScope.sessionId + "&user-token=" + encodeURIComponent( $rootScope.token );
			} else {
				this.clearAuth();
			}
		},

		clearAuth : function() {
			$rootScope.authenticated = false;
			$rootScope.sessionId = '';
			$rootScope.token = '';
			$rootScope.displayName = '';
			$rootScope.loginName = '';
			$rootScope.personUid = '';
			$rootScope.companyUid = '';
			$rootScope.authParam = '';
			this.clearHistory();
		},

		getAuth : function() {
			return $rootScope.userInfo;
		},

		getSessionId : function() {
			return $rootScope.sessionId;
		},

		getPersonUid : function() {
			return $rootScope.personUid;
		},

		getLoginName : function() {
			return $rootScope.loginName;
		},
		
		getUserName : function() {
			return $rootScope.loginName;
		},

		getDisplayName : function() {
			return $rootScope.displayName;
		}
	}
});
