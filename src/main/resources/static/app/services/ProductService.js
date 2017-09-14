app.factory('ProductService', function($http, $rootScope, $window, $upload) {
	return {
		
		createProduct : function( product ) {
			return $http.put("/products/", product );
		},
		
		updateProduct : function( productUid, product ) {
			return $http.put("/products/" + productUid, product );
		},

		getProduct : function( productUid ) {
			var path = "/products/" + productUid + $rootScope.authParam;				
			return $http.get(path);
		},
		
		saveProductImage : function( fileToUpload, productUid ) {
			var path = "/products/" + productUid + "/images" + $rootScope.authParam;
			return $upload.upload({
			    url : path,
			    file : fileToUpload,
			    method : 'POST'
			});
		}
		
	};
});