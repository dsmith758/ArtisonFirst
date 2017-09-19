app.controller('businessController', [ '$rootScope', '$scope', '$location', 'BusinessService', 'LoginService', function($rootScope, $scope, $location, BusinessService, LoginService) {

	$scope.message = '';
	$scope.header = 'Business Profile';
	$scope.subheader = 'Use the form below to manage your business profile.';
	$scope.showAddressBoxFlag = true;
	
	$scope.userName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	// BUISNESS DATA
	$scope.registration = {
		account : {
			sessionId : $rootScope.sessionId,
			token : $rootScope.token
		},
		company : {
			uid : "",
			type : "COMPANY",
			companyName : "",
			businessType : "",
			logoUri : ""
		},
		addressInfo : [
			 {
				 uid : "",
				 addressName : "",
				 address1 : "",
				 address2 : "",
				 city : "",
				 state : "",
				 zip : "",
				 country : ""
			}
		]
	};	
	
	$scope.go = function(path) {
		LoginService.go( path );
	};
	
	$scope.goBack = function(path) {
		LoginService.back();
	};

	$scope.createBusiness = function() {
		var promise = BusinessService.createBusiness( $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.company;
			$scope.registration.addressInfo = results.data.addressInfo;
			$scope.message = "Business Profile created";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving business profile";
		});
	};

	$scope.saveBusiness = function() {
		var promise = BusinessService.updateBusiness( $scope.registration.company.uid, $scope.registration );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.company;
			$scope.registration.addressInfo = results.data.addressInfo;
			$scope.message = "Business Profile updated";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving business profile";
		});
	};
	
	$scope.getBusiness = function() {
		var promise = BusinessService.getBusiness();

		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration.account = results.data.account;
			$scope.registration.company = results.data.company;
			$scope.registration.addressInfo = results.data.addressInfo;
			$scope.message = "";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error retrieving business: ";
		});
	};
	
	$scope.saveBusinessLogo = function( fileToUpload ) {
		var promise = BusinessService.saveBusinessLogo( fileToUpload[0], $scope.registration.company.uid );
		
		promise.then(function(results) {
			LoginService.setAuth(results.data);
			$scope.registration = results.data;
			$scope.message = "Logo updated";
		}, function(error) {
			if(response.status === 401) {
				$location.path( "/login" );
			}
			$scope.message = "Error saving logo";
		});
	};
	
	$scope.addAddressInfo = function() {
		$scope.registration.addressInfo.push({
				uid : "",
				addressName : "",
				address1 : "",
				address2 : "",
				city : "",
				state : "",
				zipCode : "",
				country : ""
			}
		);
	};

	$scope.removeAddressInfo = function(idx) {
		$scope.registration.addressInfo.splice(idx, 1);
	};
	
	$scope.getBusiness();

} ]);