app.controller('productController', [ '$rootScope', '$scope', '$location', '$anchorScroll', 'LoginService', 'ProductService', function($rootScope, $scope, $location, $anchorScroll,  LoginService, ProductService ) {
	
	$scope.message = "";
	$scope.userName = LoginService.getUserName();
	$scope.showList = true;
	$scope.showImageControl = false;
	
	// PRODUCT DATA
	$scope.registration = {
		account : {
			sessionId : $rootScope.sessionId,
			token : $rootScope.token
		},
	    list : [],
	    product : {},
		company : {
			uid : "",
			type : "COMPANY",
			companyName : "",
			businessType : "",
			logoUri : ""
		}
	};
	
	$scope.list = [];

	$scope.product = {
            uid : "",
            type : "PRODUCT",
            name : "",
            itemNumber : "",
            description : "",
            fields : [],
            imageUri : "",
            status : "",
            image : {}
	};
	
    $scope.fields =	[{
		field : {
			label : ""
		},
		value : ""
	}];

	$scope.go = function(path) {
		LoginService.go( path );
	};
	
	$scope.goBack = function(path) {
		LoginService.back();
	};
	
	$scope.toggleList = function() {
		$scope.showList = !$scope.showList;
		if ( $scope.showList ) {
			$scope.showImageControl = false;
			$scope.getProducts();
		}
	};
	
	$scope.editProduct = function( item ) {
		$scope.getProduct( $scope.registration.list[item].uid );
		$scope.showImageControl = true;
		$scope.toggleList();
	};
	
	$scope.newProduct = function() {
		$scope.resetRecord();
		$scope.showImageControl = false;
		$scope.toggleList();
	};
	
	$scope.resetRecord = function() {
		$scope.product = {
		        uid : "",
		        type : "PRODUCT",
		        name : "",
		        itemNumber : "",
		        description : "",
	            fields : [],
	  	        imageUri : "",
		        status : "",
		        image : {}
			};
		    $scope.fields =	[{
	    		field : {
	    			label : ""
	    		},
	    		value : ""
		    }];		
	}
	
	$scope.saveProduct = function() {
		if ( $scope.product.uid != '' ) {
			$scope.updateProduct();
		}
		else {
			$scope.createProduct();
		}
	}
	
	$scope.createProduct = function() {
		$scope.product.fields = $scope.fields;
		$scope.registration.product = $scope.product;
		var promise = ProductService.createProduct( $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.product = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "Product created";
			$scope.showImageControl = true;
			$location.hash( "top" );
			$anchorScroll();
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error creating product";
		});
	};

	$scope.updateProduct = function() {
		$scope.product.fields = $scope.fields;
		$scope.registration.product = $scope.product;
		var promise = ProductService.updateProduct( $scope.product.uid, $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.product = results.data.product;
			$scope.registration.company = results.data.company;
			$scope.message = "Product updated";
			$location.hash( "top" );
			$anchorScroll();
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error updating product.";
		});
	};
	
	$scope.confirmDelete = function( item ) {;
		var msg = "Click OK to confirm the deletion of: \n'" + $scope.registration.list[item].name + "'?"
		if ( confirm( msg ) ) {
			$scope.deleteProduct( item );
		}

	};
 
	$scope.deleteProduct = function( item ) {
		var promise = ProductService.deleteProduct( $scope.registration.list[item].uid );

		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.company;
			$scope.message = "";
			$scope.getProducts();
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving product. ";
		});
	};
	
	$scope.getProduct = function( productUid ) {
		$scope.resetRecord();
		var promise = ProductService.getProduct( productUid );

		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.product = results.data.product;
			if ( results.data.product.fields != null ) {
				$scope.fields = results.data.product.fields;			
			}
			$scope.registration.company = results.data.company;
			$scope.message = "";
			$location.hash( "top" );
			$anchorScroll();
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
		var promise = ProductService.saveProductImage( fileToUpload[0], $scope.product.uid );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.product.imageUri = results.data.product.imageUri;
			$scope.message = "Logo updated";
			$location.hash( "top" );
			$anchorScroll();
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error saving product image";
		});
	};
	
	$scope.addField = function() {
		$scope.fields.push({
        		field : {
        			label : ""
        		},
        		value : ""
		});
	};

	$scope.removeField = function(idx) {
		if ( $scope.fields.length == 1 ){
			$scope.fields = [];
			return;
		}
		$scope.fields.splice(idx,1);
	};
	
	$scope.getProducts();
	
} ]);