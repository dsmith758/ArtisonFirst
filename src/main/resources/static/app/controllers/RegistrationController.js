app.controller('registrationController', [ '$rootScope', '$scope', '$location', 'RegistrationService', 'LoginService', function($rootScope, $scope, $location, RegistrationService, LoginService ) {

	$scope.message = '';
	$scope.header = 'User Registration';
	$scope.subheader = 'USE THE FORM BELOW TO REGISTER.';
	$scope.showAddressBoxFlag = true;
	
	// REGISTRATION
	$scope.registration = {
			account: {
		        loginName: $rootScope.loginName,
		        displayName : $rootScope.displayName,
		        sessionId : $rootScope.sessionId
		    },		
			person : {
				firstName : "",
				middleName : "",
				lastName : "",
				personalExperience  : "",
				contactInfo  : [
					{
						contactType : "",
						value : ""
					}
				]
			},		
			company : {
				companyName : "",
				businessType : ""
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
	
	$scope.contactOptions = [ {
		value : "CELL",
		name : "Cell Phone"
	}, {
		value : "HOME",
		name : "Home Phone"
	}, {
		value : "OFFICE",
		name : "Office Phone"
	}, {
		value : "EMAIL",
		name : "Email"
	}, {
		value : "TWITTER",
		name : "Twitter"
	}, {
		value : "FACEBOOK",
		name : "Facebook"
	}, {
		value : "LINKEDIN",
		name : "LinkedIn"
	} ]

	$scope.saveRegistration = function( ) {
		
		var promise = RegistrationService.create( $scope.registration );
		
		promise.then(function(results) {
			$location.path('/registered');
		}, function(error) {
			$location.path('/register');
		})

	};

	$scope.go = function(path) {
		$location.path(path);
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
	
	$scope.addContactInfo = function() {
		$scope.registration.person.contactInfo.push({
			contactType : "",
			value : ""
		});
	};

	$scope.removeContactInfo = function(item) {
		if ( $scope.registration.person.contactInfo.length == 1 ){
			$scope.registration.person.contactInfo = [];
			return;
		}
		var idx = 0;
		for (var item in $scope.registration.person.contactInfo) {
			idx++;
			if (item.type == item.type && item.value == item.value) {
				$scope.registration.person.contactInfo.splice(idx, 1);
				return;
			}
		}
	};

} ]);