app.factory('ProfileService', function($http, $rootScope, $window, $upload) {
	return {
		
		updateProfile : function( userInfo ) {
			var path = "/persons/" + $rootScope.personUid;
			return $http.put( path, userInfo );
		},

		getProfile : function() {
			var path = "/persons/" + $rootScope.personUid + $rootScope.authParam;
			return $http.get(path);
		},
		
		saveProfileImage : function( fileToUpload ) {
			var path = "/persons/" + $rootScope.personUid + "/images" + $rootScope.authParam;
			return $upload.upload({
			    url : path,
			    file : fileToUpload,
			    method : 'POST'
			});
		}
		
	}
});
