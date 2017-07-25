app.directive( "preLoginDirective", function() {
	return {
		link : function( scope, element, attrs ) {
				scope.pageBack = attrs.pageBack;
				scope.pageController = attrs.pageController;
				scope.pageHeader = attrs.pageHeader;
				scope.pageSubtitle = attrs.pageSubtitle;
				scope.pageBody = attrs.pageBody;
		},
		// template : "<ng-include src='app/views/templates/pre-login-template.html'/>"
		template : "hello world."
	}
})