app.controller('productController', [ '$scope', '$location', 'LoginService', 'ProductService', function($scope, $location, LoginService. ProductService ) {

	$scope.message = "";
	$scope.userName = LoginService.getUserName();
	
	// PRODUCT DATA
	$scope.registration = {
		account : {
			sessionId : $rootScope.sessionId,
			token : $rootScope.token
		},
		product : {
			uid : "",
			type : "PRODUCT",
			name : "",
			description : "",
			imageUri : ""
		},
		company : {
			uid : "",
			type : "COMPANY",
			companyName : "",
			businessType : "",
			logoUri : ""
		}
	};
	
	$scope.go = function(path) {
		$location.path(path);
	};
	
	$scope.goBack = function(path) {
		$location.path('/main');
	};
	
	$scope.createProduct = function() {
		var promise = ProductService.createProduct( $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "Product created";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error creating product";
		});
	};

	$scope.saveProduct = function() {
		var promise = ProductService.updateProduct( $scope.registration.product.uid, $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "Product updated";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error updating product.";
		});
	};
	
	$scope.getProduct = function( productUid ) {
		var promise = ProductService.getProduct( productUid );

		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving product. ";
		});
	};
	
	$scope.saveProductImage = function( fileToUpload ) {
		var promise = BusinessService.saveProductImage( fileToUpload[0], $scope.registration.product.uid );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration = results.data;
			$scope.message = "Logo updated";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error saving product image";
		});
	};
} ]);