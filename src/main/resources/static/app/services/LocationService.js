app.factory('LocationService', function($http, $window) {
	var location = [{
		"uid" : "",
		"address1" : "",
		"address2" : "",
		"city" : "",
		"state" : "",
		"zipCode" : "",
		"country" : ""
	}];
	
	return {

		get : function() {
			return location;
		},
		
		update : function() {
			
		},
		
		create : function() {
			
		}
	};
});