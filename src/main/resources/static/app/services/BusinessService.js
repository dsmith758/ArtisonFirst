app.factory('BusinessService', function($http, $rootScope, $window, $upload) {
	return {

		updateBusiness : function( companyUid, organization ) {
			return $http.put("/companies/" + companyUid, organization );

		},

		getBusiness : function() {
			var path = "/persons/" + $rootScope.personUid + "/companies" + $rootScope.authParam;
			return $http.get(path);
		},
		
		saveBusinessLogo : function( fileToUpload ) {
			var path = "/companies/" + $rootScope.personUid + "/logos" + $rootScope.authParam;
			return $upload.upload({
			    url : path,
			    file : fileToUpload,
			    method : 'POST'
			});
		}
	};
});
