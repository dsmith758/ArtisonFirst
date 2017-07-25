app.factory('LoginService', function($http, $rootScope, $window) {

	return {

		login : function(loginName, password) {
			return $http.post('/login', {
				account : {
					loginName : loginName,
					password : password
				}
			});
		},

		logout : function() {
			if ($rootScope.userInfo != null) {
				$rootScope.userInfo = null;
				$http.post('/logout', {
					account : {
						sessionId : $rootScope.sessionId,
						token : $rootScope.token
					}
				});
			}
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
