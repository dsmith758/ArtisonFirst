app.controller('productController', [ '$rootScope', '$scope', '$location', 'LoginService', 'ProductService', function($rootScope, $scope, $location, LoginService, ProductService ) {
	
	$scope.message = "";
	$scope.userName = LoginService.getUserName();
	$scope.showList = true;
	
	// PRODUCT DATA
	$scope.registration = {
		account : {
			sessionId : $rootScope.sessionId,
			token : $rootScope.token
		},
	    list : [
	    ],
	    product : {},
		company : {
			uid : "",
			type : "COMPANY",
			companyName : "",
			businessType : "",
			logoUri : ""
		}
	};
	
	$scope.product = {
            uid : "",
            type : "PRODUCT",
            name : "",
            description : "",
            imageUri : "",
            status : ""
	};
	
	$scope.list = [];
	
	$scope.go = function(path) {
		$location.path(path);
	};
	
	$scope.goBack = function(path) {
		$location.path('/main');
	};
	
	$scope.toggleList = function() {
		$scope.showList = !$scope.showList;
		if ( $scope.showList ) {
			$scope.getProducts();
		}
	};
	
	$scope.editProduct = function( item ) {
		$scope.getProduct( $scope.registration.list[item].uid );
		$scope.toggleList();
	};
	
	$scope.newProduct = function() {
		$scope.product = {
	            uid : "",
	            type : "PRODUCT",
	            name : "",
	            description : "",
	            imageUri : "",
	            status : ""
		};
		$scope.toggleList();
	};
	
	$scope.saveProduct = function() {
		if ( $scope.product.uid != '' ) {
			$scope.updateProduct();
		}
		else {
			$scope.createProduct();
		}
	}
	
	$scope.createProduct = function() {
		$scope.registration.product = $scope.product;
		var promise = ProductService.createProduct( $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.product = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "Product created";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error creating product";
		});
	};

	$scope.updateProduct = function() {
		$scope.registration.product = $scope.product;
		var promise = ProductService.updateProduct( $scope.product.uid, $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.product = results.data.product;
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
			$scope.product = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving product. ";
		});
	};
	
	$scope.getProducts = function( ) {
		var promise = ProductService.getProducts( $rootScope.companyUid );

		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.list = results.data.list;
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
		var promise = ProductService.saveProductImage( fileToUpload[0], $scope.registration.product.uid );
		
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
	
	$scope.getProducts();
	
} ]);