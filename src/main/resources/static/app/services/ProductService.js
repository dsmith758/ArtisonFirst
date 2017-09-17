app.factory('ProductService', function($http, $rootScope, $window, $upload) {
	return {
		
		createProduct : function( product ) {
			return $http.post("/products/", product );
		},
		
		updateProduct : function( productUid, product ) {
			return $http.put("/products/" + productUid, product );
		},
		
		deleteProduct : function( productUid ) {
			var path = "/products/" + productUid + $rootScope.authParam;				
			return $http.delete(path);
		},

		getProduct : function( productUid ) {
			var path = "/products/" + productUid + $rootScope.authParam;				
			return $http.get(path);
		},
		
		getProducts : function( companyUid ) {
			var path = "/companies/" + companyUid + "/products/" + $rootScope.authParam;				
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