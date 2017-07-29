app.controller('profileController', [ '$rootScope', '$scope', '$location', 'ProfileService', 'LoginService', function($rootScope, $scope, $location, ProfileService, LoginService ) {

	$scope.message = '';
	$scope.header = 'User Profile';
	$scope.subheader = 'Use the form below to manage your profile.';
	$scope.userName = $rootScope.loginName;
	$scope.displayName = $rootScope.displayName;
	$scope.personUid = $rootScope.personUid;

	$scope.registration = {
	   account : {
	        personUid : "",
	        loginName : "",
	        displayName : "",
	        sessionId : "",
	        token : "",
	        authenticated: false
	    },
	    person: {
	        uid : "",
	        type : "PERSON",
	        firstName : "",
	        middleName : "",
	        lastName : "",
	        personalExperience : "",
	        imageUri : "",
	        contactInfo : [
	            {
	                contactType: "",
	                value: ""
	            }	        
	       ]
	    }
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

	$scope.go = function(path) {
		$location.path(path);
	};
	
	$scope.goBack = function(path) {
		$location.path('/main');
	};
	
	$scope.getProfile = function() {
		var promise = ProfileService.getProfile();

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
			$scope.message = "Error retrieving profile";
		});
	};

	$scope.saveProfile = function() {
		var promise = ProfileService.updateProfile( $scope.registration );
		
		promise.then(function(results) {
			if ( results.data.account.authenticated == true ) {
				LoginService.setAuth(results.data);
				$scope.registration = results.data;
				$scope.message = "Profile updated";
			} else {
				LoginService.clearAuth();
				$scope.message = "Authentication lost";
				$location.path('/login');
			}
		}, function(error) {
			$scope.message = "Error saving profile";
		});
	};
	
	$scope.saveProfileImage = function( fileToUpload ) {
		var promise = ProfileService.saveProfileImage( fileToUpload[0] );
		
		promise.then(function(results) {
			if ( results.data.account.authenticated == true ) {
				LoginService.setAuth(results.data);
				$scope.registration = results.data;
				$scope.message = "Profile image updated";
			} else {
				LoginService.clearAuth();
				$scope.message = "Authentication lost";
				$location.path('/login');
			}
		}, function(error) {
			$scope.message = "Error saving profile image";
		});
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
	
	$scope.getProfile();


} ]);