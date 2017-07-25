app.controller('businessController', [ '$rootScope', '$scope', '$location', 'BusinessService', 'LoginService', function($rootScope, $scope, $location, BusinessService, LoginService) {

	$scope.message = '';
	$scope.header = 'Business Profile';
	$scope.subheader = 'Use the form below to manage your business profile.';
	$scope.showAddressBoxFlag = true;
	
	$scope.userName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;

	// REGISTRATION
	$scope.registration = {
		account: {
	        personUid: "",
	        loginName: "",
	        displayName: "",
	    },
		company : {
			companyName : "",
			businessType : ""
		},
		addressInfo : [
			 {
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
		$location.path(path);
	};
	
	$scope.goBack = function(path) {
		$location.path('/main');
	};

	$scope.saveBusiness = function() {
		var promise = BusinessService.updateBusiness( $scope.registration.company.uid, $scope.registration );
		
		promise.then(function(results) {
			if ( results.data.account.authenticated == true ) {
				LoginService.setAuth(results.data);
				$scope.registration.account = results.data.account;
				$scope.registration.organization.company = results.data.company;
				$scope.registration.organization.addressInfo = results.data.addressInfo;
				$scope.message = "Business Profile updated";
			} else {
				LoginService.clearAuth();
				$scope.message = "Authentication lost";
				$location.path('/login');
			}
		}, function(error) {
			$scope.message = "Error retrieving business profile";
		});
	}
	
	$scope.getBusiness = function() {
		var promise = BusinessService.getBusiness();

		promise.then(function(results) {
			if ( results.data.account.authenticated == true ) {
				LoginService.setAuth(results.data);
				$scope.registration = results.data;
				$scope.message = "";
			} else {
				LoginService.clearAuth();
				$scope.message = "Authentication lost";
				$location.path('/login');
			}
		}, function(error) {
			$scope.message = "Error saving business profile";
		});
	}

	$scope.removeAddressInfo = function(idx) {
		$scope.registration.organization.addressInfo.splice(idx, 1);
	};

	$scope.toggleAddressInfo = function(item) {
		for (var address in $scope.registration.organization.addressInfo ) {
			if (address.addressName == item.addressName) {
				address.toggle = !address.toggle
				return;
			}
		}
	};
	
	$scope.getBusiness();

} ]);