app.factory('BusinessService', function($http, $rootScope, $window, $upload) {
	return {

		updateBusiness : function( companyUid, organization ) {
			return $http.put("/companies/" + companyUid, organization );

		},

		getBusiness : function() {
			if ( $rootScope.companyUid == null || $rootScope.companyUid == '' ) {
				var path = "/persons/" + $rootScope.personUid + "/companies" + $rootScope.authParam;				
				return $http.get(path);
			}
			var path = "/companies/" + $rootScope.companyUid + $rootScope.authParam;				
			return $http.get(path);
		},
		
		saveBusinessLogo : function( fileToUpload, companyUid ) {
			var path = "/companies/" + companyUid + "/logos" + $rootScope.authParam;
			return $upload.upload({
			    url : path,
			    file : fileToUpload,
			    method : 'POST'
			});
		}
	};
});
